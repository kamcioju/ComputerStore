package model;

import es.upv.inf.Database;
import es.upv.inf.Product;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static model.CartPC.CheckQuantiy;
import model.ListPCWrapper;
import model.PC;

public class PcMarshing {

    static ListPCWrapper dbList = new ListPCWrapper();

    static {
        dbList.setPcList(new ArrayList<PC>());
        PC standardPC = new PC();
        standardPC.setPcName("StandardPc");
        standardPC.addProduct(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(1));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.CPU).get(0));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.CASE).get(3));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.GPU).subList(0, 2));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.HDD).subList(0, 2));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(0, 1));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(0, 1));
        standardPC.addProductList(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 2));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.SPEAKER).get(3));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.KEYBOARD).get(5));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.FAN).get(3));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(8));
        standardPC.addProduct(Database.getProductByCategory(Product.Category.DVD_WRITER).get(2));

        PC GamingPc = new PC();
        GamingPc.setPcName("GamingPc");
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(10));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.CPU).get(15));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.HDD).subList(3, 5));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(2, 5));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.CASE).get(3));
        GamingPc.addProductList(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 1));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.SPEAKER).get(5));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.FAN).get(6));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(13));
        GamingPc.addProduct(Database.getProductByCategory(Product.Category.DVD_WRITER).get(3));

        PC WorkingPc = new PC();
        WorkingPc.setPcName("WorkingPc");
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(3));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.CPU).get(15));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.CASE).get(6));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.HDD).subList(3, 5));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(2, 5));
        WorkingPc.addProductList(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 1));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.SPEAKER).get(5));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.FAN).get(8));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(11));
        WorkingPc.addProduct(Database.getProductByCategory(Product.Category.DVD_WRITER).get(8));

        PC MusicPc = new PC();
        MusicPc.setPcName("MusicPc");
        MusicPc.addProduct(Database.getProductByCategory(Product.Category.MOTHERBOARD).get(3));
        MusicPc.addProduct(Database.getProductByCategory(Product.Category.CPU).get(15));        
        MusicPc.addProduct(Database.getProductByCategory(Product.Category.CASE).get(15));
        MusicPc.addProductList(Database.getProductByCategory(Product.Category.GPU).subList(5, 7));
        MusicPc.addProductList(Database.getProductByCategory(Product.Category.HDD).subList(6, 8));
        MusicPc.addProductList(Database.getProductByCategory(Product.Category.RAM).subList(4, 6));
        MusicPc.addProductList(Database.getProductByCategory(Product.Category.MOUSE).subList(0, 1));
        MusicPc.addProduct(Database.getProductByCategory(Product.Category.SPEAKER).get(6));
        MusicPc.addProduct(Database.getProductByCategory(Product.Category.FAN).get(3));
        MusicPc.addProduct(Database.getProductByCategory(Product.Category.POWER_SUPPLY).get(7));
        MusicPc.addProduct(Database.getProductByCategory(Product.Category.DVD_WRITER).get(7));

        dbList.getPcList().add(GamingPc);
        dbList.getPcList().add(WorkingPc);
        dbList.getPcList().add(standardPC);
        dbList.getPcList().add(MusicPc);

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
        ListPCWrapper pcs = (ListPCWrapper) jaxbUnmarshaller.unmarshal(new File("PCDatabase.xml"));
        return pcs;
    }

    public static void marshalingDefaultSet() {
        try {
            File userConfig = new File("userConfigurations");
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

    public static void marshalingUserConfiguration(PC pcToSave) {
        try {
            dbList.setPcList(new ArrayList<PC>());
            dbList.addPc(pcToSave);
            String configurationName = pcToSave.getPcName() + ".xml";
            File file = new File(configurationName);
            JAXBContext jaxbContext = JAXBContext.newInstance(ListPCWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dbList, file);
            jaxbMarshaller.marshal(dbList, System.out);

            //Save the name to Configuration file <to txt>
            try {
                String filename = "userConfigurations.txt";
                FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                fw.write(System.getProperty("line.separator") + configurationName);//appends the string to the file
                fw.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static int CheckComponents(PC pc) {

        ArrayList<String> requiredList = new ArrayList<String>() {
            {
                add("MOTHERBOARD");
                add("CPU");
                add("HDD");
                add("HDD_SDD");
                add("RAM");
                add("GPU");
                add("CASE");
            }
        };
        ArrayList<String> currentRequired = new ArrayList<>();
        ArrayList<String> optionalList = new ArrayList<String>() {
            {
                add("POWER_SUPPLY");
                add("FAN");
                add("SCREEN");
                add("MOUSE");
                add("DVD_WRITER");
                add("KEYBOARD");
            }
        };
        int requiredParts = 0;
        int hardDrives = 0;
        int optionalParts = 0;
        for (Product product : pc.getProductList()) {
            String productCat = product.getCategory().toString();
            if (requiredList.contains(productCat)) {
                if (!currentRequired.contains(productCat)) {
                    currentRequired.add(productCat);
                    requiredParts++;
                    if (productCat == "HDD" || productCat == "HDD_SSD") {
                        hardDrives++;
                    }
                }
            }
        }

        for (Product product : pc.getProductList()) {
            String productCat = product.getCategory().toString();
            if (optionalList.contains(productCat)) {
                optionalParts++;
            }
        }
        if((requiredParts ==6 && hardDrives==1) || (requiredParts == 7 && hardDrives == 2))
        {
              return 3; //everything is OK, GO HOME
        }       
         else if (optionalParts < 1) {
            return 2; // Warning, 0 optional parts
        } else {
         
            return 1; //You have not enought mandatory parts
        }
    }

    public static List<PC> LoadPcConfiguration() {
        try {

            final List<String> lines;
            try {
                Path path = Paths.get("userConfigurations.txt");
                lines = Files.readAllLines(path);
                List<PC> userPcList = new ArrayList<PC>();
                for (String userConfig : lines) {
                    JAXBContext jaxbContext = JAXBContext.newInstance(ListPCWrapper.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    ListPCWrapper pcs = (ListPCWrapper) jaxbUnmarshaller.unmarshal(new File(userConfig));
                    userPcList.addAll(pcs.getPcList());
                }
                return userPcList;
            } catch (IOException ex) {

            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
