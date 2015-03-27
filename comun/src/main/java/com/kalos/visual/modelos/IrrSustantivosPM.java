// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.modelos;

import com.kalos.beans.IrrSustantivoBean;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;

// Referenced classes of package kalos.A.C:
//            B

public class IrrSustantivosPM extends PagingModel<IrrSustantivoBean> {

    public IrrSustantivosPM(FuenteDatosCacheable b, int i) {
	super(b, i);
    }

    public Object getValueAt(int i, int j) {
	IrrSustantivoBean u = (IrrSustantivoBean) getFila(i);
	switch (j) {
	case 0: // '\0'
	    return u.getForma();

	case 1: // '\001'
	    return u.getCaso();

	case 2: // '\002'
	    return u.getNumero();

	case 3: // '\003'
	    return Integer.valueOf(u.getSubindice());
	}
	return null;
    }

    public int getColumnCount() {
	return 4;
    }
}
