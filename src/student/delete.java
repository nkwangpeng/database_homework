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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class DeletePanel extends JPanel{  
    public void paintComponent(Graphics g){  
        super.paintComponent(g);  
        //����һ�ű���ͼƬ  2.jpg��ͼƬ��·��  �Լ��趨Ϊ�Լ���Ҫ��ӵ�ͼƬ  
        Image image = new ImageIcon("images//back.png").getImage();  
        g.drawImage(image, 0, 0, this);  
    }  
}
public class delete extends JFrame {
	DeletePanel panel=new DeletePanel();
	JLabel label = new JLabel("�����ɾ����ѧ��ѧ��");
	JTextField textId = new JTextField();
	JButton buttonDelete = new JButton("ɾ��");
	JButton buttonReturn = new JButton("����");
	
	Vector rowData = new Vector();  //rowData���Դ�Ŷ���,��ʼ�����ݿ���ȡ 
	Vector scorerowData = new Vector();
	Vector classrowData = new Vector();
	Vector columnNames = new Vector();
	Vector scorecolNames = new Vector();
	Vector classcolNames = new Vector();

    JTable table=null;  
    JTable scoretable=null;
    JTable classtable=null;
    JScrollPane scrollpane=null; 
    JScrollPane scorescrollpane=null;
    JScrollPane classscrollpane=null;
    PreparedStatement prestatement=null;  
    Connection conn=null;  
    ResultSet result=null;  
    
	public delete() {
		setTitle("ɾ��ѧ����Ϣ");
		label.setBounds(new Rectangle(530, 210, 200, 40));
		textId.setBounds(new Rectangle(530, 260, 200, 35));
		
		label.setForeground(Color.white);
		label.setFont(new java.awt.Font("����", Font.BOLD, 20));
		
		buttonDelete.setBounds(new Rectangle(530, 325, 60, 30));
		buttonReturn.setBounds(new Rectangle(650, 325, 60, 30));
		
		buttonDelete.addActionListener(new deleteLisener());
		buttonReturn.addActionListener(new returnLisener());
		                        
        //��ʼ��Jtable  
		table();
	}
	void table()
	{
        try {      
            //��������  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            //�õ�����  
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC", "root",
					"wp010214");                
            prestatement=conn.prepareStatement("select * from student");           
            result=prestatement.executeQuery();  
            
            while(result.next()){  
                //rowData���Դ�Ŷ���  
                Vector hang=new Vector();  
                hang.add(result.getInt(1));  
                hang.add(result.getString(2));  
                hang.add(result.getString(3));  
                hang.add(result.getString(4));  
                hang.add(result.getInt(5));  
                  
                //���뵽rowData  
                rowData.add(hang);             	
            }  
            
            prestatement=conn.prepareStatement("select * from score");
            result=prestatement.executeQuery();
            while(result.next()){  
                //rowData���Դ�Ŷ���  
                Vector hang=new Vector();  
                hang.add(result.getInt(1));  
                hang.add(result.getString(2));   
                hang.add(result.getInt(3));  
                  
                //���뵽rowData  
                scorerowData.add(hang);             	
            }  
            
            prestatement=conn.prepareStatement("select * from class");
            result=prestatement.executeQuery();
            while(result.next()){  
                //rowData���Դ�Ŷ���  
                Vector hang=new Vector();  
                hang.add(result.getString(1));  
                hang.add(result.getInt(2));   
                hang.add(result.getString(3));  
                hang.add(result.getInt(4)); 
                  
                //���뵽rowData  
                classrowData.add(hang);             	
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
        columnNames.add("ѧ��");  
        columnNames.add("����");  
        columnNames.add("�Ա�");  
        columnNames.add("�༶");  
        columnNames.add("�꼶");         
        table = new JTable(rowData,columnNames); 
		scrollpane = new JScrollPane(table);  
		scrollpane.setBounds(new Rectangle(30, 70, 400, 150));
        
        scorecolNames.add("ѧ��");
        scorecolNames.add("�γ�");
        scorecolNames.add("�ɼ�");
        scoretable=new JTable(scorerowData,scorecolNames);
        scorescrollpane=new JScrollPane(scoretable);
        scorescrollpane.setBounds(new Rectangle(30, 250,400, 150));
        
        classcolNames.add("�༶");
        classcolNames.add("�꼶");
        classcolNames.add("ѧԺ");
        classcolNames.add("����");
        classtable=new JTable(classrowData,classcolNames);
        classscrollpane=new JScrollPane(classtable);
        classscrollpane.setBounds(new Rectangle(30, 430, 400, 150));
             
		/*Container contpane = getContentPane();
		contpane.setLayout(null);
		contpane.add(label);
		contpane.add(textId);
		contpane.add(buttonDelete);
		contpane.add(buttonReturn);
		contpane.add(scrollpane);
		contpane.add(scorescrollpane);
		contpane.add(classscrollpane);*/
        
        panel.setLayout(null);
		panel.add(label);
		panel.add(textId);
		panel.add(buttonDelete);
		panel.add(buttonReturn);
		panel.add(scrollpane);
		panel.add(scorescrollpane);
		panel.add(classscrollpane);
		this.add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	class deleteLisener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = textId.getText();
			int key = Integer.parseInt(s);
			try {
				database.delete(key);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			dispose();
			new delete();
		}
	}
	
	class returnLisener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new mainwin();
		}
	}
	public static void main(String args[]) {
		new delete();
	}
}

