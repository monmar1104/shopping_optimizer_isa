<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="what" var="WDYWlanguage" scope="session"/>

<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="all.mainName" bundle="${WDYWlanguage}"/></title>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/fontello/css/fontello.css" type="text/css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
            integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
            crossorigin="anonymous"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id"
          content="830889560506-bfd7gjnpsj4o3ss6n24f2pis4dscu4u9.apps.googleusercontent.com">
</head>
<body class="bg-dark">
<div class="container">
    <%@include file="header.jsp" %>
    <%@include file="links.jsp" %>
    <div>
        <div class="row mt-3 pl-2 pr-2 pt-3 border border-secondary">
            <div>
                <div class="row">
                    <div class="col-12 justify-content-center"><h3><fmt:message key="adminPanel.adminPanel"
                                                                                bundle="${WDYWlanguage}"/></h3></div>
                </div>
                <div class="row">
                    <a href="add-question" class="col-lg-5 justify-content-center link-button mx-auto">
                        <fmt:message key="adminPanel.addQuestion" bundle="${WDYWlanguage}"/> </a>
                    <a href="show-all-question" class="col-lg-5 justify-content-center link-button mx-auto">
                        <fmt:message key="adminPanel.deleteOrEditQuestion" bundle="${WDYWlanguage}"/>
                    </a>
                    <a href="add-category-promo" class="col-lg-5 justify-content-center link-button mx-auto">
                        <fmt:message key="adminPanel.addCatToPromotion" bundle="${WDYWlanguage}"/>
                    </a>
                    <a class="col-lg-5 justify-content-center link-button mx-auto" href="remove-category-promo">
                        <fmt:message key="adminPanel.delPromoCategory" bundle="${WDYWlanguage}"/>
                    </a>
                    <a class="col-lg-5 justify-content-center link-button mx-auto" href="add-user">
                        <fmt:message key="adminPanel.addUser" bundle="${WDYWlanguage}"/>
                    </a>
                    <a class="col-lg-5 justify-content-center link-button mx-auto" href="reports">
                        <fmt:message key="adminPanel.reports" bundle="${WDYWlanguage}"/>
                    </a>
                </div>
            </div>
        </div>
        <div class="row m-0">
            <span class="mx-auto p-2">&#169 infoShare Academy</span>
        </div>
    </div>
</div>
</body>
</html>