package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;

public class stok_nonmodel1 {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty ok;
    private final SimpleStringProperty ng;

    public stok_nonmodel1(String np ,String o,String n){
        this.nama_part = new SimpleStringProperty(np);
        this.ok = new SimpleStringProperty(o);
        this.ng = new SimpleStringProperty(n);
    }

    public SimpleStringProperty nama_partProperty() {
        return nama_part;
    }

    public SimpleStringProperty okProperty() {
        return ok;
    }

    public SimpleStringProperty ngProperty() {
        return ng;
    }
}
