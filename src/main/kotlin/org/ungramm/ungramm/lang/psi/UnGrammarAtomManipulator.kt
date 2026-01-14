package org.ungramm.ungramm.lang.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator
import com.intellij.util.IncorrectOperationException

class UnGrammarAtomManipulator : AbstractElementManipulator<UnGrammarAtom>() {

    @Throws(IncorrectOperationException::class)
    override fun handleContentChange(
        element: UnGrammarAtom,
        range: TextRange,
        newContent: String
    ): UnGrammarAtom {
        // Create a new rule with the new name and replace the IDENT token
        val newRule = UnGrammarElementFactory.createRule(element.project, newContent)
        val newAtom = newRule.node.findChildByType(UnGrammarTypes.IDENT)?.psi

        if (newAtom != null) {
            val identNode = element.node.findChildByType(UnGrammarTypes.IDENT)
            if (identNode != null) {
                val newIdentNode = newRule.node.findChildByType(UnGrammarTypes.IDENT)
                if (newIdentNode != null) {
                    element.node.replaceChild(identNode, newIdentNode)
                }
            }
        }

        return element
    }
}
