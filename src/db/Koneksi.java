/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class Koneksi {

    private Connection connect;
    private Statement db;

    public Koneksi() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Class Driver ditemukan");
            try {
                connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ryvana_07014"/*username*/, "ryvana"/*pass*/);
                System.out.println("Koneksi Database sukses");
            } catch (SQLException se) {
                System.out.println("Koneksi Database gagal : " + se);
            }
        } catch (ClassNotFoundException err) {
            System.out.println("Class Driver tidak ditemukan, Terjadi kesalahan pada : " + err);
        }
    }

    public ResultSet GetData(String sql) {
        try {
            db = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return db.executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    public int ManipulasiData(String sql) {
        try {
            db = connect.createStatement();
            return db.executeUpdate(sql);
        } catch (SQLException e) {
            return 0;
        }
    }
}
