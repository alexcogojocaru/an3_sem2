Pentru usurinta se poate adauga executabilul asadmin in path

export PATH="<LOCATIE_SERVER_GLASSFISH>/bin:$PATH"

asadmin start-domain
asadmin stop-domain

Deploy pe server:
asadmin deploy /ear/target/lab2.ear

Stergere de pe server:
asadmin undeploy <NUME_APLICATIE>

Redeploy:
asadmin redeploy --name lab2 /ear/target/lab2.ear