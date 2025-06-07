package gt.edu.umg.taskmanager.service;

import gt.edu.umg.taskmanager.util.Action;

import java.util.Stack;

public class ServicioPilaApp {

    private Stack<Action> actionStack;

    public ServicioPilaApp() {
        this.actionStack = new Stack<>();
    }

    public void pushAction(Action action) {
        actionStack.push(action);
    }

    public Action popAction() {
        if (!actionStack.isEmpty()) {
            return actionStack.pop();
        }
        return null;
    }

    public boolean isEmpty() {
        return actionStack.isEmpty();
    }
}
