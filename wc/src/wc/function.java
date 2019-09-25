package wc;

import java.io.*;
import java.util.regex.*;

public class function {
	private BufferedReader br;
	
	//文件词统计函数
	int getWordnumber(String filename) throws IOException {
		int num=0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//读取文件
			FileReader fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = null;
			StringBuffer sbf = new StringBuffer();
			while((line=br.readLine())!= null) {
				sbf.append(line);
				String str = sbf.toString();
				//正则表达式替换符号
				str = str.replaceAll("[\\p{Nd}\\u9fa5-\\uffe5\\p{Punct}\\s&&[^-]]", " ");
				//按空格将内容分割
				strword = str.split("\\s+");
				num=strword.length;
			}
			br.close();
			fr.close();
		}else {
			System.out.println("文件不存在,请重新输入文件!");
		}
		return num;
	}
	
	//文件字符统计函数
	int getCharnumber(String filename) throws IOException {
		int number = 0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//读取文件
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
				System.out.println("该文件为空");
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
			System.out.println("文件不存在,请重新输入文件!");
		}
		return number;
	}
	
	//文件行数统计函数
	int getLinenumber(String filename) throws IOException {
		int linenum = 0;
		File file = new File(filename);
		if(file.exists()) {
			//读取文件
			FileReader fr = new FileReader(filename);
			//读取文件行数
			LineNumberReader lnr = new LineNumberReader(fr);
			while(lnr.readLine()!= null) {
				linenum=lnr.getLineNumber();
			}
			lnr.close();
			fr.close();
		}else {
			System.out.println("文件不存在,请重新输入文件!");
		}
		return linenum;
	}
	
		//统计文件或者文件夹中程序文件的空行、代码行、注释行，有递归文件目录功能
			int[] diffline(File file) throws IOException {
				int spaceline = 0;
				int nodeline = 0;
				int codeline = 0;
				if (file == null || !file.exists()) {
					System.out.println(file + "，文件不存在！");
				}else {
				if (file.isDirectory()) {
					File[] files = file.listFiles(new FileFilter() {
						public boolean accept(File pathname) {
							return pathname.getName().endsWith(".java")|| pathname.isDirectory()||
									pathname.getName().endsWith(".c")||pathname.getName().endsWith(".cpp")  ;
						}
					});
					//递归文件目录
					for (File target : files) {
						diffline(target);
					}
				} else {
					// 将指定路径的文件与字符流绑定
					BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					// 定义匹配每一行的正则匹配器
					Pattern nodeLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+", 
							Pattern.MULTILINE + Pattern.DOTALL);	// 注释匹配器(匹配单行、多行、文档注释)
					Pattern spaceLinePattern = Pattern.compile("^\\s*$");	// 空白行匹配器（匹配回车、tab键、空格）
					
					// 遍历文件中的每一行，并根据正则匹配的结果记录每一行匹配的结果
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
