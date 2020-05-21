package Vista.Editar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.Controller;
import Control.EnfermeroController;
import Control.MedicoController;
import Control.PacienteController;

public class EditarMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField nColegiadoTextField;

	public EditarMedico(JTable tabla) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		String nombre = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
		String apellido = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
		int nColegiado = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
		String campo = tabla.getValueAt(tabla.getSelectedRow(), 4).toString();
		contentPanel.setLayout(null);
		
		JLabel idLabel = new JLabel("ID Enfermero");
		idLabel.setBounds(38, 46, 82, 16);
		contentPanel.add(idLabel);
		
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(38, 84, 50, 16);
		contentPanel.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(38, 122, 52, 16);
		contentPanel.add(apellidoLabel);
		
		JLabel nColegiadoLabel = new JLabel("Nº Colegiado");
		nColegiadoLabel.setBounds(38, 160, 83, 16);
		contentPanel.add(nColegiadoLabel);
		
		JLabel campoLabel = new JLabel("Campo");
		campoLabel.setBounds(38, 197, 61, 16);
		contentPanel.add(campoLabel);
		
		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setText(Integer.toString(id));
		idTextField.setBounds(158, 41, 220, 26);
		contentPanel.add(idTextField);
		idTextField.setColumns(10);
		
		nombreTextField = new JTextField();
		nombreTextField.setText(nombre);
		nombreTextField.setBounds(158, 79, 220, 26);
		contentPanel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setText(apellido);
		apellidoTextField.setBounds(158, 117, 220, 26);
		contentPanel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		nColegiadoTextField = new JTextField();
		nColegiadoTextField.setText(Integer.toString(nColegiado));
		nColegiadoTextField.setBounds(158, 155, 220, 26);
		contentPanel.add(nColegiadoTextField);
		nColegiadoTextField.setColumns(10);
		
		JComboBox<String> campoComboBox = new JComboBox<String>(MedicoController.rellenarComboBox());
		campoComboBox.setSelectedItem(campo);
		campoComboBox.setBounds(158, 193, 225, 27);
		contentPanel.add(campoComboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar edición");
				okButton.setActionCommand("editarMedico");
				okButton.addActionListener(new MedicoController(this, idTextField, nombreTextField, apellidoTextField, nColegiadoTextField, campoComboBox));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("cancelar");
				cancelButton.addActionListener(new Controller(this));
				buttonPane.add(cancelButton);
			}
		}
	}

}
