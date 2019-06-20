
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {

	// Calculating the sum of first n natural numbers sequentially
	public static Function<Long, Long> sequentialSum() {

		return n -> {
			return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
		};
	}

	public static void main(String[] args) {
		long seqExecutionTime = measureSumPerf(sequentialSum(), 10000000);
		System.out.println("Sequential Execution Time is: " + seqExecutionTime + " msecs");
		long iterativeExecutionTime = measureSumPerf(iterativeSum(), 10000000);
		System.out.println("Iterative Execution Time is: " + iterativeExecutionTime + " msecs");
		long parallelExecutionTime = measureSumPerf(parallelSum(), 10000000);
		System.out.println("Prallel Execution Time is: " + parallelExecutionTime + " msecs");
		
		long seqExecutionTimeRangeClosed = measureSumPerf(sequentialSumRangeClosed(), 10000000);
		System.out.println("Sequential Execution Time using range closed is: " + seqExecutionTimeRangeClosed + " msecs");
		
		long parallelExecutionTimeRangeClosed = measureSumPerf(parallelSumRangeClosed(), 10000000);
		System.out.println("Prallel Execution Time using range closed is: " + parallelExecutionTimeRangeClosed + " msecs");

	}

	// Calculating the sum of first n natural numbers iteratively
	private static Function<Long, Long> iterativeSum() {
		return n -> {
			long sum = 0;
			for (long i = 1; i <= n; i++) {
				sum = sum + i;
			}
			return sum;
		};

	}

	// Calculating the sum of first n natural numbers parallelly
	public static Function<Long, Long> parallelSum() {

		return n -> {
			return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
		};
	}
	
	// Calculating the sum of first n natural numbers sequentially using rangeClosed 
		public static Function<Long, Long> sequentialSumRangeClosed() {
			
			return n -> {
				return LongStream.rangeClosed(1L, n).reduce(0L, Long::sum);
			};
		}
		
		// Calculating the sum of first n natural numbers parallelly using rangeClosed 
				public static Function<Long, Long> parallelSumRangeClosed() {
					
					return n -> {
						return LongStream.rangeClosed(1L, n).parallel().reduce(0L, Long::sum);
					};
				}
	private static long measureSumPerf(Function<Long, Long> function, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = function.apply(n);
			long duration = (System.nanoTime() - start) / 1000000;
			System.out.println("Sum is: " + sum);
			if (duration < fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}
}
