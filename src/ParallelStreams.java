
import java.util.function.Function;

public class ParallelStreams {

	// Calculating the sum of first n natural numbers sequentially
	public static Function<Long,Long> sequentialSum() {
		return n -> {
			long sum = 0;
			for (long i = 1; i <= n; i++) {
				sum = sum + i;
			}
			return sum;
		};
	
		
	}
	public static void main(String[] args) {
		long seqExecutionTime = measureSumPerf(sequentialSum(), 10000000);
		System.out.println("Sequential Execution Time is: " + seqExecutionTime +" msecs");
		//measureSumPerf(ParallelStreams::sequentialSum, 10000);
	}
	private static long  measureSumPerf(Function<Long,Long> function, long n) {
		long fastest = Long.MAX_VALUE;
		for(int i=0; i<10; i++) {
			long start = System.nanoTime();
			long sum = function.apply(n);
			long duration = (System.nanoTime()-start)/1000000;
			System.out.println("Sum is: " + sum);
			if(duration<fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}
}
