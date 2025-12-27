Create an application and removed all data from pom and just make it 
a parent pom file and a docker-compose,.yml file which have all connnecting 
images config and ports etc and same added a yml file in the respective modules

Downloaded docker app and run it 
open terminal and run command : docker compose up -d --build to run docker-compose.yml file
and docker server up and build the component.
docker ps command means check all the ports and status of all the running modules in docker
docker network inspect shopsphere-backend_default command shows all the Connected containers Internal IPs Subnet
docker compose down command to down the compose but volume will be present 




