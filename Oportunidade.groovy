class Oportunidade {
    Long id
    String titulo
    String descricao
    String uf
    String periodoInscricao
    String link
    boolean enviado

    public Oportunidade() {
        enviado = false;
    }

    String toString() {
        return this.titulo + " - " + this.descricao + "\n\r" + this.link
    }
}