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
	 * ���룺TinyCCode�ļ����µ��ļ�
	 * ���0��PreCode�ļ�����Ԥ����֮���Դ���룬��ȥ��ע�͵ȴ���
	 * ���1��InterCode�ļ�����������м���룬����̨Ҳ�����
	 * ���2��AsmCode�ļ����������x86�ܹ��Ļ����룬����̨Ҳ�����
	 * �ļ�������һһ��Ӧ��
	 * 
	 * ��������Լ���Ƶ�Tiny-C���룿
	 * 1. ����Tiny��C���﷨��ʽ������д��txt�ı�������TinyCCode�ļ���
	 * 2. �� codeName ���飬��������ļ���
	 * 3. �ı� main ������index��ֵ
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * �ı�index��ֵ��ѡ���Ӧ�Ĵ����ļ���index=4������ʾѡ��insert sort.txt
		 */
		int index = 6;
		
		String src = srcFloder+codeName[index]+extensionName;
		String pre = preFloder+codeName[index]+"_pre"+extensionName;
		String inter = interFloder+codeName[index]+"_inter"+extensionName;
		String asm = asmFloder+codeName[index]+"_asm"+extensionName;
		
		//Ԥ����
		Preprocessing.Preprocess(src,pre);
		
		//��ʼ��
		Global.init(pre,inter,"stack.txt");
		Global.stackFw.write("���	����ջ		����\n");
		//ǰ��
		Lexer lex=new Lexer(Global.srcFr);
		Parser parse=new Parser(lex,Global.stackFw);
		parse.program();
		//System.out.println(lex.scan().toString());
		Global.close();
		//���
		System.out.println("-----------------------------------------------");
		Assembler assembler = new Assembler(inter,asm);
		assembler.assemble();
		assembler.asmCodeSave();
	}

}
