package alex;

public class ALexOperations {
  private AnalizadorLexicoSeRuM alex;
  public ALexOperations(AnalizadorLexicoSeRuM alex) {
   this.alex = alex;   
  }

  public UnidadLexica unidadFinInstruccion() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.SEP); 
  } 

  public UnidadLexica unidadId() {
     return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.IDEN,
                                         alex.lexema()); 
  } 

  public UnidadLexica unidadEnt() {
     return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ENT,alex.lexema()); 
  } 
  public UnidadLexica unidadReal() {
     return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.REAL,alex.lexema()); 
  } 

  //Operadores aritmeticos
  public UnidadLexica unidadSuma() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAS); 
  } 
  public UnidadLexica unidadResta() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOS); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.POR); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DIV); 
  } 

  // Aux
  public UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PAP); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PCIERRE); 
  } 
  public UnidadLexica unidadCAp() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.CAP); 
  } 
  public UnidadLexica unidadCCierre() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.CCIERRE); 
  } 
  public UnidadLexica unidadLAp() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.LAP); 
  } 
  public UnidadLexica unidadLCierre() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.LCIERRE); 
  } 

  public UnidadLexica unidadIgual() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.IGUAL); 
  } 
  public UnidadLexica unidadComa() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.COMA); 
  } 

  //keywords
  public UnidadLexica unidadWhile() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.WHILE); 
  }
  public UnidadLexica unidadIf() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.IF); 
  }
  public UnidadLexica unidadElse() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.ELSE); 
  }

  //boolean operators
  public UnidadLexica unidadNot() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NOT); 
  }
  public UnidadLexica unidadAnd() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.AND); 
  }
  public UnidadLexica unidadOr() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.OR); 
  }

  //boolean constants
  public UnidadLexica unidadTrue() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.TRUE); 
  }
  public UnidadLexica unidadFalse() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.FALSE); 
  }

  //types
  public UnidadLexica unidadInt() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.T_INT); 
  }
  public UnidadLexica unidadBool() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.T_BOOL); 
  }

  public UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.EOF); 
  }
  public void error() {
    System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
  }
}
