package com.example.demo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface IEmployeeService
{
    Flux<String> findByName(String name);    
}