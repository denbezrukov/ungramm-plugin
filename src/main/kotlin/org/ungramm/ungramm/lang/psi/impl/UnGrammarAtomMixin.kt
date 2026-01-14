package org.ungramm.ungramm.lang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import org.ungramm.ungramm.lang.psi.UnGrammarAtom
import org.ungramm.ungramm.lang.psi.UnGrammarReference
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

abstract class UnGrammarAtomMixin(node: ASTNode) : ASTWrapperPsiElement(node), UnGrammarAtom {

    override fun getReference(): PsiReference? {
        val identNode = node.findChildByType(UnGrammarTypes.IDENT) ?: return null
        val identElement = identNode.psi
        // Calculate the range of the IDENT relative to this atom element
        val startOffset = identElement.startOffsetInParent
        val endOffset = startOffset + identElement.textLength
        return UnGrammarReference(this, TextRange(startOffset, endOffset))
    }
}
