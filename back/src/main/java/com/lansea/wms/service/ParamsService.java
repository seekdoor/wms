package com.lansea.wms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParamsService {


    @Value("${ec.md5Secret}")
    private String md5Secret;
    @Value("${ec.tokenSecret}")
    private String tokenSecret;

    public String getMd5Secret() {
        return md5Secret;
    }

    public void setMd5Secret(String md5Secret) {
        this.md5Secret = md5Secret;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}
