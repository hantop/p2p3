package com.fenlibao.p2p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Administrator on 2015/6/15.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(WebAppRootListener.class);
        servletContext.addListener(HttpSessionEventPublisher.class);
        super.onStartup(servletContext);
    }

//    @Override
//    protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
//        servletContext.addListener(WebAppRootListener.class);
//        return super.createRootApplicationContext(servletContext);
//    }

//    @Bean
//    public ServletListenerRegistrationBean servletListener() {
//        ServletListenerRegistrationBean servletListener =  new ServletListenerRegistrationBean();
//        servletListener.setListener(new WebAppRootListener());
//        return servletListener;
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class,args);
    }


}
