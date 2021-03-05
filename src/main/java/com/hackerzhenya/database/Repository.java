package com.hackerzhenya.database;

import org.hibernate.Session;

public abstract class Repository {
    protected final Session session;

    public Repository(Session session) {
        this.session = session;
    }
}
