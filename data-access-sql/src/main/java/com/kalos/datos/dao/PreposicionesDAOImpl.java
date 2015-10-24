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

import com.kalos.beans.ParticulaBean;
import com.kalos.beans.PreposicionBean;
import com.kalos.beans.Significado;
import com.kalos.datos.dao.ParticulasDAOImpl.SeleccionAbstracta;
import com.kalos.datos.dao.ParticulasDAOImpl.SeleccionPorIds;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.recursos.Configuracion;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

// Referenced classes of package kalos.E.C:
//            c

public class PreposicionesDAOImpl extends JdbcDaoSupport implements PreposicionesDAO {

    class Modificacion extends SqlUpdate {

	public Modificacion(DataSource datasource) {
	    super(datasource, UPDATE_SQL);
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	}
    }

    class Insercion extends SqlUpdate {
	public Insercion(DataSource datasource) {
	    super(datasource, INSERT_SQL);
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	}
    }

    class SeleccionTodasConSig extends SeleccionAbstractaConSig {
	public SeleccionTodasConSig(DataSource datasource) {
	    super(datasource, SEL_TODAS_CON_SIG_SQL);
	}
    }

    class _I extends SeleccionAbstractaConSig {
	public _I(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    class SeleccionPorFormaSinSig extends SelecionAbstrsactaSinSig {
	public SeleccionPorFormaSinSig(DataSource datasource) {
	    super(datasource, SEL_POR_FORMA_SIN_SIG_SQL);
	    declareParameter(new SqlParameter(12));
	}
    }

    class SelNoAcentuables extends SelecionAbstrsactaSinSig {
	public SelNoAcentuables(DataSource datasource) {
	    super(datasource, SEL_NO_ACENTUABLES_SQL);
	}
    }

    abstract class SelecionAbstrsactaSinSig extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    PreposicionBean c1 = new PreposicionBean();
	    c1.setId(resultset.getString("PREPOSICION_ID"));
	    c1.setPreposicion(resultset.getString("PREPOSICION"));
	    c1.setFormaDiccionario(resultset.getString("FORMA"));
	    c1.setOrden(resultset.getInt("ORDEN"));
	    c1.setParticularidad(Particularidad.getEnum(resultset.getString("PARTICULARIDAD")));
	    c1.setGenitivo(resultset.getInt("GENITIVO") == 1);
	    c1.setAcusativo(resultset.getInt("ACUSATIVO") == 1);
	    c1.setDativo(resultset.getInt("DATIVO") == 1);
	    return c1;
	}

	public SelecionAbstrsactaSinSig(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    abstract class SeleccionAbstractaConSig extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    PreposicionBean bean = new PreposicionBean();
	    bean.setId(resultset.getString("PREPOSICION_ID"));
	    bean.setPreposicion(resultset.getString("PREPOSICION"));
	    bean.setFormaDiccionario(resultset.getString("FORMA"));
	    bean.setOrden(resultset.getInt("ORDEN"));
	    bean.setParticularidad(Particularidad.getEnum(resultset.getString("PARTICULARIDAD")));
	    bean.setGenitivo(resultset.getInt("GENITIVO") == 1);
	    bean.setAcusativo(resultset.getInt("ACUSATIVO") == 1);
	    bean.setDativo(resultset.getInt("DATIVO") == 1);
	    Significado sig = new Significado();
	    sig.setIdioma(Configuracion.getIdiomaSignificados());
	    sig.setReferenteId(bean.getId());
	    sig.setId(resultset.getString("SIGNIFICADO_ID"));
	    sig.setValor(resultset.getString("VALOR"));
	    HashMap hashmap = new HashMap();
	    hashmap.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), sig);
	    bean.setSignificados(hashmap);
	    return bean;
	}

	public SeleccionAbstractaConSig(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    class SeleccionTodosIds extends SeleccionIds {

	public SeleccionTodosIds(DataSource datasource) {
	    super(datasource, SEL_TODOS_IDS_SQL, "PREPOSICION_ID");
	}
    }

    private void puebla() {
	StringBuffer stringbuffer = new StringBuffer(200);
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  PREP.PREPOSICION_ID,   \n");
	stringbuffer.append("  PREP.ORDEN,   \n");
	stringbuffer.append("  PREP.PREPOSICION,   \n");
	stringbuffer.append("  PREP.FORMA,   \n");
	stringbuffer.append("  PREP.PARTICULARIDAD,   \n");
	stringbuffer.append("  PREP.GENITIVO,   \n");
	stringbuffer.append("  PREP.ACUSATIVO,   \n");
	stringbuffer.append("  PREP.DATIVO,   \n");
	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
	stringbuffer.append("  SIG.VALOR   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  PREPOSICIONES PREP       \n");
	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON PREP.PREPOSICION_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados())
		.append("')    \n");
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  PREP.ORDEN   \n");
	SEL_TODAS_CON_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  PRE.PREPOSICION_ID   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  PREPOSICIONES PRE       \n");
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  PRE.ORDEN   \n");
	SEL_TODOS_IDS_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  PREP.PREPOSICION_ID,   \n");
	stringbuffer.append("  PREP.ORDEN,   \n");
	stringbuffer.append("  PREP.PREPOSICION,   \n");
	stringbuffer.append("  PREP.FORMA,   \n");
	stringbuffer.append("  PREP.PARTICULARIDAD,   \n");
	stringbuffer.append("  PREP.GENITIVO,   \n");
	stringbuffer.append("  PREP.ACUSATIVO,   \n");
	stringbuffer.append("  PREP.DATIVO,   \n");
	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
	stringbuffer.append("  SIG.VALOR   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  PREPOSICIONES PREP       \n");
	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
	stringbuffer.append("      ON PREP.PREPOSICION_ID=SIG.REFERENTE_ID       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  PREP.PREPOSICION_ID IN (?)    \n");
	stringbuffer.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados())
		.append("')    \n");
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  PREP.ORDEN   \n");
	SEL_POR_IDS_CON_SIG = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  PREP.PREPOSICION_ID,   \n");
	stringbuffer.append("  PREP.ORDEN,   \n");
	stringbuffer.append("  PREP.PREPOSICION,   \n");
	stringbuffer.append("  PREP.FORMA,   \n");
	stringbuffer.append("  PREP.PARTICULARIDAD,   \n");
	stringbuffer.append("  PREP.GENITIVO,   \n");
	stringbuffer.append("  PREP.ACUSATIVO,   \n");
	stringbuffer.append("  PREP.DATIVO   \n");
	stringbuffer.append("FROM   \n");
	stringbuffer.append("  PREPOSICIONES PREP   \n");
	stringbuffer.append("WHERE   \n");
	stringbuffer.append("  FORMA not like '%/%'  \n");
	stringbuffer.append("  AND FORMA NOT LIKE  '%\\\\%'  \n");
	stringbuffer.append("  AND FORMA NOT LIKE '%=%'  \n");
	SEL_NO_ACENTUABLES_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  PREP.PREPOSICION_ID,   \n");
	stringbuffer.append("  PREP.ORDEN,   \n");
	stringbuffer.append("  PREP.PREPOSICION,   \n");
	stringbuffer.append("  PREP.FORMA,   \n");
	stringbuffer.append("  PREP.PARTICULARIDAD,   \n");
	stringbuffer.append("  PREP.GENITIVO,   \n");
	stringbuffer.append("  PREP.ACUSATIVO,   \n");
	stringbuffer.append("  PREP.DATIVO   \n");
	stringbuffer.append("FROM   \n");
	stringbuffer.append("  PREPOSICIONES PREP   \n");
	stringbuffer.append("WHERE   \n");
	stringbuffer.append("  FORMA=?  \n");
	SEL_POR_FORMA_SIN_SIG_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("INSERT INTO PREPOSICIONES (  \n");
	stringbuffer.append("  PREPOSICION_ID,   \n");
	stringbuffer.append("  ORDEN,   \n");
	stringbuffer.append("  PREPOSICION,   \n");
	stringbuffer.append("  FORMA,   \n");
	stringbuffer.append("  PARTICULARIDAD,   \n");
	stringbuffer.append("  GENITIVO,   \n");
	stringbuffer.append("  ACUSATIVO,   \n");
	stringbuffer.append("  DATIVO   \n");
	stringbuffer.append(") VALUES (     \n");
	stringbuffer.append("  ?,?,?,?,?,?,?,?   \n");
	stringbuffer.append(" )   \n");
	INSERT_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("UPDATE PREPOSICIONES SET  \n");
	stringbuffer.append("  ORDEN=?,   \n");
	stringbuffer.append("  PREPOSICION=?,   \n");
	stringbuffer.append("  FORMA=?,   \n");
	stringbuffer.append("  PARTICULARIDAD=?,   \n");
	stringbuffer.append("  GENITIVO=?,   \n");
	stringbuffer.append("  ACUSATIVO=?,   \n");
	stringbuffer.append("  DATIVO=?   \n");
	stringbuffer.append("WHERE     \n");
	stringbuffer.append("  PREPOSICION_ID=?   \n");
	UPDATE_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("DELETE FROM PREPOSICIONES  \n");
	stringbuffer.append("WHERE     \n");
	stringbuffer.append("  PREPOSICION_ID=?   \n");
	DELETE_SQL = stringbuffer.toString();
    }

    public List<String> seleccionaTodosLosIds() {
	return seleccionTodosIds.execute();
    }

    public List<PreposicionBean> seleccionaPreposicionesNoAcentuables() {
	return selNoAcentuables.execute();
    }

    public List<PreposicionBean> seleccionaPorFormaParaAM(String s) {
	return seleccionPorFormaSinSig.execute(new Object[] { s });
    }

    public List<PreposicionBean> getTodas() {
	return seleccionTodasConSig.execute(new Object[0]);
    }

    

    public List<PreposicionBean> getRegistros(List<String> ids) {
        List<PreposicionBean> resultado = new ArrayList<PreposicionBean>();
        int restantes = ids.size();
        int comienzo = 0;
        int segmento = 1000;
        while (restantes > 0) {
            List<String> subIds = ids.subList(comienzo, Math.min(comienzo + segmento, ids.size()));
            StringBuffer idsSeparadosComa = new StringBuffer(500);
            for (String id : subIds) {
                idsSeparadosComa.append("'" + id + "',");
            }
            idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
            String sql = SEL_POR_IDS_CON_SIG.replaceFirst("\\?", idsSeparadosComa.toString());

            SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);

            List<PreposicionBean> entradasDic = seleccionPorIds.execute();
            resultado.addAll(entradasDic);
            comienzo += segmento;
            restantes -= segmento;
        }
        return resultado;
    }    
    

    // selección de registros a mostrar de la tabla "PARTICULAS", dados los
    // IDs
    class SeleccionPorIds extends SeleccionAbstractaConSig {
        public SeleccionPorIds(DataSource dataSource, String sql) {
            super(dataSource, sql);
        }
    }   

    public void inserta(PreposicionBean c1) {
	String s = com.kalos.datos.util.DBUtil.getHashableId();
	insercion.update(new Object[] { s, Integer.valueOf(c1.getOrden()), c1.getPreposicion(),
		c1.getFormaDiccionario(), c1.getParticularidad().abreviatura() ,
		Integer.valueOf(c1.isGenitivo() ? 1 : 0), Integer.valueOf(c1.isAcusativo() ? 1 : 0),
		Integer.valueOf(c1.isDativo() ? 1 : 0) });
	c1.setId(s);
    }

    public void modifica(PreposicionBean c1) {
	modificacion.update(new Object[] { Integer.valueOf(c1.getOrden()), c1.getPreposicion(),
		c1.getFormaDiccionario(), c1.getParticularidad().abreviatura(),
		Integer.valueOf(c1.isGenitivo() ? 1 : 0), Integer.valueOf(c1.isAcusativo() ? 1 : 0),
		Integer.valueOf(c1.isDativo() ? 1 : 0), c1.getId() });
    }

    public void borra(String s) {
	borrado.update(new Object[] { s });
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	selNoAcentuables = new SelNoAcentuables(getDataSource());
	seleccionTodosIds = new SeleccionTodosIds(getDataSource());
	seleccionTodasConSig = new SeleccionTodasConSig(getDataSource());
	seleccionPorFormaSinSig = new SeleccionPorFormaSinSig(getDataSource());
	insercion = new Insercion(getDataSource());
	modificacion = new Modificacion(getDataSource());
	borrado = new Borrado(getDataSource(), DELETE_SQL);
    }

    private static String SEL_TODAS_CON_SIG_SQL;
    private static String SEL_TODOS_IDS_SQL;
    private static String SEL_POR_IDS_CON_SIG;
    private static String SEL_NO_ACENTUABLES_SQL;
    private static String SEL_POR_FORMA_SIN_SIG_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private SeleccionTodosIds seleccionTodosIds;
    private SeleccionTodasConSig seleccionTodasConSig;
    private SeleccionPorFormaSinSig seleccionPorFormaSinSig;
    private SelNoAcentuables selNoAcentuables;
    private Insercion insercion;
    private Modificacion modificacion;
    private Borrado borrado;
}
