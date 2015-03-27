package com.kalos.visual.controles.textos.alternable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.uLetras;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.menues.FabricaMenues;
import com.kalos.visual.controles.textos.teclas.ManejaInputMap;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Control de texto que permite ingresar una palabra en griego, con o sin
 * diacr�ticos. Permite especificar si se debe hacer una correspondencia exacta
 * o parcial (principio, medio, fin) del texto ingresado
 */

public class TextoAlternable extends JPanel {
    /**
     * txtSinNada se usa cuando se emplea el teclado de sistema.
     * txtConDiacriticos se usa cuando se quiere insertar diacr�ticos
     * txtSinDiacriticos se usa cuando no se desea insertr diacr�ticos
     */
    private JTextField texto = new JTextField();
    private boolean esBeta;
    private boolean contieneError = false;
    private JComboBox cmbPosicion = new JComboBox();
    private boolean permitePosicion;
    private JPanel elPanel; // el panel en el que se dibuja todo
    private JToggleButton beta = new JToggleButton(Recursos.cargador.getImagen("a_arriba.gif"));
    private JToggleButton alfa = new JToggleButton(Recursos.cargador.getImagen("alfa.gif"));

    /**
     * cadena completa �nica, considerando que la inserci�n unicode se hace
     * cargo de largas y breves
     */
    private String cadenaCompleta = null;

    public TextoAlternable(boolean permitePosicion) {
	setLayout(new BorderLayout());
	if (permitePosicion) {
	    this.permitePosicion = permitePosicion;
	    cmbPosicion.addItem(Recursos.getCadena("exacto"));
	    cmbPosicion.addItem(Recursos.getCadena("principio"));
	    cmbPosicion.addItem(Recursos.getCadena("medio"));
	    cmbPosicion.addItem(Recursos.getCadena("fin"));
	}
	ButtonGroup bug = new ButtonGroup();
	bug.add(alfa);
	bug.add(beta);
	alfa.setPreferredSize(new Dimension(22, 22));
	beta.setPreferredSize(new Dimension(22, 22));
	alfa.setSelected(true);
	esBeta = false;

	texto = new JTextField();
	FabricaMenues.adjuntaMenuCopiarPegar(texto, true, true);

	// chkDiacriticos.setSelected(Configuracion.usaDiacriticosPorDefecto());

	texto.getDocument().addDocumentListener(new MyDocumentListener());

	alfa.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ev) {
		esBeta = false;
		alfa.setIcon(Recursos.cargador.getImagen("alfa.gif"));
		beta.setIcon(Recursos.cargador.getImagen("a_arriba.gif"));
		texto.setFont(Configuracion.getFont());
		conversionDeLoPresenteAGriego();
		ManejaInputMap.asociaTeclas(texto);
	    }
	});

	beta.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ev) {
		esBeta = true;
		alfa.setIcon(Recursos.cargador.getImagen("alfa_arriba.gif"));
		beta.setIcon(Recursos.cargador.getImagen("a.gif"));
		texto.setFont(getParent().getFont());
		conversionDeLoPresenteABeta();
		texto.setInputMap(0, new JTextField().getInputMap());
	    }
	});

	// texto.addFocusListener(new FocusAdapter(){
	// public void focusLost(FocusEvent ev){
	// correccionAutomatica();
	// }
	// });

	inicializaControles();
	// if (!Configuracion.getTieneTecladoDeSistema()){
	ManejaInputMap.asociaTeclas(texto);
	// }

    }

    private void evaluaCambio() {
	if (!esBeta) { // paso de unicode a completo
	    try {
		String cadenaUnicodeCompuesta = texto.getText();
		cadenaCompleta = OpPalabras.strUnicodeACompleto(cadenaUnicodeCompuesta);
		desMarcaError();
		beta.setEnabled(true);
		alfa.setEnabled(true);
	    } catch (Exception ex) {
		cadenaCompleta = null;
		marcaError();
		beta.setEnabled(false);
		alfa.setEnabled(false);
	    }
	} else { // paso de beta a completo
	    try {
		String cadenaBeta = texto.getText().toUpperCase();
		cadenaCompleta = OpPalabras.strBetaACompleto(cadenaBeta);
		desMarcaError();
		beta.setEnabled(true);
		alfa.setEnabled(true);
	    } catch (Exception ex) {
		cadenaCompleta = null;
		marcaError();
		beta.setEnabled(false);
		alfa.setEnabled(false);
	    }
	}
    }

    /**
     * agrega los controles al panel. El texto que se decide agregar est� dado
     * por la variable global "elTexto"
     *
     */
    private void inicializaControles() {
	if (elPanel != null)
	    remove(elPanel);

	FormLayout layout = new FormLayout("pref:grow(1), 3dlu, pref, 3dlu, pref, 9dlu, pref ", // cols
		"15dlu" // rows
	);

	PanelBuilder builder = new PanelBuilder(layout);
	// builder.setDefaultDialogBorder();
	CellConstraints cc = new CellConstraints();

	texto.setFont(Configuracion.getFont());

	// en los "looks and feels" Kunststoff y Aqua, cuanto inserto una
	// may�scula
	// con esp�ritu y acento al principio de la palabra, los diacr�ticos
	// quedan un poco comidos.
	// La clase TextUIBasica soluciona el problema para los looks and feels
	// b�sicos, pero un intento similar para Kunststoff falla.
	texto.setUI(new TextUIBasica());

	builder.add(texto, cc.xy(1, 1));
	builder.add(beta, cc.xy(3, 1));
	builder.add(alfa, cc.xy(5, 1));

	// el combo de posici�n depende de un par�metro
	if (permitePosicion) {
	    builder.add(cmbPosicion, cc.xy(7, 1));
	}

	elPanel = builder.getPanel();
	add(elPanel, BorderLayout.CENTER);

	revalidate();
	repaint();

    }

    /**
     * Se activa al parder el foco, y realiza algunas correcciones
     * autom�ticamente
     */
    public void correccionAutomatica() {
	StringBuffer unicode = new StringBuffer(getText());
	if (unicode.length() == 0)
	    return;
	// corrijo sigma final
	if (unicode.charAt(unicode.length() - 1) == uLetras.cSigma) {
	    unicode.setCharAt(unicode.length() - 1, uLetras.cSigmaFinal);
	    setText(unicode.toString());
	}
    }

    public JTextField getJTextField() {
	return texto;
    }

    public String getText() {
	return getJTextField().getText();
    }

    public void setText(String text) {
	texto.setText(text);
    }

    public LugarSubcadena getPosicion() {
	if (!permitePosicion)
	    return LugarSubcadena.Exacto;
	else {
	    switch (cmbPosicion.getSelectedIndex()) {
	    case 1:
		return LugarSubcadena.Principio;
	    case 2:
		return LugarSubcadena.Medio;
	    default: // 3
		return LugarSubcadena.Fin;
	    }
	}
    }

    private void conversionDeLoPresenteAGriego() {
	String cadenaUnicode = OpPalabras.strCompletoAUnicode(cadenaCompleta);
	texto.setText(cadenaUnicode);
    }

    private void conversionDeLoPresenteABeta() {
	String cadenaBetaKalos = OpPalabras.strCompletoABeta(cadenaCompleta);
	String cadenaBeta = OpPalabras.strBetaKalosABeta(cadenaBetaKalos);
	texto.setText(cadenaBeta);
    }

    public String getCadenaCompleta() {
	return cadenaCompleta;
    }

    public void setCadenaCompleta(String cadenaCompleta) {
	this.cadenaCompleta = cadenaCompleta;
    }

    public void marcaError() {
	texto.setBackground(Color.red);
	contieneError = true;
    }

    public void desMarcaError() {
	texto.setBackground(Color.white);
	contieneError = false;
    }

    public boolean contieneError() {
	return contieneError;
    }

    class MyDocumentListener implements DocumentListener {
	public void insertUpdate(DocumentEvent e) {
	    evaluaCambio();
	}

	public void removeUpdate(DocumentEvent e) {
	    evaluaCambio();
	}

	public void changedUpdate(DocumentEvent e) {
	}

    }

}