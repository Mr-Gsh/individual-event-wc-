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
				//ѭ��ѯ����������
				while(true) {
					System.out.print("����������:");
					//��������
					scanner = new Scanner(System.in);
					if(scanner.hasNext()) {
					str=scanner.nextLine();
					}
					//��һ����Ϊ�ж�,�ڶ���Ϊ�ļ�·��
					String[] strword = str.split(" ");
					if(strword.length==2) {
					if(strword[0].equals("-c")) {
						int chara=ft.getCharnumber(strword[1]);
						System.out.println("���ļ����ַ���:"+chara);
					}else if(strword[0].equals("-w")) {
						int word=ft.getWordnumber(strword[1]);
						System.out.println("���ļ��Ĵ���:"+word);
					}else if(strword[0].equals("-l")) {
						int line=ft.getLinenumber(strword[1]);
						System.out.println("���ļ�������:"+line);
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
							System.out.println("��������������������룡");
						}
					}
				}
		}

	}
