class Oportunidade {
    Long id
    String titulo
    String descricao
    String uf
    String periodoInscricao
    String link
    boolean enviado
    String hash

    public Oportunidade() {
        enviado = false;
    }

    String toString() {
        return this.titulo 
            + " - " 
            + this.descricao 
            + "\n\r" 
            + this.uf
            + " - "
            + this.periodoInscricao
            + "\n\r"
            + this.link
    }
}