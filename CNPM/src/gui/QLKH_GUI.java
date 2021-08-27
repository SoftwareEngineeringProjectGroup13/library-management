package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import bll.KHACHHANG_BLL;
import dto.KHACHHANG_DTO;

public class QLKH_GUI extends JFrame {
	KHACHHANG_BLL khBLL = new KHACHHANG_BLL();
	Font fntTitle, fnt, fntDetail;
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	public JTable Table;
	public JFrame f;
	public JLabel TitleLabel;
	public JScrollPane s;
	public JButton AddButton;
	public JButton SetButton;
	public JButton DeleteButton;
	public JButton SearchButton;
	public JTextField SearchTextField;
	public JLabel Name;
	public JTextField NameTextField;
	public JLabel Id;
	public JTextField IdTextField;
	public JLabel Gender;
	public JTextField GenderTextField;
	public JLabel BirthYear;
	public JTextField BirthYearTextField;
	public JLabel Address;
	public JTextField AddressTextField;
	public JLabel Phone;
	public JTextField PhoneTextField;
	public JButton btnBack;
	public JLabel TitleDetailLabel;

	public QLKH_GUI() {
		initComponents();
		loadKHACHHANGList();
	}

	public void initComponents() {
		fntTitle = new Font("Arial", Font.BOLD, 30);
		fnt = new Font("Arial", Font.PLAIN, 20);
		fntDetail = new Font("Arial", Font.BOLD, 20);

		this.f = new JFrame();
		this.f.setBounds(0, 0, 1000, 700);

		this.TitleLabel = new JLabel("Quản Lý Độc Giả");
		TitleLabel.setBounds(375, 10, 300, 60);
		TitleLabel.setFont(fntTitle);

		btnBack = new JButton(new ImageIcon(getClass().getClassLoader().getResource("home.png")));
		btnBack.setBounds(25, 15, 75, 30);

		this.Table = new JTable();
		Table.getTableHeader().setBorder(border);
		Table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		Table.getTableHeader().setPreferredSize(new Dimension(30, 30));
		Table.getTableHeader().setBackground(Color.CYAN);
		Table.setRowHeight(25);
		Table.setFocusable(false);

		this.s = new JScrollPane(Table);
		s.setBounds(5, 65, 975, 300);

		this.TitleDetailLabel = new JLabel("Chi Tiết Độc Giả ");
		TitleDetailLabel.setBounds(375, 360, 300, 60);
		TitleDetailLabel.setFont(fntTitle);

		this.AddButton = new JButton("Thêm");
		AddButton.setBounds(50, 600, 100, 40);
		AddButton.setFont(fnt);

		this.SetButton = new JButton("Sửa");
		SetButton.setBounds(180, 600, 100, 40);
		SetButton.setFont(fnt);

		this.DeleteButton = new JButton("Xóa");
		DeleteButton.setBounds(310, 600, 100, 40);
		DeleteButton.setFont(fnt);

		this.SearchButton = new JButton("Tìm Kiếm");
		SearchButton.setBounds(500, 600, 120, 40);
		SearchButton.setFont(fnt);

		this.SearchTextField = new JTextField(50);
		SearchTextField.setBounds(625, 600, 350, 40);
		SearchTextField.setFont(fnt);

		this.Id = new JLabel("Mã KH");
		Id.setBounds(90, 425, 100, 30);
		Id.setFont(fntDetail);

		this.IdTextField = new JTextField(50);
		IdTextField.setBounds(200, 425, 150, 30);
		IdTextField.setFont(fntDetail);

		this.Gender = new JLabel("Giới tính");
		Gender.setBounds(90, 470, 100, 30);
		Gender.setFont(fntDetail);

		this.GenderTextField = new JTextField(50);
		GenderTextField.setBounds(200, 470, 150, 30);
		GenderTextField.setFont(fntDetail);

		this.BirthYear = new JLabel("Năm sinh");
		BirthYear.setBounds(90, 520, 100, 30);
		BirthYear.setFont(fntDetail);

		this.BirthYearTextField = new JTextField(50);
		BirthYearTextField.setBounds(200, 520, 150, 30);
		BirthYearTextField.setFont(fntDetail);

		this.Name = new JLabel("Họ và tên");
		Name.setBounds(500, 425, 100, 30);
		Name.setFont(fntDetail);

		this.NameTextField = new JTextField(50);
		NameTextField.setBounds(620, 425, 300, 30);
		NameTextField.setFont(fntDetail);

		this.Phone = new JLabel("Điện thoại");
		Phone.setBounds(500, 470, 150, 30);
		Phone.setFont(fntDetail);

		this.PhoneTextField = new JTextField(50);
		PhoneTextField.setBounds(620, 470, 300, 30);
		PhoneTextField.setFont(fntDetail);

		this.Address = new JLabel("Địa chỉ");
		Address.setBounds(500, 520, 150, 30);
		Address.setFont(fntDetail);

		this.AddressTextField = new JTextField(50);
		AddressTextField.setBounds(620, 520, 300, 30);
		AddressTextField.setFont(fntDetail);

		// JFrame add
		f.add(this.TitleLabel);
		f.add(this.s);
		f.add(this.AddButton);
		f.add(this.SetButton);
		f.add(this.DeleteButton);
		f.add(this.SearchButton);
		f.add(this.SearchTextField);
		f.add(this.Name);
		f.add(this.NameTextField);
		f.add(this.Id);
		f.add(this.IdTextField);
		f.add(this.Gender);
		f.add(this.GenderTextField);
		f.add(this.BirthYear);
		f.add(this.BirthYearTextField);
		f.add(this.Address);
		f.add(this.AddressTextField);
		f.add(this.Phone);
		f.add(this.PhoneTextField);
		f.add(btnBack);
		f.add(TitleDetailLabel);
		f.setTitle("Quản Lý Thư Viện - Quản Lý Độc Giả");
		f.setSize(1000, 700);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setResizable(false);
		f.setVisible(true);

		// Add Event
		Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				ClickMouseClicked(evt);
			}
		});

		AddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addKHACHHANGActionPerformed(e);
			}
		});

		DeleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});

		SetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateActionPerformed(e);
			}
		});

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
	}

	public void loadKHACHHANGList() {
		DefaultTableModel dtm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.addColumn("Mã");
		dtm.addColumn("Họ và tên");
		dtm.addColumn("Năm sinh");
		dtm.addColumn("Giới tính");
		dtm.addColumn("Địa chỉ");
		dtm.addColumn("Số điện thoại");
		Table.setModel(dtm);
		Vector<KHACHHANG_DTO> arr = new Vector<KHACHHANG_DTO>();
		arr = khBLL.getAllKhachhang();
		for (int i = 0; i < arr.size(); i++) {
			KHACHHANG_DTO kh = arr.get(i);
			String id = kh.getMaKH();
			String name = kh.getHoTenKH();
			int birth = kh.getNamSinh();
			String gt = kh.getGioiTinh();
			String dc = kh.getDiaChi();
			String dt = kh.getDienThoai();
			Object[] row = { id, name, birth, gt, dc, dt };
			dtm.addRow(row);
		}
	}

	public void addKHACHHANGActionPerformed(ActionEvent e) {
		try {
			if (IdTextField.getText().trim().equals("") || NameTextField.getText().trim().equals("")
					|| GenderTextField.getText().trim().equals("") || AddressTextField.getText().trim().equals("")
					|| PhoneTextField.getText().trim().equals("") || BirthYearTextField.getText().trim().equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
			else {
				KHACHHANG_DTO nv = new KHACHHANG_DTO();
				nv.setMaKH(IdTextField.getText());
				nv.setHoTenKH(NameTextField.getText());
				nv.setDiaChi(AddressTextField.getText());
				nv.setNamSinh(Integer.parseInt(BirthYearTextField.getText()));
				nv.setGioiTinh(GenderTextField.getText());
				nv.setDienThoai(PhoneTextField.getText());
				JOptionPane.showMessageDialog(this, khBLL.addKHACHHANG(nv));
				loadKHACHHANGList();
				cleanView();
				IdTextField.setEditable(true);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	public void ClickMouseClicked(MouseEvent evt) {
		int i = Table.getSelectedRow();
		IdTextField.setText(Table.getValueAt(i, 0).toString());
		NameTextField.setText(Table.getValueAt(i, 1).toString());
		BirthYearTextField.setText(Table.getValueAt(i, 2).toString());
		GenderTextField.setText(Table.getValueAt(i, 3).toString());
		AddressTextField.setText(Table.getValueAt(i, 4).toString());
		PhoneTextField.setText(Table.getValueAt(i, 5).toString());
		IdTextField.setEditable(false);
	}

	public void deleteActionPerformed(ActionEvent e) {
		try {
			if (IdTextField.getText().trim().equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa");
			else {
				String x = IdTextField.getText();
				JOptionPane.showMessageDialog(this, khBLL.deleteKHACHHANG(x));
				loadKHACHHANGList();
				cleanView();
				IdTextField.setEditable(true);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public void cleanView() {
		IdTextField.setText("");
		NameTextField.setText("");
		GenderTextField.setText("");
		BirthYearTextField.setText("");
		AddressTextField.setText("");
		PhoneTextField.setText("");
	}

	public void updateActionPerformed(ActionEvent e) {
		try {
			if (IdTextField.getText().trim().equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa");
			else {
				KHACHHANG_DTO nv = new KHACHHANG_DTO();
				nv.setMaKH(IdTextField.getText());
				nv.setHoTenKH(NameTextField.getText());
				nv.setDiaChi(AddressTextField.getText());
				nv.setNamSinh(Integer.parseInt(BirthYearTextField.getText()));
				nv.setGioiTinh(GenderTextField.getText());
				nv.setDienThoai(PhoneTextField.getText());
				JOptionPane.showMessageDialog(this, khBLL.updateKHACHHANG(nv));
				loadKHACHHANGList();
				cleanView();
				IdTextField.setEditable(true);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	public void backActionPerformed(ActionEvent e) {
		f.setVisible(false);
		new HomeGUI().setVisible(true);
	}
}
