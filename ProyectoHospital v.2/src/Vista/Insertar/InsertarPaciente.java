package Vista.Insertar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controller;
import Control.PacienteController;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class InsertarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField dniTextField;
	private JComboBox<String> habitacionComboBox;

	public InsertarPaciente() {
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel nombreLabel = new JLabel("Nombre");
			nombreLabel.setBounds(43, 30, 50, 16);
			contentPanel.add(nombreLabel);
		}
		{
			nombreTextField = new JTextField();
			nombreTextField.setBounds(147, 25, 277, 26);
			contentPanel.add(nombreTextField);
			nombreTextField.setColumns(10);
		}
		{
			JLabel ApellidoLabel_1 = new JLabel("Apellido");
			ApellidoLabel_1.setBounds(43, 68, 52, 16);
			contentPanel.add(ApellidoLabel_1);
		}
		{
			apellidoTextField = new JTextField();
			apellidoTextField.setBounds(147, 63, 277, 26);
			contentPanel.add(apellidoTextField);
			apellidoTextField.setColumns(10);
		}
		{
			JLabel dniLabel = new JLabel("DNI");
			dniLabel.setBounds(43, 106, 24, 16);
			contentPanel.add(dniLabel);
		}
		{
			dniTextField = new JTextField();
			dniTextField.setBounds(147, 101, 277, 26);
			contentPanel.add(dniTextField);
			dniTextField.setColumns(10);
		}
		{
			JLabel habitacionLabel = new JLabel("Habitacion");
			habitacionLabel.setBounds(44, 145, 68, 16);
			contentPanel.add(habitacionLabel);
		}
		{
			habitacionComboBox = new JComboBox<String>(PacienteController.rellenarComboBox());
			habitacionComboBox.setBounds(147, 141, 277, 27);
			contentPanel.add(habitacionComboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton insertarButton = new JButton("Insertar");
				insertarButton.setActionCommand("insertarPaciente");
				insertarButton.addActionListener(new PacienteController(this,nombreTextField,apellidoTextField,dniTextField,habitacionComboBox));
				buttonPane.add(insertarButton);
				getRootPane().setDefaultButton(insertarButton);
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
