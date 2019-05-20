parser grammar TinyDBParser;

options { tokenVocab = TinyDBLexer; }

root
    : sqlStatements EOF
    ;

sqlStatements
    : (sqlStatement ';')*
    ;

sqlStatement
    : dbSpecifiedStatement | dbUnspecifiedStatement
    ;

dbSpecifiedStatement
    : createTable | dropTable
    | selectStatement | insertStatement | updateStatement | deleteStatement
    ;

dbUnspecifiedStatement
    : createDatabase | dropDatabase | useDatabase
    | showStatement | shutdownStatement
    ;

createDatabase
    : CREATE DATABASE dbName
    ;

createTable
    : CREATE TABLE tableName '(' createDefinition (',' createDefinition)* ')'
    ;

createDefinition
    : attrName dataType nullNotnull?                                #columnDeclaration
    | PRIMARY KEY '(' attrName ')'                                 #constraintDeclaration
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
    : SELECT fullColumnNames FROM tableSources (WHERE whereExpr=predicate)?
    ;

fullColumnNames
    : STAR
    | fullColumnName (',' fullColumnName)*
    ;

fullColumnName
    : (tableName '.')? attrName (AS alias=ID)?
    ;

tableSources
    : tableSource (',' tableSource)*
    ;

tableSource
    : table joinPart*                                     #tableSourceBase
    ;

joinPart
    : JOIN table
      (
        ON comparisonExpressionPredicate
      )?                                                            #innerJoin
    | (LEFT | RIGHT) OUTER? JOIN table
        (
          ON comparisonExpressionPredicate
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
    : andExpressionPredicate (OR andExpressionPredicate)*
    ;

andExpressionPredicate
    : comparisonExpressionPredicate (AND comparisonExpressionPredicate)*
    ;

comparisonExpressionPredicate
    : constant comparisonOperator fullColumnName                    #vkCmpExpressionPredicate
    | fullColumnName comparisonOperator constant                    #kvCmpExpressionPredicate
    | fullColumnName comparisonOperator fullColumnName              #kkCmpExpressionPredicate
    | constant comparisonOperator constant                          #vvCmpExpressionPredicate
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
    | NULL_LITERAL
    ;

comparisonOperator
    : '=' | '>' | '<' | '<' '=' | '>' '='
    | '<' '>' | '!' '='
    ;

showStatement
    : SHOW DATABASES
    | SHOW DATABASE dbName
    | SHOW TABLE table
    ;

shutdownStatement
    : SHUTDOWN
    ;

dbName
    : ID
    ;

table
    : originalName=tableName (AS alias=tableName)?
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
    : NOT NULL_LITERAL
    ;

decimalLiteral
    : DECIMAL_LITERAL | ZERO_DECIMAL | ONE_DECIMAL | TWO_DECIMAL
    ;