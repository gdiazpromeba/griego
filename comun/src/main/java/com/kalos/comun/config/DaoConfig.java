package com.kalos.comun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kalos.datos.dao.AdjetivoDAO;
import com.kalos.datos.dao.AdjetivoDAOImpl;
import com.kalos.datos.dao.AdjetivosComoNominalesDAO;
import com.kalos.datos.dao.AdjetivosComoNominalesDAOImpl;
import com.kalos.datos.dao.AdverbiosDAO;
import com.kalos.datos.dao.AdverbiosDAOImpl;
import com.kalos.datos.dao.CombosDAO;
import com.kalos.datos.dao.CombosDAOImpl;
import com.kalos.datos.dao.ConjuncionesDAO;
import com.kalos.datos.dao.ConjuncionesDAOImpl;
import com.kalos.datos.dao.CubosTipoPartDAO;
import com.kalos.datos.dao.CubosTipoPartDAOImpl;
import com.kalos.datos.dao.DesinInfinitivosDAO;
import com.kalos.datos.dao.DesinInfintivosDAOImpl;
import com.kalos.datos.dao.DesinSustDAO;
import com.kalos.datos.dao.DesinSustDAOImpl;
import com.kalos.datos.dao.DesinVerbosDAO;
import com.kalos.datos.dao.DesinVerbosDAOImpl;
import com.kalos.datos.dao.DiccionarioDAO;
import com.kalos.datos.dao.DiccionarioDAOImpl;
import com.kalos.datos.dao.EncParticulasDAO;
import com.kalos.datos.dao.EncParticulasDAOImpl;
import com.kalos.datos.dao.InterjeccionesDAO;
import com.kalos.datos.dao.InterjeccionesDAOImpl;
import com.kalos.datos.dao.IrrAdjetivosEnterosDAO;
import com.kalos.datos.dao.IrrAdjetivosEnterosDAOImpl;
import com.kalos.datos.dao.IrrAdjetivosIndividualesDAO;
import com.kalos.datos.dao.IrrAdjetivosIndividualesDAOImpl;
import com.kalos.datos.dao.IrrInfinitivosDAO;
import com.kalos.datos.dao.IrrInfinitivosDAOImpl;
import com.kalos.datos.dao.IrrParticipiosEnterosDAO;
import com.kalos.datos.dao.IrrParticipiosEnterosDAOImpl;
import com.kalos.datos.dao.IrrParticipiosSimplesDAO;
import com.kalos.datos.dao.IrrParticipiosSimplesDAOImpl;
import com.kalos.datos.dao.IrrSustantivosDAO;
import com.kalos.datos.dao.IrrSustantivosDAOImpl;
import com.kalos.datos.dao.IrrVerbosDAO;
import com.kalos.datos.dao.IrrVerbosDAOImpl;
import com.kalos.datos.dao.IrrVerbosIndividualesDAO;
import com.kalos.datos.dao.IrrVerbosIndividualesDAOImpl;
import com.kalos.datos.dao.ParticulasDAO;
import com.kalos.datos.dao.ParticulasDAOImpl;
import com.kalos.datos.dao.PreposicionesDAO;
import com.kalos.datos.dao.PreposicionesDAOImpl;
import com.kalos.datos.dao.PreposicionesEnVerbosDAO;
import com.kalos.datos.dao.PreposicionesEnVerbosDAOImpl;
import com.kalos.datos.dao.SeguridadDAO;
import com.kalos.datos.dao.SeguridadDAOImpl;
import com.kalos.datos.dao.SignificadoDAO;
import com.kalos.datos.dao.SignificadoDAOImpl;
import com.kalos.datos.dao.SubstractorPrefijosDAO;
import com.kalos.datos.dao.SubstractorPrefijosDAOImpl;
import com.kalos.datos.dao.SustantivosDAO;
import com.kalos.datos.dao.SustantivosDAOImpl;
import com.kalos.datos.dao.TemasTermRegNominalDAO;
import com.kalos.datos.dao.TemasTermRegNominalDAOImpl;
import com.kalos.datos.dao.TermRegInfinitivoDAO;
import com.kalos.datos.dao.TermRegInfinitivoDAOImpl;
import com.kalos.datos.dao.TermRegSustantivoDAO;
import com.kalos.datos.dao.TermRegSustantivoDAOImpl;
import com.kalos.datos.dao.TermRegVerboDAO;
import com.kalos.datos.dao.TermRegVerboDAOImpl;
import com.kalos.datos.dao.TiposAdjetivoDAO;
import com.kalos.datos.dao.TiposAdjetivoDAOImpl;
import com.kalos.datos.dao.TiposSustantivoDAO;
import com.kalos.datos.dao.TiposSustantivoDAOImpl;
import com.kalos.datos.dao.TiposVerboDAO;
import com.kalos.datos.dao.TiposVerboDAOImpl;
import com.kalos.datos.dao.TransParticipiosDAO;
import com.kalos.datos.dao.TransParticipiosDAOImpl;
import com.kalos.datos.dao.VerbalizadorParticipiosDAO;
import com.kalos.datos.dao.VerbalizadorParticipiosDAOImpl;
import com.kalos.datos.dao.VerbosCompuestosDAO;
import com.kalos.datos.dao.VerbosCompuestosDAOImpl;
import com.kalos.datos.dao.VerbosDAO;
import com.kalos.datos.dao.VerbosDAOImpl;
import com.kalos.datos.modulos.FuenteDeDatosKalos;

@Configuration
public class DaoConfig {

    @Bean 
	public FuenteDeDatosKalos kalosDataSource() {
		FuenteDeDatosKalos dataSource = new FuenteDeDatosKalos();
		dataSource.setDriverClassName("com.mckoi.JDBCDriver");
		dataSource.setUrl("jdbc:mckoi://localhost/");
		dataSource.setUsername("gonzalo");
		dataSource.setPassword("ymcsngm");
		return dataSource;
	}
	
	@Bean 
	public AdjetivoDAO adjetivoDAO(){
		AdjetivoDAOImpl dao= new AdjetivoDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}
	
	
	@Bean
	public AdverbiosDAO adverbiosDAO(){
		AdverbiosDAOImpl dao= new AdverbiosDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}
	
	@Bean
	public AdjetivosComoNominalesDAO adjetivosComoNominalesDAO(){
		AdjetivosComoNominalesDAOImpl dao= new AdjetivosComoNominalesDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}
	
	
	@Bean
	public CombosDAO combosDAO(){
		CombosDAOImpl dao= new CombosDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}	
	
	@Bean
	public ConjuncionesDAO conjuncionesDAO(){
		ConjuncionesDAOImpl dao= new ConjuncionesDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}	
	
	
	@Bean
	public CubosTipoPartDAO cubosTipoPartDAO(){
		CubosTipoPartDAOImpl dao= new CubosTipoPartDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}	
	
	@Bean
	public DesinSustDAO desinSustDAO(){
		DesinSustDAOImpl dao= new DesinSustDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}

	@Bean
	public DesinInfinitivosDAO desinInfinitivosDAO(){
		DesinInfintivosDAOImpl dao= new DesinInfintivosDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}

	@Bean
	public DesinVerbosDAO desinVerbosDAO(){
		DesinVerbosDAOImpl dao= new DesinVerbosDAOImpl();
		dao.setDataSource(kalosDataSource());
		return dao;
	}

    @Bean
    public DiccionarioDAO diccionarioDAO(){
        DiccionarioDAOImpl dao= new DiccionarioDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }
    
    @Bean
    public EncParticulasDAO encParticulasDAO(){
        EncParticulasDAOImpl dao= new EncParticulasDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }   


    @Bean
    public InterjeccionesDAO interjeccionesDAO(){
        InterjeccionesDAOImpl dao= new InterjeccionesDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }  
    
    @Bean
    public IrrAdjetivosEnterosDAO irrAdjetivosEnterosDAO(){
        IrrAdjetivosEnterosDAOImpl dao= new IrrAdjetivosEnterosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }     
    
    @Bean
    public IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAO(){
        IrrAdjetivosIndividualesDAOImpl dao= new IrrAdjetivosIndividualesDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }  
    

    @Bean
    public IrrInfinitivosDAO irrInfinitivosDAO(){
        IrrInfinitivosDAOImpl dao= new IrrInfinitivosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }      

    @Bean
    public IrrParticipiosEnterosDAO irrParticipiosEnterosDAO(){
        IrrParticipiosEnterosDAOImpl dao= new IrrParticipiosEnterosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }  

    @Bean
    public IrrParticipiosSimplesDAO irrParticipiosSimplesDAO(){
        IrrParticipiosSimplesDAOImpl dao= new IrrParticipiosSimplesDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    } 
    
    @Bean
    public IrrSustantivosDAO irrSustantivosDAO(){
        IrrSustantivosDAOImpl dao= new IrrSustantivosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }    
    
    @Bean
    public IrrVerbosDAO irrVerbosDAO(){
        IrrVerbosDAOImpl dao= new IrrVerbosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    } 
    
    @Bean
    public IrrVerbosIndividualesDAO irrVerbosIndividualesDAO(){
        IrrVerbosIndividualesDAOImpl dao= new IrrVerbosIndividualesDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }   

    @Bean
    public ParticulasDAO particulasDAO(){
        ParticulasDAOImpl dao= new ParticulasDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    } 

    @Bean
    public PreposicionesDAO preposicionesDAO(){
        PreposicionesDAOImpl dao= new PreposicionesDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }
    
    @Bean
    public PreposicionesEnVerbosDAO preposicionesEnVerbosDAO(){
        PreposicionesEnVerbosDAOImpl dao= new PreposicionesEnVerbosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }   
    
 
    @Bean
    public SeguridadDAO seguridadDAO(){
        SeguridadDAOImpl dao= new SeguridadDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    } 


    @Bean
    public SignificadoDAO significadoDAO(){
        SignificadoDAOImpl dao= new SignificadoDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    } 
    
    
    @Bean
    public SubstractorPrefijosDAO substractorPrefijosDAO(){
        SubstractorPrefijosDAOImpl dao= new SubstractorPrefijosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }   
    
    @Bean
    public SustantivosDAO sustantivosDAO(){
        SustantivosDAOImpl dao= new SustantivosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }     


    @Bean
    public TemasTermRegNominalDAO temasTermRegNominalDAO(){
        TemasTermRegNominalDAOImpl dao= new TemasTermRegNominalDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }   

    @Bean
    public TermRegSustantivoDAO termRegSustantivoDAO(){
        TermRegSustantivoDAOImpl dao= new TermRegSustantivoDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    } 

    @Bean
    public TermRegVerboDAO termRegVerboDAO(){
        TermRegVerboDAOImpl dao= new TermRegVerboDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }
    
    @Bean
    public TermRegInfinitivoDAO termRegInfinitivoDAO(){
        TermRegInfinitivoDAOImpl dao= new TermRegInfinitivoDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }    


    @Bean
    public TransParticipiosDAO transParticipiosDAO(){
        TransParticipiosDAOImpl dao= new TransParticipiosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }

    @Bean
    public TiposAdjetivoDAO tiposAdjetivoDAO(){
        TiposAdjetivoDAOImpl dao= new TiposAdjetivoDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }

    @Bean
    public TiposSustantivoDAO tiposSustantivoDAO(){
        TiposSustantivoDAOImpl dao= new TiposSustantivoDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }

    @Bean
    public TiposVerboDAO tiposVerboDAO(){
        TiposVerboDAOImpl dao= new TiposVerboDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }

    @Bean
    public VerbalizadorParticipiosDAO verbalizadorParticipiosDAO(){
        VerbalizadorParticipiosDAOImpl dao= new VerbalizadorParticipiosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }
    
    @Bean
    public VerbosDAO verbosDAO(){
        VerbosDAOImpl dao= new VerbosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }
    
    @Bean
    public VerbosCompuestosDAO verbosCompuestosDAO(){
        VerbosCompuestosDAOImpl dao= new VerbosCompuestosDAOImpl();
        dao.setDataSource(kalosDataSource());
        return dao;
    }    



}
