// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.modelos;

import com.kalos.beans.IrrVerboIndividual;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;

// Referenced classes of package kalos.A.C:
//            B

public class IrrVerboIndividualPM extends PagingModel<IrrVerboIndividual> {

    public IrrVerboIndividualPM(FuenteDatosCacheable b, int i) {
	super(b, i);
    }

    public Object getValueAt(int i, int j) {
	IrrVerboIndividual q1 = (IrrVerboIndividual) getFila(i);
	switch (j) {
	case 0: // '\0'
	    return q1.getForma();

	case 1: // '\001'
	    return q1.getPartic();

	case 2: // '\002'
	    return Integer.valueOf(q1.getSubPart());

	case 3: // '\003'
	    return Boolean.valueOf(q1.isRespetaAcento());

	case 4: // '\004'
	    return q1.getModo();

	case 5: // '\005'
	    return q1.getTiempo();

	case 6: // '\006'
	    return q1.getVoz();

	case 7: // '\007'
	    return q1.getFuerte();
	}
	return null;
    }

    public int getColumnCount() {
	return 8;
    }
}
