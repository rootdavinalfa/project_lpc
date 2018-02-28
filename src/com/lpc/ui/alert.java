/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package com.lpc.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;

public class alert {
    @SuppressWarnings("all")
    public void warn_showForm(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Tidak dapat melanjutkan");
        alert.setHeaderText(null);
        alert.setContentText("Anda belum memasukkan data yang dibutuhkan untuk search (null data)");
        alert.showAndWait();
    }
    public void warn_wrongcode(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Tidak Dapat Login Ke APP,ログインをAPP,できません");
        alert.setHeaderText(null);
        alert.setContentText("Pastikan Kode Yang Anda Masukkan Benar");
        alert.showAndWait();
    }
    public void error_upload(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data can't be processed");
        alert.setHeaderText(null);
        alert.setContentText("Pastikan data sudah terisi semua! Lalu coba kembali");
    }
    public void max15(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Tidak dapat melanjutkan");
        alert.setHeaderText(null);
        alert.setContentText("Maksimal List untuk dilaporkan adalah 15");
        alert.showAndWait();
    }
    public void error_sql(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("ERROR Connection");
        alert.setContentText("Tidak dapat menghubungkan ke database.Silahkan hubungi admin");

        Exception ex = new ConnectException("Tidak dapat menghubungkan ke database.Silahkan hubungi admin");

//Log Crawler
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// DIALOG
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    public void info_upload(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UPLOAD DATA SUCCESSFULLY");
        alert.setHeaderText(null);
        alert.setContentText("Data yang baru anda masukkan berhasil ter Input");

        alert.showAndWait();
    }
    public void info_Cb_disabled(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Permission Denied");
        alert.setHeaderText(null);
        alert.setContentText("Centang checkbox allow dan periksa kembali data yang di-input sebelum menekan tombol upload");

        alert.showAndWait();
    }
    public void warn_datanotfound(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Data Tidak Ditemukan");
        alert.setHeaderText(null);
        alert.setContentText("Data yang anda masukkan tidak ada di Database.");

        alert.showAndWait();
    }
    public void err_requirednotfound(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data can't be processed");
        alert.setHeaderText(null);
        alert.setContentText("Pastikan data sudah terisi semua! Lalu coba kembali");

        Exception ex = new Exception("Error!!,Cek kembali data yang diminta lalu coba kembali");

//Log Crawler
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was: (Jika error terus berlanjut,hubungi IT department)");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// DIALOG
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    public void info_export(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("EXPORT DATA SUCCESSFULLY");
        alert.setHeaderText(null);
        alert.setContentText("Data telah terExport,silahkan cek di folder yang sama dengan program ini");

        alert.showAndWait();
    }
    public void warn_cbUpload(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ILLEGAL REQUEST");
        alert.setHeaderText("Tidak Dapat Melanjutkan Proses");
        alert.setContentText("Pastikan untuk menceklist pada checkbox allow update jika ingin merubah data.");

        alert.showAndWait();
    }
    public void warn_poalready(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Already Inputted");
        alert.setHeaderText("Tidak Dapat Melanjutkan Proses");
        alert.setContentText("Data yang anda masukkan sudah terdaftar di server");

        alert.showAndWait();
    }

    public void warn_poerror(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERROR");
        alert.setHeaderText("Tidak Dapat Melanjutkan Proses");
        alert.setContentText("Pastikan data sudah anda masukkan");

        alert.showAndWait();
    }
    public void warn_poempty(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Tidak Dapat Melanjutkan Proses");
        alert.setContentText("Pastikan data sudah terinput");
        alert.showAndWait();
    }

    public void warn_wipassy(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Tidak Dapat Melanjutkan Proses");
        alert.setContentText("Kode yang anda masukkan merupakan kode part yang bukan termasuk part yang di perlukan Assy.");

        alert.showAndWait();
    }
    public void warn_datanotfoundCPROD(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Not Found");
        alert.setHeaderText("Tidak Dapat Melist");
        alert.setContentText("Pastikan anda sudah melaporkan produksi,dan cek kembali!");

        alert.showAndWait();
    }
    public void warn_dataisondatabase(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Data sudah ada");
        alert.setHeaderText("Tidak Dapat Upload");
        alert.setContentText("Pastikan anda memilih FG dan WIP dengan benar!");

        alert.showAndWait();
    }
}
