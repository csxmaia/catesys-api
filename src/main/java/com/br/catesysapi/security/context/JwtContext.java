package com.br.catesysapi.security.context;

import com.br.catesysapi.domain.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtContext {
    private UsuarioContext usuario;

    public JwtContext generate(Usuario usuario) {
        UsuarioContext usuarioContext = new UsuarioContext();
        usuarioContext.setId(usuario.getId());
        usuarioContext.setEmail(usuario.getPessoa().getEmail());
        usuarioContext.setNome(usuario.getPessoa().getNome());
        usuarioContext.setRoles(usuario.getStringRoles());
        this.usuario = usuarioContext;
        return this;
    }
}
