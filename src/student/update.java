package student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.beancontext.BeanContextServiceProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.table.DefaultTableCellRenderer;

class UpdatePanel extends JPanel{  
    public void paintComponent(Graphics g){  
        super.paintComponent(g);  
        //绘制一张背景图片  2.jpg是图片的路径  自己设定为自己想要添加的图片  
        Image image = new ImageIcon("images//back.png").getImage();  
        g.drawImage(image, 0, 0, this);  
    }  
}
public class update extends JFrame {
	UpdatePanel panel=new UpdatePanel();
	JLabel labelGender = new JLabel("性别");
	JLabel labelClass = new JLabel("班级");
	JLabel labelGrade = new JLabel("年级");
	JLabel labelId = new JLabel("学号");
	JLabel labelName = new JLabel("姓名");
	JTextField textId = new JTextField();
	JTextField textName = new JTextField();
	JTextField textGender = new JTextField();
	JTextField textClass = new JTextField();
	JTextField textGrade = new JTextField();
	
	JButton buttonUpdate = new JButton("更新");
	JButton buttonReturn = new JButton("返回");
	
	Vector rowData = new Vector();  //rowData可以存放多行,开始从数据库里取 
	Vector classrowData = new Vector();
	Vector columnNames = new Vector();
	Vector classColumnNames = new Vector(); 
    JTable table=null;  
    JTable classtable=null;
    JScrollPane scrollpane=null;  
    JScrollPane classscrollpane=null;
    PreparedStatement prestatement=null;  
    Connection conn=null;  
    ResultSet result=null;  
    
	public update() {
		setTitle("更新学生信息");
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
		
		labelId.setFont(new java.awt.Font("宋体", Font.BOLD, 15));
		labelName.setFont(new java.awt.Font("宋体", Font.BOLD, 15));
		labelGender.setFont(new java.awt.Font("宋体", Font.BOLD, 15));
		labelClass.setFont(new java.awt.Font("宋体", Font.BOLD, 15));
		labelGrade.setFont(new java.awt.Font("宋体", Font.BOLD, 15));

		textId.setBounds(new Rectangle(570, 70, 150, 35));
		textName.setBounds(new Rectangle(570, 120, 150, 35));
		textGender.setBounds(new Rectangle(570, 170, 150, 35));
		textClass.setBounds(new Rectangle(570, 220, 150, 35));
		textGrade.setBounds(new Rectangle(570, 270, 150, 35));
		
		buttonUpdate.setBounds(new Rectangle(570, 330, 60, 30));
		buttonReturn.setBounds(new Rectangle(660, 330, 60, 30));
		
		buttonUpdate.addActionListener(new updateLisener());
		buttonReturn.addActionListener(new returnLisener());
		
		table();
	}
	void table()
	{
        try {      
            //加载驱动  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            //得到连接  
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC", "root",
				"wp010214");                
            prestatement=conn.prepareStatement("select * from student");           
            result=prestatement.executeQuery();  
            
            while (result.next()) {
				// rowData可以存放多行
				Vector row = new Vector();
				row.add(result.getInt(1));
				row.add(result.getString(2));
				row.add(result.getString(3));
				row.add(result.getString(4));
				row.add(result.getInt(5));

				// 加入到rowData
				rowData.add(row);
			}

			prestatement = conn.prepareStatement("select * from class");
			result = prestatement.executeQuery();
			while (result.next()) {
				// rowData可以存放多行
				Vector row = new Vector();
				row.add(result.getString(1));
				row.add(result.getInt(2));
				row.add(result.getString(3));
				row.add(result.getInt(4));

				// 加入到rowData
				classrowData.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("班级");
		columnNames.add("年级");
		table = new JTable(rowData, columnNames);
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(new Rectangle(30, 70, 400, 200));

		classColumnNames.add("班级");
		classColumnNames.add("年级");
		classColumnNames.add("学院");
		classColumnNames.add("人数");
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
		panel.add(buttonUpdate);
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
	class updateLisener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String s = textId.getText();
			String class_name=textClass.getText();
			String stu_name=textName.getText();
			String gender=textGender.getText();
			String s2=textGrade.getText();
			if (s.equals("") || stu_name.equals("") || gender.equals("") || class_name.equals("") || s2.equals("")) {
				JOptionPane.showMessageDialog(null, "请正确填写信息", "失败", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				int id = Integer.parseInt(s);
				int grade=Integer.parseInt(s2);
			try {
				database.update(id,stu_name,gender,class_name,grade);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
			dispose();
			new update();
		}
	}
	class returnLisener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new mainwin();
		}
	}
	public static void main(String args[]) {
		new update();
	}
}

