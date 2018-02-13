package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

public class stok_assy_model {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty ok;
    private final SimpleStringProperty ng;

    public stok_assy_model(String np,String o,String n){
        this.nama_part= new SimpleStringProperty(np);
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
