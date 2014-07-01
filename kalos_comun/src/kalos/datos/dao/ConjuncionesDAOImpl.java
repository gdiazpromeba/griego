// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import kalos.beans.AdjetivoBean;
import kalos.beans.ConjuncionBean;
import kalos.beans.Significado;
import kalos.datos.dao.AdjetivoDAOImpl.SeleccionPorIds;
import kalos.datos.dao.AdverbiosDAOImpl.SeleccionAbstracta;
import kalos.datos.dao.AdverbiosDAOImpl.SeleccionPorIdConSignificado;
import kalos.datos.dao.comunes.Borrado;
import kalos.datos.dao.comunes.ModificaCodigoIndividual;
import kalos.datos.dao.comunes.SeleccionIds;
import kalos.enumeraciones.Idioma;
import kalos.enumeraciones.LugarSubcadena;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.SubtipoConjuncion;
import kalos.enumeraciones.TipoConjuncion;
import kalos.recursos.Configuracion;
import kalos.utils.Listas;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

// Referenced classes of package kalos.E.C:
//            d

public class ConjuncionesDAOImpl extends JdbcDaoSupport implements ConjuncionesDAO {
    
    
    class Update extends SqlUpdate {


	public Update(DataSource datasource) {
	    super(datasource, UPDATE_SQL);
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(1));
	}
    }

    class Insert extends SqlUpdate {



	public Insert(DataSource datasource) {
	    super(datasource, INSERT_SQL);
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	}
    }

    class SeleccionPorIds extends SeleccionAbstractasinSignificado {

	public SeleccionPorIds(DataSource datasource, String s) {
	    super(datasource, s);
	}
	
	
    }


    class SeleccionPorIdConSignificado extends SeleccionAbstracta {

	public SeleccionPorIdConSignificado(DataSource datasource, String sql) {
	    super(datasource, sql);
	}
    }

    class SelectPorForma extends SeleccionAbstractasinSignificado {

	public SelectPorForma(DataSource datasource, String sql) {
	    super(datasource, sql);
	}
    }

    class SelectIdsPorSubtipo extends SeleccionIds {
	public SelectIdsPorSubtipo(DataSource datasource) {
	    super(datasource, SELECT_IDS_POR_SUBTIPO_SQL, "CONJUNCION_ID");
	    declareParameter(new SqlParameter(1));
	}
    }



    class SelectSinAcento extends SeleccionAbstractasinSignificado {

	public SelectSinAcento(DataSource datasource) {
	    super(datasource, SELECT_SIN_ACENTO_SQL);
	}
    }


    class SelectTodasConSignificado extends SeleccionAbstracta {


	public SelectTodasConSignificado(DataSource datasource) {
	    super(datasource, SELECT_TODAS_CON_SIGNIFICADO_SQL);
	}
    }


    abstract class SeleccionAbstractasinSignificado extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    kalos.beans.ConjuncionBean e = new kalos.beans.ConjuncionBean();
	    e.setId(resultset.getString("CONJUNCION_ID"));
	    e.setCodigo(resultset.getInt("CODIGO"));
	    e.setForma(resultset.getString("FORMA"));
	    e.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
	    e.setTipo(TipoConjuncion.getEnum(resultset.getString("TIPO")));
	    e.setSubtipo(SubtipoConjuncion.getEnum(resultset.getString("SUBTIPO")));
	    e.setParte(resultset.getInt("PARTE") == 1);
	    return e;
	}
	
	public SeleccionAbstractasinSignificado(DataSource datasource, String s) {
	    super(datasource, s);
	}
	
    }
    
    abstract class SeleccionAbstracta extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    kalos.beans.ConjuncionBean e = new kalos.beans.ConjuncionBean();
	    e.setId(resultset.getString("CONJUNCION_ID"));
	    e.setCodigo(resultset.getInt("CODIGO"));
	    e.setForma(resultset.getString("FORMA"));
	    e.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
	    e.setTipo(TipoConjuncion.getEnum(resultset.getString("TIPO")));
	    e.setSubtipo(SubtipoConjuncion.getEnum(resultset.getString("SUBTIPO")));
	    e.setParte(resultset.getInt("PARTE") == 1);
	    Significado sig = new Significado();
	    sig.setIdioma(Configuracion.getIdiomaSignificados());
	    sig.setReferenteId(e.getId());
	    sig.setId(resultset.getString("SIGNIFICADO_ID"));
	    sig.setValor(resultset.getString("VALOR"));
	    HashMap hashmap = new HashMap();
	    hashmap.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), sig);
	    e.setSignificados(hashmap);
	    return e;
	}

	public SeleccionAbstracta(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    private void C() {
	StringBuffer stringbuffer = new StringBuffer(200);
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ       \n");
	stringbuffer.append("    INNER JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  CONJ.FORMA LIKE ?    \n");
	stringbuffer.append((new StringBuilder()).append("  AND SIG.IDIOMA='")
		.append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("'    \n").toString());
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  CONJ.CODIGO   \n");
	SELECT_POR_FORMA_CON_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
	stringbuffer.append("  CONJ.CODIGO,   \n");
	stringbuffer.append("  CONJ.FORMA,   \n");
	stringbuffer.append("  CONJ.PARTIC,   \n");
	stringbuffer.append("  CONJ.TIPO,   \n");
	stringbuffer.append("  CONJ.SUBTIPO,   \n");
	stringbuffer.append("  CONJ.PARTE,   \n");
	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
	stringbuffer.append("  SIG.VALOR   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ       \n");
	stringbuffer.append("    INNER JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append((new StringBuilder()).append("  SIG.IDIOMA='")
		.append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("'    \n").toString());
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  CONJ.CODIGO   \n");
	SELECT_TODAS_CON_SIGNIFICADO_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  CONJ.SUBTIPO=?    \n");
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  CONJ.CODIGO   \n");
	SELECT_IDS_POR_SUBTIPO_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
	stringbuffer.append("  CONJ.CODIGO,   \n");
	stringbuffer.append("  CONJ.FORMA,   \n");
	stringbuffer.append("  CONJ.TIPO,   \n");
	stringbuffer.append("  CONJ.SUBTIPO,   \n");
	stringbuffer.append("  CONJ.PARTE,   \n");
	stringbuffer.append("  CONJ.PARTIC   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  CONJ.FORMA=?    \n");
	SELECT_POR_FORMA_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
	stringbuffer.append("  CONJ.CODIGO,   \n");
	stringbuffer.append("  CONJ.FORMA,   \n");
	stringbuffer.append("  CONJ.TIPO,   \n");
	stringbuffer.append("  CONJ.SUBTIPO,   \n");
	stringbuffer.append("  CONJ.PARTE,   \n");
	stringbuffer.append("  CONJ.PARTIC,   \n");
	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
	stringbuffer.append("  SIG.VALOR   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ       \n");
	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID IN (?)    \n");
	stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='")
		.append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("')    \n").toString());
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  CONJ.CODIGO   \n");
	SELECT_POR_VARIOS_IDS_CON_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
	stringbuffer.append("  CONJ.CODIGO,   \n");
	stringbuffer.append("  CONJ.FORMA,   \n");
	stringbuffer.append("  CONJ.TIPO,   \n");
	stringbuffer.append("  CONJ.SUBTIPO,   \n");
	stringbuffer.append("  CONJ.PARTE,   \n");
	stringbuffer.append("  CONJ.PARTIC   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID IN (?)    \n");
	SELECT_POR_VARIOS_IDS_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
	stringbuffer.append("  CONJ.CODIGO,   \n");
	stringbuffer.append("  CONJ.FORMA,   \n");
	stringbuffer.append("  CONJ.TIPO,   \n");
	stringbuffer.append("  CONJ.SUBTIPO,   \n");
	stringbuffer.append("  CONJ.PARTE,   \n");
	stringbuffer.append("  CONJ.PARTIC,   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID=?    \n");
	SELECT_POR_ID_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
	stringbuffer.append("  CONJ.CODIGO,   \n");
	stringbuffer.append("  CONJ.FORMA,   \n");
	stringbuffer.append("  CONJ.TIPO,   \n");
	stringbuffer.append("  CONJ.SUBTIPO,   \n");
	stringbuffer.append("  CONJ.PARTE,   \n");
	stringbuffer.append("  CONJ.PARTIC,   \n");
	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
	stringbuffer.append("  SIG.VALOR   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  CONJUNCIONES CONJ       \n");
	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  CONJ.CONJUNCION_ID=?    \n");
	stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='")
		.append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("')    \n").toString());
	SELECT_POR_ID_CON_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  CONJUNCION_ID,   \n");
	stringbuffer.append("  CODIGO,   \n");
	stringbuffer.append("  FORMA,   \n");
	stringbuffer.append("  TIPO,   \n");
	stringbuffer.append("  SUBTIPO,   \n");
	stringbuffer.append("  PARTE,   \n");
	stringbuffer.append("  PARTIC   \n");
	stringbuffer.append("FROM   \n");
	stringbuffer.append("  CONJUNCIONES   \n");
	stringbuffer.append("WHERE   \n");
	stringbuffer.append("  FORMA not like '%/%'  \n");
	stringbuffer.append("  AND FORMA NOT LIKE  '%\\\\%'  \n");
	stringbuffer.append("  AND FORMA NOT LIKE '%=%'  \n");
	SELECT_SIN_ACENTO_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("INSERT INTO CONJUNCIONES (  \n");
	stringbuffer.append("  CONJUNCION_ID,   \n");
	stringbuffer.append("  CODIGO,   \n");
	stringbuffer.append("  FORMA,   \n");
	stringbuffer.append("  TIPO,   \n");
	stringbuffer.append("  SUBTIPO,   \n");
	stringbuffer.append("  PARTE,   \n");
	stringbuffer.append("  PARTIC   \n");
	stringbuffer.append(") VALUES (     \n");
	stringbuffer.append("  ?,?,?,?,?,?,?   \n");
	stringbuffer.append(" )   \n");
	INSERT_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("UPDATE CONJUNCIONES SET  \n");
	stringbuffer.append("  CODIGO=?,   \n");
	stringbuffer.append("  FORMA=?,   \n");
	stringbuffer.append("  TIPO=?,   \n");
	stringbuffer.append("  SUBTIPO=?,   \n");
	stringbuffer.append("  PARTE=?,   \n");
	stringbuffer.append("  PARTIC=?   \n");
	stringbuffer.append("WHERE     \n");
	stringbuffer.append("  CONJUNCION_ID=?   \n");
	UPDATE_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("UPDATE CONJUNCIONES SET  \n");
	stringbuffer.append("  CODIGO=?   \n");
	stringbuffer.append("WHERE     \n");
	stringbuffer.append("  CONJUNCION_ID=?   \n");
	UPDATE_CODIGO_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("DELETE FROM CONJUNCIONES  \n");
	stringbuffer.append("WHERE     \n");
	stringbuffer.append("  CONJUNCION_ID=?   \n");
	DELETE_SQL = stringbuffer.toString();
    }

    public List seleccionaTodos() {
	return selTodasConSignificado.execute();
    }

    public List seleccionaConjuncionesNoAcentuables() {
	return selSinAcento.execute();
    }

    
    public List<ConjuncionBean> getRegistros(List<String> ids) {
	List<List<String>> segmentos = Listas.segmentos(ids, 500);
	List<ConjuncionBean> resultado = new ArrayList<ConjuncionBean>();
	for (List<String> segmento : segmentos) {
	    StringBuffer idsSeparadosComa = new StringBuffer(500);
	    for (String id : segmento) {
		idsSeparadosComa.append("'" + id + "',");
	    }
	    if (idsSeparadosComa.length() > 0) {
		idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
	    }
	    String sql = SELECT_POR_VARIOS_IDS_CON_SIG_SQL.replaceFirst("\\?", idsSeparadosComa.toString());
	    SeleccionPorIdConSignificado seleccionPorIds = new SeleccionPorIdConSignificado(getDataSource(), sql);
	    List<ConjuncionBean> adjs = seleccionPorIds.execute();
	    resultado.addAll(adjs);
	}
	return resultado;
    }
    

    public List<ConjuncionBean> getRegistrosSinSignificado(List<String> ids) {
	List<List<String>> segmentos = Listas.segmentos(ids, 500);
	List<ConjuncionBean> resultado = new ArrayList<ConjuncionBean>();
	for (List<String> segmento : segmentos) {
	    StringBuffer idsSeparadosComa = new StringBuffer(500);
	    for (String id : segmento) {
		idsSeparadosComa.append("'" + id + "',");
	    }
	    if (idsSeparadosComa.length() > 0) {
		idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
	    }
	    String sql = SELECT_POR_VARIOS_IDS_SQL.replaceFirst("\\?", idsSeparadosComa.toString());
	    SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);
	    List<ConjuncionBean> adjs = seleccionPorIds.execute();
	    resultado.addAll(adjs);
	}
	return resultado;
    }    
    

    public List seleccionaPorForma(String s, LugarSubcadena h1) {
	String s1="";
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
	String s2 = SELECT_POR_FORMA_CON_SIG_SQL.replaceFirst("\\?", s1);
	SelectPorForma sel = new SelectPorForma(getDataSource(), s2);
	List list = sel.execute();
	return list;
    }

    public List seleccionaPorSubtipo(SubtipoConjuncion stc) {
	List list = selIdsPorSubtipo.execute(new Object[] { SubtipoConjuncion.getCadena(stc) });
	return list;
    }

    public List seleccionaPorFormaParaAM(String s) {
	return selPorForma.execute(new Object[] { s });
    }

    public kalos.beans.ConjuncionBean seleccionaInidvidual(String s) {
	List list = selPorIdConSig.execute(new Object[] { s });
	if (list.size() == 0)
	    return null;
	else
	    return (kalos.beans.ConjuncionBean) list.get(0);
    }

    public kalos.beans.ConjuncionBean seleccionaInidvidualParaAM(String s) {
	List list = selPorId.execute(new Object[] { s });
	if (list.size() == 0)
	    return null;
	else
	    return (kalos.beans.ConjuncionBean) list.get(0);
    }

    public void inserta(kalos.beans.ConjuncionBean e) {
	String s = kalos.datos.util.DBUtil.getHashableId();
	insert.update(new Object[] { s, Integer.valueOf(e.getCodigo()), e.getForma(),
		TipoConjuncion.getCadena(e.getTipo()), SubtipoConjuncion.getCadena(e.getSubtipo()),
		Integer.valueOf(e.isParte() ? 1 : 0), Particularidad.getString(e.getPartic()) });
	e.setId(s);
    }

    public void modifica(kalos.beans.ConjuncionBean e) {
	update.update(new Object[] { Integer.valueOf(e.getCodigo()), e.getForma(),
		TipoConjuncion.getCadena(e.getTipo()), SubtipoConjuncion.getCadena(e.getSubtipo()),
		Integer.valueOf(e.isParte() ? 1 : 0), Particularidad.getString(e.getPartic()), e.getId() });
    }

    public void modificaCodigoIndividual(String s, int i) {
	modificaCodigoIndividual.update(new Object[] { Integer.valueOf(i), s });
    }

    public void borra(String s) {
	borrado.update(new Object[] { s });
    }

    public void initDao() throws Exception {
	super.initDao();
	selPorIdConSig = new SeleccionPorIdConSignificado(getDataSource(), SELECT_POR_ID_CON_SIG_SQL);
	selPorId = new SeleccionPorIds(getDataSource(), SELECT_POR_ID_SQL);
	selSinAcento = new SelectSinAcento(getDataSource());
	selTodasConSignificado = new SelectTodasConSignificado(getDataSource());
	selIdsPorSubtipo = new SelectIdsPorSubtipo(getDataSource());
	insert = new Insert(getDataSource());
	borrado = new Borrado(getDataSource(), DELETE_SQL);
	update = new Update(getDataSource());
	modificaCodigoIndividual = new ModificaCodigoIndividual(getDataSource(), UPDATE_CODIGO_SQL);
    }



    private static String SELECT_POR_FORMA_CON_SIG_SQL;
    private static String SELECT_IDS_POR_SUBTIPO_SQL;
    private static String SELECT_POR_FORMA_SQL;
    private static String SELECT_POR_ID_CON_SIG_SQL;
    private static String SELECT_POR_ID_SQL;
    private static String SELECT_POR_VARIOS_IDS_CON_SIG_SQL;
    private static String SELECT_POR_VARIOS_IDS_SQL;
    private static String SELECT_SIN_ACENTO_SQL;
    private static String SELECT_TODAS_CON_SIGNIFICADO_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_CODIGO_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private SelectTodasConSignificado selTodasConSignificado;
    private SelectSinAcento selSinAcento;
    private SelectIdsPorSubtipo selIdsPorSubtipo;
    private SelectPorForma selPorForma;
    private SeleccionPorIdConSignificado selPorIdConSig;
    private SeleccionPorIds selPorId;
    private Insert insert;
    private Update update;
    private ModificaCodigoIndividual modificaCodigoIndividual;
    private Borrado borrado;
}
