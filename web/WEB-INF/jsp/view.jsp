<%@ page import="ru.basejava.webapp.model.ListSection" %>
<%@ page import="ru.basejava.webapp.model.OrganizationSection" %>
<%@ page import="ru.basejava.webapp.model.TextSection" %>
<%@ page import="ru.basejava.webapp.utils.HtmlUtil" %>
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

            <h1>${resume.fullName}</h1>

            <c:forEach var="contactEntry" items="${resume.contacts}">
                <jsp:useBean id="contactEntry"
                             type="java.util.Map.Entry<ru.basejava.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
            </c:forEach>

            <hr />

            <c:forEach var="sectionEntry" items="${resume.sections}">
                <jsp:useBean id="sectionEntry"
                             type="java.util.Map.Entry<ru.basejava.webapp.model.SectionType, ru.basejava.webapp.model.AbstractSection>"/>
                <c:set var="type" value="${sectionEntry.key}"/>
                <c:set var="section" value="${sectionEntry.value}"/>
                <jsp:useBean id="section" type="ru.basejava.webapp.model.AbstractSection"/>
                <h3>${type.title}</h3>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE'}">
                        <p><%=((TextSection) section).getContent()%></p>
                    </c:when>
                    <c:when test="${type=='PERSONAL'}">
                        <p><%=((TextSection) section).getContent()%></p>
                    </c:when>
                    <c:when test="${type=='QUALIFICATION' || type=='ACHIEVEMENT'}">
                        <ul>
                            <c:forEach var="item" items="<%=((ListSection) section).getContent()%>">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
                            <c:choose>
                                <c:when test="${empty org.url}">
                                    <h6>${org.title}</h6>
                                </c:when>
                                <c:otherwise>
                                    <h6><a href="${org.url}">${org.title}</a></h6>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach var="stage" items="${org.stages}">
                                <p>
                                <jsp:useBean id="stage" type="ru.basejava.webapp.model.Organization.CareerStage"/>
                                <strong><%=HtmlUtil.formatDates(stage)%></strong><br>
                                ${stage.description}
                                </p>
                            </c:forEach>
                            <hr>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:forEach>
            <br/>
            <button onclick="window.history.go(-1)" class="btn btn-primary">к списку резюме</button>
            <a href="resume?uuid=${resume.uuid}&action=edit" class="btn btn-success" role="button">изменить</a>
            <a href="resume?uuid=${resume.uuid}&action=delete" class="btn btn-danger" role="button">удалить</a>
        </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"/>
</body>
</html>
