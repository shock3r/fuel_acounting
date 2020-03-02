import java.util.Date;
import java.util.Objects;

public class Transport {
    private Integer transportId;
    private String transportName;
    private Integer fuelId;
    private Double fuelTankCapasity;
    private Date date;

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public Integer getFuelId() {
        return fuelId;
    }

    public void setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
    }

    public Double getFuelTankCapasity() {
        return fuelTankCapasity;
    }

    public void setFuelTankCapasity(Double fuelTankCapasity) {
        this.fuelTankCapasity = fuelTankCapasity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportId=" + transportId +
                ", transportName='" + transportName + '\'' +
                ", fuelId=" + fuelId +
                ", fuelTankCapasity=" + fuelTankCapasity +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return transportId.equals(transport.transportId) &&
                transportName.equals(transport.transportName) &&
                fuelId.equals(transport.fuelId) &&
                fuelTankCapasity.equals(transport.fuelTankCapasity) &&
                date.equals(transport.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportId, transportName, fuelId, fuelTankCapasity, date);
    }
}
