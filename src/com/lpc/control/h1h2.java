package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class h1h2 {
    private final StringProperty nama_part;
    private final StringProperty jumlah_stok;

    public h1h2(String nama_part,String jumlah_stok){
        this.nama_part = new SimpleStringProperty(nama_part);
        this.jumlah_stok = new SimpleStringProperty(jumlah_stok);
    }

    public StringProperty nama_partProperty() {
        return nama_part;
    }

    public StringProperty jumlah_stokProperty() {
        return jumlah_stok;
    }
    public String getNM(){
        return nama_part.get();
    }
}
