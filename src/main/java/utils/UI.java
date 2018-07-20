package utils;

import com.jme3.app.SimpleApplication;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.math.Vector2f;
import tonegod.gui.controls.buttons.Button;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;


public class UI {
    private Screen screen;

    public UI(SimpleApplication simpleApplication){
        screen = new Screen(simpleApplication , "tonegod/gui/style/def/style_map.gui.xml");
        screen.setUseCustomCursors(true);
        screen.setUseUIAudio(true);
        // Добавить окно
        Button add_cube = new Button(screen,  "Btn1", new Vector2f(255, 5) ) {
            @Override
            public void onButtonMouseLeftDown(MouseButtonEvent mouseButtonEvent, boolean b) {
                //Должно сформироваться событие что надо создать куб
            }

            @Override
            public void onButtonMouseRightDown(MouseButtonEvent mouseButtonEvent, boolean b) {

            }

            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent mouseButtonEvent, boolean b) {

            }

            @Override
            public void onButtonMouseRightUp(MouseButtonEvent mouseButtonEvent, boolean b) {

            }

            @Override
            public void onButtonFocus(MouseMotionEvent mouseMotionEvent) {

            }

            @Override
            public void onButtonLostFocus(MouseMotionEvent mouseMotionEvent) {

            }
        };

        Button add_model = new Button(screen,  "Btn2", new Vector2f(500, 5) ) {
            @Override
            public void onButtonMouseLeftDown(MouseButtonEvent mouseButtonEvent, boolean b) {

            }

            @Override
            public void onButtonMouseRightDown(MouseButtonEvent mouseButtonEvent, boolean b) {

            }

            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent mouseButtonEvent, boolean b) {

            }

            @Override
            public void onButtonMouseRightUp(MouseButtonEvent mouseButtonEvent, boolean b) {

            }

            @Override
            public void onButtonFocus(MouseMotionEvent mouseMotionEvent) {

            }

            @Override
            public void onButtonLostFocus(MouseMotionEvent mouseMotionEvent) {

            }
        };

//        Window win = new Window(screen, "win", new Vector2f(15, 15));
//
//        // Создать кнопку и добавить в окно
//        ButtonAdapter makeWindow = new ButtonAdapter( screen,  "Btn1", new Vector2f(15, 55) ) {
//            @Override
//            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
//                createNewWindow(" New Window" + winCount);
//            }
//        };
//        makeWindow.setText("New Window");
//        win.setWindowTitle("OLOLO");
//        win.setUseCloseButton(true);
//        // Добавим её в наше начальное окно
//        win.addChild(makeWindow);

        // Добавить окно на экран
        //screen.addElement(win);
        screen.addElement(add_cube);
        screen.addElement(add_model);

    }

    public Screen getScreen(){
        return screen;
    }
}
