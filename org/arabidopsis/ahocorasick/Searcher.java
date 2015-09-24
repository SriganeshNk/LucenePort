/*    */ package org.arabidopsis.ahocorasick;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ 
/*    */ 
/*    */ class Searcher
/*    */   implements Iterator
/*    */ {
/*    */   private SearchResult currentResult;
/*    */   private AhoCorasick tree;
/*    */   
/*    */   Searcher(AhoCorasick tree, SearchResult result)
/*    */   {
/* 16 */     this.tree = tree;
/* 17 */     this.currentResult = result;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 22 */     return this.currentResult != null;
/*    */   }
/*    */   
/*    */   public Object next()
/*    */   {
/* 27 */     if (!hasNext())
/* 28 */       throw new NoSuchElementException();
/* 29 */     Object result = this.currentResult;
/* 30 */     this.currentResult = this.tree.continueSearch(this.currentResult);
/* 31 */     return result;
/*    */   }
/*    */   
/*    */   public void remove()
/*    */   {
/* 36 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\Searcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */