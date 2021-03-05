<h2>Pentru usurinta se poate adauga executabilul asadmin in path</h2>

export PATH="<LOCATIE_SERVER_GLASSFISH>/bin:$PATH"

<b>asadmin</b> start-domain
<b>asadmin</b> stop-domain

<h2>Deploy pe server:</h2>
<b>asadmin</b> deploy /ear/target/lab2.ear

<h2>Stergere de pe server:</h2>
<b>asadmin</b> undeploy <NUME_APLICATIE>

<h2>Redeploy:</h2>
<b>asadmin</b> redeploy --name lab2 /ear/target/lab2.ear