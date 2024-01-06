package design.pattern.behavioural;

import common.helper.order.Order;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * {@link TemplateMethod} is used to define the skeleton of algorithm in base class and which can be implemented in steps on the subclasses
 * */
public interface TemplateMethod {}

abstract class OrderExport implements TemplateMethod {
    public final void export(Order order, String fileName) throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.println(formatHeader());

            printWriter.println(formatOrderName(order));

            printWriter.println(formatQuantity(order));

            printWriter.println(formatPrice(order));

            printWriter.println(formatFooter());
        }
    }

    protected abstract String formatHeader();

    protected abstract String formatOrderName(Order order);

    protected abstract String formatQuantity(Order order);

    protected abstract String formatPrice(Order order);

    protected abstract String formatFooter();

    public static void main(String[] args) throws FileNotFoundException {
        Order order = new Order("Tea", 5, 100.00);

        OrderExport jsonExport = new JSONOrderExport();
        jsonExport.export(order, "order.json");

        OrderExport xmlExport = new XMLOrderExport();
        xmlExport.export(order, "order.xml");
    }
}

class JSONOrderExport extends OrderExport {

    @Override
    protected String formatHeader() {
        return "{";
    }

    @Override
    protected String formatOrderName(Order order) {
        return "\t\"name\": \"" + order.getName() + "\",";
    }

    @Override
    protected String formatQuantity(Order order) {
        return "\t\"quantity\": \"" + order.getQuantity() + " units\"" + ",";
    }

    @Override
    protected String formatPrice(Order order) {
        return "\t\"price\": \""+ "Rs." + order.getPrice() + "\"";
    }

    @Override
    protected String formatFooter() {
        return "}";
    }
}

class XMLOrderExport extends OrderExport {

    @Override
    protected String formatHeader() {
        return "<order>";
    }

    @Override
    protected String formatOrderName(Order order) {
        return "\t<name>" + order.getName() + "</name>";
    }

    @Override
    protected String formatQuantity(Order order) {
        return "\t<quantity>" + order.getQuantity() + "</quantity>";
    }

    @Override
    protected String formatPrice(Order order) {
        return "\t<price>" + order.getPrice() + "</price>";
    }

    @Override
    protected String formatFooter() {
        return "</order>";
    }
}