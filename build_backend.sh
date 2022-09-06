./gradlew :backend:buildFatJar &&
docker build -t oradore-backend . &&
docker save -o oradore-backend.tar oradore-backend &&
scp oradore-backend.tar dobrynin@advp44.gm.fh-koeln.de:/home/dobrynin/oradore/oradore/ &&
rm oradore-backend.tar
