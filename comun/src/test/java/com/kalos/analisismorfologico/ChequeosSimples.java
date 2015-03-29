package com.kalos.analisismorfologico;import java.util.HashSet;import java.util.Iterator;import java.util.Set;import org.junit.Ignore;import org.springframework.beans.factory.support.DefaultListableBeanFactory;import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;import org.springframework.context.ApplicationContext;import org.springframework.context.support.GenericApplicationContext;import org.springframework.core.io.ClassPathResource;import com.kalos.analisismorfologico.negocio.AMAdjetivos;import com.kalos.analisismorfologico.negocio.AMInfinitivos;import com.kalos.analisismorfologico.negocio.AMNominal;import com.kalos.analisismorfologico.negocio.AMParticipios;import com.kalos.analisismorfologico.negocio.AMSustantivos;import com.kalos.analisismorfologico.negocio.AMUtil;import com.kalos.analisismorfologico.negocio.AMVerbal;import com.kalos.analisismorfologico.negocio.AMVerbos;import com.kalos.analisismorfologico.negocio.ExtractorPrefijos;import com.kalos.beans.ResultadoUniversal;import com.kalos.beans.SustantivoBean;import com.kalos.datos.gerentes.GerenteSustantivos;import com.kalos.datos.gerentes.GerenteVerbalizadorParticipios;import com.kalos.datos.gerentes.GerenteVerbos;import com.kalos.datos.gerentes.GerenteVerbosCompuestos;import com.kalos.enumeraciones.CompLetras;import com.kalos.flexion.declinacion.Declina;import com.kalos.flexion.declinacion.Participios;import com.kalos.operaciones.AACacheable;import com.kalos.operaciones.OpPalabras;@Ignorepublic class ChequeosSimples implements CompLetras {    public static void testAMVerbos() {        try {            //String[] arrayOfString = { OpPalabras.strBetaACompleto("A)POBA/LLW") };            String[] arrayOfString = { OpPalabras.strBetaACompleto("E)LU/QHN") };            HashSet localHashSet = new HashSet();            ApplicationContext localApplicationContext = creaContexto();            AMVerbos localI = (AMVerbos) localApplicationContext.getBean("amVerbos");            AMUtil localC = (AMUtil) localApplicationContext.getBean("amUtil");            AACacheable localB = new AACacheable();            localI.buscaCanonica(arrayOfString, localHashSet, localB, true, true);            System.out.println("********* resultado ****************");            Iterator<ResultadoUniversal> localIterator = localHashSet.iterator();            while (localIterator.hasNext()) {                ResultadoUniversal localj = localIterator.next();                System.out.println("resultado id=" + localj.getId() + " compuesto=" + localj.getIdCompuesto());                System.out.println(localC.accidentesHablados(localj));            }        } catch (Exception localException) {            localException.printStackTrace();        }    }    public static void testAMInfinitivos() {        try {            //	    String[] arrayOfString = { OpPalabras.strBetaACompleto("BALEI/N") };            //	    String[] arrayOfString = { OpPalabras.strBetaACompleto("A)GAGEI=N") };            String[] arrayOfString = { OpPalabras.strBetaACompleto("BH=NAI") };            HashSet localHashSet = new HashSet();            ApplicationContext localApplicationContext = creaContexto();            AMInfinitivos localJ = (AMInfinitivos) localApplicationContext.getBean("amInfinitivos");            AMUtil localC = (AMUtil) localApplicationContext.getBean("amUtil");            AACacheable localB = new AACacheable();            localJ.buscaCanonica(arrayOfString, localHashSet, localB, false, true);            System.out.println("********* resultado ****************");            localC.debugBeans(localHashSet, new String[] { "formaAccidentada" });        } catch (Exception localException) {            localException.printStackTrace();        }    }    public static void testTipoSustantivoIndividual() {        ApplicationContext localApplicationContext = creaContexto();        Declina localE = (Declina) localApplicationContext.getBean("declina");        GerenteSustantivos localj = (GerenteSustantivos) localApplicationContext.getBean("gerenteSustantivos");        String str1 = "-10ae9356:10a61e88d52:-7fff-10ae";        SustantivoBean locali = localj.seleccionaUno(str1);        System.out.println("comprobando " + locali.getNominativo() + " - " + locali.getGenitivo() + " id="                + locali.getId());        String str2 = OpPalabras.strBetaACompleto(locali.getNominativo());        String str3 = OpPalabras.strBetaACompleto(locali.getGenitivo());        int i = localE.sugiereDeclinacion(str2, str3, locali.getGenero());        int j = i == locali.getTipoNominal() ? 1 : 0;        if (j == 0) {            System.out.println("tipo en BD=" + locali.getTipoNominal() + " sugerido=" + i);        }    }    public static void testAMSustantivos() {        try {            //String[] arrayOfString = { OpPalabras.strBetaACompleto("*GABBAQA=") };            String[] arrayOfString = { OpPalabras.strBetaACompleto("SU_KH=N") };            Set<ResultadoUniversal> localHashSet = new HashSet<ResultadoUniversal>();            ApplicationContext localApplicationContext = creaContexto();            AMSustantivos localF = (AMSustantivos) localApplicationContext.getBean("amSustantivos");            localF.setApplicationContext(localApplicationContext);            AMUtil localC = (AMUtil) localApplicationContext.getBean("amUtil");            AACacheable localB = new AACacheable();            localF.buscaCanonica(arrayOfString, localHashSet, localB, false, true);            System.out.println("********* resultado ****************");            localC.debugBeans(localHashSet, new String[0]);        } catch (Exception localException) {            localException.printStackTrace();        }    }    public static void testAMParticipios() {        try {            //String[] arrayOfString = { OpPalabras.strBetaACompleto("E)SFRA_GISME/NOU") };            String[] arrayOfString = { OpPalabras.strBetaACompleto("KAQH/MENOJ") };            Set localHashSet = new HashSet();            ApplicationContext localApplicationContext = creaContexto();            AMParticipios localD = (AMParticipios) localApplicationContext.getBean("amParticipios");            localD.setParticipios((Participios) localApplicationContext.getBean("participios"));            localD.setAmUtil((AMUtil) localApplicationContext.getBean("amUtil"));            localD.setAmNominal((AMNominal) localApplicationContext.getBean("amNominal"));            localD.setAmVerbal((AMVerbal) localApplicationContext.getBean("amVerbal"));            localD.setExtractorPrefijos((ExtractorPrefijos) localApplicationContext.getBean("extractorPrefijos"));            localD.setGerenteVerbos((GerenteVerbos) localApplicationContext.getBean("gerenteVerbos"));            localD.setGerenteVerbosCompuestos((GerenteVerbosCompuestos) localApplicationContext                    .getBean("gerenteVerbosCompuestos"));            localD.setGerenteVerbalizadorParticipios((GerenteVerbalizadorParticipios) localApplicationContext                    .getBean("gerenteVerbalizadorParticipios"));            AMUtil localC = (AMUtil) localApplicationContext.getBean("amUtil");            AACacheable localB = new AACacheable();            localD.buscaCanonica(arrayOfString, localHashSet, localB, false, true);            System.out.println("********* resultado ****************");            localC.debugBeans(localHashSet, new String[] { "formaCanonica" });        } catch (Exception localException) {            localException.printStackTrace();        }    }    public static void testAMAdjetivos() {        try {            //String[] arrayOfString = { OpPalabras.strBetaACompleto("DWDEKA/FU_LON") };            //String[] arrayOfString = { OpPalabras.strBetaACompleto("A)XI/AI_J") };            String[] arrayOfString = { OpPalabras.strBetaACompleto("A)XI/A_") };            HashSet localHashSet = new HashSet();            ApplicationContext localApplicationContext = creaContexto();            AMAdjetivos localL = (AMAdjetivos) localApplicationContext.getBean("amAdjetivos");            localL.setApplicationContext(localApplicationContext);            AMUtil localC = (AMUtil) localApplicationContext.getBean("amUtil");            AACacheable localB = new AACacheable();            localL.buscaCanonica(arrayOfString, localHashSet, localB, true, true);            System.out.println("********* resultado ****************");            localC.debugBeans(localHashSet, new String[] { "formaAccidentada" });        } catch (Exception localException) {            localException.printStackTrace();        }    }    public static ApplicationContext creaContexto() {        ApplicationContext contexto;        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);        ClassPathResource res = new ClassPathResource("daos.xml");        reader.loadBeanDefinitions(res);        res = new ClassPathResource("gerentes-datos.xml");        reader.loadBeanDefinitions(res);        res = new ClassPathResource("flexion.xml");        reader.loadBeanDefinitions(res);        res = new ClassPathResource("analisisMorfologico.xml");        reader.loadBeanDefinitions(res);        contexto = new GenericApplicationContext(factory);        return contexto;    }    public static void main(String[] paramArrayOfString) {        //Recursos.cambiaLocale("es");        testAMAdjetivos();    }}