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
package com.lpc.control;

import com.lpc.driver.connector;
import com.lpc.driver.version;
import com.lpc.ui.alert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class aksesControl implements Initializable{
    @FXML private Label lbl_ver;
    @FXML private TextField txt_kode;

    private static final int LIMIT = 6;

    public void initialize(URL url, ResourceBundle resourceBundel){
        version();
        a();
        b();
        k();
    }
    public void version(){
        version ver = new version();
        lbl_ver.setText(ver.version());
    }
    private void k(){
        txt_kode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kp();
            }
        });
    }
    private void a(){
        txt_kode.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (txt_kode.getText().length() >= LIMIT) {

                        // if it's 11th character then just setText to previous
                        // one

                        txt_kode.setText(txt_kode.getText().substring(0, LIMIT));
                    }
                }
            }
        });
    }
    private void b(){
        txt_kode.textProperty().addListener((ov, oldValue, newValue) -> {
            txt_kode.setText(newValue.toUpperCase());
        });
    }
    private void kp(){
        try{
            String kp = txt_kode.getText();
            String level = null;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT level FROM huser WHERE kodepass='"+kp+"';");
            if(rs.next()){
                rs1 = stmt.executeQuery("SELECT level FROM huser WHERE kodepass='"+kp+"';");
                while(rs1.next()){
                    level = rs1.getString(1);
                }
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/lpc/ui/main.fxml"));
                loader.load();
                mainControl mc = loader.getController();
                mc.if1(level);
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(p));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                //if(level.equals("1")){
            }
            else if(!rs.next()){
                alert al = new alert();
                al.warn_wrongcode();
            }




        }catch (Exception e){
            System.out.println(e);
        }
    }

}
