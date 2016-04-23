// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.DesinSust;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.AcentoConcuerda;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.OrigenTema;
import com.kalos.enumeraciones.PosicionConcuerda;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

// Referenced classes of package kalos.E.C:
//            Q

public class DesinSustDAOImpl extends JdbcDaoSupport implements DesinSustDAO {

	public List<DesinSust> seleccionaTodos(){
		List<DesinSust> list= selTodos.execute(new Object[0]);
		return list;
	}

	class Update extends SqlUpdate {

		public Update(DataSource datasource) {
			super(datasource, UPDATE_SQL);
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(12));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(12));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(1));
		}
	}

	class Insert extends SqlUpdate {

		public Insert(DataSource datasource) {
			super(datasource, INSERT_SQL);
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(12));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(1));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(12));
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(4));
		}
	}

	class SelIdsPorTipoSust extends SeleccionIds {

		public SelIdsPorTipoSust(DataSource datasource, String sql) {
			super(datasource, sql);
		}
	}



	class SeleccionNomVoc extends SeleccionAbstracta {

		public SeleccionNomVoc(DataSource datasource) {
			super(datasource, SEL_NOM_VOC_SQL);
		}
	}

	class SeleccionConContraccion extends com.kalos.datos.dao.comunes.SeleccionIds {

		public SeleccionConContraccion(DataSource datasource) {
			super(datasource, SEL_CON_CONTRACCION_SQL, "DESIN_SUST_ID" );
		}
	}

	class SelPorId extends SeleccionAbstracta {

		public SelPorId(DataSource datasource, String s) {
			super(datasource, s);
		}
	}

	class SelTodos extends SeleccionAbstracta {

		public SelTodos(DataSource datasource) {
			super(datasource, SEL_TODOS);
		}
	}

	class Delete extends SqlUpdate {

		public Delete(DataSource datasource, String s) {
			super(datasource, s);
		}
	}

	abstract class SeleccionIds extends MappingSqlQuery {

		protected Object mapRow(ResultSet resultset, int i) throws SQLException {
			String s = resultset.getString("DESIN_SUST_ID");
			return s;
		}


		public SeleccionIds(DataSource datasource, String s) {
			super(datasource, s);
		}
	}

	abstract class SeleccionAbstracta extends MappingSqlQuery {

		protected Object mapRow(ResultSet resultset, int i) throws SQLException {
			DesinSust bean = new DesinSust();
			bean.setId(resultset.getString("DESIN_SUST_ID"));
			bean.setSubindice(resultset.getInt("SUBINDICE"));
			bean.setDesinencia(resultset.getString("DESINENCIA"));
			bean.setSilaba(resultset.getInt("SILABA"));
			bean.setAcento(Acento.getEnum(resultset.getInt("ACENTO")));
			bean.setCaso(Caso.getEnum(resultset.getInt("CASO")));
			bean.setNumero(Numero.getEnum(resultset.getInt("NUMERO")));
			bean.setContraccion(Contraccion.getEnum(resultset.getInt("CONTRACCION")));
			bean.setOrigenTema(OrigenTema.getEnum(resultset.getString("ORIGEN_TEMA")));
			bean.setTipoSustantivo(resultset.getInt("TIPO_SUSTANTIVO"));
			bean.setTipoSustantivoId(resultset.getString("TIPO_SUSTANTIVO_ID"));
			bean.setTipoSustDesClave(resultset.getString("TIPO_SUST_DES_CLAVE"));
			bean.setAcentoConcuerda(AcentoConcuerda.getEnum(resultset.getInt("ACENTO_CONCUERDA")));
			bean.setPosicionConcuerda(PosicionConcuerda.getEnum(resultset.getInt("POSICION_CONCUERDA")));
			bean.setRegexDesinencia(resultset.getString("REGEX_DESINENCIA"));
			bean.setOrden(Integer.valueOf(resultset.getInt("ORDEN")));
			bean.setOrdenPorDefecto(resultset.getInt("ORDEN_POR_DEFECTO"));
			return bean;
		}

		public SeleccionAbstracta(DataSource datasource, String s) {
			super(datasource, s);
		}
	}


	private void puebla() {
		StringBuffer stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  DESIN_SUST DSU       \n");
		stringbuffer.append("  INNER JOIN TIPOS_SUSTANTIVO TIS       \n");
		stringbuffer.append("    ON DSU.TIPO_SUSTANTIVO=TIS.TIPO_SUSTANTIVO     \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO_ID IN (?)  ");
		stringbuffer.append("  CONDICION_ORDEN  ");
		stringbuffer.append("ORDER BY  \n");
		stringbuffer.append("  DSU.NUMERO,   \n");
		stringbuffer.append("  DSU.CASO,   \n");
		stringbuffer.append("  DSU.SUBINDICE,   \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO   \n");
		SEL_ID_POR_TIPO_SUST_SQL = stringbuffer.toString();

		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID,   \n");
		stringbuffer.append("  DSU.SUBINDICE,   \n");
		stringbuffer.append("  DSU.DESINENCIA,   \n");
		stringbuffer.append("  DSU.SILABA,   \n");
		stringbuffer.append("  DSU.ACENTO,   \n");
		stringbuffer.append("  DSU.CASO,   \n");
		stringbuffer.append("  DSU.NUMERO,   \n");
		stringbuffer.append("  DSU.CONTRACCION,   \n");
		stringbuffer.append("  DSU.ORIGEN_TEMA,   \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  TIS.TIPO_SUST_DES_CLAVE,   \n");
		stringbuffer.append("  TIS.TIPO_SUSTANTIVO_ID,   \n");
		stringbuffer.append("  DSU.ACENTO_CONCUERDA,   \n");
		stringbuffer.append("  DSU.POSICION_CONCUERDA,   \n");
		stringbuffer.append("  DSU.REGEX_DESINENCIA,   \n");
		stringbuffer.append("  DSU.ORDEN,   \n");
		stringbuffer.append("  DSU.ORDEN_POR_DEFECTO   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  DESIN_SUST DSU       \n");
		stringbuffer.append("  INNER JOIN TIPOS_SUSTANTIVO TIS       \n");
		stringbuffer.append("    ON DSU.TIPO_SUSTANTIVO=TIS.TIPO_SUSTANTIVO     \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  DSU.CASO IN (1,2)  ");
		stringbuffer.append("  AND DSU.NUMERO<>0  ");
		stringbuffer.append("ORDER BY  \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  DSU.CASO   \n");
		SEL_NOM_VOC_SQL = stringbuffer.toString();


		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID,   \n");
		stringbuffer.append("  DSU.SUBINDICE,   \n");
		stringbuffer.append("  DSU.DESINENCIA,   \n");
		stringbuffer.append("  DSU.SILABA,   \n");
		stringbuffer.append("  DSU.ACENTO,   \n");
		stringbuffer.append("  DSU.CASO,   \n");
		stringbuffer.append("  DSU.NUMERO,   \n");
		stringbuffer.append("  DSU.CONTRACCION,   \n");
		stringbuffer.append("  DSU.ORIGEN_TEMA,   \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  TIS.TIPO_SUST_DES_CLAVE,   \n");
		stringbuffer.append("  TIS.TIPO_SUSTANTIVO_ID,   \n");
		stringbuffer.append("  DSU.ACENTO_CONCUERDA,   \n");
		stringbuffer.append("  DSU.POSICION_CONCUERDA,   \n");
		stringbuffer.append("  DSU.REGEX_DESINENCIA,   \n");
		stringbuffer.append("  DSU.ORDEN,   \n");
		stringbuffer.append("  DSU.ORDEN_POR_DEFECTO   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  DESIN_SUST DSU       \n");
		stringbuffer.append("  INNER JOIN TIPOS_SUSTANTIVO TIS       \n");
		stringbuffer.append("    ON DSU.TIPO_SUSTANTIVO=TIS.TIPO_SUSTANTIVO     \n");
		stringbuffer.append("ORDER BY  \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  DSU.CASO   \n");
		SEL_TODOS = stringbuffer.toString();

		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  DESIN_SUST DSU       \n");
		stringbuffer.append("  INNER JOIN TIPOS_SUSTANTIVO TIS       \n");
		stringbuffer.append("    ON DSU.TIPO_SUSTANTIVO=TIS.TIPO_SUSTANTIVO     \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  DSU.CONTRACCION<>0  ");
		stringbuffer.append("ORDER BY  \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  DSU.NUMERO,   \n");
		stringbuffer.append("  DSU.CASO,   \n");
		stringbuffer.append("  DSU.SUBINDICE   \n");
		SEL_CON_CONTRACCION_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID,   \n");
		stringbuffer.append("  DSU.SUBINDICE,   \n");
		stringbuffer.append("  DSU.DESINENCIA,   \n");
		stringbuffer.append("  DSU.SILABA,   \n");
		stringbuffer.append("  DSU.ACENTO,   \n");
		stringbuffer.append("  DSU.CASO,   \n");
		stringbuffer.append("  DSU.NUMERO,   \n");
		stringbuffer.append("  DSU.CONTRACCION,   \n");
		stringbuffer.append("  DSU.ORIGEN_TEMA,   \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  TIS.TIPO_SUST_DES_CLAVE,   \n");
		stringbuffer.append("  TIS.TIPO_SUSTANTIVO_ID,   \n");
		stringbuffer.append("  DSU.ACENTO_CONCUERDA,   \n");
		stringbuffer.append("  DSU.POSICION_CONCUERDA,   \n");
		stringbuffer.append("  DSU.REGEX_DESINENCIA,   \n");
		stringbuffer.append("  DSU.ORDEN,   \n");
		stringbuffer.append("  DSU.ORDEN_POR_DEFECTO   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  DESIN_SUST DSU       \n");
		stringbuffer.append("  INNER JOIN TIPOS_SUSTANTIVO TIS       \n");
		stringbuffer.append("    ON DSU.TIPO_SUSTANTIVO=TIS.TIPO_SUSTANTIVO     \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID=?  ");
		GET_POR_ID_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("SELECT   \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID,   \n");
		stringbuffer.append("  DSU.SUBINDICE,   \n");
		stringbuffer.append("  DSU.DESINENCIA,   \n");
		stringbuffer.append("  DSU.SILABA,   \n");
		stringbuffer.append("  DSU.ACENTO,   \n");
		stringbuffer.append("  DSU.CASO,   \n");
		stringbuffer.append("  DSU.NUMERO,   \n");
		stringbuffer.append("  DSU.CONTRACCION,   \n");
		stringbuffer.append("  DSU.ORIGEN_TEMA,   \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  TIS.TIPO_SUST_DES_CLAVE,   \n");
		stringbuffer.append("  TIS.TIPO_SUSTANTIVO_ID,   \n");
		stringbuffer.append("  DSU.ACENTO_CONCUERDA,   \n");
		stringbuffer.append("  DSU.POSICION_CONCUERDA,   \n");
		stringbuffer.append("  DSU.REGEX_DESINENCIA,   \n");
		stringbuffer.append("  DSU.ORDEN,   \n");
		stringbuffer.append("  DSU.ORDEN_POR_DEFECTO   \n");
		stringbuffer.append("FROM        \n");
		stringbuffer.append("  DESIN_SUST DSU       \n");
		stringbuffer.append("  INNER JOIN TIPOS_SUSTANTIVO TIS       \n");
		stringbuffer.append("    ON DSU.TIPO_SUSTANTIVO=TIS.TIPO_SUSTANTIVO     \n");
		stringbuffer.append("WHERE  \n");
		stringbuffer.append("  DSU.DESIN_SUST_ID IN (?)  ");
		stringbuffer.append("ORDER BY  \n");
		stringbuffer.append("  DSU.NUMERO,   \n");
		stringbuffer.append("  DSU.CASO,   \n");
		stringbuffer.append("  DSU.SUBINDICE,   \n");
		stringbuffer.append("  DSU.TIPO_SUSTANTIVO   \n");
		SEL_POR_ID = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("INSERT DESIN_SUST (   \n");
		stringbuffer.append("  DESIN_SUST_ID,   \n");
		stringbuffer.append("  SUBINDICE,   \n");
		stringbuffer.append("  DESINENCIA,   \n");
		stringbuffer.append("  SILABA,   \n");
		stringbuffer.append("  ACENTO,   \n");
		stringbuffer.append("  CASO,   \n");
		stringbuffer.append("  NUMERO,   \n");
		stringbuffer.append("  CONTRACCION,   \n");
		stringbuffer.append("  ORIGEN_TEMA,   \n");
		stringbuffer.append("  TIPO_SUSTANTIVO,   \n");
		stringbuffer.append("  TIPO_SUSTANTIVO_ID,   \n");
		stringbuffer.append("  ACENTO_CONCUERDA,   \n");
		stringbuffer.append("  POSICION_CONCUERDA,   \n");
		stringbuffer.append("  REGEX_DESINENCIA,   \n");
		stringbuffer.append("  ORDEN,   \n");
		stringbuffer.append("  ORDEN_POR_DEFECTO   \n");
		stringbuffer.append(" ) VALUES (    \n");
		stringbuffer.append("  ?,?,?,?,?,?,?,?,?,?,     \n");
		stringbuffer.append("  ?,?,?,?,?,?              \n");
		stringbuffer.append(")     \n");
		INSERT_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("UPDATE DESIN_SUST SET   \n");
		stringbuffer.append("  SUBINDICE=?,   \n");
		stringbuffer.append("  DESINENCIA=?,   \n");
		stringbuffer.append("  SILABA=?,   \n");
		stringbuffer.append("  ACENTO=?,   \n");
		stringbuffer.append("  CASO=?,   \n");
		stringbuffer.append("  NUMERO=?,   \n");
		stringbuffer.append("  CONTRACCION=?,   \n");
		stringbuffer.append("  ORIGEN_TEMA=?,   \n");
		stringbuffer.append("  TIPO_SUSTANTIVO=?,   \n");
		stringbuffer.append("  TIPO_SUSTANTIVO_ID=?,   \n");
		stringbuffer.append("  ACENTO_CONCUERDA=?,   \n");
		stringbuffer.append("  POSICION_CONCUERDA=?,   \n");
		stringbuffer.append("  REGEX_DESINENCIA=?,   \n");
		stringbuffer.append("  ORDEN=?,   \n");
		stringbuffer.append("  ORDEN_POR_DEFECTO=?   \n");
		stringbuffer.append("WHERE             \n");
		stringbuffer.append("  DESIN_SUST_ID=?   \n");
		UPDATE_SQL = stringbuffer.toString();
		stringbuffer = new StringBuffer(200);
		stringbuffer.append("DELETE FROM DESIN_SUST WHERE   \n");
		stringbuffer.append("  DESIN_SUST_ID=?  \n");
		DELETE_SQL = stringbuffer.toString();
	}

	public List getRegistros(List list) {
		StringBuffer stringbuffer = new StringBuffer(500);
		String s1;
		for (Iterator iterator = list.iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder())
				.append("'").append(s1).append("',").toString()))
			s1 = (String) iterator.next();

		if (stringbuffer.length() > 0)
			stringbuffer.deleteCharAt(stringbuffer.length() - 1);
		else
			return new ArrayList();
		String s = SEL_POR_ID.replaceFirst("\\?", stringbuffer.toString());
		SelPorId _li = new SelPorId(getDataSource(), s);
		List list1 = _li.execute();
		return list1;
	}

	public List getPorTipos(String as[], boolean flag) {
		StringBuffer stringbuffer = new StringBuffer();
		String as1[] = as;
		int i = as1.length;
		for (int j = 0; j < i; j++) {
			String s1 = as1[j];
			stringbuffer.append((new StringBuilder()).append("'").append(s1).append("',").toString());
		}

		stringbuffer.deleteCharAt(stringbuffer.length() - 1);
		String s = SEL_ID_POR_TIPO_SUST_SQL.replaceFirst("\\?", stringbuffer.toString());
		if (flag)
			s = s.replaceFirst("CONDICION_ORDEN", "");
		else
			s = s.replaceFirst("CONDICION_ORDEN", " AND ORDEN IS NOT NULL \n");
		SelIdsPorTipoSust selIds = new SelIdsPorTipoSust(getDataSource(), s);
		List list = selIds.execute(new Object[0]);
		return list;
	}

	public DesinSust getInidvidual(String s) {
		List list = selConContraccion.execute(new Object[] { s });
		if (list.size() == 0)
			return null;
		else
			return (DesinSust) list.get(0);
	}

	public List seleccionaNominativosSingulares() {
		List list = selNomVoc.execute(new Object[0]);
		return list;
	}

	public List seleccionaContracciones() {
		List list = selConContraccion.execute(new Object[0]);
		return list;
	}


	public void inserta(DesinSust bean) {
		String s = com.kalos.datos.util.DBUtil.getHashableId();
		insert.update(new Object[] {
				s,
				Integer.valueOf(bean.getSubindice()),
				bean.getDesinencia(),
				bean.getSilaba(),
				bean.getAcento().valorEntero(),
				bean.getCaso().valorEntero(),
				bean.getNumero().valorEntero(),
				bean.getContraccion().valorEntero() ,
				bean.getOrigenTema().abreviatura(),
				Integer.valueOf(bean.getTipoSustantivo()),
				bean.getTipoSustantivoId(),
				bean.getAcentoConcuerda().valorEntero(),
				bean.getPosicionConcuerda().valorEntero(),
				bean.getRegexDesinencia(),
				bean.getOrden(),
				Integer.valueOf(bean.getOrdenPorDefecto()) });
		bean.setId(s);
	}

	public void modifica(DesinSust bean) {
		update.update(new Object[] { Integer.valueOf(bean.getSubindice()), bean.getDesinencia(),
				Integer.valueOf(bean.getSilaba()), bean.getAcento().valorEntero(),
				bean.getCaso().valorEntero(), bean.getNumero().valorEntero(),
				bean.getContraccion().valorEntero(), bean.getOrigenTema().abreviatura(),
				bean.getTipoSustantivo(), bean.getTipoSustantivoId(), bean.getAcentoConcuerda().valorEntero(),
				bean.getPosicionConcuerda().valorEntero(), bean.getRegexDesinencia(), bean.getOrden(),
				Integer.valueOf(bean.getOrdenPorDefecto()), bean.getId() });
	}

	public void borra(String s) {
		delete.update(new Object[] { s });
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		selConContraccion = new SeleccionConContraccion(getDataSource());
		selNomVoc = new SeleccionNomVoc(getDataSource());
		selPorId = new SelPorId(getDataSource(), SEL_ID_POR_TIPO_SUST_SQL);
		insert = new Insert(getDataSource());
		delete = new Delete(getDataSource(), DELETE_SQL);
		update = new Update(getDataSource());
		selTodos = new SelTodos(getDataSource());
	}


	private static String SEL_ID_POR_TIPO_SUST_SQL;
	private static String SEL_NOM_VOC_SQL;
	private static String SEL_CON_CONTRACCION_SQL;
	private static String SEL_POR_ID;
	private static String SEL_TODOS;
	private static String INSERT_SQL;
	private static String UPDATE_SQL;
	private static String DELETE_SQL;
	private static String GET_POR_ID_SQL;
	private SeleccionConContraccion selConContraccion;
	private SeleccionNomVoc selNomVoc;
	private SelPorId selPorId;
	private Insert insert;
	private Update update;
	private Delete delete;
	private SelTodos selTodos;
}
