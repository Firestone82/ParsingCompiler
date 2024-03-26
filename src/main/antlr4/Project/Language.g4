grammar Language;
program: (statement)+;

statement
    : ';'                                                   // Empty command
    | TYPE VAR (',' VAR)* ';'                               // Variable declaration
    | expression ';'                                        // AAAA, expressions
    | 'read' VAR (',' VAR)* ';'                             // Read variable
    | 'write' expression (',' expression)* ';'              // Write expression
    | '{' statement (statement)* '}'                    // Block statement
    | 'if' '(' condition ')' statement ('else' statement)?  // ifStatement
    | 'while' '(' condition ')' statement                   // whileStatement
    ;

condition
	: expression operator expression
	| unary expression
	| VAR
	| BOOL
	;

expression
    : '(' expression ')'
    | expression operator expression
    | unary expression
    | VAR
    | literal
    | VAR '=' expression
    ;

operator: '+' | '-' | '*' | '/' | '%' | '.' | '<' | '>' | '==' | '!=' | '&&' | '||' | '-' | '!';
unary: '-' | '!';

literal
    : INT
    | FLOAT
    | BOOL
    | STRING
    ;

INT: DIGIT+;
FLOAT: DIGIT+ '.' DIGIT*;
BOOL: 'true' | 'false';
STRING: ('"' (~["\\])* '"');

TYPE: 'int' | 'float' | 'bool' | 'string';
VAR: LETTER (LETTER | DIGIT)*;

COMMENT: '//' ~[\r\n]* -> skip;
SPACE: [ \t\r\n]+ -> skip;

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];
