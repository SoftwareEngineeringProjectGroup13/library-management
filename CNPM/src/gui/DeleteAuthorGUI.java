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
import javax.swing.table.DefaultTableModel;

import bll.AuthorBLL;
import bll.NoticeMissingInformation;
import dal.AuthorDAL;
import dto.AuthorDTO;

public class DeleteAuthorGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfId;
	private JButton btnDelete;
	private JLabel lbNoteId;

	private AuthorDTO author = new AuthorDTO();
	private AuthorDAL dao = new AuthorDAL();
	private DefaultTableModel model;
	private NoticeMissingInformation notify = new NoticeMissingInformation();
	private AuthorBLL bll = new AuthorBLL();

	public DeleteAuthorGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 400, 200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.lightGray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(null);
		pnTitle.setBackground(new Color(186, 85, 211));
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

		JLabel lbDeleteAuthor = new JLabel("Delete Author");
		lbDeleteAuthor.setIcon(new ImageIcon(getClass().getClassLoader().getResource("writer.png")));
		lbDeleteAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lbDeleteAuthor.setForeground(Color.WHITE);
		lbDeleteAuthor.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbDeleteAuthor.setBounds(75, 2, 250, 70);
		pnTitle.add(lbDeleteAuthor);

		JLabel lbId = new JLabel("Enter Author ID");
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

		btnDelete = new JButton("Delete Author Info");
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

				if (bll.deleteAuthor(tfId.getText())) {
					resetText();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
