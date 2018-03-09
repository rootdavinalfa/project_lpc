package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;



public class finishgood_delivery_stok {
    private final SimpleStringProperty namapart;
    private final SimpleStringProperty po;
    private final SimpleStringProperty customer;
    private final SimpleStringProperty jumlah;
    private final SimpleStringProperty tanggal;
    private final SimpleStringProperty status;
    public finishgood_delivery_stok(String np,String p ,String cus,String jmlh,String tgl,String stat){
        this.namapart = new SimpleStringProperty(np);
        this.po = new SimpleStringProperty(p);
        this.customer = new SimpleStringProperty(cus);
        this.jumlah = new SimpleStringProperty(jmlh);
        this.tanggal = new SimpleStringProperty(tgl);
        this.status = new SimpleStringProperty(stat);
    }

    public SimpleStringProperty jumlahProperty() {
        return jumlah;
    }

    public SimpleStringProperty namapartProperty() {
        return namapart;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public SimpleStringProperty tanggalProperty() {
        return tanggal;
    }

    public SimpleStringProperty customerProperty() {
        return customer;
    }

    public SimpleStringProperty poProperty() {
        return po;
    }
}
