package ${classPath};

public class ${className} {
    <#if columns??>
         <#list columns as col>
             <#if col.type?? && col.type == 'int'>
    private ${col.type} ${col.name};

             </#if>
         </#list>
    </#if>

<#if columns??>
    <#list columns as col>
        <#if col.type?? && col.type == 'int'>
    public ${col.type} get${col.name?cap_first}(){
        return this.${col.name};
    }

     public void set${col.name?cap_first}(${col.type} ${col.name}){
         this.${col.name} = ${col.name};
     }
        </#if>
    </#list>
</#if>
}