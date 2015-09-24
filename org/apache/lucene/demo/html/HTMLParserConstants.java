/*     */ package org.apache.lucene.demo.html;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract interface HTMLParserConstants
/*     */ {
/*     */   public static final int EOF = 0;
/*     */   
/*     */ 
/*     */   public static final int ScriptStart = 1;
/*     */   
/*     */ 
/*     */   public static final int TagName = 2;
/*     */   
/*     */ 
/*     */   public static final int DeclName = 3;
/*     */   
/*     */ 
/*     */   public static final int Comment1 = 4;
/*     */   
/*     */ 
/*     */   public static final int Comment2 = 5;
/*     */   
/*     */ 
/*     */   public static final int Word = 6;
/*     */   
/*     */ 
/*     */   public static final int LET = 7;
/*     */   
/*     */   public static final int NUM = 8;
/*     */   
/*     */   public static final int HEX = 9;
/*     */   
/*     */   public static final int Entity = 10;
/*     */   
/*     */   public static final int Space = 11;
/*     */   
/*     */   public static final int SP = 12;
/*     */   
/*     */   public static final int Punct = 13;
/*     */   
/*     */   public static final int ScriptText = 14;
/*     */   
/*     */   public static final int ScriptEnd = 15;
/*     */   
/*     */   public static final int ArgName = 16;
/*     */   
/*     */   public static final int ArgEquals = 17;
/*     */   
/*     */   public static final int TagEnd = 18;
/*     */   
/*     */   public static final int ArgValue = 19;
/*     */   
/*     */   public static final int ArgQuote1 = 20;
/*     */   
/*     */   public static final int ArgQuote2 = 21;
/*     */   
/*     */   public static final int Quote1Text = 23;
/*     */   
/*     */   public static final int CloseQuote1 = 24;
/*     */   
/*     */   public static final int Quote2Text = 25;
/*     */   
/*     */   public static final int CloseQuote2 = 26;
/*     */   
/*     */   public static final int CommentText1 = 27;
/*     */   
/*     */   public static final int CommentEnd1 = 28;
/*     */   
/*     */   public static final int CommentText2 = 29;
/*     */   
/*     */   public static final int CommentEnd2 = 30;
/*     */   
/*     */   public static final int DEFAULT = 0;
/*     */   
/*     */   public static final int WithinScript = 1;
/*     */   
/*     */   public static final int WithinTag = 2;
/*     */   
/*     */   public static final int AfterEquals = 3;
/*     */   
/*     */   public static final int WithinQuote1 = 4;
/*     */   
/*     */   public static final int WithinQuote2 = 5;
/*     */   
/*     */   public static final int WithinComment1 = 6;
/*     */   
/*     */   public static final int WithinComment2 = 7;
/*     */   
/*  90 */   public static final String[] tokenImage = {
/*  91 */     "<EOF>", 
/*  92 */     "\"<script\"", 
/*  93 */     "<TagName>", 
/*  94 */     "<DeclName>", 
/*  95 */     "\"<!--\"", 
/*  96 */     "\"<!\"", 
/*  97 */     "<Word>", 
/*  98 */     "<LET>", 
/*  99 */     "<NUM>", 
/* 100 */     "<HEX>", 
/* 101 */     "<Entity>", 
/* 102 */     "<Space>", 
/* 103 */     "<SP>", 
/* 104 */     "<Punct>", 
/* 105 */     "<ScriptText>", 
/* 106 */     "<ScriptEnd>", 
/* 107 */     "<ArgName>", 
/* 108 */     "\"=\"", 
/* 109 */     "<TagEnd>", 
/* 110 */     "<ArgValue>", 
/* 111 */     "\"\\'\"", 
/* 112 */     "\"\\\"\"", 
/* 113 */     "<token of kind 22>", 
/* 114 */     "<Quote1Text>", 
/* 115 */     "<CloseQuote1>", 
/* 116 */     "<Quote2Text>", 
/* 117 */     "<CloseQuote2>", 
/* 118 */     "<CommentText1>", 
/* 119 */     "\"-->\"", 
/* 120 */     "<CommentText2>", 
/* 121 */     "\">\"" };
/*     */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\apache\lucene\demo\html\HTMLParserConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */