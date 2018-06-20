{
/**Este programa calcula el factorial.
 * Se puede comprobar que no hace falta un salto de linea antes de EOF para cerrar un bloque.*/

int n = 10
int nFactorial = 1

while n > 0
    nFactorial = nFactorial * n
    n = n - 1
}
{  0} ssp 7;
{  1} ldc 5;
{  2} ldc 10;
{  3} sto;
{  4} ldc 6;
{  5} ldc 1;
{  6} sto;
{  7} ldc 5;
{  8} ind;
{  9} ldc 0;
{ 10} grt;
{ 11} fjp 26;
{ 12} ldc 6;
{ 13} ldc 6;
{ 14} ind;
{ 15} ldc 5;
{ 16} ind;
{ 17} mul;
{ 18} sto;
{ 19} ldc 5;
{ 20} ldc 5;
{ 21} ind;
{ 22} ldc 1;
{ 23} sub;
{ 24} sto;
{ 25} ujp 7;
{ 26} stp;
{
Run successfully completed
Final P-machine state:
PC = 26   MP = 0   SP = 6   EP = -1   NP = 100   code size = 27
STORE =
(0,Null)
(1,Null)
(2,Null)
(3,Null)
(4,Null)
(5,Int 0)
(6,Int 3628800)
(7,Bool False)
(8,Int 0)
(9,Int 1)
(10,Null)
}