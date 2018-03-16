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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class propertiesReader {
    private String host;
    private String user;
    private String pass;
    private String database;
    private String port;

    FileInputStream inputStream;
    String result ="";
    public String getProperties()throws Exception{
        try {
            String userdir = System.getProperty("user.dir");


            Properties prop = new Properties();
            String propFileName = "./config.properties";

            inputStream = new FileInputStream(propFileName);
            //inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value
            String user1 = prop.getProperty("user");
            String host1 = prop.getProperty("server");
            String port1 = prop.getProperty("port");
            String pass1 = prop.getProperty("pass");
            String database1 = prop.getProperty("database");
            user = user1;
            host = host1;
            port = port1;
            pass = pass1;
            database = database1;

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }



    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public String getPass() {
        return pass;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }
}
