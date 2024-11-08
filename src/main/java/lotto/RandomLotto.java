package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomLotto {
    private static RandomLotto instance;
    private final List<List<Integer>> randomLottos;
    private final Map<Prise, Integer> prizeCount;

    private RandomLotto() {
        randomLottos = new ArrayList<>();
        prizeCount = new HashMap<>();
        for (Prise prise : Prise.values()) {
            prizeCount.put(prise, 0);
        }
    }

    public static RandomLotto getInstance() {
        if (instance == null) {
            instance = new RandomLotto();
        }
        return instance;
    }

    public Map<Prise, Integer> getPrizeCount() {
        return prizeCount;
    }

    public void countCalculator(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 1,000원으로 나누어 떨어져야 합니다.");
        }

        newLottos(money / 1000);
    }

    public List<Integer> pickNewLotto() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public void newLottos(int cnt) {
        for (int i = 0; i < cnt; i++) {
            List<Integer> lotto = pickNewLotto();
            randomLottos.add(lotto);
        }
        new IOManager().printNewLotto(cnt, randomLottos);
    }

    public void lottoCheck(Lotto lotto, int bonusNumber) {
        List<Integer> numbers = lotto.getNumbers();

        for (List<Integer> randomLotto : randomLottos) {
            int cnt = matchCount(randomLotto, numbers);
            boolean bonus = bonusCheck(randomLotto, bonusNumber);
            Prise prise = priseCheck(cnt, bonus);

            if (prise != null) {
                prizeCount.put(prise, prizeCount.get(prise) + 1);
            }
        }
    }

    public Prise priseCheck(int match, boolean bonus) {
        Prise prise = Prise.getRank(match);
        if (prise == Prise.THIRD && bonus) {
            return Prise.SECOND;
        }
        return prise;
    }

    public int matchCount(List<Integer> randomLotto, List<Integer> numbers) {
        int cnt = 0;

        for (int lottoNumber : randomLotto) {
            if (numbers.contains(lottoNumber)) {
                cnt++;
            }
        }

        return cnt;
    }

    public boolean bonusCheck(List<Integer> randomLotto, int bonusNumber) {
        for (int lottoNumber : randomLotto) {
            if (lottoNumber == bonusNumber) {
                return true;
            }
        }
        return false;
    }

    public double winningRateCalculator(int lottoCount) {
        int totalPrize = 0;

        for (Prise prize : Prise.values()) {
            int cnt = prizeCount.getOrDefault(prize, 0);
            int money = prize.getMoney();
            totalPrize += cnt * money;
        }
        return (double) totalPrize / lottoCount * 100;
    }
}
