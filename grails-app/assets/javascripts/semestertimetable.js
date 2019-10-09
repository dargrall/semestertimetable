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
                removeModuleFromSemester(semesterId, moduleId);
            }
            calculateCredits();
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
            var semBody = ui.draggable.parent();
            removePreview($(this), ui);
            removeModule($(this), ui, semBody);
            calculateCredits();
        },
        over: function (event, ui) {
            previewDrop($(this), ui)
        },
        out: function (event, ui) {
            removePreview($(this), ui);
        }
    });

    // Events

    $('#addSemester').click(addSemester);

    // Functions

    function addSemester() {
        $.ajax({
            url: "addSemester",
            type: "POST",
            success: function() {
                displayNewSemester();
            },
            error: function(xhr, status, error) {
                console.log(status);
                console.log(error);
            }
        });
    }

    function displayNewSemester() {
        var semesterCard = $("#timetable").find("#newSemester").clone();
        semesterCard.removeAttr("id");
        $('#timetable').append(semesterCard);
        location.reload();
    }

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
        };
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

    function calculateCredits() {
        var semCredits = 0;
        var totalCredits = 0;
        $(".semesterBody").each(function() {
            semCredits = 0;
            $(this).find("input[name='module.credits']").each(function() {
                semCredits += parseInt($(this).val());
            });
            $(this).find(".semesterCredits").text(semCredits);
            totalCredits += semCredits;
        });
        $("#totalCredits").text("Total: " + totalCredits);
console.log(totalCredits);
    }
});