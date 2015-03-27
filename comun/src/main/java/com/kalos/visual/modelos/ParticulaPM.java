// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.modelos;

import com.kalos.beans.ParticulaBean;
import com.kalos.beans.Significado;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.operaciones.OpSignificados;

// Referenced classes of package kalos.A.C:
//            B

public class ParticulaPM extends PagingModel<ParticulaBean> {

    public ParticulaPM(FuenteDatosCacheable b, int i) {
	super(b, i);
    }

    public Object getValueAt(int i, int j) {
	ParticulaBean l1 = (ParticulaBean) getFila(i);
	switch (j) {
	case 0: // '\0'
	    return l1.getForma();

	case 1: // '\001'
	    return l1.getParticulaTipo();

	case 2: // '\002'
	    return l1.getGenero();

	case 3: // '\003'
	    return l1.getNumero();

	case 4: // '\004'
	    return l1.getCaso();

	case 5: // '\005'
	    return l1.getPersona();

	case 6: // '\006'
	    Significado q = OpSignificados.getSignificadoIndividual(l1);
	    return q.getValor();

	case 7: // '\007'
	    return l1.getParticularidad();
	}
	return null;
    }

    public int getColumnCount() {
	return 8;
    }
}
