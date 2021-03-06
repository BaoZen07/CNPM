package ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import dao.BaoHanh_DAO;
import dao.KhachHang_DAO;
import entity.BaoHanh;
import entity.KhachHang;

public class QuanLyBaoHanhPanel extends JPanel implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaBH;
	private JTextField txtMaxe;
	private JTextField txtTenxe;
	private JTextField txtThongtinBH;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JTable tableBaoHanh;
	private DefaultTableModel modelBaoHanh;

	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private BaoHanh_DAO dsBH;
	private JButton btnXoaTrang;

	public QuanLyBaoHanhPanel() {
		setLookAndFeel();
		setSize(500, 600);
		// north
		JPanel pnlNorth = new JPanel();
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.Y_AXIS));

		JLabel lblTitle = new JLabel("QU???N L?? B???O H??NH");
		lblTitle.setFont(new Font("Times new roman", Font.BOLD, 20));
		lblTitle.setFont(HEADER_FONT);
		lblTitle.setForeground(HEADER_COLOR);
		JPanel pnlTitle = new JPanel();
		pnlTitle.add(lblTitle);
		
		pnlNorth.add(pnlTitle);

		// center
		Box boxNorth = Box.createVerticalBox();

		pnlNorth.add(boxNorth);
		/// line1
		Box box1 = Box.createHorizontalBox();
		box1.add(Box.createHorizontalStrut(20));
		JLabel lblMa = new JLabel("M?? b???o h??nh");
		box1.add(lblMa);
		box1.add(Box.createHorizontalStrut(10));
		txtMaBH = new JTextField();
		box1.add(txtMaBH);
		box1.add(Box.createHorizontalStrut(20));
		JLabel lblMaxe = new JLabel("M?? xe");
		box1.add(lblMaxe);
		box1.add(Box.createHorizontalStrut(10));
		txtMaxe = new JTextField();
		box1.add(txtMaxe);
		box1.add(Box.createHorizontalStrut(20));

		/// line 2
		Box box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(20));
		JLabel lblTenxe = new JLabel("T??n xe");
		box2.add(lblTenxe);
		box2.add(Box.createHorizontalStrut(20));
		txtTenxe = new JTextField();
		box2.add(txtTenxe);
		
		box2.add(Box.createHorizontalStrut(20));
		JLabel lblThongtinBH = new JLabel("Th??ng tin BH");
		box2.add(lblThongtinBH);
		box2.add(Box.createHorizontalStrut(20));
		txtThongtinBH = new JTextField();
		box2.add(txtThongtinBH);
		

		boxNorth.add(box1);
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(box2);
		boxNorth.add(Box.createVerticalStrut(10));
		// south
		Box boxCenter = Box.createHorizontalBox();

		String[] header = { "M?? b???o h??nh", "M?? xe", "T??n xe", "Th??ng tin b???o h??nh" };
		modelBaoHanh = new DefaultTableModel(header, 5);
		tableBaoHanh = new JTable(modelBaoHanh);
		tableBaoHanh.setRowHeight(25);
		tableBaoHanh.setFont(NORMAL_FONT);
		JScrollPane pane = new JScrollPane(tableBaoHanh);

		boxCenter.add(pane);

		Box boxEast = Box.createVerticalBox();

		btnThem = new JButton("Th??m",new ImageIcon("Images/add.png"));
		btnSua = new JButton("S???a",new ImageIcon("Images/update.png"));
		btnXoa = new JButton("X??a",new ImageIcon("Images/delete.png"));
		btnXoaTrang = new JButton("X??a tr???ng",new ImageIcon("Images/erase.png"));
		
		Dimension d = new Dimension(150, 80);
		btnThem.setPreferredSize(d);
		btnXoa.setPreferredSize(d);
		btnSua.setPreferredSize(d);
		btnXoaTrang.setPreferredSize(d);
		
		Box boxThem = Box.createHorizontalBox();
		boxThem.add(Box.createHorizontalGlue());
		boxThem.add(btnThem);
		boxThem.add(Box.createHorizontalGlue());
		
		Box boxXoa = Box.createHorizontalBox();
		boxXoa.add(Box.createHorizontalGlue());
		boxXoa.add(btnXoa);
		boxXoa.add(Box.createHorizontalGlue());
		
		Box boxSua = Box.createHorizontalBox();
		boxSua.add(Box.createHorizontalGlue());
		boxSua.add(btnSua);
		boxSua.add(Box.createHorizontalGlue());
		
		Box boxXoaTrang = Box.createHorizontalBox();
		boxXoaTrang.add(Box.createHorizontalGlue());
		boxXoaTrang.add(btnXoaTrang);
		boxXoaTrang.add(Box.createHorizontalGlue());
		
		boxEast.add(boxThem);
		boxEast.add(boxSua);
		boxEast.add(boxXoa);
		boxEast.add(boxXoaTrang);

		// set font
		lblMa.setFont(NORMAL_FONT);
		txtMaBH.setFont(NORMAL_FONT);
		lblMaxe.setFont(NORMAL_FONT);
		txtMaxe.setFont(NORMAL_FONT);
		lblTenxe.setFont(NORMAL_FONT);
		txtTenxe.setFont(NORMAL_FONT);
		lblThongtinBH.setFont(NORMAL_FONT);
		txtThongtinBH.setFont(NORMAL_FONT);
		// items
		btnThem.setFont(NORMAL_FONT);
		btnSua.setFont(NORMAL_FONT);
		btnXoa.setFont(NORMAL_FONT);
		

		this.setLayout(new BorderLayout());
		this.add(pnlNorth, BorderLayout.NORTH);
		this.add(boxCenter, BorderLayout.CENTER);
		this.add(boxEast, BorderLayout.EAST);
		this.setPreferredSize(new Dimension(500, 600));
		
		dsBH = new BaoHanh_DAO();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	

	}

	private void setLookAndFeel() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
					break;

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	

	public void loadDataToTable() {
		
		try {
			deleteDataInTable();
			dsBH.getAll();

			List<BaoHanh> list = dsBH.getAll();
			for (BaoHanh item : list) {
				String[] row = { item.getMaBH(), item.getMaXe(), item.getTenxe(),
						 item.getThongtinBH()};

				modelBaoHanh.addRow(row);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "L???i k???t n???i!", "L???i", JOptionPane.ERROR_MESSAGE);

		}
	}

	private void deleteDataInTable() {
		while (modelBaoHanh.getRowCount() > 0) {
			modelBaoHanh.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			if(o.equals(btnThem))
				themBaoHanh();
			else if(o.equals(btnXoa))
				xoaBaoHanh();
			else if(o.equals(btnSua))
				suaBaoHanh();
			else if(o.equals(btnXoaTrang))
				xoaRong();
				
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	}
	
	
	private void xoaRong() {
		txtMaBH.setText("");
		txtMaxe.setText("");
		txtTenxe.setText("");
		txtThongtinBH.setText("");
		tableBaoHanh.clearSelection();
	}

	


	private void suaBaoHanh() throws SQLException{
		int row = tableBaoHanh.getSelectedRow();
		if(row == - 1)
			JOptionPane.showMessageDialog(this, "Ch???n phi???u b???o h??nh c???n s???a");
		else {
			String MaBH = txtMaBH.getText().trim();
			String MaXe = txtMaxe.getText().trim();
			String Tenxe = txtTenxe.getText().trim();
			String ThongtinBH = txtThongtinBH.getText().trim();
			BaoHanh bh = new BaoHanh(MaBH,MaXe,Tenxe,ThongtinBH);
			if(!bh.getMaBH().equalsIgnoreCase(tableBaoHanh.getValueAt(row, 0).toString()))
				JOptionPane.showMessageDialog(this, "Kh??ng ???????c s???a m?? b???o h??nh!!");
			else {
				if(dsBH.suaTTBH(bh)) {
					loadDataToTable();
					JOptionPane.showMessageDialog(this, "S???a th??nh c??ng");
				}
				else
					JOptionPane.showMessageDialog(this, "S???a kh??ng th??nh c??ng");
			}			
		}	
	}
	
	private void xoaBaoHanh() throws SQLException {
		int row = tableBaoHanh.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Ph???i ch???n phi???u c???n x??a");
		else {
			int replay = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c mu???n x??a d??ng n??y ?",
					"C???nh b??o",JOptionPane.YES_NO_OPTION);
			if(replay == JOptionPane.YES_OPTION) {
				List<BaoHanh> dsbh = dsBH.getAll();
				int rows = tableBaoHanh.getSelectedRow();
				if(rows >= 0 || rows < dsbh.size()) {
					BaoHanh bh = dsbh.get(rows);
					if(dsBH.xoaBaoHanh(bh)) {
						loadDataToTable();
						xoaTrang();
						JOptionPane.showMessageDialog(this, "X??a th??nh c??ng");
					}else
						JOptionPane.showMessageDialog(this, "X??a kh??ng th??nh c??ng");
				}
			}
		}
		
	}

	private void themBaoHanh() { 
		if(validData()) {
			String MaBH = txtMaBH.getText().trim();
			String MaXe = txtMaxe.getText().trim();
			String Tenxe = txtTenxe.getText().trim();
			String ThongtinBH = txtThongtinBH.getText().trim();
			
			BaoHanh bh = new BaoHanh(MaBH,MaXe,Tenxe,ThongtinBH);
			try {
				if(dsBH.themBaoHanh(bh)) {
					loadDataToTable();
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng");
				}
				else
					JOptionPane.showMessageDialog(this, "Th??m th???t b???i");
			} catch (HeadlessException | SQLException e) {
				JOptionPane.showMessageDialog(this, "Tr??ng m??");
			}
		}
		
	}

	private void xoaTrang() {
		txtMaBH.setText("");
		txtMaxe.setText("");
		txtTenxe.setText("");
		txtThongtinBH.setText("");
		
	}

	public void mouseClicked(MouseEvent e) {
		int row = tableBaoHanh.getSelectedRow();
		txtMaBH.setText(tableBaoHanh.getValueAt(row, 1).toString());
		txtMaxe.setText(tableBaoHanh.getValueAt(row, 2).toString());
		txtTenxe.setText(tableBaoHanh.getValueAt(row, 3).toString());
		txtThongtinBH.setText(tableBaoHanh.getValueAt(row, 4).toString());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private boolean validData() {
		String MaBH = txtMaBH.getText().trim();
		String MaXe = txtMaxe.getText().trim();
		String Tenxe= txtTenxe.getText().trim();
		String ThongtinBH = txtThongtinBH.getText().trim();
	
		
		Pattern pattern = Pattern.compile("BH[0-9]{2,5}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(MaBH);
		boolean match_ma = matcher.matches();
		if(MaBH.length() < 1) {
			JOptionPane.showMessageDialog(this, "M?? b???o h??nh kh??ng ???????c ????? tr???ng");
			return false;
		}
		else if(!match_ma){
			JOptionPane.showMessageDialog(this, "M?? b???o h??nh kh??ng ????ng ?????nh d???ng");
			return false;
		}
		else if(Tenxe.length() < 1) {
			JOptionPane.showMessageDialog(this, "T??n kh??ng ???????c ????? tr???ng");
			return false;
		}
		else if(!Tenxe.matches("[\\p{L}a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(this, "T??n kh??ng ch???a s??? v?? k?? t??? ?????c bi???t");
			return false;
		}	
		return true;
	}
	public void setPopupMenu(JPopupMenu popup) {
		txtMaBH.setComponentPopupMenu(popup);
		txtMaxe.setComponentPopupMenu(popup);
		txtTenxe.setComponentPopupMenu(popup);
		txtThongtinBH.setComponentPopupMenu(popup);
	}
}
