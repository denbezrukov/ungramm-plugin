package org.ungramm.ungramm.lang

import com.intellij.lexer.FlexAdapter

class UnGrammarLexerAdapter : FlexAdapter(UnGrammarLexer(null))
