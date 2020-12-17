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
        br()
        div("Concursos encontrados: ") 
        div ("$concursosEncontrados")

        br()
        br()
        div ("Copyright &copy; herculeshssj")
    }
}