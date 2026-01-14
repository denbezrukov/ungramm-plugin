package org.ungramm.ungramm.lang

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.ungramm.ungramm.lang.parser.UnGrammarParser
import org.ungramm.ungramm.lang.psi.UnGrammarFile
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

class UnGrammarParserDefinition : ParserDefinition {
    companion object {
        val FILE = IFileElementType(UnGrammarLanguage.INSTANCE)
        val COMMENTS = TokenSet.create(UnGrammarTypes.COMMENT)
    }

    override fun createLexer(project: Project?): Lexer = UnGrammarLexerAdapter()

    override fun createParser(project: Project?): PsiParser = UnGrammarParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode?): PsiElement = UnGrammarTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = UnGrammarFile(viewProvider)
}
