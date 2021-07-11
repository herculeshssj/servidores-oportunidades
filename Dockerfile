FROM tomcat

RUN apt update && apt upgrade -y && apt install -y ant

RUN mkdir /app

COPY ./ /app

RUN cp /app/naviox-users.properties /app/oportunidade/web/WEB-INF/classes

RUN ant -f /app/oportunidade/build.xml compile createWar

RUN mv /app/oportunidade.dist/oportunidade.war /usr/local/tomcat/webapps && rm -rf /app

EXPOSE 8080

CMD ["catalina.sh", "run"]
