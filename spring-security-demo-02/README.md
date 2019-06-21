<h3>Development Process</h3>
1. Add Maven dependencies ->
	<li>Spring mvc-web,spring security,servlet,jsp,jstl,mysql,c3p0 and maven war plugin</li>
2. Create database for authorities.
3. Create Spring App Configuration and Security Configuration<br>
4. Create Spring Dispatcher Servlet Initializer and Security Web App Initializer<br>
5. Develop Spring Controllers<br>
6. Develop JSP view pages<br>

<hr>

This demo same as demo-01. Differences are the authorities user and password provided by database, also password stores as bcrypt.<br>

<hr>

<b>Sign in page:</b></br>

![](images/loginpage.png)

<b>Invalid username or password :</b></br>

![](images/invalid.png)

<b>Employee role's home :</b></br>

![](images/employeehome.png)

<b>After log out :</b></br>

![](images/logout.png)

<b>Manager role's home :</b></br>

![](images/managerhome.png)

<b>Only manager role's access page :</b></br>

![](images/onlymanager.png)

<b>Admin role's home :</b></br>

![](images/adminhome.png)

<b>Only admin role's access page :</b></br>

![](images/onlyadmin.png)

<b>Access Denied Page :</b></br>

![](images/accessdenied.png)
