package wc;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private static Scanner scanner;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
			
				String str = null;
				function ft = new function();
				//循环询问命令输入
				while(true) {
					System.out.print("请输入命令:");
					//命令输入
					scanner = new Scanner(System.in);
					if(scanner.hasNext()) {
					str=scanner.nextLine();
					}
					//第一个作为判断,第二个为文件路径
					String[] strword = str.split(" ");
					if(strword.length==2) {
					if(strword[0].equals("-c")) {
						int chara=ft.getCharnumber(strword[1]);
						System.out.println("该文件的字符数:"+chara);
					}else if(strword[0].equals("-w")) {
						int word=ft.getWordnumber(strword[1]);
						System.out.println("该文件的词数:"+word);
					}else if(strword[0].equals("-l")) {
						int line=ft.getLinenumber(strword[1]);
						System.out.println("该文件的行数:"+line);
					}else if(strword[0].equals("-a")) {
						File file = new File(strword[1]);
						ft.diffline(file);
					}else if(strword[0].equals("-s-a")) {
						File file = new File(strword[1]);
						ft.diffline(file);
					}
					}else {
						if(strword[0].equals("-x")) {
							//GUI.main(null);
						}else if(strword[0].equals("end")) {
							break;
						}else {
							System.out.println("命令输入错误，请重新输入！");
						}
					}
				}
		}

	}
