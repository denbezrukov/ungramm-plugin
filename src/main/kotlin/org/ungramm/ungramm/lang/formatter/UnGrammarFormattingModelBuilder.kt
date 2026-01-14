package org.ungramm.ungramm.lang.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.formatter.common.AbstractBlock
import org.ungramm.ungramm.lang.UnGrammarLanguage
import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

class UnGrammarFormattingModelBuilder : FormattingModelBuilder {

    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val settings = formattingContext.codeStyleSettings
        val element = formattingContext.psiElement

        val spacingBuilder = SpacingBuilder(settings, UnGrammarLanguage.INSTANCE)
            .around(UnGrammarTypes.EQ).spaces(1)
            .around(UnGrammarTypes.PIPE).spaces(1)
            .before(UnGrammarTypes.COLON).spaces(0)
            .after(UnGrammarTypes.COLON).spaces(0)
            .before(UnGrammarTypes.STAR).spaces(0)
            .before(UnGrammarTypes.QUESTION).spaces(0)
            .after(UnGrammarTypes.LPAREN).spaces(0)
            .before(UnGrammarTypes.RPAREN).spaces(0)

        return FormattingModelProvider.createFormattingModelForPsiFile(
            element.containingFile,
            UnGrammarBlock(
                element.node,
                Wrap.createWrap(WrapType.NONE, false),
                Alignment.createAlignment(),
                spacingBuilder
            ),
            settings
        )
    }
}
