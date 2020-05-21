package Vista.Insertar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.Controller;
import Control.EnfermeroController;

import javax.swing.JLabel;

public class InsertarEnfermero extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField nColegiadoTextField;

	public InsertarEnfermero() {
		setBounds(100, 100, 450, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(51, 39, 61, 16);
		contentPanel.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(51, 83, 61, 16);
		contentPanel.add(apellidoLabel);
		
		JLabel nColegiado = new JLabel("Nº Colegiado");
		nColegiado.setBounds(51, 128, 90, 16);
		contentPanel.add(nColegiado);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(155, 34, 230, 26);
		contentPanel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setBounds(155, 78, 230, 26);
		contentPanel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		nColegiadoTextField = new JTextField();
		nColegiadoTextField.setBounds(155, 123, 230, 26);
		contentPanel.add(nColegiadoTextField);
		nColegiadoTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton insertarButton = new JButton("Insertar");
				insertarButton.setActionCommand("insertarEnfermero");
				insertarButton.addActionListener(new EnfermeroController(this, nombreTextField, apellidoTextField, nColegiadoTextField));
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
