package serum;

import java_cup.runtime.*;
import java.io.Reader;
import java.io.FileReader;
import java.util.*;

/**
 * Especificacion del analizador lexico de SeRuM
 */
%%

%class AnalizadorLexico
%unicode
%cup
%line
%column

%{
  //StringBuffer string = new StringBuffer();

  Stack<Integer> indentation = new Stack<Integer>();
  {
    indentation.push(0);
  }

  Map<String, Integer> keywords = new HashMap<>();
  {
    //Types
    keywords.put("int",  sym.T_INT);
    keywords.put("bool", sym.T_BOOL);

    // Control
    keywords.put("if",    sym.IF);
    keywords.put("else",  sym.ELSE);
    keywords.put("while", sym.WHILE);

    // bool ops
    keywords.put("and", sym.AND_OP);
    keywords.put("or",  sym.OR_OP);
    keywords.put("not", sym.NOT_OP);
  }

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }

  public static void main(String[] args){

    try {
        AnalizadorLexico alex = new AnalizadorLexico(new FileReader(args[0]));
        Symbol result = alex.next_token();

        do{
            //Logger.log.println(result.class);
            result = alex.next_token();
        } while (result.sym != 0);

    } catch (Exception ex){
        ex.printStackTrace();
    }
  }
%}

%eofval{
    /*Puede que al terminar un fichero nos hayamos dejado algúnos bloques sin cerrar.
    En ese caso hay que cerrarlos con el EOF.*/
    Logger.log.println ("The block stack is " + indentation.toString());

    if (indentation.peek()>0) { //Si no estamos en el bloque inicial lo cerramos:
        Logger.log.println(" } ");
        indentation.pop();
        //Queremos volver a reconocer el EOF por si hay que cerrar más bloques:
        yypushback(yylength());
        return symbol(sym.END_BLOCK);
    }
    return new java_cup.runtime.Symbol(sym.EOF);
%eofval}

/******************************************
             Macro declarations
*******************************************/

LineTerminator = \r|\n|\r\n
WhiteSpace     = [ \t\f]
Newline        = {LineTerminator} {WhiteSpace}*
EmptyLine      = {LineTerminator} {WhiteSpace}* {LineTerminator}

/* Instruction separators */
InstructionSplit = "\\" {WhiteSpace}* {LineTerminator} {WhiteSpace}*

/* Variables and constants */

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*
BoolLiteral = "True" | "False"

/* comments */

Comment = {BlockComent} | {EndOfLineComment}

//Como no tenemos comentarios para documentar podemos sustituir TraditionalComment por:
 BlockComent = "/*" ~"*/"

EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?

InputCharacter = [^\r\n]
CommentContent = ( [^*] | \*+ [^/*] )*

%state NEWLINE

%%
/* This code will be executed each time `yylex` is called, before
 * any generated code.
 */

//2int se reconoce como intLiteral + int token. Esto no tiene sentido


<YYINITIAL> {
 
  /* literals */
  {DecIntegerLiteral}            { Logger.log.println(" intLiteral ");  return symbol(sym.INTEGER_LITERAL, yytext());}
  {BoolLiteral}                  { Logger.log.println(" boolLiteral "); return symbol(sym.BOOL_LITERAL,    yytext());}

  /* operators */
  "="                            { Logger.log.println(" = "); return symbol(sym.ASSIGN_OP); }

  "=="                           { Logger.log.println(" == "); return symbol(sym.EQ_OP); }
  "<"                            { Logger.log.println(" < "); return symbol(sym.LT_OP);   }
  ">"                            { Logger.log.println(" > "); return symbol(sym.GT_OP);   }
  "<="                           { Logger.log.println(" <= "); return symbol(sym.LET_OP);  }
  ">="                           { Logger.log.println(" >= "); return symbol(sym.GET_OP);  }

  "+"                            { Logger.log.println(" + "); return symbol(sym.SUM_OP);  }
  "-"                            { Logger.log.println(" - "); return symbol(sym.SUBS_OP); }
  "*"                            { Logger.log.println(" * "); return symbol(sym.PROD_OP);  }
  "/"                            { Logger.log.println(" / "); return symbol(sym.DIV_OP);   }

  /* misc */
  "["                            { Logger.log.println(" [ "); return symbol(sym.LBRACKET); }
  "]"                            { Logger.log.println(" ] "); return symbol(sym.RBRACKET); }

  /* identifiers */ 
  {Identifier}                   
  { 
    // If the identifier is a recognized keyword, we emit a keyword token
    if(keywords.containsKey(yytext())){
        Logger.log.println(" " + yytext() + " ");
        return symbol(keywords.get(yytext()));
    } else {
        Logger.log.println(" id:" + yytext());
        return symbol(sym.IDENTIFIER, yytext());
    }
  }

  /* instruction separators */
  ";"                            { /* ignore */   }
  "{"                            { Logger.log.println(" { "); return symbol(sym.START_BLOCK); }
  "}"                            { Logger.log.println(" } "); return symbol(sym.END_BLOCK);   }

  {EmptyLine}           { /* Empty lines are deleted*/ yypushback(1);}

  {LineTerminator}      {
                            yybegin(NEWLINE);

                            // We undo the matching of newline to handle the case where there are no spaces in the next line
                            yypushback(yylength());

                        }

  {InstructionSplit}             { /* ignore */ }

  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

<NEWLINE> {
  {Newline} {
        // This should match the empty string!
        // Consumes all the white space in front of a newline,
        // and determines if we need to open or close a block

        Logger.log.println ("The block stack is " + indentation.toString());

        int actual_column = yylength() - 1;

        if (actual_column > indentation.peek()) {
            indentation.push(actual_column);
            Logger.log.println(" { ");
            yybegin(YYINITIAL);
            return symbol(sym.START_BLOCK);
        }

        else if (actual_column < indentation.peek()) {
            indentation.pop();
            Logger.log.println(" } ");
            yypushback(yylength()); // Undo the matching
            return symbol(sym.END_BLOCK);
        }

        else if (actual_column == indentation.peek()){
            yybegin(YYINITIAL);
        }
  }
}

/* error fallback */
[^]                              { throw new Error("Illegal character <"+
                                                    yytext()+">"); }