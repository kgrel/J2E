#!/bin/sh
mvn clean package && docker build -t pl.fis.wojciech.drzewiecki/lbd .
docker rm -f lbd || true && docker run -d -p 8080:8080 -p 4848:4848 --name lbd pl.fis.wojciech.drzewiecki/lbd 
