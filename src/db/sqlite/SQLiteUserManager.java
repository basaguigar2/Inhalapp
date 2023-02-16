/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.UserManager;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.User;

/**
 *
 * @author gisel
 */
public class SQLiteUserManager implements UserManager{
  
    private Connection c;

    public SQLiteUserManager(Connection c) {
        this.c = c;
    }

    public SQLiteUserManager() {
        super();
    }
    
    @Override
    public void addUser(User u) {
        try {
            String sq1 = "INSERT INTO users ( userName, userPassword, email) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User checkPassword(String username, String password) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE userName = ? AND userPassword = ? ";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setPassword(rs.getString("userPassword"));
                user.setUsername(rs.getString("userName"));
            }
            preparedStatement.close();
            rs.close();
            return user;
//            } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
       // } catch (NoResultException e) {
        //    user = null;

        } catch (SQLException e) {
            e.printStackTrace();
            user = null;
        }
        return user;
    }

    @Override
    public int getId(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existingUserName(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User selectUserByUserId(Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
