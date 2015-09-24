package org.apache.lucene.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.arabidopsis.ahocorasick.AhoCorasick;
import org.arabidopsis.ahocorasick.SearchResult;

public class FileDocument
{
	public static Document Document(File f, Connection conn, AhoCorasick tree) throws IOException
	{
		Document doc = new Document();
		doc.add(new Field("path", f.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("modified",
				DateTools.timeToString(f.lastModified(), DateTools.Resolution.MINUTE), 
				Field.Store.YES, 
				Field.Index.NOT_ANALYZED));
		FileInputStream infileStream = new FileInputStream(f);
		InputStreamReader instream = null;
		try {
			instream = new InputStreamReader(infileStream, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Reader in = new BufferedReader(instream);
		int ch = 0;
		int count = 0;
		StringBuffer buffer = new StringBuffer();
		for (count = 0; count < 1000; count++) {
			try {
				ch = in.read();
				buffer.append((char)ch);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		in.close();
		instream.close();
		long longDate = 0L;
		try {
			String performedDate = "Performed on Date : ";
			String str_date = buffer.substring(buffer.indexOf(performedDate)+performedDate.length(), buffer.indexOf("</b><br><b>Event Title :")).trim();
			//String str_date = buffer.substring(184, 195).trim();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Date date = formatter.parse(str_date);
			longDate = date.getTime();
			System.out.println("Date: " + str_date + " " + longDate);
		}
		catch (ParseException e) {
			System.out.println("Exception Parsing date:" + e);
		}
		
		System.out.println("Person ID" + buffer.substring(buffer.indexOf("Person ID :") + "Person ID :".length(), buffer.indexOf("<\b><br><b> MRN Number :")).trim());
		System.out.println("Encounter ID" + buffer.substring(buffer.indexOf("Encounter ID :") + "Encounter ID :".length(), buffer.indexOf("<\b><br><b> Person ID :")).trim());
		System.out.println("MRN Number" + buffer.substring(buffer.indexOf("MRN Number :") + "MRN Number :".length(), buffer.indexOf("<\b><br> <b> Report")).trim());	
		
		doc.add(new Field("createdOn", DateTools.timeToString(longDate, 
				DateTools.Resolution.MINUTE), Field.Store.YES, 
				Field.Index.NOT_ANALYZED));

		doc.add(new Field("personID", 
				buffer.substring(buffer.indexOf("Person ID :") + "Person ID :".length(), buffer.indexOf("<\b><br><b> MRN Number :")).trim(), 
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		doc.add(new Field("EncounterID", 
				buffer.substring(buffer.indexOf("Encounter ID :") + "Encounter ID :".length(), buffer.indexOf("<\b><br><b> Person ID :")).trim(),  
				Field.Store.YES, Field.Index.NOT_ANALYZED));

		doc.add(new Field("MRNNumber",
				buffer.substring(buffer.indexOf("MRN Number :") + "MRN Number :".length(), buffer.indexOf("<\b><br> <b> Report")).trim(), 
				Field.Store.YES, Field.Index.NOT_ANALYZED));

		doc.add(new Field("contents", new FileReader(f)));
		
		String Impression = "";
		StringBuffer content = new StringBuffer();
		try {
			BufferedReader input = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = input.readLine()) != null) {
				content.append(line);
			}
			input.close();
		}
		catch (Exception localException) {}
		int startIndex = content.indexOf("Impression");
		if (startIndex == -1) {
			startIndex = content.indexOf("IMPRESSION");
		}
		int endIndex = content.indexOf("Radiologist");
		if (endIndex == -1) {
			endIndex = content.indexOf("RADIOLOGIST");
		}
		if (startIndex >= 0) {
			if ((endIndex >= 10) && (endIndex > startIndex)) {
				if (endIndex - 10 - startIndex > 0) {
					Impression = content.substring(startIndex, endIndex - 9);
				} else {
					Impression = content.substring(startIndex, startIndex + 150);
				}
			}
		}
		else {
			Impression = "";
		}
		doc.add(new Field("impression", Impression.trim(), Field.Store.YES, Field.Index.ANALYZED));
		String description = "";
		int desIndex = content.toString().toLowerCase().indexOf("description");
		endIndex = content.toString().toLowerCase().indexOf("impression");

		System.out.println("First endIndex : " + endIndex);
		System.out.println("First desIndex : " + desIndex);
		System.out.println("First content len :" + content.length());
		
		if (content.length() <= endIndex) {
			description = "empty";
			System.out.println("length < endIndex");
		}
		else if ((desIndex != -1) && (endIndex != -1)) {
			if (endIndex - 1 > desIndex) {
				description = content.substring(desIndex, endIndex - 1);
			} else {
				description = content.substring(desIndex);
			}
		}
		doc.add(new Field("description", description.trim(), Field.Store.YES, Field.Index.ANALYZED));
		return doc;
	}

	public static Document Document(File f, File reportDestPath, Connection conn, AhoCorasick tree, HashMap<String, ArrayList<String>> synonymMap) throws IOException
	{
		Document doc = new Document();
		String tempFilePath = f.getName();
		String destFileName = reportDestPath.getPath() + "\\" + tempFilePath;
		System.out.println("dest file name = " + destFileName);

		doc.add(new Field("path", destFileName, Field.Store.YES, 
				Field.Index.NOT_ANALYZED));

		doc.add(new Field("modified", DateTools.timeToString(f.lastModified(), 
				DateTools.Resolution.MINUTE), Field.Store.YES, 
				Field.Index.NOT_ANALYZED));

		FileInputStream infileStream = new FileInputStream(f);
		InputStreamReader instream = null;
		try {
			instream = new InputStreamReader(infileStream, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Reader in = new BufferedReader(instream);
		int ch = 0;
		int count = 0;
		StringBuffer buffer = new StringBuffer();
		for (count = 0; count < 1000; count++) {
			try {
				ch = in.read();
				buffer.append((char)ch);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		in.close();
		instream.close();
		long longDate = 0L;
		
		/*System.out.println("Person ID" + buffer.substring(buffer.indexOf("Person ID :") + "Person ID :".length(), buffer.indexOf("</b><br><b> MRN Number :")).trim());
		System.out.println("Encounter ID" + buffer.substring(buffer.indexOf("Encounter ID :") + "Encounter ID :".length(), buffer.indexOf("</b><br><b>Person ID :")).trim());
		System.out.println("MRN Number" + buffer.substring(buffer.indexOf("MRN Number :") + "MRN Number :".length(), buffer.indexOf("</b><br> <b> Report")).trim());	
		*/
		try {
			String performedDate = "Performed on Date : ";
			String str_date = buffer.substring(buffer.indexOf(performedDate)+performedDate.length(), buffer.indexOf("</b><br><b>Event Title :")).trim();
			//System.out.println("Date: " + str_date);
			//String str_date = buffer.substring(184, 195).trim();
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Date date = formatter.parse(str_date);
			longDate = date.getTime();
			//System.out.println("Date: " + str_date + " " + longDate + " " + DateTools.timeToString(longDate, DateTools.Resolution.MINUTE));
		}
		catch (ParseException e) {
			System.out.println("Exception 12:" + e);
		}
		
		doc.add(new Field("createdOn", DateTools.timeToString(longDate, 
				DateTools.Resolution.MINUTE), Field.Store.YES, Field.Index.NOT_ANALYZED));

		doc.add(new Field("personID", 
				buffer.substring(buffer.indexOf("Person ID :") + "Person ID :".length(), buffer.indexOf("</b><br><b> MRN Number :")).trim(), 
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		doc.add(new Field("EncounterID", 
				buffer.substring(buffer.indexOf("Encounter ID :") + "Encounter ID :".length(), buffer.indexOf("</b><br><b>Person ID :")).trim(),  
				Field.Store.YES, Field.Index.NOT_ANALYZED));

		doc.add(new Field("MRNNumber",
				buffer.substring(buffer.indexOf("MRN Number :") + "MRN Number :".length(), buffer.indexOf("</b><br> <b> Report")).trim(), 
				Field.Store.YES, Field.Index.NOT_ANALYZED));

		doc.add(new Field("contents", new FileReader(f)));
		
		String Impression = "";
		StringBuffer content = new StringBuffer();
		try {
			BufferedReader input = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = input.readLine()) != null) {
				content.append(line);
			}
			input.close();
		}
		catch (Exception localException) {}
		int startIndex = content.indexOf("Impression");
		if (startIndex == -1) {
			startIndex = content.indexOf("IMPRESSION");
		}
		int endIndex = content.indexOf("Radiologist");
		if (endIndex == -1) {
			endIndex = content.indexOf("RADIOLOGIST");
		}
		if (startIndex >= 0) {
			if ((endIndex >= 10) && (endIndex > startIndex)) {
				if (endIndex - 10 - startIndex > 0) {
					Impression = content.substring(startIndex, endIndex - 9);
				} 
				else {
					Impression = content.substring(startIndex, startIndex + 150);
				}
			}
		}
		else {
			Impression = "";
		}
		doc.add(new Field("impression", Impression.trim(), Field.Store.YES, Field.Index.ANALYZED));
		String description = "";
		int desIndex = content.toString().toLowerCase().indexOf("description");
		endIndex = content.toString().toLowerCase().indexOf("impression");

		System.out.println("Second endIndex : " + endIndex);
		System.out.println("Second desIndex : " + desIndex);
		System.out.println("Second content len :" + content.length());
		
		if (content.length() <= endIndex) {
			description = "empty";
			System.out.println("length < endIndex");
		}
		else if ((desIndex != -1) && (endIndex != -1)) {
			if (endIndex - 1 > desIndex) {
				description = content.substring(desIndex, endIndex - 1);
			} else {
				description = content.substring(desIndex);
			}
		}
		doc.add(new Field("description", description.trim(), Field.Store.YES, Field.Index.ANALYZED));
		Iterator<?> localIterator1;
		for (Iterator<?> iter = tree.search(content.toString().getBytes()); iter.hasNext();) {
			SearchResult result = (SearchResult)iter.next();
			localIterator1 = result.getOutputs().iterator();
			Object res = localIterator1.next();
			ArrayList<String> synonyms = (ArrayList<String>)synonymMap.get(res);
			if (synonyms != null) {
				for (String synonym : synonyms) {
					doc.add(new Field("radlexSynonym", synonym, Field.Store.YES, Field.Index.ANALYZED));
					doc.add(new Field("contents", synonym, Field.Store.YES, Field.Index.ANALYZED));
				}
			}
		}
		return doc;
	}
}