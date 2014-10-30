package kalos.flexion;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import kalos.B.q;
import kalos.C.A;
import kalos.E.E.jA;
import kalos.G.I;
import kalos.K.Q;
import kalos.K.Y;

public class ProveedorDMInterjecciones
{
  private jA A;
  
  public DefaultTableModel getTMModeloInterjecciones()
  {
    DefaultTableModel localDefaultTableModel = new DefaultTableModel(new String[] { "FORMA", "PARTICULARIDAD", "SIGNIFICADO" }, 0);
    List localList = this.A.seleccionaTodos();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Y localY = (Y)localIterator.next();
      Q localQ = (Q)localY.getSignificados().get(q.getEnum(A.getIdiomaSignificados()));
      String str = localQ.getValor();
      localDefaultTableModel.addRow(new Object[] { I.strBetaACompleto(localY.getInterjeccion()), localY.getPartic(), str });
    }
    return localDefaultTableModel;
  }
  
  public void setGerenteInterjecciones(jA paramjA)
  {
    this.A = paramjA;
  }
}
