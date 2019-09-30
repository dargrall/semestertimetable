<!doctype html>
    <head>
    <meta name="layout" content="main"/>
    <title>Welcome to your Semester Timetable</title>
</head>
<body>
    <h1>Welcome to your Semester Timetable</h1>
    <form id="inputData">
        <input name="" type="hidden"
    </form>
    <div id="timetable">
        <g:each in="${semesterList}" var="semester">
            <g:render template="/semesterTemplate" model="[semester: semester]"/>
        </g:each>
        <g:render template="/semesterTemplate" model="null"/>
    </div>
    <button id="addSemester" type="button" class="btn btn-primary"><i class="fas fa-plus"></i></button>
    <br/>
    <h2><g:message code="availableModules.label"/></h2>
    <ul class="moduleList">
        <g:each in="${semesterModuleList}" var="module">
            <g:render template="/semesterModuleTemplate" model="[module: module]"/>
        </g:each>
    </ul>
</body>
</html>