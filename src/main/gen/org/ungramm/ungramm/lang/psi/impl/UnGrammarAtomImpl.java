// This is a generated file. Not intended for manual editing.
package org.ungramm.ungramm.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.ungramm.ungramm.lang.psi.UnGrammarTypes.*;
import org.ungramm.ungramm.lang.psi.*;

public class UnGrammarAtomImpl extends UnGrammarAtomMixin implements UnGrammarAtom {

  public UnGrammarAtomImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UnGrammarVisitor visitor) {
    visitor.visitAtom(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UnGrammarVisitor) accept((UnGrammarVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public UnGrammarAlternation getAlternation() {
    return findChildByClass(UnGrammarAlternation.class);
  }

  @Override
  @Nullable
  public PsiElement getIdent() {
    return findChildByType(IDENT);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

}
