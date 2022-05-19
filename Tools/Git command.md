# Git command

## config email and name

    `git config --global user.email "your_email@example.com"`
    `git config --global user.name "FIRST_NAME LAST_NAME"`

## git add

Developing a project revolves around the basic edit/stage/commit pattern. First, you edit your files in the working directory. When you’re ready to save a copy of the current state of the project, you stage changes with `git add`. After you’re happy with the staged snapshot, you commit it to the project history with `git commit`. The `git reset` command is used to undo a commit or staged snapshot.

    `git add .`

## git commit

The "commit" command is used to save your changes to the local repository.

### Important Options

-m "message": **Sets the commit's message.**

-a: **Includes all currently changed files in this commit.** Keep in mind, however, that untracked (new) files are not included.

--amend: **Rewrites the very last commit** with any currently staged changes and/or a new commit message. Git will rewrite the last commit and effectively replace it with the amended one. Note that such a rewriting of commits should only be performed on commits that have not been pushed to a remote repository, yet.

    `git commit -am "message"`
    `git commit --amend -m "new commit message"`

## git pull fast-forward

    `git pull --ff-only origin`

## git push
