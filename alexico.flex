package serum;

import java_cup.runtime.*;
import java.io.Reader;

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

  int last_column = 0;

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace     = [ \t\f]
WhiteSpaceOrNothing = [ \t\f]*

/* Instruction separators */
//InstructionBreak = "\\" {WhiteSpace}* {LineTerminator} {WhiteSpace}*

/* Variables and constants */

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*
BoolLiteral = "True" | "False"

/* comments */

// Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

// TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// // Comment can be the last line of the file, without line terminator.
// EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
// DocumentationComment = "/**" {CommentContent} "*"+ "/"

// InputCharacter = [^\r\n]
// CommentContent       = ( [^*] | \*+ [^/*] )*

%state NEWLINE
// %state STRING

%%

<YYINITIAL> {
  /* types */
  "int"                { System.out.print(" int "); return symbol(sym.T_INT);  }
  "bool"               { System.out.print(" bool "); return symbol(sym.T_BOOL); }
  "[]"                 { System.out.print(" [] "); return symbol(sym.T_ARRAY);}
  
  /* keywords */
  "if"                 { System.out.print(" if "); return symbol(sym.IF);    }
  "else"               { System.out.print(" else "); return symbol(sym.ELSE);  }
  "while"              { System.out.print(" while "); return symbol(sym.WHILE); }
 
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

  "and"                          { System.out.print(" and "); return symbol(sym.AND_OP); }
  "or"                           { System.out.print(" or "); return symbol(sym.OR_OP);  }
  "not"                          { System.out.print(" not "); return symbol(sym.NOT_OP); }

  /* misc */
  "["                            { System.out.print(" [ "); return symbol(sym.LBRACKET); }
  "]"                            { System.out.print(" ] "); return symbol(sym.RBRACKET); }

  /* identifiers */ 
  {Identifier}                   { System.out.print(" id "); return symbol(sym.IDENTIFIER, yytext()); }

  /* instruction separators */

  ";"                            { System.out.print(" ; "); return symbol(sym.SEPARATOR);   }
  "{"                            { System.out.print(" { "); return symbol(sym.START_BLOCK); }
  "}"                            { System.out.print(" } "); return symbol(sym.END_BLOCK);   }

  {LineTerminator}      { 	   
                            yybegin(NEWLINE);
                            System.out.print(" ;\n");
                            return symbol(sym.SEPARATOR);
                        }

  //{InstructionBreak}		 	 { /* ignore */ }

  /* comments */
  // {Comment}                      { System.out.print(" /*comment*/ "); /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

<NEWLINE> {
  {WhiteSpace}* {
  		//System.out.println("I have consumed your delicious whitespace");
  		// Consumes all the white space in front of a newline, 
  		// and determines if we need to open or close a block
  		yybegin(YYINITIAL);

  		int actual_column = yylength();
  		int aux = last_column;
  		last_column = actual_column;

  		if (aux < actual_column) {
  			System.out.print(" { ");
  			return symbol(sym.START_BLOCK);
  		} else if (aux > actual_column) {
  			System.out.print(" } ");
  			return symbol(sym.END_BLOCK);
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