<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <asset:stylesheet src="jquery-ui.css" rel="stylesheet"/>
    <asset:javascript src="jquery-ui-1.12.1/external/jquery/jquery.js"/>
    <asset:javascript src="jquery-ui-1.12.1/jquery-ui.js"/>

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-primary bg-primary">
    <g:link class="navbar-brand" controller="semester" action="timetable"><g:message code="semestertimetable.label"/></g:link>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="semesterModuleDropdown" role="button" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false">
                    <g:message code="semestermodule.label"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="semesterModuleDropdown">
                    <g:link controller="semesterModule" action="create" class="dropdown-item"><g:message code="semestermodule.create.label"/></g:link>
                    <div class="dropdown-divider"></div>
                    <g:link controller="semesterModule" class="dropdown-item"><g:message code="semestermodule.list.label"/></g:link>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="semesterDropdown" role="button" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false">
                    <g:message code="semester.label"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="semesterDropdown">
                    <g:link controller="semester" action="create" class="dropdown-item"><g:message code="semester.create.label"/></g:link>
                    <div class="dropdown-divider"></div>
                    <g:link controller="semester" class="dropdown-item"><g:message code="semester.list.label"/></g:link>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

<g:layoutBody/>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
