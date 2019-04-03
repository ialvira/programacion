package com.ipartek.formacion.pojo;

import java.util.List;
public class CarritoDto {
	private List<Integer> misId;

	public List<Integer> getMisId() {
		return misId;
	}

	public CarritoDto(List<Integer> misId) {
		super();
		this.misId = misId;
	}
	public CarritoDto() {
		
	}
	public void setMisId(List<Integer> misId) {
		this.misId = misId;
	}

	@Override
	public String toString() {
		return "CarritoDto [misId=" + misId + "]";
	}
	
}
