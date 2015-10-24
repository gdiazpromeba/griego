package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.IrrParticipioSimpleBean;
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

public class IrrParticipiosSimplesDAOImpl extends JdbcDaoSupport implements IrrParticipiosSimplesDAO {
    private static String SEL_POR_VERBO_SQL;
    private static String SEL_POR_FORMA_SQL;
    private SelPorVerbo selPorVerbo;
    private SelPorForma selPorForma;

    private void puebla() {
	StringBuffer localStringBuffer = new StringBuffer();
	localStringBuffer.append("select \n");
	localStringBuffer.append("  VERBO_ID, PARTIC, SUBPART, ASPECTO, FUERTE, VOZ, GENERO, CASO, NUMERO, FORMA, \n");
	localStringBuffer.append("  IRR_PARTICIPIO_SIMPLE_ID \n");
	localStringBuffer.append("from IRR_PARTICIPIOS_SIMPLES  \n");
	localStringBuffer.append("where  \n");
	localStringBuffer.append("  VERBO_ID=? \n");
	localStringBuffer.append("order by \n");
	localStringBuffer.append("  PARTIC, \n");
	localStringBuffer.append("  SUBPART, \n");
	localStringBuffer.append("  ASPECTO, \n");
	localStringBuffer.append("  FUERTE, \n");
	localStringBuffer.append("  VOZ \n");
	SEL_POR_VERBO_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer();
	localStringBuffer.append("select \n");
	localStringBuffer.append("  VERBO_ID, PARTIC, SUBPART, ASPECTO, FUERTE, VOZ, GENERO, CASO, NUMERO, FORMA, \n");
	localStringBuffer.append("  IRR_PARTICIPIO_SIMPLE_ID \n");
	localStringBuffer.append("from IRR_PARTICIPIOS_SIMPLES  \n");
	localStringBuffer.append("where  \n");
	localStringBuffer.append("  FORMA=? \n");
	localStringBuffer.append("order by \n");
	localStringBuffer.append("  PARTIC, \n");
	localStringBuffer.append("  SUBPART, \n");
	localStringBuffer.append("  ASPECTO, \n");
	localStringBuffer.append("  FUERTE, \n");
	localStringBuffer.append("  VOZ \n");
	SEL_POR_FORMA_SQL = localStringBuffer.toString();
    }

    public List<IrrParticipioSimpleBean> seleccionaPorVerbo(String paramString) {
	List localList = this.selPorVerbo.execute(new Object[] { paramString });
	return localList;
    }

    public List<IrrParticipioSimpleBean> seleccionaPorForma(String paramString) {
	List localList = this.selPorForma.execute(new Object[] { paramString });
	return localList;
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	this.selPorVerbo = new SelPorVerbo(getDataSource());
	this.selPorForma = new SelPorForma(getDataSource());
    }

    class SelPorForma extends IrrParticipiosSimplesDAOImpl.SeleccionAbsracta {
	public SelPorForma(DataSource paramDataSource) {
	    super(paramDataSource, SEL_POR_FORMA_SQL);
	    declareParameter(new SqlParameter(12));
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
