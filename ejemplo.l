package alex;

%%
%line
%class AnalizadorLexicoSeRuM
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digitoPositivo}{digito}*
parteDecimal = {digito}* {digitoPositivo}

separador = [ \t\r\b]
comentario = %[^\n]* 

finInstruccion = (\n|;)

identificador = {letra}({letra}|{digito})*

numeroEntero = [\+,\-]?{parteEntera}
numeroReal = [\+,\-]?{parteEntera}\.{parteDecimal}

operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = /

parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \]
llaveApertura = \{
llaveCierre = \}

igual = \=
coma  = \,

while = while
if = if
else = else

not = not
and = and
or = or

true = True
false = False

int = int
bool = bool

%%

{separador}               {}
{comentario}              {}

{finInstruccion}          {return ops.finInstruccion();}

{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}

{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}

{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCierre();}
{llaveApertura}           {return ops.unidadLAp();}
{llaveCierre}             {return ops.unidadLCierre();}

{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}

{while}                   {return ops.unidadWhile();}
{if}                      {return ops.unidadIf();}
{else}                    {return ops.unidadElse();}

{not}                     {return ops.unidadNot();}
{and}                     {return ops.unidadAnd();}
{or}                      {return ops.unidadOr();}

{true}                    {return ops.unidadTrue();}
{false}                   {return ops.unidadFalse();}

{int}                     {return ops.unidadInt();}
{bool}                    {return ops.unidadBool();}

[^]                       {ops.error();}  