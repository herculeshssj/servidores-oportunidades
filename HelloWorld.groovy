import groovy.util.XmlSlurper

class HelloWorld {
    def hello() {
        print("Hello World!!! :D")
    }

    def testHtmlParse() {

        def xml = '''
            <html><head><title>201 Created</title></head>
            <body><h1>TGT Created</h1><form action="URI" method="POST">
            Service:<input value="" name="service" type="text"/>
            <br/>
            <input value="Submit" type="submit"/></form></body></html>
            '''
        def html = new XmlSlurper().parseText(xml)
        if (html.body.form.@action == "URI") {
            println "test ok"
        } else {
            println "test fail :("
        }
         

    }
}