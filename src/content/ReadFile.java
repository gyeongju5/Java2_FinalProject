package content;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ReadFile {

    public ReadFile(File one) {

    }

    public static void loadFile(String fileName, LinkedList<Order> orderList) throws FileNotFoundException, IOException {

            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            
            while (line != null) {
                StringTokenizer token = new StringTokenizer(line, ",");
                Order order = new Order(token.nextToken());
                order.setName(token.nextToken());
                order.setAddress(token.nextToken());
                order.setCity(token.nextToken());
                order.setProduct(token.nextToken());
                order.setPrice(Double.parseDouble(token.nextToken()));
                order.setQuantity(Integer.parseInt(token.nextToken()));
                orderList.add(order);
                line = br.readLine();
            }
            

    }

}
