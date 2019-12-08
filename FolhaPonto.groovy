/**
    Script Groovy que gera os horários certos para preencher folha de ponto.
*/

import java.util.Calendar
import java.util.Random

static String acertarHoraMinuto(int valor) {
    return valor < 10 ? "0" + valor : valor
}

static String acertar12Horas(int valor) {
    return valor == 0 ? "12" : valor
}

static String acertar13Horas(int valor) {
    return valor == 0 ? "12" : "13"
}

static String acertar17Horas(int valor) {
    return valor == 4 ? "16" : "17"
}

public static void main (String... args) {

    for (int i = 1; i <= 31; i++) {
        boolean chegouAntesDas8 = new Random().nextBoolean()

        int minutosChegada = new Random().nextInt(30)

        Calendar horaExpediente = Calendar.getInstance()
        
        int hora = 0
        int minuto = 0

        if (chegouAntesDas8) {
            horaExpediente.set(Calendar.HOUR, 7)
            minutosChegada += 45
        } else {
            horaExpediente.set(Calendar.HOUR, 8)
        }

        horaExpediente.set(Calendar.MINUTE, minutosChegada)

        String folhaPonto = "Dia " + i + " - "

        folhaPonto += "Chegada: " + acertarHoraMinuto(horaExpediente.get(Calendar.HOUR)) +  ":" + acertarHoraMinuto(horaExpediente.get(Calendar.MINUTE)) + " / "

        horaExpediente.add(Calendar.HOUR, 4)

        folhaPonto += "Almoço: " + acertar12Horas(horaExpediente.get(Calendar.HOUR)) +  ":" + acertarHoraMinuto(horaExpediente.get(Calendar.MINUTE)) + " / "

        horaExpediente.add(Calendar.HOUR, 1)

        folhaPonto += "Retorno: " + acertar13Horas(horaExpediente.get(Calendar.HOUR)) +  ":" + acertarHoraMinuto(horaExpediente.get(Calendar.MINUTE)) + " / "

        horaExpediente.add(Calendar.HOUR, 4)

        folhaPonto += "Saída: " + acertar17Horas(horaExpediente.get(Calendar.HOUR)) +  ":" + acertarHoraMinuto(horaExpediente.get(Calendar.MINUTE))

        println folhaPonto
    }
}