package org.ungramm.ungramm.lang.psi

import com.intellij.psi.tree.IElementType
import org.ungramm.ungramm.lang.UnGrammarLanguage

class UnGrammarTokenType(debugName: String) : IElementType(debugName, UnGrammarLanguage.INSTANCE) {
    override fun toString(): String = "UnGrammarTokenType." + super.toString()
}
