var chartFornecedor = {

    create : function(element){

        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "/ajax/chartFornecedor",
            success : function(data, statusText, xhr) {
                
                //Instancia de um novo map<month, value>
                data = new Map(JSON.parse(data)
                                   .map(function(v, k, array){ 
                                        return Object.values(array[k]) 
                                   })) 


                var month = Array.from(data.keys())
                var dados = flatten(Array.from(data.values()))

                Highcharts.chart('fornecedorChart', {

                    chart: {
                        type: 'column',
                        inverted: true,
                        polar: false
                    },

                    title: {
                        text: ''
                    },

                    subtitle: {
                        text: ''
                    },
                    exporting:{
                        enabled : false
                    },
                    legend: {
                        align: 'right',
                        verticalAlign: 'middle',
                        layout: 'vertical'
                    },
                    credits: {
                        enabled: false
                    },
                    plotOptions: {
                        
                        column: {
                            grouping: true,
                            shadow: false,
                            borderWidth: 1
                        },
                        series: {
                            borderRadius: 3,
                            pointWidth: 15
                        }
                    },
                    xAxis: {
                        categories: month,
                        type: 'datetime'
                    },

                    yAxis: {
                        allowDecimals: false,
                        title: {
                            text: 'Valor (Reais)'
                        },
                        labels: {
                            overflow: 'justify'
                        }
                    },
                    tooltip: {
                        shared: true,
                        pointFormat: '{series.name} retorno de <b>R${point.y:,.0f}</b><br/>'
                        /* valueSuffix: ' Reais' */
                    },
                    series: dados
                });

            },
            error : function(e) {
                alert("ERROR: " + e.responseText)
            }
        })

    }

}

const getUniqueKeys = (obj) =>
    [].concat(...obj)
      .reduce((acc, cur) =>
            acc.add(cur.name),
            new Set())

const makeObj = (...keys) => {
    return keys.reduce((acc, cur) =>
                acc.concat({
                    name: cur,
                    data: []
                }), [])
}

const addItem = (obj, comp) =>
    obj.map((item) => {
                let result = comp.find(key => key.name === item.name)
                if(result) item.data.push(result.data)
                else item.data.push(null)
            })

const flatten = (obj) => 
    obj.reduce((acc, cur) => {
            addItem(acc,cur)
            return acc    
        }, makeObj(...getUniqueKeys(obj)))
