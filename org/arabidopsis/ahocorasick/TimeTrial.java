package org.arabidopsis.ahocorasick;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;



public class TimeTrial
{
	public static void main(String[] args)
			throws IOException
	{
		long startTime = System.currentTimeMillis();
		AhoCorasick tree = new AhoCorasick();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream("/usr/share/dict/words")));
		String line;
		while ((line = reader.readLine()) != null) { 
			tree.add(line.getBytes(), null);
		}
		tree.prepare();
		long endTime = System.currentTimeMillis();
		System.out.println("endTime - startTime = " + (
				endTime - startTime) + 
				" milliseconds");
	}
}


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\TimeTrial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */