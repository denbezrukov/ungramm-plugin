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

public class UnGrammarSequenceImpl extends ASTWrapperPsiElement implements UnGrammarSequence {

  public UnGrammarSequenceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UnGrammarVisitor visitor) {
    visitor.visitSequence(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UnGrammarVisitor) accept((UnGrammarVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<UnGrammarItem> getItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, UnGrammarItem.class);
  }

}
