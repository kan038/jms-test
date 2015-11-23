package com.example.jms.domain.service;

import javax.inject.Inject;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.util.ErrorHandler;



public class TodoErrorHandler implements ErrorHandler {

    @Inject
    private JmsTemplate jmsTemplate;

    @Override
    public void handleError(Throwable t) {
       
    	if ( t.getCause() instanceof MethodArgumentNotValidException) {
    		System.out.println("MethodArgumentNotValidException例外が発生しました。" + t.getCause().getMessage());
    		
    		jmsTemplate.convertAndSend("ReplyTestQueue3", "MethodArgumentNotValidException例外が発生しました。");
    		
    	} else {
    		System.out.println(t.getMessage() + "が発生しました。");
    	}
    	
    	
    	
    	
    }
}