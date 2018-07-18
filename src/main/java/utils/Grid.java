package utils;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;


public class Grid {
    private Material m;
    private Spatial[][] cells;
    private int iG , jG;

    public Grid(int iG , int jG, SimpleApplication simpleApplication){
        m = new Material (simpleApplication.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        this.iG = iG;
        this.jG = jG;
        cells = new Spatial[iG][jG];
        for (int i = 0; i < iG; i++)
        {
            for (int j = 0; j < jG; j++)
            {
                cells[i][j] = simpleApplication.getAssetManager().loadModel("Models/cellframe.j3o");
                cells[i][j].setMaterial(m);
                cells[i][j].setLocalTranslation(i, 0, j);
            }
        }
    }

    public Spatial[][] getGrid(){
        return cells;
    }
}