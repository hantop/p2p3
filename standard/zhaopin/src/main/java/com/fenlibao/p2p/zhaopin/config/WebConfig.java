package com.fenlibao.p2p.zhaopin.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fenlibao.p2p.weixin.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/6/18.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addRedirectViewController("/","relationship/list");
        registry.addViewController("/login").setViewName("sign/login");
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
//        messageConverters.add(createXmlHttpMessageConverter());
//        messageConverters.add(customJackson2HttpMessageConverter());
////        messageConverters.add(new MappingJackson2HttpMessageConverter());
//
//        super.configureMessageConverters(messageConverters);
//    }
//
//    @Bean
//    public HttpMessageConverter<Object> createXmlHttpMessageConverter() {
//        MarshallingHttpMessageConverter xmlConverter =
//                new MarshallingHttpMessageConverter();
//
//        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
//        xstreamMarshaller.setAnnotatedClasses(Message.class);
//
//        xmlConverter.setMarshaller(xstreamMarshaller);
//        xmlConverter.setUnmarshaller(xstreamMarshaller);
//
//        return xmlConverter;
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jsonConverter.setObjectMapper(objectMapper);
//        return jsonConverter;
//    }

//    @Bean
//    public CommonsMultipartResolver filterMultipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(-1);
//        return multipartResolver;
//    }

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer(){
//        return new MyCustomizer();
//    }
//
//    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {
//
//        @Override
//        public void customize(ConfigurableEmbeddedServletContainer container) {
//            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
//        }
//    }
}
