var dataTable = {

      table : null,

      create : function(data){

          this.table = $(data).DataTable({
                      pagingType: "simple_numbers",
                      search: true,
                      info:     true,
                      language: {
                              /*"url": "//cdn.datatables.net/plug-ins/1.10.13/i18n/Portuguese-Brasil.json" */
                              "url": "/layout/vendors/datatables/JSON/Portuguese-Brasil.json"
                            },
                      order: [[ 0, "asc" ]]
                              
                  });
          
          return this.table;

      },

      change : function(element, index){

          this.table.columns(index)
               .search($(element).val())
               .draw();

      },

      search : function(element){
          this.table.search($(element).val())
               .draw();
      }

};                

