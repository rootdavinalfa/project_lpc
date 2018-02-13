package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;

public class fg_t2_model {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty jumlah;
    private final SimpleStringProperty stat;

    public fg_t2_model(String np,String jml,String st){
        this.nama_part= new SimpleStringProperty(np);
        this.jumlah = new SimpleStringProperty(jml);
        this.stat = new SimpleStringProperty(st);
    }

    public SimpleStringProperty nama_partProperty() {
        return nama_part;
    }

    public SimpleStringProperty jumlahProperty() {
        return jumlah;
    }

    public SimpleStringProperty statProperty() {
        return stat;
    }
}
