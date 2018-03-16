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

public class kodep {
    private final StringProperty nama_part;
    private final StringProperty kode_part;

    public kodep(String nama_part,String kode_part){
        this.nama_part = new SimpleStringProperty(nama_part);
        this.kode_part = new SimpleStringProperty(kode_part);
    }

    public StringProperty nama_partProperty() {
        return nama_part;
    }

    public StringProperty kode_partProperty() {
        return kode_part;
    }
}
