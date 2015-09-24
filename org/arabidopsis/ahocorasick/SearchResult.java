/*    */ package org.arabidopsis.ahocorasick;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SearchResult
/*    */ {
/*    */   State lastMatchedState;
/*    */   byte[] bytes;
/*    */   int lastIndex;
/*    */   
/*    */   SearchResult(State s, byte[] bs, int i)
/*    */   {
/* 19 */     this.lastMatchedState = s;
/* 20 */     this.bytes = bs;
/* 21 */     this.lastIndex = i;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Set getOutputs()
/*    */   {
/* 29 */     return this.lastMatchedState.getOutputs();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getLastIndex()
/*    */   {
/* 38 */     return this.lastIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\SearchResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */