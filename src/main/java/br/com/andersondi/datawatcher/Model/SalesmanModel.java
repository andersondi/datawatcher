package br.com.andersondi.datawatcher.Model;

public class SalesmanModel {
    String cpf = "";
    String name = "";
    Double salary = 0.0;
    Double amountSold = 0.0;

    public SalesmanModel() {}

    public SalesmanModel( String cpf, String name, Double salary ) {
        this.cpf = cpf;
        this.name = name.toLowerCase();
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf( String cpf ) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary( Double salary ) {
        this.salary = salary;
    }

    public Double getAmountSold() {
        return amountSold;
    }

    public void setAmountSold( Double amountSold ) {
        this.amountSold = amountSold;
    }

    public void addBilledAmount(Double value){
        this.setAmountSold( getAmountSold() + value );
    }
}
