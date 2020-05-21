package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controller;
import Control.EnfermeroController;
import Control.MedicoController;
import Control.PacienteController;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable tablePaciente;
	private JTable tableEnfermero;
	private JTable tableMedico;
	private JTextField buscarTextField;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(5, 5, 586, 22);
		contentPane.add(menuBar);
		
		JMenu menuInsertar = new JMenu("Insertar");
		menuBar.add(menuInsertar);
		
		JMenuItem insertarPaciente = new JMenuItem("Paciente");
		insertarPaciente.setActionCommand("abrirInsertarPaciente");
		insertarPaciente.addActionListener(new Controller());
		menuInsertar.add(insertarPaciente);
		
		JMenuItem insertarEnfermero = new JMenuItem("Enfermero");
		insertarEnfermero.setActionCommand("abrirInsertarEnfermero");
		insertarEnfermero.addActionListener(new Controller());
		menuInsertar.add(insertarEnfermero);
		
		JMenuItem insertarMedico = new JMenuItem("Médico");
		insertarMedico.setActionCommand("abrirInsertarMedico");
		insertarMedico.addActionListener(new Controller());
		menuInsertar.add(insertarMedico);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 54, 586, 319);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPanePaciente = new JScrollPane();
		tabbedPane.addTab("Pacientes", null, scrollPanePaciente, null);
		scrollPanePaciente.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanePaciente.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tablePaciente = new JTable();
		tablePaciente.setEnabled(false);
		tablePaciente.setModel(new PacienteController().rellenarTablaPaciente());
		scrollPanePaciente.setViewportView(tablePaciente);
		
		JScrollPane scrollPaneEnfermero = new JScrollPane();
		tabbedPane.addTab("Enfermeros", null, scrollPaneEnfermero, null);
		scrollPaneEnfermero.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneEnfermero.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		tableEnfermero = new JTable();
		tableEnfermero.setEnabled(false);
		tableEnfermero.setModel(new EnfermeroController().rellenarTablaEnfermero());
		scrollPaneEnfermero.setViewportView(tableEnfermero);
		
		JScrollPane scrollPaneMedico = new JScrollPane();
		tabbedPane.addTab("Médicos", null, scrollPaneMedico, null);
		scrollPaneMedico.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMedico.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		tableMedico = new JTable();
		tableMedico.setModel(new MedicoController().rellenarTablaMedico());
		scrollPaneMedico.setViewportView(tableMedico);
		
		addWindowListener(new Controller(tablePaciente,tableEnfermero,tableMedico));
		
		buscarTextField = new JTextField();
		buscarTextField.setText("Buscar...");
		buscarTextField.setBounds(449, 34, 130, 26);
		buscarTextField.addKeyListener(new Controller(buscarTextField, tablePaciente, tableEnfermero, tableMedico));
		
		JPopupMenu medicoPopupMedico = new JPopupMenu();
		medicoPopupMedico.addPopupMenuListener(Controller.seleccionarClickDerecho(tableMedico, medicoPopupMedico));
		addPopup(tableMedico, medicoPopupMedico);
		
		JMenuItem editarMenuItem3 = new JMenuItem("Editar");
		editarMenuItem3.setActionCommand("abrirEditarMedico");
		editarMenuItem3.addActionListener(new Controller(tableMedico));
		
		JMenuItem asignarMenuItem2 = new JMenuItem("Ver pacientes asignados");
		asignarMenuItem2.setActionCommand("abrirAsignarMedico");
		asignarMenuItem2.addActionListener(new Controller(tableMedico));
		medicoPopupMedico.add(asignarMenuItem2);
		medicoPopupMedico.add(editarMenuItem3);
		
		JMenuItem eliminarMenuItem3 = new JMenuItem("Eliminar");
		eliminarMenuItem3.setActionCommand("abrirEliminarMedico");
		eliminarMenuItem3.addActionListener(new Controller(tableMedico));
		medicoPopupMedico.add(eliminarMenuItem3);
		
		JPopupMenu enfermeroPopupmenu = new JPopupMenu();
		enfermeroPopupmenu.addPopupMenuListener(Controller.seleccionarClickDerecho(tableEnfermero, enfermeroPopupmenu));
		addPopup(tableEnfermero, enfermeroPopupmenu);
		
		JMenuItem editarMenuItem2 = new JMenuItem("Editar");
		editarMenuItem2.setActionCommand("abrirEditarEnfermero");
		editarMenuItem2.addActionListener(new Controller(tableEnfermero));
		
		JMenuItem asignarMenuItem1 = new JMenuItem("Ver pacientes asignados");
		asignarMenuItem1.setActionCommand("abrirAsignarEnfermero");
		asignarMenuItem1.addActionListener(new Controller(tableEnfermero));
		enfermeroPopupmenu.add(asignarMenuItem1);
		enfermeroPopupmenu.add(editarMenuItem2);
		
		JMenuItem eliminarMenuItem2 = new JMenuItem("Eliminar");
		eliminarMenuItem2.setActionCommand("abrirEliminarEnfermero");
		eliminarMenuItem2.addActionListener(new Controller(tableEnfermero));
		enfermeroPopupmenu.add(eliminarMenuItem2);
		
		JPopupMenu pacientePopupMenu = new JPopupMenu();
		pacientePopupMenu.addPopupMenuListener(Controller.seleccionarClickDerecho(tablePaciente, pacientePopupMenu));
		addPopup(tablePaciente, pacientePopupMenu);
		
		JMenuItem editarMenuItem = new JMenuItem("Editar");
		editarMenuItem.setActionCommand("abrirEditarPaciente");
		editarMenuItem.addActionListener(new Controller(tablePaciente));
		pacientePopupMenu.add(editarMenuItem);
		
		JMenuItem eliminarMenuItem = new JMenuItem("Eliminar");
		eliminarMenuItem.setActionCommand("abrirEliminarPaciente");
		eliminarMenuItem.addActionListener(new Controller(tablePaciente));
		pacientePopupMenu.add(eliminarMenuItem);
		contentPane.add(buscarTextField);
		buscarTextField.setColumns(10);
	}


	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
