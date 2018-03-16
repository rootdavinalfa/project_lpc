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

public class stok_ssamodel {
    private final StringProperty nama_part;
    private final StringProperty picp;
    private final StringProperty picw;
    private final StringProperty jml;
    private final StringProperty ket;
    public stok_ssamodel(String nama,String picp,String picw,String jml,String kt){
        this.nama_part = new SimpleStringProperty(nama);
        this.picp = new SimpleStringProperty(picp);
        this.picw = new SimpleStringProperty(picw);
        this.jml = new SimpleStringProperty(jml);
        this.ket = new SimpleStringProperty(kt);
    }

    public StringProperty nama_partProperty() {
        return nama_part;
    }

    public StringProperty picpProperty() {
        return picp;
    }

    public StringProperty picwProperty() {
        return picw;
    }

    public StringProperty jmlProperty() {
        return jml;
    }

    public StringProperty ketProperty() {
        return ket;
    }

    public String getNama_part() {
        return nama_part.get();
    }

    public String getPicp() {
        return picp.get();
    }

    public String getPicw() {
        return picw.get();
    }

    public String getJml() {
        return jml.get();
    }

    public String getKet() {
        return ket.get();
    }
}
