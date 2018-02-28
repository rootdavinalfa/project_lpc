package com.lpc.control;

import com.lpc.driver.connector;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import javax.annotation.Resource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

public class finishgoodControl implements Initializable {
    @FXML
    private Label t2_label_date;
    @FXML
    private TableView<fg_t2_model> t2_tv;
    @FXML
    private TableColumn<fg_t2_model, String> t2_npCOL;
    @FXML
    private TableColumn<fg_t2_model, String> t2_jumCOL;
    @FXML
    private TableColumn<fg_t2_model, String> t2_anonCOL;

    //Stok Region (NON)
    @FXML
    private Label stok_nontgl;
    @FXML
    private TextField stok_nonNP;
    @FXML
    private TextField stok_nonPICP;
    @FXML
    private TextField stok_nonPICW;
    @FXML
    private TextField stok_nonJUM;
    @FXML
    private TextArea stok_nonKET;
    @FXML
    private TableView<stok_nonmodel> stok_nonTV;
    @FXML
    private TableColumn<stok_nonmodel, String> stok_nonNPCOL;
    @FXML
    private TableColumn<stok_nonmodel, String> stok_nonPICPCOL;
    @FXML
    private TableColumn<stok_nonmodel, String> stok_nonPICWCOL;
    @FXML
    private TableColumn<stok_nonmodel, String> stok_nonJMLCOL;
    @FXML
    private TableView<stok_nonmodel1> stok_nonTV1;
    @FXML
    private TableColumn<stok_nonmodel1, String> stok_nonNPCOL1;
    @FXML
    private TableColumn<stok_nonmodel1, String> stok_nonOKCOL1;
    @FXML
    private TableColumn<stok_nonmodel1, String> stok_nonNGCOL1;

    private ObservableList<stok_nonmodel> stok_non = FXCollections.observableArrayList();
    private ObservableList<stok_nonmodel1> stok_non1 = FXCollections.observableArrayList();

    //Stok SSA(ASSY)
    @FXML
    private Label ssa_tgl;
    @FXML
    private TextField stok_ssaNP;
    @FXML
    private TextField stok_ssaPIP;
    @FXML
    private TextField stok_ssaPIW;
    @FXML
    private TextField stok_ssaJML;
    @FXML
    private TextArea stok_ssaKET;
    @FXML
    private TableView<stok_ssamodel> ssa_TV;
    @FXML
    private TableColumn<stok_ssamodel, String> ssa_NPCOL;
    @FXML
    private TableColumn<stok_ssamodel, String> ssa_PIPCOL;
    @FXML
    private TableColumn<stok_ssamodel, String> ssa_PIWCOL;
    @FXML
    private TableColumn<stok_ssamodel, String> ssa_JMLCOL;
    @FXML
    private TableView<stok_ssamodel1> ssa_TV1;
    @FXML
    private TableColumn<stok_ssamodel1, String> ssa_kodeCOL;
    @FXML
    private TableColumn<stok_ssamodel1, String> ssa_NPCOL1;
    @FXML
    private TableColumn<stok_ssamodel1, String> ssa_jmlCOL1;

    private ObservableList<stok_ssamodel1> ssa_mod1 = FXCollections.observableArrayList();
    private ObservableList<stok_ssamodel> ssa_mod = FXCollections.observableArrayList();


    private ObservableList<fg_t2_model> t2_models = FXCollections.observableArrayList();


    //Stok FG
    @FXML private RadioButton stok_fg_all;
    @FXML private RadioButton stok_fg_assy;
    @FXML private RadioButton stok_fg_non;
    final ToggleGroup stok_fg_group = new ToggleGroup();


    public void initialize(URL url, ResourceBundle rb) {
        setCurrentTime();
        setCurrentTime1();
        t2_refresh();
        stok_nonrefresh();
        stok_ssarefresh();
        radiobutton_stok_fg();
    }

    private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss E");
                                t2_label_date.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setCurrentTime1() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                //t2_label_date.setText(simpleDateFormat.format(time.getTime()));
                                stok_nontgl.setText(simpleDateFormat.format(time.getTime()));
                                ssa_tgl.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void t2_refresh() {
        try {
            t2_tv.getItems().clear();
            getT2_models().clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,jumlah,stat FROM stok_wfg_total ORDER BY nama_part ASC,stat ASC;");
            while (rs.next()) {
                t2_models.addAll(new fg_t2_model(rs.getString(1), rs.getString(2), rs.getString(3)));
                t2_npCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                t2_jumCOL.setCellValueFactory(cellData -> cellData.getValue().jumlahProperty());
                t2_anonCOL.setCellValueFactory(cellData -> cellData.getValue().statProperty());
            }
            t2_tv.setItems(getT2_models());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void radiobutton_stok_fg(){
        stok_fg_all.setToggleGroup(stok_fg_group);
        stok_fg_assy.setToggleGroup(stok_fg_group);
        stok_fg_non.setToggleGroup(stok_fg_group);
        stok_fg_all.setSelected(true);
        stok_fg_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (stok_fg_group.getSelectedToggle() != null) {
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    RadioButton button = (RadioButton)stok_fg_group.getSelectedToggle();
                    String a =button.getId();

                    if(Objects.equals(a, "stok_fg_all")){
                        rdcprod1();

                    }else if(Objects.equals(a, "stok_fg_assy")){
                        rdcprod2();

                    }
                    else if(Objects.equals(a, "stok_fg_non")){
                        rdcprod3();

                    }
                    System.out.println(a);
                }
            }
        });
    }
    private void rdcprod1(){
        t2_refresh();
    }
    @SuppressWarnings("all")
    private void rdcprod2(){
        try {
            t2_tv.getItems().clear();
            getT2_models().clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,jumlah,stat FROM stok_wfg_total WHERE stat='assy' ORDER BY nama_part ASC ;");
            while (rs.next()) {
                t2_models.addAll(new fg_t2_model(rs.getString(1), rs.getString(2), rs.getString(3)));
                t2_npCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                t2_jumCOL.setCellValueFactory(cellData -> cellData.getValue().jumlahProperty());
                t2_anonCOL.setCellValueFactory(cellData -> cellData.getValue().statProperty());
            }
            t2_tv.setItems(getT2_models());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @SuppressWarnings("all")
    private void rdcprod3(){
        try {
            t2_tv.getItems().clear();
            getT2_models().clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,jumlah,stat FROM stok_wfg_total WHERE stat='non' ORDER BY nama_part ASC ;");
            while (rs.next()) {
                t2_models.addAll(new fg_t2_model(rs.getString(1), rs.getString(2), rs.getString(3)));
                t2_npCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                t2_jumCOL.setCellValueFactory(cellData -> cellData.getValue().jumlahProperty());
                t2_anonCOL.setCellValueFactory(cellData -> cellData.getValue().statProperty());
            }
            t2_tv.setItems(getT2_models());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private ObservableList<fg_t2_model> getT2_models() {
        return t2_models;
    }

    //Stok NON Region
    public ObservableList<stok_nonmodel1> getStok_non1() {
        return stok_non1;
    }

    public ObservableList<stok_nonmodel> getStok_non() {
        return stok_non;
    }

    //@SuppressWarnings("all")
    @FXML
    protected void stok_nonATL() {
        try {
            String np = stok_nonNP.getText();
            String picp = stok_nonPICP.getText();
            String picw = stok_nonPICW.getText();
            String jml = stok_nonJUM.getText();
            String ket = stok_nonKET.getText();
            String np1 = null;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE code_part='" + np + "' && a.nama_part = b.nama_part;");
            if (rs.next()) {
                do {
                    np1 = rs.getString(1);
                }
                while (rs.next());

                if (stok_nonTV.getItems().size() < 15) {
                    stok_non.addAll(new stok_nonmodel(np1, picp, picw, jml, ket));
                    stok_nonNPCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                    stok_nonPICPCOL.setCellValueFactory(cellData -> cellData.getValue().picpProperty());
                    stok_nonPICWCOL.setCellValueFactory(cellData -> cellData.getValue().picwProperty());
                    stok_nonJMLCOL.setCellValueFactory(cellData -> cellData.getValue().jmlProperty());
                    stok_nonTV.setItems(getStok_non());
                } else {
                    alert al = new alert();
                    al.max15();
                }
            } else {
                alert al = new alert();
                al.warn_datanotfound();
            }

        } catch (Exception e) {

        }
    }

    //@SuppressWarnings("all")
    @FXML
    protected void stok_nonEXP() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            String tgl = stok_nontgl.getText();

            for (int i = 0; i < stok_nonTV.getItems().size(); i++) {
                int jm = 0;
                int cus = 0;
                int tot = 0;
                int jm1 = 0;
                int jm2 = 0;
                int tot1 = 0;
                String td = null;
                String stat = "non";
                String nama_p = stok_nonTV.getItems().get(i).getNama_part();
                String picp = stok_nonTV.getItems().get(i).getPicp();
                String picw = stok_nonTV.getItems().get(i).getPicw();
                String jml = stok_nonTV.getItems().get(i).getJml();
                int jml1 = Integer.parseInt(jml);
                String ket = stok_non.get(i).getKet();
                rs = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='" + nama_p + "' && stat = '" + stat + "';");
                if (rs.next()) {
                    rs2 = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='" + nama_p + "' && stat='" + stat + "';");
                    while (rs2.next()) {
                        jm = rs2.getInt(1);
                    }
                    rs1 = stmt.executeQuery("SELECT ok FROM stok_barang_fresh WHERE nama_part='" + nama_p + "';");
                    while (rs1.next()) {
                        jm1 = rs1.getInt(1);
                    }
                    tot = jm + jml1;
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("UPDATE stok_wfg_total SET jumlah='" + tot + "' WHERE nama_part='" + nama_p + "' && stat='" + stat + "';");
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket) VALUES ('" + nama_p + "','" + jml + "','" + tgl + "','" + picp + "','" + picw + "','" + ket + "');");
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET ok='" + tot1 + "' WHERE nama_part='" + nama_p + "';");
                } else if (!rs.next()) {
                    rs1 = stmt.executeQuery("SELECT ok FROM stok_barang_fresh WHERE nama_part='" + nama_p + "';");
                    while (rs1.next()) {
                        jm1 = rs1.getInt(1);
                    }
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket,stat) VALUES ('" + nama_p + "','" + jml + "','" + tgl + "','" + picp + "','" + picw + "','" + ket + "','" + stat + "');");
                    stmt.executeUpdate("INSERT INTO  stok_wfg_total(nama_part, jumlah,stat) VALUES ('" + nama_p + "','" + jml + "','" + stat + "');");
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET ok='" + tot1 + "' WHERE nama_part='" + nama_p + "';");
                }
            }
            con.close();
            stok_nonTV.getItems().clear();
            stok_non.clear();
            stok_nonrefresh();
            alert al = new alert();
            al.info_upload();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //@SuppressWarnings("all")
    @FXML
    protected void stok_nonrefresh() {
        try {
            stok_nonTV1.getItems().clear();
            stok_non1.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,a.ok,b.code_part FROM stok_barang_fresh a,list_part b WHERE a.nama_part = b.nama_part;");
            while (rs.next()) {
                stok_non1.addAll(new stok_nonmodel1(rs.getString(1), rs.getString(2), rs.getString(3)));
                stok_nonNPCOL1.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                stok_nonOKCOL1.setCellValueFactory(cellData -> cellData.getValue().okProperty());
                stok_nonNGCOL1.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
            }
            stok_nonTV1.setItems(getStok_non1());

        } catch (Exception e) {
        }
    }

    //SSA Sub ASSY Region
    public ObservableList<stok_ssamodel> getSsa_mod() {
        return ssa_mod;
    }

    public ObservableList<stok_ssamodel1> getSsa_mod1() {
        return ssa_mod1;
    }

    //@SuppressWarnings("all")
    @FXML
    protected void ssa_ATL() {
        try {
            String np = stok_ssaNP.getText();
            String picp = stok_ssaPIP.getText();
            String picw = stok_ssaPIW.getText();
            String jml = stok_ssaJML.getText();
            String ket = stok_ssaKET.getText();
            String np1 = null;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE code_part='" + np + "' && a.nama_part = b.nama_part;");
            if (rs.next()) {
                do {
                    np1 = rs.getString(1);
                }
                while (rs.next());

                if (ssa_TV.getItems().size() < 15) {
                    ssa_mod.addAll(new stok_ssamodel(np1, picp, picw, jml, ket));
                    ssa_NPCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                    ssa_PIPCOL.setCellValueFactory(cellData -> cellData.getValue().picpProperty());
                    ssa_PIWCOL.setCellValueFactory(cellData -> cellData.getValue().picwProperty());
                    ssa_JMLCOL.setCellValueFactory(cellData -> cellData.getValue().jmlProperty());
                    ssa_TV.setItems(getSsa_mod());
                } else {
                    alert al = new alert();
                    al.max15();
                }
            } else {
                alert al = new alert();
                al.warn_datanotfound();
            }

        } catch (Exception e) {

        }
    }

    //@SuppressWarnings("all")
    @FXML
    protected void ssa_EXP() {
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            String tgl = ssa_tgl.getText();

            for (int i = 0; i < ssa_TV.getItems().size(); i++) {
                int jm = 0;
                int cus = 0;
                int tot = 0;
                int jm1 = 0;
                int jm2 = 0;
                int tot1 = 0;
                String td = null;
                String stat = "assy";
                String nama_p = ssa_TV.getItems().get(i).getNama_part();
                String picp = ssa_TV.getItems().get(i).getPicp();
                String picw = ssa_TV.getItems().get(i).getPicw();
                String jml = ssa_TV.getItems().get(i).getJml();
                int jml1 = Integer.parseInt(jml);
                String ket = ssa_mod.get(i).getKet();
                rs = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='" + nama_p + "' && stat='" + stat + "';");
                if (rs.next()) {
                    rs2 = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='" + nama_p + "' && stat ='" + stat + "';");
                    while (rs2.next()) {
                        jm = rs2.getInt(1);
                    }
                    rs1 = stmt.executeQuery("SELECT stok FROM stok_barang_subassy WHERE nama_part='" + nama_p + "';");
                    while (rs1.next()) {
                        jm1 = rs1.getInt(1);
                    }
                    tot = jm + jml1;
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("UPDATE stok_wfg_total SET jumlah='" + tot + "' WHERE nama_part='" + nama_p + "' && stat='" + stat + "';");
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket) VALUES ('" + nama_p + "','" + jml + "','" + tgl + "','" + picp + "','" + picw + "','" + ket + "');");
                    stmt.executeUpdate("UPDATE stok_barang_subassy SET stok='" + tot1 + "' WHERE nama_part='" + nama_p + "';");
                } else if (!rs.next()) {
                    rs1 = stmt.executeQuery("SELECT stok FROM stok_barang_subassy WHERE nama_part='" + nama_p + "';");
                    while (rs1.next()) {
                        jm1 = rs1.getInt(1);
                    }
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket,stat) VALUES ('" + nama_p + "','" + jml + "','" + tgl + "','" + picp + "','" + picw + "','" + ket + "','" + stat + "');");
                    stmt.executeUpdate("INSERT INTO  stok_wfg_total(nama_part, jumlah,stat) VALUES ('" + nama_p + "','" + jml + "','" + stat + "');");
                    stmt.executeUpdate("UPDATE stok_barang_subassy SET stok='" + tot1 + "' WHERE nama_part='" + nama_p + "';");
                }
            }
            con.close();
            ssa_TV.getItems().clear();
            ssa_mod.clear();
            stok_ssarefresh();
            alert al = new alert();
            al.info_upload();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //@SuppressWarnings("all")
    @FXML
    protected void stok_ssarefresh() {
        try {
            ssa_TV1.getItems().clear();
            ssa_mod1.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,a.stok,b.code_part FROM stok_barang_subassy a,list_part b WHERE a.nama_part = b.nama_part;");
            while (rs.next()) {
                ssa_mod1.addAll(new stok_ssamodel1(rs.getString(1), rs.getString(2), rs.getString(3)));
                ssa_NPCOL1.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                ssa_jmlCOL1.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
                ssa_kodeCOL.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
                System.out.println(rs.getString(1));
            }
            ssa_TV1.setItems(getSsa_mod1());

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}