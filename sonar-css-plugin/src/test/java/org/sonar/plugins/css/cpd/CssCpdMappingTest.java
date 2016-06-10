/*
 * SonarQube CSS Plugin
 * Copyright (C) 2013-2016 Tamas Kende and David RACODON
 * mailto: kende.tamas@gmail.com and david.racodon@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.css.cpd;

import com.google.common.base.Charsets;

import java.io.File;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.config.Settings;
import org.sonar.api.scan.filesystem.FileQuery;
import org.sonar.api.scan.filesystem.ModuleFileSystem;
import org.sonar.plugins.css.core.CssLanguage;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CssCpdMappingTest {

  private CssCpdMapping mapping;

  @Before
  public void setup() {
    ModuleFileSystem fileSystem = mock(ModuleFileSystem.class);
    when(fileSystem.sourceCharset()).thenReturn(Charsets.UTF_8);
    when(fileSystem.files(Mockito.any(FileQuery.class))).thenReturn(Collections.singletonList(new File("src/test/resources/org/sonar/plugins/css/cssProject/css/boxSizing.css")));
    mapping = new CssCpdMapping(
      new CssLanguage(new Settings()), fileSystem);
  }

  @Test
  public void test() {
    assertThat(mapping.getLanguage().getKey()).isEqualTo(CssLanguage.KEY);
    assertThat(mapping.getTokenizer()).isNotNull();
  }

}
