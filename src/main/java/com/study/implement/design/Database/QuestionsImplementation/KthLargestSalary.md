# KthLargest Salary 

Normal Implementation for Second Largest Salary 
```text
select max(salary) 
from empl
where salary < (select max(salary) from emp)
```

MySQL Specific Implementation for Second Largest Salary 

```text
select salary 
from emp
order by salary desc
limit 1 offset 1;
```

Kth Largest Element 
```text
select salary
from emp 
order by salary desc
limit k -1,1;
```