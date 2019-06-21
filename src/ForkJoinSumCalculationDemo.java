import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculationDemo {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Max Count of first n natural numbers to calculate the sum: ");
		long numbers = sc.nextLong();
		long begin = System.nanoTime();
		long sum = forkJoinSum(numbers);
		long time = (System.nanoTime() - begin)/1000000;
		System.out.println("sum computed in time: " + time + " msecs");
		System.out.println("Final Sum is : " + sum);
	}

	private static long forkJoinSum(long numbers) {
		long[] numArray = LongStream.rangeClosed(1, numbers).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numArray);
		return new ForkJoinPool().invoke(task);
	}
}
