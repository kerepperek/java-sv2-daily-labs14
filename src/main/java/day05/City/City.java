package day05.City;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class City {
    private String name;
    private long fullArea;
    List<Building> buildings = new ArrayList<>();

    public City(String name, long fullArea) {
        this.name = name;
        this.fullArea = fullArea;
    }

    public long getReservedArea() {
        long result = 0;
        for (Building building : buildings) {
            result += building.getFullArea();
        }
        return result;
    }

    public void addBuilding(Building building) {
        if (fullArea < getReservedArea() + building.getArea()) {
            throw new IllegalArgumentException("City can't be larger than " + fullArea);
        }
        buildings.add(building);
    }

    public Building findHighestBuilding() {

        return buildings.stream().sorted(Comparator.comparing(Building::getLevels).reversed())
                .findFirst().orElseThrow(()->new IllegalArgumentException(("Empty Building")));

       // return buildings.stream()
       //                 .max(Comparator.comparing(Building::getLevels))
       //         .orElseThrow(()->new IllegalArgumentException("Empty Building"));

       // return buildings.stream().max(new Comparator<Building>(){
       //     @Override
       //     public int compart(Building o1, Building o2){
       //         return o1.getLevels()-o2.getLevels();
        //    }

       // })

       // int highestLevel = 0;
       // Building result = buildings.get(0);

       // for (Building building : buildings) {
        //    if (building.getLevels() > highestLevel) {
         //       result = building;
          //      highestLevel = building.getLevels();
          //  }
       // }
      //  return result;
    }

    public List<Building> findBuildingsByStreet(String street) {

        return buildings.stream()
                .filter(f->f.getAddress().getStreet().equals(street))
                .collect(Collectors.toList());

   //     List<Building> result = new ArrayList<>();
    //    for (Building building : buildings) {
    //        if (building.getAddress().getStreet().equals(street)) {
    //            result.add(building);
    //        }
    //    }
    //    return result;
    }

    public boolean isThereBuildingWithMorePeopleThan(int numberOfPeople) {
        return buildings.stream()
                .anyMatch(o->o.calculateNumberOfPeopleCanFit()> numberOfPeople);

      //  for (Building building : buildings) {
      //      if (building.calculateNumberOfPeopleCanFit() > numberOfPeople) {
      //          return true;
      //      }
      //  }
      //  return false;
    }

    public String getName() {
        return name;
    }

    public long getFullArea() {
        return fullArea;
    }

    public List<Building> getBuildings() {
        return buildings;
    }
}
