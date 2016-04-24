package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.TestInfinitivoBean;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

@SuppressWarnings("unchecked")
public class TestInfinitivosDAOImpl extends JdbcDaoSupport implements TestInfinitivosDAO  {

	private static String SELECCION_TODOS_SQL;


	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  VER.VERBO,   \n");
		sb.append("  TEI.TEST_INFINITIVO_ID,   \n");
		sb.append("  TEI.FORMA_CONJUGADA,   \n");
		sb.append("  TEI.VOZ,   \n");
		sb.append("  TEI.ASPECTO,   \n");
		sb.append("  TEI.FUERTE,   \n");
		sb.append("  TEI.VERBO_ID   \n");
		sb.append("FROM        \n");
		sb.append("  VERBOS VER       \n");
		sb.append("    INNER JOIN TEST_INFINITIVOS TEI                 \n");
		sb.append("      ON VER.VERBO_ID=TEI.VERBO_ID       \n");
		//sb.append("WHERE TEI.ACTIVA=1       \n");
		SELECCION_TODOS_SQL = sb.toString();
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestInfinitivoBean bean = new TestInfinitivoBean();
			bean.setId(rs.getString("TEST_INFINITIVO_ID"));
			bean.setVerbo(rs.getString("VERBO"));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setAspecto(Aspecto.getEnum(rs.getInt("ASPECTO")));
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setIdVerbo(rs.getString("VERBO_ID"));
			bean.setFormaConjugada(rs.getString("FORMA_CONJUGADA"));
			return bean;
		}
	}

	//selección de todos
	class SeleccionTodos extends SeleccionAbstracta {
		public SeleccionTodos(DataSource dataSource) {
			super(dataSource, SELECCION_TODOS_SQL);
		}
	}

	private SeleccionTodos seleccionTodos;
	
	/* (non-Javadoc)
	 * @see kalos.dao.TestVerbo#seleccionaTodo()
	 */
	/* (non-Javadoc)
	 * @see kalos.dao.TestInfinitivosDAO#seleccionaTodo()
	 */
	public List<TestInfinitivoBean> seleccionaTodos() {
		return seleccionTodos.execute();
	}


	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionTodos = new SeleccionTodos(getDataSource());
	}

}
