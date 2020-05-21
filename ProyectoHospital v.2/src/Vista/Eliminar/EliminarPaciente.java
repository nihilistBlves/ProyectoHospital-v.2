package Vista.Eliminar;

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

public class EliminarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idTextField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField dniTextField;

	public EliminarPaciente(JTable tabla) {
		setBounds(100, 100, 493, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel seguroLabel = new JLabel("¿Está seguro de querer eliminar el siguiente paciente?");
		seguroLabel.setBounds(70, 30, 379, 16);
		contentPanel.add(seguroLabel);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(21, 78, 61, 16);
		contentPanel.add(idLabel);
		
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(94, 78, 61, 16);
		contentPanel.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(220, 78, 61, 16);
		contentPanel.add(apellidoLabel);
		
		JLabel dniLabel = new JLabel("DNI");
		dniLabel.setBounds(365, 78, 61, 16);
		contentPanel.add(dniLabel);
		
		idTextField = new JTextField(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
		idTextField.setEditable(false);
		idTextField.setBounds(16, 103, 55, 26);
		contentPanel.add(idTextField);
		idTextField.setColumns(10);
		
		nombreTextField = new JTextField(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
		nombreTextField.setEditable(false);
		nombreTextField.setBounds(93, 103, 99, 26);
		contentPanel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		apellidoTextField = new JTextField(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
		apellidoTextField.setEditable(false);
		apellidoTextField.setBounds(220, 103, 120, 26);
		contentPanel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		dniTextField = new JTextField(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
		dniTextField.setEditable(false);
		dniTextField.setBounds(365, 103, 107, 26);
		contentPanel.add(dniTextField);
		dniTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.setActionCommand("eliminarPaciente");
				okButton.addActionListener(new PacienteController(this, tabla));
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
