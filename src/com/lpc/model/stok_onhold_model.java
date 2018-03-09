package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;


public class stok_onhold_model {
    private final SimpleStringProperty namapart;
    private final SimpleStringProperty onhold;
    public stok_onhold_model(String np ,String oh){
        this.namapart = new SimpleStringProperty(np);
        this.onhold = new SimpleStringProperty(oh);
    }

    public SimpleStringProperty namapartProperty() {
        return namapart;
    }

    public SimpleStringProperty onholdProperty() {
        return onhold;
    }
}
