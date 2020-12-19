class Oportunidade {
    Long id
    String titulo
    String descricao
    String uf
    String periodoInscricao
    String link
    boolean enviado
    String hash
    Date dataCadastro
    boolean arquivado

    public Oportunidade() {
        enviado = false
        dataCadastro = new Date()
        arquivado = false
    }

    String toString() {
        return this.titulo + "\n\r" + this.descricao + "\n\r" + this.link
    }
}