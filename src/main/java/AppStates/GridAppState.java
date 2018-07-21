package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import utils.CreateGeoms;
import utils.Grid;

import java.util.logging.Logger;

public class GridAppState extends AbstractAppState {

    private SimpleApplication app;
    private final Logger log = Logger.getLogger(String.valueOf(GridAppState.class.getClass()));
    private int gridY , gridX;
    private Grid grid;
    private Spatial[][] cells;
    //private Node x = new Node("x");  // Некоторые поля пользовательских классов...
    //public Node getX(){ return x; }  // Некоторые пользовательские методы...

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;          // переход к более конкретному классу
        gridY = 10;
        gridX = 10;
        grid = new Grid(gridY , gridX, this.app);
        cells = new Spatial[gridY][gridX];
        cells = grid.getGrid();
        for (int i = 0; i < 10; i++) //_columnCount
        {
            for (int j = 0; j < 10; j++) //_rowCount
            {
                this.app.getRootNode().attachChild(cells[i][j]);
            }
        }
        // инициализация, не зависящая от состояния: ПАУЗА или ВЫПОЛНЯЕТСЯ
        //this.app.getRootNode().attachChild(getX()); // изменение графа сцены...
        //this.app.doSomething();                     // вызов пользовательских методов...
    }

    @Override
    public void cleanup() {
        super.cleanup();
        // Отменить регистрацию всех моих слушателей, отсоединить все мои узлы и.т.д.
        //this.app.getRootNode().detachChild(getX()); // изменение графа сцены...
        //this.app.doSomethingElse();                 // вызов пользовательских методов...
    }

    @Override
    public void setEnabled(boolean enabled) {
        // Пауза и возобновление
        super.setEnabled(enabled);
        if(enabled){

            // инициализация, которая используется, пока состояние ВЫПОЛНЯЕТСЯ
            //this.app.getRootNode().attachChild(getX()); // изменение графа сцены...
            //this.app.doSomethingElse();                 // вызов пользовательских методов...
        } else {
            // Убрать все, что не нужно, пока состояние ПАУЗА
        //...
        }
    }

    // Обратите внимание, что обновление вызывается только тогда, когда состояние подключено и включено.
    @Override
    public void update(float tpf) {
        // В то время как игра ВЫПОЛНЯЕТСЯ
        //this.app.getRootNode().getChild("blah").scale(tpf); // изменение графа сцены...
        //x.setUserData(...);                                 // вызов пользовательских методов...
    }

}