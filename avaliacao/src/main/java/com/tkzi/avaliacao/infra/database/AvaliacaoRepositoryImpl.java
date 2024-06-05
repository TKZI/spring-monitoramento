package com.tkzi.avaliacao.infra.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tkzi.avaliacao.domain.Avaliacao;
import com.tkzi.avaliacao.domain.AvaliacaoRepository;

@Component
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

	private static final List<Avaliacao> AVALIACOES = new ArrayList<>();
	private static long id = 1;
	
	//Inicializador dos statics
	static {
		AVALIACOES.add(new Avaliacao(nextId(), 10, "Produto superou minhas expectativas.",
				"Thiago",  1L));
		AVALIACOES.add(new Avaliacao(nextId(), 1, "Veio com feito.",
				"Alexandre", 1L));
		AVALIACOES.add(new Avaliacao(nextId(), 4, "Computador trava muito."
				,"Maria", 1L));
		AVALIACOES.add(new Avaliacao(nextId(), 8, "Quase perfeito.",
				"Daniel", 2L));
		AVALIACOES.add(new Avaliacao(nextId(), 5, "Não vem com sistema operacional.",
				"Alex", 3L));
	}
	
	@Override
	public void save(Avaliacao avaliacao) {
		avaliacao.setId(nextId());
		AVALIACOES.add(avaliacao);
	}

	@Override
	public Optional<Avaliacao> getOne(Long id) {
		return AVALIACOES.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	@Override
	public List<Avaliacao> getAll() {
		return new ArrayList<>(AVALIACOES);
	}

	private static long nextId() {
		return id++;
	}
}
