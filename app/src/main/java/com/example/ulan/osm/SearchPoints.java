package com.example.ulan.osm;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by Админ on 12.12.2016.
 */

public class SearchPoints {
    ArrayList<NearRoutes> nearRoutes=new ArrayList<>();
    public ArrayList<NearRoutes> getNearRoutes(){
        ArrayList<GeoPoint> waypoints=new ArrayList<>();



        waypoints.add(new GeoPoint(42.82556130410877,74.54463958740234));
        waypoints.add(new GeoPoint(42.827072098038144,74.54850196838379));
        waypoints.add(new GeoPoint(42.82814222139804,74.55172061920166));
        waypoints.add(new GeoPoint(42.829149379401606,74.55562591552734));
        waypoints.add(new GeoPoint(42.82959000586782,74.5603895187378));
        waypoints.add(new GeoPoint(42.82959000586782,74.5645523071289));
        waypoints.add(new GeoPoint(42.829243799623086,74.56991672515869));
        waypoints.add(new GeoPoint(42.83273724642086,74.56897258758545));
        waypoints.add(new GeoPoint(42.8374892759524,74.56867218017578));
        waypoints.add(new GeoPoint(42.841831870777796,74.5688009262085));
        waypoints.add(new GeoPoint(42.84381425824815,74.57167625427246));
        waypoints.add(new GeoPoint(42.84375132629021,74.57661151885986));
        waypoints.add(new GeoPoint(42.843625462182004,74.58283424377441));
        waypoints.add(new GeoPoint(42.84346813168613,74.58798408508301));
        waypoints.add(new GeoPoint(42.84334226700096,74.5922327041626));

        waypoints.add(new GeoPoint(42.8432478683188,74.59798336029053));
        waypoints.add(new GeoPoint(42.842838805696026,74.60180282592773));
        waypoints.add(new GeoPoint(42.842933205003234,74.60587978363037));
        waypoints.add(new GeoPoint(42.8427444062446,74.60892677307129));
        waypoints.add(new GeoPoint(42.846237089884106,74.60914134979248));
        waypoints.add(new GeoPoint(42.84969811310071,74.60969924926758));
        waypoints.add(new GeoPoint(42.85466906167286,74.61047172546387));
        waypoints.add(new GeoPoint(42.85973398377773,74.61077213287354));
        waypoints.add(new GeoPoint(42.863760457706775,74.61145877838135));
        waypoints.add(new GeoPoint(42.869548053927616,74.61154460906982));
        waypoints.add(new GeoPoint(42.86794393759348,74.6116304397583));
        waypoints.add(new GeoPoint(42.873542436971015,74.61205959320068));
        waypoints.add(new GeoPoint(42.87687613355197,74.61240291595459));
        waypoints.add(new GeoPoint(42.87923477234301,74.61201667785645));
        waypoints.add(new GeoPoint(42.88275683842475,74.61188793182373));
        waypoints.add(new GeoPoint(42.88524103196829,74.61441993713379));

        waypoints.add(new GeoPoint(42.88524103196829,74.619140625));
        waypoints.add(new GeoPoint(42.886152926016855,74.62502002716064));
        waypoints.add(new GeoPoint(42.886624590062965,74.62995529174805));
        waypoints.add(new GeoPoint(42.88794523020608,74.63523387908936));
        waypoints.add(new GeoPoint(42.88505236255113,74.63686466217041));
        waypoints.add(new GeoPoint(42.88127885303417,74.63712215423584));
        waypoints.add(new GeoPoint(42.87803973995659,74.63776588439941));
        waypoints.add(new GeoPoint(42.873039221843875,74.63789463043213));
        waypoints.add(new GeoPoint(42.868635914458274,74.6367359161377));
        waypoints.add(new GeoPoint(42.86555341237382,74.63557720184326));
        waypoints.add(new GeoPoint(42.862974057652735,74.63682174682617));
        waypoints.add(new GeoPoint(42.86026876496821,74.63690757751465));
        waypoints.add(new GeoPoint(42.85737459970219,74.63703632354736));


        waypoints.add(new GeoPoint(42.85476344290181,74.63484764099121));
        waypoints.add(new GeoPoint(42.85249825359579,74.6340537071228));
        waypoints.add(new GeoPoint(42.849981279145176,74.63407516479492));
        waypoints.add(new GeoPoint(42.84718102453893,74.63431119918823));
        waypoints.add(new GeoPoint(42.84552912942721,74.63499784469604));
        waypoints.add(new GeoPoint(42.843782792277196,74.63564157485962));
        waypoints.add(new GeoPoint(42.84219373990316,74.63630676269531));
        waypoints.add(new GeoPoint(42.84164306865359,74.63559865951538));
        waypoints.add(new GeoPoint(42.84180040379715,74.63253021240234));
        waypoints.add(new GeoPoint(42.8418476042621,74.63036298751831));
        waypoints.add(new GeoPoint(42.84186333774239,74.62841033935547));
        waypoints.add(new GeoPoint(42.84216227310683,74.62549209594727));
        waypoints.add(new GeoPoint(42.84213080629447,74.62268114089966));
        waypoints.add(new GeoPoint(42.842429740364786,74.61952686309814));
        waypoints.add(new GeoPoint(42.842461207024876,74.6171236038208));
        waypoints.add(new GeoPoint(42.84258707350502,74.61416244506836));
        waypoints.add(new GeoPoint(42.84290173858353,74.61102962493896));

        NearRoutes nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("100");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);
        waypoints=new ArrayList<>();
        waypoints.add(new GeoPoint(42.8727247103058,74.42773818969727));
        waypoints.add(new GeoPoint(42.87278761274164,74.43198680877686));
        waypoints.add(new GeoPoint(42.87247309992134,74.43614959716797));
        waypoints.add(new GeoPoint(42.87256745393573,74.44147109985352));
        waypoints.add(new GeoPoint(42.87322792799739,74.44752216339111));
        waypoints.add(new GeoPoint(42.873856944341846,74.45498943328857));
        waypoints.add(new GeoPoint(42.873856944341846,74.46138381958008));
        waypoints.add(new GeoPoint(42.87413999960522,74.46730613708496));
        waypoints.add(new GeoPoint(42.87413999960522,74.47288513183594));
        waypoints.add(new GeoPoint(42.87429725196834,74.47726249694824));
        waypoints.add(new GeoPoint(42.874706106237184,74.48309898376465));
        waypoints.add(new GeoPoint(42.874894807293835,74.48803424835205));
        waypoints.add(new GeoPoint(42.87546090700176,74.49421405792236));
        waypoints.add(new GeoPoint(42.87511495779733,74.5008659362793));
        waypoints.add(new GeoPoint(42.874485954275166,74.5054578781128));

        waypoints.add(new GeoPoint(42.8741714501099,74.51138019561768));
        waypoints.add(new GeoPoint(42.87401419742623,74.51550006866455));
        waypoints.add(new GeoPoint(42.874202900598554,74.52198028564453));
        waypoints.add(new GeoPoint(42.87451740460354,74.52794551849365));
        waypoints.add(new GeoPoint(42.87445450393076,74.53189373016357));
        waypoints.add(new GeoPoint(42.875272207676126,74.53927516937256));
        waypoints.add(new GeoPoint(42.875775404595586,74.5448112487793));
        waypoints.add(new GeoPoint(42.87637294560815,74.55099105834961));
        waypoints.add(new GeoPoint(42.87719062393322,74.55618381500244));
        waypoints.add(new GeoPoint(42.87808691272639,74.56165552139282));
        waypoints.add(new GeoPoint(42.87766235649983,74.56560373306274));
        waypoints.add(new GeoPoint(42.877520837108506,74.57062482833862));
        waypoints.add(new GeoPoint(42.87615279959249,74.57174062728882));
        waypoints.add(new GeoPoint(42.8759955519576,74.57461595535278));
        waypoints.add(new GeoPoint(42.8758540287436,74.5773196220398));


        waypoints.add(new GeoPoint(42.87560243111733,74.58017349243164));
        waypoints.add(new GeoPoint(42.875555256448195,74.58465814590454));
        waypoints.add(new GeoPoint(42.8753036576038,74.5885419845581));
        waypoints.add(new GeoPoint(42.875130682803245,74.59214687347412));
        waypoints.add(new GeoPoint(42.87508350777349,74.59467887878418));
        waypoints.add(new GeoPoint(42.874926257413854,74.59841251373291));
        waypoints.add(new GeoPoint(42.874769006653516,74.60107326507568));
        waypoints.add(new GeoPoint(42.87465893088287,74.60633039474487));
        waypoints.add(new GeoPoint(42.87531938256163,74.60931301116943));
        waypoints.add(new GeoPoint(42.8756967803474,74.6116304397583));
        waypoints.add(new GeoPoint(42.87566533062006,74.61446285247803));
        waypoints.add(new GeoPoint(42.875633880876705,74.61849689483643));
        waypoints.add(new GeoPoint(42.8753036576038,74.62280988693237));
        waypoints.add(new GeoPoint(42.87531938256163,74.62660789489746));
        waypoints.add(new GeoPoint(42.87514640780515,74.62961196899414));

        waypoints.add(new GeoPoint(42.87525648270628,74.63364601135254));
        waypoints.add(new GeoPoint(42.875429457154226,74.63705778121948));
        waypoints.add(new GeoPoint(42.87769380520938,74.6376371383667));
        waypoints.add(new GeoPoint(42.87989517504822,74.63733673095703));
        waypoints.add(new GeoPoint(42.88190778731522,74.63692903518677));
        waypoints.add(new GeoPoint(42.884124729523926,74.63649988174438));
        waypoints.add(new GeoPoint(42.88682897669629,74.63585615158081));
        waypoints.add(new GeoPoint(42.88830682912404,74.63780879974365));
        waypoints.add(new GeoPoint(42.888558374947195,74.64031934738159));
        waypoints.add(new GeoPoint(42.88871559056573,74.64405298233032));
        waypoints.add(new GeoPoint(42.888526931775395,74.64692831039429));
        waypoints.add(new GeoPoint(42.88879419822473,74.65111255645752));
        waypoints.add(new GeoPoint(42.888526931775395,74.65503931045532));
        waypoints.add(new GeoPoint(42.888369715676,74.6575927734375));
        waypoints.add(new GeoPoint(42.88805528227508,74.66038227081299));

        waypoints.add(new GeoPoint(42.88783517794072,74.66390132904053));
        waypoints.add(new GeoPoint(42.88764651645762,74.66692686080933));
        waypoints.add(new GeoPoint(42.88715913828883,74.669930934906));
        waypoints.add(new GeoPoint(42.886876142746274,74.6729564666748));
        waypoints.add(new GeoPoint(42.88624725911459,74.67566013336182));
        waypoints.add(new GeoPoint(42.88583848131591,74.6778917312622));
        waypoints.add(new GeoPoint(42.885413978427124,74.68089580535889));
        waypoints.add(new GeoPoint(42.884690744555385,74.68402862548828));
        waypoints.add(new GeoPoint(42.883998947700576,74.68754768371582));
        waypoints.add(new GeoPoint(42.88354298644115,74.68982219696045));
        waypoints.add(new GeoPoint(42.88277256148325,74.69192504882813));
        waypoints.add(new GeoPoint(42.88113734193738,74.69196796417236));
        waypoints.add(new GeoPoint(42.87987945125642,74.69136714935303));
        waypoints.add(new GeoPoint(42.87860581081416,74.69093799591064));
        waypoints.add(new GeoPoint(42.87706482797305,74.6905517578125));


        waypoints.add(new GeoPoint(42.8758540287436,74.6905517578125));
        waypoints.add(new GeoPoint(42.8741714501099,74.69070196151733));
        waypoints.add(new GeoPoint(42.872551728276676,74.69074487686157));
        waypoints.add(new GeoPoint(42.87097914213699,74.69053030014038));
        waypoints.add(new GeoPoint(42.869752497131884,74.69040155410767));
        waypoints.add(new GeoPoint(42.869878307997936,74.69304084777832));
        waypoints.add(new GeoPoint(42.869752497131884,74.6964955329895));
        waypoints.add(new GeoPoint(42.868667367767806,74.69756841659546));
        waypoints.add(new GeoPoint(42.86880890746237,74.70044374465942));



        nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("258");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);

        waypoints=new ArrayList<>();
        waypoints.add(new GeoPoint(42.85521961680832,74.68828797340393));
        waypoints.add(new GeoPoint(42.85530613216907,74.68670010566711));
        waypoints.add(new GeoPoint(42.85538478239185,74.68533754348755));
        waypoints.add(new GeoPoint(42.855526352540416,74.68330979347229));
        waypoints.add(new GeoPoint(42.855526352540416,74.68188285827637));
        waypoints.add(new GeoPoint(42.85563646243159,74.68044519424438));
        waypoints.add(new GeoPoint(42.85567578734515,74.67907190322876));
        waypoints.add(new GeoPoint(42.85573084218205,74.67729091644287));
        waypoints.add(new GeoPoint(42.85580162690024,74.67585325241089));
        waypoints.add(new GeoPoint(42.85588027649194,74.67420101165771));
        waypoints.add(new GeoPoint(42.85596679092712,74.67285990715027));
        waypoints.add(new GeoPoint(42.855998250691684,74.67146515846252));
        waypoints.add(new GeoPoint(42.85607690003297,74.66917991638184));
        waypoints.add(new GeoPoint(42.85617914402689,74.66765642166138));
        waypoints.add(new GeoPoint(42.8559825208114,74.66597199440002));
        waypoints.add(new GeoPoint(42.85524321191873,74.66501712799072));

        waypoints.add(new GeoPoint(42.85497580013957,74.66362237930298));
        waypoints.add(new GeoPoint(42.85532186222164,74.66170191764832));
        waypoints.add(new GeoPoint(42.85562859744588,74.65975999832153));
        waypoints.add(new GeoPoint(42.85566792236443,74.65797901153564));
        waypoints.add(new GeoPoint(42.85573084218205,74.65676665306091));
        waypoints.add(new GeoPoint(42.855652192400015,74.65551137924194));
        waypoints.add(new GeoPoint(42.85557354251781,74.65407371520996));
        waypoints.add(new GeoPoint(42.855424107465666,74.65261459350586));
        waypoints.add(new GeoPoint(42.85544770249795,74.65068340301514));
        waypoints.add(new GeoPoint(42.85550275753819,74.64915990829468));
        waypoints.add(new GeoPoint(42.85578589696987,74.6476686000824));
        waypoints.add(new GeoPoint(42.85611622466605,74.64612364768982));
        waypoints.add(new GeoPoint(42.85617914402689,74.64427828788757));
        waypoints.add(new GeoPoint(42.856163414192686,74.64260458946228));
        waypoints.add(new GeoPoint(42.856218468594854,74.64092016220093));

        waypoints.add(new GeoPoint(42.856375766616296,74.63876366615295));
        waypoints.add(new GeoPoint(42.85624992823119,74.63674664497375));
        waypoints.add(new GeoPoint(42.856281387851524,74.63476181030273));
        waypoints.add(new GeoPoint(42.856367901724745,74.63274478912354));
        waypoints.add(new GeoPoint(42.85649373986944,74.63019132614136));
        waypoints.add(new GeoPoint(42.85628925275409,74.62750911712646));
        waypoints.add(new GeoPoint(42.8566667668997,74.62495565414429));
        waypoints.add(new GeoPoint(42.856603848035704,74.62199449539185));
        waypoints.add(new GeoPoint(42.856745415389554,74.61886167526245));
        waypoints.add(new GeoPoint(42.85683979344513,74.6157717704773));
        waypoints.add(new GeoPoint(42.856965630628196,74.61259603500366));
        waypoints.add(new GeoPoint(42.857138656336225,74.61012840270996));
        waypoints.add(new GeoPoint(42.857012819505755,74.60830450057983));
        waypoints.add(new GeoPoint(42.85687125276494,74.60637331008911));
        waypoints.add(new GeoPoint(42.85699708988391,74.60317611694336));

        waypoints.add(new GeoPoint(42.85699708988391,74.60062265396118));
        waypoints.add(new GeoPoint(42.85723303379079,74.59834814071655));
        waypoints.add(new GeoPoint(42.85726449291026,74.5958161354065));
        waypoints.add(new GeoPoint(42.85756335374586,74.59304809570313));
        waypoints.add(new GeoPoint(42.85756335374586,74.59043025970459));
        waypoints.add(new GeoPoint(42.857531894778646,74.58813428878784));
        waypoints.add(new GeoPoint(42.858821699292,74.58693265914917));
        waypoints.add(new GeoPoint(42.86118102807355,74.58710432052612));
        waypoints.add(new GeoPoint(42.863257162826166,74.58716869354248));
        waypoints.add(new GeoPoint(42.86630832507597,74.58716869354248));
        waypoints.add(new GeoPoint(42.8699726659792,74.58751201629639));
        waypoints.add(new GeoPoint(42.871954150263775,74.58813428878784));
        waypoints.add(new GeoPoint(42.87289769185009,74.58616018295288));
        waypoints.add(new GeoPoint(42.87292914298788,74.58395004272461));
        waypoints.add(new GeoPoint(42.87305494737871,74.58096742630005));

        waypoints.add(new GeoPoint(42.873306555391046,74.57852125167847));
        waypoints.add(new GeoPoint(42.873479535304504,74.57568883895874));
        waypoints.add(new GeoPoint(42.87336945723386,74.5730710029602));
        waypoints.add(new GeoPoint(42.87244164855116,74.5713758468628));
        waypoints.add(new GeoPoint(42.87107349843532,74.5730710029602));
        waypoints.add(new GeoPoint(42.871057772395616,74.57513093948364));
        waypoints.add(new GeoPoint(42.87064889395683,74.5782208442688));
        waypoints.add(new GeoPoint(42.87064889395683,74.58139657974243));
        waypoints.add(new GeoPoint(42.87060171550114,74.58452939987183));


        nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("102");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);



        waypoints=new ArrayList<>();
        waypoints.add(new GeoPoint(42.858499250689455,74.56839323043823));

        waypoints.add(new GeoPoint(42.85941940444318,74.5678460597992));
        waypoints.add(new GeoPoint(42.85963961014565,74.56668734550476));
        waypoints.add(new GeoPoint(42.85848352144626,74.56602215766907));
        waypoints.add(new GeoPoint(42.85765773055133,74.56621527671814));
        waypoints.add(new GeoPoint(42.85702068431517,74.56674098968506));
        waypoints.add(new GeoPoint(42.85662744261721,74.56800699234009));
        waypoints.add(new GeoPoint(42.85659598317319,74.5698630809784));
        waypoints.add(new GeoPoint(42.857626271632206,74.57075357437134));
        waypoints.add(new GeoPoint(42.858805970130945,74.57094669342041));
        waypoints.add(new GeoPoint(42.85973398377773,74.5700991153717));
        waypoints.add(new GeoPoint(42.86048896764153,74.56888675689697));
        waypoints.add(new GeoPoint(42.861912401690134,74.5691442489624));
        waypoints.add(new GeoPoint(42.86292687333096,74.5693588256836));
        waypoints.add(new GeoPoint(42.864389570537654,74.56965923309326));
        waypoints.add(new GeoPoint(42.86541972895399,74.56961631774902));

        waypoints.add(new GeoPoint(42.86677227726045,74.56951975822449));
        waypoints.add(new GeoPoint(42.8689504468324,74.56946611404419));
        waypoints.add(new GeoPoint(42.871057772395616,74.56927299499512));
        waypoints.add(new GeoPoint(42.87135656646485,74.57073211669922));
        waypoints.add(new GeoPoint(42.87237874576272,74.5716118812561));
        waypoints.add(new GeoPoint(42.873510986145774,74.57094669342041));
        waypoints.add(new GeoPoint(42.87327510444561,74.56875801086426));
        waypoints.add(new GeoPoint(42.8722372142543,74.56877946853638));
        waypoints.add(new GeoPoint(42.87454885491588,74.57178354263306));
        waypoints.add(new GeoPoint(42.87596410238254,74.57169771194458));
        waypoints.add(new GeoPoint(42.87596410238254,74.57431554794312));
        waypoints.add(new GeoPoint(42.8757911294332,74.57691192626953));
        waypoints.add(new GeoPoint(42.87571250520505,74.57952976226807));
        waypoints.add(new GeoPoint(42.87547663191952,74.58249092102051));
        waypoints.add(new GeoPoint(42.87546090700176,74.58515167236328));

        waypoints.add(new GeoPoint(42.87536655741107,74.58781242370605));
        waypoints.add(new GeoPoint(42.875209307772685,74.59064483642578));
        waypoints.add(new GeoPoint(42.87500488264376,74.59352016448975));
        waypoints.add(new GeoPoint(42.87495770751783,74.59723234176636));
        waypoints.add(new GeoPoint(42.87478473174758,74.59993600845337));
        waypoints.add(new GeoPoint(42.87462748062662,74.6026611328125));
        waypoints.add(new GeoPoint(42.87458030521219,74.60590124130249));
        waypoints.add(new GeoPoint(42.87454885491588,74.609055519104));
        waypoints.add(new GeoPoint(42.874360152801394,74.61210250854492));
        waypoints.add(new GeoPoint(42.87269325906385,74.61175918579102));
        waypoints.add(new GeoPoint(42.87130938855008,74.61169481277466));
        waypoints.add(new GeoPoint(42.870224286557296,74.61096525192261));
        waypoints.add(new GeoPoint(42.86854155443349,74.6110725402832));
        waypoints.add(new GeoPoint(42.866748686555546,74.61085796356201));
        waypoints.add(new GeoPoint(42.865317500261156,74.6106219291687));

        waypoints.add(new GeoPoint(42.8631942506776,74.61077213287354));
        waypoints.add(new GeoPoint(42.86125967081362,74.61049318313599));
        waypoints.add(new GeoPoint(42.86017439215353,74.6105146408081));
        waypoints.add(new GeoPoint(42.85871159508046,74.61030006408691));
        waypoints.add(new GeoPoint(42.857311681559416,74.61014986038208));
        waypoints.add(new GeoPoint(42.85600611563032,74.6100640296936));
        waypoints.add(new GeoPoint(42.854684791887706,74.6098279953003));
        waypoints.add(new GeoPoint(42.85345782309768,74.60976362228394));
        waypoints.add(new GeoPoint(42.85144428302755,74.60965633392334));
        waypoints.add(new GeoPoint(42.85035883185174,74.60965633392334));
        waypoints.add(new GeoPoint(42.84927336159994,74.60948467254639));
        waypoints.add(new GeoPoint(42.84828226340581,74.60914134979248));
        waypoints.add(new GeoPoint(42.84666186226376,74.60911989212036));
        waypoints.add(new GeoPoint(42.844947022529,74.60892677307129));
        waypoints.add(new GeoPoint(42.84334226700096,74.60875511169434));

        waypoints.add(new GeoPoint(42.841658802185975,74.60854053497314));
        waypoints.add(new GeoPoint(42.840227034331726,74.60864782333374));
        waypoints.add(new GeoPoint(42.838543484632524,74.60832595825195));
        waypoints.add(new GeoPoint(42.837080175202715,74.60839033126831));
        waypoints.add(new GeoPoint(42.83552242063442,74.60793972015381));
        waypoints.add(new GeoPoint(42.8343108065995,74.60772514343262));
        waypoints.add(new GeoPoint(42.832878868477614,74.6075963973999));
        waypoints.add(new GeoPoint(42.831509841668186,74.60766077041626));
        waypoints.add(new GeoPoint(42.83029814896496,74.60733890533447));
        waypoints.add(new GeoPoint(42.82863006560534,74.60718870162964));
        waypoints.add(new GeoPoint(42.82746552789747,74.6070384979248));
        waypoints.add(new GeoPoint(42.82609638118157,74.60686683654785));
        waypoints.add(new GeoPoint(42.82501048463722,74.60675954818726));
        waypoints.add(new GeoPoint(42.823735712180756,74.60654497146606));
        waypoints.add(new GeoPoint(42.822728465970954,74.6061372756958));

        waypoints.add(new GeoPoint(42.82186285064838,74.60452795028687));
        waypoints.add(new GeoPoint(42.82173694195548,74.60233926773071));
        waypoints.add(new GeoPoint(42.82173694195548,74.59965705871582));
        waypoints.add(new GeoPoint(42.8206352299551,74.59802627563477));
        waypoints.add(new GeoPoint(42.81909280017386,74.59688901901245));
        waypoints.add(new GeoPoint(42.81769198876807,74.59622383117676));
        waypoints.add(new GeoPoint(42.816401325451594,74.5954942703247));
        waypoints.add(new GeoPoint(42.81523655738275,74.59467887878418));
        waypoints.add(new GeoPoint(42.81341066058439,74.59384202957153));
        waypoints.add(new GeoPoint(42.81213564895897,74.5932412147522));
        waypoints.add(new GeoPoint(42.810482816989584,74.59246873855591));
        waypoints.add(new GeoPoint(42.80911329418293,74.59180355072021));
        waypoints.add(new GeoPoint(42.80805858353698,74.59103107452393));
        waypoints.add(new GeoPoint(42.80678346157832,74.59028005599976));
        waypoints.add(new GeoPoint(42.80539811410433,74.58944320678711));

        waypoints.add(new GeoPoint(42.80410719421642,74.58834886550903));
        waypoints.add(new GeoPoint(42.80344598091478,74.58746910095215));
        waypoints.add(new GeoPoint(42.80324131822254,74.58500146865845));
        waypoints.add(new GeoPoint(42.803650642929995,74.58298444747925));
        waypoints.add(new GeoPoint(42.803934019987146,74.58070993423462));
        waypoints.add(new GeoPoint(42.804075708028975,74.57847833633423));
        waypoints.add(new GeoPoint(42.80429611100458,74.57573175430298));
        waypoints.add(new GeoPoint(42.804768400451074,74.57337141036987));
        waypoints.add(new GeoPoint(42.80491008658193,74.57058191299438));
        waypoints.add(new GeoPoint(42.80341449439081,74.5697021484375));
        waypoints.add(new GeoPoint(42.802186507456575,74.57047462463379));
        waypoints.add(new GeoPoint(42.801115934857854,74.57111835479736));
        waypoints.add(new GeoPoint(42.80139932352501,74.5730710029602));
        waypoints.add(new GeoPoint(42.8017771730621,74.5748519897461));
        waypoints.add(new GeoPoint(42.80125762935369,74.57796335220337));

        waypoints.add(new GeoPoint(42.80092700835864,74.58070993423462));
        waypoints.add(new GeoPoint(42.80062787355505,74.5842719078064));
        waypoints.add(new GeoPoint(42.802375430109834,74.58452939987183));

        nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("103");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);

        waypoints=new ArrayList<>();

        waypoints.add(new GeoPoint(42.82882677588999,74.60456013679504));

        waypoints.add(new GeoPoint(42.828897591439215,74.6035087108612));
        waypoints.add(new GeoPoint(42.82896840690731,74.60124492645264));
        waypoints.add(new GeoPoint(42.82946411291227,74.59972143173218));
        waypoints.add(new GeoPoint(42.83091186641983,74.59975361824036));
        waypoints.add(new GeoPoint(42.831918979294144,74.59992527961731));
        waypoints.add(new GeoPoint(42.83310703666422,74.59992527961731));
        waypoints.add(new GeoPoint(42.83443668968296,74.60018277168274));
        waypoints.add(new GeoPoint(42.83571910898334,74.6001935005188));
        waypoints.add(new GeoPoint(42.8372689912699,74.60034370422363));
        waypoints.add(new GeoPoint(42.83909418350769,74.60060119628906));
        waypoints.add(new GeoPoint(42.84087212065856,74.60038661956787));
        waypoints.add(new GeoPoint(42.84228814019598,74.59967851638794));
        waypoints.add(new GeoPoint(42.8432478683188,74.5986270904541));
        waypoints.add(new GeoPoint(42.84321640205937,74.59673881530762));
        waypoints.add(new GeoPoint(42.84318493578391,74.59512948989868));

        waypoints.add(new GeoPoint(42.843405199375596,74.59251165390015));
        waypoints.add(new GeoPoint(42.843405199375596,74.590322971344));
        waypoints.add(new GeoPoint(42.84351533087696,74.5880913734436));
        waypoints.add(new GeoPoint(42.843877190142,74.58605289459229));
        waypoints.add(new GeoPoint(42.84538753636222,74.586181640625));
        waypoints.add(new GeoPoint(42.84733834557906,74.586181640625));
        waypoints.add(new GeoPoint(42.84952506654558,74.58643913269043));
        waypoints.add(new GeoPoint(42.85171171009848,74.58665370941162));
        waypoints.add(new GeoPoint(42.853803893686106,74.58686828613281));
        waypoints.add(new GeoPoint(42.85537691737407,74.5867395401001));
        waypoints.add(new GeoPoint(42.8577206483415,74.58714723587036));
        waypoints.add(new GeoPoint(42.859922730609156,74.58699703216553));
        waypoints.add(new GeoPoint(42.86226628903947,74.58731889724731));
        waypoints.add(new GeoPoint(42.86454684774366,74.58740472793579));
        waypoints.add(new GeoPoint(42.86684304932105,74.5876407623291));

        waypoints.add(new GeoPoint(42.86931215708122,74.58787679672241));
        waypoints.add(new GeoPoint(42.871466648125725,74.58811283111572));
        waypoints.add(new GeoPoint(42.87404564799502,74.58843469619751));
        waypoints.add(new GeoPoint(42.87509923278741,74.5896577835083));
        waypoints.add(new GeoPoint(42.87506778275555,74.59249019622803));
        waypoints.add(new GeoPoint(42.87506778275555,74.59521532058716));
        waypoints.add(new GeoPoint(42.874769006653516,74.59843397140503));
        waypoints.add(new GeoPoint(42.874831907005735,74.6015453338623));
        waypoints.add(new GeoPoint(42.87480045683765,74.60416316986084));
        waypoints.add(new GeoPoint(42.87475328155545,74.60572957992554));
        waypoints.add(new GeoPoint(42.874643205756755,74.60778951644897));
        waypoints.add(new GeoPoint(42.874470229104965,74.60911989212036));
        waypoints.add(new GeoPoint(42.8756967803474,74.60946321487427));
        waypoints.add(new GeoPoint(42.8757282300587,74.61171627044678));
        waypoints.add(new GeoPoint(42.875633880876705,74.61426973342896));

        waypoints.add(new GeoPoint(42.87544518207999,74.61624383926392));
        waypoints.add(new GeoPoint(42.87524075773241,74.6190333366394));
        waypoints.add(new GeoPoint(42.875209307772685,74.62201595306396));
        waypoints.add(new GeoPoint(42.87511495779733,74.62476253509521));
        waypoints.add(new GeoPoint(42.874941982467845,74.62828159332275));
        waypoints.add(new GeoPoint(42.874926257413854,74.63182210922241));
        waypoints.add(new GeoPoint(42.87502060767771,74.63456869125366));
        waypoints.add(new GeoPoint(42.87487908222783,74.63665008544922));
        waypoints.add(new GeoPoint(42.876184249071386,74.6371865272522));
        waypoints.add(new GeoPoint(42.87791394572744,74.63793754577637));
        waypoints.add(new GeoPoint(42.88036688693864,74.63720798492432));
        waypoints.add(new GeoPoint(42.882646776903044,74.63714361190796));
        waypoints.add(new GeoPoint(42.884439182960314,74.63680028915405));
        waypoints.add(new GeoPoint(42.8867032003867,74.63604927062988));
        waypoints.add(new GeoPoint(42.88888852728331,74.6355128288269));

        waypoints.add(new GeoPoint(42.89093228812932,74.63482618331909));
        waypoints.add(new GeoPoint(42.892740173965606,74.63441848754883));
        waypoints.add(new GeoPoint(42.89445368640407,74.63443994522095));
        waypoints.add(new GeoPoint(42.89635578665387,74.63435411453247));
        waypoints.add(new GeoPoint(42.89857220962223,74.63388204574585));
        waypoints.add(new GeoPoint(42.900238404217966,74.63330268859863));
        waypoints.add(new GeoPoint(42.902093171657356,74.63278770446777));
        waypoints.add(new GeoPoint(42.90481234847521,74.6324872970581));
        waypoints.add(new GeoPoint(42.906934166148865,74.63227272033691));
        waypoints.add(new GeoPoint(42.90981029128362,74.63190793991089));
        waypoints.add(new GeoPoint(42.91328346569293,74.63098526000977));
        waypoints.add(new GeoPoint(42.91721216008619,74.63064193725586));
        waypoints.add(new GeoPoint(42.92170627931197,74.63137149810791));
        waypoints.add(new GeoPoint(42.92525734446736,74.6332597732544));
        waypoints.add(new GeoPoint(42.92733132323206,74.63210105895996));

        waypoints.add(new GeoPoint(42.93009651963195,74.6336030960083));
        waypoints.add(new GeoPoint(42.93333289864652,74.63501930236816));
        waypoints.add(new GeoPoint(42.936286338837306,74.63742256164551));
        waypoints.add(new GeoPoint(42.939082550601924,74.63922500610352));

        nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("104");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);


        waypoints=new ArrayList<>();

        waypoints.add(new GeoPoint(42.811262014706884,74.63497638702393));

        waypoints.add(new GeoPoint(42.81172638039531,74.63404297828674));
        waypoints.add(new GeoPoint(42.81227731821539,74.63274478912354));
        waypoints.add(new GeoPoint(42.81331621618022,74.63089942932129));
        waypoints.add(new GeoPoint(42.81476434786169,74.62871074676514));
        waypoints.add(new GeoPoint(42.81618096560772,74.62635040283203));
        waypoints.add(new GeoPoint(42.81816417593365,74.62343215942383));
        waypoints.add(new GeoPoint(42.82011584463175,74.62068557739258));
        waypoints.add(new GeoPoint(42.82168972612954,74.61845397949219));
        waypoints.add(new GeoPoint(42.82342094950302,74.61935520172119));
        waypoints.add(new GeoPoint(42.821406430416666,74.62257385253906));
        waypoints.add(new GeoPoint(42.820210278651416,74.62450504302979));
        waypoints.add(new GeoPoint(42.818573401893836,74.62562084197998));
        waypoints.add(new GeoPoint(42.82474294198018,74.62120056152344));
        waypoints.add(new GeoPoint(42.82587605588896,74.62343215942383));
        waypoints.add(new GeoPoint(42.828929064990604,74.6229600906372));

        waypoints.add(new GeoPoint(42.830628612967125,74.62137222290039));
        waypoints.add(new GeoPoint(42.83371285845132,74.6217155456543));
        waypoints.add(new GeoPoint(42.835317864087465,74.62248802185059));
        waypoints.add(new GeoPoint(42.83528639378937,74.62437629699707));
        waypoints.add(new GeoPoint(42.83519198279893,74.62677955627441));
        waypoints.add(new GeoPoint(42.835349334369525,74.62948322296143));
        waypoints.add(new GeoPoint(42.83660813251064,74.63072776794434));
        waypoints.add(new GeoPoint(42.83903124674179,74.6306848526001));
        waypoints.add(new GeoPoint(42.84145426595244,74.63025569915771));
        waypoints.add(new GeoPoint(42.84151720025027,74.63235855102539));
        waypoints.add(new GeoPoint(42.84145426595244,74.63501930236816));
        waypoints.add(new GeoPoint(42.842146539702654,74.63660717010498));
        waypoints.add(new GeoPoint(42.84519874510405,74.63566303253174));
        waypoints.add(new GeoPoint(42.84821933599944,74.63484764099121));
        waypoints.add(new GeoPoint(42.85095661891722,74.63463306427002));

        waypoints.add(new GeoPoint(42.85451175930407,74.63493347167969));
        waypoints.add(new GeoPoint(42.855990385752044,74.6372938156128));
        waypoints.add(new GeoPoint(42.85595892598347,74.64111328125));
        waypoints.add(new GeoPoint(42.85573870715474,74.64454650878906));
        waypoints.add(new GeoPoint(42.85570724725796,74.64819431304932));
        waypoints.add(new GeoPoint(42.855235346882935,74.65240001678467));
        waypoints.add(new GeoPoint(42.85536118733554,74.6566915512085));
        waypoints.add(new GeoPoint(42.85504658572322,74.66175556182861));
        waypoints.add(new GeoPoint(42.85567578734515,74.66647624969482));
        waypoints.add(new GeoPoint(42.85583308674894,74.67046737670898));
        waypoints.add(new GeoPoint(42.85551848754068,74.67415809631348));
        waypoints.add(new GeoPoint(42.85539264740862,74.67814922332764));
        waypoints.add(new GeoPoint(42.85526680702012,74.68201160430908));
        waypoints.add(new GeoPoint(42.855235346882935,74.68647480010986));
        waypoints.add(new GeoPoint(42.855015125473834,74.6894359588623));

        waypoints.add(new GeoPoint(42.854952204926995,74.69226837158203));
        waypoints.add(new GeoPoint(42.85746897679614,74.6920108795166));
        waypoints.add(new GeoPoint(42.86042605267215,74.69149589538574));
        waypoints.add(new GeoPoint(42.8636660902292,74.69132423400879));
        waypoints.add(new GeoPoint(42.86618250693338,74.69093799591064));
        waypoints.add(new GeoPoint(42.86929643059273,74.69063758850098));
        waypoints.add(new GeoPoint(42.872410197164946,74.69068050384521));
        waypoints.add(new GeoPoint(42.875209307772685,74.69072341918945));
        waypoints.add(new GeoPoint(42.878259879240495,74.69098091125488));
        waypoints.add(new GeoPoint(42.88131029990049,74.6922254562378));
        waypoints.add(new GeoPoint(42.88184489417563,74.69497203826904));
        waypoints.add(new GeoPoint(42.88109017149966,74.69836235046387));
        waypoints.add(new GeoPoint(42.88175055434601,74.70050811767578));
        waypoints.add(new GeoPoint(42.883857442842775,74.70149517059326));
        waypoints.add(new GeoPoint(42.88568125836437,74.70072269439697));

        waypoints.add(new GeoPoint(42.88813389077558,74.70115184783936));
        waypoints.add(new GeoPoint(42.89011479191738,74.70205307006836));
        waypoints.add(new GeoPoint(42.89244148343848,74.70308303833008));

        nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("105");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);


        waypoints=new ArrayList<>();

        waypoints.add(new GeoPoint(42.814669905524795,74.64072704315186));

        waypoints.add(new GeoPoint(42.81693648180323,74.63849544525146));
        waypoints.add(new GeoPoint(42.818510444230085,74.63699340820313));
        waypoints.add(new GeoPoint(42.816243925643235,74.63424682617188));
        waypoints.add(new GeoPoint(42.81504767400701,74.63364601135254));
        waypoints.add(new GeoPoint(42.81567728301592,74.63115692138672));
        waypoints.add(new GeoPoint(42.81813269690146,74.62841033935547));
        waypoints.add(new GeoPoint(42.81964367236993,74.62738037109375));
        waypoints.add(new GeoPoint(42.82153233978264,74.630126953125));
        waypoints.add(new GeoPoint(42.82383014067157,74.63171482086182));
        waypoints.add(new GeoPoint(42.82644260505481,74.63184356689453));
        waypoints.add(new GeoPoint(42.828803170688886,74.63115692138672));
        waypoints.add(new GeoPoint(42.82836253861359,74.62836742401123));
        waypoints.add(new GeoPoint(42.827103572519036,74.6255350112915));
        waypoints.add(new GeoPoint(42.826222280996205,74.62347507476807));
        waypoints.add(new GeoPoint(42.82807927347793,74.62278842926025));

        waypoints.add(new GeoPoint(42.83084892132031,74.62197303771973));
        waypoints.add(new GeoPoint(42.83336667530016,74.62248802185059));
        waypoints.add(new GeoPoint(42.836230495760724,74.62253093719482));
        waypoints.add(new GeoPoint(42.83852775030683,74.6226167678833));
        waypoints.add(new GeoPoint(42.84132839716448,74.6229600906372));
        waypoints.add(new GeoPoint(42.84236680699649,74.62090015411377));
        waypoints.add(new GeoPoint(42.84236680699649,74.61798191070557));
        waypoints.add(new GeoPoint(42.84258707350502,74.61463451385498));
        waypoints.add(new GeoPoint(42.8427444062446,74.6101713180542));
        waypoints.add(new GeoPoint(42.842838805696026,74.60712432861328));
        waypoints.add(new GeoPoint(42.84290173858353,74.60373401641846));
        waypoints.add(new GeoPoint(42.84305907052178,74.60072994232178));
        waypoints.add(new GeoPoint(42.84331080078961,74.59875583648682));
        waypoints.add(new GeoPoint(42.843279334562226,74.59656715393066));
        waypoints.add(new GeoPoint(42.84353106393256,74.59296226501465));

        waypoints.add(new GeoPoint(42.84359399611488,74.59085941314697));
        waypoints.add(new GeoPoint(42.84346813168613,74.58802700042725));
        waypoints.add(new GeoPoint(42.844600902313516,74.58626747131348));
        waypoints.add(new GeoPoint(42.84718102453893,74.58661079406738));
        waypoints.add(new GeoPoint(42.84818787227221,74.5858383178711));
        waypoints.add(new GeoPoint(42.84840811802621,74.58253383636475));
        waypoints.add(new GeoPoint(42.84913177711727,74.58150386810303));
        waypoints.add(new GeoPoint(42.851239779192014,74.58184719085693));
        waypoints.add(new GeoPoint(42.851900481451366,74.5801305770874));
        waypoints.add(new GeoPoint(42.85193194328741,74.57678318023682));
        waypoints.add(new GeoPoint(42.851837557731166,74.57330703735352));
        waypoints.add(new GeoPoint(42.851837557731166,74.57013130187988));
        waypoints.add(new GeoPoint(42.851837557731166,74.56751346588135));
        waypoints.add(new GeoPoint(42.851900481451366,74.565110206604));
        waypoints.add(new GeoPoint(42.851900481451366,74.56244945526123));

        waypoints.add(new GeoPoint(42.851869019599285,74.56013202667236));
        waypoints.add(new GeoPoint(42.85164878618596,74.55811500549316));
        waypoints.add(new GeoPoint(42.851869019599285,74.55528259277344));
        waypoints.add(new GeoPoint(42.852120713967174,74.55206394195557));
        waypoints.add(new GeoPoint(42.85221509909069,74.54901695251465));
        waypoints.add(new GeoPoint(42.85196340510745,74.54699993133545));
        waypoints.add(new GeoPoint(42.85196340510745,74.5448112487793));
        waypoints.add(new GeoPoint(42.85010713030379,74.54403877258301));
        waypoints.add(new GeoPoint(42.85039029447312,74.54601287841797));
        waypoints.add(new GeoPoint(42.85196340510745,74.54292297363281));
        waypoints.add(new GeoPoint(42.85224656076648,74.5405626296997));
        waypoints.add(new GeoPoint(42.852152175691046,74.53781604766846));
        waypoints.add(new GeoPoint(42.85208925222729,74.53549861907959));
        waypoints.add(new GeoPoint(42.852152175691046,74.53202247619629));
        waypoints.add(new GeoPoint(42.85224656076648,74.52871799468994));

        waypoints.add(new GeoPoint(42.85230948406997,74.52657222747803));
        waypoints.add(new GeoPoint(42.8527814068027,74.52395439147949));
        waypoints.add(new GeoPoint(42.853001636177225,74.52150821685791));
        waypoints.add(new GeoPoint(42.85076784467949,74.5213794708252));
        waypoints.add(new GeoPoint(42.84891153394358,74.521164894104));
        waypoints.add(new GeoPoint(42.84718102453893,74.52077865600586));
        waypoints.add(new GeoPoint(42.845513396880456,74.52095031738281));
        waypoints.add(new GeoPoint(42.84296467140691,74.52086448669434));
        waypoints.add(new GeoPoint(42.84142279877949,74.5208215713501));
        waypoints.add(new GeoPoint(42.84019556653375,74.52090740203857));
        waypoints.add(new GeoPoint(42.85240386890499,74.51961994171143));
        waypoints.add(new GeoPoint(42.85249825359579,74.51614379882813));
        waypoints.add(new GeoPoint(42.85243533048462,74.5118522644043));
        waypoints.add(new GeoPoint(42.85227802242623,74.50884819030762));
        waypoints.add(new GeoPoint(42.853001636177225,74.50601577758789));

        waypoints.add(new GeoPoint(42.85297017488608,74.50451374053955));
        waypoints.add(new GeoPoint(42.85133416566178,74.50404167175293));
        waypoints.add(new GeoPoint(42.85001274195887,74.50395584106445));
        waypoints.add(new GeoPoint(42.8485339723902,74.5037841796875));
        waypoints.add(new GeoPoint(42.84727541721109,74.50365543365479));
        waypoints.add(new GeoPoint(42.8458123145836,74.50341939926147));
        waypoints.add(new GeoPoint(42.84394012197173,74.50331211090088));
        waypoints.add(new GeoPoint(42.842524140297,74.50326919555664));
        waypoints.add(new GeoPoint(42.84115532716234,74.50305461883545));
        waypoints.add(new GeoPoint(42.83973928166202,74.50301170349121));
        waypoints.add(new GeoPoint(42.8383232037082,74.50314044952393));
        waypoints.add(new GeoPoint(42.83652945837806,74.5031189918518));
        waypoints.add(new GeoPoint(42.834893013710996,74.50292587280273));
        waypoints.add(new GeoPoint(42.83383874275277,74.50286149978638));
        waypoints.add(new GeoPoint(42.833146375921594,74.50217485427856));

        waypoints.add(new GeoPoint(42.83336667530016,74.50043678283691));
        waypoints.add(new GeoPoint(42.83346108907916,74.49876308441162));

        nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("106");
        nearRoutes1.setWaypoints(waypoints);
        nearRoutes.add(nearRoutes1);

        waypoints=new ArrayList<>();

        waypoints.add(new GeoPoint(42.81284399199598,74.55455303192139));


        waypoints.add(new GeoPoint(42.813505104844324,74.55708503723145));
        waypoints.add(new GeoPoint(42.81397732398058,74.5592737197876));
        waypoints.add(new GeoPoint(42.81476434786169,74.56133365631104));
        waypoints.add(new GeoPoint(42.81511063519636,74.56360816955566));
        waypoints.add(new GeoPoint(42.81734571588834,74.56403732299805));
        waypoints.add(new GeoPoint(42.819580715795915,74.56369400024414));
        waypoints.add(new GeoPoint(42.82162677163886,74.56356525421143));
        waypoints.add(new GeoPoint(42.824585563405755,74.56369400024414));
        waypoints.add(new GeoPoint(42.82691472539329,74.56339359283447));
        waypoints.add(new GeoPoint(42.82933821970032,74.5630931854248));
        waypoints.add(new GeoPoint(42.82946411291228,74.56605434417725));
        waypoints.add(new GeoPoint(42.829416902987845,74.56828594207764));
        waypoints.add(new GeoPoint(42.83015652099458,74.56944465637207));
        waypoints.add(new GeoPoint(42.83206060322596,74.56938028335571));
        waypoints.add(new GeoPoint(42.83338241094002,74.5693588256836));
        waypoints.add(new GeoPoint(42.83619902592739,74.5691442489624));
        waypoints.add(new GeoPoint(42.83862215620089,74.56897258758545));
        waypoints.add(new GeoPoint(42.84167453571436,74.56892967224121));
        waypoints.add(new GeoPoint(42.843562530031726,74.56862926483154));
        waypoints.add(new GeoPoint(42.84557632704343,74.5685863494873));
        waypoints.add(new GeoPoint(42.84743273801082,74.56845760345459));
        waypoints.add(new GeoPoint(42.84935201950563,74.56850051879883));
        waypoints.add(new GeoPoint(42.851208317003355,74.56871509552002));
        waypoints.add(new GeoPoint(42.85259263814233,74.56777095794678));
        waypoints.add(new GeoPoint(42.85529826714128,74.5682430267334));
        waypoints.add(new GeoPoint(42.857783566067575,74.5691442489624));
        waypoints.add(new GeoPoint(42.860677712165035,74.56888675689697));
        waypoints.add(new GeoPoint(42.862848232714505,74.56944465637207));
        waypoints.add(new GeoPoint(42.86583650571898,74.56944465637207));
        waypoints.add(new GeoPoint(42.86835283395119,74.56948757171631));
        waypoints.add(new GeoPoint(42.87118358060107,74.56897258758545));
        waypoints.add(new GeoPoint(42.87137229242842,74.5706033706665));
        waypoints.add(new GeoPoint(42.87288196627519,74.57167625427246));
        waypoints.add(new GeoPoint(42.87536655741107,74.57210540771484));
        waypoints.add(new GeoPoint(42.876750336950685,74.5740795135498));
        waypoints.add(new GeoPoint(42.87656164156793,74.57704067230225));
        waypoints.add(new GeoPoint(42.87643584432553,74.57927227020264));
        waypoints.add(new GeoPoint(42.87624714798109,74.58098888397217));
        waypoints.add(new GeoPoint(42.87643584432553,74.58287715911865));
        waypoints.add(new GeoPoint(42.8759955519576,74.58643913269043));
        waypoints.add(new GeoPoint(42.87596410238254,74.58995819091797));
        waypoints.add(new GeoPoint(42.8758697535612,74.59253311157227));
        waypoints.add(new GeoPoint(42.87546090700176,74.59519386291504));
        waypoints.add(new GeoPoint(42.877253521817124,74.59609508514404));
        waypoints.add(new GeoPoint(42.87976938460171,74.59635257720947));
        waypoints.add(new GeoPoint(42.88247382268652,74.59656715393066));
        waypoints.add(new GeoPoint(42.883668769194145,74.59523677825928));
        waypoints.add(new GeoPoint(42.88363732352993,74.59296226501465));
        waypoints.add(new GeoPoint(42.88209646634934,74.59287643432617));
        waypoints.add(new GeoPoint(42.88064991234185,74.59274768829346));

        waypoints.add(new GeoPoint(42.879266220251154,74.59266185760498));
        waypoints.add(new GeoPoint(42.87819698238238,74.59253311157227));
        waypoints.add(new GeoPoint(42.87744221508419,74.59218978881836));
        waypoints.add(new GeoPoint(42.88568125836437,74.59708213806152));
        waypoints.add(new GeoPoint(42.888542653363295,74.59716796875));
        waypoints.add(new GeoPoint(42.891278148649484,74.59721088409424));
        waypoints.add(new GeoPoint(42.89357335568507,74.59746837615967));
        waypoints.add(new GeoPoint(42.89668589725144,74.59785461425781));
        waypoints.add(new GeoPoint(42.89954678173843,74.59802627563477));
        waypoints.add(new GeoPoint(42.90159018939116,74.59755420684814));
        waypoints.add(new GeoPoint(42.904010753903044,74.59785461425781));
        waypoints.add(new GeoPoint(42.906336921152096,74.5977258682251));
        waypoints.add(new GeoPoint(42.90850583585835,74.59789752960205));
        waypoints.add(new GeoPoint(42.910957560586894,74.59785461425781));
        waypoints.add(new GeoPoint(42.91183764311213,74.5960521697998));
        waypoints.add(new GeoPoint(42.912246248584154,74.59416389465332));
        waypoints.add(new GeoPoint(42.91246626579317,74.59244728088379));
        waypoints.add(new GeoPoint(42.912749143907796,74.5911169052124));
        waypoints.add(new GeoPoint(42.91485497350565,74.59081649780273));
        waypoints.add(new GeoPoint(42.914352095365615,74.59094524383545));
        waypoints.add(new GeoPoint(42.917526444817675,74.59158897399902));
        waypoints.add(new GeoPoint(42.9190978444306,74.59141731262207));
        waypoints.add(new GeoPoint(42.919192127133016,74.58940029144287));
        waypoints.add(new GeoPoint(42.919160699581575,74.58776950836182));
        waypoints.add(new GeoPoint(42.919160699581575,74.58600997924805));

        nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("107");
        nearRoutes1.setWaypoints(waypoints);
        nearRoutes.add(nearRoutes1);


        return nearRoutes;
    }
}
