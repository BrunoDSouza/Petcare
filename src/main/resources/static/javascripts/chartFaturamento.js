var chartFaturamento = {

	create : function(element){

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/ajax/chartFaturamento",
			success : function(data, statusText, xhr) {
				
				//Instancia de um novo map<month, value>
				data = new Map(JSON.parse(data)
						   		   .map(function(v, k, array){ 
									    return Object.values(array[k]) 
								   })) 


				var month = Array.from(data.keys())
				var dados = Array.from(data.values())

				Highcharts.chart(element, {

				    title: {
				        text: ''
				    },
				    exporting:{
				    	enabled : false
				    },
				    xAxis: {
				        categories: month
				    },
				    yAxis: {
				        title: {
				            text: 'Valor (Reais)'
				        }
				    },
				    tooltip: {
				        pointFormat: 'Retorno de <b>R${point.y:,.0f}</b><br/>'
				    },
				    credits: {
				        enabled: false
				    },
				    series: [{
				    	type: 'column',
				        colorByPoint: false,
				        data: dados,
				        showInLegend: false
				    }],
				    

				});


			},
			error : function(e) {
				alert("ERROR: " + e.responseText)
			}
		})

	}

}