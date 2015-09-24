/*     */ package org.arabidopsis.ahocorasick;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class State
/*     */ {
/*     */   private static final int THRESHOLD_TO_USE_SPARSE = 3;
/*     */   private int depth;
/*     */   private EdgeList edgeList;
/*     */   private State fail;
/*     */   private Set outputs;
/*     */   
/*     */   public State(int depth)
/*     */   {
/*  28 */     this.depth = depth;
/*  29 */     if (depth > 3) {
/*  30 */       this.edgeList = new SparseEdgeList();
/*     */     } else
/*  32 */       this.edgeList = new DenseEdgeList();
/*  33 */     this.fail = null;
/*  34 */     this.outputs = new HashSet();
/*     */   }
/*     */   
/*     */   public State extend(byte b)
/*     */   {
/*  39 */     if (this.edgeList.get(b) != null)
/*  40 */       return this.edgeList.get(b);
/*  41 */     State nextState = new State(this.depth + 1);
/*  42 */     this.edgeList.put(b, nextState);
/*  43 */     return nextState;
/*     */   }
/*     */   
/*     */   public State extendAll(byte[] bytes)
/*     */   {
/*  48 */     State state = this;
/*  49 */     for (int i = 0; i < bytes.length; i++) {
/*  50 */       if (state.edgeList.get(bytes[i]) != null) {
/*  51 */         state = state.edgeList.get(bytes[i]);
/*     */       } else
/*  53 */         state = state.extend(bytes[i]);
/*     */     }
/*  55 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  65 */     byte[] keys = this.edgeList.keys();
/*  66 */     int result = 1;
/*  67 */     for (int i = 0; i < keys.length; i++)
/*  68 */       result += this.edgeList.get(keys[i]).size();
/*  69 */     return result;
/*     */   }
/*     */   
/*     */   public State get(byte b)
/*     */   {
/*  74 */     return this.edgeList.get(b);
/*     */   }
/*     */   
/*     */   public void put(byte b, State s)
/*     */   {
/*  79 */     this.edgeList.put(b, s);
/*     */   }
/*     */   
/*     */   public byte[] keys() {
/*  83 */     return this.edgeList.keys();
/*     */   }
/*     */   
/*     */   public State getFail()
/*     */   {
/*  88 */     return this.fail;
/*     */   }
/*     */   
/*     */   public void setFail(State f)
/*     */   {
/*  93 */     this.fail = f;
/*     */   }
/*     */   
/*     */   public void addOutput(Object o)
/*     */   {
/*  98 */     this.outputs.add(o);
/*     */   }
/*     */   
/*     */   public Set getOutputs()
/*     */   {
/* 103 */     return this.outputs;
/*     */   }
/*     */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\State.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */