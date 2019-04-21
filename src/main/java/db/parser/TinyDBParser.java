package db.parser;

// Generated from TinyDBParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TinyDBParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SPACE=1, SPEC_MYSQL_COMMENT=2, COMMENT_INPUT=3, LINE_COMMENT=4, AND=5, 
		AS=6, ASC=7, BY=8, CREATE=9, CROSS=10, DATABASE=11, DATABASES=12, DEFAULT=13, 
		DELETE=14, DESC=15, DISTINCT=16, DROP=17, FALSE=18, FROM=19, GROUP=20, 
		INNER=21, INSERT=22, INTO=23, IS=24, JOIN=25, KEY=26, LEFT=27, LIKE=28, 
		NATURAL=29, NOT=30, NULL_LITERAL=31, ON=32, OR=33, ORDER=34, OUTER=35, 
		PRIMARY=36, RIGHT=37, SELECT=38, SET=39, SHOW=40, TABLE=41, TRUE=42, UPDATE=43, 
		USE=44, VALUES=45, WHERE=46, WITH=47, XOR=48, INT=49, LONG=50, DOUBLE=51, 
		FLOAT=52, DECIMAL=53, NUMERIC=54, STRING=55, AVG=56, COUNT=57, MAX=58, 
		MIN=59, SUM=60, SHUTDOWN=61, VAR_ASSIGN=62, PLUS_ASSIGN=63, MINUS_ASSIGN=64, 
		MULT_ASSIGN=65, DIV_ASSIGN=66, MOD_ASSIGN=67, AND_ASSIGN=68, XOR_ASSIGN=69, 
		OR_ASSIGN=70, STAR=71, DIVIDE=72, MODULE=73, PLUS=74, MINUSMINUS=75, MINUS=76, 
		DIV=77, MOD=78, EQUAL_SYMBOL=79, GREATER_SYMBOL=80, LESS_SYMBOL=81, EXCLAMATION_SYMBOL=82, 
		BIT_NOT_OP=83, BIT_OR_OP=84, BIT_AND_OP=85, BIT_XOR_OP=86, DOT=87, LR_BRACKET=88, 
		RR_BRACKET=89, COMMA=90, SEMI=91, AT_SIGN=92, ZERO_DECIMAL=93, ONE_DECIMAL=94, 
		TWO_DECIMAL=95, SINGLE_QUOTE_SYMB=96, DOUBLE_QUOTE_SYMB=97, REVERSE_QUOTE_SYMB=98, 
		COLON_SYMB=99, START_NATIONAL_STRING_LITERAL=100, STRING_LITERAL=101, 
		DECIMAL_LITERAL=102, HEXADECIMAL_LITERAL=103, REAL_LITERAL=104, NULL_SPEC_LITERAL=105, 
		ID=106, ERROR_RECONGNIGION=107;
	public static final int
		RULE_root = 0, RULE_sqlStatements = 1, RULE_sqlStatement = 2, RULE_ddlStatement = 3, 
		RULE_dmlStatement = 4, RULE_administrationStatement = 5, RULE_createDatabase = 6, 
		RULE_createTable = 7, RULE_createDefinition = 8, RULE_colunmConstraint = 9, 
		RULE_dropDatabase = 10, RULE_dropTable = 11, RULE_useDatabase = 12, RULE_insertStatement = 13, 
		RULE_insertStatementValue = 14, RULE_selectStatement = 15, RULE_selectElements = 16, 
		RULE_selectElement = 17, RULE_fullColumnName = 18, RULE_fromClause = 19, 
		RULE_tableSources = 20, RULE_tableSource = 21, RULE_joinPart = 22, RULE_tableSourceItem = 23, 
		RULE_updateStatement = 24, RULE_updatedElement = 25, RULE_deleteStatement = 26, 
		RULE_expressions = 27, RULE_expression = 28, RULE_predicate = 29, RULE_expressionAtom = 30, 
		RULE_dataType = 31, RULE_lengthOneDimension = 32, RULE_constant = 33, 
		RULE_unaryOperator = 34, RULE_comparisonOperator = 35, RULE_logicalOperator = 36, 
		RULE_bitOperator = 37, RULE_mathOperator = 38, RULE_showStatement = 39, 
		RULE_shutdownStatement = 40, RULE_dbName = 41, RULE_tableName = 42, RULE_attrName = 43, 
		RULE_attrNames = 44, RULE_nullNotnull = 45, RULE_decimalLiteral = 46;
	public static final String[] ruleNames = {
		"root", "sqlStatements", "sqlStatement", "ddlStatement", "dmlStatement", 
		"administrationStatement", "createDatabase", "createTable", "createDefinition", 
		"colunmConstraint", "dropDatabase", "dropTable", "useDatabase", "insertStatement", 
		"insertStatementValue", "selectStatement", "selectElements", "selectElement", 
		"fullColumnName", "fromClause", "tableSources", "tableSource", "joinPart", 
		"tableSourceItem", "updateStatement", "updatedElement", "deleteStatement", 
		"expressions", "expression", "predicate", "expressionAtom", "dataType", 
		"lengthOneDimension", "constant", "unaryOperator", "comparisonOperator", 
		"logicalOperator", "bitOperator", "mathOperator", "showStatement", "shutdownStatement", 
		"dbName", "tableName", "attrName", "attrNames", "nullNotnull", "decimalLiteral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'AND'", "'AS'", "'ASC'", "'BY'", "'CREATE'", 
		"'CROSS'", "'DATABASE'", "'DATABASES'", "'DEFAULT'", "'DELETE'", "'DESC'", 
		"'DISTINCT'", "'DROP'", "'FALSE'", "'FROM'", "'GROUP'", "'INNER'", "'INSERT'", 
		"'INTO'", "'IS'", "'JOIN'", "'KEY'", "'LEFT'", "'LIKE'", "'NATURAL'", 
		"'NOT'", "'NULL'", "'ON'", "'OR'", "'ORDER'", "'OUTER'", "'PRIMARY'", 
		"'RIGHT'", "'SELECT'", "'SET'", "'SHOW'", "'TABLE'", "'TRUE'", "'UPDATE'", 
		"'USE'", "'VALUES'", "'WHERE'", "'WITH'", "'XOR'", "'INT'", "'LONG'", 
		"'DOUBLE'", "'FLOAT'", "'DECIMAL'", "'NUMERIC'", "'STRING'", "'AVG'", 
		"'COUNT'", "'MAX'", "'MIN'", "'SUM'", "'SHUTDOWN'", "':='", "'+='", "'-='", 
		"'*='", "'/='", "'%='", "'&='", "'^='", "'|='", "'*'", "'/'", "'%'", "'+'", 
		"'--'", "'-'", "'DIV'", "'MOD'", "'='", "'>'", "'<'", "'!'", "'~'", "'|'", 
		"'&'", "'^'", "'.'", "'('", "')'", "','", "';'", "'@'", "'0'", "'1'", 
		"'2'", "'''", "'\"'", "'`'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SPACE", "SPEC_MYSQL_COMMENT", "COMMENT_INPUT", "LINE_COMMENT", 
		"AND", "AS", "ASC", "BY", "CREATE", "CROSS", "DATABASE", "DATABASES", 
		"DEFAULT", "DELETE", "DESC", "DISTINCT", "DROP", "FALSE", "FROM", "GROUP", 
		"INNER", "INSERT", "INTO", "IS", "JOIN", "KEY", "LEFT", "LIKE", "NATURAL", 
		"NOT", "NULL_LITERAL", "ON", "OR", "ORDER", "OUTER", "PRIMARY", "RIGHT", 
		"SELECT", "SET", "SHOW", "TABLE", "TRUE", "UPDATE", "USE", "VALUES", "WHERE", 
		"WITH", "XOR", "INT", "LONG", "DOUBLE", "FLOAT", "DECIMAL", "NUMERIC", 
		"STRING", "AVG", "COUNT", "MAX", "MIN", "SUM", "SHUTDOWN", "VAR_ASSIGN", 
		"PLUS_ASSIGN", "MINUS_ASSIGN", "MULT_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", 
		"AND_ASSIGN", "XOR_ASSIGN", "OR_ASSIGN", "STAR", "DIVIDE", "MODULE", "PLUS", 
		"MINUSMINUS", "MINUS", "DIV", "MOD", "EQUAL_SYMBOL", "GREATER_SYMBOL", 
		"LESS_SYMBOL", "EXCLAMATION_SYMBOL", "BIT_NOT_OP", "BIT_OR_OP", "BIT_AND_OP", 
		"BIT_XOR_OP", "DOT", "LR_BRACKET", "RR_BRACKET", "COMMA", "SEMI", "AT_SIGN", 
		"ZERO_DECIMAL", "ONE_DECIMAL", "TWO_DECIMAL", "SINGLE_QUOTE_SYMB", "DOUBLE_QUOTE_SYMB", 
		"REVERSE_QUOTE_SYMB", "COLON_SYMB", "START_NATIONAL_STRING_LITERAL", "STRING_LITERAL", 
		"DECIMAL_LITERAL", "HEXADECIMAL_LITERAL", "REAL_LITERAL", "NULL_SPEC_LITERAL", 
		"ID", "ERROR_RECONGNIGION"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TinyDBParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TinyDBParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public SqlStatementsContext sqlStatements() {
			return getRuleContext(SqlStatementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TinyDBParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			sqlStatements();
			setState(95);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SqlStatementsContext extends ParserRuleContext {
		public List<SqlStatementContext> sqlStatement() {
			return getRuleContexts(SqlStatementContext.class);
		}
		public SqlStatementContext sqlStatement(int i) {
			return getRuleContext(SqlStatementContext.class,i);
		}
		public SqlStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStatements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitSqlStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStatementsContext sqlStatements() throws RecognitionException {
		SqlStatementsContext _localctx = new SqlStatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sqlStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << DELETE) | (1L << DROP) | (1L << INSERT) | (1L << SELECT) | (1L << SHOW) | (1L << UPDATE) | (1L << USE) | (1L << SHUTDOWN))) != 0)) {
				{
				{
				setState(97);
				sqlStatement();
				setState(98);
				match(SEMI);
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SqlStatementContext extends ParserRuleContext {
		public DdlStatementContext ddlStatement() {
			return getRuleContext(DdlStatementContext.class,0);
		}
		public DmlStatementContext dmlStatement() {
			return getRuleContext(DmlStatementContext.class,0);
		}
		public AdministrationStatementContext administrationStatement() {
			return getRuleContext(AdministrationStatementContext.class,0);
		}
		public SqlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitSqlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStatementContext sqlStatement() throws RecognitionException {
		SqlStatementContext _localctx = new SqlStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sqlStatement);
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case USE:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				ddlStatement();
				}
				break;
			case DELETE:
			case INSERT:
			case SELECT:
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				dmlStatement();
				}
				break;
			case SHOW:
			case SHUTDOWN:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				administrationStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DdlStatementContext extends ParserRuleContext {
		public CreateDatabaseContext createDatabase() {
			return getRuleContext(CreateDatabaseContext.class,0);
		}
		public CreateTableContext createTable() {
			return getRuleContext(CreateTableContext.class,0);
		}
		public DropDatabaseContext dropDatabase() {
			return getRuleContext(DropDatabaseContext.class,0);
		}
		public DropTableContext dropTable() {
			return getRuleContext(DropTableContext.class,0);
		}
		public UseDatabaseContext useDatabase() {
			return getRuleContext(UseDatabaseContext.class,0);
		}
		public DdlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ddlStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDdlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DdlStatementContext ddlStatement() throws RecognitionException {
		DdlStatementContext _localctx = new DdlStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ddlStatement);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				createDatabase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				createTable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				dropDatabase();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				dropTable();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(114);
				useDatabase();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DmlStatementContext extends ParserRuleContext {
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public InsertStatementContext insertStatement() {
			return getRuleContext(InsertStatementContext.class,0);
		}
		public UpdateStatementContext updateStatement() {
			return getRuleContext(UpdateStatementContext.class,0);
		}
		public DeleteStatementContext deleteStatement() {
			return getRuleContext(DeleteStatementContext.class,0);
		}
		public DmlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dmlStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDmlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DmlStatementContext dmlStatement() throws RecognitionException {
		DmlStatementContext _localctx = new DmlStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dmlStatement);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				selectStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				updateStatement();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				deleteStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdministrationStatementContext extends ParserRuleContext {
		public ShowStatementContext showStatement() {
			return getRuleContext(ShowStatementContext.class,0);
		}
		public ShutdownStatementContext shutdownStatement() {
			return getRuleContext(ShutdownStatementContext.class,0);
		}
		public AdministrationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_administrationStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitAdministrationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdministrationStatementContext administrationStatement() throws RecognitionException {
		AdministrationStatementContext _localctx = new AdministrationStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_administrationStatement);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SHOW:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				showStatement();
				}
				break;
			case SHUTDOWN:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				shutdownStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateDatabaseContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(TinyDBParser.CREATE, 0); }
		public TerminalNode DATABASE() { return getToken(TinyDBParser.DATABASE, 0); }
		public DbNameContext dbName() {
			return getRuleContext(DbNameContext.class,0);
		}
		public CreateDatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createDatabase; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitCreateDatabase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateDatabaseContext createDatabase() throws RecognitionException {
		CreateDatabaseContext _localctx = new CreateDatabaseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_createDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(CREATE);
			setState(128);
			match(DATABASE);
			setState(129);
			dbName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateTableContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(TinyDBParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(TinyDBParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<CreateDefinitionContext> createDefinition() {
			return getRuleContexts(CreateDefinitionContext.class);
		}
		public CreateDefinitionContext createDefinition(int i) {
			return getRuleContext(CreateDefinitionContext.class,i);
		}
		public CreateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitCreateTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableContext createTable() throws RecognitionException {
		CreateTableContext _localctx = new CreateTableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_createTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(CREATE);
			setState(132);
			match(TABLE);
			setState(133);
			tableName();
			setState(134);
			match(LR_BRACKET);
			setState(135);
			createDefinition();
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(136);
				match(COMMA);
				setState(137);
				createDefinition();
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(143);
			match(RR_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateDefinitionContext extends ParserRuleContext {
		public CreateDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createDefinition; }
	 
		public CreateDefinitionContext() { }
		public void copyFrom(CreateDefinitionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ColumnDeclarationContext extends CreateDefinitionContext {
		public AttrNameContext attrName() {
			return getRuleContext(AttrNameContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public List<ColunmConstraintContext> colunmConstraint() {
			return getRuleContexts(ColunmConstraintContext.class);
		}
		public ColunmConstraintContext colunmConstraint(int i) {
			return getRuleContext(ColunmConstraintContext.class,i);
		}
		public ColumnDeclarationContext(CreateDefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitColumnDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstraintDeclarationContext extends CreateDefinitionContext {
		public TerminalNode PRIMARY() { return getToken(TinyDBParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(TinyDBParser.KEY, 0); }
		public AttrNamesContext attrNames() {
			return getRuleContext(AttrNamesContext.class,0);
		}
		public ConstraintDeclarationContext(CreateDefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitConstraintDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateDefinitionContext createDefinition() throws RecognitionException {
		CreateDefinitionContext _localctx = new CreateDefinitionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_createDefinition);
		int _la;
		try {
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new ColumnDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				attrName();
				setState(146);
				dataType();
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << NULL_LITERAL) | (1L << PRIMARY))) != 0)) {
					{
					{
					setState(147);
					colunmConstraint();
					}
					}
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case PRIMARY:
				_localctx = new ConstraintDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(PRIMARY);
				setState(154);
				match(KEY);
				setState(155);
				match(LR_BRACKET);
				setState(156);
				attrNames();
				setState(157);
				match(RR_BRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColunmConstraintContext extends ParserRuleContext {
		public ColunmConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colunmConstraint; }
	 
		public ColunmConstraintContext() { }
		public void copyFrom(ColunmConstraintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrimaryKeyColumnConstraintContext extends ColunmConstraintContext {
		public TerminalNode PRIMARY() { return getToken(TinyDBParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(TinyDBParser.KEY, 0); }
		public PrimaryKeyColumnConstraintContext(ColunmConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitPrimaryKeyColumnConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullColumnConstraintContext extends ColunmConstraintContext {
		public NullNotnullContext nullNotnull() {
			return getRuleContext(NullNotnullContext.class,0);
		}
		public NullColumnConstraintContext(ColunmConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitNullColumnConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColunmConstraintContext colunmConstraint() throws RecognitionException {
		ColunmConstraintContext _localctx = new ColunmConstraintContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_colunmConstraint);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case NULL_LITERAL:
				_localctx = new NullColumnConstraintContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				nullNotnull();
				}
				break;
			case PRIMARY:
				_localctx = new PrimaryKeyColumnConstraintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				match(PRIMARY);
				setState(163);
				match(KEY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DropDatabaseContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(TinyDBParser.DROP, 0); }
		public TerminalNode DATABASE() { return getToken(TinyDBParser.DATABASE, 0); }
		public DbNameContext dbName() {
			return getRuleContext(DbNameContext.class,0);
		}
		public DropDatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropDatabase; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDropDatabase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropDatabaseContext dropDatabase() throws RecognitionException {
		DropDatabaseContext _localctx = new DropDatabaseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_dropDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(DROP);
			setState(167);
			match(DATABASE);
			setState(168);
			dbName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DropTableContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(TinyDBParser.DROP, 0); }
		public TerminalNode TABLE() { return getToken(TinyDBParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public DropTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDropTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropTableContext dropTable() throws RecognitionException {
		DropTableContext _localctx = new DropTableContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_dropTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(DROP);
			setState(171);
			match(TABLE);
			setState(172);
			tableName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UseDatabaseContext extends ParserRuleContext {
		public TerminalNode USE() { return getToken(TinyDBParser.USE, 0); }
		public TerminalNode DATABASE() { return getToken(TinyDBParser.DATABASE, 0); }
		public DbNameContext dbName() {
			return getRuleContext(DbNameContext.class,0);
		}
		public UseDatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useDatabase; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitUseDatabase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseDatabaseContext useDatabase() throws RecognitionException {
		UseDatabaseContext _localctx = new UseDatabaseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_useDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(USE);
			setState(175);
			match(DATABASE);
			setState(176);
			dbName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InsertStatementContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(TinyDBParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(TinyDBParser.INTO, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public InsertStatementValueContext insertStatementValue() {
			return getRuleContext(InsertStatementValueContext.class,0);
		}
		public AttrNamesContext attrNames() {
			return getRuleContext(AttrNamesContext.class,0);
		}
		public InsertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitInsertStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStatementContext insertStatement() throws RecognitionException {
		InsertStatementContext _localctx = new InsertStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(INSERT);
			setState(179);
			match(INTO);
			setState(180);
			tableName();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LR_BRACKET) {
				{
				setState(181);
				match(LR_BRACKET);
				setState(182);
				attrNames();
				setState(183);
				match(RR_BRACKET);
				}
			}

			setState(187);
			insertStatementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InsertStatementValueContext extends ParserRuleContext {
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode VALUES() { return getToken(TinyDBParser.VALUES, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public InsertStatementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatementValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitInsertStatementValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStatementValueContext insertStatementValue() throws RecognitionException {
		InsertStatementValueContext _localctx = new InsertStatementValueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_insertStatementValue);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				selectStatement();
				}
				break;
			case VALUES:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				match(VALUES);
				setState(191);
				match(LR_BRACKET);
				setState(192);
				expressions();
				setState(193);
				match(RR_BRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(TinyDBParser.SELECT, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public FromClauseContext fromClause() {
			return getRuleContext(FromClauseContext.class,0);
		}
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_selectStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(SELECT);
			setState(198);
			selectElements();
			setState(199);
			fromClause();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectElementsContext extends ParserRuleContext {
		public Token star;
		public List<SelectElementContext> selectElement() {
			return getRuleContexts(SelectElementContext.class);
		}
		public SelectElementContext selectElement(int i) {
			return getRuleContext(SelectElementContext.class,i);
		}
		public SelectElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitSelectElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementsContext selectElements() throws RecognitionException {
		SelectElementsContext _localctx = new SelectElementsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_selectElements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				{
				setState(201);
				((SelectElementsContext)_localctx).star = match(STAR);
				}
				break;
			case ID:
				{
				setState(202);
				selectElement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(205);
				match(COMMA);
				setState(206);
				selectElement();
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectElementContext extends ParserRuleContext {
		public SelectElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElement; }
	 
		public SelectElementContext() { }
		public void copyFrom(SelectElementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SelectColumnElementContext extends SelectElementContext {
		public AttrNameContext alias;
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public AttrNameContext attrName() {
			return getRuleContext(AttrNameContext.class,0);
		}
		public TerminalNode AS() { return getToken(TinyDBParser.AS, 0); }
		public SelectColumnElementContext(SelectElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitSelectColumnElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementContext selectElement() throws RecognitionException {
		SelectElementContext _localctx = new SelectElementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_selectElement);
		int _la;
		try {
			_localctx = new SelectColumnElementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			fullColumnName();
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(213);
					match(AS);
					}
				}

				setState(216);
				((SelectColumnElementContext)_localctx).alias = attrName();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FullColumnNameContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public AttrNameContext attrName() {
			return getRuleContext(AttrNameContext.class,0);
		}
		public FullColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitFullColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullColumnNameContext fullColumnName() throws RecognitionException {
		FullColumnNameContext _localctx = new FullColumnNameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_fullColumnName);
		try {
			setState(224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				tableName();
				setState(220);
				match(DOT);
				setState(221);
				attrName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				attrName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromClauseContext extends ParserRuleContext {
		public ExpressionContext whereExpr;
		public TerminalNode FROM() { return getToken(TinyDBParser.FROM, 0); }
		public TableSourcesContext tableSources() {
			return getRuleContext(TableSourcesContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(TinyDBParser.WHERE, 0); }
		public TerminalNode GROUP() { return getToken(TinyDBParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(TinyDBParser.BY, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FromClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromClause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitFromClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromClauseContext fromClause() throws RecognitionException {
		FromClauseContext _localctx = new FromClauseContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_fromClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(FROM);
			setState(227);
			tableSources();
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(228);
				match(WHERE);
				setState(229);
				((FromClauseContext)_localctx).whereExpr = expression(0);
				}
			}

			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(232);
				match(GROUP);
				setState(233);
				match(BY);
				setState(234);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableSourcesContext extends ParserRuleContext {
		public List<TableSourceContext> tableSource() {
			return getRuleContexts(TableSourceContext.class);
		}
		public TableSourceContext tableSource(int i) {
			return getRuleContext(TableSourceContext.class,i);
		}
		public TableSourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSources; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitTableSources(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourcesContext tableSources() throws RecognitionException {
		TableSourcesContext _localctx = new TableSourcesContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_tableSources);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			tableSource();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(238);
				match(COMMA);
				setState(239);
				tableSource();
				}
				}
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableSourceContext extends ParserRuleContext {
		public TableSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSource; }
	 
		public TableSourceContext() { }
		public void copyFrom(TableSourceContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TableSourceBaseContext extends TableSourceContext {
		public TableSourceItemContext tableSourceItem() {
			return getRuleContext(TableSourceItemContext.class,0);
		}
		public List<JoinPartContext> joinPart() {
			return getRuleContexts(JoinPartContext.class);
		}
		public JoinPartContext joinPart(int i) {
			return getRuleContext(JoinPartContext.class,i);
		}
		public TableSourceBaseContext(TableSourceContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitTableSourceBase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourceContext tableSource() throws RecognitionException {
		TableSourceContext _localctx = new TableSourceContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_tableSource);
		int _la;
		try {
			_localctx = new TableSourceBaseContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			tableSourceItem();
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CROSS) | (1L << INNER) | (1L << JOIN) | (1L << LEFT) | (1L << NATURAL) | (1L << RIGHT))) != 0)) {
				{
				{
				setState(246);
				joinPart();
				}
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JoinPartContext extends ParserRuleContext {
		public JoinPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinPart; }
	 
		public JoinPartContext() { }
		public void copyFrom(JoinPartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InnerJoinContext extends JoinPartContext {
		public TerminalNode JOIN() { return getToken(TinyDBParser.JOIN, 0); }
		public TableSourceItemContext tableSourceItem() {
			return getRuleContext(TableSourceItemContext.class,0);
		}
		public TerminalNode ON() { return getToken(TinyDBParser.ON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INNER() { return getToken(TinyDBParser.INNER, 0); }
		public TerminalNode CROSS() { return getToken(TinyDBParser.CROSS, 0); }
		public InnerJoinContext(JoinPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitInnerJoin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NaturalJoinContext extends JoinPartContext {
		public TerminalNode NATURAL() { return getToken(TinyDBParser.NATURAL, 0); }
		public TerminalNode JOIN() { return getToken(TinyDBParser.JOIN, 0); }
		public TableSourceItemContext tableSourceItem() {
			return getRuleContext(TableSourceItemContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(TinyDBParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(TinyDBParser.RIGHT, 0); }
		public TerminalNode OUTER() { return getToken(TinyDBParser.OUTER, 0); }
		public NaturalJoinContext(JoinPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitNaturalJoin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OuterJoinContext extends JoinPartContext {
		public TerminalNode JOIN() { return getToken(TinyDBParser.JOIN, 0); }
		public TableSourceItemContext tableSourceItem() {
			return getRuleContext(TableSourceItemContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(TinyDBParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(TinyDBParser.RIGHT, 0); }
		public TerminalNode ON() { return getToken(TinyDBParser.ON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode OUTER() { return getToken(TinyDBParser.OUTER, 0); }
		public OuterJoinContext(JoinPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitOuterJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinPartContext joinPart() throws RecognitionException {
		JoinPartContext _localctx = new JoinPartContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_joinPart);
		int _la;
		try {
			setState(279);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CROSS:
			case INNER:
			case JOIN:
				_localctx = new InnerJoinContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CROSS || _la==INNER) {
					{
					setState(252);
					_la = _input.LA(1);
					if ( !(_la==CROSS || _la==INNER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(255);
				match(JOIN);
				setState(256);
				tableSourceItem();
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(257);
					match(ON);
					setState(258);
					expression(0);
					}
				}

				}
				break;
			case LEFT:
			case RIGHT:
				_localctx = new OuterJoinContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				_la = _input.LA(1);
				if ( !(_la==LEFT || _la==RIGHT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(262);
					match(OUTER);
					}
				}

				setState(265);
				match(JOIN);
				setState(266);
				tableSourceItem();
				{
				setState(267);
				match(ON);
				setState(268);
				expression(0);
				}
				}
				break;
			case NATURAL:
				_localctx = new NaturalJoinContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				match(NATURAL);
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LEFT || _la==RIGHT) {
					{
					setState(271);
					_la = _input.LA(1);
					if ( !(_la==LEFT || _la==RIGHT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(273);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==OUTER) {
						{
						setState(272);
						match(OUTER);
						}
					}

					}
				}

				setState(277);
				match(JOIN);
				setState(278);
				tableSourceItem();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableSourceItemContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TableSourceItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSourceItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitTableSourceItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourceItemContext tableSourceItem() throws RecognitionException {
		TableSourceItemContext _localctx = new TableSourceItemContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_tableSourceItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			tableName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateStatementContext extends ParserRuleContext {
		public TableNameContext alias;
		public TerminalNode UPDATE() { return getToken(TinyDBParser.UPDATE, 0); }
		public List<TableNameContext> tableName() {
			return getRuleContexts(TableNameContext.class);
		}
		public TableNameContext tableName(int i) {
			return getRuleContext(TableNameContext.class,i);
		}
		public TerminalNode SET() { return getToken(TinyDBParser.SET, 0); }
		public List<UpdatedElementContext> updatedElement() {
			return getRuleContexts(UpdatedElementContext.class);
		}
		public UpdatedElementContext updatedElement(int i) {
			return getRuleContext(UpdatedElementContext.class,i);
		}
		public TerminalNode WHERE() { return getToken(TinyDBParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AS() { return getToken(TinyDBParser.AS, 0); }
		public UpdateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitUpdateStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateStatementContext updateStatement() throws RecognitionException {
		UpdateStatementContext _localctx = new UpdateStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(UPDATE);
			setState(284);
			tableName();
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(285);
					match(AS);
					}
				}

				setState(288);
				((UpdateStatementContext)_localctx).alias = tableName();
				}
			}

			setState(291);
			match(SET);
			setState(292);
			updatedElement();
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(293);
				match(COMMA);
				setState(294);
				updatedElement();
				}
				}
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(300);
				match(WHERE);
				setState(301);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdatedElementContext extends ParserRuleContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UpdatedElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updatedElement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitUpdatedElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdatedElementContext updatedElement() throws RecognitionException {
		UpdatedElementContext _localctx = new UpdatedElementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_updatedElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			fullColumnName();
			setState(305);
			match(EQUAL_SYMBOL);
			setState(306);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteStatementContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(TinyDBParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(TinyDBParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(TinyDBParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeleteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDeleteStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteStatementContext deleteStatement() throws RecognitionException {
		DeleteStatementContext _localctx = new DeleteStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(DELETE);
			setState(309);
			match(FROM);
			setState(310);
			tableName();
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(311);
				match(WHERE);
				setState(312);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionsContext expressions() throws RecognitionException {
		ExpressionsContext _localctx = new ExpressionsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			expression(0);
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(316);
				match(COMMA);
				setState(317);
				expression(0);
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotExpressionContext extends ExpressionContext {
		public Token notOperator;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(TinyDBParser.NOT, 0); }
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalOperatorContext logicalOperator() {
			return getRuleContext(LogicalOperatorContext.class,0);
		}
		public LogicalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PredicateExpressionContext extends ExpressionContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public PredicateExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitPredicateExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(324);
				((NotExpressionContext)_localctx).notOperator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==EXCLAMATION_SYMBOL) ) {
					((NotExpressionContext)_localctx).notOperator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(325);
				expression(3);
				}
				break;
			case 2:
				{
				_localctx = new PredicateExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(326);
				predicate(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(335);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalExpressionContext(new ExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(329);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(330);
					logicalOperator();
					setState(331);
					expression(3);
					}
					} 
				}
				setState(337);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
	 
		public PredicateContext() { }
		public void copyFrom(PredicateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionAtomPredicateContext extends PredicateContext {
		public ExpressionAtomContext expressionAtom() {
			return getRuleContext(ExpressionAtomContext.class,0);
		}
		public ExpressionAtomPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitExpressionAtomPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryComparasionPredicateContext extends PredicateContext {
		public PredicateContext left;
		public PredicateContext right;
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public BinaryComparasionPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitBinaryComparasionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsNullPredicateContext extends PredicateContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode IS() { return getToken(TinyDBParser.IS, 0); }
		public NullNotnullContext nullNotnull() {
			return getRuleContext(NullNotnullContext.class,0);
		}
		public IsNullPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitIsNullPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		return predicate(0);
	}

	private PredicateContext predicate(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PredicateContext _localctx = new PredicateContext(_ctx, _parentState);
		PredicateContext _prevctx = _localctx;
		int _startState = 58;
		enterRecursionRule(_localctx, 58, RULE_predicate, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExpressionAtomPredicateContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(339);
			expressionAtom(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(350);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(348);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryComparasionPredicateContext(new PredicateContext(_parentctx, _parentState));
						((BinaryComparasionPredicateContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(341);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(342);
						comparisonOperator();
						setState(343);
						((BinaryComparasionPredicateContext)_localctx).right = predicate(3);
						}
						break;
					case 2:
						{
						_localctx = new IsNullPredicateContext(new PredicateContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(345);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(346);
						match(IS);
						setState(347);
						nullNotnull();
						}
						break;
					}
					} 
				}
				setState(352);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionAtomContext extends ParserRuleContext {
		public ExpressionAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionAtom; }
	 
		public ExpressionAtomContext() { }
		public void copyFrom(ExpressionAtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnaryExpressionAtomContext extends ExpressionAtomContext {
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public ExpressionAtomContext expressionAtom() {
			return getRuleContext(ExpressionAtomContext.class,0);
		}
		public UnaryExpressionAtomContext(ExpressionAtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitUnaryExpressionAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubqueryExpessionAtomContext extends ExpressionAtomContext {
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public SubqueryExpessionAtomContext(ExpressionAtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitSubqueryExpessionAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstantExpressionAtomContext extends ExpressionAtomContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantExpressionAtomContext(ExpressionAtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitConstantExpressionAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FullColumnNameExpressionAtomContext extends ExpressionAtomContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public FullColumnNameExpressionAtomContext(ExpressionAtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitFullColumnNameExpressionAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitExpressionAtomContext extends ExpressionAtomContext {
		public ExpressionAtomContext left;
		public ExpressionAtomContext right;
		public BitOperatorContext bitOperator() {
			return getRuleContext(BitOperatorContext.class,0);
		}
		public List<ExpressionAtomContext> expressionAtom() {
			return getRuleContexts(ExpressionAtomContext.class);
		}
		public ExpressionAtomContext expressionAtom(int i) {
			return getRuleContext(ExpressionAtomContext.class,i);
		}
		public BitExpressionAtomContext(ExpressionAtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitBitExpressionAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NestedExpressionAtomContext extends ExpressionAtomContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NestedExpressionAtomContext(ExpressionAtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitNestedExpressionAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MathExpressionAtomContext extends ExpressionAtomContext {
		public ExpressionAtomContext left;
		public ExpressionAtomContext right;
		public MathOperatorContext mathOperator() {
			return getRuleContext(MathOperatorContext.class,0);
		}
		public List<ExpressionAtomContext> expressionAtom() {
			return getRuleContexts(ExpressionAtomContext.class);
		}
		public ExpressionAtomContext expressionAtom(int i) {
			return getRuleContext(ExpressionAtomContext.class,i);
		}
		public MathExpressionAtomContext(ExpressionAtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitMathExpressionAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionAtomContext expressionAtom() throws RecognitionException {
		return expressionAtom(0);
	}

	private ExpressionAtomContext expressionAtom(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionAtomContext _localctx = new ExpressionAtomContext(_ctx, _parentState);
		ExpressionAtomContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_expressionAtom, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				_localctx = new ConstantExpressionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(354);
				constant();
				}
				break;
			case 2:
				{
				_localctx = new FullColumnNameExpressionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(355);
				fullColumnName();
				}
				break;
			case 3:
				{
				_localctx = new UnaryExpressionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(356);
				unaryOperator();
				setState(357);
				expressionAtom(5);
				}
				break;
			case 4:
				{
				_localctx = new NestedExpressionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(359);
				match(LR_BRACKET);
				setState(360);
				expression(0);
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(361);
					match(COMMA);
					setState(362);
					expression(0);
					}
					}
					setState(367);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(368);
				match(RR_BRACKET);
				}
				break;
			case 5:
				{
				_localctx = new SubqueryExpessionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(370);
				match(LR_BRACKET);
				setState(371);
				selectStatement();
				setState(372);
				match(RR_BRACKET);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(386);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(384);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						_localctx = new BitExpressionAtomContext(new ExpressionAtomContext(_parentctx, _parentState));
						((BitExpressionAtomContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expressionAtom);
						setState(376);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(377);
						bitOperator();
						setState(378);
						((BitExpressionAtomContext)_localctx).right = expressionAtom(3);
						}
						break;
					case 2:
						{
						_localctx = new MathExpressionAtomContext(new ExpressionAtomContext(_parentctx, _parentState));
						((MathExpressionAtomContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expressionAtom);
						setState(380);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(381);
						mathOperator();
						setState(382);
						((MathExpressionAtomContext)_localctx).right = expressionAtom(2);
						}
						break;
					}
					} 
				}
				setState(388);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(TinyDBParser.INT, 0); }
		public TerminalNode LONG() { return getToken(TinyDBParser.LONG, 0); }
		public TerminalNode FLOAT() { return getToken(TinyDBParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(TinyDBParser.DOUBLE, 0); }
		public LengthOneDimensionContext lengthOneDimension() {
			return getRuleContext(LengthOneDimensionContext.class,0);
		}
		public TerminalNode STRING() { return getToken(TinyDBParser.STRING, 0); }
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDataType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_dataType);
		try {
			setState(395);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(389);
				match(INT);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(390);
				match(LONG);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(391);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(392);
				match(DOUBLE);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(393);
				match(STRING);
				}
				setState(394);
				lengthOneDimension();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LengthOneDimensionContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(TinyDBParser.DECIMAL_LITERAL, 0); }
		public LengthOneDimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lengthOneDimension; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitLengthOneDimension(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LengthOneDimensionContext lengthOneDimension() throws RecognitionException {
		LengthOneDimensionContext _localctx = new LengthOneDimensionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_lengthOneDimension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			match(LR_BRACKET);
			setState(398);
			match(DECIMAL_LITERAL);
			setState(399);
			match(RR_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(TinyDBParser.STRING_LITERAL, 0); }
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public TerminalNode REAL_LITERAL() { return getToken(TinyDBParser.REAL_LITERAL, 0); }
		public NullNotnullContext nullNotnull() {
			return getRuleContext(NullNotnullContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_constant);
		try {
			setState(407);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(401);
				match(STRING_LITERAL);
				}
				break;
			case ZERO_DECIMAL:
			case ONE_DECIMAL:
			case TWO_DECIMAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
				decimalLiteral();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(403);
				match(MINUS);
				setState(404);
				decimalLiteral();
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(405);
				match(REAL_LITERAL);
				}
				break;
			case NOT:
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(406);
				nullNotnull();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOperatorContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(TinyDBParser.NOT, 0); }
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_unaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			_la = _input.LA(1);
			if ( !(((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (NOT - 30)) | (1L << (PLUS - 30)) | (1L << (MINUS - 30)) | (1L << (EXCLAMATION_SYMBOL - 30)) | (1L << (BIT_NOT_OP - 30)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_comparisonOperator);
		try {
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(411);
				match(EQUAL_SYMBOL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				match(GREATER_SYMBOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(413);
				match(LESS_SYMBOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(414);
				match(LESS_SYMBOL);
				setState(415);
				match(EQUAL_SYMBOL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(416);
				match(GREATER_SYMBOL);
				setState(417);
				match(EQUAL_SYMBOL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(418);
				match(LESS_SYMBOL);
				setState(419);
				match(GREATER_SYMBOL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(420);
				match(EXCLAMATION_SYMBOL);
				setState(421);
				match(EQUAL_SYMBOL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(422);
				match(LESS_SYMBOL);
				setState(423);
				match(EQUAL_SYMBOL);
				setState(424);
				match(GREATER_SYMBOL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalOperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(TinyDBParser.AND, 0); }
		public TerminalNode XOR() { return getToken(TinyDBParser.XOR, 0); }
		public TerminalNode OR() { return getToken(TinyDBParser.OR, 0); }
		public LogicalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitLogicalOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOperatorContext logicalOperator() throws RecognitionException {
		LogicalOperatorContext _localctx = new LogicalOperatorContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_logicalOperator);
		try {
			setState(434);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(427);
				match(AND);
				}
				break;
			case BIT_AND_OP:
				enterOuterAlt(_localctx, 2);
				{
				setState(428);
				match(BIT_AND_OP);
				setState(429);
				match(BIT_AND_OP);
				}
				break;
			case XOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(430);
				match(XOR);
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 4);
				{
				setState(431);
				match(OR);
				}
				break;
			case BIT_OR_OP:
				enterOuterAlt(_localctx, 5);
				{
				setState(432);
				match(BIT_OR_OP);
				setState(433);
				match(BIT_OR_OP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitOperatorContext extends ParserRuleContext {
		public BitOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitBitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitOperatorContext bitOperator() throws RecognitionException {
		BitOperatorContext _localctx = new BitOperatorContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_bitOperator);
		try {
			setState(443);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LESS_SYMBOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(436);
				match(LESS_SYMBOL);
				setState(437);
				match(LESS_SYMBOL);
				}
				break;
			case GREATER_SYMBOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(438);
				match(GREATER_SYMBOL);
				setState(439);
				match(GREATER_SYMBOL);
				}
				break;
			case BIT_AND_OP:
				enterOuterAlt(_localctx, 3);
				{
				setState(440);
				match(BIT_AND_OP);
				}
				break;
			case BIT_XOR_OP:
				enterOuterAlt(_localctx, 4);
				{
				setState(441);
				match(BIT_XOR_OP);
				}
				break;
			case BIT_OR_OP:
				enterOuterAlt(_localctx, 5);
				{
				setState(442);
				match(BIT_OR_OP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MathOperatorContext extends ParserRuleContext {
		public TerminalNode DIV() { return getToken(TinyDBParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(TinyDBParser.MOD, 0); }
		public MathOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitMathOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathOperatorContext mathOperator() throws RecognitionException {
		MathOperatorContext _localctx = new MathOperatorContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_mathOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (STAR - 71)) | (1L << (DIVIDE - 71)) | (1L << (MODULE - 71)) | (1L << (PLUS - 71)) | (1L << (MINUSMINUS - 71)) | (1L << (MINUS - 71)) | (1L << (DIV - 71)) | (1L << (MOD - 71)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShowStatementContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(TinyDBParser.SHOW, 0); }
		public TerminalNode DATABASES() { return getToken(TinyDBParser.DATABASES, 0); }
		public TerminalNode DATABASE() { return getToken(TinyDBParser.DATABASE, 0); }
		public DbNameContext dbName() {
			return getRuleContext(DbNameContext.class,0);
		}
		public TerminalNode TABLE() { return getToken(TinyDBParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ShowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitShowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowStatementContext showStatement() throws RecognitionException {
		ShowStatementContext _localctx = new ShowStatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_showStatement);
		try {
			setState(455);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(447);
				match(SHOW);
				setState(448);
				match(DATABASES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(449);
				match(SHOW);
				setState(450);
				match(DATABASE);
				setState(451);
				dbName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(452);
				match(SHOW);
				setState(453);
				match(TABLE);
				setState(454);
				tableName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShutdownStatementContext extends ParserRuleContext {
		public TerminalNode SHUTDOWN() { return getToken(TinyDBParser.SHUTDOWN, 0); }
		public ShutdownStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shutdownStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitShutdownStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShutdownStatementContext shutdownStatement() throws RecognitionException {
		ShutdownStatementContext _localctx = new ShutdownStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_shutdownStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			match(SHUTDOWN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DbNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TinyDBParser.ID, 0); }
		public DbNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dbName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDbName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DbNameContext dbName() throws RecognitionException {
		DbNameContext _localctx = new DbNameContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_dbName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TinyDBParser.ID, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TinyDBParser.ID, 0); }
		public AttrNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitAttrName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttrNameContext attrName() throws RecognitionException {
		AttrNameContext _localctx = new AttrNameContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrNamesContext extends ParserRuleContext {
		public List<AttrNameContext> attrName() {
			return getRuleContexts(AttrNameContext.class);
		}
		public AttrNameContext attrName(int i) {
			return getRuleContext(AttrNameContext.class,i);
		}
		public AttrNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrNames; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitAttrNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttrNamesContext attrNames() throws RecognitionException {
		AttrNamesContext _localctx = new AttrNamesContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_attrNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			attrName();
			setState(470);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(466);
				match(COMMA);
				setState(467);
				attrName();
				}
				}
				setState(472);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NullNotnullContext extends ParserRuleContext {
		public TerminalNode NULL_LITERAL() { return getToken(TinyDBParser.NULL_LITERAL, 0); }
		public TerminalNode NOT() { return getToken(TinyDBParser.NOT, 0); }
		public NullNotnullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullNotnull; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitNullNotnull(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullNotnullContext nullNotnull() throws RecognitionException {
		NullNotnullContext _localctx = new NullNotnullContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_nullNotnull);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(473);
				match(NOT);
				}
			}

			setState(476);
			match(NULL_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecimalLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(TinyDBParser.DECIMAL_LITERAL, 0); }
		public TerminalNode ZERO_DECIMAL() { return getToken(TinyDBParser.ZERO_DECIMAL, 0); }
		public TerminalNode ONE_DECIMAL() { return getToken(TinyDBParser.ONE_DECIMAL, 0); }
		public TerminalNode TWO_DECIMAL() { return getToken(TinyDBParser.TWO_DECIMAL, 0); }
		public DecimalLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimalLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDecimalLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalLiteralContext decimalLiteral() throws RecognitionException {
		DecimalLiteralContext _localctx = new DecimalLiteralContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_decimalLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			_la = _input.LA(1);
			if ( !(((((_la - 93)) & ~0x3f) == 0 && ((1L << (_la - 93)) & ((1L << (ZERO_DECIMAL - 93)) | (1L << (ONE_DECIMAL - 93)) | (1L << (TWO_DECIMAL - 93)) | (1L << (DECIMAL_LITERAL - 93)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 28:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 29:
			return predicate_sempred((PredicateContext)_localctx, predIndex);
		case 30:
			return expressionAtom_sempred((ExpressionAtomContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean predicate_sempred(PredicateContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean expressionAtom_sempred(ExpressionAtomContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3m\u01e3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\2\3\3\3\3\3\3\7\3g\n\3\f\3"+
		"\16\3j\13\3\3\4\3\4\3\4\5\4o\n\4\3\5\3\5\3\5\3\5\3\5\5\5v\n\5\3\6\3\6"+
		"\3\6\3\6\5\6|\n\6\3\7\3\7\5\7\u0080\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\7\t\u008d\n\t\f\t\16\t\u0090\13\t\3\t\3\t\3\n\3\n\3\n\7"+
		"\n\u0097\n\n\f\n\16\n\u009a\13\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a2\n\n"+
		"\3\13\3\13\3\13\5\13\u00a7\n\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00bc\n\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00c6\n\20\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\5\22\u00ce\n\22\3\22\3\22\7\22\u00d2\n\22\f\22\16\22\u00d5"+
		"\13\22\3\23\3\23\5\23\u00d9\n\23\3\23\5\23\u00dc\n\23\3\24\3\24\3\24\3"+
		"\24\3\24\5\24\u00e3\n\24\3\25\3\25\3\25\3\25\5\25\u00e9\n\25\3\25\3\25"+
		"\3\25\5\25\u00ee\n\25\3\26\3\26\3\26\7\26\u00f3\n\26\f\26\16\26\u00f6"+
		"\13\26\3\27\3\27\7\27\u00fa\n\27\f\27\16\27\u00fd\13\27\3\30\5\30\u0100"+
		"\n\30\3\30\3\30\3\30\3\30\5\30\u0106\n\30\3\30\3\30\5\30\u010a\n\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0114\n\30\5\30\u0116\n\30"+
		"\3\30\3\30\5\30\u011a\n\30\3\31\3\31\3\32\3\32\3\32\5\32\u0121\n\32\3"+
		"\32\5\32\u0124\n\32\3\32\3\32\3\32\3\32\7\32\u012a\n\32\f\32\16\32\u012d"+
		"\13\32\3\32\3\32\5\32\u0131\n\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\5\34\u013c\n\34\3\35\3\35\3\35\7\35\u0141\n\35\f\35\16\35\u0144"+
		"\13\35\3\36\3\36\3\36\3\36\5\36\u014a\n\36\3\36\3\36\3\36\3\36\7\36\u0150"+
		"\n\36\f\36\16\36\u0153\13\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\7\37\u015f\n\37\f\37\16\37\u0162\13\37\3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \7 \u016e\n \f \16 \u0171\13 \3 \3 \3 \3 \3 \3 \5 \u0179\n \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \7 \u0183\n \f \16 \u0186\13 \3!\3!\3!\3!\3!\3!\5"+
		"!\u018e\n!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\5#\u019a\n#\3$\3$\3%\3%\3"+
		"%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u01ac\n%\3&\3&\3&\3&\3&\3&\3&\5"+
		"&\u01b5\n&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01be\n\'\3(\3(\3)\3)\3)\3"+
		")\3)\3)\3)\3)\5)\u01ca\n)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\7.\u01d7\n"+
		".\f.\16.\u01da\13.\3/\5/\u01dd\n/\3/\3/\3\60\3\60\3\60\2\5:<>\61\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT"+
		"VXZ\\^\2\b\4\2\f\f\27\27\4\2\35\35\'\'\4\2  TT\6\2  LLNNTU\3\2IP\4\2_"+
		"ahh\2\u0200\2`\3\2\2\2\4h\3\2\2\2\6n\3\2\2\2\bu\3\2\2\2\n{\3\2\2\2\f\177"+
		"\3\2\2\2\16\u0081\3\2\2\2\20\u0085\3\2\2\2\22\u00a1\3\2\2\2\24\u00a6\3"+
		"\2\2\2\26\u00a8\3\2\2\2\30\u00ac\3\2\2\2\32\u00b0\3\2\2\2\34\u00b4\3\2"+
		"\2\2\36\u00c5\3\2\2\2 \u00c7\3\2\2\2\"\u00cd\3\2\2\2$\u00d6\3\2\2\2&\u00e2"+
		"\3\2\2\2(\u00e4\3\2\2\2*\u00ef\3\2\2\2,\u00f7\3\2\2\2.\u0119\3\2\2\2\60"+
		"\u011b\3\2\2\2\62\u011d\3\2\2\2\64\u0132\3\2\2\2\66\u0136\3\2\2\28\u013d"+
		"\3\2\2\2:\u0149\3\2\2\2<\u0154\3\2\2\2>\u0178\3\2\2\2@\u018d\3\2\2\2B"+
		"\u018f\3\2\2\2D\u0199\3\2\2\2F\u019b\3\2\2\2H\u01ab\3\2\2\2J\u01b4\3\2"+
		"\2\2L\u01bd\3\2\2\2N\u01bf\3\2\2\2P\u01c9\3\2\2\2R\u01cb\3\2\2\2T\u01cd"+
		"\3\2\2\2V\u01cf\3\2\2\2X\u01d1\3\2\2\2Z\u01d3\3\2\2\2\\\u01dc\3\2\2\2"+
		"^\u01e0\3\2\2\2`a\5\4\3\2ab\7\2\2\3b\3\3\2\2\2cd\5\6\4\2de\7]\2\2eg\3"+
		"\2\2\2fc\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\5\3\2\2\2jh\3\2\2\2ko"+
		"\5\b\5\2lo\5\n\6\2mo\5\f\7\2nk\3\2\2\2nl\3\2\2\2nm\3\2\2\2o\7\3\2\2\2"+
		"pv\5\16\b\2qv\5\20\t\2rv\5\26\f\2sv\5\30\r\2tv\5\32\16\2up\3\2\2\2uq\3"+
		"\2\2\2ur\3\2\2\2us\3\2\2\2ut\3\2\2\2v\t\3\2\2\2w|\5 \21\2x|\5\34\17\2"+
		"y|\5\62\32\2z|\5\66\34\2{w\3\2\2\2{x\3\2\2\2{y\3\2\2\2{z\3\2\2\2|\13\3"+
		"\2\2\2}\u0080\5P)\2~\u0080\5R*\2\177}\3\2\2\2\177~\3\2\2\2\u0080\r\3\2"+
		"\2\2\u0081\u0082\7\13\2\2\u0082\u0083\7\r\2\2\u0083\u0084\5T+\2\u0084"+
		"\17\3\2\2\2\u0085\u0086\7\13\2\2\u0086\u0087\7+\2\2\u0087\u0088\5V,\2"+
		"\u0088\u0089\7Z\2\2\u0089\u008e\5\22\n\2\u008a\u008b\7\\\2\2\u008b\u008d"+
		"\5\22\n\2\u008c\u008a\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2"+
		"\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092"+
		"\7[\2\2\u0092\21\3\2\2\2\u0093\u0094\5X-\2\u0094\u0098\5@!\2\u0095\u0097"+
		"\5\24\13\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2"+
		"\u0098\u0099\3\2\2\2\u0099\u00a2\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009c"+
		"\7&\2\2\u009c\u009d\7\34\2\2\u009d\u009e\7Z\2\2\u009e\u009f\5Z.\2\u009f"+
		"\u00a0\7[\2\2\u00a0\u00a2\3\2\2\2\u00a1\u0093\3\2\2\2\u00a1\u009b\3\2"+
		"\2\2\u00a2\23\3\2\2\2\u00a3\u00a7\5\\/\2\u00a4\u00a5\7&\2\2\u00a5\u00a7"+
		"\7\34\2\2\u00a6\u00a3\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\25\3\2\2\2\u00a8"+
		"\u00a9\7\23\2\2\u00a9\u00aa\7\r\2\2\u00aa\u00ab\5T+\2\u00ab\27\3\2\2\2"+
		"\u00ac\u00ad\7\23\2\2\u00ad\u00ae\7+\2\2\u00ae\u00af\5V,\2\u00af\31\3"+
		"\2\2\2\u00b0\u00b1\7.\2\2\u00b1\u00b2\7\r\2\2\u00b2\u00b3\5T+\2\u00b3"+
		"\33\3\2\2\2\u00b4\u00b5\7\30\2\2\u00b5\u00b6\7\31\2\2\u00b6\u00bb\5V,"+
		"\2\u00b7\u00b8\7Z\2\2\u00b8\u00b9\5Z.\2\u00b9\u00ba\7[\2\2\u00ba\u00bc"+
		"\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00be\5\36\20\2\u00be\35\3\2\2\2\u00bf\u00c6\5 \21\2\u00c0\u00c1\7/\2"+
		"\2\u00c1\u00c2\7Z\2\2\u00c2\u00c3\58\35\2\u00c3\u00c4\7[\2\2\u00c4\u00c6"+
		"\3\2\2\2\u00c5\u00bf\3\2\2\2\u00c5\u00c0\3\2\2\2\u00c6\37\3\2\2\2\u00c7"+
		"\u00c8\7(\2\2\u00c8\u00c9\5\"\22\2\u00c9\u00ca\5(\25\2\u00ca!\3\2\2\2"+
		"\u00cb\u00ce\7I\2\2\u00cc\u00ce\5$\23\2\u00cd\u00cb\3\2\2\2\u00cd\u00cc"+
		"\3\2\2\2\u00ce\u00d3\3\2\2\2\u00cf\u00d0\7\\\2\2\u00d0\u00d2\5$\23\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4#\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00db\5&\24\2\u00d7\u00d9"+
		"\7\b\2\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00dc\5X-\2\u00db\u00d8\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc%\3\2\2\2\u00dd"+
		"\u00de\5V,\2\u00de\u00df\7Y\2\2\u00df\u00e0\5X-\2\u00e0\u00e3\3\2\2\2"+
		"\u00e1\u00e3\5X-\2\u00e2\u00dd\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\'\3\2"+
		"\2\2\u00e4\u00e5\7\25\2\2\u00e5\u00e8\5*\26\2\u00e6\u00e7\7\60\2\2\u00e7"+
		"\u00e9\5:\36\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ed\3\2"+
		"\2\2\u00ea\u00eb\7\26\2\2\u00eb\u00ec\7\n\2\2\u00ec\u00ee\5:\36\2\u00ed"+
		"\u00ea\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee)\3\2\2\2\u00ef\u00f4\5,\27\2"+
		"\u00f0\u00f1\7\\\2\2\u00f1\u00f3\5,\27\2\u00f2\u00f0\3\2\2\2\u00f3\u00f6"+
		"\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5+\3\2\2\2\u00f6"+
		"\u00f4\3\2\2\2\u00f7\u00fb\5\60\31\2\u00f8\u00fa\5.\30\2\u00f9\u00f8\3"+
		"\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"-\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u0100\t\2\2\2\u00ff\u00fe\3\2\2\2"+
		"\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\7\33\2\2\u0102\u0105"+
		"\5\60\31\2\u0103\u0104\7\"\2\2\u0104\u0106\5:\36\2\u0105\u0103\3\2\2\2"+
		"\u0105\u0106\3\2\2\2\u0106\u011a\3\2\2\2\u0107\u0109\t\3\2\2\u0108\u010a"+
		"\7%\2\2\u0109\u0108\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u010c\7\33\2\2\u010c\u010d\5\60\31\2\u010d\u010e\7\"\2\2\u010e\u010f"+
		"\5:\36\2\u010f\u011a\3\2\2\2\u0110\u0115\7\37\2\2\u0111\u0113\t\3\2\2"+
		"\u0112\u0114\7%\2\2\u0113\u0112\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116"+
		"\3\2\2\2\u0115\u0111\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"\u0118\7\33\2\2\u0118\u011a\5\60\31\2\u0119\u00ff\3\2\2\2\u0119\u0107"+
		"\3\2\2\2\u0119\u0110\3\2\2\2\u011a/\3\2\2\2\u011b\u011c\5V,\2\u011c\61"+
		"\3\2\2\2\u011d\u011e\7-\2\2\u011e\u0123\5V,\2\u011f\u0121\7\b\2\2\u0120"+
		"\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\5V"+
		",\2\u0123\u0120\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0126\7)\2\2\u0126\u012b\5\64\33\2\u0127\u0128\7\\\2\2\u0128\u012a\5"+
		"\64\33\2\u0129\u0127\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u0130\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u012f\7\60"+
		"\2\2\u012f\u0131\5:\36\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\63\3\2\2\2\u0132\u0133\5&\24\2\u0133\u0134\7Q\2\2\u0134\u0135\5:\36\2"+
		"\u0135\65\3\2\2\2\u0136\u0137\7\20\2\2\u0137\u0138\7\25\2\2\u0138\u013b"+
		"\5V,\2\u0139\u013a\7\60\2\2\u013a\u013c\5:\36\2\u013b\u0139\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\67\3\2\2\2\u013d\u0142\5:\36\2\u013e\u013f\7\\\2"+
		"\2\u013f\u0141\5:\36\2\u0140\u013e\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140"+
		"\3\2\2\2\u0142\u0143\3\2\2\2\u01439\3\2\2\2\u0144\u0142\3\2\2\2\u0145"+
		"\u0146\b\36\1\2\u0146\u0147\t\4\2\2\u0147\u014a\5:\36\5\u0148\u014a\5"+
		"<\37\2\u0149\u0145\3\2\2\2\u0149\u0148\3\2\2\2\u014a\u0151\3\2\2\2\u014b"+
		"\u014c\f\4\2\2\u014c\u014d\5J&\2\u014d\u014e\5:\36\5\u014e\u0150\3\2\2"+
		"\2\u014f\u014b\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152"+
		"\3\2\2\2\u0152;\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0155\b\37\1\2\u0155"+
		"\u0156\5> \2\u0156\u0160\3\2\2\2\u0157\u0158\f\4\2\2\u0158\u0159\5H%\2"+
		"\u0159\u015a\5<\37\5\u015a\u015f\3\2\2\2\u015b\u015c\f\5\2\2\u015c\u015d"+
		"\7\32\2\2\u015d\u015f\5\\/\2\u015e\u0157\3\2\2\2\u015e\u015b\3\2\2\2\u015f"+
		"\u0162\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161=\3\2\2\2"+
		"\u0162\u0160\3\2\2\2\u0163\u0164\b \1\2\u0164\u0179\5D#\2\u0165\u0179"+
		"\5&\24\2\u0166\u0167\5F$\2\u0167\u0168\5> \7\u0168\u0179\3\2\2\2\u0169"+
		"\u016a\7Z\2\2\u016a\u016f\5:\36\2\u016b\u016c\7\\\2\2\u016c\u016e\5:\36"+
		"\2\u016d\u016b\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170"+
		"\3\2\2\2\u0170\u0172\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173\7[\2\2\u0173"+
		"\u0179\3\2\2\2\u0174\u0175\7Z\2\2\u0175\u0176\5 \21\2\u0176\u0177\7[\2"+
		"\2\u0177\u0179\3\2\2\2\u0178\u0163\3\2\2\2\u0178\u0165\3\2\2\2\u0178\u0166"+
		"\3\2\2\2\u0178\u0169\3\2\2\2\u0178\u0174\3\2\2\2\u0179\u0184\3\2\2\2\u017a"+
		"\u017b\f\4\2\2\u017b\u017c\5L\'\2\u017c\u017d\5> \5\u017d\u0183\3\2\2"+
		"\2\u017e\u017f\f\3\2\2\u017f\u0180\5N(\2\u0180\u0181\5> \4\u0181\u0183"+
		"\3\2\2\2\u0182\u017a\3\2\2\2\u0182\u017e\3\2\2\2\u0183\u0186\3\2\2\2\u0184"+
		"\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185?\3\2\2\2\u0186\u0184\3\2\2\2"+
		"\u0187\u018e\7\63\2\2\u0188\u018e\7\64\2\2\u0189\u018e\7\66\2\2\u018a"+
		"\u018e\7\65\2\2\u018b\u018c\79\2\2\u018c\u018e\5B\"\2\u018d\u0187\3\2"+
		"\2\2\u018d\u0188\3\2\2\2\u018d\u0189\3\2\2\2\u018d\u018a\3\2\2\2\u018d"+
		"\u018b\3\2\2\2\u018eA\3\2\2\2\u018f\u0190\7Z\2\2\u0190\u0191\7h\2\2\u0191"+
		"\u0192\7[\2\2\u0192C\3\2\2\2\u0193\u019a\7g\2\2\u0194\u019a\5^\60\2\u0195"+
		"\u0196\7N\2\2\u0196\u019a\5^\60\2\u0197\u019a\7j\2\2\u0198\u019a\5\\/"+
		"\2\u0199\u0193\3\2\2\2\u0199\u0194\3\2\2\2\u0199\u0195\3\2\2\2\u0199\u0197"+
		"\3\2\2\2\u0199\u0198\3\2\2\2\u019aE\3\2\2\2\u019b\u019c\t\5\2\2\u019c"+
		"G\3\2\2\2\u019d\u01ac\7Q\2\2\u019e\u01ac\7R\2\2\u019f\u01ac\7S\2\2\u01a0"+
		"\u01a1\7S\2\2\u01a1\u01ac\7Q\2\2\u01a2\u01a3\7R\2\2\u01a3\u01ac\7Q\2\2"+
		"\u01a4\u01a5\7S\2\2\u01a5\u01ac\7R\2\2\u01a6\u01a7\7T\2\2\u01a7\u01ac"+
		"\7Q\2\2\u01a8\u01a9\7S\2\2\u01a9\u01aa\7Q\2\2\u01aa\u01ac\7R\2\2\u01ab"+
		"\u019d\3\2\2\2\u01ab\u019e\3\2\2\2\u01ab\u019f\3\2\2\2\u01ab\u01a0\3\2"+
		"\2\2\u01ab\u01a2\3\2\2\2\u01ab\u01a4\3\2\2\2\u01ab\u01a6\3\2\2\2\u01ab"+
		"\u01a8\3\2\2\2\u01acI\3\2\2\2\u01ad\u01b5\7\7\2\2\u01ae\u01af\7W\2\2\u01af"+
		"\u01b5\7W\2\2\u01b0\u01b5\7\62\2\2\u01b1\u01b5\7#\2\2\u01b2\u01b3\7V\2"+
		"\2\u01b3\u01b5\7V\2\2\u01b4\u01ad\3\2\2\2\u01b4\u01ae\3\2\2\2\u01b4\u01b0"+
		"\3\2\2\2\u01b4\u01b1\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b5K\3\2\2\2\u01b6"+
		"\u01b7\7S\2\2\u01b7\u01be\7S\2\2\u01b8\u01b9\7R\2\2\u01b9\u01be\7R\2\2"+
		"\u01ba\u01be\7W\2\2\u01bb\u01be\7X\2\2\u01bc\u01be\7V\2\2\u01bd\u01b6"+
		"\3\2\2\2\u01bd\u01b8\3\2\2\2\u01bd\u01ba\3\2\2\2\u01bd\u01bb\3\2\2\2\u01bd"+
		"\u01bc\3\2\2\2\u01beM\3\2\2\2\u01bf\u01c0\t\6\2\2\u01c0O\3\2\2\2\u01c1"+
		"\u01c2\7*\2\2\u01c2\u01ca\7\16\2\2\u01c3\u01c4\7*\2\2\u01c4\u01c5\7\r"+
		"\2\2\u01c5\u01ca\5T+\2\u01c6\u01c7\7*\2\2\u01c7\u01c8\7+\2\2\u01c8\u01ca"+
		"\5V,\2\u01c9\u01c1\3\2\2\2\u01c9\u01c3\3\2\2\2\u01c9\u01c6\3\2\2\2\u01ca"+
		"Q\3\2\2\2\u01cb\u01cc\7?\2\2\u01ccS\3\2\2\2\u01cd\u01ce\7l\2\2\u01ceU"+
		"\3\2\2\2\u01cf\u01d0\7l\2\2\u01d0W\3\2\2\2\u01d1\u01d2\7l\2\2\u01d2Y\3"+
		"\2\2\2\u01d3\u01d8\5X-\2\u01d4\u01d5\7\\\2\2\u01d5\u01d7\5X-\2\u01d6\u01d4"+
		"\3\2\2\2\u01d7\u01da\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9"+
		"[\3\2\2\2\u01da\u01d8\3\2\2\2\u01db\u01dd\7 \2\2\u01dc\u01db\3\2\2\2\u01dc"+
		"\u01dd\3\2\2\2\u01dd\u01de\3\2\2\2\u01de\u01df\7!\2\2\u01df]\3\2\2\2\u01e0"+
		"\u01e1\t\7\2\2\u01e1_\3\2\2\2\62hnu{\177\u008e\u0098\u00a1\u00a6\u00bb"+
		"\u00c5\u00cd\u00d3\u00d8\u00db\u00e2\u00e8\u00ed\u00f4\u00fb\u00ff\u0105"+
		"\u0109\u0113\u0115\u0119\u0120\u0123\u012b\u0130\u013b\u0142\u0149\u0151"+
		"\u015e\u0160\u016f\u0178\u0182\u0184\u018d\u0199\u01ab\u01b4\u01bd\u01c9"+
		"\u01d8\u01dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}