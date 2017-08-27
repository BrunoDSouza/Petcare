const validateData = (date) => {
	var regex = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
	var value = date.val()

	if(!value.match(regex) && value !== ""){
		date.parent('.data-field')
			.addClass('has-error')
		date.val("")

		$('.error').html("Por favor insira uma data válida!")
						.fadeIn(0200)
						.fadeOut(8000)
		

		return false

	}

	date.parent('.data-field')
		.removeClass('has-error')
	return true;


}
const maskData = (date) => {

	var mask = date.val();
	mask = mask.replace(/(\d{2})(\d)/,"$1/$2")

	date.val(mask)
}


/*Funcção para retornar os dados de request e alimentar modal*/
const getModal = function(target, element){

	var url = target.data('url-edit');

	if(url == null || url == undefined) return

	$.get(
		url,
		function(data){
			$(element).html(data)
			$('.chosen').chosen({width: '100%'});
		}
	);

};


/*Function para uniformizar tamanhos de elementos*/
const uniformHeight = (collection) => {

	var maxHeight = 0

	return collection.each(function(){
		maxHeight = Math.max(maxHeight,$(this).height());
	}).height(maxHeight);
}



/*Funcção para customizar mensagem validador do html*/
/*document.addEventListener("DOMContentLoaded", function() {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function(e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("Este campo é obrigatório!");
            }
        };
        elements[i].oninput = function(e) {
            e.target.setCustomValidity("");
        };
    }
})*/
