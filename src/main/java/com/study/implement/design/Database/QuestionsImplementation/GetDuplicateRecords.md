# Query to get duplicate records from table and remove the duplicate records.

```text
select username,email, count(*) as duplicate_count
from table
group by username,email
having count(*) > 1;

```

```text

```