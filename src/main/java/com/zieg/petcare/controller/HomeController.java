package com.zieg.petcare.controller;


import java.util.Calendar;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zieg.petcare.model.authentication.Users;
import com.zieg.petcare.model.enums.TipoMedida;
import com.zieg.petcare.model.enums.TipoMovimentacao;
import com.zieg.petcare.services.FornecedorService;
import com.zieg.petcare.services.MovimentacaoService;
import com.zieg.petcare.services.ProdutoService;
import com.zieg.petcare.services.TipoProdutoService;

/**
 * @author zieg
 *
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	@Autowired ProdutoService ProdutoService;
	@Autowired FornecedorService FornecedorService;
	@Autowired MovimentacaoService MovimentacaoService;
	@Autowired TipoProdutoService TipoService;

	@GetMapping
	public ModelAndView home(){
		
		
		ModelAndView mv = new ModelAndView("home/dashboard"); 
		mv.addObject("TotalEstoque", ProdutoService.getTotal());
		mv.addObject("EstoqueOff", ProdutoService.getTotalEstoqueOff());
		mv.addObject("FornecedoresTotal", FornecedorService.getTotal());
		mv.addObject("FaturaDesperdicio", MovimentacaoService.getFaturamento(Calendar.MONTH,  TipoMovimentacao.DESCARTE));
		mv.addObject("FaturamentoMes", MovimentacaoService.getFaturamento(Calendar.MONTH, TipoMovimentacao.SAIDA));
		mv.addObject("FaturamentoAno", MovimentacaoService.getFaturamento(Calendar.YEAR, TipoMovimentacao.SAIDA));
		mv.addObject("FaturamentoTotal", MovimentacaoService.getFaturamento(TipoMovimentacao.SAIDA));
		mv.addObject("FaturaLiquidaMes", MovimentacaoService.getFaturaLiquido(Calendar.MONTH, TipoMovimentacao.SAIDA));
		mv.addObject("FaturaLiquidaAno", MovimentacaoService.getFaturaLiquido(Calendar.YEAR, TipoMovimentacao.SAIDA));
		mv.addObject("medida", TipoMedida.values());
		mv.addObject("Fornecedores", FornecedorService.findAll() );
		mv.addObject("tipoProduto", TipoService.findAll());
		mv.addObject("produtos", Collections.emptyList());
		mv.addObject("users", new Users());
		
		return mv;
		
	}
	
	
	
}
