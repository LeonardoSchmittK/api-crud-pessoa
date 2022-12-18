package com.futurodev.controllers;

import com.futurodev.model.Pessoa;
import com.futurodev.repository.PessoaRepository;
import com.futurodev.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;


    @PostMapping(value="/",produces = "application/json")
    public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa){
        Pessoa pes = pessoaRepository.save(pessoa);
        return new ResponseEntity<Pessoa>(pes, HttpStatus.CREATED);
    }

    @GetMapping(value="/{idPessoa}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable(value="idPessoa") Long idPessoa){
        Pessoa pes = pessoaRepository.findById(idPessoa).get();
        return new ResponseEntity<Pessoa>(pes,HttpStatus.OK);
    }

    @DeleteMapping(value="/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idPessoa){
        pessoaRepository.deleteById(idPessoa);
        return new ResponseEntity<String>("Contato deletado com sucesso!",HttpStatus.OK);
    }

    @PutMapping(value="/",produces = "application/json")
    public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa contato){
        Pessoa pes = pessoaRepository.save(contato);
        return new ResponseEntity<Pessoa>(pes,HttpStatus.OK);
    }

    @GetMapping(value="/buscarPorNome", produces = "application/json")
    public ResponseEntity<List<Pessoa>> getContatoById(@RequestParam(name="nome") String nome){
        List<Pessoa> pessoas=       pessoaRepository.getPessoaByName(nome);
        return new ResponseEntity<List<Pessoa>>(pessoas,HttpStatus.OK);
    }


}
