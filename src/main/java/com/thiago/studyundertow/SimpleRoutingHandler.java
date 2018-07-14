package com.thiago.studyundertow;

import io.undertow.Undertow;
import io.undertow.io.Sender;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;

/**
 *
 * @author wwthi
 */
public class SimpleRoutingHandler {

    public static void main(String[] args) {
        RoutingHandler handlers = new RoutingHandler();

        handlers.get("/", (exchange) -> {
                 exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                 Sender sender = exchange.getResponseSender();
                 sender.send("{\"mensagem\": \"Olá Mundo (GET)\"}");
             });

        handlers.get("/pessoa", (exchange) -> {
                 exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                 Sender sender = exchange.getResponseSender();
                 sender.send("{\"mensagem\": \"Uma pessoa (GET)\"}");
             });

        handlers.get("/carro", (exchange) -> {
                 exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                 Sender sender = exchange.getResponseSender();
                 sender.send("{\"mensagem\": \"Um carro (GET)\"}");
             });

        handlers.post("/carro", (exchange) -> {
                  exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                  Sender sender = exchange.getResponseSender();
                  sender.send("{\"mensagem\": \"Um carro (POST)\"}");
              });

        handlers.setInvalidMethodHandler((exchange) -> {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            Sender sender = exchange.getResponseSender();
            sender.send("{\"mensagem\": \"Invalid Handler\"}");
        });

        handlers.setFallbackHandler((exchange) -> {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            Sender sender = exchange.getResponseSender();
            sender.send("{\"mensagem\": \"Fallback Handler\"}");
        });

        Undertow server = Undertow.builder()
                .addHttpListener(8000, "localhost")
                .setHandler(handlers)
                .build();

        server.start();
    }
}
