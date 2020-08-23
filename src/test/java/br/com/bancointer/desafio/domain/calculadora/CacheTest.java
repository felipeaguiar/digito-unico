package br.com.bancointer.desafio.domain.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CacheTest {
	
	@Mock
	private CalculadoraDigitoUnico calculadora;
	private Cache cache;
	private Map<Params, Integer> esperado;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		esperado = new LinkedHashMap<>();
		cache = new Cache(calculadora);
	}

	@Test
	void deveSalvarResultadoNoCache() {
		esperado.put(new Params("9875", 1), 2);
		mock();
		
		int digito = cache.digitoUnico("9875", 1);
		
		assertEquals(2, digito);
		assertEquals(esperado, cache.dump());
	}
	
	@Test
	void deveSalvarMaisDoisResultadosNoCache() {
		esperado.put(new Params("9875", 1), 2);
		esperado.put(new Params("9875", 4), 8);
		mock();
		
		int digito1 = cache.digitoUnico("9875", 1);
		int digito2 = cache.digitoUnico("9875", 4);
		
		assertEquals(2, digito1);
		assertEquals(8, digito2);
		assertEquals(esperado, cache.dump());
	}
	
	@Test
	void deveSalvarMaisDoisResultadosNoCacheSync() throws InterruptedException {
		esperado.put(new Params("9875", 1), 2);
		esperado.put(new Params("9875", 4), 8);
		mock();
		
		Thread thread1 = new Thread(() -> cache.digitoUnico("9875", 1));
	    Thread thread2 = new Thread(() -> cache.digitoUnico("9875", 4));
	    Thread thread3 = new Thread(() -> cache.digitoUnico("9875", 4));
	    
	    thread1.start();
	    thread2.start();
	    thread3.start();
	    
	    thread1.join();
	    thread2.join();
	    thread3.join();	    
	    
		assertEquals(esperado, cache.dump());
		verify(calculadora, times(2)).digitoUnico(anyString(), anyInt());
	}
	
	@Test
	void deveTestarUltilizacaoDeValorEmCacheSync() throws InterruptedException {
		esperado.put(new Params("1", 2), 2);
		esperado.put(new Params("1", 3), 3);
		esperado.put(new Params("1", 4), 4);
		esperado.put(new Params("1", 5), 5);
		esperado.put(new Params("1", 6), 6);
		esperado.put(new Params("1", 7), 7);
		esperado.put(new Params("1", 8), 8);
		esperado.put(new Params("1", 9), 9);
		esperado.put(new Params("1", 10), 1);
		esperado.put(new Params("1", 11), 2);
		mock();
		
		List<Thread> threads = new ArrayList<>();
		
		threads.add(new Thread(() -> cache.digitoUnico("1", 1)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 2)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 3)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 4)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 1)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 5)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 2)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 6)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 7)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 8)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 9)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 10)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 10)));
		threads.add(new Thread(() -> cache.digitoUnico("1", 11)));
	    
		for (Thread t : threads) t.start();
		for (Thread t : threads) t.join();
		
		assertEquals(esperado, cache.dump());
		verify(calculadora, times(11)).digitoUnico(anyString(), anyInt());
	}
	
	private void mock() {
		for (Map.Entry<Params, Integer> entry : esperado.entrySet()) {
		    String n = entry.getKey().getN();
		    int k = entry.getKey().getK();
		    int digito = entry.getValue();
		    
		    when(calculadora.digitoUnico(n, k)).thenReturn(digito);
		}
	}

}
