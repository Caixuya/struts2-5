package po;
import javax.persistence.*;

@Entity
@Table(name="Type")
public class Type {
	public Type() {
		super();
	}
	public Type(Double id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Id
	private Double id;
	@Column(name="name")
	private String name;
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + "]";
	}
}
