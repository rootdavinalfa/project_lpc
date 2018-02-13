package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class listShow {
    private SimpleStringProperty nama_part;
    private SimpleStringProperty nama_material;
    private SimpleStringProperty customer;
    private SimpleStringProperty berat_part;
    private SimpleStringProperty berat_runner;
    private SimpleStringProperty berat_total;
    private SimpleStringProperty harga_inj;
    private SimpleStringProperty cti;
    public listShow(String nama_part,String nama_material,String customer,String berat_part,String berat_runner,String berat_total,String harga_inj,String cti){
        this.nama_part = new SimpleStringProperty(nama_part);
        this.nama_material = new SimpleStringProperty(nama_material);
        this.customer = new SimpleStringProperty(customer);
        this.berat_part = new SimpleStringProperty(berat_part);
        this.berat_runner = new SimpleStringProperty(berat_runner);
        this.berat_total = new SimpleStringProperty(berat_total);
        this.harga_inj = new SimpleStringProperty(harga_inj);
        this.cti = new SimpleStringProperty(cti);
    }
    public StringProperty np(){
        return nama_part;
    }
    public StringProperty nm(){
        return nama_material;
    }
    public StringProperty bp(){
        return berat_part;
    }
    public StringProperty br(){
        return berat_runner;
    }
    public StringProperty bt(){
        return berat_total;
    }
    public StringProperty cust(){
        return customer;
    }
    public StringProperty hinj(){
        return harga_inj;
    }
    public StringProperty hcti(){
        return cti;

    }
}
