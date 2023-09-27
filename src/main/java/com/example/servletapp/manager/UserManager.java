package com.example.servletapp.manager;


import com.example.servletapp.db.DBConnectionProvider;
import com.example.servletapp.model.UserModel;

import java.sql.*;

public class UserManager {

    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public void save(UserModel user) {
        String sql = "INSERT INTO users_tables(full_name, email, password) VALUES(?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserModel getByEmail(String email) {
        String sql = "Select * from users_tables where email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserModel getByEmailAndPassword(String email, String password) {
        String sql = "Select * from users_tables where email = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserModel getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return UserModel.builder()
                .id(resultSet.getInt("id"))
                .fullName(resultSet.getString("full_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }

}
