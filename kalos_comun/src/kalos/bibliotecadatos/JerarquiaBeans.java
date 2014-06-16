package kalos.bibliotecadatos;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import org.apache.log4j.Logger;

import kalos.beans.TipoJerarquico;
import kalos.beans.TipoSustantivo;
import kalos.operaciones.OpBeans;

public class JerarquiaBeans<T extends TipoJerarquico> {

//    private Logger logger=Logger.getLogger("kalos.bibliotecadatos.JerarquiaBeans");
    protected DefaultMutableTreeNode raiz;

    protected DefaultTreeModel treeModel;

    protected Map<String, DefaultMutableTreeNode> mapNodes;

    protected boolean usaClave;

    /**
     * la propiedad String de los beans que indica el id del padre
     */
    protected String propiedadPadre;

    /**
     * los datos
     */
    private ListaSeleccionable beans;

    /**
     * wrapper de la jerarquéa de beans
     * 
     * @param id
     */
    public void setPK(String id) {
        beans.setPK(id);
    }

    public String getPK() {
        return beans.getPK();
    }

    protected void setBeans(List<T> lista) {
        beans = new ListaSeleccionable(lista);
        pueblaTreeModelYMapa();
    }

    public List<?> getBeans() {
        return beans.getLista();
    }

    @SuppressWarnings("unchecked")
    public JerarquiaBeans(List listaBeans, String propiedadPadre) {
        this.propiedadPadre = propiedadPadre;
        TipoJerarquico beanRaiz = new TipoJerarquico();
        beanRaiz.setId(null);
        beanRaiz.setDesClave("todo");
        raiz = new DefaultMutableTreeNode(beanRaiz);
        listaBeans.add(beanRaiz);
        this.beans = new ListaSeleccionable(listaBeans);
        pueblaTreeModelYMapa();
    }

    /**
     * Devuelve el Bean del nodo seleccionado, más los beans contenidos en los
     * nodos ancestros hasta la raíz (inclusive)
     * 
     * @param nodo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> getBeanYAncestros(DefaultMutableTreeNode nodo) {
        // TreeNode[] arrNodos=treeModel.getPathToRoot(nodo);
        TreeNode[] arrNodos = nodo.getPath();
        List<T> lstRegistros = new ArrayList<T>();
        for (int i = 0; i < arrNodos.length; i++) {
            DefaultMutableTreeNode nodAux = (DefaultMutableTreeNode) arrNodos[i];
            T regAux = (T) nodAux.getUserObject();
            lstRegistros.add(regAux);
        }
        return lstRegistros;
    }

    /**
     * dado un ID (no necesariamente el seleccionado) devuelve una lista con
     * todos los beans hasta la raíz, incluido él mismo.
     * 
     * @param id
     * @return
     */
    public List<T> getRegistroYAncestros(String id) {
        DefaultMutableTreeNode nodAux = getNodoDadoID(id);
        return getBeanYAncestros(nodAux);
    }

    /**
     * Función recursiva para devolver todos los registros hoja a partir de un
     * cierto nodo hacia abajo, incluyendo el nodo mismo llama a
     * cuelgaregistrodeste, que es la parte recursiva propiamente dicha
     * 
     * @param nod
     * @param lstResultado
     */
    @SuppressWarnings("unchecked")
    public List<T> getHojas(DefaultMutableTreeNode nod) {
        List<T> lstAux = new ArrayList<T>();
        if (nod.isLeaf())
            lstAux.add((T) nod.getUserObject());
        else
            pueblaRegistroEHijos(nod, lstAux);
        return lstAux;
    }

    /**
     * devuelve un nodo dada su propiedad id, buscándolo en el mapa de nodos
     * 
     * @param id
     * @return
     */
    private DefaultMutableTreeNode getNodoDadoID(String id) {
        return mapNodes.get(id);
    }

    /**
     * función sobrecargada que funciona con un registro como parámetro en lugar
     * de un nodo. El registro debe poseer una clave primaria de la misma
     * estructura que los registros del tablemodel
     * 
     * @param reg
     * @return
     */
    public List<T> getHojas(String id) {
        DefaultMutableTreeNode nodAux = getNodoDadoID(id);
        return getHojas(nodAux);
    }

    /**
     * devuelve todos los bean "hoja" de esta jerarquéa
     * 
     * @return
     */
    public List<T> getHojas() {
        return getHojas(raiz);
    }

    /**
     * función auxiliar a getRegistroEHijos
     * 
     * @param nod
     * @param lstResultado
     */
    @SuppressWarnings("unchecked")
    private void pueblaRegistroEHijos(DefaultMutableTreeNode nod, List<T> lstResultado) {
        Enumeration<?> hijos = nod.children();
        while (hijos.hasMoreElements()) {
            DefaultMutableTreeNode nodAux = (DefaultMutableTreeNode) hijos.nextElement();
            T bean = (T) nodAux.getUserObject();
            if (treeModel.isLeaf(nodAux))
                lstResultado.add(bean);
            else
                pueblaRegistroEHijos(nodAux, lstResultado);
        }
    }

    /**
     * Contando con que la lista seleccionable "beans" ya existe, pueblo el
     * treeModel y el mapa ids-nodos
     * 
     * @param nodo
     * @param deCero
     */
    private final void pueblaTreeModelYMapa() {
        // mapa de nodos
        mapNodes = new HashMap<String, DefaultMutableTreeNode>();
        for (Object bean : beans.getLista()) {
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(bean);
            String id = OpBeans.getId(bean);
            mapNodes.put(id, nodo);
        }
        mapNodes.put(null, raiz);
        // árbol
        DefaultMutableTreeNode raiz = getRaiz();
        treeModel = new DefaultTreeModel(raiz);
        raiz.removeAllChildren();
        cuelgaHijos(raiz);
        treeModel.nodeStructureChanged((DefaultMutableTreeNode) treeModel.getRoot());
    }

    /**
     * Debe devolverne un nodo raíz (cada vez una instancia nueva) contentiendo
     * valores razonables en la clave y nada (-1) en las columnas "padre"
     * 
     * @return
     */
    protected DefaultMutableTreeNode getRaiz() {
        return raiz;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public Map<String, DefaultMutableTreeNode> getMapNodos() {
        return mapNodes;
    }

    public boolean esAncestro(String idAncestro, String idDescendiente) {
        DefaultMutableTreeNode nodoAncestro = getNodoDadoID(idAncestro);
        DefaultMutableTreeNode nodoDescendiente = getNodoDadoID(idDescendiente);
        return nodoAncestro.isNodeAncestor(nodoDescendiente);
    }

    public boolean esDescendiente(String idAncestro, String idDescendiente) {
        DefaultMutableTreeNode nodoAncestro = getNodoDadoID(idAncestro);
        DefaultMutableTreeNode nodoDescendiente = getNodoDadoID(idDescendiente);
        return nodoDescendiente.isNodeDescendant(nodoAncestro);
    }

    /**
     * Agrega al nodo todos los registros de la lista que tengan el "padre"
     * coincidente con su id.
     * 
     * @param nod
     */
    @SuppressWarnings("unchecked")
    protected void cuelgaHijos(DefaultMutableTreeNode nod) {
        T beanPadre = (T) nod.getUserObject();
        String idPadre = OpBeans.getId(beanPadre);
        for (Object bean : beans.getLista()) {
            TipoJerarquico tij=(TipoJerarquico) bean;
            if (beanPadre.equals(bean)){
                continue;
            }
            
            String idPadreCandidato = OpBeans.getPropiedad(bean, propiedadPadre);
            String idBean = OpBeans.getId(bean);
            if ((idPadreCandidato != null && idPadreCandidato.equals(idPadre))
                    || (idPadreCandidato == null && idPadre == null)) {
                DefaultMutableTreeNode hijo = mapNodes.get(idBean);
                treeModel.insertNodeInto(hijo, nod, nod.getChildCount());
                cuelgaHijos(hijo);
            }
        }
    }

}
