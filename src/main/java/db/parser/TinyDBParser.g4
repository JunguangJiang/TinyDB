parser grammar TinyDBParser;

options { tokenVocab = TinyDBLexer; }

root
    : sqlStatements EOF
    ;

sqlStatements
    : (sqlStatement ';')*
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
    : attrName dataType nullNotnull?                                #columnDeclaration
    | PRIMARY KEY '(' attrNames ')'                                 #constraintDeclaration
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
    : INSERT INTO tableName ( '(' attrNames ')' )? VALUES '(' constants ')'
    ;

selectStatement
    : SELECT fullColumnName (',' fullColumnName)* FROM tableSources (WHERE whereExpr=predicate)?
    ;

fullColumnName
    : tableName '.' attrName
    | attrName
    ;

tableSources
    : tableSource (',' tableSource)*
    ;

tableSource
    : tableName joinPart*                                     #tableSourceBase
    ;

joinPart
    : JOIN tableName
      (
        ON predicate
      )?                                                            #innerJoin
    | (LEFT | RIGHT) OUTER? JOIN tableName
        (
          ON predicate
        )                                                           #outerJoin
    ;

updateStatement
    : UPDATE tableName
    SET updatedElement (',' updatedElement)*
          (WHERE predicate)?
    ;

updatedElement
    : attrName '=' constant
    ;

deleteStatement
    : DELETE FROM tableName (WHERE predicate)?
    ;

// Simplified approach for predicate
predicate
    : left=predicate logicalOperator right=predicate                #logicalExpressionPredicate
    | left=expressionAtom comparisonOperator right=expressionAtom   #comparisonExpressionPredicate
    ;

// Add in ASTVisitor nullNotnull in constant
expressionAtom
    : constant                                                      #constantExpressionAtom
    | fullColumnName                                                #fullColumnNameExpressionAtom
    ;

dataType
    : INT | LONG | FLOAT | DOUBLE
    | (STRING) lengthOneDimension
    ;

lengthOneDimension
    : '(' DECIMAL_LITERAL ')'
    ;

constants
    : constant (',' constant)*
    ;

constant
    : STRING_LITERAL | decimalLiteral
    | '-' decimalLiteral
    | REAL_LITERAL
    ;

comparisonOperator
    : '=' | '>' | '<' | '<' '=' | '>' '='
    | '<' '>' | '!' '='
    ;

logicalOperator
    : AND | '&' '&' | XOR | OR | '|' '|'
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
    : ID
    ;

tableName
    : ID
    ;

attrName
    : ID
    ;

attrNames
    : attrName (',' attrName)*
    ;

nullNotnull
    : NOT? NULL_LITERAL
    ;

decimalLiteral
    : DECIMAL_LITERAL | ZERO_DECIMAL | ONE_DECIMAL | TWO_DECIMAL
    ;