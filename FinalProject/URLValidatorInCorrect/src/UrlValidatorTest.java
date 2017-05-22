/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
	   
	   
   }
   
   
   public void testYourFirstPartition()
   {
	   
   }
   
   public void testYourSecondPartition(){
	   
   }
   
   
   /************************************/
   public void testIsValid()
   {
	   int num_schemes = 9;
	   int num_auths = 19;
	   int num_ports = 7;
	   int num_paths = 10;
	   int num_queries = 3;
	   
	   int cases_passed = 0, cases_failed = 0, total_cases = 0;
	   String myURL;
	   Boolean expectedValidity, actualValidity;
	   UrlValidator urlValidator = new UrlValidator();
	   
	   for (int i = 0; i < num_schemes; i++) {
		   
		   for (int j = 0; j < num_auths; j++) {
			   
			   for (int k = 0; k < num_ports; k++) {
				   
				   for (int l = 0; l < num_paths; l++) {
						   
					   for (int n = 0; n < num_queries; n++) {
							   
						   myURL = makeURLStringToTest(i, j, k, l, n);
						   expectedValidity = checkExpectedValidity(i, j, k, l, n);
						   actualValidity = urlValidator.isValid(myURL);
						   
						   if (!(expectedValidity == actualValidity)) {
							   cases_failed++;
							   System.out.println("Case failed: " + myURL + " -- Expected return value of " + expectedValidity.toString().toUpperCase() + " but got " + actualValidity.toString().toUpperCase());
						   }
						   else {
							   cases_passed++;
						   }
						   
						   total_cases++;
						   
					   }
					   
				   }
				   
			   }
			   
		   }
		   
	   }
	   
	   System.out.println();
	   System.out.println("==================SUMMARY=====================");
	   System.out.println("Cases passed: " + cases_passed + " out of " + total_cases);
	   System.out.println("Cases failed: " + cases_failed + " out of " + total_cases);	   
	   
   }
   
   
   public String makeURLStringToTest (int scheme_index, int auth_index, int port_index, int path_index, int query_index) {
   
	   String scheme_choice = test_schemes[scheme_index].item;
	   String auth_choice = test_auths[auth_index].item;
	   String port_choice = test_ports[port_index].item;
	   String path_choice = test_paths[path_index].item;
	   String query_choice = test_queries[query_index].item;
	   
	   //Debug
	   //System.out.println(scheme_choice + auth_choice + port_choice + path_choice + query_choice);
	   
	   return (scheme_choice + auth_choice + port_choice + path_choice + query_choice);
	   
   }
   
   public Boolean checkExpectedValidity (int scheme_index, int auth_index, int port_index, int path_index, int query_index) {
	   
	   Boolean scheme_val = test_schemes[scheme_index].valid;
	   Boolean auth_val = test_auths[auth_index].valid;
	   Boolean port_val = test_ports[port_index].valid;
	   Boolean path_val = test_paths[path_index].valid;
	   Boolean query_val = test_queries[query_index].valid;
	   
	   //Debug
	   //System.out.println(scheme_val && auth_val && port_val && path_val && query_val);
	   
	   return (scheme_val && auth_val && port_val && path_val && query_val);
	   
   }
   
   
   /*************************************/
   
   
   
   public void testAnyOtherUnitTest()
   {
	   UrlValidator urlVal = new UrlValidator();
	   
	   //AO: Hijacking this function to test some of my hunches about bugs.
	   
	   //Query bug - does not accept queries. 1 and 2 fail, 3 passes b/c the query is the empty string (basically nonexistent).
	   //assert(checkExpectedValidity(0,0,0,0,0) == urlVal.isValid(makeURLStringToTest(0,0,0,0,0)));
	   //assert(checkExpectedValidity(0,0,0,0,1) == urlVal.isValid(makeURLStringToTest(0,0,0,0,1)));
	   //assert(checkExpectedValidity(0,0,0,0,2) == urlVal.isValid(makeURLStringToTest(0,0,0,0,2)));
	   
	   //Empty scheme bug - if the scheme is the empty string i.e. doesn't exist, the URL does not pass .
	   //1 should pass (valid), 2 should pass (invalid), 
	   //assert(checkExpectedValidity(0,0,0,0,2) == urlVal.isValid(makeURLStringToTest(0,0,0,0,2)));
	   //assert(checkExpectedValidity(3,0,0,0,2) == urlVal.isValid(makeURLStringToTest(3,0,0,0,2)));
	   //assert(checkExpectedValidity(8,0,0,0,2) == urlVal.isValid(makeURLStringToTest(8,0,0,0,2)));
	   
	   //I have a hunch that something about the test1 path is wrong, but I don't have time to test right now.
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

   /*************************************************
    * The following arrays are from the given URL validator tests. We 
    * include these to ensure we have a testable input set that covers
    * a large amount of cases and to ensure that we cover at least as much
    * of the cases as the orginal test.
    ************************************************/
   ResultPair[] test_schemes = {new ResultPair("http://", true),
           new ResultPair("ftp://", true),
           new ResultPair("h3t://", true),
           new ResultPair("3ht://", false),
           new ResultPair("http:/", false),
           new ResultPair("http:", false),
           new ResultPair("http/", false),
           new ResultPair("://", false),
           new ResultPair("", true)
   };
   ResultPair[] test_auths = {new ResultPair("www.google.com", true),
           new ResultPair("go.com", true),
           new ResultPair("go.au", true),
           new ResultPair("0.0.0.0", true),
           new ResultPair("255.255.255.255", true),
           new ResultPair("256.256.256.256", false),
           new ResultPair("255.com", true),
           new ResultPair("1.2.3.4.5", false),
           new ResultPair("1.2.3.4.", false),
           new ResultPair("1.2.3", false),
           new ResultPair(".1.2.3.4", false),
           new ResultPair("go.a", false),
           new ResultPair("go.a1a", false),
           new ResultPair("go.cc", true),
           new ResultPair("go.1aa", false),
           new ResultPair("aaa.", false),
           new ResultPair(".aaa", false),
           new ResultPair("aaa", false),
           new ResultPair("", false)
   };
   ResultPair[] test_ports = {new ResultPair(":80", true),
           new ResultPair(":65535", true),
           new ResultPair(":0", true),
           new ResultPair("", true),
           new ResultPair(":-1", false),
           new ResultPair(":65636", true),
           new ResultPair(":65a", false)
   };
   ResultPair[] test_paths = {new ResultPair("/test1", true),
           new ResultPair("/t123", true),
           new ResultPair("/$23", true),
           new ResultPair("/..", false),
           new ResultPair("/../", false),
           new ResultPair("/test1/", true),
           new ResultPair("", true),
           new ResultPair("/test1/file", true),
           new ResultPair("/..//file", false),
           new ResultPair("/test1//file", false)
   };
   ResultPair[] test_path_options = {new ResultPair("/test1", true),
           new ResultPair("/t123", true),
           new ResultPair("/$23", true),
           new ResultPair("/..", false),
           new ResultPair("/../", false),
           new ResultPair("/test1/", true),
           new ResultPair("/#", false),
           new ResultPair("", true),
           new ResultPair("/test1/file", true),
           new ResultPair("/t123/file", true),
           new ResultPair("/$23/file", true),
           new ResultPair("/../file", false),
           new ResultPair("/..//file", false),
           new ResultPair("/test1//file", true),
           new ResultPair("/#/file", false)
   };
   ResultPair[] test_queries = {new ResultPair("?action=view", true),
           new ResultPair("?action=edit&mode=up", true),
           new ResultPair("", true)
   };
   
   
}
