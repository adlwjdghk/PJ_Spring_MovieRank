<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Path 설정 : 경로를 알려줌 Spring Project ohyoyo의 정보를 path에 집어넣음-->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- favicon -->

<!-- font awesome -->
<script src="https://kit.fontawesome.com/e2a7455480.js" crossorigin="anonymous"></script>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- favicon -->
<link rel="icon" type="image/png" sizes="16x16" href="${path}/resources/img/favicon-16x16.png">