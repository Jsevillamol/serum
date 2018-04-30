package serum;

/**
 * Especificacion del analizador lexico de SeRuM
 */
%%

%class AnalizadorLexicoSeRuM
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
InputCharacter = [^\r\n]

/* Instruction separators */
NewInstruction 	   = LineTerminator {WhiteSpace}*
InstructionBreak = "\\" {WhiteSpace}* LineTerminator {WhiteSpace}*

/* Variables and constants */

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*
BoolLiteral = "True" | "False"

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

// %state STRING

%%

<YYINITIAL> {
  /* types */
  "int"                { return symbol(sym.T_INT);  }
  "bool"               { return symbol(sym.T_BOOL); }
  "[]"                 { return symbol(sym.T_ARRAY);}
  
  /* keywords */
  "if"                 { return symbol(sym.IF);    }
  "then"			   { return symbol(sym.THEN);  }
  "else"               { return symbol(sym.ELSE);  }
  "while"              { return symbol(sym.WHILE); }
 
  /* literals */
  {DecIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL, yytext);   }
  {BoolLiteral}                  { return symbol(sym.BOOL_LITERAL, yytext);      }

  //\"                             { string.setLength(0); yybegin(STRING); }

  /* operators */
  "="                            { return symbol(sym.ASSIGN_OP); }

  "=="                           { return symbol(sym.EQ_OP); }
  "<"                            { return symbol(sym.LT_OP);   }
  ">"                            { return symbol(sym.GT_OP);   }
  "<="                           { return symbol(sym.LET_OP);  }
  ">="                           { return symbol(sym.GET_OP);  }

  "+"                            { return symbol(sym.SUM_OP);  }
  "-"                            { return symbol(sym.SUBS_OP); }
  "*"                            { return symbol(sym.PROD_OP);  }
  "/"                            { return symbol(sym.DIV_OP);   }

  "and"                          { return symbol(sym.AND_OP); }
  "or"                           { return symbol(sym.OR_OP);  }
  "not"                          { return symbol(sym.NOT_OP); }

  /* misc */
  "["                            { return symbol(sym.LBRACKET); }
  "]"                            { return symbol(sym.RBRACKET); }

  /* identifiers */ 
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext); }

  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* instruction separators */

  ";"							 { return symbol(sym.SEPARATOR);   }
  "{"                            { return symbol(sym.START_BLOCK); }
  "}"                            { return symbol(sym.END_BLOCK);   }

  {NewInstruction}				 { 	
  									int aux = last_column;
  									last_column = yycolumn;
  									if (yycolumn == aux) 
  										return symbol(sym.SEPARATOR);
  								   	else if(yycolumn > aux) 
  								   		return symbol(sym.START_BLOCK);
  								   	else // if actual_column < aux
  								   		return symbol(sym.END_BLOCK);

  								 }

  {InstructionBreak}		 	 { /* ignore */ }
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