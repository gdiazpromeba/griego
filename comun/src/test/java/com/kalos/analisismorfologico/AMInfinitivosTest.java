package com.kalos.analisismorfologico;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import com.kalos.analisismorfologico.negocio.AMInfinitivos;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TestInfinitivoBean;
import com.kalos.datos.gerentes.GerenteTestInfinitivos;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Voz;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;

public class AMInfinitivosTest extends BaseAMTest {
    private AMInfinitivos amInfinitivos;
    private GerenteTestInfinitivos gerenteTestInfinitivos;





    public void testTabla() {
	try {
	    List<TestInfinitivoBean> beans = this.gerenteTestInfinitivos.seleccionaTodos();
	    String str = "";
	    AACacheable localB = null;
	    Iterator<TestInfinitivoBean> itBeans = beans.iterator();
	    while (itBeans.hasNext()) {
		TestInfinitivoBean localB1 = itBeans.next();
		if (!str.equals(localB1.getIdVerbo())) {
		    localB = new AACacheable();
		}
		boolean bool = testGenerico(localB1.getFormaConjugada(), localB1.getIdVerbo(), localB1.getVoz(),
			localB1.getAspecto(), localB1.getFuerte(), localB, false);
		if (!bool) {
		    System.out.println("falló para la forma " + localB1.getFormaConjugada());
		}
		assertTrue(bool);
	    }
	} catch (Exception localException) {
	    localException.printStackTrace();
	}
    }

    public boolean testGenerico(String paramString1, String paramString2, Voz paramZ, Aspecto paramk, FuerteDebil paramP, AACacheable cacheAA, boolean paramBoolean) {
        System.out.println("conjugando ... " + paramString1);
        String[] arrayOfString = { OpPalabras.strBetaACompleto(paramString1) };
        Set<ResultadoUniversal> resultados = this.amInfinitivos.buscaCanonica(arrayOfString, cacheAA, false, paramBoolean);

        boolean bool = false;
        Iterator<ResultadoUniversal> localIterator = resultados.iterator();
        while (localIterator.hasNext()) {
            ResultadoUniversal localj = localIterator.next();
            try {
                if ((localj.getIdSimpleOCompuesto().equals(paramString2)) && (localj.getVoz().equals(paramZ)) && (localj.getAspecto().equals(paramk)) && (localj.getFuerte().equals(paramP))) {
                    bool = true;
                    break;
                }
            } catch (Exception localException) {
                System.out.println("el registro siguiente:");
                OpBeans.debugBean(localj, new String[0]);
                System.out.println("no coincide con los siguientes valores esperados:");
                System.out.println("  idVerbo=" + paramString2);
                System.out.println("  voz=" + paramZ);
                System.out.println("  aspecto=" + paramk);
                System.out.println("  fuerte=" + paramP);
                bool = false;
                break;
            }
        }
        return bool;
    }

 

    protected void setUp() throws Exception {
	ApplicationContext localApplicationContext = creaContexto();
	this.amInfinitivos = (AMInfinitivos) localApplicationContext.getBean("amInfinitivos");
	this.gerenteTestInfinitivos = (GerenteTestInfinitivos) localApplicationContext
		.getBean("gerenteTestInfinitivos");
    }
}
