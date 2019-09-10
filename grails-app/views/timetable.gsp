<!doctype html>
    <head>
    <meta name="layout" content="main"/>
    <title>Welcome to your Semester Timetable</title>
</head>
<body>
    <h1>Welcome to your Semester Timetable</h1>
    <g:each in="${semesterList}" var="semester">
        <g:render template="/semesterTemplate" model="[semester: semester]"/>
    </g:each>
    <br/>
    <h2><g:message code="availableModules.label"/></h2>
    <ul class="moduleList">
        <g:each in="${semesterModuleList}" var="module">
            <g:render template="/semesterModuleTemplate" model="[module: module]"/>
        </g:each>
    </ul>
</body>
</html>