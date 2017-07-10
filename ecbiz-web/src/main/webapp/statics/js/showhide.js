
function disp()
{
document.all.t01.style.display='none'
document.all.t02.style.display='none'
}
function advs()
{
document.all.advsearch.style.display='none'
document.all.comsearch.style.display='none'
}
function disbar()
{
document.all.topbar.style.display=''
document.all.leftbar.style.display=''
document.all.bottombar.style.display=''
}

function showitem(selects)
{
document.all.item01.style.display=''
document.all.item02.style.display=''
document.all.item03.style.display=''


var name=selects.options(selects.selectedIndex).value;
document.getElementById(name).style.display='none';
}

function explorenew(selects)
{
document.all.explore01.style.display='none'

var name=selects.options(selects.selectedIndex).value;
document.getElementById(name).style.display='';
}

function personnew(selects)
{
document.all.person01.style.display=''
document.all.person02.style.display=''
document.all.personall.style.display=''

var name=selects.options(selects.selectedIndex).value;
document.getElementById(name).style.display='none';
}

function rulesetting()
{
document.all.ruleauto.style.display='none'
}

function rulesetting2()
{
document.all.ruleauto.style.display=''
}















function area(selects)
{
document.all.area01.style.display='none'
document.all.area02.style.display='none'
document.all.areanone.style.display='none'

var name=selects.options(selects.selectedIndex).value;
document.getElementById(name).style.display='';
}

function industry(selects)
{
document.all.industry01.style.display='none'
document.all.industry02.style.display='none'
document.all.industry03.style.display='none'
document.all.industry04.style.display='none'
document.all.industrynone.style.display='none'

var name=selects.options(selects.selectedIndex).value;
document.getElementById(name).style.display='';
}
