#!/usr/bin/env bash

# 部署项目的脚本
PROJECT_NAME='hopesfire'

##
# 发布前准备
##
function beforeDeploy() {
    if [[ -d ${PROJECT_NAME} ]]; then
        echo "项目已存在，开始更新代码..."
        cd ${PROJECT_NAME}
        git pull
    else
        echo "项目不存在，开始克隆代码..."
        git clone git@github.com:kangxiongwei/hopesfire.git
    fi
}

##
# 执行发布命令
##
function doDeploy() {
    cd /opt/apps/${PROJECT_NAME}
    mvn clean package -Dmaven.test.skip=true
    echo "Maven打包项目完成，开始拷贝文件并部署..."
    cp ./hopesfire-web/target/*.jar ./hopesfire-web.jar
    java -jar ./hopesfire-web.jar
    echo "部署成功，请检查..."
}

beforeDeploy
doDeploy
