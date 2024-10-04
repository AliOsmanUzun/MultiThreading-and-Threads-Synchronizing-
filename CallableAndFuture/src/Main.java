import java.io.IOException;
import java.lang.classfile.TypeAnnotation.ThrowsTarget;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	
	public static void main(String[] args) {
		
	// Threadlerden değer dönmemizi ve bu değeri daha sonra elde etmemizi sağlayan 2 Interface
	// Callable ve Future interfaceleri
	// Kısaca threadlerimin işlerini bitirdikten sonra bir değer döndürmesini sağlıyorum
		
	ExecutorService executor = Executors.newFixedThreadPool(1);// Thread havuzu oluşturduk
	
	// Tasklarımızı atayalım
	/*executor.submit(new Runnable() {
		@Override
		public void run() {
			Random random = new Random();
			System.out.println("Thread çalışıyor");
			
			int sure = random.nextInt(1000) + 2000 ;
			
			try {
				Thread.sleep(sure);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread çıkıyor");
		}
	}); */
	// BU sefer runnable bir task değil Callable bir task veriyorum executor'ıma
	Future<?> future = executor.submit(new Callable<Integer>() { 
										// Generic bir interface olduğu için hangi değeri döndürmek istiyorsam
										// Generic yapı içerisine o değeri girmem gerekiyor
		@Override
		public Integer call() throws Exception {
		// Run metodu ile aynı mantıkta çalışıyor farkları ise exception fırlatabiliyor ve 
		// bir değer dönebiliyor
			
			Random random = new Random();
			System.out.println("Thread çalışıyor");
			
			int sure = random.nextInt(1000) + 2000 ;
			if (sure > 2500) {
				throw new IOException("Thread çok uzun süre uyudu");
			// Göstermek istediğim meesajı direkt olarak constructor içerisine yazabilirim
			}
			try {
				Thread.sleep(sure);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread çıkıyor");	
		
			return sure;
			// Peki bu döndürülen değeri nasıl alacağız 
			// Bunun için Future interface'inden bir referans kullanmalıyız
		}	
	}); 
	
	executor.shutdown(); // executor kullanıyorsak muhakkak shutdown ile sonlandırmamız gerekiyor
	// Taskımız bittiği zaman biz future.get ile dönen değeri elde edebiliyoruz
	try {
		System.out.println("Dönen Değer : " + future.get());
	} catch (InterruptedException e) {
		e.printStackTrace();
	} catch (ExecutionException e) {
		System.out.println(e);
	}
	/*
	Son olarak Future<?> şeklinde bir kullanım ile türden bağımsız şekilde Callable Interface'inin 
	call metodundan hangi değer dönerse dönsün future referansım sorunsuz bir şekilde o değeri alacak
	
	
	*/
	}
}
