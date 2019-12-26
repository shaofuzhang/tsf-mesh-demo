#!/bin/bash

already_run=`ps -ef|grep "tsf-user-demo-1.0-SNAPSHOT"|grep -v grep|wc -l`
if [ ${already_run} -ne 0 ];then
	echo "userService already Running!!!! Stop it first"
	exit -1
fi

nohup java -jar tsf-user-demo-1.0-SNAPSHOT.jar 1>stdout.log 2>&1 &
