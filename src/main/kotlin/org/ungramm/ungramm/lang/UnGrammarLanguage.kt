package org.ungramm.ungramm.lang

import com.intellij.lang.Language

class UnGrammarLanguage private constructor() : Language("UnGrammar") {
    companion object {
        @JvmStatic
        val INSTANCE = UnGrammarLanguage()
    }
}
