import java.util.List;

public class ReportGenerator {
    public static void main(String[] args) {
        Property property1 = new Property("Apartment A", 1500.0, "John Doe", "City Center");
        Property property2 = new Property("House B", 2000.0, "Jane Smith", "Suburb");
        Property property3 = new Property("Condo C", 1800.0, "Bob Johnson", "Downtown");
        FinancialReport financialReport = new FinancialReport("Monthly Rent Summary", List.of(property1, property2, property3));
        financialReport.generateReport();

    }
}

class Property {
    private String name;
    private double rentAmount;
    private String owner;
    private String location;

    public Property(String name, double rentAmount, String owner, String location) {
        this.name = name;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public String getOwner() {
        return owner;
    }

    public String getLocation() {
        return location;
    }

    public double getYearlyRent() {
        return rentAmount * 12;
    }

    public boolean isPremium() {
        return rentAmount > 2000;
    }

    @Override
    public String toString() {
        return String.format("Property: %s | Rent: $%.2f | Owner: %s | Location: %s", 
                name, rentAmount, owner, location);
    }
}

class FinancialReport {
    private final String reportTitle;
    private final List<Property> properties;

    public FinancialReport(String reportTitle, List<Property> properties) {
        this.reportTitle = reportTitle;
        this.properties = properties;
    }

    public double calculateTotalRent() {
        return properties.stream().mapToDouble(Property::getRentAmount).sum();
    }

    public void generateReport() {
        System.out.println("Financial Report: " + reportTitle);
        System.out.println("----------------------------");

        for (Property property : properties) {
            System.out.println(property);
            System.out.println(property.isPremium() ? "This is a premium property." : "This is a standard property.");
            System.out.printf("Yearly Rent: $%.2f%n", property.getYearlyRent());
            System.out.println("--------------------");
        }

        System.out.printf("Total Rent Amount: $%.2f%n", calculateTotalRent());
    }
}




