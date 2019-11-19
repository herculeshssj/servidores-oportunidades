class Oportunidade {
    String titulo
    String descricao
    String link

    String toString() {
        return this.titulo + " - " + this.descricao + "\n\n" + this.link
    }
}