package io.renren.modules.app.entity;


import lombok.Data;

@Data
public class TemplateParam {
    private String key;
    private String value;

    public TemplateParam(String key,String value){
        this.key=key;
        this.value=value;
    }
}
