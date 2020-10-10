package com.htjy.webflux;


import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

/**
 * Created By willz
 * Date : 2020/7/31
 * Time: 15:41
 */
public class Webflux {
    public static void main(String[] args) throws Exception {

        HttpHandler handler = new HttpHandler(){

            @Override
            public Mono<Void> handle(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
                System.out.println(serverHttpRequest.getId());
                return null;
            }
        };
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        HttpServer.create().host("127.0.0.1").port(9999).handle(adapter).bind().block();
    }
}
