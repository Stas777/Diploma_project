package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.core.util.*;
import com.bsu.mvt.server.rest.model.*;
import com.bsu.mvt.server.rest.model.constant.*;
import org.apache.commons.mail.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("mailService")
public class MailServiceImpl extends BaseServiceImpl implements MailService {
    private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    @Qualifier("configService")
    private ConfigService configService;

    @Autowired
    @Qualifier("sampleService")
    private SampleService sampleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CatalogService catalogService;

    // uuid 2 login
    private TtlHashMap<String, Long> ttlHashMap = new TtlHashMap<String, Long>(TimeUnit.MINUTES, 60L);
    private HashMap<Long, String> id2uuid = new HashMap<>();

    @Override
    public void pushToSend(Long userId, Sample s, long actionId) {
        try {
            Config c = configService.readConfig(false);
            String emailNotifyStatus = c.getEmailNotifyStatus();
            if (MailConstants.MAIL_NOTIFY_STATUS_DISABLE.equals(emailNotifyStatus)) {
                return;
            } else if (MailConstants.MAIL_NOTIFY_STATUS_ENABLE.equals(emailNotifyStatus)) {
                User user = userService.readUser(userId);
                String desc = s.getDescription();
                List<SensorDataFile> dataFileList = s.getDataFileList();

                Mail mail = new Mail();

                Sport sport = catalogService.readSport(s.getSport().getId());

                mail.setTo(c.getEmailNotifyList());
                mail.setSubj(buildSubj(s, sport, actionId));
                mail.setBody(buildBody(s, sport, user, actionId));

                new Thread(new MailTread(mail, c)).start();

            } else {
                logger.warn("MailServiceImpl.pushToSend: unknown notify status : " + emailNotifyStatus);
            }
        } catch (MVTException e) {
            logger.error("MailServiceImpl.pushToSend: error while trying to send mail.", e);
        }
    }

    private String buildSubj(Sample s, Sport sport, long actionId) {
//        public static final String MAIL_TEMPLATE_SUBJ = "Sample sample_id action_name for sport_name";
//        public static final String MAIL_TEMPLATE_BODY = "User: user_name action_name sample with id: sample_id for sport: sport_name \n QK: qk_name \n Description: sample_desc";

        String r = MailConstants.MAIL_TEMPLATE_SUBJ;
        r = r.replaceAll("sample_id", Long.toString(s.getId()));
        r = r.replaceAll("action_name", getActionName(actionId));
        r = r.replaceAll("sport_name", sport != null ? sport.getName() : "empty_sport");
        return r;
    }
    private String buildBody(Sample s, Sport sport, User u, long actionId) {
        String r = MailConstants.MAIL_TEMPLATE_BODY;
        r = r.replaceAll("user_name", u != null ? u.getFistName() + " " + u.getLastName() : "empty_user");
        r = r.replaceAll("action_name", getActionName(actionId));
        r = r.replaceAll("sample_id", Long.toString(s.getId()));
        r = r.replaceAll("sport_name", sport != null ? sport.getName() : "empty_sport");
        r = r.replaceAll("qk_name", getQKName(s));
        r = r.replaceAll("usage_name", s.getUsage() != null ? s.getUsage().name() : "empty");
        r = r.replaceAll("sample_desc", s.getDescription() != null ? s.getDescription() : "empty");
        return r;
    }

    private String getActionName(long actionId) {
        if (ActionConstants.ACTION_CREATE_SAMPLE == actionId) {
            return "create";
        } else if (ActionConstants.ACTION_UPDATE_SAMPLE == actionId) {
            return "update";
        } else {
            return "unknown action";
        }
    }

    private String getQKName(Sample s) {
        String r = "";

        List<SensorDataFile> sensorDataFiles = s.getDataFileList();

        if (sensorDataFiles != null) {
            for (SensorDataFile sdf : sensorDataFiles) {
                if (sdf.getQualifierKey() != null) {
                    QualifierKey qualifierKey = catalogService.readQualifierKey(sdf.getQualifierKey().getId());
                    r +=  qualifierKey != null ? qualifierKey.getName() + ", " : "empty ";
                }
            }
        }

        if (r.isEmpty()) {
            r = "empty_sdf_list";
        }

        return r;
    }

    private class MailTread implements Runnable {
        private Mail mail;
        private Config config;

        private MailTread(Mail mail, Config config) {
            this.mail = mail;
            this.config = config;
        }

        @Override
        public void run() {
            logger.debug("Start sending mail: " + mail);

            try {
                Email email = new SimpleEmail();
                email.setHostName(config.getEmailSmtpHost());
                email.setSmtpPort(Integer.parseInt(config.getEmailSmtpPort()));
                email.setAuthenticator(new DefaultAuthenticator(config.getEmailLogin(), config.getEmailPassword()));
//                email.setSSLOnConnect(false);
                email.setFrom(config.getEmailLogin());
                email.setSubject(mail.getSubj());
                email.setMsg(mail.getBody());
                email.addTo(config.getEmailNotifyList().split(","));
                email.send();
            } catch (EmailException e) {
                logger.debug("error while sending mail: ", e);
            }
        }
    }
}
