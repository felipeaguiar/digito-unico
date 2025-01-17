package br.com.bancointer.desafio.domain.criptografia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CriptografiaRsaTest {
	
	private String publicKey = 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2wSceL/XjYodub5w9t3M" + 
			"9351ktNBubFdSc5s9Qc8S0dmz5P7ibr811SOEa/+YLQTOm4jUrdu9hrUJZPiOo4k" + 
			"V8/IJr9RkLaBCHiYym3EMzgCEIlAAFfdXEnJno1Kp8pDiJBIDbcHe9ISACPB/s27" + 
			"zXN+qVXNup90eGjuYQ3kcuchoO76Wq/kLbvSMrKneiO5otzMZEXlxtpP3sAk3uvJ" + 
			"vOmjogejyn0dfV+mkXZDhRCD/KCKxqzipYgyBN0RcKNhWEHhXsMs+rxFTzQoLFUG" + 
			"WhxgN0ixG2MxSEKZOZHQUcyJ5C3gmeQJlHvtzZ5yjepKkK4Qzt/9aEuizeJBFWtT" + 
			"ZwIDAQAB";
			
	private String privateKey = 
			"MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDbBJx4v9eNih25" + 
			"vnD23cz3fnWS00G5sV1Jzmz1BzxLR2bPk/uJuvzXVI4Rr/5gtBM6biNSt272GtQl" + 
			"k+I6jiRXz8gmv1GQtoEIeJjKbcQzOAIQiUAAV91cScmejUqnykOIkEgNtwd70hIA" + 
			"I8H+zbvNc36pVc26n3R4aO5hDeRy5yGg7vpar+Qtu9Iysqd6I7mi3MxkReXG2k/e" + 
			"wCTe68m86aOiB6PKfR19X6aRdkOFEIP8oIrGrOKliDIE3RFwo2FYQeFewyz6vEVP" + 
			"NCgsVQZaHGA3SLEbYzFIQpk5kdBRzInkLeCZ5AmUe+3NnnKN6kqQrhDO3/1oS6LN" + 
			"4kEVa1NnAgMBAAECggEBAKU0EzrZqiPvXICw79CmeWbuEo2PoHILIUz80h75ODeo" + 
			"QhqtivzW+MUL2KXq1Lvo2OAoF6jbCG3GyuMVa32zniJGbrkbctDveOEE5ms65NWl" + 
			"l5kVjhs0K5rjZZudBAUQgmROQKtntdNmH4HPdxNV6CUOyym7uRTeFiKFM9otenZY" + 
			"D1hfKYHoYgQpAYLlbq2/JwmuL0UVyVGxkHmQw/W4MlbpdFTlNPnBdV/XXZv9wA+R" + 
			"uYqhZ0Ds1Hy0rBT9jfRCUsq19HYfhQh8FOwNlzDrhyB0N+7Xp0gT0D20rd5IHhCs" + 
			"LoFSgdFTKmBQYYECdsEabfwdJA7DZS56gL7//cA8wqECgYEA/mIPYzLwIqRnPVSO" + 
			"PbzmhJ1wzXVsyzYZ/pvE2VtU4+/Vh4kgj+5gPax6AJYniZTYgxYRSbYKHeEqLVUI" + 
			"6vkHwR3xJI8t8Sau8CwyoRe5zoXxs919NaswzyhI5ikIAsoxyMGpIRtSqNgdiiuC" + 
			"VaW6fwEQPODJkxqRk96NiOYoZJ8CgYEA3GkBCNymNH/BSFFfPpBp75zr2eDpf0gv" + 
			"DmOxmGSxZst5aFMk0VnY9LscqJWs2kmc/cKyqoqc1kNKDkosm0mcJg8J+IUdTRqs" + 
			"H18giomD4goxhxnCFmedhv/KaRaTnqYTAZx67w5FAU6Oqk3Kqi6ZduiPBsY0Y6+d" + 
			"X28YFZMflDkCgYAxRp+CcMCRhVU2TSXMkcX5lGHgesmbR5ybdGMaU5XLHY84VPjI" + 
			"yL091SZA7NU10m5qXiD1LV0iUpaP93PlyF2XLAylL1MP7pVeksEv8V+dwNaEJ/h0" + 
			"J/gpeL0+UF5r4+zm3ZS8hAVsDYkTVcBGhPbuWS9jC4wggboHccnq5eKXMwKBgQCg" + 
			"GVW7DitcLwBCe3jnhRauChn3JTv2TISBu0ChJWMG+/2wHdS8jLre6ZsjibUIyjfS" + 
			"y73jytOpfy8nuhE0qv9VVc80SdzCyQEFuJ92gfTeF/Fp5PfqNlFiYiKwusl3j8J2" + 
			"hSd2xbKyBFH0upTfkQHAMaTSkt4zGrkBPT0NTtS9YQKBgQCIHYZI7JX51Fn9WuqG" + 
			"w5CKTfadI/cG3uaqRnbQN/ophNifF5M2ikhuwPAbEoc1ar4RbKSd5a/68frXtdDt" + 
			"0Rli08SIrBnaVirN2njHo/LuMv+xgBGR6BgLTH5yU+8r4VFMBfIRk/Fj62OtGEGE" + 
			"qduD7lD+dPWJrtcMD9KmxlH1sQ==";
			
	private Criptografia criptografia;

	@BeforeEach
	void setUp() throws Exception {
		criptografia = new CriptografiaRsa();
	}

	@Test
	void test() {
		String encrypted = criptografia.encrypt("Felipe Aguiar", publicKey);
		String decoded = criptografia.decrypt(encrypted, privateKey);
		
		assertEquals("Felipe Aguiar", decoded);
	}

}
