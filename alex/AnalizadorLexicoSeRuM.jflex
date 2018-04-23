

/**
 * This class is a simple example lexer.
 */
%%

%class AnalizadorLexicoSeRuM
%unicode
%cup
%line
%column

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*
BoolLiteral = True | False

%state STRING

%%

/* keywords */
<YYINITIAL> "if"                 { return symbol(sym.IF);    }
<YYINITIAL> "else"               { return symbol(sym.ELSE);  }
<YYINITIAL> "while"              { return symbol(sym.WHILE); }

/* types */
<YYINITIAL> "int"                { return symbol(sym.INT);  }
<YYINITIAL> "bool"               { return symbol(sym.BOOL); }
<YYINITIAL> "[]"                 { return symbol(sym.T_ARRAY)}

<YYINITIAL> {
  /* identifiers */ 
  {Identifier}                   { return symbol(sym.IDENTIFIER); }
 
  /* literals */
  {DecIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL);   }
  {BoolLiteral}                  { return symbol(sym.BOOL_LITERAL);      }
  \"                             { string.setLength(0); yybegin(STRING); }

  /* operators */
  "="                            { return symbol(sym.EQ); }

  "=="                           { return symbol(sym.EQEQ); }
  "<"                            { return symbol(sym.LT);   }
  ">"                            { return symbol(sym.GT);   }
  "<="                           { return symbol(sym.LET);  }
  ">="                           { return symbol(sym.GET);  }

  "+"                            { return symbol(sym.PLUS);  }
  "-"                            { return symbol(sym.MINUS); }
  "*"                            { return symbol(sym.PROD);  }
  "/"                            { return symbol(sym.DIV);   }

  "and"                          { return symbol(sym.AND); }
  "or"                           { return symbol(sym.OR);  }
  "not"                          { return symbol(sym.NOT); }

  /* misc */
  "["                            { return symbol(sym.LBRACKET); }
  "]"                            { return symbol(sym.RBRACKET); }
  "{"                            { return symbol(sym.LBRACE); }
  "}"                            { return symbol(sym.RBRACE); }


  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return symbol(sym.STRING_LITERAL, 
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}

/* error fallback */
[^]                              { throw new Error("Illegal character <"+
                                                    yytext()+">"); }