package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;

public class list_wait_po {
    private final SimpleStringProperty nama_material;
    private final SimpleStringProperty nopo;
    private final SimpleStringProperty qty;
    private final SimpleStringProperty tanggal;
    private final SimpleStringProperty harga;

    public list_wait_po(String nama,String no,String qt,String tgl,String hrg){
        this.nama_material = new SimpleStringProperty(nama);
        this.nopo = new SimpleStringProperty(no);
        this.qty = new SimpleStringProperty(qt);
        this.tanggal = new SimpleStringProperty(tgl);
        this.harga = new SimpleStringProperty(hrg);
    }

    public SimpleStringProperty nama_materialProperty() {
        return nama_material;
    }

    public SimpleStringProperty hargaProperty() {
        return harga;
    }

    public SimpleStringProperty nopoProperty() {
        return nopo;
    }

    public SimpleStringProperty qtyProperty() {
        return qty;
    }

    public SimpleStringProperty tanggalProperty() {
        return tanggal;
    }

    public String getHarga() {
        return harga.get();
    }

    public String getNama_material() {
        return nama_material.get();
    }

    public String getNopo() {
        return nopo.get();
    }

    public String getQty() {
        return qty.get();
    }

    public String getTanggal() {
        return tanggal.get();
    }
}
