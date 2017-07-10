function doSubmit(myForm, myAction){
    myForm.action = myAction;
    myForm.submit();
}

function initProcess(){}
/*
 * 设置HTML表单的提交路径(action)
 */
function setAction(path, formno){
    if( formno == null ){
       document.forms[0].action=path;
    } else {
       document.forms[formno].action=path;
    }
}

/*
 * 选中一个HTML页面中的所有checkbox
 */
var checkflag = "false";
var checkflag2 = "false";
function selectCheckAll(field) {
     if(field == null) return;
     var flag="false";
     if (checkflag == "false") {
          for (i = 0; i < field.length; i++) {
               field[i].checked = true;
               flag="true";
          }
          checkflag = "true";
     }else {
          for (i = 0; i < field.length; i++) {
             field[i].checked = false;
             flag="true";
          }
          checkflag = "false";
     }
     if ((checkflag2 == "false") && (flag=="false")) {
          field.checked = true;
          checkflag2 = "true";
     }else if ((checkflag2 == "true") && (flag=="false")){
          field.checked=false;
          checkflag2 = "false";
     }
}

function selectAll(){
  for( j = 0; j < selectAll.arguments.length; j++ ) {
    col1 = selectAll.arguments[j];
    for(i = 0; i < col1.options.length; i++ ) {
      col1.options[i].selected = true;
    }
  } // end loop
  return true;
}

function move( col1, col2){
  index = col1.selectedIndex;
  if( index < 0 ){
    return true;
  }

  toMove = col1.options[col1.selectedIndex];
  opt = new Option(toMove.text, toMove.value, false, false);
  col1.options[col1.selectedIndex] = null;
  col2.options[col2.length] = opt;
  col2.selectedIndex = col2.length - 1;
  return true;
}

function moveOrder( col1, col2, asc){
  index = col1.selectedIndex;
  if( index < 0 ){
    return true;
  }

  toMove = col1.options[col1.selectedIndex];
  if(asc == true){
    opt = new Option(toMove.text.substr(0,toMove.text.length - 5) + " (升序)", toMove.value.substr(0, toMove.value.length - 1) + "1", false, false );
  }else{
    opt = new Option(toMove.text.substr(0,toMove.text.length - 5) + " (降序)", toMove.value.substr(0, toMove.value.length - 1) + "0", false, false );
  }
  col1.options[col1.selectedIndex ] = null;
  col2.options[col2.length] = opt;
  col2.selectedIndex = col2.length - 1;
  return true;
}

function remove(col1){
  col1.options[col1.selectedIndex] = null;
  return true;
}

function up(col1){
  index = col1.selectedIndex;
  if( index <= 0 ){
    return true;
  }

  toMoveX = col1.options[index - 1];
  toMoveY = col1.options[index];
  optX = new Option(toMoveX.text, toMoveX.value, false, false);
  optY = new Option(toMoveY.text, toMoveY.value, false, false);
  col1.options[index] = optX;
  col1.options[index - 1] = optY;
  col1.selectedIndex = index - 1;
  return true;
}

function down(col1){
  index = col1.selectedIndex;
  if(index + 1 >=  col1.options.length)
    return true;

  toMoveX = col1.options[index];
  toMoveY = col1.options[index + 1];
  optX = new Option(toMoveX.text, toMoveX.value, false, false);
  optY = new Option(toMoveY.text, toMoveY.value, false, false);
  col1.options[index] = optY;
  col1.options[index + 1] = optX;
  col1.selectedIndex = index + 1;

  return true;
}
//-->
