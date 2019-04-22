package db.parser;// Generated from TinyDBParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

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
		RULE_createTable = 7, RULE_createDefinition = 8, RULE_dropDatabase = 9, 
		RULE_dropTable = 10, RULE_useDatabase = 11, RULE_insertStatement = 12, 
		RULE_insertStatementValue = 13, RULE_selectStatement = 14, RULE_selectElements = 15, 
		RULE_selectElement = 16, RULE_fullColumnName = 17, RULE_fromClause = 18, 
		RULE_tableSources = 19, RULE_tableSource = 20, RULE_joinPart = 21, RULE_tableSourceItem = 22, 
		RULE_updateStatement = 23, RULE_updatedElement = 24, RULE_deleteStatement = 25, 
		RULE_expressions = 26, RULE_expression = 27, RULE_predicate = 28, RULE_expressionAtom = 29, 
		RULE_dataType = 30, RULE_lengthOneDimension = 31, RULE_constant = 32, 
		RULE_unaryOperator = 33, RULE_comparisonOperator = 34, RULE_logicalOperator = 35, 
		RULE_mathOperator = 36, RULE_showStatement = 37, RULE_shutdownStatement = 38, 
		RULE_dbName = 39, RULE_tableName = 40, RULE_attrName = 41, RULE_attrNames = 42, 
		RULE_nullNotnull = 43, RULE_decimalLiteral = 44;
	public static final String[] ruleNames = {
		"root", "sqlStatements", "sqlStatement", "ddlStatement", "dmlStatement", 
		"administrationStatement", "createDatabase", "createTable", "createDefinition", 
		"dropDatabase", "dropTable", "useDatabase", "insertStatement", "insertStatementValue", 
		"selectStatement", "selectElements", "selectElement", "fullColumnName", 
		"fromClause", "tableSources", "tableSource", "joinPart", "tableSourceItem", 
		"updateStatement", "updatedElement", "deleteStatement", "expressions", 
		"expression", "predicate", "expressionAtom", "dataType", "lengthOneDimension", 
		"constant", "unaryOperator", "comparisonOperator", "logicalOperator", 
		"mathOperator", "showStatement", "shutdownStatement", "dbName", "tableName", 
		"attrName", "attrNames", "nullNotnull", "decimalLiteral"
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
			setState(90);
			sqlStatements();
			setState(91);
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
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << DELETE) | (1L << DROP) | (1L << INSERT) | (1L << SELECT) | (1L << SHOW) | (1L << UPDATE) | (1L << USE) | (1L << SHUTDOWN))) != 0)) {
				{
				{
				setState(93);
				sqlStatement();
				setState(94);
				match(SEMI);
				}
				}
				setState(100);
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
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case USE:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				ddlStatement();
				}
				break;
			case DELETE:
			case INSERT:
			case SELECT:
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				dmlStatement();
				}
				break;
			case SHOW:
			case SHUTDOWN:
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
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
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				createDatabase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				createTable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				dropDatabase();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				dropTable();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(110);
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
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				selectStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				updateStatement();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
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
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SHOW:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				showStatement();
				}
				break;
			case SHUTDOWN:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
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
			setState(123);
			match(CREATE);
			setState(124);
			match(DATABASE);
			setState(125);
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
			setState(127);
			match(CREATE);
			setState(128);
			match(TABLE);
			setState(129);
			tableName();
			setState(130);
			match(LR_BRACKET);
			setState(131);
			createDefinition();
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(132);
				match(COMMA);
				setState(133);
				createDefinition();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
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
		public NullNotnullContext nullNotnull() {
			return getRuleContext(NullNotnullContext.class,0);
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
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new ColumnDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				attrName();
				setState(142);
				dataType();
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT || _la==NULL_LITERAL) {
					{
					setState(143);
					nullNotnull();
					}
				}

				}
				break;
			case PRIMARY:
				_localctx = new ConstraintDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				match(PRIMARY);
				setState(147);
				match(KEY);
				setState(148);
				match(LR_BRACKET);
				setState(149);
				attrNames();
				setState(150);
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
		enterRule(_localctx, 18, RULE_dropDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(DROP);
			setState(155);
			match(DATABASE);
			setState(156);
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
		enterRule(_localctx, 20, RULE_dropTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(DROP);
			setState(159);
			match(TABLE);
			setState(160);
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
		enterRule(_localctx, 22, RULE_useDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(USE);
			setState(163);
			match(DATABASE);
			setState(164);
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
		enterRule(_localctx, 24, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(INSERT);
			setState(167);
			match(INTO);
			setState(168);
			tableName();
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LR_BRACKET) {
				{
				setState(169);
				match(LR_BRACKET);
				setState(170);
				attrNames();
				setState(171);
				match(RR_BRACKET);
				}
			}

			setState(175);
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
		enterRule(_localctx, 26, RULE_insertStatementValue);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				selectStatement();
				}
				break;
			case VALUES:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				match(VALUES);
				setState(179);
				match(LR_BRACKET);
				setState(180);
				expressions();
				setState(181);
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
		enterRule(_localctx, 28, RULE_selectStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(SELECT);
			setState(186);
			selectElements();
			setState(187);
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
		enterRule(_localctx, 30, RULE_selectElements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				{
				setState(189);
				((SelectElementsContext)_localctx).star = match(STAR);
				}
				break;
			case ID:
				{
				setState(190);
				selectElement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(193);
				match(COMMA);
				setState(194);
				selectElement();
				}
				}
				setState(199);
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
		enterRule(_localctx, 32, RULE_selectElement);
		int _la;
		try {
			_localctx = new SelectColumnElementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			fullColumnName();
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(201);
					match(AS);
					}
				}

				setState(204);
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
		enterRule(_localctx, 34, RULE_fullColumnName);
		try {
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				tableName();
				setState(208);
				match(DOT);
				setState(209);
				attrName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(211);
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
		enterRule(_localctx, 36, RULE_fromClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(FROM);
			setState(215);
			tableSources();
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(216);
				match(WHERE);
				setState(217);
				((FromClauseContext)_localctx).whereExpr = expression(0);
				}
			}

			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(220);
				match(GROUP);
				setState(221);
				match(BY);
				setState(222);
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
		enterRule(_localctx, 38, RULE_tableSources);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			tableSource();
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(226);
				match(COMMA);
				setState(227);
				tableSource();
				}
				}
				setState(232);
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
		enterRule(_localctx, 40, RULE_tableSource);
		int _la;
		try {
			_localctx = new TableSourceBaseContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			tableSourceItem();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CROSS) | (1L << INNER) | (1L << JOIN) | (1L << LEFT) | (1L << NATURAL) | (1L << RIGHT))) != 0)) {
				{
				{
				setState(234);
				joinPart();
				}
				}
				setState(239);
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
		enterRule(_localctx, 42, RULE_joinPart);
		int _la;
		try {
			setState(267);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CROSS:
			case INNER:
			case JOIN:
				_localctx = new InnerJoinContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CROSS || _la==INNER) {
					{
					setState(240);
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

				setState(243);
				match(JOIN);
				setState(244);
				tableSourceItem();
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(245);
					match(ON);
					setState(246);
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
				setState(249);
				_la = _input.LA(1);
				if ( !(_la==LEFT || _la==RIGHT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(250);
					match(OUTER);
					}
				}

				setState(253);
				match(JOIN);
				setState(254);
				tableSourceItem();
				{
				setState(255);
				match(ON);
				setState(256);
				expression(0);
				}
				}
				break;
			case NATURAL:
				_localctx = new NaturalJoinContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(258);
				match(NATURAL);
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LEFT || _la==RIGHT) {
					{
					setState(259);
					_la = _input.LA(1);
					if ( !(_la==LEFT || _la==RIGHT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(261);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==OUTER) {
						{
						setState(260);
						match(OUTER);
						}
					}

					}
				}

				setState(265);
				match(JOIN);
				setState(266);
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
		enterRule(_localctx, 44, RULE_tableSourceItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
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
		enterRule(_localctx, 46, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(UPDATE);
			setState(272);
			tableName();
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(273);
					match(AS);
					}
				}

				setState(276);
				((UpdateStatementContext)_localctx).alias = tableName();
				}
			}

			setState(279);
			match(SET);
			setState(280);
			updatedElement();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(281);
				match(COMMA);
				setState(282);
				updatedElement();
				}
				}
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(288);
				match(WHERE);
				setState(289);
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
		enterRule(_localctx, 48, RULE_updatedElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			fullColumnName();
			setState(293);
			match(EQUAL_SYMBOL);
			setState(294);
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
		enterRule(_localctx, 50, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(DELETE);
			setState(297);
			match(FROM);
			setState(298);
			tableName();
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(299);
				match(WHERE);
				setState(300);
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
		enterRule(_localctx, 52, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			expression(0);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(304);
				match(COMMA);
				setState(305);
				expression(0);
				}
				}
				setState(310);
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
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(312);
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
				setState(313);
				expression(3);
				}
				break;
			case 2:
				{
				_localctx = new PredicateExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(314);
				predicate(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(323);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalExpressionContext(new ExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(317);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(318);
					logicalOperator();
					setState(319);
					expression(3);
					}
					} 
				}
				setState(325);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
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
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_predicate, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExpressionAtomPredicateContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(327);
			expressionAtom(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(338);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(336);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryComparasionPredicateContext(new PredicateContext(_parentctx, _parentState));
						((BinaryComparasionPredicateContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(329);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(330);
						comparisonOperator();
						setState(331);
						((BinaryComparasionPredicateContext)_localctx).right = predicate(3);
						}
						break;
					case 2:
						{
						_localctx = new IsNullPredicateContext(new PredicateContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(333);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(334);
						match(IS);
						setState(335);
						nullNotnull();
						}
						break;
					}
					} 
				}
				setState(340);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
		int _startState = 58;
		enterRecursionRule(_localctx, 58, RULE_expressionAtom, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				_localctx = new ConstantExpressionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(342);
				constant();
				}
				break;
			case 2:
				{
				_localctx = new FullColumnNameExpressionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(343);
				fullColumnName();
				}
				break;
			case 3:
				{
				_localctx = new UnaryExpressionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(344);
				unaryOperator();
				setState(345);
				expressionAtom(3);
				}
				break;
			case 4:
				{
				_localctx = new SubqueryExpessionAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(347);
				match(LR_BRACKET);
				setState(348);
				selectStatement();
				setState(349);
				match(RR_BRACKET);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(359);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MathExpressionAtomContext(new ExpressionAtomContext(_parentctx, _parentState));
					((MathExpressionAtomContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expressionAtom);
					setState(353);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(354);
					mathOperator();
					setState(355);
					((MathExpressionAtomContext)_localctx).right = expressionAtom(2);
					}
					} 
				}
				setState(361);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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
		enterRule(_localctx, 60, RULE_dataType);
		try {
			setState(368);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				match(INT);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(363);
				match(LONG);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(364);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(365);
				match(DOUBLE);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(366);
				match(STRING);
				}
				setState(367);
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
		enterRule(_localctx, 62, RULE_lengthOneDimension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(LR_BRACKET);
			setState(371);
			match(DECIMAL_LITERAL);
			setState(372);
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
		enterRule(_localctx, 64, RULE_constant);
		try {
			setState(380);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				match(STRING_LITERAL);
				}
				break;
			case ZERO_DECIMAL:
			case ONE_DECIMAL:
			case TWO_DECIMAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				decimalLiteral();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
				match(MINUS);
				setState(377);
				decimalLiteral();
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(378);
				match(REAL_LITERAL);
				}
				break;
			case NOT:
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(379);
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
		enterRule(_localctx, 66, RULE_unaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			_la = _input.LA(1);
			if ( !(((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (NOT - 30)) | (1L << (PLUS - 30)) | (1L << (MINUS - 30)) | (1L << (EXCLAMATION_SYMBOL - 30)))) != 0)) ) {
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
		enterRule(_localctx, 68, RULE_comparisonOperator);
		try {
			setState(395);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(384);
				match(EQUAL_SYMBOL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(385);
				match(GREATER_SYMBOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(386);
				match(LESS_SYMBOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(387);
				match(LESS_SYMBOL);
				setState(388);
				match(EQUAL_SYMBOL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(389);
				match(GREATER_SYMBOL);
				setState(390);
				match(EQUAL_SYMBOL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(391);
				match(LESS_SYMBOL);
				setState(392);
				match(GREATER_SYMBOL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(393);
				match(EXCLAMATION_SYMBOL);
				setState(394);
				match(EQUAL_SYMBOL);
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
		enterRule(_localctx, 70, RULE_logicalOperator);
		try {
			setState(404);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(397);
				match(AND);
				}
				break;
			case BIT_AND_OP:
				enterOuterAlt(_localctx, 2);
				{
				setState(398);
				match(BIT_AND_OP);
				setState(399);
				match(BIT_AND_OP);
				}
				break;
			case XOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(400);
				match(XOR);
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 4);
				{
				setState(401);
				match(OR);
				}
				break;
			case BIT_OR_OP:
				enterOuterAlt(_localctx, 5);
				{
				setState(402);
				match(BIT_OR_OP);
				setState(403);
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
		enterRule(_localctx, 72, RULE_mathOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
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
		enterRule(_localctx, 74, RULE_showStatement);
		try {
			setState(416);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
				match(SHOW);
				setState(409);
				match(DATABASES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(410);
				match(SHOW);
				setState(411);
				match(DATABASE);
				setState(412);
				dbName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(413);
				match(SHOW);
				setState(414);
				match(TABLE);
				setState(415);
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
		enterRule(_localctx, 76, RULE_shutdownStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
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
		enterRule(_localctx, 78, RULE_dbName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
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
		enterRule(_localctx, 80, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
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
		enterRule(_localctx, 82, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
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
		enterRule(_localctx, 84, RULE_attrNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			attrName();
			setState(431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(427);
				match(COMMA);
				setState(428);
				attrName();
				}
				}
				setState(433);
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
		enterRule(_localctx, 86, RULE_nullNotnull);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(434);
				match(NOT);
				}
			}

			setState(437);
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
		enterRule(_localctx, 88, RULE_decimalLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
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
		case 27:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 28:
			return predicate_sempred((PredicateContext)_localctx, predIndex);
		case 29:
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
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3m\u01bc\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\2\3\3\3\3\3\3\7\3c\n\3\f\3\16\3f\13\3\3\4\3"+
		"\4\3\4\5\4k\n\4\3\5\3\5\3\5\3\5\3\5\5\5r\n\5\3\6\3\6\3\6\3\6\5\6x\n\6"+
		"\3\7\3\7\5\7|\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0089"+
		"\n\t\f\t\16\t\u008c\13\t\3\t\3\t\3\n\3\n\3\n\5\n\u0093\n\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\5\n\u009b\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00b0\n\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00ba\n\17\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\5\21\u00c2\n\21\3\21\3\21\7\21\u00c6\n\21\f\21\16\21\u00c9\13\21"+
		"\3\22\3\22\5\22\u00cd\n\22\3\22\5\22\u00d0\n\22\3\23\3\23\3\23\3\23\3"+
		"\23\5\23\u00d7\n\23\3\24\3\24\3\24\3\24\5\24\u00dd\n\24\3\24\3\24\3\24"+
		"\5\24\u00e2\n\24\3\25\3\25\3\25\7\25\u00e7\n\25\f\25\16\25\u00ea\13\25"+
		"\3\26\3\26\7\26\u00ee\n\26\f\26\16\26\u00f1\13\26\3\27\5\27\u00f4\n\27"+
		"\3\27\3\27\3\27\3\27\5\27\u00fa\n\27\3\27\3\27\5\27\u00fe\n\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0108\n\27\5\27\u010a\n\27\3\27"+
		"\3\27\5\27\u010e\n\27\3\30\3\30\3\31\3\31\3\31\5\31\u0115\n\31\3\31\5"+
		"\31\u0118\n\31\3\31\3\31\3\31\3\31\7\31\u011e\n\31\f\31\16\31\u0121\13"+
		"\31\3\31\3\31\5\31\u0125\n\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\5\33\u0130\n\33\3\34\3\34\3\34\7\34\u0135\n\34\f\34\16\34\u0138"+
		"\13\34\3\35\3\35\3\35\3\35\5\35\u013e\n\35\3\35\3\35\3\35\3\35\7\35\u0144"+
		"\n\35\f\35\16\35\u0147\13\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\7\36\u0153\n\36\f\36\16\36\u0156\13\36\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\5\37\u0162\n\37\3\37\3\37\3\37\3\37\7\37"+
		"\u0168\n\37\f\37\16\37\u016b\13\37\3 \3 \3 \3 \3 \3 \5 \u0173\n \3!\3"+
		"!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u017f\n\"\3#\3#\3$\3$\3$\3$\3$\3$"+
		"\3$\3$\3$\3$\3$\5$\u018e\n$\3%\3%\3%\3%\3%\3%\3%\5%\u0197\n%\3&\3&\3\'"+
		"\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01a3\n\'\3(\3(\3)\3)\3*\3*\3+\3+\3,"+
		"\3,\3,\7,\u01b0\n,\f,\16,\u01b3\13,\3-\5-\u01b6\n-\3-\3-\3.\3.\3.\2\5"+
		"8:</\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@"+
		"BDFHJLNPRTVXZ\2\b\4\2\f\f\27\27\4\2\35\35\'\'\4\2  TT\6\2  LLNNTT\3\2"+
		"IP\4\2_ahh\2\u01d2\2\\\3\2\2\2\4d\3\2\2\2\6j\3\2\2\2\bq\3\2\2\2\nw\3\2"+
		"\2\2\f{\3\2\2\2\16}\3\2\2\2\20\u0081\3\2\2\2\22\u009a\3\2\2\2\24\u009c"+
		"\3\2\2\2\26\u00a0\3\2\2\2\30\u00a4\3\2\2\2\32\u00a8\3\2\2\2\34\u00b9\3"+
		"\2\2\2\36\u00bb\3\2\2\2 \u00c1\3\2\2\2\"\u00ca\3\2\2\2$\u00d6\3\2\2\2"+
		"&\u00d8\3\2\2\2(\u00e3\3\2\2\2*\u00eb\3\2\2\2,\u010d\3\2\2\2.\u010f\3"+
		"\2\2\2\60\u0111\3\2\2\2\62\u0126\3\2\2\2\64\u012a\3\2\2\2\66\u0131\3\2"+
		"\2\28\u013d\3\2\2\2:\u0148\3\2\2\2<\u0161\3\2\2\2>\u0172\3\2\2\2@\u0174"+
		"\3\2\2\2B\u017e\3\2\2\2D\u0180\3\2\2\2F\u018d\3\2\2\2H\u0196\3\2\2\2J"+
		"\u0198\3\2\2\2L\u01a2\3\2\2\2N\u01a4\3\2\2\2P\u01a6\3\2\2\2R\u01a8\3\2"+
		"\2\2T\u01aa\3\2\2\2V\u01ac\3\2\2\2X\u01b5\3\2\2\2Z\u01b9\3\2\2\2\\]\5"+
		"\4\3\2]^\7\2\2\3^\3\3\2\2\2_`\5\6\4\2`a\7]\2\2ac\3\2\2\2b_\3\2\2\2cf\3"+
		"\2\2\2db\3\2\2\2de\3\2\2\2e\5\3\2\2\2fd\3\2\2\2gk\5\b\5\2hk\5\n\6\2ik"+
		"\5\f\7\2jg\3\2\2\2jh\3\2\2\2ji\3\2\2\2k\7\3\2\2\2lr\5\16\b\2mr\5\20\t"+
		"\2nr\5\24\13\2or\5\26\f\2pr\5\30\r\2ql\3\2\2\2qm\3\2\2\2qn\3\2\2\2qo\3"+
		"\2\2\2qp\3\2\2\2r\t\3\2\2\2sx\5\36\20\2tx\5\32\16\2ux\5\60\31\2vx\5\64"+
		"\33\2ws\3\2\2\2wt\3\2\2\2wu\3\2\2\2wv\3\2\2\2x\13\3\2\2\2y|\5L\'\2z|\5"+
		"N(\2{y\3\2\2\2{z\3\2\2\2|\r\3\2\2\2}~\7\13\2\2~\177\7\r\2\2\177\u0080"+
		"\5P)\2\u0080\17\3\2\2\2\u0081\u0082\7\13\2\2\u0082\u0083\7+\2\2\u0083"+
		"\u0084\5R*\2\u0084\u0085\7Z\2\2\u0085\u008a\5\22\n\2\u0086\u0087\7\\\2"+
		"\2\u0087\u0089\5\22\n\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2"+
		"\2\2\u008d\u008e\7[\2\2\u008e\21\3\2\2\2\u008f\u0090\5T+\2\u0090\u0092"+
		"\5> \2\u0091\u0093\5X-\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u009b\3\2\2\2\u0094\u0095\7&\2\2\u0095\u0096\7\34\2\2\u0096\u0097\7Z"+
		"\2\2\u0097\u0098\5V,\2\u0098\u0099\7[\2\2\u0099\u009b\3\2\2\2\u009a\u008f"+
		"\3\2\2\2\u009a\u0094\3\2\2\2\u009b\23\3\2\2\2\u009c\u009d\7\23\2\2\u009d"+
		"\u009e\7\r\2\2\u009e\u009f\5P)\2\u009f\25\3\2\2\2\u00a0\u00a1\7\23\2\2"+
		"\u00a1\u00a2\7+\2\2\u00a2\u00a3\5R*\2\u00a3\27\3\2\2\2\u00a4\u00a5\7."+
		"\2\2\u00a5\u00a6\7\r\2\2\u00a6\u00a7\5P)\2\u00a7\31\3\2\2\2\u00a8\u00a9"+
		"\7\30\2\2\u00a9\u00aa\7\31\2\2\u00aa\u00af\5R*\2\u00ab\u00ac\7Z\2\2\u00ac"+
		"\u00ad\5V,\2\u00ad\u00ae\7[\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ab\3\2\2"+
		"\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\5\34\17\2\u00b2"+
		"\33\3\2\2\2\u00b3\u00ba\5\36\20\2\u00b4\u00b5\7/\2\2\u00b5\u00b6\7Z\2"+
		"\2\u00b6\u00b7\5\66\34\2\u00b7\u00b8\7[\2\2\u00b8\u00ba\3\2\2\2\u00b9"+
		"\u00b3\3\2\2\2\u00b9\u00b4\3\2\2\2\u00ba\35\3\2\2\2\u00bb\u00bc\7(\2\2"+
		"\u00bc\u00bd\5 \21\2\u00bd\u00be\5&\24\2\u00be\37\3\2\2\2\u00bf\u00c2"+
		"\7I\2\2\u00c0\u00c2\5\"\22\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2"+
		"\u00c7\3\2\2\2\u00c3\u00c4\7\\\2\2\u00c4\u00c6\5\"\22\2\u00c5\u00c3\3"+
		"\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"!\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cf\5$\23\2\u00cb\u00cd\7\b\2\2"+
		"\u00cc\u00cb\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0"+
		"\5T+\2\u00cf\u00cc\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0#\3\2\2\2\u00d1\u00d2"+
		"\5R*\2\u00d2\u00d3\7Y\2\2\u00d3\u00d4\5T+\2\u00d4\u00d7\3\2\2\2\u00d5"+
		"\u00d7\5T+\2\u00d6\u00d1\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7%\3\2\2\2\u00d8"+
		"\u00d9\7\25\2\2\u00d9\u00dc\5(\25\2\u00da\u00db\7\60\2\2\u00db\u00dd\5"+
		"8\35\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e1\3\2\2\2\u00de"+
		"\u00df\7\26\2\2\u00df\u00e0\7\n\2\2\u00e0\u00e2\58\35\2\u00e1\u00de\3"+
		"\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\'\3\2\2\2\u00e3\u00e8\5*\26\2\u00e4\u00e5"+
		"\7\\\2\2\u00e5\u00e7\5*\26\2\u00e6\u00e4\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9)\3\2\2\2\u00ea\u00e8\3\2\2\2"+
		"\u00eb\u00ef\5.\30\2\u00ec\u00ee\5,\27\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1"+
		"\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0+\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f2\u00f4\t\2\2\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\7\33\2\2\u00f6\u00f9\5.\30\2\u00f7"+
		"\u00f8\7\"\2\2\u00f8\u00fa\58\35\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\u010e\3\2\2\2\u00fb\u00fd\t\3\2\2\u00fc\u00fe\7%\2\2\u00fd"+
		"\u00fc\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\7\33"+
		"\2\2\u0100\u0101\5.\30\2\u0101\u0102\7\"\2\2\u0102\u0103\58\35\2\u0103"+
		"\u010e\3\2\2\2\u0104\u0109\7\37\2\2\u0105\u0107\t\3\2\2\u0106\u0108\7"+
		"%\2\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109"+
		"\u0105\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\7\33"+
		"\2\2\u010c\u010e\5.\30\2\u010d\u00f3\3\2\2\2\u010d\u00fb\3\2\2\2\u010d"+
		"\u0104\3\2\2\2\u010e-\3\2\2\2\u010f\u0110\5R*\2\u0110/\3\2\2\2\u0111\u0112"+
		"\7-\2\2\u0112\u0117\5R*\2\u0113\u0115\7\b\2\2\u0114\u0113\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\5R*\2\u0117\u0114\3\2\2"+
		"\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7)\2\2\u011a\u011f"+
		"\5\62\32\2\u011b\u011c\7\\\2\2\u011c\u011e\5\62\32\2\u011d\u011b\3\2\2"+
		"\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0124"+
		"\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\7\60\2\2\u0123\u0125\58\35\2"+
		"\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\61\3\2\2\2\u0126\u0127"+
		"\5$\23\2\u0127\u0128\7Q\2\2\u0128\u0129\58\35\2\u0129\63\3\2\2\2\u012a"+
		"\u012b\7\20\2\2\u012b\u012c\7\25\2\2\u012c\u012f\5R*\2\u012d\u012e\7\60"+
		"\2\2\u012e\u0130\58\35\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\65\3\2\2\2\u0131\u0136\58\35\2\u0132\u0133\7\\\2\2\u0133\u0135\58\35"+
		"\2\u0134\u0132\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137"+
		"\3\2\2\2\u0137\67\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\b\35\1\2\u013a"+
		"\u013b\t\4\2\2\u013b\u013e\58\35\5\u013c\u013e\5:\36\2\u013d\u0139\3\2"+
		"\2\2\u013d\u013c\3\2\2\2\u013e\u0145\3\2\2\2\u013f\u0140\f\4\2\2\u0140"+
		"\u0141\5H%\2\u0141\u0142\58\35\5\u0142\u0144\3\2\2\2\u0143\u013f\3\2\2"+
		"\2\u0144\u0147\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u01469"+
		"\3\2\2\2\u0147\u0145\3\2\2\2\u0148\u0149\b\36\1\2\u0149\u014a\5<\37\2"+
		"\u014a\u0154\3\2\2\2\u014b\u014c\f\4\2\2\u014c\u014d\5F$\2\u014d\u014e"+
		"\5:\36\5\u014e\u0153\3\2\2\2\u014f\u0150\f\5\2\2\u0150\u0151\7\32\2\2"+
		"\u0151\u0153\5X-\2\u0152\u014b\3\2\2\2\u0152\u014f\3\2\2\2\u0153\u0156"+
		"\3\2\2\2\u0154\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155;\3\2\2\2\u0156"+
		"\u0154\3\2\2\2\u0157\u0158\b\37\1\2\u0158\u0162\5B\"\2\u0159\u0162\5$"+
		"\23\2\u015a\u015b\5D#\2\u015b\u015c\5<\37\5\u015c\u0162\3\2\2\2\u015d"+
		"\u015e\7Z\2\2\u015e\u015f\5\36\20\2\u015f\u0160\7[\2\2\u0160\u0162\3\2"+
		"\2\2\u0161\u0157\3\2\2\2\u0161\u0159\3\2\2\2\u0161\u015a\3\2\2\2\u0161"+
		"\u015d\3\2\2\2\u0162\u0169\3\2\2\2\u0163\u0164\f\3\2\2\u0164\u0165\5J"+
		"&\2\u0165\u0166\5<\37\4\u0166\u0168\3\2\2\2\u0167\u0163\3\2\2\2\u0168"+
		"\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a=\3\2\2\2"+
		"\u016b\u0169\3\2\2\2\u016c\u0173\7\63\2\2\u016d\u0173\7\64\2\2\u016e\u0173"+
		"\7\66\2\2\u016f\u0173\7\65\2\2\u0170\u0171\79\2\2\u0171\u0173\5@!\2\u0172"+
		"\u016c\3\2\2\2\u0172\u016d\3\2\2\2\u0172\u016e\3\2\2\2\u0172\u016f\3\2"+
		"\2\2\u0172\u0170\3\2\2\2\u0173?\3\2\2\2\u0174\u0175\7Z\2\2\u0175\u0176"+
		"\7h\2\2\u0176\u0177\7[\2\2\u0177A\3\2\2\2\u0178\u017f\7g\2\2\u0179\u017f"+
		"\5Z.\2\u017a\u017b\7N\2\2\u017b\u017f\5Z.\2\u017c\u017f\7j\2\2\u017d\u017f"+
		"\5X-\2\u017e\u0178\3\2\2\2\u017e\u0179\3\2\2\2\u017e\u017a\3\2\2\2\u017e"+
		"\u017c\3\2\2\2\u017e\u017d\3\2\2\2\u017fC\3\2\2\2\u0180\u0181\t\5\2\2"+
		"\u0181E\3\2\2\2\u0182\u018e\7Q\2\2\u0183\u018e\7R\2\2\u0184\u018e\7S\2"+
		"\2\u0185\u0186\7S\2\2\u0186\u018e\7Q\2\2\u0187\u0188\7R\2\2\u0188\u018e"+
		"\7Q\2\2\u0189\u018a\7S\2\2\u018a\u018e\7R\2\2\u018b\u018c\7T\2\2\u018c"+
		"\u018e\7Q\2\2\u018d\u0182\3\2\2\2\u018d\u0183\3\2\2\2\u018d\u0184\3\2"+
		"\2\2\u018d\u0185\3\2\2\2\u018d\u0187\3\2\2\2\u018d\u0189\3\2\2\2\u018d"+
		"\u018b\3\2\2\2\u018eG\3\2\2\2\u018f\u0197\7\7\2\2\u0190\u0191\7W\2\2\u0191"+
		"\u0197\7W\2\2\u0192\u0197\7\62\2\2\u0193\u0197\7#\2\2\u0194\u0195\7V\2"+
		"\2\u0195\u0197\7V\2\2\u0196\u018f\3\2\2\2\u0196\u0190\3\2\2\2\u0196\u0192"+
		"\3\2\2\2\u0196\u0193\3\2\2\2\u0196\u0194\3\2\2\2\u0197I\3\2\2\2\u0198"+
		"\u0199\t\6\2\2\u0199K\3\2\2\2\u019a\u019b\7*\2\2\u019b\u01a3\7\16\2\2"+
		"\u019c\u019d\7*\2\2\u019d\u019e\7\r\2\2\u019e\u01a3\5P)\2\u019f\u01a0"+
		"\7*\2\2\u01a0\u01a1\7+\2\2\u01a1\u01a3\5R*\2\u01a2\u019a\3\2\2\2\u01a2"+
		"\u019c\3\2\2\2\u01a2\u019f\3\2\2\2\u01a3M\3\2\2\2\u01a4\u01a5\7?\2\2\u01a5"+
		"O\3\2\2\2\u01a6\u01a7\7l\2\2\u01a7Q\3\2\2\2\u01a8\u01a9\7l\2\2\u01a9S"+
		"\3\2\2\2\u01aa\u01ab\7l\2\2\u01abU\3\2\2\2\u01ac\u01b1\5T+\2\u01ad\u01ae"+
		"\7\\\2\2\u01ae\u01b0\5T+\2\u01af\u01ad\3\2\2\2\u01b0\u01b3\3\2\2\2\u01b1"+
		"\u01af\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2W\3\2\2\2\u01b3\u01b1\3\2\2\2"+
		"\u01b4\u01b6\7 \2\2\u01b5\u01b4\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b7"+
		"\3\2\2\2\u01b7\u01b8\7!\2\2\u01b8Y\3\2\2\2\u01b9\u01ba\t\7\2\2\u01ba["+
		"\3\2\2\2.djqw{\u008a\u0092\u009a\u00af\u00b9\u00c1\u00c7\u00cc\u00cf\u00d6"+
		"\u00dc\u00e1\u00e8\u00ef\u00f3\u00f9\u00fd\u0107\u0109\u010d\u0114\u0117"+
		"\u011f\u0124\u012f\u0136\u013d\u0145\u0152\u0154\u0161\u0169\u0172\u017e"+
		"\u018d\u0196\u01a2\u01b1\u01b5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}