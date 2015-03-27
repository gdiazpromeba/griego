package com.kalos.analisismorfologico;import java.util.HashSet;import java.util.Iterator;import java.util.List;import java.util.Set;import org.springframework.context.ApplicationContext;import com.kalos.analisismorfologico.negocio.AMVerbos;import com.kalos.beans.ResultadoUniversal;import com.kalos.beans.TestVerboBean;import com.kalos.datos.gerentes.GerenteTestVerbos;import com.kalos.enumeraciones.Modo;import com.kalos.enumeraciones.Persona;import com.kalos.enumeraciones.Tiempo;import com.kalos.enumeraciones.Voz;import com.kalos.operaciones.AACacheable;import com.kalos.operaciones.OpBeans;import com.kalos.operaciones.OpPalabras;public class AMVerbosTest extends BaseAMTest {    private AMVerbos amVerbos;    private GerenteTestVerbos gerenteTestVerbos;    private static long i = 0L;    public void testTabla() {        try {            List<TestVerboBean> localList = this.gerenteTestVerbos.seleccionaTodos();            String str = "";            AACacheable localB = null;            Iterator<TestVerboBean> localIterator = localList.iterator();            while (localIterator.hasNext()) {                TestVerboBean localJ = localIterator.next();                if (!str.equals(localJ.getIdVerbo())) {                    localB = new AACacheable();                }                boolean bool = testGenerico(localJ.getFormaConjugada(), localJ.getIdVerbo(), localJ.getVoz(),                        localJ.getModo(), localJ.getTiempo(), localJ.getPersona(), localB, false);                assertTrue(bool);                if (!bool) {                    break;                }            }        } catch (Exception localException) {            localException.printStackTrace();        }    }    public boolean testGenerico(String paramString1, String paramString2, Voz paramZ, Modo paramp, Tiempo paramj,            Persona param_, AACacheable paramB, boolean paramBoolean) {        System.out.print("conjugando ... " + paramString1);        String[] arrayOfString = { OpPalabras.strBetaACompleto(paramString1) };        Set<ResultadoUniversal> localHashSet = new HashSet<ResultadoUniversal>();        long l = this.amVerbos.buscaCanonica(arrayOfString, localHashSet, paramB, false, paramBoolean);        i += l;        System.out.print("  tardanza=" + l + " tiempo acumulado=" + i + "\n");        boolean bool = false;        Iterator<ResultadoUniversal> localIterator = localHashSet.iterator();        while (localIterator.hasNext()) {            ResultadoUniversal localj = localIterator.next();            try {                if ((localj.getIdSimpleOCompuesto().equals(paramString2)) && (localj.getVoz().equals(paramZ))                        && (localj.getModo().equals(paramp)) && (localj.getPersona().equals(param_))                        && (localj.getTiempo().equals(paramj))) {                    bool = true;                    break;                }            } catch (Exception localException) {                System.out.println("el registro siguiente:");                OpBeans.debugBean(localj, new String[0]);                System.out.println("no coincide con los siguientes valores esperados:");                System.out.println("  idVerbo=" + paramString2);                System.out.println("  voz=" + paramZ);                System.out.println("  modo=" + paramp);                System.out.println("  tiempo=" + paramj);                System.out.println("  persona=" + param_);                bool = false;                break;            }        }        return bool;    }    protected void setUp() throws Exception {        ApplicationContext localApplicationContext = creaContexto();        this.amVerbos = ((AMVerbos) localApplicationContext.getBean("amVerbos"));        this.gerenteTestVerbos = ((GerenteTestVerbos) localApplicationContext.getBean("gerenteTestVerbos"));    }}