<#-- @ftlvariable name="" type="www.chinacloud.com.whitehole.swagger.SwaggerView" -->
<html>
<head>
  <meta charset="UTF-8">
  <title>Swagger UI</title>
  <link rel="icon" type="image/png" href="${swaggerPath}/images/favicon-32x32.png" sizes="32x32" />
  <link rel="icon" type="image/png" href="${swaggerPath}/images/favicon-16x16.png" sizes="16x16" />
  <link href='${swaggerPath}/css/typography.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='${swaggerPath}/css/reset.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='${swaggerPath}/css/screen.css' media='screen' rel='stylesheet' type='text/css'/>
  <link href='${swaggerPath}/css/reset.css' media='print' rel='stylesheet' type='text/css'/>
  <link href='${swaggerPath}/css/print.css' media='print' rel='stylesheet' type='text/css'/>
  <script src='${swaggerPath}/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/jquery.slideto.min.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/jquery.wiggle.min.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/handlebars-2.0.0.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/underscore-min.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/backbone-min.js' type='text/javascript'></script>
  <script src='${swaggerPath}/swagger-ui.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/highlight.7.3.pack.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/marked.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lib/swagger-oauth.js' type='text/javascript'></script>

  <!-- Some basic translations -->
  <script src='${swaggerPath}/lang/translator.js' type='text/javascript'></script>
  <script src='${swaggerPath}/lang/zh-cn.js' type='text/javascript'></script>
  <!-- <script src='lang/en.js' type='text/javascript'></script> -->

  <script type="text/javascript">
    $(function () {
      /*var url = window.location.search.match(/url=([^&]+)/);
      if (url && url.length > 1) {
        url = decodeURIComponent(url[1]);
      } else {
        url = "http://petstore.swagger.io/v2/swagger.json";
      }*/
      var url = "${contextPath}/swagger.json";

      // Pre load translate...
      if(window.SwaggerTranslator) {
        window.SwaggerTranslator.translate();
      }
      window.swaggerUi = new SwaggerUi({
        url: url,
        dom_id: "swagger-ui-container",
        supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
        onComplete: function(swaggerApi, swaggerUi){
          if(typeof initOAuth == "function") {
            initOAuth({
              clientId: "your-client-id",
              clientSecret: "your-client-secret-if-required",
              realm: "your-realms",
              appName: "your-app-name", 
              scopeSeparator: ","
            });
          }

          if(window.SwaggerTranslator) {
            window.SwaggerTranslator.translate();
          }

          $('pre code').each(function(i, e) {
            hljs.highlightBlock(e)
          });

          addApiKeyAuthorization();
        },
        onFailure: function(data) {
          log("Unable to Load SwaggerUI");
        },
        docExpansion: "none",
        apisSorter: "alpha",
        showRequestHeaders: false
      });

      function addApiKeyAuthorization(){
        var key = encodeURIComponent($('#input_apiKey')[0].value);
        if(key && key.trim() != "") {
            var apiKeyAuth = new SwaggerClient.ApiKeyAuthorization("api_key", key, "query");
            window.swaggerUi.api.clientAuthorizations.add("api_key", apiKeyAuth);
            log("added key " + key);
        }
      }

      $('#input_apiKey').change(addApiKeyAuthorization);

      // if you have an apiKey you would like to pre-populate on the page for demonstration purposes...
      /*
        var apiKey = "myApiKeyXXXX123456789";
        $('#input_apiKey').val(apiKey);
      */

      window.swaggerUi.load();

      function log() {
        if ('console' in window) {
          console.log.apply(console, arguments);
        }
      }
  });
  </script>
</head>

<body class="swagger-section">
<div id='header'>
  <div class="swagger-ui-wrap">
    <a id="logo" href="#">swagger</a>
    <form id='api_selector'>
      <div class='input'><input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="text"/></div>
      <div class='input'><input placeholder="api_key" id="input_apiKey" name="apiKey" type="text"/></div>
      <div class='input'><a id="explore" href="#" data-sw-translate>Explore</a></div>
    </form>
  </div>
</div>

<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>