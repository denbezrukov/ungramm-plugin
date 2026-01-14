package org.ungramm.ungramm.lang

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import org.ungramm.ungramm.lang.psi.UnGrammarRule
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

class UnGrammarFindUsagesProvider : FindUsagesProvider {

    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(
            UnGrammarLexerAdapter(),
            TokenSet.create(UnGrammarTypes.IDENT),
            TokenSet.create(UnGrammarTypes.COMMENT),
            TokenSet.create(UnGrammarTypes.STRING)
        )
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun getType(element: PsiElement): String {
        return if (element is UnGrammarRule) {
            "rule"
        } else {
            ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return if (element is UnGrammarRule) {
            element.name ?: ""
        } else {
            ""
        }
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return if (element is UnGrammarRule) {
            element.text
        } else {
            ""
        }
    }
}
