/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import es.upv.inf.*;
import java.util.List;


/**
 *
 * @author qjot
 */
public class PC {

    private Product motherboard;
    private Product cpu;
    private Product computerCase;
    private List<Product> ramList;
    private List<Product> gpuList;
    private List<Product> hardDriveList;
    private List<Product> optionalComponents;
    private double totalPrice;
    
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
