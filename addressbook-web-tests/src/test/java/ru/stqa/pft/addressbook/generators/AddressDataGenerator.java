package ru.stqa.pft.addressbook.generators;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.AddressData;
import com.beust.jcommander.ParameterException;


import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddressDataGenerator {

    @Parameter(names = "-c", description = "Address count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        AddressDataGenerator generator = new AddressDataGenerator();
        JCommander commander = new JCommander(generator);
        try {
            commander.parse(args);
        } catch (ParameterException e) {
            commander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<AddressData> addresses = generateAddresses(count);
        saveAsJson(addresses, new File(file));
    }

    private void saveAsJson(List<AddressData> addresses, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(addresses);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();

    }


    private List<AddressData> generateAddresses(int count) {
        List<AddressData> addresses = new ArrayList<AddressData>();
        for (int i = 0; i < count; i++) {
            addresses.add(new AddressData().withFirstName(String.format("firstname %s", i))
                    .withLastName(String.format("lastname %s", i))
                    .withAddress(String.format("address %s", i))
                    .withEmail(String.format("mail%s@email", i))
                    .withHome(String.format("8 (222) 123-123- %s", i))
                    .withMobile(String.format("8913 111 222 %s", i))
                    .withWork(String.format("123-123%s", i))
            );
        }
        return addresses;
    }
}
