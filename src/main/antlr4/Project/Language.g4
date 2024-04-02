grammar Language;
program: (statement)+;

statement                                                                                  // Type Check
    : ';'                                                             # emptyStatement     //
    | TYPE VAR (',' VAR)* ';'                                         # varDeclStatement   // ✅
    | expression ';'                                                  # exprStatement      //
    | 'read' VAR (',' VAR)* ';'                                       # readStatement      //
    | 'write' expression (',' expression)* ';'                        # writeStatement     //
    | '{' statement* '}'                                              # blockStatement     //
    | 'if' '(' condition ')' statement ('else' statement)?            # ifStatement        //
    | 'while' '(' condition ')' statement                             # whileStatement     //
    | 'for' '(' expression ';' condition ';' expression ')' statement # forStatement       //
    ;

// TODO: Maybe remove??
condition
	: expression # boolCondition    //
	;

expression
    : '(' expression ')'    			                  # parenExpression      //
    | expression op=('+' | '-' | '*' | '/') expression    # aritmExpression      //
    | expression op='%' expression                        # moduloExpression     //
    | expression op='.' expression                        # concatExpression     //
    | expression op=('<' | '>') expression                # relationExpression   //
    | expression op=('==' | '!=') expression              # comparisonExpression //
    | expression op=('&&' | '||') expression              # logicExpression      //
    | op='!' expression                                   # notExpression        //
    | op='-' expression                                   # negExpression        //
    | VAR                                                 # varExpression        //
    | literal                                             # literalExpression    //
    | VAR '=' expression                                  # assignExpression     // ✅
    ;

literal
    : INT
    | FLOAT
    | BOOL
    | STRING
    ;

INT: DIGIT+;
FLOAT: DIGIT+ '.' DIGIT*;
BOOL: 'true' | 'false';
STRING: '"' (ESC | ~["\\])* '"';

TYPE: 'int' | 'float' | 'bool' | 'string';
VAR: LETTER (LETTER | DIGIT)*;

COMMENT: '//' ~[\r\n]* -> skip;
SPACE: [ \t\r\n]+ -> skip;

fragment ESC: '\\' (["\\/bfnrt] | UNICODE_ESC);
fragment UNICODE_ESC: 'u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];
