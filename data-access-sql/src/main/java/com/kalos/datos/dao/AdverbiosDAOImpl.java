// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.kalos.beans.AdverbioBean;
import com.kalos.beans.Significado;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.ModificaCodigoIndividual;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.datos.util.Listas;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.TipoAdverbio;
import com.kalos.recursos.Configuracion;

// Referenced classes of package kalos.E.C:
//            YA

public class AdverbiosDAOImpl extends JdbcDaoSupport implements AdverbiosDAO {
	class Modificacion extends SqlUpdate {

		public Modificacion(DataSource datasource) {
			super(datasource, MODIFICACION_SQL);
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(12));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(1));
		}
	}

	class Insercion extends SqlUpdate {

		public Insercion(DataSource datasource) {
			super(datasource, INSERCION_SQL);
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(12));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(1));
		}
	}

	class SeleccionPorIdConSignificado extends SeleccionAbstracta {

		public SeleccionPorIdConSignificado(DataSource datasource) {
			super(datasource, SELECCION_POR_ID_CON_SIGNIFICADO_SQL);
			declareParameter(new SqlParameter(1));
		}
	}

	class SeleccionIdPorAdverbio extends SeleccionIds {

		public SeleccionIdPorAdverbio(DataSource datasource) {
			super(datasource, SELECCION_ID_POR_ADVERBIO_SQL, "ADVERBIO_ID");
			declareParameter(new SqlParameter(12));
		}
	}

	class SeleccionPorLetra extends SeleccionIds {
		public SeleccionPorLetra(DataSource datasource) {
			super(datasource, SELECCION_ID_POR_LETRA_SQL, "ADVERBIO_ID");
			declareParameter(new SqlParameter(1));
		}
	}

	class SeleccionPorIds extends SeleccionAbstracta {

		public SeleccionPorIds(DataSource datasource, String s) {
			super(datasource, s);
		}
	}

    //selección individual
    class SeleccionIndividual extends SeleccionAbstracta {
        public SeleccionIndividual(DataSource dataSource) {
            super(dataSource, SELECCION_POR_IDS_CON_SIGNIFICADO_SQL);
            declareParameter(new SqlParameter(Types.CHAR));
        }
    }

	private class SeleccionPorAdverbio extends SeleccionAbstractaSinSignificado {

		public SeleccionPorAdverbio(DataSource datasource) {
			super(datasource, SELECCION_POR_ADVERBIO_SQL);
			declareParameter(new SqlParameter(12));
		}
	}

	abstract class SeleccionAbstractaSinSignificado extends MappingSqlQuery {

		protected Object mapRow(ResultSet resultset, int j) throws SQLException {
			AdverbioBean m1 = new AdverbioBean();
			m1.setId(resultset.getString("ADVERBIO_ID"));
			m1.setLetra(resultset.getString("LETRA"));
			m1.setCodigo(resultset.getInt("CODIGO"));
			m1.setAdverbio(resultset.getString("ADVERBIO"));
			m1.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
			m1.setGrado(GradoComparacion.getEnum(resultset.getString("GRADO_COMPARACION")));
			m1.setTipo(TipoAdverbio.getEnum(resultset.getString("TIPO_ADVERBIO")));
			return m1;
		}

		public SeleccionAbstractaSinSignificado(DataSource datasource, String s) {
			super(datasource, s);
		}
	}

	abstract class SeleccionAbstracta extends MappingSqlQuery {

		protected Object mapRow(ResultSet resultset, int j) throws SQLException {
			AdverbioBean m1 = new AdverbioBean();
			m1.setId(resultset.getString("ADVERBIO_ID"));
			m1.setLetra(resultset.getString("LETRA"));
			m1.setCodigo(resultset.getInt("CODIGO"));
			m1.setAdverbio(resultset.getString("ADVERBIO"));
			m1.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
			m1.setGrado(GradoComparacion.getEnum(resultset.getString("GRADO_COMPARACION")));
			m1.setTipo(TipoAdverbio.getEnum(resultset.getString("TIPO_ADVERBIO")));
			Significado q1 = new Significado();
			q1.setIdioma(Configuracion.getIdiomaSignificados());
			q1.setReferenteId(m1.getId());
			q1.setId(resultset.getString("SIGNIFICADO_ID"));
			q1.setValor(resultset.getString("VALOR"));
			HashMap hashmap = new HashMap();
			hashmap.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), q1);
			m1.setSignificados(hashmap);
			return m1;
		}

		public SeleccionAbstracta(DataSource datasource, String s) {
			super(datasource, s);
		}
	}

	private void puebla() {
		StringBuffer stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  ADV.ADVERBIO_ID   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  ADVERBIOS ADV       \n");
		stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		stringbuffer.append("      ON ADV.ADVERBIO_ID=SIG.REFERENTE_ID       \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  ADV.LETRA=?    \n");
		stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='")
				.append(Configuracion.getIdiomaSignificados()).append("')    \n").toString());
		stringbuffer.append("ORDER BY  \n");
		stringbuffer.append("  ADV.CODIGO   \n");
		SELECCION_ID_POR_LETRA_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  ADV.ADVERBIO_ID   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  ADVERBIOS ADV       \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  ADV.ADVERBIO=?     \n");
		SELECCION_ID_POR_ADVERBIO_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  ADV.ADVERBIO_ID,   \n");
		stringbuffer.append("  ADV.LETRA,   \n");
		stringbuffer.append("  ADV.CODIGO,   \n");
		stringbuffer.append("  ADV.ADVERBIO,   \n");
		stringbuffer.append("  ADV.PARTIC,   \n");
		stringbuffer.append("  ADV.GRADO_COMPARACION,   \n");
		stringbuffer.append("  ADV.TIPO_ADVERBIO   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("    ADVERBIOS ADV       \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  ADV.ADVERBIO=?    \n");
		SELECCION_POR_ADVERBIO_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  ADV.ADVERBIO_ID,   \n");
		stringbuffer.append("  ADV.LETRA,   \n");
		stringbuffer.append("  ADV.CODIGO,   \n");
		stringbuffer.append("  ADV.ADVERBIO,   \n");
		stringbuffer.append("  ADV.PARTIC,   \n");
		stringbuffer.append("  ADV.GRADO_COMPARACION,   \n");
		stringbuffer.append("  ADV.TIPO_ADVERBIO,   \n");
		stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
		stringbuffer.append("  SIG.VALOR   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("    ADVERBIOS ADV       \n");
		stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		stringbuffer.append("      ON ADV.ADVERBIO_ID=SIG.REFERENTE_ID       \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  ADV.ADVERBIO_ID=?    \n");
		stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='")
				.append(Configuracion.getIdiomaSignificados()).append("')    \n").toString());
		SELECCION_POR_ID_CON_SIGNIFICADO_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  ADV.ADVERBIO_ID,   \n");
		stringbuffer.append("  ADV.LETRA,   \n");
		stringbuffer.append("  ADV.CODIGO,   \n");
		stringbuffer.append("  ADV.ADVERBIO,   \n");
		stringbuffer.append("  ADV.PARTIC,   \n");
		stringbuffer.append("  ADV.GRADO_COMPARACION,   \n");
		stringbuffer.append("  ADV.TIPO_ADVERBIO,   \n");
		stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
		stringbuffer.append("  SIG.VALOR   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("    ADVERBIOS ADV       \n");
		stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		stringbuffer.append("      ON ADV.ADVERBIO_ID=SIG.REFERENTE_ID       \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  ADV.ADVERBIO_ID IN (?)    \n");
		stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='")
				.append(Configuracion.getIdiomaSignificados()).append("')    \n").toString());
		stringbuffer.append("ORDER BY \n");
		stringbuffer.append(" ADV.LETRA, \n");
		stringbuffer.append(" ADV.CODIGO \n");
		SELECCION_POR_IDS_CON_SIGNIFICADO_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("INSERT INTO ADVERBIOS(   \n");
		stringbuffer.append("  ADVERBIO_ID,   \n");
		stringbuffer.append("  LETRA,   \n");
		stringbuffer.append("  CODIGO,   \n");
		stringbuffer.append("  ADVERBIO,   \n");
		stringbuffer.append("  PARTIC,   \n");
		stringbuffer.append("  GRADO_COMPARACION,   \n");
		stringbuffer.append("  TIPO_ADVERBIO   \n");
		stringbuffer.append(" ) VALUES (    \n");
		stringbuffer.append("  ?,?,?,?,?,?,?  \n");
		stringbuffer.append(")     \n");
		INSERCION_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("UPDATE ADVERBIOS SET   \n");
		stringbuffer.append("  LETRA=?,   \n");
		stringbuffer.append("  CODIGO=?,   \n");
		stringbuffer.append("  ADVERBIO=?,   \n");
		stringbuffer.append("  PARTIC=?,   \n");
		stringbuffer.append("  GRADO_COMPARACION=?,   \n");
		stringbuffer.append("  TIPO_ADVERBIO=?   \n");
		stringbuffer.append("WHERE    \n");
		stringbuffer.append("  ADVERBIO_ID=?   \n");
		MODIFICACION_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("UPDATE ADVERBIOS SET   \n");
		stringbuffer.append("  CODIGO=?   \n");
		stringbuffer.append("WHERE    \n");
		stringbuffer.append("  ADVERBIO_ID=?   \n");
		MODIFICACION_CODIGO_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("DELETE FROM ADVERBIOS WHERE   \n");
		stringbuffer.append("  ADVERBIO_ID=?  \n");
		BORRADO_SQL = stringbuffer.toString();
	}

	public List seleccionaPorFormaSinSignificado(String s) {
		return seleccionIdPorAdverbio.execute(new Object[] { s });
	}

	public List getRegistros(List list) {
		List list1 = Listas.segmentos(list, 500);
		ArrayList arraylist = new ArrayList();
		List list3;
		for (Iterator iterator = list1.iterator(); iterator.hasNext(); arraylist.addAll(list3)) {
			List list2 = (List) iterator.next();
			StringBuffer stringbuffer = new StringBuffer(500);
			String s1;
			for (Iterator iterator1 = list2.iterator(); iterator1.hasNext(); stringbuffer.append((new StringBuilder())
					.append("'").append(s1).append("',").toString()))
				s1 = (String) iterator1.next();

			if (stringbuffer.length() > 0)
				stringbuffer.deleteCharAt(stringbuffer.length() - 1);
			String s = SELECCION_POR_IDS_CON_SIGNIFICADO_SQL.replaceFirst("\\?", stringbuffer.toString());
			SeleccionPorIds _li = new SeleccionPorIds(getDataSource(), s);
			list3 = _li.execute();
		}

		return arraylist;
	}

	public List seleccionaPorLetra(String s) {
		return seleccionPorLetra.execute(new Object[] { s });
	}

	public List seleccionaPorCanonica(String s) {
		return seleccionPorAdverbio.execute(new Object[] { s });
	}

	public AdverbioBean getInidvidual(String s) {
		List list = seleccionPorIdConSignificado.execute(new Object[] { s });
		if (list.size() == 0)
			return null;
		else
			return (AdverbioBean) list.get(0);
	}

	public void modificaCodigoIndividual(int j, String s) {
		modificaCodigoIndividual.update(new Object[] { Integer.valueOf(j), s });
	}

	public void inserta(AdverbioBean m1) {
		String s = com.kalos.datos.util.DBUtil.getHashableId();
		insercion.update(new Object[] { s, m1.getLetra(), Integer.valueOf(m1.getCodigo()), m1.getAdverbio(),
				m1.getPartic().abreviatura(), m1.getGrado().abreviatura(),
				m1.getTipo().abreviatura() });
		m1.setId(s);
	}

	public void modifica(AdverbioBean m1) {
		modificacion.update(new Object[] { m1.getLetra(), Integer.valueOf(m1.getCodigo()), m1.getAdverbio(),
				m1.getPartic().abreviatura(), m1.getGrado().abreviatura(),
				m1.getTipo().abreviatura(), m1.getId() });
	}

	public void borra(String s) {
		borrado.update(new Object[] { s });
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorLetra = new SeleccionPorLetra(getDataSource());
		seleccionPorAdverbio = new SeleccionIdPorAdverbio(getDataSource());
		seleccionIdPorAdverbio = new SeleccionPorAdverbio(getDataSource());
		insercion = new Insercion(getDataSource());
		borrado = new Borrado(getDataSource(), BORRADO_SQL);
		modificacion = new Modificacion(getDataSource());
		modificaCodigoIndividual = new ModificaCodigoIndividual(getDataSource(), MODIFICACION_CODIGO_SQL);
        seleccionPorIdConSignificado = new SeleccionPorIdConSignificado(getDataSource());

	}

	static String G() {
		return SELECCION_POR_ADVERBIO_SQL;
	}

	static String A() {
		return SELECCION_ID_POR_LETRA_SQL;
	}

	static String D() {
		return SELECCION_ID_POR_ADVERBIO_SQL;
	}

	static String F() {
		return SELECCION_POR_ID_CON_SIGNIFICADO_SQL;
	}

	private static String SELECCION_ID_POR_LETRA_SQL;
	private static String SELECCION_ID_POR_ADVERBIO_SQL;
	private static String SELECCION_POR_ADVERBIO_SQL;
	private static String SELECCION_POR_ID_CON_SIGNIFICADO_SQL;
	private static String SELECCION_POR_IDS_CON_SIGNIFICADO_SQL;
	private static String INSERCION_SQL;
	private static String MODIFICACION_SQL;
	private static String BORRADO_SQL;
	private static String MODIFICACION_CODIGO_SQL;
	private SeleccionPorAdverbio seleccionIdPorAdverbio;
	private SeleccionPorLetra seleccionPorLetra;
	private SeleccionIdPorAdverbio seleccionPorAdverbio;
	private SeleccionPorIds seleccionPorIds;
    private SeleccionPorIdConSignificado seleccionPorIdConSignificado;
	private ModificaCodigoIndividual modificaCodigoIndividual;
	private Insercion insercion;
	private Modificacion modificacion;
	private Borrado borrado;
}
