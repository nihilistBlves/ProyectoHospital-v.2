package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.EnfermeroDAO;
import DAO.PacienteDAO;
import Modelo.Enfermero;
import Modelo.Habitacion;
import Modelo.Paciente;
import Modelo.Pasillo;
import Vista.MensajeError;
import Vista.MensajeSuccess;
import sun.management.snmp.jvminstr.JvmThreadInstanceTableMetaImpl;

public class EnfermeroController implements ActionListener {
	
	private JDialog ventana;
	private JTable tabla;
	private JTextField idTextField, nombreTextField, apellidoTextField, nColegiadoTextField;

	public EnfermeroController() {
		
	}
	
	public EnfermeroController(JDialog ventana, JTable tabla) {
		this.ventana = ventana;
		this.tabla = tabla;
	}
	
	public EnfermeroController(JDialog ventana, JTextField nombreTextField, JTextField apellidoTextField, JTextField nColegiadoTextField) {
		this.ventana = ventana;
		this.nombreTextField = nombreTextField;
		this.apellidoTextField = apellidoTextField;
		this.nColegiadoTextField = nColegiadoTextField;
	}
	
	public EnfermeroController(JDialog ventana, JTextField idJTextField, JTextField nombreTextField, JTextField apellidoTextField, JTextField nColegiadoTextField) {
		this.ventana = ventana;
		this.idTextField = idJTextField;
		this.nombreTextField = nombreTextField;
		this.apellidoTextField = apellidoTextField;
		this.nColegiadoTextField = nColegiadoTextField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String opcion = e.getActionCommand();
		if (opcion.equals("insertarEnfermero")) {
			try {
				if (!nombreTextField.getText().contentEquals("") || !apellidoTextField.getText().contentEquals("") || !nColegiadoTextField.getText().contentEquals("")) {
					if (EnfermeroDAO.insertarEnfermero(new Enfermero(0, nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(nColegiadoTextField.getText())))) {
						MensajeSuccess m1 = new MensajeSuccess();
						m1.mostrarMensajeSuccess("Insertado correctamente.");
						ventana.dispose();
					} else {
						MensajeError m2 = new MensajeError();
						m2.mostrarMensajeError("Ha ocurrido un error en la inserción.");
					}
				} else {
					MensajeError m3 = new MensajeError();
					m3.mostrarMensajeError("Hay algún campo sin rellenar. Por favor, rellene todos los campos.");
				}
				
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		if (opcion.equals("editarEnfermero")) {
			try {
				if (!idTextField.getText().contentEquals("") || !nombreTextField.getText().contentEquals("") || !apellidoTextField.getText().contentEquals("") || !nColegiadoTextField.getText().contentEquals("")) {
					if (EnfermeroDAO.editarEnfermero(new Enfermero(Integer.parseInt(idTextField.getText()), nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(nColegiadoTextField.getText())))) {
						MensajeSuccess m1 = new MensajeSuccess();
						m1.mostrarMensajeSuccess("Editado correctamente.");
						ventana.dispose();
					} else {
						MensajeError m2 = new MensajeError();
						m2.mostrarMensajeError("Ha ocurrido un error en la edición.");
					}
				} else {
					MensajeError m3 = new MensajeError();
					m3.mostrarMensajeError("Hay algún campo sin rellenar. Por favor, rellene todos los campos.");
				}
				
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (opcion.equals("eliminarEnfermero")) {
			try {
				if (EnfermeroDAO.eliminarEnfermero(new Enfermero(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()), "", "", 0))) {
					MensajeSuccess m1 = new MensajeSuccess();
					m1.mostrarMensajeSuccess("Eliminado correctamente.");
					ventana.dispose();
				} else {
					MensajeError m2 = new MensajeError();
					m2.mostrarMensajeError("Ha ocurrido un error en la eliminación.");
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public DefaultTableModel rellenarTablaEnfermero() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Enfermero> enfermeros = null; 
		
		String[] columnas = {"ID","Nombre","Apellido","Nº Colegiado"};

		try {
			enfermeros = EnfermeroDAO.selectAllEnfermeros();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String datos[][] = new String[enfermeros.size()][5];
		for (int i=0; i<enfermeros.size(); i++) {
			datos[i][0] = Integer.toString(enfermeros.get(i).getIdEnfermero());
			datos[i][1] = enfermeros.get(i).getNombre();
			datos[i][2] = enfermeros.get(i).getApellido();
			datos[i][3] = Integer.toString(enfermeros.get(i).getNumeroColegiado());
		}
		
		modelo.setDataVector(datos, columnas);
		
		return modelo;
	}
	
	public DefaultTableModel rellenarTablaEnfermeroBuscado(List<Enfermero> enfermerosBuscados) {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Enfermero> enfermeros = enfermerosBuscados; 
		
		String[] columnas = {"ID","Nombre","Apellido","Nº Colegiado"};

		String datos[][] = new String[enfermeros.size()][5];
		for (int i=0; i<enfermeros.size(); i++) {
			datos[i][0] = Integer.toString(enfermeros.get(i).getIdEnfermero());
			datos[i][1] = enfermeros.get(i).getNombre();
			datos[i][2] = enfermeros.get(i).getApellido();
			datos[i][3] = Integer.toString(enfermeros.get(i).getNumeroColegiado());
		}
			
		modelo.setDataVector(datos, columnas);
		
		return modelo;
	}

}
