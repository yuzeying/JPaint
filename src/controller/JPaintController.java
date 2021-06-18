package controller;

import model.Shape;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    public String state = "";

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setState(String state) {
        this.state = state;
    }

    private void setupEvents() {
        attachObserver(new RedoObserver(this));
        attachObserver(new UndoObserver(this));
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState::setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, applicationState::setActiveStartAndEndPointMode);
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand().run());
        uiModule.addEvent(EventName.PASTE, () -> {Shape.undoRedoStack.push(); new PasteCommand().run();});
        uiModule.addEvent(EventName.DELETE, () -> {Shape.undoRedoStack.push(); new DeleteCommand().run();});
        uiModule.addEvent(EventName.GROUP, () -> {
            Shape.undoRedoStack.push();
            List<Shape> groupShapes = new ArrayList<>(Shape.selectedShapes);
            Shape.shapes.removeAll(groupShapes);
            Shape.selectedShapes.clear();
            Shape group = new Group(groupShapes);
            Shape.shapes.add(group);
            Shape.selectedShapes.add(group);
            MouseHandler.draw();
        });
        uiModule.addEvent(EventName.UNGROUP, () -> {
            Shape.undoRedoStack.push();
            List<Shape> removeShapes = new ArrayList<>();
            for (Shape shape : Shape.selectedShapes) {
                if (shape instanceof Group) {
                    List<Shape> groupShapes = ((Group) shape).groupShapes;
                    Shape.shapes.addAll(groupShapes);
                    removeShapes.add(shape);
                }
            }
            Shape.selectedShapes.removeAll(removeShapes);
            Shape.shapes.removeAll(removeShapes);
            MouseHandler.draw();
        });
        uiModule.addEvent(EventName.UNDO, () -> {
            setState("UNDO");
            notifyObserver();
        });
        uiModule.addEvent(EventName.REDO, () -> {
            setState("REDO");
            notifyObserver();
        });
    }

    private List<Observer> observers = new ArrayList<>();
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
 

abstract class Observer {
    protected JPaintController controller;
    public abstract void update();

    public Observer(JPaintController controller) {
        this.controller = controller;
    }
}

class UndoObserver extends Observer {

    UndoObserver(JPaintController controller) {
        super(controller);
    }

    @Override
    public void update() {
        if (this.controller.state.equals("UNDO")) {
            Shape.undoRedoStack.pop();
        }
    }
}

class RedoObserver extends Observer{

    RedoObserver(JPaintController controller) {
        super(controller);
    }

    @Override
    public void update() {
        if (this.controller.state.equals("REDO")) {
            Shape.undoRedoStack.unpop();
        }
    }
}




