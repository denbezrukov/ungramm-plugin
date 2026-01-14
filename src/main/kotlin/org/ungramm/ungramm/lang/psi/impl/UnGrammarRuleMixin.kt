package org.ungramm.ungramm.lang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import org.ungramm.ungramm.lang.psi.UnGrammarElementFactory
import org.ungramm.ungramm.lang.psi.UnGrammarNamedElement
import org.ungramm.ungramm.lang.psi.UnGrammarTypes

abstract class UnGrammarRuleMixin(node: ASTNode) : ASTWrapperPsiElement(node), UnGrammarNamedElement {

    override fun getName(): String? {
        return nameIdentifier?.text
    }

    override fun setName(name: String): PsiElement {
        val identNode = node.findChildByType(UnGrammarTypes.IDENT)
        if (identNode != null) {
            val newRule = UnGrammarElementFactory.createRule(project, name)
            val newIdentNode = newRule.node.findChildByType(UnGrammarTypes.IDENT)
            if (newIdentNode != null) {
                node.replaceChild(identNode, newIdentNode)
            }
        }
        return this
    }

    override fun getNameIdentifier(): PsiElement? {
        return node.findChildByType(UnGrammarTypes.IDENT)?.psi
    }

    override fun getTextOffset(): Int {
        return nameIdentifier?.textOffset ?: super.getTextOffset()
    }
}
