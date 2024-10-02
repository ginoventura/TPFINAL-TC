grammar compiladores;

@header {
package compiladores;
}

/* ----------------- Definición de tokens ----------------- */

MAIN : 'main' ;
INT : 'int' ;
RETURN : 'return' ;

SUM : '+' ;
RES : '-' ;
MUL : '*' ;
DIV : '/' ;
COMA : ',' ;

IGUAL : '=' ;

AND   : '&&' ;
OR    : '||' ;
NOT   : '!'  ;

MAYOR        : '>'  ;
MENOR        : '<'  ;
MAYOR_IGUAL  : '>=' ;
MENOR_IGUAL  : '<=' ;
EQL          : '==' ;
DISTINTO     : '!=' ;

FOR   : 'for'   ;
WHILE : 'while' ;
IF    : 'if'    ;
ELSE  : 'else'  ;

LLA   : '{' ;
LLC   : '}' ;
PA    : '(' ;
PC    : ')' ;
PYC   : ';' ;

ID : [a-zA-Z_][a-zA-Z0-9_]* ;

ENTERO : [0-9]+ ; 

//Regla para los espacios en blanco
WS : [ \n\t\r] -> skip ;

/* ---------------------------------------------------- */

programa : instrucciones EOF ;

instrucciones : instruccion instrucciones 
              | 
              ;

bloque : LLA instrucciones LLC ;

instruccion : declaracion
            | asignacion
            | declaracionFuncion
            | definicionFuncion
            | llamadaFuncion
            | ifor
            | iwhile
            | iif
            | retorno
            | bloque
            ;

retorno : RETURN operacion PYC ; 

/* ----------------- Asignacion y declaracion de variables ----------------- */

/* 
   int x;
   int x = 5 + 6 / 3;
   int x, y, z;
   int x = 3, z, y = 0;
   int x = 5 + (6 / 3); 
*/

declaracion : INT declaracionLista PYC ;

declaracionLista : ID asignacion_opcional siguienteDeclaracion ;

siguienteDeclaracion : COMA declaracionLista
                     | 
                     ;

asignacion_opcional : asign   
                    | 
                    ;

asign : IGUAL operacion ;

asignacion : ID IGUAL operacion PYC ;

/* ----------------- Operaciones ----------------- */

operacion : operacion (AND | OR) operacion 
          | operacion (MAYOR | MENOR | MAYOR_IGUAL | MENOR_IGUAL | EQL | DISTINTO) operacion
          | aritmetica
          ;

aritmetica : term (SUM term | RES term)* ;

term : factor (MUL factor | DIV factor)* ;

factor : PA operacion PC
       | ENTERO
       | ID
       | llamadaFuncion
       |
       ;

/* ----------------- Estructuras de control ----------------- */

/* 
   for (int i = 0; i < 10; i++) { }
   for (i = 0; i <= 10; i--) { } 
*/

ifor : FOR PA inicializacionFor operacion PYC operacion PC bloque ;

inicializacionFor : declaracion
                  | asignacion
                  ;

 /* 
   while (x > 10) { }
   while (x != 0) { }
   while ((a + b) == 20) { }
   while ((x * y) >= 100) { }
   while ((x > 4) && (y < 5)) { } 
*/

iwhile : WHILE PA operacion PC bloque ;

/* 
   if (x > 10) { }
   if (x != 0) { }
   if ((a + b) == 20) { }
   if ((x * y) >= 100) { }
   if ((x > 4) && (y < 5)) { } 
*/

iif : IF PA operacion PC bloque
    | IF PA operacion PC bloque ELSE bloque
    ;

/* --------------------------------------------- */
/* ----------------- Funciones ----------------- */

/* 
   int sumar (int x, int y); 
   int sumar (int, int);
   int sumar (int x);
   int sumar (); 
*/

declaracionFuncion : INT ID PA parametros PC PYC ;

/* 
   int sumar (int x, int y) {}
   int sumar (int x) {}
   int sumar () {} 
*/

definicionFuncion : INT ID PA parametros PC bloque ;

parametros: (param (COMA param)*)? ; 

param: INT (ID)? ;

/* 
   sumar (x, y);
   sumar (x);
   sumar (); 
*/

llamadaFuncion : ID PA argumentos PC PYC 
               | ID PA argumentos PC 
               ;

argumentos : operacion (COMA operacion)* | ;