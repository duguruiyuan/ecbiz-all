var fulongTreeConfig = {
	rootIcon        : 'statics/images/menu/sys.gif',//foldericon
	openRootIcon    : 'statics/images/menu/sys.gif',//openfoldericon
	folderIcon      : 'statics/images/menu/foldericon.gif',
	openFolderIcon  : 'statics/images/menu/openfoldericon.gif',
	fileIcon        : 'statics/images/menu/file.gif',
	iIcon           : 'statics/images/menu/I.gif',
	lIcon           : 'statics/images/menu/L.gif',
	lMinusIcon      : 'statics/images/menu/Lminus.gif',
	lPlusIcon       : 'statics/images/menu/Lplus.gif',
	tIcon           : 'statics/images/menu/T.gif',
	tMinusIcon      : 'statics/images/menu/Tminus.gif',
	tPlusIcon       : 'statics/images/menu/Tplus.gif',
	blankIcon       : 'statics/images/menu/blank.gif',
	defaultText     : 'Tree Item',
	defaultAction   : 'javascript:void(0);',
	defaultBehavior : 'classic',
	usePersistence	: true
};

var fulongTreeHandler = {
	idCounter : 0,
	idPrefix  : "fulong-tree-object-",
	all       : {},
	behavior  : null,
	selected  : null,
	onSelect  : null, /* should be part of tree, not handler */
	getId     : function() { return this.idPrefix + this.idCounter++; },
	toggle    : function (oItem) { this.all[oItem.id.replace('-plus','')].toggle(); },
	select    : function (oItem) { this.all[oItem.id.replace('-icon','')].select(); },
	focus     : function (oItem) { this.all[oItem.id.replace('-anchor','')].focus(); },
	blur      : function (oItem) { this.all[oItem.id.replace('-anchor','')].blur(); },
	keydown   : function (oItem, e) { return this.all[oItem.id].keydown(e.keyCode); },
	cookies   : new fulongCookie(),
	insertHTMLBeforeEnd	:	function (oElement, sHTML) {
		if (oElement.insertAdjacentHTML != null) {
			oElement.insertAdjacentHTML("BeforeEnd", sHTML)
			return;
		}
		var df;	// DocumentFragment
		var r = oElement.ownerDocument.createRange();
		r.selectNodeContents(oElement);
		r.collapse(false);
		df = r.createContextualFragment(sHTML);
		oElement.appendChild(df);
	}
};

/*
 * fulongCookie class
 */

function fulongCookie() {
	if (document.cookie.length) { this.cookies = ' ' + document.cookie; }
}

fulongCookie.prototype.setCookie = function (key, value) {
	document.cookie = key + "=" + escape(value);
}

fulongCookie.prototype.getCookie = function (key) {
	if (this.cookies) {
		var start = this.cookies.indexOf(' ' + key + '=');
		if (start == -1) { return null; }
		var end = this.cookies.indexOf(";", start);
		if (end == -1) { end = this.cookies.length; }
		end -= start;
		var cookie = this.cookies.substr(start,end);
		return unescape(cookie.substr(cookie.indexOf('=') + 1, cookie.length - cookie.indexOf('=') + 1));
	}
	else { return null; }
}

/*
 * fulongTreeAbstractNode class
 */

function fulongTreeAbstractNode(sText, sAction) {
	this.childNodes  = [];
	this.id     = fulongTreeHandler.getId();
	this.text   = sText || fulongTreeConfig.defaultText;
	this.action = sAction || fulongTreeConfig.defaultAction;
	this._last  = false;
	fulongTreeHandler.all[this.id] = this;
}

/*
 * To speed thing up if you're adding multiple nodes at once (after load)
 * use the bNoIdent parameter to prevent automatic re-indentation and call
 * the obj.ident() method manually once all nodes has been added.
 */

fulongTreeAbstractNode.prototype.add = function (node, bNoIdent) {
	node.parentNode = this;
	this.childNodes[this.childNodes.length] = node;
	var root = this;
	if (this.childNodes.length >= 2) {
		this.childNodes[this.childNodes.length - 2]._last = false;
	}
	while (root.parentNode) { root = root.parentNode; }
	if (root.rendered) {
		if (this.childNodes.length >= 2) {
			document.getElementById(this.childNodes[this.childNodes.length - 2].id + '-plus').src = ((this.childNodes[this.childNodes.length -2].folder)?((this.childNodes[this.childNodes.length -2].open)?fulongTreeConfig.tMinusIcon:fulongTreeConfig.tPlusIcon):fulongTreeConfig.tIcon);
			this.childNodes[this.childNodes.length - 2].plusIcon = fulongTreeConfig.tPlusIcon;
			this.childNodes[this.childNodes.length - 2].minusIcon = fulongTreeConfig.tMinusIcon;
			this.childNodes[this.childNodes.length - 2]._last = false;
		}
		this._last = true;
		var foo = this;
		while (foo.parentNode) {
			for (var i = 0; i < foo.parentNode.childNodes.length; i++) {
				if (foo.id == foo.parentNode.childNodes[i].id) { break; }
			}
			if (i == foo.parentNode.childNodes.length - 1) { foo.parentNode._last = true; }
			else { foo.parentNode._last = false; }
			foo = foo.parentNode;
		}
		fulongTreeHandler.insertHTMLBeforeEnd(document.getElementById(this.id + '-cont'), node.toString());
		if ((!this.folder) && (!this.openIcon)) {
			this.icon = fulongTreeConfig.folderIcon;
			this.openIcon = fulongTreeConfig.openFolderIcon;
		}
		if (!this.folder) { this.folder = true; this.collapse(true); }
		if (!bNoIdent) { this.indent(); }
	}
	return node;
}

fulongTreeAbstractNode.prototype.toggle = function() {
	if (this.folder) {
		if (this.open) { this.collapse(); }
		else { this.expand(); }
}	}

fulongTreeAbstractNode.prototype.select = function() {
	document.getElementById(this.id + '-anchor').focus();
}

fulongTreeAbstractNode.prototype.deSelect = function() {
	document.getElementById(this.id + '-anchor').className = '';
	fulongTreeHandler.selected = null;
}

fulongTreeAbstractNode.prototype.focus = function() {
	if ((fulongTreeHandler.selected) && (fulongTreeHandler.selected != this)) { fulongTreeHandler.selected.deSelect(); }
	fulongTreeHandler.selected = this;
	if ((this.openIcon) && (fulongTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.openIcon; }
	document.getElementById(this.id + '-anchor').className = 'selected';
	document.getElementById(this.id + '-anchor').focus();
	if (fulongTreeHandler.onSelect) { fulongTreeHandler.onSelect(this); }
}

fulongTreeAbstractNode.prototype.blur = function() {
	if ((this.openIcon) && (fulongTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.icon; }
	document.getElementById(this.id + '-anchor').className = 'selected-inactive';
}

fulongTreeAbstractNode.prototype.doExpand = function() {
	if (fulongTreeHandler.behavior == 'classic') { document.getElementById(this.id + '-icon').src = this.openIcon; }
	if (this.childNodes.length) {  document.getElementById(this.id + '-cont').style.display = 'block'; }
	this.open = true;
	if (fulongTreeConfig.usePersistence) {
		fulongTreeHandler.cookies.setCookie(this.id.substr(18,this.id.length - 18), '1');
}	}

fulongTreeAbstractNode.prototype.doCollapse = function() {
	if (fulongTreeHandler.behavior == 'classic') { document.getElementById(this.id + '-icon').src = this.icon; }
	if (this.childNodes.length) { document.getElementById(this.id + '-cont').style.display = 'none'; }
	this.open = false;
	if (fulongTreeConfig.usePersistence) {
		fulongTreeHandler.cookies.setCookie(this.id.substr(18,this.id.length - 18), '0');
}	}

fulongTreeAbstractNode.prototype.expandAll = function() {
	this.expandChildren();
	if ((this.folder) && (!this.open)) { this.expand(); }
}

fulongTreeAbstractNode.prototype.expandChildren = function() {
	for (var i = 0; i < this.childNodes.length; i++) {
		this.childNodes[i].expandAll();
} }

fulongTreeAbstractNode.prototype.collapseAll = function() {
	this.collapseChildren();
	if ((this.folder) && (this.open)) { this.collapse(true); }
}

fulongTreeAbstractNode.prototype.collapseChildren = function() {
	for (var i = 0; i < this.childNodes.length; i++) {
		this.childNodes[i].collapseAll();
} }

fulongTreeAbstractNode.prototype.indent = function(lvl, del, last, level, nodesLeft) {
	/*
	 * Since we only want to modify items one level below ourself,
	 * and since the rightmost indentation position is occupied by
	 * the plus icon we set this to -2
	 */
	if (lvl == null) { lvl = -2; }
	var state = 0;
	for (var i = this.childNodes.length - 1; i >= 0 ; i--) {
		state = this.childNodes[i].indent(lvl + 1, del, last, level);
		if (state) { return; }
	}
	if (del) {
		if ((level >= this._level) && (document.getElementById(this.id + '-plus'))) {
			if (this.folder) {
				document.getElementById(this.id + '-plus').src = (this.open)?fulongTreeConfig.lMinusIcon:fulongTreeConfig.lPlusIcon;
				this.plusIcon = fulongTreeConfig.lPlusIcon;
				this.minusIcon = fulongTreeConfig.lMinusIcon;
			}
			else if (nodesLeft) { document.getElementById(this.id + '-plus').src = fulongTreeConfig.lIcon; }
			return 1;
	}	}
	var foo = document.getElementById(this.id + '-indent-' + lvl);
	if (foo) {
		if ((foo._last) || ((del) && (last))) { foo.src =  fulongTreeConfig.blankIcon; }
		else { foo.src =  fulongTreeConfig.iIcon; }
	}
	return 0;
}

/*
 * fulongTree class
 */

function fulongTree(sText, sAction, sBehavior, sIcon, sOpenIcon) {
	this.base = fulongTreeAbstractNode;
	this.base(sText, sAction);
	this.icon      = sIcon || fulongTreeConfig.rootIcon;
	this.openIcon  = sOpenIcon || fulongTreeConfig.openRootIcon;
	/* Defaults to open */
	if (fulongTreeConfig.usePersistence) {
		this.open  = (fulongTreeHandler.cookies.getCookie(this.id.substr(18,this.id.length - 18)) == '0')?false:true;
	} else { this.open  = true; }
	this.folder    = true;
	this.rendered  = false;
	this.onSelect  = null;
	if (!fulongTreeHandler.behavior) {  fulongTreeHandler.behavior = sBehavior || fulongTreeConfig.defaultBehavior; }
}

fulongTree.prototype = new fulongTreeAbstractNode;

fulongTree.prototype.setBehavior = function (sBehavior) {
	fulongTreeHandler.behavior =  sBehavior;
};

fulongTree.prototype.getBehavior = function (sBehavior) {
	return fulongTreeHandler.behavior;
};

fulongTree.prototype.getSelected = function() {
	if (fulongTreeHandler.selected) { return fulongTreeHandler.selected; }
	else { return null; }
}

fulongTree.prototype.remove = function() { }

fulongTree.prototype.expand = function() {
	this.doExpand();
}

fulongTree.prototype.collapse = function(b) {
	if (!b) { this.focus(); }
	this.doCollapse();
}

fulongTree.prototype.getFirst = function() {
	return null;
}

fulongTree.prototype.getLast = function() {
	return null;
}

fulongTree.prototype.getNextSibling = function() {
	return null;
}

fulongTree.prototype.getPreviousSibling = function() {
	return null;
}

fulongTree.prototype.keydown = function(key) {
	if (key == 39) {
		if (!this.open) { this.expand(); }
		else if (this.childNodes.length) { this.childNodes[0].select(); }
		return false;
	}
	if (key == 37) { this.collapse(); return false; }
	if ((key == 40) && (this.open) && (this.childNodes.length)) { this.childNodes[0].select(); return false; }
	return true;
}

fulongTree.prototype.toString = function() {
	var str = "<div id=\"" + this.id + "\" onclick=\"fulongTreeHandler.toggle(this);\" class=\"fulong-tree-item\" onkeydown=\"return fulongTreeHandler.keydown(this, event)\">" +
		"<img id=\"" + this.id + "-icon\" class=\"fulong-tree-icon\" src=\"" + ((fulongTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"fulongTreeHandler.select(this);\">" +
		"<a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"fulongTreeHandler.focus(this);\" onblur=\"fulongTreeHandler.blur(this);\"" +
		(this.target ? " target=\"" + this.target + "\"" : "") +
		">" + this.text + "</a></div>" +
		"<div id=\"" + this.id + "-cont\" class=\"fulong-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	var sb = [];
	for (var i = 0; i < this.childNodes.length; i++) {
		sb[i] = this.childNodes[i].toString(i, this.childNodes.length);
	}
	this.rendered = true;
	return str + sb.join("") + "</div>";
};

/*
 * fulongTreeItem class
 */

function fulongTreeItem(sText, sAction, eParent, sIcon, sOpenIcon) {
	this.base = fulongTreeAbstractNode;
	this.base(sText, sAction);
	/* Defaults to close */
	if (fulongTreeConfig.usePersistence) {
		this.open = (fulongTreeHandler.cookies.getCookie(this.id.substr(18,this.id.length - 18)) == '1')?true:false;
	} else { this.open = false; }
	if (sIcon) { this.icon = sIcon; }
	if (sOpenIcon) { this.openIcon = sOpenIcon; }
	if (eParent) { eParent.add(this); }
}

fulongTreeItem.prototype = new fulongTreeAbstractNode;

fulongTreeItem.prototype.remove = function() {
	var iconSrc = document.getElementById(this.id + '-plus').src;
	var parentNode = this.parentNode;
	var prevSibling = this.getPreviousSibling(true);
	var nextSibling = this.getNextSibling(true);
	var folder = this.parentNode.folder;
	var last = ((nextSibling) && (nextSibling.parentNode) && (nextSibling.parentNode.id == parentNode.id))?false:true;
	this.getPreviousSibling().focus();
	this._remove();
	if (parentNode.childNodes.length == 0) {
		document.getElementById(parentNode.id + '-cont').style.display = 'none';
		parentNode.doCollapse();
		parentNode.folder = false;
		parentNode.open = false;
	}
	if (!nextSibling || last) { parentNode.indent(null, true, last, this._level, parentNode.childNodes.length); }
	if ((prevSibling == parentNode) && !(parentNode.childNodes.length)) {
		prevSibling.folder = false;
		prevSibling.open = false;
		iconSrc = document.getElementById(prevSibling.id + '-plus').src;
		iconSrc = iconSrc.replace('minus', '').replace('plus', '');
		document.getElementById(prevSibling.id + '-plus').src = iconSrc;
		document.getElementById(prevSibling.id + '-icon').src = fulongTreeConfig.fileIcon;
	}
	if (document.getElementById(prevSibling.id + '-plus')) {
		if (parentNode == prevSibling.parentNode) {
			iconSrc = iconSrc.replace('minus', '').replace('plus', '');
			document.getElementById(prevSibling.id + '-plus').src = iconSrc;
}	}	}

fulongTreeItem.prototype._remove = function() {
	for (var i = this.childNodes.length - 1; i >= 0; i--) {
		this.childNodes[i]._remove();
 	}
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) {
			for (var j = i; j < this.parentNode.childNodes.length; j++) {
				this.parentNode.childNodes[j] = this.parentNode.childNodes[j+1];
			}
			this.parentNode.childNodes.length -= 1;
			if (i + 1 == this.parentNode.childNodes.length) { this.parentNode._last = true; }
			break;
	}	}
	fulongTreeHandler.all[this.id] = null;
	var tmp = document.getElementById(this.id);
	if (tmp) { tmp.parentNode.removeChild(tmp); }
	tmp = document.getElementById(this.id + '-cont');
	if (tmp) { tmp.parentNode.removeChild(tmp); }
}

fulongTreeItem.prototype.expand = function() {
	this.doExpand();
	document.getElementById(this.id + '-plus').src = this.minusIcon;
}

fulongTreeItem.prototype.collapse = function(b) {
	if (!b) { this.focus(); }
	this.doCollapse();
	document.getElementById(this.id + '-plus').src = this.plusIcon;
}

fulongTreeItem.prototype.getFirst = function() {
	return this.childNodes[0];
}

fulongTreeItem.prototype.getLast = function() {
	if (this.childNodes[this.childNodes.length - 1].open) { return this.childNodes[this.childNodes.length - 1].getLast(); }
	else { return this.childNodes[this.childNodes.length - 1]; }
}

fulongTreeItem.prototype.getNextSibling = function() {
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) { break; }
	}
	if (++i == this.parentNode.childNodes.length) { return this.parentNode.getNextSibling(); }
	else { return this.parentNode.childNodes[i]; }
}

fulongTreeItem.prototype.getPreviousSibling = function(b) {
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) { break; }
	}
	if (i == 0) { return this.parentNode; }
	else {
		if ((this.parentNode.childNodes[--i].open) || (b && this.parentNode.childNodes[i].folder)) { return this.parentNode.childNodes[i].getLast(); }
		else { return this.parentNode.childNodes[i]; }
} }

fulongTreeItem.prototype.keydown = function(key) {
	if ((key == 39) && (this.folder)) {
		if (!this.open) { this.expand(); }
		else { this.getFirst().select(); }
		return false;
	}
	else if (key == 37) {
		if (this.open) { this.collapse(); }
		else { this.parentNode.select(); }
		return false;
	}
	else if (key == 40) {
		if (this.open) { this.getFirst().select(); }
		else {
			var sib = this.getNextSibling();
			if (sib) { sib.select(); }
		}
		return false;
	}
	else if (key == 38) { this.getPreviousSibling().select(); return false; }
	return true;
}

fulongTreeItem.prototype.toString = function (nItem, nItemCount) {
	var foo = this.parentNode;
	var indent = '';
	if (nItem + 1 == nItemCount) { this.parentNode._last = true; }
	var i = 0;
	while (foo.parentNode) {
		foo = foo.parentNode;
		indent = "<img id=\"" + this.id + "-indent-" + i + "\" src=\"" + ((foo._last)?fulongTreeConfig.blankIcon:fulongTreeConfig.iIcon) + "\">" + indent;
		i++;
	}
	this._level = i;
	if (this.childNodes.length) { this.folder = 1; }
	else { this.open = false; }
	if ((this.folder) || (fulongTreeHandler.behavior != 'classic')) {
		if (!this.icon) { this.icon = fulongTreeConfig.folderIcon; }
		if (!this.openIcon) { this.openIcon = fulongTreeConfig.openFolderIcon; }
	}
	else if (!this.icon) { this.icon = fulongTreeConfig.fileIcon; }
	var label = this.text.replace(/</g, '&lt;').replace(/>/g, '&gt;');
	var str = "<div id=\"" + this.id + "\" onclick=\"fulongTreeHandler.toggle(this);\" class=\"fulong-tree-item\" onkeydown=\"return fulongTreeHandler.keydown(this, event)\">" +
		indent +
		"<img id=\"" + this.id + "-plus\" src=\"" + ((this.folder)?((this.open)?((this.parentNode._last)?fulongTreeConfig.lMinusIcon:fulongTreeConfig.tMinusIcon):((this.parentNode._last)?fulongTreeConfig.lPlusIcon:fulongTreeConfig.tPlusIcon)):((this.parentNode._last)?fulongTreeConfig.lIcon:fulongTreeConfig.tIcon)) + "\" ondblclick=\"fulongTreeHandler.toggle(this);\">" +
		"<img id=\"" + this.id + "-icon\" class=\"fulong-tree-icon\" src=\"" + ((fulongTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"fulongTreeHandler.select(this);\">" +
		"<a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"fulongTreeHandler.focus(this);\" onblur=\"fulongTreeHandler.blur(this);\"" +
		(this.target ? " target=\"" + this.target + "\"" : "") +
		">" + label + "</a></div>" +
		"<div id=\"" + this.id + "-cont\" class=\"fulong-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	var sb = [];
	for (var i = 0; i < this.childNodes.length; i++) {
		sb[i] = this.childNodes[i].toString(i,this.childNodes.length);
	}
	this.plusIcon = ((this.parentNode._last)?fulongTreeConfig.lPlusIcon:fulongTreeConfig.tPlusIcon);
	this.minusIcon = ((this.parentNode._last)?fulongTreeConfig.lMinusIcon:fulongTreeConfig.tMinusIcon);
	return str + sb.join("") + "</div>";
}