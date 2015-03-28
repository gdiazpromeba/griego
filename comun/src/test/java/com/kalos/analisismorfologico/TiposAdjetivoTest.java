
package com.kalos.analisismorfologico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.kalos.beans.AdjetivoBean;
import com.kalos.datos.adaptadores.AdaptadorGerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.enumeraciones.Beta;
import com.kalos.flexion.declinacion.DeclinaAdjetivos;
import com.kalos.operaciones.OpPalabras;

public class TiposAdjetivoTest extends BaseAMTest {

    private DeclinaAdjetivos declinaAdjetivos;

    private GerenteAdjetivos gerenteAdjetivos;

    public void testTipos() {
        int codigo = 0;
        String letra = null;
        try {
            for (int i = 0; i < Beta.arrBeta.length; i++) {
                letra = new String(new char[] { Beta.arrBeta[i] });
                System.out.println("procesando letra " + letra);
                List<String> idsLetra = gerenteAdjetivos.seleccionaPorLetra(letra);
                AdaptadorGerenteAdjetivos aga = new AdaptadorGerenteAdjetivos(gerenteAdjetivos);
                List<AdjetivoBean> entradasLetra = new ArrayList<AdjetivoBean>(aga.getBeans(idsLetra));
                for (AdjetivoBean bean : entradasLetra) {
                    codigo = bean.getCodigo();
                    int tipoBD = bean.getTipoAdjetivo();
                    if (tipoBD == 11) { // los que están marcados como
                                        // irregulares no se analizan
                        continue;
                    }
                    String mascComp = bean.getMasculino() != null ? OpPalabras.strBetaACompleto(bean.getMasculino()) : null;
                    String femComp = bean.getFemenino() != null ? OpPalabras.strBetaACompleto(bean.getFemenino()) : null;
                    String neuComp = bean.getNeutro() != null ? OpPalabras.strBetaACompleto(bean.getNeutro()) : null;
                    String mascFemComp = bean.getMascFem() != null ? OpPalabras.strBetaACompleto(bean.getMascFem()) : null;
                    int tipoSugerido = declinaAdjetivos.sugiereTipoAdjetivoEntero( mascComp,femComp, neuComp, mascFemComp);

                    boolean losTiposSonIguales = tipoSugerido == tipoBD;
                    if (!losTiposSonIguales) {
                        System.out.println("letra=" + bean.getLetra() + " codigo=" + bean.getCodigo() + " ****** tipo en BD=" + tipoBD + " sugeridos=" + tipoSugerido);
                    }
                    assertTrue(losTiposSonIguales);
                }
            }
        } catch (Exception ex) {
            System.out.println("error en letra=" + letra + " código=" + codigo);
            ex.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        ApplicationContext contexto = creaContexto();
        declinaAdjetivos = (DeclinaAdjetivos) contexto.getBean("declinaAdjetivos");
        gerenteAdjetivos = (GerenteAdjetivos) contexto.getBean("gerenteAdjetivos");
    }

}
