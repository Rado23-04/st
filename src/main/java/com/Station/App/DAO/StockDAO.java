package com.Station.App.DAO;

import com.Station.App.Generic.GenericDAO;
import com.Station.App.Model.Stock;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

import static com.Station.App.Configuration.ConnectionDB.getConnection;
@Repository
public class StockDAO implements GenericDAO<Stock> {
    @Override
    public Stock save(Stock toSave) throws SQLException {
        String sql = "INSERT INTO \"Stock\" (id,Date, name, quantite, id_productTemplate)"
                + " VALUES (?,?,?,?,?)"
                + " ON CONFLICT (id)"
                + " DO UPDATE SET ID= EXCLUDED.ID, DATE = EXCLUDED.DATE, NAME = EXCLUDED.NAME,QUANTITE = EXCLUDED.QUANTITE,"
                + " ID_PRODUCTTEMPLATE = EXCLUDED.ID_PRODUCTTEMPLATE"
                + " RETURNING id";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        preparedStatement.setInt(1,toSave.getId());
        preparedStatement.setTimestamp(2,toSave.getDate());
        preparedStatement.setString(3,toSave.getName());
        preparedStatement.setDouble(4,toSave.getQuantite());
        preparedStatement.setInt(5,toSave.getIdProductTemplate());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                toSave.setId(generatedId);
            }
            return toSave;
        } catch (SQLException e) {
            System.out.println("==> erreur: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Stock selectById(int id) throws SQLException {
        String sql = "SELECT * FROM \"Stock\" WHERE id = ?";
        Stock stock = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id_stock= resultSet.getInt("id");
                    Timestamp date = resultSet.getTimestamp("date");
                    String name = resultSet.getString("name");
                    double quantite = resultSet.getDouble("quantite");
                    int idProductTemplate = resultSet.getInt("id_productTemplate");

                    stock = new Stock( id_stock,date, name, quantite, idProductTemplate);
                }
            }
        }
        return stock;
    }

    @Override
    public List<Stock> findAll() throws SQLException {
        return null;
    }

    @Override
    public Stock update(Stock update) throws SQLException {
        return null;
    }
}
