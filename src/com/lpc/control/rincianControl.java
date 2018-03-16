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
import com.lpc.model.rincianModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class rincianControl {

    @FXML private Button close_rincian;
    @FXML private TableView<rincianModel> rincian_tv;
    @FXML private TableColumn<rincianModel,String> rincian_namapart;
    @FXML private TableColumn<rincianModel,String> rincian_tanggal;
    @FXML private TableColumn<rincianModel,String> rincian_po;
    @FXML private TableColumn<rincianModel,String> rincian_do;
    @FXML private TableColumn<rincianModel,String> rincian_jumlah;
    @FXML private TableColumn<rincianModel,String> rincian_surat;

    private ObservableList<rincianModel> rincianModels = FXCollections.observableArrayList();

    public void cach(String nama_part,String po){
        try {
            //System.out.println(nama_part+deliver+po);
            rincian_tv.getItems().clear();
            rincianModels.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,tanggal,purchase_order,delivery_order,jumlah,surat_jalan FROM laporan_delivery WHERE nama_part='"+nama_part+"' && purchase_order='"+po+"';");
            while (rs.next()){
                rincianModels.addAll(new rincianModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                rincian_namapart.setCellValueFactory(cellData-> cellData.getValue().nama_partProperty());
                rincian_do.setCellValueFactory(cellData-> cellData.getValue().deliverProperty());
                rincian_jumlah.setCellValueFactory(cellData->cellData.getValue().jumlahProperty());
                rincian_po.setCellValueFactory(cellData->cellData.getValue().poProperty());
                rincian_tanggal.setCellValueFactory(cellData->cellData.getValue().tanggalProperty());
                rincian_surat.setCellValueFactory(cellData->cellData.getValue().surat_jalanProperty());
            }
            rincian_tv.setItems(getRincianModels());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private ObservableList<rincianModel> getRincianModels(){
        return rincianModels;
    }
    @FXML private void close_rincian(){
        Stage stage = (Stage) close_rincian.getScene().getWindow();
        stage.close();
    }
}
