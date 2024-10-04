import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {
// Üretici
	Random random = new Random();
	private ArrayBlockingQueue<Integer> kuyruk;

	public Producer(ArrayBlockingQueue<Integer> kuyruk) {
	this.kuyruk = kuyruk;
}
	@Override
		public void run() {
		// Bu producer thread'im buradaki işlemleri gerçekleştirecek
		try {
		for(int i = 1; i<=10 ; i++) {
		System.out.println("Üretici üretti : " + i);
			kuyruk.put(i); // Burada kuyruğa öğe eklenecek ve eğer kuyruk doluysa  beklenilecek 
			Thread.sleep(500);
			}
		kuyruk.put(-1); // Tüketiciye işin bittiğini belirtmek için böyle bir değer gönderiyoruz
			}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
			
	
		
		
		}
	
	
	
}
