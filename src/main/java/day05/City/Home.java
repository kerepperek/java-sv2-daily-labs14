package day05.City;

public class Home extends Building {
    protected final static int SQUARE_METERS_NEED_PER_PERSON = 20;

    public Home(int area, int levels, Address address) {
        super(area, levels, address);
        if (levels > 3) {
            throw new IllegalArgumentException("Home levels can be less then 4!");
        }
    }

    public Home(int area, Address address) {
        super(area, address);
    }

    @Override
    public int calculateNumberOfPeopleCanFit() {
        return super.getFullArea() / SQUARE_METERS_NEED_PER_PERSON;
    }
}
