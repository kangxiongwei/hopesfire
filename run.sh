#!/usr/bin/env bash

# 部署项目的脚本
mvn clean package -Dmaven.test.skip=true
cp ./hopesfire-web/target/*.jar ./hopesfire-web.jar
java -jar ./hopesfire-web.jar
