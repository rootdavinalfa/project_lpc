package com.lpc.control;

import com.lpc.driver.version;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class mainControl implements Initializable{
    @FXML private Label lab_version;
    @FXML private Button fg;
    @FXML private Button cek_material;
    @FXML private Button material;
    @FXML private Button produksi;
    @FXML private Button mgmt;


    public void initialize(URL url, ResourceBundle rb){
        version();
    }
    protected void version(){
        version ver = new version();
        lab_version.setText(ver.version());
    }
    @FXML protected void btn_cekmaterial() throws Exception{
        Parent cekMaterial = FXMLLoader.load(getClass().getResource("/com/lpc/ui/material.fxml"));
        Scene scene = new Scene(cekMaterial);
        Stage cekMaterialS = new Stage();
        version ver = new version();
        String ver1 = ver.version();
        cekMaterialS.setTitle("Cek Material | "+ver1);
        cekMaterialS.setScene(scene);
        //cekMaterialS.setFullScreen(true);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        cekMaterialS.setX((primScreenBounds.getWidth() - cekMaterialS.getWidth()) / 2);
        cekMaterialS.setY((primScreenBounds.getHeight() - cekMaterialS.getHeight()) / 4);
        cekMaterialS.show();
        cekMaterialS.setMaximized(true);
    }
    @FXML protected void btn_produksi() throws Exception{
        Parent produksi = FXMLLoader.load(getClass().getResource("/com/lpc/ui/produksi.fxml"));
        Scene scene = new Scene(produksi);
        Stage produksiS = new Stage();
        version ver = new version();
        String ver1 = ver.version();
        produksiS.setTitle("Produksi | "+ver1);
        produksiS.setScene(scene);
        //produksiS.setFullScreen(true);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        produksiS.setX((primScreenBounds.getWidth() - produksiS.getWidth()) / 2);
        produksiS.setY((primScreenBounds.getHeight() - produksiS.getHeight()) / 4);
        produksiS.show();
        produksiS.setMaximized(true);
    }
    @FXML protected void btn_warehouse() throws Exception{
        Parent warehouse = FXMLLoader.load(getClass().getResource("/com/lpc/ui/warehouse.fxml"));
        Scene scene = new Scene(warehouse);
        Stage warehouseS = new Stage();
        version ver = new version();
        String ver1 = ver.version();
        warehouseS.setTitle("Warehouse | "+ver1);
        warehouseS.setScene(scene);
        //produksiS.setFullScreen(true);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        warehouseS.setX((primScreenBounds.getWidth() - warehouseS.getWidth()) / 2);
        warehouseS.setY((primScreenBounds.getHeight() - warehouseS.getHeight()) / 4);
        warehouseS.show();
        warehouseS.setMaximized(true);
    }
    @FXML protected void btn_fg() throws Exception{
        Parent finish = FXMLLoader.load(getClass().getResource("/com/lpc/ui/finishgood.fxml"));
        Scene scene = new Scene(finish);
        Stage finishS = new Stage();
        version ver = new version();
        String ver1 = ver.version();
        finishS.setTitle("Finish Good | "+ver1);
        finishS.setScene(scene);
        //produksiS.setFullScreen(true);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        finishS.setX((primScreenBounds.getWidth() - finishS.getWidth()) / 2);
        finishS.setY((primScreenBounds.getHeight() - finishS.getHeight()) / 4);
        finishS.show();
        finishS.setMaximized(true);
    }
    @FXML protected void management() throws Exception{
        Parent mgmt = FXMLLoader.load(getClass().getResource("/com/lpc/ui/management.fxml"));
        Scene scene = new Scene(mgmt);
        Stage mgmtS = new Stage();
        version ver = new version();
        String ver1 = ver.version();
        mgmtS.setTitle("Management | "+ver1);
        mgmtS.setScene(scene);
        //produksiS.setFullScreen(true);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        mgmtS.setX((primScreenBounds.getWidth() - mgmtS.getWidth()) / 2);
        mgmtS.setY((primScreenBounds.getHeight() - mgmtS.getHeight()) / 4);
        mgmtS.show();
        mgmtS.setMaximized(true);
    }
    public void if1(String level){
        if(level.equals("1")){
            System.out.println("11111");
        }else if(level.equals("2")){

        }else if(level.equals("3")){

        }else if(level.equals("4")){
            produksi.setDisable(true);
            mgmt.setDisable(true);
            cek_material.setDisable(true);
            System.out.println("444444444");
        }else if(level.equals("5")){
            System.out.println("555555555");
            mgmt.setDisable(true);
            cek_material.setDisable(true);
            fg.setDisable(true);
            material.setDisable(true);
        }
    }
}
