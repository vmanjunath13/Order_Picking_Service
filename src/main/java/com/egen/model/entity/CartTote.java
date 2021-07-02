package com.egen.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "CartTote")
public class CartTote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartToteId;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "toteId")
    public List<Tote> toteList;

    public CartTote() {
    }

    public long getCartToteId() {
        return cartToteId;
    }

    public void setCartToteId(long cartToteId) {
        this.cartToteId = cartToteId;
    }

    public List<Tote> getToteList() {
        return toteList;
    }

    public void setToteList(List<Tote> toteList) {
        this.toteList = toteList;
    }
}
