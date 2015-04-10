package com.kalos.flexion;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.AspectosSegundos;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TiempoSegundos;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.CadenasEnum;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;

import org.apache.log4j.Logger;

public class UtilidadesTM {
    private Logger logger = Logger.getLogger(getClass().getName());

    public String[] getNombresColumna(DefaultTableModel paramDefaultTableModel) {
	ArrayList localArrayList = new ArrayList();
	int i = paramDefaultTableModel.getColumnCount();
	for (int j = 0; j < i; j++) {
	    localArrayList.add(paramDefaultTableModel.getColumnName(j));
	}
	return (String[]) localArrayList.toArray(new String[0]);
    }

    public void muestraTableModelDefault(DefaultTableModel paramDefaultTableModel, String paramString) {
	DefaultTableModel localDefaultTableModel = clona(paramDefaultTableModel);
	JFrame localJFrame = new JFrame(paramString);
	localJFrame.setSize(300, 200);
	JTable localJTable = new JTable(localDefaultTableModel);
	for (int i = 0; i < localJTable.getColumnCount(); i++) {
	    localJTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer());
	}
	for (int i = 0; i < localJTable.getColumnCount(); i++) {
	    localJTable.getColumnModel().getColumn(i).setCellEditor(null);
	}
	localJFrame.getContentPane().add(new JScrollPane(localJTable));
	A(localJFrame);
	localJFrame.setVisible(true);
    }

    private void A(JFrame paramJFrame) {
	paramJFrame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent paramAnonymousWindowEvent) {
	    }
	});
    }

    public void ICeldasAString(DefaultTableModel paramDefaultTableModel) {
	for (int i = 0; i < paramDefaultTableModel.getRowCount(); i++) {
	    for (int j = 0; j < paramDefaultTableModel.getRowCount(); j++) {
		Object localObject = paramDefaultTableModel.getValueAt(i, j);
		if ((localObject instanceof CeldaReporte)) {
		    paramDefaultTableModel.setValueAt(((CeldaReporte) localObject).getContenido(), i, j);
		}
	    }
	}
    }

    public Genero[] extraeArrayGeneros(DefaultTableModel paramDefaultTableModel) {
	int i = paramDefaultTableModel.findColumn("GENERO");
	TreeSet localTreeSet = new TreeSet();
	for (int j = 0; j < paramDefaultTableModel.getRowCount(); j++) {
	    Genero localObject = (Genero) paramDefaultTableModel.getValueAt(j, i);
	    localTreeSet.add(localObject);
	}
	ArrayList localArrayList = new ArrayList(localTreeSet);
	Collections.sort(localArrayList);
	Genero[] localObject = (Genero[]) localArrayList.toArray(new Genero[1]);
	return localObject;
    }

    public String[] extraeEncabezadosGeneros(Genero[] paramArrayOfA) {
	String[] arrayOfString = new String[paramArrayOfA.length];
	for (int i = 0; i < paramArrayOfA.length; i++) {
	    String str = CadenasEnum.getCadena(paramArrayOfA[i]);
	    arrayOfString[i] = str;
	}
	return arrayOfString;
    }

    public void reemplazaNombresColumna(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	if (paramDefaultTableModel.getColumnCount() != paramArrayOfString.length) {
	    throw new RuntimeException(
		    "el número de columnas a reemplazar por recursos no coincide con el número de recursos");
	}
	Vector localVector = new Vector(paramArrayOfString.length);
	for (String str1 : paramArrayOfString) {
	    String str2 = Recursos.getCadena(str1);
	    localVector.add(str2);
	}
	paramDefaultTableModel.setColumnIdentifiers(localVector);
    }

    public void reemplazaNombresColumna(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString1,
	    String[] paramArrayOfString2) {
	ArrayList localArrayList = new ArrayList(Arrays.asList(paramArrayOfString1));
	Vector localVector = new Vector();
	for (int i = 0; i < paramDefaultTableModel.getColumnCount(); i++) {
	    localVector.add(paramDefaultTableModel.getColumnName(i));
	}
	for (int i = 0; i < localVector.size(); i++) {
	    int j = localArrayList.indexOf(localVector.get(i));
	    if (j > -1) {
		localVector.set(i, Recursos.getCadena(paramArrayOfString2[j]));
	    }
	}
	paramDefaultTableModel.setColumnIdentifiers(localVector);
    }

    public void eliminaClavesFlexion(DefaultTableModel paramDefaultTableModel, List<ClaveFlexion> paramList, String paramString) {
	int i = paramDefaultTableModel.findColumn(paramString);
	int j = 0;
	while (j < paramDefaultTableModel.getDataVector().size()) {
	    Vector localVector = (Vector) paramDefaultTableModel.getDataVector().get(j);
	    Object localObject = localVector.get(i);
	    if ((localObject instanceof ClaveFlexion)) {
		ClaveFlexion localA = (ClaveFlexion) localObject;
		if (paramList.contains(localA)) {
		    paramDefaultTableModel.removeRow(j);
		} else {
		    j++;
		}
	    } else {
		j++;
	    }
	}
    }

    public void clavesFlexionACadena(DefaultTableModel paramDefaultTableModel, String paramString) {
	int i = paramDefaultTableModel.getRowCount();
	int j = paramDefaultTableModel.findColumn(paramString);
	for (int k = 0; k < i; k++) {
	    ClaveFlexion localA = (ClaveFlexion) paramDefaultTableModel.getValueAt(k, j);
	    String str = localA.getForma();
	    paramDefaultTableModel.setValueAt(str, k, j);
	}
    }

    public void poneNombreColumna(DefaultTableModel paramDefaultTableModel, int paramInt, String paramString) {
	Vector localVector = new Vector(Arrays.asList(getNombresColumna(paramDefaultTableModel)));
	localVector.set(paramInt, paramString);
	paramDefaultTableModel.setColumnIdentifiers(localVector);
    }

    public List<String> nombresColumna(DefaultTableModel paramDefaultTableModel) {
	ArrayList localArrayList = new ArrayList();
	for (int i = 0; i < paramDefaultTableModel.getColumnCount(); i++) {
	    String str = paramDefaultTableModel.getColumnName(i);
	    localArrayList.add(str);
	}
	return localArrayList;
    }

    public void columnasAInterfaz(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    int j = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	    B(paramDefaultTableModel, j);
	}
    }

    private void B(DefaultTableModel paramDefaultTableModel, int paramInt) {
	boolean bool = Configuracion.getMuestraMakrones();
	int i = paramDefaultTableModel.getRowCount();
	for (int j = 0; j < i; j++) {
	    Object localObject1 = paramDefaultTableModel.getValueAt(j, paramInt);
	    Object localObject2;
	    String str1;
	    if ((localObject1 instanceof String)) {
		localObject2 = (String) paramDefaultTableModel.getValueAt(j, paramInt);
		if (localObject2 != null) {
		    str1 = null;
		    if (bool) {
			str1 = OpPalabras.strCompletoAUnicode((String) localObject2);
		    } else {
			str1 = OpPalabras.strCompletoAUnicodeSinMakrones((String) localObject2);
		    }
		    paramDefaultTableModel.setValueAt(str1, j, paramInt);
		}
	    } else {
		String str2;
		if ((localObject1 instanceof CeldaReporte)) {
		    localObject2 = (CeldaReporte) paramDefaultTableModel.getValueAt(j, paramInt);
		    str1 = ((CeldaReporte) localObject2).getContenido();
		    if (str1 != null) {
			str2 = null;
			if (bool) {
			    str2 = OpPalabras.strCompletoAUnicode(str1);
			} else {
			    str2 = OpPalabras.strCompletoAUnicodeSinMakrones(str1);
			}
			((CeldaReporte) localObject2).setContenido(str2);
		    }
		} else if ((localObject1 instanceof ClaveFlexion)) {
		    localObject2 = (ClaveFlexion) paramDefaultTableModel.getValueAt(j, paramInt);
		    str1 = ((ClaveFlexion) localObject2).getForma();
		    if (str1 != null) {
			str2 = null;
			if (bool) {
			    str2 = OpPalabras.strCompletoAUnicode(str1);
			} else {
			    str2 = OpPalabras.strCompletoAUnicodeSinMakrones(str1);
			}
			((ClaveFlexion) localObject2).setForma(str2);
		    }
		}
	    }
	}
    }

    public void columnasAInterfazTolerante(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    int j = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	    if (j != -1) {
		B(paramDefaultTableModel, j);
	    }
	}
    }

    public void columnasABeta(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	int i = paramDefaultTableModel.getRowCount();
	for (int j = 0; j < paramArrayOfString.length; j++) {
	    for (int k = 0; k < i; k++) {
		String str = (String) paramDefaultTableModel.getValueAt(k, j);
		paramDefaultTableModel.setValueAt(OpPalabras.strCompletoABeta(str), k, j);
	    }
	}
    }

    public void dejaSelect(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString,
	    Object[] paramArrayOfObject) {
	int i = 0;
	int[] arrayOfInt = A(paramDefaultTableModel, paramArrayOfString);
	while (i < paramDefaultTableModel.getRowCount()) {
	    int j = 1;
	    for (int k = 0; k < arrayOfInt.length; k++) {
		Object localObject = paramDefaultTableModel.getValueAt(i, arrayOfInt[k]);
		if (!localObject.equals(paramArrayOfObject[k])) {
		    j = 0;
		    break;
		}
	    }
	    if (j == 0) {
		paramDefaultTableModel.removeRow(i);
	    } else {
		i++;
	    }
	}
    }

    private int[] A(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	int[] arrayOfInt = new int[paramArrayOfString.length];
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    arrayOfInt[i] = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	}
	return arrayOfInt;
    }

    public void borraColumna(DefaultTableModel paramDefaultTableModel, int paramInt) {
	if (paramInt == -1) {
	    StringBuffer localObject = new StringBuffer();
	    ((StringBuffer) localObject).append("error en borraColumna \n La columna índice " + paramInt);
	    ((StringBuffer) localObject).append("no existe en este TableModel. \n");
	    ((StringBuffer) localObject).append("Las columnas son: \n");
	    for (int i = 0; i < paramDefaultTableModel.getColumnCount(); i++) {
		((StringBuffer) localObject).append(paramDefaultTableModel.getColumnName(i) + "-");
	    }
	    throw new RuntimeException(((StringBuffer) localObject).toString());
	}
	Object localObject = paramDefaultTableModel.getDataVector();
	LinkedList localLinkedList = new LinkedList(Arrays.asList(getNombresColumna(paramDefaultTableModel)));
	localLinkedList.remove(paramInt);
	for (int j = 0; j < ((Vector) localObject).size(); j++) {
	    Vector localVector = (Vector) ((Vector) localObject).get(j);
	    localVector.removeElementAt(paramInt);
	}
	paramDefaultTableModel.setDataVector((Vector) localObject, new Vector(localLinkedList));
	paramDefaultTableModel.fireTableStructureChanged();
    }

    public void borraColumna(DefaultTableModel paramDefaultTableModel, String paramString) {
	int i = paramDefaultTableModel.findColumn(paramString);
	if (i == -1) {
	    StringBuffer localStringBuffer = new StringBuffer();
	    localStringBuffer.append("error en borraColumna \n La columna " + paramString);
	    localStringBuffer.append("no existe en este TableModel. \n");
	    localStringBuffer.append("Las columnas son: \n");
	    for (int j = 0; j < paramDefaultTableModel.getColumnCount(); j++) {
		localStringBuffer.append(paramDefaultTableModel.getColumnName(j) + "-");
	    }
	    throw new RuntimeException(localStringBuffer.toString());
	}
	borraColumna(paramDefaultTableModel, i);
    }

    public void borraColumnaNula(DefaultTableModel paramDefaultTableModel, String paramString) {
	int i = paramDefaultTableModel.findColumn(paramString);
	if (i == -1) {
	    StringBuffer localStringBuffer = new StringBuffer();
	    localStringBuffer.append("error en borraColumna \n La columna " + paramString);
	    localStringBuffer.append("no existe en este TableModel. \n");
	    localStringBuffer.append("Las columnas son: \n");
	    for (int j = 0; j < paramDefaultTableModel.getColumnCount(); j++) {
		localStringBuffer.append(paramDefaultTableModel.getColumnName(j) + "-");
	    }
	    throw new RuntimeException(localStringBuffer.toString());
	}
	if (todosNulos(paramDefaultTableModel, i)) {
	    borraColumna(paramDefaultTableModel, i);
	}
    }

    public boolean todosNulos(DefaultTableModel paramDefaultTableModel, int paramInt) {
	for (int i = 0; i < paramDefaultTableModel.getRowCount(); i++) {
	    Object localObject = paramDefaultTableModel.getValueAt(i, paramInt);
	    if (localObject != null) {
		return false;
	    }
	}
	return true;
    }

    public void agrupaPorColumna(DefaultTableModel paramDefaultTableModel, int paramInt1, int paramInt2,
	    Color paramColor) {
	int i = 0;
	Object localObject = null;
	do {
	    int j = 1;
	    CeldaReporte localN1 = (CeldaReporte) paramDefaultTableModel.getValueAt(i, paramInt1);
	    if (localN1.getContenido() == null) {
		localObject = null;
	    } else if (!localN1.equals(localObject)) {
		j = 0;
	    }
	    if (j == 0) {
		Vector localVector = new Vector(paramDefaultTableModel.getColumnCount());
		for (int k = 0; k < paramDefaultTableModel.getColumnCount(); k++) {
		    localVector.add(new CeldaReporte(null, paramColor));
		}
		CeldaReporte localN2 = (CeldaReporte) paramDefaultTableModel.getValueAt(i, paramInt1);
		localN2.setFondo(paramColor);
		localVector.set(paramInt2, localN2);
		localObject = paramDefaultTableModel.getValueAt(i, paramInt1);
		paramDefaultTableModel.insertRow(i, localVector);
	    }
	    i++;
	} while (i < paramDefaultTableModel.getRowCount());
    }

    public void agrupaPorColumnaString2(DefaultTableModel paramDefaultTableModel, int[] paramArrayOfInt, int paramInt) {
	int i = 0;
	Object[] arrayOfObject = new Object[paramArrayOfInt.length];
	do {
	    int j = 1;
	    for (int k = 0; k < paramArrayOfInt.length; k++) {
		Object localObject1 = paramDefaultTableModel.getValueAt(i, paramArrayOfInt[k]);
		if (localObject1 == null) {
		    arrayOfObject[k] = null;
		} else if (!localObject1.equals(arrayOfObject[k])) {
		    j = 0;
		    break;
		}
	    }
	    if (j == 0) {
		Vector localVector = new Vector(paramDefaultTableModel.getColumnCount());
		for (int m = 0; m < paramDefaultTableModel.getColumnCount(); m++) {
		    localVector.add(null);
		}
		for (int m = 0; m < paramArrayOfInt.length; m++) {
		    Object localObject2 = paramDefaultTableModel.getValueAt(i, m);
		    localVector.set(paramInt + m, localObject2);
		}
		for (int m = 0; m < paramArrayOfInt.length; m++) {
		    arrayOfObject[m] = paramDefaultTableModel.getValueAt(i, m);
		}
		paramDefaultTableModel.getDataVector().insertElementAt(localVector, i);
	    }
	    i++;
	} while (i < paramDefaultTableModel.getRowCount());
    }

    public void agrupaPorColumnaString(DefaultTableModel paramDefaultTableModel, int paramInt1, int paramInt2) {
	int i = 0;
	Object localObject1 = null;
	do {
	    int j = 1;
	    Object localObject2 = paramDefaultTableModel.getValueAt(i, paramInt1);
	    if (localObject2 == null) {
		localObject1 = null;
	    } else if (!localObject2.equals(localObject1)) {
		j = 0;
	    }
	    if (j == 0) {
		Vector localVector = new Vector(paramDefaultTableModel.getColumnCount());
		for (int k = 0; k < paramDefaultTableModel.getColumnCount(); k++) {
		    localVector.add(null);
		}
		Object localObject3 = paramDefaultTableModel.getValueAt(i, paramInt1);
		localVector.set(paramInt2, localObject3);
		localObject1 = paramDefaultTableModel.getValueAt(i, paramInt1);
		paramDefaultTableModel.insertRow(i, localVector);
	    }
	    i++;
	} while (i < paramDefaultTableModel.getRowCount());
    }

    public void enumsAStrings(DefaultTableModel paramDefaultTableModel) {
	for (int i = 0; i < paramDefaultTableModel.getRowCount(); i++) {
	    for (int j = 0; j < paramDefaultTableModel.getColumnCount(); j++) {
		Object localObject = paramDefaultTableModel.getValueAt(i, j);
		if ((localObject instanceof Enum)) {
		    localObject = CadenasEnum.getCadena((Enum) localObject);
		    paramDefaultTableModel.setValueAt(localObject, i, j);
		}
	    }
	}
    }

    public void clavesFlexionAString(DefaultTableModel paramDefaultTableModel) {
	for (int i = 0; i < paramDefaultTableModel.getRowCount(); i++) {
	    for (int j = 0; j < paramDefaultTableModel.getColumnCount(); j++) {
		Object localObject = paramDefaultTableModel.getValueAt(i, j);
		if ((localObject instanceof ClaveFlexion)) {
		    String str = ((ClaveFlexion) localObject).getForma();
		    str = OpPalabras.strCompletoAUnicode(str);
		    paramDefaultTableModel.setValueAt(str, i, j);
		}
	    }
	}
    }

    public void transformaEnICeldasReporte(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	if (!A(paramDefaultTableModel, new String[][] { paramArrayOfString })) {
	    throw new RuntimeException(
		    "no todas las columnas del tablemodel están involucradas en esta transformación ");
	}
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    int j = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	    if (j == -1) {
		throw new RuntimeException("la columna " + paramArrayOfString[i] + " no existe en esta tabla ");
	    }
	    A(paramDefaultTableModel, j);
	}
    }

    public void transformaEnICeldasReporteTolerante(DefaultTableModel paramDefaultTableModel,
	    String[] paramArrayOfString) {
	if (!A(paramDefaultTableModel, new String[][] { paramArrayOfString })) {
	    throw new RuntimeException(
		    "no todas las columnas del tablemodel están involucradas en esta transformación ");
	}
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    int j = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	    if (j != -1) {
		A(paramDefaultTableModel, j);
	    }
	}
    }

    private void A(DefaultTableModel paramDefaultTableModel, int paramInt) {
	int i = paramDefaultTableModel.getRowCount();
	for (int j = 0; j < i; j++) {
	    try {
		Object localObject = paramDefaultTableModel.getValueAt(j, paramInt);
		if (localObject == null) {
		    paramDefaultTableModel.setValueAt(new CeldaReporte(null, Color.white), j, paramInt);
		} else if ((localObject instanceof String)) {
		    paramDefaultTableModel.setValueAt(new CeldaReporte((String) localObject, Color.white), j, paramInt);
		} else if ((localObject instanceof Integer)) {
		    paramDefaultTableModel.setValueAt(new CeldaReporte(((Integer) localObject).toString(), Color.white), j,
			    paramInt);
		} else if ((localObject instanceof Enum)) {
		    paramDefaultTableModel.setValueAt(new CeldaReporte(CadenasEnum.getCadena((Enum) localObject), Color.white), j, paramInt);
		} else if ((localObject instanceof ClaveFlexion)) {
		    ClaveFlexion localA = (ClaveFlexion) paramDefaultTableModel.getValueAt(j, paramInt);
		    paramDefaultTableModel.setValueAt(new CeldaReporte(localA.getForma(), Color.white), j, paramInt);
		} else {
		    throw new RuntimeException("no puedo convertir la columna "
			    + paramDefaultTableModel.getColumnName(paramInt) + " a ICeldasReporte; el objeto es  "
			    + localObject + " de la clase " + localObject.getClass().getName());
		}
	    } catch (Exception localException) {
		System.out.println("error transformando a CeldaReporte"
			+ paramDefaultTableModel.getValueAt(j, paramInt) + " fila=" + j + " columna=" + paramInt);
		throw new RuntimeException(localException);
	    }
	}
    }

    private boolean A(DefaultTableModel paramDefaultTableModel, String[][] paramArrayOfString) {
	LinkedList localLinkedList = new LinkedList();
	for (int i = 0; i < paramDefaultTableModel.getColumnCount(); i++) {
	    localLinkedList.add(paramDefaultTableModel.getColumnName(i));
	}
	for (String[] localObject2 : paramArrayOfString) {
	    for (String localObject4 : localObject2) {
		localLinkedList.remove(localObject4);
	    }
	}
	if (localLinkedList.size() > 0) {
	    Iterator it = localLinkedList.iterator();
	    while (((Iterator) it).hasNext()) {
		String str = (String) ((Iterator) it).next();
		logger.warn("columna del tablemodel no contenida en los arrays: " + str);
	    }
	}
	return localLinkedList.size() == 0;
    }

    public void fusionaColumnas(DefaultTableModel paramDefaultTableModel, int paramInt1, int paramInt2,
	    Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3) {
	int i = paramDefaultTableModel.getRowCount();
	int j = paramArrayOfObject1.length;
	for (int k = 0; k < i; k++) {
	    Object localObject1 = paramDefaultTableModel.getValueAt(k, paramInt1);
	    Object localObject2 = paramDefaultTableModel.getValueAt(k, paramInt2);
	    if ((localObject1 != null) && (localObject2 != null)) {
		for (int m = 0; m < j; m++) {
		    if ((localObject1.equals(paramArrayOfObject1[m])) && (localObject2.equals(paramArrayOfObject2[m]))) {
			paramDefaultTableModel.setValueAt(paramArrayOfObject3[m], k, paramInt1);
			break;
		    }
		}
	    }
	}
    }

    public void cambiaValorColumna(DefaultTableModel paramDefaultTableModel, String paramString, Object paramObject1,
	    Object paramObject2) {
	int i = paramDefaultTableModel.getRowCount();
	int j = paramDefaultTableModel.findColumn(paramString);
	for (int k = 0; k < i; k++) {
	    Object localObject = paramDefaultTableModel.getValueAt(k, j);
	    if ((localObject == null) && (paramObject1 == null)) {
		paramDefaultTableModel.setValueAt(paramObject2, k, j);
	    } else if ((localObject != null) && (paramObject1 != null) && (localObject.equals(paramObject1))) {
		paramDefaultTableModel.setValueAt(paramObject2, k, j);
	    }
	}
    }

    public DefaultTableModel reacomodaColumnas(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	DefaultTableModel localDefaultTableModel = new DefaultTableModel(paramArrayOfString, 0);
	int[] arrayOfInt = new int[paramArrayOfString.length];
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    int j = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	    if (j == -1) {
		throw new RuntimeException("no se puede reordenar porque la columna " + paramArrayOfString[i]
			+ " no existe");
	    }
	    arrayOfInt[i] = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	}
	for (int i = 0; i < paramDefaultTableModel.getRowCount(); i++) {
	    Object[] arrayOfObject = new Object[paramDefaultTableModel.getColumnCount()];
	    for (int k = 0; k < paramDefaultTableModel.getColumnCount(); k++) {
		arrayOfObject[k] = paramDefaultTableModel.getValueAt(i, arrayOfInt[k]);
	    }
	    localDefaultTableModel.addRow(arrayOfObject);
	}
	return localDefaultTableModel;
    }

    public void fusionaColumnasConPrioridad(DefaultTableModel paramDefaultTableModel, String paramString1,
	    String paramString2) {
	int i = paramDefaultTableModel.getRowCount();
	int j = paramDefaultTableModel.findColumn(paramString1);
	int k = paramDefaultTableModel.findColumn(paramString2);
	for (int m = 0; m < i; m++) {
	    Object localObject1 = paramDefaultTableModel.getValueAt(m, j);
	    Object localObject2 = paramDefaultTableModel.getValueAt(m, k);
	    Object localObject3 = localObject1;
	    if (localObject2 != null) {
		localObject3 = localObject2;
	    }
	    paramDefaultTableModel.setValueAt(localObject3, m, j);
	}
	borraColumna(paramDefaultTableModel, paramString2);
    }

    public void ordenaSegunColumnas(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString,
	    boolean[] paramArrayOfBoolean) {
	int[] arrayOfInt = getIndices(paramDefaultTableModel, paramArrayOfString);
	Vector localVector = paramDefaultTableModel.getDataVector();
	try {
	    Collections.sort(localVector, new _B(arrayOfInt, paramArrayOfBoolean));
	} catch (Exception localException) {
	    StringBuffer localStringBuffer = new StringBuffer("error al tratar de ordenar el tablemodel ");
	    localStringBuffer.append("según las siguientes columnas:");
	    for (String str : paramArrayOfString) {
		localStringBuffer.append(str + "\n");
	    }
	    logger.error(localStringBuffer.toString(), localException);
	}
	paramDefaultTableModel.fireTableStructureChanged();
    }

    public int[] getIndices(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString) {
	int[] arrayOfInt = new int[paramArrayOfString.length];
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    arrayOfInt[i] = paramDefaultTableModel.findColumn(paramArrayOfString[i]);
	}
	return arrayOfInt;
    }

    public DefaultTableModel clona(DefaultTableModel paramDefaultTableModel) {
	try {
	    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
	    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
	    localObjectOutputStream.writeObject(paramDefaultTableModel);
	    localObjectOutputStream.close();
	    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(
		    localByteArrayOutputStream.toByteArray());
	    ObjectInputStream localObjectInputStream = new ObjectInputStream(localByteArrayInputStream);
	    DefaultTableModel localDefaultTableModel = (DefaultTableModel) localObjectInputStream.readObject();
	    localObjectInputStream.close();
	    return localDefaultTableModel;
	} catch (Exception localException) {
	    localException.printStackTrace();
	}
	return null;
    }

    public void fusionaTiemposYAspectos(DefaultTableModel paramDefaultTableModel) {
	String[] arrayOfString1 = Recursos.getArray(new String[] { "futuro", "aoristo", "perfecto", "pluscuamperfecto" });
	String[] arrayOfString2 = Recursos.getArray(new String[] { "futuro", "aoristo", "perfecto", "pluscuamperfecto" });
	for (int i = 0; i < arrayOfString2.length; i++) {
	    int tmp76_74 = i;
	    String[] tmp76_73 = arrayOfString2;
	    tmp76_73[tmp76_74] = (tmp76_73[tmp76_74] + " II");
	}
	fusionaColumnas(paramDefaultTableModel, paramDefaultTableModel.findColumn("TIEMPO"),
		paramDefaultTableModel.findColumn("FUERTE"), arrayOfString1,
		new Object[] { Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1) },
		arrayOfString2);
	String[] arrayOfString3 = Recursos.getArray(new String[] { "futuro", "confectivo", "perfectivo" });
	String[] arrayOfString4 = Recursos.getArray(new String[] { "futuro", "confectivo", "perfectivo" });
	for (int j = 0; j < arrayOfString4.length; j++) {
	    int tmp217_215 = j;
	    String[] tmp217_213 = arrayOfString4;
	    tmp217_213[tmp217_215] = (tmp217_213[tmp217_215] + " II");
	}
	fusionaColumnas(paramDefaultTableModel, paramDefaultTableModel.findColumn("ASPECTO"),
		paramDefaultTableModel.findColumn("FUERTE"), arrayOfString3,
		new Object[] { Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1) },
		arrayOfString4);
	borraColumna(paramDefaultTableModel, "FUERTE");
    }

    public void fusionaAspectos(DefaultTableModel paramDefaultTableModel) {
	FuerteDebil[] arrFuertes = new FuerteDebil[3];
	Arrays.fill(arrFuertes, FuerteDebil.Fuerte);
	Aspecto [] arrAspectos = { Aspecto.Futuro, Aspecto.Confectivo, Aspecto.Perfectivo};
	AspectosSegundos[] arrAspSegundos = { AspectosSegundos.FuturoSegundo, AspectosSegundos.ConfectivoSegundo, AspectosSegundos.PerfectivoSegundo};
	fusionaColumnas(paramDefaultTableModel, paramDefaultTableModel.findColumn("ASPECTO"),
		paramDefaultTableModel.findColumn("FUERTE"), arrAspectos, arrFuertes, arrAspSegundos);
	FuerteDebil[] arrayOfP2 = new FuerteDebil[Aspecto.values().length];
	AspectosSegundos [] arrAspNoSeg = { AspectosSegundos.Infectivo, AspectosSegundos.Futuro, AspectosSegundos.Perfectivo};
	Arrays.fill(arrayOfP2, FuerteDebil.Debil);
	fusionaColumnas(paramDefaultTableModel, paramDefaultTableModel.findColumn("ASPECTO"),
		paramDefaultTableModel.findColumn("FUERTE"), Aspecto.values(), arrayOfP2, arrAspNoSeg);
	borraColumna(paramDefaultTableModel, "FUERTE");
    }

    public void fusionaTiempos(DefaultTableModel paramDefaultTableModel) {
	FuerteDebil[] arrayOfP1 = new FuerteDebil[4];
	Arrays.fill(arrayOfP1, FuerteDebil.Fuerte);
	Tiempo[] arrayOfj = { Tiempo.Futuro, Tiempo.Aoristo, Tiempo.Perfecto, Tiempo.Pluscuamperfecto};
	TiempoSegundos[] arrSegundos = { TiempoSegundos.FuturoSegundo, TiempoSegundos.AoristoSegundo, TiempoSegundos.PerfectoSegundo, TiempoSegundos.PluscuamperfectoSegundo };
	fusionaColumnas(paramDefaultTableModel, paramDefaultTableModel.findColumn("TIEMPO"),
		paramDefaultTableModel.findColumn("FUERTE"), arrayOfj, arrayOfP1, arrSegundos);
	FuerteDebil[] arrayOfP2 = new FuerteDebil[Tiempo.values().length];
	TiempoSegundos[] arrNoSegundos = { TiempoSegundos.Presente, TiempoSegundos.Futuro, TiempoSegundos.Aoristo, TiempoSegundos.Perfecto, TiempoSegundos.Pluscuamperfecto };
	Arrays.fill(arrayOfP2, FuerteDebil.Debil);
	fusionaColumnas(paramDefaultTableModel, paramDefaultTableModel.findColumn("TIEMPO"),
		paramDefaultTableModel.findColumn("FUERTE"), Aspecto.values(), arrayOfP2, arrNoSegundos);
	borraColumna(paramDefaultTableModel, "FUERTE");
    }

    public void agrupaPorColumnas(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString1, int paramInt,
	    String[] paramArrayOfString2, Color paramColor) {
	int i = paramDefaultTableModel.getColumnCount();
	if (i - paramInt < paramArrayOfString1.length) {
	    StringBuffer localStringBuffer1 = new StringBuffer("no se podrá agrupar porque "
		    + paramArrayOfString1.length);
	    localStringBuffer1.append(" columnas a revisar (a las que luego debo sacar) no caben a partir del número "
		    + paramInt);
	    localStringBuffer1.append(" en un TableModel que tiene sólo " + i + " columnas");
	    throw new RuntimeException(localStringBuffer1.toString());
	}
	if (paramDefaultTableModel.findColumn("MARCA_TITULO_AGRUPADO") < 0) {
	    paramDefaultTableModel.addColumn("MARCA_TITULO_AGRUPADO");
	}
	int j = paramDefaultTableModel.findColumn("MARCA_TITULO_AGRUPADO");
	int[] arrayOfInt1 = new int[paramArrayOfString1.length];
	for (int k = 0; k < paramArrayOfString1.length; k++) {
	    arrayOfInt1[k] = paramDefaultTableModel.findColumn(paramArrayOfString1[k]);
	    if (arrayOfInt1[k] == -1) {
		StringBuffer localStringBuffer2 = new StringBuffer("una de las columnas a revisar dadas: "
			+ paramArrayOfString1[k]);
		localStringBuffer2.append(" no está en tableModel \n");
		throw new RuntimeException(localStringBuffer2.toString());
	    }
	}
	int[] arrayOfInt2 = new int[paramArrayOfString2.length];
	for (int m = 0; m < paramArrayOfString2.length; m++) {
	    arrayOfInt2[m] = paramDefaultTableModel.findColumn(paramArrayOfString2[m]);
	}
	int[] arrayOfInt3 = A(i, paramInt, arrayOfInt1, arrayOfInt2.length);
	Object localObject2;
	for (int n = 0; n < paramArrayOfString2.length; n++) {
	    int i1 = 0;
	    String str = paramArrayOfString2[n];
	    for (int i3 = 0; i3 < paramArrayOfString1.length; i3++) {
		if (paramArrayOfString1[i3].equals(str)) {
		    i1 = 1;
		    break;
		}
	    }
	    if (i1 == 0) {
		localObject2 = new StringBuffer("La columna de rótulo " + paramArrayOfString2[n]);
		((StringBuffer) localObject2).append(" no está incluida en el conjunto de columnas a revisar ");
		throw new RuntimeException(((StringBuffer) localObject2).toString());
	    }
	}
	int n = 0;
	Object localObject1 = null;
	do {
	    int i2 = 1;
	    localObject2 = A(paramDefaultTableModel, n, arrayOfInt1);
	    if (paramDefaultTableModel.getValueAt(n, j) != null) {
		localObject1 = null;
	    } else if (!Arrays.equals((Object[]) localObject2, (Object[]) localObject1)) {
		i2 = 0;
	    }
	    if (i2 == 0) {
		Vector localVector = new Vector(paramDefaultTableModel.getColumnCount());
		for (int i4 = 0; i4 < paramDefaultTableModel.getColumnCount(); i4++) {
		    if (paramColor == null) {
			localVector.add(null);
		    } else {
			localVector.add(new CeldaReporte("", paramColor));
		    }
		}
		if (paramColor == null) {
		    localVector.set(j, "X");
		} else {
		    localVector.set(j, new CeldaReporte("X", paramColor));
		}
		Object[] arrayOfObject = A(paramDefaultTableModel, n, arrayOfInt2);
		A(localVector, arrayOfInt3, arrayOfObject, paramColor);
		localObject1 = localObject2;
		paramDefaultTableModel.insertRow(n, localVector);
	    }
	    n++;
	} while (n < paramDefaultTableModel.getRowCount());
	for (int i2 = 0; i2 < paramArrayOfString1.length; i2++) {
	    borraColumna(paramDefaultTableModel, paramArrayOfString1[i2]);
	}
    }

    public int comparaConColumnasParametricas(DefaultTableModel paramDefaultTableModel, Object[] paramArrayOfObject1,
	    Object[] paramArrayOfObject2, String[] paramArrayOfString) {
	for (int i = 0; i < paramArrayOfString.length; i++) {
	    Object localObject1 = paramArrayOfObject1[paramDefaultTableModel.findColumn(paramArrayOfString[i])];
	    Object localObject2 = paramArrayOfObject2[paramDefaultTableModel.findColumn(paramArrayOfString[i])];
	    if ((localObject1 == null) && (localObject2 == null)) {
		return 0;
	    }
	    if ((localObject1 != null) && (localObject2 == null)) {
		return 1;
	    }
	    if ((localObject1 == null) && (localObject2 != null)) {
		return -1;
	    }
	    int j;
	    if ((localObject1 instanceof String)) {
		j = ((String) localObject1).compareTo((String) localObject2);
		if (j != 0) {
		    return j;
		}
	    } else if ((localObject1 instanceof Integer)) {
		j = ((Integer) localObject1).compareTo((Integer) localObject2);
		if (j != 0) {
		    return j;
		}
	    } else if ((localObject1 instanceof CeldaReporte)) {
		j = ((CeldaReporte) localObject1).compareTo((CeldaReporte) localObject2);
		if (j != 0) {
		    return j;
		}
	    } else if ((localObject1 instanceof Enum)) {
		j = ((Enum) localObject1).compareTo((Enum) localObject2);
		if (j != 0) {
		    return j;
		}
	    }
	}
	return 0;
    }

    public Object[] getFilaComoArray(DefaultTableModel paramDefaultTableModel, int paramInt) {
	return ((Vector) paramDefaultTableModel.getDataVector().get(paramInt)).toArray();
    }

    public void horizontaliza(DefaultTableModel paramDefaultTableModel, String[] paramArrayOfString1,
	    String[] paramArrayOfString2, String paramString1, Object[] paramArrayOfObject, String paramString2,
	    String[] paramArrayOfString3) {
	if (paramArrayOfObject.length != paramArrayOfString3.length) {
	     StringBuffer localObject1 = new StringBuffer("error en horizontaliza: cantidad de valores=" + paramArrayOfObject.length);
	    ((StringBuffer) localObject1).append(" distinta de la cantidad de columnas destino="
		    + paramArrayOfString3.length);
	    logger.error(((StringBuffer) localObject1).toString());
	    throw new RuntimeException(((StringBuffer) localObject1).toString());
	}
	Object localObject1 = new ArrayList(Arrays.asList(paramArrayOfString2));
	((List) localObject1).add(paramString1);
	boolean[] arrayOfBoolean = new boolean[paramArrayOfString2.length];
	Arrays.fill(arrayOfBoolean, true);
	ordenaSegunColumnas(paramDefaultTableModel, paramArrayOfString2, arrayOfBoolean);
	ArrayList localArrayList = new ArrayList();
	for (int i = 0; i < paramArrayOfString3.length; i++) {
	    paramDefaultTableModel.addColumn(paramArrayOfString3[i]);
	    localArrayList.add(Integer.valueOf(paramDefaultTableModel.getColumnCount() - 1));
	}
	for (int i = 0; i < paramDefaultTableModel.getRowCount(); i++) {
	    Object[] arrayOfObject1 = ((Vector) paramDefaultTableModel.getDataVector().get(i)).toArray();
	    int j = paramDefaultTableModel.findColumn(paramString1);
	    int k = paramDefaultTableModel.findColumn(paramString2);
	    int m = i;
	    while (m < paramDefaultTableModel.getRowCount()) {
		int n = 0;
		Object[] arrayOfObject2 = getFilaComoArray(paramDefaultTableModel, m);
		int i1 = comparaConColumnasParametricas(paramDefaultTableModel, arrayOfObject1, arrayOfObject2,
			paramArrayOfString1);
		if (i1 == 0) {
		    Object localObject2 = arrayOfObject2[j];
		    for (int i2 = 0; i2 < paramArrayOfObject.length; i2++) {
			Object localObject3 = paramArrayOfObject[i2];
			if (((localObject2 == null) && (localObject3 == null)) || (localObject2.equals(localObject3))) {
			    Object localObject4 = paramDefaultTableModel.getValueAt(m, k);
			    paramDefaultTableModel.setValueAt(localObject4, i,
				    ((Integer) localArrayList.get(i2)).intValue());
			    if (m == i) {
				break;
			    }
			    paramDefaultTableModel.removeRow(m);
			    n = 1;
			    break;
			}
		    }
		}
		if (n == 0) {
		    m++;
		}
	    }
	}
	borraColumna(paramDefaultTableModel, paramString1);
	borraColumna(paramDefaultTableModel, paramString2);
    }

    private Object[] A(DefaultTableModel paramDefaultTableModel, int paramInt, int[] paramArrayOfInt) {
	Object[] arrayOfObject = new Object[paramArrayOfInt.length];
	for (int i = 0; i < paramArrayOfInt.length; i++) {
	    arrayOfObject[i] = paramDefaultTableModel.getValueAt(paramInt, paramArrayOfInt[i]);
	}
	return arrayOfObject;
    }

    private int[] A(int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3) {
	int[] arrayOfInt = new int[paramInt3];
	if (paramInt2 + paramInt3 > paramInt1) {
	    throw new RuntimeException("no hay manera de que " + paramInt3 + " quepan en la fila, que es de "
		    + paramInt1 + " celdas, comenzando desde la posición " + paramInt2);
	}
	int i = paramInt2;
	for (int j = 0; j < arrayOfInt.length; j++) {
	    while (Arrays.binarySearch(paramArrayOfInt, i) > -1) {
		if (i >= paramInt1) {
		    throw new RuntimeException(
			    "lo siento, pero restando la columnas que serán borradas, no hay suficiente lugar desde la posición "
				    + paramInt2 + " para meter todos los rótulos");
		}
		i++;
	    }
	    arrayOfInt[j] = i;
	    i++;
	}
	return arrayOfInt;
    }

    private void A(Vector<Object> paramVector, int[] paramArrayOfInt, Object[] paramArrayOfObject, Color paramColor) {
	for (int i = 0; i < paramArrayOfInt.length; i++) {
	    Object localObject = paramArrayOfObject[i];
	    if (paramColor != null) {
		CeldaReporte localN = (CeldaReporte) localObject;
		localN.setFondo(paramColor);
	    }
	    paramVector.set(paramArrayOfInt[i], paramArrayOfObject[i]);
	}
    }

    public void agregaFilaComoAray(DefaultTableModel paramDefaultTableModel, Object[] paramArrayOfObject) {
	paramDefaultTableModel.getDataVector().add(new Vector(Arrays.asList(paramArrayOfObject)));
    }

    public void agregaColumna(DefaultTableModel paramDefaultTableModel, String paramString, Object paramObject) {
	int i = paramDefaultTableModel.getRowCount();
	Vector localVector = new Vector(paramDefaultTableModel.getRowCount());
	for (int j = 0; j < i; j++) {
	    localVector.add(paramObject);
	}
	paramDefaultTableModel.addColumn(paramString, localVector);
    }

    private class _B implements Comparator<Vector<?>> {
	int[] C;
	boolean[] arrComparar;

	_B(int[] paramArrayOfInt, boolean[] paramArrayOfBoolean) {
	    this.C = paramArrayOfInt;
	    this.arrComparar = paramArrayOfBoolean;
	}

	public int compare(Vector paramVector1, Vector paramVector2) {
	    for (int i = 0; i < this.C.length; i++) {
		int j = 0;
		Object localObject1 = paramVector1.get(this.C[i]);
		Object localObject2 = paramVector2.get(this.C[i]);
		if (((localObject1 instanceof String)) && (((String) localObject1).length() == 0)) {
		    localObject1 = null;
		}
		if (((localObject2 instanceof String)) && (((String) localObject2).length() == 0)) {
		    localObject2 = null;
		}
		if ((localObject1 == null) && (localObject2 == null)) {
		    j = 0;
		} else if (localObject1 == null) {
		    j = 1;
		} else if (localObject2 == null) {
		    j = -1;
		} else if ((localObject1 instanceof Comparable)) {
		    if (this.arrComparar[i]) {
			j = ((Comparable) localObject1).compareTo(localObject2);
		    } else {
			j = ((Comparable) localObject2).compareTo(localObject1);
		    }
		} else if (this.arrComparar[i]) {
		    j = localObject1.toString().compareTo(localObject2.toString());
		} else {
		    j = localObject2.toString().compareTo(localObject1.toString());
		}
		if (j != 0) {
		    return j;
		}
	    }
	    return 0;
	}
    }

    public class _A extends DefaultTableCellRenderer {
	public _A() {
	}

	public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1,
		boolean paramBoolean2, int paramInt1, int paramInt2) {
	    if ((paramObject instanceof Integer)) {
		setText(OpPalabras.strCompletoAUnicode(((Integer) paramObject).toString()));
	    } else if ((paramObject instanceof String)) {
		setText(OpPalabras.strCompletoAUnicode((String) paramObject));
	    } else if ((paramObject instanceof CeldaReporte)) {
		CeldaReporte localN = (CeldaReporte) paramObject;
		setText(localN.debug());
	    }
	    return this;
	}
    }
}
