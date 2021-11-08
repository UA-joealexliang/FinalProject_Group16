//Payee class is the names of companies/people/etc. that you've paid to
public class Payee {
	private String name;
	private String description;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getDescription() {
		return this.description;
	}
}
