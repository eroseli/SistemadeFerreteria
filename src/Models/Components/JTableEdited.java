package Models.Components;

import javax.swing.JTable;

public class JTableEdited extends JTable{

	public boolean isCellEditable(int row, int column) {
		return false;
	};
	
	
}
