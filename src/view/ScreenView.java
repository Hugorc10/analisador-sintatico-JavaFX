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
    public static Button btnParser;
    public static TextArea textParserOut;
    
    public BorderPane createPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        borderPane.setTop(createLexerPane());
        borderPane.setBottom(createParserPane());
    
        controller.handler();
    
        return borderPane;
    }

    public VBox createLexerPane() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

//        HBox hBox1 = new HBox();
//        hBox1.setAlignment(Pos.CENTER);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(20);
        hBox2.setAlignment(Pos.CENTER);

        HBox hBox3 = new HBox();
        hBox3.setSpacing(675);

        HBox hBox4 = new HBox();
        hBox4.setSpacing(20);

        lblTitle = new Label("Analise Lexica");
        lblTitle.setFont(new Font("Arial Black", 20));
        lblTitle.setTextFill(Color.web("#E15E5E"));

        btnAnalyse = new Button("Analisar");
        btnAnalyse.setFont(new Font("Arial", 18));
        btnAnalyse.setTextFill(Color.web("purple"));
        btnAnalyse.setPrefSize(100, 50);

        btnClean = new Button("Limpar");
        btnClean.setFont(new Font("Arial", 18));
        btnClean.setPrefSize(100, 50);

        hBox2.getChildren().addAll(btnAnalyse, btnClean);

        lblEntrada = new Label("Entrada");
        lblEntrada.setAlignment(Pos.BASELINE_LEFT);
        lblEntrada.setFont(new Font("Arial Black", 20));
        lblEntrada.setTextFill(Color.web("Purple"));

        lblSaida = new Label("Saida");
        lblSaida.setAlignment(Pos.BASELINE_RIGHT);
        lblSaida.setFont(new Font("Arial Black", 20));
        lblSaida.setTextFill(Color.web("Purple"));

        hBox3.getChildren().addAll(lblEntrada, lblSaida);

        textEnter = new TextArea();
        textEnter.setPrefSize(450, 300);
        textEnter.setStyle("-fx-font-size: 15");

        textOut = new TextArea();
        textOut.setPrefSize(450, 300);
        textOut.setStyle("-fx-font-size: 15");

        hBox4.getChildren().addAll(textEnter, textOut);

        vBox.getChildren().addAll(lblTitle, hBox2, hBox3, hBox4);
        
        return vBox;
    }

    public VBox createParserPane() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(20);

        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(650);

        Label lblTitle = new Label("Analise Sintatica");
        lblTitle.setFont(new Font("Arial Black", 20));
        lblTitle.setTextFill(Color.web("#E15E5E"));

        btnParser = new Button("Analisar");
        btnParser.setFont(new Font("Arial", 18));
        btnParser.setTextFill(Color.web("purple"));
        btnParser.setPrefSize(100, 50);

        Button btnClean = new Button("Limpar");
        btnClean.setFont(new Font("Arial", 18));
        btnClean.setPrefSize(100, 50);

        hBox1.getChildren().addAll(btnParser, btnClean);

        textParserOut = new TextArea();
        textParserOut.setEditable(false);
        textParserOut.setPrefSize(800, 300);
        textParserOut.setStyle("-fx-font-size: 15");

        hBox2.getChildren().add(textParserOut);

        vBox.setSpacing(10);
        vBox.getChildren().addAll(lblTitle, hBox1, hBox2);

        return vBox;
    }
}
