package com.mor.client.dubbo.action;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.mor.server.dubbo.service.DemoServer;
import com.mor.server.dubbo.service.IMyService;

public class ChatAction {
    /**
     * 
     * @author wanggengqi
     * @date 2014年10月23日 下午3:13:04
     */
    public void SayHello(){ 
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
	context.start();
//	DemoServer demoServer = (DemoServer) context.getBean("demoService");
//	System.out.println("client:"+demoServer.sayHello("bym"));
//	
//	IMyService myService = (IMyService) context.getBean("myService");
//	System.out.println("client:"+myService.call("bym"));
	
	

	ApplicationConfig cfg = new ApplicationConfig("consumer-of-helloworld-app");

 
// 引用远程服务 
ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
reference.setInterface("com.mor.server.dubbo.service.IMyService"); // 弱类型接口名 
reference.setVersion("1.0.0"); 
reference.setGeneric(true); // 声明为泛化接口 
reference.setApplication(cfg);
reference.setUrl("127.0.0.1:2181");
GenericService genericService = reference.get(); // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用 
// 
// 基本类型以及Date,List,Map等不需要转换，直接调用 
Object result = genericService.$invoke("call", new String[] {"java.lang.String"}, new Object[] {"world"});
    }

}
