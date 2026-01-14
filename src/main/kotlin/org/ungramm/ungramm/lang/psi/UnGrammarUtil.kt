package org.ungramm.ungramm.lang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import org.ungramm.ungramm.lang.UnGrammarFileType

object UnGrammarUtil {

    fun findRules(project: Project): List<UnGrammarRule> {
        val rules = mutableListOf<UnGrammarRule>()
        val virtualFiles = FileTypeIndex.getFiles(
            UnGrammarFileType.INSTANCE,
            GlobalSearchScope.allScope(project)
        )

        for (virtualFile in virtualFiles) {
            val file = PsiManager.getInstance(project).findFile(virtualFile) as? UnGrammarFile ?: continue
            rules.addAll(PsiTreeUtil.getChildrenOfTypeAsList(file, UnGrammarRule::class.java))
        }

        return rules
    }

    fun findRule(project: Project, name: String): UnGrammarRule? {
        return findRules(project).firstOrNull { it.name == name }
    }
}
