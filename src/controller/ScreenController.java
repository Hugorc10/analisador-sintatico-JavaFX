package controller;

import analisador.Lexer;
import analisador.LexerParser;
import analisador.parser;
import java_cup.runtime.Symbol;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import view.ScreenView;
import componentes.Tokens;

import java.io.*;

public class ScreenController {
    
    String[] my_args;
    
    /**
     * ver os passos do parsing
     */
    static boolean do_debug_parse = false;
//    ScreenView screenView;
    
    public ScreenController() {
        try {
            my_args = new String[2];
            my_args[0] = "testes/t6.u";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handler() {
        // acao do botao analisar
        ScreenView.btnAnalyse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    executeLexer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        ScreenView.btnParser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    executeParser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        // acao do botao limpar
        ScreenView.btnCleanLexer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cleanInputAndOutput();
            }
        });
        
        ScreenView.btnCleanParser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cleanOutputParser();
            }
        });
    }
    
    public void executeLexer() throws IOException {
        int cont = 1;
        
        String expr;
        if (ScreenView.textEnter.getText().isEmpty()) {
            // gera um alerta informando que o campo nao pode estar vazio
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
    
    public void executeParser() {
        String inicio = "Compilando [" + my_args[0] + "]";
        ScreenView.textParserOut.setText("");
        ScreenView.textParserOut.setText(inicio + "\n");
        System.out.println("Compilando [" + my_args[0] + "]");
        //Faz a leitura do arquivo de entrada
        FileReader reader = null;
        try {
            reader = new FileReader(new File(my_args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Cria uma instância do Lexer passando o arquivo como atributo
        LexerParser mylexer = new LexerParser(reader);
        //Cria uma instância do Parser passando o Lexer juntamente com o nome do arquivo de entrada
        parser my_parser = new parser(mylexer, my_args[0]);
        Symbol top = null;
        
        if (do_debug_parse) {
            try {
                top = my_parser.debug_parse();
            } catch (Exception e) {
                ScreenView.textParserOut.setText("O analisador encontrou erro.");
                ScreenView.textParserOut.appendText("Analise completa, olhe seu terminal.");
                System.out.println("Analisador sintatico encontrou erro: ");
            }
        } else {
            try {
                top = my_parser.parse();
            } catch (Exception e) {
//                ScreenView.textParserOut.appendText(top.toString() + "\n");
                ScreenView.textParserOut.appendText("O analisador encontrou erro.\n");
                ScreenView.textParserOut.appendText("Analise completa, olhe seu terminal.");
                System.out.println("Analisador sintatico encontrou erro: ");
            }
            
        }
        
        //Para se houver alguma falha no parsing
        if (my_parser.hasFailed()) {
            return;
        }
        
        String concluido = "Parsing Concluido. Simbolo do topo = " + top.sym;
        ScreenView.textParserOut.appendText(concluido);
        System.out.println("Parsing Concluido. Simbolo do topo = " + top.sym);
    }
    
    public void cleanInputAndOutput() {
        ScreenView.textEnter.setText("");
        ScreenView.textOut.setText("");
    }
    
    public void cleanOutputParser() {
        ScreenView.textParserOut.setText("");
    }
}
