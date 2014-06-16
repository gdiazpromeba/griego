/*
 *  This program is an unpublished work fully protected by the United
 *  States copyright laws and is considered a trade secret belonging
 *  to Turner Entertainment Group. To the extent that this work may be
 *  considered "published,"
 *  the following notice applies:
 *
 *      "Copyright 2005, Turner Entertainment Group."
 *
 *  Any unauthorized use, reproduction, distribution, display,
 *  modification, or disclosure of this program is strictly prohibited.
 *
 */
/**
 * 
 */
package kalos.datos.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import kalos.beans.SubstractorPrefijosBean;
import kalos.enumeraciones.Espiritu;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class SubstractorPrefijosDAOImpl extends JdbcDaoSupport implements SubstractorPrefijosDAO   {

	private static String SELECCION_POR_FORMA_SQL;

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);

        sb.append("SELECT \n");
        sb.append("   PREFIJO,\n");
        sb.append("   PREPOSICION,\n");
        sb.append("   AGREGAR_AL_COMER,\n");
        sb.append("   VOCAL_ANTES, \n");
        sb.append("   ESPIRITU \n");
        sb.append("FROM \n");
        sb.append("   SUBSTRACTOR_PREFIJOS \n");
        sb.append("WHERE \n");
        sb.append("  SUBSTRING(?, 1, LENGTH(PREFIJO))=PREFIJO");
        SELECCION_POR_FORMA_SQL=sb.toString();

	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubstractorPrefijosBean bean = new SubstractorPrefijosBean();
			bean.setPrefijo(rs.getString("PREFIJO"));
			bean.setPreposicion(rs.getString("PREPOSICION"));
			bean.setAgregarAlComer(rs.getString("AGREGAR_AL_COMER"));
			bean.setEspiritu(Espiritu.getEnum(rs.getInt("ESPIRITU")));
			bean.setVocalAntes(rs.getInt("VOCAL_ANTES"));
			return bean;
		}
	}

	//selección por forma
	class SeleccionPorForma extends SeleccionAbstracta {
		public SeleccionPorForma(DataSource dataSource) {
			super(dataSource, SELECCION_POR_FORMA_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}
	}

	//select todos
	private SeleccionPorForma seleccionPorForma;

	/* (non-Javadoc)
	 * @see kalos.dao.SubstractorPrefijosDAO#seleccionaPorForma(java.lang.String)
	 */
	public List<SubstractorPrefijosBean> seleccionaPorForma(String forma) {
		return seleccionPorForma.execute(new Object[] {forma});
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorForma = new SeleccionPorForma(getDataSource());
	}

}
