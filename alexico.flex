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
            //System.out.println(result.class);
            result = alex.next_token();
        } while (result.sym != 0);

    } catch (Exception ex){
        ex.printStackTrace();
    }
  }
%}

/*%eofval{
  return new java_cup.runtime.Symbol(<CUPSYM>.EOF);
%eofval} */

/******************************************
             Macro declarations
*******************************************/

LineTerminator = \r|\n|\r\n
WhiteSpace     = [ \t\f]
Newline 	   = {LineTerminator} {WhiteSpace}*
EmptyLine	   = {LineTerminator} {WhiteSpace}* {LineTerminator}

/* Instruction separators */
InstructionSplit = "\\" {WhiteSpace}* {LineTerminator} {WhiteSpace}*

/* Variables and constants */

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*
BoolLiteral = "True" | "False"

/* comments */

Comment = {TraditionalComment} | {EndOfLineComment}

// TODO Como no tenemos comentarios para documentar podemos sustituir TraditionalComment por:
// BlockComent = "/*" ~"*/""
TraditionalComment   = "/*" [^*] ~"*/" {LineTerminator} | "/*" "*"+ "/" {LineTerminator}
// TODO Puede que haga falta poner un interrogante al final de la siguiente linea por si es la última
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}

InputCharacter = [^\r\n]
CommentContent = ( [^*] | \*+ [^/*] )*

%state NEWLINE

%%
/* This code will be executed each time `yylex` is called, before
 * any generated code.
 */

//TODO: 2int se reconoce incorrectamente como intLiteral + int token


<YYINITIAL> {
 
  /* literals */
  {DecIntegerLiteral}            { System.out.println(" intLiteral ");  return symbol(sym.INTEGER_LITERAL, yytext());}
  {BoolLiteral}                  { System.out.println(" boolLiteral "); return symbol(sym.BOOL_LITERAL,    yytext());}

  /* operators */
  "="                            { System.out.println(" = "); return symbol(sym.ASSIGN_OP); }

  "=="                           { System.out.println(" == "); return symbol(sym.EQ_OP); }
  "<"                            { System.out.println(" < "); return symbol(sym.LT_OP);   }
  ">"                            { System.out.println(" > "); return symbol(sym.GT_OP);   }
  "<="                           { System.out.println(" <= "); return symbol(sym.LET_OP);  }
  ">="                           { System.out.println(" >= "); return symbol(sym.GET_OP);  }

  "+"                            { System.out.println(" + "); return symbol(sym.SUM_OP);  }
  "-"                            { System.out.println(" - "); return symbol(sym.SUBS_OP); }
  "*"                            { System.out.println(" * "); return symbol(sym.PROD_OP);  }
  "/"                            { System.out.println(" / "); return symbol(sym.DIV_OP);   }

  /* misc */
  "["                            { System.out.println(" [ "); return symbol(sym.LBRACKET); }
  "]"                            { System.out.println(" ] "); return symbol(sym.RBRACKET); }

  /* identifiers */ 
  {Identifier}                   
  { 
    // If the identifier is a recognized keyword, we emit a keyword token
    if(keywords.containsKey(yytext())){
        System.out.println(" " + yytext() + " ");
        return symbol(keywords.get(yytext()));
    } else {
        System.out.println(" id:" + yytext());
        return symbol(sym.IDENTIFIER, yytext());
    }
  }

  /* instruction separators */
// TODO ¿Porque ignoramos los ;?
  ";"                            { /* ignore */   }
  "{"                            { System.out.println(" { "); return symbol(sym.START_BLOCK); }
  "}"                            { System.out.println(" } "); return symbol(sym.END_BLOCK);   }

  {EmptyLine}			{ /* Empty lines are deleted*/ yypushback(1);}

  {LineTerminator}      { 	   
                            yybegin(NEWLINE);

                            // We undo the matching of newline to handle the case where there are no spaces in the next line
                            yypushback(yylength());

                        }

  {InstructionSplit}		 	 { /* ignore */ }

  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

<NEWLINE> {
  {Newline} { // This should match the empty string!
        //System.out.println("I have consumed your delicious whitespace");
        // Consumes all the white space in front of a newline,
        // and determines if we need to open or close a block

        //System.out.println ("The stack is " + indentation.toString());

        int actual_column = yylength() - 1;

        if (actual_column > indentation.peek()) {
            indentation.push(actual_column);
            System.out.println(" { ");
            yybegin(YYINITIAL);
            return symbol(sym.START_BLOCK);
        }

        else if (actual_column < indentation.peek()) {
            indentation.pop();
            System.out.println(" } ");
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