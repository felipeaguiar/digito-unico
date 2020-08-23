package br.com.bancointer.desafio.domain.calculadora;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache extends CalculadoraDigitoUnico {
	
	private static final int MAX = 10;
	private LinkedHashMap<Params, Integer> cacheMap;
	
	public Cache(CalculadoraDigitoUnico proximo) {
		super(proximo);
		
		cacheMap = new LinkedHashMap<Params, Integer>() { 
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<Params, Integer> eldest) { 
                return size() > MAX; 
            } 
        };
	}

	@Override
	public int digitoUnico(String n, int k) {
		Params params = new Params(n, k);
		
		synchronized (cacheMap) {
			if (cacheMap.containsKey(params)) {
				return cacheMap.get(params);
			}
		
			int digito = super.proximo.digitoUnico(n, k);
			cacheMap.put(params, digito);
			return digito;
		}
	}
	
	public Map<Params, Integer> dump() {
		synchronized (cacheMap) {
			return Collections.unmodifiableMap(cacheMap);
		}
	}

}
