package com.kalos.comun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.kalos.analisismorfologico.negocio.AMAdjetivos;
import com.kalos.analisismorfologico.negocio.AMAdverbios;
import com.kalos.analisismorfologico.negocio.AMConjunciones;
import com.kalos.analisismorfologico.negocio.AMInfinitivos;
import com.kalos.analisismorfologico.negocio.AMInterjecciones;
import com.kalos.analisismorfologico.negocio.AMNominal;
import com.kalos.analisismorfologico.negocio.AMParticipios;
import com.kalos.analisismorfologico.negocio.AMParticulas;
import com.kalos.analisismorfologico.negocio.AMPreposiciones;
import com.kalos.analisismorfologico.negocio.AMSustantivos;
import com.kalos.analisismorfologico.negocio.AMUtil;
import com.kalos.analisismorfologico.negocio.AMVerbal;
import com.kalos.analisismorfologico.negocio.AMVerbos;
import com.kalos.analisismorfologico.negocio.ExtractorPrefijos;
import com.kalos.beans.TermRegInfinitivo;
import com.kalos.beans.TermRegVerbal;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteAdjetivosComoNominales;
import com.kalos.datos.gerentes.GerenteCubosTipoPart;
import com.kalos.datos.gerentes.GerenteDesinSust;
import com.kalos.datos.gerentes.GerenteIrrAdjetivosIndividuales;
import com.kalos.datos.gerentes.GerenteIrrInfinitivos;
import com.kalos.datos.gerentes.GerenteIrrParticipiosEnteros;
import com.kalos.datos.gerentes.GerenteIrrParticipiosSimples;
import com.kalos.datos.gerentes.GerenteIrrSustantivos;
import com.kalos.datos.gerentes.GerenteIrrVerbos;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import com.kalos.datos.gerentes.GerenteSubstractorPrefijos;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteTermRegInfinitivo;
import com.kalos.datos.gerentes.GerenteTermRegSustantivo;
import com.kalos.datos.gerentes.GerenteTermRegVerbo;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.datos.gerentes.GerenteTransParticipios;
import com.kalos.datos.gerentes.GerenteVerbalizadorParticipios;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestos;
import com.kalos.flexion.conjugacion.Verbos;
import com.kalos.flexion.declinacion.Participios;

@Configuration
@Import(FlexionConfig.class)
@Lazy
public class AnalisisMorfologicoConfig {
    
    
    @Bean
    @Autowired
    public ExtractorPrefijos extractorPrefijos(GerenteSubstractorPrefijos gerenteSubstractorPrefijos){
        ExtractorPrefijos service = new ExtractorPrefijos();
        service.setGerenteSubstractorPrefijos(gerenteSubstractorPrefijos);
        return service;
    }
    
    @Bean
    @Autowired  
    public AMUtil amUtil(){
        return new AMUtil();
    }
    
    @Bean
    @Autowired   
    public AMVerbal amVerbal(AMUtil amUtil, GerenteVerbos gerenteVerbos, GerenteVerbosCompuestos gerenteVerbosCompuestos, GerenteIrrVerbos gerenteIrrVerbos, 
           ExtractorPrefijos extractorPrefijos, GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales, GerenteIrrInfinitivos gerenteIrrInfinitivos ){
        
      AMVerbal service = new AMVerbal();
      service.setGerenteVerbos(gerenteVerbos);
      service.setGerenteVerbosCompuestos(gerenteVerbosCompuestos);
      service.setGerenteIrrVerbos(gerenteIrrVerbos);
      service.setExtractorPrefijos(extractorPrefijos);
      service.setGerenteIrrVerbosIndividuales(gerenteIrrVerbosIndividuales);
      service.setGerenteIrrInfinitivos(gerenteIrrInfinitivos);
      return service;
    
    }

 
    @Bean
    @Autowired   
    public AMVerbos amVerbos(AMUtil<TermRegVerbal> amUtil, AMVerbal amVerbal, GerenteVerbos gerenteVerbos, Verbos verbos, GerenteTermRegVerbo gerenteTermRegVerbo,  
            ExtractorPrefijos extractorPrefijos, GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales){
        
        AMVerbos service = new AMVerbos();
        service.setAmUtil(amUtil);
        service.setAmVerbal(amVerbal);
        service.setGerenteVerbos(gerenteVerbos);
        service.setVerbos(verbos);
        service.setGerenteTermRegVerbo(gerenteTermRegVerbo);
        service.setExtractorPrefijos(extractorPrefijos);
        service.setGerenteIrrVerbosIndividuales(gerenteIrrVerbosIndividuales);
        return service;
        
    }
    
    @Bean
    @Autowired
    public AMInfinitivos amInfinitivos(AMUtil<TermRegInfinitivo> amUtil, AMVerbal amVerbal, ExtractorPrefijos extractorPrefijos, GerenteTermRegInfinitivo gerenteTermRegInfinitivo,  
            GerenteVerbos gerenteVerbos, GerenteIrrInfinitivos gerenteIrrInfinitivos){
        
        AMInfinitivos service = new AMInfinitivos();
        service.setExtractorPrefijos(extractorPrefijos);
        service.setGerenteTermRegInfinitivo(gerenteTermRegInfinitivo);
        service.setGerenteVerbos(gerenteVerbos);
        service.setGerenteIrrInfinitivos(gerenteIrrInfinitivos);
        return service;
        
    }

    @Bean
    @Autowired   
    public AMNominal amNominal(AMUtil amUtil, GerenteTermRegSustantivo gerenteTermRegSustantivo, GerenteTiposSustantivo gerenteTiposSustantivo, 
            GerenteDesinSust gerenteDesinSust){
        
        AMNominal service = new AMNominal(gerenteTiposSustantivo, gerenteDesinSust);
        service.setAmUtil(amUtil);
        service.setGerenteTermRegSustantivo(gerenteTermRegSustantivo);
        return service;
    }
    
    @Bean
    @Autowired
    public AMParticipios amParticipios(AMUtil amUtil, AMVerbal amVerbal, AMNominal amNominal, Participios participios, ExtractorPrefijos extractorPrefijos,  
           GerenteVerbos gerenteVerbos, GerenteVerbalizadorParticipios gerenteVerbalizadorParticipios, GerenteVerbosCompuestos gerenteVerbosCompuestos, 
           GerenteIrrParticipiosEnteros gerenteIrrParticipiosEnteros, GerenteIrrParticipiosSimples gerenteIrrParticipiosSimples, GerenteCubosTipoPart gerenteCubosTipoPart, 
           GerenteTransParticipios gerenteTransParticipios){
        
        AMParticipios service = new AMParticipios();
        service.setAmNominal(amNominal);
        service.setParticipios(participios);
        service.setExtractorPrefijos(extractorPrefijos);
        service.setGerenteVerbos(gerenteVerbos);
        service.setGerenteVerbalizadorParticipios(gerenteVerbalizadorParticipios);
        service.setGerenteVerbosCompuestos(gerenteVerbosCompuestos);
        service.setGerenteIrrParticipiosEnteros(gerenteIrrParticipiosEnteros);
        service.setGerenteIrrParticipiosSimples(gerenteIrrParticipiosSimples);
        service.setGerenteCubosTipoPart(gerenteCubosTipoPart);
        service.setGerenteTransParticipios(gerenteTransParticipios);
        return service;
    }
    
    @Bean
    @Autowired
    public AMSustantivos amSustantivos(AMUtil amUtil, AMNominal amNominal, GerenteIrrSustantivos gerenteIrrSustantivos, GerenteSustantivos gerenteSustantivos){
        
        AMSustantivos service = new AMSustantivos();
        service.setAmNominal(amNominal);
        service.setGerenteIrrSustantivos(gerenteIrrSustantivos);
        service.setGerenteSustantivos(gerenteSustantivos);
        return service;
        
    }
    
    @Bean
    @Autowired   
    public AMAdjetivos amAdjetivos(AMUtil amUtil, AMNominal amNominal, GerenteAdjetivosComoNominales gerenteAdjetivosComoNominales, GerenteIrrAdjetivosIndividuales gerenteIrrAdjetivosIndividuales,  
            GerenteAdjetivos gerenteAdjetivos){
        
        AMAdjetivos service = new AMAdjetivos();
        service.setAmUtil(amUtil);
        service.setAmNominal(amNominal);
        service.setGerenteAdjetivosComoNominales(gerenteAdjetivosComoNominales);
        service.setGerenteIrrAdjetivosIndividuales(gerenteIrrAdjetivosIndividuales);
        service.setGerenteAdjetivos(gerenteAdjetivos);
        return service;
    }
    
    @Bean
    @Autowired
    public AMAdverbios amAdverbios(){
        return new AMAdverbios();
    }
  
    
    @Bean
    @Autowired    
    public AMParticulas amParticulas(){
        return new AMParticulas();
    }
    
    @Bean
    @Autowired   
    public AMConjunciones amConjunciones(){
        return new AMConjunciones();
    }
   
    @Bean
    @Autowired   
    public AMPreposiciones amPreposiciones(){
        return new AMPreposiciones();
    }
    
    @Bean
    @Autowired   
    public AMInterjecciones amInterjecciones(){
        return new AMInterjecciones();
    }

}
