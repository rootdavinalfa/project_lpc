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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class list_po implements Initializable {
    @FXML private ProgressBar progress;
    @FXML private TableView<list_po_model> po_tv;
    @FXML private TableColumn<list_po_model,String> po_nama;
    @FXML private TableColumn<list_po_model,String> po_qtypo;
    @FXML private TableColumn<list_po_model,String> po_qtyter;
    @FXML private TableColumn<list_po_model,String> po_tanggal;
    @FXML private TableColumn<list_po_model,String> po_pic;
    @FXML private TableColumn<list_po_model,String> po_resi;

    private ObservableList<list_po_model> po_model = FXCollections.observableArrayList();

    @FXML private Label percent;
    @FXML private Label remain;
    @FXML private Label status;
    @FXML private Label summary;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private ObservableList<list_po_model> getPo_model(){
        return po_model;
    }
    public void set(String nama, String nopo,String tanggal){
        try{
            System.out.println(nama+nopo+tanggal);
            String no = null;
            float qtytotal = 0;
            float qtypo = 0;
            float per = 0;
            String tanggalpo =null;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            con = connector.setConnection();
            stmt = con.createStatement();

                rs = stmt.executeQuery("SELECT a.nama_material,b.nomor_resi,a.qty_po,b.ok,b.date_kedatangan,b.pic,a.no_po,a.tanggal_po,a.qty_terima FROM list_po_material a,warehouse_income b WHERE a.nama_material ='"+nama+"' && a.no_po ='"+nopo+"' && a.tanggal_po='"+tanggal+"' && a.nama_material=b.nama_material && a.no_po=b.no_po && a.tanggal_po = b.tanggal_po;");
                while (rs.next()) {
                    System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                    po_model.addAll(new list_po_model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                    po_nama.setCellValueFactory(cellData -> cellData.getValue().nama_materialProperty());
                    po_resi.setCellValueFactory(cellData -> cellData.getValue().no_resiProperty());
                    po_qtypo.setCellValueFactory(cellData -> cellData.getValue().qty_poProperty());
                    po_qtyter.setCellValueFactory(cellData -> cellData.getValue().qty_terimaProperty());
                    po_tanggal.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());
                    po_pic.setCellValueFactory(cellData -> cellData.getValue().picProperty());
                    no = rs.getString(7);
                    qtytotal = rs.getInt(9);
                    qtypo = rs.getInt(3);
                    tanggalpo = rs.getString(8);
                }

                progress.setProgress(qtytotal/qtypo);
                per = ((qtytotal/qtypo)*100);
                System.out.println(per);
                System.out.println(qtytotal);
            System.out.println(qtypo);
            float rem = qtypo - qtytotal;
                percent.setText(String.valueOf(per)+"%");
                remain.setText(String.valueOf(qtytotal) + " of " + String.valueOf(qtypo) + "(" + rem + " Remaining)");
                summary.setText("Untuk PO: " + no + " Tanggal PO: " + tanggalpo);
                if (qtypo == qtytotal) {
                    status.setText("ACCEPTED");
                    status.setTextFill(Color.web("#00903c"));
                } else if (qtypo > qtytotal) {
                    status.setText("ON DELIVERY");
                    status.setTextFill(Color.web("#5000fc"));
                }


                po_tv.setItems(getPo_model());

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
