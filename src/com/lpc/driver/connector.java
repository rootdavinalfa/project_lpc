/*
#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
#+++																 +++
#+++	 Davin Alfarizky putra Basudewa <rootdavin@yahoo.co.jp>		 +++
#+++				Copyright 2018,IT Department LPC				 +++
#+++						it_lpc@lpc-ind.com						 +++
#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Version 0.0.1
This File Contain some OpenSource Package.Please refer to GNU Public License
for further information.
This class,contain LPC proprietary that's coded by Davin.Please contact
davin<rootdavin@yahoo.co.jp> or/ <it_lpc@lpc-ind.com>

*/
package com.lpc.driver;

import com.lpc.ui.alert;

import java.sql.Connection;
import java.sql.DriverManager;

public class connector {
    public static Connection setConnection(){


        try{
            propertiesReader pros = new propertiesReader();
            pros.getProperties();
            String user_LoginSql = pros.getUser();
            String Pass = pros.getPass();
            String database = pros.getDatabase();
            String host = pros.getHost();
            String port= pros.getPort();

            System.out.println(host+user_LoginSql+Pass+database+port);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?useSSL=false",user_LoginSql,Pass);

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
