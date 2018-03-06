package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;

public class stok_wip_model {
    private final SimpleStringProperty namapart;
    private final SimpleStringProperty wip;
    private final SimpleStringProperty sisa;

    public stok_wip_model(String nama,String wp,String sis){
        this.namapart = new SimpleStringProperty(nama);
        this.wip = new SimpleStringProperty(wp);
        this.sisa = new SimpleStringProperty(sis);
    }

    public SimpleStringProperty wipProperty() {
        return wip;
    }

    public SimpleStringProperty namapartProperty() {
        return namapart;
    }

    public SimpleStringProperty sisaProperty() {
        return sisa;
    }
}
