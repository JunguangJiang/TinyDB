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
		RULE_root = 0, RULE_sqlStatements = 1, RULE_sqlStatement = 2, RULE_dbSpecifiedStatement = 3, 
		RULE_dbUnspecifiedStatement = 4, RULE_createDatabase = 5, RULE_createTable = 6, 
		RULE_createDefinition = 7, RULE_dropDatabase = 8, RULE_dropTable = 9, 
		RULE_useDatabase = 10, RULE_insertStatement = 11, RULE_selectStatement = 12, 
		RULE_fullColumnNames = 13, RULE_fullColumnName = 14, RULE_tableSources = 15, 
		RULE_tableSource = 16, RULE_joinPart = 17, RULE_updateStatement = 18, 
		RULE_updatedElement = 19, RULE_deleteStatement = 20, RULE_predicate = 21, 
		RULE_andExpressionPredicate = 22, RULE_comparisonExpressionPredicate = 23, 
		RULE_dataType = 24, RULE_lengthOneDimension = 25, RULE_constants = 26, 
		RULE_constant = 27, RULE_comparisonOperator = 28, RULE_showStatement = 29, 
		RULE_shutdownStatement = 30, RULE_dbName = 31, RULE_table = 32, RULE_tableName = 33, 
		RULE_attrName = 34, RULE_attrNames = 35, RULE_nullNotnull = 36, RULE_decimalLiteral = 37;
	public static final String[] ruleNames = {
		"root", "sqlStatements", "sqlStatement", "dbSpecifiedStatement", "dbUnspecifiedStatement", 
		"createDatabase", "createTable", "createDefinition", "dropDatabase", "dropTable", 
		"useDatabase", "insertStatement", "selectStatement", "fullColumnNames", 
		"fullColumnName", "tableSources", "tableSource", "joinPart", "updateStatement", 
		"updatedElement", "deleteStatement", "predicate", "andExpressionPredicate", 
		"comparisonExpressionPredicate", "dataType", "lengthOneDimension", "constants", 
		"constant", "comparisonOperator", "showStatement", "shutdownStatement", 
		"dbName", "table", "tableName", "attrName", "attrNames", "nullNotnull", 
		"decimalLiteral"
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
			setState(76);
			sqlStatements();
			setState(77);
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
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << DELETE) | (1L << DROP) | (1L << INSERT) | (1L << SELECT) | (1L << SHOW) | (1L << UPDATE) | (1L << USE) | (1L << SHUTDOWN))) != 0)) {
				{
				{
				setState(79);
				sqlStatement();
				setState(80);
				match(SEMI);
				}
				}
				setState(86);
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
		public DbSpecifiedStatementContext dbSpecifiedStatement() {
			return getRuleContext(DbSpecifiedStatementContext.class,0);
		}
		public DbUnspecifiedStatementContext dbUnspecifiedStatement() {
			return getRuleContext(DbUnspecifiedStatementContext.class,0);
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
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				dbSpecifiedStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				dbUnspecifiedStatement();
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

	public static class DbSpecifiedStatementContext extends ParserRuleContext {
		public CreateTableContext createTable() {
			return getRuleContext(CreateTableContext.class,0);
		}
		public DropTableContext dropTable() {
			return getRuleContext(DropTableContext.class,0);
		}
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
		public DbSpecifiedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dbSpecifiedStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDbSpecifiedStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DbSpecifiedStatementContext dbSpecifiedStatement() throws RecognitionException {
		DbSpecifiedStatementContext _localctx = new DbSpecifiedStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dbSpecifiedStatement);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				createTable();
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				dropTable();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				selectStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 5);
				{
				setState(95);
				updateStatement();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 6);
				{
				setState(96);
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

	public static class DbUnspecifiedStatementContext extends ParserRuleContext {
		public CreateDatabaseContext createDatabase() {
			return getRuleContext(CreateDatabaseContext.class,0);
		}
		public DropDatabaseContext dropDatabase() {
			return getRuleContext(DropDatabaseContext.class,0);
		}
		public UseDatabaseContext useDatabase() {
			return getRuleContext(UseDatabaseContext.class,0);
		}
		public ShowStatementContext showStatement() {
			return getRuleContext(ShowStatementContext.class,0);
		}
		public ShutdownStatementContext shutdownStatement() {
			return getRuleContext(ShutdownStatementContext.class,0);
		}
		public DbUnspecifiedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dbUnspecifiedStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitDbUnspecifiedStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DbUnspecifiedStatementContext dbUnspecifiedStatement() throws RecognitionException {
		DbUnspecifiedStatementContext _localctx = new DbUnspecifiedStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dbUnspecifiedStatement);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				createDatabase();
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				dropDatabase();
				}
				break;
			case USE:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				useDatabase();
				}
				break;
			case SHOW:
				enterOuterAlt(_localctx, 4);
				{
				setState(102);
				showStatement();
				}
				break;
			case SHUTDOWN:
				enterOuterAlt(_localctx, 5);
				{
				setState(103);
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
		enterRule(_localctx, 10, RULE_createDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(CREATE);
			setState(107);
			match(DATABASE);
			setState(108);
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
		enterRule(_localctx, 12, RULE_createTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(CREATE);
			setState(111);
			match(TABLE);
			setState(112);
			tableName();
			setState(113);
			match(LR_BRACKET);
			setState(114);
			createDefinition();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(115);
				match(COMMA);
				setState(116);
				createDefinition();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
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
		public AttrNameContext attrName() {
			return getRuleContext(AttrNameContext.class,0);
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
		enterRule(_localctx, 14, RULE_createDefinition);
		int _la;
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new ColumnDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				attrName();
				setState(125);
				dataType();
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(126);
					nullNotnull();
					}
				}

				}
				break;
			case PRIMARY:
				_localctx = new ConstraintDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(PRIMARY);
				setState(130);
				match(KEY);
				setState(131);
				match(LR_BRACKET);
				setState(132);
				attrName();
				setState(133);
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
		enterRule(_localctx, 16, RULE_dropDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(DROP);
			setState(138);
			match(DATABASE);
			setState(139);
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
		enterRule(_localctx, 18, RULE_dropTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(DROP);
			setState(142);
			match(TABLE);
			setState(143);
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
		enterRule(_localctx, 20, RULE_useDatabase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(USE);
			setState(146);
			match(DATABASE);
			setState(147);
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
		enterRule(_localctx, 22, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(INSERT);
			setState(150);
			match(INTO);
			setState(151);
			tableName();
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LR_BRACKET) {
				{
				setState(152);
				match(LR_BRACKET);
				setState(153);
				attrNames();
				setState(154);
				match(RR_BRACKET);
				}
			}

			setState(158);
			match(VALUES);
			setState(159);
			match(LR_BRACKET);
			setState(160);
			constants();
			setState(161);
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
		public FullColumnNamesContext fullColumnNames() {
			return getRuleContext(FullColumnNamesContext.class,0);
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
		enterRule(_localctx, 24, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(SELECT);
			setState(164);
			fullColumnNames();
			setState(165);
			match(FROM);
			setState(166);
			tableSources();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(167);
				match(WHERE);
				setState(168);
				((SelectStatementContext)_localctx).whereExpr = predicate();
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

	public static class FullColumnNamesContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(TinyDBParser.STAR, 0); }
		public List<FullColumnNameContext> fullColumnName() {
			return getRuleContexts(FullColumnNameContext.class);
		}
		public FullColumnNameContext fullColumnName(int i) {
			return getRuleContext(FullColumnNameContext.class,i);
		}
		public FullColumnNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullColumnNames; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitFullColumnNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullColumnNamesContext fullColumnNames() throws RecognitionException {
		FullColumnNamesContext _localctx = new FullColumnNamesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fullColumnNames);
		int _la;
		try {
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				match(STAR);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(172);
				fullColumnName();
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(173);
					match(COMMA);
					setState(174);
					fullColumnName();
					}
					}
					setState(179);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class FullColumnNameContext extends ParserRuleContext {
		public Token alias;
		public AttrNameContext attrName() {
			return getRuleContext(AttrNameContext.class,0);
		}
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode AS() { return getToken(TinyDBParser.AS, 0); }
		public TerminalNode ID() { return getToken(TinyDBParser.ID, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(182);
				tableName();
				setState(183);
				match(DOT);
				}
				break;
			}
			setState(187);
			attrName();
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(188);
				match(AS);
				setState(189);
				((FullColumnNameContext)_localctx).alias = match(ID);
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
		enterRule(_localctx, 30, RULE_tableSources);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			tableSource();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(193);
				match(COMMA);
				setState(194);
				tableSource();
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
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
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
			setState(200);
			table();
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << JOIN) | (1L << LEFT) | (1L << RIGHT))) != 0)) {
				{
				{
				setState(201);
				joinPart();
				}
				}
				setState(206);
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
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode ON() { return getToken(TinyDBParser.ON, 0); }
		public ComparisonExpressionPredicateContext comparisonExpressionPredicate() {
			return getRuleContext(ComparisonExpressionPredicateContext.class,0);
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
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(TinyDBParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(TinyDBParser.RIGHT, 0); }
		public TerminalNode ON() { return getToken(TinyDBParser.ON, 0); }
		public ComparisonExpressionPredicateContext comparisonExpressionPredicate() {
			return getRuleContext(ComparisonExpressionPredicateContext.class,0);
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
			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case JOIN:
				_localctx = new InnerJoinContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				match(JOIN);
				setState(208);
				table();
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(209);
					match(ON);
					setState(210);
					comparisonExpressionPredicate();
					}
				}

				}
				break;
			case LEFT:
			case RIGHT:
				_localctx = new OuterJoinContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				_la = _input.LA(1);
				if ( !(_la==LEFT || _la==RIGHT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(214);
					match(OUTER);
					}
				}

				setState(217);
				match(JOIN);
				setState(218);
				table();
				{
				setState(219);
				match(ON);
				setState(220);
				comparisonExpressionPredicate();
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
			setState(224);
			match(UPDATE);
			setState(225);
			tableName();
			setState(226);
			match(SET);
			setState(227);
			updatedElement();
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(228);
				match(COMMA);
				setState(229);
				updatedElement();
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(235);
				match(WHERE);
				setState(236);
				predicate();
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
			setState(239);
			attrName();
			setState(240);
			match(EQUAL_SYMBOL);
			setState(241);
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
			setState(243);
			match(DELETE);
			setState(244);
			match(FROM);
			setState(245);
			tableName();
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(246);
				match(WHERE);
				setState(247);
				predicate();
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
		public List<AndExpressionPredicateContext> andExpressionPredicate() {
			return getRuleContexts(AndExpressionPredicateContext.class);
		}
		public AndExpressionPredicateContext andExpressionPredicate(int i) {
			return getRuleContext(AndExpressionPredicateContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(TinyDBParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(TinyDBParser.OR, i);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			andExpressionPredicate();
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(251);
				match(OR);
				setState(252);
				andExpressionPredicate();
				}
				}
				setState(257);
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

	public static class AndExpressionPredicateContext extends ParserRuleContext {
		public List<ComparisonExpressionPredicateContext> comparisonExpressionPredicate() {
			return getRuleContexts(ComparisonExpressionPredicateContext.class);
		}
		public ComparisonExpressionPredicateContext comparisonExpressionPredicate(int i) {
			return getRuleContext(ComparisonExpressionPredicateContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(TinyDBParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(TinyDBParser.AND, i);
		}
		public AndExpressionPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpressionPredicate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitAndExpressionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpressionPredicateContext andExpressionPredicate() throws RecognitionException {
		AndExpressionPredicateContext _localctx = new AndExpressionPredicateContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_andExpressionPredicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			comparisonExpressionPredicate();
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(259);
				match(AND);
				setState(260);
				comparisonExpressionPredicate();
				}
				}
				setState(265);
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

	public static class ComparisonExpressionPredicateContext extends ParserRuleContext {
		public ComparisonExpressionPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpressionPredicate; }
	 
		public ComparisonExpressionPredicateContext() { }
		public void copyFrom(ComparisonExpressionPredicateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class KvCmpExpressionPredicateContext extends ComparisonExpressionPredicateContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public KvCmpExpressionPredicateContext(ComparisonExpressionPredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitKvCmpExpressionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class KkCmpExpressionPredicateContext extends ComparisonExpressionPredicateContext {
		public List<FullColumnNameContext> fullColumnName() {
			return getRuleContexts(FullColumnNameContext.class);
		}
		public FullColumnNameContext fullColumnName(int i) {
			return getRuleContext(FullColumnNameContext.class,i);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public KkCmpExpressionPredicateContext(ComparisonExpressionPredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitKkCmpExpressionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsCmpExpressionPredicateContext extends ComparisonExpressionPredicateContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public TerminalNode IS() { return getToken(TinyDBParser.IS, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(TinyDBParser.NULL_LITERAL, 0); }
		public TerminalNode NOT() { return getToken(TinyDBParser.NOT, 0); }
		public IsCmpExpressionPredicateContext(ComparisonExpressionPredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitIsCmpExpressionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VvCmpExpressionPredicateContext extends ComparisonExpressionPredicateContext {
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public VvCmpExpressionPredicateContext(ComparisonExpressionPredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitVvCmpExpressionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VkCmpExpressionPredicateContext extends ComparisonExpressionPredicateContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public VkCmpExpressionPredicateContext(ComparisonExpressionPredicateContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitVkCmpExpressionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonExpressionPredicateContext comparisonExpressionPredicate() throws RecognitionException {
		ComparisonExpressionPredicateContext _localctx = new ComparisonExpressionPredicateContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_comparisonExpressionPredicate);
		int _la;
		try {
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new VkCmpExpressionPredicateContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				constant();
				setState(267);
				comparisonOperator();
				setState(268);
				fullColumnName();
				}
				break;
			case 2:
				_localctx = new KvCmpExpressionPredicateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				fullColumnName();
				setState(271);
				comparisonOperator();
				setState(272);
				constant();
				}
				break;
			case 3:
				_localctx = new KkCmpExpressionPredicateContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				fullColumnName();
				setState(275);
				comparisonOperator();
				setState(276);
				fullColumnName();
				}
				break;
			case 4:
				_localctx = new VvCmpExpressionPredicateContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(278);
				constant();
				setState(279);
				comparisonOperator();
				setState(280);
				constant();
				}
				break;
			case 5:
				_localctx = new IsCmpExpressionPredicateContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(282);
				fullColumnName();
				setState(283);
				match(IS);
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(284);
					match(NOT);
					}
				}

				setState(287);
				match(NULL_LITERAL);
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
		enterRule(_localctx, 48, RULE_dataType);
		try {
			setState(297);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(INT);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				match(LONG);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(294);
				match(DOUBLE);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(295);
				match(STRING);
				}
				setState(296);
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
		enterRule(_localctx, 50, RULE_lengthOneDimension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(LR_BRACKET);
			setState(300);
			match(DECIMAL_LITERAL);
			setState(301);
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
		enterRule(_localctx, 52, RULE_constants);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			constant();
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(304);
				match(COMMA);
				setState(305);
				constant();
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

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(TinyDBParser.STRING_LITERAL, 0); }
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public TerminalNode REAL_LITERAL() { return getToken(TinyDBParser.REAL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(TinyDBParser.NULL_LITERAL, 0); }
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
		enterRule(_localctx, 54, RULE_constant);
		try {
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				match(STRING_LITERAL);
				}
				break;
			case ZERO_DECIMAL:
			case ONE_DECIMAL:
			case TWO_DECIMAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				decimalLiteral();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(313);
				match(MINUS);
				setState(314);
				decimalLiteral();
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(315);
				match(REAL_LITERAL);
				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(316);
				match(NULL_LITERAL);
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
		enterRule(_localctx, 56, RULE_comparisonOperator);
		try {
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(EQUAL_SYMBOL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(320);
				match(GREATER_SYMBOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(321);
				match(LESS_SYMBOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(322);
				match(LESS_SYMBOL);
				setState(323);
				match(EQUAL_SYMBOL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(324);
				match(GREATER_SYMBOL);
				setState(325);
				match(EQUAL_SYMBOL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(326);
				match(LESS_SYMBOL);
				setState(327);
				match(GREATER_SYMBOL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(328);
				match(EXCLAMATION_SYMBOL);
				setState(329);
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

	public static class ShowStatementContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(TinyDBParser.SHOW, 0); }
		public TerminalNode DATABASES() { return getToken(TinyDBParser.DATABASES, 0); }
		public TerminalNode DATABASE() { return getToken(TinyDBParser.DATABASE, 0); }
		public DbNameContext dbName() {
			return getRuleContext(DbNameContext.class,0);
		}
		public TerminalNode TABLE() { return getToken(TinyDBParser.TABLE, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
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
			setState(340);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				match(SHOW);
				setState(333);
				match(DATABASES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				match(SHOW);
				setState(335);
				match(DATABASE);
				setState(336);
				dbName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(337);
				match(SHOW);
				setState(338);
				match(TABLE);
				setState(339);
				table();
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
			setState(342);
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
			setState(344);
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

	public static class TableContext extends ParserRuleContext {
		public TableNameContext originalName;
		public TableNameContext alias;
		public List<TableNameContext> tableName() {
			return getRuleContexts(TableNameContext.class);
		}
		public TableNameContext tableName(int i) {
			return getRuleContext(TableNameContext.class,i);
		}
		public TerminalNode AS() { return getToken(TinyDBParser.AS, 0); }
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyDBParserVisitor ) return ((TinyDBParserVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			((TableContext)_localctx).originalName = tableName();
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(347);
				match(AS);
				setState(348);
				((TableContext)_localctx).alias = tableName();
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
		enterRule(_localctx, 66, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
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
		enterRule(_localctx, 68, RULE_attrName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
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
		enterRule(_localctx, 70, RULE_attrNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			attrName();
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(356);
				match(COMMA);
				setState(357);
				attrName();
				}
				}
				setState(362);
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
		public TerminalNode NOT() { return getToken(TinyDBParser.NOT, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(TinyDBParser.NULL_LITERAL, 0); }
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
		enterRule(_localctx, 72, RULE_nullNotnull);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			match(NOT);
			setState(364);
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
		enterRule(_localctx, 74, RULE_decimalLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3m\u0173\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\3\3\3\3\3\7"+
		"\3U\n\3\f\3\16\3X\13\3\3\4\3\4\5\4\\\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5d"+
		"\n\5\3\6\3\6\3\6\3\6\3\6\5\6k\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\7\bx\n\b\f\b\16\b{\13\b\3\b\3\b\3\t\3\t\3\t\5\t\u0082\n\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u008a\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u009f\n\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00ac\n\16\3\17\3\17\3\17"+
		"\3\17\7\17\u00b2\n\17\f\17\16\17\u00b5\13\17\5\17\u00b7\n\17\3\20\3\20"+
		"\3\20\5\20\u00bc\n\20\3\20\3\20\3\20\5\20\u00c1\n\20\3\21\3\21\3\21\7"+
		"\21\u00c6\n\21\f\21\16\21\u00c9\13\21\3\22\3\22\7\22\u00cd\n\22\f\22\16"+
		"\22\u00d0\13\22\3\23\3\23\3\23\3\23\5\23\u00d6\n\23\3\23\3\23\5\23\u00da"+
		"\n\23\3\23\3\23\3\23\3\23\3\23\5\23\u00e1\n\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\7\24\u00e9\n\24\f\24\16\24\u00ec\13\24\3\24\3\24\5\24\u00f0\n\24"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u00fb\n\26\3\27\3\27"+
		"\3\27\7\27\u0100\n\27\f\27\16\27\u0103\13\27\3\30\3\30\3\30\7\30\u0108"+
		"\n\30\f\30\16\30\u010b\13\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u0120\n\31"+
		"\3\31\3\31\5\31\u0124\n\31\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u012c\n"+
		"\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\7\34\u0135\n\34\f\34\16\34\u0138"+
		"\13\34\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0140\n\35\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u014d\n\36\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\5\37\u0157\n\37\3 \3 \3!\3!\3\"\3\"\3\"\5\""+
		"\u0160\n\"\3#\3#\3$\3$\3%\3%\3%\7%\u0169\n%\f%\16%\u016c\13%\3&\3&\3&"+
		"\3\'\3\'\3\'\2\2(\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJL\2\4\4\2\35\35\'\'\4\2_ahh\2\u0182\2N\3\2\2\2\4V\3\2"+
		"\2\2\6[\3\2\2\2\bc\3\2\2\2\nj\3\2\2\2\fl\3\2\2\2\16p\3\2\2\2\20\u0089"+
		"\3\2\2\2\22\u008b\3\2\2\2\24\u008f\3\2\2\2\26\u0093\3\2\2\2\30\u0097\3"+
		"\2\2\2\32\u00a5\3\2\2\2\34\u00b6\3\2\2\2\36\u00bb\3\2\2\2 \u00c2\3\2\2"+
		"\2\"\u00ca\3\2\2\2$\u00e0\3\2\2\2&\u00e2\3\2\2\2(\u00f1\3\2\2\2*\u00f5"+
		"\3\2\2\2,\u00fc\3\2\2\2.\u0104\3\2\2\2\60\u0123\3\2\2\2\62\u012b\3\2\2"+
		"\2\64\u012d\3\2\2\2\66\u0131\3\2\2\28\u013f\3\2\2\2:\u014c\3\2\2\2<\u0156"+
		"\3\2\2\2>\u0158\3\2\2\2@\u015a\3\2\2\2B\u015c\3\2\2\2D\u0161\3\2\2\2F"+
		"\u0163\3\2\2\2H\u0165\3\2\2\2J\u016d\3\2\2\2L\u0170\3\2\2\2NO\5\4\3\2"+
		"OP\7\2\2\3P\3\3\2\2\2QR\5\6\4\2RS\7]\2\2SU\3\2\2\2TQ\3\2\2\2UX\3\2\2\2"+
		"VT\3\2\2\2VW\3\2\2\2W\5\3\2\2\2XV\3\2\2\2Y\\\5\b\5\2Z\\\5\n\6\2[Y\3\2"+
		"\2\2[Z\3\2\2\2\\\7\3\2\2\2]d\5\16\b\2^d\5\24\13\2_d\5\32\16\2`d\5\30\r"+
		"\2ad\5&\24\2bd\5*\26\2c]\3\2\2\2c^\3\2\2\2c_\3\2\2\2c`\3\2\2\2ca\3\2\2"+
		"\2cb\3\2\2\2d\t\3\2\2\2ek\5\f\7\2fk\5\22\n\2gk\5\26\f\2hk\5<\37\2ik\5"+
		"> \2je\3\2\2\2jf\3\2\2\2jg\3\2\2\2jh\3\2\2\2ji\3\2\2\2k\13\3\2\2\2lm\7"+
		"\13\2\2mn\7\r\2\2no\5@!\2o\r\3\2\2\2pq\7\13\2\2qr\7+\2\2rs\5D#\2st\7Z"+
		"\2\2ty\5\20\t\2uv\7\\\2\2vx\5\20\t\2wu\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3"+
		"\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7[\2\2}\17\3\2\2\2~\177\5F$\2\177\u0081\5"+
		"\62\32\2\u0080\u0082\5J&\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u008a\3\2\2\2\u0083\u0084\7&\2\2\u0084\u0085\7\34\2\2\u0085\u0086\7Z"+
		"\2\2\u0086\u0087\5F$\2\u0087\u0088\7[\2\2\u0088\u008a\3\2\2\2\u0089~\3"+
		"\2\2\2\u0089\u0083\3\2\2\2\u008a\21\3\2\2\2\u008b\u008c\7\23\2\2\u008c"+
		"\u008d\7\r\2\2\u008d\u008e\5@!\2\u008e\23\3\2\2\2\u008f\u0090\7\23\2\2"+
		"\u0090\u0091\7+\2\2\u0091\u0092\5D#\2\u0092\25\3\2\2\2\u0093\u0094\7."+
		"\2\2\u0094\u0095\7\r\2\2\u0095\u0096\5@!\2\u0096\27\3\2\2\2\u0097\u0098"+
		"\7\30\2\2\u0098\u0099\7\31\2\2\u0099\u009e\5D#\2\u009a\u009b\7Z\2\2\u009b"+
		"\u009c\5H%\2\u009c\u009d\7[\2\2\u009d\u009f\3\2\2\2\u009e\u009a\3\2\2"+
		"\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\7/\2\2\u00a1\u00a2"+
		"\7Z\2\2\u00a2\u00a3\5\66\34\2\u00a3\u00a4\7[\2\2\u00a4\31\3\2\2\2\u00a5"+
		"\u00a6\7(\2\2\u00a6\u00a7\5\34\17\2\u00a7\u00a8\7\25\2\2\u00a8\u00ab\5"+
		" \21\2\u00a9\u00aa\7\60\2\2\u00aa\u00ac\5,\27\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\33\3\2\2\2\u00ad\u00b7\7I\2\2\u00ae\u00b3\5\36\20"+
		"\2\u00af\u00b0\7\\\2\2\u00b0\u00b2\5\36\20\2\u00b1\u00af\3\2\2\2\u00b2"+
		"\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b7\3\2"+
		"\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00ad\3\2\2\2\u00b6\u00ae\3\2\2\2\u00b7"+
		"\35\3\2\2\2\u00b8\u00b9\5D#\2\u00b9\u00ba\7Y\2\2\u00ba\u00bc\3\2\2\2\u00bb"+
		"\u00b8\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00c0\5F"+
		"$\2\u00be\u00bf\7\b\2\2\u00bf\u00c1\7l\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1"+
		"\3\2\2\2\u00c1\37\3\2\2\2\u00c2\u00c7\5\"\22\2\u00c3\u00c4\7\\\2\2\u00c4"+
		"\u00c6\5\"\22\2\u00c5\u00c3\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3"+
		"\2\2\2\u00c7\u00c8\3\2\2\2\u00c8!\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00ce"+
		"\5B\"\2\u00cb\u00cd\5$\23\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf#\3\2\2\2\u00d0\u00ce\3\2\2\2"+
		"\u00d1\u00d2\7\33\2\2\u00d2\u00d5\5B\"\2\u00d3\u00d4\7\"\2\2\u00d4\u00d6"+
		"\5\60\31\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00e1\3\2\2\2"+
		"\u00d7\u00d9\t\2\2\2\u00d8\u00da\7%\2\2\u00d9\u00d8\3\2\2\2\u00d9\u00da"+
		"\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\7\33\2\2\u00dc\u00dd\5B\"\2\u00dd"+
		"\u00de\7\"\2\2\u00de\u00df\5\60\31\2\u00df\u00e1\3\2\2\2\u00e0\u00d1\3"+
		"\2\2\2\u00e0\u00d7\3\2\2\2\u00e1%\3\2\2\2\u00e2\u00e3\7-\2\2\u00e3\u00e4"+
		"\5D#\2\u00e4\u00e5\7)\2\2\u00e5\u00ea\5(\25\2\u00e6\u00e7\7\\\2\2\u00e7"+
		"\u00e9\5(\25\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ef\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"\u00ee\7\60\2\2\u00ee\u00f0\5,\27\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3"+
		"\2\2\2\u00f0\'\3\2\2\2\u00f1\u00f2\5F$\2\u00f2\u00f3\7Q\2\2\u00f3\u00f4"+
		"\58\35\2\u00f4)\3\2\2\2\u00f5\u00f6\7\20\2\2\u00f6\u00f7\7\25\2\2\u00f7"+
		"\u00fa\5D#\2\u00f8\u00f9\7\60\2\2\u00f9\u00fb\5,\27\2\u00fa\u00f8\3\2"+
		"\2\2\u00fa\u00fb\3\2\2\2\u00fb+\3\2\2\2\u00fc\u0101\5.\30\2\u00fd\u00fe"+
		"\7#\2\2\u00fe\u0100\5.\30\2\u00ff\u00fd\3\2\2\2\u0100\u0103\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102-\3\2\2\2\u0103\u0101\3\2\2\2"+
		"\u0104\u0109\5\60\31\2\u0105\u0106\7\7\2\2\u0106\u0108\5\60\31\2\u0107"+
		"\u0105\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2"+
		"\2\2\u010a/\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\58\35\2\u010d\u010e"+
		"\5:\36\2\u010e\u010f\5\36\20\2\u010f\u0124\3\2\2\2\u0110\u0111\5\36\20"+
		"\2\u0111\u0112\5:\36\2\u0112\u0113\58\35\2\u0113\u0124\3\2\2\2\u0114\u0115"+
		"\5\36\20\2\u0115\u0116\5:\36\2\u0116\u0117\5\36\20\2\u0117\u0124\3\2\2"+
		"\2\u0118\u0119\58\35\2\u0119\u011a\5:\36\2\u011a\u011b\58\35\2\u011b\u0124"+
		"\3\2\2\2\u011c\u011d\5\36\20\2\u011d\u011f\7\32\2\2\u011e\u0120\7 \2\2"+
		"\u011f\u011e\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122"+
		"\7!\2\2\u0122\u0124\3\2\2\2\u0123\u010c\3\2\2\2\u0123\u0110\3\2\2\2\u0123"+
		"\u0114\3\2\2\2\u0123\u0118\3\2\2\2\u0123\u011c\3\2\2\2\u0124\61\3\2\2"+
		"\2\u0125\u012c\7\63\2\2\u0126\u012c\7\64\2\2\u0127\u012c\7\66\2\2\u0128"+
		"\u012c\7\65\2\2\u0129\u012a\79\2\2\u012a\u012c\5\64\33\2\u012b\u0125\3"+
		"\2\2\2\u012b\u0126\3\2\2\2\u012b\u0127\3\2\2\2\u012b\u0128\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012c\63\3\2\2\2\u012d\u012e\7Z\2\2\u012e\u012f\7h\2\2"+
		"\u012f\u0130\7[\2\2\u0130\65\3\2\2\2\u0131\u0136\58\35\2\u0132\u0133\7"+
		"\\\2\2\u0133\u0135\58\35\2\u0134\u0132\3\2\2\2\u0135\u0138\3\2\2\2\u0136"+
		"\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\67\3\2\2\2\u0138\u0136\3\2\2"+
		"\2\u0139\u0140\7g\2\2\u013a\u0140\5L\'\2\u013b\u013c\7N\2\2\u013c\u0140"+
		"\5L\'\2\u013d\u0140\7j\2\2\u013e\u0140\7!\2\2\u013f\u0139\3\2\2\2\u013f"+
		"\u013a\3\2\2\2\u013f\u013b\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u013e\3\2"+
		"\2\2\u01409\3\2\2\2\u0141\u014d\7Q\2\2\u0142\u014d\7R\2\2\u0143\u014d"+
		"\7S\2\2\u0144\u0145\7S\2\2\u0145\u014d\7Q\2\2\u0146\u0147\7R\2\2\u0147"+
		"\u014d\7Q\2\2\u0148\u0149\7S\2\2\u0149\u014d\7R\2\2\u014a\u014b\7T\2\2"+
		"\u014b\u014d\7Q\2\2\u014c\u0141\3\2\2\2\u014c\u0142\3\2\2\2\u014c\u0143"+
		"\3\2\2\2\u014c\u0144\3\2\2\2\u014c\u0146\3\2\2\2\u014c\u0148\3\2\2\2\u014c"+
		"\u014a\3\2\2\2\u014d;\3\2\2\2\u014e\u014f\7*\2\2\u014f\u0157\7\16\2\2"+
		"\u0150\u0151\7*\2\2\u0151\u0152\7\r\2\2\u0152\u0157\5@!\2\u0153\u0154"+
		"\7*\2\2\u0154\u0155\7+\2\2\u0155\u0157\5B\"\2\u0156\u014e\3\2\2\2\u0156"+
		"\u0150\3\2\2\2\u0156\u0153\3\2\2\2\u0157=\3\2\2\2\u0158\u0159\7?\2\2\u0159"+
		"?\3\2\2\2\u015a\u015b\7l\2\2\u015bA\3\2\2\2\u015c\u015f\5D#\2\u015d\u015e"+
		"\7\b\2\2\u015e\u0160\5D#\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"C\3\2\2\2\u0161\u0162\7l\2\2\u0162E\3\2\2\2\u0163\u0164\7l\2\2\u0164G"+
		"\3\2\2\2\u0165\u016a\5F$\2\u0166\u0167\7\\\2\2\u0167\u0169\5F$\2\u0168"+
		"\u0166\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2"+
		"\2\2\u016bI\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u016e\7 \2\2\u016e\u016f"+
		"\7!\2\2\u016fK\3\2\2\2\u0170\u0171\t\3\2\2\u0171M\3\2\2\2\"V[cjy\u0081"+
		"\u0089\u009e\u00ab\u00b3\u00b6\u00bb\u00c0\u00c7\u00ce\u00d5\u00d9\u00e0"+
		"\u00ea\u00ef\u00fa\u0101\u0109\u011f\u0123\u012b\u0136\u013f\u014c\u0156"+
		"\u015f\u016a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}