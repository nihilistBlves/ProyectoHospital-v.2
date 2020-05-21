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

import DAO.CampoMedicoDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Modelo.CampoMedico;
import Modelo.Enfermero;
import Modelo.Habitacion;
import Modelo.Medico;
import Modelo.Paciente;
import Modelo.Pasillo;
import Vista.MensajeError;
import Vista.MensajeSuccess;

public class MedicoController implements ActionListener {
	
	private JDialog ventana;
	private JTable tabla;
	private JTextField idTextField, nombreTextField, apellidoTextField, nColegiadoTextField;
	private JComboBox<String> campoComboBox;
	
	public MedicoController() {
		
	}
	
	public MedicoController(JTable tabla) {
		this.tabla = tabla;
	}
	
	public MedicoController(JDialog ventana, JTable tabla) {
		this.ventana = ventana;
		this.tabla = tabla;
	}
	
	public MedicoController(JDialog ventana, JTextField nombreTextField, JTextField apellidoTextField, JTextField nColegiadoTextField, JComboBox<String> campoComboBox) {
		this.ventana = ventana;
		this.nombreTextField = nombreTextField;
		this.apellidoTextField = apellidoTextField;
		this.nColegiadoTextField = nColegiadoTextField;
		this.campoComboBox = campoComboBox;
	}
	
	public MedicoController(JDialog ventana, JTextField idTextField, JTextField nombreTextField, JTextField apellidoTextField, JTextField nColegiadoTextField, JComboBox<String> campoComboBox) {
		this.ventana = ventana;
		this.idTextField = idTextField;
		this.nombreTextField = nombreTextField;
		this.apellidoTextField = apellidoTextField;
		this.nColegiadoTextField = nColegiadoTextField;
		this.campoComboBox = campoComboBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String opcion = e.getActionCommand();
		if (opcion.equals("insertarMedico")) {
			try {
				if (!nombreTextField.getText().contentEquals("") || !apellidoTextField.getText().contentEquals("") || !nColegiadoTextField.getText().contentEquals("")) {
					List<CampoMedico> campo = CampoMedicoDAO.selectOneCampo(new CampoMedico(0, campoComboBox.getSelectedItem().toString()));
					int idCampo = campo.get(0).getId_campo();
					if (MedicoDAO.insertarMedico(new Medico(0, nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(nColegiadoTextField.getText()), new CampoMedico(idCampo, campoComboBox.getItemAt(campoComboBox.getSelectedIndex()).toString()))))	 {
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
		if (opcion.equals("editarMedico")) {
			try {
				if (!idTextField.getText().contentEquals("") || !nombreTextField.getText().contentEquals("") || !apellidoTextField.getText().contentEquals("") || !nColegiadoTextField.getText().contentEquals("")) {
					List<CampoMedico> campo = CampoMedicoDAO.selectOneCampo(new CampoMedico(0, campoComboBox.getSelectedItem().toString()));
					int idCampo = campo.get(0).getId_campo();
					if (MedicoDAO.editarMedico(new Medico(Integer.parseInt(idTextField.getText()), nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(nColegiadoTextField.getText()), new CampoMedico(idCampo, campoComboBox.getItemAt(campoComboBox.getSelectedIndex()).toString()))))	 {
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
		if (opcion.equals("eliminarMedico")) {
			try {
				if (MedicoDAO.eliminarMedico(new Medico(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()), "", "", 0, new CampoMedico(0, "")))) {
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
	
	public DefaultTableModel rellenarTablaMedico() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Medico> medicos = null; 
		
		String[] columnas = {"ID","Nombre","Apellido","Nº Colegiado","Campo"};

		try {
			medicos = MedicoDAO.selectAllMedicos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String datos[][] = new String[medicos.size()][5];
		for (int i=0; i<medicos.size(); i++) {
			datos[i][0] = Integer.toString(medicos.get(i).getIdMedico());
			datos[i][1] = medicos.get(i).getNombre();
			datos[i][2] = medicos.get(i).getApellido();
			datos[i][3] = Integer.toString(medicos.get(i).getNumeroColegiado());
			datos[i][4] = medicos.get(i).getCampo().getNombre_campo();
		}
		
		modelo.setDataVector(datos, columnas);
		
		return modelo;
	}
	
	public DefaultTableModel rellenarTablaMedicoBuscado(List<Medico> medicosBuscados) {
		DefaultTableModel modelo = new DefaultTableModel();
		
		List<Medico> medicos = medicosBuscados; 
		
		String[] columnas = {"ID","Nombre","Apellido","Nº Colegiado", "Campo"};

		String datos[][] = new String[medicos.size()][5];
		for (int i=0; i<medicos.size(); i++) {
			datos[i][0] = Integer.toString(medicos.get(i).getIdMedico());
			datos[i][1] = medicos.get(i).getNombre();
			datos[i][2] = medicos.get(i).getApellido();
			datos[i][3] = Integer.toString(medicos.get(i).getNumeroColegiado());
			datos[i][4] = medicos.get(i).getCampo().getNombre_campo();
		}
			
		modelo.setDataVector(datos, columnas);
		
		return modelo;
	}
	
	public static String[] rellenarComboBox() {
		String[] array = null;
		try {
			List<CampoMedico> campos = CampoMedicoDAO.selectAllCampo();
			array = new String[campos.size()];
			for (int i = 0; i<campos.size(); i++) {
				array[i] = String.valueOf(campos.get(i).getNombre_campo());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}

}
