<g:set var="semesterType" value="${(!semester) ? 'newSemester' : ''}"/>
<div class="semesterBody card" id="${semesterType}">
    <div class="semesterHeader card-header">
        <g:if test="${semester}">
            <form name="semesterData">
                <input name="id" type="hidden" value="${semester?.id}"/>
                <input name="name" type="hidden" value="${semester?.name}"/>
                %{--<input name="id" type="hidden" value="${semester.id}"/>--}%
            </form>
            ${semester.name}
        </g:if>
        <g:else>
            <input id="newSemesterName" type="text" value="${message(code: "newSemester.label")}"/>
        </g:else>
    </div>
    <ul class="semesterModules list-group list-group-flush">
        <g:each in="${semester?.modules}">
            <g:render template="../semesterModuleTemplate" model="[module: it]"/>
        </g:each>
    </ul>
</div>