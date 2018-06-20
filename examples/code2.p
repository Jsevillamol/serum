{
/**Uso repetido del mismo identificador.*/

int i
int i = 100
i = 200
int i = 300
i = 400
    i = 500
    bool i = True
    int i = 600
i = 700
    int i = 800
}
{  0} ssp 10;
{  1} ldc 6;
{  2} ldc 100;
{  3} sto;
{  4} ldc 6;
{  5} ldc 200;
{  6} sto;
{  7} ldc 7;
{  8} ldc 300;
{  9} sto;
{ 10} ldc 7;
{ 11} ldc 400;
{ 12} sto;
    { 13} ldc 7;
    { 14} ldc 500;
    { 15} sto;
    { 16} ldc 8;
    { 17} ldc true;
    { 18} sto;
    { 19} ldc 9;
    { 20} ldc 600;
    { 21} sto;
{ 22} ldc 7;
{ 23} ldc 700;
{ 24} sto;
    { 25} ldc 8;
    { 26} ldc 800;
    { 27} sto;
{ 28} stp;

{
Run successfully completed
Final P-machine state:
PC = 28   MP = 0   SP = 9   EP = -1   NP = 100   code size = 29
STORE =
(0,Null)
(1,Null)
(2,Null)
(3,Null)
(4,Null)
(5,Null)
(6,Int 200)
(7,Int 700)
(8,Int 800)
(9,Int 600)
(10,Int 8)
(11,Int 800)
(12,Null)
}