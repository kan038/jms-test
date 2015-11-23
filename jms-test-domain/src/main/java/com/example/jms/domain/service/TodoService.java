package com.example.jms.domain.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.jms.domain.model.Todo;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class TodoService {


    @JmsListener(destination = "TestQueue2")
    @SendTo("ReplyTestQueue2")
    public Todo receive1(Todo todo, @Headers MessageHeaders headers) throws InterruptedException {
    	System.out.println("Received TodoID : " + todo.getTodoId());
    	System.out.println("Received headers : " + headers);
      
    	todo.setTodoId("2");
    	todo.setFinished(true);
    	
        return todo;

    }

    
    @JmsListener(destination = "TestQueue3")
    //@SendTo("ReplyTestQueue3")
    public void receive2(@Validated Todo todo, @Headers MessageHeaders headers) throws InterruptedException {

    	System.out.println("Received TodoID : " + todo.getTodoId());
    	System.out.println("Received headers : " + headers);              

    }


    @JmsListener(destination = "ReplyTestQueue3")
    @SendTo("ReplyTestQueue4")
    public void receive3(Todo todo, @Headers MessageHeaders headers) throws InterruptedException {

    	System.out.println("ReplyTestQueue3を受信しました。");
    	System.out.println("Received TodoID : " + todo.getTodoId());
    	System.out.println("Received headers : " + headers);
    	
    	

    }
   
}