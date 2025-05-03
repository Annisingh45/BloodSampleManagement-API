package com.ketan.bsm.utility;

import lombok.*;

@Getter
@Setter
public class PageStructure<T> extends ResponseStructure<T> {
    private int page;
    private int totalPage;
    private int size;

}
