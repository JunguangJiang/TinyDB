package db.parser;

// Generated from TinyDBParser.g4 by ANTLR 4.7.1
import db.parser.TinyDBParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TinyDBParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TinyDBParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(TinyDBParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#sqlStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatements(TinyDBParser.SqlStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(TinyDBParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#ddlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdlStatement(TinyDBParser.DdlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#dmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmlStatement(TinyDBParser.DmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#administrationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdministrationStatement(TinyDBParser.AdministrationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#createDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabase(TinyDBParser.CreateDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTable(TinyDBParser.CreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link TinyDBParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDeclaration(TinyDBParser.ColumnDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link TinyDBParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintDeclaration(TinyDBParser.ConstraintDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#dropDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDatabase(TinyDBParser.DropDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#dropTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTable(TinyDBParser.DropTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#useDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseDatabase(TinyDBParser.UseDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#insertStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatement(TinyDBParser.InsertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(TinyDBParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#fullColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnName(TinyDBParser.FullColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#tableSources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSources(TinyDBParser.TableSourcesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link TinyDBParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceBase(TinyDBParser.TableSourceBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerJoin(TinyDBParser.InnerJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuterJoin(TinyDBParser.OuterJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#updateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateStatement(TinyDBParser.UpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#updatedElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdatedElement(TinyDBParser.UpdatedElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#deleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteStatement(TinyDBParser.DeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpressionPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpressionPredicate(TinyDBParser.ComparisonExpressionPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExpressionPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpressionPredicate(TinyDBParser.LogicalExpressionPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpressionAtom(TinyDBParser.ConstantExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnNameExpressionAtom(TinyDBParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataType(TinyDBParser.DataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthOneDimension(TinyDBParser.LengthOneDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#constants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstants(TinyDBParser.ConstantsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(TinyDBParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(TinyDBParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOperator(TinyDBParser.LogicalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStatement(TinyDBParser.ShowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#shutdownStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShutdownStatement(TinyDBParser.ShutdownStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#dbName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDbName(TinyDBParser.DbNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(TinyDBParser.TableContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(TinyDBParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#attrName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrName(TinyDBParser.AttrNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#attrNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrNames(TinyDBParser.AttrNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#nullNotnull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullNotnull(TinyDBParser.NullNotnullContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#decimalLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteral(TinyDBParser.DecimalLiteralContext ctx);
}