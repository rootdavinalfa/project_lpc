package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;

public class stok_assy_model {
    private final SimpleStringProperty namapart;
    private final SimpleStringProperty jumlah;
    private final SimpleStringProperty ng;
    private final SimpleStringProperty total;

    public stok_assy_model(String np ,String jmlh,String n,String tot){
        this.namapart = new SimpleStringProperty(np);
        this.jumlah = new SimpleStringProperty(jmlh);
        this.ng = new SimpleStringProperty(n);
        this.total = new SimpleStringProperty(tot);
    }

    public SimpleStringProperty namapartProperty() {
        return namapart;
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public SimpleStringProperty ngProperty() {
        return ng;
    }

    public SimpleStringProperty jumlahProperty() {
        return jumlah;
    }
}
