package com.shcem.DistributionLock;//package com.shcem.DistributionLock;
//
//import com.shcem.annotation.CacheLock;
//import com.shcem.annotation.DistributionLockedObject;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//
///**
// * Created by judysen on 2017/4/4.
// */
//public class CacheLockInterceptor implements InvocationHandler {
//    /**
//     * Processes a method invocation on a proxy instance and returns
//     * the result.  This method will be invoked on an invocation handler
//     * when a method is invoked on a proxy instance that it is
//     * associated with.
//     *
//     * @param proxy  the proxy instance that the method was invoked on
//     * @param method the {@code Method} instance corresponding to
//     *               the interface method invoked on the proxy instance.  The declaring
//     *               class of the {@code Method} object will be the interface that
//     *               the method was declared in, which may be a superinterface of the
//     *               proxy interface that the proxy class inherits the method through.
//     * @param args   an array of objects containing the values of the
//     *               arguments passed in the method invocation on the proxy instance,
//     *               or {@code null} if interface method takes no arguments.
//     *               Arguments of primitive types are wrapped in instances of the
//     *               appropriate primitive wrapper class, such as
//     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
//     * @return the value to return from the method invocation on the
//     * proxy instance.  If the declared return type of the interface
//     * method is a primitive type, then the value returned by
//     * this method must be an instance of the corresponding primitive
//     * wrapper class; otherwise, it must be a type assignable to the
//     * declared return type.  If the value returned by this method is
//     * {@code null} and the interface method's return type is
//     * primitive, then a {@code NullPointerException} will be
//     * thrown by the method invocation on the proxy instance.  If the
//     * value returned by this method is otherwise not compatible with
//     * the interface method's declared return type as described above,
//     * a {@code ClassCastException} will be thrown by the method
//     * invocation on the proxy instance.
//     * @throws Throwable the exception to throw from the method
//     *                   invocation on the proxy instance.  The exception's type must be
//     *                   assignable either to any of the exception types declared in the
//     *                   {@code throws} clause of the interface method or to the
//     *                   unchecked exception types {@code java.lang.RuntimeException}
//     *                   or {@code java.lang.Error}.  If a checked exception is
//     *                   thrown by this method that is not assignable to any of the
//     *                   exception types declared in the {@code throws} clause of
//     *                   the interface method, then an
//     *                   {@link } containing the
//     *                   exception that was thrown by this method will be thrown by the
//     *                   method invocation on the proxy instance.
//     * @see
//     */
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
//        //没有cacheLock注解，pass
//        if(null == cacheLock){
//            System.out.println("no cacheLock annotation");
//            return method.invoke(proxied, args);
//        }
//
//        //获得方法中参数的注解
//        Annotation[][] annotations = method.getParameterAnnotations();
//        //根据获取到的参数注解和参数列表获得加锁的参数
//        Object lockedObject = getLockedObject(annotations,args);
//        String objectValue = lockedObject.toString();
//        DistributionRedisLock lock = new DistributionRedisLock(cacheLock.lockedPrefix(), objectValue);
//        boolean result = lock.lock(cacheLock.timeout(), cacheLock.expireTime());
//        if(!result){//取锁失败
//            ERROR_COUNT += 1;
//            throw new CacheLockException("get lock fail");
//        }
//        try{
//            //执行方法
//            return method.invoke(proxied, args);
//        }finally{
//            System.out.println("intecepor 释放锁");
//            lock.unlock();//释放锁
//        }
//    }
//    /**
//     *  从方法参数中找出@lockedComplexOnbject的参数，在redis中取该参数对应的锁
//     * @param annotations
//     * @param args
//     * @return
//     * @throws CacheLockException
//     */
//    private Object getLockedObject(Annotation[][] annotations,Object[] args) throws CacheLockException{
//        if(null == args || args.length == 0){
//            throw new CacheLockException("方法参数为空，没有被锁定的对象");
//        }
//
//        if(null == annotations || annotations.length == 0){
//            throw new CacheLockException("没有被注解的参数");
//        }
//        //不支持多个参数加锁，只支持第一个注解为lockedObject或者lockedComplexObject的参数
//        int index = -1;//标记参数的位置指针
//        for(int i = 0;i < annotations.length;i++){
//            for(int j = 0;j < annotations[i].length;j++){
//                if(annotations[i][j] instanceof LockedComplexObject){//注解为LockedComplexObject
//                    index = i;
//                    try {
//                        return args[i].getClass().getField(((LockedComplexObject)annotations[i][j]).field());
//                    } catch (Exception e) {
//                        throw new CacheLockException("注解对象中没有该属性" + ((LockedComplexObject)annotations[i][j]).field());
//                    }
//                }
//
//                if(annotations[i][j] instanceof DistributionLockedObject){
//                    index = i;
//                    break;
//                }
//            }
//            //找到第一个后直接break，不支持多参数加锁
//            if(index != -1){
//                break;
//            }
//        }
//
//        if(index == -1){
//            throw new CacheLockException("请指定被锁定参数");
//        }
//
//        return args[index];
//    }
//
//    public static int ERROR_COUNT  = 0;
//    private Object proxied;
//
//
//    public CacheLockInterceptor(Object proxied) {
//        this.proxied = proxied;
//    }
//}
