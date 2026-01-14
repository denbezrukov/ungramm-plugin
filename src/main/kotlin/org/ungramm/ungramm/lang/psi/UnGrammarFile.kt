package org.ungramm.ungramm.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.ungramm.ungramm.lang.UnGrammarFileType
import org.ungramm.ungramm.lang.UnGrammarLanguage

class UnGrammarFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, UnGrammarLanguage.INSTANCE) {
    override fun getFileType(): FileType = UnGrammarFileType.INSTANCE

    override fun toString(): String = "UnGrammar File"
}
