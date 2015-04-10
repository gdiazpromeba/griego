// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.selectores.tabulares;

import com.kalos.datos.gerentes.SeleccionadorUno;
import com.kalos.datos.gerentes.SeleccionadorUnoTodos;
import com.kalos.operaciones.OpBeans;

// Referenced classes of package kalos.A.B.F.A:
//            B

public abstract class SelectorActivoBeansTabular extends SelectorTabular {

    public SelectorActivoBeansTabular(SeleccionadorUno c, boolean flag, boolean flag1, boolean flag2,
	    boolean flag3, String s) {
	super(c, flag, flag1, flag2, flag3, s);
    }

    public void muestraDialog() {
	dialog = getDialog();
	dialog.setSize(540, 380);
	dialog.setLocationRelativeTo(null);
	dialog.setModal(true);
	dialog.setVisible(true);
	if (dialog.isAcepto()) {
	    Object obj = dialog.getBeanSeleccionado();
	    L = obj;
	    super.B.setText(OpBeans.getPropiedad(obj, H));
	}
	dialog.dispose();
    }
}
