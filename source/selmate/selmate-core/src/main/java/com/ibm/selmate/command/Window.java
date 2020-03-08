
package com.ibm.selmate.command;

public class Window {

	private String handle;

	private Window parent;

	private Window child;

	private boolean isClosed;

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Window getParent() {
		return parent;
	}

	public void setParent(Window parent) {
		this.parent = parent;
	}

	public Window getChild() {
		return child;
	}

	public void setChild(Window child) {
		this.child = child;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	/**
	 * This operation returns the no of descendants including itself.
	 * 
	 * @return
	 */
	public int getDescendantsCount() {
		int count = 1;
		Window childWindow = child;
		if (childWindow != null) {
			childWindow = childWindow.getChild();
			count++;
		}
		return count;
	}
}
