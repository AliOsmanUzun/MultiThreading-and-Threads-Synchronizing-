import java.util.concurrent.ArrayBlockingQueue;

public class Main {

	public static void main(String[] args) {
	
	/*
	 Programın mantığı : 
	 Bir queue yapımız olacak , 2 tane threadimiz olacak bunlar Producer ( üretici) Consumer ( tüketici ) 
	 Producer 1 tane değer üretip bu queue yapısının en başına atıyacak ve oradan itibaren değer eklemeye devam edecek
	 Consumer'da bu Producer'ın eklediği değerleri queue yapısından FIFO ( First in First Out ) olduğu için
	 en baştan sona doğru elde edip almaya başlıyacak
	 Queue yapısını max 10 değer taşıyacak hale getireceğiz sonsuz değer taşımayacak
	 Producer'ımızın queue'nın boyutu dolduğu zaman biraz beklemesini isteyeceğiz
	 Consumer o değerleri elde edene kadar bekleyecek yani
	 Consumer'ın da eğer queue içerisinde hiç değer yoksa beklemesini isteyeceğiz aynı şekilde 
	 
	 Bu işlemler içinde ArrayBlockingQueue yapısını kullanacağız bu veri tipi Queue'muza bir tane boyut veriyor
	 Put ve take metodlarını kullanacağız bu işlemler için
	 
	 
	 ArrayBlockingQueue, Java'da java.util.concurrent paketinde yer alan ve FIFO (First-In-First-Out)
	 prensibiyle çalışan bir BlockingQueue implementasyonudur.
	 Sabit boyutlu (kapasiteli) bir kuyruk olup, dolu olduğunda ekleme işlemleri bekletilir ve boş olduğunda 
	 ise alma işlemleri duraklatılır.
	 
	 Thread safe sağlıyor ArrayBlockingQueue threadlerin güvenilir bir şekilde kullanılması
	 
	 put metodu değerlerimizi eklenme sırasına göre queue'ya ekliyor 
	 queue'muzun kapasitesi dolduğu zaman onu çalıştıran threadi bekletiyor işleme devam ettirmiyor
	 ne zaman eleman eksilir ozaman threadimiz çalışmaya başlar
	 
	 take metoduda queue'muzun en başındaki elemanın değerini dönüyor 
	 ne zaman queue'da eleman kalmaz o zaman kendisini çalıştıran threadi bekletiyor ta ki eleman eklenene kadar
	 queue'ya ozaman threadimiz kaldığı yerden çalışmaya devam ediyor
	 
	 ArrayBlockQueue classı içerisinde ReentrantLock şeklinde bir veri tipi var
	 Bu yapı kendi içerisinde thread operasyonları yapıyor
	 Threadlerimizi senkorize hale getirmeye çalışıyor
	 */
		
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(20);
	
	Thread ureticiThread = new Thread(new Producer(queue));
	Thread tuketiciThread = new Thread(new Consumer(queue));
	
	ureticiThread.start();
	tuketiciThread.start();
	
	
	try {
		ureticiThread.join();
		tuketiciThread.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
}
