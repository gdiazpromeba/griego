package com.kalos.analisismorfologico.negocio;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kalos.beans.AdverbioBean;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.datos.gerentes.GerenteAdverbios;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpPalabras;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AMAdverbios implements AnalizadorMorfologico, ApplicationContextAware {


    private GerenteAdverbios gerenteAdverbios;
   
    Logger logger=Logger.getLogger(this.getClass().getName());

	
	/**
	 * indica si los beans dependientes ya han sido cargados, por única vez
	*/
	private boolean dependenciasCargadas=false;
	private ApplicationContext contexto;
	
	public void setApplicationContext(ApplicationContext contexto){
		this.contexto=contexto;
	}
	
	private void cargaDependencias(){
		if (!dependenciasCargadas){
			this.gerenteAdverbios=(GerenteAdverbios)contexto.getBean("gerenteAdverbios");
			dependenciasCargadas=true;
		}
	}
	
	

    public Set<ResultadoUniversal> buscaCanonica(String[] entradas,  AACacheable cacheAA,  boolean validaContraFlexion, boolean debug) {
        cargaDependencias();
        
        Set<ResultadoUniversal> resultados = new HashSet<>();

        long tiempoInicial = System.currentTimeMillis();
        Set<String> setEntradas = new HashSet<String>(Arrays.asList(entradas));

        buscaIndividuales(setEntradas, resultados);
        
        long tiempoFinal = System.currentTimeMillis();
        long lapsoEnMilis = tiempoFinal - tiempoInicial;
        if (debug) {
            GregorianCalendar lapso = new GregorianCalendar();
            lapso.setTimeInMillis(lapsoEnMilis);
            System.out.println("tardó " + lapso.get(Calendar.MINUTE) + " minutos " + lapso.get(Calendar.SECOND)
                    + " segundos " + lapso.get(Calendar.MILLISECOND) + " milisegundos");
        }
        return resultados;

    }
    
    private void buscaIndividuales(Set<String> entradas, Set<ResultadoUniversal> resultado){
        
        List<ResultadoUniversal> lstResultados=new ArrayList<ResultadoUniversal>();
            
        for (String entrada: entradas){
            String entradaBeta=OpPalabras.strCompletoABeta(entrada);
            List<AdverbioBean> individuales=gerenteAdverbios.seleccionaPorFormaSinSignificado(entradaBeta);
            for (AdverbioBean adverbio: individuales){
                ResultadoUniversal reu = new ResultadoUniversal(TipoPalabra.Adverbio, 
                        adverbio.getId(),
                        null, null, adverbio.getPartic(), null,
                        entrada, null, null, null, null, null, null, null,
                        null, null, null, null, null);
                lstResultados.add(reu);
            }
        }
        
        resultado.addAll(lstResultados);
    }
    

    

    public void setGerenteAdverbios(GerenteAdverbios gerenteAdverbios) {
		this.gerenteAdverbios = gerenteAdverbios;
	}

 


 

}
