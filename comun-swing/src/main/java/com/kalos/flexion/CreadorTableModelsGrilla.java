package com.kalos.flexion;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.kalos.enumeraciones.Reportes;
import com.kalos.enumeraciones.TipoPalabra;

/*
 * Created on Aug 14, 2004
 *
 */

/**
 * @author Gonzalo
 *
 *   Provee los tablemodels aptos para ser mostrados en forma tabular (en la JTable)  
 *   Para lograrlo, esta clase se nutre de los "tablemodels básicos".
 *   Las operaciones que normalmente se les hace a los tablemodels básicos son:
 *   -transformación a ICeldasReporte (para coloreo)
 *   -agrupamiento
 *   -borrado de columnas superfluas
 *   -internacionalización de los encabezados de columna
 *    
 */
public class CreadorTableModelsGrilla {
	
	private UtilidadesTM utilidadesTM;
	private UtilidadReportes utilidadReportes;
	private CreadorTableModelsBasicos creadorTableModelsBasicos;
    
    
    

    
    
    
    /**
	 * @param creadorTableModelsBasicos The creadorTableModelsBasicos to set.
	 */
	public void setCreadorTableModelsBasicos(CreadorTableModelsBasicos creadorTableModelsReporte) {
		this.creadorTableModelsBasicos = creadorTableModelsReporte;
	}

    
    
    public DefaultTableModel getTMGrilla(String id, Reportes tipoReporte){
        switch(tipoReporte){
        case VERBOS_POR_MODO:
        	return getVerbosCompleto(id, false);
        case VERBOS_POR_MODO_SIN_DUAL:
        	return getVerbosCompleto(id, true);
        case VERBOS_POR_VOZ:
        	return getVerbosCompletoVozPrimero(id, false);
        case VERBOS_POR_VOZ_SIN_DUAL:
        	return getVerbosCompletoVozPrimero(id, true);
        case VERBOS_POR_MODO_ABREVIADO:
        	return getVerbosPorModoAbreviado(id);
        case PARTICIPIOS_POR_NUMERO:
        	return getParticipiosCompleto(id, false);
        case PARTICIPIOS_POR_VOZ:
        	return getParticipiosCompletoVozPrimero(id, false, false);
        case PARTICIPIOS_POR_NUMERO_SIN_DUAL:
        	return getParticipiosSinDual(id, false);
        case PARTICIPIOS_POR_VOZ_SIN_DUAL:
        	return getParticipiosCompletoVozPrimero(id, false, true);
        case PARTICIPIOS_POR_NUMERO_SIN_VOCATIVO:
        	return getParticipiosCompleto(id, true);
        case PARTICIPIOS_POR_VOZ_SIN_VOCATIVO:
            return getParticipiosCompletoVozPrimero(id, true, false);
        case PARTICIPIOS_POR_CASO_ABREVIADO:
        	return getParticipiosCompacto(id);
        case PARTICIPIOS_POR_VOZ_ABREVIADO:
        	return getParticipiosCompactoVozPrimero(id);
        case INFINITIVOS_POR_VOZ:
            return getInfinitivos(id);
        case SUSTANTIVOS_POR_NUMERO:
        	return getSustantivosPorNumero(id, true);
        case SUSTANTIVOS_POR_NUMERO_SIN_DUAL:
        	return getSustantivosPorNumero(id, false);
        case ADJETIVOS_POR_GENERO:
        	return getAdjetivosPorGenero(id);
        case ADJETIVOS_POR_NUMERO:
        	return getAdjetivosPorNumero(id, true);
        case ADJETIVOS_POR_NUMERO_SIN_DUAL:
        	return getAdjetivosPorNumero(id, false);
        case ARTICULOS_POR_GENERO:
        	return getArticulosPorGenero();
        case PRONOMBRES_PERSONALES_POR_CASO:
        	return getPronombresPersonalesPorCaso(true);
        case PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL:
        	return getPronombresPersonalesPorCaso(false);
        case PRONOMBRES_RELATIVOS_POR_GENERO_1:
        	return getPronombresRelativos("2213895001115bd9d355912800022138");
        case PRONOMBRES_RELATIVOS_POR_GENERO_2:
        	return getPronombresRelativos("b695e4e1115bd9dcf76128000b695e4e");
        case PRONOMBRES_RELATIVOS_POR_GENERO_3:
            return getPronombresRelativos("5b62bf91111a838b550d1280005b62bf");
        case PRONOMBRES_INTERROGATIVOS_POR_GENERO:
            return getPronombresInterrogativos("154a3f921115ece6a2ac128000154a3f");
        case PRONOMBRES_REFLEXIVOS_POR_GENERO:
        	return getPronombresReflexivos("256406591115d5461a4a128000256406");
        case CONJUNCIONES_POR_TIPO:
            return getConjuncionesPorTipo();
        case CONJUNCIONES_ALFABETICO:
            return getConjuncionesAlfabetica();
        case PREPOSICIONES_ALFABETICO:
            return getPreposicionesAlfabetica();
        case PREPOSICIONES_POR_CASO:
            return getPreposicionesPorCaso();
        case INTERJECCIONES_ALFABETICO:
            return getInterjeccionesAlfabetica();
        case PRONOMBRES_INDEFINIDOS_POR_GENERO:
            return getPronombresIndefinidos("2796b544e111a7e4652c81280002796b");
        default:
        	throw new RuntimeException("tipo de reporte desconocido requerido en CreadorTableModelsGrilla.getTMGrilla="+ tipoReporte);	
        }
    }
    
    
    /**
     * transforma el 
     * @return
     */
    private DefaultTableModel getPronombresPersonalesPorCaso(boolean duales){
        DefaultTableModel tm=creadorTableModelsBasicos.getPronombresPersonalesPorCaso(duales);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"NOMINATIVO", "ACUSATIVO", "GENITIVO", "DATIVO"});
        
    	
    	utilidadesTM.transformaEnICeldasReporte(tm, 
    			new String[]{"PARTICULARIDAD", "PERSONA", "NOMINATIVO", "ACUSATIVO", "GENITIVO", "DATIVO"}
    	);
    	
    	
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        
//        //agrupa por persona
//        utilidadesTM.agrupaPorColumna(tm, 1, 0, utilidadReportes.getColorSubtitulo2());
//        utilidadesTM.borraColumna(tm, "PERSONA");
        
        
//        //agrupo por caso
//        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo3());
//        utilidadesTM.borraColumna(tm, "CASO");
        
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"persona", "nominativo", "acusativo", "genitivo", "dativo"});
        return tm;

    }
    
    
    private DefaultTableModel getPronombresInterrogativos(String idEncabezado){
        DefaultTableModel tm=creadorTableModelsBasicos.getPronombresInterrogativos(idEncabezado);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"MASC_FEM", "NEUTRO"});
        
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"PARTICULARIDAD", "NUMERO", "CASO", "MASC_FEM", "NEUTRO"}
        );
        
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        //agrupa por número
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "NUMERO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"caso", "masculino_o_femenino", "neutro"});
        return tm;
    }    
    

    private DefaultTableModel getPronombresRelativos(String idEncabezado){
        DefaultTableModel tm=creadorTableModelsBasicos.getPronombresRelativos(idEncabezado);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"MASCULINO", "FEMENINO", "NEUTRO"});
        
    	
    	utilidadesTM.transformaEnICeldasReporte(tm, 
    			new String[]{"PARTICULARIDAD", "NUMERO", "CASO", "MASCULINO", "FEMENINO", "NEUTRO"}
    	);
    	
    	
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        //agrupa por número
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "NUMERO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"caso", "masculino", "femenino", "neutro"});
        return tm;
    }
    
    private DefaultTableModel getPronombresIndefinidos(String idEncabezado){
        DefaultTableModel tm=creadorTableModelsBasicos.getPronombresIndefinidos(idEncabezado);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"MASC_FEM", "NEUTRO"});
        
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"PARTICULARIDAD", "NUMERO", "CASO", "MASC_FEM", "NEUTRO"}
        );
        
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        //agrupa por número
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "NUMERO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"caso", "masculino_o_femenino", "neutro"});
        return tm;
    }
    
    
    private DefaultTableModel getPronombresReflexivos(String idEncabezado){
        DefaultTableModel tm=creadorTableModelsBasicos.getPronombresReflexivos(idEncabezado);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"MASCULINO", "FEMENINO", "NEUTRO"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"PARTICULARIDAD", "PERSONA", "CASO", "MASCULINO", "FEMENINO", "NEUTRO"}
        );
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        //agrupa por número
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "PERSONA");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"caso", "masculino", "femenino", "neutro"});
        return tm;
   }     
    
    
    private DefaultTableModel getConjuncionesPorTipo(){
        DefaultTableModel tm=creadorTableModelsBasicos.getConjuncionesPorTipo();
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"FORMA"});
        
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"PARTICULARIDAD", "TIPO", "SUBTIPO", "FORMA", "SIGNIFICADO"}
        );
        
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 3, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        //agrupa por tipo
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "TIPO");
        
        //agrupa por tipo
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "SUBTIPO");

        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"forma", "significado"});
        return tm;

    }    
    
    private DefaultTableModel getConjuncionesAlfabetica(){
        DefaultTableModel tm=creadorTableModelsBasicos.getConjuncionesAlfabeticamente();
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        tm=utilidadesTM.reacomodaColumnas(tm, new String[]{"FORMA", "TIPO", "SUBTIPO", "PARTICULARIDAD", "SIGNIFICADO"});
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"FORMA"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"FORMA", "TIPO", "SUBTIPO", "PARTICULARIDAD", "SIGNIFICADO"}
        );
        
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"forma", "tipo", "subtipo", "particularidad", "significado"});
        return tm;

    }
    
    private DefaultTableModel getInterjeccionesAlfabetica(){
        DefaultTableModel tm=creadorTableModelsBasicos.getInterjeccionesAlfabeticamente();
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        tm=utilidadesTM.reacomodaColumnas(tm, new String[]{"FORMA", "PARTICULARIDAD", "SIGNIFICADO"});
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"FORMA"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"FORMA", "PARTICULARIDAD", "SIGNIFICADO"}
        );
        
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"forma", "particularidad", "significado"});
        return tm;
    }
    
    
    private DefaultTableModel getPreposicionesAlfabetica(){
        DefaultTableModel tm=creadorTableModelsBasicos.getPreposicionesAlfabeticamente();
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        tm=utilidadesTM.reacomodaColumnas(tm, new String[]{"PARTICULARIDAD", "FORMA", "SIGNIFICADO", "CASOS"});
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"FORMA"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"PARTICULARIDAD", "FORMA", "SIGNIFICADO", "CASOS"}
        );
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"forma", "significado", "casos"});
        return tm;

    }
    
    private DefaultTableModel getPreposicionesPorCaso(){
        DefaultTableModel tm=creadorTableModelsBasicos.getPreposicionesPorCaso();
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        tm=utilidadesTM.reacomodaColumnas(tm, new String[]{"PARTICULARIDAD", "CASOS", "FORMA", "SIGNIFICADO" });
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"FORMA"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, 
                new String[]{"PARTICULARIDAD", "FORMA", "SIGNIFICADO", "CASOS"}
        );
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        //agrupa por casos
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "CASOS");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"forma", "significado"});
        return tm;

    }    
    
    
    private DefaultTableModel getArticulosPorGenero(){
        DefaultTableModel tm=creadorTableModelsBasicos.getParticulasPorTipo(TipoPalabra.Articulo);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"MASCULINO", "FEMENINO", "NEUTRO"});
        
    	
    	utilidadesTM.transformaEnICeldasReporte(tm, 
    			new String[]{"PARTICULARIDAD", "NUMERO", "CASO", "MASCULINO", "FEMENINO", "NEUTRO"}
    	);
    	
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTICULARIDAD");
        
        
        //agrupa por numero
        utilidadesTM.agrupaPorColumna(tm, 1, 0, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "NUMERO");
        
        
//        //agrupo por caso
//        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo3());
//        utilidadesTM.borraColumna(tm, "CASO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"caso", "masculino", "femenino", "neutro"});
        return tm;
    }
    
    /**
     * crea y devuelve el tableModel necesario para el reporte completo de verbos
     * en forma tabular.
     * @param letra
     * @param codigo
     * @return
     */
    private DefaultTableModel getVerbosCompleto(String idVerbo, boolean sinDuales){
        DefaultTableModel tm=creadorTableModelsBasicos.getVerbosCompleto(idVerbo,sinDuales);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
    	utilidadesTM.columnasAInterfaz(tm, new String[]{"INDICATIVO", "SUBJUNTIVO", "OPTATIVO", "IMPERATIVO"});
    	
    	utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "VOZ", "TIEMPO", "PERSONA", 
    	        "INDICATIVO", "SUBJUNTIVO", "OPTATIVO", "IMPERATIVO"});
    	
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 3, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        //agrupa por voz
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "VOZ");
        
        //agrupo por tiempo
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "TIEMPO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"tiempo", "indicativo", "subjuntivo", "optativo", "imperativo"});
        return tm;
    }
    
    
    

    
    private DefaultTableModel getVerbosCompletoVozPrimero(String idVerbo, boolean sinDual){
        DefaultTableModel tm=creadorTableModelsBasicos.getVerbosCompletoVozPrimero(idVerbo, sinDual);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
    	utilidadesTM.columnasAInterfaz(tm, new String[]{"ACTIVA", "MEDIA", "PASIVA"});
    	
    	utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "MODO", "TIEMPO", "PERSONA", "ACTIVA", "MEDIA", "PASIVA"});
        
        //agrupa por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        //agrupa por voz
        utilidadesTM.agrupaPorColumna(tm, 2, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "MODO");
        
        
        //agrupo por tiempo
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "TIEMPO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"persona", "voz_activa", "voz_media", "voz_pasiva"});
        return tm;
    }
    

    


    
    
    /**
     * crea y devuelve el tableModel necesario para el reporte de infinitivos
     * en forma tabular.
     * @param letra
     * @param codigo
     * @return
     */
    private DefaultTableModel getInfinitivos(String idVerbo){
        DefaultTableModel tm=creadorTableModelsBasicos.getInfinitivos(idVerbo);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        
        //paso a unicode
        utilidadesTM.columnasAInterfaz(tm, new String[]{"ACTIVA", "MEDIA", "PASIVA"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "SUBPART", "ASPECTO", "ACTIVA", "MEDIA","PASIVA"});
        
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, Color.lightGray);
        utilidadesTM.borraColumna(tm, "PARTIC");
        utilidadesTM.borraColumna(tm, "SUBPART");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"aspecto", "activa", "media", "pasiva"});
        

        return tm;
        
    }
    
    
    
    /**
     * crea y devuelve el tableModel necesario para el reporte de sustantivos completo
     * en forma tabular.
     * @param letra
     * @param codigo
     * @return
     */
    private DefaultTableModel getSustantivosPorNumero(String idSustantivo, boolean dual){
        DefaultTableModel tm=creadorTableModelsBasicos.getSustantivosPorNumero(idSustantivo, dual);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }        
        
        String[] nombresColumna;
        if (dual){
        	nombresColumna=new String[]{"SINGULAR", "PLURAL", "DUAL"};
        }else{
        	nombresColumna=new String[]{"SINGULAR", "PLURAL"};
        }
        //paso a unicode
        utilidadesTM.columnasAInterfaz(tm, nombresColumna);
        
        
        String[] columnas=concatenaArrays(new String[]{"PARTIC", "CASO"}, nombresColumna);
        utilidadesTM.transformaEnICeldasReporte(tm, columnas);
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 1, Color.lightGray);
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"CASO", "SINGULAR", "PLURAL",  "DUAL"} , new String[]{"caso", "singular", "plural", "dual"}  );

        
        return tm;
    }
    
    @SuppressWarnings("unchecked")
    private String[] concatenaArrays(String[] arr1, String[] arr2){
        List<String> resultados=new ArrayList<String>();
        resultados.addAll(Arrays.asList(arr1));
        resultados.addAll(Arrays.asList(arr2));
        return (String[])resultados.toArray(new String[]{});
    }
    
    private DefaultTableModel getAdjetivosPorGenero(String idAdjetivo){
        DefaultTableModel tm=creadorTableModelsBasicos.getAdjetivosPorGenero(idAdjetivo);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfazTolerante(tm, new String[]{"MASC_FEM", "MASCULINO", "FEMENINO", "NEUTRO"});
        
        utilidadesTM.transformaEnICeldasReporteTolerante(tm, new String[]{"PARTIC", "GRADO", "NUMERO", "CASO", 
        		"MASC_FEM", "MASCULINO", "FEMENINO", "NEUTRO"});
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "GRADO");
        
        utilidadesTM.agrupaPorColumna(tm, 1, 0, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "NUMERO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"CASO", "MASC_FEM", "MASCULINO", "FEMENINO", "NEUTRO"}, new String[]{"caso", "masculino_o_femenino", "masculino", "femenino", "neutro"});
        
        return tm;

    }
    
    private DefaultTableModel getAdjetivosPorNumero(String idAdjetivo, boolean dual){
        DefaultTableModel tm=creadorTableModelsBasicos.getAdjetivosPorNumero(idAdjetivo, dual);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }        
        
        utilidadesTM.columnasAInterfazTolerante(tm, new String[]{"SINGULAR", "PLURAL", "DUAL"});
        
        utilidadesTM.transformaEnICeldasReporteTolerante(tm, new String[]{"PARTIC", "GRADO", "GENERO", "CASO", "SINGULAR", "PLURAL", "DUAL"});
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 3, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "GRADO");
        
        
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "GENERO");
        
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"CASO", "SINGULAR", "PLURAL", "DUAL"}, new String[]{"caso", "singular", "plural", "dual"});
        
        return tm;

    }


    


    
    

    
    
    
    /**
     * crea y devuelve el tableModel necesario para el reporte completo de verbos
     * en forma tabular.
     * @param letra
     * @param codigo
     * @return
     */
    private DefaultTableModel getVerbosPorModoAbreviado(String idVerbo){
        DefaultTableModel tm=creadorTableModelsBasicos.getVerbosCompacto(idVerbo);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        utilidadesTM.columnasAInterfaz(tm, new String[]{"ACTIVA", "MEDIA", "PASIVA"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "TIEMPO", 
                "ACTIVA", "MEDIA","PASIVA"});
        

        
        //agrupo por partic
        utilidadesTM.agrupaPorColumna(tm, 0, 1, Color.lightGray);
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"tiempo", "voz_activa", "voz_media", "voz_pasiva"});
        
        return tm;
    }
    
    


    
    
    /**
     * crea y devuele el tablemodel necesario para el reporte compacto 
     * (nominativo-genitivo) de participios
     * @param conta
     * @return
     */
    private DefaultTableModel getParticipiosCompacto(String idVerbo){
        DefaultTableModel tm=creadorTableModelsBasicos.getParticipiosCompacto(idVerbo);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        

        //paso a unicode
        utilidadesTM.columnasAInterfaz(tm, new String[]{"NOMINATIVO", "GENITIVO"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "ASPECTO", "VOZ", "GENERO", "NOMINATIVO", "GENITIVO"});
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 3, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        //agrupo por aspecto
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "ASPECTO");
        //agrupo por voz
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "VOZ");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"genero", "nominativo", "genitivo"});
        return tm;
    }
    

    
    private DefaultTableModel getParticipiosCompactoVozPrimero(String idVerbo){
        DefaultTableModel tm=creadorTableModelsBasicos.getParticipiosCompactoVozPrimero(idVerbo);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        //paso a unicode
        utilidadesTM.columnasAInterfaz(tm, new String[]{"ACTIVA", "MEDIA", "PASIVA"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "ASPECTO", "GENERO", "CASO", 
                "ACTIVA", "MEDIA", "PASIVA"});
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        //agrupo por aspecto
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "ASPECTO");
        
        //agrupo por caso
        utilidadesTM.agrupaPorColumna(tm, 1, 0, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "CASO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"genero", "activa", "media", "pasiva"});
        
        
        return tm;

    }
    
    
    
    
    /**
     * Crea y devuele el tablemodel necesario para el reporte completo de participios de la grilla
     * Si no hay formas disponibles, devuelve null.
     * @param conta
     * @return
     */
    private DefaultTableModel getParticipiosCompleto(String idVerbo, boolean sinVocativos){
        DefaultTableModel tm= creadorTableModelsBasicos.getParticipiosCompleto(idVerbo,  sinVocativos);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        //paso a unicode
        utilidadesTM.columnasAInterfaz(tm, new String[]{"SINGULAR", "PLURAL", "DUAL"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "ASPECTO", "GENERO", "VOZ", "CASO", 
                "SINGULAR", "PLURAL", "DUAL"});
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 4, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        //agrupo por aspecto
        utilidadesTM.agrupaPorColumna(tm, 0, 3, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "ASPECTO");
        //agrupo por voz
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "VOZ");
        //agrupo por género
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo4());
        utilidadesTM.borraColumna(tm, "GENERO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"genero", "singular", "plural", "dual"});
        
        return tm;

    }
    
    

    
    /**
     * Crea y devuele el tablemodel necesario para el reporte completo de participios de la grilla
     * Si no hay formas disponibles, devuelve null.
     * @param conta
     * @return
     */
    private DefaultTableModel getParticipiosCompletoVozPrimero(String idVerbo, boolean sinVocativos, boolean sinDuales){
        DefaultTableModel tm=creadorTableModelsBasicos.getParticipiosCompletoVozPrimero(idVerbo, sinVocativos, sinDuales);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
    	utilidadesTM.columnasAInterfaz(tm, new String[]{"ACTIVA", "MEDIA", "PASIVA"});
    	
    	utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "ASPECTO", "GENERO", "NUMERO", "CASO", 
    			"ACTIVA", "MEDIA", "PASIVA"});
    	
        
    	//agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 4, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        
        //agrupo por aspecto
        utilidadesTM.agrupaPorColumna(tm, 0, 3, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "ASPECTO");
        
        //agrupo por número
        utilidadesTM.agrupaPorColumna(tm, 1, 2, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "NUMERO");
        
        //agrupo por género
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo4());
        utilidadesTM.borraColumna(tm, "GENERO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"caso", "activa", "media", "pasiva"});
        
        return tm;

        
    }
    
    
    
    
    
    
    private DefaultTableModel getParticipiosSinDual(String idVerbo, boolean sinVocativos){
        DefaultTableModel tm=creadorTableModelsBasicos.getParticipiosCompletoSinDuales(idVerbo, sinVocativos);
        if (tm==null || tm.getRowCount()==0){
            return null;
        }
        
        //paso a unicode
        utilidadesTM.columnasAInterfaz(tm, new String[]{"SINGULAR", "PLURAL"});
        
        utilidadesTM.transformaEnICeldasReporte(tm, new String[]{"PARTIC", "ASPECTO", "GENERO", "VOZ", "CASO", 
                "SINGULAR", "PLURAL"});
        
        
        //agrupo por particularidad
        utilidadesTM.agrupaPorColumna(tm, 0, 4, utilidadReportes.getColorSubtitulo1());
        utilidadesTM.borraColumna(tm, "PARTIC");
        //agrupo por aspecto
        utilidadesTM.agrupaPorColumna(tm, 0, 3, utilidadReportes.getColorSubtitulo2());
        utilidadesTM.borraColumna(tm, "ASPECTO");
        //agrupo por voz
        utilidadesTM.agrupaPorColumna(tm, 0, 2, utilidadReportes.getColorSubtitulo3());
        utilidadesTM.borraColumna(tm, "VOZ");
        //agrupo por género
        utilidadesTM.agrupaPorColumna(tm, 0, 1, utilidadReportes.getColorSubtitulo4());
        utilidadesTM.borraColumna(tm, "GENERO");
        
        utilidadesTM.reemplazaNombresColumna(tm, new String[]{"caso", "singular", "plural"});
        
        
        return tm;

    }
    
    
    
    





/**
	 * @param utilidadesTM The utilidadesTM to set.
	 */
	public void setUtilidadesTM(UtilidadesTM utilidadesTM) {
		this.utilidadesTM = utilidadesTM;
	}


    public void setUtilidadReportes(UtilidadReportes utilidadReportes) {
        this.utilidadReportes = utilidadReportes;
    }
    
    
    
}
