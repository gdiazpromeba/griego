package com.kalos.comun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteConjunciones;
import com.kalos.datos.gerentes.GerenteDesinInfinitivos;
import com.kalos.datos.gerentes.GerenteDesinSust;
import com.kalos.datos.gerentes.GerenteDesinVerbos;
import com.kalos.datos.gerentes.GerenteInterjecciones;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosEnteros;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales;
import com.kalos.datos.gerentes.GerenteIrrInfinitivos;
import com.kalos.datos.gerentes.GerenteIrrParticipiosEnteros;
import com.kalos.datos.gerentes.GerenteIrrParticipiosSimples;
import com.kalos.datos.gerentes.GerenteIrrSustantivos;
import com.kalos.datos.gerentes.GerenteIrrVerbos;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import com.kalos.datos.gerentes.GerenteParticulas;
import com.kalos.datos.gerentes.GerentePreposiciones;
import com.kalos.datos.gerentes.GerentePreposicionesEnVerbos;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteTiposAdjetivo;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.datos.gerentes.GerenteTiposVerbo;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestos;
import com.kalos.flexion.CreadorTableModelsBasicos;
import com.kalos.flexion.CreadorTableModelsGrilla;
import com.kalos.flexion.CreadorTableModelsReporte;
import com.kalos.flexion.Preposiciones;
import com.kalos.flexion.ProveedorDMConjunciones;
import com.kalos.flexion.ProveedorDMInterjecciones;
import com.kalos.flexion.ProveedorDMNominal;
import com.kalos.flexion.ProveedorDMParticulas;
import com.kalos.flexion.ProveedorDMPreposiciones;
import com.kalos.flexion.ProveedorDMVerbal;
import com.kalos.flexion.UtilidadReportes;
import com.kalos.flexion.UtilidadesTM;
import com.kalos.flexion.conjugacion.CacheFlexionInfinitivos;
import com.kalos.flexion.conjugacion.CacheFlexionVerbos;
import com.kalos.flexion.conjugacion.Infinitivos;
import com.kalos.flexion.conjugacion.Verbos;
import com.kalos.flexion.conjugacion.negocio.ManejaDesinenciasInfinitivo;
import com.kalos.flexion.conjugacion.negocio.ManejaDesinenciasVerbo;
import com.kalos.flexion.declinacion.CacheFlexionParticipio;
import com.kalos.flexion.declinacion.Declina;
import com.kalos.flexion.declinacion.DeclinaAdjetivos;
import com.kalos.flexion.declinacion.ManejaDesinenciasSustantivo;
import com.kalos.flexion.declinacion.Participios;

@Configuration
@Import(AnalisisMorfologicoConfig.class)
public class SwingConfig {
    
   
    
 
    @Bean
    @Autowired    
    public CreadorTableModelsBasicos creadorTableModelsBasicos(UtilidadesTM utilidadesTM, ProveedorDMNominal proveedorDMNominal, ProveedorDMVerbal proveedorDMVerbal, 
            ProveedorDMParticulas proveedorDMParticulas, ProveedorDMConjunciones proveedorDMConjunciones, ProveedorDMPreposiciones proveedorDMPreposiciones, 
            ProveedorDMInterjecciones proveedorDMInterjecciones){
        CreadorTableModelsBasicos service = new CreadorTableModelsBasicos();
        service.setUtilidadesTM(utilidadesTM);
        service.setProveedorDMNominal(proveedorDMNominal);
        service.setProveedorDMVerbal(proveedorDMVerbal);
        service.setProveedorDMParticulas(proveedorDMParticulas);
        service.setProveedorDMConjunciones(proveedorDMConjunciones);
        service.setProveedorDMPreposiciones(proveedorDMPreposiciones);
        service.setProveedorDMInterjecciones(proveedorDMInterjecciones);
        return service;
    
    }
    
    
    @Bean
    @Autowired
    public CreadorTableModelsReporte creadorTableModelsReporte(CreadorTableModelsBasicos creadorTableModelsBasicos, UtilidadesTM utilidadesTM){
        CreadorTableModelsReporte service = new CreadorTableModelsReporte();
        service.setCreadorTableModelsBasicos(creadorTableModelsBasicos);
        service.setUtilidadesTM(utilidadesTM);
        return service;
    }

    @Bean
    @Autowired    
    public CreadorTableModelsGrilla creadorTableModelsGrilla(CreadorTableModelsBasicos creadorTableModelsBasicos, UtilidadesTM utilidadesTM, UtilidadReportes utilidadReportes){
        CreadorTableModelsGrilla service = new CreadorTableModelsGrilla();
        service.setCreadorTableModelsBasicos(creadorTableModelsBasicos);
        service.setUtilidadesTM(utilidadesTM);
        service.setUtilidadReportes(utilidadReportes);
        return service;
    }
 
    
    @Bean
    @Autowired
    public ProveedorDMNominal proveedorDMNominal(Participios participios, GerenteVerbos gerenteVerbos, Declina declina, DeclinaAdjetivos declinaAdjetivos,
            UtilidadesTM utilidadesTM){
        
        ProveedorDMNominal service = new ProveedorDMNominal();
        service.setParticipios(participios);
        service.setGerenteVerbos(gerenteVerbos);
        service.setDeclina(declina);
        service.setDeclinaAdjetivos(declinaAdjetivos);
        service.setUtilidadesTM(utilidadesTM);
        return service;
    }
   
    @Bean
    @Autowired    
    public ProveedorDMConjunciones proveedorDMConjunciones(GerenteConjunciones gerenteConjunciones){
        ProveedorDMConjunciones service = new ProveedorDMConjunciones();
        service.setGerenteConjunciones(gerenteConjunciones);
        return service;
    }
  
    @Bean
    @Autowired   
    public ProveedorDMParticulas proveedorDMParticulas(GerenteParticulas gerenteParticulas){
        ProveedorDMParticulas service = new ProveedorDMParticulas();
        service.setGerenteParticulas(gerenteParticulas);
        return service;
    }
   
    @Bean
    @Autowired   
    public ProveedorDMPreposiciones proveedorDMPreposiciones(GerentePreposiciones gerentePreposiciones){
        ProveedorDMPreposiciones service = new ProveedorDMPreposiciones();
        service.setGerentePreposiciones(gerentePreposiciones);
        return service;
    }
    
    @Bean
    @Autowired 
    public ProveedorDMInterjecciones proveedorDMInterjecciones(GerenteInterjecciones gerenteInterjecciones){
        ProveedorDMInterjecciones service = new ProveedorDMInterjecciones();
        service.setGerenteInterjecciones(gerenteInterjecciones);
        return service;
    }
    
    @Bean
    @Autowired   
    public ProveedorDMVerbal proveedorDMVerbal(GerenteVerbos gerenteVerbos, GerenteIrrVerbos gerenteIrrVerbos, Verbos verbos, Infinitivos infinitivos, 
            UtilidadesTM utilidadesTM, CacheFlexionVerbos cacheFlexionVerbos, CacheFlexionInfinitivos cachefFlexionInfinitivos){
        
        ProveedorDMVerbal service = new ProveedorDMVerbal();
        service.setGerenteIrrVerbos(gerenteIrrVerbos);
        service.setGerenteVerbos(gerenteVerbos);
        service.setVerbos(verbos);
        service.setInfinitivos(infinitivos);
        service.setUtilidadesTM(utilidadesTM);
        service.setCacheFlexionVerbos(cacheFlexionVerbos);
        service.setCacheFlexionInfinitivos(cachefFlexionInfinitivos);
        return service;
    }

    @Bean
    @Autowired    
    public UtilidadReportes utilidadReportes(){
        return new UtilidadReportes();
    }
    
    @Bean
    @Autowired    
    public UtilidadesTM utilidadesTM(){
        return new UtilidadesTM();
    }

  
        
    
}
