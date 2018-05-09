#! /bin/sh

RUN_COMMAND="java -Xms512m -Xmx512m -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 org.shop.main.ServiceRunMain"

pids=`ps -ef | grep "$RUN_COMMAND"  | grep -v "grep" |awk '{print $2}'`
if [ "$pids" = "" ]; then
  echo "[INFO]: service has stoped before !"
else
  for pid in ${pids}; do
    kill -9 $pid 1>dev/null 2>$1
    echo "[INFO] : service [pid=$pid] has stoped !"
  done
fi