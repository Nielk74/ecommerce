#!/bin/bash

# start backend
cd backend
sudo docker-compose up &
cd ..

# start ng server in datoine directory
cd datoine
npm start -n
