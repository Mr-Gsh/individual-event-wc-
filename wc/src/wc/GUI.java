package wc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frmWc;
	private JTextField num;
	private JTextField number;
	private JTextField linenum;
	private JTextField spaceline;
	private JTextField nodeline;
	private JTextField codeline;
	File file;
	function wcf=new function();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmWc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {
		initialize();
	}

	private void initialize() {
		frmWc = new JFrame();
		frmWc.setTitle("wc");
		frmWc.setResizable(false);
		frmWc.setBounds(280, 50, 800, 600);
		frmWc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmWc.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 0, 493, 562);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		panel_1.add(textArea, BorderLayout.CENTER);
		
		JButton choosefile = new JButton("选择文件");
		choosefile.setBounds(10, 32, 122, 23);
		panel.add(choosefile);
		
		JLabel filename = new JLabel("文件名:");
		filename.setBounds(10,70,80,23);
		panel.add(filename);
		
		JTextArea filepath = new JTextArea();
		filepath.setBounds(10, 95, 260, 40);
		filepath.setLineWrap(true);
		filepath.setWrapStyleWord(true); 
		filepath.setEditable(false);
		panel.add(filepath);
		
		JButton code = new JButton("统计字符数、词数、行数");
		code.setBounds(30, 220, 240, 30);
		panel.add(code);
		
		JButton diffline = new JButton("统计空行、注释行、代码行");
		diffline.setBounds(10, 400, 220, 30);
		panel.add(diffline);
		
		num = new JTextField();
		num.setBounds(120, 280, 90, 30);
		num.setEditable(false);
		panel.add(num);
		num.setColumns(10);
		
		number = new JTextField();
		number.setBounds(120, 320, 90, 30);
		number.setEditable(false);
		panel.add(number);
		number.setColumns(10);
		
		linenum = new JTextField();
		linenum.setColumns(10);
		linenum.setBounds(120, 360, 90, 30);
		linenum.setEditable(false);
		panel.add(linenum);
		
		spaceline = new JTextField();
		spaceline.setColumns(10);
		spaceline.setBounds(70, 437, 93, 29);
		spaceline.setEditable(false);
		panel.add(spaceline);
		
		nodeline = new JTextField();
		nodeline.setColumns(10);
		nodeline.setBounds(70, 476, 93, 29);
		nodeline.setEditable(false);
		panel.add(nodeline);
		
		codeline = new JTextField();
		codeline.setColumns(10);
		codeline.setBounds(70, 515, 93, 30);
		codeline.setEditable(false);
		panel.add(codeline);
		
		JLabel label = new JLabel("字符数");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 280, 54, 29);
		panel.add(label);
		
		JLabel label_1 = new JLabel("词数");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 320, 54, 29);
		panel.add(label_1);
		
		JLabel label_2= new JLabel("行数");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 360, 54, 29);
		panel.add(label_2);
		
		JLabel label_3= new JLabel("空行");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 437, 54, 29);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("注释行");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 476, 54, 29);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("代码行");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(10, 515, 54, 30);
		panel.add(label_5);
		
		choosefile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(".");
				int result=filechooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
				file=filechooser.getSelectedFile();
				if(file!=null) {
					if(textArea.getText()!=null) {
						textArea.setText("");
					}
					if(filepath.getText()!=null) {
						filepath.setText("");
						filepath.setText(file.getPath());
					}
					try {
						InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GB2312");
						BufferedReader br = new BufferedReader(read);
						String line=null;
						while((line=br.readLine())!=null) {
							textArea.append(line+"\r\n");
						}
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			}
		});
		
		code.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file.exists()) {
					String filename = file.getAbsolutePath();
					try {
						int chara=wcf.getCharnumber(filename);
						num.setText(chara+"");
						int word=wcf.getWordnumber(filename);
						number.setText(word+"");
						int line=wcf.getLinenumber(filename);
						linenum.setText(line+"");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});

		diffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file.exists()) {
					try {
						int[] line = wcf.diffline(file);
						spaceline.setText(line[0]+"");
						nodeline.setText(line[1]+"");
						codeline.setText(line[2]+"");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
	}
}