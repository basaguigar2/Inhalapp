/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.sqlite;

import jdbc.interfaces.DBManager;
import jdbc.interfaces.UserManager;
import java.rmi.NotBoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.User;

/**
 *
 * @author gisel
 */
public class SQLiteUserManager implements UserManager {

    public Connection c;

    public SQLiteUserManager(Connection c) {
        this.c = c;
    }

    public SQLiteUserManager() {
        super();
    }

    @Override
    public void addUser(User u) {
        try {
            String sq1 = "INSERT INTO users (userName, userPassword, name) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Registrado");
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
            user.setPassword(rs.getString("userPassword"));
            user.setUsername(rs.getString("userName"));
            preparedStatement.close();
            rs.close();
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            user = null;
            return user;
        }
    }

    @Override
    public int getId(String username) {
        int id = 0;
        try {
            String sql1 = "SELECT * FROM users WHERE userName = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql1);
            PreparedStatement p = c.prepareStatement(sql1);
            p.setString(1, username);
            ResultSet rs = p.executeQuery();
            id = rs.getInt("userid");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return id;
    }

    @Override
    public boolean existingUserName(String username) {
        try {
            String sql = "SELECT * FROM users WHERE userName = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, username);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public User selectUserByUserId(Integer userId) {
        try {
            String sql = "SELECT * FROM users WHERE userid = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            User u = new User();
            if (rs.next()) {
                u.setPassword(rs.getString("userPassword"));
                u.setUserId(userId);
                u.setUsername(rs.getString("userName"));
                u.setEmail(rs.getString("email"));
            }
            p.close();
            rs.close();
            return u;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (NotBoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public SQLiteUserManager getUserManager() {
        return new SQLiteUserManager(c);
    }

}
