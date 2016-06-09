package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.ConfigDAO;
import com.bsu.mvt.server.core.entity.ConfigEntity;
import com.bsu.mvt.server.core.service.ConfigService;
import com.bsu.mvt.server.rest.model.Config;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.List;

/**
 * User: Dmitry
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    @Qualifier("configDAO")
    private ConfigDAO configDAO;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Config readConfig(boolean publicOnly) {
        List<ConfigEntity> list = configDAO.listConfig();
        if (list == null) {
            return null;
        }
        Config res = new Config();

        for (ConfigEntity e : list) {
            if (ConfigEntity.FTP_BASEPATH.equals(e.getName())) {
                res.setFtpBasePath(e.getValue());
            } else if (ConfigEntity.FTP_URL.equals(e.getName())) {
                res.setFtpUrl(e.getValue());
            } else if (ConfigEntity.FTP_USER.equals(e.getName())) {
                res.setFtpUser(e.getValue());
            } else if (ConfigEntity.FTP_PASSWORD.equals(e.getName())) {
                res.setFtpPassword(e.getValue());
            }

            if (!publicOnly) {
                if (ConfigEntity.EMAIL_NOTIFY_LIST.equals(e.getName())) {
                    res.setEmailNotifyList(e.getValue());
                } else if (ConfigEntity.EMAIL_NOTIFY_STATUS.equals(e.getName())) {
                    res.setEmailNotifyStatus(e.getValue());
                } else if (ConfigEntity.EMAIL_LOGIN.equals(e.getName())) {
                    res.setEmailLogin(e.getValue());
                } else if (ConfigEntity.EMAIL_PASSWORD.equals(e.getName())) {
                    res.setEmailPassword(e.getValue());
                } else if (ConfigEntity.EMAIL_SMTP_HOST.equals(e.getName())) {
                    res.setEmailSmtpHost(e.getValue());
                } else if (ConfigEntity.EMAIL_SMTP_PORT.equals(e.getName())) {
                    res.setEmailSmtpPort(e.getValue());
                }
            }
        }

        return res;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Config readConfig() {
        return readConfig(true);
    }
}
