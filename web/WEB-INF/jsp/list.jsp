<%@ page import="java.util.List" %>
<%@ page import="ru.basejava.webapp.model.Resume" %>
<%@ page import="ru.basejava.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html">
    <link rel="stylesheet" href="./css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="inc/header.jsp"/>
<div class="container" style="margin:70px auto;">
    <div class="row justify-content-center">
        <div class="col-12">
            <table class="table table-hover">
                <thead class="thead-dark">
                <tr>
                    <th style="width: 40%;">Имя</th>
                    <th style="width: 40%;">Email</th>
                    <th>Управление</th>
                </tr>
                </thead>
                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="ru.basejava.webapp.model.Resume"/>
                    <tr>
                        <td class="align-middle"><a href="resume?action=view&uuid=${resume.uuid}">${resume.fullName}</a></td>
                        <td class="align-middle">${resume.getContact(ContactType.EMAIL)}</td>
                        <td>
                            <a href="resume?uuid=${resume.uuid}&action=edit" class="btn btn-success" role="button">изменить</a>
                            <a href="resume?uuid=${resume.uuid}&action=delete" class="btn btn-danger" role="button">удалить</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"/>
</body>
</html>
