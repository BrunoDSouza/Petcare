var chartProduto = {

	//Recebe como argumento um elemento html(element html != object jquery)
	create : function(element, tag){

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/ajax/chartProdutos/" + tag,
			success : function(data, statusText, xhr) {
				
				Highcharts.chart(element, {
				chart: {
			    	alignTicks: false,
			    	gridLineWidth: 0,
			        plotBackgroundColor: null,
			        plotBorderWidth: null,
			        plotShadow: false,
			        type: 'pie',
			    },
			    exporting:{
			    	enabled : false
			    },
			    title: {
			        text: ''
			    },
			    tooltip: {
			        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			    },
			    credits: {
			        enabled: false
			    },
			    plotOptions: {
			        pie: {
			            allowPointSelect: true,
			            cursor: 'pointer',
			            dataLabels: {
			                enabled: true,
			                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                style: {
			                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                }
			            }
			        }
			    },
			    series: [{
			    	type: "pie",
			    	name: 'Taxa de',
			    	center: ["50%", "50%"],
			    	dataLabels: {
		    			connectorPadding: 1,
		    			connectorWidth: 1,
		    			padding: 5,
		    			verticalAlign: "middle"
		    		},
			        colorByPoint: true,
			        size:"80%",
			        data: JSON.parse(data)
			    }]
			});

			},
			error : function(e) {
				alert("ERROR: " + e.responseText)
			}
		})

	}

}		

			