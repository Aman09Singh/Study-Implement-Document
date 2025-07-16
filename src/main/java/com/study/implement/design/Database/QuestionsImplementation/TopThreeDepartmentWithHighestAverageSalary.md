# Write a query to fetch the top 3 departments with the highest average salary.

```text
select departments, avg(salary)
from table
group by departments
order by avg(salary) desc
limit 3;
```



