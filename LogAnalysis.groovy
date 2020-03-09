/*
    Análise do log de acesso do NGINX configurado como proxy reverso.
*/

import java.util.regex.*
import groovy.sql.Sql

public static void main (String... args) {
    println("Análise do log de acessos do NGINX")

    Map dbConnParams = [
        url: 'jdbc:postgresql://vm-debian-10-docker/nginx',
        user: 'postgres',
        password: 'postgres',
        driver: 'org.postgresql.Driver']

    String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
    String ipv6Pattern = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

    if (args.length == 0) {
        println("Informe o caminho do arquivo!")
        System.exit(0)
    }

    try {
        // Conecta na base de dados
        def sql = Sql.newInstance(dbConnParams)

        try { 
            
            BufferedReader br = new BufferedReader( new FileReader(args[0]) )

            String line = br.readLine()

            //for (int i = 0; i < 100; i++) {
            while (line != null && line.size() != 0) {

                // IP Address
                String ipAddress = line.substring(0, 15)
                ipAddress = ipAddress.trim().replace("-", "")

                // Access On
                String accessOn = line.substring(line.indexOf('[') + 1, line.indexOf(']'))
                accessOn = accessOn.trim()
                accessOn = accessOn.substring(0, accessOn.lastIndexOf('+'))
                accessOn = accessOn.trim()

                // HTTP Method
                String httpMethod = line.substring(line.indexOf('"'), line.indexOf('"') + 5)
                httpMethod = httpMethod.trim()
                httpMethod = httpMethod.replace('"', '')
                httpMethod = httpMethod.trim()

                // Rest of log data
                String logData = line.substring(line.indexOf('"') + 5)
                logData = logData.trim()

                // IP Origin
                String ipOrigin = ""

                Pattern patternIP = Pattern.compile(IPADDRESS_PATTERN)
                Matcher matcherIP = patternIP.matcher(logData)

                if (matcherIP.find()) {
                    ipOrigin = matcherIP.group()
                } else {
                    patternIP = Pattern.compile(ipv6Pattern)
                    matcherIP = patternIP.matcher(logData)

                    if (matcherIP.find()) {
                        ipOrigin = matcherIP.group()
                    }
                }

                String sqlCommand = "insert into nginx_log (ipaddress, access_on, http_method, log_data, ip_origin) values (?, ?, ?, ?, ?)"

                sql.execute(sqlCommand, [ipAddress, accessOn, httpMethod, logData, ipOrigin])

                line = br.readLine()

            }
            
        } catch (Exception e) {
            throw new RuntimeException(e)
        }

        // Encerra a conexão com a base de dados
        sql.close()
    } catch (Exception e) {
        e.printStackTrace()
    }

    

/*
    String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    String DATE_PATTERN = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d"
    Set<String> ipAddresses = new HashSet<>()
    Map<String, Set<String>> statistics = new HashMap<>()

    try { 
        BufferedReader br = new BufferedReader( new FileReader(args[0]) ) 

        StringBuilder sb = new StringBuilder()
        String line = br.readLine()

        int contador = ipAddresses.size()

        for (int i = 0; i < 100; i++) {
        //while (line != null && line.size() != 0) {

            String[] ipOrigin = line.split('/')
            println(ipOrigin[0])
            println(ipOrigin[1])
            println(ipOrigin[2])
            println(ipOrigin[3])

            Pattern patternIP = Pattern.compile(IPADDRESS_PATTERN)
            Matcher matcherIP = patternIP.matcher(ipOrigin[0])

            //Pattern patternDATE = Pattern.compile(DATE_PATTERN)
            //Matcher matcherDATE = patternDATE.matcher(line)

            // Verifica se preciso incluir uma nova chave no Map
            //if (matcherDATE.find()) {
                //if (statistics.containsKey(matcherDATE.group())) {
                    // O dia encontra-se presente no Map, então adiciono o IP no value
                    if (matcherIP.find()) {
                        //statistics.get(matcherDATE.group()).add(matcherIP.group())
                        ipAddresses.add(matcherIP.group())
                        
                        if (ipAddresses.size() > contador) {
                            println("Incluído o IP " + matcherIP.group() + " no Set")
                            contador++
                        }
                    }
                //} else {
                  //  statistics.put(matcherDATE.group(), new HashSet())
                    //if (matcherIP.find()) {
                      //  statistics.get(matcherDATE.group()).add(matcherIP.group())
                    //}
                    //println("Incluído dia " + matcherDATE.group() + " no Map")
                //}
            //}

            line = br.readLine()
        }

    } catch (Exception e) {
        e.printStackTrace()
    }

    println("Lista de IPs de origem recuperados.")
    println(ipAddresses)
*/
}