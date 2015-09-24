/*    */ package org.apache.lucene.demo.html;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Test
/*    */ {
/*    */   public static void main(String[] argv)
/*    */     throws IOException, InterruptedException
/*    */   {
/* 24 */     if ("-dir".equals(argv[0])) {
/* 25 */       String[] files = new File(argv[1]).list();
/* 26 */       Arrays.sort(files);
/* 27 */       for (int i = 0; i < files.length; i++) {
/* 28 */         System.err.println(files[i]);
/* 29 */         File file = new File(argv[1], files[i]);
/* 30 */         parse(file);
/*    */       }
/*    */     } else {
/* 33 */       parse(new File(argv[0]));
/*    */     }
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public static void parse(File file)
/*    */     throws IOException, InterruptedException
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aconst_null
/*    */     //   1: astore_1
/*    */     //   2: new 71	java/io/FileInputStream
/*    */     //   5: dup
/*    */     //   6: aload_0
/*    */     //   7: invokespecial 73	java/io/FileInputStream:<init>	(Ljava/io/File;)V
/*    */     //   10: astore_1
/*    */     //   11: new 75	org/apache/lucene/demo/html/HTMLParser
/*    */     //   14: dup
/*    */     //   15: aload_1
/*    */     //   16: invokespecial 77	org/apache/lucene/demo/html/HTMLParser:<init>	(Ljava/io/InputStream;)V
/*    */     //   19: astore_2
/*    */     //   20: getstatic 80	java/lang/System:out	Ljava/io/PrintStream;
/*    */     //   23: new 83	java/lang/StringBuilder
/*    */     //   26: dup
/*    */     //   27: ldc 85
/*    */     //   29: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*    */     //   32: aload_2
/*    */     //   33: invokevirtual 88	org/apache/lucene/demo/html/HTMLParser:getTitle	()Ljava/lang/String;
/*    */     //   36: invokestatic 92	org/apache/lucene/demo/html/Entities:encode	(Ljava/lang/String;)Ljava/lang/String;
/*    */     //   39: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */     //   42: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*    */     //   45: invokevirtual 50	java/io/PrintStream:println	(Ljava/lang/String;)V
/*    */     //   48: getstatic 80	java/lang/System:out	Ljava/io/PrintStream;
/*    */     //   51: new 83	java/lang/StringBuilder
/*    */     //   54: dup
/*    */     //   55: ldc 105
/*    */     //   57: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*    */     //   60: aload_2
/*    */     //   61: invokevirtual 107	org/apache/lucene/demo/html/HTMLParser:getSummary	()Ljava/lang/String;
/*    */     //   64: invokestatic 92	org/apache/lucene/demo/html/Entities:encode	(Ljava/lang/String;)Ljava/lang/String;
/*    */     //   67: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */     //   70: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*    */     //   73: invokevirtual 50	java/io/PrintStream:println	(Ljava/lang/String;)V
/*    */     //   76: getstatic 80	java/lang/System:out	Ljava/io/PrintStream;
/*    */     //   79: ldc 110
/*    */     //   81: invokevirtual 50	java/io/PrintStream:println	(Ljava/lang/String;)V
/*    */     //   84: new 112	java/io/LineNumberReader
/*    */     //   87: dup
/*    */     //   88: aload_2
/*    */     //   89: invokevirtual 114	org/apache/lucene/demo/html/HTMLParser:getReader	()Ljava/io/Reader;
/*    */     //   92: invokespecial 118	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
/*    */     //   95: astore_3
/*    */     //   96: aload_3
/*    */     //   97: invokevirtual 121	java/io/LineNumberReader:readLine	()Ljava/lang/String;
/*    */     //   100: astore 4
/*    */     //   102: goto +17 -> 119
/*    */     //   105: getstatic 80	java/lang/System:out	Ljava/io/PrintStream;
/*    */     //   108: aload 4
/*    */     //   110: invokevirtual 50	java/io/PrintStream:println	(Ljava/lang/String;)V
/*    */     //   113: aload_3
/*    */     //   114: invokevirtual 121	java/io/LineNumberReader:readLine	()Ljava/lang/String;
/*    */     //   117: astore 4
/*    */     //   119: aload 4
/*    */     //   121: ifnonnull -16 -> 105
/*    */     //   124: goto +16 -> 140
/*    */     //   127: astore 5
/*    */     //   129: aload_1
/*    */     //   130: ifnull +7 -> 137
/*    */     //   133: aload_1
/*    */     //   134: invokevirtual 124	java/io/FileInputStream:close	()V
/*    */     //   137: aload 5
/*    */     //   139: athrow
/*    */     //   140: aload_1
/*    */     //   141: ifnull +7 -> 148
/*    */     //   144: aload_1
/*    */     //   145: invokevirtual 124	java/io/FileInputStream:close	()V
/*    */     //   148: return
/*    */     // Line number table:
/*    */     //   Java source line #37	-> byte code offset #0
/*    */     //   Java source line #39	-> byte code offset #2
/*    */     //   Java source line #40	-> byte code offset #11
/*    */     //   Java source line #41	-> byte code offset #20
/*    */     //   Java source line #42	-> byte code offset #48
/*    */     //   Java source line #43	-> byte code offset #76
/*    */     //   Java source line #44	-> byte code offset #84
/*    */     //   Java source line #45	-> byte code offset #96
/*    */     //   Java source line #46	-> byte code offset #105
/*    */     //   Java source line #45	-> byte code offset #113
/*    */     //   Java source line #47	-> byte code offset #127
/*    */     //   Java source line #48	-> byte code offset #129
/*    */     //   Java source line #49	-> byte code offset #137
/*    */     //   Java source line #48	-> byte code offset #140
/*    */     //   Java source line #50	-> byte code offset #148
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	149	0	file	File
/*    */     //   1	144	1	fis	java.io.FileInputStream
/*    */     //   19	70	2	parser	HTMLParser
/*    */     //   95	19	3	reader	java.io.LineNumberReader
/*    */     //   100	20	4	l	String
/*    */     //   127	11	5	localObject	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   2	127	127	finally
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\apache\lucene\demo\html\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */