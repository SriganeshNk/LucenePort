/*    */ package org.apache.lucene.demo.html;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
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
/*    */ public final class Tags
/*    */ {
/* 30 */   public static final Set<String> WS_ELEMS = Collections.synchronizedSet(new HashSet());
/*    */   
/*    */   static {
/* 33 */     WS_ELEMS.add("<hr");
/* 34 */     WS_ELEMS.add("<hr/");
/* 35 */     WS_ELEMS.add("<br");
/* 36 */     WS_ELEMS.add("<br/");
/* 37 */     WS_ELEMS.add("<p");
/* 38 */     WS_ELEMS.add("</p");
/* 39 */     WS_ELEMS.add("<div");
/* 40 */     WS_ELEMS.add("</div");
/* 41 */     WS_ELEMS.add("<td");
/* 42 */     WS_ELEMS.add("</td");
/* 43 */     WS_ELEMS.add("<li");
/* 44 */     WS_ELEMS.add("</li");
/* 45 */     WS_ELEMS.add("<q");
/* 46 */     WS_ELEMS.add("</q");
/* 47 */     WS_ELEMS.add("<blockquote");
/* 48 */     WS_ELEMS.add("</blockquote");
/* 49 */     WS_ELEMS.add("<dt");
/* 50 */     WS_ELEMS.add("</dt");
/* 51 */     WS_ELEMS.add("<h1");
/* 52 */     WS_ELEMS.add("</h1");
/* 53 */     WS_ELEMS.add("<h2");
/* 54 */     WS_ELEMS.add("</h2");
/* 55 */     WS_ELEMS.add("<h3");
/* 56 */     WS_ELEMS.add("</h3");
/* 57 */     WS_ELEMS.add("<h4");
/* 58 */     WS_ELEMS.add("</h4");
/* 59 */     WS_ELEMS.add("<h5");
/* 60 */     WS_ELEMS.add("</h5");
/* 61 */     WS_ELEMS.add("<h6");
/* 62 */     WS_ELEMS.add("</h6");
/*    */   }
/*    */ }


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\apache\lucene\demo\html\Tags.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */