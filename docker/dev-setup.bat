echo off
echo "start of script"

REM ##########################################################
REM #                       Postgres                         #
REM ##########################################################
echo "start execute of compose for postgres..."
echo %cd%\postgres
cd %cd%\postgres
docker-compose up -d
echo "setup for postgres was executed!"

echo "end of script"
pause
