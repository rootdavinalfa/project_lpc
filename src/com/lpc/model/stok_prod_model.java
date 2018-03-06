package com.lpc.model;

import javafx.beans.property.SimpleStringProperty;

public class stok_prod_model {
    private final SimpleStringProperty namapart;
    private final SimpleStringProperty stok;
    private final SimpleStringProperty ng;
    private final SimpleStringProperty runner;
    private final SimpleStringProperty bonggol;
    //private final SimpleStringProperty kwh;
    private final SimpleStringProperty wip;
    private final SimpleStringProperty status;
    private final SimpleStringProperty total;

    public stok_prod_model(String namap,String stk,String n,String run,String bong,String wi,String stat,String tot){
        this.namapart = new SimpleStringProperty(namap);
        this.stok = new SimpleStringProperty(stk);
        this.ng = new SimpleStringProperty(n);
        this.runner = new SimpleStringProperty(run);
        this.bonggol = new SimpleStringProperty(bong);
        //this.kwh = new SimpleStringProperty(kw);
        this.wip = new SimpleStringProperty(wi);
        this.status = new SimpleStringProperty(stat);
        this.total = new SimpleStringProperty(tot);

    }

    public SimpleStringProperty bonggolProperty() {
        return bonggol;
    }

    public SimpleStringProperty namapartProperty() {
        return namapart;
    }

    public SimpleStringProperty ngProperty() {
        return ng;
    }

    public SimpleStringProperty runnerProperty() {
        return runner;
    }

    public SimpleStringProperty stokProperty() {
        return stok;
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public SimpleStringProperty wipProperty() {
        return wip;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }
}
