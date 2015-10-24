package com.kalos.beans;

import com.kalos.enumeraciones.ValorExplosion;

public class DTOExplosion {
    private ValorExplosion ve;
    private ValorExplosion vePorDefecto;
    private String descripcion;
    private String etiqueta;
    private boolean opcionesHabilitadas;

    public String getDescripcion() {
	return this.descripcion;
    }

    public void setDescripcion(String paramString) {
	this.descripcion = paramString;
    }

    public String getEtiqueta() {
	return this.etiqueta;
    }

    public void setEtiqueta(String paramString) {
	this.etiqueta = paramString;
    }

    public boolean isOpcionesHabilitadas() {
	return this.opcionesHabilitadas;
    }

    public void setOpcionesHabilitadas(boolean paramBoolean) {
	this.opcionesHabilitadas = paramBoolean;
    }

    public ValorExplosion getPorDefecto() {
	return this.vePorDefecto;
    }

    public void setPorDefecto(ValorExplosion paramF) {
	this.vePorDefecto = paramF;
    }

    public ValorExplosion getValor() {
	return this.ve;
    }

    public void setValor(ValorExplosion paramF) {
	this.ve = paramF;
    }
}
