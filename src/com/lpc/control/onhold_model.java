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

public class onhold_model {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty ok;
    private final SimpleStringProperty ng;
    private final SimpleStringProperty status;

    public onhold_model(String np,String o,String n,String stat){
        this.nama_part = new SimpleStringProperty(np);
        this.ok = new SimpleStringProperty(o);
        this.ng = new SimpleStringProperty(n);
        this.status = new SimpleStringProperty(stat);
    }

    public SimpleStringProperty nama_partProperty() {
        return nama_part;
    }

    public SimpleStringProperty okProperty() {
        return ok;
    }

    public SimpleStringProperty ngProperty() {
        return ng;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public String getNama_part() {
        return nama_part.get();
    }

    public String getOk() {
        return ok.get();
    }

    public String getNg() {
        return ng.get();
    }

    public String getStatus() {
        return status.get();
    }
}
