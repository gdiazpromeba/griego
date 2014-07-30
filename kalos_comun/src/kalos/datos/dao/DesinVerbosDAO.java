package kalos.datos.dao;

import java.util.List;

import kalos.beans.DesinVerbo;

public interface DesinVerbosDAO {

    public abstract List<DesinVerbo> seleccionaRestringidas();

    public abstract List<DesinVerbo> seleccionaTodas();

}