import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {

	// Bu sefer ArrayBlockingQueue yapısını kullanmayacağız , o zaten kendi içerisinde threadleri kullanıyordu
	// Belirli koşullar gerçekleştiğinde , Array dolduğu veya boşaldığı zaman threadleri bekletiyordu
	
	// Bizde bu sefer bu belirli koşullarda bekleme , duraklama ve çalışma işlemlerini
	// wait ve notify metotları ile kendimiz yapacağız.
	Random random = new Random();
	private Object lock = new Object();
	Queue<Integer> queue = new LinkedList<Integer>();// Kendi queue yapımızı oluşturduk
	private int limit = 10;// LinkedList'imizin boyutunu kontrol etmek için
	
	
	public void producer() {
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock) {
				if (queue.size()==limit) {
					try {
					lock.wait();// Queue'mdaki eleman sayısı 10 eşitse thread'im dursun daha işlem yapmasın
					// Bu lock'a sahip thread lock'u serbest bırak ve beklemeye geç ta ki başka bir thread
					// notify metodunu kullanıp seni uyandırana kadar
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				}
				Integer value = random.nextInt(101);
				queue.offer(value);
				System.out.println("Producer çalıştı ve " + value + " değerini üretti");
				// Bu iki threadimizinde birbirlerini sürekli olarak uyandırmaları gerekiyor çünkü 
				// Uyandırmazlarsa sonsuz kadar bekliyecekler uyandırılmayı 
				lock.notify();
			}
			
		}
	}
	
	public void consumer() {
		
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (lock) {
				
				if (queue.size()==0) {
					try {
						lock.wait();// Bu şuanda sahip olan threadimi beklemeye al yani şuan ki threadi
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Integer deger = queue.poll();
				System.out.println("Consumer çalıştı ve  " + deger +" değerini tüketti"  );
				System.out.println("Queue Size : " + queue.size());
				lock.notify();
				
				
				
			}
		}
		
	}
	
	
}
