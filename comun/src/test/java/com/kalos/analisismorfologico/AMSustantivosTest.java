
package com.kalos.analisismorfologico;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.kalos.analisismorfologico.negocio.AMSustantivos;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.TestSustantivoBean;
import com.kalos.datos.gerentes.GerenteTestSustantivos;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;
import com.kalos.operaciones.AACacheable;
import com.kalos.operaciones.OpPalabras;

;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AMSustantivosTest extends BaseAMTest {

    private GerenteTestSustantivos gerenteTestSustantivos;
    private AMSustantivos amSustantivos;
    Logger logger = Logger.getLogger(this.getClass().getName());

    private static long tiempoAcumulado = 0;

    public void testTabla() {
        try {
            List<TestSustantivoBean> todos = gerenteTestSustantivos.seleccionaTodos();
            String idSustAnterior = "";
            AACacheable cacheAA = null;
            for (TestSustantivoBean bean : todos) {
                if (!idSustAnterior.equals(bean.getIdSustantivo())) {
                    //cambio el caché cada vez que cambio de sustantivo
                    cacheAA = new AACacheable();
                }
                boolean ret = testGenerico(bean.getFormaDeclinada(), bean.getIdSustantivo(), bean.getCaso(), bean.getNumero(), cacheAA);
                if (!ret) {
                    System.out.println("falló para la forma " + bean.getFormaDeclinada() + " caso=" + bean.getCaso() + " numero=" + bean.getNumero());
                }
                assertEquals(ret, true);
                if (!ret) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Dada una forma conjugada, llama al análisis morfológico y comprueba si el código del sustantivo canónico 
     * está en el set de resultado
     * @param formaDeclinada: en BETA
     * @param conta         
     */
    public boolean testGenerico(String formaDeclinada, String idSustantivo, Caso caso, Numero numero, AACacheable cacheAA) {
        System.out.print("forma declinada: ... " + formaDeclinada);
        String[] entradas = new String[] { OpPalabras.strBetaACompleto(formaDeclinada) };
        HashSet<ResultadoUniversal> sRes = new HashSet<ResultadoUniversal>();
        long tardo = amSustantivos.buscaCanonica(entradas, sRes, cacheAA, false, false);
        tiempoAcumulado += tardo;
        System.out.print("  tardanza=" + tardo + " tiempo acumulado=" + tiempoAcumulado + "\n");
        boolean encontroCodigo = false;
        for (Iterator<ResultadoUniversal> it = sRes.iterator(); it.hasNext();) {
            ResultadoUniversal regAux = it.next();
            if (regAux.getId().equals(idSustantivo) && regAux.getCaso().equals(caso) && regAux.getNumero().equals(numero)) {
                encontroCodigo = true;
                break;
            }
        }
        return encontroCodigo;
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        ApplicationContext contexto = creaContexto();
        amSustantivos = (AMSustantivos) contexto.getBean("amSustantivos");
        amSustantivos.setApplicationContext(contexto);
        gerenteTestSustantivos = (GerenteTestSustantivos) contexto.getBean("gerenteTestSustantivos");
    }

}