package org.ungramm.ungramm.lang.psi

import com.intellij.psi.*

class UnGrammarReferenceContributor : PsiReferenceContributor() {

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        // References are now provided by UnGrammarAtomMixin
    }
}
