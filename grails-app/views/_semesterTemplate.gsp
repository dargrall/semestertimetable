<g:set var="semesterType" value="${(!semester) ? 'newSemester' : ''}"/>
<div class="semesterBody card" id="${semesterType}">
    <div class="semesterHeader card-header">
        <g:if test="${semester}">
            ${semester.name}
        </g:if>
        <g:else>
            <input id="newSemesterName" type="text" value="${message(code: "newSemester.label")}"/>
        </g:else>
    </div>
    <ul class="semesterModules list-group list-group-flush">
    </ul>
</div>