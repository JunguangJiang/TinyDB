package parser;
// Generated from TinyDBParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;
import parser.TinyDBParser;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TinyDBParser}.
 */
public interface TinyDBParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(TinyDBParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(TinyDBParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(TinyDBParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(TinyDBParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDdlStatement(TinyDBParser.DdlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDdlStatement(TinyDBParser.DdlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDmlStatement(TinyDBParser.DmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDmlStatement(TinyDBParser.DmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#administrationStatement}.
	 * @param ctx the parse tree
	 */
	void enterAdministrationStatement(TinyDBParser.AdministrationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#administrationStatement}.
	 * @param ctx the parse tree
	 */
	void exitAdministrationStatement(TinyDBParser.AdministrationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabase(TinyDBParser.CreateDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabase(TinyDBParser.CreateDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(TinyDBParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(TinyDBParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link TinyDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDeclaration(TinyDBParser.ColumnDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link TinyDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDeclaration(TinyDBParser.ColumnDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link TinyDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstraintDeclaration(TinyDBParser.ConstraintDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link TinyDBParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstraintDeclaration(TinyDBParser.ConstraintDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link TinyDBParser#colunmConstraint}.
	 * @param ctx the parse tree
	 */
	void enterNullColumnConstraint(TinyDBParser.NullColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link TinyDBParser#colunmConstraint}.
	 * @param ctx the parse tree
	 */
	void exitNullColumnConstraint(TinyDBParser.NullColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link TinyDBParser#colunmConstraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryKeyColumnConstraint(TinyDBParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link TinyDBParser#colunmConstraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryKeyColumnConstraint(TinyDBParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void enterDropDatabase(TinyDBParser.DropDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void exitDropDatabase(TinyDBParser.DropDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void enterDropTable(TinyDBParser.DropTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void exitDropTable(TinyDBParser.DropTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#useDatabase}.
	 * @param ctx the parse tree
	 */
	void enterUseDatabase(TinyDBParser.UseDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#useDatabase}.
	 * @param ctx the parse tree
	 */
	void exitUseDatabase(TinyDBParser.UseDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(TinyDBParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(TinyDBParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#insertStatementValue}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatementValue(TinyDBParser.InsertStatementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#insertStatementValue}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatementValue(TinyDBParser.InsertStatementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(TinyDBParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(TinyDBParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(TinyDBParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(TinyDBParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link TinyDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectColumnElement(TinyDBParser.SelectColumnElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link TinyDBParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectColumnElement(TinyDBParser.SelectColumnElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnName(TinyDBParser.FullColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnName(TinyDBParser.FullColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(TinyDBParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(TinyDBParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void enterTableSources(TinyDBParser.TableSourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void exitTableSources(TinyDBParser.TableSourcesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link TinyDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceBase(TinyDBParser.TableSourceBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link TinyDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceBase(TinyDBParser.TableSourceBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link TinyDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceNested(TinyDBParser.TableSourceNestedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link TinyDBParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceNested(TinyDBParser.TableSourceNestedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterInnerJoin(TinyDBParser.InnerJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitInnerJoin(TinyDBParser.InnerJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterOuterJoin(TinyDBParser.OuterJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitOuterJoin(TinyDBParser.OuterJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterNaturalJoin(TinyDBParser.NaturalJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link TinyDBParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitNaturalJoin(TinyDBParser.NaturalJoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceItem(TinyDBParser.TableSourceItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceItem(TinyDBParser.TableSourceItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStatement(TinyDBParser.UpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStatement(TinyDBParser.UpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#updatedElement}.
	 * @param ctx the parse tree
	 */
	void enterUpdatedElement(TinyDBParser.UpdatedElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#updatedElement}.
	 * @param ctx the parse tree
	 */
	void exitUpdatedElement(TinyDBParser.UpdatedElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStatement(TinyDBParser.DeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStatement(TinyDBParser.DeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(TinyDBParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(TinyDBParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(TinyDBParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(TinyDBParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(TinyDBParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(TinyDBParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPredicateExpression(TinyDBParser.PredicateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link TinyDBParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPredicateExpression(TinyDBParser.PredicateExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAtomPredicate(TinyDBParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAtomPredicate(TinyDBParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryComparasionPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterBinaryComparasionPredicate(TinyDBParser.BinaryComparasionPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryComparasionPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitBinaryComparasionPredicate(TinyDBParser.BinaryComparasionPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterIsNullPredicate(TinyDBParser.IsNullPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link TinyDBParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitIsNullPredicate(TinyDBParser.IsNullPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpressionAtom(TinyDBParser.UnaryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpressionAtom(TinyDBParser.UnaryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryExpessionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryExpessionAtom(TinyDBParser.SubqueryExpessionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryExpessionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryExpessionAtom(TinyDBParser.SubqueryExpessionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpressionAtom(TinyDBParser.ConstantExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpressionAtom(TinyDBParser.ConstantExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpressionAtom(TinyDBParser.BinaryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpressionAtom(TinyDBParser.BinaryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnNameExpressionAtom(TinyDBParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnNameExpressionAtom(TinyDBParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterBitExpressionAtom(TinyDBParser.BitExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitBitExpressionAtom(TinyDBParser.BitExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterNestedExpressionAtom(TinyDBParser.NestedExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitNestedExpressionAtom(TinyDBParser.NestedExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterMathExpressionAtom(TinyDBParser.MathExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link TinyDBParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitMathExpressionAtom(TinyDBParser.MathExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(TinyDBParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(TinyDBParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 */
	void enterLengthOneDimension(TinyDBParser.LengthOneDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 */
	void exitLengthOneDimension(TinyDBParser.LengthOneDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(TinyDBParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(TinyDBParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(TinyDBParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(TinyDBParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(TinyDBParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(TinyDBParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(TinyDBParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(TinyDBParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#bitOperator}.
	 * @param ctx the parse tree
	 */
	void enterBitOperator(TinyDBParser.BitOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#bitOperator}.
	 * @param ctx the parse tree
	 */
	void exitBitOperator(TinyDBParser.BitOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#mathOperator}.
	 * @param ctx the parse tree
	 */
	void enterMathOperator(TinyDBParser.MathOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#mathOperator}.
	 * @param ctx the parse tree
	 */
	void exitMathOperator(TinyDBParser.MathOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowStatement(TinyDBParser.ShowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowStatement(TinyDBParser.ShowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#shutdownStatement}.
	 * @param ctx the parse tree
	 */
	void enterShutdownStatement(TinyDBParser.ShutdownStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#shutdownStatement}.
	 * @param ctx the parse tree
	 */
	void exitShutdownStatement(TinyDBParser.ShutdownStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#dbName}.
	 * @param ctx the parse tree
	 */
	void enterDbName(TinyDBParser.DbNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#dbName}.
	 * @param ctx the parse tree
	 */
	void exitDbName(TinyDBParser.DbNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(TinyDBParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(TinyDBParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#attrName}.
	 * @param ctx the parse tree
	 */
	void enterAttrName(TinyDBParser.AttrNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#attrName}.
	 * @param ctx the parse tree
	 */
	void exitAttrName(TinyDBParser.AttrNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#attrNames}.
	 * @param ctx the parse tree
	 */
	void enterAttrNames(TinyDBParser.AttrNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#attrNames}.
	 * @param ctx the parse tree
	 */
	void exitAttrNames(TinyDBParser.AttrNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyDBParser#nullNotnull}.
	 * @param ctx the parse tree
	 */
	void enterNullNotnull(TinyDBParser.NullNotnullContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyDBParser#nullNotnull}.
	 * @param ctx the parse tree
	 */
	void exitNullNotnull(TinyDBParser.NullNotnullContext ctx);
}