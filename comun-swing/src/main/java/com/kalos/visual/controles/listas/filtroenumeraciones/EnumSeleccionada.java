// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.listas.filtroenumeraciones;

import com.kalos.enumeraciones.TipoPalabra;

public class EnumSeleccionada {

    public EnumSeleccionada(Enum enu, boolean flag) {
	enumeracion = enu;
	seleccionada = flag;
    }

    public boolean isSeleccionado() {
	return seleccionada;
    }

    public void setSeleccionado(boolean flag) {
	seleccionada = flag;
    }

    public Enum getEnumeracion() {
	return enumeracion;
    }

    public void setTipoPalabra(TipoPalabra l) {
	enumeracion = l;
    }

    private Enum enumeracion;
    private boolean seleccionada;
}
