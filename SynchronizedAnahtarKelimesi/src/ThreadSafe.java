
public class ThreadSafe {

	 private  int  count = 0 ;
	 
	 
	 public synchronized void arttir() { // Bir thread buraya ulaştığında o threadin işi bitene kadar 
		 					// başka hiç bir thread buraya erişemesin demiş oluyorum
		 count++;
	 }
	
	public void threadleriCalistir() {
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i <5000 ; i++) {
					arttir();
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			
			@Override 
			public void run() {
				for (int i = 0; i <5000 ; i++) {
					arttir();
				}
				
			}
		});
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Join metodunu kullanarak main threade sen bekle demiş oluyoruz kısaca 
		// Bu threadler tamamlansın sonra sen çalışmaya başla diyoruz
		
		// Buradaki işlemlerin dışında kalan işlemler main threade ait şekilde davranıyor
		
		System.out.println("Count değişkenin değeri : " + count);
		// Buradaki işlem maint threade ait bir işlem
		
	}
	public static void main(String[] args) {
		// Kendi class'ımın içerisindeki main metodunda kendi objemi oluşturabilirim sınıfımdan
		ThreadSafe threadSafe = new ThreadSafe();
		threadSafe.threadleriCalistir();
		// Count değişkeninin çıktısı 0 oldu
		// Bunun sebebi thread1 ve thread2'imiz çalışırken aynı anda bu threadlerin çalışması bitmeden
		// main threadimizde çalışmış oluyor
		// Bizim istediğimiz thread1 ve thread2 imiz çalışsın onlardan sonra main threadimiz çalışsın
		// Bunun için bu threadlerimde ekstradan bir metot kullanıyorum 
		// Bu metodum join metodu bir threadin diğer threadler tamamlanana kadar beklemesini sağlamak için kullanılır
		// main threadini kendi threadlerimiz tamamlanmadan çalışmasını istemiyorsak kullanabiliriz
		
		
		//Şimdide hep farklı çıktı alıyorum count değişkeninden bunun nedeni threadlerdeki senkron sorunu
		// Çünkü ikiside count değişkenine aynı anda erişip işlem yapabiliyor buda senkron sorununa neden oluyor
		// Aynı anda değişken üzerinde işlem yapmaya çalışıyorlar buda probleme yol açıyor
		// O yüzden bende thread1'in count üzerindeki işlemi bitene kadar thread2 count değişkenine dokunamasın
		// demem gerekiyor Java'ya
		// Aslında hangi thread'im o değişken üzerinde çalışıyorsa onun işlemi bitene kadar 
		// başka bir thread o değişkene erişemesin demiş oluyorum
		// Bunun için synchronized anahtar kelimesini kullanıyorum
		
		
		// Anahtar class'a özgü bir anahtar ve tek bir tane anahtar var bu anahtarada sadece 1 tane 
		// thread sahip olabiliyor
		// Birden fazla synchronized metod var ise anahtara sahip olan thread bu metotlara ilk giren thread oluyor
		// diğer threadler bu anahtara sahip olan threading oradaki işlemlerini bitirip anahtarı onlara 
		// teslim etmesini bekliyor 
		// Yani a ve b diye metodlarımız var synchronized thread1 anahtara sahip olan threadimiz olsun
		// ilk önce a'ya girdi o a'ya girdiği zaman thread2 gidip b'ye giremez çünkü tek bir anahtar var
		// bu anahtar metodlara değil class'a özgü thread1'in tüm metodlardaki işlemlerini bitirip anahtarı 
		// kendisine teslim etmesini bekleyecek
		// Bu anahtara'da lock deniyor
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		 Synchronized anahtar kelimesi, Java'da eş zamanlılık (concurrency) sırasında ortaya çıkabilecek 
		 veri tutarsızlıklarını ve yarış durumlarını (race conditions) önlemek için kullanılır.
		 Özellikle, çoklu thread'lerin aynı kaynağa erişip bu kaynak üzerinde değişiklik yaparken
		 veri bütünlüğünü korur.
		 
		 Çoklu thread'ler aynı anda bir metodu çağırdığında, verinin doğru bir şekilde işlenmesini 
		 ve paylaşılmasını sağlamak için synchronized anahtar kelimesi kullanılır. 
		 Bir metod veya blok synchronized olduğunda, sadece bir thread o bölgeye aynı anda erişebilir. 
		 Diğer thread'ler, bir thread bu bölgeyi kullanırken beklemek zorundadır.
		 
		 
		 Veri Tutarsızlıklarını Önlemek: Çoklu thread'lerin aynı anda ortak verilere erişmesi sırasında, 
		 veri tutarsızlığı oluşabilir. Örneğin, iki thread aynı değişkeni güncellemeye çalıştığında 
		 istenmeyen sonuçlar ortaya çıkabilir.
		 
		 Yarış Durumlarını (Race Conditions) Engellemek: Aynı kaynağa birden fazla thread'in eş zamanlı 
		 erişimi bir yarış durumu yaratır. Yarış durumlarında, işlem sırası garanti edilmediği için
		 veri hataları ortaya çıkabilir.
		 */
	}
}
