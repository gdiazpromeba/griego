package com.kalos.beans;

import java.util.List;

import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Silaba;
import com.kalos.enumeraciones.TiempoOAspecto;
import com.kalos.enumeraciones.Voz;

/**
 * Para beans del tipo "terminación regular" verbal.
 * 
 * @author gdiaz
 *
 */
public interface TermRegVerbal {
	Voz getVoz();

	void setVoz(Voz voz);

	FuerteDebil getFuerte();

	int getTipoDesinencia();

	String getTerminacion();

	void setTerminacion(String terminacion);

	TiempoOAspecto getTiempoOAspecto();

	// agregados (no de tabla de terminación)
	String getFormaOriginal();

	void setFormaOriginal(String formaOriginal);

	void setFormaOriginalCompuesta(String formaOriginalCompuesta);

	String getFormaADestransformar();

	void setFormaADestransformar(String formaADestransformar);

	void setFormaDestransformada(String formaDestransformada);

	void setPreposiciones(List<String> preposiciones);

	int getTipoVerboExtendido();

	void setTipoVerboExtendido(int tve);

	void setIdVerbo(String idVerbo);

	void setParticularidad(Particularidad partic);

	String getIdVerbo();

	Particularidad getParticularidad();

	List<String> getPreposiciones();

	void setCompuesto(boolean valor);

	boolean isCompuesto();

	void setIdVerboCompuesto(String valor);

	String getIdVerboCompuesto();
	
    void setAcento(Acento val);
    
    Acento getAcento();	

	TermRegVerbal clona();
	
	Silaba getSilaba();
	
	void setSilaba(Silaba val);
}
