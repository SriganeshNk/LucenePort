/*     */ package org.apache.lucene.demo.html;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParseException
/*     */   extends Exception
/*     */ {
/*     */   protected boolean specialConstructor;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Token currentToken;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[][] expectedTokenSequences;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[] tokenImage;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal)
/*     */   {
/*  33 */     super("");
/*  34 */     this.specialConstructor = true;
/*  35 */     this.currentToken = currentTokenVal;
/*  36 */     this.expectedTokenSequences = expectedTokenSequencesVal;
/*  37 */     this.tokenImage = tokenImageVal;
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
/*     */   public ParseException()
/*     */   {
/*  52 */     this.specialConstructor = false;
/*     */   }
/*     */   
/*     */   public ParseException(String message)
/*     */   {
/*  57 */     super(message);
/*  58 */     this.specialConstructor = false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMessage()
/*     */   {
/* 100 */     if (!this.specialConstructor) {
/* 101 */       return super.getMessage();
/*     */     }
/* 103 */     StringBuffer expected = new StringBuffer();
/* 104 */     int maxSize = 0;
/* 105 */     for (int i = 0; i < this.expectedTokenSequences.length; i++) {
/* 106 */       if (maxSize < this.expectedTokenSequences[i].length) {
/* 107 */         maxSize = this.expectedTokenSequences[i].length;
/*     */       }
/* 109 */       for (int j = 0; j < this.expectedTokenSequences[i].length; j++) {
/* 110 */         expected.append(this.tokenImage[this.expectedTokenSequences[i][j]]).append(' ');
/*     */       }
/* 112 */       if (this.expectedTokenSequences[i][(this.expectedTokenSequences[i].length - 1)] != 0) {
/* 113 */         expected.append("...");
/*     */       }
/* 115 */       expected.append(this.eol).append("    ");
/*     */     }
/* 117 */     String retval = "Encountered \"";
/* 118 */     Token tok = this.currentToken.next;
/* 119 */     for (int i = 0; i < maxSize; i++) {
/* 120 */       if (i != 0) retval = retval + " ";
/* 121 */       if (tok.kind == 0) {
/* 122 */         retval = retval + this.tokenImage[0];
/* 123 */         break;
/*     */       }
/* 125 */       retval = retval + " " + this.tokenImage[tok.kind];
/* 126 */       retval = retval + " \"";
/* 127 */       retval = retval + add_escapes(tok.image);
/* 128 */       retval = retval + " \"";
/* 129 */       tok = tok.next;
/*     */     }
/* 131 */     retval = retval + "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn;
/* 132 */     retval = retval + "." + this.eol;
/* 133 */     if (this.expectedTokenSequences.length == 1) {
/* 134 */       retval = retval + "Was expecting:" + this.eol + "    ";
/*     */     } else {
/* 136 */       retval = retval + "Was expecting one of:" + this.eol + "    ";
/*     */     }
/* 138 */     retval = retval + expected.toString();
/* 139 */     return retval;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 145 */   protected String eol = System.getProperty("line.separator", "\n");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String add_escapes(String str)
/*     */   {
/* 153 */     StringBuffer retval = new StringBuffer();
/*     */     
/* 155 */     for (int i = 0; i < str.length(); i++) {
/* 156 */       switch (str.charAt(i))
/*     */       {
/*     */       case '\000': 
/*     */         break;
/*     */       case '\b': 
/* 161 */         retval.append("\\b");
/* 162 */         break;
/*     */       case '\t': 
/* 164 */         retval.append("\\t");
/* 165 */         break;
/*     */       case '\n': 
/* 167 */         retval.append("\\n");
/* 168 */         break;
/*     */       case '\f': 
/* 170 */         retval.append("\\f");
/* 171 */         break;
/*     */       case '\r': 
/* 173 */         retval.append("\\r");
/* 174 */         break;
/*     */       case '"': 
/* 176 */         retval.append("\\\"");
/* 177 */         break;
/*     */       case '\'': 
/* 179 */         retval.append("\\'");
/* 180 */         break;
/*     */       case '\\': 
/* 182 */         retval.append("\\\\");
/* 183 */         break;
/*     */       default:  char ch;
/* 185 */         if (((ch = str.charAt(i)) < ' ') || (ch > '~')) {
/* 186 */           String s = "0000" + Integer.toString(ch, 16);
/* 187 */           retval.append("\\u" + s.substring(s.length() - 4, s.length()));
/*     */         } else {
/* 189 */           retval.append(ch);
/*     */         }
/*     */         break;
/*     */       }
/*     */     }
/* 194 */     return retval.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\apache\lucene\demo\html\ParseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */