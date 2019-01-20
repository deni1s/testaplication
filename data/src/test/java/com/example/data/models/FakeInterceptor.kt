package com.example.data.models

import okhttp3.*
import java.io.IOException
import com.example.data.BuildConfig


class FakeInterceptor(stringFromFile: String) : Interceptor {

    private val TAG = FakeInterceptor::class.java.simpleName

    private val contentString = stringFromFile
    // FAKE RESPONSES.
//    private val TEACHER_ID_1 = "{\"id\":1,\"age\":28,\"name\":\"Victor Apoyan\"}"
//    private val TEACHER_ID_2 = "{\"id\":1,\"age\":16,\"name\":\"Tovmas Apoyan\"}"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        if (BuildConfig.DEBUG) {
//            Log.d(TAG, "---- DEBUG --- DEBUG -- DEBUG - DEBUG -- DEBUG --- DEBUG ----")
 //           Log.d(TAG, "----                FAKE SERVER RESPONSES                ----")
            val responseString: String
            // Get Request URI.
            val uri = chain.request().url().uri()
 //           Log.d(TAG, "---- Request URL: " + uri.toString())
            // Get Query String.
            val query = uri.getQuery()
            // Parse the Query String.
//            val parsedQuery = query.split("=".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            responseString = contentString

            response = Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/newsresponsejson"), responseString.toByteArray()))
                .addHeader("content-type", "application/newsresponsejson")
                .build()

//            Log.d(TAG, "---- DEBUG --- DEBUG -- DEBUG - DEBUG -- DEBUG --- DEBUG ----")
        } else {
            response = chain.proceed(chain.request())
        }

        return response
    }



    private val stringContent = "{\"status\":\"ok\",\"totalResults\":67,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Zdnet.fr\"},\"author\":\"Charlie Osborne\",\"title\":\"Cryptomonnaies : le projet Unit-e promet 10 000 transactions par seconde\",\"description\":\"Des chercheurs de grandes université américaines dévoilent leur nouveau projet de cryptomonnaie Unit-e avec l’objectif de reléguer le Bitcoin au rang de simple souvenir.\",\"url\":\"https://www.zdnet.fr/actualites/cryptomonnaies-le-projet-unit-e-promet-10-000-transactions-par-seconde-39879467.htm\",\"urlToImage\":\"https://www.zdnet.fr/i/edit/ne/2019/01/Bitcoin620.jpg\",\"publishedAt\":\"2019-01-18T11:27:01Z\",\"content\":\"Avant lexplosion, puis le naufrage, du Bitcoin, la cryptomonnaie a fait tourner les têtes de ceux qui y voyait un moyen de lutter contre les autorités traditionnelles, les banques centrales et les gouvernements vus comme corrompus afin de donner aux individus… [+3561 chars]\"},{\"source\":{\"id\":null,\"name\":\"Bitcoinist.com\"},\"author\":\"Esther Kim\",\"title\":\"Thousands of US Grocery Stores to Sell Bitcoin At Coinstar Kiosks\",\"description\":\"A U.S. Bitcoin ATM company has teamed up with international coin counter Coinstar to offer Bitcoin purchasing facilities in “thousands” of new locations. More Bitcoin For Cash Washington-based Coinme, which has operated Bitcoin ATMs since 2014, confirmed the …\",\"url\":\"https://bitcoinist.com/coinstar-coinme-buy-bitcoin/\",\"urlToImage\":\"https://bitcoinist.com/wp-content/uploads/2019/01/shutterstock_1239771055.jpg\",\"publishedAt\":\"2019-01-18T11:00:57Z\",\"content\":\"A U.S. Bitcoin ATM company has teamed up with international coin counter Coinstar to offer Bitcoin purchasing facilities in thousands of new locations.\\r\\nMore Bitcoin For Cash\\r\\nWashington-based Coinme, which has operated Bitcoin ATMs since 2014, confirmed the … [+2418 chars]\"},{\"source\":{\"id\":null,\"name\":\"Coindesk.com\"},\"author\":\"Omkar Godbole\",\"title\":\"Bitcoin Price Consolidation May Have Weakened Bear Case\",\"description\":\"Bitcoin's eight-day stretch of consolidation looks to have weakened the prospects of a drop back to December lows near $3,100.\",\"url\":\"https://www.coindesk.com/bitcoin-price-consolidation-may-have-weakened-bear-case\",\"urlToImage\":\"https://static.coindesk.com/wp-content/uploads/2018/07/pantera.jpg\",\"publishedAt\":\"2019-01-18T11:00:09Z\",\"content\":\"Bitcoin’s eight-day stretch of consolidation looks to have weakened the prospects of a drop back to December lows near $3,100.\\r\\nThe leading cryptocurrency by market value has been restricted to a narrow range of $3,500 to $3,700 since Jan. 11.\\r\\nThat range pla… [+1980 chars]\"},{\"source\":{\"id\":null,\"name\":\"Infoworld.com\"},\"author\":\"David Linthicum, David Linthicum\",\"title\":\"Thinking about blockchain? Do it in the cloud\",\"description\":\"According to figures provided to CoinDesk from jobs site Indeed.com, searches for roles involving bitcoin, blockchain, and cryptocurrency dropped by 3.06 percent from October 2017 to October 2018. Why? My experience is that enterprises are dealing something t…\",\"url\":\"https://www.infoworld.com/article/3333920/cloud-computing/thinking-about-blockchain-do-it-in-the-cloud.html\",\"urlToImage\":\"https://images.idgesg.net/images/article/2018/12/blockchain-mobile-technology-100782467-large.3x2.jpg\",\"publishedAt\":\"2019-01-18T11:00:00Z\",\"content\":\"According tofigures provided to CoinDeskfrom jobs site Indeed.com, searches for roles involving bitcoin,blockchain, and cryptocurrency dropped by 3.06 percent from October 2017 to October 2018.\\r\\nWhy? My experience is that enterprises are dealing something tha… [+2383 chars]\"},{\"source\":{\"id\":null,\"name\":\"Sys-con.com\"},\"author\":null,\"title\":\"Rene Bostic Joins @CloudEXPO Faculty | @ReneBosticatIBM @IBMCloud @IBMBlockchain #FinTech #Blockchain #SmartCities\",\"description\":\"Blockchain has shifted from hype to reality across many industries including Financial Services, Supply Chain, Retail, Healthcare and Government. While traditional tech and crypto organizations are generally male dominated, women have embraced blockchain tech…\",\"url\":\"http://web2.sys-con.com/node/4368414\",\"urlToImage\":\"http://res.cdn.sys-con.com/story/feb18/4238396/Rene%20Bostic%20468.JPG\",\"publishedAt\":\"2019-01-18T11:00:00Z\",\"content\":\"Power Panel: Women in Blockchain\\r\\nRegister for this session  Here\\r\\nBlockchain has shifted from hype to reality across many industries including Financial Services, Supply Chain, Retail, Healthcare and Government. While traditional tech and crypto organization… [+11331 chars]\"},{\"source\":{\"id\":\"vice-news\",\"name\":\"Vice News\"},\"author\":\"Steve Salter, Ryan White\",\"title\":\"vetements celebrates fuck-the-system nerds\",\"description\":\"“Geeks have become the new punks, they’re changing the world.”\",\"url\":\"https://i-d.vice.com/en_uk/article/j57evb/vetements-autumn-winter-19\",\"urlToImage\":\"https://video-images.vice.com/articles/5c41a85f4e8cb60007288850/lede/1547808146902-AW19M-Vetements-002.jpeg?crop=1xw:0.3738xh;0xw,0.1337xh&resize=1200:*\",\"publishedAt\":\"2019-01-18T10:53:19Z\",\"content\":\"While Demna Gvasalia has been at the forefront of crafting new glamour for a new generation at Balenciaga -- his neo-tailoring has inspired a wave that has swept over the autumn/winter 19 mens shows -- he used his latest, ever disruptive Vetements show to rev… [+2536 chars]\"},{\"source\":{\"id\":\"focus\",\"name\":\"Focus\"},\"author\":\"BTC-ECHO\",\"title\":\"- Ethereum hat ein Zentralisierungsproblem\",\"description\":\"Um den Bitcoin-Nachfolger Ethereum ist es momentan nicht bestens bestellt. Da die Nodes immer teurer werden, weichen Entwickler zunehmend auf Infura aus.\",\"url\":\"https://www.focus.de/finanzen/boerse/kryptowaehrungen/ethereum-hat-ein-zentralisierungsproblem_id_10202405.html\",\"urlToImage\":\"https://p5.focus.de/img/kryptowaehrungen/crop10202404/321271671-w1200-h627-o-q75-p5/compressed-18b06494-471d-4933-9436-0edf2cdc41c2central-ethereum-shutterstock-498224815-602x400.jpg\",\"publishedAt\":\"2019-01-18T10:36:02Z\",\"content\":\"Wie es aussieht, hat das drittgrößte Blockchainprojekt nach Bitcoin mit einem Rentabilitätsproblem zu kämpfen. Was zu Beginn des Jahres 2017 nach einer immensen digitalen Gelddruckmaschine aussah, scheint sich bisweilen einfach nicht mehr zu lohnen. Das führt… [+2607 chars]\"},{\"source\":{\"id\":null,\"name\":\"Hurriyet.com.tr\"},\"author\":\"hurriyet.com.tr\",\"title\":\"Bölgesel FinTech ekosistemi 2019’da nasıl şekillenecek?\",\"description\":\"Finans dünyası yeni yılda da teknoloji ekseninde dönüşümünü sürdürüyor. Gelişmekte olan pazarlar için tasarlanan kullanıcı dostu çözümlerin ivme kazandığı 2018 yılının ardından, bölgesel finansal teknolojiler (FinTech) pazarını hareketli bir 2019 bekliyor. Me…\",\"url\":\"http://www.hurriyet.com.tr/teknoloji/bolgesel-fintech-ekosistemi-2019da-nasil-sekillenecek-41087232\",\"urlToImage\":\"http://i.hurimg.com/i/hurriyet/75/0x0/5c41a9aec03c0e20c067398e.jpg\",\"publishedAt\":\"2019-01-18T10:25:51Z\",\"content\":\"Blockchain'in yaygnlamas için öncelikle insanlarn bu teknolojiyi daha iyi tanmas gerekiyor. Kripto para blockchain olmadan çalmasa da, blockchain'in kullanm sahas Bitcoin ve türevleriyle snrl deil. nsanlara blockchain ile kripto para arasndaki fark öretmek, b… [+4125 chars]\"},{\"source\":{\"id\":null,\"name\":\"Yahoo.com\"},\"author\":null,\"title\":\"Grayscale Adds Stellar as Latest Cryptocurrency Investment Trust\",\"description\":null,\"url\":\"https://finance.yahoo.com/news/grayscale-adds-stellar-latest-cryptocurrency-101900346.html\",\"urlToImage\":\"https://s.yimg.com/uu/api/res/1.2/zxMMygWDFSOQx7wnqqEViw--~B/aD00OTM7dz03NDA7c209MTthcHBpZD15dGFjaHlvbg--/https://media.zenfs.com/en-US/cointelegraph_667/f1d6b2b6ac4407c5541c5c77482da2c6\",\"publishedAt\":\"2019-01-18T10:19:00Z\",\"content\":\"Digital currency investment group Grayscale confirmed it had successfully launched its latest fund, dedicated to Stellars Lumens (XLM) token, in a tweet Jan. 17.\\r\\nGrayscale, which now operates nine cryptocurrency funds, timed the move to coincide with a chang… [+2027 chars]\"},{\"source\":{\"id\":null,\"name\":\"Sys-con.com\"},\"author\":null,\"title\":\"Adobe #Blockchain Keynote at @CloudEXPO New York | @Adobe @JohnBBates @24Notion #FinTech #Bitcoin #SmartCities\",\"description\":\"In very short order, the term \\\"Blockchain\\\" has lost an incredible amount of meaning. With too many jumping on the bandwagon, the market is inundated with projects and use cases that miss the real potential of the technology. We have to begin removing Blockcha…\",\"url\":\"http://virtualization.sys-con.com/node/4365172\",\"urlToImage\":\"http://res.cdn.sys-con.com/story/jan19/4365172/Screen%20Shot%202019-01-09%20at%209.07.40%20AM.png\",\"publishedAt\":\"2019-01-18T10:15:00Z\",\"content\":\"Blockchain Is Not Solving Real Problems\\r\\nDownload session slides Here\\r\\nIn very short order, the term \\\"Blockchain\\\" has lost an incredible amount of meaning. With too many jumping on the bandwagon, the market is inundated with projects and use cases that miss t… [+9071 chars]\"},{\"source\":{\"id\":\"info-money\",\"name\":\"InfoMoney\"},\"author\":\"Weruska Goeking\",\"title\":\"Os 5 assuntos que vão agitar os mercados nesta sexta-feira\",\"description\":\"China e Estados Unidos acenam para fim da guerra comercial e animam mercados\",\"url\":\"https://www.infomoney.com.br/mercados/acoes-e-indices/noticia/7875426/os-5-assuntos-que-vao-agitar-os-mercados-nesta-sexta-feira\",\"urlToImage\":\"https://images.immedia.com.br/32/32365_2_L.jpg\",\"publishedAt\":\"2019-01-18T10:14:00Z\",\"content\":\"SÃO PAULO - O otimismo global ganha força com sinais de que as negociações entre China e Estados Unidos têm avançado e o clima deve favorecer o mercado doméstico após o Ibovespa superar a marca dos 95 mil pontos na véspera e renovar mais uma vez sua máxima hi… [+8363 chars]\"},{\"source\":{\"id\":\"focus\",\"name\":\"Focus\"},\"author\":\"Stockpulse\",\"title\":\"- Crowd-Sentiment für Bitcoin im positiven Bereich. EOS, Stellar, XRP werden in Social Media heute heiß diskutiert\",\"description\":\"Die momentane Stimmungslage sehen die Marktteilnehmer in Social Media unter leicht positiven Aspekten: Für Bitcoin liegt der Sentiment-Wert aktuell (10:51 Uhr MEZ) bei +15 Punkten (von max. +100 Punkten). Für EOS zeigt das Stimmungsbarometer momentan +88 Punk…\",\"url\":\"https://www.focus.de/finanzen/boerse/f100/stockpulse/crowd-sentiment-fuer-bitcoin-im-positiven-bereich-eos-stellar-xrp-werden-in-social-media-heute-heiss-diskutiert_id_10202084.html\",\"urlToImage\":\"https://p5.focus.de/img/stockpulse/crop10202083/4972717331-w1200-h627-o-q75-p5/compressed-724600d2-2649-42ec-bbb2-fd19985798f4shutterstock-795136543-cryptos-computer-screen-1280.jpg\",\"publishedAt\":\"2019-01-18T10:04:20Z\",\"content\":\"Nachfolgend die aktuellen Stimmungs- und Buzzwerte für die wichtigsten Kryptowährungen (Sentiment: max: +100 Punkte, min: -100 Punkte; Stand: 10:50 Uhr MEZ)\\r\\nKrypto\\r\\nSentiment\\r\\nBuzz\\r\\nMkt Cap\\r\\nBitcoin\\r\\n+15 Punkte\\r\\n66%\\r\\n64,2 Mrd\\r\\nXRP\\r\\n+45 Punkte\\r\\n124%\\r\\n13,5 Mrd… [+1524 chars]\"},{\"source\":{\"id\":\"focus\",\"name\":\"Focus\"},\"author\":\"Stockpulse\",\"title\":\"- Open Trading Network, Kora Network Token und GameChain System: Diese Coins werden in Social Media aktuell am meisten diskutiert\",\"description\":\"Zum Ende der Handelswoche stehen heute diese 10 Kryptowährungen im Fokus der Marktteilnehmer in Social Media (Stand: 11:00 Uhr MEZ):\",\"url\":\"https://www.focus.de/finanzen/boerse/f100/stockpulse/open-trading-network-kora-network-token-und-gamechain-system-diese-coins-werden-in-social-media-aktuell-am-meisten-diskutiert_id_10202082.html\",\"urlToImage\":\"https://p5.focus.de/img/stockpulse/crop10202081/2682712524-w1200-h627-o-q75-p5/compressed-0468091d-8170-4098-8dde-16e6caaca917shutterstock-795136543-cryptos-computer-screen-1280.jpg\",\"publishedAt\":\"2019-01-18T10:04:18Z\",\"content\":\"Rang\\r\\nKrypto\\r\\nTicker\\r\\nBuzz\\r\\nKurs\\r\\nVeränderung\\r\\nZeit\\r\\n1.\\r\\nOpen Trading Network\\r\\nOTN\\r\\n3.788%\\r\\n0.036719486066 USD\\r\\n+1.1%\\r\\n11:00 Uhr\\r\\n2.\\r\\nKora Network Token\\r\\nKNT\\r\\n2.739%\\r\\n0.000918399269602 USD\\r\\n-0.3%\\r\\n11:00 Uhr\\r\\n3.\\r\\nGameChain System\\r\\nGCS\\r\\n2.707%\\r\\n0.00041037490916… [+1808 chars]\"},{\"source\":{\"id\":null,\"name\":\"Arageek.com\"},\"author\":\"محمد يونس\",\"title\":\"قبل المغامرة مع بيتكوين.. نقاط أساسية عليك معرفتها قبل بدء الاستثمار!\",\"description\":\"أصبح العالم أكثر اعتمادًا على الإنترنت لذلك ليس من المستغرب أن بيتكوين، تلك العملة الرقمية العالمية قد اجتذبت اهتمام المستثمرين، فهي متاحة للجميع وتوفر فرصة رائعة للتعمق في فئة أصول جديدة تمامًا. قد يبدو الاستثمار في البيتكوين مخيفًا، لك الأمر يتطلب بعض الوقت…\",\"url\":\"https://www.arageek.com/tech/2019/01/18/what-to-know-before-investing-bitcoin.html\",\"urlToImage\":\"https://static.arageek.com/wp-content/uploads/2019/01/Copy-of-etoro-articles21.jpg\",\"publishedAt\":\"2019-01-18T10:00:30Z\",\"content\":\".\\r\\n .\\r\\n :\\r\\n .\\r\\n  :\\r\\n Bitcoin \\r\\n .\\r\\n 21 !\\r\\n :\\r\\n.\\r\\n .\\r\\n .\\r\\n.\\r\\n . 2017:\\r\\nBitcoin WisdomCryptowatch .\\r\\n !!\\r\\n   !\\r\\n : \\r\\n.\\r\\n .\\r\\nLedger  TREZOR .\\r\\n 5 .\\r\\n Bitcoin .\\r\\n .\\r\\n :   \\r\\n.\\r\\n   .\\r\\n : \\r\\n… .. !\"},{\"source\":{\"id\":null,\"name\":\"Itproportal.com\"},\"author\":\"Babis Marmanis\",\"title\":\"Distributed ledgers: The key concept behind Bitcoin and the cryptocurrency hype\",\"description\":\"Innovators are exploring ways to use blockchain to disrupt and transform traditional business models across global supply chains, financial services, healthcare, government and many other industries\",\"url\":\"https://www.itproportal.com/features/distributed-ledgers-the-key-concept-behind-bitcoin-and-the-cryptocurrency-hype/\",\"urlToImage\":\"https://cdn.mos.cms.futurecdn.net/8YV42DcKTfsxEFG6Xe22yd-1200-80.jpg\",\"publishedAt\":\"2019-01-18T10:00:24Z\",\"content\":\"Bitcoin is the world's first decentralised digital payments system. In the beginning of this decade, very few people knew about it and they were mostly hobbyists on Internet fora. Just before the end of 2017, the value of a bitcoin (BTC) exceeded $20,000. The… [+6586 chars]\"},{\"source\":{\"id\":null,\"name\":\"Dailyfx.com\"},\"author\":\"Yoav Nizard, Analyste Marché, Yoav Nizard\",\"title\":\"Ripple – Bitcoin : vendues, les cryptomonnaies peuvent espérer rebondir depuis leurs creux\",\"description\":\"Le recul du Bitcoin et du XRP, devise virtuelle fondée par le réseau Ripple, les a mené à proximité de leur creux extrême de 2018.\",\"url\":\"https://www.dailyfx.com/francais/actualite_forex_trading/fondamentaux/rapports_speciaux/2019/01/18/Ripple-Bitcoin-vendues-les-cryptomonnaies-peuvent-esperer-rebondir-depuis-leurs-creux.html\",\"urlToImage\":null,\"publishedAt\":\"2019-01-18T09:52:00Z\",\"content\":\"Ripple et Bitcoin ne sont pas parvenus à franchir leurs résistances.\\r\\nSujets évoqués dans cet article Ripple et Bitcoin\\r\\nBitcoin et Ripple reculent mais se maintiennent dans leur tendance latérale de moyen terme\\r\\nLe prix de la devise virtuelle créée par Rippl… [+2904 chars]\"},{\"source\":{\"id\":null,\"name\":\"Wall-street.ro\"},\"author\":\"office@wall-street.ro (Redactia Wall-Street)\",\"title\":\"Guvernul vrea bani din bitcoin: cum ii va impozita pe cei care au tranzactionat\",\"description\":\"Duminica intra in vigoare legea 30 din 2019 privind aprobarea OUG 25 din 2018 si care a fost publicata in Monitorul Oficial. Ca noutate se prevede impozitarea veniturilor din monede virtuale, avertizeaza consultantii fiscali.\",\"url\":\"https://www.wall-street.ro/articol/Finante-Banci/238070/guvernul-vrea-bani-din-bitcoin-cum-ii-va-impozita-pe-cei-care-au-tranzactionat.html\",\"urlToImage\":\"https://img.wall-street.ro/article_pictures/238070_articol.jpg?v=1547808339\",\"publishedAt\":\"2019-01-18T09:46:11Z\",\"content\":\"Potrivit consultantului fiscal Adrian Benta, noua forma a Codului Fiscal prevede impozitarea bitcoinului. „Gata au gasit metoda sa impoziteze si bitcoin-ul! 10% din castig, in categoria veniturilor din alte surse. Legea Nr 30/2019”, a afirmat acesta vineri, i… [+788 chars]\"},{\"source\":{\"id\":\"crypto-coins-news\",\"name\":\"Crypto Coins News\"},\"author\":\"Joseph Young\",\"title\":\"Crypto Market Up $6 Billion in 4 Days: Is Bitcoin King During the Bear Market?\",\"description\":\"Since January 14, subsequent to a minor correction, the crypto market has added $6 billion to its valuation as the Bitcoin price maintained stability. While many cryptocurrencies, especially low market cap tokens fell substantially earlier this week, Bitcoin …\",\"url\":\"https://www.ccn.com/crypto-market-up-6-billion-in-4-days-is-bitcoin-king-during-the-bear-market/\",\"urlToImage\":\"https://www.ccn.com/wp-content/uploads/2019/01/Bitcoin-bear-Shutterstock.jpg\",\"publishedAt\":\"2019-01-18T09:39:39Z\",\"content\":\"Since January 14, subsequent to a minor correction, the crypto market has added $6 billion to its valuation as the Bitcoin price maintained stability.\\r\\nWhile many cryptocurrencies, especially low market cap tokens fell substantially earlier this week, Bitcoin… [+2977 chars]\"},{\"source\":{\"id\":\"focus\",\"name\":\"Focus\"},\"author\":\"BTC-ECHO\",\"title\":\"- Binance.je: Europäischer Ableger legt holprigen Start hin\",\"description\":\"Die europäische Binance-Tochter binance.je gab in der Nacht zum 16. Januar 2018 den Start ihres operativen Geschäfts bekannt. Die Meldung führte zu einem regelrechten Run auf die neue Exchange – was wiederum prompt die Server lahmlegte.\",\"url\":\"https://www.focus.de/finanzen/boerse/kryptowaehrungen/binance-je-europaeischer-ableger-legt-holprigen-start-hin_id_10201859.html\",\"urlToImage\":\"https://p5.focus.de/img/kryptowaehrungen/crop10201858/8182715487-w1200-h627-o-q75-p5/compressed-4f0b6b60-fc9e-432c-bdf4-e4b74466ebeebinanace-shutterstock-300413699-533x400.jpg\",\"publishedAt\":\"2019-01-18T09:36:02Z\",\"content\":\"Für bitcoin.de &amp; Co. gibt es neue Konkurrenz. Markt-Schwergewicht Binance lässt Worten Taten folgen und drängt offensiv auf den europäischen Krypto-Markt. Auf binance.je können europäische Nutzer seit dem 17. Januar diverse Token handeln. Dabei hat die ma… [+2091 chars]\"},{\"source\":{\"id\":null,\"name\":\"Cnews.ru\"},\"author\":null,\"title\":\"Разработана криптовалюта - «убийца» биткоина\",\"description\":\"Новая криптовалюта Unit-e , созданная североамериканскими исследователями, работает быстрее Bitcoin в несколько тысяч раз. Разработчики биткоина готовят ответные меры по повышению скорости работы сети....\",\"url\":\"http://www.cnews.ru/news/top/2019-01-18_ssha_i_kanada_razrabotali_kriptovalyutuubijtsu\",\"urlToImage\":\"http://filearchive.cnews.ru/img/zoom/2019/01/18/bit600.JPG\",\"publishedAt\":\"2019-01-18T09:31:00Z\",\"content\":\"(MIT), Unit-e. Distributed Technology Research (DTR), , , Bloomberg. - Pantera Capital. \\r\\n, \\r\\n Unit-e Bitcoin, Etherium , . : Unit-e 10 . .\\r\\n , 7 , , Etherium 30 . , : Visa, , 1700 .\\r\\n, , . \\r\\nUnit-e, , , . , .\\r\\n, . , Unit-e, .\\r\\n 2018 . Lightning Network, . 20… [+271 chars]\"}]}"
}