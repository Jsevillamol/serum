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
  	keywords.put("int", sym.T_INT);
  	keywords.put("bool", sym.T_BOOL);

  	// Control
  	keywords.put("if", sym.IF);
  	keywords.put("else", sym.ELSE);
  	keywords.put("while", sym.WHILE);

  	// bool ops
  	keywords.put("and", sym.AND_OP);
  	keywords.put("or", sym.OR_OP);
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
  		Object result = alex.next_token();
  		do{
  			//System.out.print(result.toString());
  			result = alex.next_token();
  		} while (true);

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

TraditionalComment   = "/*" [^*] ~"*/" {LineTerminator}? | "/*" "*"+ "/" {LineTerminator}?
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?

InputCharacter = [^\r\n]
CommentContent = ( [^*] | \*+ [^/*] )*

%state NEWLINE
// %state STRING

%%
/* This code will be executed each time `yylex` is called, before
   * any generated code.
*/

//TODO: 2int se reconoce incorrectamente como intLiteral + int token


<YYINITIAL> {
  /* types */
  "[]"                 { System.out.print(" [] "); return symbol(sym.T_ARRAY);}
 
  /* literals */
  {DecIntegerLiteral}            { System.out.print(" intLiteral ");  return symbol(sym.INTEGER_LITERAL, yytext());}
  {BoolLiteral}                  { System.out.print(" boolLiteral "); return symbol(sym.BOOL_LITERAL,    yytext());}

  //\"                           { string.setLength(0); yybegin(STRING); }

  /* operators */
  "="                            { System.out.print(" = "); return symbol(sym.ASSIGN_OP); }

  "=="                           { System.out.print(" == "); return symbol(sym.EQ_OP); }
  "<"                            { System.out.print(" < "); return symbol(sym.LT_OP);   }
  ">"                            { System.out.print(" > "); return symbol(sym.GT_OP);   }
  "<="                           { System.out.print(" <= "); return symbol(sym.LET_OP);  }
  ">="                           { System.out.print(" >= "); return symbol(sym.GET_OP);  }

  "+"                            { System.out.print(" + "); return symbol(sym.SUM_OP);  }
  "-"                            { System.out.print(" - "); return symbol(sym.SUBS_OP); }
  "*"                            { System.out.print(" * "); return symbol(sym.PROD_OP);  }
  "/"                            { System.out.print(" / "); return symbol(sym.DIV_OP);   }

  /* misc */
  "["                            { System.out.print(" [ "); return symbol(sym.LBRACKET); }
  "]"                            { System.out.print(" ] "); return symbol(sym.RBRACKET); }

  /* identifiers */ 
  {Identifier}                   
  { 
  	// If the identifier is a recognized keyword, we emit a keyword token
  	if(keywords.containsKey(yytext())){
  		System.out.print(" " + yytext() + " ");
  	 	return symbol(keywords.get(yytext()));
  	} else {
  		System.out.print(" id:" + yytext());
  		return symbol(sym.IDENTIFIER, yytext()); 
  	}
  }

  /* instruction separators */

  ";"                            { System.out.print(" ; "); return symbol(sym.SEPARATOR);   }
  "{"                            { System.out.print(" { "); return symbol(sym.START_BLOCK); }
  "}"                            { System.out.print(" } "); return symbol(sym.END_BLOCK);   }

  {EmptyLine}			{ /* Empty lines are deleted*/ yypushback(1);}

  {LineTerminator}      { 	   
                            yybegin(NEWLINE);
                            System.out.print(" ;\n");
                            
                            // We undo the matching of newline to handle the case where there are no spaces in the next line
                            yypushback(yylength()); 

                            return symbol(sym.SEPARATOR);
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
  			System.out.print(" { ");
  			yybegin(YYINITIAL);
  			return symbol(sym.START_BLOCK);
  		} 

  		else if (actual_column < indentation.peek()) {
  			indentation.pop();
  			System.out.print(" } ");
  			yypushback(yylength()); // Undo the matching
  			return symbol(sym.END_BLOCK);
  		}

  		else if (actual_column == indentation.peek()){
  			yybegin(YYINITIAL);
  		}
  }
}

/*<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return symbol(sym.STRING_LITERAL, 
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}*/

/* error fallback */
[^]                              { throw new Error("Illegal character <"+
                                                    yytext()+">"); }