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
  
}
