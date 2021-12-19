package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import dao.DangNhap_DAO;
import dbconnection.DatabaseConnection;

public class DangNhapFrame extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font normalFont = new Font("Times new roman", Font.PLAIN, 16);
	private JTextField txtTenTK;
	private JPasswordField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	private JCheckBox chkTuCach;

	public DangNhapFrame() {
		setSize(900, 500);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setOpacity(0.95f);
		ImageIcon icon = new ImageIcon("Images/moto.png");
		setIconImage(icon.getImage());

		setLookAndFeel();

		addControls();

		addEvent();
	}

	private void addEvent() {
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		btnDangNhap.addKeyListener(this);
		btnThoat.addKeyListener(this);
		txtMatKhau.addKeyListener(this);
		txtTenTK.addKeyListener(this);
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

	private void addControls() {
		Box boxMain = Box.createHorizontalBox();
		getContentPane().add(boxMain, BorderLayout.CENTER);

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBackground(new Color(0x303849));

		JPanel pnlRight = new JPanel();
		pnlRight.setBackground(new Color(0xFFFFFF));

		boxMain.add(pnlLeft);
		boxMain.add(pnlRight);

		Box boxLeft = Box.createVerticalBox();
		pnlLeft.add(boxLeft);

		JLabel lblImage = new JLabel(new ImageIcon("Images/moto.png"));
		JLabel lblTitle = new JLabel("Hondo");
		lblTitle.setFont(new Font("Forte", Font.BOLD, 42));
		lblTitle.setForeground(Color.white);
		JLabel lblName1 = new JLabel("Cửa hàng mua bán xe mua máy Hondo");
		lblName1.setFont(new Font("Times new roman", Font.PLAIN, 18));
		lblName1.setForeground(Color.white);
		JLabel lblName2 = new JLabel("Hệ thống quản lý mua bán xe máy");
		lblName2.setFont(normalFont);
		lblName2.setForeground(Color.white);
		JLabel lblName3 = new JLabel("Dễ dàng - Tiện lợi - Nhanh chóng");
		lblName3.setFont(normalFont);
		lblName3.setForeground(Color.white);

		Box boxImage = Box.createHorizontalBox();
		boxImage.add(Box.createHorizontalGlue());
		boxImage.add(lblImage);
		boxImage.add(Box.createHorizontalGlue());

		Box boxTitle = Box.createHorizontalBox();
		boxTitle.add(Box.createHorizontalGlue());
		boxTitle.add(lblTitle);
		boxTitle.add(Box.createHorizontalGlue());

		Box boxName1 = Box.createHorizontalBox();
		boxName1.add(Box.createHorizontalGlue());
		boxName1.add(lblName1);
		boxName1.add(Box.createHorizontalGlue());

		Box boxName2 = Box.createHorizontalBox();
		boxName2.add(Box.createHorizontalGlue());
		boxName2.add(lblName2);
		boxName2.add(Box.createHorizontalGlue());

		Box boxName3 = Box.createHorizontalBox();
		boxName3.add(Box.createHorizontalGlue());
		boxName3.add(lblName3);
		boxName3.add(Box.createHorizontalGlue());

		boxLeft.add(Box.createVerticalStrut(100));
		boxLeft.add(boxImage);
		boxLeft.add(boxTitle);
		boxLeft.add(boxName1);
		boxLeft.add(Box.createVerticalStrut(30));
		boxLeft.add(boxName2);
		boxLeft.add(boxName3);
		boxLeft.add(Box.createVerticalStrut(100));

		Box boxRight = Box.createVerticalBox();
		pnlRight.add(boxRight);

		JLabel lblHeader = new JLabel("Đăng nhập vào Hondo");
		lblHeader.setFont(new Font("Times new roman", Font.BOLD, 20));

		Box boxHeader = Box.createHorizontalBox();
		boxHeader.add(Box.createHorizontalGlue());
		boxHeader.add(lblHeader);
		boxHeader.add(Box.createHorizontalGlue());

		Box boxForm = Box.createVerticalBox();

		JLabel lblTenTK = new JLabel("Tên tài khoản");
		lblTenTK.setFont(normalFont);
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(normalFont);
		JLabel lblHinhTenTK = new JLabel(new ImageIcon("Images/username.png"));
		lblHinhTenTK.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.black));
		JLabel lblHinhMatKhau = new JLabel(new ImageIcon("Images/password.png"));
		lblHinhMatKhau.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.black));
		txtTenTK = new JTextField(30);
		txtTenTK.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
		txtTenTK.setFont(normalFont);
		txtMatKhau = new JPasswordField(30);
		txtMatKhau.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
		txtMatKhau.setFont(normalFont);
		chkTuCach = new JCheckBox("Đăng nhập với tư cách quản lý viên");
		chkTuCach.setFont(normalFont);
		chkTuCach.setBackground(null);
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(normalFont);
		btnThoat = new JButton("Thoát");
		btnThoat.setFont(normalFont);

		Box boxLabelTen = Box.createHorizontalBox();
		boxLabelTen.add(lblTenTK);
		boxLabelTen.add(Box.createHorizontalGlue());

		Box boxLabelMK = Box.createHorizontalBox();
		boxLabelMK.add(lblMatKhau);
		boxLabelMK.add(Box.createHorizontalGlue());

		Box boxTenTK = Box.createHorizontalBox();
		boxTenTK.add(lblHinhTenTK);
		boxTenTK.add(txtTenTK);

		Box boxMatKhau = Box.createHorizontalBox();
		boxMatKhau.add(lblHinhMatKhau);
		boxMatKhau.add(txtMatKhau);

		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnDangNhap);
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnThoat);

		boxForm.add(boxLabelTen);
		boxForm.add(Box.createVerticalStrut(5));
		boxForm.add(boxTenTK);
		boxForm.add(Box.createVerticalStrut(20));
		boxForm.add(boxLabelMK);
		boxForm.add(Box.createVerticalStrut(5));
		boxForm.add(boxMatKhau);
		boxForm.add(Box.createVerticalStrut(20));
		boxForm.add(boxButton);

		boxRight.add(Box.createVerticalStrut(80));
		boxRight.add(boxHeader);
		boxRight.add(Box.createVerticalStrut(30));
		boxRight.add(boxForm);
		boxRight.add(Box.createVerticalStrut(80));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat))
			System.exit(0);
		if (o.equals(btnDangNhap))
			dangNhap();
	}

	private String getPassword(char[] pass) {
		String s = "";
		for (int i = 0; i < pass.length; i++)
			s += pass[i];
		return s;
	}

	private void dangNhap() {
		if (txtTenTK.getText().isEmpty() || txtMatKhau.getPassword().length <= 0) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản và mật khẩu không được bỏ trống!", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		DatabaseConnection.userName = txtTenTK.getText();
		DatabaseConnection.password = getPassword(txtMatKhau.getPassword());

		try {
			Connection conn = DatabaseConnection.getConnection();

			this.setVisible(false);
			new MainFrame(DangNhap_DAO.laQuanLyVien()).setVisible(true);

			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Không thể kết nối!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (o.equals(btnDangNhap))
				dangNhap();
			if (o.equals(txtMatKhau))
				dangNhap();
			if (o.equals(btnThoat))
				System.exit(0);
			if (o.equals(txtTenTK))
				txtTenTK.transferFocus();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
