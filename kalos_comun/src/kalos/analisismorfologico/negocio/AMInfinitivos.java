package kalos.analisismorfologico.negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kalos.beans.IrrInfinitivoBean;
import kalos.beans.ResultadoUniversal;
import kalos.beans.TermRegInfinitivo;
import kalos.beans.TermRegVerbal;
import kalos.beans.VerboBean;
import kalos.datos.gerentes.GerenteIrrInfinitivos;
import kalos.datos.gerentes.GerenteTermRegInfinitivo;
import kalos.datos.gerentes.GerenteVerbos;
import kalos.enumeraciones.Acento;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.TipoPalabra;
import kalos.enumeraciones.Voz;
import kalos.operaciones.AACacheable;
import kalos.operaciones.AnalisisAcento;
import kalos.operaciones.OpBeans;
import kalos.operaciones.OpPalabras;

public class AMInfinitivos implements AnalizadorMorfologico {
    private AMUtil amUtil;
    private AMVerbal amVerbal;
    private GerenteTermRegInfinitivo gerenteTermRegInfinitivo;
    private GerenteIrrInfinitivos gerenteIrrInfinitivos;
    private GerenteVerbos gerenteVerbos;
    private ExtractorPrefijos extractorPrefijos;

    public long buscaCanonica(String[] entradas, Set<ResultadoUniversal> resultados, AACacheable cacheAA,
	    boolean paramBoolean1, boolean paramBoolean2) {
	Set<TermRegInfinitivo> setPaso1=new LinkedHashSet<TermRegInfinitivo>();
	Set<TermRegInfinitivo> termsRegInf = new LinkedHashSet<TermRegInfinitivo>();
	Set<TermRegInfinitivo> localLinkedHashSet3 = new LinkedHashSet<TermRegInfinitivo>();
	Set<ObjYDest> localLinkedHashSet4 = new LinkedHashSet<ObjYDest>();
	Set<TermRegInfinitivo> localLinkedHashSet5 = new LinkedHashSet<TermRegInfinitivo>();
	long l1 = System.currentTimeMillis();
	Map<Object[],TemaConPreps[]> localHashMap1 = new HashMap<Object[],TemaConPreps[]>();
	Map<TemaConPreps, HashSet<TermRegInfinitivo>> localHashMap2 = new HashMap<TemaConPreps, HashSet<TermRegInfinitivo>>();
	Set<String> setEntradas = new HashSet<String>(Arrays.asList(entradas));
	List<ObjYDest> localArrayList1 = new ArrayList<ObjYDest>();
	List<TermRegVerbal> localArrayList2 = new ArrayList<TermRegVerbal>();
	paso1(setEntradas, setPaso1, localHashMap1, cacheAA, paramBoolean2);
	this.amVerbal.extiendeTipos(setPaso1, termsRegInf, paramBoolean2);
	this.amVerbal.averiguaPreposiciones(termsRegInf, localLinkedHashSet3, 1, localHashMap1, paramBoolean2);
	this.amUtil.incorporaADestransformar(localLinkedHashSet3, paramBoolean2);
	this.amUtil.desTransformacionesTemas(localLinkedHashSet3, localLinkedHashSet4, localArrayList1, paramBoolean2);
	this.amVerbal.incorporaTemaPropuestoReconstruidos(localLinkedHashSet4, localArrayList1, paramBoolean2);
	this.amVerbal.incorporaTemaPropuestoIrregulares(localArrayList1, paramBoolean2);
	this.amUtil.paso3_5(localLinkedHashSet4, localArrayList1);
	this.amVerbal.aplicaEncuentraTemasTemprano(localLinkedHashSet4, localArrayList2, localArrayList1, paramBoolean2);
	this.amVerbal.envoltorioRestauraFormas(localLinkedHashSet4, localLinkedHashSet5, cacheAA, paramBoolean2);
	this.amUtil.agrupaTemasEnSet(localLinkedHashSet5, localHashMap2, paramBoolean2);
	setPaso1 = null;
	termsRegInf = null;
	localLinkedHashSet3 = null;
	localLinkedHashSet4 = null;
	localLinkedHashSet5 = null;
	localArrayList1 = null;
	this.amVerbal.buscaReconstruidaEnTablas(resultados, localHashMap2, false, true, paramBoolean2);
	this.amVerbal.buscaReconstruidaEnTablas(resultados, localHashMap2, true, true, paramBoolean2);
	this.amVerbal.comparaConTemasSemirreconstruidos(resultados, localArrayList2, true, paramBoolean2);
	buscaIrrIndivConjugacion(resultados, entradas, cacheAA, paramBoolean2);
	this.amVerbal.pueblaCanonicasVerbos(resultados);
	long l2 = System.currentTimeMillis();
	long l3 = l2 - l1;
	if (paramBoolean2) {
	    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
	    localGregorianCalendar.setTimeInMillis(l3);
	    System.out.println("tardó " + localGregorianCalendar.get(12) + " minutos " + localGregorianCalendar.get(13)
		    + " segundos " + localGregorianCalendar.get(14) + " milisegundos");
	}
	return l3;
    }

    public void buscaIrrIndivConjugacion(Set<ResultadoUniversal> paramSet, String[] paramArrayOfString, AACacheable cacheAA,
	    boolean paramBoolean) {
	Map<Object[], TemaConPreps[]> cacheExtraccionPreps = new HashMap<Object[], TemaConPreps[]>();
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    int j = 0;
	    TemaConPreps[] tcps = this.extractorPrefijos.averiguaPrefijos(paramArrayOfString[i], j, cacheExtraccionPreps);
	    for (int k = 0; k < tcps.length; k++) {
		String str1 = tcps[k].resto;
		AnalisisAcento localD = cacheAA.getAnalisisAcento(str1);
		if (localD.sugeridos.tipoAcento.equals(Acento.Ninguno)) {
		    str1 = OpPalabras.acentua(str1);
		}
		str1 = OpPalabras.strCompletoABeta(str1);
		if (paramBoolean) {
		    System.out.println("buscando " + str1);
		}
		List<IrrInfinitivoBean> localList = this.gerenteIrrInfinitivos.seleccionaPorForma(str1);
		Map<String, VerboBean> localHashMap2 = new HashMap<String, VerboBean>();
		Iterator<IrrInfinitivoBean> localIterator = localList.iterator();
		while (localIterator.hasNext()) {
		    IrrInfinitivoBean localy = localIterator.next();
		    String str2 = (String) OpBeans.getPropiedadObject(localy, "verboId");
		    VerboBean localh = localHashMap2.get(str2);
		    if (localh == null) {
			localh = this.gerenteVerbos.seleccionaIndividualSinSignificado(str2);
			localHashMap2.put(str2, localh);
		    }
		    String str3 = paramArrayOfString[i];
		    Particularidad localX1 = (Particularidad) OpBeans.getPropiedadObject(localy, "partic");
		    Particularidad localX2 = localh.getParticularidad();
		    Voz localZ = (Voz) OpBeans.getPropiedadObject(localy, "voz");
		    Aspecto localk = null;
		    Persona local_ = null;
		    Tiempo localj = null;
		    Modo localp = null;
		    FuerteDebil localP = null;
		    localk = (Aspecto) OpBeans.getPropiedadObject(localy, "aspecto");
		    localP = (FuerteDebil) OpBeans.getPropiedadObject(localy, "fuerte");
		    ResultadoUniversal localj1 = new ResultadoUniversal(TipoPalabra.Infinitivo, str2, null, localX2, localX1, localZ, str3, localj, localk,
			    localP, local_, null, null, null, localp, null, null, tcps[k].preps, null);
		    paramSet.add(localj1);
		}
	    }
	}
	if (paramBoolean) {
	    System.out
		    .println("buscaIrrIndivConjugacion: así queda el resultado después la búsqueda de irregularidades individuales en verbos amVerbal infinitivos ****************************");
	    this.amUtil.debugBeans(paramSet, new String[] { "formaCanonica", "formaAccidentada" });
	}
    }

    private void paso1(Set<String> paramSet, Set<TermRegInfinitivo> paramSet1, Map<Object[], TemaConPreps[]> paramHashMap, AACacheable paramB,
	    boolean paramBoolean) {
	StringBuffer localStringBuffer = new StringBuffer();
	Iterator<String> localIterator1 = paramSet.iterator();
	while (localIterator1.hasNext()) {
	    String str1 = localIterator1.next();
	    String str2 = OpPalabras.strCompletoABeta(OpPalabras.desacentuar(str1));
	    List<TermRegInfinitivo> localList = this.gerenteTermRegInfinitivo.seleccionaPorTerminacion(str2);
	    Iterator<TermRegInfinitivo> localIterator2 = localList.iterator();
	    while (localIterator2.hasNext()) {
		TermRegInfinitivo localW = localIterator2.next();
		String str3 = localW.getRegEx();
		if ((str3 != null) && (!str2.matches(str3))) {
		    if (paramBoolean) {
			localStringBuffer.append(" la terminación de este registro coincide, pero no así la REGEXP \n");
			localStringBuffer.append(localW.getTerminacion());
		    }
		} else if (!this.amVerbal.silabaAcentoAceptables(str1, localW, paramHashMap, paramB)) {
		    if (paramBoolean) {
			localStringBuffer
				.append(" la terminación de este registro coincide, pero no así los campos SILABA-ACENTO \n");
			localStringBuffer.append(localW.getTerminacion());
		    }
		} else {
		    localW.setFormaOriginal(str1);
		    paramSet1.add(localW);
		}
	    }
	}
	if (paramBoolean) {
	    System.out.println("después de la búsqueda de terminaciones de infinitivo (paso 1) *********************");
	    System.out.println(localStringBuffer.toString());
	    this.amUtil.debugBeans(paramSet1, new String[0]);
	}
    }

    public AMUtil getAmUtil() {
	return this.amUtil;
    }

    public void setAmUtil(AMUtil paramC) {
	this.amUtil = paramC;
    }

    public AMVerbal getAmVerbal() {
	return this.amVerbal;
    }

    public void setAmVerbal(AMVerbal paramP) {
	this.amVerbal = paramP;
    }

    public GerenteTermRegInfinitivo getGerenteTermRegInfinitivo() {
	return this.gerenteTermRegInfinitivo;
    }

    public void setGerenteTermRegInfinitivo(GerenteTermRegInfinitivo paramIA) {
	this.gerenteTermRegInfinitivo = paramIA;
    }

    public void setExtractorPrefijos(ExtractorPrefijos paramN) {
	this.extractorPrefijos = paramN;
    }

    public void setGerenteIrrInfinitivos(GerenteIrrInfinitivos paramaA) {
	this.gerenteIrrInfinitivos = paramaA;
    }

    public void setGerenteVerbos(GerenteVerbos paramP) {
	this.gerenteVerbos = paramP;
    }
}
