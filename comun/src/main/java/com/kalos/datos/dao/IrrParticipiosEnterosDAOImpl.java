
package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.kalos.beans.IrrParticipioEntero;
import com.kalos.datos.util.DBUtil;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Voz;

public class IrrParticipiosEnterosDAOImpl extends JdbcDaoSupport implements
        IrrParticipiosEnterosDAO {

    private static String SEL_POR_VERBO_SQL;
    private static String SEL_POR_NOMINATIVO_SQL;
    private static String SEL_POR_GENITIVO;
    private static String INSERCION_SQL;
    private SelPorVerbo selPorVerbo;
    private SelPorNominativo selPorNominativo;
    private SelPorGenitivo selPorGenitivo;

    private void puebla() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT \n");
        sql.append("  IPE.IRR_PARTICIPIO_ENTERO_ID, \n");
        sql.append("  IPE.VERBO_ID, \n");
        sql.append("  IPE.PARTIC, IPE.SUBPART,   \n");
        sql.append("  IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO,   \n");
        sql.append("  IPE.NOMINATIVO, IPE.GENITIVO,   \n");
        sql.append("  IPE.TIPO_SUSTANTIVO    \n");
        sql.append("FROM   \n");
        sql.append("  IRR_PARTICIPIOS_ENTEROS  IPE  \n");
        sql.append("WHERE               \n");
        sql.append("  IPE.VERBO_ID=?           \n");
        sql.append("ORDER BY               \n");
        sql.append("  IPE.PARTIC, IPE.SUBPART, IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO    \n");
        SEL_POR_VERBO_SQL = sql.toString();

        sql = new StringBuffer();
        sql.append("INSERT INTO IRR_PARTICIPIOS_ENTEROS ( \n");
        sql.append("  IRR_PARTICIPIO_ENTERO_ID, \n");
        sql.append("  VOZ, \n");
        sql.append("  ASPECTO, \n");
        sql.append("  FUERTE, \n");
        sql.append("  PARTIC, \n");
        sql.append("  SUBPART, \n");
        sql.append("  NOMINATIVO, \n");
        sql.append("  GENITIVO, \n");
        sql.append("  GENERO, \n");
        sql.append("  TIPO_SUSTANTIVO, \n");
        sql.append("  IGNORAR, \n");
        sql.append("  VERBO_ID  \n");
        sql.append(") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");
        INSERCION_SQL = sql.toString();
        
        sql = new StringBuffer();
        sql.append("SELECT \n");
        sql.append("  IRR_PARTICIPIO_ENTERO_ID, \n");
        sql.append("  IPE.VERBO_ID, \n");
        sql.append("  IPE.PARTIC, IPE.SUBPART,   \n");
        sql.append("  IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO,   \n");
        sql.append("  IPE.NOMINATIVO, IPE.GENITIVO,   \n");
        sql.append("  IPE.TIPO_SUSTANTIVO    \n");
        sql.append("FROM   \n");
        sql.append("  IRR_PARTICIPIOS_ENTEROS  IPE  \n");
        sql.append("WHERE               \n");
        sql.append("  IPE.VERBO_ID=?           \n");
        sql.append("ORDER BY               \n");
        sql.append("  IPE.PARTIC, IPE.SUBPART, IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO    \n");
        SEL_POR_VERBO_SQL = sql.toString();

        sql = new StringBuffer();
        sql.append("SELECT \n");
        sql.append("  IPE.IRR_PARTICIPIO_ENTERO_ID, \n");
        sql.append("  IPE.VERBO_ID, \n");
        sql.append("  IPE.PARTIC, IPE.SUBPART,   \n");
        sql.append("  IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO,   \n");
        sql.append("  IPE.NOMINATIVO, IPE.GENITIVO,   \n");
        sql.append("  IPE.TIPO_SUSTANTIVO    \n");
        sql.append("FROM   \n");
        sql.append("  IRR_PARTICIPIOS_ENTEROS  IPE  \n");
        sql.append("WHERE               \n");
        sql.append("  IPE.NOMINATIVO=?           \n");
        SEL_POR_NOMINATIVO_SQL = sql.toString();
        sql = new StringBuffer();
        sql.append("SELECT \n");
        sql.append("  IPE.IRR_PARTICIPIO_ENTERO_ID, \n");
        sql.append("  IPE.VERBO_ID, \n");
        sql.append("  IPE.PARTIC, IPE.SUBPART,   \n");
        sql.append("  IPE.VOZ, IPE.ASPECTO, IPE.FUERTE, IPE.GENERO,   \n");
        sql.append("  IPE.NOMINATIVO, IPE.GENITIVO,   \n");
        sql.append("  IPE.TIPO_SUSTANTIVO    \n");
        sql.append("FROM   \n");
        sql.append("  IRR_PARTICIPIOS_ENTEROS  IPE  \n");
        sql.append("WHERE               \n");
        sql.append("  IPE.GENITIVO=?           \n");
        SEL_POR_GENITIVO = sql.toString();
    }

    class Insercion extends SqlUpdate {

        public Insercion(DataSource datasource) {
            super(datasource, INSERCION_SQL);
            declareParameter(new SqlParameter(Types.CHAR)); // IRR_PARTICIPIO_ENTERO_ID
            declareParameter(new SqlParameter(Types.INTEGER)); // VOZ
            declareParameter(new SqlParameter(Types.INTEGER)); // ASPECTO
            declareParameter(new SqlParameter(Types.INTEGER)); // FUERTE
            declareParameter(new SqlParameter(Types.CHAR)); // PARTIC
            declareParameter(new SqlParameter(Types.INTEGER)); // SUPBART
            declareParameter(new SqlParameter(Types.VARCHAR)); // NOMINATIVO
            declareParameter(new SqlParameter(Types.VARCHAR)); // GENITIVO
            declareParameter(new SqlParameter(Types.CHAR)); // GENERO
            declareParameter(new SqlParameter(Types.INTEGER)); // TIPO_SUSTANTIVO
            declareParameter(new SqlParameter(Types.INTEGER)); // IGNORAR
            declareParameter(new SqlParameter(Types.CHAR)); // VERBO_ID
        }
    }

    private Insercion insercion;

    public void inserta(IrrParticipioEntero bean) {
        String id = DBUtil.getHashableId();
        insercion.update(new Object[] {
                id,
                bean.getVoz().valorEntero(),
                bean.getAspecto().valorEntero(),
                FuerteDebil.getInt(bean.getFuerte()),
                Particularidad.getString(bean.getPartic()),
                bean.getSubPart(),
                bean.getNominativo(),
                bean.getGenitivo(),
                bean.getGenero().valorLetra(),
                bean.getTipoSustantivo(),
                0,
                bean.getVerboId()
        });

    }

    public List<IrrParticipioEntero> seleccionaPorVerbo(String paramString) {
        List localList = this.selPorVerbo.execute(new Object[] { paramString });
        return localList;
    }

    public List<IrrParticipioEntero> seleccionaPorNominativo(String paramString) {
        List localList = this.selPorNominativo
                .execute(new Object[] { paramString });
        return localList;
    }

    public List<IrrParticipioEntero> seleccionaPorGenitivo(String paramString) {
        List localList = this.selPorGenitivo
                .execute(new Object[] { paramString });
        return localList;
    }

    public void initDao() throws Exception {
        super.initDao();
        puebla();
        this.selPorVerbo = new SelPorVerbo(getDataSource());
        this.selPorNominativo = new SelPorNominativo(getDataSource());
        this.selPorGenitivo = new SelPorGenitivo(getDataSource());
        insercion = new Insercion(getDataSource());
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

        protected Object mapRow(ResultSet paramResultSet, int paramInt)
                throws SQLException {
            IrrParticipioEntero localX = new IrrParticipioEntero();
            localX.setId(paramResultSet.getString("IRR_PARTICIPIO_ENTERO_ID"));
            localX.setVerboId(paramResultSet.getString("VERBO_ID"));
            localX.setPartic(Particularidad.getEnum(paramResultSet
                    .getString("PARTIC")));
            localX.setSubPart(paramResultSet.getInt("SUBPART"));
            localX.setAspecto(Aspecto.getEnum(paramResultSet.getInt("ASPECTO")));
            localX.setVoz(Voz.getEnum(paramResultSet.getInt("VOZ")));
            localX.setFuerte(FuerteDebil.getEnum(paramResultSet
                    .getInt("FUERTE")));
            localX.setGenero(Genero.getEnum(paramResultSet.getString("GENERO")));
            localX.setNominativo(paramResultSet.getString("NOMINATIVO"));
            localX.setGenitivo(paramResultSet.getString("GENITIVO"));
            localX.setTipoSustantivo(paramResultSet.getInt("TIPO_SUSTANTIVO"));
            return localX;
        }
    }
}
