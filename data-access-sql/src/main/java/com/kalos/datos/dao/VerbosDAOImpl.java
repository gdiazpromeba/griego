// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.kalos.beans.Significado;
import com.kalos.beans.VerboBean;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.ModificaCodigoIndividual;
import com.kalos.datos.dao.comunes.ModificaCodigosTodos;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.datos.util.DBUtil;
import com.kalos.datos.util.Listas;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.recursos.Configuracion;

// Referenced classes of package kalos.E.C:
//            W

public class VerbosDAOImpl extends JdbcDaoSupport implements VerbosDAO {
    

    class Modificacion extends SqlUpdate {
	public Modificacion(DataSource datasource) {
	    super(datasource, DELETE_SQL);
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	}
    }

    class Insercion extends SqlUpdate {
	public Insercion(DataSource datasource) {
	    super(datasource, INSERT_SQL);
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	}
    }

    class SelIndivSinSig extends SeleccionAbstractaSinSignificado {
	public SelIndivSinSig(DataSource datasource) {
	    super(datasource, SEL_INDIV_SIN_SIG_SQL);
	    declareParameter(new SqlParameter(1));
	}
    }

    class SelIndividualConSignificado extends SeleccionAbstractaConSignificado {
	public SelIndividualConSignificado(DataSource datasource) {
	    super(datasource, SEL_INDIVIDUAL_CON_SIG_SQL);
	    declareParameter(new SqlParameter(1));
	}
    }

    class SelIdsPorLetra extends SeleccionIds {
	public SelIdsPorLetra(DataSource datasource) {
	    super(datasource, SEL_IDS_POR_LETRA_SQL, "VERBO_ID");
	    declareParameter(new SqlParameter(1));
	}
    }

    class SelPorFormaSinSig extends SeleccionAbstractaSinSignificado {
	public SelPorFormaSinSig(DataSource datasource) {
	    super(datasource, SEL_POR_FORMA_SIN_SIG_SQL);
	    declareParameter(new SqlParameter(12));
	}
    }

    class SelIdsPorFormaConSig extends SeleccionIds {
	public SelIdsPorFormaConSig(DataSource datasource, String s) {
	    super(datasource, s, "VERBO_ID");
	}
    }

    class SeleccionSinSignificado extends SeleccionAbstractaSinSignificado {
	public SeleccionSinSignificado(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    class SeleccionConSignificado extends SeleccionAbstractaConSignificado {
	public SeleccionConSignificado(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    class SelIdsPorTipos extends SeleccionIds {
	public SelIdsPorTipos(DataSource datasource, String s) {
	    super(datasource, s, "VERBO_ID");
	}
    }

    abstract class SeleccionAbstractaSinSignificado extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    VerboBean bean = new VerboBean();
	    bean.setId(resultset.getString("VERBO_ID"));
	    bean.setLetra(resultset.getString("LETRA"));
	    bean.setCodigo(resultset.getInt("CODIGO"));
	    bean.setVerbo(resultset.getString("VERBO"));
	    bean.setSufijo(resultset.getString("SUFIJO_CONTRACTO"));
	    bean.setParticularidad(Particularidad.getEnum(resultset.getString("PARTIC")));
	    bean.setTipoVerbo(resultset.getInt("TIPO_VERBO"));
	    bean.setIdTipo(resultset.getString("TIPO_VERBO_ID"));
	    bean.setDibujado(resultset.getInt("DIBUJADO") != 0);
	    bean.setCompuesto(resultset.getInt("COMPUESTO") != 0);
	    return bean;
	}
	public SeleccionAbstractaSinSignificado(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    abstract class SeleccionAbstractaConSignificado extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    VerboBean h1 = new VerboBean();
	    h1.setId(resultset.getString("VERBO_ID"));
	    h1.setLetra(resultset.getString("LETRA"));
	    h1.setCodigo(resultset.getInt("CODIGO"));
	    h1.setVerbo(resultset.getString("VERBO"));
	    h1.setSufijo(resultset.getString("SUFIJO_CONTRACTO"));
	    h1.setParticularidad(Particularidad.getEnum(resultset.getString("PARTIC")));
	    h1.setTipoVerbo(resultset.getInt("TIPO_VERBO"));
	    h1.setIdTipo(resultset.getString("TIPO_VERBO_ID"));
	    h1.setDibujado(resultset.getInt("DIBUJADO") != 0);
	    h1.setCompuesto(resultset.getInt("COMPUESTO") != 0);
	    Significado q1 = new Significado();
	    q1.setIdioma(Configuracion.getIdiomaSignificados());
	    q1.setReferenteId(h1.getId());
	    q1.setId(resultset.getString("SIGNIFICADO_ID"));
	    q1.setValor(resultset.getString("VALOR"));
	    HashMap hashmap = new HashMap();
	    hashmap.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), q1);
	    h1.setSignificados(hashmap);
	    return h1;
	}

	public SeleccionAbstractaConSignificado(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }


    private void puebla() {
	StringBuffer stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("    INNER JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON VER.VERBO_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.LETRA=?    \n");
	stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA='")
		.append(Configuracion.getIdiomaSignificados()).append("')    \n").toString());
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  VER.CODIGO   \n");
	SEL_IDS_POR_LETRA_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("    INNER JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON VER.VERBO_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.VERBO LIKE ?    \n");
	stringbuffer.append((new StringBuilder()).append("  AND SIG.IDIOMA='")
		.append(Configuracion.getIdiomaSignificados()).append("'    \n").toString());
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO   \n");
	SEL_IDS_POR_FORMA_CON_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID,   \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO,   \n");
	stringbuffer.append("  VER.VERBO,   \n");
	stringbuffer.append("  VER.SUFIJO_CONTRACTO,   \n");
	stringbuffer.append("  VER.PARTIC,   \n");
	stringbuffer.append("  VER.TIPO_VERBO,   \n");
	stringbuffer.append("  VER.TIPO_VERBO_ID,   \n");
	stringbuffer.append("  VER.DIBUJADO,   \n");
	stringbuffer.append("  VER.COMPUESTO   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.VERBO=?    \n");
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO   \n");
	SEL_POR_FORMA_SIN_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID,   \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO,   \n");
	stringbuffer.append("  VER.VERBO,   \n");
	stringbuffer.append("  VER.SUFIJO_CONTRACTO,   \n");
	stringbuffer.append("  VER.PARTIC,   \n");
	stringbuffer.append("  VER.TIPO_VERBO,   \n");
	stringbuffer.append("  VER.TIPO_VERBO_ID,   \n");
	stringbuffer.append("  VER.DIBUJADO,   \n");
	stringbuffer.append("  VER.COMPUESTO,   \n");
	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
	stringbuffer.append("  SIG.VALOR   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON VER.VERBO_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.VERBO_ID IN (?)    \n");
	stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='")
		.append(Configuracion.getIdiomaSignificados()).append("')    \n").toString());
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO   \n");
	SEL_CON_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID,   \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO,   \n");
	stringbuffer.append("  VER.VERBO,   \n");
	stringbuffer.append("  VER.SUFIJO_CONTRACTO,   \n");
	stringbuffer.append("  VER.PARTIC,   \n");
	stringbuffer.append("  VER.TIPO_VERBO,   \n");
	stringbuffer.append("  VER.TIPO_VERBO_ID,   \n");
	stringbuffer.append("  VER.DIBUJADO,   \n");
	stringbuffer.append("  VER.COMPUESTO   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.VERBO_ID IN (?)    \n");
	SEL_SIN_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID,   \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO,   \n");
	stringbuffer.append("  VER.VERBO,   \n");
	stringbuffer.append("  VER.SUFIJO_CONTRACTO,   \n");
	stringbuffer.append("  VER.PARTIC,   \n");
	stringbuffer.append("  VER.TIPO_VERBO,   \n");
	stringbuffer.append("  VER.TIPO_VERBO_ID,   \n");
	stringbuffer.append("  VER.DIBUJADO,   \n");
	stringbuffer.append("  VER.COMPUESTO   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.VERBO_ID=?    \n");
	SEL_INDIV_SIN_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("    INNER JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("    ON VER.VERBO_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.TIPO_VERBO IN(?)    \n");
	stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA='")
		.append(Configuracion.getIdiomaSignificados()).append("')    \n").toString());
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO   \n");
	SEL_IDS_POR_TIPO_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  VER.VERBO_ID,   \n");
	stringbuffer.append("  VER.LETRA,   \n");
	stringbuffer.append("  VER.CODIGO,   \n");
	stringbuffer.append("  VER.VERBO,   \n");
	stringbuffer.append("  VER.SUFIJO_CONTRACTO,   \n");
	stringbuffer.append("  VER.PARTIC,   \n");
	stringbuffer.append("  VER.TIPO_VERBO,   \n");
	stringbuffer.append("  VER.TIPO_VERBO_ID,   \n");
	stringbuffer.append("  VER.DIBUJADO,   \n");
	stringbuffer.append("  VER.COMPUESTO,   \n");
	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
	stringbuffer.append("  SIG.VALOR   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  VERBOS VER       \n");
	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON VER.VERBO_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VER.VERBO_ID=?    \n");
	stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='")
		.append(Configuracion.getIdiomaSignificados()).append("')    \n").toString());
	SEL_INDIVIDUAL_CON_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("INSERT INTO VERBOS (  \n");
	stringbuffer.append("  VERBO_ID,   \n");
	stringbuffer.append("  LETRA,   \n");
	stringbuffer.append("  CODIGO,   \n");
	stringbuffer.append("  VERBO,   \n");
	stringbuffer.append("  SUFIJO_CONTRACTO,   \n");
	stringbuffer.append("  PARTIC,   \n");
	stringbuffer.append("  TIPO_VERBO,   \n");
	stringbuffer.append("  TIPO_VERBO_ID,   \n");
	stringbuffer.append("  DIBUJADO,   \n");
	stringbuffer.append("  COMPUESTO   \n");
	stringbuffer.append(") VALUES (     \n");
	stringbuffer.append("  ?,?,?,?,?,?,?,?,?,?    \n");
	stringbuffer.append(" )   \n");
	INSERT_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("UPDATE VERBOS SET  \n");
	stringbuffer.append("  LETRA=?,   \n");
	stringbuffer.append("  CODIGO=?,   \n");
	stringbuffer.append("  VERBO=?,   \n");
	stringbuffer.append("  SUFIJO_CONTRACTO=?,   \n");
	stringbuffer.append("  PARTIC=?,   \n");
	stringbuffer.append("  TIPO_VERBO=?,   \n");
	stringbuffer.append("  TIPO_VERBO_ID=?,   \n");
	stringbuffer.append("  DIBUJADO=?,   \n");
	stringbuffer.append("  COMPUESTO=?   \n");
	stringbuffer.append("WHERE     \n");
	stringbuffer.append("  VERBO_ID=?   \n");
	UPDATE_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("UPDATE VERBOS SET   \n");
	stringbuffer.append("  CODIGO=CODIGO * 100   \n");
	stringbuffer.append("WHERE    \n");
	stringbuffer.append("  LETRA=?   \n");
	UPDATE_AUMENTA_CODIGO_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("UPDATE VERBOS SET   \n");
	stringbuffer.append("  CODIGO=?   \n");
	stringbuffer.append("WHERE    \n");
	stringbuffer.append("  VERBO_ID=?   \n");
	UPDATE_CODIGO_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("DELETE FROM VERBOS  \n");
	stringbuffer.append("WHERE     \n");
	stringbuffer.append("  VERBO_ID=?   \n");
	DELETE_SQL = stringbuffer.toString();
    }

    public List seleccionaPorTipos(Integer ainteger[]) {
	StringBuffer stringbuffer = new StringBuffer();
	Integer ainteger1[] = ainteger;
	int i = ainteger1.length;
	for (int j = 0; j < i; j++) {
	    int k = ainteger1[j].intValue();
	    stringbuffer.append(k);
	    stringbuffer.append(",");
	}

	stringbuffer.deleteCharAt(stringbuffer.length() - 1);
	String s = SEL_IDS_POR_TIPO_SQL.replaceFirst("\\?", stringbuffer.toString());
	SelIdsPorTipos _ld = new SelIdsPorTipos(getDataSource(), s);
	List list = _ld.execute(new Object[0]);
	return list;
    }

    public List<VerboBean> getRegistros(List<String> list) {
	ArrayList<VerboBean> arraylist = new ArrayList<VerboBean>();
	List list1 = Listas.segmentos(list, 500);
	List list3;
	for (Iterator<List<String>> iterator = list1.iterator(); iterator.hasNext(); arraylist.addAll(list3)) {
	    List<String> list2 = iterator.next();
	    StringBuffer stringbuffer = new StringBuffer(500);
	    String s1;
	    for (Iterator iterator1 = list2.iterator(); iterator1.hasNext(); stringbuffer.append((new StringBuilder())
		    .append("'").append(s1).append("',").toString()))
		s1 = (String) iterator1.next();

	    stringbuffer.deleteCharAt(stringbuffer.length() - 1);
	    String s = SEL_CON_SIG_SQL.replaceFirst("\\?", stringbuffer.toString());
	    SeleccionConSignificado _ll = new SeleccionConSignificado(getDataSource(), s);
	    list3 = _ll.execute();
	}

	return arraylist;
    }

    public List getRegistrosSinSignificado(List list) {
	ArrayList arraylist = new ArrayList();
	int i = list.size();
	int j = 0;
	char c = '\u03E8';
	for (; i > 0; i -= c) {
	    List list1 = list.subList(j, Math.min(j + c, list.size()));
	    StringBuffer stringbuffer = new StringBuffer(500);
	    String s1;
	    for (Iterator iterator = list1.iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder())
		    .append("'").append(s1).append("',").toString()))
		s1 = (String) iterator.next();

	    stringbuffer.deleteCharAt(stringbuffer.length() - 1);
	    String s = SEL_SIN_SIG_SQL.replaceFirst("\\?", stringbuffer.toString());
	    SeleccionSinSignificado _lh = new SeleccionSinSignificado(getDataSource(), s);
	    List list2 = _lh.execute();
	    arraylist.addAll(list2);
	    j += c;
	}

	return arraylist;
    }

    public List seleccionaPorVerbo(String s, LugarSubcadena h1) {
	String s1 = null;

	switch (h1) {
	case Principio: // '\001'
	    s1 = (new StringBuilder()).append("'").append(s).append("%'").toString();
	    break;

	case Medio: // '\002'
	    s1 = (new StringBuilder()).append("'%").append(s).append("%'").toString();
	    break;

	case Fin: // '\003'
	    s1 = (new StringBuilder()).append("'%").append(s).append("'").toString();
	    break;

	case Exacto: // '\004'
	    s1 = (new StringBuilder()).append("'").append(s).append("'").toString();
	    break;
	}
	String s2 = SEL_IDS_POR_FORMA_CON_SIG_SQL.replaceFirst("\\?", s1);
	SelIdsPorFormaConSig _lc = new SelIdsPorFormaConSig(getDataSource(), s2);
	List list = _lc.execute();
	return list;
    }

    public List seleccionaPorVerboParaAM(String s) {
	return seleccionPorFormaSinSig.execute(new Object[] { s });
    }

    public List seleccionaPorLetra(String s) {
	List list = seleccionIdsPorLetra.execute(new Object[] { s });
	return list;
    }

    public VerboBean seleccionaInidvidual(String s) {
	List list = seleccionIndividualConSig.execute(new Object[] { s });
	if (list.size() == 0)
	    return null;
	else
	    return (VerboBean) list.get(0);
    }

    public VerboBean seleccionaInidvidualParaAM(String s) {
	List list = seleccionIndividualSinSig.execute(new Object[] { s });
	if (list.size() == 0)
	    return null;
	else
	    return (VerboBean) list.get(0);
    }

    public void inserta(VerboBean h1) {
	String s = DBUtil.getHashableId();
	insercion.update(new Object[] { s, h1.getLetra(), Integer.valueOf(h1.getCodigo()), h1.getVerbo(), h1.getSufijo(),
		h1.getParticularidad().abreviatura(), Integer.valueOf(h1.getTipoVerbo()), h1.getIdTipo(),
		Integer.valueOf(h1.isDibujado() ? 1 : 0), Integer.valueOf(h1.isCompuesto() ? 1 : 0) });
	h1.setId(s);
    }

    public void modifica(VerboBean h1) {
	borrado.update(new Object[] { h1.getLetra(), Integer.valueOf(h1.getCodigo()), h1.getVerbo(), h1.getSufijo(),
		h1.getParticularidad().abreviatura(), Integer.valueOf(h1.getTipoVerbo()), h1.getIdTipo(),
		Integer.valueOf(h1.isDibujado() ? 1 : 0), Integer.valueOf(h1.isCompuesto() ? 1 : 0), h1.getId() });
    }

    public void modificaCodigosTodos(String s) {
	modificacionCodigos.update(new Object[] { s });
    }

    public void modificaCodigoIndividual(int i, String s) {
	modificacionCodigoIndividual.update(new Object[] { Integer.valueOf(i), s });
    }

    public void borra(String s) {
	borrado.update(new Object[] { s });
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	seleccionIdsPorLetra = new SelIdsPorLetra(getDataSource());
	seleccionPorFormaSinSig = new SelPorFormaSinSig(getDataSource());
	seleccionIndividualConSig = new SelIndividualConSignificado(getDataSource());
	seleccionIndividualSinSig = new SelIndivSinSig(getDataSource());
	insercion = new Insercion(getDataSource());
	modificacion = new Modificacion(getDataSource());
	borrado = new Borrado(getDataSource(), DELETE_SQL);
	modificacionCodigoIndividual = new ModificaCodigoIndividual(getDataSource(), UPDATE_CODIGO_SQL);
	modificacionCodigos = new ModificaCodigosTodos(getDataSource(), UPDATE_AUMENTA_CODIGO_SQL);
    }

    static String B() {
	return SEL_POR_FORMA_SIN_SIG_SQL;
    }

    static String G() {
	return SEL_IDS_POR_LETRA_SQL;
    }

    static String E() {
	return SEL_INDIVIDUAL_CON_SIG_SQL;
    }

    static String A() {
	return SEL_INDIV_SIN_SIG_SQL;
    }

    static String F() {
	return INSERT_SQL;
    }

    static String D() {
	return UPDATE_SQL;
    }

    private static String SEL_IDS_POR_LETRA_SQL;
    private static String SEL_IDS_POR_TIPO_SQL;
    private static String SEL_IDS_POR_FORMA_CON_SIG_SQL;
    private static String SEL_POR_FORMA_SIN_SIG_SQL;
    private static String SEL_INDIVIDUAL_CON_SIG_SQL;
    private static String SEL_INDIV_SIN_SIG_SQL;
    private static String SEL_CON_SIG_SQL;
    private static String SEL_SIN_SIG_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private static String UPDATE_AUMENTA_CODIGO_SQL;
    private static String UPDATE_CODIGO_SQL;
    private SelPorFormaSinSig seleccionPorFormaSinSig;
    private SelIdsPorLetra seleccionIdsPorLetra;
    private SelIndividualConSignificado seleccionIndividualConSig;
    private SelIndivSinSig seleccionIndividualSinSig;
    private Insercion insercion;
    private Borrado borrado;
    private ModificaCodigosTodos modificacionCodigos;
    private ModificaCodigoIndividual modificacionCodigoIndividual;
    private Modificacion modificacion;
}
