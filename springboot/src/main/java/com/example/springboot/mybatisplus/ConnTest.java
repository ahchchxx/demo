package com.example.springboot.mybatisplus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnTest {
//    public static void main(String[] args) {
//
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            conn = DriverManager.getConnection("jdbc:postgresql://192.168.118.131:5432/odoo",
//                    "postgres", "postgres");
//            System.out.println("连接数据库成功!");
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from dict_city limit 10;");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//
//                System.out.println("id=" + id + "  name=" + name);
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//
//    }
}
