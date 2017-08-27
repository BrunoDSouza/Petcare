const addItens = (obj, value) => obj.push(value)
const deleteItem = (obj, index) => obj.splice(index, 1)

var item = {
	
	//Proprerties object
    array : [],     
    submitForm : false,
   		
    //Methods object

	duplicate: function(items){
		
		for (var i = 0; i < items.length-1 ; i++) {
			var obj = items[i]
			for (var j = i+1; j <=items.length-1; j++) {
				var comparator = items[j]
				if ((obj.tipo == comparator.tipo) &&
				    (obj.idproduto == comparator.idproduto) &&
				    (obj.idsetor == comparator.idsetor)){
					
					$('.error.produto, .error.tipo, .error.setor')
						.html("Atenção! Um item similar já foi adicionado, favor altere o valor!")
						.fadeIn(0200)
						.fadeOut(8000)

					deleteItem(item.array, item.array.length-1)
					return true
				}
			}
		}

	},

	verifyFields : function(input){

		var flag = 0
		$.each(input, function( i, element ) {
			if(element.value != ""){
				flag++
			}
		});

		return (flag > 0)
	},

	validate : function(){

		/*Remove erros e animações*/
		$('.error').html("")
			   	   .stop(true, true)

		var verify  = []
		var flag = 0

		verify.push($('#tipo'))
		verify.push($('#setor'))
		verify.push($('#produto'))
		verify.push($('#saldo'))

		for ( i in verify) {
			if (verify[i].val() == "" || parseInt(verify[i].val()) == 0 || verify[i] == null){

				$('.error').eq(i)
						   .html("Compo obrigatório!")
						   .fadeIn(0200)
						   .fadeOut(4000)

				flag ++
			}
		}

		return (flag > 0)
	},

	reset : function(){
		
		$('select').find('option:first')
					.prop('selected', true)
					.trigger("chosen:updated")

		$("input[type!='hidden']").val("")					
	},	

	add : function(element, input){

		if(this.validate()){		
			return
		}
		
		var obj = {}
		obj["tipo"] = $('#tipo').val()
		obj["idsetor"]			= $('#setor').val()
		obj["setor"]			= $('#setor option:selected').text()
		obj["idproduto"]		= $('#produto').val()
		obj["produto"]			= $('#produto option:selected').text()
		obj["qtd_produto"]		= $('#saldo').val()
		
		addItens(item.array,obj)

		if(this.duplicate(item.array))
			return false	


		var dados = JSON.stringify(item.array)
		input.val(dados)

		this.request(dados,element)

	},
	
	remove : function(input, element, index){
		
		deleteItem(item.array,index)
		
		var dados = JSON.stringify(item.array)

		if(item.array.length > 1) 
			input.val(dados)
		else
			input.val("");
		
		this.request(dados,element)
		
	},

	request : function(dados,element){

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/ajax/listaItens",
			data : dados,
			success : function(data, statusText, xhr) {
				
				var valid = JSON.parse(xhr.getResponseHeader("valid"));

				if(valid){
					element.html(data)
					item.reset()
				}
				else{
					$('.error.quantidade').html("Atenção! Quantidade ultrapassa o estoque!")
										  .fadeIn(0200)
										  .fadeOut(6000)
					deleteItem(item.array, item.array.length-1)
				}

			},
			error : function(e) {
				alert("ERROR: " + e.responseText)
				deleteItem(item.array, item.array.length-1)
			}
		})

	}
};

