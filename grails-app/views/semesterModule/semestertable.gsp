<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'semesterModule.label', default: 'SemesterModule')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <asset:javascript src="jquery-ui.min.js"/>
        <!-- Drag and drop -->
        <asset:javascript src="semestertimetable.js"/>
        <asset:stylesheet src="jquery-ui.min.css"/>

    </head>
    <body>
        <a href="#list-semesterModule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-semesterModule" class="content scaffold-list" role="main">
            <h1><g:message code="semestertimetable.label"/></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div id="semestertimetable" class="container">
                <div id="semesters" class="row">
                    <div class="col">
                        <span class="semesterTitle">Semester 1</span>
                    </div>
                    <div class="col">
                        <span class="semesterTitle">Semester 2</span>
                    </div>
                    <div class="col">
                        <span class="semesterTitle">Semester 3</span>
                    </div>
                    <div class="col">
                        <span class="semesterTitle">Semester 4</span>
                    </div>
                    <div class="col">
                        <span class="semesterTitle">Semester 5</span>
                    </div>
                    <div class="col">
                        <span class="semesterTitle">Semester 6</span>
                    </div>
                </div>
                <div id="semesterModules" class="row">
                    <g:each var="semesterModule" in="${semesterModuleList}">
                        <div class="semesterModule col bg-primary ui-state-default">
                            <span class="title">${semesterModule.name}</span>
                            <span class="credits">${semesterModule.credits}</span>
                        </div>
                    </g:each>
                </div>
            </div>

        </div>
    </body>
</html>
