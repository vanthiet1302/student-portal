package dev.nlu.portal.service;


import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface TransactionAction {
    boolean execute(Connection conn) throws SQLException;
}