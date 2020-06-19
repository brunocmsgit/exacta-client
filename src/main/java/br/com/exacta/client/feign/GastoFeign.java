package br.com.exacta.client.feign;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.exacta.client.dto.GastoDTO;

@FeignClient(name = "gasto", url = "http://localhost:8080/gasto")
public interface GastoFeign {
	
	@GetMapping	
    List<GastoDTO> getGastos();
	
	@GetMapping("/{id}")
	Optional<GastoDTO> detalharGasto(@PathVariable Long id);
		
	@PostMapping
	GastoDTO novoGasto(GastoDTO gastoDTO);
	
	@DeleteMapping("/{id}")
	void remover(@PathVariable Long id);

	@GetMapping("/{id}")
	Optional<GastoDTO> findById(@PathVariable Long id);

	@PutMapping("/{id}")
	GastoDTO salvar(@PathVariable Long id, GastoDTO gastoDTO);
	
	

}
