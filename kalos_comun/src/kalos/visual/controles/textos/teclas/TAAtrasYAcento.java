package kalos.visual.controles.textos.teclas;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Mariana Esplugas and Gonzalo Díaz
 * @version 1.0
 */

import static kalos.enumeraciones.uLetras.cEspacio;
import static kalos.enumeraciones.uLetras.cIota;
import static kalos.enumeraciones.uLetras.cIotaAgudo;
import static kalos.enumeraciones.uLetras.cIotaCorta;
import static kalos.enumeraciones.uLetras.cIotaLarga;
import static kalos.enumeraciones.uLetras.cLarga;
import static kalos.enumeraciones.uLetras.cMenos;
import static kalos.enumeraciones.uLetras.cUpsilon;
import static kalos.enumeraciones.uLetras.cUpsilonAgudo;
import static kalos.enumeraciones.uLetras.cUpsilonCorta;
import static kalos.enumeraciones.uLetras.cUpsilonLarga;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.text.TextAction;

import kalos.enumeraciones.EfectoTecleado;
import kalos.enumeraciones.TipoLetraBasico;
import kalos.operaciones.OpLetrasUnicode;

import org.apache.log4j.Logger;


/**
 * Acción que se usa para la adición signos diacríticos a letras ya escritas
 * 
 */
public class TAAtrasYAcento extends TextAction {

	Logger logger = Logger.getLogger(this.getClass().getName());

	public static EfectoTecleado cadenaACodigoArbitrario(String cadena) {
		if (cadena.equals("/")) {
			return EfectoTecleado.AcentoAgudo;
		} else if (cadena.equals("\\")) {
			return EfectoTecleado.AcentoGrave;
		} else if (cadena.equals("=")) {
			return EfectoTecleado.AcentoCircunflejo;
		} else if (cadena.equals("_")) {
			return EfectoTecleado.SignoLarga;
		} else if (cadena.equals(")")) {
			return EfectoTecleado.EspirituSuave;
		} else if (cadena.equals("(")) {
			return EfectoTecleado.EspirituAspero;
		} else if (cadena.equals("|")) {
			return EfectoTecleado.IotaSuscripta;
		} else if (cadena.equals("+")) {
			return EfectoTecleado.Dieresis;
		} else {
			return null;
		}
	}

	private EfectoTecleado codigo;

	private JTextField m_txt;

	public TAAtrasYAcento(JTextField txt, EfectoTecleado efecto) {
		super("");
		this.codigo = efecto;
		m_txt = txt;
	}

	public void actionPerformed(ActionEvent e) {
		StringBuffer texto = new StringBuffer(m_txt.getText());

		int iStart = m_txt.getSelectionStart(), iEnd = m_txt.getSelectionEnd();
		int iPos = m_txt.getCaretPosition();
		if (iStart != iEnd)
			return; // no estoy interesado en poner acentos si hay una selección
		if (iPos > 0) {
			char cUlt = texto.charAt(iPos - 1);
			if (cUlt != cEspacio & cUlt != cMenos) {
				switch (codigo) {
				case AcentoAgudo:
					if (OpLetrasUnicode.getTipoLetraBasico(cUlt) == TipoLetraBasico.enuVocal) {
						char acentuado = OpLetrasUnicode.acentoAgudoLetra(cUlt);
						texto.setCharAt(iPos - 1, acentuado);
						m_txt.setText(texto.toString());
						m_txt.setCaretPosition(iPos);
						return;
					}
					break;
				case EspirituAspero:
					if (OpLetrasUnicode.getTipoLetraBasico(cUlt) == TipoLetraBasico.enuVocal) {
						char aspero = OpLetrasUnicode.espirituAsperoCaracter(cUlt);
						texto.setCharAt(iPos - 1, aspero);
						m_txt.setText(texto.toString());
						m_txt.setCaretPosition(iPos);
						return;
					}
					break;
				case EspirituSuave:
					if (OpLetrasUnicode.getTipoLetraBasico(cUlt) == TipoLetraBasico.enuVocal) {
						char suave = OpLetrasUnicode.espirituSuaveCaracter(cUlt);
						texto.setCharAt(iPos - 1, suave);
						m_txt.setText(texto.toString());
						m_txt.setCaretPosition(iPos);
						return;
					}
					break;
				case IotaSuscripta:
					if (OpLetrasUnicode.getTipoLetraBasico(cUlt) == TipoLetraBasico.enuVocal) {
						char subscripto = OpLetrasUnicode.subscribeLetra(cUlt);
						texto.setCharAt(iPos - 1, subscripto);
						m_txt.setText(texto.toString());
						m_txt.setCaretPosition(iPos);
						return;
					}
					break;
				case Retroceso:
					texto.delete(iPos - 1, texto.length());
					m_txt.setText(texto.toString());
					m_txt.setCaretPosition(iPos);
					return;
				case AcentoCircunflejo:
					if (OpLetrasUnicode.getTipoLetraBasico(cUlt) == TipoLetraBasico.enuVocal) {
						char circunflejo = OpLetrasUnicode.acentoCircunflejoLetra(cUlt);
						texto.setCharAt(iPos - 1, circunflejo);
						m_txt.setText(texto.toString());
						m_txt.setCaretPosition(iPos);
						return;
					}
					break;
				case AcentoGrave:
					if (OpLetrasUnicode.getTipoLetraBasico(cUlt) == TipoLetraBasico.enuVocal) {
						char grave = OpLetrasUnicode.acentoGraveLetra(cUlt);
						texto.setCharAt(iPos - 1, grave);
						m_txt.setText(texto.toString());
						m_txt.setCaretPosition(iPos);
						return;
					}
					break;
				case Dieresis:
					if (cUlt == cIota | cUlt == cIotaLarga | cUlt == cIotaCorta | cUlt == cIotaAgudo | cUlt == cUpsilon
							| cUlt == cUpsilonLarga | cUlt == cUpsilonCorta | cUlt == cUpsilonAgudo) {
						char dieresis = OpLetrasUnicode.dieresisLetra(cUlt);
						texto.setCharAt(iPos - 1, dieresis);
						m_txt.setText(texto.toString());
						m_txt.setCaretPosition(iPos);
						return;
					}
					break;
				case SignoLarga:
					// sólo puede agregarse a lo último, de otra manera el ses
					// sobrescrito
					// causa problemas
					texto.append(cLarga);
					m_txt.setText(texto.toString());
					m_txt.setCaretPosition(texto.length());
					return;
				}

			}
		}
	}
}
