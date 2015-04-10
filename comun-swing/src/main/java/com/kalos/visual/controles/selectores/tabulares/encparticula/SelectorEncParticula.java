// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.selectores.tabulares.encparticula;

import com.kalos.datos.gerentes.GerenteEncParticulasImpl;
import com.kalos.visual.controles.FabricaControles;
import com.kalos.visual.controles.selectores.tabulares.SelectorActivoBeansTabular;

// Referenced classes of package kalos.A.B.F.A.B:
//            B

public class SelectorEncParticula extends SelectorActivoBeansTabular {

    public SelectorEncParticula(GerenteEncParticulasImpl k, FabricaControles a, int i, boolean flag, boolean flag1,
	    boolean flag2) {
	super(k, flag, flag1, flag2, true, "forma");
	M = k;
    }

    protected DialogSelectorEncParticula getDialog() {
	return new DialogSelectorEncParticula(M);
    }

    private GerenteEncParticulasImpl M;
}
