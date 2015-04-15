
package com.kalos.flexion.conjugacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kalos.beans.IrrVerbo;
import com.kalos.beans.IrrVerboIndividual;
import com.kalos.beans.VerboBean;
import com.kalos.beans.VerboSimpleCompuesto;
import com.kalos.datos.gerentes.GerenteIrrVerbos;
import com.kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import com.kalos.datos.gerentes.GerentePreposicionesEnVerbos;
import com.kalos.datos.gerentes.GerenteTiposVerbo;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.datos.gerentes.GerenteVerbosCompuestos;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Aumento;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.Voz;
import com.kalos.flexion.Preposiciones;
import com.kalos.flexion.conjugacion.negocio.Desinencia;
import com.kalos.flexion.conjugacion.negocio.ManejaDesinenciasVerbo;
import com.kalos.flexion.conjugacion.negocio.Ocurrencia;
import com.kalos.operaciones.OpBeans;
import com.kalos.operaciones.OpPalabras;
import com.kalos.operaciones.TiposVerbo;

public class Verbos {

    private GerenteIrrVerbos gerIrrVerbos;
    private GerenteIrrVerbosIndividuales getIrrVerbosIndiv;
    private GerenteVerbos gerenteVerbos;
    private GerenteTiposVerbo S;
    private GerenteVerbosCompuestos E;
    private GerentePreposicionesEnVerbos gerPreposicionesEnVerbos;
    private Preposiciones preposiciones;
    private ManejaDesinenciasVerbo manejaDesinenciasVerbo;
    private CacheFlexionVerbos L;
    private Object[][] O = { { Modo.Indicativo, Tiempo.Presente }, { Modo.Subjuntivo, Tiempo.Presente },
            { Modo.Optativo, Tiempo.Presente }, { Modo.Imperativo, Tiempo.Presente } };
    private Object[][] I = { { Modo.Indicativo, Tiempo.Presente }, { Modo.Subjuntivo, Tiempo.Presente },
            { Modo.Imperativo, Tiempo.Presente } };
    private Object[][] G = { { Modo.Optativo, Tiempo.Presente } };
    private static Object[][] C = { { Modo.Indicativo, Tiempo.Imperfecto } };
    private Object[][] F = { { Modo.Indicativo, Tiempo.Futuro }, { Modo.Optativo, Tiempo.Futuro } };
    private Object[][] A = { { Modo.Subjuntivo, Tiempo.Aoristo }, { Modo.Optativo, Tiempo.Aoristo },
            { Modo.Imperativo, Tiempo.Aoristo } };
    private Object[][] B = { { Modo.Indicativo, Tiempo.Aoristo } };
    private Object[][] H = { { Modo.Indicativo, Tiempo.Perfecto }, { Modo.Subjuntivo, Tiempo.Perfecto },
            { Modo.Optativo, Tiempo.Perfecto }, { Modo.Imperativo, Tiempo.Perfecto } };
    private Object[][] J = { { Modo.Indicativo, Tiempo.Pluscuamperfecto, Persona._1ps },
            { Modo.Indicativo, Tiempo.Pluscuamperfecto } };
    private Map<Integer, String> N;

    public void conjugaPorDefecto(Ocurrencia paramC, VerboBean paramh, List<String> paramList) {
        A(paramC, paramh, paramList);
        C(paramC, paramh, paramList);
        D(paramC, paramh, paramList);
        B(paramC, paramh, paramList);
    }

    private void A(Ocurrencia paramC, VerboBean paramh, List<String> paramList) {
        StringBuffer localStringBuffer1 = new StringBuffer(OpPalabras.strBetaACompleto(paramh.getVerbo()));
        OpPalabras.desacentuar(localStringBuffer1);
        Ocurrencia localC = paramC;
        StringBuffer localStringBuffer2 = new StringBuffer();
        Voz localZ;
        if (TiposVerbo.esDeponente(paramh.getTipoVerbo())) {
            localZ = Voz.Media;
        } else {
            localZ = Voz.Activa;
        }
        localStringBuffer2.append(localStringBuffer1);
        extraeTemaDadaCanonica(localStringBuffer2, paramh.getTipoVerbo(), Aspecto.Infectivo);
        OpPalabras.desacentuar(localStringBuffer2);
        String str = OpPalabras.aumenta(localStringBuffer2.toString(), Aumento.Normal);
        switch (paramh.getTipoVerbo()) {
            case 73:
            case 74:
            case 87:
                break;
            case 21:
            case 24:
            case 25:
            case 26:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 77:
            case 78:
            case 79:
            case 80:
            case 91:
            case 92:
            case 93:
            case 94:
            case 96:
            case 97:
                A(localC, this.O, localStringBuffer2.toString(), 1, Voz.Activa, Voz.Media, localZ == Voz.Media,
                        Contraccion.sumaAcentuada, null, false, paramList);
                A(localC, C, str.toString(), 1, Voz.Activa, Voz.Media, localZ == Voz.Media, Contraccion.sumaAcentuada,
                        null, false, paramList);
                break;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                A(localC, this.I, localStringBuffer2.toString(), 1, Voz.Activa, Voz.Media, localZ == Voz.Media,
                        Contraccion.vocalica, null, false, paramList);
                A(localC, this.G, localStringBuffer2.toString(), 3, Voz.Activa, Voz.Media, localZ == Voz.Media,
                        Contraccion.vocalica, null, false, paramList);
                A(localC, C, str.toString(), 1, Voz.Activa, Voz.Media, localZ == Voz.Media, Contraccion.vocalica, null,
                        false, paramList);
        }
    }

    public String aChar32(int paramInt) {
        return (String) this.N.get(Integer.valueOf(paramInt));
    }

    public String sugiereSufijoContracto(String paramString) {
        if (paramString.endsWith("A/W")) {
            return "-W=";
        }
        if (paramString.endsWith("E/W")) {
            return "-W=";
        }
        if (paramString.endsWith("O/W")) {
            return "-W=";
        }
        if (paramString.endsWith("A/OMAI")) {
            return "-W=MAI";
        }
        if (paramString.endsWith("E/OMAI")) {
            return "-OU=MAI";
        }
        if (paramString.endsWith("O/OMAI")) {
            return "-OU=MAI";
        }
        return null;
    }

    public int aInt(String paramString) {
        Iterator<Entry<Integer, String>> localIterator = this.N.entrySet().iterator();
        while (localIterator.hasNext()) {
            Map.Entry<Integer, String> localEntry = localIterator.next();
            String str = (String) localEntry.getValue();
            if (str.equals(paramString)) {
                return ((Integer) localEntry.getKey()).intValue();
            }
        }
        throw new RuntimeException("id de tipo de adjetivo inválido");
    }

    private void C(Ocurrencia paramC, VerboBean verboBean, List<String> paramList) {
        StringBuffer localStringBuffer = new StringBuffer(OpPalabras.strBetaACompleto(verboBean.getVerbo()));
        OpPalabras.desacentuar(localStringBuffer);
        Ocurrencia localC = paramC;
        String str1 = localStringBuffer.toString();
        Voz localZ;
        if (TiposVerbo.esDeponente(verboBean.getTipoVerbo())) {
            localZ = Voz.Media;
        } else {
            localZ = Voz.Activa;
        }
        str1 = extraeTemaDadaCanonica(str1, verboBean.getTipoVerbo(), Aspecto.Futuro);
        boolean bool = localZ == Voz.Media;
        str1 = OpPalabras.desacentuar(str1);
        Contraccion localc;
        switch (verboBean.getTipoVerbo()) {
            case 73:
            case 74:
            case 87:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                localc = Contraccion.sumaAcentuada;
                A(localC, this.F, str1.toString(), 1, Voz.Activa, Voz.Pasiva, localZ == Voz.Media, localc, null, false,
                        paramList);
                break;
            case 21:
            case 24:
            case 25:
            case 26:
                OpPalabras.alargaUltimaVocal(str1);
                localc = Contraccion.sumaAcentuada;
                String str2 = OpPalabras.acortaUltimaVocal(str1.toString());
                A(localC, this.F, str1.toString(), str2, 1, Voz.Activa, Voz.Pasiva, bool, localc, null, false, paramList);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 79:
            case 80:
            case 91:
            case 92:
            case 93:
            case 94:
            case 96:
            case 97:
                localc = Contraccion.consonantica;
                correccionesDeDobles(str1);
                A(localC, this.F, str1.toString(), 1, Voz.Activa, Voz.Pasiva, bool, localc, null, false, paramList);
        }
    }

    private void D(Ocurrencia paramC, VerboBean paramh, List<String> paramList) {
        String str1 = OpPalabras.strBetaACompleto(paramh.getVerbo());
        OpPalabras.desacentuar(str1);
        Ocurrencia localC = paramC;
        Voz localZ;
        if (TiposVerbo.esDeponente(paramh.getTipoVerbo())) {
            localZ = Voz.Media;
        } else {
            localZ = Voz.Activa;
        }
        String str2 = str1;
        str2 = extraeTemaDadaCanonica(str2, paramh.getTipoVerbo(), Aspecto.Confectivo);
        String str3;
        switch (paramh.getTipoVerbo()) {
            case 73:
            case 74:
            case 87:
                break;
            case 21:
            case 24:
            case 25:
            case 26:
                OpPalabras.alargaUltimaVocal(str2);
                str2 = OpPalabras.desacentuar(str2);
                str3 = OpPalabras.aumenta(str2, Aumento.Normal);
                String str4 = OpPalabras.acortaUltimaVocal(str2);
                String str5 = OpPalabras.acortaUltimaVocal(str3);
                A(localC, this.B, str3, str5, 1, Voz.Activa, Voz.Pasiva, localZ == Voz.Media, Contraccion.sumaAcentuada,
                        null, false, paramList);
                A(localC, this.A, str2, str4, 1, Voz.Activa, Voz.Pasiva, localZ == Voz.Media, Contraccion.sumaAcentuada,
                        null, false, paramList);
                break;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                str3 = OpPalabras.aumenta(str2.toString(), Aumento.Normal);
                A(localC, this.B, str3, 1, Voz.Activa, Voz.Pasiva, localZ == Voz.Media, Contraccion.sumaAcentuada, null,
                        false, paramList);
                A(localC, this.A, str2, 1, Voz.Activa, Voz.Pasiva, localZ == Voz.Media, Contraccion.sumaAcentuada, null,
                        false, paramList);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 79:
            case 80:
            case 91:
            case 92:
            case 93:
            case 94:
            case 96:
            case 97:
                str2 = correccionesDeDobles(str2);
                str3 = OpPalabras.aumenta(str2.toString(), Aumento.Normal);
                A(localC, this.B, str3, 1, Voz.Activa, Voz.Pasiva, localZ == Voz.Media, Contraccion.consonantica, null,
                        false, paramList);
                A(localC, this.A, str2, 1, Voz.Activa, Voz.Pasiva, localZ == Voz.Media, Contraccion.consonantica, null,
                        false, paramList);
        }
    }

    private void B(Ocurrencia paramC, VerboBean paramh, List<String> paramList) {
        String str1 = OpPalabras.strBetaACompleto(paramh.getVerbo());
        OpPalabras.desacentuar(str1);
        Ocurrencia localC = paramC;
        Voz localZ;
        if (TiposVerbo.esDeponente(paramh.getTipoVerbo())) {
            localZ = Voz.Media;
        } else {
            localZ = Voz.Activa;
        }
        String str2 = str1;
        str2 = extraeTemaDadaCanonica(str2, paramh.getTipoVerbo(), Aspecto.Perfectivo);
        boolean bool = localZ == Voz.Media;
        StringBuffer localStringBuffer1;
        StringBuffer localStringBuffer2;
        switch (paramh.getTipoVerbo()) {
            case 73:
            case 74:
            case 87:
                break;
            case 21:
            case 24:
            case 25:
            case 26:
                str2 = OpPalabras.acortaUltimaVocal(str2.toString());
                localStringBuffer1 = new StringBuffer(OpPalabras.reduplica(str2.toString()));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumenta(localStringBuffer1.toString(), Aumento.Normal));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumentaTema(localStringBuffer1.toString(),
                        localStringBuffer2.toString(), Modo.Indicativo, Tiempo.Pluscuamperfecto));
                A(localC, this.H, localStringBuffer1.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.sumaAcentuada,
                        null, false, paramList);
                A(localC, this.J, localStringBuffer2.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.sumaAcentuada,
                        null, false, paramList);
                break;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                localStringBuffer1 = new StringBuffer(OpPalabras.reduplica(str2.toString()));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumenta(localStringBuffer1.toString(), Aumento.Normal));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumentaTema(localStringBuffer1.toString(),
                        localStringBuffer2.toString(), Modo.Indicativo, Tiempo.Pluscuamperfecto));
                A(localC, this.H, localStringBuffer1.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.sumaAcentuada,
                        null, false, paramList);
                A(localC, this.J, localStringBuffer2.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.sumaAcentuada,
                        null, false, paramList);
                break;
            case 79:
            case 80:
                str2 = correccionesDeDobles(str2);
                localStringBuffer1 = new StringBuffer(OpPalabras.reduplica(str2));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumenta(localStringBuffer1.toString(), Aumento.Normal));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumentaTema(localStringBuffer1.toString(),
                        localStringBuffer2.toString(), Modo.Indicativo, Tiempo.Pluscuamperfecto));
                A(localC, this.H, localStringBuffer1.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.consonantica,
                        null, false, paramList);
                A(localC, this.J, localStringBuffer2.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.consonantica,
                        null, false, paramList);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 91:
            case 92:
            case 93:
            case 94:
            case 96:
            case 97:
                correccionesDeDobles(str2);
                localStringBuffer1 = new StringBuffer(OpPalabras.reduplica(str2));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumenta(localStringBuffer1.toString(), Aumento.Normal));
                localStringBuffer2 = new StringBuffer(OpPalabras.aumentaTema(localStringBuffer1.toString(),
                        localStringBuffer2.toString(), Modo.Indicativo, Tiempo.Pluscuamperfecto));
                A(localC, this.H, localStringBuffer1.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.consonantica,
                        null, false, paramList);
                A(localC, this.J, localStringBuffer2.toString(), 1, Voz.Activa, Voz.Media, bool, Contraccion.consonantica,
                        null, false, paramList);
        }
    }

    public static String correccionesDeDobles(String paramString) {
        StringBuffer localStringBuffer = new StringBuffer(paramString);
        if (localStringBuffer.charAt(localStringBuffer.length() - 1) == 'ζ') {
            localStringBuffer.setCharAt(localStringBuffer.length() - 1, 'δ');
        }
        if (localStringBuffer.toString().endsWith(OpPalabras.strBetaACompleto("PT"))) {
            localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
        }
        if ((localStringBuffer.toString().endsWith(OpPalabras.strBetaACompleto("SS")))
                || (localStringBuffer.toString().endsWith(OpPalabras.strBetaACompleto("TT")))) {
            localStringBuffer.delete(localStringBuffer.length() - 2, localStringBuffer.length());
            localStringBuffer.append('γ');
        }
        return localStringBuffer.toString();
    }

    public static String extraeTemaDadaCanonica(String paramString, int paramInt, Aspecto paramk) {
        boolean bool = TiposVerbo.esDeponente(paramInt);
        int i;
        if (!bool) {
            if (TiposVerbo.esEnNuMi(paramInt)) {
                if (paramk == Aspecto.Infectivo) {
                    i = 2;
                } else {
                    i = 4;
                }
            } else if ((paramInt == 93) || (paramInt == 91)) {
                i = 1;
            } else if (TiposVerbo.esEnMiPropiamente(paramInt)) {
                i = 2;
            } else if (TiposVerbo.esVocalicoContracto(paramInt)) {
                i = 1;
            } else {
                i = 1;
            }
        } else if (TiposVerbo.esEnNuMi(paramInt)) {
            if (paramk == Aspecto.Infectivo) {
                i = 3;
            } else {
                i = 5;
            }
        } else if ((paramInt == 94) || (paramInt == 92)) {
            i = 6;
        } else if (TiposVerbo.esEnMiPropiamente(paramInt)) {
            i = 3;
        } else if (TiposVerbo.esVocalicoContracto(paramInt)) {
            i = 4;
        } else {
            i = 4;
        }
        String str = paramString.substring(0, paramString.length() - i);
        str = OpPalabras.desacentuar(str);
        if (paramk != Aspecto.Infectivo) {
            StringBuffer localStringBuffer = new StringBuffer(str);
            if (TiposVerbo.esVocalicoContracto(paramInt)) {
                localStringBuffer.delete(localStringBuffer.length() - 1, localStringBuffer.length());
                switch (paramInt) {
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                        localStringBuffer.append('η');
                        break;
                    default:
                        localStringBuffer.append('ω');
                }
            }
            localStringBuffer = new StringBuffer(correccionesDeDobles(localStringBuffer.toString()));
            str = localStringBuffer.toString();
        }
        return str;
    }

    public void extraeTemaDadaCanonica(StringBuffer paramStringBuffer, int paramInt, Aspecto paramk) {
        String str = extraeTemaDadaCanonica(paramStringBuffer.toString(), paramInt, paramk);
        paramStringBuffer.delete(0, paramStringBuffer.length());
        paramStringBuffer.append(str);
    }

    private void C(Ocurrencia oc, List<IrrVerbo> paramList, List<IrrVerboIndividual> paramList1,
            List<String> paramList2) {
        Iterator<IrrVerbo> localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            IrrVerbo irrVerbo = (IrrVerbo) localIterator.next();
            List<String> localList = paramList2;
            if (irrVerbo.isCompuesto()) {
                localList = null;
            }
            String temaAum = null;
            String temaIrr = null;
            temaIrr = irrVerbo.getTema();
            temaAum = OpPalabras.aumenta(irrVerbo.getTema(), irrVerbo.getAumento());
            String tema = irrVerbo.getModo().equals(Modo.Indicativo) ? temaAum : temaIrr;
            pueblaPersonas(oc, temaIrr, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                    irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(), irrVerbo.getTiempoJuego(),
                    false, localList, irrVerbo.getSubPart());
            switch (irrVerbo.getPropagacion()) {
                case Ninguna:
                    break;
                case haciaModoYVoz:
                    pueblaYModoYVoz(oc, tema, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
                    break;
                case haciaLaVoz:
                    pueblaYVoz(oc, temaAum, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
                    break;
                case haciaElModo:
                    pueblaYModo(oc, temaAum, temaIrr, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
            }
            if (irrVerbo.isPats()) {
                pueblaPersonas(oc, temaAum, irrVerbo.getVoz(), irrVerbo.getModo(), Tiempo.Imperfecto, irrVerbo.getFuerte(),
                        irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(), Tiempo.Imperfecto,
                        false, localList, irrVerbo.getSubPart());
                pueblaYModoYVoz(oc, temaAum, irrVerbo.getVoz(), irrVerbo.getModo(), Tiempo.Imperfecto, irrVerbo.getFuerte(),
                        irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(), Tiempo.Imperfecto,
                        false, localList, irrVerbo.getSubPart());
            }
        }
        A(oc, paramList1, Aspecto.Infectivo, paramList2);
    }

    private void A(Ocurrencia paramC, List<IrrVerboIndividual> paramList, Aspecto paramk, List<String> paramList1) {
        Iterator<IrrVerboIndividual> localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            IrrVerboIndividual localq = localIterator.next();
            List<String> localList = paramList1;
            if (localq.isCompuesto()) {
                localList = null;
            }
            String str = localq.getForma();
            str = this.preposiciones.une(str, localList, !localq.isRespetaAcento());
            paramC.agregaFormaIndividual(localq.getVoz(), localq.getModo(), localq.getTiempo(), localq.getFuerte(),
                    localq.getPersona(), str, localq.getSubPart());
        }
    }

    private void B(Ocurrencia paramC, List<IrrVerbo> paramList, List<IrrVerboIndividual> paramList1, List<String> paramList2) {
        Iterator<IrrVerbo> localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            IrrVerbo irrVerbo = (IrrVerbo) localIterator.next();
            List<String> localList = paramList2;
            if (irrVerbo.isCompuesto()) {
                localList = null;
            }
            String str = irrVerbo.getTema();
            pueblaPersonas(paramC, str, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                    irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(), irrVerbo.getTiempoJuego(),
                    false, localList, irrVerbo.getSubPart());
            switch (irrVerbo.getPropagacion()) {
                case Ninguna:
                    break;
                case haciaModoYVoz:
                    pueblaYModoYVoz(paramC, str, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
                    break;
                case haciaLaVoz:
                    pueblaYVoz(paramC, str, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
                    break;
                case haciaElModo:
                    pueblaYModo(paramC, str, str, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
            }
        }
        A(paramC, paramList1, Aspecto.Futuro, paramList2);
    }

    private void A(Ocurrencia paramC, List<IrrVerbo> paramList, List<IrrVerboIndividual> paramList1,
            List<String> paramList2) {
        Iterator<IrrVerbo> localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            IrrVerbo irrVerbo = (IrrVerbo) localIterator.next();
            List<String> localList = paramList2;
            if (irrVerbo.isCompuesto()) {
                localList = null;
            }
            String str1 = OpPalabras.aumenta(irrVerbo.getTema(), irrVerbo.getAumento());
            String str2 = irrVerbo.getTema();
            String str3 = irrVerbo.getModo().equals(Modo.Indicativo) ? str1 : str2;
            pueblaPersonas(paramC, str3, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                    irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(), irrVerbo.getTiempoJuego(),
                    false, localList, irrVerbo.getSubPart());
            switch (irrVerbo.getPropagacion()) {
                case Ninguna:
                    break;
                case haciaModoYVoz:
                    pueblaYModoYVoz(paramC, str3, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
                    break;
                case haciaLaVoz:
                    pueblaYVoz(paramC, str1, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
                    break;
                case haciaElModo:
                    pueblaYModo(paramC, str1, str2, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), false, localList, irrVerbo.getSubPart());
            }
        }
        A(paramC, paramList1, Aspecto.Confectivo, paramList2);
    }

    private void A(Ocurrencia paramC, List<IrrVerbo> paramList, List<IrrVerboIndividual> paramList1, int tipo,
            List<String> paramList2) {
        Iterator<IrrVerbo> localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            IrrVerbo irrVerbo = (IrrVerbo) localIterator.next();
            List<String> localList = paramList2;
            if (irrVerbo.isCompuesto()) {
                localList = null;
            }
            String temaIrr = irrVerbo.getTema();
            if (irrVerbo.isReduplicacion()) {
                temaIrr = OpPalabras.reduplica(temaIrr);
            }
            String str2 = OpPalabras.aumenta(temaIrr, irrVerbo.getAumento());
            String str3 = irrVerbo.getModo().equals(Modo.Indicativo) ? str2 : temaIrr;
            boolean liquidoEnS = (TiposVerbo.esLiquido(tipo)) && (temaIrr.charAt(temaIrr.length() - 1) == 'σ');
            pueblaPersonas(paramC, temaIrr, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                    irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(), irrVerbo.getTiempoJuego(),
                    liquidoEnS, localList, irrVerbo.getSubPart());
            switch (irrVerbo.getPropagacion()) {
                case Ninguna:
                    break;
                case haciaModoYVoz:
                    pueblaYModoYVoz(paramC, str3, irrVerbo.getVoz(), irrVerbo.getModo(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), liquidoEnS, localList, irrVerbo.getSubPart());
                    break;
                case haciaLaVoz:
                    pueblaYVoz(paramC, temaIrr, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), liquidoEnS, localList, irrVerbo.getSubPart());
                    break;
                case haciaElModo:
                    pueblaYModo(paramC, str2, temaIrr, irrVerbo.getVoz(), irrVerbo.getTiempo(), irrVerbo.getFuerte(),
                            irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                            irrVerbo.getTiempoJuego(), liquidoEnS, localList, irrVerbo.getSubPart());
            }
            if (irrVerbo.isPats()) {
                pueblaPersonas(paramC, str2, irrVerbo.getVoz(), irrVerbo.getModo(), Tiempo.Pluscuamperfecto, irrVerbo.getFuerte(),
                        irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                        Tiempo.Pluscuamperfecto, liquidoEnS, localList, irrVerbo.getSubPart());
                pueblaYModoYVoz(paramC, str2, irrVerbo.getVoz(), irrVerbo.getModo(), Tiempo.Pluscuamperfecto, irrVerbo.getFuerte(),
                        irrVerbo.getContraccion(), irrVerbo.getJuego(), irrVerbo.getVozJuego(),
                        Tiempo.Pluscuamperfecto, liquidoEnS, localList, irrVerbo.getSubPart());
            }
        }
        A(paramC, paramList1, Aspecto.Perfectivo, paramList2);
    }

    private void A(Ocurrencia paramC, Object[][] paramArrayOfObject, String paramString, int paramInt, Voz paramZ1,
            Voz paramZ2, boolean paramBoolean1, Contraccion paramc, Tiempo paramj, boolean paramBoolean2,
            List<String> paramList) {
        A(paramC, paramArrayOfObject, paramString, paramString, paramInt, paramZ1, paramZ2, paramBoolean1, paramc,
                paramj, paramBoolean2, paramList);
    }

    private void A(Ocurrencia paramC, Object[][] paramArrayOfObject, String paramString1, String paramString2,
            int paramInt, Voz paramZ1, Voz paramZ2, boolean paramBoolean1, Contraccion paramc, Tiempo paramj,
            boolean paramBoolean2, List<String> paramList) {
        for (int i = Voz.getInt(paramZ1); i <= Voz.getInt(paramZ2); i++) {
            Voz localZ1 = Voz.getEnum(i);
            if ((!paramBoolean1) || (!localZ1.equals(Voz.Media))) {
                Voz localZ2;
                if ((paramBoolean1) && (localZ1.equals(Voz.Activa))) {
                    localZ2 = Voz.Media;
                } else {
                    localZ2 = localZ1;
                }
                String str = null;
                if (localZ1 == Voz.Pasiva) {
                    str = paramString2;
                } else {
                    str = paramString1;
                }
                for (int j = 0; j < paramArrayOfObject.length; j++) {
                    Modo localp = (Modo) paramArrayOfObject[j][0];
                    Tiempo localj = (Tiempo) paramArrayOfObject[j][1];
                    if (paramj == null) {
                        paramj = localj;
                    }
                    pueblaPersonas(paramC, str, localZ1, localp, localj, FuerteDebil.Debil, paramc, paramInt, localZ2, paramj,
                            paramBoolean2, paramList, 0);
                }
            }
        }
    }

    private void pueblaPersonas(Ocurrencia oc, String tema, Voz voz, Modo modo, Tiempo tiempo, FuerteDebil fuerte, Contraccion contraccion, int paramInt1, Voz jvoz, Tiempo jtie, boolean liquidoEnS, List<String> paramList, int paramInt2) {
        for (Persona local_ : Persona.values()) {
            Desinencia[] desinencias = this.manejaDesinenciasVerbo.getDesinencias(paramInt1, jvoz, modo, jtie, fuerte, local_);
            if (desinencias != null) {
                for (int k = 0; k < desinencias.length; k++)
                {
                    String des = desinencias[k].cadena;
                    Verbos.Comedor.come(liquidoEnS, tema, des);
                    tema = Comedor.temaComido;
                    des = Comedor.desinenciaComida;
                    int m = desinencias[k].getPosicion();
                    Acento localE = desinencias[k].getTipoAcento();
                    if (des.equals("&"))
                    {
                        oc.agregaFormaIndividual(voz, modo, tiempo, fuerte, local_, this.preposiciones.une(tema, paramList, true), paramInt2);
                        break;
                    }
                    String str2 = OpPalabras.contraeGenerica(tema, des, contraccion, m, localE);
                    if (str2 != null)
                    {
                        boolean bool = (m == 0) && (contraccion != Contraccion.vocalica);
                        str2 = this.preposiciones.une(str2, paramList, bool);
                        oc.agregaFormaIndividual(voz, modo, tiempo, fuerte, local_, str2, paramInt2);
                    }
                }
            }
        }
    }

    private void pueblaYModoYVoz(Ocurrencia paramC, String paramString, Voz paramZ1, Modo paramp, Tiempo paramj1, FuerteDebil paramP,
            Contraccion paramc, int paramInt1, Voz paramZ2, Tiempo paramj2, boolean liquidoEnS,
            List<String> paramList, int paramInt2) {
        int i = (paramZ1.equals(Voz.Activa)) && (paramZ2.equals(Voz.Media)) ? 1 : 0;
        if ((i == 0) && (paramZ1 == Voz.Activa)) {
            pueblaPersonas(paramC, paramString, Voz.Media, paramp, paramj1, paramP, paramc, paramInt1, Voz.Media, paramj2,
                    liquidoEnS, paramList, paramInt2);
        }
    }

    private void pueblaYModo(Ocurrencia oc, String paramString1, String paramString2, Voz paramZ1, Tiempo paramj1,
            FuerteDebil paramP, Contraccion paramc, int paramInt1, Voz paramZ2, Tiempo paramj2, boolean liquidoEnS,
            List<String> paramList, int paramInt2) {
        int i = (paramZ1.equals(Voz.Activa)) && (paramZ2.equals(Voz.Media)) ? 1 : 0;
        if ((i == 0) && (paramZ1 == Voz.Activa)) {
            pueblaPersonas(oc, paramString1, Voz.Media, Modo.Indicativo, paramj1, paramP, paramc, paramInt1, Voz.Media, paramj2,
                    liquidoEnS, paramList, paramInt2);
        }
        for (int j = 2; j <= 4; j++) {
            Modo localp = Modo.getEnum(j);
            pueblaPersonas(oc, paramString2, Voz.Activa, localp, paramj1, paramP, paramc, paramInt1, paramZ2, paramj2,
                    liquidoEnS, paramList, paramInt2);
            pueblaYModoYVoz(oc, paramString2, paramZ1, localp, paramj1, paramP, paramc, paramInt1, paramZ2, paramj2,
                    liquidoEnS, paramList, paramInt2);
        }
    }

    private void pueblaYVoz(Ocurrencia paramC, String paramString, Voz paramZ1, Tiempo tiempo, FuerteDebil fuerte,
            Contraccion contraccion, int paramInt1, Voz paramZ2, Tiempo paramj2, boolean paramBoolean,
            List<String> paramList, int paramInt2) {
        for (int i = 2; i <= 4; i++) {
            Modo modo = Modo.getEnum(i);
            pueblaPersonas(paramC, paramString, paramZ1, modo, tiempo, fuerte, contraccion, paramInt1, paramZ2, paramj2, paramBoolean,
                    paramList, paramInt2);
        }
    }

    public Ocurrencia conjuga(VerboBean verboBean, Particularidad partic) {
        return conjuga(verboBean, partic, true);
    }

    public Ocurrencia conjuga(VerboBean verboBean, Particularidad partic, boolean paramBoolean) {
        Ocurrencia oc = null;
        if (paramBoolean) {
            oc = this.L.getOcurrencia(verboBean.getId(), partic);
            if (oc != null) {
                return oc;
            }
        }
        oc = new Ocurrencia();
        VerboBean bean = verboBean;
        List<String> preps = null;
        if (verboBean.isCompuesto()) {
            VerboSimpleCompuesto localH = this.E.seleccionaPorVerboCompuesto(verboBean.getId());
            bean = this.gerenteVerbos.seleccionaIndividualSinSignificado(localH.getIdVerboSimple());
            if (verboBean.isCompuesto()) {
                preps = this.gerPreposicionesEnVerbos.seleccionaPorVerbo(verboBean.getId());
                for (int i = 0; i < preps.size(); i++) {
                    preps.set(i, OpPalabras.strBetaACompleto((String) preps.get(i)));
                }
            }
        }
        if ((!bean.isDibujado()) && (partic.equals(bean.getParticularidad()))) {
            conjugaPorDefecto(oc, bean, preps);
        }
        pueblaIrrs(oc, bean, partic, preps);
        if (verboBean.isCompuesto()) {
            pueblaIrrs(oc, verboBean, partic, null);
        }
        this.L.setOcurrencia(verboBean.getId(), partic, oc);
        return oc;
    }

    private void pueblaIrrs(Ocurrencia oc, VerboBean verboBean, Particularidad partic, List<String> preps) {
        List<IrrVerbo> irrsVerbo = this.gerIrrVerbos.seleccionaPorVerboPartic(verboBean.getId(), partic);
        for (IrrVerbo irrVerbo : irrsVerbo) {
            irrVerbo.setTema(OpPalabras.strBetaACompleto(irrVerbo.getTema()));
        }
        List<IrrVerboIndividual> irrsVerboIndiv = this.getIrrVerbosIndiv.seleccionaPorVerboPartic(verboBean.getId(), partic);
        for (IrrVerboIndividual irrVerboIndividual : irrsVerboIndiv) {
            irrVerboIndividual.setForma(OpPalabras.strBetaACompleto(irrVerboIndividual.getForma()));
        }
        List<IrrVerbo> localArrayList1 = new ArrayList<IrrVerbo>();
        List<IrrVerbo> localArrayList2 = new ArrayList<IrrVerbo>();
        List<IrrVerbo> localArrayList3 = new ArrayList<IrrVerbo>();
        List<IrrVerbo> localArrayList4 = new ArrayList<IrrVerbo>();
        List<IrrVerboIndividual> localArrayList5 = new ArrayList<IrrVerboIndividual>();
        List<IrrVerboIndividual> localArrayList6 = new ArrayList<IrrVerboIndividual>();
        List<IrrVerboIndividual> localArrayList7 = new ArrayList<IrrVerboIndividual>();
        List<IrrVerboIndividual> localArrayList8 = new ArrayList<IrrVerboIndividual>();
        distribuyeIrregularidades(irrsVerbo, irrsVerboIndiv, localArrayList1, localArrayList2, localArrayList3,
                localArrayList4, localArrayList5, localArrayList6, localArrayList7, localArrayList8);
        C(oc, localArrayList1, localArrayList5, preps);
        B(oc, localArrayList2, localArrayList6, preps);
        A(oc, localArrayList3, localArrayList7, preps);
        A(oc, localArrayList4, localArrayList8, verboBean.getTipoVerbo(), preps);
    }

    public void distribuyeIrregularidades(List<IrrVerbo> irrsVerbo, List<IrrVerboIndividual> irrsVerboIndiv,
            List<IrrVerbo> irrsVerboInfectivo, List<IrrVerbo> irrsVerboFuturo, List<IrrVerbo> irrsVerboConfectivo, List<IrrVerbo> irrsVerboPerfectivo,
            List<IrrVerboIndividual> irrsVerboIndivInfectivo, List<IrrVerboIndividual> irrsVerboIndivFuturo,
            List<IrrVerboIndividual> irrsVerboIndivConfectivo, List<IrrVerboIndividual> irrsVerboIndivPerfectivo) {

        for (IrrVerbo irrVerbo : irrsVerbo) {
            switch (irrVerbo.getTiempo()) {
                case Presente:
                case Imperfecto:
                    irrsVerboInfectivo.add(irrVerbo);
                    break;
                case Futuro:
                    irrsVerboFuturo.add(irrVerbo);
                    break;
                case Aoristo:
                    irrsVerboConfectivo.add(irrVerbo);
                    break;
                case Perfecto:
                case Pluscuamperfecto:
                    irrsVerboPerfectivo.add(irrVerbo);
                    break;
            }
        }

        for (IrrVerboIndividual irrVerboIndiv : irrsVerboIndiv) {
            switch (irrVerboIndiv.getTiempo()) {
                case Presente:
                case Imperfecto:
                    irrsVerboIndivInfectivo.add(irrVerboIndiv);
                    break;
                case Futuro:
                    irrsVerboIndivFuturo.add(irrVerboIndiv);
                    break;
                case Aoristo:
                    irrsVerboIndivConfectivo.add(irrVerboIndiv);
                    break;
                case Perfecto:
                case Pluscuamperfecto:
                    irrsVerboIndivPerfectivo.add(irrVerboIndiv);
                    break;
            }
        }

    }

    public GerenteIrrVerbos getGerenteIrrVerbos() {
        return this.gerIrrVerbos;
    }

    public void setGerenteIrrVerbos(GerenteIrrVerbos paramy) {
        this.gerIrrVerbos = paramy;
    }

    public GerenteVerbos getGerenteVerbos() {
        return this.gerenteVerbos;
    }

    public void setGerenteVerbos(GerenteVerbos paramP) {
        this.gerenteVerbos = paramP;
    }

    public GerenteTiposVerbo getGerenteTiposVerbo() {
        return this.S;
    }

    public void setGerenteTiposVerbo(GerenteTiposVerbo paramhA) {
        this.S = paramhA;
        this.N = this.S.getMapaTiposID();
    }

    public GerenteIrrVerbosIndividuales getGerenteIrrVerbosIndividuales() {
        return this.getIrrVerbosIndiv;
    }

    public void setGerenteIrrVerbosIndividuales(GerenteIrrVerbosIndividuales paramW) {
        this.getIrrVerbosIndiv = paramW;
    }

    public void setGerentePreposicionesEnVerbos(GerentePreposicionesEnVerbos paramfA) {
        this.gerPreposicionesEnVerbos = paramfA;
    }

    public void setPreposiciones(Preposiciones paramB) {
        this.preposiciones = paramB;
    }

    public void setManejaDesinenciasVerbo(ManejaDesinenciasVerbo paramF) {
        this.manejaDesinenciasVerbo = paramF;
    }

    public void setGerenteVerbosCompuestos(GerenteVerbosCompuestos paramU) {
        this.E = paramU;
    }

    public void setCacheFlexionVerbos(CacheFlexionVerbos paramC) {
        this.L = paramC;
    }

    public CacheFlexionVerbos getCacheFlexionVerbos() {
        return this.L;
    }

    public static class Comedor {

        static String temaComido;
        static String desinenciaComida;

        static void come(boolean liquidoEnS, String tema, String des) {
            temaComido = tema;
            desinenciaComida = des;
            if (liquidoEnS) {
                int i = tema.charAt(tema.length() - 1);
                int j = des.charAt(0);
                if (i == 963) {
                    if (j == 963) {
                        desinenciaComida = des.substring(1);
                    }
                } else if ((i == 956) && (j != 956)) {
                    temaComido = tema.substring(0, tema.length() - 1);
                }
            }
        }
    }
}
