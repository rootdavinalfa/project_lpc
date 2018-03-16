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

public class material_tabEdit {
    private final StringProperty nama_part;
    private final StringProperty nama_material;
    private final StringProperty jumlah;
    private final StringProperty harga;

    public material_tabEdit(String nama_part, String nama_material, String jumlah,String harga){
        this.nama_part = new SimpleStringProperty(nama_part);
        this.nama_material = new SimpleStringProperty(nama_material);
        this.jumlah = new SimpleStringProperty(jumlah);
        this.harga = new SimpleStringProperty(harga);
    }

    public StringProperty nama_partProperty() {
        return nama_part;
    }

    public StringProperty nama_materialProperty() {
        return nama_material;
    }

    public StringProperty jumlahProperty() {
        return jumlah;
    }
    public StringProperty hargaProperty(){
        return harga;
    }
}
