package org.apache.lucene.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.arabidopsis.ahocorasick.AhoCorasick;

public class IndexFiles
{
  private Connection conn = null;
  static File INDEX_DIR;
  
  public static void main(String[] args)
    throws SQLException
  {
    String usage = "java org.apache.lucene.demo.IndexFiles <indexDir> <tempReportDir> <destReportDir> ";
    if (args.length < 3)
    {
      System.err.println("Usage: " + usage);
      System.exit(1);
    }
    System.out.println(args[0]);
    INDEX_DIR = new File(args[0]);
    
    boolean incrementalIndexing = false;
    if (INDEX_DIR.exists())
    {
      System.out.println("Index directory is already present. Adding new documents to existing index.");
      incrementalIndexing = true;
    }
    System.out.println("Opeing tempReportDir");
    
    File tempReportDir = new File(args[1]);
    if ((!tempReportDir.exists()) || (!tempReportDir.canRead()))
    {
      System.out.println("Document directory '" + 
        tempReportDir.getAbsolutePath() + 
        "' does not exist or is not readable, please check the path");
      System.exit(1);
    }
    System.out.println("Opeing destReportDir");
    
    File destReportDir = new File(args[2]);
    if ((!destReportDir.exists()) || (!destReportDir.canRead()))
    {
      System.out.println("Document directory '" + 
        destReportDir.getAbsolutePath() + 
        "' does not exist or is not readable, please check the path");
      System.exit(1);
    }
    System.out.println("Opened temp and dest dirs");
    
    Date start = new Date();
    Connection conn = null;
    try
    {
      AhoCorasick tree = new AhoCorasick();
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection("jdbc:mysql://localhost/radlex", "root", "devroot");
      if (conn == null)
      {
        System.out.println("Can't establish the connection!");
        System.exit(0);
      }
      else
      {
        System.out.println("Connection Succesful");
      }
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("select name from radterms");
      while (resultSet.next())
      {
        String term = resultSet.getString("name");
        if ((term != null) && (term.length() >= 5)) {
          tree.add(term.getBytes(), term);
        }
      }
      tree.prepare();
      resultSet.close();
      
      String selectSynonyms = "select rt1.name rt1name, rt2.name rt2name from radlinks rl join radterms rt1 on rl.pid = rt1.radlex_id join radterms rt2 on rl.cid = rt2.radlex_id where relation_type = 'synonymof' ";
      
      resultSet = statement.executeQuery(selectSynonyms);
      
      HashMap<String, ArrayList<String>> synonymMap = new HashMap();
      while (resultSet.next())
      {
        String term1 = resultSet.getString("rt1name");
        String term2 = resultSet.getString("rt2name");
        
        ArrayList<String> term1Value = (ArrayList)synonymMap.get(term1);
        if (term1Value == null)
        {
          ArrayList<String> list = new ArrayList();
          list.add(term2);
          synonymMap.put(term1, list);
        }
        else
        {
          term1Value.add(term2);
        }
        ArrayList<String> term2Value = (ArrayList)synonymMap.get(term2);
        if (term2Value == null)
        {
          ArrayList<String> list = new ArrayList();
          list.add(term1);
          synonymMap.put(term2, list);
        }
        else
        {
          term2Value.add(term1);
        }
      }
      System.out.println(INDEX_DIR);
      IndexWriter writer = new IndexWriter(FSDirectory.open(INDEX_DIR), 
        new StandardAnalyzer(Version.LUCENE_CURRENT), 
        !incrementalIndexing, IndexWriter.MaxFieldLength.LIMITED);
      System.out.println("Indexing to directory '" + INDEX_DIR + "'...");
      indexDocs(writer, tempReportDir, destReportDir, conn, tree, synonymMap);
      System.out.println("Optimizing...");
      writer.optimize();
      writer.close();
      
      Date end = new Date();
      System.out.println(end.getTime() - start.getTime() + 
        " total milliseconds");
      conn.close();
    }
    catch (Exception e)
    {
      if (conn != null) {
        conn.close();
      }
      System.out.println(" caught a " + e.getClass() + 
        "\n with message: " + e.getMessage());
    }
  }
  
  static void indexDocs(IndexWriter writer, File tempReportDir, File destReportDir, Connection conn, AhoCorasick tree, HashMap<String, ArrayList<String>> synonymMap)
    throws IOException
  {
    if (tempReportDir.canRead()) {
      if (tempReportDir.isDirectory())
      {
        String[] files = tempReportDir.list();
        if (files != null) {
          for (int i = 0; i < files.length; i++) {
            indexDocs(writer, new File(tempReportDir, files[i]), 
              destReportDir, conn, tree, synonymMap);
          }
        }
      }
      else
      {
        System.out.println("adding " + tempReportDir);
        try
        {
          writer.addDocument(FileDocument.Document(tempReportDir, 
            destReportDir, conn, tree, synonymMap));
        }
        catch (FileNotFoundException localFileNotFoundException) {}
      }
    }
  }
}
