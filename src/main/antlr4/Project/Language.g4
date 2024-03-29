grammar Language;
program: (statement)+;

statement                                                                      // Type Check
    : ';'                                                   # emptyStatement   //
    | TYPE VAR (',' VAR)* ';'                               # varDeclStatement // ✅
    | expression ';'                                        # exprStatement    //
    | 'read' VAR (',' VAR)* ';'                             # readStatement    //
    | 'write' expression (',' expression)* ';'              # writeStatement   //
    | '{' statement* '}'                                    # blockStatement   //
    | 'if' '(' condition ')' statement ('else' statement)?  # ifStatement      //
    | 'while' '(' condition ')' statement                   # whileStatement   //
    ;

condition
	: expression operator expression # binaryCondition  //
	| unary expression               # unaryCondition   //
	| VAR 							 # varCondition     //
	| BOOL 							 # boolCondition	//
	;

expression
    : '(' expression ')'    			# parenExpression   //
    | expression operator expression    # binaryExpression  //
    | unary expression                  # unaryExpression   //
    | VAR                               # varExpression     //
    | literal                           # literalExpression //
    | VAR '=' expression                # assignExpression  // ✅
    ;

operator: '+' | '-' | '*' | '/' | '%' | '.' | '<' | '>' | '==' | '!=' | '&&' | '||';
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
