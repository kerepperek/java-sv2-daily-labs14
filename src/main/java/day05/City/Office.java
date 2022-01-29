package day05.City;

public class Office extends Building {
    private String company;
    private int numberOfTablesPerLevel;
    protected final static int MINIMUM_TABLE_AREA = 2;
    protected final static int MAXIMUM_TABLE_AREA = 5;


    public Office(int area, int levels, Address address, String company, int numberOfTablesPerLevel) {
        super(area, levels, address);
        if (area < numberOfTablesPerLevel * MINIMUM_TABLE_AREA) {
            throw new IllegalArgumentException("These number of table can not be implemented into this area!");
        }
        if (area > numberOfTablesPerLevel * MAXIMUM_TABLE_AREA) {
            throw new IllegalArgumentException("There is not enough tables!");
        }
        this.company = company;
        this.numberOfTablesPerLevel = numberOfTablesPerLevel;
    }

    @Override
    public int calculateNumberOfPeopleCanFit() {
        return (super.getLevels() - 1) * numberOfTablesPerLevel;
    }

    public String getCompany() {
        return company;
    }

    public int getNumberOfTablesPerLevel() {
        return numberOfTablesPerLevel;
    }
}
