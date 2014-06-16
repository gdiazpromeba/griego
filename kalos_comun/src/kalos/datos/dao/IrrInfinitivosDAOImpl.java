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

import kalos.beans.IrrInfinitivoBean;
import kalos.enumeraciones.Aspecto;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class IrrInfinitivosDAOImpl extends JdbcDaoSupport implements IrrInfinitivosDAO {

	private String SELECCION_POR_VERBO_PARTIC;
	private String SELECCION_POR_FORMA;

	private void puebla() {
        StringBuffer sb=new StringBuffer();
        sb.append("SELECT \n");
        sb.append("  irr.VERBO_ID,   \n");
        sb.append("  irr.IRR_INFINITIVO_ID,   \n");
        sb.append("  irr.PARTIC,   \n");
        sb.append("  irr.SUBPART,   \n");
        sb.append("  irr.ASPECTO,   \n");
        sb.append("  irr.FUERTE,   \n");
        sb.append("  irr.VOZ,   \n");
        sb.append("  irr.FORMA   \n");
        sb.append("FROM   \n");
        sb.append("   IRR_INFINITIVOS irr \n");
        sb.append("       \n");
        sb.append("WHERE             \n");
        sb.append("   irr.VERBO_ID=?        \n");
        sb.append("   AND irr.PARTIC=?   \n");
        sb.append("ORDER BY          \n");
        sb.append("   irr.SUBPART       \n");
		SELECCION_POR_VERBO_PARTIC = sb.toString();
		
        sb=new StringBuffer();
        sb.append("SELECT \n");
        sb.append("  irr.VERBO_ID,   \n");
        sb.append("  irr.IRR_INFINITIVO_ID,   \n");
        sb.append("  irr.VERBO_ID,   \n");
        sb.append("  irr.PARTIC,   \n");
        sb.append("  irr.SUBPART,   \n");
        sb.append("  irr.ASPECTO,   \n");
        sb.append("  irr.FUERTE,   \n");
        sb.append("  irr.VOZ,   \n");
        sb.append("  irr.FORMA   \n");
        sb.append("FROM   \n");
        sb.append("   IRR_INFINITIVOS irr \n");
        sb.append("WHERE             \n");
        sb.append("   irr.FORMA=?        \n");
        sb.append("ORDER BY          \n");
        sb.append("   irr.VERBO_ID       \n");
		SELECCION_POR_FORMA= sb.toString();
		
		
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		
		public SeleccionAbstracta(DataSource dataSource, String sql){
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			IrrInfinitivoBean bean=new IrrInfinitivoBean();
			bean.setId(rs.getString("IRR_INFINITIVO_ID"));
			bean.setVerboId(rs.getString("VERBO_ID"));
			bean.setPartic(Particularidad.getEnum(rs.getString("PARTIC")));
			bean.setSupPart(rs.getInt("SUBPART"));
			bean.setAspecto(Aspecto.getEnum(rs.getInt("ASPECTO")));
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setForma(rs.getString("FORMA"));
			return bean;
		}
	}

	//selección por verbo y partic
	class SeleccionPorVerboPartic extends SeleccionAbstracta {
		public SeleccionPorVerboPartic(DataSource dataSource) {
			super(dataSource, SELECCION_POR_VERBO_PARTIC);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}
	
	private SeleccionPorVerboPartic seleccionPorVerboPartic;
	

	/* (non-Javadoc)
	 * @see kalos.dao.IrrInfinitivosDAO#seleccionaPorVerbopartic(java.lang.String, java.lang.String)
	 */
	public List<IrrInfinitivoBean> seleccionaPorVerbopartic(String verboId, Particularidad partic){
		List<IrrInfinitivoBean> resultado=seleccionPorVerboPartic.execute(
				new Object[]{
						verboId, 
						Particularidad.getString(partic)
				});
		return resultado;
	}

	
	//selección por verbo y partic
	class SeleccionPorForma extends SeleccionAbstracta {
		public SeleccionPorForma(DataSource dataSource) {
			super(dataSource, SELECCION_POR_FORMA);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}
	}

	private SeleccionPorForma seleccionPorForma;
	
	public List<IrrInfinitivoBean> seleccionaPorForma(String forma){
		List<IrrInfinitivoBean> resultado=seleccionPorForma.execute(
				new Object[]{forma});
		return resultado;
	}
	

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorVerboPartic = new SeleccionPorVerboPartic(getDataSource());
		seleccionPorForma = new SeleccionPorForma(getDataSource());
	}

}
