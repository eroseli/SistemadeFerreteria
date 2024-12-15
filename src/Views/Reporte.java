package Views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Reporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte frame = new Reporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reporte() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					imprimirTicket();
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
	}
	
	public void imprimirTicket() throws JRException {
		
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject( getClass().getResource( ("/report2.jasper")));
        
        //FileInputStream img = new FileInputStream("");
        HashMap hm = new HashMap();
        
        
        hm.put("total","100");
        hm.put("pago","200");
        hm.put("cambio", "");
        hm.put("nombre_usuario", "Eros Eli Roque Santiago");
        hm.put("nombre_cliente", "Daneil dias");
        hm.put("imagen",null);
        hm.put("local", "AutoPlace");
        hm.put("nombre_local","Auto Place");
        hm.put("subtotal", "100");
        
        hm.put("descuento","0");
        hm.put("iva", "0.00");
        hm.put("leyenda", "Gracias por su compra");
        
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, new JRBeanCollectionDataSource(null));

        JasperViewer view = new JasperViewer(jasperPrint,false);
        view.setVisible(true);
        
        // Paso 5: Ver el reporte
        JasperViewer.viewReport(jasperPrint, true);
        
	}

}
