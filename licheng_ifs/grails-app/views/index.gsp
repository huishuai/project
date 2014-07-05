<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>
<html>
<head>
  <title>Welcome to Grails</title>
  <meta name="layout" content="main"/>
  <style type="text/css" media="screen">

  #nav {
    margin-top: 20px;
    margin-left: 30px;
    width: 228px;
    float: left;

  }

  .homePagePanel * {
    margin: 0px;
  }

  .homePagePanel .panelBody ul {
    list-style-type: none;
    margin-bottom: 10px;
  }

  .homePagePanel .panelBody h1 {
    text-transform: uppercase;
    font-size: 1.1em;
    margin-bottom: 10px;
  }

  .homePagePanel .panelBody {
    background: url(images/leftnav_midstretch.png) repeat-y top;
    margin: 0px;
    padding: 15px;
  }

  .homePagePanel .panelBtm {
    background: url(images/leftnav_btm.png) no-repeat top;
    height: 20px;
    margin: 0px;
  }

  .homePagePanel .panelTop {
    background: url(images/leftnav_top.png) no-repeat top;
    height: 11px;
    margin: 0px;
  }

  h2 {
    margin-top: 15px;
    margin-bottom: 15px;
    font-size: 1.2em;
  }

  #pageBody {
    margin-left: 20px;
    margin-right: 20px;
  }
  </style>
</head>
<body>
<div id="pageBody">
  <h1 align="center">综合前置系统</h1>
  <p>综合前置系统为统一的第三方接口系统，凡是涉及到地方放系统的集成接口都在这儿:</p>

  <div id="Socket" class="dialog">
    <h2>Socket:</h2>
    <ul>
      <li class="controller">按次点播接口端口：${ConfigurationHolder.config.BOSS_ONCE_ORDER_SERVER_PORT}</li>
      <li class="controller">包月订购接口端口：${ConfigurationHolder.config.BOSS_ORDER_SERVER_PORT}</li>
    </ul>
  </div>
  <div id="FTP" class="dialog">
    <h2>FTP:</h2>
    <ul>
      <li class="controller">.....</li>
    </ul>
  </div>
  <div id="WebServices" class="dialog">
    <h2>WebServices:</h2>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/services">WebServices统一公布地址</a></li>
      <li class="controller"><a href="${request.getContextPath()}/services/callCenter?wsdl">呼叫中心wsdl地址</a></li>
      <li class="controller"><a href="${request.getContextPath()}/services/bankCard?wsdl">一卡通wsdl地址</a></li>
    </ul>
  </div>
  <div id="Http Post" class="dialog">
    <h2>UMS Http Post Process:</h2>
    <ul>
      <li class="controller">UMS 余额查询:http://${request.getLocalAddr()}:${request.getLocalPort()}${request.getContextPath()}/umsProcess/balance</li>
      <li class="controller">UMS 充值:http://${request.getLocalAddr()}:${request.getLocalPort()}${request.getContextPath()}/umsProcess/recharge</li>
      <li class="controller">UMS 密码验证:http://${request.getLocalAddr()}:${request.getLocalPort()}${request.getContextPath()}/umsProcess/passwordValid</li>
      <li class="controller">UMS 密码修改:http://${request.getLocalAddr()}:${request.getLocalPort()}${request.getContextPath()}/umsProcess/passwordModify</li>
      <li class="controller">UMS 基本收视费查询:http://${request.getLocalAddr()}:${request.getLocalPort()}${request.getContextPath()}/umsProcess/baseQuery</li>
      <li class="controller">UMS 基本收视费缴费:http://${request.getLocalAddr()}:${request.getLocalPort()}${request.getContextPath()}/umsProcess/baseAccoBank</li>
    </ul>
  </div>
  <%
    def test = request.getParameter("test")
    if (test.equals("true")) {
  %>
  <div id="InterFaceTestList" class="dialog">
    <h2>FTP InterFace Test:</h2>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/bsmpDeploy/syncPublishAsset">影片资源同步接口(准实时-日增量)</a></li>
    </ul>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/bsmpDeploy/syncAllAuthService">全量订购关系同步(月全量)</a></li>
    </ul>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/bsmpDeploy/syncIncrementAuthService">增量订购关系同步(增量15分钟一次)</a></li>
    </ul>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/bsmpDeploy/syncUmsUserInfo">UMS用户信息同步(月全量)</a></li>
    </ul>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/bsmpDeploy/syncUmsPoint">积分点击信息同步</a></li>
    </ul>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/bsmpDeploy/syncBsmpIboss">bsmp resource_business同步iboss</a></li>
    </ul>
    <ul>
      <li class="controller"><a href="${request.getContextPath()}/bsmpDeploy/syncBsmpIbossResource">bsmp resource同步iboss</a></li>
    </ul>
  </div>
  <%
    }
  %>
</div>
</body>
</html>