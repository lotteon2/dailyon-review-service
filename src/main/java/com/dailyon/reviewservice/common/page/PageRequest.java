package com.dailyon.reviewservice.common.page;

import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.dailyon.reviewservice.common.page.OrderCondition.RECENT;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Getter
public class PageRequest {
  private static final int PAGE_SIZE = 8;
  private static final int PAGE = 0;
  private static final String ORDER = RECENT.getSort();
  private static final Sort.Direction SORT = DESC;

  private Pageable pageable;

  public PageRequest(int page, String order, String sort) {
    this.pageable = toPageable(page, toOrderCondition(order), toSortDirection(sort));
  }

  private String toOrderCondition(String order) {
    return (order == null || order.isBlank())
        ? RECENT.getSort()
        : OrderCondition.valueOf(order).getSort();
  }

  private Sort.Direction toSortDirection(String sort) {
    return (sort == null || sort.isBlank()) ? DESC : Sort.Direction.valueOf(sort);
  }

  private Pageable toPageable(int page, String order, Sort.Direction sort) {
    return org.springframework.data.domain.PageRequest.of(
        page != 0 ? page : PAGE, PAGE_SIZE, sort, order);
  }
}
