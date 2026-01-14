// This is a generated file. Not intended for manual editing.
package org.ungramm.ungramm.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.ungramm.ungramm.lang.psi.UnGrammarTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.ungramm.ungramm.lang.psi.*;

public class UnGrammarAlternationImpl extends ASTWrapperPsiElement implements UnGrammarAlternation {

  public UnGrammarAlternationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UnGrammarVisitor visitor) {
    visitor.visitAlternation(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UnGrammarVisitor) accept((UnGrammarVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<UnGrammarSequence> getSequenceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, UnGrammarSequence.class);
  }

}
