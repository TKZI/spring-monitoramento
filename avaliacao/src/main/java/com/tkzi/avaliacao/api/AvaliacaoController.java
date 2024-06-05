package com.tkzi.avaliacao.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tkzi.avaliacao.domain.AvaliacaoRepository;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	private final AvaliacaoRepository avaliacoes;
	
	public AvaliacaoController(AvaliacaoRepository avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	@GetMapping
	public List<AvaliacaoModel> buscarPorProduto(@RequestParam Long produtoId){
		return avaliacoes.getAll()
				.stream()
				.filter(avaliacao -> avaliacao.getProdutoId().equals(produtoId))
				.map(AvaliacaoModel::of)
				.collect(Collectors.toList());
	}

}
