package com.thiago.studyundertow;

import io.undertow.Undertow;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import io.undertow.util.Methods;

/**
 *
 * @author wwthi
 */
public class HelloServer {

    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8000, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange he) throws Exception {
                        HttpString method = he.getRequestMethod();

                        if (method.equals(Methods.GET)) {
                            he.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                            Sender sender = he.getResponseSender();

                            sender.send("{\"mensagem\": \"Método GET\"}");
                        } else if (method.equals(Methods.POST)) {
                            he.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                            Sender sender = he.getResponseSender();

                            sender.send("{\"mensagem\": \"Método POST\"}");
                        }
                    }
                })
                .build();

        server.start();
    }
}
