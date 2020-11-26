package po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Street entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STREET", schema = "SCOTT")
public class Street implements java.io.Serializable {

	// Fields

	private Double id;
	private String name;
	//private Double districtId;
	private District district;
	private Set<House> houses = new HashSet<House>(0);

	// Constructors

	/** default constructor */
	public Street() {
	}

	/** minimal constructor */
	public Street(Double id) {
		this.id = id;
	}

	/** full constructor */
	public Street(Double id, String name, District district, Set<House> houses) {
		this.id = id;
		this.name = name;
		this.district = district;
		this.houses = houses;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 0)
	public Double getId() {
		return this.id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DISTRICT_ID")
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "street")
	public Set<House> getHouses() {
		return this.houses;
	}

	public void setHouses(Set<House> houses) {
		this.houses = houses;
	}

}