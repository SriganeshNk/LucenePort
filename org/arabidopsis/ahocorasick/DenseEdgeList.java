/*    */ package org.arabidopsis.ahocorasick;
/*    */ 
/*    */ 
/*    */ 
/*    */ class DenseEdgeList
/*    */   implements EdgeList
/*    */ {
/*    */   private State[] array;
/*    */   
/*    */ 
/*    */   public DenseEdgeList()
/*    */   {
/* 13 */     this.array = new State['Ā'];
/* 14 */     for (int i = 0; i < this.array.length; i++) {
/* 15 */       this.array[i] = null;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static DenseEdgeList fromSparse(SparseEdgeList list)
/*    */   {
/* 23 */     byte[] keys = list.keys();
/* 24 */     DenseEdgeList newInstance = new DenseEdgeList();
/* 25 */     for (int i = 0; i < keys.length; i++) {
/* 26 */       newInstance.put(keys[i], list.get(keys[i]));
/*    */     }
/* 28 */     return newInstance;
/*    */   }
/*    */   
/*    */   public State get(byte b)
/*    */   {
/* 33 */     return this.array[(b & 0xFF)];
/*    */   }
/*    */   
/*    */   public void put(byte b, State s)
/*    */   {
/* 38 */     this.array[(b & 0xFF)] = s;
/*    */   }
/*    */   
/*    */   public byte[] keys()
/*    */   {
/* 43 */     int length = 0;
/* 44 */     for (int i = 0; i < this.array.length; i++) {
/* 45 */       if (this.array[i] != null)
/* 46 */         length++;
/*    */     }
/* 48 */     byte[] result = new byte[length];
/* 49 */     int j = 0;
/* 50 */     for (int i = 0; i < this.array.length; i++) {
/* 51 */       if (this.array[i] != null) {
/* 52 */         result[j] = ((byte)i);
/* 53 */         j++;
/*    */       }
/*    */     }
/* 56 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\DenseEdgeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */