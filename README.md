# Calculator
使用栈数据结构 Java编程实现带括号的加减乘除计算器
### 规范

#### 添加数字（0,...,9以及"."）时的规范<p>
将显示屏的文本进行分割（regex = " "）后得到symbol字符串数组，下面将分类讨论：<p>
1. 若symbol的最后一个字符串是数字（非"0"）：<p>
   1. 添加的数字为"."的情况:<p>
      1. 若该数字不包含"."，则向显示屏添加"."<p>
      2. 若该数字已包含"."，则不再向显示屏添加"."<p>
   2. 添加的数字为0,..,9的其中之一，直接向显示屏添加"."<p>
2. 若symbol的最后一个字符串是数字"0"：<p>
   1. 添加的数字为"."，则直接向显示屏添加"."<p>
   2. 添加的数字为0,..,9的其中之一，先去除最后一个字符串"0"，再添加<p>
3. 若symbol的最后一个字符串是操作符<p>
   1. 添加的数字为"."的情况，则不向显示屏添加"."<p>
   2. 添加的数字为0,..,9的其中之一的情况：先加空格，在添加数字<p>
 
#### 添加操作符（"+"、"-"、"*"、"/"、"("、")"）时的规范<p>
将显示屏的文本进行分割（regex = " "）后得到symbol字符串数组，下面将分类讨论:<p>
1. 若symbol的最后一个字符串为数字：<p>
   1. 若该操作符为"("：<p>
      1. 若数字非"0"，则先添加空格，再添加"*"，再添加空格，最后添加该操作符<p>
      2. 若数字为"0"，则先删除最后一个字符串，再添加该操作符<p>
   2. 若该操作符非"("：<p>
      1. 若数字非"0"，则先添加空格，再添加该操作符<p>
      2. 若数字为"0"，则先添加空格，再添加该操作符<p>
2. 若symbol的最后一个字符串为操作符：<p>
   1. 若最后一个字符串为"("：<p>
      1. 若该操作符为"("或")"，则先添加空格，再添加该操作符<p>
      2. 若该操作符为其他，则先删除最后两个字符串(因为"("前面会有一个操作符)，再添加该操作符<p>
   1. 若最后一个字符串为")"，则先添加空格，再添加该操作符<p>
   2. 若最后一个字符串为其他：<p>
      1. 若该操作符为"("，则先添加空格，再添加该操作符<p>
      2. 若该操作符为其他，则先删除最后一个字符串，再添加该操作符<p>
  
### 效果图
<div align = center>
  <img src = "Oh_MyBug_Calculator\Calculator_1.jpg" width = 40%> <p>
</div>
<div align = center>
  <img src = "Oh_MyBug_Calculator\Calculator_2.jpg" width = 40%> <p>
</div>
