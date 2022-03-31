package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Погода mini");
        primaryStage.setScene(new Scene(root, 378, 525)); //Задание размера окна
        primaryStage.setResizable(false); //Запрет на изменение размера
        primaryStage.centerOnScreen(); //Центровка по экрану
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
