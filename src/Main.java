import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.Controller;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));

        FXMLLoader loader = new FXMLLoader();
        //Create a controller
        Controller controller =new Controller();
        //Attach controller
        loader.setController(controller);
        //Attach XML File
        Parent root = loader.load(getClass().getResourceAsStream(View.XML_FILE));
        //Attach css
        //Initialize controller
        controller.init();
        //Create the view
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(View.LABEL);
        //Show the view
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
