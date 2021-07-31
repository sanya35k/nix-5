package models;

import javax.persistence.*;

@Entity
@DiscriminatorValue("income")
public class Income extends Operation {
}