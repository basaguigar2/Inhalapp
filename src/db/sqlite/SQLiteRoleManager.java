/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.RoleManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Role;

/**
 *
 * @author gisel
 */
public class SQLiteRoleManager implements RoleManager{

    private Connection c;
    
    public SQLiteRoleManager(Connection c){
        this.c = c;
    }
    
      public SQLiteRoleManager() {
        super();
    }
      
    @Override
    public void addRole(Role r) {
        String sq1 = "INSERT INTO role (type) VALUES (?)";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, r.getRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Role selectRoleById(Integer roleid) {
        try {
            String sql = "SELECT * FROM role WHERE roleid = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,roleid);
            ResultSet rs = p.executeQuery();
            Role role = null;
            if(rs.next()){
                role = new Role (rs.getInt("roleid"),rs.getString("type"));
            }
            p.close();
            rs.close();
            return role ;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Role> selectAllRoles() {
        try {
            String sql = "SELECT * FROM role";
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            ArrayList <Role> rList = new ArrayList<Role>();
            while(rs.next()){
                rList.add( new Role (rs.getInt("roleid"),rs.getString("type")));
            }
            p.close();
            rs.close();
            return rList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int getId(String type) {
        int id=0;
                try {
                    String sql1 = "SELECT * FROM role WHERE type = ?";
                    PreparedStatement preparedStatement = c.prepareStatement(sql1);
                    PreparedStatement p = c.prepareStatement(sql1);
                    p.setString(1,type);
                    ResultSet rs = p.executeQuery();
                    id = rs.getInt("roleid");
                } catch (SQLException ex) {
                ex.printStackTrace();
            }
        return id;
    }
    
}
