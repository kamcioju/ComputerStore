/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import es.upv.inf.*;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author qjot
 */
public class PC {
    
    private Product motherboard;
    private Product cpu;
    private Product computerCase;
    private List<Product> ramList = new ArrayList<Product>();
    private List<Product> gpuList = new ArrayList<Product>();
    private List<Product> hardDriveList= new ArrayList<Product>();
    private List<Product> optionalComponents = new ArrayList<Product>();

    
    
    private double totalPrice;
    private String pcName; 

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName.toUpperCase();
    }
    //Constructors
    public PC()
    {
        this.totalPrice = 0.0;
    }
    
    //GETTERS & SETTERS
    
      public Product getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Product motherboard) {
        this.motherboard = motherboard;
    }

    public Product getCpu() {
        return cpu;
    }

    public void setCpu(Product cpu) {
        this.cpu = cpu;
    }

    public Product getComputerCase() {
        return computerCase;
    }

    public void setComputerCase(Product computerCase) {
        this.computerCase = computerCase;
    }

    public List<Product> getRamList() {
        return ramList;
    }

    public void setRamList(List<Product> ramList) {
        this.ramList = ramList;
    }

    public List<Product> getGpuList() {
        return gpuList;
    }

    public void setGpuList(List<Product> gpuList) {
        this.gpuList = gpuList;
    }

    public List<Product> getHardDriveList() {
        return hardDriveList;
    }

    public void setHardDriveList(List<Product> hardDriveList) {
        this.hardDriveList = hardDriveList;
    }

    public List<Product> getOptionalComponents() {
        return optionalComponents;
    }

    public void setOptionalComponents(List<Product> optionalComponents) {
        this.optionalComponents = optionalComponents;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
