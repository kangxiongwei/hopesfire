#!/usr/bin/env bash
# 部署项目的脚本
PROJECT_NAME='hopesfire'
mvn clean package -Dmaven.test.skip=true
cp ./hopesfire-web/target/*.jar ./${PROJECT_NAME}.jar