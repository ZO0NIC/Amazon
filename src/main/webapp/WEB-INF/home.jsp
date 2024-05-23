<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUP</title>
</head>
<body>
<p>The Best Website EVER</p> 

<form action="searchProduct" method="post">
		<label for="tag">SEARCH PRODUCT:</label> <input type="text" id="tag"
			name="tag" required><br> <input type="submit"
			value="Submit">
	</form>


<button onclick="redirectToLogin()">Login Page</button>

<script>
  function redirectToLogin() {
    window.location.href = 'login';
  }
  </script>
  
  <br> <p></p>
  
<button onclick="redirectToRegistration()">Registration Page</button>

<script>
  function redirectToRegistration() {
    window.location.href = 'registration';
  }
  </script>
  
  <br> <p></p>
  
<button onclick="redirectToAdmin()">Admin Page</button>

<script>
  function redirectToAdmin() {
    window.location.href = 'admin';
  }
  </script>
  
  <br> <p></p>
  
  <button onclick="redirectToSearch()">Search Page</button>

  <script>
    function redirectToSearch() {
      window.location.href = 'search';
    }
</script>
</body>
</html>