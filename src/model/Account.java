package model;

public class Account {
    Long id;
    String name;
    Enum<AccountStatus> status;

    public Account(Long id, String name, Enum<AccountStatus> status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Account(String name, Enum<AccountStatus> status) {
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
