#!/bin/bash
cd /tmp
git clone https://github.com/herculeshssj/servidores-oportunidades
cd /tmp/servidores-oportunidades
docker run --name servidores-oportunidades --rm --link mongodb-container -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy Main.groovy
rm -rf /tmp/servidores-oportunidades