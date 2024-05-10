package com.Station.App.Generic;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<A> {
    A save (A toSave) throws SQLException;
    A selectById(int id) throws SQLException;
    List<A> findAll() throws SQLException;
    A update (A update) throws SQLException;
}
