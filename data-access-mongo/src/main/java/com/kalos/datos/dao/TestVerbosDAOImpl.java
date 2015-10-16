package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.TestVerboBean;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

@SuppressWarnings("unchecked")
public class TestVerbosDAOImpl extends JdbcDaoSupport implements TestVerbosDAO {

	private static String SELECCION_TODOS_SQL;


	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  VER.VERBO,   \n");
		sb.append("  TEV.TEST_VERBO_ID,   \n");
		sb.append("  TEV.FORMA_CONJUGADA,   \n");
		sb.append("  TEV.VOZ,   \n");
		sb.append("  TEV.MODO,   \n");
		sb.append("  TEV.TIEMPO,   \n");
		sb.append("  TEV.FUERTE,   \n");
		sb.append("  TEV.PERSONA,   \n");
		sb.append("  TEV.VERBO_ID   \n");
		sb.append("FROM        \n");
		sb.append("  VERBOS VER       \n");
		sb.append("    INNER JOIN TEST_VERBOS TEV                 \n");
		sb.append("      ON VER.VERBO_ID=TEV.VERBO_ID       \n");

		SELECCION_TODOS_SQL = sb.toString();
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestVerboBean bean = new TestVerboBean();
			bean.setId(rs.getString("TEST_VERBO_ID"));
			bean.setVerbo(rs.getString("VERBO"));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setModo(Modo.getEnum(rs.getInt("MODO")));
			bean.setTiempo(Tiempo.getEnum(rs.getInt("TIEMPO")));
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setId(rs.getString("TEST_VERBO_ID"));
			bean.setIdVerbo(rs.getString("VERBO_ID"));
			bean.setPersona(Persona.getEnum(rs.getInt("PERSONA")));
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
	 * @see kalos.dao.TestVerbo#seleccionaTodos()
	 */
	public List<TestVerboBean> seleccionaTodos() {
		return seleccionTodos.execute();
	}


	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionTodos = new SeleccionTodos(getDataSource());
	}

}
