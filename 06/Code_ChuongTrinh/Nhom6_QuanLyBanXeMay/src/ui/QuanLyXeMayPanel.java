package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.Box;
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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import dao.XeMay_DAO;
import entity.XeMay;

public class QuanLyXeMayPanel extends JPanel implements ActionListener ,MouseListener{

	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private XeMay_DAO listXeMay;
	private JTextField txtMaXe;
	private JTextField txtLoaiXe;
	private JTextField txtDungTich;
	private JTextField txtTenXe;
	private JTextField txtNuocSanXuat;
	private JTextField txtSoLuongTon;
	private JTextField txtDonGia;
	private JTextField txtMauXe;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JTable tableXeMay;
	private JScrollPane scroll;
	private DefaultTableModel defaultTable;
	private JPanel pnlTitle;
	private JButton btnSearch;
	private JTextField txtSearch;
	private JRadioButton radTimTheoMa;
	private JRadioButton radTimTheoHang;
	private JRadioButton radTimTheoDungTich;
	private JComboBox<String> ckbHangXe;
	public QuanLyXeMayPanel() {
		
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());	
		setLookAndFeel();
		addEvent();		
		addNorth();
		addCenter();
		addEast();
		
		listXeMay = new XeMay_DAO();
		tableXeMay.addMouseListener(this);
		btnSearch.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		
	}
	private void addNorth() {
		Box boxNorth, boxSearch, boxLineRad;
		btnSearch = new JButton("", new ImageIcon("Images/search.png"));
		btnSearch.setFont(NORMAL_FONT);
		txtSearch = new JTextField();
		pnlTitle = new JPanel();
		JLabel lblHeader = new JLabel("QU???N L?? XE M??Y");
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);
		
		JLabel lblTimTheoMa = new JLabel("T??m theo m?? xe");
		lblTimTheoMa.setFont(NORMAL_FONT);
		radTimTheoMa = new JRadioButton();
		JLabel lblTimTheoHang = new JLabel("T??m theo h??ng xe");
		lblTimTheoHang.setFont(NORMAL_FONT);
		radTimTheoHang = new JRadioButton();
		JLabel lblTimTheoDungTich = new JLabel("T??m theo dung t??ch");
		lblTimTheoDungTich.setFont(NORMAL_FONT);
		radTimTheoDungTich = new JRadioButton();
		ButtonGroup group = new ButtonGroup();
		group.add(radTimTheoDungTich);
		group.add(radTimTheoHang);
		group.add(radTimTheoMa);
		
		boxLineRad = Box.createHorizontalBox();
		boxLineRad.add(radTimTheoMa);
		radTimTheoMa.setSelected(true);
		boxLineRad.add(lblTimTheoMa);
		boxLineRad.add(Box.createHorizontalStrut(20));
		boxLineRad.add(radTimTheoHang);
		boxLineRad.add(lblTimTheoHang);
		boxLineRad.add(Box.createHorizontalStrut(20));
		boxLineRad.add(radTimTheoDungTich);
		boxLineRad.add(lblTimTheoDungTich);
		
		boxSearch = Box.createHorizontalBox();
		boxSearch.add(Box.createHorizontalStrut(20));
		boxSearch.add(txtSearch);
		boxSearch.add(Box.createHorizontalStrut(5));
		boxSearch.add(btnSearch);
		boxSearch.add(Box.createHorizontalStrut(20));
		
		boxNorth = Box.createVerticalBox();
		pnlTitle.add(lblHeader);
		boxNorth.add(pnlTitle); 
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxSearch);
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxLineRad); 
		boxNorth.add(Box.createVerticalStrut(20));
		
		Box boxLeft, boxRight, boxGroup;
		
		JLabel lblHangXe = new JLabel("H??ng xe");
		
		txtMaXe = new JTextField();
		txtTenXe = new JTextField();
		txtLoaiXe = new JTextField();
		txtDungTich = new JTextField();
		txtMauXe = new JTextField();
		txtNuocSanXuat = new JTextField();
		txtSoLuongTon = new JTextField();
		txtDonGia = new JTextField();

		
		ckbHangXe = new JComboBox<String>();
		ckbHangXe.addItem("Honda");
		ckbHangXe.addItem("Yamaha");
		ckbHangXe.addItem("Suzuki");
		ckbHangXe.addItem("Piaggio");
		ckbHangXe.addItem("SYM");
		ckbHangXe.addItem("Kawasaki");
		
		boxLeft = Box.createVerticalBox();
		boxRight = Box.createVerticalBox();
		boxGroup = Box.createHorizontalBox();
		
		txtMaXe = addInputItemTo(boxLeft, "M?? xe");
		txtTenXe = addInputItemTo(boxRight, "T??n xe");
		txtLoaiXe = addInputItemTo(boxLeft, "Lo???i xe");
		txtNuocSanXuat = addInputItemTo(boxRight, "N?????c s???n xu???t");
		txtDungTich = addInputItemTo(boxLeft, "Dung t??ch");
		txtMauXe = addInputItemTo(boxRight, "M??u xe");
		txtSoLuongTon = addInputItemTo(boxLeft, "S??? l?????ng t???n");
		txtDonGia = addInputItemTo(boxRight, "????n gi??");
		
		Box boxCkb;
		boxCkb = Box.createHorizontalBox();
		boxCkb.add(Box.createHorizontalGlue());
		boxCkb.add(lblHangXe);
		boxCkb.add(Box.createHorizontalStrut(5));
		boxCkb.add(ckbHangXe);
		boxCkb.add(Box.createHorizontalGlue());
		
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.add(boxLeft);
		boxGroup.add(Box.createHorizontalStrut(10));
		boxGroup.add(boxRight);
		boxGroup.add(Box.createHorizontalStrut(20));
		boxNorth.add(boxGroup);
		boxNorth.add(boxCkb);
		boxNorth.add(Box.createVerticalStrut(20));
		
		this.add(boxNorth, BorderLayout.NORTH);
	}
	private void addCenter() {
		
		String[] header = {"M?? xe", "T??n xe", "Lo???i xe", "H??ng xe", "Dung t??ch", "M??u xe", "N?????c s???n xu???t", "S??? l?????ng t???n", "????n gi??"};
		defaultTable = new DefaultTableModel(header, 0);
		tableXeMay = new JTable(defaultTable) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableXeMay.setFont(NORMAL_FONT);
		tableXeMay.setRowHeight(25);
		scroll = new JScrollPane(tableXeMay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroll, BorderLayout.CENTER);
	}
	private void addEast() {
		
		Box boxButton;
		
		boxButton = Box.createVerticalBox();
		boxButton.add(Box.createVerticalStrut(50));
		btnThem = addButtonTo(boxButton, "Th??m", "Images/add.png");
		btnSua = addButtonTo(boxButton, "S???a", "Images/update.png");
		btnXoa = addButtonTo(boxButton, "Xo??", "Images/delete.png");
		btnXoaTrang = addButtonTo(boxButton, "Xo?? tr???ng", "Images/erase.png");
		
		this.add(boxButton, BorderLayout.EAST);
	}
	public void focus() {
		txtSearch.requestFocus();
	}
	
	private void addEvent() {
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			if(o.equals(btnThem))
				themXeMay();
			else if(o.equals(btnXoa))
				xoaXeMay();
			else if(o.equals(btnSua))
				suaTTXeMay();
			else if(o.equals(btnSearch) && radTimTheoDungTich.isSelected())
				timTheoDungTich();
			else if(o.equals(btnSearch) && radTimTheoHang.isSelected())
				timTheoHangXe();
			else if(o.equals(btnSearch) && radTimTheoMa.isSelected())
				timTheoMa();
			
			else
				xoaTrang();
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
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
	private JButton addButtonTo(Box box, String name, String iconPath) {
		JButton btn = new JButton(name, new ImageIcon(iconPath));
		btn.setFont(NORMAL_FONT);
		btn.setPreferredSize(new Dimension(150, 25));

		Box boxButton = Box.createHorizontalBox();
		boxButton.add(Box.createHorizontalGlue());
		boxButton.add(btn);
		boxButton.add(Box.createHorizontalGlue());

		box.add(Box.createVerticalStrut(5));
		box.add(boxButton);
		box.add(Box.createVerticalStrut(5));
		
		return btn;
	}
	private JTextField addInputItemTo(Box box, String name) {
		JLabel label = new JLabel(name); label.setFont(NORMAL_FONT); label.setPreferredSize(new Dimension(90, 25));
		JTextField text = new JTextField(); text.setFont(NORMAL_FONT);
		
		Box boxItem = Box.createHorizontalBox();
		boxItem.add(Box.createHorizontalGlue());
		boxItem.add(label);
		boxItem.add(Box.createHorizontalStrut(5));
		boxItem.add(text);
		boxItem.add(Box.createHorizontalGlue());
		
		box.add(Box.createVerticalStrut(5));
		box.add(boxItem);
		box.add(Box.createVerticalStrut(5));
		
		return text;
	}
	public void loadDataToTable() {
		try {
			deleteDataInTable();
			NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
			
			List<XeMay> dsxm = listXeMay.getAll();
			for (XeMay xeMay : dsxm) {
				String donGia = nf.format(xeMay.getDonGia());
				defaultTable.addRow(new Object[] {
					xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
					xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
					xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});		
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "L???i k???t n???i!", "L???i", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void deleteDataInTable() {
		while(defaultTable.getRowCount() > 0) {
			defaultTable.removeRow(0);
		}
	}
	public void setPopupMenu(JPopupMenu popup) {
		txtMaXe.setComponentPopupMenu(popup);
		txtTenXe.setComponentPopupMenu(popup);
		txtLoaiXe.setComponentPopupMenu(popup);
		txtNuocSanXuat.setComponentPopupMenu(popup);
		txtDungTich.setComponentPopupMenu(popup);
		txtMauXe.setComponentPopupMenu(popup);
		txtSoLuongTon.setComponentPopupMenu(popup);
		txtDonGia.setComponentPopupMenu(popup);
		txtSearch.setComponentPopupMenu(popup);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int row = tableXeMay.getSelectedRow();
		String temp = defaultTable.getValueAt(row, 8).toString();
		temp = temp.replace(",","");
		txtMaXe.setText(defaultTable.getValueAt(row, 0).toString());
		txtTenXe.setText(defaultTable.getValueAt(row, 1).toString());
		txtLoaiXe.setText(defaultTable.getValueAt(row, 2).toString());
		ckbHangXe.setSelectedItem(defaultTable.getValueAt(row, 3).toString());
		txtDungTich.setText(defaultTable.getValueAt(row, 4).toString());
		txtMauXe.setText(defaultTable.getValueAt(row, 5).toString());
		txtNuocSanXuat.setText(defaultTable.getValueAt(row, 6).toString());
		txtSoLuongTon.setText(defaultTable.getValueAt(row, 7).toString());
		txtDonGia.setText(temp);
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
	private void themXeMay() throws SQLException {
		if(validData()) {
			XeMay xm = new XeMay(txtMaXe.getText().trim(),
					txtTenXe.getText().trim(), txtLoaiXe.getText().trim(),
					ckbHangXe.getSelectedItem().toString(), Integer.parseInt(txtDungTich.getText()),
					txtMauXe.getText().trim(), txtNuocSanXuat.getText().trim(),
					Integer.parseInt(txtSoLuongTon.getText()), Double.parseDouble(txtDonGia.getText()));
			if(listXeMay.themXeMay(xm)) {
				loadDataToTable();
				xoaTrang();
				JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			}else 
				JOptionPane.showMessageDialog(this, "Th??m kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
		}else
			return;
	}
	private void xoaXeMay() throws SQLException{
		int row = tableXeMay.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Ph???i ch???n xe c???n xo??", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
		else {
			int replay = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c mu???n xo?? d??ng n??y!!", "C???nh b??o", JOptionPane.YES_NO_OPTION);
			if(replay == JOptionPane.YES_OPTION) {
				List<XeMay> dsxm = listXeMay.getAll();
				int rows = tableXeMay.getSelectedRow();
				if(rows >=0 || rows < dsxm.size()) {
					XeMay xm = dsxm.get(rows);
					if(listXeMay.xoaXeMay(xm)) {
						loadDataToTable();
						xoaTrang();
						JOptionPane.showMessageDialog(this, "Xo?? th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					}else
						JOptionPane.showMessageDialog(this, "Xo?? kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			return;
		}
	}
	private void suaTTXeMay() throws SQLException {
		int row = tableXeMay.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Ph???i ch???n xe c???n s???a", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
		else {
				if(validData()) {
					XeMay xm = new XeMay(txtMaXe.getText().trim(),
							txtTenXe.getText().trim(), txtLoaiXe.getText().trim(),
							ckbHangXe.getSelectedItem().toString(), Integer.parseInt(txtDungTich.getText()),
							txtMauXe.getText().trim(), txtNuocSanXuat.getText().trim(),
							Integer.parseInt(txtSoLuongTon.getText()), Double.parseDouble(txtDonGia.getText()));
					if(!xm.getMaXe().equalsIgnoreCase(defaultTable.getValueAt(row, 0).toString())) {
						JOptionPane.showMessageDialog(this, "Kh??ng ???????c s???a m?? xe m??y!!", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
						txtMaXe.setText(defaultTable.getValueAt(row, 0).toString());
					}
					else {
						if(listXeMay.suaTTXeMay(xm)) {
							loadDataToTable();
							JOptionPane.showMessageDialog(this, "S???a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					}else 
						JOptionPane.showMessageDialog(this, "S???a kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					}
				}
		}
	}
	private void xoaTrang() {
		txtMaXe.setText("");
		txtTenXe.setText("");
		txtLoaiXe.setText("");
		ckbHangXe.setSelectedItem("");
		txtDungTich.setText("");
		txtMauXe.setText("");
		txtNuocSanXuat.setText("");
		txtSoLuongTon.setText("");
		txtDonGia.setText("");
		txtMaXe.requestFocus();
	}
	private void timTheoMa() throws SQLException{
		if(txtSearch.getText().equals(""))
			loadDataToTable();
		else {
			XeMay xeMay = new XeMay();
			xeMay = listXeMay.timTheoMa(txtSearch.getText());
			if(xeMay == null)
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			else {
				deleteDataInTable();
				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
				String donGia = nf.format(xeMay.getDonGia());
				defaultTable.addRow(new Object[] {
						xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
						xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
						xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});
			}
		}
	}
	private void timTheoDungTich() throws SQLException {
		if(txtSearch.getText().equals(""))
			loadDataToTable();
		else {
			List<XeMay> dsxm = new ArrayList<XeMay>();
			dsxm = listXeMay.timTheoDungTich(Integer.parseInt(txtSearch.getText()));
			if(dsxm == null)
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			else {
				deleteDataInTable();
				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
				for (XeMay xeMay : dsxm) {
					String donGia = nf.format(xeMay.getDonGia());
					defaultTable.addRow(new Object[] {
							xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
							xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
							xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});
				}
			}
		}
	}
	private void timTheoHangXe() throws SQLException {
		if(txtSearch.getText().equals(""))
			loadDataToTable();
		else {
			List<XeMay> dsxm = new ArrayList<XeMay>();
			dsxm = listXeMay.timTheoHangXe(txtSearch.getText());
			if(dsxm == null)
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			else {
				deleteDataInTable();
				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
				for (XeMay xeMay : dsxm) {
					String donGia = nf.format(xeMay.getDonGia());
					defaultTable.addRow(new Object[] {
							xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
							xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
							xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});
				}
			}
		}
	}
	private boolean validData() {
		String maxe = txtMaXe.getText().trim();
		String tenxe = txtTenXe.getText().trim();
		String loaixe = txtLoaiXe.getText().trim();
		String dungtich = txtDungTich.getText().trim();
		String mauxe = txtMauXe.getText().trim();
		String nuocsanxuat = txtNuocSanXuat.getText().trim();
		String soluongton = txtSoLuongTon.getText().trim();
		String dongia = txtDonGia.getText().trim();
		
		//M?? xe kh??ng ???????c b??? tr???ng v?? ph???i b???t ?????u b???ng 'XM', theo sau l?? 2 ?????n 3 k?? t??? s???! 
		if(!(!maxe.isEmpty() && maxe.matches("XM\\d{2,3}"))) {
			JOptionPane.showMessageDialog(this,
					"M?? xe kh??ng ???????c b??? tr???ng v?? ph???i b???t ?????u b???ng 'XM', theo sau l?? 2 ?????n 3 k?? t??? s???!",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtMaXe.requestFocus();
			return false;
		}
		
		//T??n xe kh??ng ???????c b??? tr???ng, c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng
		//Kh??ng ch???a k?? t??? ?????c bi???t
		if(!(!tenxe.isEmpty() && tenxe.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"T??n xe kh??ng ???????c b??? tr???ng. T??n xe c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng v?? kh??ng ch???a k?? t??? ?????c bi???t",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtTenXe.requestFocus();
			return false;
		}
		
		//Lo???i xe kh??ng ???????c b??? tr???ng, c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng
		//Kh??ng ch???a k?? t??? ?????c bi???t
		if(!(!loaixe.isEmpty() && loaixe.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Lo???i xe kh??ng ???????c b??? tr???ng. Lo???i xe c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng v?? kh??ng ch???a k?? t??? ?????c bi???t",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtLoaiXe.requestFocus();
			return false;
		}
		
		//Dung t??ch kh??ng ???????c b??? tr???ng, dung t??ch ph???i l???n h??n 0
		if(!dungtich.isEmpty()) {
			try {
				int dt = Integer.parseInt(dungtich);
				if(dt < 0) {
					JOptionPane.showMessageDialog(this, "Dung t??ch ph???i l???n h??n ho???c b???ng 0", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Dung t??ch ph???i nh???p s???", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(this, "Dung t??ch kh??ng ???????c b??? tr???ng", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		//M??u xe kh??ng ???????c b??? tr???ng, c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng
		//Kh??ng ch???a k?? t??? ?????c bi???t
		if(!(!mauxe.isEmpty() && mauxe.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"M??u xe kh??ng ???????c b??? tr???ng. M??u xe c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng v?? kh??ng ch???a k?? t??? ?????c bi???t",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtMauXe.requestFocus();
			return false;
		}
		
		//N?????c s???n xu???t kh??ng ???????c b??? tr???ng, c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng
		//Kh??ng ch???a k?? t??? ?????c bi???t
		if(!(!nuocsanxuat.isEmpty() && nuocsanxuat.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"N?????c s???n xu???t kh??ng ???????c b??? tr???ng. N?????c s???n xu???t c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng v?? kh??ng ch???a k?? t??? ?????c bi???t",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtNuocSanXuat.requestFocus();
			return false;
		}
		
		//S??? l?????ng t???n kh??ng ???????c b??? tr???ng, s??? l?????ng t???n ph???i l???n h??n ho???c b???ng 0
		if(!soluongton.isEmpty()) {
			try {
				int slt = Integer.parseInt(soluongton);
				if(slt < 0) {
					JOptionPane.showMessageDialog(this, "S??? l?????ng t???n ph???i l???n h??n ho???c b???ng 0", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "S??? l?????ng t???n ph???i nh???p s???", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(this, "S??? l?????ng t???n kh??ng ???????c b??? tr???ng", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		//????n gi?? kh??ng ???????c b??? tr???ng, ????n gi?? ph???i l???n h??n ho???c b???ng 0
		if(!dongia.isEmpty()) {
			try {
				double dg = Double.parseDouble(dongia);
				if(dg < 0) {
					JOptionPane.showMessageDialog(this, "????n gi?? ph???i l???n h??n ho???c b???ng 0", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "????n gi?? ph???i nh???p s???", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(this, "????n gi?? kh??ng ???????c b??? tr???ng", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
}
