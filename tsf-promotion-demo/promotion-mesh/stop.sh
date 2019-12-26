#!/bin/bash

pid=`ps -ef|grep "tsf-promotion-demo-1.0-SNAPSHOT"|grep -v grep|awk '{print $2}'`
kill -SIGTERM $pid
echo "process ${pid} killed"
