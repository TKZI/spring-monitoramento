package com.tkzi.produto.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tkzi.produto.client.avaliacoes.AvaliacaoClient;
import com.tkzi.produto.client.avaliacoes.AvaliacaoModel;
import com.tkzi.produto.domain.Produto;
import com.tkzi.produto.domain.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private final ProdutoRepository produtos;
	private final AvaliacaoClient avaliacoes;

	public ProdutoController(ProdutoRepository produtos, AvaliacaoClient avaliacoes) {
		this.produtos = produtos;
		this.avaliacoes = avaliacoes;
	}

	@GetMapping
	public List<ProdutoModel> buscarTodos() {
		return produtos.getAll()
				.stream()
				.map(this::converterProdutoParaModelo)
				.collect(Collectors.toList());
	}

	@GetMapping("/{produtoId}")
	public ProdutoModel buscarPorId(@PathVariable Long produtoId) {
		return produtos.getOne(produtoId)
				.map(this::converterProdutoParaModeloComAvaliacao)
				.orElseThrow(RecursoNaoEncontradoException::new);
	}
	
	private ProdutoModel converterProdutoParaModelo(Produto produto) {
		return ProdutoModel.of(produto);
	}
	
	private ProdutoModel converterProdutoParaModeloComAvaliacao(Produto produto) {
		return ProdutoModel.of(produto, buscarAvaliacaoDoProduto(produto.getId()));
	}

	private List<AvaliacaoModel> buscarAvaliacaoDoProduto(Long productId) {
		return avaliacoes.buscarTodosPorProduto(productId);
	}

}