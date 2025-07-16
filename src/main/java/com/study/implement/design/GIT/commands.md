```shell
git init - initialise a local git repo
git clone <repo-name> - cloning repo
git status - working directory status
git add <file> - stage a file for commit
git add . - stage all changes for commit
git commit -m "message" - commit staged changes with a message
git commit -am :message - stage and commit tracked files in one step
git diff - show unstanged changes
git diff --staged - show staged changes
git branch - list all branches
git branch <name> - create a new branch
git checkout <branch> - checkout a new branch 
git checkout -b <branch> - Create and switch to a new branch.
git merge <branch> - merge another branch into current 
git branch - d <branch>  - delete a branch 

//remote
git remote add origin <url> - add remote repo
git remote -v - list remote repo
git push -u origin <branch>  - push changes and set upstream 
git push - push changes to remote
git pull - fetch and merge from remote
git fetch - fetch from remote wihtout merging

//undoing changes
git restore <file> - restore file to last committed state
git restore --staged <file> - unstage a staged file 
git reset <file> - unstage changes
git reset -- hard - reset working directory and index
git reset --soft HEAD~1 - Undo last commit, keep changes staged
git revert <commit> - create a new commit that undoes a specific commit. 
 
//history and logs
git log - view commit history
git log --oneline - condensed commit history
git show <commit>  - show detaisl of a specific commit
git blame <file>  - show who changesd each line in a file 

//stashing and cleaning 
git stash  - temp save uncommitted changes
git stash pop = re apply stashed changes
git stash list - list all stashes
git clean -f - remove untracked files
git clean -fd - remove untracked files and directories 

//advanced
git tag = lsit tag
git tag <name>  - create a tag
git cherry-pick <commit>  - apply a specific commit to current branch 
git rebase <branch>  - reapply commits on top of another base 
git reflog = show history of branch references


```