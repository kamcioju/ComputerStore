/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author qjot
 */
public class CurrentContent {    
    public static AnchorPane currentContent = new AnchorPane();   
    public static int type;
     //Type, AppStart -> Type = 1, set Conent to MainWindow, and then set Type to other number.
    public static SimpleBooleanProperty button1=new SimpleBooleanProperty(true);
    public static SimpleBooleanProperty button2=new SimpleBooleanProperty(true);
    public static SimpleBooleanProperty button3=new SimpleBooleanProperty(true);
    public static SimpleBooleanProperty button4=new SimpleBooleanProperty(true);
    public static SimpleBooleanProperty button5=new SimpleBooleanProperty(true);
    public static SimpleBooleanProperty button6=new SimpleBooleanProperty(true);
}
