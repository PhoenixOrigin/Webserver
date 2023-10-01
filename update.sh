#!/bin/bash
git stash
git pull
git restore --staged src/main/resources/api.properties
git restore --staged src/main/resources/database.properties
