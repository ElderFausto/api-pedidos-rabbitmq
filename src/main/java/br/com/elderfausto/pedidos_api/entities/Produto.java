package br.com.elderfausto.pedidos_api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	private UUID id = UUID.randomUUID();
	private String nome;
	private Double valor;
}
