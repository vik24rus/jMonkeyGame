package main;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLocator;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ClasspathLocator;

class Test implements AssetLocator
{
    @Override
    public void setRootPath(String s) {

    }

    @Override
    public AssetInfo locate(AssetManager assetManager, AssetKey assetKey) {
        return null;
    }
}
