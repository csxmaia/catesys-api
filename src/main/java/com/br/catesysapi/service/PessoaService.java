package com.br.catesysapi.service;

import com.br.catesysapi.entity.Pessoa;
import com.br.catesysapi.exception.ValidationException;
import com.br.catesysapi.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaService {
    final PessoaRepository pessoaRepository;

    public void validarNovaPessoa(String email, String cpf) {
        Optional<Pessoa> pessoaByEmail = pessoaRepository.findPessoaByEmail(email);
        if(pessoaByEmail.isPresent()) {
            throw new ValidationException("Email já utilizado");
        }

        Optional<Pessoa> pessoaByCpf = pessoaRepository.findPessoaByCpf(cpf);
        if(pessoaByCpf.isPresent()) {
            throw new ValidationException("CPF já utilizado");
        }
    }
}
