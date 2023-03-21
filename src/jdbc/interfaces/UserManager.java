/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.interfaces;

import pojos.User;

/**
 *
 * @author gisel
 */
public interface UserManager {
    public void addUser(User u);
    public User checkPassword(String username, String password);
    public int getId(String username);
    public boolean existingUserName(String username);
    public User selectUserByUserId(Integer userId);
    public void createLinkUserRole(int roleId, int userId);
}
