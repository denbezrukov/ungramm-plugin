package org.ungramm.ungramm.lang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import org.ungramm.ungramm.lang.UnGrammarFileType

object UnGrammarElementFactory {

    fun createRule(project: Project, name: String): UnGrammarRule {
        val file = createFile(project, "$name = 'dummy'")
        return file.firstChild as UnGrammarRule
    }

    fun createFile(project: Project, text: String): UnGrammarFile {
        val name = "dummy.ungram"
        return PsiFileFactory.getInstance(project)
            .createFileFromText(name, UnGrammarFileType.INSTANCE, text) as UnGrammarFile
    }
}
