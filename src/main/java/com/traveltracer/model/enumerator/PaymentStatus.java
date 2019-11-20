package com.traveltracer.model.enumerator;

public enum PaymentStatus {

	NAO_PAGO(0,"NAO_PAGO"),
	PAGO(1,"PAGO");
	
	private int code;
	private String description;
	
	
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	
	
	public int getCode() {
		return code;
	}



	public String getDescription() {
		return description;
	}

	public static PaymentStatus toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		
		for (PaymentStatus x : PaymentStatus.values()) {
			if (cod.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
		
	}
}
