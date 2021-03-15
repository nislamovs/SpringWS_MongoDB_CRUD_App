package com.soap.soapclient.domain.dto.properties;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class WsSslProperties {

    private Resource trustStore;

    private String trustStorePassword;

    private Resource keyStore;

    private String keyStorePassword;

    private String keyPassword;
}
