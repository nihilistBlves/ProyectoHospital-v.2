package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.HabitacionDAO;
import DAO.PacienteDAO;
import Modelo.Habitacion;
import Modelo.Paciente;
import Modelo.Pasillo;
import Vista.MensajeError;
import Vista.MensajeSuccess;

public class PacienteController implements ActionListener {
	
	private JDialog ventana;
	private JTable tabla;
	private JTextField idTextField, nombreTextField, apellidoTextField, dniTextField;
	private JComboBox<String> habitacionComboBox;
	
	public PacienteController() {
		
	}
	
	public PacienteController(JTable tabla) {
		this.tabla = tabla;
	}
	
	public PacienteController(JDialog ventana, JTable tabla) {
		this.ventana = ventana;
		this.tabla = tabla;
	}
	
	public PacienteController(JDialog ventana, JTextField nombreTextField, JTextField apellidoTextField, JTextField dniTextField, JComboBox<String> habitacionComboBox) {
		this.ventana = ventana;
		this.nombreTextField = nombreTextField;
		this.apellidoTextField = apellidoTextField;
		this.dniTextField = dniTextField;
		this.habitacionComboBox = habitacionComboBox;
	}
	
	public PacienteController(JDialog ventana, JTextField idTextField,JTextField nombreTextField, JTextField apellidoTextField, JTextField dniTextField, JComboBox<String> habitacionComboBox) {
		this.ventana = ventana;
		this.idTextField = idTextField;
		this.nombreTextField = nombreTextField;
		this.apellidoTextField = apellidoTextField;
		this.dniTextField = dniTextField;
		this.habitacionComboBox = habitacionComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String opcion = e.getActionCommand();
		if (opcion.equals("insertarPaciente")) {
			try {
				if (!nombreTextField.getText().contentEquals("") || !apellidoTextField.getText().contentEquals("") || !dniTextField.getText().contentEquals("")) {
					if (PacienteDAO.insertarPaciente(new Paciente(0, nombreTextField.getText(), apellidoTextField.getText(), dniTextField.getText(), new Habitacion(Integer.parseInt(habitacionComboBox.getItemAt(habitacionComboBox.getSelectedIndex())), new Pasillo(0))))) {
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
		if (opcion.equals("editarPaciente")) {
			try {
				if (!idTextField.getText().contentEquals("") || !nombreTextField.getText().contentEquals("") || !apellidoTextField.getText().contentEquals("") || !dniTextField.getText().contentEquals("")) {
					if (PacienteDAO.editarPaciente(new Paciente(Integer.parseInt(idTextField.getText()), nombreTextField.getText(), apellidoTextField.getText(), dniTextField.getText(), new Habitacion(Integer.parseInt(habitacionComboBox.getItemAt(habitacionComboBox.getSelectedIndex())), new Pasillo(0))))) {
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
		if (opcion.equals("eliminarPaciente")) {
			try {
				if (PacienteDAO.eliminarPaciente(new Paciente(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()), "", "", "", new Habitacion(0, new Pasillo(0))))) {
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
	
	public DefaultTableModel rellenarTablaPaciente() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Paciente> pacientes = null; 
		
		String[] columnas = {"ID","Nombre","Apellido","DNI","Habitacion"};

		try {
			pacientes = PacienteDAO.selectAllPacientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String datos[][] = new String[pacientes.size()][5];
		for (int i=0; i<pacientes.size(); i++) {
			datos[i][0] = Integer.toString(pacientes.get(i).getIdPaciente());
			datos[i][1] = pacientes.get(i).getNombre();
			datos[i][2] = pacientes.get(i).getApellido();
			datos[i][3] = pacientes.get(i).getDni();
			datos[i][4] = Integer.toString(pacientes.get(i).getHabitacion().getIdHabitacion());
		}
		
		modelo.setDataVector(datos, columnas);
		
		return modelo;
	}
	
	public DefaultTableModel rellenarTablaPacienteBuscado(List<Paciente> pacientesBuscados) {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Paciente> pacientes = pacientesBuscados; 
		
		String[] columnas = {"ID","Nombre","Apellido","DNI","Habitacion"};

		String datos[][] = new String[pacientes.size()][5];
		for (int i=0; i<pacientes.size(); i++) {
			datos[i][0] = Integer.toString(pacientes.get(i).getIdPaciente());
			datos[i][1] = pacientes.get(i).getNombre();
			datos[i][2] = pacientes.get(i).getApellido();
			datos[i][3] = pacientes.get(i).getDni();
			datos[i][4] = Integer.toString(pacientes.get(i).getHabitacion().getIdHabitacion());
		}
			
		modelo.setDataVector(datos, columnas);
		
		return modelo;
	}
	
	public static String[] rellenarComboBox() {
		String[] array = null;
		try {
			List<Habitacion> habitaciones = HabitacionDAO.selectAllHabitacionesVacias();
			array = new String[habitaciones.size()];
			for (int i = 0; i<habitaciones.size(); i++) {
				array[i] = String.valueOf(habitaciones.get(i).getIdHabitacion());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}
	

}
