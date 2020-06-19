package br.com.exacta.client.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exacta.client.dto.GastoDTO;
import br.com.exacta.client.feign.GastoFeign;


@RestController
@RequestMapping("/gasto")
public class GastoController {
	
	@Autowired
	private GastoFeign gastoFeign;
	
	@GetMapping
	public ResponseEntity<List<GastoDTO>> listarGastos() {
		return ResponseEntity.ok(gastoFeign.getGastos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GastoDTO> detalharGasto(@PathVariable Long id) {
		Optional<GastoDTO> gastoDTOOptional = gastoFeign.detalharGasto(id);
		if (gastoDTOOptional.isPresent()) {
			return ResponseEntity.ok(gastoDTOOptional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<GastoDTO> novoGasto(@RequestBody GastoDTO gastoDTO){
		return ResponseEntity.ok(gastoFeign.novoGasto(gastoDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GastoDTO> alterarGasto(@PathVariable Long id, @RequestBody GastoDTO gastoDTO){
		Optional<GastoDTO> gastoOptional = gastoFeign.findById(id);
		if (gastoOptional.isPresent()) {
			gastoDTO.setId(gastoOptional.get().getId());
			return ResponseEntity.ok(gastoFeign.salvar(gastoDTO.getId(), gastoDTO));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> removerGasto(@PathVariable Long id) {
        Optional<GastoDTO> gastoOptional = gastoFeign.findById(id);
        if(gastoOptional.isPresent()) {
        	gastoFeign.remover(gastoOptional.get().getId());
        	return ResponseEntity.ok().build();            
        }
        return ResponseEntity.notFound().build();
    }

}
