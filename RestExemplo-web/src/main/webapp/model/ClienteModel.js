var ClienteModel = Backbone.Model.extend({

    urlRoot : 'http://localhost:8080/RestExemplo-web/rest/cliente/inserirCliente',

    defaults : {
        nome: '',
        sobrenome: '',
        idade : 1
    },
    
});

var ClienteModelCollection = Backbone.Collection.extend({
    model: ClienteModel    
});