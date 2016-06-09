package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.rest.model.*;

public interface MailService {
    void pushToSend(Long userId, Sample sample, long actionId);

}
