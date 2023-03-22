/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example;

import db.interfaces.DBManager;
import db.sqlite.SQLiteManager;

/**
 *
 * @author basag
 */
class Testing {

    private DBManager db;

    public Testing(DBManager db) {
        this.db = db;
    }

   /* public void testDB(String name) {
        System.out.println(db.getConnection());
        boolean h = db.existingUserName(name);
        if (h) {
            System.out.println("OK");
        } else {
            System.out.println("No OK");
        }
    }*/
}
