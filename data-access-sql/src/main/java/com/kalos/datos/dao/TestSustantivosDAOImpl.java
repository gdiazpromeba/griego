package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.TestSustantivoBean;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

@SuppressWarnings("unchecked")
public class TestSustantivosDAOImpl extends JdbcDaoSupport implements TestSustantivosDAO   {

	private String SELECCION_TODOS_SQL;


	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  TES.TEST_SUSTANTIVO_ID,   \n");
		sb.append("  TES.SUSTANTIVO_ID,   \n");
		sb.append("  TES.FORMA_DECLINADA,   \n");
		sb.append("  TES.CASO,   \n");
		sb.append("  TES.NUMERO   \n");
		sb.append("FROM        \n");
		sb.append("  TEST_SUSTANTIVOS TES                 \n");
		sb.append("ORDER BY         \n");
		sb.append("  SUSTANTIVO_ID                 \n");
		

		SELECCION_TODOS_SQL = sb.toString();
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestSustantivoBean bean = new TestSustantivoBean();
			bean.setId(rs.getString("TEST_SUSTANTIVO_ID"));
			bean.setIdSustantivo(rs.getString("SUSTANTIVO_ID"));
			bean.setFormaDeclinada(rs.getString("FORMA_DECLINADA"));
			bean.setCaso(Caso.getEnum(rs.getInt("CASO")));
			bean.setNumero(Numero.getEnum(rs.getInt("NUMERO")));
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
	 * @see kalos.dao.TestSustantivosDAO#seleccionaTodo()
	 */
	public List<TestSustantivoBean> seleccionaTodos() {
		return seleccionTodos.execute();
	}


	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionTodos = new SeleccionTodos(getDataSource());
	}

}
