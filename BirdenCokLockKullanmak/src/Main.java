
public class Main {
	
	
	public static void main(String[] args) {
		
		/* ListWorker worker = new ListWorker();
		long baslangic = System.currentTimeMillis();
		
		worker.degerAta();
		
		long bitis = System.currentTimeMillis();
		
		System.out.println("Geçen Süre : " + (bitis - baslangic));*/
		
		ListWorker worker = new ListWorker();
		
		worker.calistir();
		// Burada çalıştırdığımz zaman threadlerimi paralel bir şekilde
		// Çalıştırdıkları metot synchronized olmadığı için veri tutarsızlığı ve thread yarışından dolayı
		// çıktı farklılığı oluşuyor
		// Synchronized yaptığım zaman threadlerimin işlem yapacağı verilerin bulunduğu metodu 
		// Veri tutarsızlığının önüne geçmiş oldum
		
		// Ayrıca synchronized bir metoda girdiyse benim thread'im lock metoda değil class'a özgü olduğu için
		// Öbür threadler bu threadler bu lock'ı kullanıp tüm synchronized metotlardaki işlemlerini bitirene kadar
		// ve lock onlara teslim edilene kadar hiç bir metota girip işlem yapamazlar
		
		// Tek bir lock olduğu zaman ve çok synchronized metot olduğunda bu performans kaybına yol açabilir
		// Çünkü diğer metotlar bu işlemin bitmesini beklemek zorunda ve teker teker çalıştıracaklar bu metotları
		// Burada performans kaybına neden olmamak için 2 tane anahtara sahip olmam gerekiyor 
		// Yani thread1 im ilk metoda girdiği anahtarı thread2'ye bırakıp diğer metoda farklı bir anahtarla girmesi gerekiyor ki
		// thread2'im boş yere beklmesin ve performans kaybı oluşmasın
		// Bu yüzden artık metotlarımızı synchronized yazmıyacağız onun yerine 2 tane anahtar oluşturacağız
		// Bu işlemi synchronized kod bloğu yazarak yapacağız
		
	}
	
	
	
	
	
	
	
	
	
}