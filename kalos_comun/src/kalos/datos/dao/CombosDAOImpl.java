package kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import kalos.beans.ValorCombo;
import kalos.recursos.Configuracion;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

public class CombosDAOImpl extends JdbcDaoSupport implements CombosDAO

{
    private static String SEL_SQL;
    private Seleccion seleccion;

    private void puebla() {
	StringBuffer localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("SELECT   \n");
	localStringBuffer.append("  CMB.CLAVE_COMBO,   \n");
	localStringBuffer.append("  CMB.TIPO_CLAVE,   \n");
	localStringBuffer.append("  CMB.GRIEGO,   \n");
	localStringBuffer.append("  COV.CLAVE_VALOR,   \n");
	localStringBuffer.append("  COT.TEXTO_ID,   \n");
	localStringBuffer.append("  COT.VALOR   \n");
	localStringBuffer.append("FROM        \n");
	localStringBuffer.append("  COMBOS CMB   \n");
	localStringBuffer.append("    INNER JOIN COMBOS_VALORES COV       \n");
	localStringBuffer.append("      ON CMB.CLAVE_COMBO=COV.CLAVE_COMBO       \n");
	localStringBuffer.append("    INNER JOIN COMBOS_VALORES_TEXTO COT                 \n");
	localStringBuffer.append("      ON COV.COMBOS_VALORES_ID=COT.REFERENTE_ID       \n");
	localStringBuffer.append("WHERE  \n");
	localStringBuffer.append("  COV.CLAVE_COMBO=?    \n");
	localStringBuffer.append("  AND COT.IDIOMA='" + Configuracion.getIdiomaSignificados() + "'\n");
	localStringBuffer.append("ORDER BY  \n");
	localStringBuffer.append("  COV.ORDEN   \n");
	SEL_SQL = localStringBuffer.toString();
    }


    /* (non-Javadoc)
     * @see kalos.datos.dao.CombosDAO#getPorClave(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<ValorCombo> getPorClave(String paramString) {
	return this.seleccion.execute(new Object[] { paramString });
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	this.seleccion = new Seleccion(getDataSource());
    }

    class Seleccion extends SeleccionAbstracta {
	public Seleccion(DataSource paramDataSource) {
	    super(paramDataSource, SEL_SQL);
	    declareParameter(new SqlParameter(1));
	}
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {
	public SeleccionAbstracta(DataSource paramDataSource, String paramString) {
	    super(paramDataSource, paramString);
	}

	protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
	    ValorCombo localI = new ValorCombo();
	    localI.setId(paramResultSet.getString("TEXTO_ID"));
	    localI.setClaveCombo(paramResultSet.getString("CLAVE_COMBO"));
	    localI.setTipoClave(paramResultSet.getInt("TIPO_CLAVE"));
	    localI.setGriego(paramResultSet.getInt("GRIEGO") != 0);
	    localI.setClaveValor(paramResultSet.getString("CLAVE_VALOR"));
	    localI.setTexto(paramResultSet.getString("VALOR"));
	    return localI;
	}
    }
}
