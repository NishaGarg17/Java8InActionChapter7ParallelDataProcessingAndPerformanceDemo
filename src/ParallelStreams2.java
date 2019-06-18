import java.util.function.Function;

public class ParallelStreams2 {
	
	public static Function<Long, Long> sequentialSum() {
		return n -> {
			long sum = 0;
			for (long i = 1; i <= n; i++)
				sum = sum + i;
			return sum;
		};
	}
	
	public static void main(String[] args) {
		long seqExecutionTime = measureSumPerformance(sequentialSum(), 10_000_000);
		System.out.println("Execution time is : " + seqExecutionTime + " msecs.");
	}
	
	private static long measureSumPerformance(Function<Long, Long> function, long n) {
		//long sum = 0;
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = function.apply(n);
			long duration = (System.nanoTime() - start)/ 1_000_000;
			System.out.println("Sum is : " + sum);
			if (duration < fastest)
				fastest = duration;
		}
		
		return fastest;
	}

}
