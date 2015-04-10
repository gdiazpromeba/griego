
package com.kalos.visual.controles.textos.teclas;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.InputMap;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.kalos.enumeraciones.EfectoTecleado;
import com.kalos.enumeraciones.uLetras;
import com.kalos.recursos.CargaRecursos;
import com.kalos.recursos.Configuracion;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Maneja las combinaciones de taclas a las que se les presta atención en los
 * textos de entrada griega. Los arrays arrTeclado, arrTecladoSinMarcas, etc,
 * agrupan dichas combinaciones de teclas según el modo de entrada de texto
 * porel cual el control está configurado (algunos, como copiarPegar, se aplican
 * a todos). Lo que se hace una vez que se tecleó está determinado por una
 * "TextAction", que se vincula en el contructor al "keystroke" definido por la
 * combinación de teclas. La información de dicha vinculación se guarda en un
 * "inputmap".
 */
@SuppressWarnings("unchecked")
public class ManejaInputMap implements uLetras {

    static MultiMap mapeos = new MultiHashMap();
    static {
        try {
            URL keyboardsUrl = CargaRecursos.getResourceURL("keyboards.xml");
            org.dom4j.Document doc = new SAXReader().read(keyboardsUrl);
            org.dom4j.Element raiz = doc.getRootElement();
            List<Element> teclados = raiz.elements("keyboard");
            for (Element teclado : teclados) {
                if (teclado.attributeValue("name").equals(Configuracion.getUltimoTeclado())) {
                    List<Element> keystrokes = teclado.elements("keystroke");
                    for (Element keystroke : keystrokes) {
                        String nombre = keystroke.attributeValue("name");
                        List<Element> combinaciones = keystroke.elements("combination");
                        for (Element combinacion : combinaciones) {
                            KeyStroke ks = KeyStroke.getKeyStroke(combinacion.attributeValue("modifier") + " "+ combinacion.attributeValue("key"));
                            mapeos.put(nombre, ks);
                        }
                    }
                    break;
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int[][] arrTeclado = {

            // letras simples
            { KeyEvent.VK_A, 0, cAlfa }, { KeyEvent.VK_B, 0, cBeta }, { KeyEvent.VK_G, 0, cGamma },
            { KeyEvent.VK_D, 0, cDelta }, { KeyEvent.VK_E, 0, cEpsilon }, { KeyEvent.VK_Z, 0, cDzeta },
            { KeyEvent.VK_H, 0, cEta }, { KeyEvent.VK_Q, 0, cTheta },
            { KeyEvent.VK_I, 0, cIota },
            { KeyEvent.VK_K, 0, cKappa },
            { KeyEvent.VK_L, 0, cLambda },
            { KeyEvent.VK_M, 0, cMu },
            { KeyEvent.VK_N, 0, cNu },
            { KeyEvent.VK_X, 0, cXi },
            { KeyEvent.VK_O, 0, cOmicron },

            { KeyEvent.VK_P, 0, cPi },
            { KeyEvent.VK_R, 0, cRho },
            { KeyEvent.VK_S, 0, cSigma },
            { KeyEvent.VK_J, 0, cSigmaFinal },
            { KeyEvent.VK_T, 0, cTau },
            { KeyEvent.VK_U, 0, cUpsilon },
            { KeyEvent.VK_F, 0, cFi },
            { KeyEvent.VK_C, 0, cJi },
            { KeyEvent.VK_Y, 0, cPsi },
            { KeyEvent.VK_W, 0, cOmega },

            // mayúsculas
            { KeyEvent.VK_A, Event.SHIFT_MASK, cAlfaMayuscula }, { KeyEvent.VK_B, Event.SHIFT_MASK, cBetaMayuscula },
            { KeyEvent.VK_G, Event.SHIFT_MASK, cGammaMayuscula }, { KeyEvent.VK_D, Event.SHIFT_MASK, cDeltaMayuscula },
            { KeyEvent.VK_E, Event.SHIFT_MASK, cEpsilonMayuscula },
            { KeyEvent.VK_Z, Event.SHIFT_MASK, cDzetaMayuscula }, { KeyEvent.VK_H, Event.SHIFT_MASK, cEtaMayuscula },
            { KeyEvent.VK_Q, Event.SHIFT_MASK, cThetaMayuscula }, { KeyEvent.VK_I, Event.SHIFT_MASK, cIotaMayuscula },
            { KeyEvent.VK_K, Event.SHIFT_MASK, cKappaMayuscula },
            { KeyEvent.VK_L, Event.SHIFT_MASK, cLambdaMayuscula }, { KeyEvent.VK_M, Event.SHIFT_MASK, cMuMayuscula },
            { KeyEvent.VK_N, Event.SHIFT_MASK, cNuMayuscula }, { KeyEvent.VK_X, Event.SHIFT_MASK, cXiMayuscula },
            { KeyEvent.VK_O, Event.SHIFT_MASK, cOmicronMayuscula },

            { KeyEvent.VK_P, Event.SHIFT_MASK, cPiMayuscula }, { KeyEvent.VK_R, Event.SHIFT_MASK, cRhoMayuscula },
            { KeyEvent.VK_S, Event.SHIFT_MASK, cSigmaMayuscula }, { KeyEvent.VK_T, Event.SHIFT_MASK, cTauMayuscula },
            { KeyEvent.VK_U, Event.SHIFT_MASK, cUpsilonMayuscula }, { KeyEvent.VK_F, Event.SHIFT_MASK, cFiMayuscula },
            { KeyEvent.VK_C, Event.SHIFT_MASK, cJiMayuscula }, { KeyEvent.VK_Y, Event.SHIFT_MASK, cPsiMayuscula },
            { KeyEvent.VK_W, Event.SHIFT_MASK, cOmegaMayuscula },

            // subscriptas
            { KeyEvent.VK_J, Event.SHIFT_MASK, cOmegaSubscripta }, { KeyEvent.VK_V, Event.SHIFT_MASK, cEtaSubscripta },
            { KeyEvent.VK_V, 0, cAlfaSubscripta },

    };

    static Object[][] arrAtras = {

            // universales
            { KeyEvent.VK_SLASH, false, EfectoTecleado.AcentoAgudo },
            { KeyEvent.VK_DIVIDE, false, EfectoTecleado.AcentoAgudo },
            { KeyEvent.VK_LEFT_PARENTHESIS, false, EfectoTecleado.EspirituAspero },
            { KeyEvent.VK_RIGHT_PARENTHESIS, false, EfectoTecleado.EspirituSuave },
            { KeyEvent.VK_EQUALS, false, EfectoTecleado.AcentoCircunflejo },
            { KeyEvent.VK_BACK_SLASH, false, EfectoTecleado.AcentoGrave },
            { KeyEvent.VK_SEPARATOR, false, EfectoTecleado.IotaSuscripta },
            { KeyEvent.VK_PLUS, false, EfectoTecleado.Dieresis }, 
            { KeyEvent.VK_UNDERSCORE, false, EfectoTecleado.SignoLarga },

    };

    static int[][] arrMovimiento = { { KeyEvent.VK_RIGHT, 0 }, { KeyEvent.VK_LEFT, 0 },
            { KeyEvent.VK_RIGHT, Event.SHIFT_MASK }, { KeyEvent.VK_LEFT, Event.SHIFT_MASK }, { KeyEvent.VK_END, 0 },
            { KeyEvent.VK_END, Event.SHIFT_MASK }, { KeyEvent.VK_HOME, 0 }, { KeyEvent.VK_HOME, Event.SHIFT_MASK }, };
    static int[][] arrBorrado = { { KeyEvent.VK_BACK_SPACE, 0 }, { KeyEvent.VK_DELETE, 0 } };

    // para copiado y pegado
    static int[][] copiarPegar = { { KeyEvent.VK_C, KeyEvent.CTRL_MASK }, { KeyEvent.VK_V, KeyEvent.CTRL_MASK },
            { KeyEvent.VK_INSERT, KeyEvent.CTRL_MASK }, { KeyEvent.VK_INSERT, KeyEvent.SHIFT_MASK }, };

    JTextField m_txt;
    InputMap m_im;

    /**
     * @param sinMarcas
     * determina cuál array voy a usar para la maniobra de
     * "atrás y acento"
     */
    public static void asociaTeclas(JTextField txt) {

        txt.setInputMap(0, new InputMap());
        // KeyStroke[] todas=txt.getInputMap().allKeys();
        // for (int i=0; i<todas.length; i++){
        // txt.getInputMap().remove(todas[i]);
        // }

        // acciones de agregado simple
        for (int i = 0; i < arrTeclado.length; i++) {
            TAAgregadoSimple ka = new TAAgregadoSimple((char) arrTeclado[i][2], txt);
            KeyStroke ks = KeyStroke.getKeyStroke(arrTeclado[i][0], arrTeclado[i][1]);
            txt.getInputMap().put(ks, ka);
        }

        // acciones 'atrás y acento'
        for (int i = 0; i < arrAtras.length; i++) {
            TAAtrasYAcento aa = new TAAtrasYAcento(txt, (EfectoTecleado) arrAtras[i][2]);
            KeyStroke ks = KeyStroke.getKeyStroke((int) arrAtras[i][0], 0,  (boolean) arrAtras[i][1]);
            txt.getInputMap().put(ks, aa);
        }
        for (Iterator it = mapeos.entrySet().iterator(); it.hasNext();) {
            Map.Entry entrada = (Map.Entry) it.next();
            String clave = (String) entrada.getKey();
            EfectoTecleado codigoArbitrario = TAAtrasYAcento.cadenaACodigoArbitrario(clave);
            Collection col = (Collection) mapeos.get(clave);
            for (Iterator itcol = col.iterator(); itcol.hasNext();) {
                KeyStroke ks = (KeyStroke) itcol.next();
                txt.getInputMap().put(ks, new TAAtrasYAcento(txt, codigoArbitrario));
            }
        }

        // acciones de movimiento - movimiento/selección
        for (int i = 0; i < arrMovimiento.length; i++) {
            TAMovimientoSeleccion aa = new TAMovimientoSeleccion(txt, arrMovimiento[i][0], arrMovimiento[i][1]);
            KeyStroke ks = KeyStroke.getKeyStroke(arrMovimiento[i][0], arrMovimiento[i][1]);
            txt.getInputMap().put(ks, aa);
        }

        // acciones de borrado
        for (int i = 0; i < arrBorrado.length; i++) {
            TABorrado aa = new TABorrado(txt, arrBorrado[i][0]);
            KeyStroke ks = KeyStroke.getKeyStroke(arrBorrado[i][0], arrBorrado[i][1]);
            txt.getInputMap().put(ks, aa);
        }

        // acciones de copiado/pegado
        // (agregadas con el nuevo estilo de manejo de teclado, en 1.3), ver
        // http://java.sun.com/products/jfc/tsc/special_report/kestrel/keybindings.html
        for (int i = 0; i < copiarPegar.length; i++) {
            txt.getInputMap().put(KeyStroke.getKeyStroke(copiarPegar[i][0], copiarPegar[i][1]),
                    new TACopiarPegar(txt, copiarPegar[i][0], copiarPegar[i][1]));
        }

    }
}