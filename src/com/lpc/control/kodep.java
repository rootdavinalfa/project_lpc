package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class kodep {
    private final StringProperty nama_part;
    private final StringProperty kode_part;

    public kodep(String nama_part,String kode_part){
        this.nama_part = new SimpleStringProperty(nama_part);
        this.kode_part = new SimpleStringProperty(kode_part);
    }

    public StringProperty nama_partProperty() {
        return nama_part;
    }

    public StringProperty kode_partProperty() {
        return kode_part;
    }
}
