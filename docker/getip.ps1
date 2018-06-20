$ip = docker inspect --format '{{ .NetworkSettings.IPAddress }}' httpserver-java

echo $ip;

#docker run oliechti/felix 172.17.0.2:8080 yosra.harbaoui@heig-vd.ch