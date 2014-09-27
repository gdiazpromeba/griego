package kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kalos.beans.EncParticulaBean;
import kalos.beans.ParticulaBean;
import kalos.datos.dao.comunes.Borrado;
import kalos.enumeraciones.TipoPalabra;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

public class EncParticulasDAOImpl extends JdbcDaoSupport implements EncParticulasDAO {
    private static String SELECT_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private Seleccion seleccion;
    private Modificacion modificacion;
    private Insercion insercion;
    private Borrado borrado;

    private void puebla() {
	StringBuffer localStringBuffer = new StringBuffer(200);
	localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("SELECT   \n");
	localStringBuffer.append("  PARTICULA_ENCABEZADO_ID,   \n");
	localStringBuffer.append("  TIPO_PALABRA,   \n");
	localStringBuffer.append("  FORMA   \n");
	localStringBuffer.append("FROM   \n");
	localStringBuffer.append("  PARTICULA_ENCABEZADOS   \n");
	SELECT_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("INSERT INTO PARTICULA_ENCABEZADOS(  \n");
	localStringBuffer.append("  PARTICULA_ENCABEZADO_ID,   \n");
	localStringBuffer.append("  TIPO_PALABRA,   \n");
	localStringBuffer.append("  FORMA   \n");
	localStringBuffer.append("  ?,?,?   \n");
	localStringBuffer.append(" )   \n");
	INSERT_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("UPDATE PARTICULA_ENCABEZADOS SET  \n");
	localStringBuffer.append("  PARTICULA_ENCABEZADO_ID=?,   \n");
	localStringBuffer.append("  TIPO_PALABRA=?,   \n");
	localStringBuffer.append("  FORMA=?   \n");
	localStringBuffer.append("WHERE     \n");
	localStringBuffer.append("  PARTICULA_ENCABEZADO_ID=?   \n");
	UPDATE_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("DELETE FROM PARTICULA_ENCABEZADOS  \n");
	localStringBuffer.append("WHERE     \n");
	localStringBuffer.append("  PARTICULA_ENCABEZADO_ID=?   \n");
	DELETE_SQL = localStringBuffer.toString();
    }

    /* (non-Javadoc)
     * @see kalos.datos.dao.EncParticulasDAO#seleccionaEncParticulasTodos()
     */
    @Override
    public List<EncParticulaBean> seleccionaEncParticulasTodos() {
	return seleccion.execute();
    }

    /* (non-Javadoc)
     * @see kalos.datos.dao.EncParticulasDAO#inserta(kalos.beans.EncParticulaBean)
     */
    @Override
    public void inserta(EncParticulaBean paramf) {
	String str = kalos.datos.util.DBUtil.getHashableId();
	insercion.update(new Object[] { str, TipoPalabra.getString(paramf.getTipoPalabra()), paramf.getForma() });
	paramf.setId(str);
    }

    /* (non-Javadoc)
     * @see kalos.datos.dao.EncParticulasDAO#modifica(kalos.beans.ParticulaBean)
     */
    @Override
    public void modifica(ParticulaBean paraml) {
	modificacion.update(new Object[] { TipoPalabra.getString(paraml.getParticulaTipo()), paraml.getForma(), paraml.getId() });
    }

    /* (non-Javadoc)
     * @see kalos.datos.dao.EncParticulasDAO#borra(java.lang.String)
     */
    @Override
    public void borra(String paramString) {
	this.borrado.update(new Object[] { paramString });
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	seleccion = new Seleccion(getDataSource());
	modificacion = new Modificacion(getDataSource());
	insercion = new Insercion(getDataSource());
	borrado = new Borrado(getDataSource(), DELETE_SQL);
    }

    class Insercion extends SqlUpdate {
	public Insercion(DataSource paramDataSource) {
	    super(paramDataSource, INSERT_SQL);
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(1));
	}
    }

    class Modificacion extends SqlUpdate {
	public Modificacion(DataSource paramDataSource) {
	    super(paramDataSource, UPDATE_SQL);
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(12));
	}
    }

    class Seleccion extends SeleccionAbstracta {
	public Seleccion(DataSource paramDataSource) {
	    super(paramDataSource, SELECT_SQL);
	}
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {
	public SeleccionAbstracta(DataSource paramDataSource, String paramString) {
	    super(paramDataSource, paramString);
	}

	protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
	    EncParticulaBean localf = new EncParticulaBean();
	    localf.setId(paramResultSet.getString("PARTICULA_ENCABEZADO_ID"));
	    localf.setTipoPalabra(TipoPalabra.getEnum(paramResultSet.getString("TIPO_PALABRA")));
	    localf.setForma(paramResultSet.getString("FORMA"));
	    return localf;
	}
    }
}