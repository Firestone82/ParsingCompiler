// Generated from C:/Users/Pavel/OneDrive - VSB-TUO/Skola/Vysoka/6-Semestr/PJP/Project/src/main/antlr4/Project/Language.g4 by ANTLR 4.13.1
package cz.mik0486.pjp.project.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LanguageParser}.
 */
public interface LanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(LanguageParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(LanguageParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDeclStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclStatement(LanguageParser.VarDeclStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDeclStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclStatement(LanguageParser.VarDeclStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStatement(LanguageParser.ExprStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStatement(LanguageParser.ExprStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReadStatement(LanguageParser.ReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReadStatement(LanguageParser.ReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code writeStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWriteStatement(LanguageParser.WriteStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code writeStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWriteStatement(LanguageParser.WriteStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(LanguageParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(LanguageParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(LanguageParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(LanguageParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(LanguageParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(LanguageParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doWhileStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(LanguageParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doWhileStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(LanguageParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(LanguageParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(LanguageParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterBoolCondition(LanguageParser.BoolConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitBoolCondition(LanguageParser.BoolConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moduloExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterModuloExpression(LanguageParser.ModuloExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moduloExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitModuloExpression(LanguageParser.ModuloExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVarExpression(LanguageParser.VarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVarExpression(LanguageParser.VarExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpression(LanguageParser.AssignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpression(LanguageParser.AssignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aritmExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAritmExpression(LanguageParser.AritmExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aritmExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAritmExpression(LanguageParser.AritmExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpression(LanguageParser.LogicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpression(LanguageParser.LogicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(LanguageParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(LanguageParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationExpression(LanguageParser.RelationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationExpression(LanguageParser.RelationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(LanguageParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(LanguageParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(LanguageParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(LanguageParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegExpression(LanguageParser.NegExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegExpression(LanguageParser.NegExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(LanguageParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(LanguageParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConcatExpression(LanguageParser.ConcatExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConcatExpression(LanguageParser.ConcatExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(LanguageParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(LanguageParser.LiteralContext ctx);
}