(function(window,undefined){
window.Sys = function (ua){
    var b = {
        ie: /msie/.test(ua) && !/opera/.test(ua),
        opera: /opera/.test(ua),
        safari: /webkit/.test(ua) && !/chrome/.test(ua),
        firefox: /firefox/.test(ua),
        chrome: /chrome/.test(ua)
    },vMark = "";
    for (var i in b) {
        if (b[i]) { vMark = "safari" == i ? "version" : i; break; }
    }
    b.version = vMark && RegExp("(?:" + vMark + ")[\\/: ]([\\d.]+)").test(ua) ? RegExp.$1 : "0";
    b.ie6 = b.ie && parseInt(b.version, 10) == 6;
    b.ie7 = b.ie && parseInt(b.version, 10) == 7;
    b.ie8 = b.ie && parseInt(b.version, 10) == 8;
    return b;
}(window.navigator.userAgent.toLowerCase());
window.Sys.ie6&&document.execCommand("BackgroundImageCache", false, true);
window.objetId = function(Id){
    return document.getElementById(Id);
};
window.addListener = function(element,e,fn){
    !element.events&&(element.events = {});
    element.events[e]&&(element.events[e][addListener.guid++]=fn)||(element.events[e] = {'0':fn});
    element.addEventListener?element.addEventListener(e,fn,false):element.attachEvent("on" + e,fn);
};
window.addListener.guid = 1;
window.removeListener = function(element,e,fn){
    var handlers = element.events[e],type;
    if(fn){
        for(type in handlers)
            if(handlers[type]===fn){
                element.removeEventListener?element.removeEventListener(e,fn,false):element.detachEvent("on" + e,fn);
                delete handlers[type];
            }
    }else{
        for(type in handlers){
            element.removeEventListener?element.removeEventListener(e,handlers[type],false):element.detachEvent("on" + e,handlers[type]);
            delete handlers[type];
        }
    }
};
window.setStyle = function(e,o){
    if(typeof o=="string")
        e.style.cssText=o;
    else
        for(var i in o)
            e.style[i] = o[i];
};
var slice = Array.prototype.slice;
window.Bind = function(object, fun) {
    var args = slice.call(arguments).slice(2);
    return function() {
            return fun.apply(object, args);
    };
};
window.BindAsEventListener = function(object, fun,args) {
    var args = slice.call(arguments).slice(2);
    return function(event) {
        return fun.apply(object, [event || window.event].concat(args));
    }
};
//copy from jQ
window.Extend = function(){
  var target = arguments[0] || {}, i = 1, length = arguments.length, deep = true, options;
  if ( typeof target === "boolean" ) {
    deep = target;
    target = arguments[1] || {};
    i = 2;
  }
  if ( typeof target !== "object" && Object.prototype.toString.call(target)!="[object Function]")
    target = {};
  for(;i<length;i++){
    if ( (options = arguments[ i ]) != null )
      for(var name in options){
        var src = target[ name ], copy = options[ name ];
        if ( target === copy )
          continue;
        if ( deep && copy && typeof copy === "object" && !copy.nodeType ){
          target[ name ] = arguments.callee( deep, src || ( copy.length != null ? [ ] : { } ), copy );
        }
        else if(copy !== undefined)
          target[ name ] = copy;
      }
  }
  return target;
};
window.objPos = function(o){
  var x = 0, y = 0;
  do{x += o.offsetLeft;y += o.offsetTop;}while((o=o.offsetParent));
  return {'x':x,'y':y};
}
window.Class = function(properties){
    var _class = function(){return (arguments[0] !== null && this.initialize && typeof(this.initialize) == 'function') ? this.initialize.apply(this, arguments) : this;};
    _class.prototype = properties;
    return _class;
};
})(window);
var Table = new Class({
    options :{
        minWidth : 62
    },
    initialize : function(tab,set){
        this.table      = tab;
        this.rows       = [];             //里面记录所有tr的引用
        this.sortCol    = null;           //记录哪列正在排序中
        this.inputtd    = null;           //记录哪个td正在被编辑了
        this.editconfig = {};             //编辑表格的规则和提示
        this.thead      = tab.getElementsByTagName('thead')[0];
        this.theadTds   = tab.getElementsByTagName('thead')[0].getElementsByTagName('td'); //常常用到的dom集合可以用个属性来引用
        this.tbodyTds   = tab.getElementsByTagName('tbody')[0].getElementsByTagName('td');
        this.closConfig = {
            td    : null,
            totd  : null
        };
    this.widthConfig = {
      td          : null,
      nexttd      : null,
      x           : 0,
      tdwidth     : 0,
      nexttdwidth : 0
    };
    Extend(this,this.options);
    //不知道原因 反正不设置就会乱跳
    (Sys.ie6||Sys.chrome)&&(tab.width=tab.offsetWidth)
         //记录那些checkbox,radio被选中了   ie6在做dom操作的时候不会记住这些状态
        if(Sys.ie6){
            this.checkbox = {};
            var checkboxs = tab.getElementsByTagName('input'),i=0,l=checkboxs.length;
            for(;i<l;i++)
                (checkboxs[i].type=="checkbox"||checkboxs[i].type=="radio")&&
                addListener(checkboxs[i],"click",Bind(this,function(elm,i){
                    elm.checked==true?(this.checkbox[i] = elm):(delete this.checkbox[i]);
                },checkboxs[i],i));
        };
        var i=0,l=set.length,rows =tab.tBodies[0].rows,d=document,tabTads=tab.getElementsByTagName('td'),length=this.theadTds.length;
        //编辑用的input
    this.input = d.createElement('input');
        this.input.type = "text";
        this.input.className = 'edit';
        //用于显示正在拖拽的div
        this.div = d.body.appendChild(d.createElement('div'));
        this.div.className ="div";
    //进行缩放的时候显示的竖线
    this.line = d.body.appendChild(d.createElement('div'));
    this.line.className = 'line';
    this.line.style.top = objPos(tab).y +"px";
    //遍历set 做一些设置
        for(;i<l;i++){
            //排序事件
            //addListener(this.theadTds[set[i].id],'click',Bind(this,this.sortTable,this.theadTds[set[i].id],set[i].type));
            //给需要编辑的表给列定义所需配置
            set[i].edit&&(this.editconfig[set[i].id]={rule:set[i].edit.rule,message:set[i].edit.message});
        }
        //把 所有的tr放到一个数组 用于排序
        for( i=0,l=rows.length;i<l;i++)
            this.rows[i]=rows[i];

                   //遍历所有的td 做一些设置
        for( i=0,l=tabTads.length;i<l;i++){
            //将头部的td全部做上标记 拖拽的时候要用到
            i<length&&tabTads[i].setAttribute('clos',i);
            //将需要编辑的td添加edit属性
            i>=length&&this.editconfig[i%length]&&tabTads[i].setAttribute('edit',i%length);
        }

        //绑定 拖拽 和缩放的操作
        addListener(this.thead,'mousedown',BindAsEventListener(this,this.dragOrWidth));

        //拖拽的时候 记录移动到了那列td上
        addListener(this.thead,'mouseover',BindAsEventListener(this,this.theadHover));
        //当离开input时候保存下修改的内容
        addListener(this.input,'blur',Bind(this,this.save,this.input));
    },
    sortTable :function(td,type){ //td为点击的那个元素 n 为哪一列进行排序 type为进行什么类型的排序
        var frag=document.createDocumentFragment(),span=td.getElementsByTagName('span')[0],str= span.innerHTML;
        if(td===this.sortCol){
            this.rows.reverse();
            span.innerHTML =str.replace(/.$/,str.charAt(str.length-1)=="▼"?"▲":"▼") ;
        }else{
            this.rows.sort(this.compare(td.getAttribute('clos'),type));
            span.innerHTML = span.innerHTML + "▲";
            this.sortCol!=null&&(this.sortCol.getElementsByTagName('span')[0].innerHTML = this.sortCol.getElementsByTagName('span')[0].innerHTML.replace(/.$/,''));//把之前那列排序的标识去掉
        };
        for(var i=0,l=this.rows.length;i<l;i++)
            frag.appendChild(this.rows[i]);
        this.table.tBodies[0].appendChild(frag);
        if(Sys.ie6){
            for(var s in this.checkbox)
                this.checkbox[s].checked = true;
        }
        this.sortCol = td;   //记录哪一列正在排序中
    },
    compare :function(n,type){
    return function (a1,a2){
      var convert ={
        int    : function(v){return parseInt(v)},
        float  : function(v){return parseFloat(v)},
        date   : function(v){return v.toString()},
        string : function(v){return v.toString()}
      };
      !convert[type]&&(convert[type]=function(v){return v.toString()});
      a1 =convert[type](a1.cells[n].innerHTML);
      a2 =convert[type](a2.cells[n].innerHTML);
      return a1==a2?0:a1<a2?-1:1;
    };
    },
    edit: function(e){
        var elem = this.inputtd=e.srcElement || e.target;
        if(!elem.getAttribute('edit'))return;
        this.input.value = elem.innerHTML;
        elem.innerHTML = "";
        elem.appendChild(this.input);
        this.input.focus();
    },
    save : function(elem){
    var editinfo=this.editconfig[elem.parentNode.getAttribute('edit')],status={
      "[object Function]" : 'length' in editinfo.rule&&editinfo.rule(this.input.value)||false,
      "[object RegExp]"   : 'test' in editinfo.rule&&editinfo.rule.test(this.input.value)||false
    }[Object.prototype.toString.call(editinfo.rule)];
    //如果不符合条件  修改提示信息
    typeof status != "boolean"&&(editinfo.message = status);
    if(status===true){
      this.inputtd.innerHTML = this.input.value;
      this.inputtd=null;
    }else{
      alert(editinfo.message);
      this.input.focus();
    }
    },
    theadHover  : function(e){
        var elem = e.srcElement || e.target;
        if(elem.nodeName.toLowerCase() ==='td')
            this.closConfig.totd = elem.getAttribute('clos');
    },
    dragOrWidth : function(e){
        var elem = e.srcElement || e.target,widthConfig=this.widthConfig;

        //执行拖拽
        if(elem.nodeName.toLowerCase()==='td'){
            this.closConfig.td = elem.getAttribute('clos');
            addListener(document,'mousemove',BindAsEventListener(this,this.dragMove));
            addListener(document,'mouseup',Bind(this,this.dragUp));
        }

    //执行宽度缩放
    if(elem.nodeName.toLowerCase()==='div'){
      Sys.ie?(e.cancelBubble=true):e.stopPropagation();
      //如果是最后一个td里面的div 不进行缩放
      if(this.theadTds[this.theadTds.length-1]===elem.parentNode)return
      widthConfig.x = e.clientX;
      widthConfig.td = elem.parentNode;
      widthConfig.nexttd = widthConfig.td.nextSibling;
      while(widthConfig.nexttd.nodeName.toLowerCase()!="td"){
         widthConfig.nexttd = widthConfig.nexttd.nextSibling;
      };
      widthConfig.tdwidth     = widthConfig.td.offsetWidth;
      widthConfig.nexttdwidth = widthConfig.nexttd.offsetWidth;
      this.line.style.height  = this.table.offsetHeight +"px";
      addListener(document,'mousemove',BindAsEventListener(this,this.widthMove));
      addListener(document,'mouseup',Bind(this,this.widthUp));
    }
    },
    dragMove : function(e){
        window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
        setStyle(this.div,{display:"block",left:e.clientX+9+"px",top:e.clientY+20+"px"});
    },
    dragUp :function(){
        var closConfig = this.closConfig,rows = this.table.getElementsByTagName('tr'),td,n,o,i=0,l=rows.length;
    this.div.style.display = "none";
        removeListener(document,'mousemove');
        removeListener(document,'mouseup');
        //在同一列 不进行列替换
        //if(closConfig.td === closConfig.totd)return;

    //进行列替换 如果
    /** if(closConfig.td*1+1===closConfig.totd*1){
      n = closConfig.totd;
      o = closConfig.td;
    }else{
      n = closConfig.td;
      o = closConfig.totd;
    }
        for(;i<l;i++){
            td = rows[i].getElementsByTagName('td');
            rows[i].insertBefore(td[n],td[o]);
        }
		**/
        //重新标识表头
        for(i=0,l=this.theadTds.length;i<l;i++)
            this.theadTds[i].setAttribute('clos',i);
    },
  widthMove : function(e){
    window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
    var widthConfig = this.widthConfig,x = e.clientX - widthConfig.x,left = e.clientX,clientX=left;
    if(clientX<widthConfig.x&&widthConfig.x - clientX>widthConfig.tdwidth-this.minWidth){
      left = widthConfig.x - widthConfig.tdwidth+this.minWidth;
    }
    if(clientX>widthConfig.x&&clientX - widthConfig.x>widthConfig.nexttdwidth-this.minWidth){
      left =widthConfig.x + widthConfig.nexttdwidth-this.minWidth;
    }
    setStyle(this.line,{display:"block",left:left+"px"});
  },
  widthUp : function(){
    this.line.style.display = "none";

    var widthConfig = this.widthConfig,x= parseInt(this.line.style.left) - widthConfig.x;

    widthConfig.nexttd.style.width = widthConfig.nexttdwidth -x -1 +'px';
    widthConfig.td.style.width = widthConfig.tdwidth + x -1 +'px';
    removeListener(document,'mousemove');
    removeListener(document,'mouseup');
  }
});
window.onload = function(){
    function checkName(val){
        if(val.replace(/^\s+$/g,'')==='') return '姓名输入不能为空';
        if(val.replace(/^\s+|\s+$/,'').length>10) return '姓名长度不能大于10个字符';
        if(!/^[\u4e00-\u9fa5a-z]+$/i.test(val)) return '姓名只能输入中文或者是字母';
        return true;
    };
  function checkRemark(val){
    if(val.replace(/^\s+$/g,'')==='') return '备注输入不能为空';
    if(val.replace(/^\s+|\s+$/,'').length>15) return '备注长度不能大于15个字符';
    if(!/^[\u4e00-\u9fa5\w\s]+$/i.test(val)) return '备注只能输入中文数字下划线空格';
    return true;
  }
    var set = [
        {id:0,type:"int"},
        {id:2,type:"string",edit:{rule:checkName,message:''}},
        {id:3,type:"date",edit:{rule:/^\d{4}\-\d{2}\-\d{2}$/,message:"按这中格式输入日期 1985-02-30"}},
        {id:4,type:"string",edit:{rule:checkRemark,message:''}}
    ];
    new Table(objetId("data_list"),set);
}

function data_list(o,a,b,c,d){
var t	;
  if(document.getElementById(o)!=null)
  {
 	t=document.getElementById(o).getElementsByTagName("tr");
 	for(var i=0;i<t.length;i++){
  	t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
/**
	点击变色
   t[i].onclick=function(){
  if(this.x!="1"){
    this.x="1";
    //this.style.backgroundColor=d;
  }else{
    this.x="0";
    //this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
  }
  }**/
  t[i].onmouseover=function(){
  if(this.x!="1")this.style.backgroundColor=c;
  }
  t[i].onmouseout=function(){
  if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
  }
	}
  }


}
function chg(id_num){
  var oa = document.getElementById(id_num);
  var ob = document.getElementById("test_a");
  if(oa.style.display == "table-row"){
     oa.style.display = "none";
  }else{
     oa.style.display = "table-row";
  }
  return false;
}