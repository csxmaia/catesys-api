package com.br.catesysapi.service;

import com.br.catesysapi.domain.entity.Pessoa;
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
        if(email != null) {
            Optional<Pessoa> pessoaByEmail = pessoaRepository.findPessoaByEmailAndAtivoTrue(email);
            if(pessoaByEmail.isPresent()) {
                throw new ValidationException("Email já utilizado");
            }
        }

        if(cpf != null) {
            Optional<Pessoa> pessoaByCpf = pessoaRepository.findPessoaByCpfAndAtivoTrue(cpf);
            if(pessoaByCpf.isPresent()) {
                throw new ValidationException("CPF já utilizado");
            }
        }
    }
}
