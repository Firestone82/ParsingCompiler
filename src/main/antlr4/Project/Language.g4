grammar Language;
program: (statement)+;

// ✅ - Done
// 🆗 - Skipped, should be fine

statement                                                                                  // Type Check | Tests
    : ';'                                                             # emptyStatement     // 🆗️           ✅✅
    | TYPE VAR (',' VAR)* ';'                                         # varDeclStatement   // ✅           ✅✅
    | expression ';'                                                  # exprStatement      // ✅           ✅✅
    | 'read' VAR (',' VAR)* ';'                                       # readStatement      // ✅           ✅✅
    | 'write' expression (',' expression)* ';'                        # writeStatement     // 🆗️           ✅✅
    | '{' statement* '}'                                              # blockStatement     // 🆗           ✅✅
    | 'if' '(' condition ')' statement ('else' statement)?            # ifStatement        // 🆗           ✅
    | 'while' '(' condition ')' statement                             # whileStatement     // 🆗           ✅
    | 'do' statement 'while' '(' condition ')' ';'                    # doWhileStatement   // 🆗           ✅
    | 'for' '(' expression ';' condition ';' expression ')' statement # forStatement       // 🆗           ✅
    ;

condition                           // Type Check | Tests
	: expression # boolCondition    // ✅
	;

expression                                                                       // Type Check | Tests
    : '(' expression ')'    			                  # parenExpression      // ✅           ✅✅
    | expression op=('+' | '-' | '*' | '/') expression    # aritmExpression      // ✅           ✅✅
    | expression op='%' expression                        # moduloExpression     // ✅           ✅✅
    | expression op='.' expression                        # concatExpression     // ✅           ✅✅
    | expression op=('<' | '>') expression                # relationExpression   // ✅           ✅✅
    | expression op=('==' | '!=') expression              # comparisonExpression // ✅           ✅✅
    | expression op=('&&' | '||') expression              # logicExpression      // ✅           ✅✅
    | expression '?' expression split=':' expression      # ternaryExpression    // ✅           ✅
    | op='!' expression                                   # notExpression        // ✅           ✅✅
    | op='-' expression                                   # negExpression        // ✅           ✅✅
    | VAR                                                 # varExpression        // ✅           🆗🆗
    | literal                                             # literalExpression    // ✅           🆗🆗
    | VAR '=' expression                                  # assignExpression     // ✅           ✅✅
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
