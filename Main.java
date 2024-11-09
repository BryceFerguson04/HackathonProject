import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage appStage) {
        Layout layout = new Layout();
        Scene scene = new Scene(layout, 800, 600);
        appStage.setScene(scene);
        appStage.setTitle("GreenVault");
        appStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}