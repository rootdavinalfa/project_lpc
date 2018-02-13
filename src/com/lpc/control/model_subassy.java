package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;

public class model_subassy {
    private SimpleStringProperty nama_part;
    private SimpleStringProperty wip;

    public model_subassy(String nmp,String wp){
        this.nama_part = new SimpleStringProperty(nmp);
        this.wip = new SimpleStringProperty(wp);
    }

    public SimpleStringProperty nama_partProperty() {
        return nama_part;
    }

    public SimpleStringProperty wipProperty() {
        return wip;
    }
}
