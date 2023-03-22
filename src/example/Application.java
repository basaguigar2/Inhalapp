package example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import db.interfaces.DBManager;
import db.sqlite.SQLiteManager;



public class Application {
    
    private static DBManager db = new SQLiteManager();
    private static Testing t = new Testing(db);
    public static void main(String[] args) {
        db.connect();
    }
}
