package ISS.database.numberofastronauts.entity;

import ISS.database.person.entity.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "number_of_astronauts")
@JsonIgnoreProperties(value = "message")
public class NumberOfAstronauts {

    @Id
    private long timestamp;
    @JsonProperty("number")
    private int count;

    @OneToMany(mappedBy = "number", fetch = FetchType.EAGER)
    private Set<Person> people;

    public NumberOfAstronauts() {
    }

    public NumberOfAstronauts(long timestamp, int count) {
        this.timestamp = timestamp;
        this.count = count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfAstronauts that = (NumberOfAstronauts) o;
        return timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }
}
