package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class cekprod {
    private final StringProperty nama_part;
    private final StringProperty operator;
    private final StringProperty tanggal;
    private final StringProperty jam;
    private final StringProperty shift;
    private final StringProperty beratp;
    private final StringProperty beratr;
    private final StringProperty beratt;
    private final StringProperty cti;
    private final StringProperty good;
    private final StringProperty ng;
    private final StringProperty kwh;
    private final StringProperty cav;
    private final StringProperty nomesin;

    public cekprod(String nama_part,String operator,String tanggal,String jam,String shift,String beratp,String beratr,String beratt,String cti,String good,String ng,String kwh,String cav,String nomes){
        this.nama_part = new SimpleStringProperty(nama_part);
        this.operator = new SimpleStringProperty(operator);
        this.tanggal = new SimpleStringProperty(tanggal);
        this.jam = new SimpleStringProperty(jam);
        this.shift = new SimpleStringProperty(shift);
        this.beratp = new SimpleStringProperty(beratp);
        this.beratr = new SimpleStringProperty(beratr);
        this.beratt = new SimpleStringProperty(beratt);
        this.cti = new SimpleStringProperty(cti);
        this.good = new SimpleStringProperty(good);
        this.ng = new SimpleStringProperty(ng);
        this.kwh = new SimpleStringProperty(kwh);
        this.cav = new SimpleStringProperty(cav);
        this.nomesin = new SimpleStringProperty(nomes);
    }

    public StringProperty nama_partProperty() {
        return nama_part;
    }

    public StringProperty operatorProperty() {
        return operator;
    }

    public StringProperty tanggalProperty() {
        return tanggal;
    }

    public StringProperty jamProperty() {
        return jam;
    }

    public StringProperty shiftProperty() {
        return shift;
    }

    public StringProperty beratpProperty() {
        return beratp;
    }

    public StringProperty beratrProperty() {
        return beratr;
    }

    public StringProperty berattProperty() {
        return beratt;
    }

    public StringProperty ctiProperty() {
        return cti;
    }

    public StringProperty goodProperty() {
        return good;
    }

    public StringProperty ngProperty() {
        return ng;
    }

    public StringProperty kwhProperty() {
        return kwh;
    }
    public StringProperty cavproperty(){
        return cav;
    }

    public StringProperty nomesinProperty() {
        return nomesin;
    }

    public String getCav(){
        return cav.get();
    }
    public String getNP(){
        return nama_part.get();
    }

    public String getJam() {
        return jam.get();
    }
    public String getDate(){
        return tanggal.get();
    }
    public String getOP(){
        return operator.get();
    }
}
