package myparser;

import java_cup.runtime.*;

%%

%class analisador.LexerParser
%line
%column
%cup
%{
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */

    private Symbol symbol(int type) {
        return new LEXSymbol(type, yyline, yycolumn,yytext());
    }

    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */

    private Symbol symbol(int type, Object value) {
        return new LEXSymbol(type, yyline, yycolumn, value, yytext());
    }
%}


/* DEFINIR AS OUTRAS EXPRESSOES REGULARES */
digit=[0-9]
letter=[A-Za-z]
alphanumeric={letter}|{digit}
other_id_char=[_]
identifier=({letter}|{other_id_char})({alphanumeric}|{other_id_char})*
int={digit}+
leftbar=[/][*]
rightbar=[*][/]
leftbrace=[{]
rightbrace=[}]
nonrightbrace=[^}]
nonrightbrace2=([^*]|[*][^/])
comment_body={nonrightbrace}*
comment_body2={nonrightbrace2}*
commnetbrace={leftbrace}{comment_body}{rightbrace}
commnetbar={leftbar}{comment_body2}{rightbar}
whitespace=[ \r\n\t]
string=[\"][^\"\n]*[\"]
invalid=({digit}+({letter}|[_]|[!]|[@]|[#]|[$]|[%]|[&]|[(]|[\[]|[\]]|[\\])[^\r\s\t\n]*|[\"][^\n\"]*[\n])

%%

<YYINITIAL> {

    /* Print the token found that was declared in the class sym and then
       return it. */

    "_"                { return symbol(sym.UNDER); }
    "."                { return symbol(sym.PONT); }
    ","                { return symbol(sym.VIRG); }
    ";"                { return symbol(sym.PVIR); }
    ":"                { return symbol(sym.DPON); }
    "("                { return symbol(sym.APAR); }
    ")"                { return symbol(sym.FPAR); }
    "["                { return symbol(sym.ACOL); }
    "]"                { return symbol(sym.FCOL); }
    "{"                { return symbol(sym.ACHA); }
    "}"                { return symbol(sym.FCHA); }
    "+"                { return symbol(sym.MAIS); }
    "-"                { return symbol(sym.MENOS); }
    "\""                { return symbol(sym.ASPA); }
    "\'"                { return symbol(sym.APOS); }
    "\\"                { return symbol(sym.BARR); }
    "|"                { return symbol(sym.PIPE); }
    "!"                { return symbol(sym.EXCL); }
    "?"                { return symbol(sym.PERG); }
    ">"                { return symbol(sym.MAIOR); }
    "<"                { return symbol(sym.MENOR); }
    "="                { return symbol(sym.IGUAL); }
    "*"                { return symbol(sym.MULT); }
    "\/"                { return symbol(sym.DIV); }
    "begin"              { return symbol(sym.BEGIN);}
    "end"              { return symbol(sym.END);}
    "byte"              { return symbol(sym.TIPO);}
    "string"              { return symbol(sym.TIPO);}
    "while"              { return symbol(sym.WHILE);}
    "if"              { return symbol(sym.IF);}
    "else"              { return symbol(sym.ELSE);}
    "and"              { return symbol(sym.AND);}
    "or"              { return symbol(sym.OR);}
    "not"              { return symbol(sym.NOT);}
    "=="              { return symbol(sym.IGUALI);}
    "<>"              { return symbol(sym.DIFE);}
    "<="              { return symbol(sym.MAIORQ);}
    ">="              { return symbol(sym.MENORQ);}
    "readln"              { return symbol(sym.READLN);}
    "write"              { return symbol(sym.WRITE);}
    "writeln"              { return symbol(sym.WRITELN);}
    "true"              { return symbol(sym.BOOLEAN);}
    "false"              { return symbol(sym.BOOLEAN);}
    "boolean"              { return symbol(sym.TIPO);}
    "final"              { return symbol(sym.FINAL);}
    "int"              { return symbol(sym.TIPO);}


/* TOKENS DEFINIDOS PELAS EXPRESSOS REGULARES */

{int} { return symbol(sym.INT, new Integer(yytext())); }
{string} { return symbol(sym.STRING, new String(yytext())); }
{commnetbar} {/* Comments */ }
{commnetbrace} { /* Comments. */ }
{whitespace} {/* Ignore whitespace */; }
{identifier} { return symbol(sym.IDEN, new String(yytext()));}
{invalid} { throw new Error(" caracter invalido, linha: "+ yyline +" <"+yytext()+">"); }

}