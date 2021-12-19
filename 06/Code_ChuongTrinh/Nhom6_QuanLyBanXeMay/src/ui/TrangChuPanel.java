package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class TrangChuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Times new roman", Font.PLAIN, 18);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private final Color HOVER_COLOR = new Color(0xFCCB49);
	private final Color NORMAL_COLOR = new Color(0xFFFFFF);
	private JPanel pnlQLKH;
	private JPanel pnlQLNV;
	private JPanel pnlQLXM;
	private JPanel pnlQLHD;
	private JPanel pnlQLBH;///
	
	private boolean isManager;
	private MainFrame mainFrame;
	private JPanel pnlTKDT;
	private JPanel pnlHoSo;

	public TrangChuPanel(MainFrame mainFrame, boolean isManager) {
		this.mainFrame = mainFrame;
		this.isManager = isManager;
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());
		setOpaque(false);
		setLookAndFeel();
		addNorth();
		addCenter();

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

	private void addNorth() {
		Box boxNorth = Box.createVerticalBox();

		this.add(boxNorth, BorderLayout.NORTH);

		Box boxHeader = Box.createHorizontalBox();

		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxHeader);
		boxNorth.add(Box.createVerticalStrut(5));

		JLabel lblHeader = new JLabel("TRANG CHỦ");
		lblHeader.setForeground(HEADER_COLOR);
		lblHeader.setFont(HEADER_FONT);

		boxHeader.add(Box.createHorizontalGlue());
		boxHeader.add(lblHeader);
		boxHeader.add(Box.createHorizontalGlue());
	}

	private void addCenter() {
		JPanel pnlCenter = new JPanel(new GridLayout(2, 2, 30, 30));
		
		Box boxFixedCenterHorizontal = Box.createHorizontalBox();
		boxFixedCenterHorizontal.add(Box.createHorizontalStrut(30));
		boxFixedCenterHorizontal.add(pnlCenter);
		boxFixedCenterHorizontal.add(Box.createHorizontalStrut(30));
		
		Box boxFixedCenterVertical = Box.createVerticalBox();
		boxFixedCenterVertical.add(Box.createVerticalStrut(30));
		boxFixedCenterVertical.add(boxFixedCenterHorizontal);
		boxFixedCenterVertical.add(Box.createVerticalStrut(30));
		
		this.add(boxFixedCenterVertical, BorderLayout.CENTER);
		
		pnlQLKH = createPanel(pnlCenter, "Quản lý khách hàng", "Images/customers.png");
		pnlHoSo = createPanel(pnlCenter, "Hồ sơ của tôi", "Images/profile2.png");
		if(isManager)
			pnlQLNV = createPanel(pnlCenter, "Quản lý nhân viên", "Images/employees.png");
			pnlQLXM = createPanel(pnlCenter, "Quản lý xe máy", "Images/motobikes.png");
			pnlQLHD = createPanel(pnlCenter, "Quản lý hóa đơn", "Images/orders.png");
			pnlQLBH = createPanel(pnlCenter, "Quản lý bảo hành", "Images/orders.png");///
		if(isManager) {
			pnlTKDT = createPanel(pnlCenter, "Thống kê doanh thu", "Images/revenue.png");
		}
	}
	
	private JPanel createPanel(Container container, String name, String iconPath) {
		JPanel pnl = new JPanel();
		
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		pnl.setBackground(NORMAL_COLOR);
		pnl.setBorder(null);
		
		
		pnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!pnl.isEnabled())
					return;
				pnl.setBackground(HOVER_COLOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!pnl.isEnabled())
					return;
				pnl.setBackground(NORMAL_COLOR);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				
				if(o.equals(pnlQLHD))
					mainFrame.changePanel(mainFrame.getPnlQLHD());
				if(o.equals(pnlQLKH))
					mainFrame.changePanel(mainFrame.getPnlQLKH());
				if(o.equals(pnlQLXM))
					mainFrame.changePanel(mainFrame.getPnlQLXM());
				if(o.equals(pnlQLBH))
					mainFrame.changePanel(mainFrame.getPnlQLBH());///
				if(o.equals(pnlQLNV))
					mainFrame.changePanel(mainFrame.getPnlQLNV());
				if(o.equals(pnlHoSo))
					mainFrame.changePanel(mainFrame.getPnlProfile());
				if(o.equals(pnlTKDT))
					mainFrame.changePanel(mainFrame.getPnlTKDT());
			}
		});
		
		JLabel lblIcon = new JLabel(new ImageIcon(iconPath));
		JLabel lblName = new JLabel(name); lblName.setFont(NORMAL_FONT);
		
		Box boxIcon = Box.createHorizontalBox();
		Box boxName = Box.createHorizontalBox();
		
		boxIcon.add(Box.createHorizontalGlue());
		boxIcon.add(lblIcon);
		boxIcon.add(Box.createHorizontalGlue());
		
		boxName.add(Box.createHorizontalGlue());
		boxName.add(lblName);
		boxName.add(Box.createHorizontalGlue());
		
		pnl.add(Box.createVerticalGlue());
		pnl.add(boxIcon);
		pnl.add(boxName);
		pnl.add(Box.createVerticalGlue());
		
		container.add(pnl);
		
		return pnl;
	}
	
	public void focus() {
		
	}
}
