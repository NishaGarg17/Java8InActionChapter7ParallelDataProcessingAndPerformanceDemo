import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long>  {
	private final long[] numbers;
	private final int start;
	private final int end;
	
	private static final long THRESHHOLD = 10000;
	
	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}
	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}
	@Override
	protected Long compute() {
		int length = end-start;
		if(length <= THRESHHOLD) {
			return computeSumSequentially();
		}
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
		leftTask.fork();
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start + length/2, end);
		long rightTaskResult = rightTask.compute();
		long leftTaskResult = leftTask.join();
		return leftTaskResult + rightTaskResult;
	}
	// method to calculate the sum sequentially
	private long computeSumSequentially() {
		long sum = 0;
		for(int i = start; i < end; i++) {
			sum = sum + numbers[i];
		}
		return sum;
	}


}
