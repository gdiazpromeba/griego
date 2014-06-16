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

import java.util.List;

import kalos.beans.EncParticulaBean;
import kalos.beans.ParticulaBean;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.0 $
 */
public interface EncParticulasDAO {

    public abstract List<EncParticulaBean> seleccionaEncParticulasTodos();

    public abstract void inserta(EncParticulaBean ea);

    public abstract void modifica(ParticulaBean ea);

    public abstract void borra(String id);

}