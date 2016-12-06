var ClienteListView = Backbone.View.extend({

    initialize: function initialize() {
        var that = this;
        $(this.el).unbind();
        $.get('templates/ClienteListView.html', function (data) {
            that.template = _.template(data);
            //that.$el.html(that.template);
        }, 'html');
        that.render();
        that.posRender();
    },

    render: function render() {
        this.$el.html(this.template);
        return this;
    },

    posRender: function posRender() {
        this.listar();
    },

    listar: function listar() {
        var listClientes = new ClienteModelCollection();
        listClientes.fetch();
        //this.model.set('listClientes', listClientes);

        this.tblListCompras = $('#tblresultado').dataTable({
            'bPaginate': true,
            'bDestroy': true,
            'bFilter': true,
            'bDestroy': true,
            'oLanguage': {
                'sLengthMenu': '_MENU_ Registros por página',
                'sZeroRecords': 'Nenhum registro correspondente ao critério informado',
                'sInfoFiltered': '(Filtrados de _MAX_ registros)',
                'sInfoEmtpy': 'Exibindo 0 a 0 de 0 registros',
                'sSearch': 'Pesquisar',
                'oPaginate': {
                    'sFirst': 'Primeiro',
                    'sLast': 'Último'
                }
            }
        });
    }



});