FROM tomcat

RUN apt update && apt upgrade -y && apt install -y ant

RUN mkdir /app

COPY ./ /app

RUN ant -f /app/oportunidade/build.xml deployWar

RUN mv /workspace.dist/oportunidade.dist/oportunidade.war /usr/local/tomcat/webapps && rm -rf /workspace.dist

EXPOSE 8080

CMD ["catalina.sh", "run"]
