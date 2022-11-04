package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senac.entity.Endereco;
import br.com.senac.repository.EnderecoRepository;

public class EnderecoService {
	@Autowired
	EnderecoRepository repo;
	public List<Endereco> selectAll(){
		return repo.findAll();
	}
	
	public Endereco insert(Endereco endereco) {
		return repo.save(endereco);
	}
	
	public Endereco select(Integer id) {
		return repo.findById(id).get();
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Endereco update(Endereco enderecoAlterado) {
		Endereco endereco = select(enderecoAlterado.getId());
		endereco.setBairro(enderecoAlterado.getBairro());
		endereco.setCep(enderecoAlterado.getCep());
		endereco.setComplemento(enderecoAlterado.getComplemento());
		endereco.setNumero(enderecoAlterado.getNumero());
		endereco.setRua(enderecoAlterado.getRua());
		return insert(endereco);
	}
}
