package po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * House entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HOUSE", schema = "SCOTT")
public class House implements java.io.Serializable {

	// Fields

	private Double id;
	private User user;
	private Type type;
	private Street street;
	private String title;
	private String description;
	private Double price;
	private Timestamp pubdate;
	private Double floorage;
	private String contact;

	// Constructors

	/** default constructor */
	public House() {
	}

	/** minimal constructor */
	public House(Double id) {
		this.id = id;
	}

	/** full constructor */
	public House(Double id, User users, Type type, Street street,
			String title, String description, Double price, Timestamp pubdate,
			Double floorage, String contact) {
		this.id = id;
		this.user = users;
		this.type = type;
		this.street = street;
		this.title = title;
		this.description = description;
		this.price = price;
		this.pubdate = pubdate;
		this.floorage = floorage;
		this.contact = contact;
	}

	// Property accessors
	@Id	
	@Column(name = "ID", unique = true, nullable = false, length = 10)
	public Double getId() {
		return this.id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID")
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STREET_ID")
	public Street getStreet() {
		return this.street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PRICE", precision = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "PUBDATE", length = 7)
	public Timestamp getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(Timestamp pubdate) {
		this.pubdate = pubdate;
	}

	@Column(name = "FLOORAGE", precision = 0)
	public Double getFloorage() {
		return this.floorage;
	}

	public void setFloorage(Double floorage) {
		this.floorage = floorage;
	}

	@Column(name = "CONTACT", length = 100)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", user=" + user.getName() + ", type=" + type.getId() + ", street=" + street.getId() + ", title=" + title
				+ ", description=" + description + ", price=" + price + ", pubdate=" + pubdate + ", floorage="
				+ floorage + ", contact=" + contact + "]";
	}

}