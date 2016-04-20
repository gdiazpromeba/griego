
package com.kalos.mantenimiento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.AdverbioBean;
import com.kalos.beans.ConjuncionBean;
import com.kalos.beans.EncParticulaBean;
import com.kalos.beans.EntradaDiccionario;
import com.kalos.beans.InterjeccionBean;
import com.kalos.beans.ParticulaBean;
import com.kalos.beans.PreposicionBean;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.VerboBean;
import com.kalos.comun.config.DaoConfig;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteAdverbios;
import com.kalos.datos.gerentes.GerenteConjunciones;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.datos.gerentes.GerenteEncParticulas;
import com.kalos.datos.gerentes.GerenteInterjecciones;
import com.kalos.datos.gerentes.GerenteParticulas;
import com.kalos.datos.gerentes.GerentePreposiciones;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.util.DBUtil;
import com.kalos.enumeraciones.Beta;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;
import com.kalos.operaciones.comparadores.ComparadorBeansGriegos;
import com.kalos.utils.Listas;

public class CompactaTodo {

    private static Logger logger = Logger.getLogger(GeneraTermRegInfinitivo.class.getName());

    private static Connection con;

    private static ApplicationContext contexto;


    public static ApplicationContext creaContexto() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DaoConfig.class);
        context.refresh();
        return context;
    }

    public static void main(String[] args) throws Exception {
        contexto = creaContexto();
        DataSource kalosDataSource = (DataSource) contexto.getBean("mysqlDataSource");
        con = kalosDataSource.getConnection();
        
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT TABLE_NAME FROM SYS_JDBC.Tables where TABLE_TYPE='TABLE'  ");
        while(rs.next()){
            String tableName = rs.getString("TABLE_NAME");
            String sqlCompacta ="COMPACT TABLE " + tableName;
            Statement stmCompacta = con.createStatement();
            stmCompacta.executeQuery(sqlCompacta);
            stmCompacta.close();
        }
        stm.close();
        con.close();
    }



}
