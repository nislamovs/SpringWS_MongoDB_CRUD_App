package com.soap.soapclient.configuration;

import com.soap.soapclient.domain.dto.properties.WsClientProperties;
import com.soap.soapclient.domain.dto.properties.WsSslProperties;
import com.soap.soapclient.services.PersonsClient;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import javax.net.ssl.SSLContext;
import java.util.Arrays;

@EnableWs
@Configuration
@RequiredArgsConstructor
public class WebServiceConfig extends WsConfigurerAdapter {

//    private final String PATH_PREFIX = "/api/v1/ws";
//    private final String TARGET_NAMESPACE_PREFIX = "http://localhost:8082" + PATH_PREFIX;

    private final Environment environment;

//    @Value("${client.ssl.trust-store}")
//    private Resource trustStore;
//
//    @Value("${client.ssl.trust-store-password}")
//    private String trustStorePassword;
//
//    @Value("${client.ssl.key-store}")
//    private Resource keyStore;
//
//    @Value("${client.ssl.key-store-password}")
//    private String keyStorePassword;
//
//    @Value("${client.ssl.key-password}")
//    private String keyPassword;

    @Bean
    @ConfigurationProperties(prefix = "client.soap.ws")
    public WsClientProperties wsClientProperties() {
        return new WsClientProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "client.ssl")
    public WsSslProperties wsSslProperties() {
        return new WsSslProperties();
    }

    @Bean
    public SaajSoapMessageFactory messageFactory() {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion(SoapVersion.SOAP_11);
        return messageFactory;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(WebServiceTemplateBuilder builder,
                                                 Jaxb2Marshaller marshaller,
                                                 SaajSoapMessageFactory messageFactory,
                                                 WsClientProperties wsClientProperties) throws Exception {

        return builder
                .setDefaultUri(wsClientProperties.getTargetNamespace())
                .setMarshaller(marshaller)
                .setUnmarshaller(marshaller)
                .setWebServiceMessageFactory(messageFactory)
                .setCheckConnectionForFault(true)
                .messageSenders(httpComponentsMessageSender())
                .build();
    }

    @Bean
    public HttpComponentsMessageSender httpComponentsMessageSender() throws Exception {
        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setHttpClient(httpClient());

        return httpComponentsMessageSender;
    }

    public HttpClient httpClient() throws Exception {
        return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory())
                .addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor()).build();
    }

    public SSLConnectionSocketFactory sslConnectionSocketFactory() throws Exception {
        // NoopHostnameVerifier essentially turns hostname verification off as otherwise following error
        // is thrown: java.security.cert.CertificateException: No name matching localhost found
        return (Arrays.toString(environment.getActiveProfiles()).contains("local"))
                ? new SSLConnectionSocketFactory(sslContext(), NoopHostnameVerifier.INSTANCE)
                : new SSLConnectionSocketFactory(sslContext(), new DefaultHostnameVerifier());
    }

    public SSLContext sslContext() throws Exception {

        WsSslProperties wsSslProperties = wsSslProperties();

        return SSLContextBuilder.create().loadKeyMaterial(
                wsSslProperties.getKeyStore().getFile(),
                wsSslProperties.getKeyStorePassword().toCharArray(),
                wsSslProperties.getKeyPassword().toCharArray()).loadTrustMaterial(
                        wsSslProperties.getTrustStore().getFile(),
                        wsSslProperties.getTrustStorePassword().toCharArray())
            .build();
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.soap.soapclient.wsdl");
        return marshaller;
    }

    @Bean
    public PersonsClient personsClient(Jaxb2Marshaller marshaller,
                                       WebServiceTemplate webServiceTemplate,
                                       WsClientProperties wsClientProperties) {

        PersonsClient client = new PersonsClient(webServiceTemplate, wsClientProperties);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }



}
