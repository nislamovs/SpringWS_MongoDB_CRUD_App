package com.soap.soapserver.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
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

	private final String PATH_PREFIX = "/api/v1/ws";
	private final String TARGET_NAMESPACE_PREFIX = "http://localhost:8082" + PATH_PREFIX;

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
		validatingInterceptor.setValidateRequest(true);
		validatingInterceptor.setValidateResponse(true);

		validatingInterceptor.setXsdSchema(personsSchema());
//		validatingInterceptor.setXsdSchema(statsSchema());

		interceptors.add(validatingInterceptor);
	}

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, PATH_PREFIX + "/*");
	}

	@Bean(name = "persons")
	public DefaultWsdl11Definition personsWsdlDefinition(XsdSchema personsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PersonsPort");
		wsdl11Definition.setLocationUri(PATH_PREFIX + "/persons");
		wsdl11Definition.setTargetNamespace(TARGET_NAMESPACE_PREFIX + "/persons");
		wsdl11Definition.setSchema(personsSchema);
		return wsdl11Definition;
	}

//	@Bean(name = "stats")
//	public DefaultWsdl11Definition statsWsdlDefinition(XsdSchema statsSchema) {
//		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//		wsdl11Definition.setPortTypeName("StatsPort");
//		wsdl11Definition.setLocationUri(PATH_PREFIX + "/stats");
//		wsdl11Definition.setTargetNamespace(TARGET_NAMESPACE_PREFIX + "/stats");
//		wsdl11Definition.setSchema(statsSchema);
//		return wsdl11Definition;
//	}

	@Bean
	public XsdSchema personsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd_schemas/persons.xsd"));
	}

//	@Bean
//	public XsdSchema statsSchema() {
//		return new SimpleXsdSchema(new ClassPathResource("xsd_schemas/stats.xsd"));
//	}
}
