// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.CubosTipoPartBean;
import com.kalos.datos.dao.CubosTipoPartDAO;

// Referenced classes of package kalos.E.E:
//            RA

public class GerenteDatos implements GerenteCubosTipoPart {

    private CubosTipoPartDAO ctp;

    public List<CubosTipoPartBean> seleccionaTodos() {
	return ctp.seleccionaTodos();
    }

    public void setCubosTipoPartDAO(CubosTipoPartDAO ctp) {
	this.ctp = ctp;
    }

}
