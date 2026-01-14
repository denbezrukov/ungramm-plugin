// This is a generated file. Not intended for manual editing.
package org.ungramm.ungramm.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface UnGrammarLabeled extends PsiElement {

  @NotNull
  UnGrammarAtom getAtom();

  @Nullable
  UnGrammarPostfix getPostfix();

  @NotNull
  PsiElement getIdent();

}
