/*    */ package org.arabidopsis.ahocorasick;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Queue
/*    */ {
/*    */   ArrayList l1;
/*    */   ArrayList l2;
/*    */   
/*    */   public Queue()
/*    */   {
/* 15 */     this.l1 = new ArrayList();
/* 16 */     this.l2 = new ArrayList();
/*    */   }
/*    */   
/*    */   public void add(State s) {
/* 20 */     this.l2.add(s);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 24 */     return (this.l1.isEmpty()) && (this.l2.isEmpty());
/*    */   }
/*    */   
/*    */   public State pop() {
/* 28 */     if (isEmpty())
/* 29 */       throw new IllegalStateException();
/* 30 */     if (this.l1.isEmpty()) {
/* 31 */       for (int i = this.l2.size() - 1; i >= 0; i--)
/* 32 */         this.l1.add(this.l2.remove(i));
/* 33 */       assert (this.l2.isEmpty());
/* 34 */       assert (!this.l1.isEmpty());
/*    */     }
/* 36 */     return (State)this.l1.remove(this.l1.size() - 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\Queue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */