package com.soap.soapserver.domain.dto.properties;

import lombok.Data;

@Data
public class WsClientProperties {

    private String pathPrefix;

    private String targetNamespace;
}
