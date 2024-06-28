package Models.Components;

import javax.swing.JTable;

public class JTableEdidata extends JTable{

	public boolean isCellEditable(int row, int column) {
		return false;
	};
	
}
