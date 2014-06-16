// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.beans;

import javax.rmi.CORBA.Tie;



import kalos.enumeraciones.Aumento;
import kalos.enumeraciones.Contraccion;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Propagacion;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;

// Referenced classes of package kalos.K:
//            L

public class IrrVerbo implements IBeanIrrVerbo
{


    public Aumento getAumento()
    {
        return J;
    }

    public void setAumento(Aumento s)
    {
        J = s;
    }

    public Contraccion getContraccion()
    {
        return N;
    }

    public void setContraccion(Contraccion c)
    {
        N = c;
    }

    public FuerteDebil getFuerte()
    {
        return A;
    }

    public void setFuerte(FuerteDebil p)
    {
        A = p;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String s)
    {
        id = s;
    }

    public int getJuego()
    {
        return I;
    }

    public void setJuego(int i)
    {
        I = i;
    }

    public Modo getModo()
    {
        return F;
    }

    public void setModo(Modo p)
    {
        F = p;
    }

    public Particularidad getPartic()
    {
        return G;
    }

    public void setPartic(Particularidad x)
    {
        G = x;
    }

    public Propagacion getPropagacion()
    {
        return Q;
    }

    public void setPropagacion(Propagacion d)
    {
        Q = d;
    }

    public boolean isReduplicacion()
    {
        return R;
    }

    public void setReduplicacion(boolean flag)
    {
        R = flag;
    }

    public int getSubPart()
    {
        return E;
    }

    public void setSubPart(int i)
    {
        E = i;
    }

    public String getTema()
    {
        return C;
    }

    public void setTema(String s)
    {
        C = s;
    }

    public Tiempo getTiempo()
    {
        return B;
    }

    public void setTiempo(Tiempo j)
    {
        B = j;
    }

    public Tiempo getTiempoJuego()
    {
        return P;
    }

    public void setTiempoJuego(Tiempo j)
    {
        P = j;
    }

    public String getVerboId()
    {
        return D;
    }

    public void setVerboId(String s)
    {
        D = s;
    }

    public Voz getVoz()
    {
        return L;
    }

    public void setVoz(Voz z)
    {
        L = z;
    }

    public Voz getVozJuego()
    {
        return K;
    }

    public void setVozJuego(Voz z)
    {
        K = z;
    }

    public boolean isCompuesto()
    {
        return O;
    }

    public void setCompuesto(boolean flag)
    {
        O = flag;
    }

    public int hashCode()
    {
        int i = 1;
        i = 31 * i + (id != null ? id.hashCode() : 0);
        return i;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        IrrVerbo s = (IrrVerbo)obj;
        if(id == null)
        {
            if(s.id != null)
                return false;
        } else
        if(!id.equals(s.id))
            return false;
        return true;
    }

    public boolean isPats()
    {
        return H;
    }

    public void setPats(boolean flag)
    {
        H = flag;
    }

    private String id;
    private String D;
    private String C;
    private Particularidad G;
    private int E;
    private Modo F;
    private Tiempo B;
    private Voz L;
    private FuerteDebil A;
    private Contraccion N;
    private Aumento J;
    private boolean R;
    private int I;
    private Tiempo P;
    private Voz K;
    private Propagacion Q;
    private boolean H;
    private boolean O;
}
