{


int[2][2] my_array
int maximo = 0

my_array[0][0] = 21
my_array[0][1] = 32
my_array[1][0] = 43
my_array[1][1] = 24


int i = 0
while i < 2
    int j = 0
    while j < 2
        if my_array[i][j] > maximo    maximo = my_array[i][j]
        j = j + 1
    i = i + 1
}
{  0} ssp 12;
{  1} ldc 9;
{  2} ldc 0;
{  3} sto;          {int maximo = 0}
{  4} ldc 5;
{  5} ldc 0;
{  6} ixa 2;
{  7} ldc 0;
{  8} ixa 1;
{  9} ldc 21;
{ 10} sto;          {my_array[0][0] = 21}
{ 11} ldc 5;
{ 12} ldc 0;
{ 13} ixa 2;
{ 14} ldc 1;
{ 15} ixa 1;
{ 16} ldc 32;
{ 17} sto;          {my_array[0][1] = 32}
{ 18} ldc 5;
{ 19} ldc 1;
{ 20} ixa 2;
{ 21} ldc 0;
{ 22} ixa 1;
{ 23} ldc 43;
{ 24} sto;          {my_array[1][0] = 43}
{ 25} ldc 5;
{ 26} ldc 1;
{ 27} ixa 2;
{ 28} ldc 1;
{ 29} ixa 1;
{ 30} ldc 24;
{ 31} sto;          {my_array[1][1] = 24}
{ 32} ldc 10;
{ 33} ldc 0;
{ 34} sto;          {int i = 0}
{ 35} ldc 10;
{ 36} ind;
{ 37} ldc 2;
{ 38} les;          {i < 2}
{ 39} fjp 84;
    { 40} ldc 11;
    { 41} ldc 0;
    { 42} sto;      {int j = 0}
    { 43} ldc 11;
    { 44} ind;
    { 45} ldc 2;
    { 46} les;      {j < 2}
    { 47} fjp 77;
        { 48} ldc 5;
        { 49} ldc 10;
        { 50} ind;
        { 51} ixa 2;
        { 52} ldc 11;
        { 53} ind;
        { 54} ixa 1;
        { 55} ind;
        { 56} ldc 9;
        { 57} ind;
        { 58} grt;      {my_array[i][j] > maximo}
        { 59} fjp 70;
            { 60} ldc 9;
            { 61} ldc 5;
            { 62} ldc 10;
            { 63} ind;
            { 64} ixa 2;
            { 65} ldc 11;
            { 66} ind;
            { 67} ixa 1;
            { 68} ind;
            { 69} sto;  {maximo = my_array[i][j]}
        { 70} ldc 11;
        { 71} ldc 11;
        { 72} ind;
        { 73} ldc 1;
        { 74} add;
        { 75} sto;      {j = j + 1}
        { 76} ujp 43;
    { 77} ldc 10;
    { 78} ldc 10;
    { 79} ind;
    { 80} ldc 1;
    { 81} add;
    { 82} sto;          {i = i + 1}
    { 83} ujp 35;
{ 84} stp;
{
Run successfully completed
Final P-machine state:
PC = 84   MP = 0   SP = 11   EP = -1   NP = 100   code size = 85
STORE =
(0,Null)
(1,Null)
(2,Null)
(3,Null)
(4,Null)
(5,Int 21)
(6,Int 32)
(7,Int 43)
(8,Int 24)
(9,Int 43)
(10,Int 2)
(11,Int 2)
(12,Bool False)
(13,Int 2)
(14,Int 1)
(15,Null)
}