package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class model_h1h2 {
    private final StringProperty nama_material;
    private final StringProperty qty;
    private final StringProperty ok;
    private final StringProperty ng;
    private final StringProperty satuan;
    private final StringProperty date;
    private final StringProperty pic;
    private final StringProperty sup;
    private final StringProperty lot_cust;
    private final StringProperty lot_lpc;
    private final StringProperty resi;


    public model_h1h2(String namaM,String q,String g,String n,String sat,String dat,String pc,String sp,String ll,String lc,String res){
        this.nama_material = new SimpleStringProperty(namaM);
        this.qty = new SimpleStringProperty(q);
        this.ok = new SimpleStringProperty(g);
        this.ng = new SimpleStringProperty(n);
        this.satuan = new SimpleStringProperty(sat);
        this.date = new SimpleStringProperty(dat);
        this.pic = new SimpleStringProperty(pc);
        this.sup = new SimpleStringProperty(sp);
        this.lot_cust = new SimpleStringProperty(lc);
        this.lot_lpc = new SimpleStringProperty(ll);
        this.resi = new SimpleStringProperty(res);
    }

    public StringProperty nama_materialProperty() {
        return nama_material;
    }

    public StringProperty qtyProperty() {
        return qty;
    }

    public StringProperty satuanProperty() {
        return satuan;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty picProperty() {
        return pic;
    }

    public StringProperty supProperty() {
        return sup;
    }

    public StringProperty okProperty() {
        return ok;
    }

    public StringProperty ngProperty() {
        return ng;
    }

    public StringProperty lot_custProperty() {
        return lot_cust;
    }

    public StringProperty lot_lpcProperty() {
        return lot_lpc;
    }

    public StringProperty resiProperty() {
        return resi;
    }
}
