package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import dao.DoanhThuObject;
import dao.ThongKeDoanhThu_DAO;

import javax.swing.UIManager.LookAndFeelInfo;


public class ThongKeDoanhThuPanel extends JPanel implements ActionListener {	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Times new roman", Font.PLAIN, 14);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JButton btnThongKe;
	private JLabel lblTongDT;
	private JComboBox<String> cbbThang;
	private JComboBox<String> cbbNam;
	private ThongKeDoanhThu_DAO thongKeDoanhThu;
	private DefaultTableModel tableModel;
	private JTable tableDoanhThu;
	private JRadioButton radThang;
	private JRadioButton radNam;
	private JLabel lblThongTin;


	public ThongKeDoanhThuPanel() {
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());
		
		setLookAndFeel();
		
		addNorth();
		addCenter();
		
		addEvent();
	}

	private void addEvent() {
		btnThongKe.addActionListener(this);
	}

	private void addNorth() {
		Box boxNorth = Box.createVerticalBox();

		this.add(boxNorth, BorderLayout.NORTH);

		Box boxHeader = Box.createHorizontalBox();

		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxHeader);
		boxNorth.add(Box.createVerticalStrut(5));

		JLabel lblHeader = new JLabel("THỐNG KÊ DOANH THU");
		lblHeader.setForeground(HEADER_COLOR);
		lblHeader.setFont(HEADER_FONT);

		boxHeader.add(Box.createHorizontalGlue());
		boxHeader.add(lblHeader);
		boxHeader.add(Box.createHorizontalGlue());
		
		radThang = new JRadioButton("Theo tháng"); radThang.setFont(NORMAL_FONT); radThang.setSelected(true);
		radNam = new JRadioButton("Theo năm"); radNam.setFont(NORMAL_FONT);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radThang);
		group.add(radNam);
		
		Box boxRad = Box.createHorizontalBox();
		boxRad.add(new JLabel("Thống kê theo "));
		boxRad.add(Box.createHorizontalGlue());
		boxRad.add(radThang);
		boxRad.add(Box.createHorizontalGlue());
		boxRad.add(radNam);
		boxRad.add(Box.createHorizontalGlue());
		
		boxNorth.add(boxRad);
		boxNorth.add(Box.createVerticalStrut(10));
		
		btnThongKe = new JButton("Thống kê");
		btnThongKe.setFont(NORMAL_FONT);
		
		String thang[] = { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8",
				"Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" };
		DefaultComboBoxModel<String> cbbThangModel = new DefaultComboBoxModel<String>(thang);
		cbbThang = new JComboBox<String>(cbbThangModel);
		cbbThang.setFont(NORMAL_FONT);

		String nam[] = new String[130];
		for(int i = 0; i < 130; i++)
			nam[i] = (i + 1970) + "";
		DefaultComboBoxModel<String> cbbNamModel = new DefaultComboBoxModel<String>(nam);
		cbbNam = new JComboBox<String>(cbbNamModel);
		cbbNam.setFont(NORMAL_FONT);
		cbbNam.setSelectedIndex(LocalDate.now().getYear() - 1970);
		
		Box boxTime = Box.createHorizontalBox();
		
		boxTime.add(Box.createHorizontalStrut(10));
		boxTime.add(new JLabel("Chọn tháng "));
		boxTime.add(cbbThang);
		boxTime.add(Box.createHorizontalStrut(10));
		boxTime.add(new JLabel("Chọn năm "));
		boxTime.add(cbbNam);
		boxTime.add(Box.createHorizontalStrut(10));
		boxTime.add(btnThongKe);
		boxTime.add(Box.createHorizontalStrut(10));
		
		boxNorth.add(boxTime);
		boxNorth.add(Box.createVerticalStrut(10));
		
		lblThongTin = new JLabel("");
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 18));
		
		Box boxThongTin = Box.createHorizontalBox();
		boxThongTin.add(Box.createHorizontalGlue());
		boxThongTin.add(lblThongTin);
		boxThongTin.add(Box.createHorizontalGlue());
		
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxThongTin);
		boxNorth.add(Box.createVerticalStrut(10));
		
		JLabel lblTong = new JLabel("Tổng doanh thu: ");
		lblTong.setFont(NORMAL_FONT);
		lblTongDT = new JLabel();
		lblTongDT.setFont(NORMAL_FONT);
		
		Box boxTong = Box.createHorizontalBox();
		boxTong.add(Box.createHorizontalStrut(10));
		boxTong.add(lblTong);
		boxTong.add(Box.createHorizontalStrut(10));
		boxTong.add(lblTongDT);
		boxTong.add(Box.createHorizontalGlue());
		
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxTong);
		boxNorth.add(Box.createVerticalStrut(10));
	}
	
	String[] tbHeader = {"Mã nhân viên", "Tên nhân viên", "Số xe đã bán", "Tổng doanh thu"};
	
	private void addCenter() {
		
		tableModel = new DefaultTableModel(tbHeader, 0);
		
		tableDoanhThu = new JTable(tableModel);
		tableDoanhThu.setFont(NORMAL_FONT);
		
		JScrollPane scroll = new JScrollPane(tableDoanhThu);
		this.add(scroll, BorderLayout.CENTER);
		
	}
	
	private void thongKe() {
		thongKeDoanhThu = new ThongKeDoanhThu_DAO();
		
		int thang = cbbThang.getSelectedIndex() + 1;
		int nam = Integer.parseInt(cbbNam.getSelectedItem().toString());
		
		try {
			List<DoanhThuObject> list = null;
			if(radThang.isSelected()) {
				list = thongKeDoanhThu.thongKeTheoThang(thang, nam);
				lblThongTin.setText("Kết quả thống kê doanh thu tháng " + thang + " năm " + nam);
			}
			else {
				list = thongKeDoanhThu.thongKeTheoNam(nam);
				lblThongTin.setText("Kết quả thống kê doanh thu năm " + nam);
			}
			tableModel.setRowCount(0);
			for(DoanhThuObject item : list) {
				tableModel.addRow(new Object[] {
						item.getMaNV(), item.getTenNV(), item.getSoXeDaBan(), item.getDoanhThu()
				});
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình thống kê!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnThongKe)) {
			thongKe();
		}
	}

}
