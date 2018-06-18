{
/*Con este código se puede ver el orden en que se guardan los arrays.
 *Si funciona bien en memoria se deberán ver los numeros de 0 a 59 de forma consecutiva.
 */
int[3][10][2] array

int index1 = 0
while index1 < 3
        int index2 = 0
        while index2 < 10
            int index3 = 0
            while index3 < 2
                array[index1][index2][index3] = 20*index1 + 2*index2 + index3
                index3 = index3 + 1
            index2 = index2 + 1
        index1 = index1 + 1

bool b = True
}
{Las tabulaciones y los comentarios han sido añadidos a mano para facilitar la comprensión:}
{  0} ssp 68;
{  1} ldc 65;
{  2} ldc 0;
{  3} sto;   {int index1 = 0}
{  4} ldc 65;
{  5} ind;
{  6} ldc 3;
{  7} les;   {index1<3}
{  8} fjp 69;
    {  9} ldc 66;
    { 10} ldc 0;
    { 11} sto;   {int index2 = 0}
    { 12} ldc 66;
    { 13} ind;
    { 14} ldc 10;
    { 15} les;   {index2 < 10}
    { 16} fjp 62;
        { 17} ldc 67;
        { 18} ldc 0;
        { 19} sto;   {int index3 = 0}
        { 20} ldc 67;
        { 21} ind;
        { 22} ldc 2;
        { 23} les;   {index3 < 2}
        { 24} fjp 55;
            { 25} ldc 5;
            { 26} ldc 65;
            { 27} ind;
            { 28} ixa 20;
            { 29} ldc 66;
            { 30} ind;
            { 31} ixa 2;
            { 32} ldc 67;
            { 33} ind;
            { 34} ixa 1; {array[index1][index2][index3]}
            { 35} ldc 20;
            { 36} ldc 65;
            { 37} ind;
            { 38} mul;
            { 39} ldc 2;
            { 40} ldc 66;
            { 41} ind;
            { 42} mul;
            { 43} add;
            { 44} ldc 67;
            { 45} ind;
            { 46} add;   {20*index1 + 2*index2 + index3}
            { 47} sto;
            { 48} ldc 67;
            { 49} ldc 67;
            { 50} ind;
            { 51} ldc 1;
            { 52} add;
            { 53} sto;   {index3 = index3 + 1}
            { 54} ujp 20;
        { 55} ldc 66;
        { 56} ldc 66;
        { 57} ind;
        { 58} ldc 1;
        { 59} add;
        { 60} sto;   {index2 = index2 + 1}
        { 61} ujp 12;
    { 62} ldc 65;
    { 63} ldc 65;
    { 64} ind;
    { 65} ldc 1;
    { 66} add;
    { 67} sto;   {index1 = index1 + 1}
    { 68} ujp 4;
{ 69} ldc 66;
{ 70} ldc true;
{ 71} sto;   {bool b = True}
{ 72} stp;


{
Run successfully completed
Final P-machine state:
PC = 72   MP = 0   SP = 67   EP = -1   NP = 100   code size = 73
STORE =
(0,Null)
(1,Null)
(2,Null)
(3,Null)
(4,Null)
(5,Int 0)
(6,Int 1)
(7,Int 2)
(8,Int 3)
(9,Int 4)
(10,Int 5)
(11,Int 6)
(12,Int 7)
(13,Int 8)
(14,Int 9)
(15,Int 10)
(16,Int 11)
(17,Int 12)
(18,Int 13)
(19,Int 14)
(20,Int 15)
(21,Int 16)
(22,Int 17)
(23,Int 18)
(24,Int 19)
(25,Int 20)
(26,Int 21)
(27,Int 22)
(28,Int 23)
(29,Int 24)
(30,Int 25)
(31,Int 26)
(32,Int 27)
(33,Int 28)
(34,Int 29)
(35,Int 30)
(36,Int 31)
(37,Int 32)
(38,Int 33)
(39,Int 34)
(40,Int 35)
(41,Int 36)
(42,Int 37)
(43,Int 38)
(44,Int 39)
(45,Int 40)
(46,Int 41)
(47,Int 42)
(48,Int 43)
(49,Int 44)
(50,Int 45)
(51,Int 46)
(52,Int 47)
(53,Int 48)
(54,Int 49)
(55,Int 50)
(56,Int 51)
(57,Int 52)
(58,Int 53)
(59,Int 54)
(60,Int 55)
(61,Int 56)
(62,Int 57)
(63,Int 58)
(64,Int 59)
(65,Int 3)
(66,Bool True)
(67,Int 2)
(68,Int 66)
(69,Bool True)
(70,Int 1)
(71,Int 9)
(72,Null)
}