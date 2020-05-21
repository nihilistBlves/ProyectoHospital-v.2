package Vista.Insertar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controller;
import Control.MedicoController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class InsertarMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField nColegiadoTextField;

	public InsertarMedico() {
		setBounds(100, 100, 450, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel nombreLabel = new JLabel("Nombre");
			nombreLabel.setBounds(73, 35, 61, 16);
			contentPanel.add(nombreLabel);
		}
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(73, 79, 61, 16);
		contentPanel.add(apellidoLabel);
		
		JLabel nColegiadoLabel = new JLabel("Nº Colegiado");
		nColegiadoLabel.setBounds(73, 122, 93, 16);
		contentPanel.add(nColegiadoLabel);
		
		JLabel campoMedicoLabel = new JLabel("Campo Médico");
		campoMedicoLabel.setBounds(73, 166, 93, 16);
		contentPanel.add(campoMedicoLabel);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(187, 30, 200, 26);
		contentPanel.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setBounds(187, 74, 200, 26);
		contentPanel.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		
		nColegiadoTextField = new JTextField();
		nColegiadoTextField.setBounds(187, 117, 200, 26);
		contentPanel.add(nColegiadoTextField);
		nColegiadoTextField.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>(MedicoController.rellenarComboBox());
		comboBox.setBounds(187, 162, 200, 27);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton insertarButton = new JButton("Insertar");
				insertarButton.setActionCommand("insertarMedico");
				insertarButton.addActionListener(new MedicoController(this, nombreTextField, apellidoTextField, nColegiadoTextField, comboBox));
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
