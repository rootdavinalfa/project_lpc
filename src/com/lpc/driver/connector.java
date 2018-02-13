package com.lpc.driver;

import com.lpc.ui.alert;

import java.sql.Connection;
import java.sql.DriverManager;

public class connector {
    public static Connection setConnection(){
        String user_LoginSql = "root";
        String Pass = "Davinalfa123";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpc?useSSL=false",user_LoginSql,Pass);

            return con;
        }
        catch (Exception e){
            alert al = new alert();
            al.error_sql();
            System.out.println("Tidak dapat menghubungkan,java log :\n"+ e);
        }
        return null;

    }
}
