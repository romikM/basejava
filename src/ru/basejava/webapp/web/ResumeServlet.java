package ru.basejava.webapp.web;

import ru.basejava.webapp.Config;
import ru.basejava.webapp.model.*;
import ru.basejava.webapp.storage.Storage;
import ru.basejava.webapp.utils.DateUtil;
import ru.basejava.webapp.utils.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResumeServlet extends HttpServlet {
    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        final boolean createNew = (Objects.equals(uuid, "") || uuid == null);
        Resume r;
        if (createNew) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.addFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.strIsEmpty(value)) {
                r.getContacts().remove(type);
            } else {
                r.addContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.strIsEmpty(value) && values.length < 2) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.addSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        r.addSection(type, new ListSection(value.split("\\n")));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Organization> orgs = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!HtmlUtil.strIsEmpty(name)) {
                                List<Organization.CareerStage> stages = new ArrayList<>();
                                String pfx = type.name() + i;
                                String[] datesFrom = request.getParameterValues(pfx + "dateFrom");
                                String[] datesTo = request.getParameterValues(pfx + "dateTo");
                                String[] descriptions = request.getParameterValues(pfx + "description");
                                for (int j = 0; j < descriptions.length; j++) {
                                    if (!HtmlUtil.strIsEmpty(descriptions[j])) {
                                        stages.add(new Organization.CareerStage(DateUtil.parse(datesFrom[j]), DateUtil.parse(datesTo[j]), descriptions[j]));
                                    }
                                }
                                orgs.add(new Organization(name, urls[i], stages));
                            }
                        }
                        r.addSection(type, new OrganizationSection(orgs));
                        break;
                }
            }
        }
        if (createNew) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "add":
                r = Resume.EMPTY;
                break;
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType type : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION}) {
                    OrganizationSection section = (OrganizationSection) r.getSection(type);
                    List<Organization> emptyFirstOrganizations = new ArrayList<>();
                    emptyFirstOrganizations.add(Organization.EMPTY);
                    if (section != null) {
                        for (Organization org : section.getOrganizations()) {
                            List<Organization.CareerStage> emptyStages = new ArrayList<>();
                            emptyStages.add(Organization.CareerStage.EMPTY);
                            emptyStages.addAll(org.getStages());
                            emptyFirstOrganizations.add(new Organization(org.getTitle(), org.getUrl(), emptyStages));
                        }
                    }
                    r.addSection(type, new OrganizationSection(emptyFirstOrganizations));
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
