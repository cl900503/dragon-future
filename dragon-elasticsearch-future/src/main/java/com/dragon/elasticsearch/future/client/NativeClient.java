package com.dragon.elasticsearch.future.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.LongTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
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
		ElasticsearchClient elasticsearchClient = new ElasticsearchClient(transport);

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
//		Query value = RangeQuery.of(m -> m.field("loginTime").lt(JsonData.of(1714111868356L)))._toQuery();
//		DeleteByQueryRequest request = new DeleteByQueryRequest.Builder().index(Arrays.asList("statistics_index_name")).query(value).build();
//		DeleteByQueryResponse deleteByQuery = esClient.deleteByQuery(request);
//		System.out.println(deleteByQuery);
		
//		long timestamp = System.currentTimeMillis() - (1 * 86400L * 1000L);
//		// 清理 elasticsearch
//		Query value = RangeQuery.of(m -> m.field("loginTime").lt(JsonData.of(timestamp)))._toQuery();
//		DeleteByQueryRequest request = new DeleteByQueryRequest.Builder().index(Arrays.asList("statistics_index_name"))
//				.query(value).build();
//		DeleteByQueryResponse response = elasticsearchClient.deleteByQuery(request);
//		System.out.println(response);

		
		
		
		List<Long> userIds = Arrays.asList(4611686027042922244L, 4611686027042922251L);
//		List<Byte> deviceTypes = Arrays.asList((byte)1);

		SearchResponse<Void> response = elasticsearchClient.search(s -> s
		    .index("statistics_index_name")
		    .size(0) // 只聚合，不返回普通查询结果
//		    .query(q -> q
//		        .terms(t -> t
//		            .field("userID")
//		            .terms(v -> v.value(userIds.stream().map(FieldValue::of).collect(Collectors.toList())))
//		        )
//		    )
		    
            .query(q -> q.bool(b ->{	
            	b.filter(f -> f.terms(t -> t
                        .field("userID") // IN 查询
                        .terms(v -> v.value(userIds.stream().map(FieldValue::of).collect(Collectors.toList())))
                    ));
            	
//            	b.filter(f -> f.terms(t -> t
//                        .field("deviceType") // IN 查询
//                        .terms(v -> v.value(deviceTypes.stream().map(FieldValue::of).collect(Collectors.toList())))
//                    ));
            		return b;
            	}
             ))
		    
            .aggregations("group_by_user", a -> a
                    .terms(t -> t
                        .field("userID")
                        .size(1)) // 按 userId 分组
                    .aggregations("latest_login", sub -> sub
                        .topHits(th -> th
                            .size(2) // 取 1 条记录
                            .sort(sort -> sort
                                .field(f -> f.field("loginTime").order(SortOrder.Desc)) // 按 loginTime 降序
                            )
                        )
                    )
            )
		    , Void.class);
		
		
		System.out.println(response);
		
		
		Map<String, Aggregate> aggs = response.aggregations();
		Aggregate userAgg = aggs.get("group_by_user");
		if (userAgg != null && userAgg.isLterms()) {
			List<LongTermsBucket> array = userAgg.lterms().buckets().array();
			for (LongTermsBucket bucket : array) {

				String userId = bucket.key();
				System.out.println("userId:" + userId);

				// 获取聚合结果中的 topHits
				List<Hit<JsonData>> hits = bucket.aggregations().get("latest_login").topHits().hits().hits();

				System.out.println("hits.size:" + hits.size());

				if (!hits.isEmpty()) {
					for (Hit<JsonData> hit : hits) {

						System.out.println(hit.source().toJson().toString());

					}
				}

			}
		}
        
//        System.out.println(userAgg);
        

//		SearchResponse<LoginRecord> response = elasticsearchClient.search(s -> s
//			    .index("statistics_index_name")  // 指定索引
//			    .query(q -> q.matchAll(m -> m)) // matchAll 查询，获取所有文档
//			    .size(1000), // 这里设置返回的最大条数（默认 10）
//			    LoginRecord.class // 直接返回 JSON 格式的 _source
//			);
		
		
//		System.out.println(response);
//		
//		List<LoginRecord> jsonResults = response.hits().hits().stream()
//			    .map(hit -> hit.source()) // 直接获取 _source JSON
//			    .filter(Objects::nonNull)
//			    .collect(Collectors.toList());
//
//			for (LoginRecord json : jsonResults) {
//			    System.out.println(json);
//			}
		
	}
}
