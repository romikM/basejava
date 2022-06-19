<%@ page import="ru.basejava.webapp.model.ContactType" %>
<%@ page import="ru.basejava.webapp.model.ListSection" %>
<%@ page import="ru.basejava.webapp.model.OrganizationSection" %>
<%@ page import="ru.basejava.webapp.model.SectionType" %>
<%@ page import="ru.basejava.webapp.utils.DateUtil" %>
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
    <jsp:useBean id="resume" type="ru.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="inc/header.jsp"/>
<div class="container" style="margin:70px auto;">
    <div class="row justify-content-center">
        <div class="col-12">
            <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="uuid" value="${resume.uuid}">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="fullName"><h3>Имя</h3></label>
                        <input type="text" class="form-control" required="true" id="fullName" name="fullName"
                               value="${resume.fullName}">
                    </div>
                </div>

                <h3>Контактные данные</h3>
                <c:forEach var="type" items="<%=ContactType.values()%>">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="${type.name()}">${type.title}</label>
                            <input type="text" class="form-control" id="${type.name()}" name="${type.name()}" size=30
                                   value="${resume.getContact(type)}">
                        </div>
                    </div>
                </c:forEach>
                <hr/>
                <c:forEach var="type" items="<%=SectionType.values()%>">
                    <c:set var="section" value="${resume.getSection(type)}"/>
                    <jsp:useBean id="section" type="ru.basejava.webapp.model.AbstractSection"/>
                    <div class="form-row">
                        <div class="form-group col-md-10">
                            <label for="${type.name()}"><h3>${type.title}</h3></label>
                            <c:choose>
                                <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                                    <textarea class="form-control" name="${type}" rows="6"><%=section%></textarea>
                                </c:when>
                                <c:when test="${type=='QUALIFICATION' || type=='ACHIEVEMENT'}">
                                <textarea name="${type}"
                                          class="form-control"
                                          rows="6"><%=String.join("\n", ((ListSection) section).getContent())%></textarea>
                                </c:when>
                                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>" varStatus="counter">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="${type}">Название учереждения</label>
                                                <input type="text" class="form-control" name="${type}" id="${type}" value="${org.title}">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="${type}url">Сайт учереждения</label>
                                                <input type="text" class="form-control" name="${type}url" id="${type}url" value="${org.url}">
                                            </div>
                                        </div>
                                        <c:forEach var="stage" items="${org.stages}">
                                            <jsp:useBean id="stage" type="ru.basejava.webapp.model.Organization.CareerStage"/>
                                            <div class="form-row">
                                                <div class="form-group col-md-3">
                                                    <label for="${type}${counter.index}dateFrom">С какой даты</label>
                                                    <input type="text" class="form-control" name="${type}${counter.index}dateFrom" value="<%=DateUtil.format(stage.getdateFrom())%>" placeholder="MM/yyyy">
                                                </div>
                                                <div class="form-group col-md-3">
                                                    <label for="${type}${counter.index}dateTo">По какую дату</label>
                                                    <input type="text" class="form-control" name="${type}${counter.index}dateTo" value="<%=DateUtil.format(stage.getdateTo())%>" placeholder="MM/yyyy">
                                                </div>
                                                <div class="form-group col-md-10">
                                                    <label for="${type}${counter.index}description">Описание</label>
                                                    <textarea class="form-control" name="${type}${counter.index}description" rows="8">${stage.description}</textarea>
                                                </div>
                                            </div>
                                            <hr />
                                        </c:forEach>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>

                <button type="submit" class="btn btn-success">Сохранить</button>
                <button type="reset" class="btn btn-danger">Отменить</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"/>
</body>
</html>