package com.kalos.mantenimiento.config;

import org.apache.commons.dbcp.BasicDataSource;
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
import com.kalos.recursos.Configuracion;


@Configuration
public class MySqlDaoConfig {
    
    //private static Logger logger = Logger.getLogger(DaoConfig.class);
    


    @Bean 
	public BasicDataSource mysqlDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/kalos");
        dataSource.setUsername("root");
        dataSource.setPassword("");
//        String databasePath = Configuracion.getProperty("database_path");
//        if (databasePath !=null){
//          dataSource.addConnectionProperty("database_path", Configuracion.getProperty("database_path"));
//        }
    	return dataSource;
	}


	@Bean
	public AdjetivoDAO adjetivoDAOMySql(){
		AdjetivoDAOImpl dao= new AdjetivoDAOImpl();
		dao.setDataSource(mysqlDataSource());
		return dao;
	}
	

	@Bean (name = "adverbiosDAOMySql")
	public AdverbiosDAO adverbiosDAOMySql(){
		AdverbiosDAOImpl dao= new AdverbiosDAOImpl();
		dao.setDataSource(mysqlDataSource());
		return dao;
	}


	
	@Bean (name = "adjetivosComoNominalesDAOMySql")
	public AdjetivosComoNominalesDAO adjetivosComoNominalesDAOMySql(){
		AdjetivosComoNominalesDAOImpl dao= new AdjetivosComoNominalesDAOImpl();
		dao.setDataSource(mysqlDataSource());
		return dao;
	}

	/*
    @Bean
    public CombosDAO combosDAOMySql(){
        CombosDAOImpl dao= new CombosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public ConjuncionesDAO conjuncionesDAOMySql(){
        ConjuncionesDAOImpl dao= new ConjuncionesDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    */

    @Bean
    public CubosTipoPartDAO cubosTipoPartDAOMySql(){
        CubosTipoPartDAOImpl dao= new CubosTipoPartDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }


	/*
    @Bean
    public DesinSustDAO desinSustDAOMySql(){
        DesinSustDAOImpl dao= new DesinSustDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public DesinInfinitivosDAO desinInfinitivosDAOMySql(){
        DesinInfintivosDAOImpl dao= new DesinInfintivosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public DesinVerbosDAO desinVerbosDAOMySql(){
        DesinVerbosDAOImpl dao= new DesinVerbosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public DiccionarioDAO diccionarioDAOMySql(){
        DiccionarioDAOImpl dao= new DiccionarioDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }
    
    @Bean
    public EncParticulasDAO encParticulasDAOMySql(){
        EncParticulasDAOImpl dao= new EncParticulasDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }   


    @Bean
    public InterjeccionesDAO interjeccionesDAOMySql(){
        InterjeccionesDAOImpl dao= new InterjeccionesDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }  
    
    @Bean
    public IrrAdjetivosEnterosDAO irrAdjetivosEnterosDAOMySql(){
        IrrAdjetivosEnterosDAOImpl dao= new IrrAdjetivosEnterosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }     
    
    @Bean
    public IrrAdjetivosIndividualesDAO irrAdjetivosIndividualesDAOMySql(){
        IrrAdjetivosIndividualesDAOImpl dao= new IrrAdjetivosIndividualesDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }  
    

    @Bean
    public IrrInfinitivosDAO irrInfinitivosDAOMySql(){
        IrrInfinitivosDAOImpl dao= new IrrInfinitivosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }      

    @Bean
    public IrrParticipiosEnterosDAO irrParticipiosEnterosDAOMySql(){
        IrrParticipiosEnterosDAOImpl dao= new IrrParticipiosEnterosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }  

    @Bean
    public IrrParticipiosSimplesDAO irrParticipiosSimplesDAO(){
        IrrParticipiosSimplesDAOImpl dao= new IrrParticipiosSimplesDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    } 
    
    @Bean
    public IrrSustantivosDAO irrSustantivosDAOMysql(){
        IrrSustantivosDAOImpl dao= new IrrSustantivosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }    
    
    @Bean
    public IrrVerbosDAO irrVerbosDAOMysql(){
        IrrVerbosDAOImpl dao= new IrrVerbosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    } 
    
    @Bean
    public IrrVerbosIndividualesDAO irrVerbosIndividualesDAOMysql(){
        IrrVerbosIndividualesDAOImpl dao= new IrrVerbosIndividualesDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }   

    @Bean
    public ParticulasDAO particulasDAOMysql(){
        ParticulasDAOImpl dao= new ParticulasDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    } 

    @Bean
    public PreposicionesDAO preposicionesDAOMysql(){
        PreposicionesDAOImpl dao= new PreposicionesDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }
    
    @Bean
    public PreposicionesEnVerbosDAO preposicionesEnVerbosDAOMysql(){
        PreposicionesEnVerbosDAOImpl dao= new PreposicionesEnVerbosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }   
    



    @Bean
    public SignificadoDAO significadoDAOMysql(){
        SignificadoDAOImpl dao= new SignificadoDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    } 
    
    
    @Bean
    public SubstractorPrefijosDAO substractorPrefijosDAOMysql(){
        SubstractorPrefijosDAOImpl dao= new SubstractorPrefijosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }   
    
    @Bean
    public SustantivosDAO sustantivosDAOMysql(){
        SustantivosDAOImpl dao= new SustantivosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }     


    @Bean
    public TemasTermRegNominalDAO temasTermRegNominalDAOMysql(){
        TemasTermRegNominalDAOImpl dao= new TemasTermRegNominalDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }   

    @Bean
    public TermRegSustantivoDAO termRegSustantivoDAOMysql(){
        TermRegSustantivoDAOImpl dao= new TermRegSustantivoDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    } 

    @Bean
    public TermRegVerboDAO termRegVerboDAOMysql(){
        TermRegVerboDAOImpl dao= new TermRegVerboDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }
    
    @Bean
    public TermRegInfinitivoDAO termRegInfinitivoDAOMysql(){
        TermRegInfinitivoDAOImpl dao= new TermRegInfinitivoDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }    


    @Bean
    public TransParticipiosDAO transParticipiosDAOMysql(){
        TransParticipiosDAOImpl dao= new TransParticipiosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public TiposAdjetivoDAO tiposAdjetivoDAOMysql(){
        TiposAdjetivoDAOImpl dao= new TiposAdjetivoDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public TiposSustantivoDAO tiposSustantivoDAOMysql(){
        TiposSustantivoDAOImpl dao= new TiposSustantivoDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public TiposVerboDAO tiposVerboDAOMysql(){
        TiposVerboDAOImpl dao= new TiposVerboDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }

    @Bean
    public VerbalizadorParticipiosDAO verbalizadorParticipiosDAOMysql(){
        VerbalizadorParticipiosDAOImpl dao= new VerbalizadorParticipiosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }
    */
    @Bean (name = "verbosDAOMySql")
    public VerbosDAO verbosDAOMysql(){
        VerbosDAOImpl dao= new VerbosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }
    /*
    @Bean
    public VerbosCompuestosDAO verbosCompuestosDAOMysql(){
        VerbosCompuestosDAOImpl dao= new VerbosCompuestosDAOImpl();
        dao.setDataSource(mysqlDataSource());
        return dao;
    }
       */



}
