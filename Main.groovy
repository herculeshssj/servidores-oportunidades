import OportunidadeService
import Oportunidade

public static void main(String... args) {

    OportunidadeService service = new OportunidadeService()

    service.buscarOportunidade()

    for (Oportunidade oportunidade : service.listaOportunidade) {
        println oportunidade.toString()
    }
    
}