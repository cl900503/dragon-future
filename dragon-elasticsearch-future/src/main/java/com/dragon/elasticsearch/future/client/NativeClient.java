package com.dragon.elasticsearch.future.client;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.DeleteByQueryRequest;
import co.elastic.clients.elasticsearch.core.DeleteByQueryResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class NativeClient {
	public static void main(String[] args) throws ElasticsearchException, IOException {

		// URL and API key
		String serverUrl = "http://192.168.0.81:11110";
//		String apiKey = "VnVhQ2ZHY0JDZGJrU...";

		// Create the low-level client
		RestClient restClient = RestClient
		    .builder(HttpHost.create(serverUrl))
//		    .setDefaultHeaders(new Header[]{
//		        new BasicHeader("Authorization", "ApiKey " + apiKey)
//		    })
		    .build();

		// Create the transport with a Jackson mapper
		ElasticsearchTransport transport = new RestClientTransport(
		    restClient, new JacksonJsonpMapper());

		// And create the API client
		ElasticsearchClient esClient = new ElasticsearchClient(transport);

//		// Creating an index
//		CreateIndexResponse createIndexResponse = esClient.indices().create(c -> c.index("users"));
//		System.out.println(createIndexResponse);
//		
//		User user = new User();
//		user.setUserId(1000L);
//		user.setName("chenlong1000");
//		Map<String, Byte> searchSetting = new HashMap<>();
//		searchSetting.put("props$name", (byte) 1);
//		user.setSearchSetting(searchSetting);
//
//		IndexResponse response = esClient.index(i -> i
//		    .index("users")
//		    .id(user.getUserId().toString())
//		    .document(user)
//		);
//		
//		System.out.println(response);
		
//		Query byName = WildcardQuery.of(m -> m.field("name").value("chenlong*"))._toQuery();
//
//		Query setmatch = MatchQuery.of(m -> m.field("searchSetting.props$name").query("1"))._toQuery();
//
//		Query setexists = ExistsQuery.of(m -> m.field("searchSetting.props$name"))._toQuery();
//		
//		Query notsetexists = BoolQuery.of(m->m.mustNot(setexists))._toQuery();
//
//		Query setting = BoolQuery.of(b -> b.should(setmatch).should(notsetexists))._toQuery();
//		
//		SearchResponse<User> response = esClient.search(s -> s
//		    .index("users")
//		    .query(q -> q
//		        .bool(b -> b 
//		            .must(byName) 
//		            .must(setting)
//		        )
//		    ),
//		    User.class
//		);
//
//		List<Hit<User>> hits = response.hits().hits();
//		for (Hit<User> hit : hits) {
//			User user = hit.source();
//			System.out.println(user);
//		}

//		System.out.println(response.hits().hits());
		Query value = RangeQuery.of(m -> m.field("loginTime").lt(JsonData.of(1714111868356L)))._toQuery();
		DeleteByQueryRequest request = new DeleteByQueryRequest.Builder().index(Arrays.asList("statistics_index_name")).query(value).build();
		DeleteByQueryResponse deleteByQuery = esClient.deleteByQuery(request);
		System.out.println(deleteByQuery);
		
	}
}
