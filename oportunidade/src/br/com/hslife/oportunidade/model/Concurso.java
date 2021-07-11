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
@Table(name="concursos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tab(properties = "titulo, descricao, uf, arquivado")
public class Concurso extends Identifiable {
    
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

    @Column(length = 2, nullable = false)
    @ReadOnly
    String uf;

    @Column(nullable = false)
    @ReadOnly
    String hash;

    @Column
    @ReadOnly
    LocalDate dataCadastro;

    @Column(columnDefinition = "text")
    @ReadOnly
    String cargos;

    @Column
    @ReadOnly
    Double salario;

    @Column(columnDefinition = "text")
    @ReadOnly
    String nivelEscolaridade;

    @Column
    @ReadOnly
    Integer vagas;

    @Column(columnDefinition = "text")
    @ReadOnly
    String vagasCargosSalarios;

    @Column
    @ReadOnly
    String periodoInscricao;

    @Column
    @ReadOnly
    LocalDate dataTerminoInscricao;

    @Column
    @ReadOnly
    Boolean arquivado;

    public String toString() {
        return this.titulo + "\n\r" + this.descricao + "\n\r" + "UF: " + this.uf + "\n\r" + this.link + "\n\r" + this.vagasCargosSalarios + "\n\r" + this.periodoInscricao;
    }
}
