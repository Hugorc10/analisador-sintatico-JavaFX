import view.ScreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenView view = new ScreenView();
        Scene scene = new Scene(view.createPane(), 850, 700);
    
        // seta o titulo da janela
        primaryStage.setTitle("Analisador Lexico");
        // nao pode mudar o tamanho
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        // mostra a tela
        primaryStage.show();
    }
}
