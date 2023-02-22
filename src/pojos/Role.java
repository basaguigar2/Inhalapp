/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

import java.util.ArrayList;

/**
 *
 * @author gisel
 */
public class Role {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String type;
    private ArrayList<User> users;
    
    public Role() {
        super();
        this.users = new ArrayList<User>();
    }
    
    /**
     *
     * @param role - [String] Role of the user (patient/doctor).
     */
    public Role(String role){
        super();
        this.type = role;
        this.users = new ArrayList<User>();
    }

    public Role(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Used to get the ID of the role (1-patient, 2-doctor).
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Used to set the ID of the role (1-patient, 2-doctor).
     * @param id - ID of the role (Integer).
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Used to get the type of role (patient/doctor)
     * @return type
     */
    public String getRole() {
        return type;
    }

    /**
     * Used to set the type of role (patient/doctor).
     * @param role - Type of role (String)
     */
    public void setRole(String role) {
        this.type = role;
    }

    /**
     * Used to get the list of users that have a role
     * @return users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Used to set the list of users that have a role.
     * @param users - list of users with a role (List<User>).
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", role=" + type + "]";
    }
}
