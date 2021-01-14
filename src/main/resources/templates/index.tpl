yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("$title")
        link(rel: "stylesheet", type: "text/css", href: "/css/style.css")
    }
    body {
        h1 ("$title")

        div {
            a(href:"/logout", "Sair")
        }

        br()

        div {
            form (id: "searchForm", action:"/buscar", method:"GET") {
                table(border: "0") {
                    tr {
                        td("Titulo")
                        td {
                            input(name: 'buscaTitulo', type: 'text', value: buscaTitulo ?: '')
                        }
                        td("Descricao")
                        td {
                            input(name: 'buscaDescricao', type: 'text', value: buscaDescricao ?: '')
                        }
                        td("UF")
                        td {
                            select (id: 'buscaUf', name: 'buscaUf', value: buscaUf) {
                                estados.each { estado ->
                                    option("$estado")
                                }
                            }
                        }
                        td {
                            input(type: 'submit', value: 'Buscar')
                        }
                        td {
                            input(type: 'reset', value: 'Limpar')
                        }
                    }
                }
            }
        }

        br()
        div {
            table(border: "1") {
                tr {
                    th("Titulo")
                    th("Descricao")
                    th("UF")
                }
                listConcurso.each { concurso ->
                    tr {
                        td {
                            a(href:"/$concurso.id", "$concurso.titulo")
                        }
                        td("$concurso.descricao")
                        td(concurso.uf ?: '')
                    }
                }
            }
        }

        br()
        div("Concursos encontrados: $concursosEncontrados")

        br()
        div ("Copyright &copy; herculeshssj")
    }
}