package br.com.exacta.client.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GastoDTO {

		private Long id;
		private String nomePessoa;
		private String descricao;
		private LocalDateTime dataHora; 
		private BigDecimal valor;
		private String tags;

}
