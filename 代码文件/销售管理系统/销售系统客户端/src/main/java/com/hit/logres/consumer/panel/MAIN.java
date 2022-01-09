package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.api.service.*;
import javafx.application.Application;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.List;

public class MAIN {

    private static String zookeeperHost = System
            .getProperty("zookeeper.address", "150.158.52.158");
    private static String zookeeperPort = System.getProperty("zookeeper.port",
            "2182");

    private static String address = "dubbo://150.158.52.158:28888";

    public static void main(String[] args){

        ReferenceConfig<CustomerService> referenceCs = new ReferenceConfig<>();
        referenceCs.setApplication(new ApplicationConfig("consumer"));
        referenceCs.setUrl(address);
        referenceCs.setInterface(CustomerService.class);
        Starter.cs = referenceCs.get();

        ReferenceConfig<GoodService> referenceGs = new ReferenceConfig<>();
        referenceGs.setApplication(new ApplicationConfig("consumer"));
        referenceGs.setUrl(address);
        referenceGs.setInterface(GoodService.class);
        Starter.gs = referenceGs.get();

        ReferenceConfig<InventoryService> referenceIts = new ReferenceConfig<>();
        referenceIts.setApplication(new ApplicationConfig("consumer"));
        referenceIts.setUrl(address);
        referenceIts.setInterface(InventoryService.class);
        Starter.its = referenceIts.get();

        ReferenceConfig<InvoiceService> referenceIs = new ReferenceConfig<>();
        referenceIs.setApplication(new ApplicationConfig("consumer"));
        referenceIs.setUrl(address);
        referenceIs.setInterface(InvoiceService.class);
        Starter.is = referenceIs.get();

        ReferenceConfig<InvoiceLineService> referenceIls = new ReferenceConfig<>();
        referenceIls.setApplication(new ApplicationConfig("consumer"));
        referenceIls.setUrl(address);
        referenceIls.setInterface(InvoiceLineService.class);
        Starter.ils = referenceIls.get();

        ReferenceConfig<PaymentService> referencePs = new ReferenceConfig<>();
        referencePs.setApplication(new ApplicationConfig("consumer"));
        referencePs.setUrl(address);
        referencePs.setInterface(PaymentService.class);
        Starter.ps = referencePs.get();

        ReferenceConfig<PurchaseService> referencePcs = new ReferenceConfig<>();
        referencePcs.setApplication(new ApplicationConfig("consumer"));
        referencePcs.setUrl(address);
        referencePcs.setInterface(PurchaseService.class);
        Starter.pcs = referencePcs.get();

        ReferenceConfig<UserService> referenceUs = new ReferenceConfig<>();
        referenceUs.setApplication(new ApplicationConfig("consumer"));
        referenceUs.setUrl(address);
        referenceUs.setInterface(UserService.class);
        Starter.us = referenceUs.get();

        ReferenceConfig<DraftService> referenceDs = new ReferenceConfig<>();
        referenceDs.setApplication(new ApplicationConfig("consumer"));
        referenceDs.setUrl(address);
        referenceDs.setInterface(DraftService.class);
        Starter.ds = referenceDs.get();

        ReferenceConfig<DraftLineService> referenceDls = new ReferenceConfig<>();
        referenceDls.setApplication(new ApplicationConfig("consumer"));
        referenceDls.setUrl(address);
        referenceDls.setInterface(DraftLineService.class);
        Starter.dls = referenceDls.get();

        ReferenceConfig<WarehouseService> referenceWs = new ReferenceConfig<>();
        referenceWs.setApplication(new ApplicationConfig("consumer"));
        referenceWs.setUrl(address);
        referenceWs.setInterface(WarehouseService.class);
        Starter.ws = referenceWs.get();

        Application.launch(Starter.class,args);
    }
}
