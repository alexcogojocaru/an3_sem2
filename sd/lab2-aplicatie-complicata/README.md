<h2>Pentru usurinta se poate adauga executabilul asadmin in path</h2>

export PATH="<LOCATIE_SERVER_GLASSFISH>/bin:$PATH"

asadmin start-domain
asadmin stop-domain

<h2>Deploy pe server:</h2>
asadmin deploy /ear/target/lab2.ear

<h2>Stergere de pe server:</h2>
asadmin undeploy <NUME_APLICATIE>

<h2>Redeploy:</h2>
asadmin redeploy --name lab2 /ear/target/lab2.ear