import java.util.Objects;

public class Fuel {
    private Integer fuelId;
    private String fuelName;

    public Integer getFuelId() {
        return fuelId;
    }

    public void setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "fuelId=" + fuelId +
                ", fuelName='" + fuelName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return fuelId.equals(fuel.fuelId) &&
                fuelName.equals(fuel.fuelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelId, fuelName);
    }
}
