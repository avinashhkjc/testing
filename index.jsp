<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <!-- <applet code="org.me.hello.MyApplet" archive="HelloApplet.jar" width="600" height="480"> -->
<applet name="SmartCardOTP" code="org.me.hello.SmartCardOTP" archive="SmartCardOTP.jar" width="600" height="480"> 
</applet>
<form>
 <input type="button" value="Call Applet" onclick="callApplet();"/>
  <input type="text"  id="txt3"/>
 </form>
 </form>
</body>
<script type="text/javascript">
 
        function callApplet() {
            var currentTime = new Date();
            var time = currentTime.toISOString();
            alert('testing11');
            // invoke drawText() method of the applet and pass time string
            // as argument:
 
          alert('testing otp values:::'+document.SmartCardOTP.getOTP()) ;
          document.getElementById('txt3').value = document.SmartCardOTP.getOTP();
 
        }
    </script>
</html>