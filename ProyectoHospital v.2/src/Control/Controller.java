package Control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import DAO.EnfermeroDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Modelo.CampoMedico;
import Modelo.Enfermero;
import Modelo.Habitacion;
import Modelo.Medico;
import Modelo.Paciente;
import Modelo.Pasillo;
import Vista.MainFrame;
import Vista.Asignar.AsignarEnfermeroPaciente;
import Vista.Asignar.AsignarMedicoPaciente;
import Vista.Editar.EditarEnfermero;
import Vista.Editar.EditarMedico;
import Vista.Editar.EditarPaciente;
import Vista.Eliminar.EliminarEnfermero;
import Vista.Eliminar.EliminarMedico;
import Vista.Eliminar.EliminarPaciente;
import Vista.Insertar.InsertarEnfermero;
import Vista.Insertar.InsertarMedico;
import Vista.Insertar.InsertarPaciente;

public class Controller implements ActionListener, WindowListener, KeyListener {
	
	public JTable tablaUno, tablaDos, tablaTres, tablaParaEditar;
	public JTextField buscarTextField;
	public JDialog ventana;
	
	public Controller() {
		
	}
	
	public Controller(JDialog ventana) {
		this.ventana = ventana;
	}
	
	public Controller(JTextField buscarTextField, JTable tablaUno, JTable tablaDos, JTable tablaTres) {
		this.buscarTextField = buscarTextField;
		this.tablaUno = tablaUno;
		this.tablaDos = tablaDos;
		this.tablaTres = tablaTres;
	}
	
	public Controller(JTable tablaParaEditar) {
		this.tablaParaEditar = tablaParaEditar;
	}
	
	public Controller(JTable tablaUno, JTable tablaDos, JTable tablaTres) {
		this.tablaUno = tablaUno;
		this.tablaDos = tablaDos;
		this.tablaTres = tablaTres;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		if (evento.equals("abrirInsertarPaciente")) {
			try {
				InsertarPaciente dialog = new InsertarPaciente();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirInsertarEnfermero")) {
			try {
				InsertarEnfermero dialog = new InsertarEnfermero();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirInsertarMedico")) {
			try {
				InsertarMedico dialog = new InsertarMedico();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirEditarPaciente")) {
			try {
				EditarPaciente dialog = new EditarPaciente(tablaParaEditar);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirEditarEnfermero")) {
			try {
				EditarEnfermero dialog = new EditarEnfermero(tablaParaEditar);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirEditarMedico")) {
			try {
				EditarMedico dialog = new EditarMedico(tablaParaEditar);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirEliminarPaciente")) {
			try {
				EliminarPaciente dialog = new EliminarPaciente(tablaParaEditar);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirEliminarEnfermero")) {
			try {
				EliminarEnfermero dialog = new EliminarEnfermero(tablaParaEditar);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirEliminarMedico")) {
			try {
				EliminarMedico dialog = new EliminarMedico(tablaParaEditar);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (evento.equals("abrirAsignarEnfermero")) {
			AsignarEnfermeroPaciente dialog = new AsignarEnfermeroPaciente(tablaParaEditar);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		if (evento.equals("abrirAsignarMedico")) {
			AsignarMedicoPaciente dialog = new AsignarMedicoPaciente(tablaParaEditar);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		if (evento.equals("cancelar")) {
			ventana.dispose();
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		tablaUno.setModel(new PacienteController().rellenarTablaPaciente());
		tablaDos.setModel(new EnfermeroController().rellenarTablaEnfermero());
		tablaTres.setModel(new MedicoController().rellenarTablaMedico());
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String texto = buscarTextField.getText();
		try {
			List<Paciente> pacientes = PacienteDAO.selectSearchingBy(new Paciente(0, texto, texto, "null", new Habitacion(0, new Pasillo(0))));
			PacienteController c1 = new PacienteController();
			tablaUno.setModel(c1.rellenarTablaPacienteBuscado(pacientes));
			List<Enfermero> enfermeros = EnfermeroDAO.selectSearchingBy(new Enfermero(0, texto, texto, 0));
			EnfermeroController c2 = new EnfermeroController();
			tablaDos.setModel(c2.rellenarTablaEnfermeroBuscado(enfermeros));
			List<Medico> medicos = MedicoDAO.selectSearchingBy(new Medico(0, texto, texto, 0, new CampoMedico(0, texto)));
			MedicoController c3 = new MedicoController();
			tablaDos.setModel(c3.rellenarTablaMedicoBuscado(medicos));;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static PopupMenuListener seleccionarClickDerecho(JTable tabla, JPopupMenu popUpMenuTabla) {
		PopupMenuListener seleccionar = new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						int rowAtPoint = tabla
								.rowAtPoint(SwingUtilities.convertPoint(popUpMenuTabla, new Point(0, 0), tabla));
						if (rowAtPoint > -1) {
							tabla.setRowSelectionInterval(rowAtPoint, rowAtPoint);
						}
					}
				});
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub

			}
		};

		return seleccionar;
	}
	
	
	
	

}
