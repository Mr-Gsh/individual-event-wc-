package wc;

import java.io.*;
import java.util.regex.*;

public class function {
	private BufferedReader br;
	
	//�ļ���ͳ�ƺ���
	int getWordnumber(String filename) throws IOException {
		int num=0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//��ȡ�ļ�
			FileReader fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = null;
			StringBuffer sbf = new StringBuffer();
			while((line=br.readLine())!= null) {
				sbf.append(line);
				String str = sbf.toString();
				//������ʽ�滻����
				str = str.replaceAll("[\\p{Nd}\\u9fa5-\\uffe5\\p{Punct}\\s&&[^-]]", " ");
				//���ո����ݷָ�
				strword = str.split("\\s+");
				num=strword.length;
			}
			br.close();
			fr.close();
		}else {
			System.out.println("�ļ�������,�����������ļ�!");
		}
		return num;
	}
	
	//�ļ��ַ�ͳ�ƺ���
	int getCharnumber(String filename) throws IOException {
		int number = 0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//��ȡ�ļ�
			FileReader fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = null;
			String str=null;
			StringBuffer sbf = new StringBuffer();
			while((line=br.readLine())!= null) {
				sbf.append(line);
				str = sbf.toString();
				strword = str.split("\\s+");
			}
			if(strword==null) {
				System.out.println("���ļ�Ϊ��");
				br.close();
				fr.close();
				return 0;
			}
			for(int i=0;i<strword.length;i++) {
				Pattern pattern = Pattern.compile("[0-9a-zA-Z]*");
				Matcher matcher = pattern.matcher(strword[i]);
				if(matcher.find()) {
					number+=matcher.regionEnd();
				}
			}
			br.close();
			fr.close();
		}else {
			System.out.println("�ļ�������,�����������ļ�!");
		}
		return number;
	}
	
	//�ļ�����ͳ�ƺ���
	int getLinenumber(String filename) throws IOException {
		int linenum = 0;
		File file = new File(filename);
		if(file.exists()) {
			//��ȡ�ļ�
			FileReader fr = new FileReader(filename);
			//��ȡ�ļ�����
			LineNumberReader lnr = new LineNumberReader(fr);
			while(lnr.readLine()!= null) {
				linenum=lnr.getLineNumber();
			}
			lnr.close();
			fr.close();
		}else {
			System.out.println("�ļ�������,�����������ļ�!");
		}
		return linenum;
	}
	
		//ͳ���ļ������ļ����г����ļ��Ŀ��С������С�ע���У��еݹ��ļ�Ŀ¼����
			int[] diffline(File file) throws IOException {
				int spaceline = 0;
				int nodeline = 0;
				int codeline = 0;
				if (file == null || !file.exists()) {
					System.out.println(file + "���ļ������ڣ�");
				}else {
				if (file.isDirectory()) {
					File[] files = file.listFiles(new FileFilter() {
						public boolean accept(File pathname) {
							return pathname.getName().endsWith(".java")|| pathname.isDirectory()||
									pathname.getName().endsWith(".c")||pathname.getName().endsWith(".cpp")  ;
						}
					});
					//�ݹ��ļ�Ŀ¼
					for (File target : files) {
						diffline(target);
					}
				} else {
					// ��ָ��·�����ļ����ַ�����
					BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					// ����ƥ��ÿһ�е�����ƥ����
					Pattern nodeLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+", 
							Pattern.MULTILINE + Pattern.DOTALL);	// ע��ƥ����(ƥ�䵥�С����С��ĵ�ע��)
					Pattern spaceLinePattern = Pattern.compile("^\\s*$");	// �հ���ƥ������ƥ��س���tab�����ո�
					
					// �����ļ��е�ÿһ�У�����������ƥ��Ľ����¼ÿһ��ƥ��Ľ��
					String line = null;
						while((line = bufr.readLine()) != null) {
							if (nodeLinePattern.matcher(line).find()) {
								nodeline ++;
							}else if (spaceLinePattern.matcher(line).find()) {
								spaceline ++;
							}else{
								codeline ++;
							} 
						}
				}
				}
				int[] Sline= {spaceline,nodeline,codeline};
				return Sline;
			}
		
}
