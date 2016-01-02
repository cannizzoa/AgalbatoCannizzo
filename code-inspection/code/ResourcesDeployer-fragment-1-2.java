    private void processArchive(DeploymentContext dc) {

        try {
            ReadableArchive archive = dc.getSource();

            if (ResourceUtil.hasResourcesXML(archive, locator)) {

                Map<String,Map<String, List>> appScopedResources = new HashMap<String,Map<String,List>>();
                Map<String, String> fileNames = new HashMap<String, String>();

                String appName = getAppNameFromDeployCmdParams(dc);
                //using appName as it is possible that "deploy --name=APPNAME" will
                //be different than the archive name.
                retrieveAllResourcesXMLs(fileNames, archive, appName);

                for (Map.Entry<String, String> entry: fileNames.entrySet())
                {
                    String moduleName = entry.getKey();
                    String fileName = entry.getValue();
                    debug("Sun Resources XML : " + fileName);

                    moduleName = org.glassfish.resourcebase.resources.util.ResourceUtil.getActualModuleNameWithExtension(moduleName);
                    String scope ;
                    if(appName.equals(moduleName))
                    {
                        scope = JAVA_APP_SCOPE_PREFIX;
                    }
                    else
                    {
                        scope = JAVA_MODULE_SCOPE_PREFIX;
                    }

                    File file = new File(fileName);
                    ResourcesXMLParser parser = new ResourcesXMLParser(file, scope);

                    validateResourcesXML(file, parser);

                    List list = parser.getResourcesList();

                    Map<String, List> resourcesList = new HashMap<String, List>();
                    List<org.glassfish.resources.api.Resource> nonConnectorResources =
                            ResourcesXMLParser.getNonConnectorResourcesList(list, false, true);
                    resourcesList.put(NON_CONNECTOR_RESOURCES, nonConnectorResources);
                    
                    List<org.glassfish.resources.api.Resource> connectorResources =
                            ResourcesXMLParser.getConnectorResourcesList(list, false, true);
                    resourcesList.put(CONNECTOR_RESOURCES, connectorResources);

                    appScopedResources.put(moduleName, resourcesList);
                }
                dc.addTransientAppMetaData(APP_SCOPED_RESOURCES_MAP, appScopedResources);
                ApplicationInfo appInfo = appRegistry.get(appName);
                if(appInfo != null){
                    Application app = dc.getTransientAppMetaData(ServerTags.APPLICATION, Application.class);
                    appInfo.addTransientAppMetaData(ServerTags.APPLICATION, app);
                }
            }
        } catch (Exception e) {
            // only DeploymentExceptions are propagated and result in deployment failure
            // in the event notification infrastructure
            throw new DeploymentException("Failue while processing glassfish-resources.xml(s) in the archive ", e);
        }
    }


    /**
     * retain old resource configuration for the new archive being deployed.
     * @param dc DeploymentContext
     * @param allResources all resources (app scoped, module scoped) of old application
     * @throws Exception when unable to retain old resource configuration.
     */
    public void retainResourceConfig(DeploymentContext dc, Map<String, Resources> allResources) throws Exception {
        String appName = getAppNameFromDeployCmdParams(dc);
        Application application = dc.getTransientAppMetaData(ServerTags.APPLICATION, Application.class);
        Resources appScopedResources = allResources.get(appName);

        if(appScopedResources != null){
            application.setResources(appScopedResources);
        }

        if(DeploymentUtils.isArchiveOfType(dc.getSource(), DOLUtils.earType(), locator)){
            List<Module> modules = application.getModule();
            if(modules != null){
                for(Module module : modules){
                    Resources moduleScopedResources = allResources.get(module.getName());
                    if(moduleScopedResources != null){
                        module.setResources(moduleScopedResources);
                    }
                }
            }
        }
    }

