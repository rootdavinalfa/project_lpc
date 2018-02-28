package com.lpc.control;

import com.lpc.driver.connector;
import com.lpc.ui.alert;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.sound.midi.SysexMessage;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class managementControl implements Initializable{
    @FXML private TextField txt_nama_po;
    @FXML private TextField txt_no_po;
    @FXML private TextField txt_qty_po;
    @FXML private TextField txt_harga_po;
    @FXML private Label po_tanggal;

    @FXML private TableView<list_ok_po> ok_po;
    @FXML private TableColumn<list_ok_po,String> OKnama_materialCOL;
    @FXML private TableColumn<list_ok_po,String> OKnopo_materialCOL;
    @FXML private TableColumn<list_ok_po,String> OKqtypo_materialCOL;
    @FXML private TableColumn<list_ok_po,String> OKtanggalpo_materialCOL;
    @FXML private TableColumn<list_ok_po,String> OKhargapo_materialCOL;
    @FXML private TableColumn<list_ok_po,String> OKstatuspo_materialCOL;

    private ObservableList<list_ok_po> list_ok = FXCollections.observableArrayList();
    private ObservableList<list_wait_po> list_wait = FXCollections.observableArrayList();

    @FXML private TableView<list_wait_po> wait_po;
    @FXML private TableColumn<list_wait_po,String> Waitnama_materialCOL;
    @FXML private TableColumn<list_wait_po,String> Waitnopo_materialCOL;
    @FXML private TableColumn<list_wait_po,String> Waitqtypo_materialCOL;
    @FXML private TableColumn<list_wait_po,String> Waittanggalpo_materialCOL;
    @FXML private TableColumn<list_wait_po,String> Waithargapo_materialCOL;

    //Stok Region (NON)
    @FXML private Label stok_nontgl;
    @FXML private TextField stok_nonNP;
    @FXML private TextField stok_nonPICP;
    @FXML private TextField stok_nonPICW;
    @FXML private TextField stok_nonJUM;
    @FXML private TextArea stok_nonKET;
    @FXML private TableView<stok_nonmodel> stok_nonTV;
    @FXML private TableColumn<stok_nonmodel,String> stok_nonNPCOL;
    @FXML private TableColumn<stok_nonmodel,String> stok_nonPICPCOL;
    @FXML private TableColumn<stok_nonmodel,String> stok_nonPICWCOL;
    @FXML private TableColumn<stok_nonmodel,String> stok_nonJMLCOL;
    @FXML private TableView<stok_nonmodel1> stok_nonTV1;
    @FXML private TableColumn<stok_nonmodel1,String> stok_nonNPCOL1;
    @FXML private TableColumn<stok_nonmodel1,String> stok_nonOKCOL1;
    @FXML private TableColumn<stok_nonmodel1,String> stok_nonNGCOL1;

    private ObservableList<stok_nonmodel> stok_non = FXCollections.observableArrayList();
    private ObservableList<stok_nonmodel1> stok_non1 = FXCollections.observableArrayList();

    //Stok SSA(ASSY)
    @FXML private Label ssa_tgl;
    @FXML private TextField stok_ssaNP;
    @FXML private TextField stok_ssaPIP;
    @FXML private TextField stok_ssaPIW;
    @FXML private TextField stok_ssaJML;
    @FXML private TextArea stok_ssaKET;
    @FXML private TableView<stok_ssamodel>ssa_TV;
    @FXML private TableColumn<stok_ssamodel,String> ssa_NPCOL;
    @FXML private TableColumn<stok_ssamodel,String> ssa_PIPCOL;
    @FXML private TableColumn<stok_ssamodel,String> ssa_PIWCOL;
    @FXML private TableColumn<stok_ssamodel,String> ssa_JMLCOL;
    @FXML private TableView<stok_ssamodel1> ssa_TV1;
    @FXML private TableColumn<stok_ssamodel1,String> ssa_kodeCOL;
    @FXML private TableColumn<stok_ssamodel1,String> ssa_NPCOL1;
    @FXML private TableColumn<stok_ssamodel1,String> ssa_jmlCOL1;

    private ObservableList<stok_ssamodel1> ssa_mod1 = FXCollections.observableArrayList();
    private ObservableList<stok_ssamodel> ssa_mod = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){
        setCurrentTime();
        po_tabACT();
        refresh_po();

    }

    private void po_tabACT(){
        ok_po.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount() == 1 ){

                select_po_tab();

            }
        });
    }
    private void select_po_tab(){
        if(ok_po.getSelectionModel().getSelectedItem() != null){
            try {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();

                String a = sendToModal();
                String b = sendToModal1();
                String c = sendToModal2();
                rs = stmt.executeQuery("SELECT a.nama_material,b.nomor_resi,a.qty_po,b.ok,b.date_kedatangan,b.pic,a.no_po,a.tanggal_po,a.qty_terima FROM list_po_material a,warehouse_income b WHERE a.nama_material ='"+a+"' && a.no_po ='"+c+"' && a.tanggal_po='"+b+"' && a.nama_material=b.nama_material && a.no_po=b.no_po && a.tanggal_po = b.tanggal_po;");
                //cekprod selectedcp = cprod_tab.getSelectionModel().getSelectedItem();
                System.out.println(a);
                //mc = new modal_cekprod();
                //mc.redirect(stage,a,b,c);
                if(rs.next()){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/lpc/ui/list_po.fxml"));
                    loader.load();
                    list_po mc = loader.getController();
                    mc.set(a,c,b);
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(p));
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.showAndWait();
                }
                else{
                    alert al = new alert();
                    al.warn_datanotfound();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public String sendToModal(){
        String a;
        list_ok_po okpo = ok_po.getSelectionModel().getSelectedItem();
        a = okpo.getNama_material();
        return a;
    }
    public String sendToModal1(){
        String a;
        list_ok_po okpo = ok_po.getSelectionModel().getSelectedItem();
        a = okpo.getTanggal();
        return a;
    }
    public String sendToModal2(){
        String a;
        list_ok_po okpo = ok_po.getSelectionModel().getSelectedItem();
        a = okpo.getNopo();
        return a;
    }


    private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                po_tanggal.setText(simpleDateFormat.format(time.getTime()));

                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML private void add_wait_po(){
        String nama = txt_nama_po.getText();
        String nopo = txt_no_po.getText();
        String qty = txt_qty_po.getText();
        String harga = txt_harga_po.getText();
        String tanggal = po_tanggal.getText();
        if(txt_nama_po.getText().isEmpty() && txt_harga_po.getText().isEmpty() && txt_no_po.getText().isEmpty() && txt_qty_po.getText().isEmpty()){
            alert al = new alert();
            al.warn_poempty();
        }
        else {
            if (wait_po.getItems().size() < 15) {
                list_wait.addAll(new list_wait_po(nama, nopo, qty, tanggal, harga));
                Waitnama_materialCOL.setCellValueFactory(cellData -> cellData.getValue().nama_materialProperty());
                Waitnopo_materialCOL.setCellValueFactory(cellData -> cellData.getValue().nopoProperty());
                Waitqtypo_materialCOL.setCellValueFactory(cellData -> cellData.getValue().qtyProperty());
                Waittanggalpo_materialCOL.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());
                Waithargapo_materialCOL.setCellValueFactory(cellData -> cellData.getValue().hargaProperty());
                wait_po.setItems(getList_wait());
            } else {
                alert al = new alert();
                al.max15();
            }
        }
    }

    @FXML private void upload_po(){
        try{
            Connection con = null;
            Statement stmt = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            for(int i=0;i<wait_po.getItems().size();i++){
                String nama = String.valueOf(wait_po.getItems().get(i).getNama_material());
                String nopo = String.valueOf(wait_po.getItems().get(i).getNopo());
                String qty = String.valueOf(wait_po.getItems().get(i).getQty());
                String harga = String.valueOf(wait_po.getItems().get(i).getHarga());
                String tanggal = String.valueOf(wait_po.getItems().get(i).getTanggal());
                stmt.executeUpdate("INSERT INTO list_po_material(nama_material,no_po,qty_po,harga,tanggal_po)" +
                        "VALUES ('"+nama+"','"+nopo+"','"+qty+"','"+harga+"','"+tanggal+"');");
            }
            ok_po.getItems().clear();
            list_ok.clear();
            wait_po.getItems().clear();
            list_wait.clear();
            refresh_po();
            alert al = new alert();
            al.info_upload();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML private void refresh_po(){
        try{
            ok_po.getItems().clear();
            list_ok.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_material,no_po,qty_po,tanggal_po,harga,status FROM list_po_material ORDER BY nama_material ASC,no_po ASC ,tanggal_po DESC,status DESC;");
            while(rs.next()){
                list_ok.addAll(new list_ok_po(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                OKnama_materialCOL.setCellValueFactory(cellData->cellData.getValue().nama_materialProperty());
                OKnopo_materialCOL.setCellValueFactory(cellData->cellData.getValue().nopoProperty());
                OKtanggalpo_materialCOL.setCellValueFactory(cellData->cellData.getValue().tanggalProperty());
                OKqtypo_materialCOL.setCellValueFactory(cellData->cellData.getValue().qtyProperty());
                OKhargapo_materialCOL.setCellValueFactory(cellData->cellData.getValue().hargaProperty());
                OKstatuspo_materialCOL.setCellValueFactory(cellData->cellData.getValue().statusProperty());

            }
            ok_po.setItems(getList_ok());
        con.close();
        }

        catch (Exception e){
            System.out.println(e);
        }
    }

    private ObservableList<list_ok_po> getList_ok(){
        return list_ok;
    }
    private ObservableList<list_wait_po> getList_wait(){
        return list_wait;
    }



}
