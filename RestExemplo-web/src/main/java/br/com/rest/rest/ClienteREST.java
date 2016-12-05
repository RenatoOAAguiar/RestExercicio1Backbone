package br.com.rest.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.rest.data.ClienteRepositorio;
import br.com.rest.model.Cliente;
import br.com.rest.service.ClienteRegistro;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/cliente")
@RequestScoped
public class ClienteREST {

	@Inject
	private ClienteRepositorio clienteRepositorio;

	@Inject
	ClienteRegistro clienteRegistro;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listarTodos() {
		return clienteRepositorio.findAllOrderedByName();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente lookupMemberById(@PathParam("id") long id) {
		Cliente cliente = clienteRepositorio.findById(id);
		if (cliente == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return cliente;
	}

	@POST
	@Path("/inserirCliente")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void inserirCliente(Cliente cliente) {
		clienteRepositorio.insereCliente(cliente);
	}

	@DELETE
	@Path("/removerCliente")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void removerCliente(Cliente cliente) {
		clienteRepositorio.removerCliente(cliente);
	}
}
