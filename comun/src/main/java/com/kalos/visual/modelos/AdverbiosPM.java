// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.modelos;

import com.kalos.beans.AdverbioBean;
import com.kalos.beans.Significado;
import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.operaciones.OpSignificados;

// Referenced classes of package kalos.A.C:
//            B

public class AdverbiosPM extends PagingModel<AdverbioBean> {

    public AdverbiosPM(FuenteDatosCacheable b, int i) {
	super(b, i);
    }

    public Object getValueAt(int i, int j) {
	AdverbioBean m1 = (AdverbioBean) getFila(i);
	switch (j) {
	case 0: // '\0'
	    return m1.getLetra();

	case 1: // '\001'
	    return Integer.valueOf(m1.getCodigo());

	case 2: // '\002'
	    return m1.getAdverbio();

	case 3: // '\003'
	    return m1.getTipo();

	case 4: // '\004'
	    Significado q = OpSignificados.getSignificadoIndividual(m1);
	    return q.getValor();
	}
	return null;
    }

    public int getColumnCount() {
	return 5;
    }
}
