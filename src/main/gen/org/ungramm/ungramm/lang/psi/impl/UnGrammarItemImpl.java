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

public class UnGrammarItemImpl extends ASTWrapperPsiElement implements UnGrammarItem {

  public UnGrammarItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UnGrammarVisitor visitor) {
    visitor.visitItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UnGrammarVisitor) accept((UnGrammarVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public UnGrammarAtom getAtom() {
    return findChildByClass(UnGrammarAtom.class);
  }

  @Override
  @Nullable
  public UnGrammarLabeled getLabeled() {
    return findChildByClass(UnGrammarLabeled.class);
  }

  @Override
  @Nullable
  public UnGrammarPostfix getPostfix() {
    return findChildByClass(UnGrammarPostfix.class);
  }

}
