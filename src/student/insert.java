package student;

import java.awt.Color;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class InsertPanel extends JPanel{  
    public void paintComponent(Graphics g){  
        super.paintComponent(g);  
        //����һ�ű���ͼƬ  2.jpg��ͼƬ��·��  �Լ��趨Ϊ�Լ���Ҫ��ӵ�ͼƬ  
        Image image = new ImageIcon("images//back.png").getImage();  
        g.drawImage(image, 0, 0, this);  
    }  
}

public class insert extends JFrame {
	InsertPanel panel=new InsertPanel();
	JLabel labelGender = new JLabel("�Ա�");
	JLabel labelClass = new JLabel("�༶");
	JLabel labelGrade = new JLabel("�꼶");
	JLabel labelId = new JLabel("ѧ��");
	JLabel labelName = new JLabel("����");
	JTextField textId = new JTextField();
	JTextField textName = new JTextField();
	JTextField textGender = new JTextField();
	JTextField textClass = new JTextField();
	JTextField textGrade = new JTextField();

	JButton buttonInsert = new JButton("���");
	JButton buttonReturn = new JButton("����");

	Vector rowData = new Vector(); // rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ
	Vector columnNames = new Vector();
	Vector classrowData = new Vector();
	Vector classColumnNames = new Vector();
	JTable table = null;
	JScrollPane scrollpane = new JScrollPane();
	JTable classtable = null;
	JScrollPane classscrollpane = new JScrollPane();
	PreparedStatement prestatement = null;
	Connection conn = null;
	ResultSet result = null;

	public insert() {
		setTitle("���ѧ����Ϣ");
		labelId.setBounds(new Rectangle(530, 70, 70, 40));
		labelName.setBounds(new Rectangle(530, 120, 70, 40));
		labelGender.setBounds(new Rectangle(530, 170, 70, 40));
		labelClass.setBounds(new Rectangle(530, 220, 70, 40));
		labelGrade.setBounds(new Rectangle(530, 270, 70, 40));
		
		labelId.setForeground(Color.white);
		labelName.setForeground(Color.white);
		labelGender.setForeground(Color.white);
		labelClass.setForeground(Color.white);
		labelGrade.setForeground(Color.white);
		
		labelId.setFont(new java.awt.Font("����", Font.BOLD, 15));
		labelName.setFont(new java.awt.Font("����", Font.BOLD, 15));
		labelGender.setFont(new java.awt.Font("����", Font.BOLD, 15));
		labelClass.setFont(new java.awt.Font("����", Font.BOLD, 15));
		labelGrade.setFont(new java.awt.Font("����", Font.BOLD, 15));

		textId.setBounds(new Rectangle(570, 70, 150, 35));
		textName.setBounds(new Rectangle(570, 120, 150, 35));
		textGender.setBounds(new Rectangle(570, 170, 150, 35));
		textClass.setBounds(new Rectangle(570, 220, 150, 35));
		textGrade.setBounds(new Rectangle(570, 270, 150, 35));

		buttonInsert.setBounds(new Rectangle(570, 330, 60, 30));
		buttonReturn.setBounds(new Rectangle(660, 330, 60, 30));

		buttonInsert.addActionListener(new insertLisener());
		buttonReturn.addActionListener(new returnLisener());

		table();

	}

	void table() {
		try {
			// ��������
			Class.forName("com.mysql.cj.jdbc.Driver");
			// �õ�����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC", "root",
					"wp010214");
			prestatement = conn.prepareStatement("select * from student");
			result = prestatement.executeQuery();

			while (result.next()) {
				// rowData���Դ�Ŷ���
				Vector row = new Vector();
				row.add(result.getInt(1));
				row.add(result.getString(2));
				row.add(result.getString(3));
				row.add(result.getString(4));
				row.add(result.getInt(5));

				// ���뵽rowData
				rowData.add(row);
			}

			prestatement = conn.prepareStatement("select * from class");
			result = prestatement.executeQuery();
			while (result.next()) {
				// rowData���Դ�Ŷ���
				Vector row = new Vector();
				row.add(result.getString(1));
				row.add(result.getInt(2));
				row.add(result.getString(3));
				row.add(result.getInt(4));

				// ���뵽rowData
				classrowData.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("�༶");
		columnNames.add("�꼶");
		table = new JTable(rowData, columnNames);
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(new Rectangle(30, 70, 400, 200));

		classColumnNames.add("�༶");
		classColumnNames.add("�꼶");
		classColumnNames.add("ѧԺ");
		classColumnNames.add("����");
		classtable = new JTable(classrowData, classColumnNames);
		classscrollpane = new JScrollPane(classtable);
		classscrollpane.setBounds(new Rectangle(30, 400, 400, 200));

		panel.setLayout(null);
		panel.add(labelId);
		panel.add(labelName);
		panel.add(labelGender);
		panel.add(labelClass);
		panel.add(labelGrade);
		panel.add(textId);
		panel.add(textName);
		panel.add(textGender);
		panel.add(textClass);
		panel.add(textGrade);
		panel.add(buttonInsert);
		panel.add(buttonReturn);
		panel.add(scrollpane);
		panel.add(classscrollpane);
		this.add(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}

	class insertLisener implements ActionListener {
		@SuppressWarnings("unlikely-arg-type")
		public void actionPerformed(ActionEvent e) {
			String s1 = textId.getText();String name = textName.getText();String gender = textGender.getText();
			String class_name = textClass.getText();String s5 = textGrade.getText();String sql = "select id from student";boolean insert_bool=true;
			try{
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getInt("id"));
					String id=rs.getString("id");
					if(s1.equals(id)) {
						insert_bool=false;
						JOptionPane.showMessageDialog(null, "ѧ����Ϣ��¼��", "ʧ��", JOptionPane.INFORMATION_MESSAGE);}}
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if (s1.equals("") || name.equals("") || gender.equals("") || class_name.equals("") || s5.equals("")) {
				JOptionPane.showMessageDialog(null, "����ȷ��д��Ϣ", "ʧ��", JOptionPane.INFORMATION_MESSAGE);
				insert_bool=false;
			}
			if(insert_bool) {
				int id = Integer.parseInt(s1);
				int grade = Integer.parseInt(s5);
				try {
					database.insert(id, name, gender, class_name, grade);
				} catch (Exception e1) {
					e1.printStackTrace();
				}}
			dispose();
			new insert();
		}
	}

	class returnLisener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new mainwin();
		}
	}

	public static void main(String args[]) {
		new insert();
	}
}