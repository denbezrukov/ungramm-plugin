package org.ungramm.ungramm.lang.psi

import com.intellij.psi.tree.IElementType
import org.ungramm.ungramm.lang.UnGrammarLanguage

class UnGrammarElementType(debugName: String) : IElementType(debugName, UnGrammarLanguage.INSTANCE)
