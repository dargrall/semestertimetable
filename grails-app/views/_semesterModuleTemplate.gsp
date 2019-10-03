<li class="semesterModule">
    <form name="semesterData">
        <input name="id" type="hidden" value="${module?.id}"/>
        <input name="name" type="hidden" value="${module?.name}"/>
        <input name="credits" type="hidden" value="${module?.credits}"/>
        %{--<input name="id" type="hidden" value="${semester.id}"/>--}%
    </form>
    <div class="content">
        <div class="header">${module?.name}</div>
        <div class="credits">${module?.credits} LP</div>
    </div>
</li>