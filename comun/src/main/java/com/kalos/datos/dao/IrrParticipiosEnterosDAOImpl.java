package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.IrrParticipioEntero;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

public class IrrParticipiosEnterosDAOImpl extends JdbcDaoSupport implements IrrParticipiosEnterosDAO {
    private static String SEL_POR_VERBO_SQL;
    private static String SEL_POR_NOMINATIVO_SQL;
    private static String SEL_POR_GENITIVO;
    private SelPorVerbo selPorVerbo;
    private SelPorNominativo selPorNominativo;
    private SelPorGenitivo selPorGenitivo;

    private void puebla() {
	StringBuffer localStringBuffer = new StringBuffer();
	localStringBuffer.append("SELECT \n");
	localStringBuffer.append("  IPE.IRR_PARTICIPIO_ENTERO_ID, \n");
	localStringBuffer.append("  IPE.VERBO_ID, \n");
	localStringBuffer.append("  IPE.PARTIC, IPE.SUBPART,   \n");
	localStringBuffer.append("  IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO,   \n");
	localStringBuffer.append("  IPE.NOMINATIVO, IPE.GENITIVO,   \n");
	localStringBuffer.append("  IPE.TIPO_SUSTANTIVO    \n");
	localStringBuffer.append("FROM   \n");
	localStringBuffer.append("  IRR_PARTICIPIOS_ENTEROS  IPE  \n");
	localStringBuffer.append("WHERE               \n");
	localStringBuffer.append("  IPE.VERBO_ID=?           \n");
	localStringBuffer.append("ORDER BY               \n");
	localStringBuffer.append("  IPE.PARTIC, IPE.SUBPART, IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO    \n");
	SEL_POR_VERBO_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer();
	localStringBuffer.append("SELECT \n");
	localStringBuffer.append("  IPE.IRR_PARTICIPIO_ENTERO_ID, \n");
	localStringBuffer.append("  IPE.VERBO_ID, \n");
	localStringBuffer.append("  IPE.PARTIC, IPE.SUBPART,   \n");
	localStringBuffer.append("  IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO,   \n");
	localStringBuffer.append("  IPE.NOMINATIVO, IPE.GENITIVO,   \n");
	localStringBuffer.append("  IPE.TIPO_SUSTANTIVO    \n");
	localStringBuffer.append("FROM   \n");
	localStringBuffer.append("  IRR_PARTICIPIOS_ENTEROS  IPE  \n");
	localStringBuffer.append("WHERE               \n");
	localStringBuffer.append("  IPE.NOMINATIVO=?           \n");
	SEL_POR_NOMINATIVO_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer();
	localStringBuffer.append("SELECT \n");
	localStringBuffer.append("  IPE.IRR_PARTICIPIO_ENTERO_ID, \n");
	localStringBuffer.append("  IPE.VERBO_ID, \n");
	localStringBuffer.append("  IPE.PARTIC, IPE.SUBPART,   \n");
	localStringBuffer.append("  IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO,   \n");
	localStringBuffer.append("  IPE.NOMINATIVO, IPE.GENITIVO,   \n");
	localStringBuffer.append("  IPE.TIPO_SUSTANTIVO    \n");
	localStringBuffer.append("FROM   \n");
	localStringBuffer.append("  IRR_PARTICIPIOS_ENTEROS  IPE  \n");
	localStringBuffer.append("WHERE               \n");
	localStringBuffer.append("  IPE.GENITIVO=?           \n");
	SEL_POR_GENITIVO = localStringBuffer.toString();
    }

    public List<IrrParticipioEntero> seleccionaPorVerbo(String paramString) {
	List localList = this.selPorVerbo.execute(new Object[] { paramString });
	return localList;
    }

    public List<IrrParticipioEntero> seleccionaPorNominativo(String paramString) {
	List localList = this.selPorNominativo.execute(new Object[] { paramString });
	return localList;
    }

    public List<IrrParticipioEntero> seleccionaPorGenitivo(String paramString) {
	List localList = this.selPorGenitivo.execute(new Object[] { paramString });
	return localList;
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	this.selPorVerbo = new SelPorVerbo(getDataSource());
	this.selPorNominativo = new SelPorNominativo(getDataSource());
	this.selPorGenitivo = new SelPorGenitivo(getDataSource());
    }

    class SelPorGenitivo extends SeleccionAbstracta {
	public SelPorGenitivo(DataSource paramDataSource) {
	    super(paramDataSource, SEL_POR_GENITIVO);
	    declareParameter(new SqlParameter(12));
	}
    }

    class SelPorNominativo extends SeleccionAbstracta {
	public SelPorNominativo(DataSource paramDataSource) {
	    super(paramDataSource, SEL_POR_NOMINATIVO_SQL);
	    declareParameter(new SqlParameter(12));
	}
    }

    class SelPorVerbo extends SeleccionAbstracta {
	public SelPorVerbo(DataSource paramDataSource) {
	    super(paramDataSource, SEL_POR_VERBO_SQL);
	    declareParameter(new SqlParameter(12));
	}
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {
	public SeleccionAbstracta(DataSource paramDataSource, String paramString) {
	    super(paramDataSource, paramString);
	}

	protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
	    IrrParticipioEntero localX = new IrrParticipioEntero();
	    localX.setId(paramResultSet.getString("IRR_PARTICIPIO_ENTERO_ID"));
	    localX.setVerboId(paramResultSet.getString("VERBO_ID"));
	    localX.setPartic(Particularidad.getEnum(paramResultSet.getString("PARTIC")));
	    localX.setSubPart(paramResultSet.getInt("SUBPART"));
	    localX.setAspecto(Aspecto.getEnum(paramResultSet.getInt("ASPECTO")));
	    localX.setVoz(Voz.getEnum(paramResultSet.getInt("VOZ")));
	    localX.setFuerte(FuerteDebil.getEnum(paramResultSet.getInt("FUERTE")));
	    localX.setGenero(Genero.getEnum(paramResultSet.getString("GENERO")));
	    localX.setNominativo(paramResultSet.getString("NOMINATIVO"));
	    localX.setGenitivo(paramResultSet.getString("GENITIVO"));
	    localX.setTipoSustantivo(paramResultSet.getInt("TIPO_SUSTANTIVO"));
	    return localX;
	}
    }
}
