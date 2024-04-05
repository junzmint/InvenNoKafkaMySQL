package service;

import api.API;
import cache.LocalCache;
import transactionshandler.TransactionsHandling;

public class Main {
    public static final LocalCache cache = new LocalCache();
    public static final TransactionsHandling handler = new TransactionsHandling();

    public static void main(String[] args) {
        cache.initialize("123", 10);
        handler.setCache(cache);
        API.Api();
    }
}
