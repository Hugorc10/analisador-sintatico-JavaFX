package view;

import controller.ScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScreenView {
    private final ScreenController controller = new ScreenController();
    private Label lblTitle;
    private Label lblEntrada;
    private Label lblSaida;
    public static TextArea textEnter;
    public static TextArea textOut;
    public static Button btnAnalyse;
    public static Button btnClean;
    
    public BorderPane createPane() {
        BorderPane borderPane = new BorderPane();
        Insets insets1 = new Insets(15, 0, 15, 15);
        Insets insets2 = new Insets(15, 15, 15, 15);
        
//        FlowPane root = new FlowPane();
//        root.setHgap(10);
//        root.setVgap(10);
    
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(20);
        
        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(0, 15, 0, 15));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(650);
        
        lblTitle = new Label("Analise Lexica");
        lblTitle.setFont(new Font("Arial Black", 20));
        lblTitle.setTextFill(Color.web("#E15E5E"));
        
        lblEntrada = new Label("Entrada");
        lblEntrada.setFont(new Font("Arial Black", 20));
        lblEntrada.setTextFill(Color.web("Purple"));
    
        lblSaida = new Label("Saida");
        lblSaida.setFont(new Font("Arial Black", 20));
        lblSaida.setTextFill(Color.web("Purple"));
//        vBox.getChildren().add(lblTitle);
    
        btnAnalyse = new Button("Analisar");
        btnAnalyse.setFont(new Font("Arial", 18));
        btnAnalyse.setTextFill(Color.web("purple"));
        btnAnalyse.setPrefSize(100, 50);
        hBox1.getChildren().add(btnAnalyse);
        
        btnClean = new Button("Limpar");
        btnClean.setFont(new Font("Arial", 18));
        btnClean.setPrefSize(100, 50);
        hBox1.getChildren().add(btnClean);
        
        hBox2.getChildren().addAll(lblEntrada, lblSaida);
        
        vBox.setSpacing(10);
        vBox.getChildren().addAll(lblTitle, hBox1, hBox2);
        
        borderPane.setTop(vBox);
        
        textEnter = new TextArea();
//        textEnter.setPrefColumnCount(15);
        textEnter.setPrefSize(400, 300);
        textEnter.setStyle("-fx-font-size: 15");
        borderPane.setLeft(textEnter);
        BorderPane.setMargin(textEnter, insets1);
        
        textOut = new TextArea();
        textOut.setPrefSize(400, 300);
        textOut.setStyle("-fx-font-size: 15");
//        textOut.setPrefColumnCount(15);
        borderPane.setRight(textOut);
        BorderPane.setMargin(textOut, insets2);
        
//        root.getChildren().addAll(lblTitle, textEnter, textOut, btnAnalyse, btnClean);
        controller.handler();
        return borderPane;
    }
}
