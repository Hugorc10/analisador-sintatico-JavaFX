package lexer;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Generator {
    
    public static void main(String[] args) {
        try {
            // chama o metodo "geraLexer"
            geraLexer("/lexer/Lexer.flex");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public static void geraLexer(String subPath) throws URISyntaxException {
        // pega o caminho raiz do arquivo
        String rootPath = Paths.get("").toAbsolutePath().toString();
        
        String file = rootPath + subPath;
        File sourceCode = new File(file); // abre o arquivo "Lexer.flex"
        // apenas descomente este codigo se a biblioteca JFlex estiver no projeto
        JFlex.Main.generate(sourceCode); // gera a classe "Lexer.java"
    }
}
