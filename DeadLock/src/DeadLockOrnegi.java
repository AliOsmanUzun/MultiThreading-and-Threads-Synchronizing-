import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockOrnegi {
	
	private Hesap hesap1 = new Hesap();
	private Hesap hesap2 = new Hesap();
	
	private Random random = new Random();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private Condition condition1 = lock1.newCondition();
	private Condition condition2 = lock2.newCondition();
	
	public void locklariKontrolEt(Lock a, Lock b) {
		// 2 tane lock gönderdik
		boolean aDeger = false;
		boolean bDeger = false;
		// A elde edildiyse ama B elde edilmediyse A lock'unu diğer threade bırakmamız gerekiyor
	
		while(true) {
		// Bu sonsuz döngüden 2 lock'uda elde edebildiyse çıkacağım
			aDeger = a.tryLock();// Kilidi almaya çalış alırsan true , alamazsan false değer dön ve bekleme 
			bDeger = b.tryLock();
		// İki lock'umuzda alınabilecek durumda ise yani true değer dönüyorsa döngüden çıkacağız
		if (aDeger==true && bDeger==true) {
			return; // direkt metodumu sonlandırıyorum
			
		}
		if (aDeger==true) {
			// aDegeri true bDegerimiz false ise buraya giricez
			// Diğer lock elimizde deği onu beklemeye kalkarsa deadlock olabilir o yüzden elimizdeki lock'u
			// bırakıp diğer thread'in locku almasına izin veriyor ve işlemlerini bitirmesini bekliyoruz
			a.unlock(); // a lock'u kilidi bıraksın
		}
		if (bDeger == true) {
			// Aynı işlemi b lock'u içinde yapıyoruz
			b.unlock(); // b lock'unun kilidini serbest bırak
		}
		}
	
	}
	
	
	public void thread1Fonksiyonu() {
		
		locklariKontrolEt(lock1, lock2);
		
		try {
		for(int i = 1 ; i<=5000 ; i++) {
			// 5 bin defa hesap1'den hesap2'ye para transferi yapmış olduk
			Hesap.paraTransferi(hesap1, hesap2, random.nextInt(100));
		}
		}finally {
			lock1.unlock();
			lock2.unlock();
		}
		
	}
	
	public void thread2Fonksiyonu() {

		locklariKontrolEt(lock2, lock1);
		
		try {
		for(int i = 1 ; i<=5000 ; i++) {
			// 5 bin defa hesap1'den hesap2'ye para transferi yapmış olduk
			Hesap.paraTransferi(hesap2, hesap1, random.nextInt(100));
		}
		}
		finally {
			lock2.unlock();
			lock1.unlock();
		}
		
	}
	
	/*
		Burada thread1 fonksiyonumda thread1'im ilk olarak lock1'i çağırıp ardından lock2'yi çağırısa
		thread2 fonksiyonumdada thread2'im ilk olarak lock2'i çağırıp ardından lock1'çağırırsa
		iki threadde birbirlerinin lockl'larını bırakmalarını bekleyecek sonsuz döngüye girip
		buna DeadLock diyoruz işte
		
		Bu DeadLock olayını çözmek önüne geçmek için ReentrantLock sınıfından bir metot kullanıyoruz
		Bu metodun adı tryLock
		
		tryLock metodu: bir thread'in kilidi almaya çalıştığında , eğer kilit zaten başka bir thread tarafından 
		kullanılıyorsa beklemeden geri dönmesine olanak tanır.Bu sayede , deadlock ( ölü kilitlenme ) riskini
		azaltmaya yardımcı olabilir ve kilidi alıp almadığını kontrol ederek threadlerin daha esnek bir 
		kontrol mekanizması sunar
		
		Adı üstünde tryLock , Lock'u almayı deniyor alamazsa geri dönüyor beklemiyor
		Alabilirse direkt olarak kilidi true değer döner  , eğer kilid başka bir thread'in elindeyse
		beklemez ve geri dönüp false değer döndürür.
		
		
	
	*/
	public void threadOver() {
		
	System.out.println("Hesap1 Bakiye : " + hesap1.getBakiye() + " Hesap2 Bakiye : " + hesap2.getBakiye());
	
	System.out.println("Toplam Bakiye : " + (hesap1.getBakiye() + hesap2.getBakiye()));
		
	}
}
