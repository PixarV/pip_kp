//package com.itmo.probe;
//import java.util.Map;
//
//import javax.faces.component.UIViewRoot;
//import javax.faces.context.FacesContext;
//
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.config.Scope;

/**
 * The JSF view scope ported to spring *
 */
//public class ViewScope implements Scope {
//
//    @Override
//    public Object get(String name, ObjectFactory<?> objectFactory) {
//        FacesContext currentInstance = FacesContext.getCurrentInstance();
//        final UIViewRoot viewRoot = currentInstance.getViewRoot();
//        Map<String, Object> viewMap = viewRoot.getViewMap();
//        if (viewMap.containsKey(name)) {
//            return viewMap.get(name);
//        } else {
//            Object object = objectFactory.getObject();
//            viewMap.put(name, object);
//
//            return object;
//        }
//    }
//
//    @Override
//    public String getConversationId() {
//        return null;
//    }
//
//    @Override
//    public void registerDestructionCallback(String arg0, Runnable arg1) {
//
//    }
//
//    @Override
//    public Object remove(String name) {
//        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
//    }
//
//    @Override
//    public Object resolveContextualObject(String arg0) {
//        return null;
//    }
//
//}
