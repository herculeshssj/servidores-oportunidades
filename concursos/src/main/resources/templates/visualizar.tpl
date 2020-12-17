yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("Concurso - Visualizar")
        link(rel: "stylesheet", type: "text/css", href: "/css/style.css")
    }
    body {
        h1("Concurso - Visualizar")
        a(href: "/", "Voltar a listagem")
        br()
        br()
        div {
            table(border: "0") {
                tr {
                    td("Titulo")
                    td(":")
                    td(concurso.titulo ?: '')
                }
                tr {
                    td("Descricao")
                    td(":")
                    td(concurso.descricao ?: '')
                }
                tr {
                    td("Link")
                    td(":")
                    td {
                        a(href: "$concurso.link", concurso.link)
                    }
                }
                tr {
                    td("UF")
                    td(":")
                    td(concurso.uf ?: '')
                }
                tr {
                    td("Cargos")
                    td(":")
                    td(concurso.cargos ?: '')
                }
                tr {
                    td("Salario")
                    td(":")
                    td(concurso.salario ?: '')
                }
                tr {
                    td("Nivel de Escolaridade")
                    td(":")
                    td(concurso.nivelEscolaridade ?: '')
                }
                tr {
                    td("Vagas")
                    td(":")
                    td(concurso.vagas ?: '')
                }
                tr {
                    td("Vagas, cargos e salarios")
                    td(":")
                    td(concurso.vagasCargosSalarios ?: '')
                }
                tr {
                    td("Periodo de Inscricao")
                    td(":")
                    td(concurso.periodoInscricao ?: '')
                }
                tr {
                    td("Data de Termino da Inscricao")
                    td(":")
                    td(concurso.dataTerminoInscricao ?: '')
                }
            }
        }
        br()
        br()
        if (errorMessage!=null) {
            div(class: "error", "$errorMessage")
        }
    }
}