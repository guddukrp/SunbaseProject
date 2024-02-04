<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    

    <title>Login Page</title>
    <style>
		*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);
    background-size: cover;
    background-position: center;
}
.wrapper{
    width: 420px;
    background: rgba(0,0,0,0.4);
    border: 2px solid rgba(255, 255, 255 ,.2);
    backdrop-filter: blur(20px);
    box-shadow: 0, 0,10px rgba(0,0,0,.2);
    color: white;
    border-radius: 10px;
    padding: 30px 40px;
}
.wrapper h1{
    font-size: 36px;
    text-align: center;
}
.wrapper .input-box{
    position: relative;
    width: 100%;
    height: 50px;
    margin: 30px 0;
}
.input-box input{
    width: 100%;
    height: 100%;
    background: transparent;
    border: none;
    outline: none;
    border: 2px solid rgba(255, 255, 255 ,.2);
    border-radius: 40px;
    color: white;
    font-size: 16px;
    padding: 20px 45px 20px 20px;
}
.input-box input::placeholder{
    color: #ffffff;
} 
    .input-box i{
    position: absolute;
    right: 20px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 20px;
} 


.wrapper .btn{
    width: 100%;
    height: 45px;
    background:#fff;
    border: none;
    outline: none;
    border-radius: 40px;
    box-shadow: 0,0,10px rgba(0,0,0,.1);
    cursor: pointer;
    font-size: 16px;
    color: #333;
    font-weight: 600;
}


	</style>
</head>
<body>
    
    <div class="wrapper">
        <form action="login" method="post">
            <h1>Login</h1>
            <%
			    String error = request.getParameter("error");
			    if (error != null && !error.isEmpty()) {
			%>
			        <h4 style="color: red"><%= error %></h4>
			<%
			    }
			%>
            <div class="input-box">
                <input type="text" name="username" placeholder="Login Id" required>
                <i class='bx bxs-user' ></i>
                
            </div>
            <div class="input-box">
                <input type="password" name="password" placeholder="password" required>
                <i class='bx bxs-lock' ></i>
            </div>
            
            <div>
                <button type="submit" class="btn">Login</button>
            </div>
             
        </form>

    </div>
</body>
</html>
