<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOC TYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Implementing jsp page</title>
</head>
<body>
 <form:form method="POST" enc-type="multipart/form-data" modelAttribute="fileUploadModel">
        File to Upload: <form:input type="file" path="file"/></br> </br>
        <input type="submit" value="Upload"></br>
        <form:errors path="file" style="color:red;"/>
 </form:form>
</body>
</html>