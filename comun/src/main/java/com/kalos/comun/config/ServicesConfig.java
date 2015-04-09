package com.kalos.comun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.kalos.datos.dao.AdjetivoDAO;
import com.kalos.datos.dao.AdjetivosComoNominalesDAO;
import com.kalos.datos.dao.AdverbiosDAO;
import com.kalos.datos.dao.CombosDAO;
import com.kalos.datos.dao.ConjuncionesDAO;
import com.kalos.datos.dao.CubosTipoPartDAO;
import com.kalos.datos.dao.DesinInfinitivosDAO;
import com.kalos.datos.dao.DesinSustDAO;
import com.kalos.datos.dao.DesinVerbosDAO;
import com.kalos.datos.dao.DiccionarioDAO;
import com.kalos.datos.dao.EncParticulasDAO;
import com.kalos.datos.dao.InterjeccionesDAO;
import com.kalos.datos.dao.IrrAdjetivosEnterosDAO;
import com.kalos.datos.dao.IrrAdjetivosIndividualesDAO;
import com.kalos.datos.dao.IrrInfinitivosDAO;
import com.kalos.datos.dao.IrrParticipiosEnterosDAO;
import com.kalos.datos.dao.IrrParticipiosSimplesDAO;
import com.kalos.datos.dao.IrrSustantivosDAO;
import com.kalos.datos.dao.IrrVerbosDAO;
import com.kalos.datos.dao.IrrVerbosIndividualesDAO;
import com.kalos.datos.dao.ParticulasDAO;
import com.kalos.datos.dao.PreposicionesDAO;
import com.kalos.datos.dao.PreposicionesEnVerbosDAO;
import com.kalos.datos.dao.SeguridadDAO;
import com.kalos.datos.dao.SignificadoDAO;
import com.kalos.datos.dao.SubstractorPrefijosDAO;
import com.kalos.datos.dao.SustantivosDAO;
import com.kalos.datos.dao.TemasTermRegNominalDAO;
import com.kalos.datos.dao.TermRegInfinitivoDAO;
import com.kalos.datos.dao.TermRegSustantivoDAO;
import com.kalos.datos.dao.TermRegVerboDAO;
import com.kalos.datos.dao.TiposAdjetivoDAO;
import com.kalos.datos.dao.TiposSustantivoDAO;
import com.kalos.datos.dao.TiposVerboDAO;
import com.kalos.datos.dao.TransParticipiosDAO;
import com.kalos.datos.dao.VerbalizadorParticipiosDAO;
import com.kalos.datos.dao.VerbosCompuestosDAO;
import com.kalos.datos.dao.VerbosDAO;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteAdjetivosComoNominales;
import com.kalos.datos.gerentes.GerenteAdjetivosComoNominalesImpl;
import com.kalos.datos.gerentes.GerenteAdjetivosImpl;
import com.kalos.datos.gerentes.GerenteAdverbios;
import com.kalos.datos.gerentes.GerenteAdverbiosImpl;
import com.kalos.datos.gerentes.GerenteCombosValores;
import com.kalos.datos.gerentes.GerenteCombosValoresImpl;
import com.kalos.datos.gerentes.GerenteConjunciones;
import com.kalos.datos.gerentes.GerenteConjuncionesImpl;
import com.kalos.datos.gerentes.GerenteCubosTipoPart;
import com.kalos.datos.gerentes.GerenteCubosTipoPartImpl;
import com.kalos.datos.gerentes.GerenteDesinInfinitivos;
import com.kalos.datos.gerentes.GerenteDesinInfinitivosImpl;
import com.kalos.datos.gerentes.GerenteDesinSust;
import com.kalos.datos.gerentes.GerenteDesinSustImpl;
import com.kalos.datos.gerentes.GerenteDesinVerbos;
import com.kalos.datos.gerentes.GerenteDesinVerbosImpl;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.datos.gerentes.GerenteDiccionarioImpl;
import com.kalos.datos.gerentes.GerenteEncParticulas;
import com.kalos.datos.gerentes.GerenteEncParticulasImpl;
import com.kalos.datos.gerentes.GerenteInterjecciones;
import com.kalos.datos.gerentes.GerenteInterjeccionesImpl;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosEnteros;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosEnterosImpl;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividualesImpl;
import com.kalos.datos.gerentes.GerenteIrrInfinitivos;
import com.kalos.datos.gerentes.GerenteIrrInfinitivosImpl;
import com.kalos.datos.gerentes.GerenteIrrParticipiosEnteros;
import com.kalos.datos.gerentes.GerenteIrrParticipiosEnterosImpl;
import com.kalos.datos.gerentes.GerenteIrrParticipiosSimples;
import com.kalos.datos.gerentes.GerenteIrrParticipiosSimplesImpl;
import com.kalos.datos.gerentes.GerenteIrrSustantivos;
import com.kalos.datos.gerentes.GerenteIrrSustantivosImpl;
import com.kalos.datos.gerentes.GerenteIrrVerbos;
import com.kalos.datos.gerentes.GerenteIrrVerbosImpl;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividualesImpl;
import com.kalos.datos.gerentes.GerenteParticulas;
import com.kalos.datos.gerentes.GerenteParticulasImpl;
import com.kalos.datos.gerentes.GerentePreposiciones;
import com.kalos.datos.gerentes.GerentePreposicionesEnVerbos;
import com.kalos.datos.gerentes.GerentePreposicionesEnVerbosImpl;
import com.kalos.datos.gerentes.GerentePreposicionesImpl;
import com.kalos.datos.gerentes.GerenteSeguridad;
import com.kalos.datos.gerentes.GerenteSeguridadImpl;
import com.kalos.datos.gerentes.GerenteSignificados;
import com.kalos.datos.gerentes.GerenteSignificadosImpl;
import com.kalos.datos.gerentes.GerenteSubstractorPrefijos;
import com.kalos.datos.gerentes.GerenteSubstractorPrefijosImpl;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteSustantivosImpl;
import com.kalos.datos.gerentes.GerenteTemasTermRegNominal;
import com.kalos.datos.gerentes.GerenteTemasTermRegNominalImpl;
import com.kalos.datos.gerentes.GerenteTermRegInfinitivo;
import com.kalos.datos.gerentes.GerenteTermRegInfinitivoImpl;
import com.kalos.datos.gerentes.GerenteTermRegSustantivo;
import com.kalos.datos.gerentes.GerenteTermRegSustantivoImpl;
import com.kalos.datos.gerentes.GerenteTermRegVerbo;
import com.kalos.datos.gerentes.GerenteTermRegVerboImpl;
import com.kalos.datos.gerentes.GerenteTiposAdjetivo;
import com.kalos.datos.gerentes.GerenteTiposAdjetivoImpl;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.datos.gerentes.GerenteTiposSustantivoImpl;
import com.kalos.datos.gerentes.GerenteTiposVerbo;
import com.kalos.datos.gerentes.GerenteTiposVerboImpl;
import com.kalos.datos.gerentes.GerenteTransParticipios;
import com.kalos.datos.gerentes.GerenteTransParticipiosImpl;
import com.kalos.datos.gerentes.GerenteVerbalizadorParticipios;
import com.kalos.datos.gerentes.GerenteVerbalizadorParticipiosImpl;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestosImpl;
import com.kalos.datos.gerentes.GerenteVerbosImpl;

@Configuration
@Import(DaoConfig.class)
public class ServicesConfig   {
    
    @Bean 
    @Autowired
    public GerenteSignificados gerenteSignificados(SignificadoDAO dao ) {
        GerenteSignificadosImpl  service = new GerenteSignificadosImpl();
        service.setSignificadoDAO(dao);
        return service;
    }   
    
    @Bean 
    @Autowired
    public GerenteAdjetivos gerenteAdjetivos(AdjetivoDAO adjetivoDAO, GerenteSignificados gerenteSignificados, IrrAdjetivosEnterosDAO  irrAdjetivosEnterosDAO, IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAO, 
            GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales, GerenteIrrAdjetivosEnteros gerenteIrrAdjetivosEnteros) {
        
        GerenteAdjetivosImpl  service = new GerenteAdjetivosImpl();
        
        service.setAdjetivoDAO(adjetivoDAO);
        service.setGerenteAdjetivosComoNominales(gerenteAdjetivosComoNominales);
        service.setGerenteSignificados(gerenteSignificados);
        service.setGerenteAdjetivosComoNominales(gerenteAdjetivosComoNominales);
        service.setIrrAdjetivosEnterosDAO(irrAdjetivosEnterosDAO);
        service.setGerenteIrrAdjetivosEnteros(gerenteIrrAdjetivosEnteros);
        return service;
    }  
    
    @Bean 
    @Autowired   
    public GerenteAdverbios gerenteAdverbios(AdverbiosDAO adverbiosDAO,  GerenteSignificados gerenteSignificados){
        GerenteAdverbiosImpl service = new GerenteAdverbiosImpl();
        service.setAdverbiosDAO(adverbiosDAO);
        service.setGerenteSignificados(gerenteSignificados);
        return service;
    }
    
    @Bean 
    @Autowired     
    public GerenteInterjecciones gerenteInterjecciones(InterjeccionesDAO interjeccionesDAO, GerenteSignificados gerenteSignificados){
        GerenteInterjeccionesImpl service = new GerenteInterjeccionesImpl();
        service.setInterjeccionesDAO(interjeccionesDAO);
        service.setGerenteSignificados(gerenteSignificados);
        return service;
    }

    @Bean 
    @Autowired     
    public GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales(AdjetivosComoNominalesDAO adjetivosComoNominalesDAO){
        GerenteAdjetivosComoNominalesImpl service = new GerenteAdjetivosComoNominalesImpl();
        service.setAdjetivosComoNominalesDAO(adjetivosComoNominalesDAO);
        return service;
    }
    
    @Bean 
    @Autowired 
    public GerenteCombosValores gerenteCombosValores (CombosDAO combosDAO ){
        GerenteCombosValoresImpl service = new GerenteCombosValoresImpl();
        service.setCombosDAO(combosDAO);
        return service;
    }
    
    @Bean 
    @Autowired 
    public GerenteConjunciones gerenteConjunciones(ConjuncionesDAO conjuncionesDAO, GerenteSignificados gerenteSignificados){
        GerenteConjuncionesImpl service = new GerenteConjuncionesImpl();
        service.setConjuncionesDAO(conjuncionesDAO);
        service.setGerenteSignificados(gerenteSignificados);
        return service;
    }
    
    @Bean 
    @Autowired 
    public GerenteCubosTipoPart gerenteCubosTipoPart(CubosTipoPartDAO cubosTipoPartDAO){
        GerenteCubosTipoPartImpl service = new GerenteCubosTipoPartImpl();
        service.setCubosTipoPartDAO(cubosTipoPartDAO);
        return service;
    }
    
    @Bean 
    @Autowired 
    public GerenteDesinSust gerenteDesinSust(DesinSustDAO desinSustDAO){
        GerenteDesinSustImpl service = new GerenteDesinSustImpl();
        service.setDesinSustDAO(desinSustDAO);
        return service;
    }
    
    @Bean 
    @Autowired     
    public GerenteDesinInfinitivos gerenteDesinInfinitivos(DesinInfinitivosDAO desinInfinitivosDAO){
        GerenteDesinInfinitivosImpl service = new GerenteDesinInfinitivosImpl();
        service.setDesinInfinitivosDAO(desinInfinitivosDAO);
        return service;
    }
    
    @Bean 
    @Autowired     
    public GerenteDesinVerbos gerenteDesinVerbos(DesinVerbosDAO desinVerbosDAO){
        GerenteDesinVerbosImpl service = new GerenteDesinVerbosImpl();
        service.setDesinVerbosDAO(desinVerbosDAO);
        return service;
    }
    
    @Bean 
    @Autowired    public GerenteEncParticulas gerenteEncParticulas(EncParticulasDAO encParticulasDAO){
        GerenteEncParticulasImpl service = new GerenteEncParticulasImpl();
        service.setEncParticulasDAO(encParticulasDAO);
        return service;
    }
    
    @Bean 
    @Autowired    
    public GerenteDiccionario gerenteDiccionario(DiccionarioDAO diccionarioDAO){
        GerenteDiccionarioImpl service= new GerenteDiccionarioImpl();
        service.setDiccionarioDAO(diccionarioDAO);
        return service;
    }
    
    @Bean 
    @Autowired    
    public GerenteIrrAdjetivosEnteros gerenteIrrAdjetivosEnteros(IrrAdjetivosEnterosDAO irrAdjetivosEnterosDAO){
        GerenteIrrAdjetivosEnterosImpl service = new GerenteIrrAdjetivosEnterosImpl();
        service.setIrrAdjetivosEnterosDAO(irrAdjetivosEnterosDAO);
        return service;
    }
 
    @Bean 
    @Autowired   
    public GerenteIrrAdjetivosIndividuales gerenteIrrAdjetivosIndividuales(IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAO){
        GerenteIrrAdjetivosIndividualesImpl service = new GerenteIrrAdjetivosIndividualesImpl();
        service.setIrrAdjetivosIndividualesDAO(irrAdjetivosIndividualesDAO);
        return service;
    }
    
    @Bean 
    @Autowired    
    public GerenteIrrInfinitivos gerenteIrrInfinitivos(IrrInfinitivosDAO irrInfinitivosDAO){
        GerenteIrrInfinitivosImpl service = new GerenteIrrInfinitivosImpl();
        service.setIrrInfinitivosDAO(irrInfinitivosDAO);
        return service;
    }

    @Bean 
    @Autowired    
    public GerenteIrrParticipiosEnteros gerenteIrrParticipiosEnteros(IrrParticipiosEnterosDAO irrParticipiosEnterosDAO){
        GerenteIrrParticipiosEnterosImpl service= new GerenteIrrParticipiosEnterosImpl();
        service.setIrrParticipiosEnterosDAO(irrParticipiosEnterosDAO);
        return service;
    }

    @Bean 
    @Autowired    
    public GerenteIrrParticipiosSimples gerenteIrrParticipiosSimples(IrrParticipiosSimplesDAO irrParticipiosSimplesDAO){
        GerenteIrrParticipiosSimplesImpl service = new GerenteIrrParticipiosSimplesImpl();
        service.setIrrParticipiosSimplesDAO(irrParticipiosSimplesDAO);
        return service;
    }
    
    
    @Bean 
    @Autowired   
    public GerenteIrrSustantivos gerenteIrrSustantivos(IrrSustantivosDAO irrSustantivosDAO){
        GerenteIrrSustantivosImpl service = new GerenteIrrSustantivosImpl();
        service.setIrrSustantivosDAO(irrSustantivosDAO);
        return service;
    }
    
    
    @Bean 
    @Autowired    
    public GerenteIrrVerbos gerenteIrrVerbos(IrrVerbosDAO irrVerbosDAO){
        GerenteIrrVerbosImpl service = new GerenteIrrVerbosImpl();
        service.setIrrVerbosDAO(irrVerbosDAO);
        return service;
    }
    
    @Bean 
    @Autowired    
    public GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales(IrrVerbosIndividualesDAO irrVerbosIndividualesDAO){
        GerenteIrrVerbosIndividualesImpl service = new GerenteIrrVerbosIndividualesImpl();
        service.setIrrVerbosIndividualesDAO(irrVerbosIndividualesDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteTiposAdjetivo gerenteTiposAdjetivo(TiposAdjetivoDAO tiposAdjetivoDAO){
        GerenteTiposAdjetivoImpl service = new GerenteTiposAdjetivoImpl();
        service.setTiposAdjetivoDAO(tiposAdjetivoDAO);
        return service;
    }
    
    @Bean 
    @Autowired    
    public GerenteParticulas gerenteParticulas(ParticulasDAO particulasDAO){
        GerenteParticulasImpl service = new GerenteParticulasImpl();
        service.setParticulasDAO(particulasDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerentePreposiciones gerentePreposiciones(PreposicionesDAO preposicionesDAO){
        GerentePreposicionesImpl service = new GerentePreposicionesImpl();
        service.setPreposicionesDAO(preposicionesDAO);
        return service;
    }
    
    
    @Bean 
    @Autowired   
    public GerentePreposicionesEnVerbos gerentePreposicionesEnVerbos(PreposicionesEnVerbosDAO preposicionesEnVerbosDAO){
        GerentePreposicionesEnVerbosImpl service = new GerentePreposicionesEnVerbosImpl();
        service.setPreposicionesEnVerbosDAO(preposicionesEnVerbosDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteSeguridad gerenteSeguridad(SeguridadDAO seguridadDAO){
        GerenteSeguridadImpl service = new GerenteSeguridadImpl();
        service.setSeguridadDAO(seguridadDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteSustantivos gerenteSustantivos(SustantivosDAO sustantivosDAO, IrrSustantivosDAO irrSustantivosDAO, DiccionarioDAO diccionarioDAO, GerenteSignificados gerenteSignificados){
        GerenteSustantivosImpl service = new GerenteSustantivosImpl();
        service.setSustantivosDAO(sustantivosDAO);
        service.setIrrSustantivosDAO(irrSustantivosDAO);
        service.setDiccionarioDAO(diccionarioDAO);
        service.setGerenteSignificados(gerenteSignificados);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteSubstractorPrefijos gerenteSubstractorPrefijos(SubstractorPrefijosDAO substractorPrefijosDAO){
        GerenteSubstractorPrefijosImpl service = new GerenteSubstractorPrefijosImpl();
        service.setSubstractorPrefijosDAO(substractorPrefijosDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteTemasTermRegNominal gerenteTemasTermRegNominal(TemasTermRegNominalDAO  temasTermRegNominalDAO){
        GerenteTemasTermRegNominalImpl service = new GerenteTemasTermRegNominalImpl();
        service.setTemasTermRegNominalDAO(temasTermRegNominalDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteTiposSustantivo gerenteTiposSustantivo(TiposSustantivoDAO tiposSustantivoDAO){
        GerenteTiposSustantivoImpl service = new GerenteTiposSustantivoImpl();
        service.setTiposSustantivoDAO(tiposSustantivoDAO);
        return service;
    }
    
    @Bean 
    @Autowired    
    public GerenteTiposVerbo gerenteTiposVerbo(TiposVerboDAO tiposVerboDAO){
        GerenteTiposVerboImpl service = new GerenteTiposVerboImpl();
        service.setTiposVerboDAO(tiposVerboDAO);
        return service;
    }
    
    
    @Bean 
    @Autowired
    public GerenteTermRegInfinitivo gerenteTermRegInfinitivo(TermRegInfinitivoDAO termRegInfinitivoDAO){
        GerenteTermRegInfinitivoImpl service = new GerenteTermRegInfinitivoImpl();
        service.setTermRegInfinitivoDAO(termRegInfinitivoDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteTermRegSustantivo gerenteTermRegSustantivo(TermRegSustantivoDAO termRegSustantivoDAO){
        GerenteTermRegSustantivoImpl service = new GerenteTermRegSustantivoImpl();
        service.setTermRegSustantivoDAO(termRegSustantivoDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteTermRegVerbo gerenteTermRegVerbo(TermRegVerboDAO termRegVerboDAO){
        GerenteTermRegVerboImpl service = new GerenteTermRegVerboImpl();
        service.setTermRegVerboDAO(termRegVerboDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteTransParticipios gerenteTransParticipios(TransParticipiosDAO transParticipiosDAO){
        GerenteTransParticipiosImpl service = new GerenteTransParticipiosImpl();
        service.setTransParticipiosDAO(transParticipiosDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteVerbalizadorParticipios gerenteVerbalizadorParticipios(VerbalizadorParticipiosDAO verbalizadorParticipiosDAO){
        GerenteVerbalizadorParticipiosImpl service = new GerenteVerbalizadorParticipiosImpl();
        service.setVerbalizadorParticipiosDAO(verbalizadorParticipiosDAO);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteVerbos gerenteVerbos(VerbosDAO verbosDAO, IrrVerbosDAO irrVerbosDAO, IrrVerbosIndividualesDAO irrVerbosIndividualesDAO, GerenteSignificados gerenteSignificados, 
            GerentePreposicionesEnVerbos gerentePreposicionesEnVerbos, GerenteVerbosCompuestos gerenteVerbosCompuestos){
        GerenteVerbosImpl service = new GerenteVerbosImpl();
        service.setVerbosDAO(verbosDAO);
        service.setIrrVerbosDAO(irrVerbosDAO);
        service.setIrrVerbosIndividualesDAO(irrVerbosIndividualesDAO);
        service.setGerenteSignificados(gerenteSignificados);
        service.setGerentePreposicionesEnVerbos(gerentePreposicionesEnVerbos);
        service.setGerenteVerbosCompuestos(gerenteVerbosCompuestos);
        return service;
    }
    
    @Bean 
    @Autowired   
    public GerenteVerbosCompuestos gerenteVerbosCompuestos(VerbosCompuestosDAO verbosCompuestosDAO, PreposicionesEnVerbosDAO preposicionesEnVerbosDAO){
        GerenteVerbosCompuestosImpl service = new GerenteVerbosCompuestosImpl();
        service.setVerbosCompuestosDAO(verbosCompuestosDAO);
        service.setPreposicionesEnVerbosDAO(preposicionesEnVerbosDAO);
        return service;
    }
 
}
