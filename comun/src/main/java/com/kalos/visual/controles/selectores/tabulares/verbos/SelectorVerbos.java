// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.selectores.tabulares.verbos;

import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.visual.controles.FabricaControles;
import com.kalos.visual.controles.selectores.tabulares.SelectorActivoBeansTabular;

// Referenced classes of package kalos.A.B.F.A.A:
//            A

public class SelectorVerbos extends SelectorActivoBeansTabular {

    public SelectorVerbos(GerenteVerbos p, FabricaControles a, int i, boolean flag, boolean flag1, boolean flag2) {
	super(p, flag, flag1, flag2, true, "verbo");
	gerenteVerbos = p;
	fabricaContrloles = a;
    }

    protected DialogSelectorVerbos getDialog() {
	return new DialogSelectorVerbos(fabricaContrloles, gerenteVerbos);
    }

    private GerenteVerbos gerenteVerbos;
    private FabricaControles fabricaContrloles;
}
