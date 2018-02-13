package com.lpc.control;

import com.lpc.driver.connector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;

import javax.annotation.Resource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class finishgoodControl implements Initializable {
    @FXML private Label t2_label_date;
    @FXML private TableView<fg_t2_model> t2_tv;
    @FXML private TableColumn<fg_t2_model,String> t2_npCOL;
    @FXML private TableColumn<fg_t2_model,String> t2_jumCOL;
    @FXML private TableColumn<fg_t2_model,String> t2_anonCOL;

    private ObservableList<fg_t2_model> t2_models = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle rb){
        setCurrentTime();
        t2_refresh();
    }

    private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
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

    @FXML private void t2_refresh(){
        try {
            t2_tv.getItems().clear();
            getT2_models().clear();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT nama_part,jumlah,stat FROM stok_wfg_total;");
            while(rs.next()){
                t2_models.addAll(new fg_t2_model(rs.getString(1),rs.getString(2),rs.getString(3)));
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
    private ObservableList<fg_t2_model> getT2_models(){
        return t2_models;
    }
}
