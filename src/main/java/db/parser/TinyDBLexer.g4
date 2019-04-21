/*
MySQL (Positive Technologies) grammar
The MIT License (MIT).
Copyright (c) 2015-2017, Ivan Kochurkin (kvanttt@gmail.com), Positive Technologies.
Copyright (c) 2017, Ivan Khudyashev (IHudyashov@ptsecurity.com)
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

lexer grammar TinyDBLexer;

channels { MYSQLCOMMENT, ERRORCHANNEL }

// SKIP

SPACE:                               [ \t\r\n]+    -> channel(HIDDEN);
SPEC_MYSQL_COMMENT:                  '/*!' .+? '*/' -> channel(MYSQLCOMMENT);
COMMENT_INPUT:                       '/*' .*? '*/' -> channel(HIDDEN);
LINE_COMMENT:                        (
                                       ('-- ' | '#') ~[\r\n]* ('\r'? '\n' | EOF)
                                       | '--' ('\r'? '\n' | EOF)
                                     ) -> channel(HIDDEN);


// Keywords
// Common Keywords

AND:                                 'AND';
AS:                                  'AS';
ASC:                                 'ASC';
BY:                                  'BY';
CREATE:                              'CREATE';
CROSS:                               'CROSS';
DATABASE:                            'DATABASE';
DATABASES:                           'DATABASES';
DEFAULT:                             'DEFAULT';
DELETE:                              'DELETE';
DESC:                                'DESC';
DISTINCT:                            'DISTINCT';
DROP:                                'DROP';
FALSE:                               'FALSE';
FROM:                                'FROM';
GROUP:                               'GROUP';
INNER:                               'INNER';
INSERT:                              'INSERT';
INTO:                                'INTO';
IS:                                  'IS';
JOIN:                                'JOIN';
KEY:                                 'KEY';
LEFT:                                'LEFT';
LIKE:                                'LIKE';
NATURAL:                             'NATURAL';
NOT:                                 'NOT';
NULL_LITERAL:                        'NULL';
ON:                                  'ON';
OR:                                  'OR';
ORDER:                               'ORDER';
OUTER:                               'OUTER';
PRIMARY:                             'PRIMARY';
RIGHT:                               'RIGHT';
SELECT:                              'SELECT';
SET:                                 'SET';
SHOW:                                'SHOW';
TABLE:                               'TABLE';
TRUE:                                'TRUE';
UPDATE:                              'UPDATE';
USE:                                 'USE';
VALUES:                              'VALUES';
WHERE:                               'WHERE';
WITH:                                'WITH';
XOR:                                 'XOR';


// DATA TYPE Keywords

INT:                                 'INT';
LONG:                                'LONG';
DOUBLE:                              'DOUBLE';
FLOAT:                               'FLOAT';
DECIMAL:                             'DECIMAL';
NUMERIC:                             'NUMERIC';
STRING:                              'STRING';

// Group function Keywords

AVG:                                 'AVG';
COUNT:                               'COUNT';
MAX:                                 'MAX';
MIN:                                 'MIN';
SUM:                                 'SUM';


SHUTDOWN:                            'SHUTDOWN';

// Operators
// Operators. Assigns

VAR_ASSIGN:                          ':=';
PLUS_ASSIGN:                         '+=';
MINUS_ASSIGN:                        '-=';
MULT_ASSIGN:                         '*=';
DIV_ASSIGN:                          '/=';
MOD_ASSIGN:                          '%=';
AND_ASSIGN:                          '&=';
XOR_ASSIGN:                          '^=';
OR_ASSIGN:                           '|=';


// Operators. Arithmetics

STAR:                                '*';
DIVIDE:                              '/';
MODULE:                              '%';
PLUS:                                '+';
MINUSMINUS:                          '--';
MINUS:                               '-';
DIV:                                 'DIV';
MOD:                                 'MOD';


// Operators. Comparation

EQUAL_SYMBOL:                        '=';
GREATER_SYMBOL:                      '>';
LESS_SYMBOL:                         '<';
EXCLAMATION_SYMBOL:                  '!';


// Operators. Bit

BIT_NOT_OP:                          '~';
BIT_OR_OP:                           '|';
BIT_AND_OP:                          '&';
BIT_XOR_OP:                          '^';


// Constructors symbols

DOT:                                 '.';
LR_BRACKET:                          '(';
RR_BRACKET:                          ')';
COMMA:                               ',';
SEMI:                                ';';
AT_SIGN:                             '@';
ZERO_DECIMAL:                        '0';
ONE_DECIMAL:                         '1';
TWO_DECIMAL:                         '2';
SINGLE_QUOTE_SYMB:                   '\'';
DOUBLE_QUOTE_SYMB:                   '"';
REVERSE_QUOTE_SYMB:                  '`';
COLON_SYMB:                          ':';


// Literal Primitives


START_NATIONAL_STRING_LITERAL:       'N' SQUOTA_STRING;
STRING_LITERAL:                      DQUOTA_STRING | SQUOTA_STRING;
DECIMAL_LITERAL:                     DEC_DIGIT+;
HEXADECIMAL_LITERAL:                 'X' '\'' (HEX_DIGIT HEX_DIGIT)+ '\''
                                     | '0X' HEX_DIGIT+;

REAL_LITERAL:                        (DEC_DIGIT+)? '.' DEC_DIGIT+
                                     | DEC_DIGIT+ '.' EXPONENT_NUM_PART
                                     | (DEC_DIGIT+)? '.' (DEC_DIGIT+ EXPONENT_NUM_PART)
                                     | DEC_DIGIT+ EXPONENT_NUM_PART;
NULL_SPEC_LITERAL:                   '\\' 'N';



// Identifiers

ID:                                  ID_LITERAL;


// Fragments for Literal primitives


fragment EXPONENT_NUM_PART:          'E' '-'? DEC_DIGIT+;
fragment ID_LITERAL:                 [A-Z_$0-9]*?[A-Z_$]+?[A-Z_$0-9]*;
fragment DQUOTA_STRING:              '"' ( '\\'. | '""' | ~('"'| '\\') )* '"';
fragment SQUOTA_STRING:              '\'' ('\\'. | '\'\'' | ~('\'' | '\\'))* '\'';
fragment BQUOTA_STRING:              '`' ( '\\'. | '``' | ~('`'|'\\'))* '`';
fragment HEX_DIGIT:                  [0-9A-F];
fragment DEC_DIGIT:                  [0-9];


// Last tokens must generate Errors

ERROR_RECONGNIGION:                  .    -> channel(ERRORCHANNEL);