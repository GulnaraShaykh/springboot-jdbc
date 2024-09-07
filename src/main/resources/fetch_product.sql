select product_name
from netology.orders
         inner join netology.customers
                    on netology.orders.customer_id = netology.customers.id
where lower(netology.customers.name) = lower(:name);