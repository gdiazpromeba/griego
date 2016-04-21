
package com.kalos.mantenimiento;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.AdverbioBean;
import com.kalos.beans.VerboBean;
import com.kalos.comun.config.DaoConfig;
import com.kalos.datos.dao.AdjetivoDAO;
import com.kalos.datos.dao.AdjetivosComoNominalesDAO;
import com.kalos.datos.dao.AdverbiosDAO;
import com.kalos.datos.dao.VerbosDAO;
import com.kalos.enumeraciones.Beta;
import com.kalos.mantenimiento.config.MySqlDaoConfig;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class MigracionMysql {

    private static Logger logger = Logger.getLogger(GeneraTermRegInfinitivo.class.getName());

    private static Connection con;

    private static ApplicationContext contexto;


    public static ApplicationContext creaContexto() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DaoConfig.class);
        context.register(MySqlDaoConfig.class);
        context.refresh();
        return context;
    }

    public static void main(String[] args) throws Exception {
        contexto = creaContexto();
        //migraVerbos();
        //migraAdjetivos();
        migraAdverbios();
    }

    private static void migraVerbos(){

        VerbosDAO verbosDAO = (VerbosDAO) contexto.getBean("verbosDAO");
        VerbosDAO verbosDAOMySql = (VerbosDAO) contexto.getBean("verbosDAOMySql");

        for (char c : Beta.arrBeta) {
            List<String> ids = verbosDAO.seleccionaPorLetra(String.valueOf(c));
            logger.info("inserting letter=" + c);
            ids.forEach(id -> {
                VerboBean ben = verbosDAO.seleccionaInidvidual(id);
                logger.info(" inserting verb " + ben.getVerbo());
                verbosDAOMySql.inserta(ben);
            });
        }
    }

    private static void migraAdjetivos(){

        AdjetivoDAO dao = (AdjetivoDAO) contexto.getBean("adjetivoDAO");
        AdjetivoDAO daoMySql = (AdjetivoDAO) contexto.getBean("adjetivoDAOMySql");

        for (char c : Beta.arrBeta) {
            List<String> ids = dao.seleccionaPorLetra(String.valueOf(c));
            logger.info("inserting letter=" + c);
            ids.forEach(id -> {
                AdjetivoBean bean = dao.getInidvidual(id);
                if (bean.getCodigo()==-1) return;
                logger.info(" inserting adjective " + bean.getMasculino() + " id=" + id);
                daoMySql.inserta(bean);
            });
        }
    }

    private static void migraAdverbios(){

        AdverbiosDAO dao = (AdverbiosDAO) contexto.getBean("adverbiosDAO");
        AdverbiosDAO daoMySql = (AdverbiosDAO) contexto.getBean("adverbiosDAOMySql");

        for (char c : Beta.arrBeta) {
            List<String> ids = dao.seleccionaPorLetra(String.valueOf(c));
            logger.info("inserting letter=" + c);
            ids.forEach(id -> {
                AdverbioBean bean = dao.getInidvidual(id);
                if (bean.getCodigo()==-1) return;
                logger.info(" inserting adverb " + bean.getAdverbio() + " id=" + id);
                daoMySql.inserta(bean);
            });
        }
    }

    private static void migraAdjetivosComoNominales(){

        AdjetivosComoNominalesDAO dao = (AdjetivosComoNominalesDAO) contexto.getBean("adjetivosComoNominales");
        AdjetivosComoNominalesDAO daoMySql = (AdjetivosComoNominalesDAO) contexto.getBean("adjetivosComoNominalesMySql");

        for (char c : Beta.arrBeta) {
            List<String> ids = dao.seleccionaPorLetra(String.valueOf(c));
            logger.info("inserting letter=" + c);
            ids.forEach(id -> {
                AdverbioBean bean = dao.getInidvidual(id);
                if (bean.getCodigo()==-1) return;
                logger.info(" inserting adverb " + bean.getAdverbio() + " id=" + id);
                daoMySql.inserta(bean);
            });
        }
    }









}
