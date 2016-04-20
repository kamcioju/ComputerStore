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
import model.ListPCWrapper;
import model.PC;

public class PcMarshing
{
	static ListPCWrapper dbList = new ListPCWrapper();
 
static
{
    dbList.setPcList(new  ArrayList<PC>());
        PC standardPC = new PC();
        standardPC.setPcName("StandardPc");
        standardPC.addProduct(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(1));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.CPU).get(0));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.GPU).subList(0, 2));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.HDD).subList(0, 2));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(0, 1));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 2));
        
        PC GamingPc = new PC();
        GamingPc.setPcName("GamingPc");
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(10));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.CPU).get(15));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.HDD).subList(3, 5));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(2, 5));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 1));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.SPEAKER).get(5));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.FAN).get(6));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(13));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.DVD_WRITER).get(3));
        
        
        PC WorkingPc = new PC();
        WorkingPc.setPcName("WorkingPc");
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(3));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.CPU).get(15));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.HDD).subList(3, 5));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(2, 5));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 1));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.SPEAKER).get(5));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.FAN).get(6));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(13));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.DVD_WRITER).get(3));
        
        
        dbList.getPcList().add(GamingPc);
        dbList.getPcList().add(WorkingPc);
        dbList.getPcList().add(standardPC);
        
        	try {
            File file = new File("PCDatabase.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ListPCWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dbList, file);
            jaxbMarshaller.marshal(dbList, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
}
		public static ListPCWrapper unMarshalingDefaultSet() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ListPCWrapper.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ListPCWrapper pcs = (ListPCWrapper) jaxbUnmarshaller.unmarshal( new File("PCDatabase.xml") );
		//ListPCWrapper pcsList = new ListPCWrapper();
                //for(int i=0; i< pcsList.getPcList().size(); i++)
                //{
                  //  pcsList.addPc();
		//}
                return pcs;
	}

	public static void marshalingDefaultSet() {
		try {
            File file = new File("PCDatabase.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ListPCWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dbList, file);
            jaxbMarshaller.marshal(dbList, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
        
        
	public static void marshalingUserConfiguration() {
		try {
            File file = new File("PCDatabase.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ListPCWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dbList, file);
            jaxbMarshaller.marshal(dbList, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
        
}