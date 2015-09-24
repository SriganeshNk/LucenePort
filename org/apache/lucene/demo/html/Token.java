/*     */ package org.apache.lucene.demo.html;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Token
/*     */ {
/*     */   public int kind;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int beginLine;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int beginColumn;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int endLine;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int endColumn;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String image;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Token next;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Token specialToken;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getValue()
/*     */   {
/*  65 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Token() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Token(int kind)
/*     */   {
/*  78 */     this(kind, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Token(int kind, String image)
/*     */   {
/*  86 */     this.kind = kind;
/*  87 */     this.image = image;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  95 */     return this.image;
/*     */   }
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
/*     */ 
/*     */ 
/*     */   public static Token newToken(int ofKind, String image)
/*     */   {
/* 114 */     return new Token(ofKind, image);
/*     */   }
/*     */   
/*     */ 
/*     */   public static Token newToken(int ofKind)
/*     */   {
/* 120 */     return newToken(ofKind, null);
/*     */   }
/*     */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\apache\lucene\demo\html\Token.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */