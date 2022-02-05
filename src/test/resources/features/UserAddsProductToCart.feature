Feature: User adds product to Cart page

  Scenario Outline: User adds product to Cart from PDP
    Given User search for <category> category
    And User filter results by <brand> brand
    And User filter to show only available products
    And User sort products by price in descending order
    And User click on first product from grid
    When User click Add to Cart button
    Then Product is displayed in Cart modal
    And Total price is less than <price>

    Examples:
      | category     | brand      | price  |
      | Ноутбуки     | HP         | 300000 |
      | Холодильники | Samsung    | 130000 |
      | Смартфони    | Apple      | 100000 |
      | Книги        | Freedom    | 2000   |
      | Велосипед    | Discovery  | 10000  |