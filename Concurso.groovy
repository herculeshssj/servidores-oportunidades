class Concurso {
    Long id
    String titulo
    String descricao
    String link
    String uf
    String hash
    Date dataCadastro
    String cargos
    Double salario
    String nivelEscolaridade
    Integer vagas
    String vagasCargosSalarios
    String periodoInscricao
    Date dataTerminoInscricao

    public Concurso() {
        dataCadastro = new Date()
    }

    String toString() {
        return this.titulo + "\n\r" + this.descricao + "\n\rUF: " + this.uf + "\n\r" + this.link
    }
}