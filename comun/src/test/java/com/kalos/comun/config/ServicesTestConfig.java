package com.kalos.comun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.kalos.datos.dao.TestAdjetivosDAO;
import com.kalos.datos.dao.TestInfinitivosDAO;
import com.kalos.datos.dao.TestParticipiosDAO;
import com.kalos.datos.dao.TestSustantivosDAO;
import com.kalos.datos.dao.TestVerbosDAO;
import com.kalos.datos.gerentes.GerenteTestAdjetivos;
import com.kalos.datos.gerentes.GerenteTestAdjetivosImpl;
import com.kalos.datos.gerentes.GerenteTestInfinitivos;
import com.kalos.datos.gerentes.GerenteTestInfinitivosImpl;
import com.kalos.datos.gerentes.GerenteTestParticipios;
import com.kalos.datos.gerentes.GerenteTestParticipiosImpl;
import com.kalos.datos.gerentes.GerenteTestSustantivos;
import com.kalos.datos.gerentes.GerenteTestSustantivosImpl;
import com.kalos.datos.gerentes.GerenteTestVerbos;
import com.kalos.datos.gerentes.GerenteTestVerbosImpl;

@Configuration
@Import(DaosTestConfig.class)
public class ServicesTestConfig {
	
	@Bean
	@Autowired
	public GerenteTestAdjetivos gerenteTestAdjetivos(TestAdjetivosDAO testAdjetivosDAO){
		GerenteTestAdjetivosImpl service = new GerenteTestAdjetivosImpl();
		service.setTestAdjetivosDAO(testAdjetivosDAO);
		return service;
	}


	@Bean
	@Autowired
	public GerenteTestInfinitivos gerenteTestInfinitivos(TestInfinitivosDAO testInfinitivosDAO){
		GerenteTestInfinitivosImpl service = new GerenteTestInfinitivosImpl();
		service.setTestInfinitivosDAO(testInfinitivosDAO);
		return service;
	}

	
	@Bean
	@Autowired
	public GerenteTestParticipios gerenteTestParticipios(TestParticipiosDAO testParticipiosDAO){
		GerenteTestParticipiosImpl service = new GerenteTestParticipiosImpl();
		service.setTestParticipiosDAO(testParticipiosDAO);
		return service;
	}
	

	@Bean
	@Autowired
	public GerenteTestSustantivos gerenteTestSustantivos(TestSustantivosDAO testSustantivosDAO){
		GerenteTestSustantivosImpl service = new GerenteTestSustantivosImpl();
		service.setTestSustantivosDAO(testSustantivosDAO);
		return service;
	}
	

	@Bean
	@Autowired
	public GerenteTestVerbos gerenteTestVerbos(TestVerbosDAO testVerbosDAO){
		GerenteTestVerbosImpl service = new GerenteTestVerbosImpl();
		service.setTestVerbosDAO(testVerbosDAO);
		return service;
	}
	
}
