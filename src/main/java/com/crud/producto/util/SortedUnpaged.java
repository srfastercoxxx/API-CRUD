package com.crud.producto.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class SortedUnpaged implements Pageable {

    private final Sort sort;

    private SortedUnpaged(Sort sort) {
        this.sort = sort;
    }

    public static SortedUnpaged getInstance(Sort sort) {
        return new SortedUnpaged(sort);
    }

    public boolean isPaged() {
        return false;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    public Pageable previousOrFirst() {
        return this;
    }

    public Pageable next() {
        return this;
    }

    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Optional.empty();
    }

    public Sort getSort() {
        return sort;
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return null;
    }

    public int getPageSize() {
        return 0;
    }

    public int getPageNumber() {
        return 0;
    }

    public long getOffset() {
        return 0;
    }

    public Pageable first() {
        return this;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }
}