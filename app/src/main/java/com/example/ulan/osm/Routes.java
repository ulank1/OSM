package com.example.ulan.osm;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by Админ on 09.12.2016.
 */

public class Routes {
    ArrayList<GeoPoint> waypoints;
    public ArrayList<GeoPoint> getReout(String number){
        waypoints=new ArrayList<>();
        if (number.equals("100")||number.equals("101")) {
            waypoints.add(new GeoPoint(42.82467, 74.53717));
            GeoPoint endPoint = new GeoPoint(42.82462, 74.53928);

            waypoints.add(new GeoPoint(42.82467, 74.53717));
            waypoints.add(endPoint);
            waypoints.add(new GeoPoint(42.82468, 74.54049));
            waypoints.add(new GeoPoint(42.82476, 74.54145));
            waypoints.add(new GeoPoint(42.82517, 74.54311));
            waypoints.add(new GeoPoint(42.82571, 74.54464));
            waypoints.add(new GeoPoint(42.82759, 74.55005));
            waypoints.add(new GeoPoint(42.82951, 74.56047));
            waypoints.add(new GeoPoint(42.82966, 74.5693));
            waypoints.add(new GeoPoint(42.84384, 74.56838));
            waypoints.add(new GeoPoint(42.84292, 74.60881));
            waypoints.add(new GeoPoint(42.88473, 74.61175));
            waypoints.add(new GeoPoint(42.88809, 74.63546));
            waypoints.add(new GeoPoint(42.84174, 74.63617));
            waypoints.add(new GeoPoint(42.84291, 74.60881));
        }
        else if (number.equals("102")){
            waypoints.add(new GeoPoint(42.85522748184612,74.68825578689575));
            waypoints.add(new GeoPoint(42.856108359741434,74.6662187576294));
            waypoints.add(new GeoPoint(42.85532186222165,74.66154098510742));
            waypoints.add(new GeoPoint(42.85622633350544,74.64162826538086));
            waypoints.add(new GeoPoint(42.85757908322346,74.58682537078857));
            waypoints.add(new GeoPoint(42.872826926731484,74.58789825439453));
            waypoints.add(new GeoPoint(42.873542436971015,74.5713758468628));
            waypoints.add(new GeoPoint(42.87121503261237,74.57107543945313));
            waypoints.add(new GeoPoint(42.871334370237664,74.58789825439453));





        }
        else if (number.equals("258")){
            waypoints.add(new GeoPoint(42.87253600261362,74.42203044891357));
            waypoints.add(new GeoPoint(42.876844684425684,74.55425262451172));
            waypoints.add(new GeoPoint(42.877253521817124,74.57133293151855));
            waypoints.add(new GeoPoint(42.87615279959249,74.57356452941895));
            waypoints.add(new GeoPoint(42.87454885491588,74.60864782333374));
            waypoints.add(new GeoPoint(42.87580685426683,74.61055755615234));
            waypoints.add(new GeoPoint(42.87487908222783,74.63677883148193));
            waypoints.add(new GeoPoint(42.88755218549967,74.6357274055481));
            waypoints.add(new GeoPoint(42.887913786721725,74.66222763061523));
            waypoints.add(new GeoPoint(42.88275683842475,74.69192504882813));
            waypoints.add(new GeoPoint(42.87014565523683,74.6906590461731));
            waypoints.add(new GeoPoint(42.86981540259698,74.69628095626831));
            waypoints.add(new GeoPoint(42.86869882106132,74.70089435577393));

        }


        else if (number.equals("103")){
            waypoints.add(new GeoPoint(42.85850711530955,74.56845760345459));

            waypoints.add(new GeoPoint(42.860913642008015,74.566730260849));
            waypoints.add(new GeoPoint(42.85982835726556,74.56473469734192));
            waypoints.add(new GeoPoint(42.85578589696987,74.56646203994751));
            waypoints.add(new GeoPoint(42.85697349544363,74.57210540771484));
            waypoints.add(new GeoPoint(42.86059906868369,74.57112908363342));
            waypoints.add(new GeoPoint(42.86285609678066,74.56912279129028));
            waypoints.add(new GeoPoint(42.8717261255495,74.56871509552002));
            waypoints.add(new GeoPoint(42.87237088290966,74.57133293151855));
            waypoints.add(new GeoPoint(42.87248096276139,74.56866145133972));
            waypoints.add(new GeoPoint(42.871136402554086,74.57057118415833));
            waypoints.add(new GeoPoint(42.8748633571578,74.5715582370758));
            waypoints.add(new GeoPoint(42.875909065587614,74.57157969474792));
            waypoints.add(new GeoPoint(42.87429725196834,74.61150169372559));
            waypoints.add(new GeoPoint(42.82071392432045,74.59819793701172));
            waypoints.add(new GeoPoint(42.80491008658193,74.57129001617432));


            waypoints.add(new GeoPoint(42.80094275225561,74.57819938659668));
            waypoints.add(new GeoPoint(42.80292645122009,74.5850658416748));



        }

        else if (number.equals("104")){

            waypoints.add(new GeoPoint(42.82885038108208,74.604731798172));

            waypoints.add(new GeoPoint(42.83139968876766,74.6000862121582));
            waypoints.add(new GeoPoint(42.84290960518995,74.5995819568634));
            waypoints.add(new GeoPoint(42.843680527760895,74.58605289459229));
            waypoints.add(new GeoPoint(42.87509923278741,74.5881986618042));
            waypoints.add(new GeoPoint(42.874344427599134,74.60886240005493));
            waypoints.add(new GeoPoint(42.87491053235585,74.63660717010498));
            waypoints.add(new GeoPoint(42.926530021532294,74.63343143463135));
            waypoints.add(new GeoPoint(42.927386314142964,74.63221907615662));
            waypoints.add(new GeoPoint(42.94040206711003,74.63987946510315));
            waypoints.add(new GeoPoint(42.941266020933014,74.63888168334961));


        }

        else if (number.equals("105")){
            waypoints.add(new GeoPoint(42.81128956192127,74.63493883609772));

            waypoints.add(new GeoPoint(42.81227731821539,74.63307201862335));
            waypoints.add(new GeoPoint(42.8172827569746,74.62510585784912));
            waypoints.add(new GeoPoint(42.81891966789863,74.62617874145508));
            waypoints.add(new GeoPoint(42.8138986210416,74.63122129440308));
            waypoints.add(new GeoPoint(42.81919510538136,74.62571740150452));
            waypoints.add(new GeoPoint(42.823531116622526,74.61917281150818));
            waypoints.add(new GeoPoint(42.82661571626426,74.62308883666992));
            waypoints.add(new GeoPoint(42.83536506950455,74.6222198009491));
            waypoints.add(new GeoPoint(42.83504249843576,74.63026642799377));
            waypoints.add(new GeoPoint(42.8417060027591,74.63034152984619));
            waypoints.add(new GeoPoint(42.84258707350502,74.63649988174438));
            waypoints.add(new GeoPoint(42.85595892598347,74.63630676269531));
            waypoints.add(new GeoPoint(42.85600611563032,74.69233274459839));
            waypoints.add(new GeoPoint(42.88230086798515,74.69248294830322));
            waypoints.add(new GeoPoint(42.88121595925344,74.70055103302002));

            waypoints.add(new GeoPoint(42.88722202601087,74.70089435577393));
            waypoints.add(new GeoPoint(42.89344759312796,74.7035551071167));


        }
        else if (number.equals("106")){
            waypoints.add(new GeoPoint(42.81496897243018,74.6402657032013));
            waypoints.add(new GeoPoint(42.81865209888337,74.63692903518677));
            waypoints.add(new GeoPoint(42.81500832323111,74.63231563568115));
            waypoints.add(new GeoPoint(42.818959016185566,74.62636113166809));
            waypoints.add(new GeoPoint(42.82303536303879,74.6315860748291));
            waypoints.add(new GeoPoint(42.82666292832816,74.6242904663086));
            waypoints.add(new GeoPoint(42.84151720025027,74.62300300598145));
            waypoints.add(new GeoPoint(42.84359399611488,74.58815574645996));
            waypoints.add(new GeoPoint(42.84755859436212,74.58639621734619));
            waypoints.add(new GeoPoint(42.84840811802621,74.58163261413574));
            waypoints.add(new GeoPoint(42.851869019599285,74.5777702331543));
            waypoints.add(new GeoPoint(42.85208925222729,74.54455375671387));
            waypoints.add(new GeoPoint(42.84903738728185,74.54455375671387));
            waypoints.add(new GeoPoint(42.85180609584704,74.54691410064697));
            waypoints.add(new GeoPoint(42.85240386890499,74.53657150268555));
            waypoints.add(new GeoPoint(42.84944640886026,74.52056407928467));
            waypoints.add(new GeoPoint(42.840636114247104,74.52009201049805));
            waypoints.add(new GeoPoint(42.85252971512733,74.51966285705566));
            waypoints.add(new GeoPoint(42.8527814068027,74.50459957122803));
            waypoints.add(new GeoPoint(42.83320931868131,74.50185298919678));
            waypoints.add(new GeoPoint(42.833303732700706,74.49944972991943));

        }else if (number.equals("107")){

            waypoints.add(new GeoPoint(42.812615749014405,74.55394685268402));

            waypoints.add(new GeoPoint(42.81518146645775,74.56435918807983));
            waypoints.add(new GeoPoint(42.82949952033193,74.5633453130722));
            waypoints.add(new GeoPoint(42.83005816804645,74.5692890882492));
            waypoints.add(new GeoPoint(42.85155440019699,74.5688009262085));
            waypoints.add(new GeoPoint(42.8466303977432,74.56811428070068));
            waypoints.add(new GeoPoint(42.85339490096416,74.56789970397949));
            waypoints.add(new GeoPoint(42.871561003678764,74.56933736801147));
            waypoints.add(new GeoPoint(42.8758697535612,74.57236289978027));
            waypoints.add(new GeoPoint(42.875272207676126,74.58703994750977));
            waypoints.add(new GeoPoint(42.87715917496722,74.5836067199707));
            waypoints.add(new GeoPoint(42.877253521817124,74.57416534423828));
            waypoints.add(new GeoPoint(42.87558670623163,74.57716941833496));
            waypoints.add(new GeoPoint(42.87508350777349,74.58695411682129));
            waypoints.add(new GeoPoint(42.87612135009757,74.59545135498047));
            waypoints.add(new GeoPoint(42.88313419072282,74.59695339202881));

            waypoints.add(new GeoPoint(42.88203357340208,74.58858489990234));
            waypoints.add(new GeoPoint(42.876215698534246,74.59081649780273));
            waypoints.add(new GeoPoint(42.87989517504822,74.5968246459961));
            waypoints.add(new GeoPoint(42.91136617189362,74.59815502166748));
            waypoints.add(new GeoPoint(42.91362920085005,74.59051609039307));
            waypoints.add(new GeoPoint(42.91892499243481,74.59139585494995));
            waypoints.add(new GeoPoint(42.91941211954428,74.58697557449341));


        }
        else if (number.equals("108")){
            waypoints.add(new GeoPoint(42.864594030827334,74.46183443069458));
            waypoints.add(new GeoPoint(42.87357388778022,74.46069717407227));
            waypoints.add(new GeoPoint(42.87709627698713,74.57124710083008));
            waypoints.add(new GeoPoint(42.8758540287436,74.5721697807312));
            waypoints.add(new GeoPoint(42.87473755645337,74.60079431533813));
            waypoints.add(new GeoPoint(42.8762628726985,74.59832668304443));
            waypoints.add(new GeoPoint(42.877363592959696,74.57249164581299));
            waypoints.add(new GeoPoint(42.87429725196834,74.60751056671143));
            waypoints.add(new GeoPoint(42.87571250520505,74.61068630218506));
            waypoints.add(new GeoPoint(42.88020965004254,74.6119737625122));
            waypoints.add(new GeoPoint(42.88421906572314,74.61199522018433));
            waypoints.add(new GeoPoint(42.88241093012394,74.61502075195313));
            waypoints.add(new GeoPoint(42.88096438348943,74.61351871490479));
            waypoints.add(new GeoPoint(42.88335431183083,74.61257457733154));
            waypoints.add(new GeoPoint(42.8879766736744,74.6581506729126));
            waypoints.add(new GeoPoint(42.88549259029359,74.67711925506592));

            waypoints.add(new GeoPoint(42.888259664168004,74.67930793762207));
            waypoints.add(new GeoPoint(42.887638655549964,74.68454360961914));
            waypoints.add(new GeoPoint(42.8898318099349,74.68952178955078));
            waypoints.add(new GeoPoint(42.89011479191738,74.69299793243408));
            waypoints.add(new GeoPoint(42.889108627858974,74.69630241394043));

        }
        else if (number.equals("110")){

            waypoints.add(new GeoPoint(42.81111247247183,74.63484764099121));

            waypoints.add(new GeoPoint(42.82840974934332,74.60736036300659));
            waypoints.add(new GeoPoint(42.87160818140119,74.61156606674194));
            waypoints.add(new GeoPoint(42.873794042995904,74.57219123840332));
            waypoints.add(new GeoPoint(42.872661807805855,74.56403732299805));
            waypoints.add(new GeoPoint(42.877725253902895,74.56060409545898));
            waypoints.add(new GeoPoint(42.87627859741189,74.55631256103516));
            waypoints.add(new GeoPoint(42.872661807805855,74.56257820129395));
            waypoints.add(new GeoPoint(42.87052308466151,74.57837104797363));
            waypoints.add(new GeoPoint(42.869186345061166,74.61124420166016));


        }
        else if (number.equals("113")) {

            waypoints.add(new GeoPoint(42.875177857796935,74.62017059326172));

            waypoints.add(new GeoPoint(42.87497343256381,74.63643550872803));
            waypoints.add(new GeoPoint(42.8877408472713,74.63553428649902));
            waypoints.add(new GeoPoint(42.88635731421295,74.6246337890625));
            waypoints.add(new GeoPoint(42.88392033393075,74.55584049224854));
            waypoints.add(new GeoPoint(42.87244164855116,74.56624746322632));
            waypoints.add(new GeoPoint(42.87094769000549,74.57324266433716));
            waypoints.add(new GeoPoint(42.86880890746237,74.62019205093384));
            waypoints.add(new GeoPoint(42.87506778275555,74.6204924583435));


        }
        else if (number.equals("114")){
            waypoints.add(new GeoPoint(42.88466716069942,74.56769585609436));


            waypoints.add(new GeoPoint(42.88405397727985,74.57890748977661));
            waypoints.add(new GeoPoint(42.887913786721725,74.63538408279419));
            waypoints.add(new GeoPoint(42.875398007290656,74.63549137115479));
            waypoints.add(new GeoPoint(42.874894807293835,74.62064266204834));
            waypoints.add(new GeoPoint(42.86901335311489,74.61784243583679));
            waypoints.add(new GeoPoint(42.871718262613285,74.6143126487732));
            waypoints.add(new GeoPoint(42.87239447146584,74.56689119338989));
            waypoints.add(new GeoPoint(42.873794042995904,74.56373691558838));
            waypoints.add(new GeoPoint(42.88300840687992,74.55794334411621));
            waypoints.add(new GeoPoint(42.88476935734347,74.56888675689697));

        }

        else if (number.equals("117")){
            waypoints.add(new GeoPoint(42.814559722616124,74.64051246643066));

            waypoints.add(new GeoPoint(42.81810121785324,74.63615655899048));
            waypoints.add(new GeoPoint(42.81523655738275,74.6321439743042));
            waypoints.add(new GeoPoint(42.823357996775165,74.61963415145874));
            waypoints.add(new GeoPoint(42.82746552789747,74.6140444278717));
            waypoints.add(new GeoPoint(42.83730832787794,74.61318612098694));
            waypoints.add(new GeoPoint(42.839361664047566,74.60843324661255));
            waypoints.add(new GeoPoint(42.92879249361496,74.61126565933228));
            waypoints.add(new GeoPoint(42.92773982596945,74.62098598480225));
            waypoints.add(new GeoPoint(42.94753325022567,74.62411880493164));
            waypoints.add(new GeoPoint(42.94932376997723,74.609055519104));
            waypoints.add(new GeoPoint(42.95237067508238,74.61240291595459));
            waypoints.add(new GeoPoint(42.958997958056045,74.61396932601929));

        }
        else if (number.equals("118")){
            waypoints.add(new GeoPoint(42.805587026950356,74.5632004737854));

            waypoints.add(new GeoPoint(42.80621673227063,74.56916570663452));
            waypoints.add(new GeoPoint(42.82946411291227,74.56485271453857));
            waypoints.add(new GeoPoint(42.8308331850354,74.56931591033936));
            waypoints.add(new GeoPoint(42.8437355932907,74.5697021484375));
            waypoints.add(new GeoPoint(42.84386145717455,74.58609580993652));
            waypoints.add(new GeoPoint(42.87014565523683,74.58905696868896));
            waypoints.add(new GeoPoint(42.869217798090226,74.61110472679138));
            waypoints.add(new GeoPoint(42.86675655012485,74.60969924926758));
            waypoints.add(new GeoPoint(42.868604461132705,74.58772659301758));
            waypoints.add(new GeoPoint(42.8727247103058,74.61193084716797));
            waypoints.add(new GeoPoint(42.88464357683445,74.61356163024902));
            waypoints.add(new GeoPoint(42.89165544882318,74.63883876800537));
            waypoints.add(new GeoPoint(42.89926384303349,74.64304447174072));
            waypoints.add(new GeoPoint(42.89376199903989,74.64690685272217));

        }

        return  waypoints;
    }
}
