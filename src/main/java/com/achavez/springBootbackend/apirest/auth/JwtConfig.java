package com.achavez.springBootbackend.apirest.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345"; 
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEpAIBAAKCAQEAuLWOsLuod1vUaYoTnvmO9bYQPCtnjO0Iw0iSJCEYgENNIgEk\n"
			+ "7ZqfeSpko6eQEEhkZ+hB+ijTLz5c/+bu4UW86VCH4y8Ssckl0AXyYs23Z60T/7wa\n"
			+ "gKPJ7oD3G+NJvBy0Ni4v6UxBGNpFn4/CiPK9D2GlRieOtFgBz4Av8zfENXQME9KF\n"
			+ "lUGmHO2vzt8alUhnOcRL2W9+VcUaT/YVqqvhu5gvUeLwDRvSBOnkEIK4Bjef6RLP\n"
			+ "DPE/FUX23TkwasK1thY/LiENkTahLt6xIpj2sfMPumX8RoqIf5RxKBA4WDxyMrJ7\n"
			+ "1AFHHfbbTpTtSRqd2mrQeX5R8NFACNJt4MN+fwIDAQABAoIBAA0SXDzJqwx3ZTrq\n"
			+ "2KShiKp+7fs0HynsBHi9hAomhXNeT/EeH5zpGmwawfCLBTkBxolL+I9ZoEhxo2dA\n"
			+ "0psCsnHYjyVWyX3nfFOSeCrh4B7oMsggace8z6efXN2OYkN7zI0hC6b5DIpTuCWo\n"
			+ "qN3EP/j8shOa7RB6QfaLXdlakvotU1wWXIFocnPs6/bhBVxnL8BJ6GH6kpszpjMU\n"
			+ "bGQlBV75kCLsHp0ELUMRMAPadvLZyyjIv5I5whSt7dNPIW7cENyVskdmoCUP/YOR\n"
			+ "mQxmLgAFdRf2HE5bBCJDXToe3W7wokjXOqKH54AAAd1INPuSyxFcXKRawdIXCmIh\n"
			+ "5Ls68/ECgYEA7qkMF+wQAIL/zJBLvxsmUVoK+/USErIM7XsySDztEa81UYjfQ6cx\n"
			+ "lEdK4eYB1k44UFiqSdr5C2vIQuw4GuyznIdw6JikQDbPLtcKqLlp1gR/oJX+ULWe\n"
			+ "WLk2fyitush46vnwUwlAyoRXeBLOqw3LVRamQ+7QGsLzb6h3j+CH0lMCgYEAxiEM\n"
			+ "Ymni8dykCo4dOWXKbORhEkyiDWB58mSuouVxdFABVBldqztCiiyKam3tPiesZ9GL\n"
			+ "fr0Z2sWj+y/DxnO1siZyLcnfvSSGb2MG48LBQ+GyS9lFi05IcpogmGMspML7bfiJ\n"
			+ "clOb1bEp08M41NhM0flW7d5H30KSnFEbA0BjdaUCgYEAv/iijauIw6Cb+gQnqbVB\n"
			+ "ATcYlsrumrM1yuDJ9WlsmEkC7m7WiCi4rLkb9tF4sNWCXr6zW20T3qf4EjzIdH/p\n"
			+ "igM4QyBlHnH3/YlkPYvHIivELXv5cRpmIJ/6whYKiLAw83HQajqLL1t8sE0QiG80\n"
			+ "G2F4pDQ4mQ4vmqU8ZzMIjZkCgYAfxgEEb1Kz0tyDzNKm98oEuINQufVrC8PCuZ7A\n"
			+ "BOey7XmpEQBSi4haPFZeRw663wXpCFPEmXLiALwRdlGBK+FBlqTM7wjwfkEI7+qU\n"
			+ "8BCqYDGFtGxQsNcxecIsbgskyRglRKxWJ05gcK7yhOZCTAXVzpTARNtl1HlKlD8u\n"
			+ "wE6y+QKBgQDl0iXPE11zgc94FSD2godl/JyKy6qQdWbxwNftI1kYB01A4h+8pHbO\n"
			+ "yXDjtH0cH34ilv8FnK5HWYJ4e5oCqHv2GRAtRtn9tYQb3LYnMhGdrchwahXSExYQ\n"
			+ "igbyY0NEkQh547Pzir/iTKNaDoZirvn3FZM0F+hWUc7V8Vp8RCuKnA==\n"
			+ "-----END RSA PRIVATE KEY-----"; 
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuLWOsLuod1vUaYoTnvmO\n"
			+ "9bYQPCtnjO0Iw0iSJCEYgENNIgEk7ZqfeSpko6eQEEhkZ+hB+ijTLz5c/+bu4UW8\n"
			+ "6VCH4y8Ssckl0AXyYs23Z60T/7wagKPJ7oD3G+NJvBy0Ni4v6UxBGNpFn4/CiPK9\n"
			+ "D2GlRieOtFgBz4Av8zfENXQME9KFlUGmHO2vzt8alUhnOcRL2W9+VcUaT/YVqqvh\n"
			+ "u5gvUeLwDRvSBOnkEIK4Bjef6RLPDPE/FUX23TkwasK1thY/LiENkTahLt6xIpj2\n"
			+ "sfMPumX8RoqIf5RxKBA4WDxyMrJ71AFHHfbbTpTtSRqd2mrQeX5R8NFACNJt4MN+\n"
			+ "fwIDAQAB\n"
			+ "-----END PUBLIC KEY-----"; 
	
}
