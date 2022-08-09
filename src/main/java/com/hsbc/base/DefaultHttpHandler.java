package com.hsbc.base;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DefaultHttpHandler implements HttpHandler {
    final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        InetSocketAddress inetSocketAddress = httpExchange.getRemoteAddress();
        System.out.println("request ip：" + inetSocketAddress);
        System.out.println("request host：" + inetSocketAddress.getHostName());
        System.out.println("request port:" + inetSocketAddress.getPort());
        String requestMethod = httpExchange.getRequestMethod();
        System.out.println("request method:" + httpExchange.getRequestMethod());
        //url
        URI url = httpExchange.getRequestURI();
        System.out.println("url:" + url);
        if (requestMethod.equalsIgnoreCase("GET")) {//客户端的请求是get方法
            Headers responseHeaders = httpExchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/html;charset=utf-8");
            String response = "";
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream responseBody = httpExchange.getResponseBody();
            OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
            writer.write(response);
            writer.close();
            responseBody.close();
        } else {
            //请求报文
            InputStream inputStream = httpExchange.getRequestBody();
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            Headers headers = httpExchange.getResponseHeaders();
            headers.set("Content-Type", "application/json; charset=utf-8");
            int i;
            while ((i = inputStream.read()) != -1) {
                bas.write(i);
            }
            String requestmsg = bas.toString();
            Result result = HandlerMapping.getApiResult(url.toString(), requestmsg);
            System.out.println("request param:" + requestmsg);
            //返回报文
            String resmsg = objectMapper.writeValueAsString(result);
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, resmsg.getBytes(StandardCharsets.UTF_8).length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(resmsg.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        }
    }
}
