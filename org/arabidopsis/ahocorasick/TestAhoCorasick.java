/*    */ package org.arabidopsis.ahocorasick;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.Statement;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class TestAhoCorasick
/*    */ {
/*    */   public static void main(String[] argv) throws Exception
/*    */   {
/* 13 */     AhoCorasick tree = new AhoCorasick();
/* 14 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/* 15 */     Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost/radlex", "root", "root");
/* 16 */     System.out.println("object of one of the devs in sysec");
/* 17 */     if (conn == null) {
/* 18 */       System.out.println("Can't establish the connection!");
/* 19 */       System.exit(0);
/*    */     }
/*    */     
/* 22 */     Statement s = conn.createStatement();
/* 23 */     s.executeQuery("select name from radterms");
/* 24 */     ResultSet rs = s.getResultSet();
/* 25 */     while (rs.next()) {
/* 26 */       String term = rs.getString("name");
/*    */       
/* 28 */       if ((term != null) && (term.length() >= 3)) {
/* 29 */         tree.add(term.getBytes(), term);
/*    */       }
/*    */     }
/* 32 */     tree.prepare();
/* 33 */     rs.close();
/*    */     
/*    */ 
/*    */ 
/* 37 */     String text = "The ga3 ashish ashishkumar mutant of Arabidopsis is a gibberellin-responsive dwarf. We present data showing that the ga3-1 mutant is deficient in ent-kaurene oxidase activity, the first cytochrome P450-mediated step in the gibberellin biosynthetic pathway. By using a tissue combination of conventional map-based cloning and random sequencing we identified a putative cytochrome P450 gene mapping to the same location as GA3. Relative to the progenitor line, two ga3 mutant pneumothorax alleles contained single base changes generating in-frame stop codons in the predicted amino acid sequence of the P450. A genomic clone spanning the P450 locus complemented the ga3-2 mutant. The deduced GA3 protein defines an additional class of cytochrome P450 enzymes. The GA3 gene was expressed in all tissues examined, RNA abundancef being highest in inflorescence tissue.";
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
/* 49 */     java.util.Set termsThatHit = new java.util.HashSet();
/* 50 */     for (Iterator iter = tree.search(text.getBytes()); iter.hasNext();) {
/* 51 */       SearchResult result = (SearchResult)iter.next();
/*    */       
/* 53 */       System.out.println(result.getOutputs() + " : " + result.lastIndex);
/*    */     }
/*    */     
/* 56 */     conn.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\TestAhoCorasick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */