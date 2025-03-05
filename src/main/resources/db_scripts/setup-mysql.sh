#!/bin/bash


echo "Starting MySQL container..."
sudo podman run --name mysql \
  -e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
  -p 3306:3306 -d docker.io/mysql:8

if [ $? -ne 0 ]; then
  echo "Failed to start MySQL container. Exiting..."
  exit 1
fi


echo "Waiting for MySQL to be ready..."
until sudo podman exec -it mysql mysqladmin ping --silent &> /dev/null; do
  echo "Waiting for MySQL to be online..."
  sleep 5
done
echo "MySQL is ready."


echo "Creating database 'spring_data_jpa_db' if not exists..."
sudo podman exec -it mysql mysql -u root -p"" -e "CREATE DATABASE IF NOT EXISTS spring_data_jpa_db;"
if [ $? -ne 0 ]; then
  echo "Failed to create database. Exiting..."
  exit 1
fi

# Step 1: Get the directory of the current script (setup-mysql.sh)
SCRIPT_DIR=$(dirname "$(realpath "$0")")

# Step 2: Navigate to the parent directory of db_scripts (springdatajpa)
PROJECT_ROOT=$(realpath "$SCRIPT_DIR/../../../../")  # Go up 4 levels from db_scripts to springdatajpa

echo "Starting Spring Boot application..."
cd "$PROJECT_ROOT" || exit
./mvnw spring-boot:run

if [ $? -ne 0 ]; then
  echo "Spring Boot application failed to start. Exiting..."
  # Stop and clean up MySQL container before exiting
  echo "Stopping and removing MySQL container..."
  sudo podman stop mysql
  sudo podman rm mysql
  exit 1
fi


echo "Stopping and removing MySQL container..."
sudo podman stop mysql
sudo podman rm mysql
echo "MySQL container stopped and removed."
