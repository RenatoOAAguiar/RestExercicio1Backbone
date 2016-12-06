var ClienteView = Backbone.View.extend({

    events:{
        'click #btnSalvar' : 'salvar',
        'click #btnListar' : 'abrirListView'
    },

    initialize: function(){
        var that = this;
        $.get('templates/ClienteView.html', function (data) {
            that.template = _.template(data);
            that.$el.html(that.template());  
        }, 'html');
            //that.render();
    },

    render : function(){
        this.inicializaModel();
        this.$el.html(this.template);
        return this;
    },

    salvar : function salvar(){
        this.model.set("nome", $('#nomeCliente').val());
        this.model.set("sobrenome", $('#sobrenomeCliente').val());
        this.model.set("idade", $('#idadeCliente').val());
        this.model.save(null,{
            success: function(model,response){
                console.log(model);
                alert("Sucesso");
                $('#btnListar').show();
            }
        });
    },

    inicializaModel : function inicializaModel(){
        this.model = new ClienteModel();
    },

    abrirListView : function abrirListView(){
        var that = this;
        $.get('view/ClienteListView.js', function (data) {
            clienteListView = new ClienteListView({ el : $('#resultado'), model : that.model });
        });
    }
});
var clienteView = new ClienteView({el:this.$('#dados'), model: new ClienteModel()});