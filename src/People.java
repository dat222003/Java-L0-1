public abstract class People {
    private static int nextId = 1;
    private Integer id;
    private String name;
    private String dateOfBirth;
    private Double height;
    private Double weight;

    public People(String name, String dateOfBirth, Double height, Double weight) {
        this.id = nextId;
        nextId++;
        if (name == null) throw new IllegalArgumentException("name cannot be null.");
        if (dateOfBirth == null) throw new IllegalArgumentException("date of birth cannot be null.");
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", DoB='" + dateOfBirth + '\'' +
                ", Height=" + height +
                ", Weight=" + weight +
                '}';
    }
}
