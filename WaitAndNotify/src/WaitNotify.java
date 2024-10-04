import java.util.Scanner;

public class WaitNotify {

	private Object lock = new Object();
	
	public void thread1Fonksiyonu() {
	
		synchronized (lock){ // Bulunduğu nesne üzerinde bir kilit oluşturdu bu this anahtar kelimesi
							// Synchronized kod bloğu ile kullanılarak bir thread burayı çalıştırırken 
							// başka bir thread buraya giremeyecek ancak ilk giren threadin işi bittiği zaman
							// ve anahtarı ona devrederse girebilecek
							// o anki obje üzerinde tek bir anahtar oluşturmuş oluyoruz kısaca
		System.out.println("Thread 1 Çalışıyor");
		System.out.println("Thread 1 Thread 2'nin kendisini uyandırmasını bekliyor");
		
		try {
			lock.wait(); // Object class'ından gelen bir metodumuz notify'da aynı şekilde 
			// Wait metodu ile burada ben  anahtarı teslim edicem , başka birisi beni uyandırana kadar
			// buradan ileri gidemeyeceğim şeklinde bir şey söylemiş oluyoruz Java'ya
			// Eğer başka bir thread buradaki thread'i uyandırmazsa sonsuza kadar threadimiz uyumuş olucak burada
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread 1 uyandı , Devam ediyor...");
		
		}
		
	}
	
	public void thread2Fonksiyonu() {
	Scanner scanner = new Scanner(System.in);
	try {
		Thread.sleep(2000);
		// 2 saniye uyutmamın nedeni objemiz üzerinde tek bir kilit var ve ona ilk olarak
		// thread 1 sahip olsun diye burada thread2 yi uyutuyorum
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	synchronized (lock) {
		System.out.println("Thread 2 Çalışıyor");
		System.out.println("Devam etmek için bir tuşa basın");
		
		scanner.nextLine();
		
		lock.notify(); // Notify metodunu kullanarak uyuyan diğer threadimizi uyandırdık
		// Sonra threadimiz burada işlerine kaldığı yerden devam edip buradaki işlerininin hepsini tamamlayıp öyle locku devrediyor 
		System.out.println("Uyandırdım abicim , Ben gidiyorum ama 2 saniye bekleyeceksin çünkü ben uyuyorum");
		// Burada objemiz üzerinde tek bir kilit olduğu için ve şuanda kilit thread2'mizde onun tüm işlemleri bitene kadar
		// thread1'miz çalışamayacak onu uyandırmış olsakda buradaki işlemler bitecek ve thread2'miz locku thread1'e teslim edecek
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
	
	
	/*
	 this kullanımı: synchronized (this) ile senkronizasyon yapıldığında,
	 bu nesne üzerinde bir kilit (lock) oluşturulmuş olur. 
	 Bu kilit, aynı anda birden fazla iş parçacığının o nesnenin senkronize edilmiş bloklarına erişmesini engeller.
	 Yani, bir iş parçacığı synchronized (this) bloğunu çalıştırırken, 
	 başka bir iş parçacığı o nesne üzerinde senkronize edilmiş başka bir blok veya metoda giremez.
	 Bu yüzden kullanılması pek önerilmiyor çünkü başka bağımsız this kullanılan synchronized blokları ve metotlarıda 
	 bloklanmış olucak obje üzerinde tek bir kilit olduğu için
	 
	 
	 
	 */
}
