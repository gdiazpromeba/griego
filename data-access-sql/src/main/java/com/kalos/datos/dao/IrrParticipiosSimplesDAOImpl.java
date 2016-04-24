package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.IrrParticipioSimpleBean;
import com.kalos.datos.util.DBUtil;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

public class IrrParticipiosSimplesDAOImpl extends JdbcDaoSupport implements IrrParticipiosSimplesDAO {
	private static String SEL_POR_VERBO_SQL;
	private static String SEL_POR_FORMA_SQL;
	private static String SEL_TODO;
	private static String INSERCION_SQL;
	private SelPorVerbo selPorVerbo;
	private SelPorForma selPorForma;
	private SelTodo selTodo;


	private void puebla() {
		StringBuffer sql = new StringBuffer();
		sql.append("select \n");
		sql.append("  VERBO_ID, PARTIC, SUBPART, ASPECTO, FUERTE, VOZ, GENERO, CASO, NUMERO, FORMA, \n");
		sql.append("  IRR_PARTICIPIO_SIMPLE_ID \n");
		sql.append("from IRR_PARTICIPIOS_SIMPLES  \n");
		sql.append("where  \n");
		sql.append("  VERBO_ID=? \n");
		sql.append("order by \n");
		sql.append("  PARTIC, \n");
		sql.append("  SUBPART, \n");
		sql.append("  ASPECTO, \n");
		sql.append("  FUERTE, \n");
		sql.append("  VOZ \n");
		SEL_POR_VERBO_SQL = sql.toString();

		sql = new StringBuffer();
		sql.append("select \n");
		sql.append("  VERBO_ID, PARTIC, SUBPART, ASPECTO, FUERTE, VOZ, GENERO, CASO, NUMERO, FORMA, \n");
		sql.append("  IRR_PARTICIPIO_SIMPLE_ID \n");
		sql.append("from IRR_PARTICIPIOS_SIMPLES  \n");
		sql.append("where  \n");
		sql.append("  FORMA=? \n");
		sql.append("order by \n");
		sql.append("  PARTIC, \n");
		sql.append("  SUBPART, \n");
		sql.append("  ASPECTO, \n");
		sql.append("  FUERTE, \n");
		sql.append("  VOZ \n");
		SEL_POR_FORMA_SQL = sql.toString();

		sql = new StringBuffer();
		sql.append("select \n");
		sql.append("  VERBO_ID, PARTIC, SUBPART, ASPECTO, FUERTE, VOZ, GENERO, CASO, NUMERO, FORMA, \n");
		sql.append("  IRR_PARTICIPIO_SIMPLE_ID \n");
		sql.append("from IRR_PARTICIPIOS_SIMPLES  \n");
		sql.append("order by \n");
		sql.append("  PARTIC, \n");
		sql.append("  SUBPART, \n");
		sql.append("  ASPECTO, \n");
		sql.append("  FUERTE, \n");
		sql.append("  VOZ \n");
		SEL_TODO = sql.toString();

		sql = new StringBuffer();
		sql.append("INSERT INTO IRR_PARTICIPIOS_SIMPLES ( \n");
		sql.append("  PARTIC,   \n");
		sql.append("  SUBPART,  \n");
		sql.append("  ASPECTO,  \n");
		sql.append("  FUERTE,   \n");
		sql.append("  VOZ,      \n");
		sql.append("  CASO,    \n");
		sql.append("  NUMERO,  \n");
		sql.append("  GENERO,  \n");
		sql.append("  FORMA, \n");
		sql.append("  IRR_PARTICIPIO_SIMPLE_ID, \n");
		sql.append("  VERBO_ID \n");
		sql.append(") VALUES  ( \n");
		sql.append(" ?,?,?,?,?,?,?,?,?,?, \n");
		sql.append(" ?) \n");
		INSERCION_SQL = sql.toString();

	}


	class Insercion extends SqlUpdate {

		public Insercion(DataSource datasource) {
			super(datasource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.CHAR)); // PARTIC
			declareParameter(new SqlParameter(Types.INTEGER)); // SUBPART
			declareParameter(new SqlParameter(Types.INTEGER)); // ASPECTO
			declareParameter(new SqlParameter(Types.INTEGER)); // FUERTE
			declareParameter(new SqlParameter(Types.INTEGER)); // VOZ
			declareParameter(new SqlParameter(Types.INTEGER)); // CASO
			declareParameter(new SqlParameter(Types.INTEGER)); // NUMERO
			declareParameter(new SqlParameter(Types.CHAR)); // GENERO
			declareParameter(new SqlParameter(Types.VARCHAR)); // FORMA
			declareParameter(new SqlParameter(Types.CHAR)); // IRR_PARTICIPIO_SIMPLE_ID
			declareParameter(new SqlParameter(Types.CHAR)); // VERBO_ID
		}
	}

	private Insercion insercion;

	public void inserta(IrrParticipioSimpleBean bean) {
		String id = DBUtil.getHashableId();
		insercion.update(new Object[] {
				bean.getPartic().abreviatura(),
				bean.getSubPart(),
				bean.getAspecto().valorEntero(),
				bean.getFuerte().valorEntero(),
				bean.getVoz().valorEntero(),
				bean.getCaso().valorEntero(),
				bean.getNumero().valorEntero(),
				bean.getGenero().valorLetra(),
				bean.getForma(),
				bean.getId(),
				bean.getVerboId()
		});

	}

	public List<IrrParticipioSimpleBean> seleccionaPorVerbo(String paramString) {
		List localList = this.selPorVerbo.execute(new Object[] { paramString });
		return localList;
	}

	public List<IrrParticipioSimpleBean> seleccionaPorForma(String paramString) {
		List localList = this.selPorForma.execute(new Object[] { paramString });
		return localList;
	}

	public List<IrrParticipioSimpleBean> seleccionaTodo() {
		List localList = this.selTodo.execute(new Object[] {  });
		return localList;
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		this.selPorVerbo = new SelPorVerbo(getDataSource());
		this.selPorForma = new SelPorForma(getDataSource());
		selTodo = new SelTodo(getDataSource());
		insercion = new Insercion(getDataSource());
	}

	class SelPorForma extends IrrParticipiosSimplesDAOImpl.SeleccionAbsracta {
		public SelPorForma(DataSource paramDataSource) {
			super(paramDataSource, SEL_POR_FORMA_SQL);
			declareParameter(new SqlParameter(12));
		}
	}

	class SelTodo extends IrrParticipiosSimplesDAOImpl.SeleccionAbsracta {
		public SelTodo(DataSource paramDataSource) {
			super(paramDataSource, SEL_TODO);
		}
	}

	class SelPorVerbo extends IrrParticipiosSimplesDAOImpl.SeleccionAbsracta {
		public SelPorVerbo(DataSource paramDataSource) {
			super(paramDataSource, SEL_POR_VERBO_SQL);
			declareParameter(new SqlParameter(1));
		}
	}

	abstract class SeleccionAbsracta extends MappingSqlQuery {
		public SeleccionAbsracta(DataSource paramDataSource, String paramString) {
			super(paramDataSource, paramString);
		}

		protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
			IrrParticipioSimpleBean bean = new IrrParticipioSimpleBean();
			bean.setId(paramResultSet.getString("IRR_PARTICIPIO_SIMPLE_ID"));
			bean.setVerboId(paramResultSet.getString("VERBO_ID"));
			bean.setPartic(Particularidad.getEnum(paramResultSet.getString("PARTIC")));
			bean.setSubPart(paramResultSet.getInt("SUBPART"));
			bean.setAspecto(Aspecto.getEnum(paramResultSet.getInt("ASPECTO")));
			bean.setVoz(Voz.getEnum(paramResultSet.getInt("VOZ")));
			bean.setFuerte(FuerteDebil.getEnum(paramResultSet.getInt("FUERTE")));
			bean.setGenero(Genero.getEnum(paramResultSet.getString("GENERO")));
			bean.setNumero(Numero.getEnum(paramResultSet.getInt("NUMERO")));
			bean.setCaso(Caso.getEnum(paramResultSet.getInt("CASO")));
			bean.setForma(paramResultSet.getString("FORMA"));
			return bean;
		}
	}
}
