var ClienteModel = Backbone.Model.extend({

    urlRoot : 'rest/cliente',

    defaults : {
        nome: '',
        sobrenome: '',
        idade : 1
    },
    
});

var ClienteModelCollection = Backbone.Collection.extend({
    model: ClienteModel,
    url: 'rest/cliente'
});