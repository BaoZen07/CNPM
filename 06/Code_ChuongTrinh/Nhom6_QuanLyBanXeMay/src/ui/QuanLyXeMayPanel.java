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
		JLabel lblHeader = new JLabel("QUẢN LÝ XE MÁY");
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);
		
		JLabel lblTimTheoMa = new JLabel("Tìm theo mã xe");
		lblTimTheoMa.setFont(NORMAL_FONT);
		radTimTheoMa = new JRadioButton();
		JLabel lblTimTheoHang = new JLabel("Tìm theo hãng xe");
		lblTimTheoHang.setFont(NORMAL_FONT);
		radTimTheoHang = new JRadioButton();
		JLabel lblTimTheoDungTich = new JLabel("Tìm theo dung tích");
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
		
		JLabel lblHangXe = new JLabel("Hãng xe");
		
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
		
		txtMaXe = addInputItemTo(boxLeft, "Mã xe");
		txtTenXe = addInputItemTo(boxRight, "Tên xe");
		txtLoaiXe = addInputItemTo(boxLeft, "Loại xe");
		txtNuocSanXuat = addInputItemTo(boxRight, "Nước sản xuất");
		txtDungTich = addInputItemTo(boxLeft, "Dung tích");
		txtMauXe = addInputItemTo(boxRight, "Màu xe");
		txtSoLuongTon = addInputItemTo(boxLeft, "Số lượng tồn");
		txtDonGia = addInputItemTo(boxRight, "Đơn giá");
		
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
		
		String[] header = {"Mã xe", "Tên xe", "Loại xe", "Hãng xe", "Dung tích", "Màu xe", "Nước sản xuất", "Số lượng tồn", "Đơn giá"};
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
		btnThem = addButtonTo(boxButton, "Thêm", "Images/add.png");
		btnSua = addButtonTo(boxButton, "Sửa", "Images/update.png");
		btnXoa = addButtonTo(boxButton, "Xoá", "Images/delete.png");
		btnXoaTrang = addButtonTo(boxButton, "Xoá trắng", "Images/erase.png");
		
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
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}else 
				JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}else
			return;
	}
	private void xoaXeMay() throws SQLException{
		int row = tableXeMay.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn xe cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		else {
			int replay = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá dòng này!!", "Cảnh báo", JOptionPane.YES_NO_OPTION);
			if(replay == JOptionPane.YES_OPTION) {
				List<XeMay> dsxm = listXeMay.getAll();
				int rows = tableXeMay.getSelectedRow();
				if(rows >=0 || rows < dsxm.size()) {
					XeMay xm = dsxm.get(rows);
					if(listXeMay.xoaXeMay(xm)) {
						loadDataToTable();
						xoaTrang();
						JOptionPane.showMessageDialog(this, "Xoá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}else
						JOptionPane.showMessageDialog(this, "Xoá không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			return;
		}
	}
	private void suaTTXeMay() throws SQLException {
		int row = tableXeMay.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn xe cần sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		else {
				if(validData()) {
					XeMay xm = new XeMay(txtMaXe.getText().trim(),
							txtTenXe.getText().trim(), txtLoaiXe.getText().trim(),
							ckbHangXe.getSelectedItem().toString(), Integer.parseInt(txtDungTich.getText()),
							txtMauXe.getText().trim(), txtNuocSanXuat.getText().trim(),
							Integer.parseInt(txtSoLuongTon.getText()), Double.parseDouble(txtDonGia.getText()));
					if(!xm.getMaXe().equalsIgnoreCase(defaultTable.getValueAt(row, 0).toString())) {
						JOptionPane.showMessageDialog(this, "Không được sửa mã xe máy!!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
						txtMaXe.setText(defaultTable.getValueAt(row, 0).toString());
					}
					else {
						if(listXeMay.suaTTXeMay(xm)) {
							loadDataToTable();
							JOptionPane.showMessageDialog(this, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}else 
						JOptionPane.showMessageDialog(this, "Sửa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		
		//Mã xe không được bỏ trống và phải bắt đầu bằng 'XM', theo sau là 2 đến 3 kí tự số! 
		if(!(!maxe.isEmpty() && maxe.matches("XM\\d{2,3}"))) {
			JOptionPane.showMessageDialog(this,
					"Mã xe không được bỏ trống và phải bắt đầu bằng 'XM', theo sau là 2 đến 3 kí tự số!",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtMaXe.requestFocus();
			return false;
		}
		
		//Tên xe không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Không chứa ký tự đặc biệt
		if(!(!tenxe.isEmpty() && tenxe.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Tên xe không được bỏ trống. Tên xe có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtTenXe.requestFocus();
			return false;
		}
		
		//Loại xe không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Không chứa ký tự đặc biệt
		if(!(!loaixe.isEmpty() && loaixe.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Loại xe không được bỏ trống. Loại xe có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtLoaiXe.requestFocus();
			return false;
		}
		
		//Dung tích không được bỏ trống, dung tích phải lớn hơn 0
		if(!dungtich.isEmpty()) {
			try {
				int dt = Integer.parseInt(dungtich);
				if(dt < 0) {
					JOptionPane.showMessageDialog(this, "Dung tích phải lớn hơn hoặc bằng 0", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Dung tích phải nhập số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(this, "Dung tích không được bỏ trống", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		//Màu xe không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Không chứa ký tự đặc biệt
		if(!(!mauxe.isEmpty() && mauxe.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Màu xe không được bỏ trống. Màu xe có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtMauXe.requestFocus();
			return false;
		}
		
		//Nước sản xuất không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Không chứa ký tự đặc biệt
		if(!(!nuocsanxuat.isEmpty() && nuocsanxuat.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Nước sản xuất không được bỏ trống. Nước sản xuất có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtNuocSanXuat.requestFocus();
			return false;
		}
		
		//Số lượng tồn không được bỏ trống, số lượng tồn phải lớn hơn hoặc bằng 0
		if(!soluongton.isEmpty()) {
			try {
				int slt = Integer.parseInt(soluongton);
				if(slt < 0) {
					JOptionPane.showMessageDialog(this, "Số lượng tồn phải lớn hơn hoặc bằng 0", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Số lượng tồn phải nhập số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(this, "Số lượng tồn không được bỏ trống", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		//Đơn giá không được bỏ trống, đơn giá phải lớn hơn hoặc bằng 0
		if(!dongia.isEmpty()) {
			try {
				double dg = Double.parseDouble(dongia);
				if(dg < 0) {
					JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn hoặc bằng 0", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Đơn giá phải nhập số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(this, "Đơn giá không được bỏ trống", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
}
