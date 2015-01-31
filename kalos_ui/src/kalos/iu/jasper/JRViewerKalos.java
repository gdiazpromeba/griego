// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.iu.jasper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

import kalos.recursos.Configuracion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRImageMapRenderer;
import net.sf.jasperreports.engine.JRPrintAnchorIndex;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintImageAreaHyperlink;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.print.JRPrinterAWT;
import net.sf.jasperreports.engine.util.JRClassLoader;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.view.JRHyperlinkListener;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRPrintSaveContributor;

public class JRViewerKalos extends JPanel implements JRHyperlinkListener {
	class _A extends JLabel {

		public void paintComponent(Graphics g1) {
			if (isRenderImage())
				super.paintComponent(g1);
			else
				B.setListaSeleccionable((Graphics2D) g1.create());
		}

		public boolean isRenderImage() {
			return C;
		}

		public void setRenderImage(boolean flag) {
			C = flag;
		}

		private boolean C;
		A B;
		final A A;

		public _A(A a2) {
			A = A.this;
			super();
			B = null;
			B = a2;
		}
	}

	protected class _B extends JPanel implements MouseListener,
			MouseMotionListener {

		public String getToolTipText(MouseEvent mouseevent) {
			String s1 = null;
			JRPrintImageAreaHyperlink jrprintimageareahyperlink = A(mouseevent);
			if (jrprintimageareahyperlink != null)
				s1 = A.B(jrprintimageareahyperlink.getHyperlink());
			if (s1 == null)
				s1 = super.getToolTipText(mouseevent);
			return s1;
		}

		public void mouseDragged(MouseEvent mouseevent) {
		}

		public void mouseMoved(MouseEvent mouseevent) {
			JRPrintImageAreaHyperlink jrprintimageareahyperlink = A(mouseevent);
			if (jrprintimageareahyperlink != null
					&& jrprintimageareahyperlink.getHyperlink()
							.getHyperlinkType() != 1)
				mouseevent.getComponent().setCursor(
						Cursor.getPredefinedCursor(12));
			else
				mouseevent.getComponent().setCursor(Cursor.getDefaultCursor());
		}

		protected JRPrintImageAreaHyperlink A(MouseEvent mouseevent) {
			return A((int) ((float) mouseevent.getX() / A.U),
					(int) ((float) mouseevent.getY() / A.U));
		}

		protected JRPrintImageAreaHyperlink A(int i1, int j1) {
			JRPrintImageAreaHyperlink jrprintimageareahyperlink = null;
			Iterator iterator = B.iterator();
			do {
				if (jrprintimageareahyperlink != null || !iterator.hasNext())
					break;
				JRPrintImageAreaHyperlink jrprintimageareahyperlink1 = (JRPrintImageAreaHyperlink) iterator
						.next();
				if (jrprintimageareahyperlink1.getArea().containsPoint(i1, j1))
					jrprintimageareahyperlink = jrprintimageareahyperlink1;
			} while (true);
			return jrprintimageareahyperlink;
		}

		public void mouseClicked(MouseEvent mouseevent) {
			JRPrintImageAreaHyperlink jrprintimageareahyperlink = A(mouseevent);
			if (jrprintimageareahyperlink != null)
				A.C(jrprintimageareahyperlink.getHyperlink());
		}

		public void mouseEntered(MouseEvent mouseevent) {
		}

		public void mouseExited(MouseEvent mouseevent) {
		}

		public void mousePressed(MouseEvent mouseevent) {
		}

		public void mouseReleased(MouseEvent mouseevent) {
		}

		protected final java.util.List B;
		final A A;

		public _B(Rectangle rectangle, JRImageMapRenderer jrimagemaprenderer) {
			A = A.this;
			super();
			try {
				B = jrimagemaprenderer.getImageAreaHyperlinks(rectangle);
			} catch (JRException jrexception) {
				throw new JRRuntimeException(jrexception);
			}
			addMouseListener(this);
			addMouseMotionListener(this);
		}
	}

	public JRViewerKalos(String s1, boolean flag) throws JRException {
		this(s1, flag, ((Locale) (null)));
	}

	public JRViewerKalos(InputStream inputstream, boolean flag) throws JRException {
		this(inputstream, flag, ((Locale) (null)));
	}

	public JRViewerKalos(JasperPrint jasperprint) {
		this(jasperprint, ((Locale) (null)));
	}

	public JRViewerKalos(String s1, boolean flag, Locale locale) throws JRException {
		this(s1, flag, locale, null);
	}

	public JRViewerKalos(InputStream inputstream, boolean flag, Locale locale)
			throws JRException {
		this(inputstream, flag, locale, null);
	}

	public JRViewerKalos(JasperPrint jasperprint, Locale locale) {
		this(jasperprint, locale, ((ResourceBundle) (null)));
	}

	public JRViewerKalos(String s1, boolean flag, Locale locale,
			ResourceBundle resourcebundle) throws JRException {
		n = 0.5F;
		w = 10F;
		Y = 2;
		C0 = 1;
		S = false;
		A2 = null;
		A5 = null;
		C = 0;
		A4 = 0.0F;
		A3 = null;
		b = 72;
		U = 0.0F;
		q = new DecimalFormat("#.##");
		_ = null;
		d = 0;
		c = 0;
		H = new ArrayList();
		o = new HashMap();
		i = new ArrayList();
		A(locale, resourcebundle);
		E();
		I();
		G();
		A(s1, flag);
		V.setSelectedIndex(Y);
		A();
		addHyperlinkListener(this);
	}

	public JRViewerKalos(InputStream inputstream, boolean flag, Locale locale,
			ResourceBundle resourcebundle) throws JRException {
		n = 0.5F;
		w = 10F;
		Y = 2;
		C0 = 1;
		S = false;
		A2 = null;
		A5 = null;
		C = 0;
		A4 = 0.0F;
		A3 = null;
		b = 72;
		U = 0.0F;
		q = new DecimalFormat("#.##");
		_ = null;
		d = 0;
		c = 0;
		H = new ArrayList();
		o = new HashMap();
		i = new ArrayList();
		A(locale, resourcebundle);
		E();
		I();
		G();
		A(inputstream, flag);
		V.setSelectedIndex(Y);
		A();
		addHyperlinkListener(this);
	}

	public JRViewerKalos(JasperPrint jasperprint, Locale locale,
			ResourceBundle resourcebundle) {
		n = 0.5F;
		w = 10F;
		Y = 2;
		C0 = 1;
		S = false;
		A2 = null;
		A5 = null;
		C = 0;
		A4 = 0.0F;
		A3 = null;
		b = 72;
		U = 0.0F;
		q = new DecimalFormat("#.##");
		_ = null;
		d = 0;
		c = 0;
		H = new ArrayList();
		o = new HashMap();
		i = new ArrayList();
		A(locale, resourcebundle);
		E();
		I();
		G();
		setListaSeleccionable(jasperprint);
		V.setSelectedIndex(Y);
		A();
		addHyperlinkListener(this);
	}

	private void E() {
		b = Toolkit.getDefaultToolkit().getScreenResolution();
	}

	public void clear() {
		A(this);
		A5 = null;
	}

	protected void I() {
	}

	public void addSaveContributor(JRSaveContributor jrsavecontributor) {
		i.add(jrsavecontributor);
	}

	public void removeSaveContributor(JRSaveContributor jrsavecontributor) {
		i.remove(jrsavecontributor);
	}

	public JRSaveContributor[] getSaveContributors() {
		return (JRSaveContributor[]) (JRSaveContributor[]) i
				.toArray(new JRSaveContributor[i.size()]);
	}

	public void addHyperlinkListener(JRHyperlinkListener jrhyperlinklistener) {
		H.add(jrhyperlinklistener);
	}

	public void removeHyperlinkListener(JRHyperlinkListener jrhyperlinklistener) {
		H.remove(jrhyperlinklistener);
	}

	public JRHyperlinkListener[] getHyperlinkListeners() {
		return (JRHyperlinkListener[]) (JRHyperlinkListener[]) H
				.toArray(new JRHyperlinkListener[H.size()]);
	}

	protected void A(Locale locale, ResourceBundle resourcebundle) {
		if (locale != null)
			setLocale(locale);
		else
			setLocale(Locale.getDefault());
		if (resourcebundle == null)
			_ = ResourceBundle.getBundle("net/sf/jasperreports/view/viewer",
					getLocale());
		else
			_ = resourcebundle;
	}

	protected String A(String s1) {
		return _.getString(s1);
	}

	protected void A() {
		String as[] = {
				"net.sf.jasperreports.view.save.JRPdfSaveContributor",
				"net.sf.jasperreports.view.save.JRRtfSaveContributor",
				"net.sf.jasperreports.view.save.JRHtmlSaveContributor",
				"net.sf.jasperreports.view.save.JRSingleSheetXlsSaveContributor",
				"net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor",
				"net.sf.jasperreports.view.save.JRCsvSaveContributor",
				"net.sf.jasperreports.view.save.JRXmlSaveContributor",
				"net.sf.jasperreports.view.save.JREmbeddedImagesXmlSaveContributor" };
		for (int i1 = 0; i1 < as.length; i1++)
			try {
				Class class1 = JRClassLoader.loadClassForName(as[i1]);
				Method method = class1.getMethod("getInstance", (Class[]) null);
				JRSaveContributor jrsavecontributor = (JRSaveContributor) method
						.invoke(null, (Object[]) null);
				i.add(jrsavecontributor);
			} catch (Exception exception) {
			}

	}

	public void gotoHyperlink(JRPrintHyperlink jrprinthyperlink) {
		switch (jrprinthyperlink.getHyperlinkType()) {
		case 1: // '\001'
		default:
			break;

		case 2: // '\002'
			if (cadenaPathSeleccionado()) {
				System.out.println((new StringBuilder())
						.append("Hyperlink reference : ")
						.append(jrprinthyperlink.getHyperlinkReference())
						.toString());
				System.out
						.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;

		case 3: // '\003'
			if (jrprinthyperlink.getHyperlinkAnchor() == null)
				break;
			Map map = A5.getAnchorIndexes();
			JRPrintAnchorIndex jrprintanchorindex = (JRPrintAnchorIndex) map
					.get(jrprinthyperlink.getHyperlinkAnchor());
			if (jrprintanchorindex.getPageIndex() != C) {
				setListaSeleccionable(jrprintanchorindex.getPageIndex());
				preparaColumnas();
			}
			Container container1 = B.getParent();
			if (!(container1 instanceof JViewport))
				break;
			JViewport jviewport1 = (JViewport) container1;
			int j1 = (int) ((float) jrprintanchorindex.getElementAbsoluteX() * U);
			int k1 = (int) ((float) jrprintanchorindex.getElementAbsoluteY() * U);
			int l1 = B.getWidth() - jviewport1.getWidth();
			int i2 = B.getHeight() - jviewport1.getHeight();
			if (j1 < 0)
				j1 = 0;
			if (j1 > l1)
				j1 = l1;
			if (k1 < 0)
				k1 = 0;
			if (k1 > i2)
				k1 = i2;
			jviewport1.setViewPosition(new Point(j1, k1));
			break;

		case 4: // '\004'
			int i1 = C + 1;
			if (jrprinthyperlink.getHyperlinkPage() != null)
				i1 = jrprinthyperlink.getHyperlinkPage().intValue();
			if (i1 < 1 || i1 > A5.getPages().size() || i1 == C + 1)
				break;
			A(i1 - 1);
			preparaColumnas();
			Container container = B.getParent();
			if (container instanceof JViewport) {
				JViewport jviewport = (JViewport) container;
				jviewport.setViewPosition(new Point(0, 0));
			}
			break;

		case 5: // '\005'
			if (cadenaPathSeleccionado()) {
				System.out.println((new StringBuilder())
						.append("Hyperlink reference : ")
						.append(jrprinthyperlink.getHyperlinkReference())
						.toString());
				System.out.println((new StringBuilder())
						.append("Hyperlink anchor    : ")
						.append(jrprinthyperlink.getHyperlinkAnchor())
						.toString());
				System.out
						.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;

		case 6: // '\006'
			if (cadenaPathSeleccionado()) {
				System.out.println((new StringBuilder())
						.append("Hyperlink reference : ")
						.append(jrprinthyperlink.getHyperlinkReference())
						.toString());
				System.out
						.println((new StringBuilder())
								.append("Hyperlink page      : ")
								.append(jrprinthyperlink.getHyperlinkPage())
								.toString());
				System.out
						.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;

		case 7: // '\007'
			if (cadenaPathSeleccionado()) {
				System.out.println((new StringBuilder())
						.append("Hyperlink of type ")
						.append(jrprinthyperlink.getLinkType()).toString());
				System.out
						.println("Implement your own JRHyperlinkListener to manage this type of event.");
			}
			break;
		}
	}

	protected boolean B() {
		int i1;
		if (H == null) {
			i1 = 0;
		} else {
			i1 = H.size();
			if (H.contains(this))
				i1--;
		}
		return i1 == 0;
	}

	private void G() {
		u = new JPanel();
		O = new JButton();
		Q = new JButton();
		a = new JButton();
		T = new JPanel();
		t = new JButton();
		BA = new JButton();
		B5 = new JButton();
		N = new JButton();
		I = new JTextField();
		R = new JPanel();
		z = new JToggleButton();
		AA = new JToggleButton();
		F = new JToggleButton();
		P = new JPanel();
		G = new JButton();
		s = new JButton();
		V = new JComboBox();
		DefaultComboBoxModel defaultcomboboxmodel = new DefaultComboBoxModel();
		for (int i1 = 0; i1 < Z.length; i1++)
			defaultcomboboxmodel.addElement((new StringBuilder()).append("")
					.append(Z[i1]).append("%").toString());

		V.setModel(defaultcomboboxmodel);
		X = new JPanel();
		D = new JScrollPane();
		D.getHorizontalScrollBar().setUnitIncrement(5);
		D.getVerticalScrollBar().setUnitIncrement(5);
		B = new JPanel();
		W = new JPanel();
		m = new JPanel();
		J = new JPanel();
		l = new JPanel();
		k = new JPanel();
		j = new JPanel();
		h = new JPanel();
		r = new JLabel();
		f = new JPanel();
		K = new _A(this);
		p = new JPanel();
		A = new JLabel();
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(450, 150));
		setPreferredSize(new Dimension(450, 150));
		u.setLayout(new FlowLayout(0, 0, 2));
		O.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/save.GIF")));
		O.setToolTipText(buscaPorLetra("save"));
		O.setMargin(new Insets(2, 2, 2, 2));
		O.setMaximumSize(new Dimension(23, 23));
		O.setMinimumSize(new Dimension(23, 23));
		O.setPreferredSize(new Dimension(23, 23));
		O.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.N(actionevent);
			}
		});
		u.add(O);
		Q.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/print.GIF")));
		Q.setToolTipText(buscaPorLetra("print"));
		Q.setMargin(new Insets(2, 2, 2, 2));
		Q.setMaximumSize(new Dimension(23, 23));
		Q.setMinimumSize(new Dimension(23, 23));
		Q.setPreferredSize(new Dimension(23, 23));
		Q.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.E(actionevent);
			}

		});
		u.add(Q);
		a.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/reload.GIF")));
		a.setToolTipText(buscaPorLetra("reload"));
		a.setMargin(new Insets(2, 2, 2, 2));
		a.setMaximumSize(new Dimension(23, 23));
		a.setMinimumSize(new Dimension(23, 23));
		a.setPreferredSize(new Dimension(23, 23));
		a.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.H(actionevent);
			}

		});
		u.add(a);
		T.setMaximumSize(new Dimension(10, 10));
		u.add(T);
		t.setIcon(new ImageIcon(getClass().getResource("/net/sf/jasperreports/view/images/first.GIF")));
		t.setToolTipText(buscaPorLetra("first.page"));
		t.setMargin(new Insets(2, 2, 2, 2));
		t.setMaximumSize(new Dimension(23, 23));
		t.setMinimumSize(new Dimension(23, 23));
		t.setPreferredSize(new Dimension(23, 23));
		t.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.G(actionevent);
			}

		});
		u.add(t);
		BA.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/previous.GIF")));
		BA.setToolTipText(buscaPorLetra("previous.page"));
		BA.setMargin(new Insets(2, 2, 2, 2));
		BA.setMaximumSize(new Dimension(23, 23));
		BA.setMinimumSize(new Dimension(23, 23));
		BA.setPreferredSize(new Dimension(23, 23));
		BA.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.K(actionevent);
			}

		});
		u.add(BA);
		B5.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/next.GIF")));
		B5.setToolTipText(buscaPorLetra("next.page"));
		B5.setMargin(new Insets(2, 2, 2, 2));
		B5.setMaximumSize(new Dimension(23, 23));
		B5.setMinimumSize(new Dimension(23, 23));
		B5.setPreferredSize(new Dimension(23, 23));
		B5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.L(actionevent);
			}

		});
		u.add(B5);
		N.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/last.GIF")));
		N.setToolTipText(buscaPorLetra("last.page"));
		N.setMargin(new Insets(2, 2, 2, 2));
		N.setMaximumSize(new Dimension(23, 23));
		N.setMinimumSize(new Dimension(23, 23));
		N.setPreferredSize(new Dimension(23, 23));
		N.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.D(actionevent);
			}

		});
		u.add(N);
		I.setToolTipText(buscaPorLetra("go.to.page"));
		I.setMaximumSize(new Dimension(40, 23));
		I.setMinimumSize(new Dimension(40, 23));
		I.setPreferredSize(new Dimension(40, 23));
		I.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.M(actionevent);
			}

		});
		u.add(I);
		R.setMaximumSize(new Dimension(10, 10));
		u.add(R);
		z.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/actualsize.GIF")));
		z.setToolTipText(buscaPorLetra("actual.size"));
		z.setMargin(new Insets(2, 2, 2, 2));
		z.setMaximumSize(new Dimension(23, 23));
		z.setMinimumSize(new Dimension(23, 23));
		z.setPreferredSize(new Dimension(23, 23));
		z.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.setListaSeleccionable(actionevent);
			}

		});
		u.add(z);
		AA.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/fitpage.GIF")));
		AA.setToolTipText(buscaPorLetra("fit.page"));
		AA.setMargin(new Insets(2, 2, 2, 2));
		AA.setMaximumSize(new Dimension(23, 23));
		AA.setMinimumSize(new Dimension(23, 23));
		AA.setPreferredSize(new Dimension(23, 23));
		AA.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.F(actionevent);
			}

		});
		u.add(AA);
		F.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/fitwidth.GIF")));
		F.setToolTipText(buscaPorLetra("fit.width"));
		F.setMargin(new Insets(2, 2, 2, 2));
		F.setMaximumSize(new Dimension(23, 23));
		F.setMinimumSize(new Dimension(23, 23));
		F.setPreferredSize(new Dimension(23, 23));
		F.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.J(actionevent);
			}
		});
		u.add(F);
		P.setMaximumSize(new Dimension(10, 10));
		u.add(P);
		G.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/zoomin.GIF")));
		G.setToolTipText(buscaPorLetra("zoom.in"));
		G.setMargin(new Insets(2, 2, 2, 2));
		G.setMaximumSize(new Dimension(23, 23));
		G.setMinimumSize(new Dimension(23, 23));
		G.setPreferredSize(new Dimension(23, 23));
		G.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.C(actionevent);
			}

		});
		u.add(G);
		s.setIcon(new ImageIcon(getClass().getResource(
				"/net/sf/jasperreports/view/images/zoomout.GIF")));
		s.setToolTipText(buscaPorLetra("zoom.out"));
		s.setMargin(new Insets(2, 2, 2, 2));
		s.setMaximumSize(new Dimension(23, 23));
		s.setMinimumSize(new Dimension(23, 23));
		s.setPreferredSize(new Dimension(23, 23));
		s.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.B(actionevent);
			}

		});
		u.add(s);
		V.setEditable(true);
		V.setToolTipText(buscaPorLetra("zoom.ratio"));
		V.setMaximumSize(new Dimension(80, 23));
		V.setMinimumSize(new Dimension(80, 23));
		V.setPreferredSize(new Dimension(80, 23));
		V.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionevent) {
				A.I(actionevent);
			}

		});
		V.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent itemevent) {
				A.setListaSeleccionable(itemevent);
			}

		});
		u.add(V);
		add(u, "North");
		X.setLayout(new BorderLayout());
		X.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent componentevent) {
				A.setListaSeleccionable(componentevent);
			}

		});
		D.setHorizontalScrollBarPolicy(32);
		D.setVerticalScrollBarPolicy(22);
		B.setLayout(new GridBagLayout());
		W.setLayout(new BorderLayout());
		W.setMinimumSize(new Dimension(100, 100));
		W.setPreferredSize(new Dimension(100, 100));
		m.setLayout(new GridBagLayout());
		m.setMinimumSize(new Dimension(100, 120));
		m.setPreferredSize(new Dimension(100, 120));
		J.setLayout(null);
		J.setMinimumSize(new Dimension(5, 5));
		J.setPreferredSize(new Dimension(5, 5));
		J.setOpaque(false);
		J.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent mouseevent) {
				A.D(mouseevent);
			}

			public void mouseReleased(MouseEvent mouseevent) {
				A.setListaSeleccionable(mouseevent);
			}

		});
		J.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent mouseevent) {
				A.B(mouseevent);
			}

		});
		GridBagConstraints gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = 0;
		gridbagconstraints.gridy = 0;
		gridbagconstraints.gridwidth = 2;
		gridbagconstraints.gridheight = 2;
		gridbagconstraints.fill = 1;
		m.add(J, gridbagconstraints);
		l.setBackground(Color.gray);
		l.setMinimumSize(new Dimension(5, 5));
		l.setPreferredSize(new Dimension(5, 5));
		gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = 2;
		gridbagconstraints.gridy = 1;
		gridbagconstraints.fill = 3;
		m.add(l, gridbagconstraints);
		k.setMinimumSize(new Dimension(5, 5));
		k.setPreferredSize(new Dimension(5, 5));
		gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = 0;
		gridbagconstraints.gridy = 2;
		m.add(k, gridbagconstraints);
		j.setBackground(Color.gray);
		j.setMinimumSize(new Dimension(5, 5));
		j.setPreferredSize(new Dimension(5, 5));
		gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = 1;
		gridbagconstraints.gridy = 2;
		gridbagconstraints.fill = 2;
		m.add(j, gridbagconstraints);
		h.setBackground(Color.gray);
		h.setMinimumSize(new Dimension(5, 5));
		h.setPreferredSize(new Dimension(5, 5));
		r.setText("jLabel1");
		h.add(r);
		gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = 2;
		gridbagconstraints.gridy = 2;
		m.add(h, gridbagconstraints);
		f.setMinimumSize(new Dimension(5, 5));
		f.setPreferredSize(new Dimension(5, 5));
		gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = 2;
		gridbagconstraints.gridy = 0;
		m.add(f, gridbagconstraints);
		K.setBackground(Color.white);
		K.setBorder(new LineBorder(new Color(0, 0, 0)));
		K.setOpaque(true);
		gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = 0;
		gridbagconstraints.gridy = 0;
		gridbagconstraints.gridwidth = 2;
		gridbagconstraints.gridheight = 2;
		gridbagconstraints.fill = 1;
		gridbagconstraints.weightx = 1.0D;
		gridbagconstraints.weighty = 1.0D;
		m.add(K, gridbagconstraints);
		W.add(m, "Center");
		gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.insets = new Insets(5, 5, 5, 5);
		B.add(W, gridbagconstraints);
		D.setViewportView(B);
		X.add(D, "Center");
		add(X, "Center");
		p.setLayout(new FlowLayout(1, 0, 0));
		A.setFont(new Font("Dialog", 1, 10));
		A.setText("Page i of n");
		p.add(A);
		add(p, "South");
	}

	void M(ActionEvent actionevent) {
		try {
			int i1 = Integer.parseInt(I.getText());
			if (i1 != C + 1 && i1 > 0 && i1 <= A5.getPages().size()) {
				A(i1 - 1);
				preparaColumnas();
			}
		} catch (NumberFormatException numberformatexception) {
		}
	}

	void A(ItemEvent itemevent) {
		z.setSelected(false);
		AA.setSelected(false);
		F.setSelected(false);
	}

	void A(ComponentEvent componentevent) {
		if (AA.isSelected()) {
			H();
			AA.setSelected(true);
		} else if (F.isSelected()) {
			A(((float) B.getVisibleRect().getWidth() - 20F)
					/ (float) A5.getPageWidth());
			F.setSelected(true);
		}
	}

	void A(ActionEvent actionevent) {
		if (z.isSelected()) {
			AA.setSelected(false);
			F.setSelected(false);
			V.setSelectedIndex(-1);
			setZoomRatio(1.0F);
			z.setSelected(true);
		}
	}

	void J(ActionEvent actionevent) {
		if (F.isSelected()) {
			z.setSelected(false);
			AA.setSelected(false);
			V.setSelectedIndex(-1);
			A(((float) B.getVisibleRect().getWidth() - 20F)
					/ (float) A5.getPageWidth());
			F.setSelected(true);
		}
	}

	void F(ActionEvent actionevent) {
		if (AA.isSelected()) {
			z.setSelected(false);
			F.setSelected(false);
			V.setSelectedIndex(-1);
			H();
			AA.setSelected(true);
		}
	}

	void N(ActionEvent actionevent) {
		JFileChooser jfilechooser = new JFileChooser();
		for (int i1 = 0; i1 < i.size(); i1++)
			jfilechooser.addChoosableFileFilter((JRSaveContributor) i.get(i1));

		if (i.size() > 0)
			jfilechooser.setFileFilter((JRSaveContributor) i.get(0));
		int j1 = jfilechooser.showSaveDialog(this);
		if (j1 == 0) {
			FileFilter filefilter = jfilechooser.getFileFilter();
			File file = jfilechooser.getSelectedFile();
			Object obj = null;
			if (filefilter instanceof JRSaveContributor) {
				obj = (JRSaveContributor) filefilter;
			} else {
				int k1 = 0;
				do {
					if (obj != null || k1 >= i.size())
						break;
					obj = (JRSaveContributor) i.get(k1++);
					if (!((JRSaveContributor) (obj)).accept(file))
						obj = null;
				} while (true);
				if (obj == null)
					obj = new JRPrintSaveContributor();
			}
			try {
				((JRSaveContributor) (obj)).save(A5, file);
			} catch (JRException jrexception) {
				jrexception.printStackTrace();
				JOptionPane.showMessageDialog(this, buscaPorLetra("error.saving"));
			}
		}
	}

	void B(MouseEvent mouseevent) {
		Container container = B.getParent();
		if (container instanceof JViewport) {
			JViewport jviewport = (JViewport) container;
			Point point = jviewport.getViewPosition();
			int i1 = point.x - (mouseevent.getX() - d);
			int j1 = point.y - (mouseevent.getY() - c);
			int k1 = B.getWidth() - jviewport.getWidth();
			int l1 = B.getHeight() - jviewport.getHeight();
			if (i1 < 0)
				i1 = 0;
			if (i1 > k1)
				i1 = k1;
			if (j1 < 0)
				j1 = 0;
			if (j1 > l1)
				j1 = l1;
			jviewport.setViewPosition(new Point(i1, j1));
		}
	}

	void A(MouseEvent mouseevent) {
		J.setCursor(new Cursor(0));
	}

	void D(MouseEvent mouseevent) {
		J.setCursor(new Cursor(13));
		d = mouseevent.getX();
		c = mouseevent.getY();
	}

	void E(ActionEvent actionevent) {
		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					JasperPrintManager.printReport(A.A5, true);
				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(A, Configuracion. A.setListaSeleccionable("error.printing"));
				}
			}

		});
		thread.start();
	}

	void D(ActionEvent actionevent) {
		setListaSeleccionable(A5.getPages().size() - 1);
		preparaColumnas();
	}

	void L(ActionEvent actionevent) {
		A(C + 1);
		preparaColumnas();
	}

	void K(ActionEvent actionevent) {
		A(C - 1);
		preparaColumnas();
	}

	void G(ActionEvent actionevent) {
		A(0);
		preparaColumnas();
	}

	void H(ActionEvent actionevent) {
		if (C0 == 1) {
			try {
				A(A2, S);
			} catch (JRException jrexception) {
				jrexception.printStackTrace();
				A5 = null;
				A(0);
				preparaColumnas();
				JOptionPane.showMessageDialog(this, buscaPorLetra("error.loading"));
			}
			A4 = 0.0F;
			U = 0.0F;
			setZoomRatio(1.0F);
		}
	}

	void C(ActionEvent actionevent) {
		z.setSelected(false);
		AA.setSelected(false);
		F.setSelected(false);
		int i1 = (int) (100F * C());
		int j1 = Arrays.binarySearch(Z, i1);
		if (j1 < 0)
			setZoomRatio((float) Z[-j1 - 1] / 100F);
		else if (j1 < V.getModel().getSize() - 1)
			setZoomRatio((float) Z[j1 + 1] / 100F);
	}

	void B(ActionEvent actionevent) {
		z.setSelected(false);
		AA.setSelected(false);
		F.setSelected(false);
		int i1 = (int) (100F * C());
		int j1 = Arrays.binarySearch(Z, i1);
		if (j1 > 0)
			setZoomRatio((float) Z[j1 - 1] / 100F);
		else if (j1 < -1)
			setZoomRatio((float) Z[-j1 - 2] / 100F);
	}

	void I(ActionEvent actionevent) {
		float f1 = C();
		if (f1 < n)
			f1 = n;
		if (f1 > w)
			f1 = w;
		setZoomRatio(f1);
	}

	void C(MouseEvent mouseevent) {
		JPanel jpanel = (JPanel) mouseevent.getSource();
		JRPrintHyperlink jrprinthyperlink = (JRPrintHyperlink) o.get(jpanel);
		C(jrprinthyperlink);
	}

	protected void C(JRPrintHyperlink jrprinthyperlink) {
		try {
			Object obj = null;
			for (int i1 = 0; i1 < H.size(); i1++) {
				JRHyperlinkListener jrhyperlinklistener = (JRHyperlinkListener) H
						.get(i1);
				jrhyperlinklistener.gotoHyperlink(jrprinthyperlink);
			}

		} catch (JRException jrexception) {
			jrexception.printStackTrace();
			JOptionPane.showMessageDialog(this, buscaPorLetra("error.hyperlink"));
		}
	}

	private void A(int i1) {
		C = i1;
		if (A5 != null && A5.getPages() != null && A5.getPages().size() > 0) {
			t.setEnabled(C > 0);
			BA.setEnabled(C > 0);
			B5.setEnabled(C < A5.getPages().size() - 1);
			N.setEnabled(C < A5.getPages().size() - 1);
			I.setEnabled(t.isEnabled() || N.isEnabled());
			I.setText((new StringBuilder()).append("").append(C + 1).toString());
			A.setText(MessageFormat.format(buscaPorLetra("page"), new Object[] {
					new Integer(C + 1), new Integer(A5.getPages().size()) }));
		} else {
			t.setEnabled(false);
			BA.setEnabled(false);
			B5.setEnabled(false);
			N.setEnabled(false);
			I.setEnabled(false);
			I.setText("");
			A.setText("");
		}
	}

	protected void A(String s1, boolean flag) throws JRException {
		if (flag)
			A5 = JRPrintXmlLoader.load(s1);
		else
			A5 = (JasperPrint) JRLoader.loadObject(s1);
		C0 = 1;
		S = flag;
		A2 = s1;
		a.setEnabled(true);
		A(0);
	}

	protected void A(InputStream inputstream, boolean flag) throws JRException {
		if (flag)
			A5 = JRPrintXmlLoader.load(inputstream);
		else
			A5 = (JasperPrint) JRLoader.loadObject(inputstream);
		C0 = 2;
		S = flag;
		a.setEnabled(false);
		A(0);
	}

	protected void A(JasperPrint jasperprint) {
		A5 = jasperprint;
		C0 = 3;
		S = false;
		a.setEnabled(false);
		A(0);
	}

	protected void F() {
		if (A5 == null || A5.getPages() == null || A5.getPages().size() == 0) {
			W.setVisible(false);
			O.setEnabled(false);
			Q.setEnabled(false);
			z.setEnabled(false);
			AA.setEnabled(false);
			F.setEnabled(false);
			G.setEnabled(false);
			s.setEnabled(false);
			V.setEnabled(false);
			if (A5 != null)
				JOptionPane.showMessageDialog(this, buscaPorLetra("no.pages"));
			return;
		}
		W.setVisible(true);
		O.setEnabled(true);
		Q.setEnabled(true);
		z.setEnabled(true);
		AA.setEnabled(true);
		F.setEnabled(true);
		G.setEnabled(A4 < w);
		s.setEnabled(A4 > n);
		V.setEnabled(true);
		Dimension dimension = new Dimension(
				(int) ((float) A5.getPageWidth() * U) + 8,
				(int) ((float) A5.getPageHeight() * U) + 8);
		W.setMaximumSize(dimension);
		W.setMinimumSize(dimension);
		W.setPreferredSize(dimension);
		long l1 = JRProperties
				.getLongProperty("net.sf.jasperreports.viewer.render.buffer.max.size");
		boolean flag;
		if (l1 <= 0L) {
			flag = false;
		} else {
			long l2 = JRPrinterAWT.getImageSize(A5, U);
			flag = l2 <= l1;
		}
		K.setRenderImage(flag);
		if (flag) {
			Object obj = null;
			ImageIcon imageicon = null;
			try {
				Image image = JasperPrintManager.printPageToImage(A5, C, U);
				imageicon = new ImageIcon(image);
			} catch (Exception exception) {
				exception.printStackTrace();
				JOptionPane.showMessageDialog(
						this,
						ResourceBundle.getBundle(
								"net/sf/jasperreports/view/viewer").getString(
								"error.displaying"));
			}
			K.setIcon(imageicon);
		}
		J.removeAll();
		o = new HashMap();
		getListaSeleccionable();
		if (!flag) {
			K.setIcon(null);
			X.validate();
			X.repaint();
		}
	}

	protected void D() {
		java.util.List list = A5.getPages();
		JRPrintPage jrprintpage = (JRPrintPage) list.get(C);
		java.util.List list1 = jrprintpage.getElements();
		if (list1 != null && list1.size() > 0) {
			Iterator iterator = list1.iterator();
			do {
				if (!iterator.hasNext())
					break;
				JRPrintElement jrprintelement = (JRPrintElement) iterator
						.next();
				JRImageMapRenderer jrimagemaprenderer = null;
				if (jrprintelement instanceof JRPrintImage) {
					net.sf.jasperreports.engine.JRRenderable jrrenderable = ((JRPrintImage) jrprintelement)
							.getRenderer();
					if (jrrenderable instanceof JRImageMapRenderer)
						jrimagemaprenderer = (JRImageMapRenderer) jrrenderable;
				}
				boolean flag = jrimagemaprenderer != null;
				JRPrintHyperlink jrprinthyperlink = null;
				if (!flag && (jrprintelement instanceof JRPrintHyperlink))
					jrprinthyperlink = (JRPrintHyperlink) jrprintelement;
				boolean flag1 = jrprinthyperlink != null
						&& jrprinthyperlink.getHyperlinkType() != 1;
				if (flag1 || flag) {
					Object obj;
					if (flag1) {
						obj = new JPanel();
						((JPanel) (obj)).addMouseListener(g);
					} else {
						Rectangle rectangle = new Rectangle(0, 0,
								jrprintelement.getWidth(),
								jrprintelement.getHeight());
						obj = new _B(rectangle, jrimagemaprenderer);
					}
					if (flag1)
						((JPanel) (obj)).setCursor(new Cursor(12));
					((JPanel) (obj)).setLocation(
							(int) ((float) jrprintelement.getX() * U),
							(int) ((float) jrprintelement.getY() * U));
					((JPanel) (obj)).setSize(
							(int) ((float) jrprintelement.getWidth() * U),
							(int) ((float) jrprintelement.getHeight() * U));
					((JPanel) (obj)).setOpaque(false);
					String s1;
					if (flag1)
						s1 = B(jrprinthyperlink);
					else
						s1 = "";
					((JPanel) (obj)).setToolTipText(s1);
					J.add(((Component) (obj)));
					o.put(obj, jrprintelement);
				}
			} while (true);
		}
	}

	protected String B(JRPrintHyperlink jrprinthyperlink) {
		String s1 = jrprinthyperlink.getHyperlinkTooltip();
		if (s1 == null)
			s1 = setListaSeleccionable(jrprinthyperlink);
		return s1;
	}

	protected String A(JRPrintHyperlink jrprinthyperlink) {
		String s1 = null;
		switch (jrprinthyperlink.getHyperlinkType()) {
		default:
			break;

		case 2: // '\002'
			s1 = jrprinthyperlink.getHyperlinkReference();
			break;

		case 3: // '\003'
			if (jrprinthyperlink.getHyperlinkAnchor() != null)
				s1 = (new StringBuilder()).append("#")
						.append(jrprinthyperlink.getHyperlinkAnchor())
						.toString();
			break;

		case 4: // '\004'
			if (jrprinthyperlink.getHyperlinkPage() != null)
				s1 = (new StringBuilder()).append("#page ")
						.append(jrprinthyperlink.getHyperlinkPage()).toString();
			break;

		case 5: // '\005'
			s1 = "";
			if (jrprinthyperlink.getHyperlinkReference() != null)
				s1 = (new StringBuilder()).append(s1)
						.append(jrprinthyperlink.getHyperlinkReference())
						.toString();
			if (jrprinthyperlink.getHyperlinkAnchor() != null)
				s1 = (new StringBuilder()).append(s1).append("#")
						.append(jrprinthyperlink.getHyperlinkAnchor())
						.toString();
			break;

		case 6: // '\006'
			s1 = "";
			if (jrprinthyperlink.getHyperlinkReference() != null)
				s1 = (new StringBuilder()).append(s1)
						.append(jrprinthyperlink.getHyperlinkReference())
						.toString();
			if (jrprinthyperlink.getHyperlinkPage() != null)
				s1 = (new StringBuilder()).append(s1).append("#page ")
						.append(jrprinthyperlink.getHyperlinkPage()).toString();
			break;
		}
		return s1;
	}

	private void A(Container container) {
		Component acomponent[] = container.getComponents();
		if (acomponent != null) {
			for (int i1 = 0; i1 < acomponent.length; i1++)
				if (acomponent[i1] instanceof Container)
					A((Container) acomponent[i1]);

		}
		acomponent = null;
		container.removeAll();
		container = null;
	}

	private float C() {
		float f1 = A4;
		try {
			f1 = q.parse(String.valueOf(V.getEditor().getItem())).floatValue() / 100F;
		} catch (ParseException parseexception) {
		}
		return f1;
	}

	public void setZoomRatio(float f1) {
		if (f1 > 0.0F) {
			V.getEditor().setItem(
					(new StringBuilder()).append(q.format(f1 * 100F))
							.append("%").toString());
			if (A4 != f1) {
				A4 = f1;
				U = (A4 * (float) b) / 72F;
				preparaColumnas();
			}
		}
	}

	private void A(float f1) {
		if (f1 > 0.0F && U != f1) {
			A4 = (f1 * 72F) / (float) b;
			U = f1;
			V.getEditor().setItem(
					(new StringBuilder()).append(q.format(A4 * 100F))
							.append("%").toString());
			preparaColumnas();
		}
	}

	public void setFitWidthZoomRatio() {
		A(((float) B.getVisibleRect().getWidth() - 20F)
				/ (float) A5.getPageWidth());
	}

	public void setFitPageZoomRatio() {
		A(((float) B.getVisibleRect().getHeight() - 20F)
				/ (float) A5.getPageHeight());
	}

	protected void A(Graphics2D graphics2d) {
		try {
			if (A3 == null)
				A3 = new JRGraphics2DExporter();
			else
				A3.reset();
			A3.setParameter(JRExporterParameter.JASPER_PRINT, A5);
			A3.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D,
					graphics2d);
			A3.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(C));
			A3.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO,
					new Float(U));
			A3.setParameter(JRExporterParameter.OFFSET_X, new Integer(1));
			A3.setParameter(JRExporterParameter.OFFSET_Y, new Integer(1));
			A3.exportReport();
		} catch (Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(this, buscaPorLetra("error.displaying"));
		}
	}

	private void H() {
		float f1 = ((float) B.getVisibleRect().getHeight() - 20F)
				/ (float) A5.getPageHeight();
		float f2 = ((float) B.getVisibleRect().getWidth() - 20F)
				/ (float) A5.getPageWidth();
		A(f1 >= f2 ? f2 : f1);
	}

	public static final String M = "net.sf.jasperreports.viewer.render.buffer.max.size";
	private static final int e = 1;
	private static final int E = 2;
	private static final int v = 3;
	public static final int L = 72;
	protected float n;
	protected float w;
	protected int Z[] = { 50, 75, 100, 125, 150, 175, 200, 250, 400, 800 };
	protected int Y;
	private int C0;
	private boolean S;
	private String A2;
	JasperPrint A5;
	private int C;
	private float A4;
	private JRGraphics2DExporter A3;
	private int b;
	protected float U;
	private DecimalFormat q;
	private ResourceBundle _;
	private int d;
	private int c;
	private java.util.List H;
	private Map o;
	private MouseListener g = new MouseAdapter() {

		public void mouseClicked(MouseEvent mouseevent) {
			A.C(mouseevent);
		}

	};
	private java.util.List i;
	protected JToggleButton z;
	protected JButton t;
	protected JToggleButton AA;
	protected JToggleButton F;
	protected JButton N;
	protected JButton B5;
	protected JButton BA;
	protected JButton Q;
	protected JButton a;
	protected JButton O;
	protected JButton G;
	protected JButton s;
	protected JComboBox V;
	private JLabel r;
	private JPanel m;
	private JPanel l;
	private JPanel k;
	private JPanel j;
	private JPanel h;
	private JPanel f;
	private _A K;
	protected JLabel A;
	private JPanel B;
	private JPanel J;
	private JPanel X;
	private JPanel W;
	protected JPanel T;
	protected JPanel R;
	protected JPanel P;
	protected JPanel p;
	private JScrollPane D;
	protected JPanel u;
	protected JTextField I;
}
