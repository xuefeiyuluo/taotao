package com.sparrow.taotao;

import java.util.Date;

public class ItemDesc {
    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

    public ItemDesc(Long itemId, Date created, Date updated) {
        this.itemId = itemId;
        this.created = created;
        this.updated = updated;
    }

    public ItemDesc(Long itemId, Date created, Date updated, String itemDesc) {
        this.itemId = itemId;
        this.created = created;
        this.updated = updated;
        this.itemDesc = itemDesc;
    }

    public ItemDesc() {
        super();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}