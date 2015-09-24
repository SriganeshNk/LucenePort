package org.arabidopsis.ahocorasick;

abstract interface EdgeList
{
  public abstract State get(byte paramByte);
  
  public abstract void put(byte paramByte, State paramState);
  
  public abstract byte[] keys();
}


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\EdgeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */