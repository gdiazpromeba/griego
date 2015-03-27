// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.modelos;

import com.kalos.beans.DesinSust;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;

// Referenced classes of package kalos.A.C:
//            B

public class DesinSustPM extends PagingModel<DesinSust> {

    public DesinSustPM(FuenteDatosCacheable b, int i) {
	super(b, i);
    }

    public Object getValueAt(int i, int j) {
	DesinSust a = (DesinSust) getFila(i);
	switch (j) {
	case 0: // '\0'
	    return Integer.valueOf(a.getSubindice());

	case 1: // '\001'
	    return a.getDesinencia();

	case 2: // '\002'
	    return Integer.valueOf(a.getSilaba());

	case 3: // '\003'
	    return a.getAcento();

	case 4: // '\004'
	    return a.getCaso();

	case 5: // '\005'
	    return a.getNumero();

	case 6: // '\006'
	    return a.getContraccion();

	case 7: // '\007'
	    return a.getOrigenTema();

	case 8: // '\b'
	    return a.getAcentoConcuerda();

	case 9: // '\t'
	    return a.getPosicionConcuerda();

	case 10: // '\n'
	    return Integer.valueOf(a.getTipoSustantivo());
	}
	return null;
    }

    public int getColumnCount() {
	return 11;
    }
}
