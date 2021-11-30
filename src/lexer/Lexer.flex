package lexer;

import static componentes.Tokens.*;

%%
%class Lexer
%type Tokens

L = [a-zA-Z_]+
D = [0-9]+
//WHITE = [\t| |\r]+
WHITE = [ ,\t,\r]+
%{
    public String lexeme;
%}
%%

{WHITE} { /*Ignore*/ }

/* Pular linha */
( "\n" ) { lexeme = yytext(); return LINHA; }

/* Operador Aritmetico */
( "+" | "-" | "*" | "/" | "mod" ) { lexeme = yytext(); return OPERADOR_ARITMETICO; }

/*Operador Logico */
( and | or | not ) { lexeme = yytext(); return OP_LOGICO; }

/* Operadores Relacionais */
("=" | ">=" | ">" | "<" | "<=" | "<>") { lexeme = yytext(); return OP_RELACIONAL; }

/* Atribuicao */
( ":=" ) { lexeme = yytext(); return OP_ATRIBUICAO; }

/* Simbolos Especiais */
( "(" | ")" | "," | ";" | ":" ) { lexeme = yytext(); return SIMBOLO_ESPECIAL; }

/* Comentarios */
( "/*".* | .*"*/" ) { /*Ignore*/ }

/* String */
(\"(.[^\"]*)\") { lexeme = yytext(); return STRING_LITERAL; }

/* Marcador de inicio do algoritmo */
( "program" ) { lexeme = yytext(); return INICIO; }

/* Marcador de inicio do bloco */
( "begin" ) {lexeme = yytext(); return INICIO_BLOCO;}

/* Marcador de fim do algoritmo */
( "." ) { lexeme = yytext(); return FIM; }

/* Marcador de fim do bloco */
( "end" ) { lexeme = yytext(); return FIM_BLOCO; }

/* Identificar a leitura na tela */
( "read" ) { lexeme = yytext(); return LEIA; }

/* Identificar a leitura na tela */
( "write" ) { lexeme = yytext(); return ESCREVA; }

/* Identificar a leitura na tela */
( "writeln" ) { lexeme = yytext(); return ESCREVALN; }

/* Palavras reservadas da linguagem */
( abosolute | array | case | char | const | div | do | dowto | else
| external | file | for | forward | func | function | goto | if | implementation
| interface | interrupt | label | main | nil | nit | of | packed | proc
| real | record | repeat | set | shl | shr | string | then | to | type | unit
| until | uses | var | while | with | xor ) { lexeme = yytext(); return PALAVRA; }

/* Padrao para o identificador */
{L}({L}|{D})* { lexeme = yytext(); return IDENTIFICADOR; }

/* Padrao para identificar o numero */
("(-"{D}+")")|{D}+ { lexeme = yytext(); return NUMERO; }

/* Padrao para identificar o numero real */
[0-9]+"."[0-9]*|[0-9]*"."[0-9]+ { lexeme = yytext(); return NUMEROREAL; }

/* Erro */
. { return ERROR; }

