package com.kalos.comun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kalos.datos.dao.TestAdjetivosDAO;
import com.kalos.datos.dao.TestAdjetivosDAOImpl;
import com.kalos.datos.dao.TestInfinitivosDAO;
import com.kalos.datos.dao.TestInfinitivosDAOImpl;
import com.kalos.datos.dao.TestParticipiosDAO;
import com.kalos.datos.dao.TestParticipiosDAOImpl;
import com.kalos.datos.dao.TestSustantivosDAO;
import com.kalos.datos.dao.TestSustantivosDAOImpl;
import com.kalos.datos.dao.TestVerbosDAO;
import com.kalos.datos.dao.TestVerbosDAOImpl;
import com.kalos.datos.modulos.FuenteDeDatosKalos;

@Configuration
public class DaosTestConfig {

	@Bean
	@Autowired
	public TestAdjetivosDAO testAdjetivosDAO(
			FuenteDeDatosKalos fuenteDeDatosKalos) {
		TestAdjetivosDAOImpl dao = new TestAdjetivosDAOImpl();
		dao.setDataSource(fuenteDeDatosKalos);
		return dao;
	}

	@Bean
	@Autowired
	public TestInfinitivosDAO testInfinitivosDAO(
			FuenteDeDatosKalos fuenteDeDatosKalos) {
		TestInfinitivosDAOImpl dao = new TestInfinitivosDAOImpl();
		dao.setDataSource(fuenteDeDatosKalos);
		return dao;
	}

	@Bean
	@Autowired
	public TestParticipiosDAO testParticipiosDAO(
			FuenteDeDatosKalos fuenteDeDatosKalos) {
		TestParticipiosDAOImpl dao = new TestParticipiosDAOImpl();
		dao.setDataSource(fuenteDeDatosKalos);
		return dao;
	}

	@Bean
	@Autowired
	public TestSustantivosDAO testSustantivosDAO(
			FuenteDeDatosKalos fuenteDeDatosKalos) {
		TestSustantivosDAOImpl dao = new TestSustantivosDAOImpl();
		dao.setDataSource(fuenteDeDatosKalos);
		return dao;
	}

	@Bean
	@Autowired
	public TestVerbosDAO testVerbosDAO(FuenteDeDatosKalos fuenteDeDatosKalos) {
		TestVerbosDAOImpl dao = new TestVerbosDAOImpl();
		dao.setDataSource(fuenteDeDatosKalos);
		return dao;
	}
}
