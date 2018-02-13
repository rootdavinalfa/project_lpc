package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;

public class stok_ssamodel1 {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty stok;
    private final SimpleStringProperty code;

    public stok_ssamodel1(String np ,String o,String n){
        this.nama_part = new SimpleStringProperty(np);
        this.stok = new SimpleStringProperty(o);
        this.code = new SimpleStringProperty(n);
    }

    public SimpleStringProperty nama_partProperty() {
        return nama_part;
    }

    public SimpleStringProperty stokProperty() {
        return stok;
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }
}
