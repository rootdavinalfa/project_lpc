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
package davin.alfa;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class pass extends Dialog<String>{

        private PasswordField passwordField;
        public pass() {
            setTitle("Anda yakin dengan data yang anda masukkan?");
            setHeaderText("Tekan OK jika dirasa sudah sesuai dengan keadaan di lapangan!");

            ButtonType passwordButtonType = new ButtonType("OK", ButtonData.OK_DONE);
            getDialogPane().getButtonTypes().addAll(passwordButtonType, ButtonType.CANCEL);

            passwordField = new PasswordField();
            passwordField.setPromptText("Password");

            HBox hBox = new HBox();
            hBox.getChildren().add(passwordField);
            hBox.setPadding(new Insets(20));

            HBox.setHgrow(passwordField, Priority.ALWAYS);

            getDialogPane().setContent(hBox);

            Platform.runLater(() -> passwordField.requestFocus());

            setResultConverter(dialogButton -> {
                if (dialogButton == passwordButtonType) {
                    return passwordField.getText();
                }
                return null;
            });
        }

        public PasswordField getPasswordField() {
            return passwordField;
        }
}
