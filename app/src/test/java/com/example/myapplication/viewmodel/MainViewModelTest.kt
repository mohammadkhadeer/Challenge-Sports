package com.example.myapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.model.api.ApiHelper
import com.example.myapplication.model.data.news.NewsBase
import com.example.myapplication.utils.Resource
import androidx.lifecycle.Observer
import com.example.myapplication.model.data.homepage.leagueInfo.BaseLeagueInfoHomePage
import com.example.myapplication.model.data.homepage.liveOdds.BaseLiveOdds
import com.example.myapplication.model.data.news.details.NewsPostBase
import com.example.myapplication.model.data.news.details.OnPostDetailResponse
import com.example.myapplication.utils.Status.*
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest{
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var apiHelper: ApiHelper


    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Main Thread")

    @Before
    fun initMocksAndMainThread() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun runViewModel(){
       // testNewsList()
        testLiveOdds()

    }


    fun testNewsList(){
        val newsListBaseClass=Gson().fromJson("{\"list\":[{\"id\":26519,\"admin_id\":5,\"category\":3,\"cover\":17729,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T08:42:14.000000Z\",\"update_time\":\"2022-08-16T12:04:09.000000Z\",\"title\":\"With me here, don&#39;t think about jumping the tower and killing it! Inventory of the strongest heroes to defend the tower. (Four)\",\"description\":\"Wang Zhaojun is a famous tool man Mage. He is full of control skills, passive skills shield effect, first skill slows down, second skill freezes, and third skill uses ice cannon. Unless the opponent moves very fast, he will not easily jump over the tower.\",\"keywords\":\"E-sports, King, Wang Zhaojun, Donghuang Taiyi, Keeping the Tower, Leaping the Tower\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20211221\\/75d6a0b995c2f469e6371d6454512840.jpg\"},{\"id\":26518,\"admin_id\":5,\"category\":3,\"cover\":22524,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T08:35:25.000000Z\",\"update_time\":\"2022-08-16T12:13:10.000000Z\",\"title\":\"With me here, don&#39;t think about jumping the tower and killing it! Inventory of the strongest heroes to defend the tower. (three)\",\"description\":\"At present, the black technology gameplay of &quot;weakening the dark letter&quot; has become the password for the top score this season. Almost no hero can resist, and the mobility is extremely strong. This warrior is out of control! In the S28 season, the Lightning Dagger was revised, and the biggest beneficiary was not the shooter, but the secret letter. His own basic attack has two stages of damage, and one more basic attack can trigger the arc passive.\",\"keywords\":\"E-sports, Li Xin, dark letter, light letter, king, tower guard\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220322\\/49b2f5cacb20215cf61f090783801eed.jpeg\"},{\"id\":26517,\"admin_id\":6,\"category\":1,\"cover\":30740,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T08:19:13.000000Z\",\"update_time\":\"2022-08-16T08:28:49.000000Z\",\"title\":\"Bournemouth boss thinks Guardiola is underrated\",\"description\":\"Scott Parker, the coach of the newly-promoted Bournemouth in the Premier League, also talked about the topic of Manchester City coach Guardiola in a recent interview, and also said that Manchester City has achieved such dazzling results, and some part should be attributed to Guardio Pull&#39;s coaching ability.\",\"keywords\":\"Premier League, Manchester City, Bournemouth, Haaland, Guardiola, Scott Parker\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220816\\/44a0fb4445c18179a1eaaf5a24fad625.jpg\"},{\"id\":26516,\"admin_id\":5,\"category\":3,\"cover\":24585,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T07:24:35.000000Z\",\"update_time\":\"2022-08-16T12:08:33.000000Z\",\"title\":\"Huang Zhong&#39;s big move can cover almost the entire range of the defense tower. As long as the enemy wants to break into the defense tower, in addition to taking damage from the defense tower, there are also several cannons of Huang Zhong. If he is there, he may not reach Huang Zhong. In front of Zhong, they were beaten to the point of being left with blood, and the risk was too great. Think twice before taking action. Huang Zhong&#39;s ability to defend towers is really good, but the big move has a disadvantage, that is, after opening the post, he can&#39;t move. During the use of the skill, standing in the same place, the damage range is large. Don&#39;t ignore the principle of &quot;fighting poison with poison&quot;. Standing on the spot, the mage said &quot;it is exactly what I want&quot;, because the hit rate of their skills is greatly improved, as high as 99.99%, especially the long mage, who casts spells from a distance or the edge of the fort, and come to Mo Xie and Nu Wa. , Angela, as long as Huang Zhong dares to make a big move, they will immediately use a set of skills to instantly kill you, and there will be no chance for you to fire a single shot. It&#39;s okay to guard the tower, but pay more attention to these long masters. Bai Qi is positioned as the Bai Qi of the tank. He has a range of control skills. With passive HP recovery, he can counter-kill the enemies who step into the defense tower. Don&#39;t underestimate Bai Qi, anti-armor, counter-kill, Anti-control and blood recovery are his strengths. The passive skill &quot;Scythe of Counterattack&quot;, when Bai Qi causes or receives damage, will gain the power of shadow, increase the movement speed and the healing effect, up to 70 points of movement speed and 80% of the healing effect. In addition, the ultimate skill &quot;Pride Taunt&quot;, Bai Qi is immediately filled with the power of shadow, jumping to the designated position, causing 300 spell damage and taunting the enemy. Taunt has a base duration of 1 to 1.5 seconds, and increases by an additional 0.2 seconds for every 1000 points of extra life in White, up to a maximum of 2.5 seconds. Combining all attributes and mechanisms, he is very fleshy, and it is not easy to be killed. Once the enemy steps into the tower, he will first use his big move to taunt. After being touched by the enemy, the protection mechanism of the defense tower will automatically attack the enemy within the range. , and continue to recover blood during the attack. In just a few seconds, if there is no displacement or flash away, he will be crushed to death in the tower, and he will be killed by the defensive tower. Even if you can&#39;t kill you, you can still get support from your teammates, and others will harvest this daring local hero. Guess who is the hero guarding the tower strong? Remember to stay with 77577 Sports and read the next article!\",\"description\":\"Bai Qi, who is positioned as a tank, has a range of control skills, and with passive HP recovery, he can counter-kill the enemy who stepped into the defense tower. Don&#39;t underestimate Bai Qi, anti-armor, anti-kill, anti-control , Returning blood is his strength.\",\"keywords\":\"E-sports, Huang Zhong, Bai Qi, hero, tower jumping, defense\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220417\\/d8b0e523b84507f8bab969a0456802b5.jpg\"},{\"id\":26515,\"admin_id\":5,\"category\":3,\"cover\":24589,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T07:18:19.000000Z\",\"update_time\":\"2022-08-16T12:10:54.000000Z\",\"title\":\"With me here, don&#39;t think about jumping the tower and killing it! Inventory of the strongest heroes to defend the tower.\",\"description\":\"Who is the best candidate to kill the tower? There are hundreds of heroes in the canyon, and there are countless people who have the ability to jump over the tower and kill them, especially those heroes who are born with multi-stage displacement mechanisms, such as Shangguan Waner, Yao, Jing, Li Bai, etc., click the skills at will to fly under the tower to attack, Go back to the place and kill the invisible.\",\"keywords\":\"E-sports, King of Glory, Huang Zhong, Tower Jump, Defense, Hero\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220417\\/c673254353bd9b2ee646526aa8c9f458.jpg\"},{\"id\":26514,\"admin_id\":6,\"category\":1,\"cover\":30739,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T07:14:58.000000Z\",\"update_time\":\"2022-08-16T08:35:21.000000Z\",\"title\":\"Champions League qualifiers - Grass cavalry against PSV Eindhoven is inevitable\",\"description\":\"The first round of the 2022\\/23 UEFA Champions League qualifying play-offs will be played at the Ebrox Stadium tomorrow. A bloody battle.\",\"keywords\":\"UEFA Champions League, Glasgow Cavalry, PSV, Monaco, Borussia Dortmund, Leipzig\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220816\\/d6d6dd2fef22d64a928e9281758be67f.jpg\"},{\"id\":26513,\"admin_id\":6,\"category\":1,\"cover\":30738,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T06:11:04.000000Z\",\"update_time\":\"2022-08-16T08:46:10.000000Z\",\"title\":\"Liverpool to introduce new measures to guard against crime\",\"description\":\"According to reports on the official website of Premier League giants Liverpool, Liverpool will formulate and introduce more stringent measures in the new season to avoid and beware of any criminal and anti-social behavior.\",\"keywords\":\"Premier League, Liverpool, EFL, FA\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220816\\/250e730b0707af59662d118d85e0f9ff.jpg\"},{\"id\":26512,\"admin_id\":6,\"category\":1,\"cover\":30737,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T05:10:15.000000Z\",\"update_time\":\"2022-08-16T05:30:06.000000Z\",\"title\":\"Jorde Jong to stay Xavi: He will be an important player for us\",\"description\":\"After the start of the new season, the 25-year-old star Frankie de Jong from the Netherlands has still not made positive progress in the summer transfer turmoil with the major giants. According to media reports, Frenkie de Jong, who has been on the same news page as Chelsea recently, is more inclined to join Tuchel&#39;s team, directly causing Manchester United to suffer another blow.\",\"keywords\":\"La Liga, Premier League, Frenkie de Jong, Barcelona, Manchester United, Chelsea, Xavi\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220816\\/1740e2d8085df38a48ccc64fbcdfdec8.jpg\"},{\"id\":26511,\"admin_id\":6,\"category\":1,\"cover\":30735,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T04:28:22.000000Z\",\"update_time\":\"2022-08-16T05:30:08.000000Z\",\"title\":\"Manchester United lose again, players need extra training and no vacation\",\"description\":\"Not long after the start of the new Premier League season, the giants Manchester United suffered a two-game losing streak. They lost to Brighton 1-2 in the first round, and they were humiliated by Brentford 0-4 earlier. , and Dutch coach Ten Hag also became the first time in 101 years that Manchester United suffered a two-game losing streak at the start of the new coach.\",\"keywords\":\"Premier League, Manchester United, Ten Hag, Liverpool, Klopp\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220816\\/0b99f8f7d2c8e1c0a45a058397522527.jpg\"},{\"id\":26510,\"admin_id\":6,\"category\":1,\"cover\":30734,\"browse\":0,\"sort\":0,\"status\":1,\"translate\":1,\"create_time\":\"2022-08-16T03:34:18.000000Z\",\"update_time\":\"2022-08-16T05:25:06.000000Z\",\"title\":\"At the end of the game, Tuchel and Conte are still arguing\",\"description\":\"In the London derby in the second round of the Premier League in the 2022\\/23 season, Chelsea and Tottenham Hotspur at home were drawn 2-2. However, coaches Tuchel and Conte, whose combined age is over 100, continued to quarrel in that game, and even almost caused a more serious conflict. In the end, both Tuchel and Conte were shown red cards.\",\"keywords\":\"Premier League, Tottenham, Chelsea, Tuchel, Conte, Kane, Reece James\",\"path\":\"https:\\/\\/cdn.77577cf.com\\/uploads\\/images\\/20220816\\/44882b10d952a0736ac6599a0ad32f72.jpg\"}],\"meta\":{\"locale\":\"en\",\"currentPage\":1,\"lastPage\":2642,\"perPage\":10,\"total\":26412}}"
            ,NewsBase::class.java)
        coEvery { apiHelper.getNews("en","1") }returns newsListBaseClass
        val vm=MainViewModel(apiHelper = apiHelper)

        val observer =
            Observer<Resource<NewsBase>> { t ->
                when (t?.status) {
                    SUCCESS -> {
                        assert(true)
                    }
                    ERROR -> {
                        assert(false)
                    }
                    LOADING -> {
                        assert(true) {
                            println("loading")
                        }
                    }
                    else -> {
                        assert(false)
                    }
                }
            }
        vm.newsLiveData.observeForever(observer)
        vm.makeNewsCall("1")
        vm.makeNewsPostCall("26519",object : OnPostDetailResponse<NewsPostBase> {
            override fun onSuccess(responseBody: NewsPostBase) {
                assert(true)
            }

            override fun onFailure(message: String) {
                assert(false)
            }

            override fun onLoading(message: String) {
                assert(true)
            }
        })

    }

    fun testLiveOdds(){
        val liveOddsMockResponse=Gson().fromJson("{\n" +
                "  \"list\": [\n" +
                "    {\n" +
                "      \"handicap\": [\n" +
                "        [\n" +
                "          2135718,\n" +
                "          1,\n" +
                "          0.5,\n" +
                "          0.95,\n" +
                "          0.85,\n" +
                "          0.5,\n" +
                "          0.84,\n" +
                "          0.96,\n" +
                "          false,\n" +
                "          false,\n" +
                "          \"2022-08-30 15:15:04\",\n" +
                "          false,\n" +
                "          2\n" +
                "        ]\n" +
                "      ],\n" +
                "      \"europeOdds\": [\n" +
                "        [\n" +
                "          2135718,\n" +
                "          1,\n" +
                "          1.95,\n" +
                "          3.45,\n" +
                "          3.22,\n" +
                "          1.84,\n" +
                "          3.45,\n" +
                "          3.58,\n" +
                "          \"2022-08-30 15:15:42\",\n" +
                "          false,\n" +
                "          2\n" +
                "        ]\n" +
                "      ],\n" +
                "      \"overUnder\": [\n" +
                "        [\n" +
                "          2135718,\n" +
                "          1,\n" +
                "          2.5,\n" +
                "          0.75,\n" +
                "          0.95,\n" +
                "          2.75,\n" +
                "          0.9,\n" +
                "          0.8,\n" +
                "          \"2022-08-30 15:15:04\",\n" +
                "          false,\n" +
                "          2\n" +
                "        ]\n" +
                "      ],\n" +
                "      \"handicapHalf\": [\n" +
                "        [\n" +
                "          2135718,\n" +
                "          1,\n" +
                "          0.25,\n" +
                "          1.07,\n" +
                "          0.68,\n" +
                "          0.25,\n" +
                "          1.07,\n" +
                "          0.68,\n" +
                "          \"2022-08-29 18:29:00\",\n" +
                "          2\n" +
                "        ]\n" +
                "      ],\n" +
                "      \"overUnderHalf\": [\n" +
                "        [\n" +
                "          2135718,\n" +
                "          1,\n" +
                "          1,\n" +
                "          0.68,\n" +
                "          1.02,\n" +
                "          1,\n" +
                "          0.68,\n" +
                "          1.02,\n" +
                "          \"2022-08-29 18:29:00\",\n" +
                "          2\n" +
                "        ]\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}"
            ,BaseLiveOdds::class.java)
        coEvery { apiHelper.getLiveOdds() }returns liveOddsMockResponse
        val vm=MainViewModel(apiHelper = apiHelper)

        val observer =
            Observer<Resource<BaseLiveOdds>> { t ->
                when (t?.status) {
                    SUCCESS -> {
                        assert(true)
                    }
                    ERROR -> {
                        assert(false)
                    }
                    LOADING -> {

                    }
                    else -> {
                        assert(false)
                    }
                }
            }
        vm.liveOddsLiveData.observeForever(observer)
      //  vm.makeLiveOddsCall()
    }

}