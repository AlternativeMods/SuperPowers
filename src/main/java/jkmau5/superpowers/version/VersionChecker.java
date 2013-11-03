/*
 * Copyright 2013 jk-5 and Lordmau5
 *
 * jk-5 and Lordmau5 License this file to you under the LGPL v3 License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License
 */

package jkmau5.superpowers.version;

import com.google.gson.Gson;
import cpw.mods.fml.common.FMLModContainer;
import cpw.mods.fml.common.Loader;
import jkmau5.superpowers.SuperPowers;
import jkmau5.superpowers.config.ConfigTag;
import jkmau5.superpowers.utils.SPLogger;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * Our currentVersion checker. It compares the local currentVersion with a remote json file that is updated by the CI
 *
 * @author jk-5
 */
public class VersionChecker {

    private static final String VERSION_URL = "https://dl.dropboxusercontent.com/u/224513697/minecraft/mods/SuperPowers/";
    private static final Gson gson = new Gson();

    @Getter private boolean enabled;
    @Getter @Setter(AccessLevel.PACKAGE) private Version currentVersion;
    @Getter @Setter(AccessLevel.PACKAGE) private VersionChannel preferredChannel;

    private final ExecutorService pool = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("SuperPowers-Executor-" + thread.getId());
            return thread;
        }
    });

    public static void main(String args[]) throws Exception {
        RemoteInfo info = new VersionChecker(false).getRemoteInfo().get();
        System.out.println(gson.toJson(info.versions));
    }

    public VersionChecker(){
        this(true);
    }

    protected VersionChecker(boolean doRead){
        if(doRead){
            ConfigTag tag = SuperPowers.getInstance().getConfig().getTag("versionCheck").useBraces();
            this.enabled = tag.getTag("enabled").setComment("Set this to false to enable the version check. (Not sure why you want to do that, but, here it is)").getBooleanValue(true);
            this.preferredChannel = VersionChannel.valueOf(tag.getTag("preferredChannel").setComment("Change here, which builds you want to be notified of. Valid values are: development, snapshot, stable").getValue("stable").toUpperCase());
        }
    }

    public void init(){
        this.readVersionFromFile();
    }

    public Future checkRemoteVersion(){
        if(this.needsWarning()){
            SPLogger.warn("--------------------------------[WARNING]--------------------------------------");
            SPLogger.warn("You are running a " + this.currentVersion.channel.name().toLowerCase() + " build.");
            SPLogger.warn("This means that there could be bugs that crash your game or worse!");
            SPLogger.warn("We (the developers of this mod) do not take any responsibility for any damage to your worlds or game!");
            SPLogger.warn("Also, always make backups!");
            SPLogger.warn("--------------------------------[WARNING]--------------------------------------");
        }
        if(this.isLessStableThanWanted()){
            SPLogger.warn("--------------------------------[WARNING]--------------------------------------");
            SPLogger.warn("In the config, you specified that you only want to run, " + this.preferredChannel.name().toLowerCase() + " builds, and above");
            SPLogger.warn("It seems like you are running a " + this.currentVersion.channel.name().toLowerCase() + " build.");
            SPLogger.warn("You could have changed the config or you have installed another version by hand");
            SPLogger.warn("Be careful! Backup your worlds! Things may not work well in builds that aren\'t stable releases");
            SPLogger.warn("--------------------------------[WARNING]--------------------------------------");
        }

        if(!this.enabled) return null;
        return pool.submit(new VersionCheckRunnable());
    }

    private Version readVersionFromFile(){
        Version ret = new Version();
        Properties prop = ((FMLModContainer) Loader.instance().activeModContainer()).searchForVersionProperties();
        if(prop == null){
            ret.build = -1;
            ret.version = "unknown";
            ret.channel = VersionChannel.DEVELOPMENT;
        }else{
            ret.build = Integer.parseInt(prop.getProperty("SuperPowers.build"));
            ret.version = prop.getProperty("SuperPowers.version");
            ret.channel = VersionChannel.valueOf(prop.getProperty("SuperPowers.channel"));
        }
        return ret;
    }

    protected boolean needsWarning(){
        return this.currentVersion.channel != VersionChannel.STABLE;
    }

    protected boolean isLessStableThanWanted(){
        return this.currentVersion.channel.ordinal() < this.preferredChannel.ordinal();
    }

    public Future<RemoteInfo> getRemoteInfo(){
        return pool.submit(new RemoteInfoFetchCallable());
    }

    private final class VersionCheckRunnable implements Runnable {

        @Override
        public void run() {
            SPLogger.info("Fetching version information from remote server");
            try{
                RemoteInfo info = VersionChecker.this.getRemoteInfo().get();
                Version remote = info.getFromChannel(VersionChecker.this.preferredChannel);
                SPLogger.info("Remote version (%s build %d), is " + (remote.isNewer(VersionChecker.this.currentVersion) ? "newer" : "equal to your version"), remote.version, remote.build);
            }catch (Exception e){
                SPLogger.severe("Error while fetching remote version data", e);
            }
        }
    }

    private static final class RemoteInfoFetchCallable implements Callable<RemoteInfo>{

        @Override
        public RemoteInfo call() throws Exception {
            URL remoteFile = new URL(VERSION_URL + "info.json");
            @Cleanup
            Reader reader = new InputStreamReader(remoteFile.openStream());
            return gson.fromJson(reader, RemoteInfo.class);
        }
    }
}
