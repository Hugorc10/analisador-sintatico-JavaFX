package myparser;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class GeneratorParser {
    
    public static void main(String[] args) {
        try {
            // chama o metodo "geraLexer"
            geraParser("/myparser/LexerCup.flex");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public static void geraParser(String subPath) throws URISyntaxException {
        // java -jar java-cup-11a.jar -analisador.parser Parser -symbols Sym Parser.cup
        
        // pega o caminho raiz do arquivo
        String rootPath = Paths.get("").toAbsolutePath().toString();
        
        String file = rootPath + subPath;
        File sourceCode = new File(file); // abre o arquivo "Lexer.flex"
        // apenas descomente este codigo se a biblioteca JFlex estiver no projeto
        jflex.Main.generate(sourceCode); // gera a classe "Lexer.java"
    }
}
