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
	
	

}
