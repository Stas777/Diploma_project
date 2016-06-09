package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.rest.model.Config;


public interface ConfigService {

    /**
     *
     * @param publicOnly - true to return only public records
     * @return
     */
    public Config readConfig(boolean publicOnly);
    public Config readConfig();
}
