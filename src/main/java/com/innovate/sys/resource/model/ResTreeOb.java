package com.innovate.sys.resource.model;

public class ResTreeOb {

	public static final String ROOT_ID = "-1";
	private String id;
	private String pId;
	private String name;
	private boolean open;
	private boolean checked;
	private String  menuType;

	/*
	 * var zNodes =[ { id:1, pId:0, name:"随意勾选 1", open:true}, { id:11, pId:1,
	 * name:"随意勾选 1-1", open:true}, { id:111, pId:11, name:"随意勾选 1-1-1"}, {
	 * id:112, pId:11, name:"随意勾选 1-1-2"}, { id:12, pId:1, name:"随意勾选 1-2",
	 * open:true}, { id:121, pId:12, name:"随意勾选 1-2-1"}, { id:122, pId:12,
	 * name:"随意勾选 1-2-2"}, { id:2, pId:0, name:"随意勾选 2", checked:true,
	 * open:true}, { id:21, pId:2, name:"随意勾选 2-1"}, { id:22, pId:2,
	 * name:"随意勾选 2-2", open:true}, { id:221, pId:22, name:"随意勾选 2-2-1",
	 * checked:true}, { id:222, pId:22, name:"随意勾选 2-2-2"}, { id:23, pId:2,
	 * name:"随意勾选 2-3"} ];
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public static ResTreeOb getRootTreeObj(String name)
	{
		ResTreeOb root = new ResTreeOb();
		root.setName(name);
		root.setId(ROOT_ID);
		root.setOpen(true);
		
		return root;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}
