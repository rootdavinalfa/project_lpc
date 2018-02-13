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
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.Sides;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class warehouseControl implements Initializable {
    @FXML private TextField h1h2_nm;
    @FXML private TextField h1h2_qty;
    @FXML private TextField h1h2_qtyng;
    @FXML private TextField h1h2_satuan;
    @FXML private DatePicker h1h2_date;
    @FXML private TextField h1h2_pic;
    @FXML private TextField h1h2_supplier;
    @FXML private TextField h1h2_lotc;
    @FXML private TextField h1h2_lotlpc;
    @FXML private TextField h1h2_resi;
    @FXML private Label h1h2_nowdate;
    @FXML private TableColumn<h1h2,String> h1h2_nmCOL;
    @FXML private TableColumn<h1h2,String> h1h2_jumlahCOL;
    @FXML private TableView<h1h2> h1h2_tv;

//h1h3
    @FXML private TextField h1h3_nm;
    @FXML private TextField h1h3_jumlah;
    @FXML private TextField h1h3_picwh;
    @FXML private TextField h1h3_picpen;
    @FXML private DatePicker h1h3_date;
    @FXML private TextArea h1h3_ket;
    @FXML private TableView<model_h1h3> h1h3TV;
    @FXML private TableColumn<model_h1h3,String> h1h3_nmCOL;
    @FXML private TableColumn<model_h1h3,String> h1h3_jumlahCOL;
    @FXML private TableColumn<model_h1h3,String> h1h3_tanggalCOL;
    @FXML private TableColumn<model_h1h3,String> h1h3_picCOL;
    @FXML private TableColumn<model_h1h3,String> h1h3_picpCOL;
    @FXML private Label aa;

    private ObservableList<h1h2> h1h2D = FXCollections.observableArrayList();
    private ObservableList<model_h1h3> h1h3_mod = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){
        h1h2_refresh();
        h1h2_tvACT();
        setCurrentTime();
    }

    private void h1h2_tvACT(){
        h1h2_tv.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount() == 1 ){

                select_h1h2();

            }
        });
    }
    private void select_h1h2(){
        if(h1h2_tv.getSelectionModel().getSelectedItem() != null){
            try {
                String a = sendToModal();
                //cekprod selectedcp = cprod_tab.getSelectionModel().getSelectedItem();
                System.out.println(a);
                //mc = new modal_cekprod();
                //mc.redirect(stage,a,b,c);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/lpc/ui/modal_h1h2.fxml"));
                loader.load();
                modal_h1h2 mh = loader.getController();
                mh.set(a);
                mh.print();
                mh.cach();
                //mh.cach1();
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
        h1h2 selectedh2h2 = h1h2_tv.getSelectionModel().getSelectedItem();
        a = selectedh2h2.getNM();
        return a;
    }
    private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                h1h2_nowdate.setText(simpleDateFormat.format(time.getTime()));

                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    @FXML private void h1h2_refresh(){
        h1h2_tv.setItems(null);
        h1h2D.clear();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_material,jumlah_stok FROM warehouseraw");
            while (rs.next()){
                h1h2D.addAll(new h1h2(rs.getString(1),rs.getString(2)));
                h1h2_nmCOL.setCellValueFactory(cellData -> cellData.getValue().nama_partProperty());
                h1h2_jumlahCOL.setCellValueFactory(cellData -> cellData.getValue().jumlah_stokProperty());

            }
            con.close();
            h1h2_tv.setItems(getINC());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<h1h2> getINC(){
        return h1h2D;
    }
    @FXML private void h1h2_upload(){
        String a = h1h2_nm.getText();
        String ba = h1h2_qty.getText();
        String bb;
        bb = h1h2_qtyng.getText();
        int b1 = Integer.parseInt(ba);
        int b2 = Integer.parseInt(bb);
        int b = b1;
        String c = h1h2_satuan.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String d = h1h2_date.getValue().format(formatter);
        String e = h1h2_pic.getText();
        String f = h1h2_supplier.getText();
        String g = h1h2_lotc.getText();
        String h = h1h2_lotlpc.getText();
        String i = h1h2_resi.getText();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            //ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO warehouse_income(nama_material, qty,ok,ng, satuan, date_kedatangan, pic, supplier,lot_cust,lot_lpc,nomor_resi) VALUES('"+a+"','"+b+"','"+b1+"','"+b2+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"');");
            con.close();
            h1h2resql();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void h1h2resql(){
        String a = null;
        String b = null;
        String c = null;
        String d = null;
        String e = h1h2_nm.getText();
        try {
            Class.forName("com.lpc.driver.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            con = connector.setConnection();
            stmt = con.createStatement();

            //rs = stmt.executeQuery("SELECT nama_material,SUM(qty),satuan,pic FROM warehouse_income WHERE nama_material='"+e+"';");
            rs = stmt.executeQuery("SELECT nama_material FROM warehouseraw WHERE nama_material='"+e+"';");
            if(rs.next()){
                rs1 = stmt.executeQuery("SELECT jumlah_stok,satuan FROM warehouseraw WHERE nama_material ='"+e+"';");
                while(rs1.next()){
                    a = rs1.getString(1);
                    b = rs1.getString(2);
                }
                rs2 = stmt.executeQuery("SELECT qty,satuan FROM warehouse_income WHERE nama_material='"+e+"';");
                while(rs2.next()){
                    c = rs2.getString(1);
                }
                int jml1 = Integer.parseInt(a);
                int jml2 = Integer.parseInt(c);
                int jml = jml1+jml2;
                stmt.executeUpdate("UPDATE warehouseraw SET jumlah_stok='"+jml+"',satuan='"+b+"' WHERE nama_material='"+e+"';");
                con.close();

            }
            else if (!rs.next()){
                rs1 = stmt.executeQuery("SELECT qty,satuan FROM warehouse_income WHERE nama_material='"+e+"';");
                while (rs1.next()){
                    a=rs1.getString(1);
                    b=rs1.getString(2);
                }
                stmt.executeUpdate("INSERT INTO warehouseraw(nama_material, jumlah_stok, satuan) VALUES('"+e+"','"+a+"','"+b+"');");
                con.close();
            }
            h1h2_refresh();
            alert al = new alert();
            al.info_upload();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML private void h1h3_addTolist(){
        String nm = h1h3_nm.getText();
        String jml  = h1h3_jumlah.getText();
        String pw = h1h3_picwh.getText();
        String pp = h1h3_picpen.getText();
        String ket = h1h3_ket.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = h1h3_date.getValue().format(formatter);
        if(h1h3TV.getItems().size() < 15){
            h1h3_mod.addAll(new model_h1h3(nm,jml,pw,pp,date,ket));
            h1h3_nmCOL.setCellValueFactory(cellData -> cellData.getValue().nama_materialProperty());
            h1h3_jumlahCOL.setCellValueFactory(cellData -> cellData.getValue().jumlahProperty());
            h1h3_picCOL.setCellValueFactory(cellData -> cellData.getValue().pic_whProperty());
            h1h3_picpCOL.setCellValueFactory(cellData -> cellData.getValue().pic_penProperty());
            h1h3_tanggalCOL.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
            h1h3TV.setItems(geth1h3());
        }
        else{
            alert al = new alert();
            al.max15();
        }
        //System.out.println(geth1h3());
    }
    @FXML private void h1h3_print(){
        try {
            //int index = 0;
            Connection con = null;
            Statement stmt = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            ResultSet rs = null;
            for(int i=0;i<h1h3TV.getItems().size();i++){
                String a = String.valueOf(h1h3TV.getItems().get(i).getNama_material());
                String b = String.valueOf(h1h3TV.getItems().get(i).getJumlah());
                String c = String.valueOf(h1h3TV.getItems().get(i).getDate());
                String d = String.valueOf(h1h3TV.getItems().get(i).getPic_wh());
                String e = String.valueOf(h1h3TV.getItems().get(i).getPic_pen());
                String f = String.valueOf(h1h3_ket.getText());
                int jml_a = Integer.parseInt(b);
                int jml_b =0;
                String sat = null;
                rs = stmt.executeQuery("SELECT jumlah_stok,satuan FROM warehouseraw WHERE nama_material='"+a+"';");
                while(rs.next()){
                    jml_b = rs.getInt(1);
                    sat = rs.getString(2);
                }
                int jml =jml_b - jml_a;
                stmt.executeUpdate("UPDATE warehouseraw SET jumlah_stok ='"+jml+"' WHERE nama_material='"+a+"';");
                stmt.executeUpdate("INSERT INTO warehouse_out (nama_material, tanggal, picwh, picpen, jumlah, ket,satuan) " +
                        "VALUES ('"+a+"','"+c+"','"+d+"','"+e+"','"+b+"','"+f+"','"+sat+"');");
            }
            con.close();
            pdfCreateh1h3();
            clearh1h3();
            alert al = new alert();
            al.info_upload();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*
        String a =String.valueOf(h1h3_mod.get(0).dateProperty());
        String b = String.valueOf(h1h3TV.getItems().size());
        String c = String.valueOf(h1h3TV.getItems().get(0).nama_materialProperty());
        System.out.println(a+" Size: "+b+" :: "+c);

        aa.setText(a);*/
    }
    private void  pdfCreateh1h3(){
        try {
            PDDocument pdfDoc = PDDocument.load(new File("template_lpc_wh_out.pdf")); // paste your pdf-file location here

            PDDocumentCatalog docCatalog = pdfDoc.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();

            if(acroForm !=null){
                Calendar time = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String a = simpleDateFormat.format(time.getTime());
                String b = "zero";
                String c_wh = h1h3_picwh.getText();
                String c_pen = h1h3_picpen.getText();
                PDField field_tanggal = (PDField) acroForm.getField( "tanggal_laporan");
                field_tanggal.setValue(a);
                PDField field_nl = (PDField) acroForm.getField( "nomor_laporan" );
                field_nl.setValue(b);
                PDField field_picwh = (PDField) acroForm.getField( "pic_warehouse" );
                field_picwh.setValue(c_wh);
                PDField field_picp = (PDField) acroForm.getField( "pic_penerima" );
                field_picp.setValue(c_pen);

                try {
                    Connection con = null;
                    Statement stmt = null;
                    String lc = null;
                    String ll = null;
                    String pic =null;
                    con = connector.setConnection();
                    stmt = con.createStatement();
                    ResultSet rs = null;
                    ResultSet rs1 = null;
                    String ket = null;
                    for (int i = 0; i < h1h3TV.getItems().size(); i++) {
                        int ii = i+1;
                        String no = String.valueOf(ii);
                        String nm = h1h3TV.getItems().get(i).getNama_material();
                        String date = String.valueOf(h1h3TV.getItems().get(i).getDate());
                        String qty = String.valueOf(h1h3TV.getItems().get(i).getJumlah());
                        //System.out.println(no+"/"+nm+"/"+lc+"/"+ll+"/"+date+"/"+pic+"/"+qty+"/"+ket);
                        rs = stmt.executeQuery("SELECT lot_cust,lot_lpc,pic FROM warehouse_income WHERE nama_material='"+nm+"';");
                        while (rs.next()){
                            lc = rs.getString(1);
                            ll = rs.getString(2);
                            pic = rs.getString(3);
                        }
                        rs1 = stmt.executeQuery("SELECT ket FROM warehouse_out WHERE nama_material='"+nm+"';");
                        while(rs1.next()){
                            ket = rs1.getString(1);
                        }

                        PDField field_no = (PDField) acroForm.getField("no" + ii);
                        field_no.setValue(no);
                        PDField field_nm = (PDField) acroForm.getField("nama_material" + ii);
                        field_nm.setValue(nm);
                        PDField field_lotc = (PDField) acroForm.getField("lot_cust" + ii);
                        field_lotc.setValue(lc);
                        PDField field_lotl = (PDField) acroForm.getField("lot_lpc" + ii);
                        field_lotl.setValue(ll);
                        PDField field_tp = (PDField) acroForm.getField("tanggal" + ii);
                        field_tp.setValue(date);
                        PDField field_pic = (PDField) acroForm.getField("pic" + ii);
                        field_pic.setValue(pic);
                        PDField field_qty = (PDField) acroForm.getField("qty" + ii);
                        field_qty.setValue(qty);
                        PDField field_ket = (PDField) acroForm.getField("ket" + ii);
                        field_ket.setValue(ket);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            /*
            for (Object fieldObj : acroForm.getFields()) {
                PDField field = (PDField) fieldObj;
                System.out.println(field.getFullyQualifiedName()); // print field's name
                field.setValue("1"); // set value of field to 1
            }*/

            pdfDoc.save("test2.pdf"); // save changes to another file
            pdfDoc.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void clearh1h3(){
        h1h3_mod.clear();
        h1h3TV.getItems().clear();
    }
    private ObservableList<model_h1h3> geth1h3(){
        return h1h3_mod;
    }
}
