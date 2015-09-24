package org.arabidopsis.ahocorasick;

import java.util.Iterator;
import java.util.Set;

































public class AhoCorasick
{
	private State root;
	private boolean prepared;

	public AhoCorasick()
	{
		this.root = new State(0);
		this.prepared = false;
	}







	public void add(byte[] keyword, Object output)
	{
		if (this.prepared)
			throw new IllegalStateException(
					"can't add keywords after prepare() is called");
		State lastState = this.root.extendAll(keyword);
		lastState.addOutput(output);
	}






	public void prepare()
	{
		prepareFailTransitions();
		this.prepared = true;
	}






	public Iterator search(byte[] bytes)
	{
		return new Searcher(this, startSearch(bytes));
	}











	private void prepareFailTransitions()
	{
		Queue q = new Queue();
		for(int i = 0; i < 256; i++)
			if (this.root.get((byte) i) != null) {
				this.root.get((byte) i).setFail(this.root);
				q.add(this.root.get((byte) i));
			}
		this.prepareRoot();
		while (! q.isEmpty()) {
			State state = q.pop();
			byte[] keys = state.keys();
			for (int i = 0; i < keys.length; i++) {
				State r = state;
				byte a = keys[i];
				State s = r.get(a);
				q.add(s);
				r = r.getFail();
				while (r.get(a) == null)
					r = r.getFail();
				s.setFail(r.get(a));
				s.getOutputs().addAll(r.get(a).getOutputs());
			}
		}
	}






	private void prepareRoot()
	{
		for (int i = 0; i < 256; i++) {
			if (this.root.get((byte)i) == null) {
				this.root.put((byte)i, this.root);
			}
		}
	}




	State getRoot()
	{
		return this.root;
	}





	SearchResult startSearch(byte[] bytes)
	{
		if (!this.prepared)
			throw new IllegalStateException(
					"can't start search until prepare()");
		return continueSearch(
				new SearchResult(this.root, bytes, 0));
	}






	SearchResult continueSearch(SearchResult lastResult)
	{
		byte[] bytes = lastResult.bytes;
		State state = lastResult.lastMatchedState;
		for (int i = lastResult.lastIndex; i < bytes.length; i++) {
			byte b = bytes[i];
			while (state.get(b) == null)
				state = state.getFail();
			state = state.get(b);
			if (state.getOutputs().size() > 0)
				return new SearchResult(state, bytes, i + 1);
		}
		return null;
	}
}


/* Location:              C:\Users\Sriganesh\workspace\sbrad\WebContent\WEB-INF\lib\lucene_demo_ashish.jar!\org\arabidopsis\ahocorasick\AhoCorasick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */