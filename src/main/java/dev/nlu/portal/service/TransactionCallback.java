package dev.nlu.portal.service;

import java.sql.Connection;

@FunctionalInterface
public interface TransactionCallback<T> {
    T execute(Connection conn) throws Exception;
}
