// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.// Jad home page: http://www.kpdus.com/jad.html// Decompiler options: packimports(3) package kalos.visual.controles;import kalos.A.B.F.B.C;import kalos.C.F;import kalos.E.C.dA;import kalos.E.E.DA;import kalos.E.E.h;import kalos.E.E.hA;import kalos.beans.TipoJerarquico;import kalos.bibliotecadatos.JerarquiaBeans;import kalos.datos.dao.CombosDAO;import kalos.datos.gerentes.GerenteTiposAdjetivo;import kalos.datos.gerentes.GerenteTiposSustantivo;import kalos.datos.gerentes.GerenteTiposVerbo;import kalos.recursos.Recursos;public class FabricaControles{    public void setCombosDAO(CombosDAO da)    {        combosDAO = da;    }    public C getSelectorTiposVerbo(boolean flag, boolean flag1, boolean flag2)    {        java.util.List list = gerenteTiposVerbo.getTodos();        kalos.J.A a = new kalos.J.A(list, "padreId");        C c = new C(flag, flag1, flag2, a, false, Recursos.getCadena("tipo_de_verbo"));        return c;    }    public C getSelectorTiposAdjetivo(boolean flag, boolean flag1, boolean flag2)    {        java.util.List list = gerenteTiposAdjetivo.getTodos();        JerarquiaBeans<TipoJerarquico> a = new JerarquiaBeans<TipoJerarquico>(list, "padreId");        C c = new C(flag, flag1, flag2, a, false, Recursos.getCadena("tipo_adjetivo"));        return c;    }    public C getSelectorTiposSustantivo(boolean flag, boolean flag1, boolean flag2)    {        java.util.List list = gerenteTiposSustantivo.getTodos();        kalos.J.A a = new kalos.J.A(list, "padreId");        C c = new C(flag, flag1, flag2, a, false, Recursos.getCadena("tipo_sustantivo"));        return c;    }    public kalos.A.B.J.C creaCombo(String s)    {        dA da = combosDAO;        java.util.List list = da.getPorClave(s);        kalos.A.B.J.C c = new kalos.A.B.J.C(list);        return c;    }    public void setGerenteTiposVerbo(GerenteTiposVerbo gtv)    {        gerenteTiposVerbo = gtv;    }    public void setGerenteTiposAdjetivo(GerenteTiposAdjetivo gta)    {        gerenteTiposAdjetivo = gta;    }    public void setGerenteTiposSustantivo(GerenteTiposSustantivo gts)    {        gerenteTiposSustantivo = gts;    }    private CombosDAO combosDAO;    private GerenteTiposAdjetivo gerenteTiposAdjetivo;    private GerenteTiposSustantivo gerenteTiposSustantivo;    private GerenteTiposVerbo gerenteTiposVerbo;}