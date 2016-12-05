var ClienteView = Backbone.View.extend({

    events:{
        'click #btnSalvar' : 'salvar'
    },

    salvar: function salvar(){
        this.model.set("nome", $('#nomeCliente').val());
        this.model.set("sobrenome", $('#sobrenomeCliente').val());
        this.model.set("idade", $('#idadeCliente').val());
        this.model.save(null,{
            success: function(model,response){
                console.log(model);
                alert("Sucesso");
            }
        });
    }
});
var clienteView = new ClienteView({el:this.$('#dados'), model: new ClienteModel()});