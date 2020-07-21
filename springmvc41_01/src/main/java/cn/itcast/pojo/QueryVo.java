package cn.itcast.pojo;

public class QueryVo {
    private Item item;
    private int[] ids;

    public QueryVo() {
    }

    public QueryVo(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }
}