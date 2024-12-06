package com.epam.task.microservice.EPAMT1Microservice.config;

import com.epam.task.microservice.EPAMT1Microservice.filter.TransactionIdFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private static final Logger log = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public FilterRegistrationBean<TransactionIdFilter> transactionIdFilter(){
        FilterRegistrationBean<TransactionIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TransactionIdFilter());
        registrationBean.addUrlPatterns("/training-work/*");
        log.info("TransactionIdFilter registered for /training-work/* URL patterns");
        return  registrationBean;
    }

}
