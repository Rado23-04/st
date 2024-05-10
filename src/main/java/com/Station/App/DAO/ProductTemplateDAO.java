package com.Station.App.DAO;

import com.Station.App.Configuration.ConnectionDB;
import com.Station.App.Generic.GenericDAO;
import com.Station.App.Model.ProductTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

import static com.Station.App.Configuration.ConnectionDB.getConnection;

@Repository
public class ProductTemplateDAO implements GenericDAO<ProductTemplate> {
    @Override
    public ProductTemplate save(ProductTemplate toSave) throws SQLException {
        String sql = "INSERT INTO \"ProductTemplate\" (id, name, quantite, prix)"
                + " VALUES (?, ?, ?, ?)"
                + " ON CONFLICT (id)"
                + " DO UPDATE SET name = EXCLUDED.name, quantite = EXCLUDED.quantite, prix = EXCLUDED.prix"
                + " RETURNING id";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,toSave.getId());
            preparedStatement.setString(2,toSave.getName());
            preparedStatement.setDouble(3,toSave.getQuantite());
            preparedStatement.setDouble(4,toSave.getPrix());

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
    public ProductTemplate selectById(int id) throws SQLException {
        String sql = "SELECT * FROM \"ProductTemplate\" WHERE id = ?";
        ProductTemplate productTemplate = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id_product= resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double quantite = resultSet.getDouble("quantite");
                    double prix = resultSet.getDouble("prix");

                    productTemplate = new ProductTemplate( id_product,name, quantite, prix);
                }
            }
        }
        return productTemplate;
    }

    @Override
    public List<ProductTemplate> findAll() throws SQLException {
        return null;
    }

    @Override
    public ProductTemplate update(ProductTemplate update) throws SQLException {
        String up = "Update \"ProductTemplate\" set quantite = ? where id = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement(up)){
            preparedStatement.setDouble(1,update.getQuantite());
            preparedStatement.setInt(2,update.getId());
            preparedStatement.executeUpdate();
        }
        return update;
    }
}
