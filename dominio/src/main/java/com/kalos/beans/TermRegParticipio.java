// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.OrigenTema;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Silaba;
import com.kalos.enumeraciones.Voz;


// Referenced classes of package kalos.K:
//            G, T

public class TermRegParticipio implements TermRegNominal, TermRegVerbal, TieneJuego, Verboide {
    

    public int hashCode() {
        if (hash == null)
            hash = Integer.valueOf(cadena());
        return hash.intValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof TermRegParticipio)
            return hashCode() == obj.hashCode();
        else
            return false;
    }

    private int cadena() {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(tipoSustantivo);
        stringbuffer.append("|");
        stringbuffer.append(caso.name());
        stringbuffer.append("|");
        stringbuffer.append(genero.name());
        stringbuffer.append("|");
        stringbuffer.append(origenTema!=null?origenTema.name():null);
        stringbuffer.append("|");
        stringbuffer.append(subindice);
        stringbuffer.append("|");
        stringbuffer.append(terminacion);
        stringbuffer.append("|");
        stringbuffer.append(exContraccion);
        stringbuffer.append("|");
        stringbuffer.append(acento.name());
        stringbuffer.append("|");
        stringbuffer.append(regexDesinencia);
        stringbuffer.append("|");
        stringbuffer.append(silaba.name());
        stringbuffer.append(formaOriginal);
        stringbuffer.append("|");
        stringbuffer.append(terminacionPendienteRevision);
        stringbuffer.append("|");
        stringbuffer.append(nominativoPropuesto);
        stringbuffer.append("|");
        stringbuffer.append(genitivoPropuesto);
        stringbuffer.append("|");
        stringbuffer.append(puedeAcento);
        stringbuffer.append("|");
        stringbuffer.append(formaADestransformar);
        stringbuffer.append("|");
        stringbuffer.append(idVerbo);
        stringbuffer.append("|");
        stringbuffer.append(voz.name());
        stringbuffer.append("|");
        stringbuffer.append(aspecto.name());
        stringbuffer.append("|");
        stringbuffer.append(numero.name());
        stringbuffer.append("|");
        stringbuffer.append(preposiciones);
        stringbuffer.append("|");
        stringbuffer.append(particularidad!=null?particularidad.name():null);
        stringbuffer.append("|");
        stringbuffer.append(tipoDesinencia);
        stringbuffer.append("|");
        stringbuffer.append(tipoVerboExtendido);
        stringbuffer.append("|");
        stringbuffer.append(fuerte.name());
        stringbuffer.append("|");
        stringbuffer.append(formaDestransformada);
        stringbuffer.append("|");
        stringbuffer.append(formaOriginalCompuesta);
        stringbuffer.append("|");
        stringbuffer.append(formaCanonica);
        stringbuffer.append("|");
        stringbuffer.append(temaPropuesto);
        stringbuffer.append("|");
        stringbuffer.append(juego);
        stringbuffer.append("|");
        stringbuffer.append(contraccionComedora);
        stringbuffer.append("|");
        stringbuffer.append(terminacionVerbalizada);
        stringbuffer.append("|");
        stringbuffer.append(tipoVerbo);
        return stringbuffer.toString().hashCode();
    }

    public Acento getAcento() {
        return acento;
    }

    public void setAcento(Acento e1) {
        acento = e1;
        hash = null;
    }

    public int getAcentoConcuerda() {
        return acentoConcuerda;
    }

    public void setAcentoConcuerda(int i1) {
        acentoConcuerda = i1;
        hash = null;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso a1) {
        caso = a1;
        hash = null;
    }

    public boolean isExContraccion() {
        return exContraccion;
    }

    public void setExContraccion(boolean flag) {
        exContraccion = flag;
        hash = null;
    }

    public int getIdTipoSustantivo() {
        return idTipoSustantivo;
    }

    public void setIdTipoSustantivo(int i1) {
        idTipoSustantivo = i1;
        hash = null;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero n1) {
        numero = n1;
        hash = null;
    }

    public int getPosicionConcuerda() {
        return posicionConcuerda;
    }

    public void setPosicionConcuerda(int i1) {
        posicionConcuerda = i1;
        hash = null;
    }

    public void setOrigenTema(OrigenTema g1) {
        origenTema = g1;
        hash = null;
    }

    public OrigenTema getOrigenTema() {
        return origenTema;
    }

    public String getRegExDesinencia() {
        return regexDesinencia;
    }

    public void setRegExDesinencia(String s1) {
        regexDesinencia = s1;
        hash = null;
    }

    public Silaba getSilaba() {
        return silaba;
    }

    public void setSilaba(Silaba i1) {
        silaba = i1;
        hash = null;
    }

    public int getSubindice() {
        return subindice;
    }

    public void setSubindice(int i1) {
        subindice = i1;
        hash = null;
    }

    public String getTerminacion() {
        return terminacion;
    }

    public void setTerminacion(String s1) {
        terminacion = s1;
        hash = null;
    }

    public String getTiposHoja() {
        return tiposHoja;
    }

    public void setTiposHoja(String s1) {
        tiposHoja = s1;
        hash = null;
    }

    public int getTipoSustantivo() {
        return tipoSustantivo;
    }

    public void setTipoSustantivo(int i1) {
        tipoSustantivo = i1;
        hash = null;
    }

    public String getFormaOriginal() {
        return formaOriginal;
    }

    public void setFormaOriginal(String s1) {
        formaOriginal = s1;
        hash = null;
    }

    public TermRegNominal getTerminacionPendienteRevision() {
        return terminacionPendienteRevision;
    }

    public void setTerminacionPendienteRevision(TermRegNominal t1) {
        terminacionPendienteRevision = t1;
        hash = null;
    }

    public String getGenitivoPropuesto() {
        return genitivoPropuesto;
    }

    public void setGenitivoPropuesto(String s1) {
        genitivoPropuesto = s1;
        hash = null;
    }

    public String getNominativoPropuesto() {
        return nominativoPropuesto;
    }

    public void setNominativoPropuesto(String s1) {
        nominativoPropuesto = s1;
        hash = null;
    }

    public boolean isPudeAcento() {
        return puedeAcento;
    }

    public void setPudeAcento(boolean flag) {
        puedeAcento = flag;
        hash = null;
    }

    public String getFormaADestransformar() {
        return formaADestransformar;
    }

    public void setFormaADestransformar(String s1) {
        formaADestransformar = s1;
        hash = null;
    }

    public String getIdVerbo() {
        return idVerbo;
    }

    public void setIdVerbo(String s1) {
        idVerbo = s1;
        hash = null;
    }

    public Particularidad getParticularidad() {
        return particularidad;
    }

    public void setParticularidad(Particularidad x1) {
        particularidad = x1;
        hash = null;
    }

    public List<String> getPreposiciones() {
        return preposiciones;
    }

    public void setPreposiciones(List<String> list) {
        preposiciones = list;
        hash = null;
    }

    public int getTipoDesinencia() {
        return tipoDesinencia;
    }

    public void setTipoDesinencia(int i1) {
        tipoDesinencia = i1;
        hash = null;
    }

    public int getTipoVerboExtendido() {
        return tipoVerboExtendido;
    }

    public void setTipoVerboExtendido(int i1) {
        tipoVerboExtendido = i1;
        hash = null;
    }

    public Voz getVoz() {
        return voz;
    }

    public void setVoz(Voz z1) {
        voz = z1;
        hash = null;
    }

    public String getFormaDestransformada() {
        return formaDestransformada;
    }

    public void setFormaDestransformada(String s1) {
        formaDestransformada = s1;
        hash = null;
    }

    public String getFormaOriginalCompuesta() {
        return formaOriginalCompuesta;
    }

    public void setFormaOriginalCompuesta(String s1) {
        formaOriginalCompuesta = s1;
        hash = null;
    }

    public FuerteDebil getFuerte() {
        return fuerte;
    }

    public void setFuerte(FuerteDebil p1) {
        fuerte = p1;
        hash = null;
    }

    public Aspecto getAspecto() {
        return aspecto;
    }

    public Aspecto getTiempoOAspecto() {
        return getAspecto();
    }

    public void setAspecto(Aspecto k1) {
        aspecto = k1;
        hash = null;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero a1) {
        genero = a1;
        hash = null;
    }

    public String getFormaCanonica() {
        return formaCanonica;
    }

    public void setFormaCanonica(String s1) {
        formaCanonica = s1;
        hash = null;
    }

    public String getTemaPropuesto() {
        return temaPropuesto;
    }

    public void setTemaPropuesto(String s1) {
        temaPropuesto = s1;
        hash = null;
    }

    public int getJuego() {
        return juego;
    }

    public void setJuego(int i1) {
        juego = i1;
        hash = null;
    }

    public int getContraccionComedora() {
        return contraccionComedora;
    }

    public void setContraccionComedora(int i1) {
        contraccionComedora = i1;
        hash = null;
    }

    public String getTerminacionVerbalizada() {
        return terminacionVerbalizada;
    }

    public void setTerminacionVerbalizada(String s1) {
        terminacionVerbalizada = s1;
        hash = null;
    }

    public int getTipoVerbo() {
        return tipoVerbo;
    }

    public void setTipoVerbo(int i1) {
        tipoVerbo = i1;
        hash = null;
    }

    public boolean isCompuesto() {
        return compuesto;
    }

    public void setCompuesto(boolean flag) {
        compuesto = flag;
    }

    public String getIdVerboCompuesto() {
        return idVerboCompuesto;
    }

    public void setIdVerboCompuesto(String s1) {
        idVerboCompuesto = s1;
    }

    public TermRegParticipio clona() {
        TermRegParticipio nuevo= new TermRegParticipio();
        nuevo.setAcento(this.acento);
        nuevo.setAcentoConcuerda(this.acentoConcuerda);
        nuevo.setAspecto(this.aspecto);
        nuevo.setCaso(this.caso);
        nuevo.setCompuesto(this.compuesto);
        nuevo.setContraccionComedora(this.contraccionComedora);
        nuevo.setExContraccion(this.exContraccion);
        nuevo.setFormaADestransformar(this.formaADestransformar);
        nuevo.setFormaCanonica(this.formaCanonica);
        nuevo.setFormaDestransformada(this.formaDestransformada);
        nuevo.setFormaOriginal(this.formaOriginal);
        nuevo.setFormaOriginalCompuesta(this.formaOriginalCompuesta);
        nuevo.setFuerte(this.fuerte);
        nuevo.setGenero(this.genero);
        nuevo.setGenitivoPropuesto(this.genitivoPropuesto);
        nuevo.setIdTipoSustantivo(this.idTipoSustantivo);
        nuevo.setIdVerbo(this.idVerbo);
        nuevo.setIdVerboCompuesto(this.idVerboCompuesto);
        nuevo.setJuego(this.juego);
        nuevo.setNominativoPropuesto(this.nominativoPropuesto);
        nuevo.setNumero(this.numero);
        nuevo.setOrigenTema(this.origenTema);
        nuevo.setParticularidad(this.particularidad);
        nuevo.setPosicionConcuerda(this.posicionConcuerda);
        List<String> newPreps = new ArrayList<>();
        if (this.getPreposiciones()!=null){
          for (String prep : this.getPreposiciones()) {
            newPreps.add(prep);
          }
        }
        nuevo.setPreposiciones(newPreps);
        nuevo.setPudeAcento(this.puedeAcento);
        nuevo.setRegExDesinencia(this.regexDesinencia);
        nuevo.setSilaba(this.silaba);
        nuevo.setSubindice(this.subindice);
        nuevo.setTemaPropuesto(this.temaPropuesto);
        nuevo.setTerminacion(this.terminacion);
        nuevo.setTerminacionPendienteRevision(this.terminacionPendienteRevision);
        nuevo.setTerminacionVerbalizada(this.terminacionVerbalizada);
        nuevo.setTipoDesinencia(this.tipoDesinencia);
        nuevo.setTiposHoja(this.tiposHoja);
        nuevo.setTipoSustantivo(this.tipoSustantivo);
        nuevo.setTipoVerbo(this.tipoVerbo);
        nuevo.setTipoVerboExtendido(this.tipoVerboExtendido);
        nuevo.setVoz(this.voz);
        return nuevo;
    }

    private int tipoSustantivo;
    private int idTipoSustantivo;
    private Genero genero;
    private int subindice;
    private String terminacion;
    private String tiposHoja;
    private boolean exContraccion;
    private int acentoConcuerda;
    private int posicionConcuerda;
    private Acento acento;
    private String regexDesinencia;
    private Silaba silaba;
    private String formaOriginal;
    private TermRegNominal terminacionPendienteRevision;
    private String nominativoPropuesto;
    private String genitivoPropuesto;
    private boolean puedeAcento;
    private String formaADestransformar;
    private String idVerbo;
    private Voz voz;
    private Aspecto aspecto;
    private List<String> preposiciones;
    private Particularidad particularidad;
    private int tipoDesinencia;
    private int tipoVerboExtendido;
    private FuerteDebil fuerte;
    private Numero numero;
    private String formaDestransformada;
    private String formaOriginalCompuesta;
    private String formaCanonica;
    private String temaPropuesto;
    private int juego;
    private int contraccionComedora;
    private String terminacionVerbalizada;
    private int tipoVerbo;
    private boolean compuesto;
    private String idVerboCompuesto;
    private Caso caso;
    private OrigenTema origenTema;
    Integer hash;
}
