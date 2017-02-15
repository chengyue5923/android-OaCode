package com.idxk.mobileoa.utils.common.android;

import com.idxk.mobileoa.config.enums.HttpMethod;
import com.idxk.mobileoa.model.bean.HttpConfigBean;
import com.idxk.mobileoa.model.bean.NetNofityBean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;

/**
 *dom解析
 */
public class DomManager {




    public static HashMap<Integer,HttpConfigBean>  paseFromUrl(InputStream stream){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        HashMap<Integer,HttpConfigBean> beans = new HashMap<Integer,HttpConfigBean>() ;
        try {
            //生成DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            //调用完这句后XML文档解析完成，暂存在内存中
            Document document = builder.parse(stream);
            //获得根元素
            Element root = document.getDocumentElement();
            //匹配结点，返回结点集
            NodeList personNodes = root.getElementsByTagName("interface");

            for(int i=0;i<personNodes.getLength();i++){
                HttpConfigBean bean = new HttpConfigBean();
                Element personElement = (Element)personNodes.item(i);
                bean.setId(Integer.parseInt(personElement.getAttribute("id")));
                //获得结点上的属性
                NodeList childsNodes = personElement.getChildNodes();
                for (int j = 0; j < childsNodes.getLength(); j++) {
                      Node node =  childsNodes.item(j);
                    //判断是否为元素类型
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element childNode = (Element) node;
                        String key = childNode.getNodeName();
                        if(childNode.getFirstChild()!=null){
                            String value = childNode.getFirstChild().getNodeValue();
                            if (key.equals("actioin")){
//                                if()
                                if(bean.getId()==38|bean.getId()==39||bean.getId()==40||bean.getId()==41){
                                    bean.setActioin(value.replace("!","/"));
                                }else{
                                    bean.setActioin(value.replace("!","&"));
                                }

                                continue;
                            }
                            if (key.equals("timeout")){
                                Logger.e("----"+value);
                                bean.setTimeout(Long.parseLong(value));
                                continue;
                            }
                            if (key.equals("method")){
                                int method =Integer.parseInt(value);
                                bean.setMethod(HttpMethod.getMethodByTypt(method));
                                continue;
                            }
                            if (key.equals("cach")){
                                bean.setCach(Boolean.parseBoolean(value));
                                continue;
                            }
                            if (key.equals("https")){
                                bean.setHttps(Boolean.parseBoolean(value));
                                continue;
                            }
                            if (key.equals("header")){
                                bean.setHeader(Boolean.parseBoolean(value));
                                continue;
                            }
                            if (key.equals("needLogin")){
                                bean.setNeedLogin(Boolean.parseBoolean(value));
                                continue;
                            }
                            if (key.equals("showToast")){
                                bean.setShowToast(Boolean.parseBoolean(value));
                                continue;
                            }
                            if (key.equals("showLoadDialog")){
                                bean.setShowLoadDialog(Boolean.parseBoolean(value));
                                continue;
                            }
                            if (key.equals("showNotify")){
                                bean.setShowNotify(Boolean.parseBoolean(value));
                                continue;
                            }
                        }
                        if (key.equals("nofiy")){
                            String notifyId = childNode.getAttribute("id");
                            String show = childNode.getAttribute("show");
                            NetNofityBean net = new NetNofityBean();
                            net.setId(Integer.parseInt(notifyId));
                            net.setName(show);
                            bean.setNofity(net);
                            continue;
                        }
                }
              }
                beans.put(bean.getId(),bean);
            }

        }catch (Exception e){

            System.out.print(e.getLocalizedMessage());
            Logger.e(e.getLocalizedMessage(),e);

        }

        return beans;
    }



}
