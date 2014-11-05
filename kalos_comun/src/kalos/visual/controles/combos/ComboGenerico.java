package kalos.visual.controles.combos;

import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import kalos.beans.ValorCombo;

public class ComboGenerico
  extends JComboBox
{
  public ComboGenerico(List<ValorCombo> paramList)
  {
    DefaultComboBoxModel<ValorCombo> localDefaultComboBoxModel = new DefaultComboBoxModel();
    Iterator<ValorCombo> localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ValorCombo localI = localIterator.next();
      localDefaultComboBoxModel.addElement(localI);
    }
    setModel(localDefaultComboBoxModel);
    setRenderer(new RendererComboGenerico());
  }
  
  public void setClave(Object paramObject)
  {
    ComboBoxModel<ValorCombo> localComboBoxModel = getModel();
    Object localObject;
    for (int i = 0; i < localComboBoxModel.getSize(); i++)
    {
      ValorCombo localI = localComboBoxModel.getElementAt(i);
      localObject = localI.getClaveValor();
      if (localI.getTipoClave() == 2) {
        localObject = Integer.valueOf(Integer.parseInt((String)localObject));
      }
      if (localObject.equals(paramObject))
      {
        setSelectedIndex(i);
        return;
      }
    }
    StringBuffer localStringBuffer = new StringBuffer(300);
    localStringBuffer.append("se quiso forzar el valor " + paramObject + " que no está presente en este combo \n");
    localStringBuffer.append("los valores presentes son:  \n");
    for (int j = 0; j < localComboBoxModel.getSize(); j++)
    {
      ValorCombo vc = (ValorCombo)localComboBoxModel.getElementAt(j);
      localStringBuffer.append(vc.getClaveValor() + "-");
    }
    throw new RuntimeException(localStringBuffer.toString());
  }
  
  public Object getClaveValorSeleccionada()
  {
    ValorCombo localI = (ValorCombo)getSelectedItem();
    if (localI == null) {
      return null;
    }
    if (localI.getTipoClave() == 2) {
      return Integer.valueOf(Integer.parseInt(localI.getClaveValor()));
    }
    return localI.getClaveValor();
  }
}
