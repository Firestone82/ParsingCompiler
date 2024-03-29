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
	 * Enter a parse tree produced by the {@code binaryCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterBinaryCondition(LanguageParser.BinaryConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitBinaryCondition(LanguageParser.BinaryConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterUnaryCondition(LanguageParser.UnaryConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitUnaryCondition(LanguageParser.UnaryConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterVarCondition(LanguageParser.VarConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitVarCondition(LanguageParser.VarConditionContext ctx);
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
	 * Enter a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(LanguageParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(LanguageParser.BinaryExpressionContext ctx);
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
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(LanguageParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(LanguageParser.UnaryExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link LanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(LanguageParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(LanguageParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(LanguageParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(LanguageParser.UnaryContext ctx);
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