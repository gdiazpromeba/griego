
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
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.AspectosSegundos;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TiempoSegundos;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;

public class UtilidadesTM {

    private Logger logger = Logger.getLogger(getClass().getName());

    public String[] getNombresColumna(DefaultTableModel tn) {
        List<String> nombres = new ArrayList<>();
        int i = tn.getColumnCount();
        for (int j = 0; j < i; j++) {
            nombres.add(tn.getColumnName(j));
        }
        return nombres.toArray(new String[0]);
    }

    /**
     * método utilitario para observar tablemodels, en sus estados intermedios
     * @param tm
     * @param titulo
     */
    public void muestraTableModelDefault(DefaultTableModel tm, String titulo) {
        DefaultTableModel defaultTableModel = clona(tm);
        JFrame frame = new JFrame(titulo);
        frame.setSize(300, 200);
        JTable table = new JTable(defaultTableModel);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer());
        }
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellEditor(null);
        }
        frame.getContentPane().add(new JScrollPane(table));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }

    public void ICeldasAString(DefaultTableModel tm) {
        for (int i = 0; i < tm.getRowCount(); i++) {
            for (int j = 0; j < tm.getRowCount(); j++) {
                Object valorCelda = tm.getValueAt(i, j);
                if (valorCelda instanceof CeldaReporte) {
                    tm.setValueAt(((CeldaReporte) valorCelda).getContenido(), i, j);
                }
            }
        }
    }

    public Genero[] extraeArrayGeneros(DefaultTableModel tm) {
        int i = tm.findColumn("GENERO");
        Set<Genero> setGeneros = new TreeSet<>();
        for (int j = 0; j < tm.getRowCount(); j++) {
            Genero genero = (Genero) tm.getValueAt(j, i);
            setGeneros.add(genero);
        }
        List<Genero> listGeneros = new ArrayList<>(setGeneros);
        Collections.sort(listGeneros);
        Genero[] localObject = listGeneros.toArray(new Genero[1]);
        return localObject;
    }

    public String[] extraeEncabezadosGeneros(Genero[] paramArrayOfA) {
        String[] arrayOfString = new String[paramArrayOfA.length];
        for (int i = 0; i < paramArrayOfA.length; i++) {
            String str = paramArrayOfA[i].toString();
            arrayOfString[i] = str;
        }
        return arrayOfString;
    }

    public void reemplazaNombresColumna(DefaultTableModel tm, String[] nombresNuevos) {
        if (tm.getColumnCount() != nombresNuevos.length) {
            throw new RuntimeException("el número de columnas a reemplazar por recursos no coincide con el número de recursos");
        }
        Vector<String> nombres = new Vector<>(nombresNuevos.length);
        for (String str1 : nombresNuevos) {
            String str2 = Recursos.getCadena(str1);
            nombres.add(str2);
        }
        tm.setColumnIdentifiers(nombres);
    }

    public void reemplazaNombresColumna(DefaultTableModel tm, String[] nombresViejos, String[] nombresNuevos) {
        List<String> lstNombresViejos = new ArrayList<>(Arrays.asList(nombresViejos));
        Vector<String> nombres = new Vector<>();
        for (int i = 0; i < tm.getColumnCount(); i++) {
            nombres.add(tm.getColumnName(i));
        }
        for (int i = 0; i < nombres.size(); i++) {
            int j = lstNombresViejos.indexOf(nombres.get(i));
            if (j > -1) {
                nombres.set(i, Recursos.getCadena(nombresNuevos[j]));
            }
        }
        tm.setColumnIdentifiers(nombres);
    }

    public void eliminaClavesFlexion(DefaultTableModel tm, List<ClaveFlexion> paramList, String paramString) {
        int i = tm.findColumn(paramString);
        int j = 0;
        while (j < tm.getDataVector().size()) {
            Vector<?> localVector = (Vector<?>) tm.getDataVector().get(j);
            Object localObject = localVector.get(i);
            if ((localObject instanceof ClaveFlexion)) {
                ClaveFlexion localA = (ClaveFlexion) localObject;
                if (paramList.contains(localA)) {
                    tm.removeRow(j);
                } else {
                    j++;
                }
            } else {
                j++;
            }
        }
    }

    public void clavesFlexionACadena(DefaultTableModel tm, String paramString) {
        int i = tm.getRowCount();
        int j = tm.findColumn(paramString);
        for (int k = 0; k < i; k++) {
            ClaveFlexion localA = (ClaveFlexion) tm.getValueAt(k, j);
            String str = localA.getForma();
            tm.setValueAt(str, k, j);
        }
    }

    public void poneNombreColumna(DefaultTableModel tm, int paramInt, String paramString) {
        Vector<String> nombres = new Vector<>(Arrays.asList(getNombresColumna(tm)));
        nombres.set(paramInt, paramString);
        tm.setColumnIdentifiers(nombres);
    }

    public List<String> nombresColumna(DefaultTableModel tm) {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < tm.getColumnCount(); i++) {
            String str = tm.getColumnName(i);
            nombres.add(str);
        }
        return nombres;
    }

    public void columnasAInterfaz(DefaultTableModel tm, String[] paramArrayOfString) {
        for (int i = 0; i < paramArrayOfString.length; i++) {
            int indiceColumna = tm.findColumn(paramArrayOfString[i]);
            columnaAInterfaz(tm, indiceColumna);
        }
    }

    private void columnaAInterfaz(DefaultTableModel paramDefaultTableModel, int paramInt) {
        boolean muestraMacrones = Configuracion.getMuestraMakrones();
        int i = paramDefaultTableModel.getRowCount();
        for (int j = 0; j < i; j++) {
            Object localObject1 = paramDefaultTableModel.getValueAt(j, paramInt);
            Object localObject2;
            String str1;
            if ((localObject1 instanceof String)) {
                localObject2 = (String) paramDefaultTableModel.getValueAt(j, paramInt);
                if (localObject2 != null) {
                    str1 = null;
                    if (muestraMacrones) {
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
                        if (muestraMacrones) {
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
                        if (muestraMacrones) {
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
                columnaAInterfaz(paramDefaultTableModel, j);
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

    /**
     * deja sólo las filas de un tableModel cuyas celdas en las columnas dadas, tienen los valores dados.
     * @param tableModel
     * @param nombresColumna
     * @param valoresCelda
     */
    public void dejaSelect(DefaultTableModel tableModel, String[] nombresColumna, Object[] valoresCelda) {
        int i = 0;
        int[] indicesColumnas = indicesColumnas(tableModel, nombresColumna);
        while (i < tableModel.getRowCount()) {
            int j = 1;
            for (int k = 0; k < indicesColumnas.length; k++) {
                Object valorCelda = tableModel.getValueAt(i, indicesColumnas[k]);
                if (!valorCelda.equals(valoresCelda[k])) {
                    j = 0;
                    break;
                }
            }
            if (j == 0) {
                tableModel.removeRow(i);
            } else {
                i++;
            }
        }
    }

    /**
     * dado un tableModel, encuentra los índices de los nombres de columna dados
     * @param tableModel
     * @param nombresColumna
     * @return
     */
    private int[] indicesColumnas(DefaultTableModel tableModel, String[] nombresColumna) {
        int[] indicesColumna = new int[nombresColumna.length];
        for (int i = 0; i < nombresColumna.length; i++) {
            indicesColumna[i] = tableModel.findColumn(nombresColumna[i]);
        }
        return indicesColumna;
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

    public void agrupaPorColumna(DefaultTableModel tm, int paramInt1, int paramInt2, Color color) {
        int fila = 0;
        Object localObject = null;
        do {
            int resComparacion = 1;
            CeldaReporte valorCelda = (CeldaReporte) tm.getValueAt(fila, paramInt1);
            if (valorCelda.getContenido() == null) {
                localObject = null;
            } else if (!valorCelda.equals(localObject)) {
                resComparacion = 0;
            }
            if (resComparacion == 0) {
                Vector<CeldaReporte> localVector = new Vector<>(tm.getColumnCount());
                for (int k = 0; k < tm.getColumnCount(); k++) {
                    localVector.add(new CeldaReporte(null, color));
                }
                CeldaReporte localN2 = (CeldaReporte) tm.getValueAt(fila, paramInt1);
                localN2.setFondo(color);
                localVector.set(paramInt2, localN2);
                localObject = tm.getValueAt(fila, paramInt1);
                tm.insertRow(fila, localVector);
            }
            fila++;
        } while (fila < tm.getRowCount());
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
                    localObject = ((Enum) localObject).toString();
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
            throw new RuntimeException("no todas las columnas del tablemodel están involucradas en esta transformación ");
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
                    paramDefaultTableModel.setValueAt(new CeldaReporte(((Enum) localObject).toString(), Color.white), j, paramInt);
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

    public void ordenaSegunColumnas(DefaultTableModel tm, String[] columnasOrden, boolean[] arrComparar) {
        int[] indColumnasOrden = getIndices(tm, columnasOrden);
        Vector vecDatos = tm.getDataVector();
        try {
            Collections.sort(vecDatos, new ComparadorFilas(indColumnasOrden, arrComparar));
        } catch (Exception localException) {
            StringBuffer sb = new StringBuffer("error al tratar de ordenar el tablemodel ");
            sb.append("según las siguientes columnas:");
            for (String str : columnasOrden) {
                sb.append(str + "\n");
            }
            logger.error(sb.toString(), localException);
        }
        tm.fireTableStructureChanged();
    }

    /**
     * dado un array de nombres de columna de un tablemodel, devuelve los índices respectivos
     * con la posición de esas columnas en el tablemodel
     * @param tm
     * @param columnas
     * @return
     */
    public int[] getIndices(DefaultTableModel tm, String[] columnas) {
        int[] arrIndices = new int[columnas.length];
        for (int i = 0; i < columnas.length; i++) {
            arrIndices[i] = tm.findColumn(columnas[i]);
        }
        return arrIndices;
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

    public void fusionaAspectos(DefaultTableModel tm) {
        FuerteDebil[] arrFuertes = new FuerteDebil[3];
        Arrays.fill(arrFuertes, FuerteDebil.Fuerte);
        Aspecto[] arrAspectos = { Aspecto.Futuro, Aspecto.Confectivo, Aspecto.Perfectivo };
        AspectosSegundos[] arrAspSegundos = { AspectosSegundos.FuturoSegundo, AspectosSegundos.ConfectivoSegundo, AspectosSegundos.PerfectivoSegundo };
        fusionaColumnas(tm, tm.findColumn("ASPECTO"),
                tm.findColumn("FUERTE"), arrAspectos, arrFuertes, arrAspSegundos);
        FuerteDebil[] arrFuerteDebil = new FuerteDebil[Aspecto.values().length];
        AspectosSegundos[] arrAspNoSeg = { AspectosSegundos.Infectivo, AspectosSegundos.Futuro, AspectosSegundos.Confectivo, AspectosSegundos.Perfectivo };
        Arrays.fill(arrFuerteDebil, FuerteDebil.Debil);
        fusionaColumnas(tm, tm.findColumn("ASPECTO"), tm.findColumn("FUERTE"), Aspecto.values(), arrFuerteDebil, arrAspNoSeg);
        borraColumna(tm, "FUERTE");
    }

    public void fusionaTiempos(DefaultTableModel tm) {
        FuerteDebil[] arrFuertes = new FuerteDebil[4];
        Arrays.fill(arrFuertes, FuerteDebil.Fuerte);
        Tiempo[] arrTiempos = { Tiempo.Futuro, Tiempo.Aoristo, Tiempo.Perfecto, Tiempo.Pluscuamperfecto };
        TiempoSegundos[] arrSegundos = { TiempoSegundos.FuturoSegundo, TiempoSegundos.AoristoSegundo, TiempoSegundos.PerfectoSegundo, TiempoSegundos.PluscuamperfectoSegundo };
        fusionaColumnas(tm, tm.findColumn("TIEMPO"),
                tm.findColumn("FUERTE"), arrTiempos, arrFuertes, arrSegundos);
        FuerteDebil[] arrFuerteDebil = new FuerteDebil[Tiempo.values().length];
        TiempoSegundos[] arrNoSegundos = { TiempoSegundos.Presente, TiempoSegundos.Futuro, TiempoSegundos.Aoristo, TiempoSegundos.Perfecto, TiempoSegundos.Pluscuamperfecto };
        Arrays.fill(arrFuerteDebil, FuerteDebil.Debil);
        fusionaColumnas(tm, tm.findColumn("TIEMPO"), tm.findColumn("FUERTE"), Aspecto.values(), arrFuerteDebil, arrNoSegundos);
        borraColumna(tm, "FUERTE");
    }

    public void agrupaPorColumnas(DefaultTableModel tm, String[] paramArrayOfString1, int paramInt,
            String[] paramArrayOfString2, Color paramColor) {
        int i = tm.getColumnCount();
        if (i - paramInt < paramArrayOfString1.length) {
            StringBuffer sb = new StringBuffer("no se podrá agrupar porque " + paramArrayOfString1.length)
            .append(" columnas a revisar (a las que luego debo sacar) no caben a partir del número " + paramInt)
            .append(" en un TableModel que tiene sólo " + i + " columnas");
            throw new RuntimeException(sb.toString());
        }
        if (tm.findColumn("MARCA_TITULO_AGRUPADO") < 0) {
            tm.addColumn("MARCA_TITULO_AGRUPADO");
        }
        int j = tm.findColumn("MARCA_TITULO_AGRUPADO");
        int[] arrayOfInt1 = new int[paramArrayOfString1.length];
        for (int k = 0; k < paramArrayOfString1.length; k++) {
            arrayOfInt1[k] = tm.findColumn(paramArrayOfString1[k]);
            if (arrayOfInt1[k] == -1) {
                StringBuffer localStringBuffer2 = new StringBuffer("una de las columnas a revisar dadas: "
                        + paramArrayOfString1[k]);
                localStringBuffer2.append(" no está en tableModel \n");
                throw new RuntimeException(localStringBuffer2.toString());
            }
        }
        int[] arrayOfInt2 = new int[paramArrayOfString2.length];
        for (int m = 0; m < paramArrayOfString2.length; m++) {
            arrayOfInt2[m] = tm.findColumn(paramArrayOfString2[m]);
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
            localObject2 = A(tm, n, arrayOfInt1);
            if (tm.getValueAt(n, j) != null) {
                localObject1 = null;
            } else if (!Arrays.equals((Object[]) localObject2, (Object[]) localObject1)) {
                i2 = 0;
            }
            if (i2 == 0) {
                Vector localVector = new Vector(tm.getColumnCount());
                for (int i4 = 0; i4 < tm.getColumnCount(); i4++) {
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
                Object[] arrayOfObject = A(tm, n, arrayOfInt2);
                A(localVector, arrayOfInt3, arrayOfObject, paramColor);
                localObject1 = localObject2;
                tm.insertRow(n, localVector);
            }
            n++;
        } while (n < tm.getRowCount());
        for (int i2 = 0; i2 < paramArrayOfString1.length; i2++) {
            borraColumna(tm, paramArrayOfString1[i2]);
        }
    }

    public int comparaConColumnasParametricas(DefaultTableModel tm, Object[] arrayFila1,
            Object[] arrayFila2, String[] nombresColumna) {
        for (int i = 0; i < nombresColumna.length; i++) {
            Object contenidoCelda1 = arrayFila1[tm.findColumn(nombresColumna[i])];
            Object contenidoCelda2 = arrayFila2[tm.findColumn(nombresColumna[i])];
            if ((contenidoCelda1 == null) && (contenidoCelda2 == null)) {
                return 0;
            }
            if ((contenidoCelda1 != null) && (contenidoCelda2 == null)) {
                return 1;
            }
            if ((contenidoCelda1 == null) && (contenidoCelda2 != null)) {
                return -1;
            }
            int j;
            if ((contenidoCelda1 instanceof String)) {
                j = ((String) contenidoCelda1).compareTo((String) contenidoCelda2);
                if (j != 0) {
                    return j;
                }
            } else if ((contenidoCelda1 instanceof Integer)) {
                j = ((Integer) contenidoCelda1).compareTo((Integer) contenidoCelda2);
                if (j != 0) {
                    return j;
                }
            } else if ((contenidoCelda1 instanceof CeldaReporte)) {
                j = ((CeldaReporte) contenidoCelda1).compareTo((CeldaReporte) contenidoCelda2);
                if (j != 0) {
                    return j;
                }
            } else if ((contenidoCelda1 instanceof Enum)) {
                j = ((Enum) contenidoCelda1).compareTo((Enum) contenidoCelda2);
                if (j != 0) {
                    return j;
                }
            }
        }
        return 0;
    }

    public Object[] getFilaComoArray(DefaultTableModel tm, int numeroFila) {
        return ((Vector) tm.getDataVector().get(numeroFila)).toArray();
    }

    /**
     * 
     * @param tm                               el tablemodel a modificar
     * @param columnas                         las columnas que se van a convertir en filas
     * @param columnasOrden                    los nombres de columna relevantes para ordenar, en la secuencia según importancia
     * @param columnaEncabezadosHorizontales   la columna que va a desaparecer, y desarrollarse horizontalmente según los valores posibles
     * @param valoresPosibles                  los valores de posibles dicha columna, que ayudan a distribuir las celdas en alguna de las columnas recientemente generadas
     * @param columnaCelda                     la columna que se va a convertir en valores de celda
     * @param titulosEncabezadosColumna        los nombres de las nuevas columnas
     */
    public void horizontaliza(DefaultTableModel tm, String[] columnas, String[] columnasOrden, String columnaEncabezadosHorizontales, Object[] valoresPosibles,
            String columnaCelda, String[] titulosEncabezadosColumna) {
        if (valoresPosibles.length != titulosEncabezadosColumna.length) {
            StringBuffer sb = new StringBuffer("error en horizontaliza: cantidad de valores=" + valoresPosibles.length);
            sb.append(" distinta de la cantidad de columnas destino=" + titulosEncabezadosColumna.length);
            logger.error(sb.toString());
            throw new RuntimeException(sb.toString());
        }
        List<String> lista = new ArrayList<String>(Arrays.asList(columnasOrden));
        lista.add(columnaEncabezadosHorizontales);
        columnasOrden = lista.toArray(new String[] {});

        boolean[] arrOrdena = new boolean[columnasOrden.length];
        Arrays.fill(arrOrdena, true);
        ordenaSegunColumnas(tm, columnasOrden, arrOrdena);
        List<Integer> localArrayList = new ArrayList<>();
        for (int i = 0; i < titulosEncabezadosColumna.length; i++) {
            tm.addColumn(titulosEncabezadosColumna[i]);
            localArrayList.add(Integer.valueOf(tm.getColumnCount() - 1));
        }
        for (int i = 0; i < tm.getRowCount(); i++) {
            Object[] arrayFila1 = ((Vector) tm.getDataVector().get(i)).toArray();
            int indiceColEncabezadosHorizontales = tm.findColumn(columnaEncabezadosHorizontales);
            int indiceColCelda = tm.findColumn(columnaCelda);
            int m = i;
            while (m < tm.getRowCount()) {
                int n = 0;
                Object[] arrayFila2 = getFilaComoArray(tm, m);
                int i1 = comparaConColumnasParametricas(tm, arrayFila1, arrayFila2, columnas);
                if (i1 == 0) {
                    Object valorCelda = arrayFila2[indiceColEncabezadosHorizontales];
                    for (int i2 = 0; i2 < valoresPosibles.length; i2++) {
                        Object localObject3 = valoresPosibles[i2];
                        if (((valorCelda == null) && (localObject3 == null)) || (valorCelda.equals(localObject3))) {
                            Object localObject4 = tm.getValueAt(m, indiceColCelda);
                            tm.setValueAt(localObject4, i, ((Integer) localArrayList.get(i2)).intValue());
                            if (m == i) {
                                break;
                            }
                            tm.removeRow(m);
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
        borraColumna(tm, columnaEncabezadosHorizontales);
        borraColumna(tm, columnaCelda);
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
        Vector<Object> localVector = new Vector<>(paramDefaultTableModel.getRowCount());
        for (int j = 0; j < i; j++) {
            localVector.add(paramObject);
        }
        paramDefaultTableModel.addColumn(paramString, localVector);
    }

    private class ComparadorFilas implements Comparator<Vector<?>> {

        int[] indicesColumna;
        boolean[] arrComparar;

        ComparadorFilas(int[] indicesColumna, boolean[] arrComparar) {
            this.indicesColumna = indicesColumna;
            this.arrComparar = arrComparar;
        }

        public int compare(Vector<?> paramVector1, Vector<?> paramVector2) {
            for (int i = 0; i < this.indicesColumna.length; i++) {
                int resComparacion = 0;
                Object valor1 = paramVector1.get(this.indicesColumna[i]);
                Object valor2 = paramVector2.get(this.indicesColumna[i]);
                if (valor1 instanceof String && ((String) valor1).length() == 0) {
                    valor1 = null;
                }
                if (valor2 instanceof String && ((String) valor2).length() == 0) {
                    valor2 = null;
                }
                if (valor1 == null && valor2 == null) {
                    resComparacion = 0;
                } else if (valor1 == null) {
                    resComparacion = 1;
                } else if (valor2 == null) {
                    resComparacion = -1;
                } else if (valor1 instanceof Comparable) {
                    if (valor1.getClass() != valor2.getClass()){
                        throw new RuntimeException("cannot compare " + valor1 + " with " + valor2 + " because their classes are different");
                    }
                    if (this.arrComparar[i]) {
                        resComparacion = ((Comparable) valor1).compareTo(valor2);
                    } else {
                        resComparacion = ((Comparable) valor2).compareTo(valor1);
                    }
                } else if (this.arrComparar[i]) {
                    resComparacion = valor1.toString().compareTo(valor2.toString());
                } else {
                    resComparacion = valor2.toString().compareTo(valor1.toString());
                }
                if (resComparacion != 0) {
                    return resComparacion;
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
