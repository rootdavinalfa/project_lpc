/*
#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
#+++																 +++
#+++	 Davin Alfarizky putra Basudewa <rootdavin@yahoo.co.jp>		 +++
#+++				Copyright 2018,IT Department LPC				 +++
#+++						it_lpc@lpc-ind.com						 +++
#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Version 0.0.1
This File Contain some OpenSource Package.Please refer to GNU Public License
for further information.
This class,contain LPC proprietary that's coded by Davin.Please contact
davin<rootdavin@yahoo.co.jp> or/ <it_lpc@lpc-ind.com>

*/
package com.lpc.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class model_h1h2_out {
    private final StringProperty nama_material;
    private final StringProperty qty;
    private final StringProperty satuan;
    private final StringProperty date;
    private final StringProperty pic;
    private final StringProperty pic2;
    private final StringProperty keterangan;

    public model_h1h2_out(String namaM, String q,String sat, String dat, String pc, String pc2, String ket){
        this.nama_material = new SimpleStringProperty(namaM);
        this.qty = new SimpleStringProperty(q);
        this.satuan = new SimpleStringProperty(sat);
        this.date = new SimpleStringProperty(dat);
        this.pic = new SimpleStringProperty(pc);
        this.pic2 = new SimpleStringProperty(pc2);
        this.keterangan = new SimpleStringProperty(ket);
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

    public StringProperty pic2Property() {
        return pic2;
    }
    public StringProperty keteranganProperty() {
        return keterangan;
    }
}
