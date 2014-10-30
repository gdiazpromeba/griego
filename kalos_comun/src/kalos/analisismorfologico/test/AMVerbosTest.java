package kalos.D.A;import java.io.PrintStream;import java.util.ArrayList;import java.util.Arrays;import java.util.GregorianCalendar;import java.util.HashMap;import java.util.HashSet;import java.util.Iterator;import java.util.LinkedHashSet;import java.util.List;import java.util.Map;import java.util.Set;import kalos.B.E;import kalos.B.X;import kalos.B.Z;import kalos.B._;import kalos.B.k;import kalos.B.l;import kalos.B.p;import kalos.E.E.W;import kalos.E.E.ZA;import kalos.G.B;import kalos.G.D;import kalos.G.D._A;import kalos.G.D._B;import kalos.K.h;import kalos.K.q;import kalos.K.t;public class I  implements K{  private C n;  private P i;  private ZA k;  private kalos.H.B.A m;  private kalos.E.E.P j;  private W h;  private N l;    public long buscaCanonica(String[] paramArrayOfString, HashSet<kalos.K.j> paramHashSet, B paramB, boolean paramBoolean1, boolean paramBoolean2)  {    long l1 = System.currentTimeMillis();    LinkedHashSet localLinkedHashSet1 = new LinkedHashSet();    LinkedHashSet localLinkedHashSet2 = new LinkedHashSet();    LinkedHashSet localLinkedHashSet3 = new LinkedHashSet();    LinkedHashSet localLinkedHashSet4 = new LinkedHashSet();    LinkedHashSet localLinkedHashSet5 = new LinkedHashSet();    HashMap localHashMap1 = new HashMap();    HashMap localHashMap2 = new HashMap();    HashSet localHashSet = new HashSet(Arrays.asList(paramArrayOfString));    ArrayList localArrayList1 = new ArrayList();    ArrayList localArrayList2 = new ArrayList();    A(localHashSet, localLinkedHashSet1, localHashMap1, paramB, paramBoolean2);    this.i.extiendeTipos(localLinkedHashSet1, localLinkedHashSet2, paramBoolean2);    this.i.averiguaPreposiciones(localLinkedHashSet2, localLinkedHashSet3, 1, localHashMap1, paramBoolean2);    this.n.incorporaADestransformar(localLinkedHashSet3, paramBoolean2);    this.n.desTransformacionesTemas(localLinkedHashSet3, localLinkedHashSet4, localArrayList1, paramBoolean2);    this.i.incorporaTemaPropuestoReconstruidos(localLinkedHashSet4, localArrayList1, paramBoolean2);    this.i.incorporaTemaPropuestoIrregulares(localArrayList1, paramBoolean2);    this.n.paso3_5(localLinkedHashSet4, localArrayList1);    this.i.aplicaEncuentraTemasTemprano(localLinkedHashSet4, localArrayList2, localArrayList1, paramBoolean2);    this.i.envoltorioRestauraFormas(localLinkedHashSet4, localLinkedHashSet5, paramB, paramBoolean2);    this.n.agrupaTemasEnSet(localLinkedHashSet5, localHashMap2, paramBoolean2);    localLinkedHashSet1 = null;    localLinkedHashSet2 = null;    localLinkedHashSet3 = null;    localLinkedHashSet4 = null;    localLinkedHashSet5 = null;    localArrayList1 = null;    this.i.buscaReconstruidaEnTablas(paramHashSet, localHashMap2, false, false, paramBoolean2);    this.i.buscaReconstruidaEnTablas(paramHashSet, localHashMap2, true, false, paramBoolean2);    this.i.comparaConTemasSemirreconstruidos(paramHashSet, localArrayList2, false, paramBoolean2);    A(paramHashSet, paramArrayOfString, paramB, paramBoolean2);    this.i.pueblaCanonicasVerbos(paramHashSet);    if (paramBoolean1) {      D(paramHashSet);    }    long l2 = System.currentTimeMillis();    long l3 = l2 - l1;    if (paramBoolean2)    {      GregorianCalendar localGregorianCalendar = new GregorianCalendar();      localGregorianCalendar.setTimeInMillis(l3);      System.out.println("tardó " + localGregorianCalendar.get(12) + " minutos " + localGregorianCalendar.get(13) + " segundos " + localGregorianCalendar.get(14) + " milisegundos");    }    return l3;  }    private void D(Set<kalos.K.j> paramSet)  {    Iterator localIterator = paramSet.iterator();    while (localIterator.hasNext())    {      kalos.K.j localj = (kalos.K.j)localIterator.next();      h localh = this.j.seleccionaUno(localj.getIdSimpleOCompuesto());      kalos.H.B.A.C localC = this.m.conjuga(localh, localj.getParticularidad());      List localList = localC.getFormas(localj.getVoz(), localj.getModo(), localj.getTiempo(), localj.getFuerte(), localj.getPersona());      if (localList == null) {        localIterator.remove();      } else if (!localList.contains(localj.getFormaAccidentada())) {        localIterator.remove();      }    }  }    private void A(Set<String> paramSet, Set<t> paramSet1, HashMap<Object[], M[]> paramHashMap, B paramB, boolean paramBoolean)  {    StringBuffer localStringBuffer = new StringBuffer();    Iterator localIterator1 = paramSet.iterator();    while (localIterator1.hasNext())    {      String str1 = (String)localIterator1.next();      String str2 = kalos.G.I.strCompletoABeta(str1);      String str3 = kalos.G.I.strCompletoABeta(kalos.G.I.desacentuar(str1));      List localList = this.k.seleccionaPorTerminacion(str3);      Iterator localIterator2 = localList.iterator();      while (localIterator2.hasNext())      {        t localt = (t)localIterator2.next();        String str4 = localt.getRegEx();        if ((str4 != null) && (!str2.matches(str4)))        {          if (paramBoolean)          {            localStringBuffer.append("la terminación del tipo de desinencia " + localt.getTipoDesinencia() + " es '" + localt.getTerminacion() + "' y coincide,");            localStringBuffer.append(" pero no así la REGEXP " + localt.getRegEx() + " \n");          }        }        else if (!this.i.silabaAcentoAceptables(str1, localt, paramHashMap, paramB))        {          if (paramBoolean)          {            localStringBuffer.append("la terminación del tipo de desinencia " + localt.getTipoDesinencia() + " es '" + localt.getTerminacion() + "' y coincide,");            localStringBuffer.append(" pero no así los campos sílaba-acento \n");          }        }        else        {          localt.setFormaOriginal(str1);          paramSet1.add(localt);        }      }    }    if (paramBoolean)    {      System.out.println("después de la búsqueda de terminaciones (paso 1) *********************");      System.out.println("  rechazados ***");      System.out.println(localStringBuffer.toString());      System.out.println("  creados ***");      this.n.debugBeans(paramSet1, new String[0]);    }  }    public C getAmUtil()  {    return this.n;  }    public void setAmUtil(C paramC)  {    this.n = paramC;  }    public P getAmVerbal()  {    return this.i;  }    public void setAmVerbal(P paramP)  {    this.i = paramP;  }    public ZA getGerenteTermRegVerbo()  {    return this.k;  }    public void setGerenteTermRegVerbo(ZA paramZA)  {    this.k = paramZA;  }    public void setGerenteVerbos(kalos.E.E.P paramP)  {    this.j = paramP;  }    public void setVerbos(kalos.H.B.A paramA)  {    this.m = paramA;  }    private void A(Set<kalos.K.j> paramSet, String[] paramArrayOfString, B paramB, boolean paramBoolean)  {    HashMap localHashMap1 = new HashMap();    for (int i1 = 0; i1 < paramArrayOfString.length; i1++)    {      int i2 = 0;      M[] arrayOfM = this.l.averiguaPrefijos(paramArrayOfString[i1], i2, localHashMap1);      for (int i3 = 0; i3 < arrayOfM.length; i3++)      {        String str1 = arrayOfM[i3].B;        D localD = paramB.getAnalisisAcento(str1);        if (localD.D.B.equals(E.D)) {          str1 = kalos.G.I.acentua(str1);        }        str1 = kalos.G.I.strCompletoABeta(str1);        if (paramBoolean) {          System.out.println("buscando " + str1);        }        List localList = A(str1, paramB);        HashMap localHashMap2 = new HashMap();        Iterator localIterator = localList.iterator();        while (localIterator.hasNext())        {          q localq = (q)localIterator.next();          String str2 = (String)kalos.G.A.getPropiedadObject(localq, "verboId");          h localh = (h)localHashMap2.get(str2);          if (localh == null)          {            localh = this.j.seleccionaIndividualSinSignificado(str2);            localHashMap2.put(str2, localh);          }          String str3 = paramArrayOfString[i1];          X localX1 = (X)kalos.G.A.getPropiedadObject(localq, "partic");          X localX2 = localh.getParticularidad();          Z localZ = (Z)kalos.G.A.getPropiedadObject(localq, "voz");          k localk = null;          _ local_ = null;          kalos.B.j localj = null;          p localp = null;          kalos.B.P localP = null;          localp = (p)kalos.G.A.getPropiedadObject(localq, "modo");          local_ = (_)kalos.G.A.getPropiedadObject(localq, "persona");          localj = (kalos.B.j)kalos.G.A.getPropiedadObject(localq, "tiempo");          localP = (kalos.B.P)kalos.G.A.getPropiedadObject(localq, "fuerte");          kalos.K.j localj1 = new kalos.K.j(l.B, str2, null, localX2, localX1, localZ, str3, localj, localk, localP, local_, null, null, null, localp, null, null, arrayOfM[i3].A, null);          paramSet.add(localj1);        }      }    }    if (paramBoolean)    {      System.out.println("buscaIrrIndivConjugacion: así queda el resultado después la búsqueda de irregularidades individuales en verbos o infinitivos ****************************");      this.n.debugBeans(paramSet, new String[] { "formaCanonica", "formaAccidentada" });    }  }    private List<q> A(String paramString, B paramB)  {    List localList1 = this.h.seleccionaPorForma(paramString);    D localD = paramB.getAnalisisAcento(kalos.G.I.strBetaACompleto(paramString));    int i1 = (localD.D.A == localD.C.B) && (localD.D.B == localD.C.C) ? 1 : 0;    if (((i1 != 0) && (((paramString.endsWith("MI")) && (!paramString.endsWith("NU_MI"))) || ((paramString.endsWith("SI")) && (!paramString.endsWith("NU_SI"))))) || (paramString.endsWith("TI")))    {      String str = kalos.G.I.desacentuar(kalos.G.I.strBetaACompleto(paramString));      str = kalos.G.I.acentua(str, -1);      str = kalos.G.I.strCompletoABeta(str);      List localList2 = this.h.seleccionaPorForma(str);      Iterator localIterator = localList2.iterator();      while (localIterator.hasNext())      {        q localq = (q)localIterator.next();        int i2 = (localq.getPersona() == _.H) || (localq.getPersona() == _.B) || (localq.getPersona() == _.D) ? 1 : 0;        if ((i2 != 0) && (localq.getTiempo() == kalos.B.j.Q) && (localq.getModo() == p.C) && (localq.getVoz() == Z.D)) {          localList1.add(localq);        }      }    }    return localList1;  }    public void setGerenteIrrVerbosIndividuales(W paramW)  {    this.h = paramW;  }    public void setExtractorPrefijos(N paramN)  {    this.l = paramN;  }}