/*    */ package org.arabidopsis.ahocorasick;
/*    */ 
/*    */ 
/*    */ 
/*    */ class SparseEdgeList
/*    */   implements EdgeList
/*    */ {
/*    */   private Cons head;
/*    */   
/*    */ 
/*    */   public SparseEdgeList()
/*    */   {
/* 13 */     this.head = null;
/*    */   }
/*    */   
/*    */   public State get(byte b)
/*    */   {
/* 18 */     Cons c = this.head;
/* 19 */     while (c != null) {
/* 20 */       if (c.b == b)
/* 21 */         return c.s;
/* 22 */       c = c.next;
/*    */     }
/* 24 */     return null;
/*    */   }
/*    */   
/*    */   public void put(byte b, State s) {
/* 28 */     this.head = new Cons(b, s, this.head);
/*    */   }
/*    */   
/*    */   public byte[] keys()
/*    */   {
/* 33 */     int length = 0;
/* 34 */     Cons c = this.head;
/* 35 */     while (c != null) {
/* 36 */       length++;
/* 37 */       c = c.next;
/*    */     }
/* 39 */     byte[] result = new byte[length];
/* 40 */     c = this.head;
/* 41 */     int j = 0;
/* 42 */     while (c != null) {
/* 43 */       result[j] = c.b;
/* 44 */       j++;
/* 45 */       c = c.next;
/*    */     }
/* 47 */     return result;
/*    */   }
/*    */   
/*    */   private static class Cons
/*    */   {
/*    */     byte b;
/*    */     State s;
/*    */     Cons next;
/*    */     
/*    */     public Cons(byte b, State s, Cons next) {
/* 57 */       this.b = b;
/* 58 */       this.s = s;
/* 59 */       this.next = next;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\SparseEdgeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */