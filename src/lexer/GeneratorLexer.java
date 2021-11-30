package lexer;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class GeneratorLexer {
    
    public static void main(String[] args) {
        try {
            // chama o metodo "geraLexer"
            geraLexer("C:\\Users\\hugor\\Analisador-Lexico\\src\\lexer\\Lexer.flex");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public static void geraLexer(String subPath) throws URISyntaxException {
        // pega o caminho raiz do arquivo
        String rootPath = Paths.get("").toAbsolutePath().toString();
        
        String file = rootPath + subPath;
        File sourceCode = new File("C:\\Users\\hugor\\Analisador-Lexico\\src\\lexer\\Lexer.flex"); // abre o arquivo
        // apenas descomente este codigo se a biblioteca JFlex estiver no projeto
        jflex.Main.generate(sourceCode); // gera a classe "Lexer.java"
    }
}
