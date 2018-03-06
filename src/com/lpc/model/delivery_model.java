package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;


public class delivery_model {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty delivery_order;
    private final SimpleStringProperty delivery_cust;
    private final SimpleStringProperty deliveryJumlah;
    private final SimpleStringProperty delivery_suratjalan;
    private final SimpleStringProperty delivery_po;
    private final SimpleStringProperty delivery_tanggal;
    private final SimpleStringProperty delivery_terimasebagian;
    private final SimpleStringProperty delivery_actual;

    public delivery_model(String np,String d_o,String dc,String jml,String sr,String po,String tgl,String ts,String actual){
        this.nama_part = new SimpleStringProperty(np);
        this.delivery_order = new SimpleStringProperty(d_o);
        this.delivery_cust = new SimpleStringProperty(dc);
        this.deliveryJumlah = new SimpleStringProperty(jml);
        this.delivery_suratjalan = new SimpleStringProperty(sr);
        this.delivery_po = new SimpleStringProperty(po);
        this.delivery_tanggal = new SimpleStringProperty(tgl);
        this.delivery_terimasebagian = new SimpleStringProperty(ts);
        this.delivery_actual = new SimpleStringProperty(actual);
    }

    public SimpleStringProperty nama_partProperty() {
        return nama_part;
    }

    public SimpleStringProperty delivery_custProperty() {
        return delivery_cust;
    }

    public SimpleStringProperty delivery_orderProperty() {
        return delivery_order;
    }

    public SimpleStringProperty delivery_poProperty() {
        return delivery_po;
    }

    public SimpleStringProperty delivery_suratjalanProperty() {
        return delivery_suratjalan;
    }

    public SimpleStringProperty delivery_tanggalProperty() {
        return delivery_tanggal;
    }

    public SimpleStringProperty delivery_terimasebagianProperty() {
        return delivery_terimasebagian;
    }
    public SimpleStringProperty delivery_actualProperty(){
        return delivery_actual;
    }

    public SimpleStringProperty deliveryJumlahProperty() {
        return deliveryJumlah;
    }

    public String getDelivery_cust() {
        return delivery_cust.get();
    }

    public String getDelivery_order() {
        return delivery_order.get();
    }

    public String getDelivery_po() {
        return delivery_po.get();
    }

    public String getDelivery_suratjalan() {
        return delivery_suratjalan.get();
    }

    public String getDeliveryJumlah() {
        return deliveryJumlah.get();
    }

    public String getNama_part() {
        return nama_part.get();
    }

    public String getDelivery_tanggal() {
        return delivery_tanggal.get();
    }

    public String getDelivery_terimasebagian() {
        return delivery_terimasebagian.get();
    }

    public String getDelivery_actual() {
        return delivery_actual.get();
    }
}
