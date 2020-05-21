package Vista.Editar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Control.Controller;
import Control.PacienteController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EditarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField dniTextField;

	public EditarPaciente(JTable tabla) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		String nombre = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
		String apellido = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
		String dni = tabla.getValueAt(tabla.getSelectedRow(), 3).toString();
		int habitacion = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		
		JLabel idLabel = new JLabel("ID Paciente");
		idLabel.setBounds(40, 32, 76, 16);
		contentPanel.add(idLabel);
		
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(40, 70, 61, 16);
		contentPanel.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(40, 108, 61, 16);
		contentPanel.add(apellidoLabel);
		
		JLabel dniLabel = new JLabel("DNI");
		dniLabel.setBounds(40, 147, 61, 16);
		contentPanel.add(dniLabel);
		
		JLabel habitacionLabel = new JLabel("Habitación");
		habitacionLabel.setBounds(40, 189, 76, 16);
		contentPanel.add(habitacionLabel);
		
		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setText(Integer.toString(id));
		idTextField.setBounds(170, 27, 225, 26);
		contentPanel.add(idTextField);
		idTextField.setColumns(10);
		
		nombreTextField = new JTextField();
		nombreTextField.setText(nombre);
		nombreTextField.setBounds(170, 65, 225, 26);
		contentPanel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setText(apellido);
		apellidoTextField.setBounds(170, 103, 225, 26);
		contentPanel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		dniTextField = new JTextField();
		dniTextField.setText(dni);
		dniTextField.setBounds(170, 142, 225, 26);
		contentPanel.add(dniTextField);
		dniTextField.setColumns(10);
		
		JComboBox<String> habitacionComboBox = new JComboBox<String>(PacienteController.rellenarComboBox());
		habitacionComboBox.addItem(Integer.toString(habitacion));
		habitacionComboBox.setSelectedItem(Integer.toString(habitacion));
		habitacionComboBox.setBounds(170, 185, 225, 27);
		contentPanel.add(habitacionComboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar edición");
				okButton.setActionCommand("editarPaciente");
				okButton.addActionListener(new PacienteController(this, idTextField, nombreTextField, apellidoTextField, dniTextField, habitacionComboBox));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancerlar");
				cancelButton.setActionCommand("cancelar");
				cancelButton.addActionListener(new Controller(this));
				buttonPane.add(cancelButton);
			}
		}
	}
}
