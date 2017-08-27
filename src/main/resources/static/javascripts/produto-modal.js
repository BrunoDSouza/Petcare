const adicionaProduto = function(button){
	
	var url = button.attr('href');

	$.get(
		url,
		function(data){
			$('#produto').html(data)
			$('#produto option:last').prop('selected', true)
						 			 .trigger("chosen:updated");
		}
	);

};


const listarProdutos = function(element, url){
	
	$.get(
		url,
		function(data){
			element.html(data);
			dataTable.create($('.dataTable'));
		}
	);
};


const findAllProdutos = function(element){
	listarProdutos(element, "/ajax/listProdutosAll");
}

const findAllProdutosModal = function(element){
	listarProdutos(element, "/ajax/listProdutos");
}

const findOffProdutos = function(element){
	listarProdutos(element, "/ajax/listProdutosOff");
}


