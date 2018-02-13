package com.lpc.control;

import com.lpc.driver.connector;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.codegen.ClassEmitter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class modal_cekprod implements Initializable{
    @FXML private PieChart pie;
    @FXML private TextArea ta_ket;
    @FXML private Label lbl_namapart;
    @FXML private Label lbl_tp;
    @FXML private Label lbl_jam;
    @FXML private Label total;
    @FXML private Button close;
    @FXML private Label ng,good,capaian,target;
    @FXML private Label nosin;
    @FXML private Label onhold;
    private fromCP fc = new fromCP();

    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    private ObservableList<PieChart.Data> ok = FXCollections.observableArrayList();
    public void sender(String nama,String time,String date){
        namaProperty().set(nama);
        timeProperty().set(time);
        dateProperty().set(date);
    }
    public void set(String nama,String time,String date){
        lbl_namapart.setText(nama);
        lbl_jam.setText(time);
        lbl_tp.setText(date);
        cach();
    }

    public String getNama(){
        return namaProperty().get();
    }
    public String getTime(){
        return timeProperty().get();
    }
    public String getDate(){
        return dateProperty().get();
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty dateProperty() {
        return date;
    }
    //private final fromCP model;

    //private Scene scene;
    //private Stage stage;
    /*
    @SuppressWarnings("all")
    public void redirect(Stage stage,String name,String time ,String date)throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("/com/lpc/ui/selectedcekPROD.fxml"));
        Scene scene = new Scene(parent);
       // stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        produksiControl pc = new produksiControl();
        //String a = pc.sendToModal();
        //String b = pc.sendToModal1();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,date,time,keterangan FROM laporan_produksi WHERE nama_part='"+name+"'&& time='"+time+"'&& date='"+date+"';");
            while(rs.next()){
                lbl_namapart.setText(rs.getString(1));
                lbl_tp.setText(rs.getString(2));
                lbl_jam.setText(rs.getString(3));
                ta_ket.setText(rs.getString(4));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        stage.hide();
        stage.show();

    }*/
    public void initialize(URL url, ResourceBundle rb){
        //fc.getDate();
        //produksiControl pc = new produksiControl();
        //fc.sender(pc.sendToModal(),pc.sendToModal1(),pc.sendToModal2());
        //cach();
        ShowPercent();

    }


    public void cach(){


        try {
            //fromCP f = new fromCP();
            String a = lbl_namapart.getText();
            System.out.println(a);
            produksiControl pc = new produksiControl();
            String nama = lbl_namapart.getText();
            String time = lbl_jam.getText();
            String date = lbl_tp.getText();
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,date,time,keterangan,actual_produksi,ng_produksi,total_ouput,operator,target,no_mesin,onhold FROM laporan_produksi WHERE nama_part='"+nama+"'&& time='"+time+"'&& date='"+date+"';");
            while(rs.next()){
                lbl_namapart.setText(rs.getString(1));
                lbl_tp.setText(rs.getString(2));
                lbl_jam.setText(rs.getString(3));
                ta_ket.setText(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println(rs.getString(6));
                ok.addAll(new PieChart.Data("Produksi Good",rs.getInt(5)),new PieChart.Data("Produksi NG",rs.getInt(6)),new PieChart.Data("ONHOLD",rs.getInt(11)));
                total.setText("Total Produksi: "+rs.getString(7));
                float aa = rs.getFloat(5);
                float bb = rs.getFloat(6);
                float cc = rs.getFloat(7);
                float dd = rs.getFloat(9);
                nosin.setText("No Mesin: "+rs.getString(10));
                float ee = rs.getFloat(11);
                float perca = ((aa*100)/cc);
                float percb = ((bb*100)/cc);
                float peroh = ((ee*100)/cc);
                float capai = ((aa*100)/dd);
                target.setText("Target: "+String.valueOf(dd));
                good.setText("Barang good : "+aa+" / "+perca+"%");
                ng.setText("Barang NG : "+bb+" / "+percb+"%");
                onhold.setText("ONHOLD : "+ee+" / "+peroh+"%");
                pie.getData().addAll(ok);
                pie.setTitle("Produksi Part ,Oleh Operator: "+rs.getString(8));
                capaian.setText(String.valueOf(capai)+"%");
                pie.setLabelLineLength(10);
                pie.setLegendSide(Side.LEFT);
                //ShowPercent();
            }
            //pie.getData().addAll(data);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ShowPercent(){
        System.out.println("LLLLL");

    }
    @FXML private void close(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();

    }

}
