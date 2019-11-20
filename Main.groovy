@Grab('org.mongodb:mongodb-driver:3.11.2')
@Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2')

import OportunidadeService
import Oportunidade

public static void main(String... args) {

    OportunidadeService service = new OportunidadeService()

    //service.buscarOportunidade()

    service.salvarOportunidades()

    //for (Oportunidade oportunidade : service.listaOportunidade) {
    //    println oportunidade.toString()
    //}
    
}