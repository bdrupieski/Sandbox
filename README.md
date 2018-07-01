# Sandbox

I use this repository as a place to dump and keep track of things I try out.

I used to have separate ScalaPlayground and JavaSandbox repositories, but decided to
merge them all into one repository so I wouldn't end up with a repository per
language. I retained the commit history of both of those repositories in this one
by first putting all of the files in those repositories into `scala` and `java` folders 
respectively to eliminate the possibility of merge conflicts, and then did something
like this:

```
cd C:\src\Sandbox
git remote add ScalaPlayground C:\src\ScalaPlayground
git fetch ScalaPlayground
git merge --allow-unrelated-histories ScalaPlayground/master
git remote remove ScalaPlayground
git remote add JavaSandbox C:\src\JavaSandbox
git fetch JavaSandbox
git merge --allow-unrelated-histories JavaSandbox/master
git remote remove JavaSandbox
```
