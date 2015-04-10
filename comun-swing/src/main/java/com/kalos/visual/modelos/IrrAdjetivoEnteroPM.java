// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.modelos;

import com.kalos.beans.IrrAdjetivoEntero;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;

// Referenced classes of package kalos.A.C:
//            B

public class IrrAdjetivoEnteroPM extends PagingModel<IrrAdjetivoEntero> {

    public IrrAdjetivoEnteroPM(FuenteDatosCacheable b, int i) {
	super(b, i);
    }

    public Object getValueAt(int i, int j) {
	IrrAdjetivoEntero v1 = (IrrAdjetivoEntero) getFila(i);
	switch (j) {
	case 0: // '\0'
	    return v1.getGenero();

	case 1: // '\001'
	    return v1.getGrado();

	case 2: // '\002'
	    return v1.getParticularidad();

	case 3: // '\003'
	    return Integer.valueOf(v1.getSubindice());

	case 4: // '\004'
	    return v1.getNominativo();

	case 5: // '\005'
	    return v1.getGenitivo();
	}
	return null;
    }

    public int getColumnCount() {
	return 6;
    }
}
