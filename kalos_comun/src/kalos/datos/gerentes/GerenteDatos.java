// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.datos.gerentes;

import java.util.List;

import kalos.datos.dao.CubosTipoPartDAO;

// Referenced classes of package kalos.E.E:
//            RA

public class Gerentes implements RA
{


    public List seleccionaTodos(){
        return A.seleccionaTodos();
    }

    public void setCubosTipoPartDAO(CubosTipoPartDAO za){
	ctp = za;
    }

    private CubosTipoPartDAO ctp;
}
