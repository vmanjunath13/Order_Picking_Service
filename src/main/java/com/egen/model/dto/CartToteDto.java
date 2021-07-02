package com.egen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartToteDto implements Serializable {

    private long cartToteId;
    public List<ToteDto> toteList;

    public CartToteDto() {
    }

    public long getCartToteId() {
        return cartToteId;
    }

    public void setCartToteId(long cartToteId) {
        this.cartToteId = cartToteId;
    }

    public List<ToteDto> getToteList() {
        return toteList;
    }

    public void setToteList(List<ToteDto> toteList) {
        this.toteList = toteList;
    }
}
