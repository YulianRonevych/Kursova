package com.example.home;
import  java.sql.*;

public class DBCon {
    static String dbUrl = "jdbc:mysql://localhost:3306/Home?characterEncoding=latin1&useConfigs=maxPerformance";
    static String User = "root";
    static String Password = "teremok22800747A";

    public static Connection GetCon() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection MyCon = (Connection) DriverManager.getConnection(dbUrl,User,Password);
        return MyCon;
    }
}
