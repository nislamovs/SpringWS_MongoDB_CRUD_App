package com.soap.soapserver.configuration;

import com.soap.soapserver.domain.dto.properties.WsClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;

import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

//	private final String PATH_PREFIX = "/api/v1/ws";
//	private final String TARGET_NAMESPACE_PREFIX = "http://localhost:8082" + PATH_PREFIX;

	@Bean
	@ConfigurationProperties(prefix = "client.soap.ws")
	public WsClientProperties wsClientProperties() {
		return new WsClientProperties();
	}

	@Bean
	public SoapEnvelopeLoggingInterceptor soapEnvelopeLoggingInterceptor() {
		SoapEnvelopeLoggingInterceptor interceptor = new SoapEnvelopeLoggingInterceptor();
		interceptor.setLogRequest(true);
		interceptor.setLogResponse(true);
		return interceptor;
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
		validatingInterceptor.setValidateRequest(true);
		validatingInterceptor.setValidateResponse(true);

		validatingInterceptor.setXsdSchema(personsSchema());
//		validatingInterceptor.setXsdSchema(statsSchema());

		interceptors.add(validatingInterceptor);
		interceptors.add(soapEnvelopeLoggingInterceptor());
	}



	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext,
															WsClientProperties wsClientProperties) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		servlet.setTransformSchemaLocations(true);
		return new ServletRegistrationBean(servlet, wsClientProperties.getPathPrefix() + "/*");
	}



	@Bean(name = "persons")
	public DefaultWsdl11Definition personsWsdlDefinition(XsdSchema personsSchema, WsClientProperties wsClientProperties) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PersonsPort");
		wsdl11Definition.setLocationUri(wsClientProperties.getPathPrefix() + "/persons");
		wsdl11Definition.setTargetNamespace(wsClientProperties.getTargetNamespace() + "/persons");
		wsdl11Definition.setSchema(personsSchema);
		return wsdl11Definition;
	}

		@Bean(name = "quests")
	public DefaultWsdl11Definition questsWsdlDefinition(XsdSchema questsSchema, WsClientProperties wsClientProperties) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("QuestsPort");
		wsdl11Definition.setLocationUri(wsClientProperties.getPathPrefix() + "/quests");
		wsdl11Definition.setTargetNamespace(wsClientProperties.getTargetNamespace() + "/quests");
		wsdl11Definition.setSchema(questsSchema);
		return wsdl11Definition;
	}

	@Bean(name = "skills")
	public DefaultWsdl11Definition skillsWsdlDefinition(XsdSchema skillsSchema, WsClientProperties wsClientProperties) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SkillsPort");
		wsdl11Definition.setLocationUri(wsClientProperties.getPathPrefix() + "/skills");
		wsdl11Definition.setTargetNamespace(wsClientProperties.getTargetNamespace() + "/skills");
		wsdl11Definition.setSchema(skillsSchema);
		return wsdl11Definition;
	}

	@Bean(name = "stats")
	public DefaultWsdl11Definition statsWsdlDefinition(XsdSchema statsSchema, WsClientProperties wsClientProperties) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("StatsPort");
		wsdl11Definition.setLocationUri(wsClientProperties.getPathPrefix() + "/stats");
		wsdl11Definition.setTargetNamespace(wsClientProperties.getTargetNamespace() + "/stats");
		wsdl11Definition.setSchema(statsSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema personsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd_schemas/persons.xsd"));
	}

	@Bean
	public XsdSchema questsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd_schemas/quests.xsd"));
	}

	@Bean
	public XsdSchema skillsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd_schemas/skills.xsd"));
	}

	@Bean
	public XsdSchema statsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd_schemas/stats.xsd"));
	}
}
