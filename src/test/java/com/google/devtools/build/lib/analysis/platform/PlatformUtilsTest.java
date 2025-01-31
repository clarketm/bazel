// Copyright 2019 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.analysis.platform;

import static com.google.common.truth.Truth.assertThat;

import build.bazel.remote.execution.v2.Platform;
import com.google.devtools.build.lib.remote.options.RemoteOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link PlatformUtils } */
@RunWith(JUnit4.class)
public final class PlatformUtilsTest {
  @Test
  public void testParsePlatformSortsProperties() throws Exception {
    String s =
        String.join(
            "\n",
            "properties: {",
            " name: \"b\"",
            " value: \"2\"",
            "}",
            "properties: {",
            " name: \"a\"",
            " value: \"1\"",
            "}");
    RemoteOptions remoteOptions = new RemoteOptions();
    remoteOptions.remoteDefaultPlatformProperties = s;

    Platform expected =
        Platform.newBuilder()
            .addProperties(Platform.Property.newBuilder().setName("a").setValue("1"))
            .addProperties(Platform.Property.newBuilder().setName("b").setValue("2"))
            .build();
    assertThat(PlatformUtils.getPlatformProto(null, remoteOptions)).isEqualTo(expected);
  }

  @Test
  public void testParsePlatformHandlesNull() throws Exception {
    assertThat(PlatformUtils.getPlatformProto(null, null)).isEqualTo(null);
  }
}
