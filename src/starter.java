import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class starter extends Application{
    public void main(String args[]){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("com/lpc/ui/main.fxml"));
        primaryStage.setTitle("LPC");
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();*/
        Parent root = FXMLLoader.load(getClass().getResource("com/lpc/ui/akses.fxml"));
        primaryStage.setTitle("LOGIN | LPC");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
