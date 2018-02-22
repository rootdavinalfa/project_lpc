package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

public class list_po_model {
    private final SimpleStringProperty nama_material;
    private final SimpleStringProperty no_resi;
    private final SimpleStringProperty qty_po;
    private final SimpleStringProperty qty_terima;
    private final SimpleStringProperty tanggal;
    private final SimpleStringProperty pic;

    public list_po_model(String nama,String resi,String qty,String qty_ter,String tgl,String pc){
        this.nama_material = new SimpleStringProperty(nama);
        this.no_resi = new SimpleStringProperty(resi);
        this.qty_po = new SimpleStringProperty(qty);
        this.qty_terima = new SimpleStringProperty(qty_ter);
        this.tanggal = new SimpleStringProperty(tgl);
        this.pic = new SimpleStringProperty(pc);
    }

    public SimpleStringProperty tanggalProperty() {
        return tanggal;
    }

    public SimpleStringProperty nama_materialProperty() {
        return nama_material;
    }

    public SimpleStringProperty no_resiProperty() {
        return no_resi;
    }

    public SimpleStringProperty picProperty() {
        return pic;
    }

    public SimpleStringProperty qty_poProperty() {
        return qty_po;
    }

    public SimpleStringProperty qty_terimaProperty() {
        return qty_terima;
    }
}
