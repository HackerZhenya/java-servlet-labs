package com.hackerzhenya.database;

import java.sql.SQLException;

public abstract class Repository {
    protected final Executor executor;

    public Repository(Executor executor) {
        this.executor = executor;

        try {
            migrate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
    }

    protected void migrate() throws SQLException {

    }
}
