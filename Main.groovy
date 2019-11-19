import Oportunidades
import Oportunidade

static void main(String... args) {
    
    Oportunidades oportunidades = new Oportunidades().buscarOportunidades()

    for (Oportunidade op : oportunidades.listaOportunidade) {
        println op.toString()
    }
}