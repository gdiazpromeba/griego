package kalos.datos.dao.comunes;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class ModificaCodigosTodos extends SqlUpdate {
    public ModificaCodigosTodos(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter(Types.CHAR));
    }
}
