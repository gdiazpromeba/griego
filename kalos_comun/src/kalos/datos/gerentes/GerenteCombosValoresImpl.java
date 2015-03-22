package kalos.datos.gerentes;

import java.util.List;





public class GerenteCombosValoresImpl implements GerenteCombosValores
{
  private kalos.datos.dao.CombosDAO combosDao;
  
  /* (non-Javadoc)
 * @see kalos.datos.gerentes.GerenteCombosValores#getPorClave(java.lang.String)
 */
@Override
public List<kalos.beans.ValorCombo> getPorClave(String paramString)
  {
    return this.combosDao.getPorClave(paramString);
  }
  
  /* (non-Javadoc)
 * @see kalos.datos.gerentes.GerenteCombosValores#setCombosDAO(kalos.datos.dao.CombosDAO)
 */
@Override
public void setCombosDAO(kalos.datos.dao.CombosDAO paramdA)
  {
    this.combosDao = paramdA;
  }
}
