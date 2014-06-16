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

import kalos.beans.TermRegVerbo;
import kalos.enumeraciones.Acento;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class TermRegVerboDAOImpl extends JdbcDaoSupport implements TermRegVerboDAO  {

	private static String SELECCION_POR_TERMINACION_SQL;

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);

		sb.append("SELECT \n");
		sb.append("   VOZ, \n");
		sb.append("   MODO, \n");
		sb.append("   TIEMPO, \n");
		sb.append("   FUERTE, \n");
		sb.append("   PERSONA, \n");
		sb.append("   TIPO_DESINENCIA, \n");
		sb.append("   TERMINACION, \n");
		sb.append("   REGEX_TERM, \n");
		sb.append("   SILABA, \n");
		sb.append("   ACENTO \n");
		sb.append("FROM \n");
		sb.append("   TERM_REG_VERBOS \n");
		sb.append("WHERE \n");
		sb.append(" SUBSTRING(?, LENGTH(?)-LENGTH(TERMINACION)+1, LENGTH(?))=TERMINACION \n");
		SELECCION_POR_TERMINACION_SQL = sb.toString();
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TermRegVerbo bean = new TermRegVerbo();
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setModo(Modo.getEnum(rs.getInt("MODO")));
			bean.setTiempo(Tiempo.getEnum(rs.getInt("TIEMPO")));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setPersona(Persona.getEnum(rs.getInt("PERSONA")));
			bean.setTipoDesinencia(rs.getInt("TIPO_DESINENCIA"));
			bean.setTerminacion(rs.getString("TERMINACION"));
			bean.setRegEx(rs.getString("REGEX_TERM"));
			bean.setSilaba(rs.getInt("SILABA"));
			bean.setAcento(Acento.getEnum(rs.getInt("ACENTO")));
			return bean;
		}
	}

	//selección por terminación
	class SeleccionPorTerminacion extends SeleccionAbstracta {
		public SeleccionPorTerminacion(DataSource dataSource) {
			super(dataSource, SELECCION_POR_TERMINACION_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR)); //la termnación
			declareParameter(new SqlParameter(Types.VARCHAR));// la misma terminación
			declareParameter(new SqlParameter(Types.VARCHAR));//la misma terminación (3 veces)
		}
	}


	private SeleccionPorTerminacion seleccionPorTerminacion;

	/* (non-Javadoc)
	 * @see kalos.dao.TermRegVerboDAO#seleccionaPorTerminacion(java.lang.String)
	 */
	public List<TermRegVerbo> seleccionaPorTerminacion(String terminacion) {
		List<TermRegVerbo> lstAux = seleccionPorTerminacion.execute(new Object[] {
				terminacion, terminacion, terminacion});
		return lstAux;
	}


	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorTerminacion = new SeleccionPorTerminacion(getDataSource());
	}

}
