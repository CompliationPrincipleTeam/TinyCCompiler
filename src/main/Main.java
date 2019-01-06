package main;
import java.io.IOException;

import assembler.Assembler;
import global.Global;
import lexer.Lexer;
import parser.Parser;
import preprocessing.Preprocessing;


public class Main {
	private static final String srcFloder = "TinyCCode/";
	private static final String preFloder = "PreCode/";
	private static final String interFloder = "InterCode/";
	private static final String asmFloder = "AsmCode/";

	private static final String[] codeName = {"bubble sort", "select sort", 
											  "sum",         "swap", 
											  "insert sort", "shell sort",
											  "test"};
	private static final String extensionName = ".txt";
	
	/*
	 * 输入：TinyCCode文件夹下的文件
	 * 输出0：PreCode文件夹是预处理之后的源代码，即去除注释等处理
	 * 输出1：InterCode文件夹是输出的中间代码，控制台也有输出
	 * 输出2：AsmCode文件夹是输出的x86架构的汇编代码，控制台也有输出
	 * 文件名都是一一对应的
	 * 
	 * 如何运行自己设计的Tiny-C代码？
	 * 1. 按照Tiny—C的语法格式，代码写成txt文本，放在TinyCCode文件夹
	 * 2. 在 codeName 数组，加入代码文件名
	 * 3. 改变 main 函数中index的值
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * 改变index的值，选择对应的代码文件；index=4，即表示选择insert sort.txt
		 */
		int index = 6;
		
		String src = srcFloder+codeName[index]+extensionName;
		String pre = preFloder+codeName[index]+"_pre"+extensionName;
		String inter = interFloder+codeName[index]+"_inter"+extensionName;
		String asm = asmFloder+codeName[index]+"_asm"+extensionName;
		
		//预处理
		Preprocessing.Preprocess(src,pre);
		
		//初始化
		Global.init(pre,inter,"stack.txt");
		Global.stackFw.write("编号	符号栈		动作\n");
		//前端
		Lexer lex=new Lexer(Global.srcFr);
		Parser parse=new Parser(lex,Global.stackFw);
		parse.program();
		//System.out.println(lex.scan().toString());
		Global.close();
		//后端
		System.out.println("-----------------------------------------------");
		Assembler assembler = new Assembler(inter,asm);
		assembler.assemble();
		assembler.asmCodeSave();
	}

}
