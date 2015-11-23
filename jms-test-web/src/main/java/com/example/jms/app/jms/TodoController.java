package com.example.jms.app.jms;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jms.domain.model.Todo;
import com.example.jms.domain.service.TodoService;



@Controller
@RequestMapping("todo")
public class TodoController {
    @Inject // (1)
    TodoService todoService;

    @Inject
    private JmsTemplate jmsTemplate;
    
    @ModelAttribute // (2)
    public TodoForm setUpForm() {
        TodoForm form = new TodoForm();
        return form;
    }
   
    @RequestMapping(value = "jms1_1")
    public String initJms1_1(Model model) {
        
    	Todo todo = new Todo();
    	
    	
    	model.addAttribute("todo", todo);
    	
        return "todo/jmsSend";
    }

    
    @RequestMapping(value = "jms1_2")
    public String initJms1_2(Model model) {
        
    	Todo todo = new Todo();
    	todo.setTodoId("11111");
    	    	
    	jmsTemplate.convertAndSend("TestQueue1", todo);
    	
    	
    	Todo retTodo =(Todo)jmsTemplate.receiveAndConvert("TestQueue1");
    	retTodo.setFinished(true);
    	
    	
    	model.addAttribute("todo", retTodo);
    	
        return "todo/jmsRecieve";
    }
    
    
    
    @RequestMapping(value = "jms2_1")
    public String initJms2_1(Model model) {
        
    	Todo todo = new Todo();
    	
    	model.addAttribute("todo", todo);
    	
        return "todo/jmsSend";
    }

    
    @RequestMapping(value = "jms2_2")
    public String initJms2_2(Model model) throws InterruptedException {
        
    	Todo todo = new Todo();
    	todo.setTodoId("11111");
    	    	
    	jmsTemplate.convertAndSend("TestQueue2", todo);
    	
    	TimeUnit.SECONDS.sleep(3L);
    	
    	Todo retTodo =(Todo)jmsTemplate.receiveAndConvert("ReplyTestQueue2");
    	retTodo.setFinished(true);
    	model.addAttribute("todo", retTodo);
    	
        return "todo/jmsRecieve";
    }
    
    
    @RequestMapping(value = "jms3_1")
    public String initJms3_1(Model model) {
        
    	Todo todo = new Todo();
    	
    	model.addAttribute("todo", todo);
    	
        return "todo/jmsSend";
    }

    
    @RequestMapping(value = "jms3_2")
    public String initJms3_2(Model model) throws InterruptedException {
        
    	Todo todo = new Todo();
    	todo.setTodoId("11111");
    	    	
    	jmsTemplate.convertAndSend("TestQueue3", todo);
    	
    	//TimeUnit.SECONDS.sleep(5L);
    	
    	//String s =(String)jmsTemplate.receiveAndConvert("ReplyTestQueue3");
    	Todo retTodo = new Todo();
    	retTodo.setFinished(true);
    	//retTodo.setDescription(s);
    	model.addAttribute("todo", todo);
    	
        return "todo/jmsRecieve";
    }
    
}

