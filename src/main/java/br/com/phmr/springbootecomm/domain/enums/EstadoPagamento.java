package br.com.phmr.springbootecomm.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), PARCELADO(3, "Parcelado");

	private Integer cod;
	private String descricao;

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		for (EstadoPagamento estadoPagamento: EstadoPagamento.values()) {
			if (cod == estadoPagamento.getCod()) {
				return estadoPagamento;
			}
		}
		throw new IllegalArgumentException("Código inválido para Estado Pagamento. Cod:" + cod);
		
	}

}
