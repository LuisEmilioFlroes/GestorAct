package gt.edu.umg.taskmanager.service;

import gt.edu.umg.taskmanager.util.Action;
import org.springframework.stereotype.Component;
import java.util.Stack;

@Component
public class PilaOperaciones {
    private Stack<Action> actionStack;

    public PilaOperaciones() {
        this.actionStack = new Stack<>();
    }

    public void pushAction(Action action) {
        actionStack.push(action);
        System.out.println("Acción registrada: " + action);
    }

    public Action popAction() {
        if (!actionStack.isEmpty()) {
            return actionStack.pop();
        }
        return null;
    }

    public void printActions() {
        if (actionStack.isEmpty()) {
            System.out.println("No hay acciones registradas.");
        } else {
            System.out.println("Historial de acciones:");
            for (Action action : actionStack) {
                System.out.println(action);
            }
        }
    }
}
