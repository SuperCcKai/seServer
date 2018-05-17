<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="_utils.loginSystem.RedisUtils" %>
<% 
	System.out.println("index.jsp");
	boolean flag = false;
	Cookie[] cookieArr = request.getCookies();
	if(cookieArr == null) {
		System.out.println("login cookie check error");
		response.sendRedirect("404.html");
	}
	for(Cookie cookie:cookieArr) {
		System.out.println("has cookie");
		flag = RedisUtils.loginCheck(cookie);
		if(flag) {
			System.out.println("login cookie is true");
			break;
		}
	}
	if(!flag) {
		System.out.println("login cookie check error");
		response.sendRedirect("404.html");
	}
%>
<!DOCTYPE html>
<html>
  <head>
    <title>欢迎登录时光日程系统</title>
    <meta name="description" content="this is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
    <style type="text/css">
    #header{
    background: #4876ff;
    height:100px;
    width:100%;
    }
    .picture1{
    position: absolute;
    top:15px;
    height: 90px;
    width:  90px;
    border-radius: 999px;
    }
    .picture11{
    position: absolute;
    top:15px;
    right: 10px;
    height: 90px;
    width:  90px;
    border-radius: 999px;
    }
    .toper{
    font-family: Lobster, Monospace;
    position: absolute;
    top:15px;
    left:120px;
    color:#ffffff;
    }
    .toper-1{
    font-family: Lobster, Monospace;
    position: absolute;
    top:50px;
    left:115px;
    color:#ffffff;
    }
    .toper-2{
    font-family: Lobster, Monospace;
    position: absolute;
    top:15px;
    right:110px;
    color:#ffffff;
    }
    .toper-3{
    font-family: Lobster, Monospace;
    position: absolute;
    top:50px;
    right:180px;
    color:#ffffff;
    }
     #headpicture{
    height:400px;
    width:100%;
    }
    .picture2{
    height: 400px;
    width:  100%;
    }
    #historyNotice{
    margin-top:30px;
    height: 600px;
    width:100%;
    /*background: #e0ffff;*/
    }
    div.left{
            margin:18px;
			width: 45%;
			float:left;
		}
    div.right{
            margin:18px;
			width: 45%;
			float:left;
		}
    
    .topic{
    margin:0;
    }
    .time{
    margin:0;
    }
    #footer{
    height:75px;
    width: 100%;
    background: #4876ff;
    }
    </style>
  </head>
  
  <body>
    <div id="header">
    <img class="picture1" alt="图片无法显示" src="images/地球.gif"> 
    <img class="picture11" alt="图片无法显示" src="images/校徽2.jpg"> 
     <h2 class="toper">时光日程</h2>
     <h3 class="toper-1">TimeCalendar</h3>
     <h3 class="toper-2">计算机科学与工程学院</h3>
     <h3 class="toper-3">梁媛</h3>
    </div>
   
    <div class = "nav">
    	<table width="100%" border="0" cellpadding="15" class="navtitle" 
    	 style="background-color:white" rules="none">
    		<tr> 
    		    <th>               </th>
    			<th>首页</th>
    			<th>班级课表</th>
    			<th>学余时间</th>
    			<th>发布通知</th>
    			<th>标签总览</th>
    			<th>天气信息</th>
    			<th>               </th>
    		</tr>
    	</table>
    </div>
    <div id="headpicture">
    <img class="picture2" alt="图片无法显示" src="images/景色7.jpg">
    </div>
    <div id="historyNotice">
    <div class = "left">
    <article>
     <p class="topic">主题</p>
     <p class="time"><small><b>发布日期</b></small></p>
      <p>历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史
          历史通知历史通知历史通知历史通知历史通知历史通知历史通知通知历史通知历史通知历史通知历史通知</p>
    </article>
    
     <article>
     <p class="topic">主题</p>
     <p class="time"><small><b>发布日期</b></small></p>
      <p>历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史
          历史通知历史通知历史通知历史通知历史通知历史通知历史通知通知历史通知历史通知历史通知历史通知</p>
    </article>
    
     <article>
     <p class="topic">主题</p>
     <p class="time"><small><b>发布日期</b></small></p>
      <p>历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史
          历史通知历史通知历史通知历史通知历史通知历史通知历史通知通知历史通知历史通知历史通知历史通知</p>
    </article>
    </div>
    <div class = "right">
     <article>
     <p class="topic">主题</p>
     <p class="time"><small><b>发布日期</b></small></p>
      <p>历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史
          历史通知历史通知历史通知历史通知历史通知历史通知历史通知通知历史通知历史通知历史通知历史通知</p>
    </article>
   
    <article>
     <p class="topic">主题</p>
     <p class="time"><small><b>发布日期</b></small></p>
      <p>历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史通知历史
          历史通知历史通知历史通知历史通知历史通知历史通知历史通知通知历史通知历史通知历史通知历史通知</p>
    </article>
    </div>
    
    
    </div>
     <div id="footer">
      <p>联系管理员</p>
    </div>
    <!-- <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <canvas height="926" width="1920" style="position: fixed; top: 0px; left: 0px; z-index: -1; opacity: 0.5;" id="c_n1"></canvas>
    <script>
! function() {
    function o(w, v, i) {
        return w.getAttribute(v) || i
    }

    function j(i) {
        return document.getElementsByTagName(i)
    }

    function l() {
        var i = j("script"),
            w = i.length,
            v = i[w - 1];
        return {
            l: w,
            z: o(v, "zIndex", -1),
            o: o(v, "opacity", 0.5),
            c: o(v, "color", "0,0,0"),
            n: o(v, "count", 99)
        }
    }

    function k() {
        r = u.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth, n = u.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
    }

    function b() {
        e.clearRect(0, 0, r, n);
        var w = [f].concat(t);
        var x, v, A, B, z, y;
        t.forEach(function(i) {
            i.x += i.xa, i.y += i.ya, i.xa *= i.x > r || i.x < 0 ? -1 : 1, i.ya *= i.y > n || i.y < 0 ? -1 : 1, e.fillRect(i.x - 0.5, i.y - 0.5, 1, 1);
            for (v = 0; v < w.length; v++) {
                x = w[v];
                if (i !== x && null !== x.x && null !== x.y) {
                    B = i.x - x.x, z = i.y - x.y, y = B * B + z * z;
                    y < x.max && (x === f && y >= x.max / 2 && (i.x -= 0.03 * B, i.y -= 0.03 * z), A = (x.max - y) / x.max, e.beginPath(), e.lineWidth = A / 2, e.strokeStyle = "rgba(" + s.c + "," + (A + 0.2) + ")", e.moveTo(i.x, i.y), e.lineTo(x.x, x.y), e.stroke())
                }
            }
            w.splice(w.indexOf(i), 1)
        }), m(b)
    }
    var u = document.createElement("canvas"),
        s = l(),
        c = "c_n" + s.l,
        e = u.getContext("2d"),
        r, n, m = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(i) {
            window.setTimeout(i, 1000 / 45)
        },
        a = Math.random,
        f = {
            x: null,
            y: null,
            max: 20000
        };
    u.id = c;
    u.style.cssText = "position:fixed;top:0;left:0;z-index:" + s.z + ";opacity:" + s.o;
    j("body")[0].appendChild(u);
    k(), window.onresize = k;
    window.onmousemove = function(i) {
        i = i || window.event, f.x = i.clientX, f.y = i.clientY
    }, window.onmouseout = function() {
        f.x = null, f.y = null
    };
    for (var t = [], p = 0; s.n > p; p++) {
        var h = a() * r,
            g = a() * n,
            q = 2 * a() - 1,
            d = 2 * a() - 1;
        t.push({
            x: h,
            y: g,
            xa: q,
            ya: d,
            max: 6
        })
    }
    setTimeout(function() {
        b()
    }, 100)
}();
</script> -->
  </body>
</html>
