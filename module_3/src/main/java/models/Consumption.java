package models;

import javax.persistence.*;

@Entity
@DiscriminatorValue("consumption")
public class Consumption extends Operation {
}