1.聊天集成入口
MyFragment.java
((HomeNewSunActivity) getActivity()).startImLogin(info.getLogin());

2.拍照路径修改
SelectImageActivity.java
直接用path = Common.takePhoto1(this, IConstant.CAMERA);


3.MessageActivity.java
聊天信息页面

