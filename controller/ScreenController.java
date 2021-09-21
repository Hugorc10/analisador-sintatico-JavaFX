package controller;

import analisador.Lexer;
import componentes.Tokens;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import view.ScreenView;

import java.io.IOException;
import java.io.StringReader;

public class ScreenController {
//    ScreenView screenView;
    
    public void handler() {
        // acao do botao analisar
        ScreenView.btnAnalyse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    
        // acao do botao limpar
        ScreenView.btnClean.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cleanInput();
            }
        });
    }
    
    public void execute() throws IOException {
        int cont = 1;
        
        String expr;
        if (ScreenView.textEnter.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "A campo nao pode estar vazio.");
            alert.show();
            
            return;
        }
        expr = ScreenView.textEnter.getText(); // recebe o que foi digitado
        Lexer lexer = new Lexer(new StringReader(expr));
        
        String result = "";
        
        while (true) {
            Tokens tokens = lexer.yylex();
            if (tokens == null) {
                ScreenView.textOut.setText(result);
                
                return;
            }
            switch (tokens) {
                case PALAVRA:
                    result = result + "Linha: " + cont + "<PALAVRA_RESERVADA> " + lexer.lexeme + "\n";
                    break;
                case OP_ATRIBUICAO:
                    result = result + "Linha: " + cont + "<OPERADOR_ATRIBUICAO> " + lexer.lexeme + "\n";
                    break;
                case OPERADOR_ARITMETICO:
                    result = result + "Linha: " + cont + "<OPERADOR_ARITMETICO> " + lexer.lexeme + "\n";
                    break;
                case OP_RELACIONAL:
                    result = result + "Linha: " + cont + "<OPERADOR_RELACIONAL> " + lexer.lexeme + "\n";
                    break;
                case OP_LOGICO:
                    result = result + "Linha: " + cont + "<OPERADOR_LOGICO> " + lexer.lexeme + "\n";
                    break;
                case SIMBOLO_ESPECIAL:
                    result = result + "Linha: " + cont + "<SIMBOLO_ESPECIAL> " + lexer.lexeme + "\n";
                    break;
                case IDENTIFICADOR:
                    result = result + "Linha: " + cont + "<IDENTIFICADOR> " + lexer.lexeme + "\n";
                    break;
                case STRING_LITERAL:
                    result = result + "Linha: " + cont + "<STRING_LITERAL> " + lexer.lexeme + "\n";
                    break;
                case ERROR:
                    result = result + "Erro Lexico na linha: " + cont + ". SIMBOLO " + lexer.yytext() + " NAO RECONHECIDO\n";
                    break;
                case NUMERO:
                    result = result + "Linha: " + cont + "<NUMERO> " + lexer.lexeme + "\n";
                    break;
                case NUMEROREAL:
                    result = result + "Linha: " + cont + "<NUMERO_REAL> " + lexer.lexeme + "\n";
                    break;
                case INICIO:
                    result = result + "Linha: " + cont + "<INICIO_ALGORITMO> " + lexer.lexeme + "\n";
                    break;
                case INICIO_BLOCO:
                    result = result + "Linha: " + cont + "<INICIO_BLOCO> " + lexer.lexeme + "\n";
                    break;
                case FIM:
                    result = result + "Linha: " + cont + "<FIM_ALGORITMO> " + lexer.lexeme + "\n";
                    break;
                case FIM_BLOCO:
                    result = result + "Linha: " + cont + "<FIM_BLOCO> " + lexer.lexeme + "\n";
                    break;
                case LEIA:
                    result = result + "Linha: " + cont + "<LEIA_COMANDO> " + lexer.lexeme + "\n";
                    break;
                case ESCREVA:
                    result = result + "Linha: " + cont + "<ESCREVA_COMANDO> " + lexer.lexeme + "\n";
                    break;
                case ESCREVALN:
                    result = result + "Linha: " + cont + "<ESCREVALN_COMANDO> " + lexer.lexeme + "\n";
                    break;
                case LINHA:
                    cont++;
                    break;
                default:
                    result = result + "Linha: " + cont + "<" + lexer.lexeme + ">" + cont++;
                    break;
            }
        }
    }
    
    public void cleanInput() {
        ScreenView.textEnter.setText("");
        ScreenView.textOut.setText("");
    }
}
