package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import io.rsocket.*;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
 
@Service
public class EmployeeService implements IEmployeeService {
     
    private RSocket socket;
 
    public Flux<String> findByName(String name) {
        
		//Flux<String> flux = Flux.fromArray(new String[]{"A", "B", "C"});

		 this.socket = RSocketFactory.connect()
          .transport(TcpClientTransport.create("localhost", 8081))
          .start()
          .block();

		 String end = socket
          .requestResponse(DefaultPayload.create(name))
          .map(Payload::getDataUtf8)
          .block();

		this.socket.dispose();

		Flux<String> flux = Flux.fromArray(new String[]{"A", "B", "C",end});

		return flux;
    }    
}