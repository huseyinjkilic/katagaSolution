/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package katagaSolution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KatagaControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
     public void checkStatusForEqualInputs() throws Exception { 
    	this.mockMvc.perform(get("/calculateKataga/cat/dog")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void checkStatusForEqualInputsForCatAndDog() throws Exception {

        this.mockMvc.perform(get("/calculateKataga/cat/dog")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("cat,cot,cog,dog"));
    }
    
    @Test
    public void checkStatusForUnequalInputsForCatAndDog2() throws Exception {

        this.mockMvc.perform(get("/calculateKataga/cat/dog2")).andDo(print()).andExpect(status().isOk())
              .andExpect(content().string("Error accured words must be of the same length"));
    }

    @Test
    public void checkStatusForEqualInputsForCodeToRuby() throws Exception {

        this.mockMvc.perform(get("/calculateKataga/code/ruby")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("code,rode,robe,robs,rubs,ruby"));
    }
    
    @Test
    public void checkStatusForEqualInputsForRubyToCode() throws Exception {

        this.mockMvc.perform(get("/calculateKataga/ruby/code")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("ruby,rubs,robs,robe,rode,code"));
    }

}
