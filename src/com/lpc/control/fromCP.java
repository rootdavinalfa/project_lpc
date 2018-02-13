package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class fromCP {
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    public void sender(String nama,String time,String date){
        namaProperty().set(nama);
        timeProperty().set(time);
        dateProperty().set(date);
    }

    public String getNama(){
        return namaProperty().get();
    }
    public String getTime(){
        return timeProperty().get();
    }
    public String getDate(){
        return dateProperty().get();
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty dateProperty() {
        return date;
    }
}
