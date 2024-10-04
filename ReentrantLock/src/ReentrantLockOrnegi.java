import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockOrnegi {
	private int say = 0;
	// Ben bu say değişkenimi 20 bine eşitlemek istiyorum 10 bin tane thread1 arttıracak
	// 10 bin tane thread2 arttıracak
	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public void arttir() {
		for(int i = 1; i<= 10000 ; i++) {
			say++;// 10 bin defa arttırıyorum değerini
		}
		
	}
	public void thread1Fonksiyonu() {
		// Bu metot ile bu lock'u (kiliti) almak için çağrı yapıyoruz boştaysa direkt alıyor
		// değilse kendisine devredilmesi için bekliyor 
		// lock alındıktan sonra anahtar bu threadde olduğu için buraya sadece o giriş yapabiliyor
		lock.lock();
		try {
		System.out.println("Thread 1 çalışıyor");
		System.out.println("Thread 1 uyandırılmayı bekliyor");	
		condition.await(); // Thread 1 locku teslim etti ve beklemeye geçti
		
		System.out.println("Thread 1 uyandırıldı ve işlemine devam ediyor");
		arttir(); // kritik bölge olarak adlandırılıyor bu kısım
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally { // Hata fırlatılsada fırlatılmasada buradaki kodlar çalışsın
		lock.unlock();
		// Unlock ile de kilidi serbest bırakıyor ve başka threadlerin almasına izin veriyoruz
		}
		
	}
	
	public void thread2Fonksiyonu() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		
		
		lock.lock();// Lock için çağrı yaptık
		try {
		System.out.println("Thread 2 çalışıyor");	
			arttir();
		System.out.print("Devam etmek için bir tuşa basınız : ");
		scanner.nextLine();
		condition.signal();// Kilidi bırakıp beklemeye geçen threadi uyandır ( thread1'i yani )
						   // Notify ile aynı mantıkta çalışıyor signal metodu 
						   
		System.out.println("Thread 1'i uyandırdım , Ben kendi işlemlerime devam ediyorum");
		} 
		finally {
		lock.unlock(); // Locku serbest bıraktık
		}
		
	}
	
	public void threadOver() {
		// Say değişkeninin değerini ekrana yazdırmak için
		System.out.println("Say Değişkeni Toplam Değeri : " + say);
	}
	// Synchronized kod bloklarında wait ve notify kod bloklarını kullanabiliyorduk
	// Buradada kullanabiliriz ancak bu işlem için Condition class'ından bir tane condition belirlememiz gerekiyo
	
	// Aslında bir nevi kendi synhronized bloklarım gibi çalışma mantığı lock ve unlock'un
	// Çağrısı yapılan anahtarı ilk kim kaptıysa onun etkilediği kodları diğer threadler çalıştıramıyor
	
	// Biz lock ve unlock metotları arasında exception fırlatabilecek kodlar veya metotlar kullanıyorsak
	// Ve program o kısımdan çalışmayı kestiğinde biz unlock metodunu çalıştıramayabiliriz buda sonsuza kadar
	// programımızı kitlememize neden olabilir yani bizim mutlaka ama mutlaka exception bile fırlatılsa
	// Unlock metodunu kullanmamız gerekiyor eğer lock metodunu kullandıysak
}
