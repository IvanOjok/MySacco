package com.jambo.mysacco.utils;

public final class RequestContext {

    private static final ThreadLocal<String> CLIENT_IP = new ThreadLocal<>();

    private RequestContext() {}

    public static void setClientIp(String ip) {
        CLIENT_IP.set(ip);
    }

    public static String getClientIp() {
        return CLIENT_IP.get();
    }

    public static void clear() {
        CLIENT_IP.remove();
    }
}
