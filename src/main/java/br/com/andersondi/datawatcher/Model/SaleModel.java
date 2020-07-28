package br.com.andersondi.datawatcher.Model;

import java.util.LinkedList;
import java.util.List;

public class SaleModel {
    private String id;
    private String salesmanName;
    private Double value = 0.0;
    private List<ItemModel> listOfItems;

    public SaleModel( String id, String salesmanName, List<ItemModel> listOfItems ) {
        this.id = id;
        this.salesmanName = salesmanName;
        this.listOfItems = listOfItems;
        this.value = calculateSaleValue( listOfItems );
    }

    private Double calculateSaleValue(List<ItemModel> listOfItems) {
        Double result = 0.0;

        for(ItemModel item : listOfItems){
            result += ( item.getPrice() * item.getQuantity() );
        }

        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<ItemModel> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItens(List<ItemModel> listOfItems) {
        this.listOfItems = listOfItems;
    }

}
