# Tools for new iMac

## VScode

be able to type 'code' in any folder to start editing files in that folder.

`Cmd+Shift+P` and type **Shell Command: Install 'code' command in PATH**

## homebrew, install at `/opt/homebrew/`

OS origin PATH changed for `brew` command

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

## Java install with Homebrew and softlink to `/Library/Java/JavaVirtualMachines/openjdk.jdk`

```bash
$ ls /Library/Java/JavaVirtualMachines

$ brew install openjdk

For the system Java wrappers to find this JDK, symlink it with
    sudo ln -sfn /opt/homebrew/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk

openjdk is keg-only, which means it was not symlinked into /opt/homebrew,
because macOS provides similar software and installing this software in
parallel can cause all kinds of trouble.

If you need to have openjdk first in your PATH, run:
    echo 'export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"' >> ~/.zshrc

For compilers to find openjdk you may need to set:
    export CPPFLAGS="-I/opt/homebrew/opt/openjdk/include"
```

After symlink command

```bash
$ ls -il /Library/Java/JavaVirtualMachines
    openjdk.jdk -> /opt/homebrew/opt/openjdk/libexec/openjdk.jdk

$ java -version
    openjdk version "18.0.1" 2022-04-19
    OpenJDK Runtime Environment Homebrew (build 18.0.1+0)
    OpenJDK 64-Bit Server VM Homebrew (build 18.0.1+0, mixed mode, sharing)        
```
