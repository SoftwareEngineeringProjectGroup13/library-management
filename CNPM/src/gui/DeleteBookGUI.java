package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bll.BookBLL;
import bll.CustomizeJTable;
import bll.NoticeMissingInformation;
import dal.BookDAL;
import dto.BookDTO;

public class DeleteBookGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfId;
	private JLabel lbNoteId;
	private JButton btnDelete;

	private BookDAL dao = new BookDAL();
	private BookDTO book = new BookDTO();
	private NoticeMissingInformation notify = new NoticeMissingInformation();
	private CustomizeJTable custom = new CustomizeJTable();
	private BookBLL bll = new BookBLL();

	public DeleteBookGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(480, 200, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(null);
		pnTitle.setBackground(new Color(255, 194, 14));
		pnTitle.setBounds(0, 0, 400, 75);
		contentPane.add(pnTitle);

		JLabel lbBack = new JLabel("");
		lbBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("home.png")));
		lbBack.setBounds(0, 0, 32, 32);
		pnTitle.add(lbBack);
		lbBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new HomeGUI().setVisible(true);
			}
		});

		JLabel lblDeleteAuthor = new JLabel("Delete Book");
		lblDeleteAuthor.setIcon(new ImageIcon(getClass().getClassLoader().getResource("book.png")));
		lblDeleteAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteAuthor.setForeground(Color.WHITE);
		lblDeleteAuthor.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDeleteAuthor.setBounds(75, 2, 250, 70);
		pnTitle.add(lblDeleteAuthor);

		JLabel lbId = new JLabel("Enter Book ID");
		lbId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbId.setBounds(10, 90, 127, 20);
		contentPane.add(lbId);

		tfId = new JTextField();
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfId.setColumns(10);
		tfId.setBounds(149, 90, 219, 25);
		contentPane.add(tfId);

		lbNoteId = notify.getNotice("id");
		lbNoteId.setForeground(Color.RED);
		lbNoteId.setBounds(10, 120, 126, 14);
		contentPane.add(lbNoteId);
		lbNoteId.setVisible(false);

		btnDelete = new JButton("Delete Book Info");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(10, 150, 378, 30);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		btnDelete.setFocusable(false);
	}

	private void resetText() {
		tfId.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDelete) {

			if (notify.isFilled(tfId.getText(), lbNoteId)) {
				return;
			}

			if (notify.isId(tfId.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid id !");
				return;
			}

			try {
				if (JOptionPane.showConfirmDialog(this, "Do you want to delete the selected item?", "message",
						JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					return;
				}

				if (bll.deleteBook(tfId.getText())) {
					resetText();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
