package com.epam.task.microservice.EPAMT1Microservice.filter;

import com.epam.task.microservice.EPAMT1Microservice.service.TrainingWorkService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;


public class TransactionIdFilter implements Filter {

    private static final String TRANSACTION_ID = "Transaction-ID";

    private static final Logger log = LoggerFactory.getLogger(TrainingWorkService.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String transactionId = httpRequest.getHeader(TRANSACTION_ID);

        if (transactionId != null) {
            MDC.put(TRANSACTION_ID, transactionId);
            log.info("Transaction-ID set in filter: {}", transactionId);
        } else {
            MDC.put(TRANSACTION_ID, "NO TRANSACTION");
            log.info("Transaction-ID set in filter: NO TRANSACTION");

        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }

}