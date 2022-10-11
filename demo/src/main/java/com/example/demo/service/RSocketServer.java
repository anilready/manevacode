package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rsocket.*;
import reactor.core.Disposable;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service
public class RSocketServer implements IRSocketServer {
     
    private final Disposable server;

    public RSocketServer() {
        this.server = RSocketFactory.receive()
          .acceptor((setupPayload, reactiveSocket) -> Mono.just(new RSocketImpl()))
          .transport(TcpServerTransport.create("localhost", 8081))
          .start()
          .subscribe();
    }

    public void dispose() {
        this.server.dispose();
    }

    private class RSocketImpl extends AbstractRSocket {
	
	@Override
	public Mono<Payload> requestResponse(Payload payload) {
		try {
			System.out.println("IN THE RSOCKET SERVER");

			String echo = "Hello " + payload.getDataUtf8();

			return Mono.just(DefaultPayload.create(echo)); // reflect the payload back to the sender
		} catch (Exception x) {
			return Mono.error(x);
		}
	}
	}   
}