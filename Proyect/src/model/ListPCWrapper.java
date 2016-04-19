/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import es.upv.inf.Database;
import es.upv.inf.Product;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qjot
 */
@XmlRootElement(name="listPC")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement
public class ListPCWrapper {
    
    @XmlElement(name = "PC")
    private List<PC> pcList;
//Befeore PC
    //@XmlElement(name = "ListPCWrapper")
    public List<PC> getPcList() {
        return pcList;
    }

    public void setPcList(List<PC> list) {
        pcList = list;
    }
    public void addPc(PC pc) {
        pcList.add(pc);
    }

    public void saveListToXml() {
        try {
            File file = new File("PCDatabase.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ListPCWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(pcList, file);
            jaxbMarshaller.marshal(pcList, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void getListFromXml() {
        try {
            File file = new File("PCDatabase.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(PC.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            PC person = (PC) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
 
    
    public void createStandardDatabase()
    {
        PC standardPC = new PC();
        standardPC.setPcName("StandardPc");
        standardPC.setMotherboard(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(0));
        standardPC.setComputerCase(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(0));
        standardPC.setCpu(Database.getProductByCategory(Product.Category.CPU).get(0));
        standardPC.setGpuList(Database.getProductByCategory(Product.Category.GPU).subList(0, 2));
        standardPC.setHardDriveList(Database.getProductByCategory(Product.Category.HDD).subList(0, 2));
        standardPC.setRamList(Database.getProductByCategory(Product.Category.RAM).subList(0, 1));
        standardPC.setOptionalComponents(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 2));
        
        PC GamingPc = new PC();
        GamingPc.setPcName("GamingPc");
        GamingPc.setMotherboard(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(10));
        GamingPc.setComputerCase(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(3));
        GamingPc.setCpu(Database.getProductByCategory(Product.Category.CPU).get(15));
        GamingPc.setGpuList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        GamingPc.setHardDriveList(Database.getProductByCategory(Product.Category.HDD).subList(3, 5));
        GamingPc.setRamList(Database.getProductByCategory(Product.Category.RAM).subList(2, 5));
        GamingPc.setOptionalComponents(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 1));
        GamingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.SPEAKER).get(5));
        GamingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.FAN).get(6));
        GamingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(13));
        GamingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.DVD_WRITER).get(3));
        
        
        PC WorkingPc = new PC();
        WorkingPc.setPcName("WorkingPc");
        WorkingPc.setMotherboard(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(10));
        WorkingPc.setComputerCase(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(3));
        WorkingPc.setCpu(Database.getProductByCategory(Product.Category.CPU).get(15));
        WorkingPc.setGpuList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        WorkingPc.setHardDriveList(Database.getProductByCategory(Product.Category.HDD).subList(3, 5));
        WorkingPc.setRamList(Database.getProductByCategory(Product.Category.RAM).subList(2, 5));
        WorkingPc.setOptionalComponents(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 1));
        WorkingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.SPEAKER).get(5));
        WorkingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.FAN).get(6));
        WorkingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(13));
        WorkingPc.addOptionalComponent(Database.getProductByCategory(Product.Category.DVD_WRITER).get(3));
        
        pcList = new ArrayList<PC>();
        pcList.add(GamingPc);
        pcList.add(WorkingPc);
        pcList.add(standardPC);

    }
}
