$(document).ready(function() {
    $('.semesterModule').draggable({
        revert: "invalid",
        /*helper: "clone",*/
        cursor: "move"
    });
    $('.semesterModules').droppable( {
        accept: ".semesterModule",
        classes: {
            "ui-droppable-active": "ui-state-active",
            "ui-droppable-hover": "ui-state-hover"
        },
        drop: function(event, ui) {
            removePreview($(this), ui);

            insertModule($(this), ui);
        },
        over: function(event, ui) {
            previewDrop($(this), ui)
        },
        out: function (event, ui) {
            removePreview($(this), ui);
        }
    });

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
});
