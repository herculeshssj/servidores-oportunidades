package br.com.hslife.oportunidade.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.Tab;
import org.openxava.model.Identifiable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="selecoes")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tab(properties = "titulo, descricao, link, arquivado")
public class Oportunidade extends Identifiable {
    
    @Column(columnDefinition = "text", nullable = false)
    @ReadOnly
    String titulo;

    @Column(columnDefinition = "text", nullable = false)
    @ReadOnly
    @Stereotype("MEMO")
    String descricao;

    @Column(columnDefinition = "text", nullable = false)
    @ReadOnly
    @Stereotype("WEBURL")
    String link;

    @Column
    @ReadOnly
    boolean enviado;

    @Column(nullable = false)
    @ReadOnly
    String hash;

    @Column
    @ReadOnly
    @Stereotype("DATETIME")
    LocalDate dataCadastro;

    @Column
    @ReadOnly
    boolean arquivado;

    public String toString() {
        return this.titulo + "\n\r" + this.descricao + "\n\r" + this.link;
    }
}
