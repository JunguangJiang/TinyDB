parser grammar TinyDBParser;

options { tokenVocab = TinyDBLexer; }

root
    : sqlStatement EOF
    ;

sqlStatement
    : ddlStatement | dmlStatement | administrationStatement
    ;

ddlStatement
    : createDatabase | createTable | dropDatabase | dropTable | useDatabase
    ;

dmlStatement
    : selectStatement | insertStatement | updateStatement | deleteStatement
    ;

administrationStatement
    : showStatement | shutdownStatement
    ;

createDatabase
    : CREATE DATABASE dbName
    ;

createTable
    : CREATE TABLE tableName '(' createDefinition (',' createDefinition)* ')'
    ;

createDefinition
    : attrName dataType colunmConstraint*                           #columnDeclaration
    | PRIMARY KEY '(' attrNames ')'                                 #constraintDeclaration
    ;

colunmConstraint
    : nullNotnull                                                   #nullColumnConstraint
    | PRIMARY KEY                                                  #primaryKeyColumnConstraint
    ;

dropDatabase
    : DROP DATABASE dbName
    ;

dropTable
    : DROP TABLE tableName
    ;

useDatabase
    : USE DATABASE dbName
    ;

insertStatement
    : INSERT INTO tableName ( '(' attrNames ')' )? insertStatementValue
    ;

insertStatementValue
    : selectStatement
    | VALUES '(' expressions ')'
    ;

selectStatement
    : SELECT selectElements fromClause
    ;

selectElements
    : (star='*' | selectElement ) (',' selectElement)*
    ;

selectElement
    : fullColumnName (AS? alias=attrName)?                                     #selectColumnElement
    ;

fullColumnName
    : tableName '.' attrName
    | attrName
    ;

fromClause
    : FROM tableSources
      (WHERE whereExpr=expression)?
      (
        GROUP BY expression
      )?
    ;

tableSources
    : tableSource (',' tableSource)*
    ;

tableSource
    : tableSourceItem joinPart*                                     #tableSourceBase
    | '(' tableSourceItem joinPart* ')'                             #tableSourceNested
    ;

joinPart
    : (INNER | CROSS)? JOIN tableSourceItem
      (
        ON expression
      )?                                                            #innerJoin
    | (LEFT | RIGHT) OUTER? JOIN tableSourceItem
        (
          ON expression
        )                                                           #outerJoin
    | NATURAL ((LEFT | RIGHT) OUTER?)? JOIN tableSourceItem         #naturalJoin
    ;

tableSourceItem
    : tableName
    ;

updateStatement
    : UPDATE tableName (AS? alias=tableName)?
    SET updatedElement (',' updatedElement)*
          (WHERE expression)?
    ;

updatedElement
    : fullColumnName '=' expression
    ;

deleteStatement
    : DELETE FROM tableName
    (WHERE expression)?
    ;

//    Expressions, predicates
expressions
    : expression (',' expression)*
    ;

// Simplified approach for expression
expression
    : notOperator=(NOT | '!') expression                            #notExpression
    | expression logicalOperator expression                         #logicalExpression
    | predicate                                                     #predicateExpression
    ;

predicate
    : predicate IS nullNotnull                                      #isNullPredicate
    | left=predicate comparisonOperator right=predicate             #binaryComparasionPredicate
    | expressionAtom                                                #expressionAtomPredicate
    ;

// Add in ASTVisitor nullNotnull in constant
expressionAtom
    : constant                                                      #constantExpressionAtom
    | fullColumnName                                                #fullColumnNameExpressionAtom
    | unaryOperator expressionAtom                                  #unaryExpressionAtom
    | BINARY expressionAtom                                         #binaryExpressionAtom
    | '(' expression (',' expression)* ')'                          #nestedExpressionAtom
    | '(' selectStatement ')'                                       #subqueryExpessionAtom
    | left=expressionAtom bitOperator right=expressionAtom          #bitExpressionAtom
    | left=expressionAtom mathOperator right=expressionAtom         #mathExpressionAtom
    ;

dataType
    : INT | LONG | FLOAT | DOUBLE
    | (STRING) lengthOneDimension
    ;

lengthOneDimension
    : '(' DECIMAL_LITERAL ')'
    ;

constant
    : STRING_LITERAL | DECIMAL_LITERAL
    | '-' DECIMAL_LITERAL
    | REAL_LITERAL | BIT_STRING
    | nullNotnull
    ;

unaryOperator
    : '!' | '~' | '+' | '-' | NOT
    ;

comparisonOperator
    : '=' | '>' | '<' | '<' '=' | '>' '='
    | '<' '>' | '!' '=' | '<' '=' '>'
    ;

logicalOperator
    : AND | '&' '&' | XOR | OR | '|' '|'
    ;

bitOperator
    : '<' '<' | '>' '>' | '&' | '^' | '|'
    ;

mathOperator
    : '*' | '/' | '%' | DIV | MOD | '+' | '-' | '--'
    ;


showStatement
    : SHOW DATABASES
    | SHOW DATABASE dbName
    | SHOW TABLE tableName
    ;

shutdownStatement
    : SHUTDOWN
    ;

dbName
    : Id
    ;

tableName
    : ID
    ;

attrName
    : Id
    ;

attrNames
    : attrName (',' attrName)*
    ;

nullNotnull
    : NOT? NULL_LITERAL
    ;