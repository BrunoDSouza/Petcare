package com.zieg.petcare.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zieg.petcare.model.Movimentacao;
import com.zieg.petcare.model.Produto;
import com.zieg.petcare.model.enums.TipoMovimentacao;

public interface MovimentacoesRepository extends JpaRepository<Movimentacao, Long>{
	
	List<Movimentacao> findByProduto(Produto produto);
	List<Movimentacao> findByTipoMovimentacao(TipoMovimentacao tipo);
	List<Movimentacao> findByTipoMovimentacaoNot(TipoMovimentacao tipo);
	List<Movimentacao> findByTipoMovimentacaoAndDtMovimentacaoGreaterThanEqual(TipoMovimentacao tipo, Date date);
	List<Movimentacao> findByTipoMovimentacaoAndDtMovimentacaoGreaterThanEqualAndDtMovimentacaoLessThanEqual(TipoMovimentacao tipo, Date begin, Date end);
	List<Movimentacao> findByProdutoAndDtMovimentacaoGreaterThanEqual(Produto produto, Date date);
}
