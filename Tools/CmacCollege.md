# Cmac College

## Website， PageSpeed Insights

> **cmaccollege.ca**

Mobile 端样式，性能需要做改进，首页需要充实内容。二级页面需要完整内容，二级页面的内容都是空白。多语言支持。

**Mobile** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205204532.png" width="350">
**Desktop** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205204623.png" width="350">

> **ocot.ca**

如果没有CMS，可以考虑和 octech.ca 整合内容后停止更新，直接跳转到 octech.ca 网站，因为搜索CQE 排名靠前，需要外链或是引流到主网站

**Mobile** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205205154.png" width="350">
**Desktop** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205205225.png" width="350">

> **octech.ca**

可以作为加拿大的主网站，设计，内容都可以，Program 页面需要丰富，更加吸引用户，后期可加入在线购买。

后续新网站的开发完成后可以使用这个网址，所有的内容都可以导入到新版的网站

**Mobile** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205205704.png" width="350">
**Desktop** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205205750.png" width="350">

> **octec.ca**

可以考虑作为中国的主网站，再根据中国区的发展规划，更新内容和SEO

**Mobile** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205201709.png" width="350">
**Desktop** <img src="https://raw.githubusercontent.com/GuoenSu/HelloWorld/main/Images/20221205201710.png" width="350">

## Main points

1. 网络营销，Google Ads，SEO，需要区分目标客户群体，中国和加拿大，有不同的推广渠道，推广方式和内容
    - Canada: Google Ads, Twitter, Facebook, YouTube, WeChat, TikTok, Instagram
    - China: Baidu, B站，微信，Weibo, 自媒体等
2. 目前需要解决的问题
    - 配合Google Ads，以及各种销售，促销活动的**着陆页面Landing Page**，提高点击率和转化率
    - 所有网站的统一规划，管理，运营。包括Logo，设计风格，内容（图片，文字，视频，新闻）
    - 中国和加拿大不同的网站，不同的服务器部署，访问速度，内容的区分
    - 网站的CMS内容管理系统，多语言支持
    - 网站在线课程购买系统开发
    - 移动端系统的开发和优化
    - 开发技术的统一，方便开发，维护，管理
3. 校园数字化项目建设规划
    - 在线学习系统
    - 学生管理系统
    - 课程管理系统
    - 教师管理系统
    - 学生系统
    - 办公自动化系统
    - 财务系统

## 教务系统

> 1.学生管理模块

    1. 在校学生管理
        - 学生列表，搜索功能
        - 添加新学生：姓名 联系电话 Email 性别 出生年月 备注信息
        - 学生详细信息，班级信息，课表信息，消课记录
        - 学生报名课程记录，有缴费记录
        - 编辑学生信息
        - 学生报名，购买课程，有收款信息
    2. 意向学生管理（学生管理扩扩展），有顾问，跟踪，进展等信息
        - 意向学生学生列表，搜索功能
        - 添加新意向学生
        - 跟进记录
        - 转为正式学生
    3. 毕业学生管理
    4. 报名记录
        - 搜索，学员 开始时间 结束时间
        - 报名列表：课程 科目 购买课次 合约金额 实付金额 欠费 开始时间 有效期 退费金额 退费课次 退费说明

> 2.班级管理模块

    - 班级列表，搜索功能
    - 添加新班级
    - 编辑班级信息
    - 班级学生列表，
    - 学生管理，添加，删除学生
    - 签到管理，班级课表管理
    - 编辑班级信息

> 3.科目及课程管理模块

    - 科目管理，CRUD
    - 课程列表，搜索功能
    - 添加新课程
    - 编辑课程信息

> 4.排课计划管理模块，按照班级，开始结束日期，星期等安排课程计划

    - 新增排课计划: 班级 上课老师 助教 开始日期 结束日期 最多排课次数 消课次数 是否跳过节假日 星期 开始时间 结束时间 教室
    - 批量生成课次，把计划批量生成课次
    - 冲突检查, 检查排课是否有冲突 如果有则有提示 可查看冲突课程
    - 删除已生成课表
    - 排课列表，所有的排课计划列表，搜索功能

> 5.课次管理模块，由上面的排课计划生成的详细的具体到每一天的课次内容

    - 课表日历，以日历形式查看课程 可以月视图 周视图 日视图
    - 课次列表，搜索功能
    - 点名消课，可以给课次所属班级的所有学员进行点名,点名后可消课
    - 课次编辑，修改时间，上课地点，停课，恢复课程
    - 删除课次

> 6.组织和人员管理模块

    - 校区管理，CRUD
    - 教室管理，CRUD
    - 职务管理，CRUD
    - 员工管理，包括老师和所有公司员工，CRUD

> 7.系统管理

    1. 权限管理
        - 角色管理，编辑角色信息 查看角色列表 删除角色
        - 权限管理，给角色配置权限
        - 给员工设置角色，给人员配置角色
        - 从角色移除员工，给人员解除权限
    2. 数据字典管理
