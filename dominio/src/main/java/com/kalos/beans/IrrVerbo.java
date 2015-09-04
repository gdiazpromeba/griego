// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.beans;

import javax.rmi.CORBA.Tie;




import com.kalos.enumeraciones.Aumento;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Propagacion;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.Voz;

// Referenced classes of package kalos.K:
//            L

public class IrrVerbo implements IBeanIrrVerbo
{


    public Aumento getAumento()
    {
        return aumento;
    }

    public void setAumento(Aumento s)
    {
        aumento = s;
    }

    public Contraccion getContraccion()
    {
        return contraccion;
    }

    public void setContraccion(Contraccion c)
    {
        contraccion = c;
    }

    public FuerteDebil getFuerte()
    {
        return fuerte;
    }

    public void setFuerte(FuerteDebil p)
    {
        fuerte = p;
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
        return juego;
    }

    public void setJuego(int i)
    {
        juego = i;
    }

    public Modo getModo()
    {
        return modo;
    }

    public void setModo(Modo p)
    {
        modo = p;
    }

    public Particularidad getPartic()
    {
        return partic;
    }

    public void setPartic(Particularidad x)
    {
        partic = x;
    }

    public Propagacion getPropagacion()
    {
        return propagacion;
    }

    public void setPropagacion(Propagacion d)
    {
        propagacion = d;
    }

    public boolean isReduplicacion()
    {
        return reduplicacion;
    }

    public void setReduplicacion(boolean flag)
    {
        reduplicacion = flag;
    }

    public int getSubPart()
    {
        return subPart;
    }

    public void setSubPart(int i)
    {
        subPart = i;
    }

    public String getTema()
    {
        return tema;
    }

    public void setTema(String s)
    {
        tema = s;
    }

    public Tiempo getTiempo()
    {
        return tiempo;
    }

    public void setTiempo(Tiempo j)
    {
        tiempo = j;
    }

    public Tiempo getTiempoJuego()
    {
        return tiempoJuego;
    }

    public void setTiempoJuego(Tiempo j)
    {
        tiempoJuego = j;
    }

    public String getVerboId()
    {
        return verboId;
    }

    public void setVerboId(String s)
    {
        verboId = s;
    }

    public Voz getVoz()
    {
        return voz;
    }

    public void setVoz(Voz z)
    {
        voz = z;
    }

    public Voz getVozJuego()
    {
        return vozJuego;
    }

    public void setVozJuego(Voz z)
    {
        vozJuego = z;
    }

    public boolean isCompuesto()
    {
        return compuesto;
    }

    public void setCompuesto(boolean flag)
    {
        compuesto = flag;
    }



    public boolean isPats()
    {
        return pats;
    }

    public void setPats(boolean flag)
    {
        pats = flag;
    }

    private String id;
    private String verboId;
    private String tema;
    
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((aumento == null) ? 0 : aumento.hashCode());
	result = prime * result + (compuesto ? 1231 : 1237);
	result = prime * result + ((contraccion == null) ? 0 : contraccion.hashCode());
	result = prime * result + ((fuerte == null) ? 0 : fuerte.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + juego;
	result = prime * result + ((modo == null) ? 0 : modo.hashCode());
	result = prime * result + ((partic == null) ? 0 : partic.hashCode());
	result = prime * result + (pats ? 1231 : 1237);
	result = prime * result + ((propagacion == null) ? 0 : propagacion.hashCode());
	result = prime * result + (reduplicacion ? 1231 : 1237);
	result = prime * result + subPart;
	result = prime * result + ((tema == null) ? 0 : tema.hashCode());
	result = prime * result + ((tiempo == null) ? 0 : tiempo.hashCode());
	result = prime * result + ((tiempoJuego == null) ? 0 : tiempoJuego.hashCode());
	result = prime * result + ((verboId == null) ? 0 : verboId.hashCode());
	result = prime * result + ((voz == null) ? 0 : voz.hashCode());
	result = prime * result + ((vozJuego == null) ? 0 : vozJuego.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	IrrVerbo other = (IrrVerbo) obj;
	if (aumento != other.aumento)
	    return false;
	if (compuesto != other.compuesto)
	    return false;
	if (contraccion != other.contraccion)
	    return false;
	if (fuerte != other.fuerte)
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (juego != other.juego)
	    return false;
	if (modo != other.modo)
	    return false;
	if (partic != other.partic)
	    return false;
	if (pats != other.pats)
	    return false;
	if (propagacion != other.propagacion)
	    return false;
	if (reduplicacion != other.reduplicacion)
	    return false;
	if (subPart != other.subPart)
	    return false;
	if (tema == null) {
	    if (other.tema != null)
		return false;
	} else if (!tema.equals(other.tema))
	    return false;
	if (tiempo != other.tiempo)
	    return false;
	if (tiempoJuego != other.tiempoJuego)
	    return false;
	if (verboId == null) {
	    if (other.verboId != null)
		return false;
	} else if (!verboId.equals(other.verboId))
	    return false;
	if (voz != other.voz)
	    return false;
	if (vozJuego != other.vozJuego)
	    return false;
	return true;
    }

    private Particularidad partic;
    private int subPart;
    private Modo modo;
    private Tiempo tiempo;
    private Voz voz;
    private FuerteDebil fuerte;
    private Contraccion contraccion;
    private Aumento aumento;
    private boolean reduplicacion;
    private int juego;
    private Tiempo tiempoJuego;
    private Voz vozJuego;
    private Propagacion propagacion;
    private boolean pats;
    private boolean compuesto;
}
