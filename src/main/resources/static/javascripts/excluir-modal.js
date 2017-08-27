const modalExclude = function(modal, target){
	var nome = target.data('nome');
	var url = target.data('url-delete');
	var form = modal.find('form');
	form.attr('action', url);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + nome + '</strong>?');
}