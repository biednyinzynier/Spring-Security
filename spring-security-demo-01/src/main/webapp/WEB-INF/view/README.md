<b>custom-login.jsp</b> --> This page will load first, this is custom login form page, must be sign in with username and password<br><br>
home.jsp --> This page will occur after success login, everyone can access this page but there is some secret contents and only related roles can see.<br><br>
leaders.jsp --> Only "MANAGER" roles can access this page, if the others trying to access they will encounter with access denied page<br><br>
systems.jsp --> Only "ADMIN" roles can access this page, if the others trying to access they will encounter with access denied page<br><br>
access-denied.jsp --> If the unauthorized role try to access the page, it will shown.
