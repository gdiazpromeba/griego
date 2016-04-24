
package com.kalos.mantenimiento;

import com.kalos.beans.*;
import com.kalos.comun.config.DaoConfig;
import com.kalos.datos.dao.*;
import com.kalos.enumeraciones.Beta;
import com.kalos.mantenimiento.config.MySqlDaoConfig;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
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
        //migraAdverbios();
        //migraAdjetivosComoNominales();
        //migraCubosTipoPart();
        //mmigraDesinSust();
        //migraDesinInfinitivos();
        //migraDesinverbos();
        //migraEncParticulas();
        //migraInterjecciones();
        //migraIrrAdjetivosEnteros();
        //migraIrrAdjetivosIndividuales();
        //migraIrrInfinitivos();
        //migraIrrParticipiosEnteros();
        //migraIrrParticipiosSimples();
        //migraIrrSustantivos();
        //migraIrrVerbos();
        //migraIrrVerbosIndividuales();
        //migraParticulas();
        //migraPreposiciones();
        //migraPreposicionesEnVerbos();
        //migraSignificados();
        //migraVerbosSimplesCompuestos();
        //migraVerbalizadorParticipios();
        //migraSubstractorPrefijos();
        //migraTipoVerbos();
        //migraTiposAdjetivo();
        migraTiposSustantivo();

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

        AdjetivosComoNominalesDAO dao = (AdjetivosComoNominalesDAO) contexto.getBean("adjetivosComoNominalesDAO");
        AdjetivosComoNominalesDAO daoMySql = (AdjetivosComoNominalesDAO) contexto.getBean("adjetivosComoNominalesDAOMySql");
        daoMySql.setAutocommit(false);
        int i=0;

            List<AdjetivoComoNominalBean> beans = dao.seleccionaTodos();
            beans.forEach(bean -> {
                logger.info(" inserting ACN id adj=" + bean.getIdAdjetivo() );
                daoMySql.inserta(bean);
            });
            if (i++ % 100 == 0) daoMySql.commit();

    }

    private static void migraCubosTipoPart(){

        CubosTipoPartDAO dao = (CubosTipoPartDAO) contexto.getBean("cubosTipoPartDAO");
        CubosTipoPartDAO daoMySql = (CubosTipoPartDAO) contexto.getBean("cubosTipoPartDAOMySql");
        daoMySql.setAutocommit(false);
        int i=0;

            List<CubosTipoPartBean> beans = dao.seleccionaTodos();
            beans.forEach(bean -> {
                logger.info(" inserting CTP nom=" + bean.getNominativo() );
                daoMySql.inserta(bean);
            });
            if (i++ % 100 == 0) daoMySql.commit();

    }

    private static void mmigraDesinSust(){

        DesinSustDAO dao = (DesinSustDAO) contexto.getBean("desinSustDAO");
        DesinSustDAO daoMySql = (DesinSustDAO) contexto.getBean("desinSustDAOMySql");
        daoMySql.setAutocommit(false);

        List<DesinSust> beans = dao.seleccionaTodos();
        beans.forEach(bean -> {
            logger.info(" inserting Desin sust id=" + bean.getId() );
            daoMySql.inserta(bean);
        });
        daoMySql.commit();

    }

    private static void migraDesinInfinitivos(){

        DesinInfinitivosDAO dao = (DesinInfinitivosDAO) contexto.getBean("desinInfinitivosDAO");
        DesinInfinitivosDAO daoMySql = (DesinInfinitivosDAO) contexto.getBean("desinInfinitivosDAOMySql");


        List<DesinInfinitivo> beans = dao.seleccionaTodas();
        beans.forEach(bean -> {
            logger.info(" inserting Desin sust id=" + bean.getDesinencia() );
            daoMySql.inserta(bean);
        });


    }

    private static void migraDesinverbos(){

        DesinVerbosDAO dao = (DesinVerbosDAO) contexto.getBean("desinVerbosDAO");
        DesinVerbosDAO daoMySql = (DesinVerbosDAO) contexto.getBean("desinVerbosDAOMySql");


        List<DesinVerbo> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting Desin vebos id=" + bean.getDesinencia() );
            daoMySql.inserta(bean);
        });


    }

    private static void migraEncParticulas(){

        EncParticulasDAO dao = (EncParticulasDAO) contexto.getBean("encParticulasDAO");
        EncParticulasDAO daoMySql = (EncParticulasDAO) contexto.getBean("encParticulasDAOMySql");


        List<EncParticulaBean> beans = dao.seleccionaEncParticulasTodos();
        beans.forEach(bean -> {
            logger.info(" inserting particula=" + bean.getForma() );
            daoMySql.inserta(bean);
        });


    }

    private static void migraInterjecciones(){

        InterjeccionesDAO dao = (InterjeccionesDAO) contexto.getBean("interjeccionesDAO");
        InterjeccionesDAO daoMySql = (InterjeccionesDAO) contexto.getBean("interjeccionesDAOMySql");


        List<InterjeccionBean> beans = dao.seleccionaTodos();
        beans.forEach(bean -> {
            logger.info(" inserting particula=" + bean.getInterjeccion() );
            daoMySql.inserta(bean);
        });


    }

    private static void migraIrrAdjetivosEnteros(){

        IrrAdjetivosEnterosDAO dao = (IrrAdjetivosEnterosDAO) contexto.getBean("irrAdjetivosEnterosDAO");
        IrrAdjetivosEnterosDAO daoMySql = (IrrAdjetivosEnterosDAO) contexto.getBean("irrAdjetivosEnterosDAOMySql");


        List<IrrAdjetivoEntero> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IAE=" + bean.getId() );
            daoMySql.inserta(bean);
        });


    }

    private static void migraIrrAdjetivosIndividuales(){

        IrrAdjetivosIndividualesDAO dao = (IrrAdjetivosIndividualesDAO) contexto.getBean("irrAdjetivosIndividualesDAO");
        IrrAdjetivosIndividualesDAO daoMySql = (IrrAdjetivosIndividualesDAO) contexto.getBean("irrAdjetivosIndividualesDAOMySql");


        List<IrrAdjetivoIndividual> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IAI=" + bean.getId() );
            daoMySql.inserta(bean);
        });


    }

    private static void migraIrrInfinitivos(){
        IrrInfinitivosDAO dao = (IrrInfinitivosDAO) contexto.getBean("irrInfinitivosDAO");
        IrrInfinitivosDAO daoMySql = (IrrInfinitivosDAO) contexto.getBean("irrInfinitivosDAOMySql");

        List<IrrInfinitivoBean> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IAI=" + bean.getId() );
            daoMySql.inserta(bean);
        });

    }

    private static void migraIrrParticipiosEnteros(){
        IrrParticipiosEnterosDAO dao = (IrrParticipiosEnterosDAO) contexto.getBean("irrParticipiosEnterosDAO");
        IrrParticipiosEnterosDAO daoMySql = (IrrParticipiosEnterosDAO) contexto.getBean("irrParticipiosEnterosDAOMySql");

        List<IrrParticipioEntero> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IPE=" + bean.getId() );
            daoMySql.inserta(bean);
        });

    }

    private static void migraIrrParticipiosSimples(){
        IrrParticipiosSimplesDAO dao = (IrrParticipiosSimplesDAO) contexto.getBean("irrParticipiosSimplesDAO");
        IrrParticipiosSimplesDAO daoMySql = (IrrParticipiosSimplesDAO) contexto.getBean("irrParticipiosSimplesDAOMySql");

        List<IrrParticipioSimpleBean> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IPS=" + bean.getId() );
            daoMySql.inserta(bean);
        });

    }

    private static void migraIrrSustantivos(){
        IrrSustantivosDAO dao = (IrrSustantivosDAO) contexto.getBean("irrSustantivosDAO");
        IrrSustantivosDAO daoMySql = (IrrSustantivosDAO) contexto.getBean("irrSustantivosDAOMySql");

        List<IrrSustantivoBean> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IrrSust=" + bean.getId() );
            daoMySql.inserta(bean);
        });

    }

    private static void migraIrrVerbos(){
        IrrVerbosDAO dao = (IrrVerbosDAO) contexto.getBean("irrVerbosDAO");
        IrrVerbosDAO daoMySql = (IrrVerbosDAO) contexto.getBean("irrVerbosDAOMySql");

        List<IrrVerbo> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IrrVerbos=" + bean.getId() );
            daoMySql.inserta(bean);
        });
    }

    private static void migraIrrVerbosIndividuales(){
        IrrVerbosIndividualesDAO dao = (IrrVerbosIndividualesDAO) contexto.getBean("irrVerbosIndividualesDAO");
        IrrVerbosIndividualesDAO daoMySql = (IrrVerbosIndividualesDAO) contexto.getBean("irrVerbosIndividualesDAOMySql");

        List<IrrVerboIndividual> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting IrrVerbosIndividuales=" + bean.getId() );
            daoMySql.inserta(bean);
        });
    }

    private static void migraParticulas(){
        ParticulasDAO dao = (ParticulasDAO) contexto.getBean("particulasDAO");
        ParticulasDAO daoMySql = (ParticulasDAO) contexto.getBean("particulasDAOMySql");

        List<ParticulaBean> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting id=" + bean.getId() );
            daoMySql.inserta(bean);
        });
    }

    private static void migraPreposiciones(){
        PreposicionesDAO dao = (PreposicionesDAO) contexto.getBean("preposicionesDAO");
        PreposicionesDAO daoMySql = (PreposicionesDAO) contexto.getBean("preposicionesDAOMySql");

        List<PreposicionBean> beans = dao.getTodas();
        beans.forEach(bean -> {
            logger.info(" inserting id=" + bean.getId() );
            daoMySql.inserta(bean);
        });
    }

    private static void migraPreposicionesEnVerbos(){
        PreposicionesEnVerbosDAO dao = (PreposicionesEnVerbosDAO) contexto.getBean("preposicionesEnVerbosDAO");
        PreposicionesEnVerbosDAO daoMySql = (PreposicionesEnVerbosDAO) contexto.getBean("preposicionesEnVerbosDAOMySql");

        List<PreposicionEnVerbo> beans = dao.seleccionaTodo();
        beans.forEach(bean -> {
            logger.info(" inserting id=" + bean.getVerboId() );
            daoMySql.inserta(bean);
        });
    }

    private static void migraSignificados(){
        SignificadoDAO dao = (SignificadoDAO) contexto.getBean("significadoDAO");
        SignificadoDAO daoMySql = (SignificadoDAO) contexto.getBean("significadoDAOMySql");
        //daoMySql.setAutocommit(false);

        List<Significado> beans = dao.seleccionaTodo();
        for (int i=0; i<beans.size(); i++){
            //if (i%100 ==0) daoMySql.commit();
            Significado bean = beans.get(i);
            logger.info(" inserting id=" + bean.getId() );
            daoMySql.inserta(bean);
        };
        //daoMySql.commit();
    }

    private static void migraVerbosSimplesCompuestos(){
        VerbosCompuestosDAO dao = (VerbosCompuestosDAO) contexto.getBean("verbosCompuestosDAO");
        VerbosCompuestosDAO daoMySql = (VerbosCompuestosDAO) contexto.getBean("verbosCompuestosDAOMySql");

        List<VerboSimpleCompuesto> beans = dao.seleccionaTodo();
        for (int i=0; i<beans.size(); i++){
            VerboSimpleCompuesto bean = beans.get(i);
            logger.info(" inserting id=" + bean.getIdVerboCompuesto() );
            daoMySql.inserta(bean);
        };
    }

    private static void migraVerbalizadorParticipios(){
        VerbalizadorParticipiosDAO dao = (VerbalizadorParticipiosDAO) contexto.getBean("verbalizadorParticipiosDAO");
        VerbalizadorParticipiosDAO daoMySql = (VerbalizadorParticipiosDAO) contexto.getBean("verbalizadorParticipiosDAOMySql");

        List<VerbalizadorBean> beans = dao.seleccionaTodo();
        for (int i=0; i<beans.size(); i++){
            VerbalizadorBean bean = beans.get(i);
            logger.info(" inserting id=" + bean.getTerminacionNominativo());
            daoMySql.inserta(bean);
        };
    }


    private static void migraSubstractorPrefijos(){
        SubstractorPrefijosDAO dao = (SubstractorPrefijosDAO) contexto.getBean("substractorPrefijosDAO");
        SubstractorPrefijosDAO daoMySql = (SubstractorPrefijosDAO) contexto.getBean("substractorPrefijosDAOMySql");

        List<SubstractorPrefijosBean> beans = dao.seleccionaTodo();
        for (int i=0; i<beans.size(); i++){
            SubstractorPrefijosBean bean = beans.get(i);
            logger.info(" inserting id=" + bean.getPrefijo());
            daoMySql.inserta(bean);
        };
    }

    private static void migraTipoVerbos(){
        TiposVerboDAO dao = (TiposVerboDAO) contexto.getBean("tiposVerboDAO");
        TiposVerboDAO daoMySql = (TiposVerboDAO) contexto.getBean("tiposVerboDAOMySql");

        List<TipoJerarquico> beans = dao.seleccionaTodo();
        for (int i=0; i<beans.size(); i++){
            TipoJerarquico bean = beans.get(i);
            logger.info(" inserting id=" + bean.getId());
            daoMySql.inserta(bean);
        };
    }

    private static void migraTiposAdjetivo(){
        TiposAdjetivoDAO dao = (TiposAdjetivoDAO) contexto.getBean("tiposAdjetivoDAO");
        TiposAdjetivoDAO daoMySql = (TiposAdjetivoDAO) contexto.getBean("tiposAdjetivoDAOMySql");

        List<TipoJerarquico> beans = dao.seleccionaTodo();
        for (int i=0; i<beans.size(); i++){
            TipoJerarquico bean = beans.get(i);
            logger.info(" inserting id=" + bean.getId());
            daoMySql.inserta(bean);
        };
    }

    private static void migraTiposSustantivo(){
        TiposSustantivoDAO dao = (TiposSustantivoDAO) contexto.getBean("tiposSustantivoDAO");
        TiposSustantivoDAO daoMySql = (TiposSustantivoDAO) contexto.getBean("tiposSustantivoDAOMySql");

        List<TipoSustantivo> beans = dao.seleccionaTodo();
        for (int i=0; i<beans.size(); i++){
            TipoSustantivo bean = beans.get(i);
            logger.info(" inserting id=" + bean.getId());
            daoMySql.inserta(bean);
        };
    }






}
