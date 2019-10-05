<!doctype html>
    <head>
    <meta name="layout" content="main"/>
    <title>Welcome to your Semester Timetable</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <h1>Welcome to your Semester Timetable</h1>
        </div>
        <div class="row">
            <span id="credits" class="col-1 align-self-end"><g:message code="credits.label"/></span>
            <div id="timetable" class="col-10">
                <div class="row">
                    <g:each in="${semesterList}" var="semester">
                        <g:render template="/semesterTemplate" model="[semester: semester]"/>
                    </g:each>
                </div>
                <g:render template="/semesterTemplate" model="null"/>
            </div>
            <span id="totalCredits" class="col-1 align-self-end"><g:message code="totalCredits.label"/> ${totalCredits}</span>
        </div>
        <div class="row">
            <h2><g:message code="availableModules.label"/></h2>
            <ul class="moduleList">
                <g:each in="${semesterModuleList}" var="module">
                    <g:render template="/semesterModuleTemplate" model="[module: module]"/>
                </g:each>
            </ul>
        </div>
    </div>
</body>
</html>