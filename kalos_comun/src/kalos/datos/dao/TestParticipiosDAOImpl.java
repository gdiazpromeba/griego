package kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kalos.beans.TestParticipiosBean;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.Caso;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.Numero;
import kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

@SuppressWarnings("unchecked")
public class TestParticipiosDAOImpl extends JdbcDaoSupport implements TestParticipiosDAO  {

	private static String SELECCION_TODOS_SQL;


	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  VER.VERBO,   \n");
		sb.append("  TEP.TEST_PARTICIPIO_ID,   \n");
		sb.append("  TEP.FORMA_DECLINADA,   \n");
		sb.append("  TEP.VOZ,   \n");
		sb.append("  TEP.ASPECTO,   \n");
		sb.append("  TEP.FUERTE,   \n");
		sb.append("  TEP.CASO,   \n");
		sb.append("  TEP.NUMERO,   \n");
		sb.append("  TEP.GENERO,   \n");
		sb.append("  TEP.VERBO_ID   \n");
		sb.append("FROM        \n");
		sb.append("  VERBOS VER       \n");
		sb.append("    INNER JOIN TEST_PARTICIPIOS TEP                 \n");
		sb.append("      ON VER.VERBO_ID=TEP.VERBO_ID       \n");

		SELECCION_TODOS_SQL = sb.toString();
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestParticipiosBean bean = new TestParticipiosBean();
			bean.setId(rs.getString("TEST_PARTICIPIO_ID"));
			bean.setVerbo(rs.getString("VERBO"));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setAspecto(Aspecto.getEnum(rs.getInt("ASPECTO")));
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setIdVerbo(rs.getString("VERBO_ID"));
			bean.setGenero(Genero.getEnum(rs.getString("GENERO")));
			bean.setNumero(Numero.getEnum(rs.getInt("NUMERO")));
			bean.setCaso(Caso.getEnum(rs.getInt("CASO")));
			bean.setFormaDeclinada(rs.getString("FORMA_DECLINADA"));
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
	/* (non-Javadoc)
	 * @see kalos.dao.TestInfinitivosDAO#seleccionaTodos()
	 */
	public List<TestParticipiosBean> seleccionaTodos() {
		return seleccionTodos.execute();
	}


	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionTodos = new SeleccionTodos(getDataSource());
	}

}
