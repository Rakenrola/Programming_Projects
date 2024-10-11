package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Calculator extends Application 
{


    public void start(Stage primaryStage) 
    {
		try 
		{
		Parent root = FXMLLoader.load(getClass().getResource("CalculatorUI.fxml")); 
		Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        
		primaryStage.setTitle("Multi-Input TextArea");
        primaryStage.setScene(scene);
        primaryStage.show();
        Image icon = new Image("/Image Assets/CalculatorIcon.png");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Calculator");
        
		}	catch (Exception e) 
		
		{
				e.printStackTrace();
		}
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}