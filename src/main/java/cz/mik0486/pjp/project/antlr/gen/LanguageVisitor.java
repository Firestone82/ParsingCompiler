// Generated from C:/Users/Pavel/OneDrive - VSB-TUO/Skola/Vysoka/6-Semestr/PJP/Project/src/main/antlr4/Project/Language.g4 by ANTLR 4.13.1
package cz.mik0486.pjp.project.antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LanguageParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(LanguageParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclStatement(LanguageParser.VarDeclStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStatement(LanguageParser.ExprStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(LanguageParser.ReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code writeStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteStatement(LanguageParser.WriteStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(LanguageParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(LanguageParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(LanguageParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doWhileStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(LanguageParser.DoWhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(LanguageParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolCondition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolCondition(LanguageParser.BoolConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moduloExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuloExpression(LanguageParser.ModuloExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpression(LanguageParser.VarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpression(LanguageParser.AssignExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(LanguageParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationExpression(LanguageParser.RelationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(LanguageParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(LanguageParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatExpression(LanguageParser.ConcatExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aritmExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAritmExpression(LanguageParser.AritmExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicExpression(LanguageParser.LogicExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpression(LanguageParser.NegExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(LanguageParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ternaryExpression}
	 * labeled alternative in {@link LanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpression(LanguageParser.TernaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(LanguageParser.LiteralContext ctx);
}