package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class model_h1h3 {
    private final StringProperty nama_material;
    private final StringProperty jumlah;
    private final StringProperty pic_wh;
    private final StringProperty pic_pen;
    private final StringProperty date;
    private final StringProperty keterangan;

    public model_h1h3(String nm,String jmlh,String pw,String pp,String dt,String ket){
        this.nama_material = new SimpleStringProperty(nm);
        this.jumlah = new SimpleStringProperty(jmlh);
        this.pic_wh = new SimpleStringProperty(pw);
        this.pic_pen = new SimpleStringProperty(pp);
        this.date = new SimpleStringProperty(dt);
        this.keterangan = new SimpleStringProperty(ket);
    }

    public StringProperty nama_materialProperty() {
        return nama_material;
    }

    public StringProperty jumlahProperty() {
        return jumlah;
    }

    public StringProperty pic_whProperty() {
        return pic_wh;
    }

    public StringProperty pic_penProperty() {
        return pic_pen;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty keteranganProperty() {
        return keterangan;
    }

    public String getNama_material() {
        return nama_material.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getJumlah() {
        return jumlah.get();
    }

    public String getPic_wh() {
        return pic_wh.get();
    }

    public String getPic_pen() {
        return pic_pen.get();
    }

    public String getKeterangan() {
        return keterangan.get();
    }
}
