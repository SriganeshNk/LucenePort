/*    */ package org.apache.lucene.demo.html;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.Writer;
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
/*    */ 
/*    */ class ParserThread
/*    */   extends Thread
/*    */ {
/*    */   HTMLParser parser;
/*    */   
/*    */   ParserThread(HTMLParser p)
/*    */   {
/* 26 */     this.parser = p;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/*    */     try {
/*    */       try {
/* 33 */         this.parser.HTMLDocument();
/*    */       } catch (ParseException e) {
/* 35 */         System.out.println("Parse Aborted: " + e.getMessage());
/*    */       } catch (TokenMgrError e) {
/* 37 */         System.out.println("Parse Aborted: " + e.getMessage());
/*    */       } finally {
/* 39 */         this.parser.pipeOut.close();
/* 40 */         synchronized (this.parser) {
/* 41 */           this.parser.summary.setLength(HTMLParser.SUMMARY_LENGTH);
/* 42 */           this.parser.titleComplete = true;
/* 43 */           this.parser.notifyAll();
/*    */         }
/*    */       }
/* 39 */       this.parser.pipeOut.close();
/* 40 */       synchronized (this.parser) {
/* 41 */         this.parser.summary.setLength(HTMLParser.SUMMARY_LENGTH);
/* 42 */         this.parser.titleComplete = true;
/* 43 */         this.parser.notifyAll();
/*    */       }
/*    */     }
/*    */     catch (IOException e) {
/* 47 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\apache\lucene\demo\html\ParserThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */