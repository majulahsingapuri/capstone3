# Capstone3

## Setup

1. Create a volume to persist the database between container restarts:

   ```bash
   docker volume create capstone_db
   ```

2. Start the database container:

   ```bash
   docker run -d -v capstone_db:/var/lib/postgresql/data -e POSTGRES_USER=capstone -e POSTGRES_PASSWORD=password -e POSTGRES_DB=capstone -p 5432:5432 --name capstone_db postgres

   ```

3. Run the Springboot server.

4. Run the SQL commands in `capstone_db.session.sql` to set up the Admin user
