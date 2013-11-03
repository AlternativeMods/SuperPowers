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

/**
 * No description given
 *
 * @author jk-5
 */
/*public class VersionCheckerTest {

    @Test
    public void testUnstableBuildWarning(){
        VersionChecker checker = new VersionChecker(false);
        checker.setCurrentChannel(VersionChannel.STABLE);
        Assert.assertFalse("Stable builds should not get a warning", checker.needsWarning());
        checker.setCurrentChannel(VersionChannel.SNAPSHOT);
        Assert.assertTrue("Snapshot builds should get a warning", checker.needsWarning());
        checker.setCurrentChannel(VersionChannel.DEVELOPMENT);
        Assert.assertTrue("Development builds should get a warning", checker.needsWarning());
    }

    @Test
    public void testLessStableThanWantedWarning(){
        VersionChecker checker = new VersionChecker(false);

        checker.setCurrentChannel(VersionChannel.STABLE);
        checker.setPreferredChannel(VersionChannel.STABLE);
        Assert.assertFalse("Equal stable channels should not get a warning", checker.isLessStableThanWanted());

        checker.setCurrentChannel(VersionChannel.SNAPSHOT);
        checker.setPreferredChannel(VersionChannel.SNAPSHOT);
        Assert.assertFalse("Equal stable channels should not get a warning", checker.isLessStableThanWanted());

        checker.setCurrentChannel(VersionChannel.DEVELOPMENT);
        checker.setPreferredChannel(VersionChannel.DEVELOPMENT);
        Assert.assertFalse("Equal stable channels should not get a warning", checker.isLessStableThanWanted());



        checker.setCurrentChannel(VersionChannel.SNAPSHOT);
        checker.setPreferredChannel(VersionChannel.STABLE);
        Assert.assertTrue("Less stable channels should get a warning", checker.isLessStableThanWanted());

        checker.setCurrentChannel(VersionChannel.DEVELOPMENT);
        checker.setPreferredChannel(VersionChannel.STABLE);
        Assert.assertTrue("Less stable channels should get a warning", checker.isLessStableThanWanted());

        checker.setCurrentChannel(VersionChannel.DEVELOPMENT);
        checker.setPreferredChannel(VersionChannel.SNAPSHOT);
        Assert.assertTrue("Less stable channels should get a warning", checker.isLessStableThanWanted());



        checker.setCurrentChannel(VersionChannel.SNAPSHOT);
        checker.setPreferredChannel(VersionChannel.DEVELOPMENT);
        Assert.assertFalse("More stable channels should not get a warning", checker.isLessStableThanWanted());

        checker.setCurrentChannel(VersionChannel.STABLE);
        checker.setPreferredChannel(VersionChannel.DEVELOPMENT);
        Assert.assertFalse("More stable channels should not get a warning", checker.isLessStableThanWanted());

        checker.setCurrentChannel(VersionChannel.STABLE);
        checker.setPreferredChannel(VersionChannel.SNAPSHOT);
        Assert.assertFalse("More stable channels should not get a warning", checker.isLessStableThanWanted());
    }
}*/
