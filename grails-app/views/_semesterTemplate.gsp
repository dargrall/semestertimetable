<g:set var="semesterType" value="${(!semester) ? 'newSemester' : ''}"/>
<div class="semesterBody card col" id="${semesterType}">
    <div class="semesterHeader card-header">
        <g:if test="${semester}">
            <form name="semesterData">
                <input name="semester.id" type="hidden" value="${semester?.id}"/>
                %{--<input name="id" type="hidden" value="${semester.id}"/>--}%
            </form>
            ${semester.id}. <g:message code="semester.label"/>
        </g:if>
        <g:else>
            <g:message code="semester.add.label"/>
        </g:else>
    </div>
    <ul class="semesterModules list-group list-group-flush">
        <g:each in="${semester?.modules}">
            <g:render template="../semesterModuleTemplate" model="[module: it]"/>
        </g:each>
    </ul>
    <span class="semesterCredits">${semester?.credits}</span>
</div>