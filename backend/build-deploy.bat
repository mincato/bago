call %CATALINA_HOME%\bin\shutdown.bat
call mvn clean install
del %CATALINA_HOME%\webapps\bago-archetype.war
rmdir /s /q %CATALINA_HOME%\webapps\bago-archetype
copy target\bago-archetype.war %CATALINA_HOME%\webapps
call %CATALINA_HOME%\bin\catalina.bat jpda start
