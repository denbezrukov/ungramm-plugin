package org.ungramm.ungramm.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class UnGrammarFileType private constructor() : LanguageFileType(UnGrammarLanguage.INSTANCE) {
    companion object {
        @JvmStatic
        val INSTANCE = UnGrammarFileType()
    }

    override fun getName(): String = "UnGrammar File"

    override fun getDescription(): String = "UnGrammar language file"

    override fun getDefaultExtension(): String = "ungram"

    override fun getIcon(): Icon? = UnGrammarIcons.FILE
}
