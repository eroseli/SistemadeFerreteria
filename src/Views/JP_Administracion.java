package Views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class JP_Administracion extends JPanel {

	private static final long serialVersionUID = 1L;

	public JP_Administracion() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JTabbedPane TP_Paneles = new JTabbedPane(JTabbedPane.TOP);
		TP_Paneles.setBounds(6, 0, 570, 644);
		add(TP_Paneles);

		JP_Empresa empresa = new JP_Empresa();
		JD_AdministrarRegistros  jd_AdministrarRegistros = new JD_AdministrarRegistros();
		
		TP_Paneles.addTab("Administrar Parámetros de la Empresa", empresa);
		TP_Paneles.addTab("Registros", jd_AdministrarRegistros);
		
	}
	
	

}
