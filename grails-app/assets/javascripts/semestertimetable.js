$(document).ready(function() {

    // Variables

    // JQueryUI stuff
    $(".semesterModule").draggable({
        revert: "invalid",
        /*helper: "clone",*/
        cursor: "move"
    });

    $(".semesterModules").droppable({
        accept: ".semesterModule",
        classes: {
            "ui-droppable-active": "ui-state-active",
            "ui-droppable-hover": "ui-state-hover"
        },
        drop: function (event, ui) {
            removePreview($(this), ui);
            insertModule($(this), ui);
        },
        over: function (event, ui) {
            previewDrop($(this), ui)
        },
        out: function (event, ui) {
            removePreview($(this), ui);
        }
    });

    $(".moduleList").droppable({
        drop: function (event, ui) {
            removePreview($(this), ui);

            insertModule($(this), ui);
        },
        over: function (event, ui) {
            previewDrop($(this), ui)
        },
        out: function (event, ui) {
            removePreview($(this), ui);
        }
    });

    // Events
    $("#addSemester").on("click", addSemesterTemplate);
    $("#timetable").on("keyup", "#newSemesterName", function (e) {
        if (e.keyCode == 13) {
            var name = $(this).val();
            addSemesterData(name);
            $(this).replaceWith(name);
        }
    });

    // Functions
    function previewDrop($container, ui) {
        var copy = ui.draggable
            .clone()
            .attr("class", "")
            .attr("id", "semesterModuleGhost")
            .attr("style", "");
        copy.appendTo($container);
    }

    function removePreview($container, ui) {
        $container.find('#semesterModuleGhost').remove();
    }

    function insertModule($container, ui) {
        var semesterId = $container.parent().find("form").find("input[name='semester.id']").val();
console.log($container.parent());
        var moduleId = ui.draggable.find("form").find("input[name='module.id']").val();
    console.log(semesterId);
    console.log(moduleId);
        addModuleToSemester(semesterId, moduleId);
        ui.draggable
            .attr("style", "")
            .hide()
            .appendTo($container)
            .end()
            .fadeIn("slow");
    }

    function addSemesterTemplate() {
        var clone = $("#newSemester").clone().removeAttr("id")
        $("#timetable").append(clone);
    }

    function addSemesterData(name) {
        var semester = {
            name: name,
            modules: []
        };

        console.log(semesterData);
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
});