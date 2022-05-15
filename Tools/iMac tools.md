1. vscode

    be able to type 'code' in any folder to start editing files in that folder.

    `Cmd+Shift+P` and type **Shell Command: Install 'code' command in PATH**    
2. command line developer tools
3. homebrew, install at `/opt/homebrew/`

    origin PATH changed for `brew` command
    ```bash
    $ echo $PATH
    /usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin


    ==> Next steps:
    - Run these two commands in your terminal to add Homebrew to your PATH:
    echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> /Users/daniel/.zprofile
    eval "$(/opt/homebrew/bin/brew shellenv)"

    $ echo $PATH
    /opt/homebrew/bin:/opt/homebrew/sbin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin

    $ brew --version
    Homebrew 3.4.11

    $ brew analytics off
    $ brew analytics state 
    Analytics are disabled.

    ```

4. Java
    ```bash
    $ ls /Library/Java/JavaVirtualMachines
    
    ```
    