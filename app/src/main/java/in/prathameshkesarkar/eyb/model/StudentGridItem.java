package in.prathameshkesarkar.eyb.model;

/**
 * Created by prathameshkesarkar on 08/02/17.
 */

public class StudentGridItem {
    String itemName;
    int gridImageItem;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return gridImageItem;
    }

    public void setItemId(int itemId) {
        this.gridImageItem = itemId;
    }

    public StudentGridItem(String itemName, int gridImageItem) {
        this.itemName = itemName;
        this.gridImageItem = gridImageItem;
    }
}
