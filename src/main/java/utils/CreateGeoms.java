package utils;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author vik24rus
 */
public class CreateGeoms {  //TODO класс создания коробки ощий для сервера и клиента
    //вероятно в будущем для сервера нужен класс без материала ИЛИ доп параметр для того что бы понимать запрос на создание объекта пришел от сервера или от клиента
    //сервер должен знать только геометрию и имя
    //например:
    //сервер знает координаты, название , геометрию, хар-ки
    //отправляет эти данные клиентам, те уже подставляют под название нужную модельку с материалами
    private Material m;
    private Box b;
    private Sphere sss;

    public CreateGeoms(SimpleApplication simpleApplication){
        m = new Material (simpleApplication.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        b = new Box(Vector3f.ZERO, new Vector3f(1,1,1));
        //sss = new Sphere(48, 42 , 2);
    }

    public Geometry createBox(){
        Geometry box = new Geometry ("box", b);
        //Geometry s = new Geometry ("sphere", sss);
        box.setMaterial(m);
        return box;
    }


}