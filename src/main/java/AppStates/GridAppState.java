package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Line;
import utils.Grid;

public class GridAppState extends BaseAppState {

    private SimpleApplication app;
    private int gridY = 10 , gridX = 10;
    private Grid grid;
    private Spatial[][] cells;
    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) app;
        grid = new Grid(gridY , gridX, this.app);
        cells = new Spatial[gridY][gridX];
        cells = grid.getGrid();

        //TODO рисование линий вместо модельки ячейки
        Line line = new Line(new Vector3f(-20, 0, -20), new Vector3f(40, 0, 0));
        line.setLineWidth(2);
        Geometry geometry = new Geometry("Bullet", line);
        Material orange = new Material(this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        orange.setColor("Color", ColorRGBA.Blue);
        geometry.setMaterial(orange);
        this.app.getRootNode().attachChild(geometry);
        //Технически безопасно выполнять всю инициализацию и очистку в методах
        //onEnable()/onDisable(). Выбор использовать initialize() и
        //cleanup() для этого, это вопрос специфики производительности для
        //разработчика.
        //TODO: Инициализация AppState, например, присоединение spatials к rootNode
    }

    @Override
    protected void cleanup(Application app) {
        //TODO: очистить то, что вы инициализировали в методе initialize,
        //например, удалить все spatials из rootNode
    }

    //onEnable()/onDisable() может использоваться для управления вещами, которые должны
    //существовать только при включенном state. Основным примером были
    //прикрепленный граф сцены или прикрепленный слушатель ввода.
    @Override
    protected void onEnable() {
        //Вызывается, когда state полностью включено, то есть: установлено и
        //isEnabled() является истинным или когда статус setEnabled() изменяется после
        //прикрепления state.
        for (int i = 0; i < 10; i++) //_columnCount
        {
            for (int j = 0; j < 10; j++) //_rowCount
            {
                this.app.getRootNode().attachChild(cells[i][j]);
            }
        }

    }

    @Override
    protected void onDisable() {
        //Вызывается, когда state было ранее включено, но теперь отключено
        //либо потому, что вызывается setEnabled (false), либо состояние
        //очищается.
        for (int i = 0; i < 10; i++) //_columnCount
        {
            for (int j = 0; j < 10; j++) //_rowCount
            {
                this.app.getRootNode().detachChild(cells[i][j]);
            }
        }

    }

    @Override
    public void update(float tpf) {
        //TODO: реализовать поведение во время выполнения
    }

}
