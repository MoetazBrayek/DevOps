package tn.esprit.spring.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Mission implements Serializable {
	private static final long serialVersionUID = -5369734855993305723L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private int id;
	@Length(min = 8, max = 255)
	@NotBlank
	@Getter
	@Setter
	private String name;
	@Length(min = 8, max = 255)
	@NotBlank
	@Getter
	@Setter
	private String description;
	@NotNull
	@ManyToOne
	@Getter
	@Setter
	private Departement departement;
	@Getter
	@OneToMany(mappedBy = "mission")
	private List<Timesheet> timesheets = new ArrayList<>();

	public Mission() {
		super();
	}

	public Mission(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Mission(String name, String description, Departement departement) {
		super();
		this.name = name;
		this.description = description;
		this.departement = departement;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Mission mission = (Mission) o;
		return name.equals(mission.name) && description.equals(mission.description)
				&& departement.equals(mission.departement) && timesheets.equals(mission.timesheets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, departement, timesheets);
	}

	@Override
	public String toString() {
		return "Mission{" + "name='" + name + '\'' + ", description='" + description + '\'' + '}';
	}
}
