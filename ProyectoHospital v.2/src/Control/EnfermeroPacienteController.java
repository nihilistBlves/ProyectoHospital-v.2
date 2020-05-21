package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.EnfermeroPacienteDAO;
import Modelo.Enfermero;
import Modelo.Habitacion;
import Modelo.Paciente;
import Modelo.Pasillo;
import Vista.MensajeError;
import Vista.MensajeSuccess;

public class EnfermeroPacienteController implements ActionListener, KeyListener {
	
	private JTextField buscarTextField;
	private JTable tablaEnfermero, asignados, noAsignados;
	
	public EnfermeroPacienteController(JTable tablaEnfermero) {
		this.tablaEnfermero = tablaEnfermero;
	}
	
	public EnfermeroPacienteController(JTextField buscarTextField, JTable tablaEnfermero, JTable asignados, JTable noAsignados) {
		this.buscarTextField = buscarTextField;
		this.tablaEnfermero = tablaEnfermero;
		this.asignados = asignados;
		this.noAsignados = noAsignados;
	}
	
	public EnfermeroPacienteController(JTable tablaEnfermero, JTable asignados, JTable noAsignados) {
		this.tablaEnfermero = tablaEnfermero;
		this.asignados = asignados;
		this.noAsignados = noAsignados;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		if (evento.equals("asignarPaciente")) {
			int idEnfermero = Integer.parseInt(tablaEnfermero.getValueAt(tablaEnfermero.getSelectedRow(), 0).toString());
			for (int i = 0; i < noAsignados.getSelectedRows().length; i++) {
				int idPaciente = Integer.parseInt(noAsignados.getValueAt(noAsignados.getSelectedRows()[i], 0).toString());
				try {
					if (EnfermeroPacienteDAO.insertarEnfermeroPaciente(new Enfermero(idEnfermero, "", "", 0), new Paciente(idPaciente, "", "", "", new Habitacion(0, new Pasillo(0))))) {
						MensajeSuccess m1 = new MensajeSuccess();
						m1.mostrarMensajeSuccess("Asignado correctamente.");
					} else {
						MensajeError m2 = new MensajeError();
						m2.mostrarMensajeError("Ha ocurrido un error en la asignación.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			asignados.setModel(this.rellenarTablaPacienteAsignados());
			noAsignados.setModel(this.rellenarTablaPacienteSinAsignar());
		}
		if (evento.equals("desasignarPaciente")) {
			int idEnfermero = Integer.parseInt(tablaEnfermero.getValueAt(tablaEnfermero.getSelectedRow(), 0).toString());
			for (int i = 0; i < asignados.getSelectedRows().length; i++) {
				int idPaciente = Integer.parseInt(asignados.getValueAt(asignados.getSelectedRows()[i], 0).toString());
				try {
					if (EnfermeroPacienteDAO.eliminarEnfermeroPaciente(new Enfermero(idEnfermero, "", "", 0), new Paciente(idPaciente, "", "", "", new Habitacion(0, new Pasillo(0))))) {
						MensajeSuccess m1 = new MensajeSuccess();
						m1.mostrarMensajeSuccess("Desasignado correctamente.");
					} else {
						MensajeError m2 = new MensajeError();
						m2.mostrarMensajeError("Ha ocurrido un error en la desasignación.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			asignados.setModel(this.rellenarTablaPacienteAsignados());
			noAsignados.setModel(this.rellenarTablaPacienteSinAsignar());
		}
	}
	
	public DefaultTableModel rellenarTablaPacienteSinAsignar() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Paciente> pacientes = null; 
		
		String[] columnas = {"ID","Nombre","Apellido","DNI","Habitacion"};

		try {
			pacientes = EnfermeroPacienteDAO.pacientesSinAsignarEnfermero(new Enfermero(Integer.parseInt(tablaEnfermero.getValueAt(tablaEnfermero.getSelectedRow(), 0).toString()), "", "", 0));
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
	
	public DefaultTableModel rellenarTablaPacienteAsignados() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Paciente> pacientes = null; 
		
		String[] columnas = {"ID","Nombre","Apellido","DNI","Habitacion"};

		try {
			pacientes = EnfermeroPacienteDAO.pacientesAsignadosEnfermero(new Enfermero(Integer.parseInt(tablaEnfermero.getValueAt(tablaEnfermero.getSelectedRow(), 0).toString()), "", "", 0));
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
	
	public DefaultTableModel rellenarTablaPacienteSinAsignarBuscar(String busqueda) {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Paciente> pacientes = null; 
		
		String[] columnas = {"ID","Nombre","Apellido","DNI","Habitacion"};

		try {
			pacientes = EnfermeroPacienteDAO.pacientesSinAsignarEnfermeroBuscar(new Enfermero(Integer.parseInt(tablaEnfermero.getValueAt(tablaEnfermero.getSelectedRow(), 0).toString()), "", "", 0), new Paciente(0, busqueda, busqueda, "", new Habitacion(0, new Pasillo(0))));
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
	
	public DefaultTableModel rellenarTablaPacienteAsignadosBuscar(String busqueda) {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Paciente> pacientes = null; 
		
		String[] columnas = {"ID","Nombre","Apellido","DNI","Habitacion"};

		try {
			pacientes = EnfermeroPacienteDAO.pacientesAsignadosEnfermeroBuscar(new Enfermero(Integer.parseInt(tablaEnfermero.getValueAt(tablaEnfermero.getSelectedRow(), 0).toString()), "", "", 0), new Paciente(0, busqueda, busqueda, "", new Habitacion(0, new Pasillo(0))));
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

	@Override
	public void keyTyped(KeyEvent e) {
		String texto = buscarTextField.getText();
		try {
			asignados.setModel(this.rellenarTablaPacienteAsignadosBuscar(texto));
			noAsignados.setModel(this.rellenarTablaPacienteSinAsignarBuscar(texto));
		} catch (NumberFormatException e1) {
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

	

}
