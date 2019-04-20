package parser;
// Generated from TinyDBParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import parser.TinyDBParser;

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
	 * Visit a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link TinyDBParser#colunmConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullColumnConstraint(TinyDBParser.NullColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link TinyDBParser#colunmConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryKeyColumnConstraint(TinyDBParser.PrimaryKeyColumnConstraintContext ctx);
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
	 * Visit a parse tree produced by {@link TinyDBParser#insertStatementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatementValue(TinyDBParser.InsertStatementValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(TinyDBParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(TinyDBParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link TinyDBParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectColumnElement(TinyDBParser.SelectColumnElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#fullColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnName(TinyDBParser.FullColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#fromClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromClause(TinyDBParser.FromClauseContext ctx);
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
	 * Visit a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link TinyDBParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceNested(TinyDBParser.TableSourceNestedContext ctx);
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
	 * Visit a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalJoin(TinyDBParser.NaturalJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceItem(TinyDBParser.TableSourceItemContext ctx);
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
	 * Visit a parse tree produced by {@link TinyDBParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(TinyDBParser.ExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(TinyDBParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(TinyDBParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicateExpression(TinyDBParser.PredicateExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAtomPredicate(TinyDBParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryComparasionPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryComparasionPredicate(TinyDBParser.BinaryComparasionPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNullPredicate(TinyDBParser.IsNullPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpressionAtom(TinyDBParser.UnaryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryExpessionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryExpessionAtom(TinyDBParser.SubqueryExpessionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpressionAtom(TinyDBParser.ConstantExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpressionAtom(TinyDBParser.BinaryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnNameExpressionAtom(TinyDBParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitExpressionAtom(TinyDBParser.BitExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpressionAtom(TinyDBParser.NestedExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathExpressionAtom(TinyDBParser.MathExpressionAtomContext ctx);
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
	 * Visit a parse tree produced by {@link TinyDBParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(TinyDBParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(TinyDBParser.UnaryOperatorContext ctx);
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
	 * Visit a parse tree produced by {@link TinyDBParser#bitOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOperator(TinyDBParser.BitOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyDBParser#mathOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathOperator(TinyDBParser.MathOperatorContext ctx);
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
}