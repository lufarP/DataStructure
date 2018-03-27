class SubArray
{
	private int prefixSum,suffixSum,sum,maximumSum;
	public SubArray(int value)
	{
		setPrefixSum(value);
		setSuffixSum(value);
		setSum(value);
		setMaximumSum(value);
	}
	public SubArray(SubArray left,SubArray right)
	{
		setPrefixSum(left,right);
		setSuffixSum(left,right);
		setSum(left,right);
		setMaximumSum(left,right);
	}
	private void setMaximumSum(SubArray left, SubArray right) 
	{
		int newSum=left.getSuffixSum()+right.getPrefixSum();
		setMaximumSum(Math.max(newSum, Math.max(getPrefixSum(), getSuffixSum())));
		
	}
	private void setSum(SubArray left, SubArray right) {
		// TODO Auto-generated method stub
		setSum(left.getSum()+right.getSum());
		
	}
	private void setSuffixSum(SubArray left, SubArray right) {
		// TODO Auto-generated method stub
		setSuffixSum(Math.max(left.getSuffixSum()+right.getSum(),right.getSuffixSum()));
		
	}
	private void setPrefixSum(SubArray left, SubArray right) {
		// TODO Auto-generated method stub
		setPrefixSum(Math.max(left.getPrefixSum(), left.getSum()+right.getPrefixSum()));
		
	}
	public int getPrefixSum() {
		return prefixSum;
	}
	private void setPrefixSum(int prefixSum) {
		this.prefixSum = prefixSum;
	}
	public int getSuffixSum() {
		return suffixSum;
	}
	private void setSuffixSum(int suffixSum) {
		this.suffixSum = suffixSum;
	}
	public int getSum() {
		return sum;
	}
	private void setSum(int sum) {
		this.sum = sum;
	}
	public int getMaximumSum() {
		return maximumSum;
	}
	private void setMaximumSum(int maximumSum) {
		this.maximumSum = maximumSum;
	}
	public String toString()
	{
		return "[PrefixSum: "+getPrefixSum()+", SuffixSum: "+getSuffixSum()+
				", MaximumSum: "+getMaximumSum()+", Sum: "+getSum()+"]";
		
	}
	
}
