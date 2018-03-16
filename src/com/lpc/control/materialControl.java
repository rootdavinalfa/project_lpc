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

import com.lpc.ui.alert;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.annotation.Resource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class materialControl implements Initializable{
    final ToggleGroup group = new ToggleGroup();
    final ToggleGroup group_uh = new ToggleGroup();
    @FXML
    private TableView<listShow> tableView;
    @FXML
    private TableColumn<listShow,String> nama_partCOL;
    @FXML
    private TableColumn<listShow,String> nama_materialCOL;
    @FXML
    private TableColumn<listShow,String> berat_partCOL;
    @FXML
    private TableColumn<listShow,String> berat_runnerCOL;
    @FXML
    private TableColumn<listShow,String> berat_totalCOL;
    @FXML
    private TableColumn<listShow,String>  customerCOL;
    @FXML
    private TableColumn<listShow,String> hargaINJCOL;
    @FXML
    private TableColumn<listShow,String> ctiCOL;
    @FXML
    private TableView<material_tabEdit> kebutuhanView;
    @FXML
    private TableColumn<material_tabEdit,String> km_npCOL;
    @FXML
    private TableColumn<material_tabEdit,String> km_nmCOL;
    @FXML
    private TableColumn<material_tabEdit,String> km_jumCOL;
    @FXML private TableColumn<material_tabEdit,String> hargaCOL;
    @FXML
    private TextField tf_upnm;
    @FXML
    private Label txt_harganow;
    @FXML
    private TextField tf_harganew;
    @FXML
    private TextField tf_newnm;
    @FXML
    private TextField tf_forp;
    @FXML
    private ComboBox<String> list_customer;
    @FXML
    private TextField tf_nbp;
    @FXML
    private TextField tf_runner;
    @FXML
    private TextField tf_ntotal;
    @FXML
    private TextField tf_hargaINJ;
    @FXML
    private TextField tf_shot;
    @FXML
    private TextField tf_cav;
    @FXML
    private TextField gNP;
    @FXML
    private Label txt_custom;
    @FXML
    private RadioButton rdbtn_inj_uh;
    @FXML
    private RadioButton rdbtn_assy_uh;
    @FXML
    private RadioButton rdbtn_mINJ;
    @FXML
    private RadioButton rdbtn_mAssy;
    @FXML
    private TextField gNM;
    @FXML
    private TextField gJumlah;
    @FXML
    private TextField gHarga;
    @FXML
    private TextField gKP;


    private String stat;
    private ObservableList<material_tabEdit> data1 = FXCollections.observableArrayList();
    private ObservableList<listShow> data = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){
        listingCB();
        radiobutton_materialbaru();
        radiobutton_updateharga();
        //tf_ntotal.setDisable(true);
    }
    private void radiobutton_materialbaru(){
        rdbtn_mINJ.setToggleGroup(group);
        rdbtn_mAssy.setToggleGroup(group);
        rdbtn_mINJ.setSelected(true);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    RadioButton button = (RadioButton)group.getSelectedToggle();
                    String a =button.getId();

                    if(Objects.equals(a, "rdbtn_mINJ")){
                        rdbtn_mINJ();

                    }else if(Objects.equals(a, "rdbtn_mAssy")){
                        rdbtn_mAssy();

                    }
                    System.out.println(a);
                }
            }
        });

    }

    private void radiobutton_updateharga(){
        rdbtn_inj_uh.setToggleGroup(group_uh);
        rdbtn_assy_uh.setToggleGroup(group_uh);
        rdbtn_inj_uh.setSelected(true);
        group_uh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group_uh.getSelectedToggle() != null) {
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    RadioButton button = (RadioButton)group_uh.getSelectedToggle();
                    String a =button.getId();
                    /*
                    if(Objects.equals(a, "rdbtn_mINJ")){
                        rdbtn_mINJ();

                    }else if(Objects.equals(a, "rdbtn_mAssy")){
                        rdbtn_mAssy();

                    }
                    System.out.println(a);*/
                }
            }
        });
    }
    @SuppressWarnings("all")
    private void rdbtn_mINJ(){
        //list_customer.setDisable(false);
        //list_customer.setEditable(true);
        tf_nbp.setDisable(false);
        tf_runner.setDisable(false);
        tf_ntotal.setDisable(false);
        tf_cav.setDisable(false);
        tf_shot.setDisable(false);
    }
    @SuppressWarnings("all")
    private void rdbtn_mAssy(){
        //list_customer.setDisable(true);
        tf_nbp.setDisable(true);
        tf_runner.setDisable(true);
        tf_ntotal.setDisable(true);
        tf_cav.setDisable(true);
        tf_shot.setDisable(true);
        //list_customer.setEditable(false);

    }
    private void listingCB(){
        try{

            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT name_customer FROM list_customer;");

            while(rs.next()){
                List<String> listPart = new ArrayList<String>();
                //Collections.sort(listPart,getComparator());
                listPart.add(rs.getString("name_customer"));

                //options.add(listPart);
                list_customer.getItems().addAll(FXCollections.observableArrayList(listPart));
                System.out.println(listPart);
            }
            con.close();


        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML protected void uploadEdited(){

            String a =gNP.getText();
            String b = gNM.getText();
            String c = gJumlah.getText();
            String d = gHarga.getText();

            try {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                stmt.executeUpdate("UPDATE list_material_assy SET nama_material ='"+b+"',jumlah = '"+c+"',harga_assy ='"+d+"';" +
                        "WHERE nama_part = '"+a+"';");
                alert al = new alert();
                al.info_upload();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
    }
    @FXML protected void cekMaterialAssy(){
        String a = gKP.getText();
        try {
            kebutuhanView.getItems().clear();
            getAssy().clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part, a.nama_material ,a.jumlah,a.harga_assy FROM list_material_assy a,list_part b " +
                    "WHERE b.code_part = '"+a+"' && a.nama_part = b.nama_part ;");
            while (rs.next()) {
                //List<String> table = new ArrayList<String>;
                data1.add(new material_tabEdit(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)));

                km_npCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                km_nmCOL.setCellValueFactory(cellData -> cellData.getValue().nama_materialProperty());
                km_jumCOL.setCellValueFactory(cellData -> cellData.getValue().jumlahProperty());
                hargaCOL.setCellValueFactory(cellData -> cellData.getValue().hargaProperty());
            }
            kebutuhanView.setItems(getAssy());
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML protected void clearTableKM(){

    }
    @FXML protected void clear(){
        tableView.getItems().clear();

    }
    @FXML protected void getData(){
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,a.nama_material,b.code_customer,a.berat_part,a.berat_runner,a.berat_total,a.harga_inject,a.cti FROM list_material_inj a,list_customer b WHERE a.custID =b.id_customer;");
            while (rs.next()) {
                //List<String> table = new ArrayList<String>;
                data.add(new listShow(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));

                nama_partCOL.setCellValueFactory(cellData -> cellData.getValue().np());
                nama_materialCOL.setCellValueFactory(cellData -> cellData.getValue().nm());
                customerCOL.setCellValueFactory(cellData->cellData.getValue().cust());
                berat_partCOL.setCellValueFactory(cellData -> cellData.getValue().bp());
                berat_runnerCOL.setCellValueFactory(cellData -> cellData.getValue().br());
                berat_totalCOL.setCellValueFactory(cellData -> cellData.getValue().bt());
                hargaINJCOL.setCellValueFactory(cellData -> cellData.getValue().hinj());
                ctiCOL.setCellValueFactory(cellData -> cellData.getValue().hcti());
            }
            tableView.setItems(getlistShow());
            con.close();
        }
        catch (Exception e){

        }
    }
    public ObservableList<listShow> getlistShow() {
        return data;
    }
    public ObservableList<material_tabEdit> getAssy(){
        return data1;
    }
    @FXML protected void btn_upHarga(){
        String nm = tf_upnm.getText();
        //String harga = txt_harganow.getText();
        String hargaUP = tf_harganew.getText();
        System.out.println(nm);
        if (rdbtn_inj_uh.isSelected()) {
            try{

                Class.forName("com.lpc.driver.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                stmt.executeUpdate("UPDATE list_material_inj SET harga_inject = '"+hargaUP+"' WHERE nama_material='"+nm+"'");
                System.out.println("SUCCESS");
                con.close();
                alert al = new alert();
                al.info_upload();
            }
            catch(Exception e){
                alert al = new alert();
                al.error_upload();
            }
        }
        else if (rdbtn_assy_uh.isSelected()){
            try{

                Class.forName("com.lpc.driver.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                stmt.executeUpdate("UPDATE list_material_assy SET harga_assy = '"+hargaUP+"' WHERE nama_material='"+nm+"'");
                System.out.println("SUCCESS");
                con.close();
                alert al = new alert();
                al.info_upload();
            }
            catch(Exception e){
                alert al = new alert();
                al.error_upload();
            }
        }

    }
    @FXML protected void btn_getHarga(){
        String nm = tf_upnm.getText();
        if (rdbtn_inj_uh.isSelected()) {
            try{
                Class.forName("com.lpc.driver.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                rs =stmt.executeQuery("SELECT harga_inject FROM list_material_inj WHERE nama_material ='"+nm+"';");
                while (rs.next()){
                    String a = rs.getString(1);
                    txt_harganow.setText(a);
                    System.out.println(a);
                }
                System.out.println("SUCCESS");
                con.close();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        else if (rdbtn_assy_uh.isSelected()){
            try{
                Class.forName("com.lpc.driver.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                rs =stmt.executeQuery("SELECT harga_assy FROM list_material_assy WHERE nama_material ='"+nm+"';");
                while (rs.next()){
                    String a = rs.getString(1);
                    txt_harganow.setText(a);
                    System.out.println(a);
                }
                System.out.println("SUCCESS");
                con.close();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }


    }
    private String getID(){
        String cp = list_customer.getValue();
        String idP="";
        try{
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmtgetID = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmtgetID = con.createStatement();
            rs = stmtgetID.executeQuery("SELECT id_customer FROM list_customer WHERE name_customer='"+cp+"';");
            while (rs.next()){
                idP = rs.getString(1);
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return idP;

    }
    @FXML protected void btn_upMat(){
        if (rdbtn_mINJ.isSelected()) {
            String newnm = tf_newnm.getText();
            String forp = tf_forp.getText();
            String cust = list_customer.getValue();
            String id = getID();
            String nbp = tf_nbp.getText();
            String runner = tf_runner.getText();
            //String total = tf_ntotal.getText();
            float totalA = Float.parseFloat(nbp);
            float totalB = Float.parseFloat(runner);
            float a = totalA + totalB;
            String total = String.valueOf(a);
            String harga = tf_hargaINJ.getText();
            String cav = tf_cav.getText();
            try{
                Class.forName("com.lpc.driver.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO list_material_inj(nama_material,nama_part,custID,cav,berat_part," +
                        "berat_runner,berat_total,harga_inject) VALUES ('"+newnm+"','"+forp+"','"+id+"','"+cav+"','"+nbp+"','"+runner+"'," +
                        "'"+total+"','"+harga+"');");
                alert al = new alert();
                al.info_upload();
            }
            catch(Exception e){
                alert al = new alert();
                al.error_upload();
                System.out.println(e);
            }
        } else if (rdbtn_mAssy.isSelected()) {
            String newnm = tf_newnm.getText();
            String forp = tf_forp.getText();
            String cust = list_customer.getValue();
            String id = getID();
            String harga = tf_hargaINJ.getText();
            //String cav = tf_cav.getText();
            try{
                Class.forName("com.lpc.driver.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO list_material_assy (nama_part, nama_material, harga_assy, custID) VALUES ('"+newnm+"','"+forp+"','"+harga+"','"+id+"')");
                alert al = new alert();
                al.info_upload();
            }
            catch(Exception e){
                alert al = new alert();
                al.error_upload();
                System.out.println(e);
            }
        }

    }
}
