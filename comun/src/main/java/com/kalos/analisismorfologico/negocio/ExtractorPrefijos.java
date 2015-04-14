
package com.kalos.analisismorfologico.negocio;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kalos.beans.SubstractorPrefijosBean;
import com.kalos.datos.gerentes.GerenteSubstractorPrefijos;
import com.kalos.enumeraciones.Espiritu;
import com.kalos.fonts.CarPos;
import com.kalos.operaciones.AnalisisAcento;
import com.kalos.operaciones.OpLetras;
import com.kalos.operaciones.OpPalabras;

public class ExtractorPrefijos {

    public static final int NADA = 0;
    public static final int TODOS_LOS_NODOS = 1;
    private GerenteSubstractorPrefijos gerenteSubstractorPrefijos;
    
    private Logger logger=Logger.getLogger(this.getClass().getName());

    public GerenteSubstractorPrefijos getGerenteSubstractorPrefijos() {
        return this.gerenteSubstractorPrefijos;
    }

    public void setGerenteSubstractorPrefijos(GerenteSubstractorPrefijos paramOA) {
        this.gerenteSubstractorPrefijos = paramOA;
    }

    public TemaConPreps[] averiguaPreposiciones(String paramString, int paramInt1, int paramInt2) {
        logger.info("en averiguapPeposiciones, gerenteSubstractor=" + gerenteSubstractorPrefijos);
        ArbolPreposiciones arbol = creaArbol(new TemaConPreps(paramString), paramInt2);
        logger.info("árbol creado=" + arbol);
        Object[] arrayOfObject;
        switch (paramInt1) {
            case 1:
                arrayOfObject = arbol.getTodosLosNodos();
                break;
            case 2:
                arrayOfObject = arbol.getHojas();
                break;
            case 3:
                arrayOfObject = arbol.getHojasMasProfundas();
                break;
            case 4:
                arrayOfObject = arbol.getTodoMenosRaiz();
                break;
            default:
                return null;
        }
        logger.info("saliendo de averiguaPreposiciones");
        return (TemaConPreps[]) Arrays.asList(arrayOfObject).toArray(new TemaConPreps[0]);
    }

    public TemaConPreps[] averiguaPrefijos(String paramString, int paramInt, Map<Object[], TemaConPreps[]> paramMap) {
        logger.info("entrando en averiguaPrefijos");
        Object[] arrayOfObject = { paramString, Integer.valueOf(paramInt) };
        TemaConPreps[] arrayOfM = (TemaConPreps[]) paramMap.get(arrayOfObject);
        if (arrayOfM == null) {
            arrayOfM = averiguaPreposiciones(paramString, 1, paramInt);
        }
        logger.info("saliendo de averiguaPrefijos");
        return arrayOfM;
    }

    private ArbolPreposiciones creaArbol(TemaConPreps temPreps, int hasta) {
        int j = 0;
        ArbolPreposiciones arbol = new ArbolPreposiciones(temPreps);
        int i;
        do {
            Object[] arrayOfObject = arbol.getHojasDelNivel(j++);
            i = 0;
            for (int k = 0; k < arrayOfObject.length; k++) {
                TemaConPreps temConPreps = (TemaConPreps) arrayOfObject[k];
                String resto = temConPreps.resto;
                if (resto.length() > hasta) {
                    List<SubstractorPrefijosBean> localList = sustraePrefijos(OpPalabras.desacentuar(OpPalabras.desespirituar(temConPreps.resto)), hasta);
                    i += localList.size();
                    Iterator<SubstractorPrefijosBean> localIterator = localList.iterator();
                    while (localIterator.hasNext()) {
                        SubstractorPrefijosBean spBean = localIterator.next();
                        String agAlComer = OpPalabras.strBetaACompleto(spBean.getAgregarAlComer());
                        String prefijo = spBean.getPrefijo();
                        String preposicion = spBean.getPreposicion();
                        int m = spBean.getVocalAntes() == 1 ? 1 : 0;
                        int n = spBean.getVocalAntes() == -1 ? 1 : 0;
                        if (temConPreps.preps.contains(preposicion)) {
                            i--;
                        } else {
                            String construida = agAlComer.concat(temConPreps.resto.substring(prefijo.length()));
                            if (construida.length() != 0) {
                                CarPos cp0 = CarPos.getCarPos(construida, 0);
                                if ((cp0.getLetraBase() == '⎹') && (cp0.tieneDieresis())) {
                                    char c = OpLetras.quitaDieresisLetra(cp0.getCaracter());
                                    StringBuffer localObject = new StringBuffer(construida);
                                    localObject.setCharAt(0, c);
                                    construida = localObject.toString();
                                }
                                CarPos localA2 = CarPos.getCarPos(construida, 1);
                                if ((cp0.getCaracter() == 'ρ') && (localA2 != null)
                                        && (localA2.getCaracter() == 'ρ')) {
                                    construida = construida.substring(1);
                                }
                                String sinPrefijo = temConPreps.resto.substring(0, prefijo.length());
                                AnalisisAcento aaConstruida = AnalisisAcento.getAnalisisAcento(construida);
                                AnalisisAcento aaSinPrefijo = AnalisisAcento.getAnalisisAcento((String) sinPrefijo);
                                if ((aaConstruida.sugeridos.silabaB1 == 0) && (aaSinPrefijo.sugeridos.silabaB1 != 0)) {
                                    construida = OpPalabras.acentua(construida);
                                }
                                if ((construida != null) && (construida.length() != 0)) {
                                    CarPos localA3 = CarPos.getCarPos(construida, 0);
                                    if ((!localA3.esVocal()) && (m != 0)) {
                                        i--;
                                    } else if ((localA3.esVocal()) && (n != 0)) {
                                        i--;
                                    } else {
                                        TemaConPreps tcpHijo;
                                        if (localA3.esVocal()) {
                                            if (!spBean.getEspiritu().equals(Espiritu.Aspero)) {
                                                try {
                                                    tcpHijo = (TemaConPreps) temConPreps.clone();
                                                } catch (CloneNotSupportedException localCloneNotSupportedException1) {
                                                    localCloneNotSupportedException1.printStackTrace();
                                                    return null;
                                                }
                                                tcpHijo.resto = OpPalabras.espirituPalabra(construida, Espiritu.Suave);
                                                tcpHijo.preps.add(preposicion);
                                                arbol.agregaHijo(temConPreps, tcpHijo);
                                            }
                                            if (!spBean.getEspiritu().equals(Espiritu.Suave)) {
                                                try {
                                                    tcpHijo = (TemaConPreps) temConPreps.clone();
                                                } catch (CloneNotSupportedException localCloneNotSupportedException2) {
                                                    localCloneNotSupportedException2.printStackTrace();
                                                    return null;
                                                }
                                                tcpHijo.resto = OpPalabras.espirituPalabra(construida, Espiritu.Aspero);
                                                tcpHijo.preps.add(preposicion);
                                                arbol.agregaHijo(temConPreps, tcpHijo);
                                            }
                                        } else {
                                            try {
                                                tcpHijo = (TemaConPreps) temConPreps.clone();
                                            } catch (CloneNotSupportedException localCloneNotSupportedException3) {
                                                localCloneNotSupportedException3.printStackTrace();
                                                return null;
                                            }
                                            tcpHijo.resto = construida;
                                            tcpHijo.preps.add(preposicion);
                                            arbol.agregaHijo(temConPreps, tcpHijo);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } while (i > 0);
        return arbol;
    }

    private List<SubstractorPrefijosBean> sustraePrefijos(String paramString, int hasta) {
        String str = paramString.substring(0, paramString.length() - hasta);
        str = OpPalabras.strCompletoABeta(str);
        List<SubstractorPrefijosBean> localList = this.gerenteSubstractorPrefijos.seleccionaPorForma(str);
        return localList;
    }
}
