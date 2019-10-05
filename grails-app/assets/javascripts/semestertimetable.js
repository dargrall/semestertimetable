$(document).ready(function() {

    // Variables

    // JQueryUI stuff
    $(".semesterModule").draggable({
        revert: "invalid",
        /*helper: "clone",*/
        cursor: "move",
        start: function(event, ui) {
            if ($(this).parent().hasClass("moduleList")) {
                $(this).addClass("availableModule");
            } else {
                $(this).removeClass("availableModule");
            }
        }
    });

    $(".semesterModules").droppable({
        accept: function(dropElem) {
            if ($(this).find(dropElem).length == 0 && dropElem.hasClass("semesterModule")) {
                return true;
            }
            return false;
        },
        classes: {
            "ui-droppable-active": "ui-state-active",
            "ui-droppable-hover": "ui-state-hover"
        },
        drop: function (event, ui) {
            removePreview($(this), ui);
            insertModule($(this), ui);
            if (!ui.draggable.hasClass("availableModule")) {
                var semesterId = ui.draggable.closest(".semesterBody").find("form").find("input[name='semester.id']").val();
                var moduleId = ui.draggable.find("form").find("input[name='module.id']").val();
console.log(semesterId, moduleId);
                removeModuleFromSemester(semesterId, moduleId);
            }
        },
        over: function (event, ui) {
            previewDrop($(this), ui)
        },
        out: function (event, ui) {
            removePreview($(this), ui);
        }
    });

    $(".moduleList").droppable({
        accept: function(dropElem) {
            if ($(this).find(dropElem).length == 0 && dropElem.hasClass("semesterModule")) {
                return true;
            }
            return false;
        },
        drop: function (event, ui) {
            removePreview($(this), ui);
            removeModule($(this), ui, ui.draggable.parent());
        },
        over: function (event, ui) {
            previewDrop($(this), ui)
        },
        out: function (event, ui) {
            removePreview($(this), ui);
        }
    });

    // Events

    // Functions
    function previewDrop($container, ui) {
        var copy = ui.draggable
            .clone()
            .attr("class", "")
            .attr("id", "semesterModuleGhost")
            .attr("style", "");
        copy.appendTo($container);
    }

    function insertModule($container, ui) {
        var semesterId = $container.parent().find("form").find("input[name='semester.id']").val();
        var moduleId = ui.draggable.find("form").find("input[name='module.id']").val();
        addModuleToSemester(semesterId, moduleId);
        ui.draggable
            .attr("style", "")
            .hide()
            .appendTo($container)
            .end()
            .fadeIn("slow");
    }

    function addModuleToSemester(semesterId, moduleId) {
        var data = {
            semesterId: semesterId,
            moduleId: moduleId
        }
        $.ajax({
            url: "addModule",
            type: "POST",
            data: data,
            success: function(data) {
                // Do some fancy toast or shit like that
                console.log(data.response);
            },
            error: function(xhr, status, error) {
                console.log(status);
                console.log(error);
            }
        });
    }

    function removePreview($container, ui) {
        $container.find('#semesterModuleGhost').remove();
    }

    function removeModule($container, ui, $previousParent) {
        var semesterId = $previousParent.parent(".semesterBody").find("form").find("input[name='semester.id']").val();
        var moduleId = ui.draggable.find("form").find("input[name='module.id']").val();
        removeModuleFromSemester(semesterId, moduleId);
        ui.draggable
            .attr("style", "")
            .hide()
            .appendTo($container)
            .end()
            .fadeIn("slow");
    }

    function removeModuleFromSemester(semesterId, moduleId) {
        var data = {
            semesterId: semesterId,
            moduleId: moduleId
        }
        $.ajax({
            url: "removeModule",
            type: "POST",
            data: data,
            success: function(data) {
                // Do some fancy toast or shit like that
                console.log(data.response);
            },
            error: function(xhr, status, error) {
                console.log(status);
                console.log(error);
            }
        });
    }
});