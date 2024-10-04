import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {

	private ArrayBlockingQueue<Integer> kuyruk;
	
	
	
	public Consumer(ArrayBlockingQueue<Integer> kuyruk) {
		this.kuyruk = kuyruk;
	}



	@Override
	public void run() {
	// Threadim çalıştığı zaman buradaki run komutu içerisinde komutlar çalışacak
		try {	
	while (true) {
	
			Integer veri = kuyruk.take(); // take metodu ile kuyruğun başındaki elemanın değerini döndürüyorum
			if (veri==-1) { // İşlem bittiyse kuyruğun başına -1 eklemiştik eğer kuyruğun başından -1 dönüyorsa
							// döngüyü kır demiş oldum
				break;
			}
			System.out.println("Tüketici tüketti : " + veri);
			System.out.println("Queue Size : " + kuyruk.size());
			Thread.sleep(1000);// 1 saniye bekley
			
			
				}
		System.out.println("Tüketici işini bitirdi");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	
	
}
