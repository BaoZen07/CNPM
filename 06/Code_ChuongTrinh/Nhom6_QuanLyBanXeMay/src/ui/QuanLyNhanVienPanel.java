/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import entity.NhanVien;
import dao.NhanVien_DAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class QuanLyNhanVienPanel extends JPanel implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnTim;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JTable tableNhanVien;
	private DefaultTableModel tableModel;
	private JLabel lblHeader, lblMaNV, lblHoTen, lblGioiTinh, lblDiaChi, lblSDT, lblEmail, lblQuanLyVien;
	private JTextField txtTim, txtMaNV, txtHoTen, txtDiaChi, txtSDT, txtEmail;
	private JCheckBox chkQuanLyVien;
	private JComboBox<String> jcbGioiTinh;
	private JRadioButton radTimTheoMa;
	private JRadioButton radTimTheoTen;
	private ButtonGroup buttonGroup;
	private NhanVien_DAO dsNV = new NhanVien_DAO();
	private JButton btnXoaTrang;

	public QuanLyNhanVienPanel() {
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());
		setLookAndFeel();
		addNorth();
		addCenter();
		addEast();
		loadDataToTable();
		dsNV = new NhanVien_DAO();
		tableNhanVien.addMouseListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	private void addNorth() {
		Box boxNorth = Box.createVerticalBox();
		add(boxNorth, BorderLayout.NORTH);
		Box boxHeader = Box.createHorizontalBox();
		boxNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		boxNorth.add(boxHeader);
		boxHeader.add(lblHeader = new JLabel("QU???N L?? NH??N VI??N"));
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);
		boxNorth.add(Box.createVerticalStrut(10));

		Box boxTim = Box.createHorizontalBox();
		boxTim.add(Box.createHorizontalGlue());
		boxTim.add(Box.createHorizontalStrut(20));
		boxTim.add(txtTim = new JTextField());
		boxTim.add(Box.createHorizontalStrut(5));
		boxTim.add(btnTim = new JButton("T??m"));
		btnTim.setIcon(new ImageIcon("Images/search.png"));
		boxNorth.add(boxTim);
		boxNorth.add(Box.createVerticalStrut(10));

		JPanel pnlTimTuyChon = new JPanel();
		pnlTimTuyChon.setLayout(new FlowLayout(FlowLayout.CENTER));
		radTimTheoMa = new JRadioButton("T??m theo m??");
		radTimTheoMa.setSelected(true);
		radTimTheoTen = new JRadioButton("T??m theo t??n");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radTimTheoMa);
		buttonGroup.add(radTimTheoTen);
		pnlTimTuyChon.add(radTimTheoMa);
		pnlTimTuyChon.add(Box.createHorizontalStrut(20));
		pnlTimTuyChon.add(radTimTheoTen);
		boxNorth.add(pnlTimTuyChon);
		boxNorth.add(Box.createVerticalStrut(30));

		Box boxNhap = Box.createVerticalBox();
		Box box1 = Box.createHorizontalBox();
		box1.add(lblMaNV = new JLabel("M?? nh??n vi??n:"));
		box1.add(Box.createHorizontalStrut(20));
		box1.add(txtMaNV = new JTextField(25));
		box1.add(Box.createHorizontalStrut(20));
		box1.add(lblHoTen = new JLabel("H??? t??n NV:"));
		box1.add(Box.createHorizontalStrut(20));
		box1.add(txtHoTen = new JTextField(25));

		Box box2 = Box.createHorizontalBox();
		box2.add(lblDiaChi = new JLabel("?????a ch???:"));
		box2.add(Box.createHorizontalStrut(20));
		box2.add(txtDiaChi = new JTextField());
		box2.add(Box.createHorizontalStrut(20));
		box2.add(lblGioiTinh = new JLabel("Gi???i t??nh"));
		String[] items = { "Nam", "N???" };
		jcbGioiTinh = new JComboBox<String>(items);
		box2.add(jcbGioiTinh);
		box2.add(Box.createHorizontalStrut(40));

		Box box3 = Box.createHorizontalBox();
		box3.add(lblSDT = new JLabel("S??T:"));
		box3.add(Box.createHorizontalStrut(20));
		box3.add(txtSDT = new JTextField(25));
		box3.add(Box.createHorizontalStrut(20));
		box3.add(lblEmail = new JLabel("Email:"));
		box3.add(Box.createHorizontalStrut(20));
		box3.add(txtEmail = new JTextField(25));
		// box3.add(Box.createHorizontalStrut(20));

		/*
		 * Box box4 = Box.createHorizontalBox(); box4.add(lblQuanLyVien = new
		 * JLabel("Qu???n l?? vi??n:")); box4.add(Box.createHorizontalStrut(20));
		 * box4.add(cbQuanLyVien = new JCheckBox(""));
		 */
		JPanel pnQuanLyVien = new JPanel();
		pnQuanLyVien.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnQuanLyVien.add(lblQuanLyVien = new JLabel("Qu???n l?? vi??n:"));
		pnQuanLyVien.add(Box.createHorizontalStrut(8));
		pnQuanLyVien.add(chkQuanLyVien = new JCheckBox(""));

		boxNhap.add(box1);
		boxNhap.add(Box.createVerticalStrut(10));
		boxNhap.add(box2);
		boxNhap.add(Box.createVerticalStrut(10));
		boxNhap.add(box3);
		boxNhap.add(Box.createVerticalStrut(10));
		boxNhap.add(pnQuanLyVien);
		boxNorth.add(boxNhap);

		lblDiaChi.setPreferredSize(lblMaNV.getPreferredSize());
		lblEmail.setPreferredSize(lblMaNV.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblHoTen.setPreferredSize(lblMaNV.getPreferredSize());
		lblSDT.setPreferredSize(lblMaNV.getPreferredSize());
		lblQuanLyVien.setPreferredSize(lblMaNV.getPreferredSize());

	}

	private void addCenter() {
		String[] headers = { "M?? NV", "H??? t??n NV", "Gi???i t??nh", "?????a ch???", "SDT", "Email", "Ch???c v???" };
		tableModel = new DefaultTableModel(headers, 0);
		tableNhanVien = new JTable(tableModel);
		tableNhanVien.setRowHeight(25);
		JScrollPane sp = new JScrollPane(tableNhanVien);
		sp.setPreferredSize(new Dimension(sp.getPreferredSize().width, 300));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(sp, BorderLayout.CENTER);

	}

	private void xoaTrang() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		jcbGioiTinh.setSelectedItem("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		chkQuanLyVien.isSelected();

	}

	private void addEast() {
		Box boxEast = Box.createVerticalBox();
		this.add(boxEast, BorderLayout.EAST);
		boxEast.add(Box.createVerticalStrut(100));
		btnThem = addButtonTo(boxEast, "Th??m nh??n vi??n");
		btnThem.setIcon(new ImageIcon("Images/add.png"));
		boxEast.add(Box.createVerticalStrut(10));
		btnSua = addButtonTo(boxEast, "S???a nh??n vi??n");
		btnSua.setIcon(new ImageIcon("Images/update.png"));
		boxEast.add(Box.createVerticalStrut(10));
		btnXoa = addButtonTo(boxEast, "Xo?? nh??n vi??n");
		btnXoa.setIcon(new ImageIcon("Images/delete.png"));
		boxEast.add(Box.createVerticalStrut(10));
		btnXoaTrang = addButtonTo(boxEast, "Xo?? tr???ng");
		btnXoaTrang.setIcon(new ImageIcon("Images/erase.png"));
		boxEast.add(Box.createVerticalStrut(10));
	}

	private JButton addButtonTo(Box box, String name) {
		JButton btn = new JButton(name);
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

	private void setLookAndFeel() {
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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

	public void focus() {
		txtTim.requestFocus();
	}

	public void loadDataToTable() {
		deleDataInTable();
		try {
			dsNV.getAll();
			ArrayList<NhanVien> list = dsNV.getDsNV();

			for (NhanVien nhanVien : list) {
				String[] row = { nhanVien.getMaNV(), nhanVien.getHoTenNV(), nhanVien.isGioiTinh() ? "Nam" : "N???",
						nhanVien.getDiaChi(), nhanVien.getSDT(), nhanVien.getEmail(),
						nhanVien.isQuanLyVien() ? "Qu???n l?? vi??n" : "Nh??n vi??n b??n h??ng" };
				tableModel.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "L???i k???t n???i", "L???i", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void deleDataInTable() {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}

	private void themNhanVien() {
		if (validData()) {
			NhanVien nv = new NhanVien(txtMaNV.getText().trim(), txtHoTen.getText().trim(),
					jcbGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false, txtDiaChi.getText().trim(),
					txtSDT.getText().trim(), txtEmail.getText().trim(), chkQuanLyVien.isSelected());

			try {
				if (dsNV.themNhanVien(nv)) {
					String[] st = { nv.getMaNV(), nv.getHoTenNV(), nv.isGioiTinh() ? "Nam" : "N???", nv.getDiaChi(),
							nv.getSDT(), nv.getEmail(), nv.isQuanLyVien() ? "Qu???n l?? vi??n" : "Nh??n vi??n b??n h??ng" };
					tableModel.addRow(st);
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng");
				} else
					JOptionPane.showMessageDialog(this, "Th??m kh??ng th??nh c??ng");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this, "Tr??ng m?? Nh??n Vi??n");
			}
		}
	}

	private void SuaTTNhanVien() throws SQLException {
		int row = tableNhanVien.getSelectedRow();

		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Ph???i ch???n Nh??n vi??n c???n s???a");
		} else {
			NhanVien nv = new NhanVien(txtMaNV.getText().trim(), txtHoTen.getText().trim(),
					jcbGioiTinh.getSelectedItem() == "Nam" ? true : false, txtDiaChi.getText().trim(),
					txtSDT.getText().trim(), txtEmail.getText().trim(), chkQuanLyVien.isSelected());
			if (!nv.getMaNV().equalsIgnoreCase(tableNhanVien.getValueAt(row, 0).toString()))
				JOptionPane.showMessageDialog(this, "Kh??ng ???????c s???a m?? Nh??n vi??n");
			else {
				if (dsNV.suaTTNhanVien(nv)) {

					tableModel.setValueAt(nv.getMaNV(), row, 0);
					tableModel.setValueAt(nv.getHoTenNV(), row, 1);
					tableModel.setValueAt(nv.isGioiTinh() ? "Nam" : "N???", row, 2);
					tableModel.setValueAt(nv.getDiaChi(), row, 3);
					tableModel.setValueAt(nv.getSDT(), row, 4);
					tableModel.setValueAt(nv.getEmail(), row, 5);
					tableModel.setValueAt(nv.isQuanLyVien() ? "Qu???n l?? vi??n" : "Nh??n vi??n b??n h??ng", row, 6);

					JOptionPane.showMessageDialog(this, "S???a th??nh c??ng");

				} else {
					JOptionPane.showMessageDialog(this, "S???a kh??ng th??nh c??ng");
				}
			}
		}
	}

	private void timTheoTen() throws SQLException {
		if (txtTim.getText().trim().equals(""))
			loadDataToTable();
		else {
			ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
			dsnv = dsNV.timTheoTenNhanVien(txtTim.getText().trim());
			if (dsnv == null) {
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y Nh??n Vi??n");
			} else {
				deleteDataInTable();
				for (NhanVien nhanVien : dsnv) {
					tableModel.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getHoTenNV(),
							nhanVien.isGioiTinh() ? "Nam" : "N???", nhanVien.getDiaChi(), nhanVien.getSDT(),
							nhanVien.getEmail(), nhanVien.isQuanLyVien() ? "Qu???n l?? vi??n" : "Nh??n vi??n b??n h??ng" });
				}
			}
		}

	}

	private void xoaNhanVien() {
		int selectedRow = tableNhanVien.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
					"B???n ch??a ch???n Nh??n Vi??n c???n x??a, nh???p ch???n v??o Nh??n Vi??n c???n x??a trong b???ng!", "Th??ng b??o",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "B???n c?? ch???c ch???n mu???n x??a!", "X??c nh???n",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;

		int count = 0;
		while (tableNhanVien.getSelectedRow() != -1) {
			int selected = tableNhanVien.getSelectedRow();
			String maNV = (String) tableNhanVien.getValueAt(selected, 0);

			try {
				if (dsNV.xoaNhanVien(maNV)) {
					tableModel.removeRow(selected);
					count++;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "L???i trong khi x??a!", "L???i", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		JOptionPane.showMessageDialog(this, "X??a th??nh c??ng " + count + " Nh??n Vi??n!", "Th??ng b??o",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private boolean validData() {
		String maNV = txtMaNV.getText().trim();
		String hoTenNV = txtHoTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String SDT = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();

		Pattern pattern = Pattern.compile("NV[0-9]{2,3}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(maNV);
		boolean match_maNV = matcher.matches();
		if (maNV.length() < 1) {
			JOptionPane.showMessageDialog(this, "M?? kh??ch h??ng kh??ng ???????c ????? tr???ng");
			return false;
		} else if (!match_maNV) {
			JOptionPane.showMessageDialog(this, "M?? kh??ch h??ng kh??ng ????ng ?????nh d???ng");
			return false;
		} else if (hoTenNV.length() < 1) {
			JOptionPane.showMessageDialog(this, "T??n kh??ng ???????c ????? tr???ng");
			return false;
		} else if (!hoTenNV.matches("[\\p{L}a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(this, "T??n kh??ng ch???a s??? v?? k?? t??? ?????c bi???t");
			return false;
		} else if (diaChi.length() < 1) {
			JOptionPane.showMessageDialog(this, "?????a ch??? kh??ng ???????c ????? tr???ng");
			return false;
		} else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			JOptionPane.showMessageDialog(this, "Email kh??ng ????ng ?????nh d???ng");
			return false;
		} else if (!SDT.matches("0[0-9]{9}")) {
			JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng ????ng");
			return false;
		}

		return true;
	}

	private void timTheoMaNhanVien() throws SQLException {
		if (txtTim.getText().trim().equals(""))
			loadDataToTable();
		else {
			NhanVien nv = new NhanVien();
			nv = dsNV.timTheoMaNhanVien(txtTim.getText().trim());
			if (nv == null)
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y Nh??n Vi??n!");
			else {
				deleteDataInTable();
				tableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTenNV(), nv.isGioiTinh() ? "Nam" : "N???",
						nv.getDiaChi(), nv.getSDT(), nv.getEmail(),
						nv.isQuanLyVien() ? "Qu???n l?? vi??n" : "Nh??n vi??n b??n h??ng" });
			}
		}

	}

	private void deleteDataInTable() {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}

	public void setPopupMenu(JPopupMenu popup) {
		txtMaNV.setComponentPopupMenu(popup);
		txtHoTen.setComponentPopupMenu(popup);
		txtDiaChi.setComponentPopupMenu(popup);
		txtSDT.setComponentPopupMenu(popup);
		txtEmail.setComponentPopupMenu(popup);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNhanVien.getSelectedRow();
		txtMaNV.setText(tableNhanVien.getValueAt(row, 0).toString());
		txtHoTen.setText(tableNhanVien.getValueAt(row, 1).toString());
		txtDiaChi.setText(tableNhanVien.getValueAt(row, 3).toString());
		jcbGioiTinh.setSelectedItem(tableNhanVien.getValueAt(row, 2).toString());
		txtSDT.setText(tableNhanVien.getValueAt(row, 4).toString());
		txtEmail.setText(tableNhanVien.getValueAt(row, 5).toString());
		String cv = tableNhanVien.getValueAt(row, 6).toString();
		if (cv == "Qu???n l?? vi??n")
			chkQuanLyVien.setSelected(true);
		else
			chkQuanLyVien.setSelected(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			if (o.equals(btnSua)) {
				SuaTTNhanVien();
			} else if (o.equals(btnXoa)) {
				xoaNhanVien();
			} else if (o.equals(btnThem)) {
				themNhanVien();
			} else if (o.equals(btnTim) && radTimTheoMa.isSelected()) {
				timTheoMaNhanVien();
			} else if (o.equals(btnTim) && radTimTheoTen.isSelected()) {
				timTheoTen();
			} else if (o.equals(btnXoaTrang)) {
				xoaRong();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private void xoaRong() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		txtTim.setText("");
		radTimTheoMa.setSelected(true);
		chkQuanLyVien.setSelected(false);
		jcbGioiTinh.setSelectedIndex(0);
		tableNhanVien.clearSelection();
	}

}
