# Hospital Management System

## Git Basics
Cloning this repo to your computer:
* git clone 

Pulling new changes (Make sure you are on the correct branch):

* git pull

Pushing new changes:

* git add .
* git commit -m "\<your message here>"
* git push

Switching to another branch:

* git checkout \<branch name>

**General workflow should be:**

* git checkout -b \<Name>
* git add .
* git commit -m "\<your message here>"
* git push

Updating your local list of branches based on the remote repo:

* git fetch

Viewing your local list of branches (Both local and remote):

* git branch -a

If you want to check whether your current branch is up-to-date or not:
* git status

If you are coding on a branch halfway but need to switch to another branch for some reason:<br />
(You can also do this if you are coding halfway and want to reference the original version of the current branch)
* git stash
* git stash pop (to reverse)

If you accidentally committed some changes and want to wipe them out completely:

* git reset --hard


## Tools / Packages
1. Maven
2. Time Package `java.time`

## Assumptions
1. All inputs are correct (no crosschecking)
2. All users have basic details which are assigned to a single "person" --> all users have names, DoB, etc. 