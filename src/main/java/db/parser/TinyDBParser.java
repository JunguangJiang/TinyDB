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
		RULE_selectStatement = 13, RULE_fullColumnName = 14, RULE_tableSources = 15, 
		RULE_tableSource = 16, RULE_joinPart = 17, RULE_updateStatement = 18, 
		RULE_updatedElement = 19, RULE_deleteStatement = 20, RULE_predicate = 21, 
		RULE_expressionAtom = 22, RULE_dataType = 23, RULE_lengthOneDimension = 24, 
		RULE_constants = 25, RULE_constant = 26, RULE_comparisonOperator = 27, 
		RULE_logicalOperator = 28, RULE_showStatement = 29, RULE_shutdownStatement = 30, 
		RULE_dbName = 31, RULE_tableName = 32, RULE_attrName = 33, RULE_attrNames = 34, 
		RULE_nullNotnull = 35, RULE_decimalLiteral = 36;
	public static final String[] ruleNames = {
		"root", "sqlStatements", "sqlStatement", "ddlStatement", "dmlStatement", 
		"administrationStatement", "createDatabase", "createTable", "createDefinition", 
		"dropDatabase", "dropTable", "useDatabase", "insertStatement", "selectStatement", 
		"fullColumnName", "tableSources", "tableSource", "joinPart", "updateStatement", 
		"updatedElement", "deleteStatement", "predicate", "expressionAtom", "dataType", 
		"lengthOneDimension", "constants", "constant", "comparisonOperator", "logicalOperator", 
		"showStatement", "shutdownStatement", "dbName", "tableName", "attrName", 
		"attrNames", "nullNotnull", "decimalLiteral"
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
			setState(74);
			sqlStatements();
			setState(75);
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
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << DELETE) | (1L << DROP) | (1L << INSERT) | (1L << SELECT) | (1L << SHOW) | (1L << UPDATE) | (1L << USE) | (1L << SHUTDOWN))) != 0)) {
				{
				{
				setState(77);
				sqlStatement();
				setState(78);
				match(SEMI);
				}
				}
				setState(84);
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
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case USE:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				ddlStatement();
				}
				break;
			case DELETE:
			case INSERT:
			case SELECT:
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				dmlStatement();
				}
				break;
			case SHOW:
			case SHUTDOWN:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
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
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				createDatabase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				createTable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				dropDatabase();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				dropTable();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(94);
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
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				selectStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				updateStatement();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
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
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SHOW:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				showStatement();
				}
				break;
			case SHUTDOWN:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
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
			setState(107);
			match(CREATE);
			setState(108);
			match(DATABASE);
			setState(109);
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
			setState(111);
			match(CREATE);
			setState(112);
			match(TABLE);
			setState(113);
			tableName();
			setState(114);
			match(LR_BRACKET);
			setState(115);
			createDefinition();
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(116);
				match(COMMA);
				setState(117);
				createDefinition();
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
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
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new ColumnDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				attrName();
				setState(126);
				dataType();
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT || _la==NULL_LITERAL) {
					{
					setState(127);
					nullNotnull();
					}
				}

				}
				break;
			case PRIMARY:
				_localctx = new ConstraintDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(PRIMARY);
				setState(131);
				match(KEY);
				setState(132);
				match(LR_BRACKET);
				setState(133);
				attrNames();
				setState(134);
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
			setState(138);
			match(DROP);
			setState(139);
			match(DATABASE);
			setState(140);
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
			setState(142);
			match(DROP);
			setState(143);
			match(TABLE);
			setState(144);
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
			setState(146);
			match(USE);
			setState(147);
			match(DATABASE);
			setState(148);
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
		public TerminalNode VALUES() { return getToken(TinyDBParser.VALUES, 0); }
		public ConstantsContext constants() {
			return getRuleContext(ConstantsContext.class,0);
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
			setState(150);
			match(INSERT);
			setState(151);
			match(INTO);
			setState(152);
			tableName();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LR_BRACKET) {
				{
				setState(153);
				match(LR_BRACKET);
				setState(154);
				attrNames();
				setState(155);
				match(RR_BRACKET);
				}
			}

			setState(159);
			match(VALUES);
			setState(160);
			match(LR_BRACKET);
			setState(161);
			constants();
			setState(162);
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

	public static class SelectStatementContext extends ParserRuleContext {
		public PredicateContext whereExpr;
		public TerminalNode SELECT() { return getToken(TinyDBParser.SELECT, 0); }
		public List<FullColumnNameContext> fullColumnName() {
			return getRuleContexts(FullColumnNameContext.class);
		}
		public FullColumnNameContext fullColumnName(int i) {
			return getRuleContext(FullColumnNameContext.class,i);
		}
		public TerminalNode FROM() { return getToken(TinyDBParser.FROM, 0); }
		public TableSourcesContext tableSources() {
			return getRuleContext(TableSourcesContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(TinyDBParser.WHERE, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
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
		enterRule(_localctx, 26, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(SELECT);
			setState(165);
			fullColumnName();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(166);
				match(COMMA);
				setState(167);
				fullColumnName();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(173);
			match(FROM);
			setState(174);
			tableSources();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(175);
				match(WHERE);
				setState(176);
				((SelectStatementContext)_localctx).whereExpr = predicate(0);
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
		enterRule(_localctx, 28, RULE_fullColumnName);
		try {
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				tableName();
				setState(180);
				match(DOT);
				setState(181);
				attrName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
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
		enterRule(_localctx, 30, RULE_tableSources);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			tableSource();
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(187);
				match(COMMA);
				setState(188);
				tableSource();
				}
				}
				setState(193);
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
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
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
		enterRule(_localctx, 32, RULE_tableSource);
		int _la;
		try {
			_localctx = new TableSourceBaseContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			tableName();
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << JOIN) | (1L << LEFT) | (1L << RIGHT))) != 0)) {
				{
				{
				setState(195);
				joinPart();
				}
				}
				setState(200);
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
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode ON() { return getToken(TinyDBParser.ON, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public InnerJoinContext(JoinPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitInnerJoin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OuterJoinContext extends JoinPartContext {
		public TerminalNode JOIN() { return getToken(TinyDBParser.JOIN, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(TinyDBParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(TinyDBParser.RIGHT, 0); }
		public TerminalNode ON() { return getToken(TinyDBParser.ON, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
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
		enterRule(_localctx, 34, RULE_joinPart);
		int _la;
		try {
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case JOIN:
				_localctx = new InnerJoinContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				match(JOIN);
				setState(202);
				tableName();
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(203);
					match(ON);
					setState(204);
					predicate(0);
					}
				}

				}
				break;
			case LEFT:
			case RIGHT:
				_localctx = new OuterJoinContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				_la = _input.LA(1);
				if ( !(_la==LEFT || _la==RIGHT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(208);
					match(OUTER);
					}
				}

				setState(211);
				match(JOIN);
				setState(212);
				tableName();
				{
				setState(213);
				match(ON);
				setState(214);
				predicate(0);
				}
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

	public static class UpdateStatementContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(TinyDBParser.UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SET() { return getToken(TinyDBParser.SET, 0); }
		public List<UpdatedElementContext> updatedElement() {
			return getRuleContexts(UpdatedElementContext.class);
		}
		public UpdatedElementContext updatedElement(int i) {
			return getRuleContext(UpdatedElementContext.class,i);
		}
		public TerminalNode WHERE() { return getToken(TinyDBParser.WHERE, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
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
		enterRule(_localctx, 36, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(UPDATE);
			setState(219);
			tableName();
			setState(220);
			match(SET);
			setState(221);
			updatedElement();
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(222);
				match(COMMA);
				setState(223);
				updatedElement();
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(229);
				match(WHERE);
				setState(230);
				predicate(0);
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
		public AttrNameContext attrName() {
			return getRuleContext(AttrNameContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
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
		enterRule(_localctx, 38, RULE_updatedElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			attrName();
			setState(234);
			match(EQUAL_SYMBOL);
			setState(235);
			constant();
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
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
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
		enterRule(_localctx, 40, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(DELETE);
			setState(238);
			match(FROM);
			setState(239);
			tableName();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(240);
				match(WHERE);
				setState(241);
				predicate(0);
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
	public static class ComparisonExpressionPredicateContext extends PredicateContext {
		public ExpressionAtomContext left;
		public ExpressionAtomContext right;
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public List<ExpressionAtomContext> expressionAtom() {
			return getRuleContexts(ExpressionAtomContext.class);
		}
		public ExpressionAtomContext expressionAtom(int i) {
			return getRuleContext(ExpressionAtomContext.class,i);
		}
		public ComparisonExpressionPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitComparisonExpressionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalExpressionPredicateContext extends PredicateContext {
		public PredicateContext left;
		public PredicateContext right;
		public LogicalOperatorContext logicalOperator() {
			return getRuleContext(LogicalOperatorContext.class,0);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public LogicalExpressionPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitLogicalExpressionPredicate(this);
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
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_predicate, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ComparisonExpressionPredicateContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(245);
			((ComparisonExpressionPredicateContext)_localctx).left = expressionAtom();
			setState(246);
			comparisonOperator();
			setState(247);
			((ComparisonExpressionPredicateContext)_localctx).right = expressionAtom();
			}
			_ctx.stop = _input.LT(-1);
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalExpressionPredicateContext(new PredicateContext(_parentctx, _parentState));
					((LogicalExpressionPredicateContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_predicate);
					setState(249);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(250);
					logicalOperator();
					setState(251);
					((LogicalExpressionPredicateContext)_localctx).right = predicate(3);
					}
					} 
				}
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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

	public final ExpressionAtomContext expressionAtom() throws RecognitionException {
		ExpressionAtomContext _localctx = new ExpressionAtomContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expressionAtom);
		try {
			setState(260);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case ZERO_DECIMAL:
			case ONE_DECIMAL:
			case TWO_DECIMAL:
			case STRING_LITERAL:
			case DECIMAL_LITERAL:
			case REAL_LITERAL:
				_localctx = new ConstantExpressionAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				constant();
				}
				break;
			case ID:
				_localctx = new FullColumnNameExpressionAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				fullColumnName();
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
		enterRule(_localctx, 46, RULE_dataType);
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(262);
				match(INT);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(LONG);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(264);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(265);
				match(DOUBLE);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(266);
				match(STRING);
				}
				setState(267);
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
		enterRule(_localctx, 48, RULE_lengthOneDimension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(LR_BRACKET);
			setState(271);
			match(DECIMAL_LITERAL);
			setState(272);
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

	public static class ConstantsContext extends ParserRuleContext {
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public ConstantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constants; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitConstants(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantsContext constants() throws RecognitionException {
		ConstantsContext _localctx = new ConstantsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_constants);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			constant();
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(275);
				match(COMMA);
				setState(276);
				constant();
				}
				}
				setState(281);
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

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(TinyDBParser.STRING_LITERAL, 0); }
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public TerminalNode REAL_LITERAL() { return getToken(TinyDBParser.REAL_LITERAL, 0); }
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
		enterRule(_localctx, 52, RULE_constant);
		try {
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				match(STRING_LITERAL);
				}
				break;
			case ZERO_DECIMAL:
			case ONE_DECIMAL:
			case TWO_DECIMAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				decimalLiteral();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(284);
				match(MINUS);
				setState(285);
				decimalLiteral();
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(286);
				match(REAL_LITERAL);
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
		enterRule(_localctx, 54, RULE_comparisonOperator);
		try {
			setState(300);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				match(EQUAL_SYMBOL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(GREATER_SYMBOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(291);
				match(LESS_SYMBOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(292);
				match(LESS_SYMBOL);
				setState(293);
				match(EQUAL_SYMBOL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(294);
				match(GREATER_SYMBOL);
				setState(295);
				match(EQUAL_SYMBOL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(296);
				match(LESS_SYMBOL);
				setState(297);
				match(GREATER_SYMBOL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(298);
				match(EXCLAMATION_SYMBOL);
				setState(299);
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
		enterRule(_localctx, 56, RULE_logicalOperator);
		try {
			setState(309);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				match(AND);
				}
				break;
			case BIT_AND_OP:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				match(BIT_AND_OP);
				setState(304);
				match(BIT_AND_OP);
				}
				break;
			case XOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(305);
				match(XOR);
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 4);
				{
				setState(306);
				match(OR);
				}
				break;
			case BIT_OR_OP:
				enterOuterAlt(_localctx, 5);
				{
				setState(307);
				match(BIT_OR_OP);
				setState(308);
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
		enterRule(_localctx, 58, RULE_showStatement);
		try {
			setState(319);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				match(SHOW);
				setState(312);
				match(DATABASES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(SHOW);
				setState(314);
				match(DATABASE);
				setState(315);
				dbName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(316);
				match(SHOW);
				setState(317);
				match(TABLE);
				setState(318);
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
		enterRule(_localctx, 60, RULE_shutdownStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
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
		enterRule(_localctx, 62, RULE_dbName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
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
		enterRule(_localctx, 64, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
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
		enterRule(_localctx, 66, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
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
		enterRule(_localctx, 68, RULE_attrNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			attrName();
			setState(334);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(330);
				match(COMMA);
				setState(331);
				attrName();
				}
				}
				setState(336);
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
		enterRule(_localctx, 70, RULE_nullNotnull);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(337);
				match(NOT);
				}
			}

			setState(340);
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
		enterRule(_localctx, 72, RULE_decimalLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
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
		case 21:
			return predicate_sempred((PredicateContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean predicate_sempred(PredicateContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3m\u015b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\3\3\3\3\3\7\3S\n\3\f"+
		"\3\16\3V\13\3\3\4\3\4\3\4\5\4[\n\4\3\5\3\5\3\5\3\5\3\5\5\5b\n\5\3\6\3"+
		"\6\3\6\3\6\5\6h\n\6\3\7\3\7\5\7l\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\7\ty\n\t\f\t\16\t|\13\t\3\t\3\t\3\n\3\n\3\n\5\n\u0083\n\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u008b\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00a0\n\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00ab\n\17\f\17\16"+
		"\17\u00ae\13\17\3\17\3\17\3\17\3\17\5\17\u00b4\n\17\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00bb\n\20\3\21\3\21\3\21\7\21\u00c0\n\21\f\21\16\21\u00c3"+
		"\13\21\3\22\3\22\7\22\u00c7\n\22\f\22\16\22\u00ca\13\22\3\23\3\23\3\23"+
		"\3\23\5\23\u00d0\n\23\3\23\3\23\5\23\u00d4\n\23\3\23\3\23\3\23\3\23\3"+
		"\23\5\23\u00db\n\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u00e3\n\24\f\24"+
		"\16\24\u00e6\13\24\3\24\3\24\5\24\u00ea\n\24\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u00f5\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\7\27\u0100\n\27\f\27\16\27\u0103\13\27\3\30\3\30\5\30\u0107"+
		"\n\30\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u010f\n\31\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\7\33\u0118\n\33\f\33\16\33\u011b\13\33\3\34\3\34\3\34"+
		"\3\34\3\34\5\34\u0122\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\5\35\u012f\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0138"+
		"\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0142\n\37\3 \3 \3"+
		"!\3!\3\"\3\"\3#\3#\3$\3$\3$\7$\u014f\n$\f$\16$\u0152\13$\3%\5%\u0155\n"+
		"%\3%\3%\3&\3&\3&\2\3,\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*"+
		",.\60\62\64\668:<>@BDFHJ\2\4\4\2\35\35\'\'\4\2_ahh\2\u0167\2L\3\2\2\2"+
		"\4T\3\2\2\2\6Z\3\2\2\2\ba\3\2\2\2\ng\3\2\2\2\fk\3\2\2\2\16m\3\2\2\2\20"+
		"q\3\2\2\2\22\u008a\3\2\2\2\24\u008c\3\2\2\2\26\u0090\3\2\2\2\30\u0094"+
		"\3\2\2\2\32\u0098\3\2\2\2\34\u00a6\3\2\2\2\36\u00ba\3\2\2\2 \u00bc\3\2"+
		"\2\2\"\u00c4\3\2\2\2$\u00da\3\2\2\2&\u00dc\3\2\2\2(\u00eb\3\2\2\2*\u00ef"+
		"\3\2\2\2,\u00f6\3\2\2\2.\u0106\3\2\2\2\60\u010e\3\2\2\2\62\u0110\3\2\2"+
		"\2\64\u0114\3\2\2\2\66\u0121\3\2\2\28\u012e\3\2\2\2:\u0137\3\2\2\2<\u0141"+
		"\3\2\2\2>\u0143\3\2\2\2@\u0145\3\2\2\2B\u0147\3\2\2\2D\u0149\3\2\2\2F"+
		"\u014b\3\2\2\2H\u0154\3\2\2\2J\u0158\3\2\2\2LM\5\4\3\2MN\7\2\2\3N\3\3"+
		"\2\2\2OP\5\6\4\2PQ\7]\2\2QS\3\2\2\2RO\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3"+
		"\2\2\2U\5\3\2\2\2VT\3\2\2\2W[\5\b\5\2X[\5\n\6\2Y[\5\f\7\2ZW\3\2\2\2ZX"+
		"\3\2\2\2ZY\3\2\2\2[\7\3\2\2\2\\b\5\16\b\2]b\5\20\t\2^b\5\24\13\2_b\5\26"+
		"\f\2`b\5\30\r\2a\\\3\2\2\2a]\3\2\2\2a^\3\2\2\2a_\3\2\2\2a`\3\2\2\2b\t"+
		"\3\2\2\2ch\5\34\17\2dh\5\32\16\2eh\5&\24\2fh\5*\26\2gc\3\2\2\2gd\3\2\2"+
		"\2ge\3\2\2\2gf\3\2\2\2h\13\3\2\2\2il\5<\37\2jl\5> \2ki\3\2\2\2kj\3\2\2"+
		"\2l\r\3\2\2\2mn\7\13\2\2no\7\r\2\2op\5@!\2p\17\3\2\2\2qr\7\13\2\2rs\7"+
		"+\2\2st\5B\"\2tu\7Z\2\2uz\5\22\n\2vw\7\\\2\2wy\5\22\n\2xv\3\2\2\2y|\3"+
		"\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2\2\2|z\3\2\2\2}~\7[\2\2~\21\3\2\2\2\177"+
		"\u0080\5D#\2\u0080\u0082\5\60\31\2\u0081\u0083\5H%\2\u0082\u0081\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u008b\3\2\2\2\u0084\u0085\7&\2\2\u0085\u0086"+
		"\7\34\2\2\u0086\u0087\7Z\2\2\u0087\u0088\5F$\2\u0088\u0089\7[\2\2\u0089"+
		"\u008b\3\2\2\2\u008a\177\3\2\2\2\u008a\u0084\3\2\2\2\u008b\23\3\2\2\2"+
		"\u008c\u008d\7\23\2\2\u008d\u008e\7\r\2\2\u008e\u008f\5@!\2\u008f\25\3"+
		"\2\2\2\u0090\u0091\7\23\2\2\u0091\u0092\7+\2\2\u0092\u0093\5B\"\2\u0093"+
		"\27\3\2\2\2\u0094\u0095\7.\2\2\u0095\u0096\7\r\2\2\u0096\u0097\5@!\2\u0097"+
		"\31\3\2\2\2\u0098\u0099\7\30\2\2\u0099\u009a\7\31\2\2\u009a\u009f\5B\""+
		"\2\u009b\u009c\7Z\2\2\u009c\u009d\5F$\2\u009d\u009e\7[\2\2\u009e\u00a0"+
		"\3\2\2\2\u009f\u009b\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\7/\2\2\u00a2\u00a3\7Z\2\2\u00a3\u00a4\5\64\33\2\u00a4\u00a5\7["+
		"\2\2\u00a5\33\3\2\2\2\u00a6\u00a7\7(\2\2\u00a7\u00ac\5\36\20\2\u00a8\u00a9"+
		"\7\\\2\2\u00a9\u00ab\5\36\20\2\u00aa\u00a8\3\2\2\2\u00ab\u00ae\3\2\2\2"+
		"\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac"+
		"\3\2\2\2\u00af\u00b0\7\25\2\2\u00b0\u00b3\5 \21\2\u00b1\u00b2\7\60\2\2"+
		"\u00b2\u00b4\5,\27\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\35"+
		"\3\2\2\2\u00b5\u00b6\5B\"\2\u00b6\u00b7\7Y\2\2\u00b7\u00b8\5D#\2\u00b8"+
		"\u00bb\3\2\2\2\u00b9\u00bb\5D#\2\u00ba\u00b5\3\2\2\2\u00ba\u00b9\3\2\2"+
		"\2\u00bb\37\3\2\2\2\u00bc\u00c1\5\"\22\2\u00bd\u00be\7\\\2\2\u00be\u00c0"+
		"\5\"\22\2\u00bf\u00bd\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2"+
		"\u00c1\u00c2\3\2\2\2\u00c2!\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c8\5"+
		"B\"\2\u00c5\u00c7\5$\23\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9#\3\2\2\2\u00ca\u00c8\3\2\2\2"+
		"\u00cb\u00cc\7\33\2\2\u00cc\u00cf\5B\"\2\u00cd\u00ce\7\"\2\2\u00ce\u00d0"+
		"\5,\27\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00db\3\2\2\2\u00d1"+
		"\u00d3\t\2\2\2\u00d2\u00d4\7%\2\2\u00d3\u00d2\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\7\33\2\2\u00d6\u00d7\5B\"\2\u00d7"+
		"\u00d8\7\"\2\2\u00d8\u00d9\5,\27\2\u00d9\u00db\3\2\2\2\u00da\u00cb\3\2"+
		"\2\2\u00da\u00d1\3\2\2\2\u00db%\3\2\2\2\u00dc\u00dd\7-\2\2\u00dd\u00de"+
		"\5B\"\2\u00de\u00df\7)\2\2\u00df\u00e4\5(\25\2\u00e0\u00e1\7\\\2\2\u00e1"+
		"\u00e3\5(\25\2\u00e2\u00e0\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2"+
		"\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e9\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7"+
		"\u00e8\7\60\2\2\u00e8\u00ea\5,\27\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3"+
		"\2\2\2\u00ea\'\3\2\2\2\u00eb\u00ec\5D#\2\u00ec\u00ed\7Q\2\2\u00ed\u00ee"+
		"\5\66\34\2\u00ee)\3\2\2\2\u00ef\u00f0\7\20\2\2\u00f0\u00f1\7\25\2\2\u00f1"+
		"\u00f4\5B\"\2\u00f2\u00f3\7\60\2\2\u00f3\u00f5\5,\27\2\u00f4\u00f2\3\2"+
		"\2\2\u00f4\u00f5\3\2\2\2\u00f5+\3\2\2\2\u00f6\u00f7\b\27\1\2\u00f7\u00f8"+
		"\5.\30\2\u00f8\u00f9\58\35\2\u00f9\u00fa\5.\30\2\u00fa\u0101\3\2\2\2\u00fb"+
		"\u00fc\f\4\2\2\u00fc\u00fd\5:\36\2\u00fd\u00fe\5,\27\5\u00fe\u0100\3\2"+
		"\2\2\u00ff\u00fb\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102-\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0107\5\66\34"+
		"\2\u0105\u0107\5\36\20\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2\2\2\u0107"+
		"/\3\2\2\2\u0108\u010f\7\63\2\2\u0109\u010f\7\64\2\2\u010a\u010f\7\66\2"+
		"\2\u010b\u010f\7\65\2\2\u010c\u010d\79\2\2\u010d\u010f\5\62\32\2\u010e"+
		"\u0108\3\2\2\2\u010e\u0109\3\2\2\2\u010e\u010a\3\2\2\2\u010e\u010b\3\2"+
		"\2\2\u010e\u010c\3\2\2\2\u010f\61\3\2\2\2\u0110\u0111\7Z\2\2\u0111\u0112"+
		"\7h\2\2\u0112\u0113\7[\2\2\u0113\63\3\2\2\2\u0114\u0119\5\66\34\2\u0115"+
		"\u0116\7\\\2\2\u0116\u0118\5\66\34\2\u0117\u0115\3\2\2\2\u0118\u011b\3"+
		"\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\65\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011c\u0122\7g\2\2\u011d\u0122\5J&\2\u011e\u011f\7N\2\2"+
		"\u011f\u0122\5J&\2\u0120\u0122\7j\2\2\u0121\u011c\3\2\2\2\u0121\u011d"+
		"\3\2\2\2\u0121\u011e\3\2\2\2\u0121\u0120\3\2\2\2\u0122\67\3\2\2\2\u0123"+
		"\u012f\7Q\2\2\u0124\u012f\7R\2\2\u0125\u012f\7S\2\2\u0126\u0127\7S\2\2"+
		"\u0127\u012f\7Q\2\2\u0128\u0129\7R\2\2\u0129\u012f\7Q\2\2\u012a\u012b"+
		"\7S\2\2\u012b\u012f\7R\2\2\u012c\u012d\7T\2\2\u012d\u012f\7Q\2\2\u012e"+
		"\u0123\3\2\2\2\u012e\u0124\3\2\2\2\u012e\u0125\3\2\2\2\u012e\u0126\3\2"+
		"\2\2\u012e\u0128\3\2\2\2\u012e\u012a\3\2\2\2\u012e\u012c\3\2\2\2\u012f"+
		"9\3\2\2\2\u0130\u0138\7\7\2\2\u0131\u0132\7W\2\2\u0132\u0138\7W\2\2\u0133"+
		"\u0138\7\62\2\2\u0134\u0138\7#\2\2\u0135\u0136\7V\2\2\u0136\u0138\7V\2"+
		"\2\u0137\u0130\3\2\2\2\u0137\u0131\3\2\2\2\u0137\u0133\3\2\2\2\u0137\u0134"+
		"\3\2\2\2\u0137\u0135\3\2\2\2\u0138;\3\2\2\2\u0139\u013a\7*\2\2\u013a\u0142"+
		"\7\16\2\2\u013b\u013c\7*\2\2\u013c\u013d\7\r\2\2\u013d\u0142\5@!\2\u013e"+
		"\u013f\7*\2\2\u013f\u0140\7+\2\2\u0140\u0142\5B\"\2\u0141\u0139\3\2\2"+
		"\2\u0141\u013b\3\2\2\2\u0141\u013e\3\2\2\2\u0142=\3\2\2\2\u0143\u0144"+
		"\7?\2\2\u0144?\3\2\2\2\u0145\u0146\7l\2\2\u0146A\3\2\2\2\u0147\u0148\7"+
		"l\2\2\u0148C\3\2\2\2\u0149\u014a\7l\2\2\u014aE\3\2\2\2\u014b\u0150\5D"+
		"#\2\u014c\u014d\7\\\2\2\u014d\u014f\5D#\2\u014e\u014c\3\2\2\2\u014f\u0152"+
		"\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151G\3\2\2\2\u0152"+
		"\u0150\3\2\2\2\u0153\u0155\7 \2\2\u0154\u0153\3\2\2\2\u0154\u0155\3\2"+
		"\2\2\u0155\u0156\3\2\2\2\u0156\u0157\7!\2\2\u0157I\3\2\2\2\u0158\u0159"+
		"\t\3\2\2\u0159K\3\2\2\2 TZagkz\u0082\u008a\u009f\u00ac\u00b3\u00ba\u00c1"+
		"\u00c8\u00cf\u00d3\u00da\u00e4\u00e9\u00f4\u0101\u0106\u010e\u0119\u0121"+
		"\u012e\u0137\u0141\u0150\u0154";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}