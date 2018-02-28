package com.lpc.control;

import com.lpc.driver.connector;


import com.lpc.ui.alert;
import davin.alfa.calendar;
import davin.alfa.pass;
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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.text.html.Option;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class produksiControl implements Initializable{
    @FXML
    private Label dateDisplay;
    @FXML
    private Label lbl_nmatinj;
    @FXML
    private Label lbl_beratP;
    @FXML
    private Label lbl_beratR;
    @FXML
    private Label lbl_beratT;
    @FXML
    private Label lbl_cti;
    @FXML
    private Label lbl_hinj;
    @FXML
    private Label lbl_htot;
    @FXML
    private Label lbl_np;
    @FXML
    private Label lbl_nmatassy;
    @FXML
    private Label lbl_hassy;
    @FXML
    private Label lbl_qty;
    @FXML
    private Label lbl_warna;
    @FXML
    private Label lbl_cust;
    @FXML
    private CheckBox allow_up;
    @FXML
    private CheckBox allow_upLP;
    @FXML
    private CheckBox cb_shiftCP;
    @FXML
    private TextField tf_kodeP;
    @FXML
    private ListView listView;
    @FXML
    private Label lbl_time;
    @FXML
    private Label lbl_shift;
    @FXML
    private Label lbl_namaP;
    @FXML
    private Label lbl_timeCP;
    @FXML
    private Label lbl_namaPCP;
    @FXML
    private TextField tf_kpLAP;
    @FXML
    private TextField tf_targetLAP;
    @FXML
    private TextField tf_actualLAP;
    @FXML
    private TextField tf_ngLAP;
    @FXML private TextField tf_onholdLAP;
    @FXML
    private TextField tf_kwhAwLAP;
    @FXML
    private TextField tf_kwhAkLAP;
    @FXML
    private TextField tf_kpCP;
    @FXML
    private ComboBox combo_shiftCP;
    @FXML
    private RadioButton rdbtn_hariini;
    @FXML
    private RadioButton rdbtn_tanggal;
    @FXML
    private RadioButton rdbtn_rentang_tanggal;
    @FXML
    private DatePicker dateCP_awal;
    @FXML
    private DatePicker dateCP_akhir;

    @FXML
    private TextField tf_namaOP;
    @FXML
    private TextField tfberat_part;
    @FXML
    private TextField tfberat_runner;
    @FXML
    private TextField tf_cti;
    @FXML
    private TextField tf_timeProd;
    @FXML
    private Label lbl_matINJ,target_ctp;
    @FXML
    private ListView list_matAssy;
    @FXML
    private Label lbl_lap_namapart;
    @FXML
    private Label lbl_totalTarget;
    @FXML
    private Label lbl_totalActual;
    @FXML
    private Label lbl_totalNG;
    @FXML
    private Label lbl_totalKWH;
    @FXML private CheckBox this_wip_part;
    @FXML private TextField tf_bonggolLAP;
    @FXML private TextField lap_kodepart_xport;
    @FXML private TextField tf_cav;
    @FXML private Label lbl_npcprod;
    @FXML private TextField kp_cprod;
    @FXML private RadioButton rdbtn_cprod1;
    @FXML private RadioButton rdbtn_cprodrt;
    @FXML private DatePicker datepick1cprod;
    @FXML private DatePicker datepick2cprod;
    @FXML private TableView<cekprod> cprod_tab;
    @FXML private TableColumn<cekprod,String> npcprodCOL;
    @FXML private TableColumn<cekprod,String >opcprodCOL;
    @FXML private TableColumn<cekprod,String> tanggalcprodCOL;
    @FXML private TableColumn<cekprod,String> jamcprodCOL;
    @FXML private TableColumn<cekprod,String> shiftcprodCOL;
    @FXML private TableColumn<cekprod,String> beratpcprodCOL;
    @FXML private TableColumn<cekprod,String> beratrcprodCOL;
    @FXML private TableColumn<cekprod,String> berattcprodCOL;
    @FXML private TableColumn<cekprod,String> cticprodCOL;
    @FXML private TableColumn<cekprod,String> goodcprodCOL;
    @FXML private TableColumn<cekprod,String> ngcprodCOL;
    @FXML private TableColumn<cekprod,String> kwhcprodCOL;
    @FXML private TableColumn<cekprod,String> cavcprodCOL;
    @FXML private TableColumn<cekprod,String> nomesinCOL;
    @FXML private TableColumn<cekprod,String> statCOL;
    @FXML private TextField tf_nomesin;
    @FXML private Label lbl_cav;
    @FXML private CheckBox manualOverride;
    @FXML private DatePicker datepickLAP;
    @FXML private TextField tf_shiftLAP;
    @FXML private TextArea keterangan_TA;
    //@FXML private ListView list_kode;
    @FXML private TableView list_kodeTL;
    @FXML private TableColumn<kodep,String> lNAMAP_col;
    @FXML private TableColumn<kodep,String> lKODEP_col;
    @FXML private ComboBox<String> cb_untuk;
    private modal_cekprod mc;
    private Stage stage;
    private String time_shift;
    //private List<String> list_kodeP = new ArrayList<>();

    //Subassy Region
    @FXML private TextField sa_kodepart;
    @FXML private TextField sa_jumlahwip;
    @FXML private TextField sa_ckkodepart;
    @FXML private TextField sa_lapkodepart;
    @FXML private TextField sa_lapjumlah;
    @FXML private TextField sa_lapjumlah1;
    @FXML private DatePicker sa_datelap;
    @FXML private TableView <model_subassy> sa_wipTV;
    @FXML private TableColumn<model_subassy,String> sa_namapCOL;
    @FXML private TableColumn<model_subassy,String> sa_wipCOL;
    private ObservableList<model_subassy> modl_assy = FXCollections.observableArrayList();

    /*
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
*/
    //ONHOLD Region
    @FXML private TextField cek_onhold_kode;
    @FXML private Label lbl_cek_onhold_total;
    @FXML private Label lbl_cek_onhold_np;
    final private CategoryAxis xAxis = new CategoryAxis();
    final private NumberAxis yAxis = new NumberAxis();
    @FXML private BarChart cek_onhold_bar = new BarChart(xAxis,yAxis);
    @FXML private TextField onhold_kp;
    @FXML private TextField onhold_ok;
    @FXML private TextField onhold_ng;
    @FXML private ComboBox onhold_status;
    @FXML private TableView<onhold_model> onhold_TV;
    @FXML private TableColumn<onhold_model,String> onhold_npCOL;
    @FXML private TableColumn<onhold_model,String> onhold_okCOL;
    @FXML private TableColumn<onhold_model,String> onhold_ngCOL;
    @FXML private TableColumn<onhold_model,String> onhold_statusCOL;
    private ObservableList<onhold_model> onholdmod = FXCollections.observableArrayList();


    //Sub Assy Cek Stok
    @FXML private TableView<stok_assy_model> stok_assy_TV;
    @FXML private TableColumn<stok_assy_model,String> stok_assy_npCOL;
    @FXML private TableColumn<stok_assy_model,String> stok_assy_okCOL;
    @FXML private TableColumn<stok_assy_model,String> stok_assy_ngCOL;
    private ObservableList<stok_assy_model> stok_assy_models = FXCollections.observableArrayList();



    private List<String> list_assy = new ArrayList<>();
    private List<String> list_assy1 = new ArrayList<>();
    private ObservableList<cekprod> cprod = FXCollections.observableArrayList();
    private ObservableList<kodep> kodepe = FXCollections.observableArrayList();
    final ToggleGroup group_cp = new ToggleGroup();
    final ToggleGroup group_cprod = new ToggleGroup();
    public void initialize(URL url, ResourceBundle rb){
        //pass pd = new pass();
        //Optional<String> result = pd.showAndWait();
        //result.ifPresent(password -> System.out.println(password));

        setCurrentTime();
        tf();
        tf1();
        tf2();
        tf3();
        getTime();
        setShift();
        disabled();
        disabled1();
        disabled2();
        list_cq();
        radiobutton_cp();
        radiobutton_cprod();
        rdbtn_hi();
        //list_cb();
        shiftTF();
        manLAP();
        list_kod();
        //stok_nonrefresh();
        //stok_ssarefresh();
        cb_shiftCP.setSelected(true);
        manualOverride.setSelected(false);
        datepickLAP.setDisable(true);
        tf_shiftLAP.setDisable(true);
        ctprod_tabACT();
        onhold_shift();
        cek_stok_assy();
    }
    public ObservableList<kodep> getKodep(){
        return kodepe;
    }
    private void list_kod(){
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,code_part FROM list_part;");
            while (rs.next()){
                kodepe.addAll(new kodep(rs.getString(1),rs.getString(2)));
                lNAMAP_col.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                lKODEP_col.setCellValueFactory(cellData -> cellData.getValue().kode_partProperty());

            }
            list_kodeTL.setItems(getKodep());
            con.close();
        }
        catch (Exception e){

        }
    }
    private void ctprod_tabACT(){
        cprod_tab.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount() == 1 ){

                    select_cprod_tab();

            }
        });
    }
    private void select_cprod_tab(){
        if(cprod_tab.getSelectionModel().getSelectedItem() != null){
            try {
                String a = sendToModal();
                String b = sendToModal1();
                String c = sendToModal2();
                String d = sendToModal3();
                //cekprod selectedcp = cprod_tab.getSelectionModel().getSelectedItem();
                System.out.println(a);
                //mc = new modal_cekprod();
                //mc.redirect(stage,a,b,c);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/lpc/ui/selectedcekPROD.fxml"));
                loader.load();
                modal_cekprod mc = loader.getController();
                mc.set(a,b,c,d);
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(p));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public String sendToModal(){
        String a;
        cekprod selectedcp = cprod_tab.getSelectionModel().getSelectedItem();
        a = selectedcp.getNP();
        return a;
    }
    public String sendToModal1(){
        String a;
        cekprod selectedcp = cprod_tab.getSelectionModel().getSelectedItem();
        a = selectedcp.getJam();
        return a;
    }
    public String sendToModal2(){
        String a;
        cekprod selectedcp = cprod_tab.getSelectionModel().getSelectedItem();
        a = selectedcp.getDate();
        return a;
    }
    public String sendToModal3(){
        String a;
        cekprod selectedcp = cprod_tab.getSelectionModel().getSelectedItem();
        a = selectedcp.getStat();
        return a;
    }

    private void shiftTF(){
        EventHandler eh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();
                    if (cb_shiftCP.isSelected()) {
                        combo_shiftCP.setDisable(true);
                        //System.out.println("A");

                    }
                    else if (!cb_shiftCP.isSelected()){
                        //list_cb();
                        combo_shiftCP.setDisable(false);
                    }
                }
            }
        };
        cb_shiftCP.setOnAction(eh);
    }
    @SuppressWarnings("all")
    private void manLAP(){
        EventHandler eh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();
                    if (manualOverride.isSelected()) {
                        datepickLAP.setDisable(false);
                        tf_shiftLAP.setDisable(false);
                    }
                    else if (!manualOverride.isSelected()){
                        datepickLAP.setDisable(true);
                        tf_shiftLAP.setDisable(true);
                    }
                }
            }
        };
        manualOverride.setOnAction(eh);
    }
    private void list_cb(){

            //combo_shiftCP.setDisable(false);
            List<String> listshift = new ArrayList<String>();
            listshift.add("1");
            listshift.add("2");
            listshift.add("3");
            combo_shiftCP.getItems().addAll(FXCollections.observableArrayList(listshift));

    }
    private void list_cq(){

        //combo_shiftCP.setDisable(false);
        List<String> listshift = new ArrayList<String>();
        listshift.add("WIP");
        listshift.add("FG");
        cb_untuk.getItems().addAll(FXCollections.observableArrayList(listshift));

    }
    private void radiobutton_cprod(){
        rdbtn_cprod1.setToggleGroup(group_cprod);
        rdbtn_cprodrt.setToggleGroup(group_cprod);
        rdbtn_cprod1.setSelected(true);
        group_cprod.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group_cprod.getSelectedToggle() != null) {
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    RadioButton button = (RadioButton)group_cprod.getSelectedToggle();
                    String a =button.getId();

                    if(Objects.equals(a, "rdbtn_cprod1")){
                        rdcprod1();

                    }else if(Objects.equals(a, "rdbtn_cprodrt")){
                        rdcprod2();

                    }
                    System.out.println(a);
                }
            }
        });
    }
    private void radiobutton_cp(){
        rdbtn_hariini.setToggleGroup(group_cp);
        rdbtn_tanggal.setToggleGroup(group_cp);
        rdbtn_rentang_tanggal.setToggleGroup(group_cp);
        rdbtn_hariini.setSelected(true);
        group_cp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group_cp.getSelectedToggle() != null) {
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    RadioButton button = (RadioButton)group_cp.getSelectedToggle();
                    String a =button.getId();

                    if(Objects.equals(a, "rdbtn_hariini")){
                        rdbtn_hi();

                    }else if(Objects.equals(a, "rdbtn_tanggal")){
                        rdbtn_tgl();
                    }
                    else if(Objects.equals(a, "rdbtn_rentang_tanggal")){
                        rdbtn_rtgl();

                    }
                    System.out.println(a);
                }
            }
        });
    }
    private void rdbtn_hi(){
        dateCP_awal.setDisable(true);
        dateCP_akhir.setDisable(true);
    }
    private void rdbtn_tgl(){
        dateCP_awal.setDisable(false);
        dateCP_akhir.setDisable(true);
    }
    private void rdbtn_rtgl(){
        dateCP_awal.setDisable(false);
        dateCP_akhir.setDisable(false);
    }
    private void tf(){
        tf_kodeP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getData();
            }
        });
    }
    private void tf1(){
        tf_kpLAP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kpLAP();
            }
        });
    }
    private void tf2(){
        tf_kpCP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kpCP();
            }
        });
    }
    private void tf3(){
        kp_cprod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kcprod();
            }
        });
    }
    private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss E");
                                SimpleDateFormat stok_non = new SimpleDateFormat("yyyy/MM/dd");
                                dateDisplay.setText(simpleDateFormat.format(time.getTime()));
                                lbl_time.setText(simpleDateFormat.format(time.getTime()));
                                //stok_nontgl.setText(stok_non.format(time.getTime()));
                                //ssa_tgl.setText(stok_non.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void getTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                                lbl_timeCP.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void setShift() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                                time_shift = simpleDateFormat.format(time.getTime());
                                int shift = Integer.parseInt(time_shift);
                                if (shift >= 8 && shift <= 15) {
                                    lbl_shift.setText("Laporan Produksi Shift 1");
                                } else if (shift >= 16 && shift <= 23) {
                                    lbl_shift.setText("Laporan Produksi Shift 2");
                                } else if (shift >= 0 && shift <= 7) {
                                    lbl_shift.setText("Laporan Produksi Shift 3");
                                }

                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @SuppressWarnings("all")
    private void disabled(){
        tf_targetLAP.setDisable(true);
        tf_actualLAP.setDisable(true);
        tf_ngLAP.setDisable(true);
        tf_kwhAkLAP.setDisable(true);
        tf_kwhAwLAP.setDisable(true);
        tf_namaOP.setDisable(true);
        tfberat_part.setDisable(true);
        tfberat_runner.setDisable(true);
        tf_cti.setDisable(true);
        tf_timeProd.setDisable(true);
        tf_cav.setDisable(true);
        keterangan_TA.setDisable(true);
        tf_nomesin.setDisable(true);
        tf_onholdLAP.setDisable(true);
        tf_bonggolLAP.setDisable(true);
        cb_untuk.setDisable(true);
    }

    @SuppressWarnings("all")
    private void enabled(){
        tf_targetLAP.setDisable(false);
        tf_actualLAP.setDisable(false);
        tf_ngLAP.setDisable(false);
        tf_kwhAkLAP.setDisable(false);
        tf_kwhAwLAP.setDisable(false);
        tf_namaOP.setDisable(false);
        tfberat_part.setDisable(false);
        tfberat_runner.setDisable(false);
        tf_cti.setDisable(false);
        tf_timeProd.setDisable(false);
        tf_cav.setDisable(false);
        keterangan_TA.setDisable(false);
        tf_onholdLAP.setDisable(false);
        tf_nomesin.setDisable(false);
        tf_bonggolLAP.setDisable(false);
        cb_untuk.setDisable(false);
    }
    @SuppressWarnings("all")
    private void disabled1(){
        cb_shiftCP.setDisable(true);
        combo_shiftCP.setDisable(true);
        rdbtn_hariini.setDisable(true);
        rdbtn_tanggal.setDisable(true);
        rdbtn_rentang_tanggal.setDisable(true);
        dateCP_awal.setDisable(true);
        dateCP_akhir.setDisable(true);

    }

    @SuppressWarnings("all")
    private void enabled1(){
        cb_shiftCP.setDisable(false);
        //combo_shiftCP.setDisable(false);
        rdbtn_hariini.setDisable(false);
        rdbtn_tanggal.setDisable(false);
        rdbtn_rentang_tanggal.setDisable(false);
        //dateCP_awal.setDisable(false);
        dateCP_akhir.setDisable(false);
    }
    @SuppressWarnings("all")
    private void disabled2(){
        rdbtn_cprod1.setDisable(true);
        rdbtn_cprodrt.setDisable(true);
        datepick1cprod.setDisable(true);
        datepick2cprod.setDisable(true);

    }

    @SuppressWarnings("all")
    private void enabled2(){
        rdbtn_cprod1.setDisable(false);
        rdbtn_cprodrt.setDisable(false);
        datepick1cprod.setDisable(false);
        datepick2cprod.setDisable(true);
    }
    private void kpLAP(){
        String a = tf_kpLAP.getText();
        list_assy1.clear();
        list_matAssy.getItems().clear();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part ,b.nama_material,c.nama_material,c.jumlah,b.target FROM list_part a,list_material_inj b,list_material_assy c WHERE a.code_part='"+a+"' && a.nama_part = b.nama_part && b.nama_part = c.nama_part ;");
            if(rs.next()){
                do{
                    lbl_namaP.setText(rs.getString(1));
                    lbl_matINJ.setText(rs.getString(2));
                    list_assy1.add(rs.getString(3)+" @ "+rs.getString(4));
                    tf_targetLAP.setText(rs.getString(5));
                    ObservableList<String> m_assy1 = FXCollections.observableArrayList(list_assy1);
                    list_matAssy.setItems(m_assy1);
                    System.out.println(m_assy1);
                }
                while (rs.next());
            }
            else{
                alert al = new alert();
                al.warn_datanotfound();
            }

            enabled();
        }
        catch (Exception e){
            alert al = new alert();
            al.warn_datanotfound();
            System.out.print(e);
        }
    }
    private void rdcprod1(){
        datepick2cprod.setDisable(true);
    }
    private void rdcprod2(){
        datepick2cprod.setDisable(false);
    }
    private void kpCP(){
        String a = tf_kpCP.getText();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part FROM list_part WHERE code_part='"+a+"';");
            while (rs.next()){
                lbl_namaPCP.setText(rs.getString(1));
            }
            enabled1();
            list_cb();
        } catch (Exception e) {
            alert al = new alert();
            al.error_sql();
        }
    }

    private void kcprod(){
        String a = kp_cprod.getText();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part FROM list_part WHERE code_part='"+a+"';");
            while (rs.next()){
                lbl_npcprod.setText(rs.getString(1));
            }
            enabled2();
        } catch (Exception e) {
        }
    }
@SuppressWarnings("all")
    @FXML protected  void show_cprod(){
        if(this_wip_part.isSelected()){
            System.out.println("WIP");
            if(rdbtn_cprod1.isSelected()){
                cprod.clear();
                cprod_tab.setItems(null);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String a = lbl_npcprod.getText();
                String b = datepick1cprod.getValue().format(formatter);
                try {
                    Class.forName("com.lpc.driver.connector");
                    Connection con = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    con = connector.setConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT nama_part,operator,date,time,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,actual_produksi,ng_produksi,total_kwh,cav,no_mesin FROM laporan_produksi" +
                            " WHERE nama_part ='"+a+"' && date ='"+b+"' && stat='WIP';");
                    if(rs.next()){
                        do{
                            cprod.add(new cekprod(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),"WIP"));
                            npcprodCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                            opcprodCOL.setCellValueFactory(cellData -> cellData.getValue().operatorProperty());
                            tanggalcprodCOL.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());
                            jamcprodCOL.setCellValueFactory(cellData -> cellData.getValue().jamProperty());
                            shiftcprodCOL.setCellValueFactory(cellData -> cellData.getValue().shiftProperty());
                            beratpcprodCOL.setCellValueFactory(cellData ->cellData.getValue().beratpProperty());
                            beratrcprodCOL.setCellValueFactory(cellData -> cellData.getValue().beratrProperty());
                            berattcprodCOL.setCellValueFactory(cellData -> cellData.getValue().berattProperty());
                            cticprodCOL.setCellValueFactory(cellData -> cellData.getValue().ctiProperty());
                            goodcprodCOL.setCellValueFactory(cellData-> cellData.getValue().goodProperty());
                            ngcprodCOL.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                            kwhcprodCOL.setCellValueFactory(cellData -> cellData.getValue().kwhProperty());
                            cavcprodCOL.setCellValueFactory(cellData -> cellData.getValue().cavproperty());
                            nomesinCOL.setCellValueFactory(cellData -> cellData.getValue().nomesinProperty());
                            statCOL.setCellValueFactory(cellData -> cellData.getValue().statProperty());
                        }
                        while (rs.next());
                        cprod_tab.setItems(cprod);
                        disabled2();
                        con.close();
                    }
                    else{
                        alert al = new alert();
                        al.warn_datanotfound();
                    }

                } catch (Exception e) {
                }
            }
            else if(rdbtn_cprodrt.isSelected()){
                cprod.clear();
                cprod_tab.setItems(null);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String a = lbl_npcprod.getText();
                String b = datepick1cprod.getValue().format(formatter);
                String c = datepick2cprod.getValue().format(formatter);
                try {

                    Class.forName("com.lpc.driver.connector");
                    Connection con = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    con = connector.setConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT nama_part,operator,date,time,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,actual_produksi,ng_produksi,total_kwh,cav,no_mesin FROM laporan_produksi" +
                            " WHERE nama_part ='"+a+"' && stat='WIP' && date BETWEEN '"+b+"' AND '"+c+"';");
                    if(rs.next()){
                        do{
                            cprod.add(new cekprod(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),"WIP"));
                            npcprodCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                            opcprodCOL.setCellValueFactory(cellData -> cellData.getValue().operatorProperty());
                            tanggalcprodCOL.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());
                            jamcprodCOL.setCellValueFactory(cellData -> cellData.getValue().jamProperty());
                            shiftcprodCOL.setCellValueFactory(cellData -> cellData.getValue().shiftProperty());
                            beratpcprodCOL.setCellValueFactory(cellData ->cellData.getValue().beratpProperty());
                            beratrcprodCOL.setCellValueFactory(cellData -> cellData.getValue().beratrProperty());
                            berattcprodCOL.setCellValueFactory(cellData -> cellData.getValue().berattProperty());
                            cticprodCOL.setCellValueFactory(cellData -> cellData.getValue().ctiProperty());
                            goodcprodCOL.setCellValueFactory(cellData-> cellData.getValue().goodProperty());
                            ngcprodCOL.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                            kwhcprodCOL.setCellValueFactory(cellData -> cellData.getValue().kwhProperty());
                            cavcprodCOL.setCellValueFactory(cellData -> cellData.getValue().cavproperty());
                            nomesinCOL.setCellValueFactory(cellData -> cellData.getValue().nomesinProperty());
                            statCOL.setCellValueFactory(cellData -> cellData.getValue().statProperty());
                        }
                        while (rs.next());
                        cprod_tab.setItems(cprod);
                        disabled2();
                        con.close();
                    }
                    else{
                        alert al = new alert();
                        al.warn_datanotfound();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        else if(!this_wip_part.isSelected()){
            System.out.println("FG");
            if(rdbtn_cprod1.isSelected()){
                cprod.clear();
                cprod_tab.setItems(null);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String a = lbl_npcprod.getText();
                String b = datepick1cprod.getValue().format(formatter);
                try {
                    Class.forName("com.lpc.driver.connector");
                    Connection con = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    con = connector.setConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT nama_part,operator,date,time,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,actual_produksi,ng_produksi,total_kwh,cav,no_mesin FROM laporan_produksi" +
                            " WHERE nama_part ='"+a+"' && date ='"+b+"' && stat='FG';");
                    if(rs.next()){
                        do{
                            cprod.add(new cekprod(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),"FG"));
                            npcprodCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                            opcprodCOL.setCellValueFactory(cellData -> cellData.getValue().operatorProperty());
                            tanggalcprodCOL.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());
                            jamcprodCOL.setCellValueFactory(cellData -> cellData.getValue().jamProperty());
                            shiftcprodCOL.setCellValueFactory(cellData -> cellData.getValue().shiftProperty());
                            beratpcprodCOL.setCellValueFactory(cellData ->cellData.getValue().beratpProperty());
                            beratrcprodCOL.setCellValueFactory(cellData -> cellData.getValue().beratrProperty());
                            berattcprodCOL.setCellValueFactory(cellData -> cellData.getValue().berattProperty());
                            cticprodCOL.setCellValueFactory(cellData -> cellData.getValue().ctiProperty());
                            goodcprodCOL.setCellValueFactory(cellData-> cellData.getValue().goodProperty());
                            ngcprodCOL.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                            kwhcprodCOL.setCellValueFactory(cellData -> cellData.getValue().kwhProperty());
                            cavcprodCOL.setCellValueFactory(cellData -> cellData.getValue().cavproperty());
                            nomesinCOL.setCellValueFactory(cellData -> cellData.getValue().nomesinProperty());
                            statCOL.setCellValueFactory(cellData -> cellData.getValue().statProperty());
                        }
                        while (rs.next());
                        cprod_tab.setItems(cprod);
                        disabled2();
                        con.close();
                    }
                    else{
                        alert al = new alert();
                        al.warn_datanotfound();
                    }

                } catch (Exception e) {
                }
            }
            else if(rdbtn_cprodrt.isSelected()){
                cprod.clear();
                cprod_tab.setItems(null);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String a = lbl_npcprod.getText();
                String b = datepick1cprod.getValue().format(formatter);
                String c = datepick2cprod.getValue().format(formatter);
                try {

                    Class.forName("com.lpc.driver.connector");
                    Connection con = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    con = connector.setConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT nama_part,operator,date,time,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,actual_produksi,ng_produksi,total_kwh,cav,no_mesin FROM laporan_produksi" +
                            " WHERE nama_part ='"+a+"' && stat='FG' && date BETWEEN '"+b+"' AND '"+c+"';");
                    if(rs.next()){
                        do{
                            cprod.add(new cekprod(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),"FG"));
                            npcprodCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                            opcprodCOL.setCellValueFactory(cellData -> cellData.getValue().operatorProperty());
                            tanggalcprodCOL.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());
                            jamcprodCOL.setCellValueFactory(cellData -> cellData.getValue().jamProperty());
                            shiftcprodCOL.setCellValueFactory(cellData -> cellData.getValue().shiftProperty());
                            beratpcprodCOL.setCellValueFactory(cellData ->cellData.getValue().beratpProperty());
                            beratrcprodCOL.setCellValueFactory(cellData -> cellData.getValue().beratrProperty());
                            berattcprodCOL.setCellValueFactory(cellData -> cellData.getValue().berattProperty());
                            cticprodCOL.setCellValueFactory(cellData -> cellData.getValue().ctiProperty());
                            goodcprodCOL.setCellValueFactory(cellData-> cellData.getValue().goodProperty());
                            ngcprodCOL.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                            kwhcprodCOL.setCellValueFactory(cellData -> cellData.getValue().kwhProperty());
                            cavcprodCOL.setCellValueFactory(cellData -> cellData.getValue().cavproperty());
                            nomesinCOL.setCellValueFactory(cellData -> cellData.getValue().nomesinProperty());
                            statCOL.setCellValueFactory(cellData -> cellData.getValue().statProperty());
                        }
                        while (rs.next());
                        cprod_tab.setItems(cprod);
                        disabled2();
                        con.close();
                    }
                    else{
                        alert al = new alert();
                        al.warn_datanotfound();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
        else{
            alert al = new alert();
            al.warn_datanotfoundCPROD();
        }
    }
    protected void getTotalAssy(){
        String kode_part = tf_kodeP.getText();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT SUM(d.harga_assy) FROM list_part a,list_material_inj b,list_customer c,list_material_assy d WHERE " +
                    "a.code_part ='"+kode_part+"'&& a.custID = c.id_customer && a.nama_part = b.nama_part && a.nama_part = d.nama_part;");
            while(rs.next()){
                lbl_hassy.setText(rs.getString(1));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML protected void clearLAP(){
        tf_kpLAP.setText("");
        tf_targetLAP.setText("");
        tf_actualLAP.setText("");
        tf_ngLAP.setText("");
        tf_kwhAwLAP.setText("");
        tf_kwhAkLAP.setText("");
        tf_timeProd.setText("");
        tf_namaOP.setText("");
        tfberat_part.setText("");
        tfberat_runner.setText("");
        tf_cti.setText("");
        list_matAssy.setItems(null);
        list_assy.clear();
        lbl_namaP.setText("");
    }
    @SuppressWarnings("all")
    public void ohold(){
        if(!manualOverride.isSelected()){
            try {
                Calendar time = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String dt = simpleDateFormat.format(time.getTime());

                String namaP = lbl_namaP.getText();

                int b = 0;
                int a = Integer.parseInt(tf_onholdLAP.getText());
                int c = 0;
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT nama_part FROM stok_barang_fresh WHERE nama_part='"+namaP+"';");
                if(rs.next()){
                    rs1 = stmt.executeQuery("SELECT on_hold FROM stok_barang_fresh WHERE nama_part='"+namaP+"';");
                    while(rs1.next()){
                        b = rs1.getInt(1);
                    }
                    c = a + b;
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET on_hold='"+c+"' WHERE nama_part='"+namaP+"';");
                    stmt.executeUpdate("INSERT INTO laporan_onhold(nama_part, date, jumlah) VALUES ('"+namaP+"','"+dt+"','"+a+"');");

                }
                else if (!rs.next()){
                    //stmt.executeUpdate("INSERT INTO laporan_onhold(nama_part, date, jumlah) VALUES ('"+namaP+"','"+dt+"','"+a+"');");
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET on_hold='"+a+"' WHERE nama_part='"+namaP+"';");
                    //stmt.executeUpdate("INSERT INTO stok_barang_onhold(nama_part, stok) VALUES ('"+namaP+"','"+a+"');");
                    stmt.executeUpdate("INSERT INTO laporan_onhold(nama_part, date, jumlah) VALUES ('"+namaP+"','"+dt+"','"+a+"');");

                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(manualOverride.isSelected()){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String dt = datepickLAP.getValue().format(formatter);

                String namaP = lbl_namaP.getText();

                int b = 0;
                int a = Integer.parseInt(tf_onholdLAP.getText());
                int c = 0;
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                ResultSet rs1 = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT nama_part FROM stok_barang_fresh WHERE nama_part='"+namaP+"';");
                if(rs.next()){
                    rs1 = stmt.executeQuery("SELECT on_hold FROM stok_barang_fresh WHERE nama_part='"+namaP+"';");
                    while(rs1.next()){
                        b = rs1.getInt(1);
                    }
                    c = a + b;
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET on_hold='"+c+"' WHERE nama_part='"+namaP+"';");
                    stmt.executeUpdate("INSERT INTO laporan_onhold(nama_part, date, jumlah) VALUES ('"+namaP+"','"+dt+"','"+a+"');");

                }
                else if (!rs.next()){
                    //stmt.executeUpdate("INSERT INTO laporan_onhold(nama_part, date, jumlah) VALUES ('"+namaP+"','"+dt+"','"+a+"');");
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET on_hold='"+a+"' WHERE nama_part='"+namaP+"';");
                    //stmt.executeUpdate("INSERT INTO stok_barang_onhold(nama_part, stok) VALUES ('"+namaP+"','"+a+"');");
                    stmt.executeUpdate("INSERT INTO laporan_onhold(nama_part, date, jumlah) VALUES ('"+namaP+"','"+dt+"','"+a+"');");

                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
    @SuppressWarnings("all")
    @FXML protected void btn_lapup(){
        if(!manualOverride.isSelected()){
            Calendar time = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dt = simpleDateFormat.format(time.getTime());
            String namaP = lbl_namaP.getText();
            String targetP = tf_targetLAP.getText();
            String actual = tf_actualLAP.getText();
            String ng = tf_ngLAP.getText();
            String stat = cb_untuk.getValue();
            //int a = Integer.parseInt(targetP);
            int a = Integer.parseInt(actual);
            int b = Integer.parseInt(ng);
            float bonggol = Float.parseFloat(tf_bonggolLAP.getText());
            int oh = Integer.parseInt(tf_onholdLAP.getText());
            int tot = a+b+oh;
            String tot_prod = String.valueOf(tot);
            String kwhAw = tf_kwhAwLAP.getText();
            String kwhAk = tf_kwhAkLAP.getText();
            float c = Float.parseFloat(kwhAw);
            float d = Float.parseFloat(kwhAk);
            float totKWH = d-c;
            String kwhTot = String.valueOf(totKWH);
            calendar cal = new calendar();
            String shift = cal.getShift();
            String nop = tf_namaOP.getText();
            String beratPart = tfberat_part.getText();
            String beratRunner = tfberat_runner.getText();
            float bp = Float.parseFloat(beratPart);
            float br = Float.parseFloat(beratRunner);
            float tb = bp+br;
            String cti = tf_cti.getText();
            String tprod = tf_timeProd.getText();
            String cav = tf_cav.getText();
            String ket = keterangan_TA.getText();
            String nomes = tf_nomesin.getText();
            int nA = 0;
            int nB = 0;
            float nC = 0;
            float nD = 0;
            //System.out.println(shift);
            if(allow_upLP.isSelected()){
                System.out.println("A");
                try {
                    Class.forName("com.lpc.driver.connector");
                    Connection con = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    ResultSet rs1 = null;
                    ResultSet rs2 = null;
                    con = connector.setConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT nama_part FROM stok_barang_fresh WHERE nama_part='"+namaP+"';");
                    if(rs.next()){
                        if(stat.equals("FG")){
                            rs2 = stmt.executeQuery("SELECT ok,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' && stat='"+stat+"';");
                            if(rs2.next()){
                                rs1 = stmt.executeQuery("SELECT ok,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' && stat='"+stat+"';");
                                while(rs1.next()){
                                    nA = rs1.getInt(1);
                                    nB = rs1.getInt(2);
                                    nC = rs1.getFloat(3);
                                    nD = rs1.getFloat(4);
                                }
                                int nOK = Integer.parseInt(actual);
                                int nNG = Integer.parseInt(ng);
                                float nRu = Float.parseFloat(beratRunner);
                                int totOK = nA + nOK;
                                int totNG = nB + nNG;
                                float totRU = nC + nRu;
                                float totBong = nD + bonggol;
                                stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                        "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                                stmt.executeUpdate("UPDATE stok_barang_fresh SET ok ='"+totOK+"',ng='"+totNG+"',runner='"+totRU+"',bonggol='"+totBong+"',stat='"+stat+"' WHERE nama_part='"+namaP+"';");
                                ohold();
                                con.close();
                                disabled();
                                allow_upLP.setSelected(false);
                                alert al = new alert();
                                al.info_upload();
                            }
                            else if(!rs2.next()){
                                alert al = new alert();
                                al.warn_dataisondatabase();
                            }
                        }
                        else if(stat.equals("WIP")){
                            rs2 = stmt.executeQuery("SELECT ok,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' && stat='"+stat+"';");
                            if(rs2.next()){
                                rs1 = stmt.executeQuery("SELECT wip,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' WHERE stat='"+stat+"';");
                                while(rs1.next()){
                                    nA = rs1.getInt(1);
                                    nB = rs1.getInt(2);
                                    nC = rs1.getFloat(3);
                                    nD = rs1.getFloat(4);
                                }
                                int nOK = Integer.parseInt(actual);
                                int nNG = Integer.parseInt(ng);
                                float nRu = Float.parseFloat(beratRunner);
                                int totOK = nA + nOK;
                                int totNG = nB + nNG;
                                float totRU = nC + nRu;
                                float totBong = nD + bonggol;
                                stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                        "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                                stmt.executeUpdate("UPDATE stok_barang_fresh SET wip ='"+totOK+"',ng='"+totNG+"',runner='"+totRU+"',bonggol='"+totBong+"',stat='"+stat+"' WHERE nama_part='"+namaP+"';");
                                ohold();
                                con.close();
                                disabled();
                                allow_upLP.setSelected(false);
                                alert al = new alert();
                                al.info_upload();
                            }
                            else if (!rs2.next()){
                                alert al = new alert();
                                al.warn_dataisondatabase();
                            }
                        }

                    }
                    else if(!rs.next()){
                        if(stat.equals("FG")){
                            stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                    "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                            stmt.executeUpdate("INSERT INTO stok_barang_fresh(nama_part, ok,ng,runner,bonggol,stat) VALUES ('"+namaP+"','"+actual+"','"+ng+"','"+beratRunner+"','"+bonggol+"','"+stat+"');");
                            ohold();
                            con.close();
                            disabled();
                            allow_upLP.setSelected(false);
                            alert al = new alert();
                            al.info_upload();
                        }
                        else if(stat.equals("WIP")){
                            stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                    "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                            stmt.executeUpdate("INSERT INTO stok_barang_fresh(nama_part, wip,ng,runner,bonggol,stat) VALUES ('"+namaP+"','"+actual+"','"+ng+"','"+beratRunner+"','"+bonggol+"','"+stat+"');");
                            ohold();
                            con.close();
                            disabled();
                            allow_upLP.setSelected(false);
                            alert al = new alert();
                            al.info_upload();
                        }

                    }
                }

                catch (Exception e) {
                    alert al = new alert();
                    al.error_sql();
                }
            }
            else{
                alert al = new alert();
                al.info_Cb_disabled();
            }
        }else if(manualOverride.isSelected()){
            System.out.println("B");
            Calendar time = Calendar.getInstance();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String dt = datepickLAP.getValue().format(formatter);
            String namaP = lbl_namaP.getText();
            String targetP = tf_targetLAP.getText();
            String actual = tf_actualLAP.getText();
            String ng = tf_ngLAP.getText();
            String stat = cb_untuk.getValue();
            float bonggol = Float.parseFloat(tf_bonggolLAP.getText());
            //int a = Integer.parseInt(targetP);
            int a = Integer.parseInt(actual);
            int b = Integer.parseInt(ng);
            int oh = Integer.parseInt(tf_onholdLAP.getText());
            int tot = a+b+oh;
            String tot_prod = String.valueOf(tot);
            String kwhAw = tf_kwhAwLAP.getText();
            String kwhAk = tf_kwhAkLAP.getText();
            float c = Float.parseFloat(kwhAw);
            float d = Float.parseFloat(kwhAk);
            float totKWH = d-c;
            String kwhTot = String.valueOf(totKWH);
            calendar cal = new calendar();
            String shift = tf_shiftLAP.getText();
            String nop = tf_namaOP.getText();
            String beratPart = tfberat_part.getText();
            String beratRunner = tfberat_runner.getText();
            float bp = Float.parseFloat(beratPart);
            float br = Float.parseFloat(beratRunner);
            float tb = bp+br;
            String cti = tf_cti.getText();
            String tprod = tf_timeProd.getText();
            String cav = tf_cav.getText();
            String ket = keterangan_TA.getText();
            String nomes = tf_nomesin.getText();
            int nA = 0;
            int nB = 0;
            float nC = 0;
            float nD = 0;
            //System.out.println(shift);
            if(allow_upLP.isSelected()){
                try {
                    Class.forName("com.lpc.driver.connector");
                    Connection con = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    ResultSet rs1 = null;
                    ResultSet rs2 = null;
                    con = connector.setConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT nama_part FROM stok_barang_fresh WHERE nama_part='"+namaP+"';");
                    if(rs.next()){
                        if(stat.equals("FG")){
                            rs2 = stmt.executeQuery("SELECT ok,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' && stat='"+stat+"';");
                            if(rs2.next()){
                                rs1 = stmt.executeQuery("SELECT ok,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' && stat='"+stat+"';");
                                while(rs1.next()){
                                    nA = rs1.getInt(1);
                                    nB = rs1.getInt(2);
                                    nC = rs1.getFloat(3);
                                    nD = rs1.getFloat(4);
                                }
                                int nOK = Integer.parseInt(actual);
                                int nNG = Integer.parseInt(ng);
                                float nRu = Float.parseFloat(beratRunner);
                                int totOK = nA + nOK;
                                int totNG = nB + nNG;
                                float totRU = nC + nRu;
                                float totBong = nD + bonggol;
                                stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                        "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                                stmt.executeUpdate("UPDATE stok_barang_fresh SET ok ='"+totOK+"',ng='"+totNG+"',runner='"+totRU+"',bonggol='"+totBong+"',stat='"+stat+"' WHERE nama_part='"+namaP+"';");
                                ohold();
                                con.close();
                                disabled();
                                allow_upLP.setSelected(false);
                                alert al = new alert();
                                al.info_upload();
                            }
                            else if(!rs2.next()){
                                alert al = new alert();
                                al.warn_dataisondatabase();
                            }
                        }
                        else if(stat.equals("WIP")){
                            rs2 = stmt.executeQuery("SELECT ok,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' && stat='"+stat+"';");
                            if(rs2.next()){
                                rs1 = stmt.executeQuery("SELECT wip,ng,runner,bonggol FROM stok_barang_fresh WHERE nama_part='"+namaP+"' WHERE stat='"+stat+"';");
                                while(rs1.next()){
                                    nA = rs1.getInt(1);
                                    nB = rs1.getInt(2);
                                    nC = rs1.getFloat(3);
                                    nD = rs1.getFloat(4);
                                }
                                int nOK = Integer.parseInt(actual);
                                int nNG = Integer.parseInt(ng);
                                float nRu = Float.parseFloat(beratRunner);
                                int totOK = nA + nOK;
                                int totNG = nB + nNG;
                                float totRU = nC + nRu;
                                float totBong = nD + bonggol;
                                stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                        "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                                stmt.executeUpdate("UPDATE stok_barang_fresh SET wip ='"+totOK+"',ng='"+totNG+"',runner='"+totRU+"',bonggol='"+totBong+"',stat='"+stat+"' WHERE nama_part='"+namaP+"';");
                                ohold();
                                con.close();
                                disabled();
                                allow_upLP.setSelected(false);
                                alert al = new alert();
                                al.info_upload();
                            }
                            else if (!rs2.next()){
                                alert al = new alert();
                                al.warn_dataisondatabase();
                            }
                        }

                    }
                    else if(!rs.next()){
                        if(stat.equals("FG")){
                            stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                    "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                            stmt.executeUpdate("INSERT INTO stok_barang_fresh(nama_part, ok,ng,runner,bonggol,stat) VALUES ('"+namaP+"','"+actual+"','"+ng+"','"+beratRunner+"','"+bonggol+"','"+stat+"');");
                            ohold();
                            con.close();
                            disabled();
                            allow_upLP.setSelected(false);
                            alert al = new alert();
                            al.info_upload();
                        }
                        else if(stat.equals("WIP")){
                            stmt.executeUpdate("INSERT INTO laporan_produksi(nama_part, date, target, actual_produksi, ng_produksi, total_ouput, kwh_awal, kwh_akhir, total_kwh,shift,berat_part_act,berat_runner_act,berat_total_act,cti_act,operator,time,cav,keterangan,no_mesin,onhold,bonggol,stat)" +
                                    "VALUES ('"+namaP+"','"+dt+"','"+targetP+"','"+actual+"','"+ng+"','"+tot_prod+"','"+kwhAw+"','"+kwhAk+"','"+kwhTot+"','"+shift+"','"+beratPart+"','"+beratRunner+"','"+tb+"','"+cti+"','"+nop+"','"+tprod+"','"+cav+"','"+ket+"','"+nomes+"','"+oh+"','"+bonggol+"','"+stat+"');");
                            stmt.executeUpdate("INSERT INTO stok_barang_fresh(nama_part, wip,ng,runner,bonggol,stat) VALUES ('"+namaP+"','"+actual+"','"+ng+"','"+beratRunner+"','"+bonggol+"','"+stat+"');");
                            ohold();
                            con.close();
                            disabled();
                            allow_upLP.setSelected(false);
                            alert al = new alert();
                            al.info_upload();
                        }

                    }
                }

                catch (Exception e) {
                    alert al = new alert();
                    al.error_sql();
                }
            }
            else{
                alert al = new alert();
                al.info_Cb_disabled();
            }
        }

    }
    protected String getStat_combo(){
        String aa = null;
        if(cb_shiftCP.isSelected()){
            aa="0";
        }
        else if (!cb_shiftCP.isSelected()){
            aa="1";
        }
        return aa;
    }
    protected String getStat_tanggal(){
        String ba = null;
        if(rdbtn_hariini.isSelected()){
            ba = "0";
        }
        else if (rdbtn_tanggal.isSelected()){
            ba = "1";
        }
        else if (rdbtn_rentang_tanggal.isSelected()){
            ba = "2";
        }
        return ba;
    }
    @SuppressWarnings("all")
    @FXML protected void searchCP(){
        int a = Integer.parseInt(getStat_combo());
        int b = Integer.parseInt(getStat_tanggal());
        calendar cal = new calendar();
        String sh = cal.getShift();
    if( a == 0 && b == 0){
        Calendar time = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = simpleDateFormat.format(time.getTime());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String nama_p = lbl_namaPCP.getText();
        //String date = (dateCP_awal.getValue()).format(formatter);

        try{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part , SUM(target), SUM(actual_produksi), SUM(ng_produksi), SUM(total_kwh) FROM laporan_produksi WHERE nama_part ='"+nama_p+"' && date ='"+date+"';");
            while (rs.next()){
                float aa = rs.getFloat(2);
                float bb = rs.getFloat(3);
                float capai = ((bb*100)/aa);
                target_ctp.setText(String.valueOf(capai)+"%");
                lbl_lap_namapart.setText("Laporan Produksi "+rs.getString(1)+"");
                lbl_totalTarget.setText(rs.getString(2));
                lbl_totalActual.setText(rs.getString(3));
                lbl_totalNG.setText(rs.getString(4));
                lbl_totalKWH.setText(rs.getString(5)+" KWH");

            }

        }
        catch (Exception e){
            alert al = new alert();
            al.err_requirednotfound();

        }
    }
    else if (a == 0 && b == 1){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String nama_p = lbl_namaPCP.getText();
        String date1 = (dateCP_awal.getValue()).format(formatter);
        try{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part , SUM(target), SUM(actual_produksi), SUM(ng_produksi), SUM(total_kwh) FROM laporan_produksi WHERE nama_part ='"+nama_p+"' && date ='"+date1+"';");
            while (rs.next()){
                float aa = rs.getFloat(2);
                float bb = rs.getFloat(3);
                float capai = ((bb*100)/aa);
                target_ctp.setText(String.valueOf(capai)+"%");
                lbl_lap_namapart.setText("Laporan Produksi "+rs.getString(1)+"");
                lbl_totalTarget.setText(rs.getString(2));
                lbl_totalActual.setText(rs.getString(3));
                lbl_totalNG.setText(rs.getString(4));
                lbl_totalKWH.setText(rs.getString(5)+" KWH");

            }

        }
        catch (Exception e){
            alert al = new alert();
            al.err_requirednotfound();

        }

    }
    else if(a == 0 && b ==2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String nama_p = lbl_namaPCP.getText();
        String date1 = (dateCP_awal.getValue()).format(formatter);
        String date2 = (dateCP_akhir.getValue()).format(formatter);
        try{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part , SUM(target), SUM(actual_produksi), SUM(ng_produksi), SUM(total_kwh) FROM laporan_produksi WHERE nama_part ='"+nama_p+"' && date BETWEEN  '"+date1+"' AND '"+date2+"';");
            while (rs.next()){
                float aa = rs.getFloat(2);
                float bb = rs.getFloat(3);
                float capai = ((bb*100)/aa);
                target_ctp.setText(String.valueOf(capai)+"%");
                lbl_lap_namapart.setText("Laporan Produksi "+rs.getString(1)+"");
                lbl_totalTarget.setText(rs.getString(2));
                lbl_totalActual.setText(rs.getString(3));
                lbl_totalNG.setText(rs.getString(4));
                lbl_totalKWH.setText(rs.getString(5)+" KWH");
            }

        }
        catch (Exception e){
            alert al = new alert();
            al.err_requirednotfound();

        }

    }
    else if (a == 1 && b == 0){

        Calendar time = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = simpleDateFormat.format(time.getTime());
        int shift = Integer.parseInt(sh);

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String nama_p = lbl_namaPCP.getText();
        //String date = (dateCP_awal.getValue()).format(formatter);

        try{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part , SUM(target), SUM(actual_produksi), SUM(ng_produksi), SUM(total_kwh) FROM laporan_produksi WHERE nama_part ='"+nama_p+"' && date ='"+date+"' && shift='"+shift+"';");
            while (rs.next()){
                float aa = rs.getFloat(2);
                float bb = rs.getFloat(3);
                float capai = ((bb*100)/aa);
                target_ctp.setText(String.valueOf(capai)+"%");
                lbl_lap_namapart.setText("Laporan Produksi "+rs.getString(1)+"");
                lbl_totalTarget.setText(rs.getString(2));
                lbl_totalActual.setText(rs.getString(3));
                lbl_totalNG.setText(rs.getString(4));
                lbl_totalKWH.setText(rs.getString(5)+" KWH");

            }

        }
        catch (Exception e){
            alert al = new alert();
            al.err_requirednotfound();

        }

    }
    else if(a == 1 && b ==1){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String nama_p = lbl_namaPCP.getText();
        String date1 = (dateCP_awal.getValue()).format(formatter);
        int shift = Integer.parseInt(sh);
        try{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part , SUM(target), SUM(actual_produksi), SUM(ng_produksi), SUM(total_kwh) FROM laporan_produksi WHERE nama_part ='"+nama_p+"' && date ='"+date1+"'&& shift ='"+shift+"';");
            while (rs.next()){
                float aa = rs.getFloat(2);
                float bb = rs.getFloat(3);
                float capai = ((bb*100)/aa);
                target_ctp.setText(String.valueOf(capai)+"%");
                lbl_lap_namapart.setText("Laporan Produksi "+rs.getString(1)+"");
                lbl_totalTarget.setText(rs.getString(2));
                lbl_totalActual.setText(rs.getString(3));
                lbl_totalNG.setText(rs.getString(4));
                lbl_totalKWH.setText(rs.getString(5)+" KWH");
            }

        }
        catch (Exception e){
            alert al = new alert();
            al.err_requirednotfound();

        }

    }else if (a == 1 && b == 2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String nama_p = lbl_namaPCP.getText();
        String date1 = (dateCP_awal.getValue()).format(formatter);
        String date2 = (dateCP_akhir.getValue()).format(formatter);
        int shift = Integer.parseInt(sh);
        try{
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part , SUM(target), SUM(actual_produksi), SUM(ng_produksi), SUM(total_kwh) FROM laporan_produksi WHERE nama_part ='"+nama_p+"'&& shift ='"+shift+"' && date BETWEEN  '"+date1+"' AND '"+date2+"';");
            while (rs.next()){
                float aa = rs.getFloat(2);
                float bb = rs.getFloat(3);
                float capai = ((bb*100)/aa);
                target_ctp.setText(String.valueOf(capai)+"%");
                lbl_lap_namapart.setText("Laporan Produksi "+rs.getString(1)+"");
                lbl_totalTarget.setText(rs.getString(2));
                lbl_totalActual.setText(rs.getString(3));
                lbl_totalNG.setText(rs.getString(4));
                lbl_totalKWH.setText(rs.getString(5)+" KWH");

            }

        }
        catch (Exception e){
            alert al = new alert();
            al.err_requirednotfound();

        }

    }
        enabled1();
    }
    @FXML protected void getData(){
        list_assy.clear();
        listView.getItems().clear();
        String kode_part = tf_kodeP.getText();
        getTotalAssy();
        try{
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part ,b.nama_material,b.berat_part,b.berat_runner,b.berat_total,b.harga_inject,b.cti," +
                    "c.name_customer,d.nama_material, d.harga_assy, b.cav ,d.jumlah FROM list_part a,list_material_inj b,list_customer c,list_material_assy d WHERE " +
                    "a.code_part ='"+kode_part+"'&& a.custID = c.id_customer && a.nama_part = b.nama_part && a.nama_part = d.nama_part;");
            while(rs.next()){
                lbl_np.setText(rs.getString(1));
                lbl_nmatinj.setText(rs.getString(2));
                lbl_beratP.setText(rs.getString(3));
                lbl_beratR.setText(rs.getString(4));
                lbl_beratT.setText(rs.getString(5));
                lbl_hinj.setText(rs.getString(6));
                lbl_cti.setText(rs.getString(7));
                lbl_cust.setText(rs.getString(8));
                //lbl_nmatassy.setText(rs.getString(9));

                //int sum = 0;
                //sum+= Integer.parseInt(rs.getString(10));
                //lbl_hassy.setText(String.valueOf(sum));
                //lbl_hassy.setText(rs.getString(10));
                list_assy.add(rs.getString(9)+" @ "+rs.getString(12));
                lbl_cav.setText(rs.getString(11));
                ObservableList<String> m_assy = FXCollections.observableArrayList(list_assy);
                listView.setItems(m_assy);
            }
            int b = Integer.parseInt(lbl_hinj.getText());
            int c = Integer.parseInt(lbl_hassy.getText());
            int a = b+c;
            lbl_htot.setText(String.valueOf(a));
            con.close();
        }
        catch (Exception e){
            alert al = new alert();
            al.warn_datanotfound();
            System.out.println(e);
        }

    }

    @FXML protected void uploadData(){

    }
    @FXML protected void exportLAP(){

        try {
            Calendar time = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(time.getTime());
            SimpleDateFormat simpleDateFormat1= new SimpleDateFormat("HH:mm:ss");
            String timex = simpleDateFormat1.format(time.getTime());
            String kpLAPX = lap_kodepart_xport.getText();
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            FileInputStream input_document = new FileInputStream(new File("template_laporan_produksi.DATA"));
            XSSFWorkbook workbook = new XSSFWorkbook(input_document);
            XSSFSheet my_worksheet = workbook.getSheetAt(0);
            rs = stmt.executeQuery("SELECT a.nama_part,a.date,a.time,a.shift,a.target,a.actual_produksi,a.ng_produksi,a.operator,a.keterangan,a.onhold FROM laporan_produksi a,list_part b WHERE a.date='"+date+"'&& b.code_part='"+kpLAPX+"'&& a.nama_part = b.nama_part;");
            int index = 5;
            XSSFRow header = my_worksheet.createRow(1);
            XSSFRow header1 = my_worksheet.createRow(2);
            header.createCell(0).setCellValue("Tanggal Laporan");
            header1.createCell(0).setCellValue("Jam Laporan");
            header.createCell(2).setCellValue(date);
            header1.createCell(2).setCellValue(timex);
            System.out.println(date+" "+timex);
            //cell.setCellValue("1");
            //cell1.setCellValue("2");
            my_worksheet.setColumnWidth(0,20*25);
            my_worksheet.setColumnWidth(1,256*25);
            my_worksheet.autoSizeColumn(2);
            my_worksheet.autoSizeColumn(3);
            my_worksheet.autoSizeColumn(4);
            my_worksheet.autoSizeColumn(5);
            my_worksheet.autoSizeColumn(6);
            my_worksheet.autoSizeColumn(7);
            my_worksheet.autoSizeColumn(8);
            my_worksheet.autoSizeColumn(9);
            my_worksheet.autoSizeColumn(10);
            my_worksheet.autoSizeColumn(11);

            float persen = 0;
            int i = 1;
            while(rs.next()){
                XSSFRow row = my_worksheet.createRow(index);
                XSSFCellStyle cellStyle = workbook.createCellStyle();
                //cellStyle.setBorderLeft();

                row.createCell(0).setCellValue(i);
                //row.createCell(0).setCellStyle(style);
                row.createCell(1).setCellValue(rs.getString(1));
                row.createCell(2).setCellValue(rs.getString(2));
                row.createCell(3).setCellValue(rs.getString(3));
                row.createCell(4).setCellValue(rs.getString(4));
                row.createCell(5).setCellValue(rs.getString(5));
                row.createCell(6).setCellValue(rs.getString(6));
                row.createCell(7).setCellValue(rs.getString(7));
                row.createCell(8).setCellValue(rs.getString(8));
                row.createCell(9).setCellValue(rs.getString(9));
                persen = ((rs.getFloat(6)*100)/rs.getFloat(5));
                row.createCell(10).setCellValue(String.valueOf(persen)+"%");
                row.createCell(11).setCellValue(rs.getString(10));
                if(index <= 26 && i <=25 ){
                    index++;
                    i++;
                }
                else{
                    con.close();
                }
            }
            input_document.close();
            String filenama = "laporan@"+date+"For@"+kpLAPX+".xlsx";
            FileOutputStream output_file =new FileOutputStream(new File(filenama));
            workbook.write(output_file);
            output_file.close();

            alert al = new alert();
            al.info_export();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML protected void printLAP(){

    }
    public ObservableList<cekprod> getcprod(){
        return cprod;
    }
    public ObservableList<model_subassy> getModl_assy(){
        return modl_assy;
    }
    //Sub Assy Region
    @FXML protected void sa_upWIP(){
        try {
            Calendar time = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String date =format.format(time.getTime());
            String kodeP = sa_kodepart.getText();
            String jumlah = sa_jumlahwip.getText();
            String namaPa = null;
            int wipUSER = Integer.parseInt(jumlah);
            int wipDB = 0;
            int wipStok = 0;
            int wip_total_stok =0;
            int wip_total_db = 0;

            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            ResultSet rs4 = null;
            ResultSet rs5 = null;
            con = connector.setConnection();
            stmt = con.createStatement();


            rs5 = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE code_part='"+kodeP+"' && a.nama_part=b.nama_part && stat='WIP';");
            if(rs5.next()){
                rs = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE code_part='"+kodeP+"' && a.nama_part=b.nama_part && stat='WIP';");
                if(rs.next()){
                    rs2 = stmt.executeQuery("SELECT nama_part FROM list_part WHERE code_part='"+kodeP+"';");
                    while (rs2.next()){
                        namaPa = rs2.getString(1);
                    }
                    rs1 = stmt.executeQuery("SELECT wip FROM stok_barang_fresh WHERE nama_part='"+namaPa+"';");
                    while(rs1.next()){
                        wipDB = rs1.getInt(1);
                    }
                    wip_total_db = wipDB - wipUSER;
                    stmt.executeUpdate("INSERT INTO laporan_wip(nama_part, tanggal, wip) VALUES ('"+namaPa+"','"+date+"','"+wipUSER+"');");
                    rs3 = stmt.executeQuery("SELECT * FROM stok_barang_wip WHERE nama_part='"+namaPa+"';");
                    if(rs3.next()){
                        rs4 = stmt.executeQuery("SELECT wip FROM stok_barang_wip WHERE nama_part='"+namaPa+"';");
                        while (rs4.next()){
                            wipStok = rs4.getInt(1);
                        }
                        wip_total_stok = wipStok + wipUSER;
                        stmt.executeUpdate("UPDATE stok_barang_wip SET wip='"+wip_total_stok+"' WHERE nama_part='"+namaPa+"';");
                        stmt.executeUpdate("UPDATE stok_barang_fresh SET wip='"+wip_total_db+"' WHERE nama_part='"+namaPa+"';");
                    }
                    else if (!rs3.next()){
                        stmt.executeUpdate("INSERT INTO stok_barang_wip(nama_part, wip) VALUES ('"+namaPa+"','"+wipUSER+"');");
                        stmt.executeUpdate("UPDATE stok_barang_fresh SET wip='"+wip_total_db+"' WHERE nama_part='"+namaPa+"';");
                    }

                    con.close();
                    alert al = new alert();
                    al.info_upload();
                }
                else{
                    con.close();
                    alert al = new alert();
                    al.error_upload();
                }
            }
            else if(!rs5.next()){
                alert al = new alert();
                al.warn_wipassy();
            }

        } catch (Exception e) {
            System.out.print(e);
            alert al = new alert();
            al.error_sql();
        }
    }
    @FXML protected void sa_wipsearch(){
        try {
            modl_assy.clear();
            sa_wipTV.getItems().clear();
            Class.forName("com.lpc.driver.connector");
            String kode = sa_ckkodepart.getText();
            String nama_part = null;
            //String nA = null;
            //int nB = 0;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            con = connector.setConnection();
            stmt =con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,b.nama_part FROM list_part a,stok_barang_wip b WHERE a.code_part='"+kode+"' && a.nama_part = b.nama_part;");
            if(rs.next()){
                rs1 = stmt.executeQuery("SELECT nama_part FROM list_part WHERE code_part='"+kode+"';");
                while (rs1.next()){
                    nama_part = rs1.getString(1);
                }
                    rs2 = stmt.executeQuery("SELECT nama_part,wip FROM stok_barang_wip WHERE nama_part='"+nama_part+"';");
                    while (rs2.next()){
                    modl_assy.addAll(new model_subassy(rs2.getString(1),rs2.getString(2)));
                    sa_namapCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                    sa_wipCOL.setCellValueFactory(cellData -> cellData.getValue().wipProperty());
                }
                sa_wipTV.setItems(getModl_assy());
                con.close();
            }
            else{
                alert al = new alert();
                al.warn_datanotfound();
                con.close();
            }


        } catch (Exception e) {
            alert al = new alert();
            al.warn_datanotfound();
        }
    }
    @FXML protected void sa_lap_up(){
        try {
            String kodeP = sa_lapkodepart.getText();
            String j_sa = sa_lapjumlah.getText();
            String j_sa1 = sa_lapjumlah1.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String date = sa_datelap.getValue().format(formatter);
            String namaP = null;
            int nA = Integer.parseInt(j_sa);
            int nB = 0;
            int nC = 0;
            int nAA = Integer.parseInt(j_sa1);
            int nBB = 0;
            int nCC = 0;
            int nTot = 0;
            int zA = 0;
            int zB = 0;
            int zTot = 0;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part FROM list_part WHERE code_part='"+kodeP+"';");
            while(rs.next()){
                namaP = rs.getString(1);
            }
            rs1 = stmt.executeQuery("SELECT nama_part FROM stok_barang_subassy WHERE nama_part='"+namaP+"';");
            if(rs1.next()){
                rs2 = stmt.executeQuery("SELECT stok,ng FROM stok_barang_subassy WHERE  nama_part='"+namaP+"';");
                while (rs2.next()){
                    nB = rs2.getInt(1);
                    nBB = rs2.getInt(2);
                }
                rs3 = stmt.executeQuery("SELECT wip FROM stok_barang_wip WHERE nama_part='"+namaP+"';");
                while (rs3.next()){
                    zA = rs3.getInt(1);
                }
                zB =nA+nAA;
                //zTot = zA - nA;
                nTot = nA + nB;
                nC = nA + nAA;
                nCC = nAA + nBB;
                zTot = zA - zB;
                stmt.executeUpdate("UPDATE stok_barang_wip set wip='"+zTot+"' WHERE nama_part='"+namaP+"';");
                stmt.executeUpdate("UPDATE stok_barang_subassy SET stok='"+nTot+"',ng='"+nCC+"' WHERE nama_part='"+namaP+"';");
                stmt.executeUpdate("INSERT INTO laporan_assy(nama_part, jumlah, tanggal,ng) VALUES ('"+namaP+"','"+nA+"','"+date+"','"+nAA+"');");
                //stmt.executeUpdate("INSERT INTO stok_barang_wip(nama_part, wip) VALUES('"+namaP+"','"+nA+"');");
                con.close();
                alert al = new alert();
                al.info_upload();
            }
            else if (!rs1.next()){
                rs3 = stmt.executeQuery("SELECT wip FROM stok_barang_wip WHERE nama_part='"+namaP+"';");
                while (rs3.next()){
                    zA = rs3.getInt(1);
                }
                zB = nA+nAA;
                zTot = zA - zB;
                stmt.executeUpdate("UPDATE stok_barang_wip set wip='"+zTot+"' WHERE nama_part='"+namaP+"';");
                stmt.executeUpdate("INSERT INTO stok_barang_subassy(nama_part,stok,ng) VALUES ('"+namaP+"','"+nA+"','"+nAA+"');");
                stmt.executeUpdate("INSERT INTO laporan_assy(nama_part, jumlah, tanggal,ng) VALUES ('"+namaP+"','"+nA+"','"+date+"','"+nAA+"');");
                //stmt.executeUpdate("INSERT INTO stok_barang_wip(nama_part, wip) VALUES('"+namaP+"','"+nA+"');");
                con.close();
                alert al = new alert();
                al.info_upload();
            }

        }catch (Exception e){
            alert al = new alert();
            al.error_sql();
        }
    }

/*
    //Stok NON Region
    public ObservableList<stok_nonmodel1> getStok_non1(){
        return stok_non1;
    }
    public ObservableList<stok_nonmodel> getStok_non(){
        return stok_non;
    }
    @FXML protected void stok_nonATL(){
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
            rs = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE code_part='"+np+"' && a.nama_part = b.nama_part;");
            if(rs.next()){
                do{
                    np1 = rs.getString(1);
                }
                while (rs.next());

                if(stok_nonTV.getItems().size() <15){
                    stok_non.addAll(new stok_nonmodel(np1,picp,picw,jml,ket));
                    stok_nonNPCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                    stok_nonPICPCOL.setCellValueFactory(cellData -> cellData.getValue().picpProperty());
                    stok_nonPICWCOL.setCellValueFactory(cellData -> cellData.getValue().picwProperty());
                    stok_nonJMLCOL.setCellValueFactory(cellData -> cellData.getValue().jmlProperty());
                    stok_nonTV.setItems(getStok_non());
                }
                else{
                    alert al = new alert();
                    al.max15();
                }
            }else{
                alert al = new alert();
                al.warn_datanotfound();
            }

        } catch (Exception e) {

        }
    }
    @FXML protected void stok_nonEXP(){
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            String tgl = stok_nontgl.getText();

            for(int i = 0;i<stok_nonTV.getItems().size();i++){
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
                rs = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='"+nama_p+"' && stat = '"+stat+"';");
                if(rs.next()){
                    rs2 = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='"+nama_p+"' && stat='"+stat+"';");
                    while (rs2.next()){
                        jm = rs2.getInt(1);
                    }
                    rs1 = stmt.executeQuery("SELECT ok FROM stok_barang_fresh WHERE nama_part='"+nama_p+"';");
                    while (rs1.next()){
                        jm1 = rs1.getInt(1);
                    }
                    tot = jm + jml1;
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("UPDATE stok_wfg_total SET jumlah='"+tot+"' WHERE nama_part='"+nama_p+"' && stat='"+stat+"';");
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket) VALUES ('"+nama_p+"','"+jml+"','"+tgl+"','"+picp+"','"+picw+"','"+ket+"');");
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET ok='"+tot1+"' WHERE nama_part='"+nama_p+"';");
                }
                else if (!rs.next()){
                    rs1 = stmt.executeQuery("SELECT ok FROM stok_barang_fresh WHERE nama_part='"+nama_p+"';");
                    while (rs1.next()){
                        jm1 = rs1.getInt(1);
                    }
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket,stat) VALUES ('"+nama_p+"','"+jml+"','"+tgl+"','"+picp+"','"+picw+"','"+ket+"','"+stat+"');");
                    stmt.executeUpdate("INSERT INTO  stok_wfg_total(nama_part, jumlah,stat) VALUES ('"+nama_p+"','"+jml+"','"+stat+"');");
                    stmt.executeUpdate("UPDATE stok_barang_fresh SET ok='"+tot1+"' WHERE nama_part='"+nama_p+"';");
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
    @FXML protected void stok_nonrefresh(){
        try {
            stok_nonTV1.getItems().clear();
            stok_non1.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,a.ok,b.code_part FROM stok_barang_fresh a,list_part b WHERE a.nama_part = b.nama_part;");
            while (rs.next()){
                stok_non1.addAll(new stok_nonmodel1(rs.getString(1),rs.getString(2),rs.getString(3)));
                stok_nonNPCOL1.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                stok_nonOKCOL1.setCellValueFactory(cellData -> cellData.getValue().okProperty());
                stok_nonNGCOL1.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
            }
            stok_nonTV1.setItems(getStok_non1());

        } catch (Exception e) {
        }
    }

    //SSA Sub ASSY Region
    public ObservableList<stok_ssamodel> getSsa_mod(){
        return ssa_mod;
    }
    public ObservableList<stok_ssamodel1> getSsa_mod1(){
        return ssa_mod1;
    }
    @FXML protected void ssa_ATL(){
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
            rs = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE code_part='"+np+"' && a.nama_part = b.nama_part;");
            if(rs.next()){
                do{
                    np1 = rs.getString(1);
                }
                while (rs.next());

                if(ssa_TV.getItems().size() <15){
                    ssa_mod.addAll(new stok_ssamodel(np1,picp,picw,jml,ket));
                    ssa_NPCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                    ssa_PIPCOL.setCellValueFactory(cellData -> cellData.getValue().picpProperty());
                    ssa_PIWCOL.setCellValueFactory(cellData -> cellData.getValue().picwProperty());
                    ssa_JMLCOL.setCellValueFactory(cellData -> cellData.getValue().jmlProperty());
                    ssa_TV.setItems(getSsa_mod());
                }
                else{
                    alert al = new alert();
                    al.max15();
                }
            }else{
                alert al = new alert();
                al.warn_datanotfound();
            }

        } catch (Exception e) {

        }
    }
    @FXML protected void ssa_EXP(){
        try {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            String tgl = ssa_tgl.getText();

            for(int i = 0;i<ssa_TV.getItems().size();i++){
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
                rs = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='"+nama_p+"' && stat='"+stat+"';");
                if(rs.next()){
                    rs2 = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part='"+nama_p+"' && stat ='"+stat+"';");
                    while (rs2.next()){
                        jm = rs2.getInt(1);
                    }
                    rs1 = stmt.executeQuery("SELECT stok FROM stok_barang_subassy WHERE nama_part='"+nama_p+"';");
                    while (rs1.next()){
                        jm1 = rs1.getInt(1);
                    }
                    tot = jm + jml1;
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("UPDATE stok_wfg_total SET jumlah='"+tot+"' WHERE nama_part='"+nama_p+"' && stat='"+stat+"';");
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket) VALUES ('"+nama_p+"','"+jml+"','"+tgl+"','"+picp+"','"+picw+"','"+ket+"');");
                    stmt.executeUpdate("UPDATE stok_barang_subassy SET stok='"+tot1+"' WHERE nama_part='"+nama_p+"';");
                }
                else if (!rs.next()){
                    rs1 = stmt.executeQuery("SELECT stok FROM stok_barang_subassy WHERE nama_part='"+nama_p+"';");
                    while (rs1.next()){
                        jm1 = rs1.getInt(1);
                    }
                    tot1 = jm1 - jml1;
                    stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima,picp,picw,ket,stat) VALUES ('"+nama_p+"','"+jml+"','"+tgl+"','"+picp+"','"+picw+"','"+ket+"','"+stat+"');");
                    stmt.executeUpdate("INSERT INTO  stok_wfg_total(nama_part, jumlah,stat) VALUES ('"+nama_p+"','"+jml+"','"+stat+"');");
                    stmt.executeUpdate("UPDATE stok_barang_subassy SET stok='"+tot1+"' WHERE nama_part='"+nama_p+"';");
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
    @FXML protected void stok_ssarefresh(){
        try {
            ssa_TV1.getItems().clear();
            ssa_mod1.clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,a.stok,b.code_part FROM stok_barang_subassy a,list_part b WHERE a.nama_part = b.nama_part;");
            while (rs.next()){
                ssa_mod1.addAll(new stok_ssamodel1(rs.getString(1),rs.getString(2),rs.getString(3)));
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
*/
    //ONHOLD Region
    @FXML private void cek_onhold_search(){
        try {
            xAxis.setLabel("Tanggal");
            yAxis.setLabel("PCS Yang Dilaporkan");
            XYChart.Series data = new XYChart.Series();
            data.setName("TOTAL ONHOLD");
            data.getData().clear();
            cek_onhold_bar.getData().clear();
            String kp  = cek_onhold_kode.getText();
            String np = null;
            String dt = null;
            String jumlah = null;
            int a =0;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part,b.on_hold FROM list_part a,stok_barang_fresh b WHERE a.code_part='"+kp+"' && a.nama_part = b.nama_part ;");
            while(rs.next()){
                //int i = 0;
                np = rs.getString(1);
                //dt = rs.getString(3);
                jumlah = rs.getString(2);
                //i++;
                data.getData().add(new XYChart.Data("Total",Integer.parseInt(jumlah)));
            }
            cek_onhold_bar.getData().addAll(data);
            rs1 = stmt.executeQuery("SELECT on_hold FROM stok_barang_fresh WHERE nama_part='"+np+"';");
            while (rs1.next()){
                a = rs1.getInt(1);
            }
            lbl_cek_onhold_total.setText(String.valueOf(a));
            lbl_cek_onhold_np.setText(np);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML private void onhold_ATL(){
        try{
            String kp = onhold_kp.getText();
            String ok = onhold_ok.getText();
            String aa = onhold_ng.getText();
            String stat = onhold_status.getSelectionModel().getSelectedItem().toString();
            String np = null;
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE a.code_part='"+kp+"' && a.nama_part = b.nama_part ;");
            if (rs.next()) {
                rs = stmt.executeQuery("SELECT a.nama_part FROM list_part a,stok_barang_fresh b WHERE a.code_part='"+kp+"' && a.nama_part = b.nama_part ;");
                while(rs.next()){
                    np = rs.getString(1);
                }
                if(onhold_TV.getItems().size() < 15){
                    onholdmod.addAll(new onhold_model(np,ok,aa,stat));
                    onhold_npCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                    onhold_okCOL.setCellValueFactory(cellData -> cellData.getValue().okProperty());
                    onhold_ngCOL.setCellValueFactory(cellData -> cellData.getValue().ngProperty());
                    onhold_statusCOL.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
                    onhold_TV.setItems(getOnholdmod());
                }
                else{
                    alert al = new alert();
                    al.max15();
                }
            } else {
                alert al = new alert();
                al.warn_datanotfound();
            }
        }
        catch (Exception e){

        }

    }
    private ObservableList<onhold_model> getOnholdmod(){
        return onholdmod;
    }
    @FXML private void onhold_upload(){
        try {
            String np = null;
            String ok = null;
            String ng = null;
            String stat = null;

            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            int a = 0;
            int b = 0;
            int c = 0;
            int aa = 0;
            int bb = 0;
            int cc = 0;
            int oh = 0;
            int oh1 = 0;
            int oh2 = 0;
            int aaa = 0;
            int bbb = 0;
            int ccc = 0;
            String dt = null;
            String picw = null;
            String picp = null;
            String ket = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            for(int i =0; i<onhold_TV.getItems().size();i++){
                Calendar time = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                dt = simpleDateFormat.format(time.getTime());
                picp = "Dari QC ";
                picw = "Dari QC";
                ket = "Dari QC ke FG ,tanpa PIC Warehouse dan Produksi";
                np = String.valueOf(onhold_TV.getItems().get(i).getNama_part());
                ok = String.valueOf(onhold_TV.getItems().get(i).getOk());
                ng = String.valueOf(onhold_TV.getItems().get(i).getNg());
                stat = String.valueOf(onhold_TV.getItems().get(i).getStatus());
                a = Integer.parseInt(ok);
                b = 0;
                c = 0;
                aa = Integer.parseInt(ng);
                bb = 0;
                cc = 0;
                oh = 0;
                oh1= 0;
                oh2 = 0;
                aaa =0;
                bbb= 0;
                ccc =0;
                int hj = 0;
                int hj1 = 0;
                int hj2 = 0;
                stmt.executeUpdate("INSERT INTO laporan_judgement(nama_part, ok, ng, stat) VALUES ('"+np+"','"+a+"','"+aa+"','"+stat+"');");
                if(stat.equals("Assy")){
                    rs = stmt.executeQuery("SELECT b.wip,a.ng,a.on_hold FROM stok_barang_fresh a,stok_barang_wip b WHERE nama_part='"+np+"';");
                    while(rs.next()){
                        b = rs.getInt(1);
                        bb = rs.getInt(2);
                        oh = rs.getInt(3);
                    }
                    c = a+b;
                    cc = aa+bb;
                    oh1 = a+aa;
                    oh2 = oh - oh1;

                    rs1 = stmt.executeQuery("SELECT nama_part FROM stok_barang_wip WHERE nama_part='"+np+"';");
                    if(rs1.next()){
                        stmt.executeUpdate("UPDATE stok_barang_fresh SET ng='"+cc+"',on_hold='"+oh2+"' WHERE nama_part='"+np+"' ;");
                        stmt.executeUpdate("UPDATE stok_barang_wip SET wip='"+c+"' WHERE nama_part='"+np+"' ;");
                        //stmt.executeUpdate("INSERT INTO stok_barang_wip(nama_part, wip) VALUES ('"+np+"','"+a+"');");
                        stmt.executeUpdate("INSERT INTO laporan_wip(nama_part, tanggal, wip) VALUES ('"+np+"','"+dt+"','"+c+"');");
                    }
                    else if(!rs1.next()){
                        stmt.executeUpdate("INSERT INTO stok_barang_wip(nama_part,wip)VALUES ('"+np+"','"+a+"');");
                        stmt.executeUpdate("INSERT INTO laporan_wip(nama_part, tanggal, wip) VALUES ('"+np+"','"+dt+"','"+c+"');");
                        //stmt.executeUpdate("INSERT INTO stok_barang_wip(nama_part, wip) VALUES ('"+np+"','"+a+"');");
                    }
                    //stmt.executeUpdate("")
                }
                else if(stat.equals("FG")){
                    //stmt.executeUpdate("INSERT INTO stok_wfg(nama_part, jumlah, tanggal_diterima, picp, picw, ket) VALUES('"+np+"','"+a+"','"+dt+"','"+picp+"','"+picw+"','"+ket+"');");
                    rs = stmt.executeQuery("SELECT nama_part FROM stok_barang_fresh WHERE nama_part='"+np+"' && stat='FG';");
                    if(rs.next()){
                        rs1 = stmt.executeQuery("SELECT ok,ng,on_hold FROM stok_barang_fresh WHERE nama_part='"+np+"';");
                        while(rs1.next()){
                            bbb = rs1.getInt(1);
                            bb = rs1.getInt(2);
                            oh = rs1.getInt(3);
                        }
                        /*
                        rs2 = stmt.executeQuery("SELECT jumlah FROM stok_wfg_total WHERE nama_part ='"+np+"';");
                        while(rs2.next()){
                            bbb = rs2.getInt(1);
                        }
                        */
                        aaa = a;
                        ccc = aaa + bbb;
                        cc = aa+bb;
                        oh1 = a + aa;
                        oh2 = oh - oh1;
                        //stmt.executeUpdate("UPDATE stok_wfg_total SET jumlah='"+ccc+"' WHERE nama_part='"+np+"' && stat='non';");
                        stmt.executeUpdate("UPDATE stok_barang_fresh SET ok='"+ccc+"',ng='"+cc+"',on_hold='"+oh2+"' WHERE nama_part='"+np+"';");
                    }
                    else if(!rs.next()){
                        rs1 = stmt.executeQuery("SELECT ok,ng,on_hold FROM stok_barang_fresh WHERE nama_part='"+np+"';");
                        while(rs1.next()){
                            aaa= rs1.getInt(1);
                            bb = rs1.getInt(2);
                            oh = rs1.getInt(3);
                        }
                        ccc = aaa+a;
                        cc = aa+bb;
                        oh1 = a + aa;
                        oh2 = oh - oh1;
                        stmt.executeUpdate("UPDATE stok_barang_fresh set ok='"+ccc+"' ,ng='"+cc+"',on_hold='"+oh2+"' WHERE nama_part='"+np+"';");
                        //stmt.executeUpdate("INSERT INTO stok_wfg_total(nama_part, jumlah, stat) VALUES ('"+np+"','"+a+"','non');");
                    }
                }
            }
            alert al = new alert();
            al.info_upload();
            onhold_TV.getItems().clear();
            onholdmod.clear();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void onhold_shift(){
        List<String> list = new ArrayList<String>();
        list.add("Assy");
        list.add("FG");
        onhold_status.getItems().addAll(FXCollections.observableArrayList(list));
    }

    //STOK ASSY
    @FXML private void cek_stok_assy(){
        try {
            getStok_assy_models().clear();
            stok_assy_TV.getItems().clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,stok,ng FROM stok_barang_subassy;");
            while(rs.next()){
                stok_assy_models.add(new stok_assy_model(rs.getString(1),rs.getString(2),rs.getString(3)));
                stok_assy_npCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                stok_assy_okCOL.setCellValueFactory(cellData->cellData.getValue().okProperty());
                stok_assy_ngCOL.setCellValueFactory(cellData->cellData.getValue().ngProperty());
            }
            stok_assy_TV.setItems(getStok_assy_models());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private ObservableList<stok_assy_model> getStok_assy_models(){
        return stok_assy_models;
    }
}
