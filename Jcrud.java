package javacrud;


import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Jcrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jcrud window = new Jcrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jcrud() {
		initialize();
		Connect();
	//	table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","shivu047");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
	
//	  public void table_load()
//	    {
//	    	try 
//	    	{
//		    pst = con.prepareStatement("select * from book");
//		    rs = pst.executeQuery();
//		    table.setModel(DbUtils.resultSetToTableModel(rs));
//		} 
//	    	catch (SQLException e) 
//	    	 {
//	    		e.printStackTrace();
//		  } 
//	    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(387, 49, 179, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 104, 434, 304);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(33, 52, 102, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(35, 113, 102, 30);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(33, 169, 102, 30);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(183, 59, 205, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(183, 120, 205, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(183, 176, 205, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				try {
				pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
				pst.setString(1, bname);
				pst.setString(2, edition);
				pst.setString(3, price);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Addeddd!!!!!");
				//table_load();
				          
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
			        }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(33, 225, 102, 52);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(169, 225, 102, 52);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				txtbname.setText("");
		            txtedition.setText("");
		            txtprice.setText("");
		            txtbname.requestFocus();

				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClear.setBackground(Color.WHITE);
		btnClear.setBounds(304, 225, 102, 52);
		panel.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(497, 116, 428, 292);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(21, 419, 434, 72);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(48, 22, 102, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					
					 try {
				          
				            String id = txtbid.getText();

				                pst = con.prepareStatement("select name,edition,price from book where id = ?");
				                pst.setString(1, id);
				                ResultSet rs = pst.executeQuery();

				            if(rs.next()==true)
				            {
				              
				                String name = rs.getString(1);
				                String edition = rs.getString(2);
				                String price = rs.getString(3);
				                
				                txtbname.setText(name);
				                txtedition.setText(edition);
				                txtprice.setText(price);
				                
				                
				            }   
				            else
				            {
				            	txtbname.setText("");
				            	txtedition.setText("");
				                txtprice.setText("");
				                 
				            }
				            
				        } 
					
					 catch (SQLException ex) {
				           
				        }

				}
			
		});
		txtbid.setColumns(10);
		txtbid.setBounds(182, 29, 205, 20);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					String bname,edition,price,bid;
					
					
					bname = txtbname.getText();
					edition = txtedition.getText();
					price = txtprice.getText();
					bid  = txtbid.getText();
					
					 try {
							pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
							pst.setString(1, bname);
				            pst.setString(2, edition);
				            pst.setString(3, price);
				            pst.setString(4, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
				           // table_load();
				           
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbname.requestFocus();
						}

			            catch (SQLException e1) {
							
							e1.printStackTrace();
						}
		
				}
			
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(507, 429, 102, 52);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String bid;
	bid  = txtbid.getText();
	
	 try {
			pst = con.prepareStatement("delete from book where id =?");
	
            pst.setString(1, bid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
           // table_load();
           
            txtbname.setText("");
            txtedition.setText("");
            txtprice.setText("");
            txtbname.requestFocus();
		}

        catch (SQLException e1) {
			
			e1.printStackTrace();
		}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(668, 429, 102, 52);
		frame.getContentPane().add(btnDelete);
	}
}
