package com.hsbc;

import com.hsbc.base.Bean;
import com.hsbc.dao.TokenDao;
import com.hsbc.dao.UserDao;
import com.hsbc.dao.impl.TokenDaoImpl;
import com.hsbc.dao.impl.UserDaoImpl;
import com.hsbc.base.DefaultHttpHandler;
import com.hsbc.util.ExpiryMap;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start {
    private static final int PORT = 8099;
    private static final String CONTEXT = "/api";
    private static final int MAX_THREADS = 8;

    public static void main(String[] args) {
        HttpServer httpServer;
        try {
            Properties props = new Properties();
            InputStream in = Start.class.getResourceAsStream("/config.properties");
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
                props.load(inputStreamReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String expireTime = props.getProperty("token_expire", "7200");
            TokenDaoImpl.TOKEN_MAP = new ExpiryMap<>(Long.parseLong(expireTime));
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
