#! /bin/sh

APP_NAME="shop-service"
APP_HOME="/home/shop/$APP_NAME"
RUN_COMMAND="java -Xms512m -Xmx512m -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 org.shop.main.ServiceRunMain"
APP_LOG_HOME="/home/shop/logs"


################################
##    [$APP_NAME]服务不可同时运行2个
################################


echo "[INFO]: 服务[$APP_NAME]正在初始化...";
v_count=`ps -ef |grep "$RUN_COMMAND" |grep -v "grep"|wc -l`
if [ $v_count -eq 0 ]; then
	echo "[INFO]: 服务[$APP_NAME]初始化完毕...";
else
    echo "[ERROR]: 已有服务[$APP_NAME]在运行，无法再次启动...";
    echo `ps -ef | grep "$RUN_COMMAND" | grep -v 'grep'`
    exit 0
fi

################################
##    提交命令
################################
echo "[INFO]: 服务[$APP_NAME]正在启动,时间：" `date +%Y%m%d-%H:%M:%S`
CLASS_PATH=$APP_HOME/bin:$(echo $APP_HOME/lib/*.jar|sed 's/ /:/g')
export CLASSPATH=$CLASS_PATH:$CLASSPATH
echo "CLASSPATH=$CLASSPATH"
echo "RUN_COMMAND=$RUN_COMMAND"
nohup $RUN_COMMAND 1>/dev/null 2>$APP_LOG_HOME/shell_${APP_NAME}_err.log &

echo "[INFO]: 命令已提交，服务[$APP_NAME]正在启动..."

################################
##    判断[$APP_NAME]服务是否已启动
################################
count=`ps -ef | grep "$RUN_COMMAND" | grep -v 'grep'|wc -l`
if [ $count -eq 0 ]; then
	echo "[ERROR]: 服务[$APP_NAME]启动失败...";
elif [ $count -gt 1 ]; then
    echo "[ERROR]: 服务[$APP_NAME]启动进程数多于1个...";
    exit 0
fi

echo "[INFO]: 服务[$APP_NAME]已启动,时间：" `date +%Y%m%d-%H:%M:%S`
echo "查看输出日志： $APP_LOG_HOME"