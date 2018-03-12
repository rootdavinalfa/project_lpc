package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;


public class rincianModel {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty tanggal;
    private final SimpleStringProperty po;
    private final SimpleStringProperty deliver;
    private final SimpleStringProperty jumlah;
    private final SimpleStringProperty surat_jalan;

    public rincianModel(String nama,String tgl,String p,String dev,String jml,String surat){
        this.nama_part = new SimpleStringProperty(nama);
        this.tanggal = new SimpleStringProperty(tgl);
        this.po = new SimpleStringProperty(p);
        this.deliver = new SimpleStringProperty(dev);
        this.jumlah = new SimpleStringProperty(jml);
        this.surat_jalan = new SimpleStringProperty(surat);
    }

    public SimpleStringProperty deliverProperty() {
        return deliver;
    }

    public SimpleStringProperty poProperty() {
        return po;
    }

    public SimpleStringProperty tanggalProperty() {
        return tanggal;
    }

    public SimpleStringProperty jumlahProperty() {
        return jumlah;
    }

    public SimpleStringProperty nama_partProperty() {
        return nama_part;
    }

    public SimpleStringProperty surat_jalanProperty() {
        return surat_jalan;
    }

}
