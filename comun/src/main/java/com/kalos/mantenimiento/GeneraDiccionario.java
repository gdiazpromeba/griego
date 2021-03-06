
package com.kalos.mantenimiento;

import java.sql.Connection;
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
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.AdverbioBean;
import com.kalos.beans.ConjuncionBean;
import com.kalos.beans.EntradaDiccionario;
import com.kalos.beans.InterjeccionBean;
import com.kalos.beans.ParticulaBean;
import com.kalos.beans.PreposicionBean;
import com.kalos.beans.SustantivoBean;
import com.kalos.beans.VerboBean;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteAdverbios;
import com.kalos.datos.gerentes.GerenteConjunciones;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.datos.gerentes.GerenteInterjecciones;
import com.kalos.datos.gerentes.GerenteParticulas;
import com.kalos.datos.gerentes.GerentePreposiciones;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.enumeraciones.Beta;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;
import com.kalos.operaciones.comparadores.ComparadorBeansGriegos;
import com.kalos.utils.Listas;

public class GeneraDiccionario {

    private static Logger logger = Logger.getLogger(GeneraTermRegInfinitivo.class.getName());

    private static Connection con;

    private static ApplicationContext contexto;

    private static GerenteDiccionario gerenteDiccionario;

    public static ApplicationContext creaContexto() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource res = new ClassPathResource("daos.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("gerentes-datos.xml");
        reader.loadBeanDefinitions(res);

        contexto = new GenericApplicationContext(factory);
        return contexto;
    }

    public static void main(String[] args) throws Exception {
        contexto = creaContexto();
        DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
        con = kalosDataSource.getConnection();
        con.setAutoCommit(false);

        creaContexto();
        gerenteDiccionario = (GerenteDiccionario) contexto.getBean("gerenteDiccionario");
        
        Statement stm = con.createStatement();
        stm.executeUpdate("DELETE FROM DICCIONARIO ");
        con.commit();       

        for (char carLetra : Beta.arrBeta) {
            String letra = String.valueOf(carLetra);



            construyeSustantivos(letra);
            construyeAdverbios(letra);
            construyeAdjetivos(letra);
            construyeVerbos(letra);
            construyeInterjeciones(letra);
        }

        construyeParticulas();
        construyePreposiciones();
        construyeConjunciones();
        
        for (char carLetra : Beta.arrBeta) {
          String letra = String.valueOf(carLetra);       
          System.out.println("ordenando " + letra);
          ordenaLetra(letra);
        }

    }

    private static void construyeSustantivos(String letra) throws SQLException {

        GerenteSustantivos gerenteSustantivos = (GerenteSustantivos) contexto.getBean("gerenteSustantivos");
        List<String> idsSustantivo = gerenteSustantivos.seleccionaPorLetra(letra);

        List<List<String>> segmentos = Listas.segmentos(idsSustantivo, 10);

        int codigo = 50000000;
        for (List<String> list : segmentos) {

            List<SustantivoBean> sustantivos = gerenteSustantivos.getBeans(list);

            for (SustantivoBean bean : sustantivos) {
                EntradaDiccionario ent = new EntradaDiccionario();
                ent.setCodigo(codigo++);
                ent.setLetra(letra);
                String formaBeta = bean.getNominativo();
                poneFormasEnBeanDiccionario(formaBeta, ent);
                ent.setReferenteId(bean.getId());
                System.out.println("RF=" + bean.getId() + " nominativo=" + ent.getUnicode());
                ent.setTipoPalabra(TipoPalabra.Sustantivo);
                gerenteDiccionario.inserta(ent);
            }

        }

    }

    private static void construyeAdverbios(String letra) throws SQLException {

        GerenteAdverbios gerente = (GerenteAdverbios) contexto.getBean("gerenteAdverbios");
        List<String> ids = gerente.seleccionaPorLetra(letra);

        List<List<String>> segmentos = Listas.segmentos(ids, 10);

        int codigo = 10;
        for (List<String> list : segmentos) {

            List<AdverbioBean> beans = gerente.getBeans(list);

            for (AdverbioBean bean : beans) {
                EntradaDiccionario ent = new EntradaDiccionario();
                codigo += 10;
                ent.setCodigo(codigo);
                ent.setLetra(letra);
                String formaBeta = bean.getAdverbio();
                poneFormasEnBeanDiccionario(formaBeta, ent);
                ent.setReferenteId(bean.getId());
                System.out.println("RF=" + bean.getId() + " forma=" + ent.getUnicode());
                ent.setTipoPalabra(TipoPalabra.Adverbio);
                gerenteDiccionario.inserta(ent);
            }

        }

    }

    private static void construyeAdjetivos(String letra) throws SQLException {

        GerenteAdjetivos gerente = (GerenteAdjetivos) contexto.getBean("gerenteAdjetivos");
        List<String> ids = gerente.seleccionaPorLetra(letra);

        List<List<String>> segmentos = Listas.segmentos(ids, 10);

        int codigo = 10;
        for (List<String> list : segmentos) {

            List<AdjetivoBean> beans = gerente.getBeans(list);

            for (AdjetivoBean bean : beans) {
                EntradaDiccionario ent = new EntradaDiccionario();
                codigo += 10;
                ent.setCodigo(codigo);
                ent.setLetra(letra);
                String formaBeta = null;
                if (bean.getMasculino() != null)
                    formaBeta = bean.getMasculino();
                else if (bean.getMascFem() != null)
                    formaBeta = bean.getMascFem();
                else if (bean.getFemenino() != null)
                    formaBeta = bean.getFemenino();
                else if (bean.getNeutro() != null) formaBeta = bean.getNeutro();
                poneFormasEnBeanDiccionario(formaBeta, ent);
                ent.setReferenteId(bean.getId());
                System.out.println("RF=" + bean.getId() + " forma=" + ent.getUnicode());
                ent.setTipoPalabra(TipoPalabra.Adjetivo);
                gerenteDiccionario.inserta(ent);
            }

        }

    }

    /**
     * dada la forma beta inicial, carga el resto de las strings relacionadas en el bean de EntradaDiccionario
     * @param formaBeta
     * @param ent
     */
    private static void poneFormasEnBeanDiccionario(String formaBeta, EntradaDiccionario ent) {
        String formaCompleta = OpPalabras.strBetaACompleto(formaBeta);
        String neutralizadaBeta = OpPalabras.neutralizaBeta(formaBeta);
        String formaAbreviada = OpPalabras.strAbreviaCompleta(formaCompleta);
        String formaBetaAbreviado = OpPalabras.strCompletoABeta(formaAbreviada);
        String formaUnicode = OpPalabras.strCompletoAUnicode(formaCompleta);
        ent.setNormalBeta(formaBeta);
        ent.setNeutralizadaBeta(neutralizadaBeta);
        ent.setSinLargasBeta(formaBetaAbreviado);
        ent.setUnicode(formaUnicode);
    }

    private static void construyeVerbos(String letra) throws SQLException {

        GerenteVerbos gerente = (GerenteVerbos) contexto.getBean("gerenteVerbos");
        List<String> ids = gerente.seleccionaPorLetra(letra);

        List<List<String>> segmentos = Listas.segmentos(ids, 10);

        int codigo = 10;
        for (List<String> list : segmentos) {

            List<VerboBean> beans = gerente.getBeans(list);

            for (VerboBean bean : beans) {
                EntradaDiccionario ent = new EntradaDiccionario();
                codigo += 10;
                ent.setCodigo(codigo);
                ent.setLetra(letra);
                String formaBeta = bean.getVerbo();
                poneFormasEnBeanDiccionario(formaBeta, ent);
                ent.setReferenteId(bean.getId());
                System.out.println("RF=" + bean.getId() + " forma=" + ent.getUnicode());
                ent.setTipoPalabra(TipoPalabra.Verbo);
                gerenteDiccionario.inserta(ent);
            }

        }

    }

    private static void construyeInterjeciones(String letra) throws SQLException {

        GerenteInterjecciones gerente = (GerenteInterjecciones) contexto.getBean("gerenteInterjecciones");
        List<String> ids = gerente.seleccionaPorLetra(letra);

        List<List<String>> segmentos = Listas.segmentos(ids, 10);

        int codigo = 10;
        for (List<String> list : segmentos) {

            List<InterjeccionBean> beans = gerente.getBeans(list);

            for (InterjeccionBean bean : beans) {
                EntradaDiccionario ent = new EntradaDiccionario();
                codigo += 10;
                ent.setCodigo(codigo);
                ent.setLetra(letra);
                String formaBeta = bean.getInterjeccion();
                poneFormasEnBeanDiccionario(formaBeta, ent);
                ent.setReferenteId(bean.getId());
                System.out.println("RF=" + bean.getId() + " forma=" + ent.getUnicode());
                ent.setTipoPalabra(TipoPalabra.Interjeccion);
                gerenteDiccionario.inserta(ent);
            }

        }

    }

    private static void construyeParticulas() throws SQLException {

        GerenteParticulas gerente = (GerenteParticulas) contexto.getBean("gerenteParticulas");
        TipoPalabra[] tips = new TipoPalabra[] { TipoPalabra.Articulo, TipoPalabra.PronombrePersonal, TipoPalabra.PronombreReflexivo, TipoPalabra.PronombreRelativo, TipoPalabra.PronombreInterrogativo, TipoPalabra.PronombreIndefinido };

        int codigo = 10;
        for (TipoPalabra tipoPalabra : tips) {
            List<String> ids = gerente.seleccionaIdsPorTipo(tipoPalabra);
            System.out.println("tipo palabra = " + tipoPalabra);

            List<List<String>> segmentos = Listas.segmentos(ids, 10);

            for (List<String> list : segmentos) {

                List<ParticulaBean> beans = gerente.getBeans(list);

                for (ParticulaBean bean : beans) {
                    EntradaDiccionario ent = new EntradaDiccionario();
                    codigo += 10;
                    ent.setCodigo(codigo);
                    ent.setLetra(bean.getForma().substring(0, 1));
                    String formaBeta = bean.getForma();
                    poneFormasEnBeanDiccionario(formaBeta, ent);
                    ent.setReferenteId(bean.getId());
                    System.out.println("RF=" + bean.getId() + " forma=" + ent.getUnicode());
                    ent.setTipoPalabra(bean.getParticulaTipo());
                    gerenteDiccionario.inserta(ent);
                }

            }

        }
    }

    private static void construyePreposiciones() throws SQLException {

        GerentePreposiciones gerente = (GerentePreposiciones) contexto.getBean("gerentePreposiciones");

        int codigo = 10;

        List<String> ids = gerente.seleccionaTodosLosIds();

        List<List<String>> segmentos = Listas.segmentos(ids, 10);

        for (List<String> list : segmentos) {

            List<PreposicionBean> beans = gerente.getBeans(list);

            for (PreposicionBean bean : beans) {
                EntradaDiccionario ent = new EntradaDiccionario();
                codigo += 10;
                ent.setCodigo(codigo);
                ent.setLetra(bean.getFormaDiccionario().substring(0, 1));
                String formaBeta = bean.getFormaDiccionario();
                poneFormasEnBeanDiccionario(formaBeta, ent);
                ent.setReferenteId(bean.getId());
                System.out.println("RF=" + bean.getId() + " forma=" + ent.getUnicode());
                ent.setTipoPalabra(TipoPalabra.Preposicion);
                gerenteDiccionario.inserta(ent);
            }

        }

    }

    private static void construyeConjunciones() throws SQLException {

        GerenteConjunciones gerente = (GerenteConjunciones) contexto.getBean("gerenteConjunciones");

        int codigo = 10;

        List<ConjuncionBean> beans = gerente.seleccionaTodos();

        for (ConjuncionBean bean : beans) {
            EntradaDiccionario ent = new EntradaDiccionario();
            codigo += 10;
            ent.setCodigo(codigo);
            ent.setLetra(bean.getForma().substring(0, 1));
            String formaBeta = bean.getForma();
            poneFormasEnBeanDiccionario(formaBeta, ent);
            ent.setReferenteId(bean.getId());
            System.out.println("RF=" + bean.getId() + " forma=" + ent.getUnicode());
            ent.setTipoPalabra(TipoPalabra.Conjuncion);
            gerenteDiccionario.inserta(ent);
        }

    }
    
    /**
     * ordena 1 letra (para todas las formas, todos los tipos de palabra) del diccionario
     * @param letra
     */
    public static void ordenaLetra(String letra) {
        List<String> ids = gerenteDiccionario.seleccionaPorLetra(letra);
        List<EntradaDiccionario> beans = gerenteDiccionario.getRegistros(ids);
        String[] propiedades = { "normalBeta" };
        OpBeans.pasaDeBetaACompleto(beans, propiedades);
        Collections.sort(beans, new ComparadorBeansGriegos(propiedades));
        OpBeans.pasaDeCompletoABeta(beans, propiedades);
        int codigo = 10;
        Iterator<EntradaDiccionario> localIterator = beans.iterator();
        while (localIterator.hasNext()) {
            EntradaDiccionario ent = localIterator.next();
            String id = ent.getId();
            gerenteDiccionario.modificaCodigoIndividual(codigo, id);
            codigo += 10;
        }
    }   

}
