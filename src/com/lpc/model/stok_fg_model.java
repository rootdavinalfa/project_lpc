package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;


public class stok_fg_model {
    private final SimpleStringProperty namapart;
    private final SimpleStringProperty stok;
    private final SimpleStringProperty jenis;
    public stok_fg_model(String np ,String stk,String jns){
        this.namapart = new SimpleStringProperty(np);
        this.jenis = new SimpleStringProperty(jns);
        this.stok = new SimpleStringProperty(stk);
    }

    public SimpleStringProperty namapartProperty() {
        return namapart;
    }

    public SimpleStringProperty jenisProperty() {
        return jenis;
    }

    public SimpleStringProperty stokProperty() {
        return stok;
    }
}
