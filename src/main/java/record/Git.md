**Git全局配置**  
`git config --global user.name "刘永健"`  
`git config --global user.email "liuyongjian@sqhtech.com"`  

**Git 创建新的库**  
`git clone git@gitlab.songshushan.com:jellyfish/jellyfish.git`  
`cd jellyfish`  
`touch README.md`  
`git add README.md`  
`git commit -m "add README"`  
`git push -u origin master`  

**本地已存在项目文件夹**  
`cd existing_folder`  
`git init`  
`git remote add origin git@gitlab.songshushan.com:simon/simon-homepage.git`  
`git add .`  
`git commit -m "Initial commit"`  
`git push -u origin master`  


**已存在的Git项目**  
`cd existing_repo`  
`git remote rename origin old-origin`  
`git remote add origin git@gitlab.songshushan.com:simon/simon-homepage.git`  
`git push -u origin --all`  
`git push -u origin --tags`  

**创建分支**  
`git branch [branch]`  

**迁移分支**  
`git checkout [branch]`  

**查询分支**  
`git branch`  

**将分支push到远程**  
`git push --set-upstream origin [branch]`  

**删除远程分支**  
`git push origin --delete [branch]`  

**删除分支**  
`git branch -d [branch]`  

**合并分支**  
`git merge [branch]`  

**删除远程tags**  
`git push origin --delete tag [tagsName]`

**新建远程tags**  
`git push origin [tagsName] --tags`
