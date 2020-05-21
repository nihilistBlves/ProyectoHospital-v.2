package Vista.Asignar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controller;
import Control.EnfermeroPacienteController;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;

public class AsignarEnfermeroPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable sinAsignarTable;
	private JTable asignadosTable;
	private JTextField buscarTextField;

	public AsignarEnfermeroPaciente(JTable tabla) {
		setBounds(100, 100, 610, 400);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 610, 339);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JScrollPane sinAsignarScrollPane = new JScrollPane();
		sinAsignarScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sinAsignarScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sinAsignarScrollPane.setBounds(346, 70, 240, 186);
		contentPanel.add(sinAsignarScrollPane);
		
		sinAsignarTable = new JTable();
		sinAsignarTable.setFillsViewportHeight(true);
		sinAsignarTable.setModel(new EnfermeroPacienteController(tabla).rellenarTablaPacienteSinAsignar());
		sinAsignarScrollPane.setViewportView(sinAsignarTable);
		
		JScrollPane asignadosScrollPane = new JScrollPane();
		asignadosScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		asignadosScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		asignadosScrollPane.setBounds(40, 70, 240, 186);
		contentPanel.add(asignadosScrollPane);
		
		asignadosTable = new JTable();
		asignadosTable.setFillsViewportHeight(true);
		asignadosTable.setModel(new EnfermeroPacienteController(tabla).rellenarTablaPacienteAsignados());
		asignadosScrollPane.setViewportView(asignadosTable);
		
		JLabel sinAsignarLabel = new JLabel("Pacientes sin asignar");
		sinAsignarLabel.setBounds(346, 42, 144, 16);
		contentPanel.add(sinAsignarLabel);
		
		JLabel asignadosLabel = new JLabel("Pacientes asignados");
		asignadosLabel.setBounds(40, 42, 127, 16);
		contentPanel.add(asignadosLabel);
		
		JButton desasignarButton = new JButton("Desasignar");
		desasignarButton.setBounds(100, 280, 117, 50);
		desasignarButton.setActionCommand("desasignarPaciente");
		desasignarButton.addActionListener(new EnfermeroPacienteController(tabla, asignadosTable, sinAsignarTable));
		contentPanel.add(desasignarButton);
		
		JButton asignarButton = new JButton("Asignar");
		asignarButton.setBounds(407, 280, 117, 50);
		asignarButton.setActionCommand("asignarPaciente");
		asignarButton.addActionListener(new EnfermeroPacienteController(tabla, asignadosTable, sinAsignarTable));
		contentPanel.add(asignarButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 339, 610, 39);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setBounds(473, 5, 132, 29);
				cancelButton.setActionCommand("cancelar");
				cancelButton.addActionListener(new Controller(this));
				buttonPane.add(cancelButton);
			}
			
			buscarTextField = new JTextField();
			buscarTextField.setText("Buscar...");
			buscarTextField.addKeyListener(new EnfermeroPacienteController(buscarTextField, tabla, asignadosTable, sinAsignarTable));
			buscarTextField.setBounds(6, 5, 117, 26);
			buttonPane.add(buscarTextField);
			buscarTextField.setColumns(10);
		}
	}
}
