package model;
import es.upv.inf.Database;
import es.upv.inf.Product;
import java.io.File;
import java.util.ArrayList;

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
        standardPC.setMotherboard(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(0));
        standardPC.setComputerCase(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(0));
        standardPC.setCpu(Database.getProductByCategory(Product.Category.CPU).get(0));
        standardPC.setGpuList(Database.getProductByCategory(Product.Category.GPU).subList(0, 2));
        standardPC.setHardDriveList(Database.getProductByCategory(Product.Category.HDD).subList(0, 2));
        standardPC.setRamList(Database.getProductByCategory(Product.Category.RAM).subList(0, 1));
        standardPC.setOptionalComponents(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 2));
        
        PC GamingPc = new PC();
        GamingPc.setPcName("GamingPc");
        GamingPc.setMotherboard(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(2));
        GamingPc.setCpu(Database.getProductByCategory(Product.Category.CPU).get(15));
        GamingPc.setGpuList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        GamingPc.setHardDriveList(Database.getProductByCategory(Product.Category.HDD).subList(0, 1));
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
	
	/*public static void main(String[] args) throws JAXBException 
	{
		marshalingExample();
		System.out.println("************************************************");
		unMarshalingExample();
	}
*/

	public static void unMarshalingExample() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(PC.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		PC pcs = (PC) jaxbUnmarshaller.unmarshal( new File("Database.xml") );
		
		
        }

	public static void marshalingExample() {
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