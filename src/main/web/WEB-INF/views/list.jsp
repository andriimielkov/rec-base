
<!DOCTYPE html>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
<c:forEach items="${list}" var="item">
    ${item}<br>
</c:forEach>
</body>