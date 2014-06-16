package kalos.utilidades.abms;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;

import javax.swing.JComponent;

public class ManejoFocosArray extends FocusTraversalPolicy{

	    private JComponent[] focos;

		public void setArrayFocos(JComponent[] focos) {
			this.focos = focos;
		}

		public Component getFirstComponent(Container focusCycleRoot) {
			if (focos != null && focos.length > 0)
				return focos[0];
			else
				return null;
		}

		public Component getLastComponent(Container focusCycleRoot) {
			if (focos != null && focos.length > 0)
				return focos[focos.length - 1];
			else
				return null;
		}

		public Component getDefaultComponent(Container focusCycleRoot) {
			if (focos != null && focos.length > 0)
				return focos[0];
			else
				return null;
		}

		public Component getComponentAfter(Container cont, Component comp) {
			if (focos != null && focos.length > 0) {
				for (int i = 0; i < focos.length; i++) {
					if (comp == focos[i]) {
						if (i < focos.length - 1) {
							return focos[i + 1];
						} else if (comp == focos[focos.length - 1]) {
							return focos[0];
						}
					}
				}
			}
			return null;
		}

		public Component getComponentBefore(Container cont, Component comp) {
			if (focos != null && focos.length > 0) {
				for (int i = 0; i < focos.length; i++) {
					if (comp == focos[i]) {
						if (i > 0)
							return focos[i - 1];
						else if (i == 0)
							return focos[focos.length - 1];
					}
				}
			}
			return null;
		}


}
