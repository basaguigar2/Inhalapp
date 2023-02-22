/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.interfaces;

import java.util.ArrayList;
import pojos.Role;

/**
 *
 * @author gisel
 */
public interface RoleManager {
    public void addRole(Role r);
    public Role selectRoleById(Integer roleid);
    public ArrayList<Role> selectAllRoles();
    public int getId (String type);
}
