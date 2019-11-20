@Grab(group='org.mongodb', module='mongodb-driver', version='3.11.2')
@Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2')

import OportunidadeService
import Oportunidade

public static void main(String... args) {

    // Inicializa o serviço
    OportunidadeService service = new OportunidadeService()

    // Busca as oportunidades
    service.buscarOportunidades()

    // Envia a notificação das novas oportunidades
    service.notificarOportunidades()
}