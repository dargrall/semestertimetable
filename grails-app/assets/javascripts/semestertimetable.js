$(document).ready(function() {

    // Variables
    var semesterData = {semesters: []};

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
        console.log(ui.draggable);
    }

    function removePreview($container, ui) {
        $container.find('#semesterModuleGhost').remove();
    }

    function insertModule($container, ui) {
        ui.draggable.attr("style", "").appendTo($container);
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
        semesterData.semesters.push(semester);
        console.log(semesterData);
    }
});