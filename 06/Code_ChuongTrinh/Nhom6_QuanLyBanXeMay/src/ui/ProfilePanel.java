package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import entity.NhanVien;
import dao.HoSo_DAO;


public class ProfilePanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JTextField txtMaNhanVien;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private HoSo_DAO hoSo = new HoSo_DAO();
	private JComboBox<String> ckbGioiTinh;
	private JComboBox<String> ckbChucVu;
	private JButton btnChinhSua;
	private JButton btnLuu;
	
	public ProfilePanel(boolean isQLV) {
		setPreferredSize(new Dimension(500,600));
		setLayout(new BorderLayout());
		setLookAndFeel();
		
		addNorth();
		
		btnChinhSua.addActionListener(this);
		btnLuu.addActionListener(this);
		if(!isQLV) {
			btnLuu.setEnabled(false);
			btnChinhSua.setEnabled(false);
		}
		
	}
	
	private void addNorth(){
		Dimension dLabel = new Dimension(90,25);
		Box boxNorth, boxGroup, boxGioiTinh, boxChucVu, boxDiaChi, boxLeft, boxRight;
		
		boxNorth = Box.createVerticalBox();
		
		JPanel pnlImage = new JPanel();
		JLabel lblImage = new JLabel(new ImageIcon("Images/dinosaur.png"));
		pnlImage.add(lblImage);
		
		JPanel pnlTitle = new JPanel();
		JLabel lblHeader = new JLabel("HỒ SƠ CỦA TÔI");
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);
		pnlTitle.add(lblHeader);
		
		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setPreferredSize(dLabel);
		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setPreferredSize(dLabel);
		ckbGioiTinh = new JComboBox<String>();
		ckbGioiTinh.addItem("Nam");
		ckbGioiTinh.addItem("Nữ");
		ckbGioiTinh.setPreferredSize(new Dimension(100,25));
		ckbChucVu = new JComboBox<String>();
		ckbChucVu.addItem("Quản lý viên");
		ckbChucVu.addItem("Nhân viên");
		ckbChucVu.setPreferredSize(new Dimension(100,25));
		
		JLabel lblMaNhanVien = new JLabel("Mã nhân viên");
		lblMaNhanVien.setPreferredSize(dLabel);
		JLabel lblHoTen = new JLabel("Họ tên");
		lblHoTen.setPreferredSize(dLabel);
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(dLabel);
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setPreferredSize(dLabel);
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setPreferredSize(dLabel);
		
		
		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		
		boxGroup = Box.createHorizontalBox();
		boxGioiTinh = Box.createHorizontalBox();
		boxChucVu = Box.createHorizontalBox();
		boxDiaChi = Box.createHorizontalBox();
		boxLeft = Box.createVerticalBox();
		boxRight = Box.createVerticalBox();
		
		boxDiaChi.add(Box.createHorizontalStrut(20));
		boxDiaChi.add(lblDiaChi);
		boxDiaChi.add(Box.createHorizontalStrut(5));
		boxDiaChi.add(txtDiaChi);
		boxDiaChi.add(Box.createHorizontalStrut(20));
		boxGioiTinh.add(lblGioiTinh); boxGioiTinh.add(ckbGioiTinh);
		boxChucVu.add(lblChucVu); boxChucVu.add(ckbChucVu);
		
		txtMaNhanVien = addInputItemTo(boxLeft, "Mã nhân viên");
		txtMaNhanVien.setEditable(false);
		txtHoTen = addInputItemTo(boxRight, "Họ tên");
		txtHoTen.setEditable(false);
		boxLeft.add(boxGioiTinh);
		ckbGioiTinh.setEnabled(false);
		boxRight.add(boxChucVu);
		ckbChucVu.setEnabled(false);
		txtSoDienThoai = addInputItemTo(boxLeft, "Số điện thoại");
		txtSoDienThoai.setEditable(false);
		txtEmail = addInputItemTo(boxRight, "Email");
		txtEmail.setEditable(false);
		
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.add(boxLeft);
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.add(boxRight);
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.setFont(NORMAL_FONT);
		
		boxNorth.add(pnlTitle);
		boxNorth.add(pnlImage);
		boxNorth.add(Box.createVerticalStrut(20));
		boxNorth.add(boxGroup);
		boxNorth.add(boxDiaChi);
		
		Box boxButton;
		btnChinhSua = new JButton("Chỉnh sửa", new ImageIcon("Images/update.png"));
		btnChinhSua.setPreferredSize(new Dimension(100,40));
		btnChinhSua.setFont(NORMAL_FONT);
		btnLuu = new JButton("Lưu", new ImageIcon("Images/save.png"));
		btnLuu.setPreferredSize(new Dimension(100,40));
		btnLuu.setFont(NORMAL_FONT);
		
		boxButton = Box.createHorizontalBox();
		boxButton.add(btnChinhSua);
		boxButton.add(btnLuu);
		boxNorth.add(boxButton);
		
		this.add(boxNorth, BorderLayout.NORTH);
		
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
	public void loadDataFromDatabaseToPanel() {
		try {
			hoSo.getProfile();
			NhanVien nhanvien = hoSo.getNhanVien();
			txtMaNhanVien.setText(nhanvien.getMaNV());
			txtHoTen.setText(nhanvien.getHoTenNV());
			ckbGioiTinh.setSelectedItem(nhanvien.isGioiTinh()? "Nam" : "Nữ");
			txtDiaChi.setText(nhanvien.getDiaChi());
			txtSoDienThoai.setText(nhanvien.getSDT());
			txtEmail.setText(nhanvien.getEmail());
			ckbChucVu.setSelectedItem(nhanvien.isQuanLyVien()? "Quản lý viên" : "Nhân viên");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		Object o = e.getSource();
		if(o.equals(btnChinhSua))
			chinhSua();
		else if(o.equals(btnLuu))
			try {
				luu();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}
	private void chinhSua() {
			txtHoTen.setEditable(true);
			txtDiaChi.setEditable(true);	
			txtSoDienThoai.setEditable(true);	
			txtEmail.setEditable(true);	
			ckbGioiTinh.setEnabled(true);
			ckbChucVu.setEnabled(true);
	}
	
	private void luu() throws SQLException{
		if(validData()) {
			NhanVien nv = new NhanVien(txtMaNhanVien.getText().trim(), txtHoTen.getText().trim(), 
					ckbGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false, txtDiaChi.getText().trim(),
					txtSoDienThoai.getText().trim(), txtEmail.getText().trim(), ckbChucVu.getSelectedItem().toString().equals("Quản lý viên") ? true : false);
			if(hoSo.modifiedProfile(nv)) {
				JOptionPane.showMessageDialog(this, "Sửa thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				txtHoTen.setEditable(false);
				txtDiaChi.setEditable(false);	
				txtSoDienThoai.setEditable(false);	
				txtEmail.setEditable(false);	
				ckbGioiTinh.setEnabled(false);
				ckbChucVu.setEnabled(false);	
			} else {
				JOptionPane.showMessageDialog(this, "Sửa nhân thông tin không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
		
	public boolean validData() {
			
		String tennv = txtHoTen.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String email = txtEmail.getText().trim();
			
		//Tên nhân viên không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Không chứa ký tự đặc biệt và số
		if(tennv.isEmpty() || !tennv.matches("[\\p{L}\\s]+")) {
			JOptionPane.showMessageDialog(this,
					"Tên xe không được bỏ trống. Tên xe có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt và số",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtHoTen.requestFocus();
			return false;
		}
		
		//Địa chỉ không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Được chứa ký tự '-', '(', ')' và số
		if(diachi.isEmpty() || !diachi.matches("[\\p{L}0-9\\s-(),]+")) {
			JOptionPane.showMessageDialog(this,
					"Địa chỉ không được bỏ trống. Địa chỉ có thể gồm nhiều từ cách nhau bởi khoảng trắng"
					+ "Được chứa ký tự '-', '(', ')' và số",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtDiaChi.requestFocus();
			return false;
		}
		
		
		if(email.isEmpty() || !email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			JOptionPane.showMessageDialog(this,
					"email không được bỏ trống. email có thể gồm nhiều từ, không được chứa khoảng trắng. "
					+ "Tên hộp thư được chưa các ký tự . và -, tên miền được chứa ký tự .",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtEmail.requestFocus();
			return false;
		}
		return true;
	}
}
