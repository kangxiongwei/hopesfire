#!/usr/bin/env bash

# 部署项目的脚本
PROJECT_NAME='hopesfire'

##
# 发布前准备
##
function beforeDeploy() {
    cd /opt/apps
    if [[ -d ${PROJECT_NAME} ]]; then
        echo "项目已存在，开始更新代码..."
        cd ${PROJECT_NAME}
        git pull
    else
        echo "项目不存在，开始克隆代码..."
        git clone git@github.com:kangxiongwei/hopesfire.git
    fi
    echo "项目代码更新完毕，开始进行部署..."
}

function stopRunningApp() {
    PID=$(jps -l | grep "$PROJECT_NAME" | awk '{print $1}')
    if [[ $? -eq 0 ]]; then
        echo "${PROJECT_NAME}进程号为${PID}"
    else
        echo "${PROJECT_NAME}进程号不存在！"
        exit
    fi
    echo "开始关闭${PROJECT_NAME}的进程${PID}..."
    kill -9 ${PID}
    echo "进程${PID}已停止..."
}

##
# 执行发布命令
##
function doDeploy() {
    cd /opt/apps/${PROJECT_NAME}
    mvn clean package -Dmaven.test.skip=true
    echo "Maven打包项目完成，开始拷贝文件并部署..."
    cp ./hopesfire-web/target/*.jar ./${PROJECT_NAME}.jar
    nohup java -jar ${PROJECT_NAME}.jar -Dspring.profiles.active=prod > jetty.log  2>&1  &
    echo "部署成功，请检查..."
}

beforeDeploy
stopRunningApp
doDeploy
