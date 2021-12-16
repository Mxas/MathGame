package lt.mk.mathgame.model;

public class OperationSetting {


	private int maxFirst;
	private int maxSecond;
	private int maxTotal;
	private boolean enabled;

	public OperationSetting(int maxFirst, int maxSecond, int maxTotal, boolean enabled) {
		this.maxFirst = maxFirst;
		this.maxSecond = maxSecond;
		this.maxTotal = maxTotal;
		this.enabled = enabled;
	}

	public int getMaxFirst() {
		return maxFirst;
	}

	public void setMaxFirst(int maxFirst) {
		this.maxFirst = maxFirst;
	}

	public int getMaxSecond() {
		return maxSecond;
	}

	public void setMaxSecond(int maxSecond) {
		this.maxSecond = maxSecond;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
