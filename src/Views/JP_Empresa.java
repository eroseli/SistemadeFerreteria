package Views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import Controllers.ControladorEmpresa;
import HerramientasConexion.Herramientas;
import Models.Empresa;
import Models.Respuesta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JP_Empresa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFEmpresa;
	private JTextField TFDireccion;
	private JTextField TFApaterno;
	private JTextField TFNombre;
	private JTextField TFTelefono1;
	private JTextField TFAmaterno;
	private JTextField TFCorreo;
	private JTextField TFTelefono2;
	private JTextField TFIdEmpresa;


	public JP_Empresa() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		TFEmpresa = new JTextField();
		TFEmpresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Herramientas.tamanioCadena(arg0, TFEmpresa, Herramientas.TamCampos.descripcionCorta);
			}
		});
		TFEmpresa.setBounds(232, 90, 300, 26);
		add(TFEmpresa);
		TFEmpresa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Empresa");
		lblNewLabel.setBounds(54, 95, 166, 16);
		add(lblNewLabel);
		
		TFDireccion = new JTextField();
		TFDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFDireccion, Herramientas.TamCampos.direccion);
			}
		});
		TFDireccion.setColumns(10);
		TFDireccion.setBounds(232, 128, 300, 26);
		add(TFDireccion);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(54, 133, 166, 16);
		add(lblDireccin);
		
		TFApaterno = new JTextField();
		TFApaterno.setColumns(10);
		TFApaterno.setBounds(232, 204, 300, 26);
		add(TFApaterno);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellito Paterno");
		lblNewLabel_1_1.setBounds(54, 209, 166, 16);
		add(lblNewLabel_1_1);
		
		JLabel lblNombrePropietario = new JLabel("Nombre Propietario");
		lblNombrePropietario.setBounds(54, 171, 166, 16);
		add(lblNombrePropietario);
		
		TFNombre = new JTextField();
		TFNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Herramientas.tamanioCadena(e, TFNombre, Herramientas.TamCampos.direccion);
			}
		});
		TFNombre.setColumns(10);
		TFNombre.setBounds(232, 166, 300, 26);
		add(TFNombre);
		
		TFTelefono1 = new JTextField();
		TFTelefono1.setColumns(10);
		TFTelefono1.setBounds(232, 280, 300, 26);
		add(TFTelefono1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Teléfono 1");
		lblNewLabel_1_2.setBounds(54, 285, 166, 16);
		add(lblNewLabel_1_2);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setBounds(54, 247, 166, 16);
		add(lblApellidoMaterno);
		
		TFAmaterno = new JTextField();
		TFAmaterno.setColumns(10);
		TFAmaterno.setBounds(232, 242, 300, 26);
		add(TFAmaterno);
		
		TFCorreo = new JTextField();
		TFCorreo.setColumns(10);
		TFCorreo.setBounds(232, 353, 300, 26);
		add(TFCorreo);
		
		JLabel lblNewLabel_1_3 = new JLabel("Correo ");
		lblNewLabel_1_3.setBounds(54, 358, 166, 16);
		add(lblNewLabel_1_3);
		
		JLabel lblTelfono = new JLabel("Teléfono 2");
		lblTelfono.setBounds(54, 320, 166, 16);
		add(lblTelfono);
		
		TFTelefono2 = new JTextField();
		TFTelefono2.setColumns(10);
		TFTelefono2.setBounds(232, 315, 300, 26);
		add(TFTelefono2);
		
		JButton BGrabar = new JButton("Grabar");
		BGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grabar();
			}
		});
		BGrabar.setBounds(414, 401, 117, 29);
		add(BGrabar);
		
		JLabel LblId = new JLabel("Id");
		LblId.setBounds(54, 57, 166, 16);
		add(LblId);
		
		TFIdEmpresa = new JTextField();
		TFIdEmpresa.setColumns(10);
		TFIdEmpresa.setBounds(232, 52, 300, 26);
		add(TFIdEmpresa);
		
		JLabel lblNewLabel_1 = new JLabel("Administrar Parámetros del Sistema");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel_1.setBounds(47, 6, 481, 39);
		add(lblNewLabel_1);
		
		obtener();
		TFIdEmpresa.setVisible(false);
		LblId.setVisible(false);
		
	}
	
	public void obtener() {
		
		Respuesta respuesta = new Respuesta("",true,null);
		ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
		Empresa empresa = new Empresa();
		
		try {
			
			respuesta = controladorEmpresa.proceso(Herramientas.tipoOperacion.seleccionar, null);
			empresa = (Empresa) respuesta.getRespuesta();
			
			TFIdEmpresa.setText(empresa.getIdEmpresa()+"");
			TFEmpresa.setText(empresa.getNombre());
			TFNombre.setText(empresa.getNombrePropietario());
			TFApaterno.setText(empresa.getApaternoPropietario());
			TFAmaterno.setText(empresa.getAmaternoPropietario());
			TFDireccion.setText(empresa.getDireccion());
			TFTelefono1.setText(empresa.getTelefono());
			TFTelefono2.setText(empresa.getTelefono2());
			TFCorreo.setText(empresa.getCorreo());
			
		} catch (Exception e) {
			System.out.println("Problemas al intentar obtener valor de la empresa");
		}
		
	}
	
	public void grabar() {
		
		Respuesta respuesta = validarFormulario();
		ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
		Empresa empresa = new Empresa();
		
		
		try {
			
			if (!respuesta.getValor()) {
				JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Advertencia",JOptionPane.WARNING_MESSAGE);
				return;
			}
			empresa.setIdEmpresa(Integer.parseInt(TFIdEmpresa.getText()));
			empresa.setNombre(TFEmpresa.getText().trim());
			empresa.setDireccion(TFDireccion.getText().trim());
			empresa.setNombrePropietario(TFNombre.getText().trim());
			empresa.setApaternoPropietario(TFApaterno.getText().trim());
			empresa.setAmaternoPropietario(TFAmaterno.getText().trim());
			empresa.setTelefono(TFTelefono1.getText().trim());
			empresa.setTelefono2(TFTelefono2.getText().trim());
			empresa.setCorreo(TFCorreo.getText());
			
			respuesta = controladorEmpresa.proceso(Herramientas.tipoOperacion.actualizar, empresa);
			
			JOptionPane.showMessageDialog(this,respuesta.getMensaje(),"Información",JOptionPane.INFORMATION_MESSAGE);
			
			
		} catch (Exception e) {
			System.out.print("Error al procesar la solicitud "+e.getMessage());
			
		}
		
	}
	
	public Respuesta validarFormulario() {
		
		Respuesta respuesta = new Respuesta ("",true,null);
		
		if (TFEmpresa.getText().trim().isEmpty() || TFEmpresa.getText().trim() == "") {
			return new Respuesta("Introduce un Nombre para la Empresa.",false,null);
		}
		
		if (TFNombre.getText().trim().isEmpty() || TFNombre.getText().trim() == "") {
			return new Respuesta("Introduce un Nombre para el Propietario.",false,null);
		}
		
		if (TFApaterno.getText().trim().isEmpty() || TFApaterno.getText().trim() == "") {
			return new Respuesta("Introduce un Apellido Paterno Válido para el Propietario.",false,null);
		}
		
		if (TFTelefono1.getText().trim().isEmpty() || TFTelefono1.getText().length() != 10) {
			return new Respuesta("Introduce un Teléfono Válido.",false,null);
		}
		
		if (TFCorreo.getText().trim().isEmpty() || TFCorreo.getText().trim() == "") {
			return new Respuesta("Introduce un Correo Válido.",false,null);
		}
		return respuesta;
	}
}
