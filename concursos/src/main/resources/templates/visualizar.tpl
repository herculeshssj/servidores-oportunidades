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
                    td("Id")
                    td(":")
                    td(concurso.id ?: '')
                }
                tr {
                    td("Título")
                    td(":")
                    td(concurso.titulo ?: '')
                }
                tr {
                    td("Descrição")
                    td(":")
                    td(concurso.descricao ?: '')
                }
                tr {
                    td("UF")
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
                    td("Hash")
                    td(":")
                    td(concurso.hash ?: '')
                }
                tr {
                    td("Data de Cadastro")
                    td(":")
                    td(concurso.dataCadastro ?: '')
                }
                tr {
                    td("Cargos")
                    td(":")
                    td(concurso.cargos ?: '')
                }
                tr {
                    td("Salário")
                    td(":")
                    td(concurso.salario ?: '')
                }
                tr {
                    td("Nível de Escolaridade")
                    td(":")
                    td(concurso.nivelEscolaridade ?: '')
                }
                tr {
                    td("Vagas")
                    td(":")
                    td(concurso.vagas ?: '')
                }
                tr {
                    td("Vagas, cargos e salários")
                    td(":")
                    td(concurso.vagasCargosSalarios ?: '')
                }
                tr {
                    td("Período de Inscrição")
                    td(":")
                    td(concurso.periodoInscricao ?: '')
                }
                tr {
                    td("Data de Término da Inscrição")
                    td(":")
                    td(concurso.dataTerminoInscricao ?: '')
                }
            }
        }
        br()
        br()
        if (allowDelete) {
            form (id:"deleteForm", action:"/$concurso.id/delete", method:"POST") {
                yield 'Excluir esse concurso? '
                input(type: 'submit', value: 'Yes')
            }
        }
        else {
            div {
                a(href: "/$concurso.id/edit", "Edit")
                yield ' | '
                a(href: "/$concurso.id/delete", "Delete")
            }
        }
        if (errorMessage!=null) {
            div(class: "error", "$errorMessage")
        }
    }
}