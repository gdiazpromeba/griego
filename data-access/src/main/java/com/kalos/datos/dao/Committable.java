package com.kalos.datos.dao;

import javax.sql.DataSource;

/**
 * Created by gdiaz on 4/22/16.
 */
public interface Committable {

    DataSource getDataSource();

    default void setAutocommit(boolean flag) {
        try {
            getDataSource().getConnection().setAutoCommit(flag);
        } catch (Exception exception) {
            throw new RuntimeException("error poblando el valor autocommit");
        }
    }

    default void commit() {
        try {
            getDataSource().getConnection().commit();
        } catch (Exception exception) {
            throw new RuntimeException("error ejecutando commit" + exception.getMessage());
        }
    }
}
