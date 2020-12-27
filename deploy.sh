#!/usr/bin/env bash

# 部署项目的脚本
PROJECT_NAME='hopesfire'

nohup java -jar ${PROJECT_NAME}.jar -Dspring.profiles.active=prod >jetty.log 2>&1 &
echo "部署成功，请检查..."
