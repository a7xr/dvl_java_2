package StudyGraphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class FrameJTable01 extends JFrame{

	JTableModel01 jTableModel01 = new JTableModel01();
	JTable jTable = new JTable();
	
	FrameJTable01() {
		setTitle("title");
		setBounds(200, 50, 500, 300);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jTableModel01.defaultTableModel.addColumn(
				"col00");
		jTableModel01.defaultTableModel.addColumn(
				"col01");
//		jTableModel01.defaultTableModel.addRow(
//			new 
//		);
		
		jTable.setModel(jTableModel01.defaultTableModel);
		getContentPane().add(new JScrollPane(jTable), BorderLayout.CENTER);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FrameJTable01();
	}
	
}

class JTableModel01 extends AbstractTableModel {
	DefaultTableModel defaultTableModel = new DefaultTableModel();

	@Override
	public int getRowCount() {
		//  Auto-generated method stub
		return defaultTableModel.getRowCount();
	}

	@Override
	public int getColumnCount() {
		//  Auto-generated method stub
		return defaultTableModel.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//  Auto-generated method stub
		return defaultTableModel.getValueAt(rowIndex, columnIndex);
	}
	
}