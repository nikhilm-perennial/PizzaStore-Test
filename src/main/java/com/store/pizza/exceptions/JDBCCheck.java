package com.store.pizza.exceptions;
import java.io.*;
import java.sql.*;
public class JDBCCheck {
    public static void main(String[] args) throws Exception{
        Connection conn = null;

        try {
            String userName = "root";
            String password = "nikhil123";

            //127.0.0.1:50661
            String url = "jdbc:mysql://127.0.0.1:50661/liv2train";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from abc");
            while (rs.next()){
                System.out.println(rs.getInt(1)+":"+rs.getString(2));
            }
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Database Connection Terminated");
                } catch (Exception e) {}
            }
        }
    }
}