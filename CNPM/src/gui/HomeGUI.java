package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import bll.ImageProcessing;
import dal.HomeDAL;

public class HomeGUI extends JFrame implements ActionListener {

	private JPanel contentPane, pnMenu;
	private JLabel lbBook, lbMember, lbAuthor;
	private JButton btnManageMember, btnDeleteBook, btnBookList, btnEditBook, btnManageEmployee, btnGenres,
			btnAddAuthor;

	protected Border btn1 = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
	protected Border btn2 = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(36, 37, 42));
	private JButton btnEditAuthor;
	private JButton btnDeleteAuthor;
	private JButton btnAuthorList;
	private JButton btnAddBook;
	private JLabel lbLogout;
	private HomeDAL dao = new HomeDAL();
	private ImageProcessing imgProcess = new ImageProcessing();

	public HomeGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(75, 15, 1216, 700);
		setTitle("Home Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnMenu = new JPanel();
		pnMenu.setBounds(0, 0, 250, 661);
		contentPane.add(pnMenu);
		pnMenu.setLayout(null);
		pnMenu.setBackground(new Color(36, 37, 42));

		JPanel pnLogo = new JPanel();
		pnLogo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		pnLogo.setBounds(0, 0, 250, 101);
		pnMenu.add(pnLogo);
		pnLogo.setBackground(new Color(1, 50, 67));
		pnLogo.setLayout(null);

		JLabel lbTitle = new JLabel("Library");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setBounds(110, 40, 130, 38);
		pnLogo.add(lbTitle);

		JLabel lbLogo = new JLabel("");
		lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bookLogo.png")));
		lbLogo.setBounds(10, 11, 90, 80);
		pnLogo.add(lbLogo);

		JLabel lbGenres = new JLabel("Thể Loại");
		lbGenres.setForeground(Color.WHITE);
		lbGenres.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbGenres.setBounds(40, 110, 100, 24);
		pnMenu.add(lbGenres);

		btnGenres = new JButton("Quản Lý Thể Loại");
		btnGenres.setForeground(Color.WHITE);
		btnGenres.setBackground(new Color(36, 37, 42));
		btnGenres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenres.setBounds(50, 140, 160, 23);
		pnMenu.add(btnGenres);
		btnGenres.addActionListener(this);
		btnGenres.setFocusable(false);

		btnAddAuthor = new JButton("Thêm");
		btnAddAuthor.setBackground(new Color(36, 37, 42));
		btnAddAuthor.setForeground(Color.WHITE);
		btnAddAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddAuthor.setBounds(10, 200, 110, 23);
		pnMenu.add(btnAddAuthor);
		btnAddAuthor.addActionListener(this);
		btnAddAuthor.setFocusable(false);

		JLabel lblAuthors = new JLabel("Tác Giả");
		lblAuthors.setForeground(Color.WHITE);
		lblAuthors.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAuthors.setBounds(40, 170, 100, 24);
		pnMenu.add(lblAuthors);

		JLabel lblMembers = new JLabel("Độc Giả");
		lblMembers.setForeground(Color.WHITE);
		lblMembers.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMembers.setBounds(40, 260, 110, 24);
		pnMenu.add(lblMembers);

		JLabel lblBooks = new JLabel("Sách");
		lblBooks.setForeground(Color.WHITE);
		lblBooks.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblBooks.setBounds(40, 320, 80, 24);
		pnMenu.add(lblBooks);

		JLabel lblEmployee = new JLabel("Nhân Viên");
		lblEmployee.setForeground(Color.WHITE);
		lblEmployee.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEmployee.setBounds(40, 410, 134, 24);
		pnMenu.add(lblEmployee);

		btnManageMember = new JButton("Quản Lý Độc Giả");
		btnManageMember.setForeground(Color.WHITE);
		btnManageMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnManageMember.setBackground(new Color(36, 37, 42));
		btnManageMember.setBounds(50, 290, 160, 23);
		pnMenu.add(btnManageMember);
		btnManageMember.addActionListener(this);
		btnManageMember.setFocusable(false);

		btnDeleteBook = new JButton("Xóa");
		btnDeleteBook.setForeground(Color.WHITE);
		btnDeleteBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteBook.setBackground(new Color(36, 37, 42));
		btnDeleteBook.setBounds(10, 380, 110, 23);
		pnMenu.add(btnDeleteBook);
		btnDeleteBook.addActionListener(this);
		btnDeleteBook.setFocusable(false);

		btnBookList = new JButton("Danh Sách");
		btnBookList.setForeground(Color.WHITE);
		btnBookList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookList.setBackground(new Color(36, 37, 42));
		btnBookList.setBounds(130, 380, 110, 23);
		pnMenu.add(btnBookList);
		btnBookList.addActionListener(this);
		btnBookList.setFocusable(false);

		btnAddBook = new JButton("Thêm");
		btnAddBook.setForeground(Color.WHITE);
		btnAddBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddBook.setBackground(new Color(36, 37, 42));
		btnAddBook.setBounds(10, 350, 110, 23);
		pnMenu.add(btnAddBook);
		btnAddBook.addActionListener(this);
		btnAddBook.setFocusable(false);

		btnEditBook = new JButton("Chỉnh Sửa");
		btnEditBook.setForeground(Color.WHITE);
		btnEditBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditBook.setBackground(new Color(36, 37, 42));
		btnEditBook.setBounds(130, 350, 110, 23);
		pnMenu.add(btnEditBook);
		btnEditBook.addActionListener(this);
		btnEditBook.setFocusable(false);

		btnManageEmployee = new JButton("Quản Lý Nhân Viên");
		btnManageEmployee.setForeground(Color.WHITE);
		btnManageEmployee.setBackground(new Color(36, 37, 42));
		btnManageEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnManageEmployee.setBounds(50, 440, 160, 23);
		pnMenu.add(btnManageEmployee);
		btnManageEmployee.addActionListener(this);
		btnManageEmployee.setFocusable(false);

		lbLogout = new JLabel("Đăng Xuất");
		lbLogout.setForeground(Color.WHITE);
		lbLogout.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbLogout.setBounds(40, 626, 170, 24);
		pnMenu.add(lbLogout);
		lbLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new LoginGUI().setVisible(true);
			}
		});

		btnEditAuthor = new JButton("Chỉnh Sửa");
		btnEditAuthor.setForeground(Color.WHITE);
		btnEditAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditAuthor.setBackground(new Color(36, 37, 42));
		btnEditAuthor.setBounds(130, 200, 110, 23);
		pnMenu.add(btnEditAuthor);
		btnEditAuthor.addActionListener(this);
		btnEditAuthor.setFocusable(false);

		btnDeleteAuthor = new JButton("Xóa");
		btnDeleteAuthor.setForeground(Color.WHITE);
		btnDeleteAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteAuthor.setFocusable(false);
		btnDeleteAuthor.setBackground(new Color(36, 37, 42));
		btnDeleteAuthor.setBounds(10, 230, 110, 23);
		pnMenu.add(btnDeleteAuthor);
		btnDeleteAuthor.addActionListener(this);
		btnDeleteAuthor.setFocusable(false);

		btnAuthorList = new JButton("Danh Sách");
		btnAuthorList.setForeground(Color.WHITE);
		btnAuthorList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAuthorList.setFocusable(false);
		btnAuthorList.setBackground(new Color(36, 37, 42));
		btnAuthorList.setBounds(130, 230, 110, 23);
		pnMenu.add(btnAuthorList);
		btnAuthorList.addActionListener(this);
		btnAuthorList.setFocusable(false);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 194, 14));
		panel1.setBounds(270, 50, 280, 150);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JPanel pnBook = new JPanel();
		pnBook.setBackground(new Color(255, 150, 14));
		pnBook.setBounds(0, 0, 280, 60);
		panel1.add(pnBook);
		pnBook.setLayout(null);

		JLabel lbHeadingBook = new JLabel("Sách");
		lbHeadingBook.setForeground(Color.WHITE);
		lbHeadingBook.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbHeadingBook.setBounds(10, 11, 80, 38);
		pnBook.add(lbHeadingBook);

		lbBook = new JLabel("05555");
		lbBook.setHorizontalAlignment(SwingConstants.CENTER);
		lbBook.setForeground(Color.WHITE);
		lbBook.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbBook.setBounds(80, 80, 120, 38);
		panel1.add(lbBook);

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(52, 191, 73));
		panel2.setBounds(580, 50, 280, 150);
		contentPane.add(panel2);
		panel2.setLayout(null);

		JPanel pnMember = new JPanel();
		pnMember.setBackground(new Color(39, 155, 55));
		pnMember.setBounds(0, 0, 280, 60);
		panel2.add(pnMember);
		pnMember.setLayout(null);

		JLabel lbHeadingMember = new JLabel("Độc Giả");
		lbHeadingMember.setForeground(Color.WHITE);
		lbHeadingMember.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbHeadingMember.setBounds(10, 11, 120, 38);
		pnMember.add(lbHeadingMember);

		lbMember = new JLabel("25");
		lbMember.setHorizontalAlignment(SwingConstants.CENTER);
		lbMember.setForeground(Color.WHITE);
		lbMember.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbMember.setBounds(80, 80, 120, 38);
		panel2.add(lbMember);

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(186, 85, 211));
		panel3.setBounds(890, 50, 280, 150);
		contentPane.add(panel3);
		panel3.setLayout(null);

		JPanel pnAuthor = new JPanel();
		pnAuthor.setBackground(new Color(148, 0, 211));
		pnAuthor.setBounds(0, 0, 280, 60);
		panel3.add(pnAuthor);
		pnAuthor.setLayout(null);

		JLabel lbHeadingAuthor = new JLabel("Tác Giả");
		lbHeadingAuthor.setForeground(Color.WHITE);
		lbHeadingAuthor.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbHeadingAuthor.setBounds(10, 11, 105, 38);
		pnAuthor.add(lbHeadingAuthor);

		lbAuthor = new JLabel("05555");
		lbAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lbAuthor.setForeground(Color.WHITE);
		lbAuthor.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbAuthor.setBounds(80, 80, 120, 38);
		panel3.add(lbAuthor);

		JPanel panel4 = new JPanel();
		panel4.setBackground(new Color(72, 169, 197));
		panel4.setBounds(270, 250, 900, 400);
		contentPane.add(panel4);
		panel4.setLayout(null);

		JPanel panel5 = new JPanel();
		panel5.setBackground(new Color(0, 142, 170));
		panel5.setBounds(0, 0, 900, 60);
		panel4.add(panel5);

		JLabel lbHeadingLatestBook = new JLabel("Latest Book Added");
		lbHeadingLatestBook.setForeground(Color.WHITE);
		lbHeadingLatestBook.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbHeadingLatestBook.setBounds(10, 11, 230, 38);
		panel5.add(lbHeadingLatestBook);

		JLabel lbBook1 = new JLabel("");
		lbBook1.setHorizontalAlignment(SwingConstants.CENTER);
		lbBook1.setIcon(new ImageIcon(imgProcess.resizeImage(
				new ImageIcon(getClass().getClassLoader().getResource("onePiece.jpg")).getImage(), 150, 200)));
		lbBook1.setBounds(25, 120, 150, 200);
		panel4.add(lbBook1);

		JLabel lbBook2 = new JLabel("");
		lbBook2.setIcon(new ImageIcon(imgProcess
				.resizeImage(new ImageIcon(getClass().getClassLoader().getResource("song.jpg")).getImage(), 150, 200)));
		lbBook2.setHorizontalAlignment(SwingConstants.CENTER);
		lbBook2.setBounds(200, 120, 150, 200);
		panel4.add(lbBook2);

		JLabel lbBook3 = new JLabel("");
		lbBook3.setIcon(new ImageIcon(imgProcess.resizeImage(
				new ImageIcon(getClass().getClassLoader().getResource("harryPotter.jpg")).getImage(), 150, 200)));
		lbBook3.setHorizontalAlignment(SwingConstants.CENTER);
		lbBook3.setBounds(375, 120, 150, 200);
		panel4.add(lbBook3);

		JLabel lbBook4 = new JLabel("");
		lbBook4.setHorizontalAlignment(SwingConstants.CENTER);
		lbBook4.setIcon(new ImageIcon(imgProcess.resizeImage(
				new ImageIcon(getClass().getClassLoader().getResource("straightUpLove.jpg")).getImage(), 150, 200)));
		lbBook4.setBounds(550, 120, 150, 200);
		panel4.add(lbBook4);

		JLabel lbBook5 = new JLabel("");
		lbBook5.setIcon(new ImageIcon(imgProcess.resizeImage(
				new ImageIcon(getClass().getClassLoader().getResource("thinkingInjava.jpg")).getImage(), 150, 200)));
		lbBook5.setHorizontalAlignment(SwingConstants.CENTER);
		lbBook5.setBounds(725, 120, 150, 200);
		panel4.add(lbBook5);

		addBorders();

		buttonHoverEffect();
		getStatistics();

	}

	private void getStatistics() {
		try {
			int numberOfBook = dao.getQuantity("book");
			int numberOfAuthor = dao.getQuantity("author");
			int numberOfMember = dao.getMember("khachhang");
			lbBook.setText(String.valueOf(numberOfBook));
			lbAuthor.setText(String.valueOf(numberOfAuthor));
			lbMember.setText(String.valueOf(numberOfMember));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buttonHoverEffect() {
		Component[] components = pnMenu.getComponents();
		for (Component check : components) {
			if (check instanceof JButton) {
				JButton button = (JButton) check;
				button.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						button.setBorder(btn1);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						button.setBorder(btn2);
					}
				});
			}
		}
	}

	private void addBorders() {
		Component[] components = pnMenu.getComponents();
		for (Component check : components) {
			if (check instanceof JButton) {
				JButton button = (JButton) check;
				button.setBorder(btn1);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGenres) {
			dispose();
			new GenresGUI().setVisible(true);
		}

		if (e.getSource() == btnAddAuthor) {
			dispose();
			new AddAuthorGUI().setVisible(true);
		}

		if (e.getSource() == btnEditAuthor) {
			dispose();
			new EditAuthorGUI().setVisible(true);
		}

		if (e.getSource() == btnDeleteAuthor) {
			dispose();
			new DeleteAuthorGUI().setVisible(true);
		}

		if (e.getSource() == btnAuthorList) {
			dispose();
			new AuthorListGUI().setVisible(true);
		}

		if (e.getSource() == btnAddBook) {
			dispose();
			new AddBookGUI().setVisible(true);
		}

		if (e.getSource() == btnEditBook) {
			dispose();
			new EditBookGUI().setVisible(true);
		}

		if (e.getSource() == btnDeleteBook) {
			dispose();
			new DeleteBookGUI().setVisible(true);
		}

		if (e.getSource() == btnBookList) {
			dispose();
			new BookListGUI().setVisible(true);
		}

		if (e.getSource() == btnManageEmployee) {
			setVisible(false);
			new QLNV_GUI();
		}

		if (e.getSource() == btnManageMember) {
			setVisible(false);
			new QLKH_GUI();
		}
	}
}