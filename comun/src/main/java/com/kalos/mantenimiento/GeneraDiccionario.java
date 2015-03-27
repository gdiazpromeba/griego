package com.kalos.mantenimiento;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.sql.DataSource;

import com.kalos.beans.EntradaDiccionario;
import com.kalos.beans.SustantivoBean;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.Comparador;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;
import com.kalos.utils.Listas;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class GeneraDiccionario {

    private static Logger logger = Logger.getLogger(GeneraTermRegInfinitivo.class.getName());

    private static Connection con;

    private static ApplicationContext contexto;

    public static ApplicationContext creaContexto() {
	DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
	XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
	FileSystemResource res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "daos.xml");
	reader.loadBeanDefinitions(res);
	res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "gerentes-datos.xml");
	reader.loadBeanDefinitions(res);

	contexto = new GenericApplicationContext(factory);
	return contexto;
    }

    public static void main(String[] args) throws Exception {
	DOMConfigurator.configure("log4j.xml");
	contexto = creaContexto();
	DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
	con = kalosDataSource.getConnection();
	con.setAutoCommit(false);

	creaContexto();
	reconstruyeLetra("A");

    }

    private static void reconstruyeLetra(String letra) throws SQLException {
	Statement stm = con.createStatement();
	stm.executeUpdate("DELETE FROM DICCIONARIO WHERE LETRA='" + letra + "'");
	con.commit();

	GerenteDiccionario gerenteDiccionario = (GerenteDiccionario) contexto.getBean("gerenteDiccionario");

	GerenteSustantivos gerenteSustantivos = (GerenteSustantivos) contexto.getBean("gerenteSustantivos");
	List<String> idsSustantivo = gerenteSustantivos.seleccionaPorLetra(letra);
	Set<String> idsSinRepetir = new HashSet<>(idsSustantivo);
	idsSustantivo = new ArrayList<>(idsSinRepetir);
	
	List<List<String>> segmentos = Listas.segmentos(idsSustantivo, 10);
	
	int codigo = 50000000;
	for (List<String> list : segmentos) {

	    List<SustantivoBean> sustantivos = gerenteSustantivos.getBeans(list);


	    for (SustantivoBean bean : sustantivos) {
		EntradaDiccionario ent = new EntradaDiccionario();
		ent.setCodigo(codigo++);
		ent.setLetra(letra);
		String nominativoBeta = bean.getNominativo();
		String nominativo = OpPalabras.strBetaACompleto(nominativoBeta);
		String neutralizadaBeta = OpPalabras.neutralizaBeta(nominativoBeta);
		String nominativoAbreviado = OpPalabras.strAbreviaCompleta(nominativo);
		String nominativoBetaAbreviado = OpPalabras.strCompletoABeta(nominativoAbreviado);
		String nominativoUnicode = OpPalabras.strCompletoAUnicode(nominativo);
		ent.setNormalBeta(nominativoBeta);
		ent.setNeutralizadaBeta(neutralizadaBeta);
		ent.setSinLargasBeta(nominativoBetaAbreviado);
		ent.setUnicode(nominativoUnicode);
		ent.setReferenteId(bean.getId());
		System.out.println("RF=" + bean.getId() + " nominativo=" + nominativoBeta);
		ent.setTipoPalabra(TipoPalabra.Sustantivo);
		gerenteDiccionario.inserta(ent);
	    }

	}

    }

}
