package com.BB.LetsCode.ClienteAPIRest.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.BB.LetsCode.ClienteAPIRest.model.Cliente;
import com.BB.LetsCode.ClienteAPIRest.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrarCliente (@RequestBody Cliente  cliente){
        return clienteRepository.save(cliente);
    }

    @DeleteMapping (value = "/{id}")
    public String apagaRegistro(@PathVariable ("id") Long id) {
        clienteRepository.deleteById(id);
        String mensagem = "Registro apagado com sucesso";
        return mensagem;
    }

    @PutMapping (value = "/{id}")
    public Cliente atualizaRegistro (@PathVariable ("id") Long id
    , @RequestBody Cliente cliente){
       Cliente clienteAtual = clienteRepository.findById(id).get();
       BeanUtils.copyProperties(cliente, clienteAtual,"id");
       return clienteRepository.save(clienteAtual);

    }
    
}