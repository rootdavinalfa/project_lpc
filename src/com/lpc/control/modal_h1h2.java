package com.lpc.control;

import com.lpc.driver.connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class modal_h1h2 implements Initializable {
    @FXML private TableView modal_h1h2TV;
    @FXML private TableColumn<model_h1h2,String> modalTV_nmCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_qCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_sCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_ttCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_picCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_supCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_lcCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_lotlCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_resiCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_okCOL;
    @FXML private TableColumn<model_h1h2,String> modalTV_ngCOL;

    @FXML private TableView modal_h1h2TV1;
    @FXML private TableColumn<model_h1h2_out,String> modalTV1_nmCOL;
    @FXML private TableColumn<model_h1h2_out,String> modalTV1_qCOL;
    @FXML private TableColumn<model_h1h2_out,String> modalTV1_sCOL;
    @FXML private TableColumn<model_h1h2_out,String> modalTV1_ttCOL;
    @FXML private TableColumn<model_h1h2_out,String> modalTV1_picCOL;
    @FXML private TableColumn<model_h1h2_out,String> modalTV1_ppCOL;
    @FXML private TableColumn<model_h1h2_out,String> modalTV1_ketCOL;

    private ObservableList<model_h1h2> h1h2MOD = FXCollections.observableArrayList();
    private ObservableList<model_h1h2_out> h1h2MOD1 = FXCollections.observableArrayList();
    private String nama_material;

    public String set(String nama){
        nama_material = nama;
        return nama_material;
    }
    public void initialize(URL url, ResourceBundle rb){

    }
    public void print(){
        System.out.println(nama_material);
    }
    public void cach(){
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            con = connector.setConnection();
            String a = nama_material;
            System.out.println(a+" Terbaca");
            stmt  = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_material,qty,ok,ng,satuan,date_kedatangan,pic,supplier,lot_lpc,lot_cust,nomor_resi FROM warehouse_income WHERE nama_material='"+a+"';");
            while(rs.next()){
                h1h2MOD.addAll(new model_h1h2(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
                modalTV_nmCOL.setCellValueFactory(cellData -> cellData.getValue().nama_materialProperty());
                modalTV_qCOL.setCellValueFactory(cellData -> cellData.getValue().qtyProperty());
                modalTV_okCOL.setCellValueFactory(cellData -> cellData.getValue().okProperty());
                modalTV_ngCOL.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                modalTV_sCOL.setCellValueFactory(cellData -> cellData.getValue().satuanProperty());
                modalTV_ttCOL.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                modalTV_picCOL.setCellValueFactory(cellData -> cellData.getValue().picProperty());
                modalTV_supCOL.setCellValueFactory(cellData -> cellData.getValue().supProperty());
                modalTV_lcCOL.setCellValueFactory(cellData -> cellData.getValue().lot_custProperty());
                modalTV_lotlCOL.setCellValueFactory(cellData -> cellData.getValue().lot_lpcProperty());
                modalTV_resiCOL.setCellValueFactory(cellData -> cellData.getValue().resiProperty());
                modal_h1h2TV.setItems(getModelh1h2());
            }
            rs1 = stmt.executeQuery("SELECT nama_material,jumlah,satuan,tanggal,picwh,picpen,ket FROM warehouse_out WHERE nama_material='"+a+"';");
            while(rs1.next()){
                h1h2MOD1.addAll(new model_h1h2_out(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7)));
                modalTV1_nmCOL.setCellValueFactory(cellData -> cellData.getValue().nama_materialProperty());
                modalTV1_qCOL.setCellValueFactory(cellData -> cellData.getValue().qtyProperty());
                modalTV1_sCOL.setCellValueFactory(cellData -> cellData.getValue().satuanProperty());
                modalTV1_ttCOL.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                modalTV1_picCOL.setCellValueFactory(cellData -> cellData.getValue().picProperty());
                modalTV1_ppCOL.setCellValueFactory(cellData -> cellData.getValue().pic2Property());
                modalTV1_ketCOL.setCellValueFactory(cellData -> cellData.getValue().keteranganProperty());

                modal_h1h2TV1.setItems(getModelh1h21());
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /*
    public void cach1(){
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            String a = nama_material;
            System.out.println(a+" Terbaca");
            stmt  = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_material,a.jumlah,b.satuan,a.tanggal,a.picwh,a.picpen,a.ket FROM warehouse_out a, warehouse_income b WHERE a.nama_material='"+a+"' && a.nama_material = b.nama_material;");
            while(rs.next()){
                h1h2MOD1.addAll(new model_h1h2_out(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
                modalTV1_nmCOL.setCellValueFactory(cellData -> cellData.getValue().nama_materialProperty());
                modalTV1_qCOL.setCellValueFactory(cellData -> cellData.getValue().qtyProperty());
                modalTV1_sCOL.setCellValueFactory(cellData -> cellData.getValue().satuanProperty());
                modalTV1_ttCOL.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                modalTV1_picCOL.setCellValueFactory(cellData -> cellData.getValue().picProperty());
                modalTV1_ppCOL.setCellValueFactory(cellData -> cellData.getValue().pic2Property());
                modalTV1_ketCOL.setCellValueFactory(cellData -> cellData.getValue().keteranganProperty());

                modal_h1h2TV1.setItems(getModelh1h21());
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
    private ObservableList<model_h1h2> getModelh1h2(){
        return h1h2MOD;
    }
    private ObservableList<model_h1h2_out> getModelh1h21(){
        return h1h2MOD1;
    }
}
