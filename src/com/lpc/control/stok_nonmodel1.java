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

public class stok_nonmodel1 {
    private final SimpleStringProperty nama_part;
    private final SimpleStringProperty ok;
    private final SimpleStringProperty ng;

    public stok_nonmodel1(String np ,String o,String n){
        this.nama_part = new SimpleStringProperty(np);
        this.ok = new SimpleStringProperty(o);
        this.ng = new SimpleStringProperty(n);
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
}
