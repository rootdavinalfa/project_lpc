package com.lpc.control;

import com.lpc.driver.connector;
import com.lpc.model.*;
import com.lpc.model.stok_assy_model;
import com.lpc.ui.alert;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.Objects;
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

    //Stok Produksi
    @FXML private RadioButton stok_prod_all;
    @FXML private RadioButton stok_prod_fg;
    @FXML private RadioButton stok_prod_wip;
    @FXML private TableView<stok_prod_model> stok_prod_tv;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_namapart;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_stok;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_ng;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_runner;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_bonggol;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_wip1;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_status;
    @FXML private TableColumn<stok_prod_model,String> stok_prod_total;
    private ObservableList<stok_prod_model> stok_prod_models = FXCollections.observableArrayList();
    final ToggleGroup stok_prod_group = new ToggleGroup();

    //Stok WIP
    @FXML private TableView<stok_wip_model> stok_wip_tv;
    @FXML private TableColumn<stok_wip_model,String> stok_wip_namapart;
    @FXML private TableColumn<stok_wip_model,String> stok_wip_permintaanassy;
    @FXML private TableColumn<stok_wip_model,String> stok_wip_sisa;
    private ObservableList<stok_wip_model> stok_wip_models = FXCollections.observableArrayList();

    //Stok Assy
    @FXML private TableView<stok_assy_model> stok_assy_tv;
    @FXML private TableColumn<stok_assy_model,String> stok_assy_namapart;
    @FXML private TableColumn<stok_assy_model,String> stok_assy_ng;
    @FXML private TableColumn<stok_assy_model,String> stok_assy_jumlah;
    @FXML private TableColumn<stok_assy_model,String> stok_assy_total;
    private ObservableList<stok_assy_model> stok_assy_models = FXCollections.observableArrayList();

    //Stok ONHOLD
    @FXML private TableView<stok_onhold_model> stok_onhold_tv;
    @FXML private TableColumn<stok_onhold_model,String> stok_onhold_namapart;
    @FXML private TableColumn<stok_onhold_model,String> stok_onhold_onhold;
    private ObservableList<stok_onhold_model> stok_onhold_models = FXCollections.observableArrayList();

    //STOK FG
    @FXML private TableView<stok_fg_model> stok_fg_tv;
    @FXML private TableColumn<stok_fg_model,String> stok_fg_namapart;
    @FXML private TableColumn<stok_fg_model,String> stok_fg_stok;
    @FXML private TableColumn<stok_fg_model,String> stok_fg_jenis;
    @FXML private RadioButton stok_fg_nonassy;
    @FXML private RadioButton stok_fg_assy;
    @FXML private RadioButton stok_fg_all;
    final ToggleGroup stok_fg_group = new ToggleGroup();
    private ObservableList<stok_fg_model> stok_fg_models = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){
        setCurrentTime();
        po_tabACT();
        refresh_po();
        radiobutton_stok_fg();
        stok_wip_refresh();
        stok_assy_refresh();
        stok_prod_refresh(1);
        stok_onhold_refresh();
        radiobutton_stok_fg_check();
        stok_fg_jenis(3);
    }

    @SuppressWarnings("all")
    private void radiobutton_stok_fg(){
        stok_prod_all.setToggleGroup(stok_prod_group);
        stok_prod_fg.setToggleGroup(stok_prod_group);
        stok_prod_wip.setToggleGroup(stok_prod_group);
        stok_prod_all.setSelected(true);
        stok_prod_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (stok_prod_group.getSelectedToggle() != null) {
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    RadioButton button = (RadioButton)stok_prod_group.getSelectedToggle();
                    String a =button.getId();

                    if(Objects.equals(a, "stok_prod_all")){
                        stok_prod_refresh(1);

                    }else if(Objects.equals(a, "stok_prod_wip")){
                        stok_prod_refresh(3);

                    }
                    else if(Objects.equals(a, "stok_prod_fg")){
                        stok_prod_refresh(2);

                    }
                    System.out.println(a);
                }
            }
        });
    }

    @SuppressWarnings("all")
    private void radiobutton_stok_fg_check(){
        stok_fg_nonassy.setToggleGroup(stok_fg_group);
        stok_fg_assy.setToggleGroup(stok_fg_group);
        stok_fg_all.setToggleGroup(stok_fg_group);
        stok_fg_all.setSelected(true);
        stok_fg_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (stok_fg_group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton)stok_fg_group.getSelectedToggle();
                    String a =button.getId();

                    if(Objects.equals(a, "stok_fg_nonassy")){
                        stok_fg_jenis(1);

                    }else if(Objects.equals(a, "stok_fg_assy")){
                        stok_fg_jenis(2);
                    }else if(Objects.equals(a, "stok_fg_all")){
                        stok_fg_jenis(3);
                    }

                    System.out.println(a);
                }
            }
        });
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
    @SuppressWarnings("all")
    @FXML private void stok_prod_refresh1(){
        RadioButton button = (RadioButton)stok_prod_group.getSelectedToggle();
        String a =button.getId();
        if(Objects.equals(a, "stok_prod_all")){
            stok_prod_refresh(1);

        }else if(Objects.equals(a, "stok_prod_wip")){
            stok_prod_refresh(3);

        }
        else if(Objects.equals(a, "stok_prod_fg")){
            stok_prod_refresh(2);

        }
    }
    @SuppressWarnings("all")
    private void stok_prod_refresh(int i){
        try {
            stok_prod_tv.getItems().clear();
            stok_prod_models.clear();
            Connection con = null;
            ResultSet rs = null;
            Statement stmt = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            int ok=0;int ng = 0;int wip=0;int total=0;
            float runner =0;float bonggol=0;
            if(i==1){
                rs = stmt.executeQuery("SELECT nama_part,ok,ng,runner,bonggol,wip,stat FROM stok_barang_fresh ORDER BY nama_part ASC,stat ASC;");
                while (rs.next()){
                    ok = rs.getInt(2);
                    ng = rs.getInt(3);
                    wip = rs.getInt(6);
                    total = ok+ng+wip;
                    stok_prod_models.addAll(new stok_prod_model(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),String.valueOf(total)));
                    stok_prod_namapart.setCellValueFactory(cellData->cellData.getValue().namapartProperty());
                    stok_prod_stok.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
                    stok_prod_ng.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                    stok_prod_runner.setCellValueFactory(cellData -> cellData.getValue().runnerProperty());
                    stok_prod_bonggol.setCellValueFactory(cellData-> cellData.getValue().bonggolProperty());
                    stok_prod_wip1.setCellValueFactory(cellData->cellData.getValue().wipProperty());
                    stok_prod_total.setCellValueFactory(cellData->cellData.getValue().totalProperty());
                    stok_prod_status.setCellValueFactory(cellData->cellData.getValue().statusProperty());

                }
                stok_prod_tv.setItems(getStok_prod_models());
                con.close();
            }else if(i==2){
                rs = stmt.executeQuery("SELECT nama_part,ok,ng,runner,bonggol,wip,stat FROM stok_barang_fresh WHERE stat='FG' ORDER BY nama_part ASC;");
                while (rs.next()){
                    ok = rs.getInt(2);
                    ng = rs.getInt(3);
                    wip = rs.getInt(6);
                    total = ok+ng+wip;
                    stok_prod_models.addAll(new stok_prod_model(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),String.valueOf(total)));
                    stok_prod_namapart.setCellValueFactory(cellData->cellData.getValue().namapartProperty());
                    stok_prod_stok.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
                    stok_prod_ng.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                    stok_prod_runner.setCellValueFactory(cellData -> cellData.getValue().runnerProperty());
                    stok_prod_bonggol.setCellValueFactory(cellData-> cellData.getValue().bonggolProperty());
                    stok_prod_wip1.setCellValueFactory(cellData->cellData.getValue().wipProperty());
                    stok_prod_total.setCellValueFactory(cellData->cellData.getValue().totalProperty());
                    stok_prod_status.setCellValueFactory(cellData->cellData.getValue().statusProperty());

                }
                stok_prod_tv.setItems(getStok_prod_models());
                con.close();
            }else if(i == 3){
                rs = stmt.executeQuery("SELECT nama_part,ok,ng,runner,bonggol,wip,stat FROM stok_barang_fresh WHERE stat='WIP' ORDER BY nama_part ASC;");
                while (rs.next()){
                    ok = rs.getInt(2);
                    ng = rs.getInt(3);
                    wip = rs.getInt(6);
                    total = ok+ng+wip;
                    stok_prod_models.addAll(new stok_prod_model(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),String.valueOf(total)));
                    stok_prod_namapart.setCellValueFactory(cellData->cellData.getValue().namapartProperty());
                    stok_prod_stok.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
                    stok_prod_ng.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                    stok_prod_runner.setCellValueFactory(cellData -> cellData.getValue().runnerProperty());
                    stok_prod_bonggol.setCellValueFactory(cellData-> cellData.getValue().bonggolProperty());
                    stok_prod_wip1.setCellValueFactory(cellData->cellData.getValue().wipProperty());
                    stok_prod_total.setCellValueFactory(cellData->cellData.getValue().totalProperty());
                    stok_prod_status.setCellValueFactory(cellData->cellData.getValue().statusProperty());

                }
                stok_prod_tv.setItems(getStok_prod_models());
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private ObservableList<stok_prod_model> getStok_prod_models(){
        return stok_prod_models;
    }

    //Stok WIP
    private ObservableList<stok_wip_model> getStok_wip_models(){
        return stok_wip_models;
    }
    @FXML private void stok_wip_refresh(){
        try {
            stok_wip_models.clear();
            stok_wip_tv.getItems().clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,a.wip,b.wip FROM stok_barang_wip a,stok_barang_fresh b WHERE a.nama_part = b.nama_part && stat='WIP' ORDER BY a.nama_part ASC;");
            while (rs.next()){
                stok_wip_models.addAll(new stok_wip_model(rs.getString(1),rs.getString(2),rs.getString(3)));
                stok_wip_namapart.setCellValueFactory(cellData -> cellData.getValue().namapartProperty());
                stok_wip_permintaanassy.setCellValueFactory(cellData -> cellData.getValue().wipProperty());
                stok_wip_sisa.setCellValueFactory(cellData -> cellData.getValue().sisaProperty());
            }
            stok_wip_tv.setItems(getStok_wip_models());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Stok Assy
    private ObservableList<stok_assy_model> getStok_assy_models(){
        return stok_assy_models;
    }
    @FXML private void stok_assy_refresh(){
        try {
            stok_assy_tv.getItems().clear();
            stok_assy_models.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            int ok = 0;
            int ng = 0;
            int tot = 0;
            rs = stmt.executeQuery("SELECT nama_part,stok,ng FROM stok_barang_subassy ORDER BY nama_part ASC");
            while (rs.next()){
                ok = rs.getInt(2);
                ng = rs.getInt(3);
                tot = ok+ng;
                stok_assy_models.addAll(new stok_assy_model(rs.getString(1),rs.getString(2),rs.getString(3),String.valueOf(tot)));
                stok_assy_namapart.setCellValueFactory(cellData -> cellData.getValue().namapartProperty());
                stok_assy_jumlah.setCellValueFactory(cellData -> cellData.getValue().jumlahProperty());
                stok_assy_ng.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                stok_assy_total.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
            }
            stok_assy_tv.setItems(getStok_assy_models());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //STOK ONHOLD
    private ObservableList<stok_onhold_model> getStok_onhold_models(){
        return stok_onhold_models;
    }
    @FXML private void stok_onhold_refresh(){
        try {
            stok_onhold_tv.getItems().clear();
            stok_onhold_models.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,on_hold FROM stok_barang_fresh ORDER BY nama_part ASC;");
            while (rs.next()){
                stok_onhold_models.addAll(new stok_onhold_model(rs.getString(1),rs.getString(2)));
                stok_onhold_namapart.setCellValueFactory(cellData -> cellData.getValue().namapartProperty());
                stok_onhold_onhold.setCellValueFactory(cellData -> cellData.getValue().onholdProperty());

            }
            stok_onhold_tv.setItems(getStok_onhold_models());
            con.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //Stok FG
    private ObservableList<stok_fg_model> getStok_fg_models(){
        return stok_fg_models;
    }

    @SuppressWarnings("all")
    @FXML private void stok_fg_refresh(){
        RadioButton button = (RadioButton)stok_fg_group.getSelectedToggle();
        String a =button.getId();

        if(Objects.equals(a, "stok_fg_nonassy")){
            stok_fg_jenis(1);

        }else if(Objects.equals(a, "stok_fg_assy")){
            stok_fg_jenis(2);
        }else if(Objects.equals(a, "stok_fg_all")){
            stok_fg_jenis(3);
        }
    }
    @SuppressWarnings("all")
    private void stok_fg_jenis(int i){
        try {
            stok_fg_tv.getItems().clear();
            stok_fg_models.clear();
            Connection con = null;
            Statement stmt =null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            String stat = null;
            if(i== 1){
                rs = stmt.executeQuery("SELECT nama_part,jumlah,stat FROM stok_wfg_total WHERE stat='non' ORDER BY nama_part ASC;");
                while (rs.next()){
                    stok_fg_models.addAll(new stok_fg_model(rs.getString(1),rs.getString(2),"Non Assy"));
                    stok_fg_namapart.setCellValueFactory(cellData->cellData.getValue().namapartProperty());
                    stok_fg_stok.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
                    stok_fg_jenis.setCellValueFactory(cellData -> cellData.getValue().jenisProperty());
                }
                stok_fg_tv.setItems(getStok_fg_models());
                con.close();
            }
            else if(i == 2){
                rs = stmt.executeQuery("SELECT nama_part,jumlah,stat FROM stok_wfg_total WHERE stat='assy' ORDER BY nama_part ASC;");
                while (rs.next()){
                    stok_fg_models.addAll(new stok_fg_model(rs.getString(1),rs.getString(2),"Assy"));
                    stok_fg_namapart.setCellValueFactory(cellData->cellData.getValue().namapartProperty());
                    stok_fg_stok.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
                    stok_fg_jenis.setCellValueFactory(cellData -> cellData.getValue().jenisProperty());
                }
                stok_fg_tv.setItems(getStok_fg_models());
                con.close();
            }
            else if(i==3){
                rs = stmt.executeQuery("SELECT nama_part,jumlah,stat FROM stok_wfg_total ORDER BY nama_part ASC,stat DESC ;");
                while (rs.next()){
                    if(rs.getString(3).equals("non")){
                        stat = "Non Assy";
                    }else if(rs.getString(3).equals("assy")){
                        stat = "Assy";
                    }
                    stok_fg_models.addAll(new stok_fg_model(rs.getString(1),rs.getString(2),stat));
                    stok_fg_namapart.setCellValueFactory(cellData->cellData.getValue().namapartProperty());
                    stok_fg_stok.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
                    stok_fg_jenis.setCellValueFactory(cellData -> cellData.getValue().jenisProperty());
                }
                stok_fg_tv.setItems(getStok_fg_models());
                con.close();
            }
        } catch (Exception e) {
        }

    }


}
