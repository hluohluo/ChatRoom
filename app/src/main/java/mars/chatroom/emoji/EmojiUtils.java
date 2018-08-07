package mars.chatroom.emoji;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import mars.chatroom.R;


/**
 * Created by santa on 16/8/8.
 */
public class EmojiUtils {


    public final static int MAXNUM_ONEPAGER = 21;
    public final static String EDELETE = "[e1f888]";

    /**
     * 表情类型标志符
     */
    public static final int EMOTION_CLASSIC_TYPE=0x0001;//经典表情
    /**
     * key-表情文字;
     * value-表情图片资源
     */
    public static ArrayMap<String, Integer> EMOTION_CLASSIC_MAP;
    
    static {
        EMOTION_CLASSIC_MAP = new ArrayMap<>();
        EMOTION_CLASSIC_MAP.put("[e1f600]", R.drawable.elf600);
        EMOTION_CLASSIC_MAP.put("[e1f601]", R.drawable.elf601);
        EMOTION_CLASSIC_MAP.put("[e1f602]", R.drawable.elf602);
        EMOTION_CLASSIC_MAP.put("[e1f603]", R.drawable.elf603);
        EMOTION_CLASSIC_MAP.put("[e1f604]", R.drawable.elf604);
        EMOTION_CLASSIC_MAP.put("[e1f605]", R.drawable.elf605);
        EMOTION_CLASSIC_MAP.put("[e1f606]", R.drawable.elf606);
        EMOTION_CLASSIC_MAP.put("[e1f607]", R.drawable.elf607);
        EMOTION_CLASSIC_MAP.put("[e1f608]", R.drawable.elf608);
        EMOTION_CLASSIC_MAP.put("[e1f609]", R.drawable.elf609);
        EMOTION_CLASSIC_MAP.put("[e1f610]", R.drawable.elf610);
        EMOTION_CLASSIC_MAP.put("[e1f611]", R.drawable.elf611);
        EMOTION_CLASSIC_MAP.put("[e1f612]", R.drawable.elf612);
        EMOTION_CLASSIC_MAP.put("[e1f613]", R.drawable.elf613);
        EMOTION_CLASSIC_MAP.put("[e1f614]", R.drawable.elf614);
        EMOTION_CLASSIC_MAP.put("[e1f615]", R.drawable.elf615);
        EMOTION_CLASSIC_MAP.put("[e1f616]", R.drawable.elf616);
        EMOTION_CLASSIC_MAP.put("[e1f617]", R.drawable.elf617);
        EMOTION_CLASSIC_MAP.put("[e1f618]", R.drawable.elf618);
        EMOTION_CLASSIC_MAP.put("[e1f619]", R.drawable.elf619);
        EMOTION_CLASSIC_MAP.put("[e1f620]", R.drawable.elf620);
        EMOTION_CLASSIC_MAP.put("[e1f621]", R.drawable.elf621);
        EMOTION_CLASSIC_MAP.put("[e1f622]", R.drawable.elf622);
        EMOTION_CLASSIC_MAP.put("[e1f623]", R.drawable.elf623);
        EMOTION_CLASSIC_MAP.put("[e1f624]", R.drawable.elf624);
        EMOTION_CLASSIC_MAP.put("[e1f625]", R.drawable.elf625);
        EMOTION_CLASSIC_MAP.put("[e1f626]", R.drawable.elf626);
        EMOTION_CLASSIC_MAP.put("[e1f627]", R.drawable.elf627);
        EMOTION_CLASSIC_MAP.put("[e1f628]", R.drawable.elf628);
        EMOTION_CLASSIC_MAP.put("[e1f629]", R.drawable.elf629);
        EMOTION_CLASSIC_MAP.put("[e1f630]", R.drawable.elf630);
        EMOTION_CLASSIC_MAP.put("[e1f631]", R.drawable.elf631);
        EMOTION_CLASSIC_MAP.put("[e1f632]", R.drawable.elf632);
        EMOTION_CLASSIC_MAP.put("[e1f633]", R.drawable.elf633);
        EMOTION_CLASSIC_MAP.put("[e1f634]", R.drawable.elf634);
        EMOTION_CLASSIC_MAP.put("[e1f635]", R.drawable.elf635);
        EMOTION_CLASSIC_MAP.put("[e1f636]", R.drawable.elf636);
        EMOTION_CLASSIC_MAP.put("[e1f637]", R.drawable.elf637);
        EMOTION_CLASSIC_MAP.put("[e1f638]", R.drawable.elf638);
        EMOTION_CLASSIC_MAP.put("[e1f639]", R.drawable.elf639);
        EMOTION_CLASSIC_MAP.put("[e1f640]", R.drawable.elf640);
        EMOTION_CLASSIC_MAP.put("[e1f641]", R.drawable.elf641);
        EMOTION_CLASSIC_MAP.put("[e1f642]", R.drawable.elf642);
        EMOTION_CLASSIC_MAP.put("[e1f643]", R.drawable.elf643);
        EMOTION_CLASSIC_MAP.put("[e1f644]", R.drawable.elf644);

        EMOTION_CLASSIC_MAP.put("[e1f645]", R.drawable.elf645);
        EMOTION_CLASSIC_MAP.put("[e1f646]", R.drawable.elf646);
        EMOTION_CLASSIC_MAP.put("[e1f647]", R.drawable.elf647);
        EMOTION_CLASSIC_MAP.put("[e1f648]", R.drawable.elf648);
        EMOTION_CLASSIC_MAP.put("[e1f649]", R.drawable.elf649);
        EMOTION_CLASSIC_MAP.put("[e1f650]", R.drawable.elf650);
        EMOTION_CLASSIC_MAP.put("[e1f651]", R.drawable.elf651);
        EMOTION_CLASSIC_MAP.put("[e1f652]", R.drawable.elf652);
        EMOTION_CLASSIC_MAP.put("[e1f653]", R.drawable.elf653);
        EMOTION_CLASSIC_MAP.put("[e1f654]", R.drawable.elf654);
        EMOTION_CLASSIC_MAP.put("[e1f655]", R.drawable.elf655);
        EMOTION_CLASSIC_MAP.put("[e1f656]", R.drawable.elf656);
        EMOTION_CLASSIC_MAP.put("[e1f657]", R.drawable.elf657);
        EMOTION_CLASSIC_MAP.put("[e1f658]", R.drawable.elf658);
        EMOTION_CLASSIC_MAP.put("[e1f659]", R.drawable.elf659);
        EMOTION_CLASSIC_MAP.put("[e1f660]", R.drawable.elf660);
        EMOTION_CLASSIC_MAP.put("[e1f661]", R.drawable.elf661);
        EMOTION_CLASSIC_MAP.put("[e1f662]", R.drawable.elf662);
        EMOTION_CLASSIC_MAP.put("[e1f663]", R.drawable.elf663);
        EMOTION_CLASSIC_MAP.put("[e1f664]", R.drawable.elf664);
        EMOTION_CLASSIC_MAP.put("[e1f665]", R.drawable.elf665);
        EMOTION_CLASSIC_MAP.put("[e1f666]", R.drawable.elf666);
        EMOTION_CLASSIC_MAP.put("[e1f667]", R.drawable.elf667);
        EMOTION_CLASSIC_MAP.put("[e1f668]", R.drawable.elf668);
        EMOTION_CLASSIC_MAP.put("[e1f669]", R.drawable.elf669);
        EMOTION_CLASSIC_MAP.put("[e1f670]", R.drawable.elf670);
        EMOTION_CLASSIC_MAP.put("[e1f671]", R.drawable.elf671);
        EMOTION_CLASSIC_MAP.put("[e1f672]", R.drawable.elf672);
        EMOTION_CLASSIC_MAP.put("[e1f673]", R.drawable.elf673);
        EMOTION_CLASSIC_MAP.put("[e1f674]", R.drawable.elf674);
        EMOTION_CLASSIC_MAP.put("[e1f675]", R.drawable.elf675);
        EMOTION_CLASSIC_MAP.put("[e1f676]", R.drawable.elf676);
        EMOTION_CLASSIC_MAP.put("[e1f677]", R.drawable.elf677);
        EMOTION_CLASSIC_MAP.put("[e1f678]", R.drawable.elf678);
        EMOTION_CLASSIC_MAP.put("[e1f679]", R.drawable.elf679);
        EMOTION_CLASSIC_MAP.put("[e1f680]", R.drawable.elf680);
        EMOTION_CLASSIC_MAP.put("[e1f681]", R.drawable.elf681);
        EMOTION_CLASSIC_MAP.put("[e1f682]", R.drawable.elf682);
        EMOTION_CLASSIC_MAP.put("[e1f683]", R.drawable.elf683);
        EMOTION_CLASSIC_MAP.put("[e1f684]", R.drawable.elf684);
        EMOTION_CLASSIC_MAP.put("[e1f685]", R.drawable.elf685);
        EMOTION_CLASSIC_MAP.put("[e1f686]", R.drawable.elf686);
        EMOTION_CLASSIC_MAP.put("[e1f687]", R.drawable.elf687);
        EMOTION_CLASSIC_MAP.put("[e1f688]", R.drawable.elf688);
        EMOTION_CLASSIC_MAP.put("[e1f689]", R.drawable.elf689);
        EMOTION_CLASSIC_MAP.put("[e1f690]", R.drawable.elf690);
        EMOTION_CLASSIC_MAP.put("[e1f691]", R.drawable.elf691);
        EMOTION_CLASSIC_MAP.put("[e1f692]", R.drawable.elf692);
        EMOTION_CLASSIC_MAP.put("[e1f693]", R.drawable.elf693);
        EMOTION_CLASSIC_MAP.put("[e1f694]", R.drawable.elf694);
        EMOTION_CLASSIC_MAP.put("[e1f695]", R.drawable.elf695);
        EMOTION_CLASSIC_MAP.put("[e1f696]", R.drawable.elf696);
        EMOTION_CLASSIC_MAP.put("[e1f697]", R.drawable.elf697);
        EMOTION_CLASSIC_MAP.put("[e1f698]", R.drawable.elf698);
        EMOTION_CLASSIC_MAP.put("[e1f699]", R.drawable.elf699);
        EMOTION_CLASSIC_MAP.put("[e1f700]", R.drawable.elf700);
        EMOTION_CLASSIC_MAP.put("[e1f701]", R.drawable.elf701);
        EMOTION_CLASSIC_MAP.put("[e1f702]", R.drawable.elf702);
        EMOTION_CLASSIC_MAP.put("[e1f703]", R.drawable.elf703);
        EMOTION_CLASSIC_MAP.put("[e1f704]", R.drawable.elf704);
        EMOTION_CLASSIC_MAP.put("[e1f705]", R.drawable.elf705);
        EMOTION_CLASSIC_MAP.put("[e1f706]", R.drawable.elf706);
        EMOTION_CLASSIC_MAP.put("[e1f707]", R.drawable.elf707);
        EMOTION_CLASSIC_MAP.put("[e1f708]", R.drawable.elf708);
        EMOTION_CLASSIC_MAP.put("[e1f709]", R.drawable.elf709);
        EMOTION_CLASSIC_MAP.put("[e1f710]", R.drawable.elf710);
        EMOTION_CLASSIC_MAP.put("[e1f711]", R.drawable.elf711);
        EMOTION_CLASSIC_MAP.put("[e1f712]", R.drawable.elf712);
        EMOTION_CLASSIC_MAP.put("[e1f713]", R.drawable.elf713);
        EMOTION_CLASSIC_MAP.put("[e1f714]", R.drawable.elf714);
        EMOTION_CLASSIC_MAP.put("[e1f715]", R.drawable.elf715);
        EMOTION_CLASSIC_MAP.put("[e1f716]", R.drawable.elf716);
        EMOTION_CLASSIC_MAP.put("[e1f717]", R.drawable.elf717);
        EMOTION_CLASSIC_MAP.put("[e1f718]", R.drawable.elf718);
        EMOTION_CLASSIC_MAP.put("[e1f719]", R.drawable.elf719);
        EMOTION_CLASSIC_MAP.put("[e1f720]", R.drawable.elf720);
        EMOTION_CLASSIC_MAP.put("[e1f721]", R.drawable.elf721);
        EMOTION_CLASSIC_MAP.put("[e1f722]", R.drawable.elf722);
        EMOTION_CLASSIC_MAP.put("[e1f723]", R.drawable.elf723);
        EMOTION_CLASSIC_MAP.put("[e1f724]", R.drawable.elf724);
        EMOTION_CLASSIC_MAP.put("[e1f725]", R.drawable.elf725);
        EMOTION_CLASSIC_MAP.put("[e1f726]", R.drawable.elf726);
        EMOTION_CLASSIC_MAP.put("[e1f727]", R.drawable.elf727);
        EMOTION_CLASSIC_MAP.put("[e1f728]", R.drawable.elf728);
        EMOTION_CLASSIC_MAP.put("[e1f729]", R.drawable.elf729);
        EMOTION_CLASSIC_MAP.put("[e1f730]", R.drawable.elf730);
        EMOTION_CLASSIC_MAP.put("[e1f731]", R.drawable.elf731);
        EMOTION_CLASSIC_MAP.put("[e1f732]", R.drawable.elf732);
        EMOTION_CLASSIC_MAP.put("[e1f733]", R.drawable.elf733);
        EMOTION_CLASSIC_MAP.put("[e1f734]", R.drawable.elf734);
        EMOTION_CLASSIC_MAP.put("[e1f735]", R.drawable.elf735);
        EMOTION_CLASSIC_MAP.put("[e1f736]", R.drawable.elf736);
        EMOTION_CLASSIC_MAP.put("[e1f737]", R.drawable.elf737);
        EMOTION_CLASSIC_MAP.put("[e1f738]", R.drawable.elf738);
        EMOTION_CLASSIC_MAP.put("[e1f739]", R.drawable.elf739);

    }
    /**
     * 根据名称获取当前表情图标R值
     * @param EmotionType 表情类型标志符
     * @param name 索引
     * @return
     */
    public static int getImgByName(int EmotionType, String name) {
        
        Integer integer=null;
        switch (EmotionType){
            case EMOTION_CLASSIC_TYPE:
                integer = EMOTION_CLASSIC_MAP.get(name);
                break;
            default:
                Log.e("DEBUG", "the emojiMap is null!!");
                break;
        }
        return integer == null ? -1 : integer;
    }
    /**
     * 根据类型获取表情数据
     * @param EmotionType
     * @return
     */
    public static ArrayMap<String ,Integer> getEmojis(int EmotionType){
        ArrayMap<String ,Integer> Emojimap=null;
        switch (EmotionType){
            case EMOTION_CLASSIC_TYPE:
                Emojimap=EMOTION_CLASSIC_MAP;
                break;
            default:
                Log.e("DEBUG", "the emojiMap is null!!");
                break;
        }
        return Emojimap;
    }





}
