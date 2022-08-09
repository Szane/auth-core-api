package com.hsbc;

import com.hsbc.dao.UserDao;
import com.hsbc.dao.impl.UserDaoImpl;
import com.hsbc.base.DefaultHttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start {
    private static final int PORT = 8099;
    private static final String CONTEXT = "/api";
    private static final int MAX_THREADS = 8;

    public static void main(String[] args) {
        HttpServer httpServer;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
            httpServer.createContext(CONTEXT, new DefaultHttpHandler());
            ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
            httpServer.setExecutor(executor);
            httpServer.start();
            System.out.println("port:" + PORT);
            System.out.println("root:" + CONTEXT);
            System.out.println("threads limit:" + MAX_THREADS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
