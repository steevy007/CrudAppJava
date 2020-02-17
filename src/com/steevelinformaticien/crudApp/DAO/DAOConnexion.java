/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steevelinformaticien.crudApp.DAO;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Sanon
 */
public class DAOConnexion {

    private Connection conn;
    private Statement st;
    private ResultSet result;
    private final String dbname = "crudapp";
    private final String pass = "";
    private final String user = "root";

    public DAOConnexion() {
        try {
            //chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbname, user, pass);
            st = (Statement) conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

}
