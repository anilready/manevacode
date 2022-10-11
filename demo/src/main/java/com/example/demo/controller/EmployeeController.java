package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.demo.service.EmployeeService;
 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
 
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Flux<String> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }    
}
